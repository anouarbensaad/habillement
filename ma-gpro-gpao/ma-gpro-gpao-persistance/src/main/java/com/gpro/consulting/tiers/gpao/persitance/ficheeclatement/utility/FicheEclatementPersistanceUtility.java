package com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.FicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.PaquetValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.ResultatRechecheFicheEclatementElementValue;
import com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.entity.FicheEclatementEntity;
import com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.entity.PaquetEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */

@Component
public class FicheEclatementPersistanceUtility {
	
	public FicheEclatementValue toValue(FicheEclatementEntity entity){
		
		FicheEclatementValue dto = new FicheEclatementValue();
		
		dto.setId(entity.getId());
		dto.setDateLancement(entity.getDateLancement());
		dto.setObservations(entity.getObservations());
		dto.setOrdreFabricationId(entity.getOrdreFabricationId());
		dto.setNombrePaquet(entity.getNombrePaquet());
		dto.setQuantiteEclate(entity.getQuantiteEclate());
		
	    if(entity.getListPaquet() != null){
	    	List<PaquetValue> list = new ArrayList<PaquetValue>();
		     for (PaquetEntity paquetEntity : entity.getListPaquet()) {
		    	 PaquetValue paquetValue = toValue(paquetEntity);
		    	 list.add(paquetValue);
		    }
		     Collections.sort(list);
		     dto.setListPaquet(new TreeSet<PaquetValue>(list));
		}
		
		dto.setBlSuppression(entity.getBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
			
		return dto;
	}
	
	public FicheEclatementEntity toEntity(FicheEclatementValue dto){
		
		FicheEclatementEntity entity = new FicheEclatementEntity();
		
		entity.setId(dto.getId());
		entity.setDateLancement(dto.getDateLancement());
		entity.setObservations(dto.getObservations());
		entity.setOrdreFabricationId(dto.getOrdreFabricationId());
		entity.setNombrePaquet(dto.getNombrePaquet());
		entity.setQuantiteEclate(dto.getQuantiteEclate());
		
	    if(dto.getListPaquet() != null){
		     Set<PaquetEntity> list = new HashSet <PaquetEntity>();
		     for (PaquetValue paquetValue : dto.getListPaquet()) {
		    	 PaquetEntity paquetEntity = toEntity(paquetValue);
		    	 paquetEntity.setFicheEclatement(entity);
		    	 list.add(paquetEntity);
		    }
		     entity.setListPaquet(list);
		}
		
	    entity.setBlSuppression(dto.getBlSuppression());
	    entity.setDateSuppression(dto.getDateSuppression());
	    entity.setDateCreation(dto.getDateCreation());
	    entity.setDateModification(dto.getDateModification());
	    entity.setVersion(dto.getVersion());
	    
		return entity;
	}
	
	
	public static PaquetValue toValue(PaquetEntity entity){
		
		PaquetValue dto = new PaquetValue();
		
		dto.setId(entity.getId());
		dto.setNum(entity.getNum());
		dto.setCouleurId(entity.getCouleurId());
		dto.setTailleId(entity.getTailleId());
		dto.setQuantite(entity.getQuantite());
		dto.setOrdre(entity.getOrdre());
		dto.setNumMatelas(entity.getNumMatelas());
		
		if(entity.getFicheEclatement() != null){
			dto.setFicheEclatementId(entity.getFicheEclatement().getId());
		}
		
		dto.setBlSuppression(entity.getBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		
		return dto;
	}
	
	
	public PaquetEntity toEntity(PaquetValue dto){
		
		PaquetEntity entity = new PaquetEntity();
		
		entity.setId(dto.getId());
		entity.setNum(dto.getNum());
		entity.setCouleurId(dto.getCouleurId());
		entity.setTailleId(dto.getTailleId());
		entity.setQuantite(dto.getQuantite());
		entity.setOrdre(dto.getOrdre());
		entity.setNumMatelas(dto.getNumMatelas());
		
		if(dto.getFicheEclatementId() != null){
			FicheEclatementEntity ficheEclatementEntity = new FicheEclatementEntity();
			ficheEclatementEntity.setId(dto.getFicheEclatementId());
			entity.setFicheEclatement(ficheEclatementEntity);
		}
		
		entity.setBlSuppression(dto.getBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
		
		return entity;
	}
	
	public List<FicheEclatementValue> listFicheEclatementToValue(List<FicheEclatementEntity> listEntity) {
		
		List<FicheEclatementValue> list = new ArrayList<FicheEclatementValue>();
		
		for(FicheEclatementEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}

	public ResultatRechecheFicheEclatementElementValue toResultatRechecheFicheEclatementElementValue(
			FicheEclatementEntity entity) {
		
		ResultatRechecheFicheEclatementElementValue result = new ResultatRechecheFicheEclatementElementValue();
		
		result.setId(entity.getId());
		result.setNombrePaquet(entity.getNombrePaquet());
		result.setQuantiteEclate(entity.getQuantiteEclate());
		result.setOrdreFabricationId(entity.getOrdreFabricationId());
		
		return result;
	}
	
}
