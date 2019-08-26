package com.gpro.consulting.tiers.commun.persistance.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.PhaseValue;
import com.gpro.consulting.tiers.commun.persistance.IPhasePersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.PhaseEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

public class PhasePersistanceImpl extends AbstractPersistance 
								  implements IPhasePersistance{

	/** EntityManager. **/
	@PersistenceContext
	private EntityManager entityManager; 
	
	/** Logger*/
	private static Logger log = Logger.getLogger(MatierePersistanceImpl.class);
	
	/************************** Creation Phase *****************************/
	@Override
	public String creerPhase(PhaseValue pPhaseValue) {
		PhaseEntite pPhaseEntite=(PhaseEntite) this.creer(PersistanceUtilities.toEntity(pPhaseValue));
		PhaseValue pPhaseValueResult=PersistanceUtilities.toValue(pPhaseEntite);
	    return pPhaseValueResult.getId().toString();
	}
	
	/************************** Suppression Phase *****************************/
	@Override
	public void supprimerPhase(PhaseValue pPhaseValue) {
		if (log.isDebugEnabled()) {
			log.debug(" ** Suppression du Phase de l'ID=" + pPhaseValue.getId().toString() + " est en cours.");
		}
		this.supprimerEntite(PhaseEntite.class,pPhaseValue.getId());
	}
	
	/************************ Modification Phase ******************************/
	@Override
	public String modifierPhase(PhaseValue pPhaseValue) {
		if (log.isDebugEnabled()) {
			log.debug(" ** Modification du Phase de l'ID=" + pPhaseValue.getId().toString() + " est en cours.");
		}
		PhaseEntite pPhaseEntite=(PhaseEntite) this.modifier(PersistanceUtilities.toEntity(pPhaseValue));
		PhaseValue pPhaseValueResult=PersistanceUtilities.toValue(pPhaseEntite);
	return pPhaseValueResult.getId().toString();
	}
	
	/************************ Recherche Phase ******************************/
	@Override
	public PhaseValue recherchePhaseParId(PhaseValue pPhaseValue) {
		if (log.isDebugEnabled()) {
			log.debug(" ** Recherche du Phase de l'ID=" + pPhaseValue.getId().toString() + " est en cours.");
		}
		PhaseEntite pPhaseEntite=(PhaseEntite) this.rechercherParId(pPhaseValue.getId().longValue(), PhaseEntite.class);
		PhaseValue pPhaseValueResult=PersistanceUtilities.toValue(pPhaseEntite);
	return pPhaseValueResult;
	
	}
	
	/******************************* Liste Phase **********************************/
	@Override
	public List<PhaseValue> listePhase() {
		List < PhaseEntite > vListePhaseEntite = this.lister(PhaseEntite.class);
		List < PhaseValue > vListPhaseValues = new ArrayList < PhaseValue >();
			for (PhaseEntite vPhaseEntite : vListePhaseEntite) {
				vListPhaseValues.add(PersistanceUtilities.toValue(vPhaseEntite));
			}
	return vListPhaseValues;
	}
	
	/***************************** Getter & Setter ********************************/
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
