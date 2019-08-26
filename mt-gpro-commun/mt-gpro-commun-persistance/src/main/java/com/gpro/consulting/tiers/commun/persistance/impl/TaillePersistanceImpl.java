package com.gpro.consulting.tiers.commun.persistance.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.persistance.ITaillePersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.FamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.SousFamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.TailleEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

public class TaillePersistanceImpl extends AbstractPersistance 
								   implements ITaillePersistance{
	
	private String PREDICATE_ID = "id";

	/** EntityManager. **/
	@PersistenceContext
	private EntityManager entityManager; 
	
	/** Logger*/
	private static Logger log = Logger.getLogger(MatierePersistanceImpl.class);
	
	/************************** Creation Taille *****************************/
	@Override
	public String creerTaille(TailleValue pTailleValue) {
		if (log.isDebugEnabled()) {
			//log.debug(" ** Creation du Taille " + pTailleValue.getDesignation() + " est en cours.");
		}
	TailleEntite pTailleEntite=(TailleEntite) this.creer(PersistanceUtilities.toEntity(pTailleValue));
	TailleValue pTailleValueResult=PersistanceUtilities.toValue(pTailleEntite);
	return pTailleValueResult.getId().toString();
	}
	
	/************************** Suppression Taille *****************************/
	@Override
	public void supprimerTaille(Long pId) {
		if (log.isDebugEnabled()) {
			//log.debug(" ** Suppression du Taille de l'ID=" + pId.longValue() + " est en cours.");
		}
	this.supprimerEntite(TailleEntite.class,pId.longValue());
	}
	
	/************************ Modification Taille ******************************/
	@Override
	public String modifierTaille(TailleValue pTailleValue) {
		if (log.isDebugEnabled()) {
			//log.debug(" ** Modification du Taille de l'ID=" + pTailleValue.getId().toString() + " est en cours.");
		}
	TailleEntite pTailleEntite=(TailleEntite) this.modifier(PersistanceUtilities.toEntity(pTailleValue));
	TailleValue pTailleValueResult=PersistanceUtilities.toValue(pTailleEntite);
	return pTailleValueResult.getId().toString();
	}
	
	/************************ Recherche Taille ******************************/
	@Override
	public TailleValue rechercheTailleParId(TailleValue pTailleValue) {
		if (log.isDebugEnabled()) {
			//log.debug(" ** Recherche du Taille de l'ID=" + pTailleValue.getId().toString() + " est en cours.");
		}
	TailleEntite pTailleEntite=(TailleEntite) this.rechercherParId(pTailleValue.getId().longValue(), TailleEntite.class);
	TailleValue pTailleValueResult=PersistanceUtilities.toValue(pTailleEntite);
	return pTailleValueResult;
	
	}
	
	/************************ Liste Taille ******************************/
	@Override
	public List<TailleValue> listeTaille() {
		List < TailleEntite > vListeTailleEntite = this.lister(TailleEntite.class);
		List < TailleValue > vListTailleValues = new ArrayList < TailleValue >();
			for (TailleEntite vTailleEntite : vListeTailleEntite) {
				vListTailleValues.add(PersistanceUtilities.toValue(vTailleEntite));
			}
			
			Collections.sort(vListTailleValues);
		
	return vListTailleValues;
	}
	
	/***************************** Getter & Setter ********************************/
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public TailleValue getById(Long id) {
		TailleEntite entity =(TailleEntite) this.rechercherParId(id, TailleEntite.class);
		return  PersistanceUtilities.toValue(entity);
	}
	
	@Override
	public List<TailleValue> getAllInList(List<Long> ids) {
		
		List<TailleValue> result = new ArrayList<TailleValue>();
	    
	    if(ids.size() > 0){
	    	
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		    CriteriaQuery<TailleEntite> criteriaQuery = criteriaBuilder.createQuery(TailleEntite.class);
		    List<Predicate> whereClause = new ArrayList<Predicate>();
		    Root<TailleEntite> root = criteriaQuery.from(TailleEntite.class);
	    	
	    	whereClause.add(root.get(PREDICATE_ID).in(ids));
	    	
		    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    
		    List<TailleEntite> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    for (TailleEntite entity : entityListResult) {
		    	TailleValue value = PersistanceUtilities.toValue(entity);
		    	result.add(value);
		    }
		    
	    }
	    Collections.sort(result);
	    
	    return result;
	}
	@Override
	public List < TailleValue > rechercherTailleByStandard(Long pIdStandard) {
		  CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < TailleEntite > vCriteriaQuery = vBuilder.createQuery(TailleEntite.class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();

		    /** create liste resultat ***/

		    /************ entity jointure *****************/
		    Root < TailleEntite > vRootTaille = vCriteriaQuery.from(TailleEntite.class);

		    /***************** Predicate *************/
		    if (pIdStandard!=null) {
		      vWhereClause.add(vBuilder.equal(vRootTaille.get("eb_sttaille_id"),
		    		  pIdStandard));
		    }
		   		    
		  
		    /** execute query and do something with result **/

		    vCriteriaQuery.select(vRootTaille).where(vWhereClause.toArray(new Predicate[] {}));
		    
		    vCriteriaQuery.orderBy(vBuilder.desc(vRootTaille.get("ordre")));
		    
		    List<TailleEntite > resultatEntite = null;
		   
				resultatEntite = this.entityManager
						.createQuery(vCriteriaQuery)
						.getResultList();
			
		    

		    /** Conversion de la liste en valeur */
		    List < TailleValue > vListeResultat = new ArrayList < TailleValue >();

		    for (TailleEntite vTailleEntite : resultatEntite) {
		    	
		    	TailleValue taille = PersistanceUtilities.toValue(vTailleEntite);
		    	vListeResultat.add(taille);
		    }

		   
		    Collections.sort(vListeResultat);
		   return vListeResultat;

	}
}
