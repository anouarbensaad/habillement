package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.aleas.ElementAleasValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.DetailSaisieElementValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.FeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelElementValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechecheFeuilleSaisieElementValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.SaisieElementValue;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.ElementAleasEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.FeuilleSaisieEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.PersonnelEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.SaisieElementEntity;

import ch.qos.logback.classic.Logger;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 25/05/2016
 *
 */

@Component
public class FeuilleSaisiePersistanceUtility {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(FeuilleSaisiePersistanceUtility.class);

	@Autowired
	private AleasPersistanceUtility aleasPersistanceUtility;
	
	public FeuilleSaisieValue toValue(FeuilleSaisieEntity entity) {

		FeuilleSaisieValue dto = new FeuilleSaisieValue();

		dto.setId(entity.getId());
		dto.setActivite(entity.getActivite());
		dto.setChaineId(entity.getChaineId());
		dto.setDateSaisie(entity.getDateSaisie());
		dto.setMinAleas(entity.getMinAleas());
		dto.setMinPresence(entity.getMinPresence());
		dto.setMinProd(entity.getMinProd());
		dto.setPscProd(entity.getPscProd());
		dto.setRendement(entity.getRendement());
		dto.setObservations(entity.getObservations());
		dto.setDirecte(entity.isDirecte());
        dto.setPeriode(entity.getPeriode());
		if (entity.getPersonnel() != null) {

			dto.setPersonnelId(entity.getPersonnel().getId());
			dto.setPersonnelMatricule(entity.getPersonnel().getMatricule());
		}

		if (entity.getListSaisie() != null) {
			List<SaisieElementValue> list = new ArrayList<SaisieElementValue>();
			for (SaisieElementEntity saisieEntity : entity.getListSaisie()) {
				SaisieElementValue saisieValue = toValue(saisieEntity);
				list.add(saisieValue);
			}
			Collections.sort(list);
			dto.setListSaisie(new TreeSet<SaisieElementValue>(list));
		}
		
		//ElementAleas
		if (entity.getListElementAleas() != null) {
			List<ElementAleasValue> list = new ArrayList<ElementAleasValue>();
			for (ElementAleasEntity elementAleasEntity : entity.getListElementAleas()) {
				
				//LOGGER.info("-------toValue-----entity.getListElementAleas()---------------"+entity.getListElementAleas());
				//LOGGER.info("-------toValue-----entity.getListElementAleas() : elementAleasEntity---------------"+elementAleasEntity);


				ElementAleasValue elementAleasValue = aleasPersistanceUtility.toValue(elementAleasEntity);
				
				//LOGGER.info("-------toValue---.getListElementAleas(): elementAleasValue---------------"+elementAleasValue);

				list.add(elementAleasValue);
				
			}
			//LOGGER.info("-------toValue-----entity.getListElementAleas(): list---------------"+list);

			Collections.sort(list);
			dto.setListElementAleas(new TreeSet<ElementAleasValue>(list));
		}

		return dto;
	}

	public FeuilleSaisieEntity toEntity(FeuilleSaisieValue dto) {

		FeuilleSaisieEntity entity = new FeuilleSaisieEntity();

		entity.setId(dto.getId());
		entity.setActivite(dto.getActivite());
		entity.setChaineId(dto.getChaineId());
		entity.setDateSaisie(dto.getDateSaisie());
		entity.setMinAleas(dto.getMinAleas());
		entity.setMinPresence(dto.getMinPresence());
		entity.setMinProd(dto.getMinProd());
		entity.setPscProd(dto.getPscProd());
		entity.setRendement(dto.getRendement());
		entity.setObservations(dto.getObservations());
		entity.setDirecte(dto.isDirecte());
        entity.setPeriode(dto.getPeriode());
		if (dto.getPersonnelId() != null) {

			PersonnelEntity personnelEntity = new PersonnelEntity();
			personnelEntity.setId(dto.getPersonnelId());

			entity.setPersonnel(personnelEntity);
		}

		if (dto.getListSaisie() != null) {

			Set<SaisieElementEntity> list = new HashSet<SaisieElementEntity>();
			//LOGGER.info("-------toEntity-----dto.getListSaisie()---------------"+dto.getListSaisie());

			for (SaisieElementValue value : dto.getListSaisie()) {
				SaisieElementEntity saisieEntity = toEntity(value);
				saisieEntity.setFiheSaisie(entity);
				list.add(saisieEntity);
			}
			//TEST UNICITE BARECODE 
			//LOGGER.info("-------toEntity-----ListSaisie---------------"+list);

			entity.setListSaisie(list);
		}
		
		

		//ElementAleas 
		if (dto.getListElementAleas() != null) {

			Set<ElementAleasEntity> listElt = new HashSet<ElementAleasEntity>();
			
			//LOGGER.info("-------toEntity-----dto.getListElementAleas()---------------"+dto.getListElementAleas());
			for (ElementAleasValue value : dto.getListElementAleas()) {
				ElementAleasEntity elementAleasEntity = aleasPersistanceUtility.toEntity(value);
				elementAleasEntity.setFeuilleSaisie(entity);
				listElt.add(elementAleasEntity);
			}
			
			//LOGGER.info("-------toEntity-----entity.getListElementAleas()---------------"+listElt);

			entity.setListElementAleas(listElt);
		}


		return entity;
	}

