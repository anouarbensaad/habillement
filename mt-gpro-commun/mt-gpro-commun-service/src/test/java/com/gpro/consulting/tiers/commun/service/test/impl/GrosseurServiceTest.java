package com.gpro.consulting.tiers.commun.service.test.impl;

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

import com.gpro.consulting.tiers.commun.coordination.value.GrosseurValue;
import com.gpro.consulting.tiers.commun.domaine.IGrosseurDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class GrosseurServiceTest {

	@Autowired
	private IGrosseurDomaine ebGrosseurDomaine;
	@Autowired
	private DataSource dataSource;
	
	
	/**************** TEST CREATION GROSSEUR ******************/
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testCreereGrosseur() {
		
		// Creation d'un objet valeur Matiere
		GrosseurValue vGrosseurValue = new GrosseurValue();
				   vGrosseurValue.setDesignation("**NEW2");
		// Lancer la création
				String vIdGrosseurResulatant = ebGrosseurDomaine.creerGrosseur(vGrosseurValue);
		// Test et vérifiacation
			assertNotNull(vIdGrosseurResulatant);
	}

	/**************** TEST SUPPRESSION GROSSEUR ******************/
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testSupprimerGrosseur() {

		//ebGrosseurDomaine.supprimerGrosseur(49L);

	}
	/**************** TEST MODIFICATION GROSSEUR ******************/
	
		@Test
		@DirtiesContext
		@Rollback(false)
		public void testModifierGrosseur() {
/*
			// objet valeur Catégorie
			GrosseurValue pGrosseurValue = new GrosseurValue();
			pGrosseurValue.setId(3L);
			pGrosseurValue.setDesignation("Modifiée");

			// modifier entity catigorie
			String vIdGrosseurResulatant = ebGrosseurDomaine.modifierGrosseur(pGrosseurValue);

			// verification si categorie est modifier
			assertNotNull(vIdGrosseurResulatant);
*/
		}
		
	
	/**************** TEST LISTE GROSSEURS ******************/
		@Test
		@DirtiesContext
		@Rollback(true)
		public void testListerGrosseur() {
			// lancer affichage de liste Grosseur
		//List<GrosseurValue> pGrosseurValues = ebGrosseurDomaine.listeGrosseur();
			// verification si liste Grosseur n'est pas nulle
		//assertNotNull(pGrosseurValues);
		}
		
	/************************* Getters & Setters **************************/
	public IGrosseurDomaine getEbGrosseurDomaine() {
		return ebGrosseurDomaine;
	}

	public void setEbGrosseurDomaine(IGrosseurDomaine ebGrosseurDomaine) {
		this.ebGrosseurDomaine = ebGrosseurDomaine;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
