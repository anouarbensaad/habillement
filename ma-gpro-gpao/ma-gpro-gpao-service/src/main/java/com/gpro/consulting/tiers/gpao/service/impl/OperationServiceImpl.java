package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.RechercheMulticritereCatalogueValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.ResultatRechecheCatalogueValue;
import com.gpro.consulting.tiers.gpao.domaine.IOperationDomaine;
import com.gpro.consulting.tiers.gpao.service.IOperationService;

/**
 * implementation of {@link IOperationService}
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */

@Service
@Transactional
public class OperationServiceImpl implements IOperationService{
	
	private static final Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);

	@Autowired
	IOperationDomaine operationDomaine;
	
	@Override
	public String create(OperationValue controleValue) {
		
		//LOGGER.info("createOperation: Delegating request to Domaine layer.");
		
		return operationDomaine.create(controleValue);
	}

	@Override
	public OperationValue getById(Long id) {
		
		//LOGGER.info("getOperationById: Delegating request {} to Domaine layer."+id);
		
		return operationDomaine.getById(id);
	}

	@Override
	public String update(OperationValue controleValue) {
		
		//LOGGER.info("updateOperation: Delegating request to Domaine layer.");
		
		return operationDomaine.update(controleValue);
	}

	@Override
	public void delete(Long id) {
		
		//LOGGER.info("deleteOperation: Delegating request {} to Domaine layer."+id);
		
		operationDomaine.delete(id);
	}

	@Override
	public List<OperationValue> getAll(){
		
		//LOGGER.info("getAll: Delegating request to Domaine layer.");
		
		return operationDomaine.getAll();
	}

	@Override
	public ResultatRechecheCatalogueValue rechercherMultiCritere(
			RechercheMulticritereCatalogueValue request) {
		
		return operationDomaine.rechercherMultiCritere(request);
	}

	@Override
	public List<OperationValue> getAllByOF(Long OFId) {
		return operationDomaine.getAllByOF(OFId);
	}

	@Override
	public List<OperationValue> getAllByProduit(Long produitId) {
		return operationDomaine.getAllByProduit(produitId);
	}

	@Override
	public OperationValue getByCode(String operationCode) {
		return operationDomaine.getByCode(operationCode);
	}

	@Override
	public List<OperationValue> getSwitchComptage(boolean comptage) {
		// TODO Auto-generated method stub
		return operationDomaine.getSwitchComptage(comptage);
	}
	
}
