package com.gpro.consulting.tiers.gpao.persitance.fichecolisage.impl;

import java.util.ArrayList;
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
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.FicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereFicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheFicheColisageElementValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheFicheColisageValue;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.IFicheColisagePersistance;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.entity.FicheColisageEntity;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.utility.FicheColisagePersistanceUtility;

/**
 * Implementation of {@link IFicheColisagePersistance} interface.
 *  
 * @author Hamdi etteieb
 * @since 07/12/2017
 *
 */

@Component
public class FicheColisagePersistanceImpl extends AbstractPersistance implements IFicheColisagePersistance{
	
	private String ORDRE_FABRICATION_NUM = "numeroOf";
	private String PRODUIT_ID = "produitId";
	private String ID = "id";
	
	private String PRODUIT_DESIGNATION = "produitDesignation";

	private String SEMAINE = "semaine";
	private String CLIENT = "clientId";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private FicheColisagePersistanceUtility ficheColisagePersistanceUtility;
	

	@Override
	public ResultatRechecheFicheColisageValue rechercherMultiCritere(
			RechercheMulticritereFicheColisageValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<FicheColisageEntity> criteriaQuery = criteriaBuilder.createQuery(FicheColisageEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<FicheColisageEntity> root = criteriaQuery.from(FicheColisageEntity.class);
		
	   
	//	//System.out.println("######    REQUEST  :   "+request.toString());
		
	
		if (request.getNumeroOF()!= null && !request.getNumeroOF().equals("")) {
		//	//System.out.println("### ENTER TO 1");
			whereClause.add(criteriaBuilder.equal(root.get(ORDRE_FABRICATION_NUM), request.getNumeroOF()));
		}
		
	
	    if (request.getDesignationProduit()!= null && !request.getDesignationProduit().equals("")) {
	    	//    //System.out.println("### ENTER TO 2");
				whereClause.add(criteriaBuilder.equal(root.get(PRODUIT_DESIGNATION), request.getDesignationProduit()));
			}
	    
	    if (request.getSemaine()!= null && !request.getSemaine().equals("")) {
	    	////System.out.println("### ENTER TO 3");
			whereClause.add(criteriaBuilder.equal(root.get(SEMAINE), request.getSemaine()));
		}
	    
        if (request.getPartieIntersseId()!= null) {
        	////System.out.println("### ENTER TO 4");
			whereClause.add(criteriaBuilder.equal(root.get(CLIENT), request.getPartieIntersseId()));
		}
	    
	    
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
		
		 List <FicheColisageEntity> resultatEntite = null;
		resultatEntite = this.entityManager
					.createQuery(criteriaQuery)
					.getResultList();
	
		

	    List<ResultatRechecheFicheColisageElementValue> list = new ArrayList<ResultatRechecheFicheColisageElementValue>();
	    
	    for (FicheColisageEntity entity : resultatEntite) {
	    	ResultatRechecheFicheColisageElementValue dto = ficheColisagePersistanceUtility.toResultatRechecheFicheColisageElementValue(entity);
	    	list.add(dto);
	    }
	    
	    ResultatRechecheFicheColisageValue result = new ResultatRechecheFicheColisageValue();
	    result.setNombreResultaRechercher(Long.valueOf(list.size()));
	    result.setList(new TreeSet<>(list));
	    //System.out.println("###### LIST SIZE  ######  :   "+list.size());
		return result;
	}
	
	@Override
	public String create(FicheColisageValue request) {

		FicheColisageEntity entity = (FicheColisageEntity) this.creer(ficheColisagePersistanceUtility.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(FicheColisageEntity.class, id.longValue());
	}
	
	@Override
	public String update(FicheColisageValue request) {
		
		FicheColisageEntity entity = (FicheColisageEntity) this.modifier(ficheColisagePersistanceUtility.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public FicheColisageValue getById(Long id) {
		
		FicheColisageEntity entity = this.rechercherParId(id, FicheColisageEntity.class);

	    return ficheColisagePersistanceUtility.toValue(entity);
	}
	
	@Override
	public List<FicheColisageValue> getAll() {
		
		List<FicheColisageEntity> listEntity = this.lister(FicheColisageEntity.class);
		
		return ficheColisagePersistanceUtility.listFicheColisageToValue(listEntity);
	}
	
	@Override
	public List<FicheColisageValue> getByOrdreFabricationId(Long ordreFabricationId) {
		
		
        //TODO 
		return null;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
