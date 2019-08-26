package com.gpro.consulting.tiers.gs.service.test.impl;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereEntiteStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheEntiteStockStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechercheEntiteStockMouvementValue;
import com.gpro.consulting.tiers.gs.domaine.IEntiteStockDomaine;
@ContextConfiguration(locations = {"/spring/application-context.xml",
		"/spring/config-ma-gpro-gs-service-test.xml",
		"/spring/config-ma-gpro-gs-persistance.xml",
		"/spring/config-ma-gpro-gs-domaine.xml"})
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class EntiteStockServiceTest {
	@Autowired
	IEntiteStockDomaine entiteStockDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;
	
	 // test recherche multi critere
	  @Test
	  @DirtiesContext
	  @Rollback(false)
	  public void testRechercheMultiCritereEntiteStock() {
	    // Creation d'un objet valeur RechercheMulticritere
	    RechercheMulticritereEntiteStockValue vRechercheMultiCritere = new RechercheMulticritereEntiteStockValue();
	    vRechercheMultiCritere.setQuantite(10.0);
	    vRechercheMultiCritere.setOperateurQuantite("=");
      // lancer recherche 
	    ResultatRechecheEntiteStockStockValue vResultatRecherche = entiteStockDomaine.rechercherEntiteStockMultiCritere(vRechercheMultiCritere);
	    // Test et vérifiacation
	    assertNotNull(vResultatRecherche);
	    assertTrue(vResultatRecherche.getNombreResultaRechercher().equals(1L));
	  }

	  // test recherche multi critere Entite Stock Mouvement
	  @Test
	  @DirtiesContext
	  @Rollback(false)
	  public void testRechercheMultiCritereEntiteStockMouvement() {
	    // Creation d'un objet valeur RechercheMulticritere
	    RechercheMulticritereEntiteStockValue vRechercheMultiCritere = new RechercheMulticritereEntiteStockValue();
	    vRechercheMultiCritere.setQuantite(10.0);
	    vRechercheMultiCritere.setOperateurQuantite("=");
	    vRechercheMultiCritere.setRefArticle("ref");
      // lancer recherche 
	    ResultatRechercheEntiteStockMouvementValue vResultatRecherche = entiteStockDomaine.rechercherEntiteStockMouvement(vRechercheMultiCritere);
	    // Test et vérifiacation
	    assertNotNull(vResultatRecherche);
	    assertTrue(vResultatRecherche.getNombreResultaRechercher().equals(1L));
	  }

	
	  
	  
	public IEntiteStockDomaine getEntiteStockDomaine() {
		return entiteStockDomaine;
	}


	public void setEntiteStockDomaine(IEntiteStockDomaine entiteStockDomaine) {
		this.entiteStockDomaine = entiteStockDomaine;
	}


	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
