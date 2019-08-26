package com.gpro.consulting.tiers.commun.persistance.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitCouleurValue;
import com.gpro.consulting.tiers.commun.persistance.IProduitCouleurPersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.ProduitCouleurEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

public class ProduitCouleurPersistanceImpl extends AbstractPersistance implements
		IProduitCouleurPersistance {
	
	/** Logger*/
	private static Logger LOGGER = Logger.getLogger(FicheBesoinPersistanceImpl.class);
	

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	

	@Override
	public List < ProduitCouleurValue > ListeProduitCouleur(Long pIdProduit) {
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery < ProduitCouleurEntite > vCriteriaQuery = vBuilder.createQuery(ProduitCouleurEntite.class);
		List < Predicate > vWhereClause = new ArrayList < Predicate >();
		Root < ProduitCouleurEntite > vRootProduitCouleur = vCriteriaQuery.from(ProduitCouleurEntite.class);
		Join < ProduitCouleurEntite, ProduitEntity> jointureCouleurProd = vRootProduitCouleur.join("produit");
		vWhereClause.add(vBuilder.equal(jointureCouleurProd.get("id"), pIdProduit));
	    vCriteriaQuery.select(vRootProduitCouleur).where(vWhereClause.toArray(new Predicate[] {}));
	    List < ProduitCouleurEntite > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();
		
	    List < ProduitCouleurValue > vListeResultat = new ArrayList < ProduitCouleurValue >();
	    for (ProduitCouleurEntite vItemEntite : resultatEntite) {
	    	ProduitCouleurValue vProduitCouleurValue = PersistanceUtilities.toValue(vItemEntite);
	        vListeResultat.add(vProduitCouleurValue);
	      }
	    
	    return vListeResultat;

	}

	
	
	
	
	
	
	
	
	
	
	
	
}
