package com.gpro.consulting.tiers.gpao.persitance.gammeof.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ElementGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.GammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ResultatRechecheGammeOfElementValue;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.entity.ElementGammeOfEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.entity.GammeOfEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 10/05/2016
 *
 */

@Component
public class GammeOfPersistanceUtilities {
	
	private static final Logger logger = LoggerFactory.getLogger(GammeOfPersistanceUtilities.class);

	public ElementGammeOfValue toValue(ElementGammeOfEntity entity) {
		
		ElementGammeOfValue dto = new ElementGammeOfValue();
		
		dto.setId(entity.getId());
		dto.setOperationId(entity.getOperationId());
		dto.setTemps(entity.getTemps());
		dto.setPdh(entity.getPdh());
		dto.setSectionId(entity.getSectionId());
		dto.setMachineId(entity.getMachineId());
		dto.setObservations(entity.getObservations());
		dto.setComptage(entity.getComptage());
		dto.setOrdre(entity.getOrdre());
		
		if(entity.getGammeOf() != null){
			dto.setGammeOfId(entity.getGammeOf().getId());
		}
		
		dto.setBlSuppression(entity.getBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		
		return dto;
	}
	
	public GammeOfValue toValue(GammeOfEntity entity) {
		
		GammeOfValue dto = new GammeOfValue();
		
		dto.setId(entity.getId());
		dto.setTempsTotal(entity.getTempsTotal());
		dto.setNbOperation(entity.getNbOperation());
		dto.setObservations(entity.getObservations());
		dto.setDate(entity.getDate());
		dto.setOrdreFabricationId(entity.getOrdreFabricationId());
		
	    if(entity.getListElementGammeOf()!= null){
	    	Set<ElementGammeOfValue> list = new HashSet<ElementGammeOfValue>();
		     for (ElementGammeOfEntity detFactureVenteEntity : entity.getListElementGammeOf()) {
		    	 ElementGammeOfValue detFactureVenteValue = toValue(detFactureVenteEntity);
		    	 list.add(detFactureVenteValue);
		    }
		     dto.setListElementGammeOf(list);
		}

		dto.setBlSuppression(entity.getBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		return dto;
	}
	
	
	public ElementGammeOfEntity toEntity(ElementGammeOfValue dto) {
		//LOGGER.info("========== ToEntity ElementGammeOF");
		ElementGammeOfEntity entity = new ElementGammeOfEntity();
		
		entity.setId(dto.getId());
		
		entity.setOperationId(dto.getOperationId());
		entity.setTemps(dto.getTemps());
		entity.setPdh(dto.getPdh());
		entity.setSectionId(dto.getSectionId());
		entity.setMachineId(dto.getMachineId());
		entity.setObservations(dto.getObservations());
		entity.setComptage(dto.getComptage());
		entity.setOrdre(dto.getOrdre());
		
		if(dto.getGammeOfId() != null){
			GammeOfEntity gammeOfEntity = new GammeOfEntity();
			gammeOfEntity.setId(dto.getGammeOfId());
			entity.setGammeOf(gammeOfEntity);
		}

		entity.setBlSuppression(dto.getBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
		
		return entity;
	}
	
	public GammeOfEntity toEntity(GammeOfValue dto) {
		//LOGGER.info("============= ToEntity GammeOf ");
		GammeOfEntity entity = new GammeOfEntity();
		
		entity.setId(dto.getId());
		
		entity.setTempsTotal(dto.getTempsTotal());
		entity.setNbOperation(dto.getNbOperation());
		entity.setObservations(dto.getObservations());
		entity.setDate(dto.getDate());
		entity.setOrdreFabricationId(dto.getOrdreFabricationId());
	    
	    if(dto.getListElementGammeOf() != null){
		     Set<ElementGammeOfEntity> list = new HashSet <ElementGammeOfEntity>();
		     for (ElementGammeOfValue elementGammeValue : dto.getListElementGammeOf()) {
		    	 ElementGammeOfEntity elementGammeEntity = toEntity(elementGammeValue);
		    	 elementGammeEntity.setGammeOf(entity);
		    	 list.add(elementGammeEntity);
		    }
		     entity.setListElementGammeOf(list);
		}
	    
		entity.setBlSuppression(dto.getBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
		
		return entity;
	}
	

	public List<GammeOfValue> listGammeOfToValue(List<GammeOfEntity> listEntity) {
		
		List<GammeOfValue> list = new ArrayList<GammeOfValue>();
		
		for(GammeOfEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}


	public ResultatRechecheGammeOfElementValue toResultatRechecheGammeOfElementValue(GammeOfEntity entity) {
		
		ResultatRechecheGammeOfElementValue dto = new ResultatRechecheGammeOfElementValue();
		
		dto.setId(entity.getId());
		dto.setTempsTotal(entity.getTempsTotal());
		dto.setNbOperation(entity.getNbOperation());
		dto.setObservations(entity.getObservations());
		dto.setDate(entity.getDate());
		dto.setOrdreFabricationId(entity.getOrdreFabricationId());
		
		return dto;
	}
}
