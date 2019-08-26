package com.gpro.consulting.tiers.gs.service;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheArticleEntreeValue;

public interface IArticleEntreeService {
	
	 
	  @Transactional(readOnly = true, rollbackFor = Exception.class)
	  public ResultatRechecheArticleEntreeValue rechercherArticleMultiCritere(
			  RecherecheMulticritereArticleValue pRechercheArticleMulitCritere,Long idMagasin);
	
	  @Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheArticleEntreeValue rechercherArticleMultiCritereFB(
				  RecherecheMulticritereArticleValue pRechercheArticleMulitCritere);
	
}
