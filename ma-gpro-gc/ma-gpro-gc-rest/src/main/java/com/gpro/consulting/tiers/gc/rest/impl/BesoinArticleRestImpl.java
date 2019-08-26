package com.gpro.consulting.tiers.gc.rest.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.gc.coordination.besoinarticle.value.BesoinArticleValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.vue.BesoinArticleVue;
import com.gpro.consulting.tiers.gc.service.IBesoinArticleService;

/**
 * @author Wahid Gazzah
 * @since 18/04/2016
 */

@RestController
@RequestMapping("/besoinArticle")
public class BesoinArticleRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(BesoinArticleRestImpl.class);

	@Autowired
	IBesoinArticleService besoinArticleService;
	
	@RequestMapping(value = "/getAllBesoinArticle", method = RequestMethod.GET, produces = "application/json")
	public List<BesoinArticleValue> getAll() {
		  
		logger.info("Delegating request to Service layer.");
		
		return besoinArticleService.getAllBesoinArticle();
	}
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<BesoinArticleValue> rechercheMulticritere(@RequestBody RechercheMulticritereProduitCommandeValue request) {
		 
		logger.info("rechercheMulticritere: Delegating request to service layer.");
		 
		return besoinArticleService.rechercheMulticritere(request);
		
	 }
	
	@RequestMapping(value = "/getBesoinProduit", method = RequestMethod.GET, produces = "application/json")
	public List<BesoinArticleVue> getBesoinProduit(@RequestParam(value="produitId") Long produitId) {
		
		logger.info("Delegating request to Domain layer.");
		
		return besoinArticleService.getBesoinProduit(produitId);
	}
	
}
