package com.gpro.consulting.tiers.gpao.persitance.abc.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.RechercheRechecheProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ResultatRechecheProductionValue;
import com.gpro.consulting.tiers.gpao.persitance.abc.IProductionPersistance;
import com.gpro.consulting.tiers.gpao.persitance.abc.entity.ProductionEntity;
import com.gpro.consulting.tiers.gpao.persitance.abc.utility.ProductionPersistanceUtilities;

/**
 * Implementation of {@link IProductionPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 11/05/2016
 *
 */

@Component
public class ProductionPersistanceImpl extends AbstractPersistance implements IProductionPersistance{

	private String PREDICATE_DATE = "date";
	private String PREDICATE_CHAINEID="chaineId";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ProductionPersistanceUtilities productionPersistanceUtilities;
	
	@Override
	public String create(ProductionValue request) {

		ProductionEntity entity = (ProductionEntity) this.creer(productionPersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public ProductionValue getById(Long id) {
		
		ProductionEntity entity = this.rechercherParId(id, ProductionEntity.class);

	    return productionPersistanceUtilities.toValue(entity);
	}

	@Override
	public String update(ProductionValue request) {

		ProductionEntity entity = (ProductionEntity) this.modifier(productionPersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(ProductionEntity.class, id.longValue());
	}

	@Override
	public List<ProductionValue> getAll() {
		
		List<ProductionEntity> listEntity = this.lister(ProductionEntity.class);
		
		return productionPersistanceUtilities.listProductionToValue(listEntity);
	}
	
	@Override
	public ResultatRechecheProductionValue rechercherMultiCritere(
			RechercheRechecheProductionValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ProductionEntity> criteriaQuery = criteriaBuilder.createQuery(ProductionEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ProductionEntity> root = criteriaQuery.from(ProductionEntity.class);
		  
		// Set request.chaineId on whereClause if not null
		if(request.getChaineId() != null){
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CHAINEID), request.getChaineId()));
		}
		
		// Set request.dateSaisieFrom on whereClause if not null
	    if (request.getDateSaisieFrom() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateSaisieFrom()));
	    }
	    
		// Set request.dateSaisieTo on whereClause if not null
	    if (request.getDateSaisieTo() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateSaisieTo()));
	    }	
		  
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		List <ProductionEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<ProductionValue> list = new ArrayList<ProductionValue>();
	     
		for (ProductionEntity entity : resultatEntite) {
			ProductionValue dto = productionPersistanceUtilities.toValue(entity);
			list.add(dto);
		}
		
		ResultatRechecheProductionValue result = new ResultatRechecheProductionValue();
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
