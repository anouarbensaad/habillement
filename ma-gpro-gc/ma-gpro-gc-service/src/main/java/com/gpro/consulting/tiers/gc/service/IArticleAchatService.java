package com.gpro.consulting.tiers.gc.service;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheArticleAchatValue;

/**
 * @author $Ameni
 *
 */
public interface IArticleAchatService {
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheArticleAchatValue rechercherArticleMultiCritere(
			  RecherecheMulticritereArticleValue pRechercheArticleMulitCritere);
	
}
