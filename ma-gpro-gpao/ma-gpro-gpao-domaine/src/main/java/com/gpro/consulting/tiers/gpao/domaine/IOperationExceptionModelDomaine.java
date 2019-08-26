package com.gpro.consulting.tiers.gpao.domaine;

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
 * Operation Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 08/08/2016
 *
 */
public interface IOperationExceptionModelDomaine {
	
	public CreateResponseValue create(OperationValue operationValue) 
			throws FonctionnelleException, TechniqueException;

	public UpdateResponseValue update(OperationValue operationValue) 
			throws FonctionnelleException, TechniqueException;
	
	public DeleteResponseValue delete(Long id) 
			throws FonctionnelleException, TechniqueException;
	
	public OperationResponseValue getById(Long id) 
			throws FonctionnelleException;

	public ListOperationResponseValue getAll() 
			throws FonctionnelleException;

	public ResultatRechecheOperationValue rechercherMultiCritere(
			RechercheMulticritereCatalogueValue request) 
					throws FonctionnelleException;

}
