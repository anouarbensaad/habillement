package com.gpro.consulting.tiers.commun.service.test.impl;
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
import com.gpro.consulting.tiers.commun.domaine.IFamilleProduitDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class FamilleProduitServiceTest {
	@Autowired
	IFamilleProduitDomaine familleProduitDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;
	
	// test si produit    est ajouter
		@Test
		@DirtiesContext
		@Rollback(false)
		public void testCreatFamilleProduit() {
			// Creation d'un objet valeur  produit
			ProduitValue produit = new ProduitValue();
			produit.setDesignation("produit 1");
			// Lancer la création
			//String produitId =produitDomaine.creerProduit(produit);
			// Test et vérifiacation
			//assertNotNull(produitId);
		}
		
		 // test  produit est supprimer
		  @Test
		  @DirtiesContext
		  @Rollback(false)
		  public void testSupprimerfamilleProduit() {
		    // create objet valeur produit
		    ProduitValue pProduitValue = new ProduitValue();
		    pProduitValue.setId(3L);
		    /************** lancer la suppression ****************/
		    familleProduitDomaine.supprimerSousFamilleProduit(1L);
		  }
		  
		  
		  
		public IFamilleProduitDomaine getFamilleProduitDomaine() {
			return familleProduitDomaine;
		}

		public void setFamilleProduitDomaine(IFamilleProduitDomaine familleProduitDomaine) {
			this.familleProduitDomaine = familleProduitDomaine;
		}

		public DataSource getDataSource() {
			return dataSource;
		}

		public void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}
	
	


}
