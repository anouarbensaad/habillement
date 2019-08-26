package com.gpro.consulting.tiers.gpao.persitance.abc;

import com.gpro.consulting.tiers.gpao.coordination.abc.value.RechercheMulticritereABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ResultatRechecheABCArticleDetailEtapeJourValue;

/**
 * ABCArticleDetailEtapeJour Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 03/05/2016
 *
 */
public interface IABCArticleDetailEtapeJourPersistance {
	
	public ResultatRechecheABCArticleDetailEtapeJourValue rechercherMultiCritere(
			RechercheMulticritereABCArticleDetailEtapeJourValue request);

}
