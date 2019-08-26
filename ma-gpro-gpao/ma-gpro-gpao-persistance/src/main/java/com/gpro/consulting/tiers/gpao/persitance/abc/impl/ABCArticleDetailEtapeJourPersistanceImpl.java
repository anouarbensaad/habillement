package com.gpro.consulting.tiers.gpao.persitance.abc.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeSet;

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
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.RechercheMulticritereABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ResultatRechecheABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.persitance.abc.IABCArticleDetailEtapeJourPersistance;
import com.gpro.consulting.tiers.gpao.persitance.abc.entity.ABCArticleDetailEtapeJourEntity;
import com.gpro.consulting.tiers.gpao.persitance.abc.utility.ABCArticleDetailEtapeJourUtility;

/**
 * Implementation of {@link IABCArticleDetailEtapeJourPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 03/05/2016
 *
 */

@Component
public class ABCArticleDetailEtapeJourPersistanceImpl extends AbstractPersistance implements IABCArticleDetailEtapeJourPersistance{
	
	private String PREDICATE_DATE = "dateSaisie";
	private String PREDICATE_PHASEID="phaseId";
	private String PREDICATE_REFCOMMANDE="refCommande";

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ABCArticleDetailEtapeJourUtility aBCArticleDetailEtapeJourUtility;
	
	@Override
	public ResultatRechecheABCArticleDetailEtapeJourValue rechercherMultiCritere(
			RechercheMulticritereABCArticleDetailEtapeJourValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ABCArticleDetailEtapeJourEntity> criteriaQuery = criteriaBuilder.createQuery(ABCArticleDetailEtapeJourEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ABCArticleDetailEtapeJourEntity> root = criteriaQuery.from(ABCArticleDetailEtapeJourEntity.class);
		
		if(request.getDateSaisieFrom() != null){
	    	Expression<Calendar> dateEntity =root.get(PREDICATE_DATE);
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(dateEntity, request.getDateSaisieFrom()));
		}
		
		if(request.getDateSaisieTo() != null){
	    	Expression<Calendar> dateEntity =root.get(PREDICATE_DATE);
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(dateEntity, request.getDateSaisieTo()));
		}
		
		if(request.getPhaseId() != null){
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PHASEID), request.getPhaseId()));
		}

		if(request.getRefCommande() != null){
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_REFCOMMANDE), request.getRefCommande()));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <ABCArticleDetailEtapeJourEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<ABCArticleDetailEtapeJourValue> list = new ArrayList<ABCArticleDetailEtapeJourValue>();
	    
	    for (ABCArticleDetailEtapeJourEntity entity : resultatEntite) {
	    	ABCArticleDetailEtapeJourValue dto = aBCArticleDetailEtapeJourUtility.toValue(entity);
	    	list.add(dto);
	    }
	    
	    ResultatRechecheABCArticleDetailEtapeJourValue result = new ResultatRechecheABCArticleDetailEtapeJourValue();
	    result.setNombreResultaRechercher(Long.valueOf(list.size()));
	    result.setList(new TreeSet<>(list));
	    
		return result;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
