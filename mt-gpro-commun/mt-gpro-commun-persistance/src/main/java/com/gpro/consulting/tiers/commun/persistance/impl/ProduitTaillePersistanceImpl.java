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
import com.gpro.consulting.tiers.commun.coordination.value.ProduitTailleValue;
import com.gpro.consulting.tiers.commun.persistance.IProduitTaillePersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.ProduitTailleEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

public class ProduitTaillePersistanceImpl  extends AbstractPersistance implements IProduitTaillePersistance{

	/** Logger*/
	private static Logger LOGGER = Logger.getLogger(FicheBesoinPersistanceImpl.class);

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public EntityManager getEntityManager() {
		return getEntityManager();
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	

	@Override
	public List < ProduitTailleValue > ListeProduitTaille(Long pIdProduit){
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery < ProduitTailleEntite > vCriteriaQuery = vBuilder.createQuery(ProduitTailleEntite.class);
		List < Predicate > vWhereClause = new ArrayList < Predicate >();
		Root < ProduitTailleEntite > vRootProduitTaille = vCriteriaQuery.from(ProduitTailleEntite.class);
		Join < ProduitTailleEntite, ProduitEntity> jointureTailleProd = vRootProduitTaille.join("produit");
		vWhereClause.add(vBuilder.equal(jointureTailleProd.get("id"), pIdProduit));
	    vCriteriaQuery.select(vRootProduitTaille).where(vWhereClause.toArray(new Predicate[] {}));
	    List < ProduitTailleEntite > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();
		
	    List<ProduitTailleValue> vListeResultat = new ArrayList < ProduitTailleValue >();
	    for (ProduitTailleEntite vItemEntite : resultatEntite) {
	    	ProduitTailleValue vProduitTailleValue = PersistanceUtilities.toValue(vItemEntite);
	        vListeResultat.add(vProduitTailleValue);
	      }
	    
	    return vListeResultat;

	}

	
	@Override
	public ProduitTailleValue getById(Long id) {
		
		ProduitTailleEntite entity =(ProduitTailleEntite) this.rechercherParId(id, ProduitTailleEntite.class);
		
		return PersistanceUtilities.toValue(entity);
	}
	
	
	
	
	

}
