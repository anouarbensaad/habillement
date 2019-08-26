package com.gpro.consulting.tiers.commun.service.test.article.recherche;

import static org.junit.Assert.*;

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
import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.domaine.IArticleDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class RechercheMultiCritereEntreDeuxPrixArticleServiceTest {
	// injection article domaine
	@Autowired
	private IArticleDomaine articleDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;

	
	/************************* test recherche multi critere article ******************************/

	// test recherche multi critere entre Deux Prix
		@Test
		@DirtiesContext
		@Rollback(true)
		public void testRechercheMultiCritereArticleEntreDeuxPrix() {
			// etrer :  deux prix inferieur et prix superieur  
			RecherecheMulticritereArticleValue vRechercheMultiCritere =  new RecherecheMulticritereArticleValue();
			vRechercheMultiCritere.setPrix_inf(2.0);
			vRechercheMultiCritere.setPrix_sup(10.0);
			// traitement : recherche article entre deux prix 
			ResultatRechecheArticleValue vResultatRecherche = articleDomaine.rechercherArticleMultiCritere(vRechercheMultiCritere);
			// resultat volue : afficher tout article ayant prix entre les valeurs saisie 
			// Test et vérifiacation
			assertNotNull(vResultatRecherche);
		}
	
	public IArticleDomaine getArticleDomaine() {
		return articleDomaine;
	}

	public void setArticleDomaine(IArticleDomaine articleDomaine) {
		this.articleDomaine = articleDomaine;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
