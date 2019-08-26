package com.gpro.consulting.tiers.gpao.persitance.stockfini.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.MouvementFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.RechercheMulticritereMouvementFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.ResultatMulticritereMouvementFiniValue;


import com.gpro.consulting.tiers.gpao.persitance.stockfini.IMouvementFiniPersistance;
import com.gpro.consulting.tiers.gpao.persitance.stockfini.entity.MouvementFiniEntity;
import com.gpro.consulting.tiers.gpao.persitance.stockfini.utilities.StockFiniPersistanceUtilities;


/**
 * @author Samer Hassen
 *
 */
@Component
public class MouvementFiniPersistanceImpl extends AbstractPersistance implements IMouvementFiniPersistance {

	@PersistenceContext
	private EntityManager entityManager;

	private String PREDICATE_NUMERO_BON = "numeroBon";
	
	private String PREDICATE_NUMERO_OF = "numeroOf";
	
	private String PREDICATE_DETAIL_OF = "detailOfId";
	
	private String PREDICATE_DATE = "date";
	
	private String PREDICATE_TYPE = "type";


	@Override
	public String createMouvementFini(MouvementFiniValue pMouvementFiniValue) {
		MouvementFiniEntity vMouvementFiniEntite = (MouvementFiniEntity) this
				.modifier(StockFiniPersistanceUtilities.toEntity(pMouvementFiniValue));

		return vMouvementFiniEntite.getId().toString();
	}

	@Override
	public void deleteMouvementFini(Long id) {
		this.supprimerEntite(MouvementFiniEntity.class, id);

	}

	@Override
	public MouvementFiniValue findMouvementFiniParId(Long pMouvementFiniId) {

		MouvementFiniEntity vMouvementFiniEntite = this.rechercherParId(pMouvementFiniId, MouvementFiniEntity.class);
		MouvementFiniValue vMouvementFiniValueResult = StockFiniPersistanceUtilities.toValue(vMouvementFiniEntite);

		return vMouvementFiniValueResult;

	}

	@Override
	public ResultatMulticritereMouvementFiniValue rechercherMouvementFiniMultiCritere(
			RechercheMulticritereMouvementFiniValue request) {

		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<MouvementFiniEntity> criteriaQuery = criteriaBuilder.createQuery(MouvementFiniEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<MouvementFiniEntity> root = criteriaQuery.from(MouvementFiniEntity.class);

		Boolean requestVide = true;

		if (estNonVide(request.getNumeroBon())) {
			requestVide = false;
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_NUMERO_BON), request.getNumeroBon()));

		}
		
		if (estNonVide(request.getNumeroOf())) {
			requestVide = false;
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_NUMERO_OF), request.getNumeroOf()));

		}

		if (request.getDetailOfId() != null) {
			requestVide = false;
			whereClause
					.add(criteriaBuilder.equal(root.get(PREDICATE_DETAIL_OF), request.getDetailOfId()));

		}
		
		if (request.getDateDe() != null) {
			 requestVide=false;
			 
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE),
					request.getDateDe()));
		}
	
		if (request.getDateA() != null) {
			requestVide=false;
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE),
					request.getDateA()));
		}
		
		if (estNonVide(request.getType())) {
			requestVide = false;
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TYPE), request.getType()));

		}
	

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

		List<MouvementFiniEntity> resultatEntite = new ArrayList<MouvementFiniEntity>();

		if (requestVide == true) {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(39).getResultList();

		} else {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		}

		List<MouvementFiniValue> list = new ArrayList<MouvementFiniValue>();

		for (MouvementFiniEntity entity : resultatEntite) {
			MouvementFiniValue dto = StockFiniPersistanceUtilities.toValue(entity);

			list.add(dto);
		}

		ResultatMulticritereMouvementFiniValue result = new ResultatMulticritereMouvementFiniValue();
		result.setNombreResultaRechercher(Long.valueOf(list.size()));
		result.setMovementFiniValues(((new HashSet<MouvementFiniValue>(list))));
		
		return result;

	}



	/***************************** Getter & Setter ********************************/
	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val) && !val.equals("undefined") && !val.equals("null");

	}

}
