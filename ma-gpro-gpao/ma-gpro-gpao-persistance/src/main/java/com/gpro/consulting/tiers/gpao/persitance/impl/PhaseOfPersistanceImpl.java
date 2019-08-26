package com.gpro.consulting.tiers.gpao.persitance.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gpao.coordination.value.PhaseOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticriterePhaseOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticriterePhaseOfValue;
import com.gpro.consulting.tiers.gpao.persitance.IPhaseOfPersistance;
import com.gpro.consulting.tiers.gpao.persitance.entite.OrdreFabricationEntite;
import com.gpro.consulting.tiers.gpao.persitance.entite.PhaseOfEntite;
import com.gpro.consulting.tiers.gpao.persitance.utilities.PersistanceUtilities;

@Component
public class PhaseOfPersistanceImpl extends AbstractPersistance implements IPhaseOfPersistance {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PhaseOfPersistanceImpl.class);
	
	private String ordre = "ordre";

	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public String modifierPhaseOf(PhaseOfValue pPhaseOfValue) {

		if (LOGGER.isDebugEnabled()) {
			//logger.debug("Modification de la phaseOf  d'ID="
			//		+ pPhaseOfValue.getId().toString() + " est en cours.");
		}
		PhaseOfEntite vPhaseOfEntite = (PhaseOfEntite) this
				.modifier(PersistanceUtilities.toEntiteSuivi(pPhaseOfValue));
		return vPhaseOfEntite.getId().toString();
	}

	/**
	 * * Recherche de Phase Of by Id
	 */
	@Override
	public PhaseOfValue recherchePhaseOfParId(PhaseOfValue pPhaseOfValue) {
		if (LOGGER.isDebugEnabled()) {
			//logger.debug("Modification de l'l'employee  d'ID="
				//	+ pPhaseOfValue.getId().toString() + " est en cours.");
		}
		PhaseOfEntite pPhaseOfEntite = (PhaseOfEntite) this.rechercherParId(
				pPhaseOfValue.getId().longValue(), PhaseOfEntite.class);
		PhaseOfValue vPhaseOfValueResult = PersistanceUtilities
				.toValueSuivi(pPhaseOfEntite);
		return vPhaseOfValueResult;
	}

	/**
	 * *****************************Liste phase
	 * of***********************************************
	 */

	@Override
	public List<PhaseOfValue> listePhaseOfValue() {
		List<PhaseOfEntite> vListPhaseOfEntite = this
				.lister(PhaseOfEntite.class);
		List<PhaseOfValue> vListPhaseOfValue = new ArrayList<PhaseOfValue>();
		for (PhaseOfEntite vPhaseOfEntite : vListPhaseOfEntite) {
			vListPhaseOfValue.add(PersistanceUtilities
					.toValueSuivi(vPhaseOfEntite));
		}
		return vListPhaseOfValue;
	}

	/**
	 * *****************************Recherche
	 * Multicritere***********************************************
	 */

	@Override
	public ResultatMulticriterePhaseOfValue rechercherPhaseOfMultiCritere(
			RechercheMulticriterePhaseOfValue pRecherchePhaseOfValueMulticritere) {

		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		List<Predicate> vWhereClause = new ArrayList<Predicate>();

		/** entity principale **/

		CriteriaQuery<PhaseOfEntite> vCriteriaQuery = vBuilder
				.createQuery(PhaseOfEntite.class);

		/************ entity jointure *****************/

		Root<PhaseOfEntite> vRootPhaseOf = vCriteriaQuery
				.from(PhaseOfEntite.class);

		/***************** Predicate *************/

		if (estNonVide(pRecherchePhaseOfValueMulticritere.getPhaseId())) {
			vWhereClause.add(vBuilder.equal(vRootPhaseOf.get("phaseId"),
					pRecherchePhaseOfValueMulticritere.getPhaseId()));
		}

		if (estNonVide(pRecherchePhaseOfValueMulticritere.getMethode())) {
			vWhereClause.add(vBuilder.equal(vRootPhaseOf.get("methode"),
					pRecherchePhaseOfValueMulticritere.getMethode()));
		}

		if (estNonVide(pRecherchePhaseOfValueMulticritere.getDateDebut())) {
			vWhereClause.add(vBuilder.equal(vRootPhaseOf.get("dateDebut"),
					pRecherchePhaseOfValueMulticritere.getDateDebut()));
		}

		if (estNonVide(pRecherchePhaseOfValueMulticritere.getDateFin())) {
			vWhereClause.add(vBuilder.equal(vRootPhaseOf.get("dateFin"),
					pRecherchePhaseOfValueMulticritere.getDateFin()));
		}

		if (estNonVide(pRecherchePhaseOfValueMulticritere.getClientId())) {
			vWhereClause.add(vBuilder.equal(vRootPhaseOf.get("clientId"),
					pRecherchePhaseOfValueMulticritere.getClientId()));
		}
		/******************************************************************************************************/

		if (estNonVide(pRecherchePhaseOfValueMulticritere.getNumero())) {

			Join<OrdreFabricationEntite, PhaseOfEntite> jointureEnStkMagasin = vRootPhaseOf
					.join(ordre);
			vWhereClause.add(vBuilder.equal(jointureEnStkMagasin.get("numero"),
					pRecherchePhaseOfValueMulticritere.getNumero()));
		}

		/** execute query and do something with result **/

		vCriteriaQuery.select(vRootPhaseOf).where(
				vWhereClause.toArray(new Predicate[] {}));
		List<PhaseOfEntite> resultatEntite = this.entityManager.createQuery(
				vCriteriaQuery).getResultList();

		/** Conversion de la liste en valeur */
		List<PhaseOfValue> vListeResultat = new ArrayList<PhaseOfValue>();

		for (PhaseOfEntite vPhaseOfEntite : resultatEntite) {
			PhaseOfValue vPv = PersistanceUtilities
					.toValueSuivi(vPhaseOfEntite);
			vListeResultat.add(vPv);
		}
		/** retour de list de recherche et le nombre de resultat **/
		ResultatMulticriterePhaseOfValue vResultatMulticriterePhaseOfValue = new ResultatMulticriterePhaseOfValue();

		vResultatMulticriterePhaseOfValue.setNombreResultatRecherche(Long
				.valueOf(vListeResultat.size()));

		vResultatMulticriterePhaseOfValue.setPhaseOf(vListeResultat);

		return vResultatMulticriterePhaseOfValue;
	}

	private boolean estNonVide(Long val) {
		return val != null && !"".equals(val);

	}

	private boolean estNonVide(Calendar val) {
		return val != null && !"".equals(val);

	}

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);

	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
