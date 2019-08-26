package com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.FicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.PaquetValue;
import com.gpro.consulting.tiers.gpao.persitance.entite.OrdreFabricationEntite;
import com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.IFicheEclatementPersistance;
import com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.IPaquetPersistance;
import com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.entity.FicheEclatementEntity;
import com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.entity.PaquetEntity;
import com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.utility.FicheEclatementPersistanceUtility;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class PaquetPersistanceImpl extends AbstractPersistance implements IPaquetPersistance{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private IFicheEclatementPersistance ficheEclatementPersistance;
	
	private String PREDICATE_FICHE_ECLATEMENT = "ficheEclatement";
	private String PREDICATE_FICHE_ECLATEMENT_ID = "id";
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public PaquetValue getById(Long id) {
		PaquetEntity entity = this.rechercherParId(id, PaquetEntity.class);
		if(entity != null){
		    return FicheEclatementPersistanceUtility.toValue(entity);
		}else{
			return null;
		}
	}

	@Override
	public Long getSommeQte(Long ofId) {
	
		Long sommeQte = 0L; 
		
		if (ofId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<PaquetEntity> root = criteriaQuery.from(PaquetEntity.class);
			
			List<FicheEclatementValue> listFicheEclatement = ficheEclatementPersistance.getByOrdreFabricationId(ofId);
			
			for (FicheEclatementValue ficheEclatementValue : listFicheEclatement) {
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_FICHE_ECLATEMENT).get(PREDICATE_FICHE_ECLATEMENT_ID),ficheEclatementValue.getId()));
			}
			
			criteriaQuery.select(criteriaBuilder.sum(root.get("quantite").as(Long.class))).where(whereClause.toArray(new Predicate[] {}));
		    sommeQte = this.entityManager.createQuery(criteriaQuery).getSingleResult();

		    if(sommeQte == null){
		    	sommeQte = 0L;
		    }
		}
	
		return sommeQte;
	}
}
