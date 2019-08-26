package com.gpro.consulting.tiers.commun.service.test.article.modifier;

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
import com.gpro.consulting.tiers.commun.coordination.value.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.SeuilArticleValue;
import com.gpro.consulting.tiers.commun.domaine.IArticleDomaine;

@ContextConfiguration(locations = { "/spring/application-context.xml",
		"/spring/config-mt-gpro-commun-service-test.xml",
		"/spring/config-mt-gpro-commun-persistance.xml",
		"/spring/config-mt-gpro-commun-domaine.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class AjouterSeuilArticleServiceTest {
	// injection article domaine
	@Autowired
	private IArticleDomaine articleDomaine;
	/** La datasource. */
	@Autowired
	private DataSource dataSource;

	// test modifier article en ajouter  ligne seuil 
	@Test
	@DirtiesContext
	@Rollback(true)
	public void testAjouterSeuilArticle() {
		// entrer : article + une seule seuil
		ArticleValue articleModifier = articleRechercher(5L);
		// creer list seuil
		Set<SeuilArticleValue> seuilValues = new HashSet<SeuilArticleValue>();

		// remplir list seuil
		SeuilArticleValue nouveauSeuil1 = creerSeuilArticle(2L, 1L,
				new GregorianCalendar(2013, 10, 28), new GregorianCalendar(
						2015, 10, 28));
		seuilValues.add(nouveauSeuil1);
		articleModifier.setSeuilEntities(seuilValues);

		// traitement : modifier article et ajouter ligne de seuil
		String vIdArticleResultant = articleDomaine
				.modifierArticle(articleModifier);
		assertNotNull(vIdArticleResultant);

		// resultat volue : article + deux ligne de seuil
		ArticleValue vArticleId = new ArticleValue();
		vArticleId.setId(Long.parseLong(vIdArticleResultant));
		ArticleValue articleRechercher = articleDomaine
				.rechercheArticleParId(vArticleId);

		// Verifiication des caracteritiuques de seuil article ajouter 
		for (SeuilArticleValue seuil : articleRechercher.getSeuilEntities()) {
			if (seuil.getMaxSeuil() == 2L) {
				assertTrue(seuil.getMaxSeuil() == 2L);
				assertTrue(seuil.getMinSeuil() == 1L);
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
