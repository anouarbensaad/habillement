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

import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.domaine.ITailleDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class TailleServiceTest {

	@Autowired
	private ITailleDomaine ebTailleDomaine;
	@Autowired
	private DataSource dataSource;
	
	/**************** TEST Get Taille *******************/
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testGetTaille() {
	// lancer la recherche d'une Taille
	TailleValue vTailleValue = new TailleValue();
	vTailleValue.setId(6L);
	TailleValue pTailleValue = ebTailleDomaine.rechercheTailleParId(vTailleValue);
	// verification si cette Taille existe
	assertNotNull(pTailleValue);
	}
	/**************** TEST LISTE TailleS ******************/
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testListerTaille() {
		// lancer affichage de liste Taille
	List<TailleValue> pTailleValues = ebTailleDomaine.listeTaille();
		// verification si liste Taille n'est pas nulle
	assertNotNull(pTailleValues);
	}
	
	/**************** TEST CREATION TAILLE ******************/
/*	@Test
	@DirtiesContext
	@Rollback(false)
	public void testCreereTaille() {
		
		// Creation d'un objet valeurTaille
		TailleValue vTailleValue = new TailleValue();
		vTailleValue.setDesignation("**NEW Taille");
		vTailleValue.setEb_sttaille_id(2L);
		// Lancer la création
		String vIdTailleResulatant = ebTailleDomaine.creerTaille(vTailleValue);

		// Test et vérifiacation
		assertNotNull(vIdTailleResulatant);
	}*/

	/************************* Getters & Setters **************************/
	public ITailleDomaine getEbTailleDomaine() {
		return ebTailleDomaine;
	}

	public void setEbTailleDomaine(ITailleDomaine ebTailleDomaine) {
		this.ebTailleDomaine = ebTailleDomaine;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}