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
import com.gpro.consulting.tiers.commun.coordination.value.TypeArticleValue;
import com.gpro.consulting.tiers.commun.domaine.ITypeArticleDomaine;

@ContextConfiguration(locations = {"/spring/application-context.xml", "/spring/config-mt-gpro-commun-service-test.xml",
		  "/spring/config-mt-gpro-commun-persistance.xml", "/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class TypeArticleServiceTest {

	@Autowired
	private ITypeArticleDomaine typeArticleDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;

	

	// test si type  article  est ajouter
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testCreereTypeArticle() {
		// Creation d'un objet valeur sous type article
		TypeArticleValue typeArticle = new TypeArticleValue();
		typeArticle.setDesignation(" type 1");
		// Lancer la création
		//String vIdTypeArticle =typeArticleDomaine.creerTypeArticle(typeArticle);
		// Test et vérifiacation
		//assertNotNull(vIdTypeArticle);
	}

	// test si categorie partie interesse est supprimer
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testSupprimerFamillePartieInteressee() {

		// create objet valeur atégorie
		CategorieValue pCategoriePartieInteresseValue = new CategorieValue();
		pCategoriePartieInteresseValue.setId(23L);
		// categoriePartieInteresseeDomaine.supprimerCategoriePartieInteressee(pCategoriePartieInteresseValue);

	}

	// test si categorie partie interesse est modifier
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierModifierPartieInteressee() {
		// Creation d'un objet valeur sous type article
		TypeArticleValue typeArticle = new TypeArticleValue();
		//typeArticle.setId(1L);
		//typeArticle.setDesignation(" type modifier ");
		// Lancer la création
		//String vIdTypeArticle =typeArticleDomaine.modifierTypeArticle(typeArticle);
		// Test et vérifiacation
		//assertNotNull(vIdTypeArticle);

	}

	// test si categorie partie interesse est supprimer
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testRechercheCategoriePartieInteresseeParId() {

		// objet valeur Catégorie
		//CategorieValue pCategoriePartieInteresseValue = new CategorieValue();
		//pCategoriePartieInteresseValue.setId(24L);
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



	


	public ITypeArticleDomaine getTypeArticleDomaine() {
		return typeArticleDomaine;
	}

	public void setTypeArticleDomaine(ITypeArticleDomaine typeArticleDomaine) {
		this.typeArticleDomaine = typeArticleDomaine;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
