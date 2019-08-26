package com.gpro.consulting.tiers.gpao.persitance.gammemontage.impl;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.GammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.RechercheMulticritereGammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitElementValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitValue;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IGammeProduitPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.entity.ElementGammeEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.entity.GammeProduitEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.entity.OperationEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.utility.GammeMontagePersistanceUtilities;

/**
 * Implementation of {@link IGammeProduitPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */

@Component
public class GammeProduitPersistanceImpl extends AbstractPersistance implements IGammeProduitPersistance{

	private String PREDICATE_TEMPS_TOTAL = "tempsTotal";
	private String PREDICATE_PRODUIT = "produitId";
	private String JOIN_LISTELEMENTGAMME = "listElementGamme";
	private String COLUMN_MACHINE = "machineId";
	private String COLUMN_SECTION = "sectionId";
	
	private int MAX_RESULTS = 26;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private GammeMontagePersistanceUtilities gammeMontagePersistanceUtilities;
	
	@Override
	public ResultatRechecheGammeProduitValue rechercherMultiCritere(
			RechercheMulticritereGammeProduitValue request, boolean allegee) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<GammeProduitEntity> root = criteriaQuery.from(GammeProduitEntity.class);
		
		// Set request.tempsTotalMin on whereClause if not null
	    if (request.getTempsTotalMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_TEMPS_TOTAL), request.getTempsTotalMin()));
	    }
	    
		// Set request.tempsTotalMax on whereClause if not null
	    if (request.getTempsTotalMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_TEMPS_TOTAL), request.getTempsTotalMax()));
	    }
	
		// Set request.produitId on whereClause if not null
		if (request.getProduitId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), request.getProduitId()));
		}
			
		// Set request.machineId on whereClause if not null
		if (request.getMachineId() != null) {
			Join<GammeProduitEntity, ElementGammeEntity> jointure = root.join(JOIN_LISTELEMENTGAMME);
			whereClause.add(criteriaBuilder.equal(jointure.get(COLUMN_MACHINE), request.getMachineId()));
		}
		
		// Set request.sectionId on whereClause if not null
		if (request.getSectionId() != null) {
			Join<GammeProduitEntity, ElementGammeEntity> jointure = root.join(JOIN_LISTELEMENTGAMME);
			whereClause.add(criteriaBuilder.equal(jointure.get(COLUMN_SECTION), request.getSectionId()));
		}
		
		criteriaQuery.select(criteriaBuilder.array(
				root.get("id").as(Long.class),
				root.get("tempsTotal").as(Double.class),
				root.get("nbOperation").as(Long.class),
				root.get("produitId").as(Long.class)
				
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
				

	    List<ResultatRechecheGammeProduitElementValue> list = new ArrayList<ResultatRechecheGammeProduitElementValue>();
	    
	    for (Object[] element : resultatEntite) {
	    	
	    	GammeProduitEntity entity = new GammeProduitEntity();
	    	
	    	entity.setId((Long) element[0] );
	    	entity.setTempsTotal((Double) element[1]);
	    	entity.setNbOperation((Long) element[2]);
	    	entity.setProduitId((Long) element[3]);
	    	
	    	ResultatRechecheGammeProduitElementValue dto = gammeMontagePersistanceUtilities.toResultatRechecheGammeProduitElementValue(entity);
	    	list.add(dto);
	    }
	    
	    ResultatRechecheGammeProduitValue result = new ResultatRechecheGammeProduitValue();
	    result.setNombreResultaRechercher(Long.valueOf(list.size()));
	    result.setList(new TreeSet<>(list));
	    
		return result;
	}
	
	@Override
	public ResultatRechecheGammeProduitValue rechercherMultiCritere(
			RechercheMulticritereGammeProduitValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<GammeProduitEntity> criteriaQuery = criteriaBuilder.createQuery(GammeProduitEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<GammeProduitEntity> root = criteriaQuery.from(GammeProduitEntity.class);
		
		// Set request.tempsTotalMin on whereClause if not null
	    if (request.getTempsTotalMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_TEMPS_TOTAL), request.getTempsTotalMin()));
	    }
	    
		// Set request.tempsTotalMax on whereClause if not null
	    if (request.getTempsTotalMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_TEMPS_TOTAL), request.getTempsTotalMax()));
	    }
	
		// Set request.produitId on whereClause if not null
		if (request.getProduitId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), request.getProduitId()));
		}
			
		// Set request.machineId on whereClause if not null
		if (request.getMachineId() != null) {
			Join<GammeProduitEntity, ElementGammeEntity> jointure = root.join(JOIN_LISTELEMENTGAMME);
			whereClause.add(criteriaBuilder.equal(jointure.get(COLUMN_MACHINE), request.getMachineId()));
		}
		
		// Set request.sectionId on whereClause if not null
		if (request.getSectionId() != null) {
			Join<GammeProduitEntity, ElementGammeEntity> jointure = root.join(JOIN_LISTELEMENTGAMME);
			whereClause.add(criteriaBuilder.equal(jointure.get(COLUMN_SECTION), request.getSectionId()));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
		
		List <GammeProduitEntity> resultatEntite = null;
				
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
				
				

	    List<ResultatRechecheGammeProduitElementValue> list = new ArrayList<ResultatRechecheGammeProduitElementValue>();
	    
	    for (GammeProduitEntity entity : resultatEntite) {
	    	ResultatRechecheGammeProduitElementValue dto = gammeMontagePersistanceUtilities.toResultatRechecheGammeProduitElementValue(entity);
	    	list.add(dto);
	    }
	    
	    ResultatRechecheGammeProduitValue result = new ResultatRechecheGammeProduitValue();
	    result.setNombreResultaRechercher(Long.valueOf(list.size()));
	    result.setList(new TreeSet<>(list));
	    
		return result;
	}
	
	@Override
	public String create(GammeProduitValue request) {

		GammeProduitEntity entity = (GammeProduitEntity) this.creer(gammeMontagePersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(GammeProduitEntity.class, id.longValue());
	}
	
	@Override
	public String update(GammeProduitValue request) {
		
		GammeProduitEntity entity = (GammeProduitEntity) this.modifier(gammeMontagePersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public GammeProduitValue getById(Long id) {
		
		GammeProduitEntity entity = this.rechercherParId(id, GammeProduitEntity.class);

	    return gammeMontagePersistanceUtilities.toValue(entity);
	}
	
	
	
	@Override
	public List<GammeProduitValue> getAll() {
		
	//	List<GammeProduitEntity> listEntity = this.lister(GammeProduitEntity.class);
				
		// A modifier 
		//TODO OPTIMISATION
		
		
		List <GammeProduitValue> listGamme=new ArrayList<GammeProduitValue>();
		
		
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
	    /** entity principale **/
	    CriteriaQuery < Object[] > vCriteriaQuery = vBuilder.createQuery(Object[].class);
	    List < Predicate > vWhereClause = new ArrayList < Predicate >();

	    /** create liste resyltat ***/

	    /************ entity jointure *****************/
	    Root < GammeProduitEntity > vRootGamme = vCriteriaQuery.from(GammeProduitEntity.class);

	    /** execute query and do something with result **/

	    vCriteriaQuery.select(vBuilder.array(
	    		vRootGamme.get("id").as(Long.class),
	    		vRootGamme.get("produitId").as(Long.class)
	    		   		
	    		)).where(vWhereClause.toArray(new Predicate[] {}));
	    
	    vCriteriaQuery.orderBy(vBuilder.desc(vRootGamme.get("id")));
	 
	 
	    List<Object[]> resultatEntite = null;
	    resultatEntite = this.entityManager
				.createQuery(vCriteriaQuery)
				.getResultList();
	    
	    
	    for (Object[] element : resultatEntite) {
	    	
	    	GammeProduitEntity vGammeEntite = new GammeProduitEntity();
	    	
	    	vGammeEntite.setId((Long) element[0]);
	    	vGammeEntite.setProduitId((Long) element[1]);
           GammeProduitValue vBm = gammeMontagePersistanceUtilities.toValue(vGammeEntite);
           listGamme.add(vBm);
	    
	    }
		
		return listGamme;
	}

	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public GammeProduitValue getByProduitId(Long produitId) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<GammeProduitEntity> criteriaQuery = criteriaBuilder.createQuery(GammeProduitEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<GammeProduitEntity> root = criteriaQuery.from(GammeProduitEntity.class);
		
		// Set request.tempsTotalMin on whereClause if not null
	    if (produitId != null) {
	    	whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), produitId));
	    }
	    
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List<GammeProduitEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
	    GammeProduitValue resultat = new GammeProduitValue();
	    if(resultatEntite.size()>0){
	    	 resultat = gammeMontagePersistanceUtilities.toValue(resultatEntite.get(0));
	    }
	    
	    
	    return resultat;

	}


}
