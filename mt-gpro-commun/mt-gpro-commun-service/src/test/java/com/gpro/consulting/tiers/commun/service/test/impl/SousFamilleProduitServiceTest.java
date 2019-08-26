package com.gpro.consulting.tiers.commun.service.test.impl;
import static org.junit.Assert.*;

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

import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.domaine.IProduitDomaine;
import com.gpro.consulting.tiers.commun.domaine.ISousFamilleArticleDomaine;
import com.gpro.consulting.tiers.commun.domaine.ISousFamilleProduitDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class SousFamilleProduitServiceTest {
	@Autowired
	ISousFamilleProduitDomaine sousFamilleProduitDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;
	
	// test si produit    est ajouter
		@Test
		@DirtiesContext
		@Rollback(false)
		public void testCreatSousFamilleProduitProduit() {
			// Creation d'un objet valeur  produit
			SousFamilleProduitValue produit = new SousFamilleProduitValue();
			produit.setDesignation("SousFamilleProduit 1");
			
			// Lancer la création
			String produitId =sousFamilleProduitDomaine.creerSousFamilleProduit(produit);
			// Test et vérifiacation
			assertNotNull(produitId);
		}

		public ISousFamilleProduitDomaine getSousFamilleProduitDomaine() {
			return sousFamilleProduitDomaine;
		}

		public void setSousFamilleProduitDomaine(ISousFamilleProduitDomaine sousFamilleProduitDomaine) {
			this.sousFamilleProduitDomaine = sousFamilleProduitDomaine;
		}

		public DataSource getDataSource() {
			return dataSource;
		}

		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}
	
	

	
}
