package com.gpro.consulting.tiers.gpao.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.socle.j2ee.mt.socle.exception.FonctionnelleException;
import com.erp.socle.j2ee.mt.socle.exception.TechniqueException;
import com.gpro.consulting.tiers.commun.coordination.response.value.CreateResponseValue;
import com.gpro.consulting.tiers.commun.coordination.response.value.DeleteResponseValue;
import com.gpro.consulting.tiers.commun.coordination.response.value.UpdateResponseValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.ListOperationResponseValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationResponseValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.RechercheMulticritereCatalogueValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.ResultatRechecheOperationValue;
import com.gpro.consulting.tiers.gpao.domaine.IOperationExceptionModelDomaine;
import com.gpro.consulting.tiers.gpao.service.IOperationExceptionModelService;

/**
 * implementation of {@link IOperationExceptionModelService}
 * 
 * @author Wahid Gazzah
 * @since 08/08/2016
 *
 */

@Service
@Transactional
public class OperationExceptionModelServiceImpl implements IOperationExceptionModelService{

	
	private static final Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);

	@Autowired
	private IOperationExceptionModelDomaine operationDomaine;
	
	@Override
	public CreateResponseValue create(OperationValue operationValue) 
			throws FonctionnelleException, TechniqueException{
		
		//LOGGER.info("createOperation: Delegating request to Domaine layer.");
		
		return operationDomaine.create(operationValue);
	}
	
	@Override
	public UpdateResponseValue update(OperationValue operationValue) 
			throws FonctionnelleException, TechniqueException{
		
		//LOGGER.info("updateOperation: Delegating request to Domaine layer.");
		
		return operationDomaine.update(operationValue);
	}

	@Override
	public OperationResponseValue getById(Long id) 
			throws FonctionnelleException{
		
		//LOGGER.info("getOperationById: Delegating request {} to Domaine layer.",id);
		
		return operationDomaine.getById(id);
	}


	@Override
	public DeleteResponseValue delete(Long id) 
			throws FonctionnelleException, TechniqueException{
		
		//LOGGER.info("deleteOperation: Delegating request {} to Domaine layer.",id);
		
		return operationDomaine.delete(id);
	}

	@Override
	public ListOperationResponseValue getAll() 
			throws FonctionnelleException{
		
		//LOGGER.info("getAll: Delegating request to Domaine layer.");
		
		return operationDomaine.getAll();
	}

	@Override
	public ResultatRechecheOperationValue rechercherMultiCritere(
			RechercheMulticritereCatalogueValue request) 
					throws FonctionnelleException{
		
		return operationDomaine.rechercherMultiCritere(request);
	}
}
