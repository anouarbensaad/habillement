package com.gpro.consulting.tiers.gpao.rest.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.gpao.coordination.abc.value.ProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.RechercheRechecheProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ResultatRechecheProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ChaineValue;
import com.gpro.consulting.tiers.gpao.service.IChaineService;
import com.gpro.consulting.tiers.gpao.service.abc.IProductionService;

/**
 * Production Rest Controller
 * 
 * @author Wahid Gazzah
 * @since 11/04/2016
 *
 */

@RestController
@RequestMapping("/production")
public class ProductionRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductionRestImpl.class);
	
	@Autowired
	private IProductionService productionService;
	
	@Autowired
	private IChaineService chaineService;
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheProductionValue rechercherMultiCritere(
			@RequestBody RechercheRechecheProductionValue request) {
	   
		 //LOGGER.info("rechercheMulticritere: Delegating request to service layer.");
		 
		 return productionService.rechercherMultiCritere(request);
	 }
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody ProductionValue request) {
		
		//LOGGER.info("createProduction: Delegating request to Service layer.");
		
		Map<Long, String> mapChaineDesignation = new HashMap<Long, String>();
		for(ChaineValue chaine : chaineService.listeChaine()){
			if(!mapChaineDesignation.containsKey(chaine.getId())){
				mapChaineDesignation.put(chaine.getId(), chaine.getDesignation());
			}
		}

		Long chaineId = request.getChaineId();

		if(chaineId !=null){
			mapChaineDesignation.containsKey(chaineId);
			request.setChaineDesignation(mapChaineDesignation.get(chaineId));
		}
		
		return productionService.create(request);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public ProductionValue getById(@PathVariable Long id) {
		  
		//LOGGER.info("getProductionById: Delegating request id {} to service layer.", id);
		
		return productionService.getById(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody ProductionValue request) {
	    
		//LOGGER.info("UpdateProduction: Delegating request to service layer.");
		
		Map<Long, String> mapChaineDesignation = new HashMap<Long, String>();
		for(ChaineValue chaine : chaineService.listeChaine()){
			if(!mapChaineDesignation.containsKey(chaine.getId())){
				mapChaineDesignation.put(chaine.getId(), chaine.getDesignation());
			}
		}

		Long chaineId = request.getChaineId();

		if(chaineId !=null){
			mapChaineDesignation.containsKey(chaineId);
			request.setChaineDesignation(mapChaineDesignation.get(chaineId));
		}
		
		return productionService.update(request);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//LOGGER.info("deleteProduction: Delegating request id {} to service layer.", id);
		  
		productionService.delete(id);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<ProductionValue> getAll() {
		  
		//LOGGER.info("getAllProduction: Delegating request id to service layer.");
		
		return productionService.getAll();
	}
	
}
