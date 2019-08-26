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

import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.domaine.ICouleurDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class CouleurServiceTest {

	@Autowired
	private ICouleurDomaine ebCouleurDomaine;
	@Autowired
	private DataSource dataSource;
	
	/**************** TEST Get Couleur *******************/
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testGetCouleur() {
	// lancer la recherche d'une Couleur
	CouleurValue vCouleurValue = new CouleurValue();
	vCouleurValue.setId(3L);
	CouleurValue pCouleurValue = ebCouleurDomaine.rechercheCouleurParId(vCouleurValue);
	// verification si cette Couleur existe
	assertNotNull(pCouleurValue);
	}
	/**************** TEST LISTE CouleurS ******************/
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testListerCouleur() {
		// lancer affichage de liste Couleur
	List<CouleurValue> pCouleurValues = ebCouleurDomaine.listeCouleur();
		// verification si liste Couleur n'est pas nulle
	assertNotNull(pCouleurValues);
	}

	/**************** TEST CREATION Couleur ******************/
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testCreereCouleur() {
		
		// Creation d'un objet valeur Couleur
		CouleurValue vCouleurValue = new CouleurValue();
				   vCouleurValue.setDesignation("NEW Couleur");
		// Lancer la création
				String vIdCouleurResulatant = ebCouleurDomaine.creerCouleur(vCouleurValue);
		// Test et vérifiacation
			assertNotNull(vIdCouleurResulatant);
	}

	/**************** TEST SUPPRESSION Couleur ******************/
	/*@Test
	@DirtiesContext
	@Rollback(false)
	public void testSupprimerCouleur() {

		// create objet valeur Couleur
		CouleurValue vCouleurValue = new CouleurValue();
		vCouleurValue.setId(9L);
		ebCouleurDomaine.supprimerCouleur(vCouleurValue);

	}*/
	/**************** TEST MODIFICATION Couleur ******************/
	
		/*@Test
		@DirtiesContext
		@Rollback(false)
		public void testModifierCouleur() {

			// objet valeur Catégorie
			CouleurValue pCouleurValue = new CouleurValue();
			pCouleurValue.setId(3L);
			pCouleurValue.setDesignation("Modifiée");

			// modifier entity catigorie
			String vIdCouleurResulatant = ebCouleurDomaine.modifierCouleur(pCouleurValue);

			// verification si categorie est modifier
			assertNotNull(vIdCouleurResulatant);

		}
		
	*/
	
	/************************* Getters & Setters **************************/
	public ICouleurDomaine getEbCouleurDomaine() {
		return ebCouleurDomaine;
	}


	public void setEbCouleurDomaine(ICouleurDomaine ebCouleurDomaine) {
		this.ebCouleurDomaine = ebCouleurDomaine;
	}


	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
