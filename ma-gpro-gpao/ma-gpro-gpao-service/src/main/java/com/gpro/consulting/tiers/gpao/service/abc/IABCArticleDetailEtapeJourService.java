package com.gpro.consulting.tiers.gpao.service.abc;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.abc.value.RechercheMulticritereABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ResultatRechecheABCArticleDetailEtapeJourValue;

/**
 * ABCArticleDetailEtapeJour Service interface
 * 
 * @author Wahid Gazzah
 * @since 03/05/2016
 *
 */
public interface IABCArticleDetailEtapeJourService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheABCArticleDetailEtapeJourValue rechercherMultiCritere(
			RechercheMulticritereABCArticleDetailEtapeJourValue request);
}
