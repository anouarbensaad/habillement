package com.gpro.consulting.tiers.gs.persitance.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.SiteEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.TypeArticleEntity;
import com.gpro.consulting.tiers.gs.coordination.value.MagasinValue;
import com.gpro.consulting.tiers.gs.persitance.IMagasinPersistance;
import com.gpro.consulting.tiers.gs.persitance.entite.EmplacementEntite;
import com.gpro.consulting.tiers.gs.persitance.entite.MagasinEntite;
import com.gpro.consulting.tiers.gs.persitance.utilities.PersistanceUtilities;

// TODO: Auto-generated Javadoc
/**
 * The Class MagasinPersistanceImpl.
 */

@Component
public class MagasinPersistanceImpl extends AbstractPersistance implements IMagasinPersistance{

	/** EntityManager. */
	  @PersistenceContext
	  private EntityManager entityManager;
	  
	  /** The Constant LOGGER. */
  	private static final Logger LOGGER = LoggerFactory.getLogger(EmplacementEntite.class);

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IMagasinPersistance#creerMagasin(com.gpro.consulting.tiers.gs.coordination.value.MagasinValue)
	 */
	@Override
	public String creerMagasin(MagasinValue pMagasinValue) {
		SiteEntite vSiteEntie=new SiteEntite();
		if(pMagasinValue.getPiComSiteId()!=null){
			   vSiteEntie=this.rechercherParId(pMagasinValue.getPiComSiteId(), SiteEntite.class);
		}
		 MagasinEntite vMagasinEntite = (MagasinEntite) this.creer(PersistanceUtilities.toEntity(pMagasinValue,vSiteEntie));
		    if (LOGGER.isDebugEnabled()) {
		      LOGGER.debug("Emplacement  d'ID=" + vMagasinEntite.getId().toString() + " est en cours.");
		    }
		    return vMagasinEntite.getId().toString();
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IMagasinPersistance#supprimerMagasin(java.lang.Long)
	 */
	@Override
	public void supprimerMagasin(Long pId) {
		this.supprimerEntite(MagasinEntite.class, pId.longValue());	
		
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IMagasinPersistance#modifierMagasin(com.gpro.consulting.tiers.gs.coordination.value.MagasinValue)
	 */
	@Override
	public String modifierMagasin(MagasinValue pMagasinValue) {
		SiteEntite vSiteEntie=new SiteEntite();
		if(pMagasinValue.getPiComSiteId()!=null){
			   vSiteEntie=this.rechercherParId(pMagasinValue.getPiComSiteId(), SiteEntite.class);
		}
		MagasinEntite vMagasinEntite = (MagasinEntite) this.modifier(PersistanceUtilities.toEntity(pMagasinValue,vSiteEntie));
		    if (LOGGER.isDebugEnabled()) {
		      LOGGER.debug("Emplacement  d'ID=" + vMagasinEntite.getId().toString() + " est en cours.");
		    }
		    return vMagasinEntite.getId().toString();
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IMagasinPersistance#rechercheMagasinParId(com.gpro.consulting.tiers.gs.coordination.value.MagasinValue)
	 */
	@Override
	public MagasinValue rechercheMagasinParId(MagasinValue pMagasinValue) {
		MagasinEntite vMagasinEntite = this.rechercherParId(pMagasinValue.getId().longValue(),
			    MagasinEntite.class);
		MagasinValue vMagasinValueResult = PersistanceUtilities.toValue(vMagasinEntite);
	 return vMagasinValueResult;
	}

	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.persitance.IMagasinPersistance#listeMagasin()
	 */
	@Override
	public List<MagasinValue> listeMagasin() {
		List < MagasinEntite > vListMagasinEntite = this.lister(MagasinEntite.class);
	    List < MagasinValue > vListMagasinValue = new ArrayList < MagasinValue >();
	    for (MagasinEntite vMagasinEntite : vListMagasinEntite) {
	    	vListMagasinValue.add(PersistanceUtilities.toValue(vMagasinEntite));
	    }
	    return vListMagasinValue;
	}

	/* (non-Javadoc)
	 * @see com.erp.socle.j2ee.mt.commun.persistance.AbstractPersistance#getEntityManager()
	 */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}


}
