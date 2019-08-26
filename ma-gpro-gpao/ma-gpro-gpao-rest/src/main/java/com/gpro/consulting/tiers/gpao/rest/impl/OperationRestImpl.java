package com.gpro.consulting.tiers.gpao.rest.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.RechercheMulticritereCatalogueValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.ResultatRechecheCatalogueValue;
import com.gpro.consulting.tiers.gpao.service.IOperationService;

/**
 * Controle Controller
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */

@RestController
@RequestMapping("/catalogue")
public class OperationRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(OperationRestImpl.class);
	
	@Autowired
	private IOperationService operationService;
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheCatalogueValue rechercherMultiCritere(@RequestBody RechercheMulticritereCatalogueValue request) {
		 

		//Check if all criterias are null so generic search
		request.setOptimized(this.checkForOptimization(request));
		
		//LOGGER.info("rechercheMulticritere: Delegating request to service layer.");

		return operationService.rechercherMultiCritere(request);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody OperationValue controleValue) {
		
		//LOGGER.info("createOperation: Delegating request to Service layer.");
		
		return operationService.create(controleValue);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public OperationValue getById(@PathVariable Long id) {
		  
		//LOGGER.info("getOperationById: Delegating request id {} to service layer."+ id);
		
		return operationService.getById(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody OperationValue controleValue) {
	    
		//LOGGER.info("UpdateOperation: Delegating request to service layer.");
		
		return operationService.update(controleValue);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//LOGGER.info("deleteOperation: Delegating request id {} to service layer."+ id);
		  
		operationService.delete(id);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<OperationValue> getAll() {
		  
		//LOGGER.info("getAllOperation: Delegating request id to service layer.");
		
		return operationService.getAll();
	}
	
	
	@RequestMapping(value = "/getAllByOFId", method = RequestMethod.GET, produces = "application/json")
	public List<OperationValue> getAllByOFId(@RequestParam Long OFId) {
		  
		//LOGGER.info("getAllByOFId: Delegating request id to service layer.");
		
		return operationService.getAllByOF(OFId);
	}
	
	
	@RequestMapping(value = "/getAllByProduit", method = RequestMethod.GET, produces = "application/json")
	public List<OperationValue> getAllByProduit(@RequestParam Long produitId) {
		  
		//LOGGER.info("getAllByOFId: Delegating request id to service layer.");
		
		return operationService.getAllByProduit(produitId);
	}
	
	@RequestMapping(value = "/getByCode", method = RequestMethod.GET, produces = "application/json")
	public OperationValue getByCode(@RequestParam String operationCode) {
		  
		//LOGGER.info("getAllByOFId: Delegating request id to service layer.");
		
		return operationService.getByCode(operationCode);
	}
	
	
	@RequestMapping(value = "/getSwitchComptage", method = RequestMethod.GET, produces = "application/json")
	public List<OperationValue> getSwicthComptage(@RequestParam String comptage) {
		  //System.out.println("comptage   :  "+comptage);
		
		return operationService.getSwitchComptage(Boolean.parseBoolean(comptage));
	}
	
	  
	  public boolean checkForOptimization( RechercheMulticritereCatalogueValue request){
			
			return 	isNullOrEmpty(request.getCode()) &&
					isNullOrEmpty(request.getMachineId()) &&
					isNullOrEmpty(request.getSectionId()) &&
					isNullOrEmpty(request.getDesignation()) &&
					isNullOrEmpty(request.getObservations());
			
		}
		
		public boolean isNullOrEmpty (Object criteria){
			return criteria == null || criteria.toString().length() == 0;
		}
}