	public SaisieElementValue toValue(SaisieElementEntity entity) {

		SaisieElementValue dto = new SaisieElementValue();

		dto.setId(entity.getId());
		dto.setCodeBarre(entity.getCodeBarre());
		dto.setElementGammeId(entity.getElementGammeId());
		dto.setQuantite(entity.getQuantite());
        dto.setOrdre(entity.getOrdre());
		dto.setOrdreFabricationNumero(entity.getOrdreFabricationNumero());
		dto.setIdClient(entity.getIdClient());
		dto.setIdProduit(entity.getIdProduit());
		dto.setIdOperation(entity.getIdOperation());
		dto.setComptage(entity.getComptage());
		
		dto.setIdOF(entity.getIdOF());
		
		if (entity.getFiheSaisie() != null) {
			dto.setFeuilleId(entity.getFiheSaisie().getId());
		}

		return dto;
	}

	public SaisieElementEntity toEntity(SaisieElementValue dto) {

		SaisieElementEntity entity = new SaisieElementEntity();

		entity.setId(dto.getId());
		entity.setCodeBarre(dto.getCodeBarre());
		entity.setElementGammeId(dto.getElementGammeId());
		entity.setPaquetId(dto.getPaquetId());
		entity.setQuantite(dto.getQuantite());
        entity.setOrdreFabricationNumero(dto.getOrdreFabricationNumero());
        entity.setOrdre(dto.getOrdre());
        
        entity.setIdClient(dto.getIdClient());
        entity.setIdProduit(dto.getIdProduit());
        entity.setIdOperation(dto.getIdOperation());
        entity.setComptage(dto.getComptage());
		
        entity.setIdOF(dto.getIdOF());
        
        
        return entity;
	}

	public List<FeuilleSaisieValue> listFicheSaisieToValue(List<FeuilleSaisieEntity> listEntity) {

		List<FeuilleSaisieValue> list = new ArrayList<FeuilleSaisieValue>();

		for (FeuilleSaisieEntity entity : listEntity) {
			list.add(toValue(entity));
		}

		return list;
	}

	public ResultatRechecheFeuilleSaisieElementValue toResultatRechecheFicheSaisieElementValue(
			FeuilleSaisieEntity entity) {

		ResultatRechecheFeuilleSaisieElementValue result = new ResultatRechecheFeuilleSaisieElementValue();

		result.setId(entity.getId());
		result.setActivite(entity.getActivite());
		result.setRendement(entity.getRendement());
		result.setMinPresence(entity.getMinPresence());
		result.setMinProd(entity.getMinProd());
		result.setDateSaisie(entity.getDateSaisie());
		result.setMinAleas(entity.getMinAleas());
		result.setPscProd(entity.getPscProd());
		result.setChaineId(entity.getChaineId());
        result.setPeriode(entity.getPeriode());
		if (entity.getPersonnel() != null) {

			result.setPersonnelMatricule(entity.getPersonnel().getMatricule());
			result.setNomPrenom(entity.getPersonnel().getPrenom() + " "+ entity.getPersonnel().getNom());
		}

		return result;
	}

	public PersonnelValue personnelToValue(PersonnelEntity entity) {
		PersonnelValue dto = new PersonnelValue();

		dto.setId(entity.getId());
		dto.setMatricule(entity.getMatricule());
		dto.setNom(entity.getNom());
		dto.setPrenom(entity.getPrenom());
		dto.setIndirect(entity.isIndirect());
		return dto;
	}

	public PersonnelEntity ToPersonnelEntity(PersonnelValue value) {

		PersonnelEntity entity = new PersonnelEntity();

		entity.setId(value.getId());
		entity.setMatricule(value.getMatricule());
		entity.setNom(value.getNom());
		entity.setPrenom(value.getPrenom());
		entity.setIndirect(value.isIndirect());

		return entity;
	}

	public List<PersonnelValue> toValue(List<PersonnelEntity> listEntity) {

		List<PersonnelValue> list = new ArrayList<PersonnelValue>();

		for (PersonnelEntity entity : listEntity) {
			list.add(personnelToValue(entity));
		}

		return list;
	}

	public PersonnelElementValue toResultatRechechePeronnelElementValue(PersonnelEntity entity) {

		PersonnelElementValue result = new PersonnelElementValue();
		result.setId(entity.getId());
		result.setMatricule(entity.getMatricule());
		result.setNom(entity.getNom());
		result.setPrenom(entity.getPrenom());

		return result;
	}

	public DetailSaisieElementValue toDetailSaisieElementValue(SaisieElementEntity entity) {

		DetailSaisieElementValue result = new DetailSaisieElementValue();

		result.setId(entity.getId());
		result.setElementGammeOFId(entity.getElementGammeId());
		if (entity.getFiheSaisie() != null) {
			result.setFicheSaisieId(entity.getFiheSaisie().getId());
			result.setDateSaisie(entity.getFiheSaisie().getDateSaisie());
			result.setDate(entity.getFiheSaisie().getDateSaisie());
			if(entity.getFiheSaisie().getPersonnel()!=null)
			result.setMatricule(entity.getFiheSaisie().getPersonnel().getMatricule());
			if(entity.getFiheSaisie().getChaineId()!=null)
			result.setIdChaine(entity.getFiheSaisie().getChaineId());
			
			
		}
        result.setIdOPeration(entity.getIdOperation());
		result.setCodeBarre(entity.getCodeBarre());
		result.setPaquetId(entity.getOrdre());
		result.setQuantite(entity.getQuantite());
		result.setOrdreFabricationNumero(entity.getOrdreFabricationNumero());
	    result.setElementGammeOFId(entity.getElementGammeId());
		return result;
	}
	
	/*---------------------------------------ElementAleas---------------------------------------*/

	public ElementAleasValue toValue(ElementAleasEntity entity) {
		ElementAleasValue dto = new ElementAleasValue();
//		if (entity.getId() != null) {
			dto.setId(entity.getId());

//		}
			
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

}
