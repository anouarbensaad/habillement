package com.gpro.consulting.tiers.commun.service.test.produit;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.gpro.consulting.tiers.commun.coordination.value.DocumentProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.domaine.IProduitDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class AjouterDocumentProduitServiceTest {
	// logger
		  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AjouterDocumentProduitServiceTest.class);
	//injection produit domaine 
	@Autowired
	IProduitDomaine produitDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;

	
	// test ajouter ligne document produit
	//=====>entrer : produit  + une seule document 
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testAjouterDocumentProduit() {
		// recherche produit par id
		ProduitValue produitModifier = produitRechercher(5L);
		// Creation d'un objet valeur produit
		// creer list document
		Set<DocumentProduitValue> documentValues = new HashSet<DocumentProduitValue>();

		// remplir list document
		DocumentProduitValue nouveauDocumen1 = creerDocumentProduit(
				"c:/documemnt", 1L);
		documentValues.add(nouveauDocumen1);
		LOGGER.info("path : " + nouveauDocumen1.getPath());
		// affecter list document au produit
		produitModifier.setDocumentProduits(documentValues);

		// traitement : modifier produit et ajouter ligne de document
		String vIdProduitResultant = produitDomaine
				.modifierProduit(produitModifier);
		assertNotNull(vIdProduitResultant);

		// resultat volue : produit + deux ligne de document
		// Vérification du resultat modifier
		ProduitValue produitRechercher = produitRechercher(Long
				.parseLong(vIdProduitResultant));
		// Test et vérifiacation

		// Verifiication des caracteritiuques de document produit
		for (DocumentProduitValue document : produitRechercher
				.getDocumentProduits()) {
			if (document.getId() == 2L) {
				assertTrue(document.getPath().equals("c:/documemnt"));
				break;
			}
		}
		// verification nombre des document
		assertEquals(produitRechercher.getDocumentProduits().size(), 2);

	}
	
	 /*********** rechercher produit par id ********/
		public ProduitValue produitRechercher(Long id) {
			// id produit à rechercher
			ProduitValue produitModifier = produitDomaine.rechercheProduitById(id);
			return produitModifier;
		}

		/***** creation document produit Value ***/
		public DocumentProduitValue creerDocumentProduit(String path,Long typeDocumentId) {
			DocumentProduitValue vDocument = new DocumentProduitValue();
			vDocument.setPath(path);
			vDocument.setTypeDocumentId(typeDocumentId);
			return vDocument;
		}
	
	public IProduitDomaine getProduitDomaine() {
		return produitDomaine;
	}

	public void setProduitDomaine(IProduitDomaine produitDomaine) {
		this.produitDomaine = produitDomaine;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
