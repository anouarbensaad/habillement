package com.gpro.consulting.tiers.gpao.service.abc;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.abc.value.ProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.RechercheRechecheProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ResultatRechecheProductionValue;

/**
 * Production Service interface
 * 
 * @author Wahid Gazzah
 * @since 11/05/2016
 *
 */
public interface IProductionService {
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(ProductionValue request);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ProductionValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(ProductionValue request);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ProductionValue> getAll();

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultatRechecheProductionValue rechercherMultiCritere(
			RechercheRechecheProductionValue request);

}
