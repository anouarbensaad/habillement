package com.gpro.consulting.tiers.gpao.rest.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.gpao.coordination.abc.value.RechercheMulticritereABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ResultatRechecheABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.service.abc.IABCArticleDetailEtapeJourService;

/**
 * Controle Controller
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */

@RestController
@RequestMapping("/aBCArticleDetailEtapeJour")
public class ABCArticleDetailEtapeJourRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(ABCArticleDetailEtapeJourRestImpl.class);
	
	@Autowired
	IABCArticleDetailEtapeJourService service;
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheABCArticleDetailEtapeJourValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereABCArticleDetailEtapeJourValue request) {
		 
		//LOGGER.info("rechercheMulticritere: Delegating request to service layer.");
		
		return service.rechercherMultiCritere(request);
	}

}
