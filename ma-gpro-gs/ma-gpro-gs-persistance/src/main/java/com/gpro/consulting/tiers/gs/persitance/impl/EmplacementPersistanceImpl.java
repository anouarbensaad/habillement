package com.gpro.consulting.tiers.gs.persitance.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gs.persitance.utilities.PersistanceUtilities;
import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gs.coordination.value.EmplacementValue;
import com.gpro.consulting.tiers.gs.persitance.IEmplacementPersistance;
import com.gpro.consulting.tiers.gs.persitance.entite.EmplacementEntite;
// TODO: Auto-generated Javadoc

/**
 * The Class EmplacementPersistanceImpl.
 */

@Component
public class EmplacementPersistanceImpl extends AbstractPersistance implements IEmplacementPersistance{

	/** EntityManager. */
	  @PersistenceContext
	  private EntityManager entityManager;
	  
	  /** The Constant LOGGER. */
  	private static final Logger LOGGER = LoggerFactory.getLogger(EmplacementPersistanceImpl.class);

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IEmplacementPersistance#creerEmplacement(com.gpro.consulting.tiers.gs.coordination.value.EmplacementValue)
	 */
	@Override
	public String creerEmplacement(EmplacementValue pEmplacementValue) {
		 EmplacementEntite vEmplacementEntite = (EmplacementEntite) this.creer(PersistanceUtilities.toEntity(pEmplacementValue));
		 LOGGER.info(" designation "+pEmplacementValue.getDesignation());
			    return vEmplacementEntite.getId().toString();
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IEmplacementPersistance#supprimerEmplacement(java.lang.Long)
	 */
	@Override
	public void supprimerEmplacement(Long pId) {
		this.supprimerEntite(EmplacementEntite.class, pId.longValue());		
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IEmplacementPersistance#modifierEmplacement(com.gpro.consulting.tiers.gs.coordination.value.EmplacementValue)
	 */
	@Override
	public String modifierEmplacement(EmplacementValue pEmplacementValue) {
		 EmplacementEntite vEmplacementEntite = (EmplacementEntite) this.modifier(PersistanceUtilities.toEntity(pEmplacementValue));
		 LOGGER.info(" id "+pEmplacementValue.getId());

		    return vEmplacementEntite.getId().toString();
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IEmplacementPersistance#rechercheEmplacementValueParId(com.gpro.consulting.tiers.gs.coordination.value.EmplacementValue)
	 */
	@Override
	public EmplacementValue rechercheEmplacementValueParId(
			EmplacementValue pEmplacementValue) {
		EmplacementEntite vEmplacementEntite = this.rechercherParId(pEmplacementValue.getId().longValue(),
			    EmplacementEntite.class);
		EmplacementValue vEmplacementValueResult = PersistanceUtilities.toValue(vEmplacementEntite);
	 return vEmplacementValueResult;
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IEmplacementPersistance#listeEmplacementValue()
	 */
	@Override
	public List<EmplacementValue> listeEmplacementValue() {
		 List < EmplacementEntite > vListEmplacementEntite = this.lister(EmplacementEntite.class);
		    List < EmplacementValue > vlistEmplacementValue = new ArrayList < EmplacementValue >();
		    for (EmplacementEntite vEmplacementEntite : vListEmplacementEntite) {
		    	vlistEmplacementValue.add(PersistanceUtilities.toValue(vEmplacementEntite));
		    }
		    return vlistEmplacementValue;
	}

	/* (non-Javadoc)
	 * @see com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance#getEntityManager()
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
