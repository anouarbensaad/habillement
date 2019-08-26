package com.gpro.consulting.tiers.gpao.persitance.gammemontage.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ElementGammeValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.GammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ElementGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.GammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.RechercheMulticritereCatalogueValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.ResultatRechecheCatalogueValue;
import com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.entity.FicheEclatementEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IOperationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.entity.OperationEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.utility.GammeMontagePersistanceUtilities;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.impl.ElementGammeOfPersistanceImpl;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.impl.GammeOfPersistanceImpl;

/**
 * Implementation of {@link IOperationPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 04/04/2016
 *
 */

@Component
public class OperationPersistanceImpl extends AbstractPersistance implements IOperationPersistance{

	private static final Logger logger = LoggerFactory.getLogger(OperationPersistanceImpl.class);

	private String PREDICATE_CODE = "code";
	private String PREDICATE_MACHINE = "machineId";
	private String PREDICATE_SECTION = "sectionId";
	private String PREDICATE_DESIGNATION = "designation";
	private String PREDICATE_OBSERVATION = "observations";

	private String PREDICATE_COMPTAGE = "comptage";

	private String PERCENT = "%";
	
	private int MAX_RESULTS = 39;

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private GammeMontagePersistanceUtilities gammeMontagePersistanceUtilities;
	
	@Autowired
	private GammeOfPersistanceImpl gammeOfPersistance;
	
	@Autowired
	private ElementGammeOfPersistanceImpl elementGammeOfPersistance;
	
	@Autowired
	private GammeProduitPersistanceImpl gammeProduitPersistance;
	
	@Autowired
	private ElementGammePersistanceImpl elementGammePersistance;
	
