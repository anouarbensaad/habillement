package com.gpro.consulting.tiers.gpao.persitance.abc;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.abc.value.ProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.RechercheRechecheProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ResultatRechecheProductionValue;

/**
 * Production Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 11/05/2016
 *
 */
public interface IProductionPersistance {
	
	public String create(ProductionValue request);

	public ProductionValue getById(Long id);

	public String update(ProductionValue request);

	public void delete(Long id);

	public List<ProductionValue> getAll();

	public ResultatRechecheProductionValue rechercherMultiCritere(
			RechercheRechecheProductionValue request);

}
