package com.gpro.consulting.tiers.gpao.persitance.stockfini.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;

import com.gpro.consulting.tiers.gpao.coordination.value.DetailOfValue;

import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereDetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereDetailOfValue;

import com.gpro.consulting.tiers.gpao.persitance.entite.DetailOfEntite;
import com.gpro.consulting.tiers.gpao.persitance.entite.OrdreFabricationEntite;

import com.gpro.consulting.tiers.gpao.persitance.stockfini.IDetailOfPersistance;
import com.gpro.consulting.tiers.gpao.persitance.utilities.PersistanceUtilities;


/**
 * @author Samer Hassen
 *
 */
@Component
public class DetailOfPersistanceImpl extends AbstractPersistance implements IDetailOfPersistance {

	@PersistenceContext
	private EntityManager entityManager;
	
	private String PREDICATE_ORDRE="ordre" ;
	
	private String PREDICATE_PARTIE_INTERESSE="partieInterresId" ;
	private String PREDICATE_NUMERO_OF="numero" ;
	
	
	private String PREDICATE_QTE_STOCK="qteStock" ;
	

	

	@Override
	public ResultatMulticritereDetailOfValue rechercherDetailOfMultiCritere(
			RechercheMulticritereDetailOfValue request) {
	
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<DetailOfEntite> criteriaQuery = criteriaBuilder.createQuery(DetailOfEntite.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<DetailOfEntite> root = criteriaQuery.from(DetailOfEntite.class);

		Boolean requestVide=true ;
		
		
		if (request.getId() != null) {
			requestVide=false;
			
			whereClause.add(
					criteriaBuilder.equal(root.get("id"), request.getId()));
	
		}
		
		if (request.getOrdre() != null) {
			requestVide=false;
			Join<DetailOfEntite, OrdreFabricationEntite> jointure_DetailOf_OF = root.join(PREDICATE_ORDRE);
			whereClause.add(
					criteriaBuilder.equal(jointure_DetailOf_OF.get("id"), request.getOrdre()));
	
		}
		
		if (estNonVide(request.getNumOF())) {
			requestVide=false;
			Join<DetailOfEntite, OrdreFabricationEntite> jointure_DetailOf_OF = root.join(PREDICATE_ORDRE);
			whereClause.add(
					criteriaBuilder.equal(jointure_DetailOf_OF.get(PREDICATE_NUMERO_OF), request.getNumOF()));
	
		}
		
		
		
		if (request.getClientId() != null) {
			requestVide=false;
			Join<DetailOfEntite, OrdreFabricationEntite> jointure_DetailOf_OF = root.join(PREDICATE_ORDRE);
			whereClause.add(
					criteriaBuilder.equal(jointure_DetailOf_OF.get(PREDICATE_PARTIE_INTERESSE), request.getClientId()));
	
		}
		
		
		//TODO
		
		//qte Stock > 0>
		
		if (estNonVide(request.getEtatStock()))
		{
			if(request.getEtatStock().equals(">"))
				whereClause.add(criteriaBuilder.gt(root.<Long>get(PREDICATE_QTE_STOCK), 0));
			
			else
				if(request.getEtatStock().equals("="))
					whereClause.add(criteriaBuilder.equal(root.<Long>get(PREDICATE_QTE_STOCK), 0));
		}
		
		
		

	   
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
		
		
		List<DetailOfEntite> resultatEntite = new ArrayList<DetailOfEntite>(); 
		
		if (requestVide==true) {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(39).getResultList();

		} else {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		}

		List<DetailOfValue > list = new ArrayList<DetailOfValue >();

		for (DetailOfEntite entity : resultatEntite) {
			DetailOfValue  dto =PersistanceUtilities.toValue(entity);

			list.add(dto);
	    }

		ResultatMulticritereDetailOfValue result = new ResultatMulticritereDetailOfValue();
		result.setNombreResultaRechercher(Long.valueOf(list.size()));
		result.setDetailOfValues(((new HashSet<DetailOfValue>(list))));

		return result;
		
	}
	



	@Override
	public String modifierDetailOf(DetailOfValue pDetailOfValue) {
		DetailOfEntite entity = (DetailOfEntite) this.modifier(PersistanceUtilities.toEntite(pDetailOfValue));
		
		return entity.getId().toString();
		
	}


	@Override
	public DetailOfValue rechercheDetailOfParId(Long pDetailOfId) {
		DetailOfEntite vDetailOfEntite = this.rechercherParId(pDetailOfId,DetailOfEntite.class);
		DetailOfValue  vDetailOfValueResult = PersistanceUtilities.toValue(vDetailOfEntite);
		
		return vDetailOfValueResult;
	}



	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val) && !val.equals("undefined") && !val.equals("null");

	}
	
	

	
	/***************************** Getter & Setter ********************************/
	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}



}
	


