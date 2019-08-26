package com.gpro.consulting.tiers.gpao.domaine.abc.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.abc.value.ProductionElementValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.RechercheRechecheProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ResultatRechecheProductionValue;
import com.gpro.consulting.tiers.gpao.domaine.abc.IProductionDomaine;
import com.gpro.consulting.tiers.gpao.persitance.abc.IProductionPersistance;

/**
 * implementation of {@link IProductionDomaine}
 * 
 * @author Wahid Gazzah
 * @since 11/05/2016
 *
 */

@Component
public class ProductionDomaineImpl implements IProductionDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductionDomaineImpl.class);
	
	private static final Double ZERO = 0D;
	
	@Autowired
	private IProductionPersistance productionPersistance;
	
	@Override
	public String create(ProductionValue request) {
		
		if(request != null){
			
			for(ProductionElementValue element : request.getListProductionElement()){
				
				Double rend = ZERO;
				
				if(validDoubleValue(element.getDem()) && validDoubleValue(element.getProd()) ){
					
					rend = (element.getProd().doubleValue() / element.getDem().doubleValue());
				}
					
				element.setRend(rend);
				element.setDate(request.getDate());
			}
		}
		
		return productionPersistance.create(request);
	}

	@Override
	public String update(ProductionValue request) {
		
		if(request != null){
			
			for(ProductionElementValue element : request.getListProductionElement()){
				
				Double rend = ZERO;
				
				if(validDoubleValue(element.getDem()) && validDoubleValue(element.getProd()) ){
					
					rend = (element.getProd().doubleValue() / element.getDem().doubleValue());
				}
					
				element.setRend(rend);
				element.setDate(request.getDate());
			}
		}
		
		return productionPersistance.update(request);
	}

	@Override
	public ProductionValue getById(Long id) {
		
		ProductionValue productionValue = productionPersistance.getById(id);
		
		if(productionValue != null){
			
			List <ProductionElementValue> listElement = new ArrayList < ProductionElementValue >();
			
			for(ProductionElementValue element : productionValue.getListProductionElement()){
				listElement.add(element);
			}
			
			Collections.sort(listElement);
			productionValue.setListProductionElement(new TreeSet<>(listElement));			
		}
		
		return productionValue;
	}

	@Override
	public void delete(Long id) {
		
		productionPersistance.delete(id);
	}

	@Override
	public List<ProductionValue> getAll() {
		
		List<ProductionValue> result = productionPersistance.getAll();
		
		if(result != null){
			
			for(ProductionValue productionValue : result){
				
				List <ProductionElementValue> listElement = new ArrayList < ProductionElementValue >();
				
				for(ProductionElementValue element : productionValue.getListProductionElement()){
					listElement.add(element);
				}
				
				Collections.sort(listElement);
				productionValue.setListProductionElement(new TreeSet<>(listElement));	
			}
		}
		
		return result;
	}
	

	@Override
	public ResultatRechecheProductionValue rechercherMultiCritere(
			RechercheRechecheProductionValue request) {
		
		//LOGGER.info("rechercheMulticritere: Delegating request to Persistance layer.");
		  
		return productionPersistance.rechercherMultiCritere(request);
	}
	
	public boolean validDoubleValue(Long value){
		
		if(value != null & value != 0)
			return true;
		else
			return false;
		
	}

}
