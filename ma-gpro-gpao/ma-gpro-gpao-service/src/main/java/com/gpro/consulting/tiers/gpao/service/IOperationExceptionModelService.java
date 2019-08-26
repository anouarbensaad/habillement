package com.gpro.consulting.tiers.gpao.service;

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

/**
 * @author Wahid Gazzah
 * @since 08/08/2016
 */
public interface IOperationExceptionModelService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public CreateResponseValue create(OperationValue operationValue) 
			throws FonctionnelleException, TechniqueException;

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public UpdateResponseValue update(OperationValue operationValue) 
			throws FonctionnelleException, TechniqueException;
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public DeleteResponseValue delete(Long id) 
			throws FonctionnelleException, TechniqueException;
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public OperationResponseValue getById(Long id) 
			throws FonctionnelleException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListOperationResponseValue getAll() 
			throws FonctionnelleException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheOperationValue rechercherMultiCritere(
			RechercheMulticritereCatalogueValue request) 
					throws FonctionnelleException;
}
