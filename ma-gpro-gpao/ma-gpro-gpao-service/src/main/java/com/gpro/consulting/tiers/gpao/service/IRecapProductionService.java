package com.gpro.consulting.tiers.gpao.service;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.RechercheMulticritereRecapProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.ResultatRechecheRecapProductionValue;


/**
 * Recap Production Service interface
 *  
 * @author Wahid Gazzah
 * @since 21/06/2016
 */
public interface IRecapProductionService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheRecapProductionValue rechercherMultiCritere(
			RechercheMulticritereRecapProductionValue request);
	
}