	@Override
	public ResultatRechecheCatalogueValue rechercherMultiCritere(
			RechercheMulticritereCatalogueValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<OperationEntity> criteriaQuery = criteriaBuilder.createQuery(OperationEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<OperationEntity> root = criteriaQuery.from(OperationEntity.class);
		
		// Set request.code on whereClause if not empty or null
		if (estNonVide(request.getCode())) {
			Expression<String> path = root.get(PREDICATE_CODE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getCode().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		// Set request.machineId on whereClause if not null
		if (request.getMachineId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_MACHINE), request.getMachineId()));
		}
		
		// Set request.sectionId on whereClause if not null
		if (request.getSectionId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_SECTION), request.getSectionId()));
		}
		
		// Set request.designation on whereClause if not empty or null
		if (estNonVide(request.getDesignation())) {
			Expression<String> path = root.get(PREDICATE_DESIGNATION);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getDesignation().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		// Set request.observations on whereClause if not empty or null
		if (estNonVide(request.getObservations())) {
			Expression<String> path = root.get(PREDICATE_OBSERVATION);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getObservations().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
		
		 List <OperationEntity> resultatEntite = null;
				
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

	    List<OperationValue> list = new ArrayList<OperationValue>();
	    
	    for (OperationEntity entity : resultatEntite) {
	    	OperationValue dto = gammeMontagePersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }
	    
	    ResultatRechecheCatalogueValue result = new ResultatRechecheCatalogueValue();
	    result.setNombreResultaRechercher(Long.valueOf(list.size()));
	    result.setList(new TreeSet<>(list));
	    
		return result;
	}
	
	@Override
	public String create(OperationValue request) {

		OperationEntity entity = (OperationEntity) this.creer(gammeMontagePersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(OperationEntity.class, id.longValue());
	}
	
	@Override
	public String update(OperationValue request) {
		
		OperationEntity entity = (OperationEntity) this.modifier(gammeMontagePersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public OperationValue getById(Long id) {
		
		OperationEntity entity = this.rechercherParId(id, OperationEntity.class);

	    return gammeMontagePersistanceUtilities.toValue(entity);
	}
	
	@Override
	public List<OperationValue> getAll() {
		
		List<OperationEntity> listEntity = this.lister(OperationEntity.class);
		
		return gammeMontagePersistanceUtilities.listOperationToValue(listEntity);
	}

	@Override
	public OperationValue getByCode(String operationCode) {

		if(estNonVide(operationCode)){
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<OperationEntity> criteriaQuery = criteriaBuilder.createQuery(OperationEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<OperationEntity> root = criteriaQuery.from(OperationEntity.class);
			
			// Set operationCode on whereClause if not empty or null
			Expression<String> path = root.get(PREDICATE_CODE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + operationCode.toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
						
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <OperationEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		    if(resultatEntite != null && resultatEntite.size() > 0){
		    	
		    	return gammeMontagePersistanceUtilities.toValue(resultatEntite.get(0));
		    }
		    
		}
		
		return null;
	}
	
	
	@Override
	public Long createOperation(OperationValue operationValue) {

		OperationEntity entity = (OperationEntity) this.creer(gammeMontagePersistanceUtilities.toEntity(operationValue));

	    return entity.getId();
	}
	
	
	@Override
	public Long updateOperation(OperationValue operationValue) {
		
		OperationEntity entity = (OperationEntity) this.modifier(gammeMontagePersistanceUtilities.toEntity(operationValue));
		
		 return entity.getId();
	}
	

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Long getIdByCode(String operationCode) {

	Long resultatId = null;
		
	if(estNonVide(operationCode)){
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<OperationEntity> root = criteriaQuery.from(OperationEntity.class);
			
			// Set operationCode on whereClause if not empty or null
			Expression<String> path = root.get(PREDICATE_CODE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + operationCode.toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
						
			criteriaQuery.select(root.get("id").as(Long.class)).where(whereClause.toArray(new Predicate[] {}));
		   	
		   	List<Long> resultatList = this.entityManager.createQuery(criteriaQuery).getResultList();
		   	
		   	if(resultatList.size()>0){
		   		resultatId= resultatList.get(0);
		   	}

		}
	
		return resultatId;
	}

	@Override
	public List<OperationValue> getAllByOF(Long OFId) {
		
		List<OperationValue> listOperations= new ArrayList<OperationValue>();
		if(OFId != null){
			
			GammeOfValue gammeOF = gammeOfPersistance.getByOFId(OFId);
			
			List<ElementGammeOfValue> listElementGammeOF = elementGammeOfPersistance.getListByGammeOFId(gammeOF.getId());
			
			for (ElementGammeOfValue elementGammeOfValue : listElementGammeOF) {
				
				OperationValue op = this.getById(elementGammeOfValue.getOperationId());
				listOperations.add(op);
				
			}
		}
	
		return listOperations;
	}

	@Override
	public List<OperationValue> getAllByProduit(Long produitId) {

		List<OperationValue> listOperations= new ArrayList<OperationValue>();
		if(produitId != null){
			
			GammeProduitValue gammeProduit = gammeProduitPersistance.getByProduitId(produitId);
			
			if(gammeProduit != null){
				List<ElementGammeValue> listElementGamme = elementGammePersistance.getAllByGammeProdId(gammeProduit.getId());
				
				for (ElementGammeValue elementGammValue : listElementGamme) {
					
					OperationValue op = this.getById(elementGammValue.getOperationId());
					listOperations.add(op);
					
				}
			}
			
		}
	
		return listOperations;
	}

	@Override
	public List<OperationValue> getSwitchComptage(boolean comptage) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<OperationEntity> criteriaQuery = criteriaBuilder.createQuery(OperationEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<OperationEntity> root = criteriaQuery.from(OperationEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_COMPTAGE), comptage));
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <OperationEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		    List<OperationValue> result = new ArrayList<OperationValue>();
		    for (OperationEntity operationEntity : resultatEntite) {
				result.add(gammeMontagePersistanceUtilities.toValue(operationEntity));
			}
		return result;
	}






}
