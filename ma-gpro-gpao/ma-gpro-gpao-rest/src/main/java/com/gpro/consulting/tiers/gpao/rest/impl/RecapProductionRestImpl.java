package com.gpro.consulting.tiers.gpao.rest.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.RechercheMulticritereRecapProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.ResultatRechecheRecapProductionValue;
import com.gpro.consulting.tiers.gpao.service.IRecapProductionService;

/**
 * Recap Production Controller
 * 
 * @author Wahid Gazzah
 * @since 21/06/2016
 *
 */

@RestController
@RequestMapping("/recapProduction")
public class RecapProductionRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(RecapProductionRestImpl.class);
	
	@Autowired
	private IRecapProductionService recapProductionService;
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheRecapProductionValue rechercherMultiCritere(@RequestBody RechercheMulticritereRecapProductionValue request) {
		
		//LOGGER.info("rechercheMulticritere: Delegating request to service layer.");
		
		ResultatRechecheRecapProductionValue result = recapProductionService.rechercherMultiCritere(request);
		
		return result;
	}
}
