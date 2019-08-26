package com.gpro.consulting.tiers.gc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheArticleAchatValue;
import com.gpro.consulting.tiers.gc.domaine.IArticleAchatDomaine;
import com.gpro.consulting.tiers.gc.service.IArticleAchatService;


/**
 * @author $Ameni
 *
 */

@Service
@Transactional()
public class ArticleAchatServiceImpl implements IArticleAchatService{

	@Autowired
    IArticleAchatDomaine articleAchatDomaine;

	@Override
	public ResultatRechecheArticleAchatValue rechercherArticleMultiCritere(
			RecherecheMulticritereArticleValue pRechercheArticleMulitCritere) {
		
		return articleAchatDomaine.rechercherArticleMultiCritere(pRechercheArticleMulitCritere);
	}

	/**
	 * @return the articleAchatDomaine
	 */
	public IArticleAchatDomaine getArticleAchatDomaine() {
		return articleAchatDomaine;
	}

	/**
	 * @param articleAchatDomaine the articleAchatDomaine to set
	 */
	public void setArticleAchatDomaine(IArticleAchatDomaine articleAchatDomaine) {
		this.articleAchatDomaine = articleAchatDomaine;
	}
	
	
}
