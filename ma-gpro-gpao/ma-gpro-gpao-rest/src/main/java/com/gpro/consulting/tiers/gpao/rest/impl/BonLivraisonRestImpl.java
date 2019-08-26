package com.gpro.consulting.tiers.gpao.rest.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.ResultatRechecheBonLivraisonValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.gpao.service.bonlivraison.IBonLivraisonService;
import com.gpro.consulting.tiers.gpao.service.bonsortiefini.IBonSortieFiniService;

/**
 * Bon de livraison Controller
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Controller
@RequestMapping("/bonlivraison")
public class BonLivraisonRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(BonLivraisonRestImpl.class);
	
	@Autowired
	private IBonLivraisonService bonLivraisonService;
	
	@Autowired
	private IBonSortieFiniService bonSortieFiniService;
	
	

	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public @ResponseBody ListProduitElementValue validateBonSortieFini(@RequestBody List<String> refBonSortieList,
			@RequestParam(value="livraisonVenteId", required = false ) Long livraisonVenteId) {
		
		ListProduitElementValue list = new ListProduitElementValue();
		
		if(refBonSortieList!=null && refBonSortieList.size()>0){
			list = bonSortieFiniService.getProduitElementList(refBonSortieList, livraisonVenteId);
		}
		
		return list;
	}
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheBonLivraisonValue rechercherMultiCritere(@RequestBody RechercheMulticritereBonLivraisonValue request) {
		 
		logger.info("rechercheMulticritere: Delegating request {} to service layer.", request);
		
		//FACON
//		request.setNatureLivraison("FINI");
		
		ResultatRechecheBonLivraisonValue result = bonLivraisonService.rechercherMultiCritere(request);
		
	
		
		return result;
	 }
	
	@RequestMapping(value = "/createBonLivraison", method = RequestMethod.POST)
	public @ResponseBody String createBonLivraison(@RequestBody LivraisonVenteValue bonLivraisonValue) {
		
		logger.info("createBonLivraison: Delegating request to Service layer.");
		// TEST
			//	//System.out.println("########  Fin de creation de Bon de livraison  REST :   "+new Date());	
				return this.bonLivraisonService.createBonLivraison(bonLivraisonValue);
	}
	
	@RequestMapping(value = "/getBonLivraisonById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody LivraisonVenteValue getBonLivraisonById(@PathVariable Long id) {
		  
		logger.info("getBonLivraisonById: Delegating request id {} to service layer.", id);
		////System.out.println("########  debut de getByID de Bon de livraison  REST :   "+new Date());	

		LivraisonVenteValue livraisonVenteValue = bonLivraisonService.getBonLivraisonById(id);
		
		
		  
		return livraisonVenteValue;
	}
	
	@RequestMapping(value = "/updateBonLivraison", method = RequestMethod.POST)
	public @ResponseBody String updateBonLivraison(@RequestBody LivraisonVenteValue bonLivraisonValue) {
	    
		logger.info("UpdateRouleauFini: Delegating request to service layer.");
		
		return this.bonLivraisonService.updateBonLivraison(bonLivraisonValue);
	}
	  
	@RequestMapping(value = "/deleteBonLivraison:{id}", method = RequestMethod.DELETE)
	public void deleteBonLivraison(@PathVariable Long id) {
		  
		logger.info("deleteBonLivraison: Delegating request id {} to service layer.", id);
		  
		bonLivraisonService.deleteBonLivraison(id);
	}
	
	@RequestMapping(value = "/getAvailableListBonLivraisonRef", method = RequestMethod.GET , produces = "application/json")
	public @ResponseBody List<String> getAvailableBonLivraisonRef() {
		
		logger.info("getListBonLivraisonRef");
		
		return bonLivraisonService.getListBonLivraisonRef();
	}
	
	@RequestMapping(value = "/getAvailableListBonLivraisonRefByClient:{idClient}", method = RequestMethod.GET , produces = "application/json")
	public @ResponseBody List<LivraisonVenteVue> getListBonLivraisonRefByClient(@PathVariable Long idClient) {
		
		logger.info("getAvailableListBonLivraisonRefByClient");
		return bonLivraisonService.getListBonLivraisonRefByClient(idClient);
	}
	
	// Added on 03/10/2016, by Zeineb Medimagh
	@RequestMapping(value = "/validateFacon", method = RequestMethod.POST)
	public @ResponseBody ListTraitFaconElementValue validateBonSortieFiniFacon(@RequestBody List<String> refBonSortieList,
			@RequestParam(value="livraisonVenteId", required = false ) Long livraisonVenteId) {
		
		ListTraitFaconElementValue list = new ListTraitFaconElementValue();
		
		if(refBonSortieList!=null && refBonSortieList.size()>0){
			list = bonSortieFiniService.getTraitFaconElementList(refBonSortieList, livraisonVenteId);
		}
		
		return list;
	}
	

	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET , produces = "application/json")
	public @ResponseBody List<LivraisonVenteValue> getAll() {
		
		logger.info("getAll");
		
		return bonLivraisonService.getAll();
	}
	
}
