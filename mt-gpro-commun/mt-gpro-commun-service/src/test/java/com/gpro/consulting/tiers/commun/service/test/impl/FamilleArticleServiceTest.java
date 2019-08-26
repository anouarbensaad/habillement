package com.gpro.consulting.tiers.commun.service.test.impl;

import static org.junit.Assert.*;

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

import com.gpro.consulting.tiers.commun.coordination.value.CategorieValue;
import com.gpro.consulting.tiers.commun.coordination.value.FamilleArticleValue;
import com.gpro.consulting.tiers.commun.domaine.IFamilleArticleDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class FamilleArticleServiceTest {

	@Autowired
	private IFamilleArticleDomaine familleArticleDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;

	// test si Famille  article  est ajouter
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testCreereFamilleArticle() {
		// Creation d'un objet valeur famille article
		FamilleArticleValue familleArticle = new FamilleArticleValue();
		familleArticle.setDesignation("famille 1");
		// Lancer la création
		//String vIdFamilleArticle =familleArticleDomaine.creerFamilleArticle(familleArticle);
		// Test et vérifiacation
		//assertNotNull(vIdFamilleArticle);
	}
	// test si categorie partie interesse est supprimer
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testSupprimerFamillePartieInteressee() {
		// create objet valeur atégorie
		//CategorieValue pCategoriePartieInteresseValue = new CategorieValue();
		//pCategoriePartieInteresseValue.setId(23L);
		// categoriePartieInteresseeDomaine.supprimerCategoriePartieInteressee(pCategoriePartieInteresseValue);
	}
	// test si categorie partie interesse est modifier
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierModifierPartieInteressee() {
		// objet valeur Catégorie
		FamilleArticleValue pFamilleArticleValue = new FamilleArticleValue();
		pFamilleArticleValue.setId(4L);
		pFamilleArticleValue.setDesignation("test famille 254564");
		// modifier entity faamille
		//String vIdFamilleResulatant = familleArticleDomaine.modifierFamilleArticle(pFamilleArticleValue);
		// verification si famille est modifier
		//assertNotNull(vIdFamilleResulatant);
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
	public void testListerFamilleArticle() {
		// lancer affichage de liste categorie
		List<FamilleArticleValue> pTypeArticles = familleArticleDomaine.listeFamilleArticle();
		// verification si liste type article  est afficher
	   assertNotNull(pTypeArticles.size());
	}

	public IFamilleArticleDomaine getFamilleArticleDomaine() {
		return familleArticleDomaine;
	}

	public void setFamilleArticleDomaine(
			IFamilleArticleDomaine familleArticleDomaine) {
		this.familleArticleDomaine = familleArticleDomaine;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
