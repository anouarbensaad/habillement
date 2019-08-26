package com.gpro.consulting.tiers.commun.service.test.partieInteresse.modifier;
import static org.junit.Assert.*;
import java.util.GregorianCalendar;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.domaine.IPartieInteresseeDomaine;

@ContextConfiguration(locations = {"/spring/application-context.xml", 
  "/spring/config-mt-gpro-commun-service-test.xml",
  "/spring/config-mt-gpro-commun-persistance.xml", "/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class ModifierCaracteristiquePartieInteresseeServiceTest {
	
  //injection partieInteresseeDomaine
  @Autowired
  private IPartieInteresseeDomaine partieInteresseeDomaine;
  /** La datasource. */
  @Autowired
  private DataSource dataSource;
 
	// entrer : produit avec caracteristique non modifier
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierCaracteristiquePartieInteressee() {
		// recheercher PI à modifier
		PartieInteresseValue partieIntersseModifier = partieInteresseRechercher(5L);
		// modifier les caracteristique
		partieIntersseModifier.setReference("PI Test 12/08/2015 ");
		partieIntersseModifier.setRaisonSociale("raison  ");
		partieIntersseModifier.setAbreviation("test");
		partieIntersseModifier.setActif(true);
		partieIntersseModifier.setActivite("production");
		partieIntersseModifier.setAdresse("sousse");
		partieIntersseModifier.setCategoriePartieInteressee(1L);
		partieIntersseModifier.setCodeDouane("4025127");
		partieIntersseModifier.setDateIntroduction(new GregorianCalendar(2013,10, 28));
		partieIntersseModifier.setEmail("email@gmail.com");
		partieIntersseModifier.setFamillePartieInteressee(1L);
		partieIntersseModifier.setFax("1472");
		partieIntersseModifier.setMatriculeFiscal("matricule 1");
		partieIntersseModifier.setRegimeCommercial("regime ");
		partieIntersseModifier.setSitePartieInteressee(1L);
		partieIntersseModifier.setTelephone("736512477412");
		partieIntersseModifier.setTypePartieInteressee(1L);

		// entrer : produit avec caracteristique non modifier
		String vIdPartieInterseResulatant = partieInteresseeDomaine
				.modifierPartieInteressee(partieIntersseModifier);

		// resultat volue : produit avec caracteristique modifier
		PartieInteresseValue vPiId = new PartieInteresseValue();
		vPiId.setId(Long.parseLong(vIdPartieInterseResulatant));
		PartieInteresseValue partieInteresseVerifier = partieInteresseeDomaine
				.recherchePartieInteresseeParId(vPiId);

		// verification caracteristique PI sont modifier
		assertNotNull(vIdPartieInterseResulatant);
		assertTrue(partieInteresseVerifier.getAbreviation().equals("test"));
		assertTrue(partieInteresseVerifier.getActivite().equals(""));
		assertTrue(partieInteresseVerifier.getAdresse().equals(""));
		assertTrue(partieInteresseVerifier.getCodeDouane().equals(""));
		assertTrue(partieInteresseVerifier.getEmail().equals(""));
		assertTrue(partieInteresseVerifier.getFax().equals(""));
		assertTrue(partieInteresseVerifier.getMatriculeFiscal().equals(""));
		assertTrue(partieInteresseVerifier.getRaisonSociale().equals(""));
		assertTrue(partieInteresseVerifier.getReference().equalsIgnoreCase(""));
		assertTrue(partieInteresseVerifier.getRegimeCommercial().equals(""));
		assertTrue(partieInteresseVerifier.getTelephone().equals(""));
		fail("erreur modification   " + vIdPartieInterseResulatant);
	}

	/*********** rechercher PI par id ********/
	public PartieInteresseValue partieInteresseRechercher(Long id) {
		// id PI à rechercher
		PartieInteresseValue vPiId = new PartieInteresseValue();
		vPiId.setId(id);
		PartieInteresseValue vPi = partieInteresseeDomaine
				.recherchePartieInteresseeParId(vPiId);
		return vPi;
	}

	public IPartieInteresseeDomaine getPartieInteresseeDomaine() {
		return partieInteresseeDomaine;
	}

	public void setPartieInteresseeDomaine(
			IPartieInteresseeDomaine partieInteresseeDomaine) {
		this.partieInteresseeDomaine = partieInteresseeDomaine;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
	
	  public void setDataSource(DataSource dataSource) {
	    this.dataSource = dataSource;
	  }

}
