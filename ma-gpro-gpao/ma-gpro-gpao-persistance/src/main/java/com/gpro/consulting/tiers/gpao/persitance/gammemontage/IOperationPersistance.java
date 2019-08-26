package com.gpro.consulting.tiers.gpao.persitance.gammemontage;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.RechercheMulticritereCatalogueValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.ResultatRechecheCatalogueValue;

/**
 * Controle Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */
public interface IOperationPersistance {

	public String create(OperationValue operationValue);

	public OperationValue getById(Long id);

	public String update(OperationValue operationValue);

	public void delete(Long id);

	public List<OperationValue> getAll();

	public ResultatRechecheCatalogueValue rechercherMultiCritere(
			RechercheMulticritereCatalogueValue request);

	public OperationValue getByCode(String operationCode);
	
	public Long getIdByCode(String operationCode);
	
	//Using when we need to return the id, Long value
	public Long createOperation(OperationValue operationValue);
	public Long updateOperation(OperationValue operationValue);
	
	
	/**
	 * @author Zeineb Medimagh
	 * @since 02/12/2016
	 */
	
	public List<OperationValue> getAllByOF(Long OFId);
	
	public List<OperationValue> getSwitchComptage(boolean comptage) ;

	/**
	 * @author Zeineb Medimagh
	 * @since 02/12/2016
	 */
	
	public List<OperationValue> getAllByProduit(Long produitId);
	
}
