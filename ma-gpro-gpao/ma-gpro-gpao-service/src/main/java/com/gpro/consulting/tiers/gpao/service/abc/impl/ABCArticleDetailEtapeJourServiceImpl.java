package com.gpro.consulting.tiers.gpao.service.abc.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.abc.value.RechercheMulticritereABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ResultatRechecheABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.domaine.abc.IABCArticleDetailEtapeJourDomaine;
import com.gpro.consulting.tiers.gpao.service.abc.IABCArticleDetailEtapeJourService;

/**
 * implementation of {@link IABCArticleDetailEtapeJourService}
 * 
 * @author Wahid Gazzah
 * @since 03/05/2016
 *
 */

@Service
@Transactional
public class ABCArticleDetailEtapeJourServiceImpl implements IABCArticleDetailEtapeJourService{
	
	private static final Logger logger = LoggerFactory.getLogger(ABCArticleDetailEtapeJourServiceImpl.class);

	@Autowired
	IABCArticleDetailEtapeJourDomaine domaine;

	@Override
	public ResultatRechecheABCArticleDetailEtapeJourValue rechercherMultiCritere(
			RechercheMulticritereABCArticleDetailEtapeJourValue request) {
		
		//LOGGER.info("rechercheMulticritere: Delegating request to Domain layer.");
		
		return domaine.rechercherMultiCritere(request);
	}
}
