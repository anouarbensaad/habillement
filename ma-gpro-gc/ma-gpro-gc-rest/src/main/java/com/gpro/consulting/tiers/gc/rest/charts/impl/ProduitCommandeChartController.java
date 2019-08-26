package com.gpro.consulting.tiers.gc.rest.charts.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheProduitCommandeValue;
import com.gpro.consulting.tiers.gc.service.IProduitCommandeService;

/**
 * ProduitCommande Chart Controller
 * 
 * @author Wahid Gazzah
 * @since 27/04/2016
 *
 */

@RestController
@RequestMapping("/produitCommandeChart")
public class ProduitCommandeChartController {

	private static final Logger logger = LoggerFactory.getLogger(ProduitCommandeChartController.class);
	
	@Autowired
	IProduitCommandeService produitCommandeService;
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheProduitCommandeValue rechercherMultiCritereBonSortieFini(@RequestBody RechercheMulticritereProduitCommandeValue request) {
		 
		logger.info("rechercheMulticritere: Delegating request {} to service layer.");
		 
		return produitCommandeService.rechercherMultiCritere(request);
		
	 }
}
