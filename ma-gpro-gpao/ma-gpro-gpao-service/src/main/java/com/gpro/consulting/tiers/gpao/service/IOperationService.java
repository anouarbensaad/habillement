package com.gpro.consulting.tiers.gpao.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.RechercheMulticritereCatalogueValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.ResultatRechecheCatalogueValue;

/**
 * @author Wahid Gazzah
 * @since 08/04/2016
 */
public interface IOperationService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(OperationValue controleValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public OperationValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(OperationValue controleValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<OperationValue> getAll();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheCatalogueValue rechercherMultiCritere(RechercheMulticritereCatalogueValue request);

	/**
	 * @author Zeineb Medimagh
	 * @since 02/12/2016
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<OperationValue> getAllByOF(Long OFId);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<OperationValue> getAllByProduit(Long produitId);

	/**
	 * @author Hajer AMRI
	 * @since 22/12/2016
	 */

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public OperationValue getByCode(String operationCode);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<OperationValue> getSwitchComptage(boolean comptage) ;

}
