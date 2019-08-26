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

import com.gpro.consulting.tiers.commun.coordination.value.MetrageValue;
import com.gpro.consulting.tiers.commun.domaine.IMetrageDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class MetrageServiceTest {

	@Autowired
	private IMetrageDomaine ebMetrageDomaine;
	
	@Autowired
	private DataSource dataSource;

	/**************** TEST CREATION METRAGE ******************/
	/*@Test
	@DirtiesContext
	@Rollback(false)
	public void testCreereMetrage() {
		
		// Creation d'un objet valeur Metrage
		MetrageValue vMetrageValue = new MetrageValue();
					 vMetrageValue.setDesignation("NEW designation METRAGE");
		// Lancer la création
		String vIdMetrageResulatant = ebMetrageDomaine.creerMetrage(vMetrageValue);

		// Test et vérifiacation
				assertNotNull(vIdMetrageResulatant);
	}*/
	
	/**************** TEST MODIFICATION MATIERE ******************/
	
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierMetrage() {

		// objet valeur MetrageValue
		MetrageValue pMetrageValue = new MetrageValue();
		pMetrageValue.setId(2L);
		pMetrageValue.setDesignation("** MODIFIEE");

		// modifier entity MatiereArticleValue
		String vIdpMetrageValueResulatant = ebMetrageDomaine.modifierMetrage(pMetrageValue);

		// verification si vIdpMatiereArticleValueResulatant est modifiée
		assertNotNull(vIdpMetrageValueResulatant);

	}
	
	/**************** TEST LISTER  METRAGE ******************/
	/*@Test
	@DirtiesContext
	@Rollback(true)
	public void testListerMetrage() {
		// lancer affichage de liste Metrage
	List<MetrageValue> pMetrageValueValues = ebMetrageDomaine.listeMetrage();
		// verification si liste n'est pas nulle
	assertNotNull(pMetrageValueValues);
	}
*/
	
	/******************* Getter & Setter ***********************/
	public IMetrageDomaine getEbMetrageDomaine() {
		return ebMetrageDomaine;
	}

	public void setEbMetrageDomaine(IMetrageDomaine ebMetrageDomaine) {
		this.ebMetrageDomaine = ebMetrageDomaine;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	
}
