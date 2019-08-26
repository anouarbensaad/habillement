package com.gpro.consulting.tiers.gs.service.test.impl;
import static org.junit.Assert.assertNotNull;

import java.util.List;

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

import com.gpro.consulting.tiers.gs.coordination.value.MagasinValue;
import com.gpro.consulting.tiers.gs.coordination.value.RaisonMouvementStockValue;
import com.gpro.consulting.tiers.gs.domaine.IMagasinDomaine;
import com.gpro.consulting.tiers.gs.domaine.IRaisonMouvementDomaine;
@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-ma-gpro-gs-service-test.xml",
		"/spring/config-ma-gpro-gs-persistance.xml",
		"/spring/config-ma-gpro-gs-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class RaisonMouvementServiceTest {
	@Autowired
	IRaisonMouvementDomaine raisonMouvementDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;
	
	// test si magasin    est ajouter
		@Test
		@DirtiesContext
		@Rollback(false)
		public void testCreatRaisonMouvement() {
			RaisonMouvementStockValue raison =new RaisonMouvementStockValue();
			raison.setDesignation("raison28/07/2015");
			  String vIdRaisonResultant = raisonMouvementDomaine.creerRaisonMouvementStock(raison);
			    // Test et vérifiacation
			    assertNotNull(vIdRaisonResultant);
		}
	
		 // test  magasin est supprimer
		  @Test
		  @DirtiesContext
		  @Rollback(false)
		  public void testSupprimerMagasin() {
		 
		  }
		  
		// test magasin est modifier
		  @Test
		  @DirtiesContext
		  @Rollback(false)
		  public void testModifierRaisonMouvement() {
			  RaisonMouvementStockValue raison =new RaisonMouvementStockValue();
				raison.setDesignation("raison28/07/2015");
				  String vIdRaisonResultant = raisonMouvementDomaine.modifierRaisonMouvementStock(raison);
				    // Test et vérifiacation
				    assertNotNull(vIdRaisonResultant);
		  }

		  // test recherche d'une liste
		  @Test
		  @DirtiesContext
		  @Rollback(false)
		  public void testListerRaisonMouvement() {
		    List <RaisonMouvementStockValue > list = raisonMouvementDomaine.listeRaisonMouvementStock();
		    assertNotNull(list);
		  }
		  
	
	
	public IRaisonMouvementDomaine getRaisonMouvementDomaine() {
			return raisonMouvementDomaine;
		}

		public void setRaisonMouvementDomaine(
				IRaisonMouvementDomaine raisonMouvementDomaine) {
			this.raisonMouvementDomaine = raisonMouvementDomaine;
		}

	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
