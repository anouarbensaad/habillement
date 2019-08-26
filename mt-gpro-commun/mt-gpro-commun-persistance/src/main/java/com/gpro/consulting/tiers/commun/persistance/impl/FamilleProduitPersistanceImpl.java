package com.gpro.consulting.tiers.commun.persistance.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.FamilleProduitValue;
import com.gpro.consulting.tiers.commun.persistance.IFamilleProduitPersitance;
import com.gpro.consulting.tiers.commun.persistance.entity.FamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * The Class FamilleProduitPersistanceImpl.
 * @author med
 */
public class FamilleProduitPersistanceImpl extends AbstractPersistance implements IFamilleProduitPersitance{

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	/*
	 * creer famille prroduit
	 */
	@Override
	public String creerFamilleProduit(FamilleProduitValue pFamilleProduitValue) {
		 FamilleProduitEntity vFamilletity= (FamilleProduitEntity) this.creer(PersistanceUtilities.toEntity(pFamilleProduitValue));
		 FamilleProduitValue vFamilleValueResult=PersistanceUtilities.toValue(vFamilletity);
		 return vFamilleValueResult.getId().toString();	
	}

	/* 
	 * supprimer
	 */
	@Override
	public void supprimerSousFamilleProduit(Long pFamilleProduitId) {
		this.supprimerEntite(FamilleProduitEntity.class, pFamilleProduitId);		
	}

	/* 
	 * modifier
	 */
	@Override
	public String modifierFamilleProduit(
			FamilleProduitValue pFamilleProduitValue) {
		 FamilleProduitEntity vFamilletity= (FamilleProduitEntity) this.modifier(PersistanceUtilities.toEntity(pFamilleProduitValue));
		 FamilleProduitValue vFamilleValueResult=PersistanceUtilities.toValue(vFamilletity);
		 return vFamilleValueResult.getId().toString();	
	}

	/* (non-Javadoc)
	 * recherche by id
	 */
	@Override
	public FamilleProduitValue rechercheFamilleProduitById(
			Long pFamilleProduitId) {
		 FamilleProduitEntity vFamilletity= (FamilleProduitEntity) this.rechercherParId(pFamilleProduitId, FamilleProduitEntity.class);
		 FamilleProduitValue vFamilleValueResult=PersistanceUtilities.toValue(vFamilletity);
		 return vFamilleValueResult;
	}

	/* liste famille
	 */
	@Override
	public List<FamilleProduitValue> listeFamilleProduit() {
		 java.util.List<FamilleProduitEntity> vFamillesEntity = this.lister(FamilleProduitEntity.class);
		  List <FamilleProduitValue> ListFamille= PersistanceUtilities.tolistFamilleProduitValues(vFamillesEntity);
		  return ListFamille;
	}

	/* (non-Javadoc)
	 */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Sets the entity manager.
	 *
	 * @param entityManager the new entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
