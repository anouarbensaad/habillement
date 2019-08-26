package com.gpro.consulting.tiers.gpao.rest.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.erp.socle.j2ee.mt.socle.exception.FonctionnelleException;
import com.erp.socle.j2ee.mt.socle.exception.TechniqueException;
import com.erp.socle.j2ee.mt.socle.trace.GestionnaireTraces;
import com.gpro.consulting.tiers.commun.coordination.response.value.CreateResponseValue;
import com.gpro.consulting.tiers.commun.coordination.response.value.DeleteResponseValue;
import com.gpro.consulting.tiers.commun.coordination.response.value.ErrorType;
import com.gpro.consulting.tiers.commun.coordination.response.value.ErrorValue;
import com.gpro.consulting.tiers.commun.coordination.response.value.UpdateResponseValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.ListOperationResponseValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationResponseValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.RechercheMulticritereCatalogueValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.ResultatRechecheOperationValue;
import com.gpro.consulting.tiers.gpao.service.IOperationExceptionModelService;

/**
 * Operation Exception Model Controller
 * 
 * @author Wahid Gazzah
 * @since 08/08/2016
 *
 */

@RestController
@RequestMapping("/exceptionModel/operation")
public class OperationExceptionModelRestImpl {

    /** Logger Technique */
    private Logger LOGGER_TECHNIQUE = GestionnaireTraces.getInstance().getLogTech();

    /** Logger Fonctionnel */
    private Logger LOGGER_FONCTIONNEL = GestionnaireTraces.getInstance().getLogFonc();
    
	@Autowired
	private IOperationExceptionModelService operationService;
		
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheOperationValue rechercherMultiCritere(@RequestBody RechercheMulticritereCatalogueValue request) 
			throws FonctionnelleException{
		 
		try {
			
			ResultatRechecheOperationValue result = operationService.rechercherMultiCritere(request);
			
			return result;
			
		}catch(FonctionnelleException e){
			
			ResultatRechecheOperationValue result = new ResultatRechecheOperationValue();
			
			ErrorValue error = new ErrorValue();
			error.setErrorType(ErrorType.FONCTIONNEL);
			error.setCode(e.getListErreurFonctionnelle().get(0).getCode());
			error.setMessage(e.getListErreurFonctionnelle().get(0).getMessage());
			
			result.getErrors().add(error);
			
			return result;
		}
		 
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public CreateResponseValue create(@RequestBody OperationValue request) 
			throws FonctionnelleException, TechniqueException{
		
		try {
			
			CreateResponseValue result = operationService.create(request);
			
			return result;
			
		}catch(FonctionnelleException e){
			
			CreateResponseValue result = new CreateResponseValue();
			
			ErrorValue error = new ErrorValue();
			error.setErrorType(ErrorType.FONCTIONNEL);
			error.setCode(e.getListErreurFonctionnelle().get(0).getCode());
			error.setMessage(e.getListErreurFonctionnelle().get(0).getMessage());
			
			result.getErrors().add(error);
			
			return result;
		}catch (TechniqueException e) {
			
			CreateResponseValue result = new CreateResponseValue();
			
			ErrorValue error = new ErrorValue();
			error.setErrorType(ErrorType.TECHNIQUE);
			error.setCode(e.getListErreurTechnique().get(0).getCode());
			error.setMessage(e.getListErreurTechnique().get(0).getMessage());
			
			result.getErrors().add(error);
			
			return result;
		}	
		
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public UpdateResponseValue update(@RequestBody OperationValue request) 
			throws FonctionnelleException, TechniqueException{
	    
		try {
			
			UpdateResponseValue result = operationService.update(request);
			
			return result;
			
		}catch(FonctionnelleException e){
			
			UpdateResponseValue result = new UpdateResponseValue();
			
			ErrorValue error = new ErrorValue();
			error.setErrorType(ErrorType.FONCTIONNEL);
			error.setCode(e.getListErreurFonctionnelle().get(0).getCode());
			error.setMessage(e.getListErreurFonctionnelle().get(0).getMessage());
			
			result.getErrors().add(error);
			
			return result;
		}catch (TechniqueException e) {
			
			UpdateResponseValue result = new UpdateResponseValue();
			
			ErrorValue error = new ErrorValue();
			error.setErrorType(ErrorType.TECHNIQUE);
			error.setCode(e.getListErreurTechnique().get(0).getCode());
			error.setMessage(e.getListErreurTechnique().get(0).getMessage());
			
			result.getErrors().add(error);
			
			return result;
		}
		
	}
	
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public DeleteResponseValue delete(@PathVariable Long id) 
			throws FonctionnelleException{
		  
		try {
			
			DeleteResponseValue result = operationService.delete(id);
			
			return result;
			
		}catch(FonctionnelleException e){
			
			DeleteResponseValue result = new DeleteResponseValue();
			
			ErrorValue error = new ErrorValue();
			error.setErrorType(ErrorType.FONCTIONNEL);
			error.setCode(e.getListErreurFonctionnelle().get(0).getCode());
			error.setMessage(e.getListErreurFonctionnelle().get(0).getMessage());
			
			result.getErrors().add(error);
			
			return result;
		}
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public OperationResponseValue getById(@PathVariable Long id){
		
		try {
		
			OperationResponseValue result = operationService.getById(id);
			
			return result;
		}
		catch (FonctionnelleException e) {
			
			LOGGER_FONCTIONNEL.info(e.getListErreurFonctionnelle().get(0).getMessage());
			
			OperationResponseValue result = new OperationResponseValue();
			
			ErrorValue error = new ErrorValue();
			error.setErrorType(ErrorType.FONCTIONNEL);
			error.setCode(e.getListErreurFonctionnelle().get(0).getCode());
			error.setMessage(e.getListErreurFonctionnelle().get(0).getMessage());
			
			result.getErrors().add(error);
	
			return result;
		}
		
	}

	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public ListOperationResponseValue getAll() 
			throws FonctionnelleException{
		  
		try {
			
			ListOperationResponseValue result = operationService.getAll();
			
			return result;
			
		}catch(FonctionnelleException e){
			
			ListOperationResponseValue result = new ListOperationResponseValue();
			
			ErrorValue error = new ErrorValue();
			error.setErrorType(ErrorType.FONCTIONNEL);
			error.setCode(e.getListErreurFonctionnelle().get(0).getCode());
			error.setMessage(e.getListErreurFonctionnelle().get(0).getMessage());
			
			result.getErrors().add(error);
			
			return result;
		}
	}

}
