package com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

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
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.RechercheMulticritereFicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.ResultatRechecheFicheEclatementElementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.ResultatRechecheFicheEclatementValue;
import com.gpro.consulting.tiers.gpao.persitance.entite.OrdreFabricationEntite;
import com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.IFicheEclatementPersistance;
import com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.entity.FicheEclatementEntity;
import com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.utility.FicheEclatementPersistanceUtility;

/**
 * Implementation of {@link IFicheEclatementPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */

@Component
public class FicheEclatementPersistanceImpl extends AbstractPersistance implements IFicheEclatementPersistance{
	
	private String ORDRE_FABRICATION_ID = "ordreFabricationId";
	private String PRODUIT_ID = "produitId";
	private String ID = "id";
	private int MAX_RESULTS = 39;


	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private FicheEclatementPersistanceUtility ficheEclatementPersistanceUtility;
	

	@Override
	public ResultatRechecheFicheEclatementValue rechercherMultiCritere(
			RechercheMulticritereFicheEclatementValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<FicheEclatementEntity> criteriaQuery = criteriaBuilder.createQuery(FicheEclatementEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<FicheEclatementEntity> root = criteriaQuery.from(FicheEclatementEntity.class);
		
		// Set request.produitId on whereClause if not null
		if (request.getProduitId()!= null) {
			 
			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<OrdreFabricationEntite> subRoot = subQuery.from(OrdreFabricationEntite.class);
			
			subQuery.select(subRoot.<Long>get(ID));
			subQuery.where(criteriaBuilder.equal(subRoot.get(PRODUIT_ID), request.getProduitId()));
			whereClause.add(criteriaBuilder.in(root.get(ORDRE_FABRICATION_ID)).value(subQuery));
		}
	
		// Set request.ordreFabricationId on whereClause if not null
		if (request.getOrdreFabricationId()!= null) {
			
			whereClause.add(criteriaBuilder.equal(root.get(ORDRE_FABRICATION_ID), request.getOrdreFabricationId()));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
		
		 List <FicheEclatementEntity> resultatEntite = null;
				
		//If criterias are empty
				if(request.isOptimized()){
					resultatEntite = this.entityManager
							.createQuery(criteriaQuery)
							.setMaxResults(MAX_RESULTS)
							.getResultList();

				}else{
					resultatEntite = this.entityManager
							.createQuery(criteriaQuery)
							.getResultList();
				}
				

	    List<ResultatRechecheFicheEclatementElementValue> list = new ArrayList<ResultatRechecheFicheEclatementElementValue>();
	    
	    for (FicheEclatementEntity entity : resultatEntite) {
	    	ResultatRechecheFicheEclatementElementValue dto = ficheEclatementPersistanceUtility.toResultatRechecheFicheEclatementElementValue(entity);
	    	list.add(dto);
	    }
	    
	    ResultatRechecheFicheEclatementValue result = new ResultatRechecheFicheEclatementValue();
	    result.setNombreResultaRechercher(Long.valueOf(list.size()));
	    result.setList(new TreeSet<>(list));
	    
		return result;
	}
	
	@Override
	public String create(FicheEclatementValue request) {

		FicheEclatementEntity entity = (FicheEclatementEntity) this.creer(ficheEclatementPersistanceUtility.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(FicheEclatementEntity.class, id.longValue());
	}
	
	@Override
	public String update(FicheEclatementValue request) {
		
		FicheEclatementEntity entity = (FicheEclatementEntity) this.modifier(ficheEclatementPersistanceUtility.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public FicheEclatementValue getById(Long id) {
		
		FicheEclatementEntity entity = this.rechercherParId(id, FicheEclatementEntity.class);

	    return ficheEclatementPersistanceUtility.toValue(entity);
	}
	
	@Override
	public List<FicheEclatementValue> getAll() {
		
		List<FicheEclatementEntity> listEntity = this.lister(FicheEclatementEntity.class);
		
		return ficheEclatementPersistanceUtility.listFicheEclatementToValue(listEntity);
	}
	
	@Override
	public List<FicheEclatementValue> getByOrdreFabricationId(Long ordreFabricationId) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<FicheEclatementEntity> criteriaQuery = criteriaBuilder.createQuery(FicheEclatementEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<FicheEclatementEntity> root = criteriaQuery.from(FicheEclatementEntity.class);
		
		//ordreFabricationId on whereClause if not null
		if (ordreFabricationId != null) {
			
			whereClause.add(criteriaBuilder.equal(root.get(ORDRE_FABRICATION_ID), ordreFabricationId));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <FicheEclatementEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
	    List<FicheEclatementValue> result = new ArrayList<FicheEclatementValue>();
	    		
	    if(resultatEntite != null){
	    	
	    	for(FicheEclatementEntity entity : resultatEntite){
	    		
	    		result.add(ficheEclatementPersistanceUtility.toValue(entity));
	    	}
	    	
	    }

		return result;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
