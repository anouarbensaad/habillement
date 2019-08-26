package com.gpro.consulting.tiers.gc.domaine;

import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheArticleAchatValue;

/**
 * @author $Ameni
 *
 */
public interface IArticleAchatDomaine {

	/** Methode de Recherche d'un Article et Affection de ce dernier à l'objet valeur ArticleAchat
	 * @param pRechercheArticleMulitCritere
	 * @return ResultatRechecheArticleAchatValue
	 */
	public ResultatRechecheArticleAchatValue rechercherArticleMultiCritere(
			  RecherecheMulticritereArticleValue pRechercheArticleMulitCritere);
	
}
