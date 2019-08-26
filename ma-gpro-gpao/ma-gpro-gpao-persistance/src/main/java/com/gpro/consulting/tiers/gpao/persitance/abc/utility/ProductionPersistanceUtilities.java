package com.gpro.consulting.tiers.gpao.persitance.abc.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.abc.value.ProductionElementValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ProductionValue;
import com.gpro.consulting.tiers.gpao.persitance.abc.entity.ProductionElementEntity;
import com.gpro.consulting.tiers.gpao.persitance.abc.entity.ProductionEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 11/05/2016
 *
 */

@Component
public class ProductionPersistanceUtilities {

	public ProductionEntity toEntity(ProductionValue dto) {
		
		ProductionEntity entity = new ProductionEntity();
		
		entity.setId(dto.getId());
		entity.setChaineDesignation(dto.getChaineDesignation());
		entity.setChaineId(dto.getChaineId());
		entity.setDate(dto.getDate());
		entity.setProduitId(dto.getProduitId());
	    
	    if(dto.getListProductionElement()!= null){
		     Set<ProductionElementEntity> list = new HashSet <ProductionElementEntity>();
		     for (ProductionElementValue productionElementValue : dto.getListProductionElement()) {
		    	 ProductionElementEntity productionElementEntity = toEntity(productionElementValue);
		    	 productionElementEntity.setProduction(entity);
		    	 list.add(productionElementEntity);
		    }
		     entity.setListProductionElement(list);
		}
	    
		return entity;
	}
	public ProductionValue toValue(ProductionEntity entity) {
		
		ProductionValue dto = new ProductionValue();
		
		dto.setId(entity.getId());
		dto.setChaineDesignation(entity.getChaineDesignation());
		dto.setChaineId(entity.getChaineId());
		dto.setDate(entity.getDate());
		dto.setProduitId(entity.getProduitId());
		
		if(entity.getListProductionElement()!= null){
			List<ProductionElementValue> list = new ArrayList<ProductionElementValue>();
			
			for (ProductionElementEntity elementEntity : entity.getListProductionElement()) {
				ProductionElementValue elementValue = toValue(elementEntity);
				list.add(elementValue);
		    }
			
			dto.setListProductionElement(new TreeSet<>(list));
		}
		
		return dto;
	}
	

	private ProductionElementEntity toEntity(ProductionElementValue dto) {
		
		ProductionElementEntity entity = new ProductionElementEntity();
		
		entity.setId(dto.getId());
		entity.setHeure(dto.getHeure());
		entity.setDem(dto.getDem());
		entity.setProd(dto.getProd());
		entity.setRend(dto.getRend());
		entity.setDate(dto.getDate());
		
		if(dto.getProductionId() != null){
			ProductionEntity productionEntity = new ProductionEntity();
			productionEntity.setId(dto.getProductionId());
			entity.setProduction(productionEntity);
		}
		
		return entity;
	}

	private ProductionElementValue toValue(ProductionElementEntity entity) {
		
		ProductionElementValue dto = new ProductionElementValue();
		
		dto.setId(entity.getId());
		dto.setHeure(entity.getHeure());
		dto.setDem(entity.getDem());
		dto.setProd(entity.getProd());
		dto.setRend(entity.getRend());
		dto.setDate(entity.getDate());
		
		if(entity.getProduction() != null){
			
			dto.setProductionId(entity.getProduction().getId());
		}
		
		return dto;
	}

	public List<ProductionValue> listProductionToValue(
			List<ProductionEntity> listEntity) {
		
		List<ProductionValue> list = new ArrayList<ProductionValue>();
		
		for(ProductionEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}

}
