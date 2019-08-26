package com.gpro.consulting.tiers.gpao.persitance.gammeof.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ElementGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.GammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.RechercheMulticritereGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ResultatRechecheGammeOfElementValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ResultatRechecheGammeOfValue;
import com.gpro.consulting.tiers.gpao.persitance.entite.OrdreFabricationEntite;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.IGammeOfPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.entity.ElementGammeOfEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.entity.GammeOfEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.utility.GammeOfPersistanceUtilities;

/**
 * Implementation of {@link IGammeOfPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 10/05/2016
 *
 */

@Component
public class GammeOfPersistanceImpl extends AbstractPersistance implements IGammeOfPersistance{

	private static final Logger logger = LoggerFactory.getLogger(GammeOfPersistanceImpl.class);

	private String PREDICATE_TEMPS_TOTAL = "tempsTotal";
	private String ORDRE_FABRICATION = "ordreFabricationId";
	private String PRODUIT_ID = "produitId";
	private String GAMME_OF = "gammeOf";
	private String COLUMN_MACHINE = "machineId";
	private String COLUMN_SECTION = "sectionId";
	private String ID = "id";
	
	private int MAX_RESULTS = 26;

	
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
	public ResultatRechecheGammeOfValue rechercherMultiCritere(
			RechercheMulticritereGammeOfValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<GammeOfEntity> criteriaQuery = criteriaBuilder.createQuery(GammeOfEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<GammeOfEntity> root = criteriaQuery.from(GammeOfEntity.class);
		
		// Set request.tempsTotalMin on whereClause if not null
	    if (request.getTempsTotalMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_TEMPS_TOTAL), request.getTempsTotalMin()));
	    }
	    
		// Set request.tempsTotalMax on whereClause if not null
	    if (request.getTempsTotalMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_TEMPS_TOTAL), request.getTempsTotalMax()));
	    }
	    
		// Set request.ordreFabricationId on whereClause if not null
		if (request.getOrdreFabricationId()!= null) {
			whereClause.add(criteriaBuilder.equal(root.get(ORDRE_FABRICATION), request.getOrdreFabricationId()));
		}
	    
		// Set request.produitId on whereClause if not null
		if (request.getProduitId()!= null) {
			 
			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<OrdreFabricationEntite> subRoot = subQuery.from(OrdreFabricationEntite.class);
			
			subQuery.select(subRoot.<Long>get(ID));
			subQuery.where(criteriaBuilder.equal(subRoot.get(PRODUIT_ID), request.getProduitId()));
			whereClause.add(criteriaBuilder.in(root.get(ORDRE_FABRICATION)).value(subQuery));
			
		}
			
		// Set request.machineId on whereClause if not null
		if (request.getMachineId() != null) {
			
			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<ElementGammeOfEntity> subRoot = subQuery.from(ElementGammeOfEntity.class);
			
			Join<ElementGammeOfEntity, GammeOfEntity> jointure = subRoot.join(GAMME_OF);
			
			subQuery.select(jointure.<Long>get(ID));
			subQuery.where(criteriaBuilder.equal(subRoot.get(COLUMN_MACHINE), request.getMachineId()));
			whereClause.add(criteriaBuilder.in(root.get(ID)).value(subQuery));

		}
		
		// Set request.sectionId on whereClause if not null
		if (request.getSectionId() != null) {
			
			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<ElementGammeOfEntity> subRoot = subQuery.from(ElementGammeOfEntity.class);
			
			Join<ElementGammeOfEntity, GammeOfEntity> jointure = subRoot.join(GAMME_OF);
			
			subQuery.select(jointure.<Long>get(ID));
			subQuery.where(criteriaBuilder.equal(subRoot.get(COLUMN_SECTION), request.getSectionId()));
			whereClause.add(criteriaBuilder.in(root.get(ID)).value(subQuery));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    
	criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
		
		List <GammeOfEntity> resultatEntite = null;
				
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


	    List<ResultatRechecheGammeOfElementValue> list = new ArrayList<ResultatRechecheGammeOfElementValue>();
	    
	    for (GammeOfEntity entity : resultatEntite) {
	    	ResultatRechecheGammeOfElementValue dto = gammeOfPersistanceUtilities.toResultatRechecheGammeOfElementValue(entity);
	    	list.add(dto);
	    }
	    
	    ResultatRechecheGammeOfValue result = new ResultatRechecheGammeOfValue();
	    result.setNombreResultaRechercher(Long.valueOf(list.size()));
	    result.setList(new TreeSet<>(list));
	    
		return result;
	}
	
	@Override
	public ResultatRechecheGammeOfValue rechercherMultiCritere(
			RechercheMulticritereGammeOfValue request, boolean allegee) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<GammeOfEntity> root = criteriaQuery.from(GammeOfEntity.class);
		
		// Set request.tempsTotalMin on whereClause if not null
	    if (request.getTempsTotalMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_TEMPS_TOTAL), request.getTempsTotalMin()));
	    }
	    
		// Set request.tempsTotalMax on whereClause if not null
	    if (request.getTempsTotalMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_TEMPS_TOTAL), request.getTempsTotalMax()));
	    }
	    
		// Set request.ordreFabricationId on whereClause if not null
		if (request.getOrdreFabricationId()!= null) {
			whereClause.add(criteriaBuilder.equal(root.get(ORDRE_FABRICATION), request.getOrdreFabricationId()));
		}
	    
		// Set request.produitId on whereClause if not null
		if (request.getProduitId()!= null) {
			 
			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<OrdreFabricationEntite> subRoot = subQuery.from(OrdreFabricationEntite.class);
			
			subQuery.select(subRoot.<Long>get(ID));
			subQuery.where(criteriaBuilder.equal(subRoot.get(PRODUIT_ID), request.getProduitId()));
			whereClause.add(criteriaBuilder.in(root.get(ORDRE_FABRICATION)).value(subQuery));
			
		}
			
		// Set request.machineId on whereClause if not null
		if (request.getMachineId() != null) {
			
			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<ElementGammeOfEntity> subRoot = subQuery.from(ElementGammeOfEntity.class);
			
			Join<ElementGammeOfEntity, GammeOfEntity> jointure = subRoot.join(GAMME_OF);
			
			subQuery.select(jointure.<Long>get(ID));
			subQuery.where(criteriaBuilder.equal(subRoot.get(COLUMN_MACHINE), request.getMachineId()));
			whereClause.add(criteriaBuilder.in(root.get(ID)).value(subQuery));

		}
		
		// Set request.sectionId on whereClause if not null
		if (request.getSectionId() != null) {
			
			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<ElementGammeOfEntity> subRoot = subQuery.from(ElementGammeOfEntity.class);
			
			Join<ElementGammeOfEntity, GammeOfEntity> jointure = subRoot.join(GAMME_OF);
			
			subQuery.select(jointure.<Long>get(ID));
			subQuery.where(criteriaBuilder.equal(subRoot.get(COLUMN_SECTION), request.getSectionId()));
			whereClause.add(criteriaBuilder.in(root.get(ID)).value(subQuery));
		}
		
		criteriaQuery.select(criteriaBuilder.array(
				
				root.get("id").as(Long.class),
				root.get("tempsTotal").as(Double.class),
				root.get("nbOperation").as(Long.class),
				root.get("ordreFabricationId").as(Long.class)
				
				)).where(whereClause.toArray(new Predicate[] {}));
		
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
		
		List <Object[]> resultatEntite = null;
				
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

	    List<ResultatRechecheGammeOfElementValue> list = new ArrayList<ResultatRechecheGammeOfElementValue>();
	    
	    for (Object[] element : resultatEntite) {
	    
	    	GammeOfEntity entity = new GammeOfEntity();
	    	
	    	entity.setId((Long) element[0]);
	    	entity.setTempsTotal((Double) element[1]);
	    	entity.setNbOperation((Long)element[2]);
	    	entity.setOrdreFabricationId((Long) element[3]);
	    	ResultatRechecheGammeOfElementValue dto = gammeOfPersistanceUtilities.toResultatRechecheGammeOfElementValue(entity);
	    	list.add(dto);
	    }
	    
	    ResultatRechecheGammeOfValue result = new ResultatRechecheGammeOfValue();
	    result.setNombreResultaRechercher(Long.valueOf(list.size()));
	    result.setList(new TreeSet<>(list));
	    
		return result;
	}
	
	@Override
	public GammeOfValue getByOFId(Long ordreFabricationId) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<GammeOfEntity> criteriaQuery = criteriaBuilder.createQuery(GammeOfEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<GammeOfEntity> root = criteriaQuery.from(GammeOfEntity.class);
		
		// Set request.ordreFabricationId on whereClause if not null
		if (ordreFabricationId!= null) {
			whereClause.add(criteriaBuilder.equal(root.get(ORDRE_FABRICATION), ordreFabricationId));
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <GammeOfEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    if(resultatEntite != null && resultatEntite.size() >0)
	    	return gammeOfPersistanceUtilities.toValue(resultatEntite.get(0));
		
	    else return null;
	}
	
	@Override
	public String create(GammeOfValue request) {

		GammeOfEntity entity = (GammeOfEntity) this.creer(gammeOfPersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(GammeOfEntity.class, id.longValue());
	}
	
	@Override
	public String update(GammeOfValue request) {
		//LOGGER.info("=========PersistanceLayer : ");
		GammeOfEntity entity = (GammeOfEntity) this.modifier(gammeOfPersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public GammeOfValue getById(Long id) {
		
		GammeOfEntity entity = this.rechercherParId(id, GammeOfEntity.class);

	    return gammeOfPersistanceUtilities.toValue(entity);
	}
	
	@Override
	public List<GammeOfValue> getAll() {
		
		List<GammeOfEntity> listEntity = this.lister(GammeOfEntity.class);
		
		return gammeOfPersistanceUtilities.listGammeOfToValue(listEntity);
	}


	@Override
	public Long getOFIdByGammeId(Long gammeOfId) {
		
		 Long ofId= null;
		
	    if (gammeOfId != null) {
	    	
	    	CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<GammeOfEntity> root = criteriaQuery.from(GammeOfEntity.class);
			
	    	whereClause.add(criteriaBuilder.equal(root.get(ID), gammeOfId));
	    	
	    	 criteriaQuery.select(root.get("ordreFabricationId").as(Long.class)).where(whereClause.toArray(new Predicate[] {}));
	 	     ofId = this.entityManager.createQuery(criteriaQuery).getSingleResult();
	    }
	    
	   return ofId;
	    
	}


	@Override
	public ElementGammeOfValue getElementGammeOFById(Long id) {
		ElementGammeOfEntity entity = this.rechercherParId(id, ElementGammeOfEntity.class);

	    return gammeOfPersistanceUtilities.toValue(entity);
	}

}
