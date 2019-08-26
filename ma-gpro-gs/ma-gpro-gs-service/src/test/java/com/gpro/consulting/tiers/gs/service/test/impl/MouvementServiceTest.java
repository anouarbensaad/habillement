package com.gpro.consulting.tiers.gs.service.test.impl;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheMouvementValue;
import com.gpro.consulting.tiers.gs.domaine.IMouvementDomaine;
import com.gpro.consulting.tiers.gs.persitance.impl.MouvementPersistanceImpl;
@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-ma-gpro-gs-service-test.xml",
		"/spring/config-ma-gpro-gs-persistance.xml",
		"/spring/config-ma-gpro-gs-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class MouvementServiceTest {
	@Autowired
	IMouvementDomaine mouvementDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;
	
  	private static final Logger LOGGER = LoggerFactory.getLogger(MouvementPersistanceImpl.class);

	
	 // test recherche multi critere
	  @Test
	  @DirtiesContext
	  @Rollback(false)
	  public void testRechercheMultiCritereMouvement() {
	    // Creation d'un objet valeur RechercheMulticritere
	    RechercheMulticritereMouvementValue vRechercheMultiCritere = new RechercheMulticritereMouvementValue();
	    //vRechercheMultiCritere.setMagasin("1");
	    //vRechercheMultiCritere.setResponsable("respo");
      // lancer recherche 
	    ResultatRechecheMouvementValue vResultatRecherche = mouvementDomaine.rechercherMouvementMultiCritere(vRechercheMultiCritere);
	    // Test et vérifiacation
	    assertNotNull(vResultatRecherche);
	    LOGGER.info("nompbre resultat = "+vResultatRecherche.getNombreResultaRechercher() );
	    assertTrue(vResultatRecherche.getNombreResultaRechercher().equals(3L));
	  }


	public IMouvementDomaine getMouvementDomaine() {
		return mouvementDomaine;
	}


	public void setMouvementDomaine(IMouvementDomaine mouvementDomaine) {
		this.mouvementDomaine = mouvementDomaine;
	}


	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
