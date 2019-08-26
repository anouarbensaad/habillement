package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gpao.coordination.aleas.AleasValue;
import com.gpro.consulting.tiers.gpao.coordination.aleas.ElementAleasValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechecheFeuilleSaisieElementValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechecheFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IAleasPersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.AleasEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.ElementAleasEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.FeuilleSaisieEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.PersonnelEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.utility.AleasPersistanceUtility;

/**
 * @author Hajer AMRI
 * @since 22/02/2017
 */

@Component
public class AleasPersistanceImpl extends AbstractPersistance implements IAleasPersistance {
	private static final Logger logger = LoggerFactory.getLogger(AleasPersistanceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	// private String COLUMN_CHAINE = "chaineId";
	// private String COLUMN_MATRICULE = "matricule";
	// private String ENTITY_PERSONNEL = "personnel";
	// private String PREDICATE_DATE_SAISIE = "dateSaisie";
	// private String PREDICATE_RENDEMENT = "rendement";
	// private String PREDICATE_ACTIVITE = "activite";

	@Autowired
	private AleasPersistanceUtility aleasPersistanceUtility;

	@Override
	public String create(AleasValue aleas) {
		AleasEntity entity = (AleasEntity) this.creer(aleasPersistanceUtility.toEntity(aleas));
		//LOGGER.info("createAleas:Persistance" + aleas);

		return entity.getId().toString();
	}

	@Override
	public AleasValue getById(Long id) {
		AleasEntity entity = this.rechercherParId(id, AleasEntity.class);

		return aleasPersistanceUtility.toValue(entity);
	}

	@Override
	public String update(AleasValue aleas) {
		AleasEntity entity = (AleasEntity) this.modifier(aleasPersistanceUtility.toEntity(aleas));

		return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		this.supprimerEntite(AleasEntity.class, id.longValue());
	}
	
	
	@Override
	public List<AleasValue> getAllAleas() {
		List<AleasEntity> listEntity = this.lister(AleasEntity.class);
		
		List<AleasValue> list = new ArrayList<AleasValue>();

		for (AleasEntity entity : listEntity) {
			list.add(aleasPersistanceUtility.toValue(entity));
		}
		
		return list;
	}


	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private boolean estNonVide(String val) {

		return val != null && !"".equals(val);
	}

	

}
