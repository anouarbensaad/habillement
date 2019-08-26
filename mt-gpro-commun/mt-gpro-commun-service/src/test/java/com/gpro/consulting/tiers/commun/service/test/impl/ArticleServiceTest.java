package com.gpro.consulting.tiers.commun.service.test.impl;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
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

import com.erp.socle.j2ee.mt.socle.exception.FonctionnelleException;
import com.gpro.consulting.tiers.commun.coordination.value.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.DocumentArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.SeuilArticleValue;
import com.gpro.consulting.tiers.commun.domaine.IArticleDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class ArticleServiceTest {
	// injection article domaine
	@Autowired
	private IArticleDomaine articleDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;

	/********* creation article 
	 * @throws FonctionnelleException **************/
	//
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testCreereArticle() throws FonctionnelleException {
		// creer article value
		ArticleValue nouveauArticle = setValueArticle("ref 14/08/2015",
				"designation ", new GregorianCalendar(2013, 10, 28),
				"empl 14/08", "", 1L, 1.0, 2L, "", 1L, "", 1L, 2.2, 2.0, 2.0,
				"", 2.2, 1L, 1L, "", 2.2, "", 1L);
		
		// creer list seuil
		Set<SeuilArticleValue> seuilValues = new HashSet<SeuilArticleValue>();
		
		// creer list document
		Set<DocumentArticleValue> documentValues = new HashSet<DocumentArticleValue>();
		
		// remplir list seuil
		SeuilArticleValue nouveauSeuil1 = creerSeuilArticle(2L, 1L,
				new GregorianCalendar(2013, 10, 28), new GregorianCalendar(
						2015, 10, 28));
		SeuilArticleValue nouveauSeuil2 = creerSeuilArticle(4L, 1L,
				new GregorianCalendar(2014, 10, 28), new GregorianCalendar(
						2015, 10, 20));
		seuilValues.add(nouveauSeuil2);
		seuilValues.add(nouveauSeuil1);
		
		// remplir list document
		DocumentArticleValue nouveauDocumen1 = creerDocumentArticle(
				"c:/documemnt", 1L);
		DocumentArticleValue nouveauDocumen2 = creerDocumentArticle("", 1L);
		documentValues.add(nouveauDocumen2);
		documentValues.add(nouveauDocumen1);
		
		// affecter list document,seuil au article
		nouveauArticle.setSeuilEntities(seuilValues);
		nouveauArticle.setDocumentEntites(documentValues);
		
		// Lancer la création
		String vIdArticleResultant = articleDomaine
				.creerArticle(nouveauArticle);
		
		// Test et vérifiacation
		assertNotNull(vIdArticleResultant);
		//
	}

	/************ Fin creation article ****************/

	/***************** modification article **************************/
	// modification article totale
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierArticleTotale() {
		// rechercher article à modifier
		ArticleValue articleModifier = articleRechercher(6L);
		/************* modification caracteristique article *************/
		articleModifier.setRef("ref 14/08/2015");
		articleModifier.setDesignation("designation modifier 15/08/2015");
		articleModifier.setDateIntroduction(new GregorianCalendar(2015, 10, 20));
		articleModifier.setCouleur("couleur ");
		articleModifier.setFamilleArticleDesignation("test 15/08/2015");
		articleModifier.setGrosseurEntite(2L);
		articleModifier.setLaize(7.0);
		articleModifier.setMatiereEntite(2L);
		articleModifier.setMethodeGestion("methode gestion");
		articleModifier.setMetrageEntite(2L);
		articleModifier.setObservation("observation testModifierCaracteristiqueArticle ");
		articleModifier.setPiEntite(1L);
		articleModifier.setPmp(2.0);
		articleModifier.setPoids(2.0);
		articleModifier.setPoidsBrut(1.8);
		articleModifier.setProducteur("producteur ");
		articleModifier.setPu(2.2);
		articleModifier.setSiteEntite(2L);
		articleModifier.setSousFamilleArtEntite(1L);
		articleModifier.setSousFamilleArtEntiteDesignation("sous famille ");
		articleModifier.setTare(2.0);
		articleModifier.setTypeArticleDesignation("type ");
		articleModifier.setUniteEntite(2L);
		// ajouter seuil
		SeuilArticleValue seuilModifier = creerSeuilArticle(10L, 2L,
				new GregorianCalendar(2013, 10, 28), new GregorianCalendar(
						2013, 10, 28));
		articleModifier.getSeuilEntities().add(seuilModifier);
		
		// ajouter document
		DocumentArticleValue documentModifier = creerDocumentArticle("c:/document.pdf",
				1L);
		articleModifier.getDocumentEntites().add(documentModifier);

		// modifier entite Article
		String vIdArticleResultant = articleDomaine
				.modifierArticle(articleModifier);
		assertNotNull(vIdArticleResultant);

		// recherche l'article deja modifier
		ArticleValue vArticleId = new ArticleValue();
		vArticleId.setId(Long.parseLong(vIdArticleResultant));
		ArticleValue articleVerfier = articleDomaine
				.rechercheArticleParId(vArticleId);
		
		// verification list document
		assertNotNull(articleVerfier.getDocumentEntites().size());
		
		// verification list seuil
		assertNotNull(articleVerfier.getSeuilEntities().size());

		// verification caracteristique article sont modifier
		assertTrue(articleVerfier.getDesignation().equalsIgnoreCase("designation modifier 15/08/2015"));
		assertTrue(articleVerfier.getCouleur() == "2");
		assertTrue(articleVerfier.getFamilleArticleDesignation().equals("test 15/08/2015"));
		assertTrue(articleVerfier.getGrosseurEntite() == 2L);
		assertTrue(articleVerfier.getLaize() == 7.0);
		assertTrue(articleVerfier.getMatiereEntite() == 2L);
		assertTrue(articleVerfier.getObservation() == "observation testModifierCaracteristiqueArticle ");
		assertTrue(articleVerfier.getPoids() == 2.0);

	}

	// test modifier article en supprimer ligne seuil et ligne document
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierArticleSuppresionSeuilDocument() {
		// rechercher article par id
		ArticleValue articleModifier = articleRechercher(5L);
		// supprimer ligne seuil
		for (SeuilArticleValue seuil : articleModifier.getSeuilEntities()) {
			if (seuil.getId() == 1L) {
				articleModifier.getSeuilEntities().remove(seuil);
			}
		}
		// supprimer ligne document
		for (DocumentArticleValue documentmodifier : articleModifier
				.getDocumentEntites()) {
			if (documentmodifier.getId() == 4L) {
				//logger 
				articleModifier.getSeuilEntities().remove(documentmodifier);
			}
		}
		// Lancer le service de modification /
		String vIdArticleResultant = articleDomaine
				.modifierArticle(articleModifier);
		assertNotNull(vIdArticleResultant);

		// Vérification du resultat modifier
		ArticleValue vArticleId = new ArticleValue();
		vArticleId.setId(Long.parseLong(vIdArticleResultant));
		ArticleValue nouveauArticle = articleDomaine
				.rechercheArticleParId(vArticleId);
		
		// Verifiication des caracteritiuques
		assertTrue(nouveauArticle.getSeuilEntities().size() == 2);
		assertTrue(nouveauArticle.getDocumentEntites().size() == 2);
	}

	// test modifier article en supprimer tous les seuil
	@Test
	@DirtiesContext
	@Rollback(false)
	public void testModifierArticleSuppresionToutSeuil() {
		// rechercher article par id
		ArticleValue articleModifier = articleRechercher(5L);
		// supprimer ligne seuil
		
			articleModifier.getSeuilEntities().clear();

		// Lancer le service de modification /
		String vIdArticleResultant = articleDomaine
				.modifierArticle(articleModifier);
		assertNotNull(vIdArticleResultant);

		// Vérification du resultat modifier
		ArticleValue vArticleId = new ArticleValue();
		vArticleId.setId(Long.parseLong(vIdArticleResultant));
		ArticleValue nouveauArticle = articleDomaine
				.rechercheArticleParId(vArticleId);
		// Verifiication des caracteritiuques
		assertTrue(nouveauArticle.getSeuilEntities().size() == 0);
	}

	/****************** Fin modification article ************************/

	/************************* test recherche multi critere article ******************************/

	// test recherche multi critere tous critere
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testRechercheMultiCritereArticleTousCritere() {
		// Creation d'un objet valeur RechercheMulticriterePartieInteresseeValue
		RecherecheMulticritereArticleValue vRechercheMultiCritere = new RecherecheMulticritereArticleValue();
		//vRechercheMultiCritere.setSite("1");
		vRechercheMultiCritere.setDesignation("article test");
		vRechercheMultiCritere.setRef("ref test");
		//vRechercheMultiCritere.setFamilleEntite("famille");
		vRechercheMultiCritere.setPrix_inf(2.0);
		vRechercheMultiCritere.setPrix_sup(10.0);
		//vRechercheMultiCritere.setSousFamilleEntite("sous famille");
		//vRechercheMultiCritere.setTypeEntite("type 1");
		ResultatRechecheArticleValue vResultatRecherche = articleDomaine
				.rechercherArticleMultiCritere(vRechercheMultiCritere);
		// Test et vérifiacation
		assertNotNull(vResultatRecherche);
		assertTrue(vResultatRecherche.getNombreResultaRechercher().equals(1L));
	}

	// test recherche multi critere entre Deux Prix
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testRechercheMultiCritereArticleEntreDeuxPrix() {
		RecherecheMulticritereArticleValue vRechercheMultiCritere = creerRecherecheMulticritereArticleValue();
		vRechercheMultiCritere.setPrix_inf(2.0);
		vRechercheMultiCritere.setPrix_sup(10.0);
		ResultatRechecheArticleValue vResultatRecherche = articleDomaine
				.rechercherArticleMultiCritere(vRechercheMultiCritere);
		// Test et vérifiacation
		assertNotNull(vResultatRecherche);
		assertTrue(vResultatRecherche.getNombreResultaRechercher().equals(1L));
	}

	// test recherche multi critere prix superieur <prix inferieur
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testRechercheMultiCritereArticlePrixDefferent() {
		RecherecheMulticritereArticleValue vRechercheMultiCritere = creerRecherecheMulticritereArticleValue();
		vRechercheMultiCritere.setPrix_inf(10.0);
		vRechercheMultiCritere.setPrix_sup(2.0);
		ResultatRechecheArticleValue vResultatRecherche = articleDomaine
				.rechercherArticleMultiCritere(vRechercheMultiCritere);
		// Test et vérifiacation
		assertEquals(vResultatRecherche.getNombreResultaRechercher()
				.longValue(), 0L);
	}

	/************************* Fin test recherche multi critere article ******************************/

	/***** creation document article Value ***/
	public DocumentArticleValue creerDocumentArticle(String path,
			Long typeEntiteId) {
		DocumentArticleValue vDocument = new DocumentArticleValue();
		vDocument.setPath(path);
		vDocument.setTypeDocumentEntite(typeEntiteId);
		return vDocument;
	}

	/***** creation Seuil article Value ***/
	public SeuilArticleValue creerSeuilArticle(Long maxSeuil, Long minSeuil,
			Calendar dateDebut, Calendar DateFin) {
		SeuilArticleValue vSeuil = new SeuilArticleValue();
		vSeuil.setMaxSeuil(maxSeuil);
		vSeuil.setMinSeuil(minSeuil);
		vSeuil.setDateDebut(dateDebut);
		vSeuil.setDateFin(DateFin);
		return vSeuil;
	}

	/***** creation article Value ***/
	public ArticleValue setValueArticle(String ref, String designation,
			Calendar dateIntroduction, String couleur,
			String familleDesignation, Long grosseur, Double laize,
			Long matiere, String methodeGestion, Long metrage,
			String observation, Long piId, Double pmp, Double poid,
			Double poidBrut, String producteur, Double pu, Long site,
			Long sousFamilleId, String sousFamilleDesignation, Double tare,
			String typeArticle, Long unite) {
		ArticleValue article = new ArticleValue();
		article.setRef(ref);
		article.setDesignation(designation);
		article.setDateIntroduction(dateIntroduction);
		article.setCouleur(couleur);
		article.setFamilleArticleDesignation(familleDesignation);
		article.setGrosseurEntite(grosseur);
		article.setLaize(laize);
		article.setMatiereEntite(matiere);
		article.setMethodeGestion(methodeGestion);
		article.setMetrageEntite(metrage);
		article.setObservation(observation);
		article.setPiEntite(piId);
		article.setPmp(pmp);
		article.setPoids(poid);
		article.setPoidsBrut(poidBrut);
		article.setProducteur(producteur);
		article.setPu(pu);
		article.setSiteEntite(site);
		article.setSousFamilleArtEntite(sousFamilleId);
		article.setSousFamilleArtEntiteDesignation(sousFamilleDesignation);
		article.setTare(tare);
		article.setTypeArticleDesignation(typeArticle);
		article.setUniteEntite(unite);
		return article;
	}

	public ArticleValue articleRechercher(Long id) {
		// id article à rechercher
		ArticleValue articleId = new ArticleValue();
		articleId.setId(id);
		ArticleValue articleModifier = articleDomaine
				.rechercheArticleParId(articleId);
		return articleModifier;
	}

	public RecherecheMulticritereArticleValue creerRecherecheMulticritereArticleValue() {
		RecherecheMulticritereArticleValue vRechercheMultiCritere = new RecherecheMulticritereArticleValue();
		return vRechercheMultiCritere;
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
