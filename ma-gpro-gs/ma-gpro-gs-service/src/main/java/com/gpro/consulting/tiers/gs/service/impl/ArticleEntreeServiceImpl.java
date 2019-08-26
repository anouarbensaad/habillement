package com.gpro.consulting.tiers.gs.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheArticleEntreeValue;
import com.gpro.consulting.tiers.gs.domaine.IArticleEntreeDomaine;
import com.gpro.consulting.tiers.gs.service.IArticleEntreeService;

@Service
@Transactional
public class ArticleEntreeServiceImpl implements IArticleEntreeService{

    @Autowired
    IArticleEntreeDomaine articleEntreeDomaine;
	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.service.IArticleEntreeService#rechercherArticleMultiCritere(com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue)
	 */
	@Override
	public ResultatRechecheArticleEntreeValue rechercherArticleMultiCritere(
			RecherecheMulticritereArticleValue pRechercheArticleMulitCritere,Long pIdMagasin) {
		// TODO Auto-generated method stub
		return articleEntreeDomaine.rechercherArticleMultiCritere(pRechercheArticleMulitCritere, pIdMagasin);
	}
	@Override
	public ResultatRechecheArticleEntreeValue rechercherArticleMultiCritereFB(
			RecherecheMulticritereArticleValue pRechercheArticleMulitCritere) {
		return articleEntreeDomaine.rechercherArticleMultiCritereFB(pRechercheArticleMulitCritere);
	}
	
}
