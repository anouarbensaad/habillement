package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.PhaseValue;
import com.gpro.consulting.tiers.commun.domaine.IPhaseDomaine;
import com.gpro.consulting.tiers.commun.persistance.IPhasePersistance;

public class PhaseDomaineImpl implements IPhaseDomaine{

	@Autowired
	IPhasePersistance ebPhasePersistance;
	
	/************************ Creation Phase ***************************/
	@Override
	public String creerPhase(PhaseValue pPhaseValue) {
		return ebPhasePersistance.creerPhase(pPhaseValue);
	}

	/*********************** suppression Phase **************************/
	@Override
	public void supprimerPhase(PhaseValue pPhaseValue) {
		ebPhasePersistance.supprimerPhase(pPhaseValue);
	}

	/************************ Modifier Phase ****************************/
	@Override
	public String modifierPhase(PhaseValue pPhaseValue) {
		return ebPhasePersistance.modifierPhase(pPhaseValue);
	}

	/************************ Rechercher Phase ***************************/
	@Override
	public PhaseValue recherchePhaseParId(PhaseValue pPhaseValue) {
		return ebPhasePersistance.recherchePhaseParId(pPhaseValue);
	}

	/************************ Liste des Phase ***************************/
	@Override
	public List<PhaseValue> listePhase() {
		return ebPhasePersistance.listePhase();
	}

	/************************ Getter & Setter ***************************/
	public IPhasePersistance getEbPhasePersistance() {
		return ebPhasePersistance;
	}

	public void setEbPhasePersistance(IPhasePersistance ebPhasePersistance) {
		this.ebPhasePersistance = ebPhasePersistance;
	}
	

}
