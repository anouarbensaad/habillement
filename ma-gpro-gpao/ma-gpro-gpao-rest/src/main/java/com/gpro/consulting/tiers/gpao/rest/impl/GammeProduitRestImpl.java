package com.gpro.consulting.tiers.gpao.rest.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.service.IProduitService;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ElementGammeValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.GammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.RechercheMulticritereGammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitElementValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.RechercheMulticritereCatalogueValue;
import com.gpro.consulting.tiers.gpao.service.IGammeProduitService;

/**
 * Gamme Produit Controller
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */

@RestController
@RequestMapping("/gammeproduit")
public class GammeProduitRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(GammeProduitRestImpl.class);
	
	@Autowired
	private IGammeProduitService gammeProduitService;
	
	@Autowired
	private IProduitService produitService;
	
	@RequestMapping(value = "/getProduitListAvailable", method = RequestMethod.GET, produces = "application/json")
	public List<ProduitValue> getProduitAvailable() {
		  
		//LOGGER.info("getProduitAvailable: Delegating request id to service layer.");
		
		return gammeProduitService.getProduitListAvailable();
	}
	
	@RequestMapping(value = "/getProduitListUsed", method = RequestMethod.GET, produces = "application/json")
	public List<ProduitValue> getProduitListUsed() {
		  
		//LOGGER.info("getProduitListUsed: Delegating request id to service layer.");
		
		return gammeProduitService.getProduitListUsed();
	}
	
	@RequestMapping(value = "/getByProduitId:{produitId}", method = RequestMethod.GET, produces = "application/json")
	public GammeProduitValue getByProduitId(@PathVariable Long produitId) {
		  
		//LOGGER.info("getGammeProduitByProduitId: Delegating request produitId {} to service layer.", produitId);
		
		return gammeProduitService.getByProduitId(produitId);
	}
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheGammeProduitValue rechercherMultiCritere(@RequestBody RechercheMulticritereGammeProduitValue request) {

		//Check if all criterias are null so generic search
		request.setOptimized(this.checkForOptimization(request));
		
		ResultatRechecheGammeProduitValue result = gammeProduitService.rechercherMultiCritere(request,true);
		
		if(result != null){
			
			for(ResultatRechecheGammeProduitElementValue element : result.getList()){
				
				if(element.getProduitId() != null){
					
					ProduitValue produit = produitService.rechercheProduitById(element.getProduitId(), true);
					
					if(produit != null){
						element.setProduitDesignation(produit.getDesignation());
						element.setProduitReference(produit.getReference());
						
					}
				}
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody GammeProduitValue gammeProduitValue) {
		
		//LOGGER.info("createGammeProduit: Delegating request to Service layer.");
		
		for(ElementGammeValue element : gammeProduitValue.getListElementGamme()){
			
			//LOGGER.info("-------------temps: "+element.getTemps());
		}
		
		return gammeProduitService.create(gammeProduitValue);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public GammeProduitValue getById(@PathVariable Long id) {
		  
		//LOGGER.info("getGammeProduitById: Delegating request id {} to service layer.", id);
		
		return gammeProduitService.getById(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody GammeProduitValue gammeProduitValue) {
	    
		//LOGGER.info("UpdateGammeProduit: Delegating request to service layer.");
		
		return gammeProduitService.update(gammeProduitValue);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//LOGGER.info("deleteGammeProduit: Delegating request id {} to service layer.", id);
		  
		gammeProduitService.delete(id);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<GammeProduitValue> getAll() {
		  
		//LOGGER.info("getAllGammeProduit: Delegating request id to service layer.");
		
		return gammeProduitService.getAll();
	}
	
	public boolean checkForOptimization( RechercheMulticritereGammeProduitValue request){
		
		return 	isNullOrEmpty(request.getProduitId()) &&
				isNullOrEmpty(request.getMachineId()) &&
				isNullOrEmpty(request.getSectionId()) &&
				isNullOrEmpty(request.getTempsTotalMax()) &&

				isNullOrEmpty(request.getProduitReference()) &&
				isNullOrEmpty(request.getProduitDesignation()) &&
				isNullOrEmpty(request.getMachineDesignation()) &&
				isNullOrEmpty(request.getSectionDesignation()) &&
				
				isNullOrEmpty(request.getTempsTotalMin());
		
	}
	
	public boolean isNullOrEmpty (Object criteria){
		return criteria == null || criteria.toString().length() == 0;
	}

}
