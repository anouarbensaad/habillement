package com.gpro.consulting.tiers.gs.domaine;

import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheArticleEntreeValue;

public interface IArticleEntreeDomaine {
	
	public ResultatRechecheArticleEntreeValue rechercherArticleMultiCritere(
			  RecherecheMulticritereArticleValue pRechercheArticleMulitCritere,Long idMagasin);
	
	public ResultatRechecheArticleEntreeValue rechercherArticleMultiCritereFB(
			  RecherecheMulticritereArticleValue pRechercheArticleMulitCritere);
    
}
