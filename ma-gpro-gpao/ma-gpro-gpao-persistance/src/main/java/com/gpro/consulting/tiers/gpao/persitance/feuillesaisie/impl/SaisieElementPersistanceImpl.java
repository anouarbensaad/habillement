package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IFeuilleSaisiePersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.ISaisieElementPersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.SaisieElementEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.utility.FeuilleSaisiePersistanceUtility;

/**
 * Implementation of {@link IFeuilleSaisiePersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 25/05/2016
 *
 */

@Component
public class SaisieElementPersistanceImpl extends AbstractPersistance implements ISaisieElementPersistance{
	
	private String ELEMENT_GAMME_ID = "elementGammeId";
	private String OF_PREDICATE = "ordreFabricationNumero";


	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private FeuilleSaisiePersistanceUtility feuilleSaisiePersistanceUtility;
	

	@Override
	public List<Long> getByElementGammeId(Long elementGammeId) {
		
		List<Long> result = new ArrayList<Long>();
		
		if(elementGammeId != null){
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<SaisieElementEntity> root = criteriaQuery.from(SaisieElementEntity.class);
			
			// Set elementGammeId on whereClause if not null
		    if (elementGammeId != null) {
		    	
		    	whereClause.add(criteriaBuilder.equal(root.get(ELEMENT_GAMME_ID), elementGammeId));
		    }
		    
			criteriaQuery.select(root.get("quantite").as(Long.class)).where(whereClause.toArray(new Predicate[] {}));
		    List <Long> resultatListQte = this.entityManager.createQuery(criteriaQuery).getResultList();

		    for (Long qte : resultatListQte) {
		    	
		    	result.add(qte);
		    }
		}
	    
		return result;
	}
	
	
	@Override
	public Long getSommeQteByGammeId(Long elementGammeId) {
		
		Long sommeQte = 0L;
		
		if(elementGammeId != null){
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<SaisieElementEntity> root = criteriaQuery.from(SaisieElementEntity.class);
			
			// Set elementGammeId on whereClause if not null
		    if (elementGammeId != null) {
		    	
		    	whereClause.add(criteriaBuilder.equal(root.get(ELEMENT_GAMME_ID), elementGammeId));
		    }
		    
			criteriaQuery.select(criteriaBuilder.sum(root.get("quantite").as(Long.class))).where(whereClause.toArray(new Predicate[] {}));
			sommeQte = this.entityManager.createQuery(criteriaQuery).getSingleResult();

			if(sommeQte == null){
				sommeQte = 0L;
			}
		}
	    
		return sommeQte;
		
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
    
	@Override
	public boolean existeOF(String numeroOF) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<SaisieElementEntity> criteriaQuery = criteriaBuilder.createQuery(SaisieElementEntity.class);
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    Root<SaisieElementEntity> root = criteriaQuery.from(SaisieElementEntity.class);
	    
	    Expression<String> path = root.get(OF_PREDICATE);
		Expression<String> upper =criteriaBuilder.upper(path);
		Predicate predicate = criteriaBuilder.like(upper,numeroOF.toUpperCase());
		
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    criteriaQuery.where(criteriaBuilder.and(predicate));
	    
	    List<SaisieElementEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
	    if(entityListResult.size()!=0){
	    	return true;
	    }
	    
		return false;
	}

	

}
