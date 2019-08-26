package com.gpro.consulting.tiers.commun.service.test.article.modifier;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.ArticleValue;
import com.gpro.consulting.tiers.commun.domaine.IArticleDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class ModifierCaracteristiqueArticleServiceTest {
	//logger
	  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ModifierCaracteristiqueArticleServiceTest.class);
	// injection article domaine
	@Autowired
	private IArticleDomaine articleDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate template; @Autowired
    private ApplicationContext ctx;
	
	// modification  caracteristique  article 
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testModifierArticleTotale() {
		// entrer : article avec caracteristique non modifier 
		ArticleValue articleModifier = articleRechercher(100L);
		articleModifier.setRef("ref 14/08/2015");
		articleModifier.setDesignation("designation modifier 18/08/2015");
		articleModifier.setDateIntroduction(new GregorianCalendar(2015, 10, 20));
		articleModifier.setCouleur("couleur");
		articleModifier.setGrosseurEntite(2L);
		articleModifier.setLaize(7.0);
		articleModifier.setMatiereEntite(2L);
		articleModifier.setMethodeGestion("methode gestion");
		articleModifier.setMetrageEntite(2L);
		articleModifier.setObservation("observation testModifierCaracteristiqueArticle");
		articleModifier.setPiEntite(1L);
		articleModifier.setPmp(2.0);
		articleModifier.setPoids(2.0);
		articleModifier.setPoidsBrut(1.8);
		articleModifier.setProducteur("producteur");
		articleModifier.setPu(2.2);
		articleModifier.setSiteEntite(2L);
		articleModifier.setSousFamilleArtEntite(1L);
		articleModifier.setSousFamilleArtEntiteDesignation("sous famille ");
		articleModifier.setTare(2.0);
		articleModifier.setTypeArticleDesignation("type");
		articleModifier.setUniteEntite(2L);
		// traitement : modifier les caracteristiques de l'article 
		String vIdArticleResultant = articleDomaine.modifierArticle(articleModifier);
		assertNotNull(vIdArticleResultant);

		// resultat volue : article avec caracteristique modifier 
		ArticleValue vArticleId = new ArticleValue();
		vArticleId.setId(Long.parseLong(vIdArticleResultant));
		ArticleValue articleVerfier = articleDomaine.rechercheArticleParId(vArticleId);
		
		// verification caracteristique article sont modifier
		assertTrue(articleVerfier.getDesignation().equals("designation modifier 18/08/2015"));
		assertTrue(articleVerfier.getCouleur().equals("couleur"));
		assertTrue(articleVerfier.getGrosseurEntite() == 2L);
		assertTrue(articleVerfier.getLaize() == 7.0);
		assertTrue(articleVerfier.getObservation().equals("observation testModifierCaracteristiqueArticle"));
		//assertTrue(articleVerfier.getPoids() == 2.0);
		assertTrue(articleVerfier.getRef().equals("ref 14/08/2015"));
		//assertTrue(articleVerfier.getDateIntroduction()==new GregorianCalendar(2015, 10, 20));
		//assertTrue(articleVerfier.getMatiereEntite()==2L);
		assertTrue(articleVerfier.getMethodeGestion().equals("methode gestion"));
		assertTrue(articleVerfier.getMetrageEntite()==2L);
		//assertTrue(articleVerfier.getPiEntite()==1L);
		assertTrue(articleVerfier.getPmp()==2.0);
		//assertTrue(articleVerfier.getPoids()==2.0);
		//assertTrue(articleVerfier.getPoidsBrut()==1.8);
		assertTrue(articleVerfier.getProducteur().equals("producteur"));
		assertTrue(articleVerfier.getPu()==2.2);
		assertTrue(articleVerfier.getSiteEntite()==2L);
		assertTrue(articleVerfier.getSousFamilleArtEntite()==1L);
		//assertTrue(articleVerfier.getTare()==2.0);
		//assertTrue(articleVerfier.getTypeArticleDesignation().equals("type"));
		assertTrue(articleVerfier.getUniteEntite()==2L);
	}

	// rechercher article
	public ArticleValue articleRechercher(Long id) {
		// id article à rechercher
		ArticleValue articleId = new ArticleValue();
		articleId.setId(id);
		ArticleValue articleModifier = articleDomaine
				.rechercheArticleParId(articleId);
		return articleModifier;
	}

   /* @Before
    public void intitDB() {
    	String script = "classpath:/resources/DataBaseTest/article.sql"; 
        Resource resource = ctx.getResource(script);
        LOGGER.info("ressource1  = "+resource.getFilename());
        JdbcTestUtils.executeSqlScript(template, resource, true);     
    }*/

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
