package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.impl;

import java.util.ArrayList;
import java.util.List;

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
import com.gpro.consulting.tiers.gpao.coordination.aleas.ElementAleasValue;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IElementAleasPersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.ElementAleasEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.utility.AleasPersistanceUtility;

/**
 * @author Hajer AMRI
 * @since 22/02/2017
 */

@Component
public class ElementAleasPersistanceImpl extends AbstractPersistance implements IElementAleasPersistance {
	private static final Logger logger = LoggerFactory.getLogger(ElementAleasPersistanceImpl.class);

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
	public String create(ElementAleasValue elementAleas) {
		ElementAleasEntity entity = (ElementAleasEntity) this.creer(aleasPersistanceUtility.toEntity(elementAleas));
		//LOGGER.info("createElementAleas:Persistance" + elementAleas);

		return entity.getId().toString();
	}

	@Override
	public ElementAleasValue getById(Long id) {
		ElementAleasEntity entity = this.rechercherParId(id, ElementAleasEntity.class);

		return aleasPersistanceUtility.toValue(entity);
	}

	@Override
	public String update(ElementAleasValue elementAleas) {
		ElementAleasEntity entity = (ElementAleasEntity) this.modifier(aleasPersistanceUtility.toEntity(elementAleas));

		return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		this.supprimerEntite(ElementAleasEntity.class, id.longValue());
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

	@Override
	public List<ElementAleasValue> rechercheParFeuilleEtId(Long aleaId, Long FeuilleId) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ElementAleasEntity> criteriaQuery = criteriaBuilder.createQuery(ElementAleasEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ElementAleasEntity> root = criteriaQuery.from(ElementAleasEntity.class);

		if (FeuilleId != null) {
			whereClause.add(criteriaBuilder.equal( root.get("feuilleSaisie").get("id"),FeuilleId));
		}
		
		if (aleaId != null) {
			whereClause.add(criteriaBuilder.equal( root.get("aleasId"),aleaId));
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

		List<ElementAleasEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		List<ElementAleasValue> list = new ArrayList<ElementAleasValue>();

		for (ElementAleasEntity entity : resultatEntite) {

			ElementAleasValue dto = aleasPersistanceUtility.toValue(entity);
			list.add(dto);
		}


		return list;
	}
}
