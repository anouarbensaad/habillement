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

import com.gpro.consulting.tiers.commun.coordination.value.MatiereArticleValue;
import com.gpro.consulting.tiers.commun.domaine.IMatiereDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class MatiereServiceTest {
	
	@Autowired
	private IMatiereDomaine ebMatiereDomaine;
	
	@Autowired
	private DataSource dataSource;
	
	/**************** TEST Get Matiere *******************/
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testGetMatiere() {
	// lancer la recherche d'une Matiere
	MatiereArticleValue vMatiereValue = new MatiereArticleValue();
	vMatiereValue.setId(3L);
	MatiereArticleValue pMatiereValue = ebMatiereDomaine.rechercheMatiereParId(vMatiereValue);
	// verification si cette matiere existe
	assertNotNull(pMatiereValue);
	}
	
	/**************** TEST LISTE MatiereS ******************/
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testListerMatiere() {
		// lancer affichage de liste Matiere
	List<MatiereArticleValue> pMatiereValues = ebMatiereDomaine.listeMatiere();
		// verification si liste Matiere n'est pas nulle
	assertNotNull(pMatiereValues);
	}
	
	/**************** TEST CREATION Matiere ******************/
	/*@Test
	@DirtiesContext
	@Rollback(false)
	public void testCreereMatiere() {
		
		// Creation d'un objet valeur Matiere
		MatiereArticleValue vMatiereValue = new MatiereArticleValue();
				   vMatiereValue.setDesignation("**NEW");
		// Lancer la création
				String vIdMatiereResulatant = ebMatiereDomaine.creerMatiere(vMatiereValue);
		// Test et vérifiacation
			assertNotNull(vIdMatiereResulatant);
	}*/

	/**************** TEST SUPPRESSION Matiere ******************/
	/*@Test
	@DirtiesContext
	@Rollback(false)
	public void testSupprimerMatiere() {

		// create objet valeur Matiere
		MatiereValue vMatiereValue = new MatiereValue();
		vMatiereValue.setId(9L);
		ebMatiereDomaine.supprimerMatiere(vMatiereValue);

	}*/
	/**************** TEST MODIFICATION Matiere ******************/
	/*
		@Test
		@DirtiesContext
		@Rollback(false)
		public void testModifierMatiere() {

			// objet valeur Catégorie
			MatiereArticleValue pMatiereValue = new MatiereArticleValue();
			pMatiereValue.setId(3L);
			pMatiereValue.setDesignation("Modifiée");

			// modifier entity catigorie
			String vIdMatiereResulatant = ebMatiereDomaine.modifierMatiere(pMatiereValue);

			// verification si categorie est modifier
			assertNotNull(vIdMatiereResulatant);

		}
		
	/************************* Getters & Setters **************************/
	public IMatiereDomaine getEbMatiereDomaine() {
		return ebMatiereDomaine;
	}
	public void setEbMatiereDomaine(IMatiereDomaine ebMatiereDomaine) {
		this.ebMatiereDomaine = ebMatiereDomaine;
	}
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
