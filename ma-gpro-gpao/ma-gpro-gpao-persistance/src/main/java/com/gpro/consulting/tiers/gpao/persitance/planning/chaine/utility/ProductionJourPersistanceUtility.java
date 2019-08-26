package com.gpro.consulting.tiers.gpao.persitance.planning.chaine.utility;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.ProductionJourElementValue;
import com.gpro.consulting.tiers.gpao.persitance.planning.chaine.entity.ProductionJourEntity;

import ch.qos.logback.classic.Logger;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author HAMDI Etteieb
 */

@Component
public class ProductionJourPersistanceUtility {
	private static final Logger logger = (Logger) LoggerFactory
			.getLogger(ProductionJourPersistanceUtility.class);

	public static ProductionJourElementValue toValue(ProductionJourEntity entity) {

		ProductionJourElementValue dto = new ProductionJourElementValue();

		dto.setId(entity.getId());
		dto.setQuantite(entity.getQuantite());
		dto.setIdElementPlanning(entity.getIdElementPlanning());
		dto.setDate(entity.getDate());
		dto.setObservation(entity.getObservation());

		dto.setDateCreation(entity.getDateCreation());
		dto.setBlSuppression(entity.getBlSuppression());
		dto.setDateModification(entity.getDateModification());
		dto.setDateSuppression(entity.getDateSuppression());
        dto.setOf(entity.getOf());
        dto.setChaine(entity.getChaine());
        dto.setSemaine(entity.getSemaine());
        dto.setPeriode(entity.getPeriode());
        dto.setPhase(entity.getPhase());
  
		dto.setEffectif(entity.getEffectif());
		dto.setQteBefore(entity.getQuantite());
		

		dto.setPartieInterresId(entity.getPartieInterresId());
        dto.setQteNonConf(entity.getQteNonConf());
		
		return dto;
	}

	public static ProductionJourEntity toEntity(ProductionJourElementValue dto) {

		ProductionJourEntity entity = new ProductionJourEntity();

		entity.setId(dto.getId());
		entity.setDate(dto.getDate());
		entity.setQuantite(dto.getQuantite());
		entity.setObservation(dto.getObservation());
		entity.setIdElementPlanning(dto.getIdElementPlanning());

		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setBlSuppression(dto.getBlSuppression());
        entity.setOf(dto.getOf());
        entity.setChaine(dto.getChaine());
		entity.setPhase(dto.getPhase());
		entity.setPeriode(dto.getPeriode());
		entity.setSemaine(dto.getSemaine());

		entity.setEffectif(dto.getEffectif());
		entity.setRendement(dto.getRendement());

		
		entity.setPartieInterresId(dto.getPartieInterresId());
		entity.setQteNonConf(dto.getQteNonConf());
		
		return entity;
	}

}
