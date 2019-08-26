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
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.RepresentantValue;
import com.gpro.consulting.tiers.commun.domaine.IPartieInteresseeDomaine;

@ContextConfiguration(locations = {"/spring/application-context.xml", 
  "/spring/config-mt-gpro-commun-service-test.xml",
  "/spring/config-mt-gpro-commun-persistance.xml", "/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class AjouterRepresenatantPartieInteresseeServiceTest {
	// logger
	  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AjouterRepresenatantPartieInteresseeServiceTest.class);
  //injection partieInteresseeDomaine
  @Autowired
  private IPartieInteresseeDomaine partieInteresseeDomaine;
  /** La datasource. */
  @Autowired
  private DataSource dataSource;

	// test ajouter ligne represantant PI
	// =====>entrer : PI + une seule represantant
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testAjouterrepresantantPi() {
		// recherche PI par id
		PartieInteresseValue piModifier = partieInteresseRechercher(5L);
		// creer list represantant
		Set<RepresentantValue> represenantants = new HashSet<RepresentantValue>();

		// remplir list represantant
		RepresentantValue nouveauRep = creerRepresentantPartieInteresse(
				"test@test.com", "22653262", "foction test", "nom et prenom",
				"265665496");
		represenantants.add(nouveauRep);
		
		// affecter list represantant au PI
		piModifier.setRepresentants(represenantants);

		// traitement : modifier PI et ajouter ligne de represantant
		String vIdPIResultant = partieInteresseeDomaine
				.modifierPartieInteressee(piModifier);
		assertNotNull(vIdPIResultant);

		// resultat volue : PI + deux ligne de represantant
		PartieInteresseValue piRechercher = partieInteresseRechercher(Long
				.parseLong(vIdPIResultant));
		// Verifiication des caracteritiuques de represantant PI ajouter  
		for (RepresentantValue vRep : piRechercher.getRepresentants()) {
				assertTrue(vRep.getEmail().equals("test@test.com"));
				LOGGER.info("email : " +vRep.getEmail());
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

	/***** creation represantant PI Value 
	 * @param mail 
	 * @param fax 
	 * @param fonction 
	 * @param nom 
	 * @param telephone ***/
	public RepresentantValue creerRepresentantPartieInteresse(String mail, String fax, 
			String fonction, String nom, String telephone) {
		RepresentantValue vRep = new RepresentantValue();
		vRep.setEmail(mail);
		vRep.setFax(fax);
		vRep.setFonction(fonction);
		vRep.setNom(nom);
		vRep.setTelephone(telephone);
		return vRep;
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
