package com.gpro.consulting.tiers.gpao.domaine;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.RechercheMulticritereCatalogueValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.ResultatRechecheCatalogueValue;

/**
 * Operation Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */
public interface IOperationDomaine {

	public String create(OperationValue operationValue);

	public OperationValue getById(Long id);

	public String update(OperationValue operationValue);

	public void delete(Long id);

	public List<OperationValue> getAll();

	public ResultatRechecheCatalogueValue rechercherMultiCritere(RechercheMulticritereCatalogueValue request);

	/**
	 * @author Zeineb Medimagh
	 * @since 02/12/2016
	 */

	public List<OperationValue> getAllByOF(Long OFId);
	
	public List<OperationValue> getAllByProduit(Long produitId);
	
	/**
	 * @author Hajer AMRI
	 * @since 22/12/2016
	 */
	
	public OperationValue getByCode(String operationCode);
	
	public List<OperationValue> getSwitchComptage(boolean comptage) ;


	
}
