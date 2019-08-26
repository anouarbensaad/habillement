package com.gpro.consulting.tiers.gpao.persitance.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gpao.coordination.value.ChaineValue;
import com.gpro.consulting.tiers.gpao.persitance.IChainePersistance;
import com.gpro.consulting.tiers.gpao.persitance.entite.ChaineEntite;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.entity.OperationEntity;
import com.gpro.consulting.tiers.gpao.persitance.utilities.PersistanceUtilities;

/**
 * @author $Ameni
 *
 */

@Component
public class ChainePersistanceImpl extends AbstractPersistance  implements IChainePersistance {

	private static final Logger logger = LoggerFactory.getLogger(ChainePersistanceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	private String PREDICATE_ID = "id";
	
	@Override
	public String creerChaine(ChaineValue pChaineValue) {
		if(log.isDebugEnabled()){
			//log.debug("creation d'un etat de Commande de Vente avec la designation : " + pChaineValue.getDesignation());
		}
		ChaineEntite vCommandeVenteEntite= (ChaineEntite) this.creer(PersistanceUtilities.toEntity(pChaineValue));
		ChaineValue vChaineValueResult=PersistanceUtilities.toValue(vCommandeVenteEntite);
		return vChaineValueResult.getId().toString();
	}

	@Override
	public void supprimerChaine(Long pId) {
		if (log.isDebugEnabled()) {
			//log.debug("Suppression  d'un etat de Commande de Vente avec l'ID : " + pId.longValue());
           }
          this.supprimerEntite(ChaineEntite.class, pId);			
	}

	@Override
	public String modifierChaine(ChaineValue pChaineValue) {
		if(log.isDebugEnabled()){
			//log.debug("Modification d'un etat de Commande de Vente avec la designation : " + pChaineValue.getDesignation());
		}
		ChaineEntite vChaineEntite= (ChaineEntite) this.modifier(PersistanceUtilities.toEntity(pChaineValue));
		ChaineValue vChaineValueResult=PersistanceUtilities.toValue(vChaineEntite);
		return vChaineValueResult.getId().toString();
	}

	@Override
	public ChaineValue rechercheChaineParId(Long pId) {
		if(log.isDebugEnabled()){
			//log.debug("recherche d'un etat de Commande de Vente avec l'id : " +pId );
		}
		ChaineEntite vChaineEntite=  this.rechercherParId(pId,ChaineEntite.class);
		ChaineValue vChaineValueResult=PersistanceUtilities.toValue(vChaineEntite);
		return vChaineValueResult;
	}

	@Override
	public List<ChaineValue> listeChaine() {
		List<ChaineEntite> vListeChaineEntite = this.lister(ChaineEntite.class);
		List < ChaineValue > vListeChaineValues = new ArrayList < ChaineValue >();
		for (ChaineEntite vChaineEntite : vListeChaineEntite) {
			 vListeChaineValues.add(PersistanceUtilities.toValue(vChaineEntite));
	 	    }
		//LOGGER.info("Size listchaine "+vListeChaineValues.size());
		
		return vListeChaineValues;
	}


	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	/**
	 * @author Zeineb Medimagh
	 * @since 29/11/2016
	 */
	@Override
	public String rechercheDesignationChaineParId(Long pId) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<ChaineEntite> root = criteriaQuery.from(ChaineEntite.class);
		
		// Set request.code on whereClause if not empty or null
		if (pId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_ID), pId));
		}
		
		criteriaQuery.select(root.get("designation").as(String.class)).where(whereClause.toArray(new Predicate[] {}));
	    String designation = this.entityManager.createQuery(criteriaQuery).getSingleResult();

		return designation;
	}
	
	
}

