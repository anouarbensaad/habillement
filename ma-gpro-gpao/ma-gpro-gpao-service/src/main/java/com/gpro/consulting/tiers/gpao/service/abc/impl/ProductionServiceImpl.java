package com.gpro.consulting.tiers.gpao.service.abc.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.abc.value.ProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.RechercheRechecheProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ResultatRechecheProductionValue;
import com.gpro.consulting.tiers.gpao.domaine.abc.IProductionDomaine;
import com.gpro.consulting.tiers.gpao.service.abc.IProductionService;

/**
 * implementation of {@link IProductionService}
 * 
 * @author Wahid Gazzah
 * @since 11/05/2016
 *
 */

@Service
@Transactional
public class ProductionServiceImpl implements IProductionService{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductionServiceImpl.class);

	@Autowired
	IProductionDomaine productionDomaine;
	
	@Override
	public String create(ProductionValue request) {
		
		//LOGGER.info("createProduction: Delegating request to Domaine layer.");
		
		return productionDomaine.create(request);
	}

	@Override
	public ProductionValue getById(Long id) {
		
		//LOGGER.info("getProductionById: Delegating request {} to Domaine layer.",id);
		
		return productionDomaine.getById(id);
	}

	@Override
	public String update(ProductionValue request) {
		
		//LOGGER.info("updateProduction: Delegating request to Domaine layer.");
		
		return productionDomaine.update(request);
	}

	@Override
	public void delete(Long id) {
		
		//LOGGER.info("deleteProductionValue: Delegating request {} to Domaine layer.",id);
		
		productionDomaine.delete(id);
	}

	@Override
	public List<ProductionValue> getAll(){
		
		//LOGGER.info("getAll: Delegating request to Domaine layer.");
		
		return productionDomaine.getAll();
	}

	@Override
	public ResultatRechecheProductionValue rechercherMultiCritere(
			RechercheRechecheProductionValue request) {
		
		//LOGGER.info("rechercherMultiCritere: Delegating request to Domaine layer.");
		
		return productionDomaine.rechercherMultiCritere(request);
	}
}
