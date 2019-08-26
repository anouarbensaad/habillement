package com.gpro.consulting.tiers.commun.persistance.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.StandardTailleValue;
import com.gpro.consulting.tiers.commun.persistance.IStandardTaillePersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.StandardTailleEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

public class StandardTaillePersistanceImpl extends AbstractPersistance 
										   implements IStandardTaillePersistance{

	/** EntityManager. **/
	@PersistenceContext
	private EntityManager entityManager; 
	
	/** Logger*/
	private static Logger log = Logger.getLogger(MatierePersistanceImpl.class);
	
	/************************** Creation StandardTaille *****************************/
	@Override
	public String creerStandardTaille(StandardTailleValue pStandardTailleValue) {
		if (log.isDebugEnabled()) {
			log.debug(" ** Creation du StandardTaille " + pStandardTailleValue.getDesignation() + " est en cours.");
		}	
	StandardTailleEntite pStandardTailleEntite=(StandardTailleEntite) this.creer(PersistanceUtilities.toEntity(pStandardTailleValue));
	StandardTailleValue pStandardTailleValueResult=PersistanceUtilities.toValue(pStandardTailleEntite);
	return pStandardTailleValueResult.getId().toString();
	}
	
	/************************** Suppression StandardTaille *****************************/
	@Override
	public void supprimerStandardTaille(Long pId) {
		if (log.isDebugEnabled()) {
			log.debug(" ** Suppression du StandardTaille de l'ID=" + pId.longValue() + " est en cours.");
		}
	this.supprimerEntite(StandardTailleEntite.class,pId.longValue());
	}
	
	/************************ Modification StandardTaille ******************************/
	@Override
	public String modifierStandardTaille(StandardTailleValue pStandardTailleValue) {
		if (log.isDebugEnabled()) {
			log.debug(" ** Modification du StandardTaille de l'ID=" + pStandardTailleValue.getId().toString() + " est en cours.");
		}
	StandardTailleEntite pStandardTailleEntite=(StandardTailleEntite) this.modifier(PersistanceUtilities.toEntity(pStandardTailleValue));
	StandardTailleValue pStandardTailleValueResult=PersistanceUtilities.toValue(pStandardTailleEntite);
	return pStandardTailleValueResult.getId().toString();
	}
	
	/************************ Recherche StandardTaille ******************************/
	@Override
	public StandardTailleValue rechercheStandardTailleParId(StandardTailleValue pStandardTailleValue) {
		if (log.isDebugEnabled()) {
			log.debug(" ** Recherche du StandardTaille de l'ID=" + pStandardTailleValue.getId().toString() + " est en cours.");
		}
	StandardTailleEntite pStandardTailleEntite=(StandardTailleEntite) this.rechercherParId(pStandardTailleValue.getId().longValue(), StandardTailleEntite.class);
	StandardTailleValue pStandardTailleValueResult=PersistanceUtilities.toValue(pStandardTailleEntite);
	return pStandardTailleValueResult;
	
	}
	
	/************************ Liste StandardTaille ******************************/
	@Override
	public List<StandardTailleValue> listeStandardTaille() {
	List < StandardTailleEntite > vListeStandardTailleEntite = this.lister(StandardTailleEntite.class);
	List < StandardTailleValue > vListStandardTailleValues = new ArrayList < StandardTailleValue >();
		for (StandardTailleEntite vStandardTailleEntite : vListeStandardTailleEntite) {
			vListStandardTailleValues.add(PersistanceUtilities.toValue(vStandardTailleEntite));
		}
	   return vListStandardTailleValues;
	}
	
	
	@Override
	public StandardTailleValue rechercheStandardTailleParId(Long pId) {
		if (log.isDebugEnabled()) {
			log.debug(" ** Recherche du StandardTaille de l'ID=" + pId + " est en cours.");
		}
	StandardTailleEntite pStandardTailleEntite=(StandardTailleEntite) this.rechercherParId(pId, StandardTailleEntite.class);
	StandardTailleValue pStandardTailleValueResult=PersistanceUtilities.toValue(pStandardTailleEntite);
	return pStandardTailleValueResult;
	
	}
	
	
	
	
	
	/***************************** Getter & Setter ********************************/
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}

