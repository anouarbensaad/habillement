package com.gpro.consulting.tiers.gpao.persitance.gammeof.impl;

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
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ElementGammeOfValue;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.entity.OperationEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.IElementGammeOfPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.entity.ElementGammeOfEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.utility.GammeOfPersistanceUtilities;

/**
 * Implementation of {@link IElementGammeOfPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 30/07/2016
 *
 */

@Component
public class ElementGammeOfPersistanceImpl  extends AbstractPersistance implements IElementGammeOfPersistance {
	
	private static final Logger logger = LoggerFactory.getLogger(ElementGammeOfPersistanceImpl.class);
	
	private String OPERATION_ID = "operationId";
	private String GAMME_OF = "gammeOf";
	private String ID = "id";
	private String CODE = "code";
	private String OF_ID="ordreFabricationId";

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private GammeOfPersistanceUtilities gammeOfPersistanceUtilities;
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	@Override
	public List<ElementGammeOfValue> getListFromCriteria(Long ofId, String operationCode) {

		CriteriaBuilder criteriaBuilderOperation = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<OperationEntity> criteriaQueryOperation = criteriaBuilderOperation.createQuery(OperationEntity.class);
		List<Predicate> whereClauseOperation = new ArrayList<Predicate>();
		Root<OperationEntity> rootOperation = criteriaQueryOperation.from(OperationEntity.class);
		
		whereClauseOperation.add(criteriaBuilderOperation.equal(rootOperation.get(CODE), operationCode));
		
		criteriaQueryOperation.select(rootOperation).where(whereClauseOperation.toArray(new Predicate[] {}));
	
		List<OperationEntity> entityListResultOperation = this.entityManager.createQuery(criteriaQueryOperation).getResultList();
	
		List<Long> operations = new ArrayList<Long>();
		
		for (OperationEntity entity : entityListResultOperation) {
			
			operations.add(entity.getId());
		}
		
		
		List<ElementGammeOfValue> result = new ArrayList<ElementGammeOfValue>();
		
		if(operations.size() > 0){
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<ElementGammeOfEntity> criteriaQuery = criteriaBuilder.createQuery(ElementGammeOfEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<ElementGammeOfEntity> root = criteriaQuery.from(ElementGammeOfEntity.class);
		
			whereClause.add(criteriaBuilder.equal(root.get(GAMME_OF).get(ID), ofId));
			
			whereClause.add(root.get(OPERATION_ID).in(operations));
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		
			List<ElementGammeOfEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();
		
			for (ElementGammeOfEntity entity : entityListResult) {
				ElementGammeOfValue value = gammeOfPersistanceUtilities.toValue(entity);
				result.add(value);
			}
		}
	
		return result;

	}
	
	@Override
	public List<Long> getByOfIdAndOperationId(Long ofId, Long operationId) {
		
		List<Long> result = new ArrayList<Long>();
		
		if(ofId != null && operationId != null){
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<ElementGammeOfEntity> root = criteriaQuery.from(ElementGammeOfEntity.class);
		
			if(ofId != null){
				whereClause.add(criteriaBuilder.equal(root.get(GAMME_OF).get(OF_ID), ofId));
			}
			
			if(operationId != null){
				whereClause.add(criteriaBuilder.equal(root.get(OPERATION_ID), operationId));
			}
			
			criteriaQuery.select(root.get("id").as(Long.class)).where(whereClause.toArray(new Predicate[] {}));
		
			List<Long> listIds = this.entityManager.createQuery(criteriaQuery).getResultList();
		
			for (Long idResult : listIds) {
				result.add(idResult);
			}
		}
		
		return result;
	}
	
	@Override
	public ElementGammeOfValue getByElementOFId(Long elementOFId) {
		
		ElementGammeOfEntity entity = this.rechercherParId(elementOFId, ElementGammeOfEntity.class);
		
	    return gammeOfPersistanceUtilities.toValue(entity);
	}

	@Override
	public List<ElementGammeOfValue> getListByGammeOFId(Long gammeOFId) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ElementGammeOfEntity> criteriaQuery = criteriaBuilder.createQuery(ElementGammeOfEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ElementGammeOfEntity> root = criteriaQuery.from(ElementGammeOfEntity.class);
		
		if(gammeOFId != null){
			whereClause.add(criteriaBuilder.equal(root.get(GAMME_OF).get("id"), gammeOFId));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	
		List<ElementGammeOfEntity> listResult = this.entityManager.createQuery(criteriaQuery).getResultList();
		List<ElementGammeOfValue> list = new ArrayList<ElementGammeOfValue>();
		
		for (ElementGammeOfEntity entity : listResult) {
			ElementGammeOfValue element = gammeOfPersistanceUtilities.toValue(entity);
			list.add(element);
		}
		
		return list;
	}


	@Override
	public List<ElementGammeOfValue> getListByGammeOperationId(Long operationId) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ElementGammeOfEntity> criteriaQuery = criteriaBuilder.createQuery(ElementGammeOfEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ElementGammeOfEntity> root = criteriaQuery.from(ElementGammeOfEntity.class);
		
		if(operationId != null){
			whereClause.add(criteriaBuilder.equal(root.get(OPERATION_ID), operationId));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	
		List<ElementGammeOfEntity> listResult = this.entityManager.createQuery(criteriaQuery).getResultList();
		List<ElementGammeOfValue> list = new ArrayList<ElementGammeOfValue>();
		
		for (ElementGammeOfEntity entity : listResult) {
			ElementGammeOfValue element = gammeOfPersistanceUtilities.toValue(entity);
			list.add(element);
		}
		
		return list;
		
	}


}
