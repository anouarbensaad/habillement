package com.gpro.consulting.tiers.commun.service.test.article.creer;

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
import com.gpro.consulting.tiers.commun.coordination.value.SeuilArticleValue;
import com.gpro.consulting.tiers.commun.domaine.IArticleDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class CreerArticleServiceTest {
	// injection article domaine
	@Autowired
	private IArticleDomaine articleDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;

	/********* creation article 
	 * @throws FonctionnelleException **************/
	//test  creation article 
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
