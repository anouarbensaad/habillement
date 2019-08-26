package com.gpro.consulting.tiers.commun.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.domaine.ISousFamilleArticleDomaine;
import com.gpro.consulting.tiers.commun.service.ISousFamilleArticleService;
// TODO: Auto-generated Javadoc

/**
 * The Class SousFamilleArticleServiceImpl.
 * @author mohamed
 */
@Service
@Transactional
public class SousFamilleArticleServiceImpl implements ISousFamilleArticleService {

	   /** The sous famille article domaine. */
   	@Autowired
	   ISousFamilleArticleDomaine sousFamilleArticleDomaine;
	
	/* (non-Javadoc)
	 * ajouter sous famille
	 */
	@Override
	public String creerSousFamilleArticle(SousFamilleArticleValue pSousFamilleArticleValue) {
		return sousFamilleArticleDomaine.creerSousFamilleArticle(pSousFamilleArticleValue);
	}

	/* (non-Javadoc)
	 * supprimer sous famille
	 */
	@Override
	public void supprimerSousFamilleArticle(Long pSousFamilleArticleId) {
       sousFamilleArticleDomaine.supprimerSousFamilleArticle(pSousFamilleArticleId);		
	}

	/* (non-Javadoc)
	 * modifier sous famille
	 */
	@Override
	public String modifierSousFamilleArticle(
			SousFamilleArticleValue pSousFamilleArticleValue) {
		return sousFamilleArticleDomaine.modifierSousFamilleArticle(pSousFamilleArticleValue);
	}

	/* (non-Javadoc)
	 * recherche par id sous famille 
	 */
	@Override
	public SousFamilleArticleValue rechercheSousFamilleArticleById(
			Long pSousFamilleArticleId) {
	     return sousFamilleArticleDomaine.rechercheSousFamilleArticleById(pSousFamilleArticleId);
	} 

	/* (non-Javadoc)
	 * liste sous famille
	 */
	@Override
	public List<SousFamilleArticleValue> listeSousFamilleArticle() {
		return sousFamilleArticleDomaine.listeSousFamilleArticle();
	}

	
	/*****get sousFamilleArticleDomaine *******/
	public ISousFamilleArticleDomaine getSousFamilleArticleDomaine() {
		return sousFamilleArticleDomaine;
	}
	/*****set sousFamilleArticleDomaine *******/
	public void setSousFamilleArticleDomaine(
			ISousFamilleArticleDomaine sousFamilleArticleDomaine) {
		this.sousFamilleArticleDomaine = sousFamilleArticleDomaine;
	}

	
}
