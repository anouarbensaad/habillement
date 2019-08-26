package com.gpro.consulting.tiers.commun.persistance.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.persistance.ICouleurPersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.CouleurEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

public class CouleurPersistanceImpl extends AbstractPersistance implements ICouleurPersistance{
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ArticlePersistanceImpl.class);
	
	private String PREDICATE_ID = "id";

	@PersistenceContext
	private EntityManager entityManager; 
	
	/************************** Creation Couleur *****************************/
	@Override
	public String creerCouleur(CouleurValue pCouleurValue) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(" ** Creation du Couleur " + pCouleurValue.getDesignation() + " est en cours.");
		}
		CouleurEntite pCouleurEntite=(CouleurEntite) this.creer(PersistanceUtilities.toEntity(pCouleurValue));
		CouleurValue pCouleurValueResult=PersistanceUtilities.toValue(pCouleurEntite);
	return pCouleurValueResult.getId().toString();
	}
	
	/************************** Suppression Couleur *****************************/
	@Override
	public void supprimerCouleur(Long pId) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(" ** Suppression du Couleur de l'ID=" + pId.longValue() + " est en cours.");
		}
		this.supprimerEntite(CouleurEntite.class, pId.longValue());
	}
	
	/************************ Modification Couleur ******************************/
	@Override
	public String modifierCouleur(CouleurValue pCouleurValue) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(" ** Modification du Couleur de l'ID=" + pCouleurValue.getId().toString() + " est en cours.");
		}
		CouleurEntite pCouleurEntite=(CouleurEntite) this.modifier(PersistanceUtilities.toEntity(pCouleurValue));
		CouleurValue pCouleurValueResult=PersistanceUtilities.toValue(pCouleurEntite);
	return pCouleurValueResult.getId().toString();
	}
	
	/************************ Recherche Couleur ******************************/
	@Override
	public CouleurValue rechercheCouleurParId(CouleurValue pCouleurValue) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(" ** Recherche du Couleur de l'ID=" + pCouleurValue.getId().toString() + " est en cours.");
		}
		CouleurEntite pCouleurEntite=(CouleurEntite) this.rechercherParId(pCouleurValue.getId().longValue(), CouleurEntite.class);
		CouleurValue pCouleurValueResult=PersistanceUtilities.toValue(pCouleurEntite);
	return pCouleurValueResult;
	
	}
	
	/************************ Liste Couleur ******************************/
	
	@Override
	public List<CouleurValue> listeCouleur() {
		List < CouleurEntite > vListeCouleurEntite = this.lister(CouleurEntite.class);
		List < CouleurValue > vListCouleurValues = new ArrayList < CouleurValue >();
			for (CouleurEntite vCouleurEntite : vListeCouleurEntite) {
			vListCouleurValues.add(PersistanceUtilities.toValue(vCouleurEntite));
			}
	return vListCouleurValues;
	}
	
	/***************************** Getter & Setter ********************************/
	public EntityManager getEntityManager() {
	return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
	this.entityManager = entityManager;
	}

	@Override
	public List<CouleurValue> getAllInList(List<Long> ids) {
		
		List<CouleurValue> result = new ArrayList<CouleurValue>();
		
	    if(ids.size() > 0){
	    	
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		    CriteriaQuery<CouleurEntite> criteriaQuery = criteriaBuilder.createQuery(CouleurEntite.class);
		    List<Predicate> whereClause = new ArrayList<Predicate>();
		    Root<CouleurEntite> root = criteriaQuery.from(CouleurEntite.class);
	    	
	    	whereClause.add(root.get(PREDICATE_ID).in(ids));
	    	
		    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    
		    List<CouleurEntite> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

		    for (CouleurEntite entity : entityListResult) {
		    	CouleurValue value = PersistanceUtilities.toValue(entity);
		    	result.add(value);
		    }
	    	
	    }
	    
	    return result;
	}

	@Override
	public CouleurValue getById(Long id) {
		CouleurEntite entity =(CouleurEntite) this.rechercherParId(id, CouleurEntite.class);
		return  PersistanceUtilities.toValue(entity);
	}

	

}
