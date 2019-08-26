package com.gpro.consulting.tiers.gpao.domaine.abc.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.abc.value.RechercheMulticritereABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ResultatRechecheABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.domaine.abc.IABCArticleDetailEtapeJourDomaine;
import com.gpro.consulting.tiers.gpao.persitance.abc.IABCArticleDetailEtapeJourPersistance;

/**
 * implementation of {@link IABCArticleDetailEtapeJourDomaine}
 * 
 * @author Wahid Gazzah
 * @since 03/05/2016
 *
 */

@Component
public class ABCArticleDetailEtapeJourDomaineImpl implements IABCArticleDetailEtapeJourDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(ABCArticleDetailEtapeJourDomaineImpl.class);

	@Autowired
	IABCArticleDetailEtapeJourPersistance persistance;

	@Override
	public ResultatRechecheABCArticleDetailEtapeJourValue rechercherMultiCritere(
			RechercheMulticritereABCArticleDetailEtapeJourValue request) {
		
		//LOGGER.info("rechercheMulticritere: Delegating request to Persistance layer.");
		
		return persistance.rechercherMultiCritere(request);
	}
	
	
}
