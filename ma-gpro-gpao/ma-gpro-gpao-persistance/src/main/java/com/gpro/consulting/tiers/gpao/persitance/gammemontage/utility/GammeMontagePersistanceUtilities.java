package com.gpro.consulting.tiers.gpao.persitance.gammemontage.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ElementGammeValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.GammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitElementValue;
import com.gpro.consulting.tiers.gpao.coordination.machine.value.MachineValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.section.value.SectionValue;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.entity.ElementGammeEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.entity.GammeProduitEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.entity.MachineEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.entity.OperationEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.entity.SectionEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */

@Component
public class GammeMontagePersistanceUtilities {

	
	public ElementGammeValue toValue(ElementGammeEntity entity) {
		
		ElementGammeValue dto = new ElementGammeValue();
		
		dto.setId(entity.getId());
		dto.setOperationId(entity.getOperationId());
		dto.setTemps(entity.getTemps());
		dto.setPdh(entity.getPdh());
		dto.setSectionId(entity.getSectionId());
		dto.setMachineId(entity.getMachineId());
		dto.setObservations(entity.getObservations());
		dto.setComptage(entity.getComptage());
		dto.setOrdre(entity.getOrdre());
		
		if(entity.getGammeProduit() != null){
			dto.setGammeProduitId(entity.getGammeProduit().getId());
		}
		
		dto.setBlSuppression(entity.getBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		
		return dto;
	}
	
	public GammeProduitValue toValue(GammeProduitEntity entity) {
		
		GammeProduitValue dto = new GammeProduitValue();
		
		dto.setId(entity.getId());
		dto.setTempsTotal(entity.getTempsTotal());
		dto.setNbOperation(entity.getNbOperation());
		dto.setObservations(entity.getObservations());
		dto.setDate(entity.getDate());
		dto.setProduitId(entity.getProduitId());
		
	    if(entity.getListElementGamme() != null){
	    	Set<ElementGammeValue> list = new HashSet<ElementGammeValue>();
		     for (ElementGammeEntity detFactureVenteEntity : entity.getListElementGamme()) {
		    	 ElementGammeValue detFactureVenteValue = toValue(detFactureVenteEntity);
		    	 list.add(detFactureVenteValue);
		    }
		     dto.setListElementGamme(list);
		}

		dto.setBlSuppression(entity.getBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		
		return dto;
	}
	
	public MachineValue toValue(MachineEntity entity) {
		
		MachineValue dto = new MachineValue();
		
		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());
		
		dto.setBlSuppression(entity.getBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		
		return dto;
	}
	
	public OperationValue toValue(OperationEntity entity) {
		
		if(entity != null){
			
			OperationValue dto = new OperationValue();
			
			dto.setId(entity.getId());
			dto.setCode(entity.getCode());
			dto.setDesignation(entity.getDesignation());
			dto.setTemps(entity.getTemps());
			dto.setPdh(entity.getPdh());
			dto.setObservations(entity.getObservations());
			dto.setMachineId(entity.getMachineId());
			dto.setSectionId(entity.getSectionId());
			
			dto.setBlSuppression(entity.getBlSuppression());
			dto.setDateSuppression(entity.getDateSuppression());
			dto.setDateCreation(entity.getDateCreation());
			dto.setDateModification(entity.getDateModification());
			dto.setVersion(entity.getVersion());
			dto.setComptage(entity.isComptage());	
			dto.setId(entity.getId());

			return dto;
		
		}else
			return null;
		
	}
	
	public SectionValue toValue(SectionEntity entity) {
		
		SectionValue dto = new SectionValue();
		
		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());
		
		dto.setBlSuppression(entity.getBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		
		dto.setId(entity.getId());

		return dto;
	}
	
	
	public ElementGammeEntity toEntity(ElementGammeValue dto) {
		
		ElementGammeEntity entity = new ElementGammeEntity();
		
		entity.setId(dto.getId());
		
		entity.setOperationId(dto.getOperationId());
		entity.setTemps(dto.getTemps());
		entity.setPdh(dto.getPdh());
		entity.setSectionId(dto.getSectionId());
		entity.setMachineId(dto.getMachineId());
		entity.setObservations(dto.getObservations());
		entity.setComptage(dto.getComptage());
		entity.setOrdre(dto.getOrdre());
		
		if(dto.getGammeProduitId() != null){
			GammeProduitEntity gammeProduitEntity = new GammeProduitEntity();
			gammeProduitEntity.setId(dto.getGammeProduitId());
			entity.setGammeProduit(gammeProduitEntity);
		}

		entity.setBlSuppression(dto.getBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
		
		return entity;
	}
	
	public GammeProduitEntity toEntity(GammeProduitValue dto) {
		
		GammeProduitEntity entity = new GammeProduitEntity();
		
		entity.setId(dto.getId());
		
		entity.setTempsTotal(dto.getTempsTotal());
		entity.setNbOperation(dto.getNbOperation());
		entity.setObservations(dto.getObservations());
		entity.setDate(dto.getDate());
		entity.setProduitId(dto.getProduitId());
	    
	    if(dto.getListElementGamme() != null){
		     Set<ElementGammeEntity> list = new HashSet <ElementGammeEntity>();
		     for (ElementGammeValue elementGammeValue : dto.getListElementGamme()) {
		    	 ElementGammeEntity elementGammeEntity = toEntity(elementGammeValue);
		    	 elementGammeEntity.setGammeProduit(entity);
		    	 list.add(elementGammeEntity);
		    }
		     entity.setListElementGamme(list);
		}
	    
		entity.setBlSuppression(dto.getBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
		
		return entity;
	}
	
	public MachineEntity toEntity(MachineValue dto) {
		
		MachineEntity entity = new MachineEntity();
		
		entity.setId(dto.getId());
		entity.setDesignation(dto.getDesignation());
		
		entity.setBlSuppression(dto.getBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
		
		return entity;
	}
	
	public OperationEntity toEntity(OperationValue dto) {
		
		OperationEntity entity = new OperationEntity();
		
		entity.setId(dto.getId());
		entity.setCode(dto.getCode());
		entity.setDesignation(dto.getDesignation());
		entity.setTemps(dto.getTemps());
		entity.setPdh(dto.getPdh());
		entity.setObservations(dto.getObservations());
		entity.setMachineId(dto.getMachineId());
		entity.setSectionId(dto.getSectionId());
		
		entity.setBlSuppression(dto.getBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());

		return entity;
	}
	
	public SectionEntity toEntity(SectionValue dto) {
		
		SectionEntity entity = new SectionEntity();
		
		entity.setId(dto.getId());
		entity.setDesignation(dto.getDesignation());
		
		entity.setBlSuppression(dto.getBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());

		return entity;
	}

	public List<OperationValue> listOperationToValue(List<OperationEntity> listEntity) {
		
		List<OperationValue> list = new ArrayList<OperationValue>();
		
		for(OperationEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}

	public List<GammeProduitValue> listGammeProduitToValue(List<GammeProduitEntity> listEntity) {
		
		List<GammeProduitValue> list = new ArrayList<GammeProduitValue>();
		
		for(GammeProduitEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}

	public List<SectionValue> listSectionToValue(List<SectionEntity> listEntity) {
		
		List<SectionValue> list = new ArrayList<SectionValue>();
		
		for(SectionEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}

	public List<MachineValue> listMachineToValue(List<MachineEntity> listEntity) {
		
		List<MachineValue> list = new ArrayList<MachineValue>();
		
		for(MachineEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}

	public ResultatRechecheGammeProduitElementValue toResultatRechecheGammeProduitElementValue(GammeProduitEntity entity) {
		
		ResultatRechecheGammeProduitElementValue dto = new ResultatRechecheGammeProduitElementValue();
		
		dto.setId(entity.getId());
		dto.setTempsTotal(entity.getTempsTotal());
		dto.setNbOperation(entity.getNbOperation());
		dto.setObservations(entity.getObservations());
		dto.setDate(entity.getDate());
		dto.setProduitId(entity.getProduitId());
		
		return dto;
	}


}
