package com.gpro.consulting.tiers.gc.rest.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.gc.coordination.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheProduitCommandeValue;
import com.gpro.consulting.tiers.gc.service.IProduitCommandeService;


/**
 * @author Wahid Gazzah
 * @since 15/03/2016
 */
@Controller
@RequestMapping("produitCommande")
public class ProduitCommandeRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(ProduitCommandeRestImpl.class);
	
	@Autowired
	IProduitCommandeService produitCommandeService;
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheProduitCommandeValue rechercherMultiCritereBonSortieFini(@RequestBody RechercheMulticritereProduitCommandeValue request) {
		 
		logger.info("rechercheMulticritere: Delegating request {} to service layer.");
		 
		return produitCommandeService.rechercherMultiCritere(request);
		
	 }
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET , produces = "application/json")
	public @ResponseBody List<ProduitCommandeValue> getAvailableBonSortieFiniRef() {
		
		logger.info("Get all produitCommande");
		
		return produitCommandeService.getAll();
	}
	
	// Added on 22/03/2016, by Ameni
	@RequestMapping(value = "/supprimerProduitCommande:{id}", method = RequestMethod.DELETE)
	public void supprimerProduitCommande(@PathVariable("id") Long id) {
		logger.info("suppresion d'un ProduitCommande: Delegating request {} to service layer. ");
		produitCommandeService.supprimerProduitCommande(id);
	}

}
