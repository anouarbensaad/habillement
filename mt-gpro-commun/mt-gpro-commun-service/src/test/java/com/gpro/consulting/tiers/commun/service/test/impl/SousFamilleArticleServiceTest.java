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
import com.gpro.consulting.tiers.commun.coordination.value.CategorieValue;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.domaine.ISousFamilleArticleDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class SousFamilleArticleServiceTest {

	@Autowired
	private ISousFamilleArticleDomaine sousFamilleArticleDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;

	// test si Famille  article  est ajouter
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testCreereSousFamilleArticle() {
		// Creation d'un objet valeur sous famille article
				SousFamilleArticleValue sousFamilleArticle = new SousFamilleArticleValue();
				sousFamilleArticle.setDesignation("sous famille 7");
				sousFamilleArticle.setIdFamilleArticle(7L);
				// Lancer la création
				String vIdSousFamilleArticle =sousFamilleArticleDomaine.creerSousFamilleArticle(sousFamilleArticle);
				// Test et vérifiacation
				//assertNotNull(vIdSousFamilleArticle);
				
	}

	// test si categorie partie interesse est supprimer
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testSupprimerSousFamille() {

		//sousFamilleArticleDomaine.supprimerSousFamilleArticle(4L);
	}

	// test si categorie partie interesse est modifier
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierModifierSousFamilleArticle() {
		// objet valeur Catégorie
		SousFamilleArticleValue pSousFamilleArticleValue = new SousFamilleArticleValue();
		pSousFamilleArticleValue.setId(4L);
		pSousFamilleArticleValue.setDesignation("test modifier sous famille");
		// modifier entity faamille
		String vIdSousFamilleResult = sousFamilleArticleDomaine.modifierSousFamilleArticle(pSousFamilleArticleValue);
		// verification si famille est modifier
		assertNotNull(vIdSousFamilleResult);
	}

	// test si categorie partie interesse est supprimer
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testRechercheCategoriePartieInteresseeParId() {

		// objet valeur Catégorie
		CategorieValue pCategoriePartieInteresseValue = new CategorieValue();
		pCategoriePartieInteresseValue.setId(24L);
		// modifier entity catigorie
		//CategorieValue vIdCategorieResulatant = categoriePartieInteresseeDomaine.rechercheCategoriePartieInteresseeParId(pCategoriePartieInteresseValue);

		// verification si categorie est modifier
		//assertTrue(vIdCategorieResulatant.getId().toString().trim().equals("24"));

	}

	// afficher liste categorie partie interesse 
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testListerCategoriePartieInteressee() {
		// lancer affichage de liste categorie
		//List<CategorieValue> pCategorieValues = categoriePartieInteresseeDomaine
				//.listeCategoriePartieInteressee();
		// verification si liste categorie est afficher
		///assertNotNull(pCategorieValues);

	}



	

	public ISousFamilleArticleDomaine getSousFamilleArticleDomaine() {
		return sousFamilleArticleDomaine;
	}

	public void setSousFamilleArticleDomaine(
			ISousFamilleArticleDomaine sousFamilleArticleDomaine) {
		this.sousFamilleArticleDomaine = sousFamilleArticleDomaine;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
