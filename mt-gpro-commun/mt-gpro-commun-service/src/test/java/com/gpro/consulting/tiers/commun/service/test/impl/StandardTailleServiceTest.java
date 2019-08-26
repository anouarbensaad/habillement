package com.gpro.consulting.tiers.commun.service.test.impl;

import static org.junit.Assert.*;

import java.util.List;

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

import com.gpro.consulting.tiers.commun.coordination.value.PhaseValue;
import com.gpro.consulting.tiers.commun.coordination.value.StandardTailleValue;
import com.gpro.consulting.tiers.commun.domaine.IStandardTailleDomaine;
import com.gpro.consulting.tiers.commun.domaine.ITailleDomaine;
import com.gpro.consulting.tiers.commun.persistance.IStandardTaillePersistance;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class StandardTailleServiceTest {

	@Autowired
	private IStandardTailleDomaine ebStandardTailleDomaine;
	@Autowired
	private DataSource dataSource;
	
	/**************** TEST CREATION STANDARDTAILLE ******************/
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testCreereStandardTaille() {
		
		// Creation d'un objet valeur STANDARDTAILLE
		StandardTailleValue vStandardTailleValue = new StandardTailleValue();
					 vStandardTailleValue.setDesignation("** NEW STANDARDTAILLE");
		// Lancer la création
		String vIdStandardTailleResulatant = ebStandardTailleDomaine.creerStandardTaille(vStandardTailleValue);

		// Test et vérifiacation
		assertNotNull(vIdStandardTailleResulatant);
	}
	/**************** TEST LISTE PhaseS ******************/
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testListerStandardTaille() {
		// lancer affichage de liste Phase
	List<StandardTailleValue> pStandardTailleValues = ebStandardTailleDomaine.listeStandardTaille();
		// verification si liste Phase n'est pas nulle
	assertNotNull(pStandardTailleValues);
	}

	/**************** TEST SUPPRESSION StandardTaille ******************/
	/*@Test
	@DirtiesContext
	@Rollback(false)
	public void testSupprimerStandardTaille() {

		// create objet valeur StandardTaille
		StandardTailleValue vStandardTailleValue = new StandardTailleValue();
		vStandardTailleValue.setId(9L);
		ebStandardTailleDomaine.supprimerStandardTaille(vStandardTailleValue);

	}*/
	
	/**************** TEST MODIFICATION StandardTaille ******************/
	/*@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierStandardTaille() {

	// objet valeur Catégorie
		StandardTailleValue pStandardTailleValue = new StandardTailleValue();
		pStandardTailleValue.setId(3L);
		pStandardTailleValue.setDesignation("**Modifiée");

		// modifier entity catigorie
		String vIdStandardTailleResulatant = ebStandardTailleDomaine.modifierStandardTaille(pStandardTailleValue);

		// verification si categorie est modifier
		assertNotNull(vIdStandardTailleResulatant);
	}		*/
			
	/************************* Getters & Setters **************************/
	public IStandardTailleDomaine getEbStandardTailleDomaine() {
		return ebStandardTailleDomaine;
	}

	public void setEbStandardTailleDomaine(
			IStandardTailleDomaine ebStandardTailleDomaine) {
		this.ebStandardTailleDomaine = ebStandardTailleDomaine;
	}


	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}