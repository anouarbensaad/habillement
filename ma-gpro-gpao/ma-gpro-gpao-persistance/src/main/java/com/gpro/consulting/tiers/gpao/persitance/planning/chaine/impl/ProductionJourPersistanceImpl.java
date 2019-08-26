package com.gpro.consulting.tiers.gpao.persitance.planning.chaine.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
import com.gpro.consulting.tiers.commun.coordination.value.PhaseValue;
import com.gpro.consulting.tiers.commun.persistance.IPhasePersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.ArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.SousFamilleArticleEntity;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.ProductionJourElementValue;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.RechercheMulticritereProductionJourValue;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.ResultatRechecheProductionJourValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.persitance.IChainePersistance;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.entite.OrdreFabricationEntite;
import com.gpro.consulting.tiers.gpao.persitance.planning.chaine.IProductionJourPersistance;
import com.gpro.consulting.tiers.gpao.persitance.planning.chaine.entity.ProductionJourEntity;
import com.gpro.consulting.tiers.gpao.persitance.planning.chaine.utility.ProductionJourPersistanceUtility;

/**
 * @author Hamdi Etteieb
 *
 */
@Component
public class ProductionJourPersistanceImpl extends AbstractPersistance implements IProductionJourPersistance {

	@PersistenceContext
	private EntityManager entityManager;
	private String PREDICATE_DATEDE="date" ;
	private String PREDICATE_DATEA="date" ;
	private String PREDICATE_CHAINE="chaine";
	private String PREDICATE_OF="of";
	private String PREDICATE_PHASE="phase";
	private String PREDICATE_SEMAINE="semaine";
	private String PREDICATE_PARTIE_INTERESSE="partieInterresId";
	private String PREDICATE_OF_URGENT="urgent";
	

	
	@Autowired
	IChainePersistance chainePersistance;
	@Autowired
	IOrdreFabricationPersistance ordreFabricationPersistance;

	
	@Autowired
	IPhasePersistance phasePersistance;
	
	@Override
	public String create(ProductionJourElementValue  pProductionValue) {
		ProductionJourEntity vProductionEntite = (ProductionJourEntity) this.modifier(ProductionJourPersistanceUtility.toEntity(pProductionValue));
		//ProductionJourElementValue  vProductionValueResult = ProductionJourPersistanceUtility.toValue(vProductionEntite);
		return vProductionEntite.getId().toString();
	}


	@Override
	public ProductionJourElementValue  getById(Long id) {
		ProductionJourEntity vProductionEntite = this.rechercherParId(id,ProductionJourEntity.class);
		ProductionJourElementValue  vProductionValueResult = ProductionJourPersistanceUtility.toValue(vProductionEntite);
		vProductionValueResult.setoFDesignation(ordreFabricationPersistance.getNumOfParId(vProductionValueResult.getOf()).toString());
		return vProductionValueResult;
	}

	@Override
	public String update(ProductionJourElementValue  planning) {
		ProductionJourEntity entity = (ProductionJourEntity) this
				.modifier(ProductionJourPersistanceUtility.toEntity(planning));
		return entity.getId().toString();

	}




	@Override
	public ResultatRechecheProductionJourValue rechercherMultiCritere(
			RechercheMulticritereProductionJourValue request) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ProductionJourEntity> criteriaQuery = criteriaBuilder.createQuery(ProductionJourEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ProductionJourEntity> root = criteriaQuery.from(ProductionJourEntity.class);

		Boolean requestVide=true ;
		
		
		if (request.getDateDe() != null) {
			 requestVide=false;
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar> get(PREDICATE_DATEDE),
					request.getDateDe()));
		}
		if (request.getDateA() != null) {
			requestVide=false;
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar> get(PREDICATE_DATEA),
					request.getDateA()));
		}
		if(request.getIdChaine()!=null){
			requestVide=false;
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CHAINE), request.getIdChaine()));
		}
		
        if(request.getIdPhase()!=null){
        	requestVide=false;
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PHASE), request.getIdPhase()));
		}
		
        if(estNonVide(request.getNumOF())){
        	requestVide=false;
        
        	OrdreFabricationValue ofs = ordreFabricationPersistance.getByNumero(request.getNumOF());
        	if (ofs != null)
        	{
        	   	Long idOF=ofs.getId();
            	
    			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_OF),idOF));
        	}
     
		}
        
     // Set request.etat on whereClause if not null
		if (estNonVide(request.getSemaine())) {
			requestVide=false;
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_SEMAINE), request.getSemaine()));
		}
		
		

		
	    
	    
	    if(request.getPartieInterresId()!=null){
        	requestVide=false;
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PARTIE_INTERESSE), request.getPartieInterresId()));
		}
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
		
		
		List<ProductionJourEntity> resultatEntite = new ArrayList<ProductionJourEntity>(); 
		
		if (requestVide==true) {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).setMaxResults(39).getResultList();

		} else {
			resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		}

		List<ProductionJourElementValue > list = new ArrayList<ProductionJourElementValue >();
		Long vIdChaine = 0L;
		Long vRefOf = 0L ;
		for (ProductionJourEntity entity : resultatEntite) {
	    	ProductionJourElementValue  dto = ProductionJourPersistanceUtility.toValue(entity);

	    	vIdChaine = dto.getChaine();
			vRefOf = dto.getOf() ;
			PhaseValue pRecherchePhase=new PhaseValue();
			pRecherchePhase.setId(dto.getPhase());
			
			String chaine =chainePersistance.rechercheDesignationChaineParId(vIdChaine);
			
	
			dto.setChaineDesignation(chaine);
			
			OrdreFabricationValue ordreFabricationValue = ordreFabricationPersistance.rechercheOrdreFabricationParId(vRefOf);
			dto.setoFDesignation(ordreFabricationValue.getNumero());
			dto.setProduitDesignation(ordreFabricationValue.getProduitDesignation());
			dto.setProduitReference(ordreFabricationValue.getProduitReference());
		//	dto.setoFDesignation(ordreFabricationPersistance.getNumOfParId(vRefOf));
			//dto.setDesignationPlanning(dto.getChaineDesignation()+"/"+dto.getoFDesignation());
			
			
			dto.setPhaseDesignation(phasePersistance.recherchePhaseParId(pRecherchePhase).getDesignation());
	    	
			
			list.add(dto);
	    }

		ResultatRechecheProductionJourValue result = new ResultatRechecheProductionJourValue();
		result.setNombreResultaRechercher(Long.valueOf(list.size()));
		result.setList(new TreeSet<>(list));

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
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	

	@Override
	public void delete(Long id) {
		this.supprimerEntite(ProductionJourEntity.class, id);
		
	}


	@Override
	public List<ProductionJourElementValue > listProductionJour() {
		List<ProductionJourEntity> vListeProductionEntite = this.lister(ProductionJourEntity.class);
		List<ProductionJourElementValue > vListeProductionValues = new ArrayList<ProductionJourElementValue >();
		
		for (ProductionJourEntity vProductionEntite : vListeProductionEntite) {
			vListeProductionValues.add(ProductionJourPersistanceUtility.toValue(vProductionEntite));
		}
		
		Collections.sort(vListeProductionValues);
		
		return vListeProductionValues;
	}
	

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val) && !val.equals("undefined") && !val.equals("null");

	}
}
	


