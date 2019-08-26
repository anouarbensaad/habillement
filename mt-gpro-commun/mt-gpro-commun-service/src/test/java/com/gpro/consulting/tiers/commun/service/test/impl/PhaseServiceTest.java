package com.gpro.consulting.tiers.commun.service.test.impl;

import static org.junit.Assert.assertNotNull;

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
import com.gpro.consulting.tiers.commun.coordination.value.PhaseValue;
import com.gpro.consulting.tiers.commun.domaine.IPhaseDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class PhaseServiceTest {

	@Autowired
	private IPhaseDomaine ebPhaseDomaine;
	@Autowired
	private DataSource dataSource;
	
	/**************** TEST Get Phase *******************/
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testGetPhase() {
	// lancer la recherche d'une Phase
	PhaseValue vPhaseValue = new PhaseValue();
	vPhaseValue.setId(3L);
	PhaseValue pPhaseValue = ebPhaseDomaine.recherchePhaseParId(vPhaseValue);
	// verification si cette Phase existe
	assertNotNull(pPhaseValue);
	}
	/**************** TEST LISTE PhaseS ******************/
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testListerPhase() {
		// lancer affichage de liste Phase
	List<PhaseValue> pPhaseValues = ebPhaseDomaine.listePhase();
		// verification si liste Phase n'est pas nulle
	assertNotNull(pPhaseValues);
	}
	/**************** TEST CREATION Phase ******************/
	/*@Test
	@DirtiesContext
	@Rollback(false)
	public void testCreerePhase() {
		
		// Creation d'un objet valeur Phase
		PhaseValue vPhaseValue = new PhaseValue();
					 vPhaseValue.setDesignation("NEW designation Phase");
		// Lancer la création
		String vIdPhaseResulatant = ebPhaseDomaine.creerPhase(vPhaseValue);

		// Test et vérifiacation
		//		assertNotNull(vIdPhaseResulatant);
	}
	*/
	/**************** TEST SUPPRESSION Phase ******************/
	/*@Test
	@DirtiesContext
	@Rollback(false)
	public void testSupprimerPhase() {

		// create objet valeur Phase
		PhaseValue vPhaseValue = new PhaseValue();
		vPhaseValue.setId(9L);
		ebPhaseDomaine.supprimerPhase(vPhaseValue);

	}*/
	/**************** TEST MODIFICATION Phase ******************/
	
		/*@Test
		@DirtiesContext
		@Rollback(false)
		public void testModifierPhase() {

			// objet valeur Catégorie
			PhaseValue pPhaseValue = new PhaseValue();
			pPhaseValue.setId(3L);
			pPhaseValue.setDesignation("**Modifiée");

			// modifier entity catigorie
			String vIdPhaseResulatant = ebPhaseDomaine.modifierPhase(pPhaseValue);

			// verification si categorie est modifier
			assertNotNull(vIdPhaseResulatant);

		}*/
		
	/************************* Getters & Setters **************************/
	public IPhaseDomaine getEbPhaseDomaine() {
		return ebPhaseDomaine;
	}


	public void setEbPhaseDomaine(IPhaseDomaine ebPhaseDomaine) {
		this.ebPhaseDomaine = ebPhaseDomaine;
	}


	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
