package com.gpro.consulting.tiers.commun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.ArticleCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.domaine.IArticleDomaine;
import com.gpro.consulting.tiers.commun.service.IArticleService;

@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl implements IArticleService {

  @Autowired
  IArticleDomaine articleDomaine;

  /**
   * {@inheritDoc}
   */
  @Override
  public String creerArticle(ArticleValue pArticleValue) {

    return articleDomaine.creerArticle(pArticleValue);
  }

  @Override
  public void supprimerArticle(Long pId) {
    articleDomaine.supprimerArticle(pId);

  }

  @Override
  public String modifierArticle(ArticleValue pArticleValue) {

    return articleDomaine.modifierArticle(pArticleValue);
  }

  @Override
  public ArticleValue rechercheArticleParId(ArticleValue pArticleValue) {

    return articleDomaine.rechercheArticleParId(pArticleValue);
  }

  @Override
  public List < ArticleValue > listeArticle() {

    return articleDomaine.listeArticle();
  }

  @Override
  public ResultatRechecheArticleValue rechercherArticleMultiCritere(
    RecherecheMulticritereArticleValue pRechercheArticleMulitCritere) {

    return articleDomaine.rechercherArticleMultiCritere(pRechercheArticleMulitCritere);
  }

  public IArticleDomaine getArticleDomaine() {
    return articleDomaine;
  }

  public void setArticleDomaine(IArticleDomaine articleDomaine) {
    this.articleDomaine = articleDomaine;
  }

@Override
public List<ArticleCacheValue> listeArticleCache() {
	return articleDomaine.listeArticleCache();
}

@Override
public boolean referenceExistence(String reference) {
	// TODO Auto-generated method stub
	return this.articleDomaine.referenceExistence(reference);
}

}
