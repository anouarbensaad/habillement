package com.gpro.consulting.tiers.commun.service.test.produit;
import static org.junit.Assert.*;
import java.util.GregorianCalendar;
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
import com.gpro.consulting.tiers.commun.domaine.IProduitDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class ModifierCaracteristiqueProduitServiceTest {
	//injection produit domaine 
	@Autowired
	IProduitDomaine produitDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;

	// test caracteristique produit est modifier
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierCaracteristiqueProduit() {
		// recherche produit à modifier par id
		ProduitValue produitModifier = produitDomaine.rechercheProduitById(5L);
		// entrer : produit avec caracteristique non modifier
		produitModifier.setDesignation("modifier produit totale");
		produitModifier.setReference("modifier reference totale");
		produitModifier.setSiteId(1L);
		produitModifier
				.setDateIntroduction(new GregorianCalendar(2013, 10, 28));
		produitModifier.setPartieIntersseId(1L);
		produitModifier.setSousFamilleId(1L);

		// traitement : modifier les caracteristiques de produit
		String vIdProduitResultant = produitDomaine
				.modifierProduit(produitModifier);
		assertNotNull(vIdProduitResultant);

		// resultat volue : produit avec caracteristique modifier
		ProduitValue produitVerifier = produitDomaine.rechercheProduitById(Long
				.parseLong(vIdProduitResultant));

		// verification caracteristique produit sont modifier
		assertTrue(produitVerifier.getDesignation().equals(
				"modifier produit totale"));
		assertTrue(produitVerifier.getReference().equals(
				"modifier reference totale"));
		assertTrue(produitVerifier.getSiteId() == 1L);
		// assertTrue(produitVerifier.getDateIntroduction()==date);
		assertTrue(produitVerifier.getPartieIntersseId() == 1L);
		assertTrue(produitVerifier.getSousFamilleId() == 1L);
		fail("erreur modifcation  " + vIdProduitResultant);
	}

	public IProduitDomaine getProduitDomaine() {
		return produitDomaine;
	}

	public void setProduitDomaine(IProduitDomaine produitDomaine) {
		this.produitDomaine = produitDomaine;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
