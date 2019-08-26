package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.ArticleCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.domaine.IArticleDomaine;
import com.gpro.consulting.tiers.commun.persistance.IArticlePersistance;

public class ArticleDomaineImpl implements IArticleDomaine{
    @Autowired
    IArticlePersistance articlePersistance;
    
    
    
	@Override
	public String creerArticle(ArticleValue pArticleValue) {
		
		return articlePersistance.creerArticle(pArticleValue);
	}

	@Override
	public void supprimerArticle(Long pId) {
		articlePersistance.supprimerArticle(pId);
		
	}
	
	@Override
	public String modifierArticle(ArticleValue pArticleValue) {
		
		return articlePersistance.modifierArticle(pArticleValue);
	}

	@Override
	public ArticleValue rechercheArticleParId(ArticleValue pArticleValue) {
		
		return articlePersistance.rechercheArticleParId(pArticleValue);
	}

	@Override
	public List<ArticleValue> listeArticle() {
		
		  List<ArticleValue> list = articlePersistance.listeArticle();
		  Collections.sort(list);
		  
		  return list;
	}

	@Override
	public ResultatRechecheArticleValue rechercherArticleMultiCritere(
			RecherecheMulticritereArticleValue pRechercheArticleMulitCritere) {
		
		return articlePersistance.rechercherArticleMultiCritere(pRechercheArticleMulitCritere);
	}

	/**
	 * @return the articlePersistance
	 */
	public IArticlePersistance getArticlePersistance() {
		return articlePersistance;
	}

	/**
	 * @param articlePersistance the articlePersistance to set
	 */
	public void setArticlePersistance(IArticlePersistance articlePersistance) {
		this.articlePersistance = articlePersistance;
	}

	@Override
	public List<ArticleCacheValue> listeArticleCache() {
		return articlePersistance.listeArticleCache();
	}

	@Override
	public ArticleValue rechercheArticleParId(Long id) {
		return this.articlePersistance.getArticleParId(id);
	}

	@Override
	public boolean referenceExistence(String reference) {
		// TODO Auto-generated method stub
		return this.articlePersistance.referenceExistence(reference);
	}

}
