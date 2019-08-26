package com.gpro.consulting.tiers.gpao.persitance.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gpao.coordination.value.StatutOfValue;
import com.gpro.consulting.tiers.gpao.persitance.IStatutPersistance;
import com.gpro.consulting.tiers.gpao.persitance.entite.StatutOfEntite;
import com.gpro.consulting.tiers.gpao.persitance.utilities.PersistanceUtilities;

/**
 * @author $Ameni
 *
 */

@Component
public class StatutPersistanceImpl extends AbstractPersistance implements IStatutPersistance {

	private static final Logger logger = LoggerFactory.getLogger(StatutPersistanceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public String creerStatut(StatutOfValue pStatutOfValue) {
		if (log.isDebugEnabled()) {
			//log.debug("creation d'un etat de Commande de Vente avec la designation : "
			//		+ pStatutOfValue.getDesignation());
		}
		StatutOfEntite vCommandeVenteEntite = (StatutOfEntite) this
				.creer(PersistanceUtilities.toEntity(pStatutOfValue));
		StatutOfValue vStatutOfValueResult = PersistanceUtilities
				.toValue(vCommandeVenteEntite);
		return vStatutOfValueResult.getId().toString();
	}

	@Override
	public void supprimerStatut(Long pId) {
		if (log.isDebugEnabled()) {
			//log.debug("Suppression  d'un etat de Commande de Vente avec l'ID : "
				//	+ pId.longValue());
		}
		this.supprimerEntite(StatutOfEntite.class, pId);
	}

	@Override
	public String modifierStatut(StatutOfValue pStatutOfValue) {
		if (log.isDebugEnabled()) {
			//log.debug("Modification d'un etat de Commande de Vente avec la designation : "
			//		+ pStatutOfValue.getDesignation());
		}
		StatutOfEntite vStatutOfEntite = (StatutOfEntite) this
				.modifier(PersistanceUtilities.toEntity(pStatutOfValue));
		StatutOfValue vStatutOfValueResult = PersistanceUtilities
				.toValue(vStatutOfEntite);
		return vStatutOfValueResult.getId().toString();
	}

	@Override
	public StatutOfValue rechercheStatutParId(Long pId) {
		if (log.isDebugEnabled()) {
			//log.debug("recherche d'un etat de Commande de Vente avec l'id : "
				//	+ pId);
		}
		StatutOfEntite vStatutOfEntite = this.rechercherParId(pId,
				StatutOfEntite.class);
		StatutOfValue vStatutOfValueResult = PersistanceUtilities
				.toValue(vStatutOfEntite);
		return vStatutOfValueResult;
	}

	@Override
	public List<StatutOfValue> listeStatut() {
		List<StatutOfEntite> vListeStatutOfEntite = this
				.lister(StatutOfEntite.class);
		List<StatutOfValue> vListeStatutOfValues = new ArrayList<StatutOfValue>();
		for (StatutOfEntite vStatutOfEntite : vListeStatutOfEntite) {
			vListeStatutOfValues.add(PersistanceUtilities
					.toValue(vStatutOfEntite));
		}
		//LOGGER.info("Size listStatut " + vListeStatutOfValues.size());
		return vListeStatutOfValues;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
