package com.gpro.consulting.tiers.gpao.rest.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ResultatRechecheBonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;
import com.gpro.consulting.tiers.gpao.service.bonsortiefini.IBonSortieFiniService;

/**
 * BonSortieFini Controller
 * 
 * @author Wahid Gazzah
 * @since 08/01/2015
 *
 */
@Controller
@RequestMapping("/bonsortiefini")
public class BonSortieFiniRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(BonSortieFiniRestImpl.class);
	
	@Autowired
	IBonSortieFiniService bonSortieFiniService;
	
	
	
	
	@RequestMapping(value = "/getAvailableListBonSortieFiniRef", method = RequestMethod.GET , produces = "application/json")
	public @ResponseBody List<String> getAvailableBonSortieFiniRef() {
		
		logger.info("getListBonSortieFiniRef");
		
		return bonSortieFiniService.getListBonSortieFiniRef();
	}
	
	// Prepare the list of RouleauFiniValue 
	@RequestMapping(value = "/validateBonSortieFini", method = RequestMethod.POST)
	public @ResponseBody List<ColisValue> validateBonSortieFini(@RequestBody List<String> barreCodeList,
			@RequestParam(value = "id", required = false) Long id) {
		
		logger.info("validateBonSortieFini");

		List<ColisValue> listRouleauFini=new ArrayList<ColisValue>();
		if(barreCodeList!=null && barreCodeList.size()>0){
			listRouleauFini = bonSortieFiniService.validateBonSortieFini(barreCodeList, id);
		}
		
		Collections.sort(listRouleauFini);
		
		return listRouleauFini;
	}
	
	@RequestMapping(value = "/createBonSortieFini", method = RequestMethod.POST)
	public @ResponseBody String createBonSortieFini(@RequestBody BonSortieFiniValue bonSortieFiniValue) {
		
		logger.info("createBonSortieFini: Delegating request to Service layer.");
		
		String id = this.bonSortieFiniService.createBonSortieFini(bonSortieFiniValue);
		
	    return id;
	}
	
	
	@RequestMapping(value = "/getBonSortieFiniById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BonSortieFiniValue getBonSortieFiniById(@PathVariable Long id) {
		  
		logger.info("getBonSortieFiniById: Delegating request id {} to service layer.", id);
		  
		return bonSortieFiniService.getBonSortieFiniById(id);
	}
	
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheBonSortieFiniValue rechercherMultiCritereBonSortieFini(@RequestBody RechercheMulticritereBonSortieFiniValue request) {
		 
		logger.info("rechercheMulticritere: Delegating request {} to service layer.", request);
		 
		ResultatRechecheBonSortieFiniValue vResultatRecherche = bonSortieFiniService.rechercherMultiCritere(request);
	
		
		return vResultatRecherche;
		
		
	 }
	
	@RequestMapping(value = "/updateBonSortieFini", method = RequestMethod.POST)
	public @ResponseBody String updateBonSortieFini(@RequestBody BonSortieFiniValue bonSortieFiniValue) {
	    
		logger.info("UpdateRouleauFini: Delegating request {} to service layer.", bonSortieFiniValue.getId());
		
		return this.bonSortieFiniService.updateBonSortieFini(bonSortieFiniValue);
	}
	  
	@RequestMapping(value = "/deleteBonSortieFini:{id}", method = RequestMethod.DELETE)
	public void deleteBonSortieFini(@PathVariable("id") String id) {
		  
		logger.info("deleteBonSortieFini: Delegating request id {} to service layer.", id);
		  
		bonSortieFiniService.deleteBonSortieFini(Long.valueOf(id));
	}

	// Added By Ghazi on 23102017
	@RequestMapping(value = "/getAvailableListBonSortieFaconRef", method = RequestMethod.GET , produces = "application/json")
	public @ResponseBody List<String> getAvailableBonSortieFaconRef() {
		
		//logger.info("getListBonSortieFiniRef");
		
		return bonSortieFiniService.getListBonSortieFaconRef();
	}
	
	
}
