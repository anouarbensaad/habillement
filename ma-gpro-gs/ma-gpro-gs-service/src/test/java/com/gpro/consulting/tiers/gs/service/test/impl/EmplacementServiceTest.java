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
import com.gpro.consulting.tiers.gs.coordination.value.EmplacementValue;
import com.gpro.consulting.tiers.gs.domaine.IEmplacementDomaine;
@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-ma-gpro-gs-service-test.xml",
		"/spring/config-ma-gpro-gs-persistance.xml",
		"/spring/config-ma-gpro-gs-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class EmplacementServiceTest {
	@Autowired
	IEmplacementDomaine emplacementDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;
	
	// test si magasin    est ajouter
		@Test
		@DirtiesContext
		@Rollback(false)
		public void testCreatEmplacement() {
			EmplacementValue emplacement =new EmplacementValue();
			emplacement.setDesignation("emplacement28/07/2015");
			  String vIdEmplacementResultant = emplacementDomaine.creerEmplacement(emplacement);
			    // Test et vérifiacation
			    assertNotNull(vIdEmplacementResultant);
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
		  public void testModifierEmplacement() {
			  EmplacementValue emplacement =new EmplacementValue();
				emplacement.setDesignation("emplacement28/07/2015");
				  String vIdEmplacementResultant = emplacementDomaine.modifierEmplacement(emplacement);
				    // Test et vérifiacation
				    assertNotNull(vIdEmplacementResultant);

		  }

		  // test recherche d'une liste
		  @Test
		  @DirtiesContext
		  @Rollback(false)
		  public void testListerEmplacement() {
		    List <EmplacementValue > list = emplacementDomaine.listeEmplacementValue();
		    assertNotNull(list);
		  }
		  
	
	
	public IEmplacementDomaine getEmplacementDomaine() {
			return emplacementDomaine;
		}

		public void setEmplacementDomaine(IEmplacementDomaine emplacementDomaine) {
			this.emplacementDomaine = emplacementDomaine;
		}

	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
