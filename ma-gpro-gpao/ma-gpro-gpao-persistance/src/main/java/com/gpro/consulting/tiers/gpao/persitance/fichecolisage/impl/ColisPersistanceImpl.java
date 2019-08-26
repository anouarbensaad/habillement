package com.gpro.consulting.tiers.gpao.persitance.fichecolisage.impl;

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
import com.gpro.consulting.tiers.commun.persistance.entity.SousFamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheColisElementValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheColisValue;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.IColisPersistance;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.IFicheColisagePersistance;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.entity.ColisEntity;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.entity.FicheColisageEntity;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.utility.FicheColisagePersistanceUtility;

/**
 * Implementation of {@link IFicheColisagePersistance} interface.
 *  
 * @author Ghazi Atroussi
 * @since 07/12/2017
 *
 */

@Component
public class ColisPersistanceImpl extends AbstractPersistance implements IColisPersistance{
	
	private String ORDRE_FABRICATION_NUM = "numeroOf";
	private String PRODUIT_REFERENCE = "produitId";
	private String ID = "id";
	
	private String PRODUIT_DESIGNATION = "produitDesignation";

	private String SEMAINE = "semaine";
	private String CLIENT = "clientId";
	
	private String PALETTE="palette";
	private String ficheColisage="ficheColisage";
	
	private String PREDICATE_BON_SORTIE = "bonSortie";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private FicheColisagePersistanceUtility ficheColisagePersistanceUtility;
	

	@Override
	public ResultatRechecheColisValue rechercherMultiCritere(
			RechercheMulticritereColisValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<ColisEntity> criteriaQuery = criteriaBuilder.createQuery(ColisEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ColisEntity> root = criteriaQuery.from(ColisEntity.class);
		
	   
	//	//System.out.println("######    REQUEST  :   "+request.toString());
		
	
		if (request.getNumeroOF()!= null && !request.getNumeroOF().equals("")) {
             
	    	Join<ColisEntity, FicheColisageEntity> jointureFiche = root.join(ficheColisage);

		
			whereClause.add(criteriaBuilder.equal(jointureFiche.get(ORDRE_FABRICATION_NUM), request.getNumeroOF()));
		}
		
	
	    if (request.getDesignationProduit()!= null && !request.getDesignationProduit().equals("")) {
	    	//    //System.out.println("### ENTER TO 2");
				
	    	Join<ColisEntity, FicheColisageEntity> jointureFiche = root.join(ficheColisage);

	    	whereClause.add(criteriaBuilder.equal(jointureFiche.get(PRODUIT_DESIGNATION), request.getDesignationProduit()));
			}
	    
	    if (request.getSemaine()!= null && !request.getSemaine().equals("")) {
	    
	    	Join<ColisEntity, FicheColisageEntity> jointureFiche = root.join(ficheColisage);
			
	    	
	    	
			whereClause.add(criteriaBuilder.equal(jointureFiche.get(SEMAINE), request.getSemaine()));
		}
	    
        if (request.getPartieIntersseId()!= null) {
        
	    	Join<ColisEntity, FicheColisageEntity> jointureFiche = root.join(ficheColisage);

        	whereClause.add(criteriaBuilder.equal(jointureFiche.get(CLIENT), request.getPartieIntersseId()));
		}
	    
        // champ directe 
        if (request.getPalette()!=null && !request.getPalette().equals("")){
        	
        	whereClause.add(criteriaBuilder.equal(root.get(PALETTE), request.getPalette()));

        }
       
        // has palette or not
        if (request.getHasPalette()!=null){
        	
        	if(request.getHasPalette() == false)
        	   whereClause.add(criteriaBuilder.isNull(root.get(PREDICATE_BON_SORTIE)));
        	else
         	   whereClause.add(criteriaBuilder.isNotNull(root.get(PREDICATE_BON_SORTIE)));

        }
        
     
        
 
	    
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
		
		 List <ColisEntity> resultatEntite = null;
		resultatEntite = this.entityManager
					.createQuery(criteriaQuery)
					.getResultList();
	
		

	    List<ResultatRechecheColisElementValue> list = new ArrayList<ResultatRechecheColisElementValue>();
	    
	    for (ColisEntity entity : resultatEntite) {
	    	ResultatRechecheColisElementValue dto = ficheColisagePersistanceUtility.toResultatRechecheColisElementValue(entity);
	    	list.add(dto);
	    }
	    
	    ResultatRechecheColisValue result = new ResultatRechecheColisValue();
	    result.setNombreResultaRechercher(Long.valueOf(list.size()));
	    result.setList(new TreeSet<>(list));
	    //System.out.println("###### LIST SIZE  ######  :   "+list.size());
		return result;
	}
		
	
	
	// modifier produit
		@Override
		public String modifierColis(ColisValue pProduitValue) {
            
			//System.out.println("##### MODIF COLIS :   "+pProduitValue.getId());
			
			ColisEntity vProduitEntity = (ColisEntity) this
					.modifier(ficheColisagePersistanceUtility.toEntity(pProduitValue));
			ColisValue vProduitValueResult = ficheColisagePersistanceUtility.toValue(vProduitEntity);
			return vProduitValueResult.getId().toString();
		}
	
	
		@Override
	public List<ColisValue> getRouleauFiniListByBarreCodeList(List<String> barreCodeList, Long id) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		    CriteriaQuery<ColisEntity> criteriaQuery = criteriaBuilder.createQuery(ColisEntity.class);
		    
		    List<Predicate> whereClause = new ArrayList<Predicate>();
		    
		    Root<ColisEntity> root = criteriaQuery.from(ColisEntity.class);
		    
		    /** Ajout des criteres de recherche par BarreCode*/
		    
		    // BarreCode is the reference on the db
		    whereClause.add(root.get("id").in(barreCodeList));
		    
			// Set bonSortieIid on whereClause if not null
			if (id != null) {
				
		    	Predicate predicate1 = criteriaBuilder.isNull(root.get(PREDICATE_BON_SORTIE));
		    	Predicate predicate2 = criteriaBuilder.equal(root.get(PREDICATE_BON_SORTIE), id);
			      
		    	whereClause.add(criteriaBuilder.or(predicate1, predicate2));
			}else{
				
			    // Retrive only RouleauFiniEntity list, that haven't BON_SORTIE
			    whereClause.add(criteriaBuilder.isNull(root.get(PREDICATE_BON_SORTIE)));
			}
		    
		    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List < ColisEntity > resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		    List<ColisValue> listRouleau = new ArrayList<ColisValue>();
		    
		    for (ColisEntity entity : resultatEntite) {
		      ColisValue value = ficheColisagePersistanceUtility.toValue(entity);
		      listRouleau.add(value);
		    }
		    
		    return listRouleau;
		    
		}
		
		
		
		
		
		
		
	
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
