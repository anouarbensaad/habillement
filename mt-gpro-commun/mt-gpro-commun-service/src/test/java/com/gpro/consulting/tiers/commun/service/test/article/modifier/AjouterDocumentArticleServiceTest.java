package com.gpro.consulting.tiers.commun.service.test.article.modifier;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

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
import com.gpro.consulting.tiers.commun.coordination.value.DocumentArticleValue;
import com.gpro.consulting.tiers.commun.domaine.IArticleDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class AjouterDocumentArticleServiceTest {
	// logger
	  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AjouterDocumentArticleServiceTest.class);
	// injection article domaine
	@Autowired
	private IArticleDomaine articleDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;

	// test modifier article en ajouter  ligne document  
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testAjouterDocumentArticle() {
		// entrer : article + une seule document 
		ArticleValue articleModifier = articleRechercher(5L);
		// creer list document
		Set<DocumentArticleValue> documentValues = new HashSet<DocumentArticleValue>();
		
		// remplir list document
				DocumentArticleValue nouveauDocumen1 = creerDocumentArticle(
						"c:/documemnt1", 1L);
				documentValues.add(nouveauDocumen1);
				LOGGER.info("path : "+nouveauDocumen1.getPath());
				// affecter list document au article
				articleModifier.setDocumentEntites(documentValues);

		// traitement : modifier article et ajouter ligne de document
		String vIdArticleResultant = articleDomaine.modifierArticle(articleModifier);
		assertNotNull(vIdArticleResultant);

		// resultat volue : article + deux ligne de document 
		ArticleValue vArticleId = new ArticleValue();
		vArticleId.setId(Long.parseLong(vIdArticleResultant));
		ArticleValue articleRechercher = articleDomaine.rechercheArticleParId(vArticleId);

		// Verifiication des caracteritiuques de document  article
		for (DocumentArticleValue document : articleRechercher.getDocumentEntites()) {
			if (document.getPath().equals("c:/documemnt1")) {
				assertTrue(document.getPath().equals("c:/documemnt1"));
				assertTrue(document.getTypeDocumentEntite()==1L);
				break;
			}
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


	/***** creation document article Value ***/
	public DocumentArticleValue creerDocumentArticle(String path,
			Long typeEntiteId) {
		DocumentArticleValue vDocument = new DocumentArticleValue();
		vDocument.setPath(path);
		vDocument.setTypeDocumentEntite(typeEntiteId);
		return vDocument;
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
