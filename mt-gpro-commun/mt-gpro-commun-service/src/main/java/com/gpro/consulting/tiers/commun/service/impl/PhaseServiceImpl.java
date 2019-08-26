package com.gpro.consulting.tiers.commun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.PhaseValue;
import com.gpro.consulting.tiers.commun.domaine.IPhaseDomaine;
import com.gpro.consulting.tiers.commun.service.IPhaseService;


@Service
@Transactional
public class PhaseServiceImpl implements IPhaseService{

	@Autowired
	IPhaseDomaine ebPhaseDomaine;
	
	/************************ Creation Matiere *****************************/
	@Override
	public String creerPhase(PhaseValue pPhaseValue) {
		return ebPhaseDomaine.creerPhase(pPhaseValue);
	}

	/************************ suppression Matiere ***************************/
	@Override
	public void supprimerPhase(PhaseValue pPhaseValue) {
		ebPhaseDomaine.supprimerPhase(pPhaseValue);
	}

	/************************ Modification Matiere ***************************/
	@Override
	public String modifierPhase(PhaseValue pPhaseValue) {
		return ebPhaseDomaine.modifierPhase(pPhaseValue);
	}

	/************************** Recherche Matiere *****************************/
	@Override
	public PhaseValue recherchePhaseParId(PhaseValue pPhaseValue) {
		return ebPhaseDomaine.recherchePhaseParId(pPhaseValue);
	}
	
	/************************** Liste des Matieres *****************************/
	@Override
	public List<PhaseValue> listePhase() {
		return ebPhaseDomaine.listePhase();
	}

	/************************** Getter & Setter *****************************/
	public IPhaseDomaine getEbPhaseDomaine() {
		return ebPhaseDomaine;
	}

	public void setEbPhaseDomaine(IPhaseDomaine ebPhaseDomaine) {
		this.ebPhaseDomaine = ebPhaseDomaine;
	}

	
}
