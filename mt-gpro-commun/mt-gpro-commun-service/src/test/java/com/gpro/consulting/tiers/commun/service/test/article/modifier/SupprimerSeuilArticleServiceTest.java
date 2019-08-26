package com.gpro.consulting.tiers.commun.service.test.article.modifier;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.SeuilArticleValue;
import com.gpro.consulting.tiers.commun.domaine.IArticleDomaine;
import com.gpro.consulting.tiers.commun.persistance.impl.ArticlePersistanceImpl;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class SupprimerSeuilArticleServiceTest {
	//logger
	  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ArticlePersistanceImpl.class);
	// injection article domaine
	@Autowired
	private IArticleDomaine articleDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;

	// test modifier article en supprimer ligne seuil 
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testSupprimerSeuilArticle() {
		// entrer : article + 2 ligne   seuil
		ArticleValue articleModifier = articleRechercher(5L);
		// supprimer ligne seuil
		for (SeuilArticleValue seuil : articleModifier.getSeuilEntities()) {
			LOGGER.info("size avant suppression= "+articleModifier.getSeuilEntities().size());
			if (seuil.getMaxSeuil()==2L && seuil.getMinSeuil()==1L) {
				articleModifier.getSeuilEntities().remove(seuil);
				LOGGER.info("size apres suppression= "+articleModifier.getSeuilEntities().size());
				break;
			}
		}
		// traitement : modifier article et supprimer ligne de seuil
		String vIdArticleResultant = articleDomaine
				.modifierArticle(articleModifier);
		assertNotNull(vIdArticleResultant);

		// resultat volue : article + 1 seul ligne de seuil
		ArticleValue vArticleId = new ArticleValue();
		vArticleId.setId(Long.parseLong(vIdArticleResultant));
		ArticleValue articleRechercher = articleDomaine
				.rechercheArticleParId(vArticleId);
		
		// Verifiication des caracteritiuques de seuil article ajouter 
		for (SeuilArticleValue seuil : articleRechercher.getSeuilEntities()) {
			LOGGER.info("size verification = "+articleModifier.getSeuilEntities().size());
				assertFalse(seuil.getMaxSeuil()==2L);
				assertFalse(seuil.getMinSeuil()==1L);
		}
	}

  /*********** rechercher article par id ********/
	public ArticleValue articleRechercher(Long id) {
		// id article à rechercher
		ArticleValue articleId = new ArticleValue();
		articleId.setId(id);
		ArticleValue articleModifier = articleDomaine
				.rechercheArticleParId(articleId);
		return articleModifier;
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
