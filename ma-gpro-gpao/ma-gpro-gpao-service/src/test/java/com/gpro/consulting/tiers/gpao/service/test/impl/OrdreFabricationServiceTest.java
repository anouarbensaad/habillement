/**
 * 
 */
package com.gpro.consulting.tiers.gpao.service.test.impl;

import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.gpro.consulting.tiers.gpao.coordination.value.CompositionOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.domaine.IOrdreFabricationDomaine;

/**
 * @author $Ameni
 *
 */
@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-ma-gpro-gpao-service-test.xml",
		"/spring/config-ma-gpro-gpao-persistance.xml",
		"/spring/config-ma-gpro-gpao-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class OrdreFabricationServiceTest {
	@Autowired
	IOrdreFabricationDomaine ordreFabricationDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;

	// test recherche d'une liste
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testListerOrdreFabrication() {
		List<OrdreFabricationValue> list = ordreFabricationDomaine
				.listeOrdreFabrication();
		assertNotNull(list);
	}

	// test si ordre est ajouté
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testCreatOrdreFabrication() {
		// creer list Composition
		Set<CompositionOfValue> vCompositionOFValues = new HashSet<CompositionOfValue>();
		
		CompositionOfValue vComposition = new CompositionOfValue();
		vComposition.setQuantite(3L);
		vComposition.setCommandeId(6L);
		//Affectation de CompositionOfValue to the Set
		vCompositionOFValues.add(vComposition);
		
		//creer OrdreFabrication
		OrdreFabricationValue vOrdre = new OrdreFabricationValue();
		vOrdre.setNumero("ordre15/10/2015");
		vOrdre.setCompositionsOF(vCompositionOFValues);
		String vIdOrdreResultant = ordreFabricationDomaine
				.creerOrdreFabrication(vOrdre);

		// Test et vérifiacation
		assertNotNull(vIdOrdreResultant);
	}
	
	

	/*********** Getter & Setter *************/
	/**
	 * @return the ordreFabricationDomaine
	 */
	public IOrdreFabricationDomaine getOrdreFabricationDomaine() {
		return ordreFabricationDomaine;
	}

	/**
	 * @param ordreFabricationDomaine
	 *            the ordreFabricationDomaine to set
	 */
	public void setOrdreFabricationDomaine(
			IOrdreFabricationDomaine ordreFabricationDomaine) {
		this.ordreFabricationDomaine = ordreFabricationDomaine;
	}

	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource
	 *            the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
