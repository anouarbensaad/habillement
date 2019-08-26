package com.gpro.consulting.tiers.gpao.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.RechercheMulticritereRecapProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.ResultatRechecheRecapProductionValue;
import com.gpro.consulting.tiers.gpao.domaine.IRecapProductionDomaine;
import com.gpro.consulting.tiers.gpao.service.IRecapProductionService;

/**
 * implementation of {@link IRecapProductionService}
 * 
 * @author Wahid Gazzah
 * @since 21/06/2016
 *
 */

@Service
@Transactional
public class RecapProductionServiceImpl implements IRecapProductionService{

	private static final Logger logger = LoggerFactory.getLogger(RecapProductionServiceImpl.class);

	@Autowired
	private IRecapProductionDomaine recapProductionDomaine;
	
	@Override
	public ResultatRechecheRecapProductionValue rechercherMultiCritere(
			RechercheMulticritereRecapProductionValue request) {
		
		//LOGGER.info("rechercherMultiCritere: Delegating request to Domaine layer.");
		
		return recapProductionDomaine.rechercherMultiCritere(request);
	}

}
