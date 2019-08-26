package com.gpro.consulting.tiers.commun.domaine.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.domaine.ISousFamilleArticleDomaine;
import com.gpro.consulting.tiers.commun.persistance.ISousFamilleArticlePersistance;
// TODO: Auto-generated Javadoc
/**
 * The Class SousFamilleArticleDomaineImpl.
 * @author mohamed
 */
public class SousFamilleArticleDomaineImpl implements ISousFamilleArticleDomaine {

	@Autowired
	ISousFamilleArticlePersistance sousFamilleArticlePersistance ;
	/* ajouter sous famille
	 */
	@Override
	public String creerSousFamilleArticle(SousFamilleArticleValue pSousFamilleArticleValue) {
		return sousFamilleArticlePersistance.creerSousFamilleArticle(pSousFamilleArticleValue);
	}

	/* (non-Javadoc)
	 * supprimer sous famille article
	 */
	@Override
	public void supprimerSousFamilleArticle(Long pSousFamilleArticleId) {
		sousFamilleArticlePersistance.supprimerSousFamilleArticle(pSousFamilleArticleId);
	}

	/* (non-Javadoc)
	 * modifier sous famille 
	 */
	@Override
	public String modifierSousFamilleArticle(SousFamilleArticleValue pSousFamilleArticleValue) {
		return sousFamilleArticlePersistance.modifierSousFamilleArticle(pSousFamilleArticleValue);
	}

	/* (non-Javadoc)
	 * recherche par id sous famille 
	 */
	@Override
	public SousFamilleArticleValue rechercheSousFamilleArticleById(Long pSousFamilleArticleID) {
		return sousFamilleArticlePersistance.rechercheSousFamilleArticleById(pSousFamilleArticleID);
	}

	/* (non-Javadoc)
	 * liste sous famille article
	 */
	@Override
	public List<SousFamilleArticleValue> listeSousFamilleArticle() {
		return sousFamilleArticlePersistance.listeSousFamilleArticle();
	}

	
	/******getter and setter sousfamille article persistance*******/
	public ISousFamilleArticlePersistance getSousFamilleArticlePersistance() {
		return sousFamilleArticlePersistance;
	}

	public void setSousFamilleArticlePersistance(
			ISousFamilleArticlePersistance sousFamilleArticlePersistance) {
		this.sousFamilleArticlePersistance = sousFamilleArticlePersistance;
	}

	
	
}
