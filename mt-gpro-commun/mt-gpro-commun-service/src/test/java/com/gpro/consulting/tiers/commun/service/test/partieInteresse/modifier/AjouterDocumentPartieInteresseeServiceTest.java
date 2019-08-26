package com.gpro.consulting.tiers.commun.service.test.partieInteresse.modifier;
import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.gpro.consulting.tiers.commun.coordination.value.DocumentValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.domaine.IPartieInteresseeDomaine;

@ContextConfiguration(locations = {"/spring/application-context.xml", 
  "/spring/config-mt-gpro-commun-service-test.xml",
  "/spring/config-mt-gpro-commun-persistance.xml", "/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class AjouterDocumentPartieInteresseeServiceTest {
	// logger
	  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AjouterDocumentPartieInteresseeServiceTest.class);
  //injection partieInteresseeDomaine
  @Autowired
  private IPartieInteresseeDomaine partieInteresseeDomaine;
  /** La datasource. */
  @Autowired
  private DataSource dataSource;


//test ajouter ligne document PI
	//=====>entrer : PI  + une seule document 
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testAjouterDocumentPi() {
		// recherche PI par id
		PartieInteresseValue piModifier = partieInteresseRechercher(5L);
		// Creation d'un objet valeur PI
		// creer list document
		Set<DocumentValue> documentValues = new HashSet<DocumentValue>();

		// remplir list document
		DocumentValue nouveauDocumen1 = creerDocumentPartieInteresse("c:/documemnt", 1L);
		documentValues.add(nouveauDocumen1);
		LOGGER.info("path : " + nouveauDocumen1.getPath());
		// affecter list document au PI
		piModifier.setDocuments(documentValues);

		// traitement : modifier PI et ajouter ligne de document
		String vIdPIResultant = partieInteresseeDomaine.modifierPartieInteressee(piModifier); 
		assertNotNull(vIdPIResultant);

		// resultat volue : PI + deux ligne de document
		PartieInteresseValue piRechercher = partieInteresseRechercher(Long.parseLong(vIdPIResultant));
		// Verifiication des caracteritiuques de document PI ajouter 
		for (DocumentValue document : piRechercher.getDocuments()) {
				assertTrue(document.getPath().equals("c:/documemnt"));
				break;
		}

	}
	
	
	
  
  /*********** rechercher PI par id ********/
	public PartieInteresseValue partieInteresseRechercher(Long id) {
		// id PI à rechercher
		PartieInteresseValue vPiId= new PartieInteresseValue();
		vPiId.setId(id);
		PartieInteresseValue vPi = partieInteresseeDomaine.recherchePartieInteresseeParId(vPiId);
		return vPi;
	}

	/***** creation document PI Value ***/
	public DocumentValue creerDocumentPartieInteresse(String path,Long typeDocumentId) {
		DocumentValue vDocument = new DocumentValue();
		vDocument.setPath(path);
		vDocument.setTypeDocument(typeDocumentId);
		return vDocument;
	}
	
	
	  public IPartieInteresseeDomaine getPartieInteresseeDomaine() {
	    return partieInteresseeDomaine;
	  }
	
	  public void setPartieInteresseeDomaine(IPartieInteresseeDomaine partieInteresseeDomaine) {
	    this.partieInteresseeDomaine = partieInteresseeDomaine;
	  }
	
	  public DataSource getDataSource() {
	    return dataSource;
	  }
	
	  public void setDataSource(DataSource dataSource) {
	    this.dataSource = dataSource;
	  }

}
