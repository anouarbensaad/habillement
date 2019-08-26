package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.utility;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.aleas.AleasValue;
import com.gpro.consulting.tiers.gpao.coordination.aleas.ElementAleasValue;
import com.gpro.consulting.tiers.gpao.coordination.aleas.FamilleAleasValue;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.AleasEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.ElementAleasEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.FamilleAleasEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.FeuilleSaisieEntity;

import ch.qos.logback.classic.Logger;

/**
 * @author Hajer AMRI
 * @since 22/02/2017
 */

@Component
public class AleasPersistanceUtility {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(AleasPersistanceUtility.class);

	/*---------------------------------------ElementAleas---------------------------------------*/

	public ElementAleasValue toValue(ElementAleasEntity entity) {
		//LOGGER.info("-------ElementAleasValue toValue method ---------------");
		ElementAleasValue dto = new ElementAleasValue();
//		if (entity.getId() != null) {
			dto.setId(entity.getId());

//		}
			
			//LOGGER.info("-------ElementAleasValue toValue before: dto---------------" + dto);

		dto.setId(entity.getId());
		dto.setDuree(entity.getDuree());
		dto.setAleasId(entity.getAleasId());
		dto.setObservations(entity.getObservations());
		dto.setBlSuppression(entity.isBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());

		 if (entity.getFeuilleSaisie() != null) {
		 dto.setFeuilleId(entity.getFeuilleSaisie().getId());
		 }

		//LOGGER.info("-------toValue---ElementAleasValue: after dto---------------" + dto);

		return dto;
	}

	public ElementAleasEntity toEntity(ElementAleasValue dto) {

		ElementAleasEntity entity = new ElementAleasEntity();
//		if (dto.getId() != null) {

			entity.setId(dto.getId());

//		}
		entity.setDuree(dto.getDuree());
		entity.setAleasId(dto.getAleasId());
		entity.setObservations(dto.getObservations());
		entity.setBlSuppression(dto.isBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		
		if (dto.getFeuilleId() != null) {
			FeuilleSaisieEntity feuilleSaisieEntity = new FeuilleSaisieEntity();
			feuilleSaisieEntity.setId(dto.getFeuilleId());
			entity.setFeuilleSaisie(feuilleSaisieEntity);
		}
		
		
		return entity;
	}

	/*---------------------------------------Aleas---------------------------------------*/

	public AleasValue toValue(AleasEntity entity) {

		AleasValue dto = new AleasValue();

		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());
		dto.setFamilleId(entity.getFamilleId());
		dto.setBlSuppression(entity.isBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());

		return dto;
	}

	public AleasEntity toEntity(AleasValue dto) {

		AleasEntity entity = new AleasEntity();

		entity.setId(dto.getId());
		entity.setDesignation(dto.getDesignation());
		entity.setFamilleId(dto.getFamilleId());
		entity.setBlSuppression(dto.isBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());

		return entity;
	}

	/*---------------------------------------FamilleAleas---------------------------------------*/

	public FamilleAleasValue toValue(FamilleAleasEntity entity) {

		FamilleAleasValue dto = new FamilleAleasValue();

		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());
		dto.setBlSuppression(entity.isBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());

		return dto;
	}

	public FamilleAleasEntity toEntity(FamilleAleasValue dto) {

		FamilleAleasEntity entity = new FamilleAleasEntity();

		entity.setId(dto.getId());
		entity.setDesignation(dto.getDesignation());
		entity.setBlSuppression(dto.isBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());

		return entity;
	}

}
