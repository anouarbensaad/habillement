package com.gpro.consulting.tiers.gpao.persitance.gammemontage.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ElementGammeValue;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.ElementGammePersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.entity.ElementGammeEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.utility.GammeMontagePersistanceUtilities;

/**
 * 
 * @author Zeineb Medimagh
 *
 */
@Component
public class ElementGammePersistanceImpl implements ElementGammePersistance {

	@PersistenceContext
	private EntityManager entityManager;
	
	private String PREDICATE_GAMME_PRODUIT = "gammeProduit";
	
	@Autowired
	private GammeMontagePersistanceUtilities gammeMontagePersistanceUtilities;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}


	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public List<ElementGammeValue> getAllByGammeProdId(Long gammeProduitId) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ElementGammeEntity> criteriaQuery = criteriaBuilder.createQuery(ElementGammeEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ElementGammeEntity> root = criteriaQuery.from(ElementGammeEntity.class);
		
		// Set request.tempsTotalMin on whereClause if not null
	    if (gammeProduitId != null) {
	    	whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_GAMME_PRODUIT).get("id"), gammeProduitId));
	    }
	    
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List<ElementGammeEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
	    List<ElementGammeValue> list = new ArrayList<ElementGammeValue>();
	    for (ElementGammeEntity elementGammeEntity : resultatEntite) {
			ElementGammeValue element = gammeMontagePersistanceUtilities.toValue(elementGammeEntity); 
			list.add(element);
		}
		return list;
	}

}
