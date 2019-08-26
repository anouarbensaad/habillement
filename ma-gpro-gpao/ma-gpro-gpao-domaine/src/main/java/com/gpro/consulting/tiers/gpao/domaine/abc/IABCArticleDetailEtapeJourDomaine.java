package com.gpro.consulting.tiers.gpao.domaine.abc;

import com.gpro.consulting.tiers.gpao.coordination.abc.value.RechercheMulticritereABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ResultatRechecheABCArticleDetailEtapeJourValue;

/**
 * ABCArticleDetailEtapeJour Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 03/05/2016
 *
 */
public interface IABCArticleDetailEtapeJourDomaine {

	public ResultatRechecheABCArticleDetailEtapeJourValue rechercherMultiCritere(
			RechercheMulticritereABCArticleDetailEtapeJourValue request);
	
	
}
