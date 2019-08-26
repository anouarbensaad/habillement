package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.value.PhaseOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticriterePhaseOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticriterePhaseOfValue;
import com.gpro.consulting.tiers.gpao.domaine.IPhaseOfDomaine;
import com.gpro.consulting.tiers.gpao.persitance.IPhaseOfPersistance;

@Component
public class PhaseOfDomaineImpl implements IPhaseOfDomaine {

	@Autowired
	IPhaseOfPersistance PhaseOfPersistance;
	
	@Override
	public PhaseOfValue recherchePhaseOfParId(PhaseOfValue pPhaseOfValue) {
		
		return PhaseOfPersistance.recherchePhaseOfParId(pPhaseOfValue);
	}

	@Override
	public String modifierPhaseOf(PhaseOfValue pPhaseOfValue) {
		
		return PhaseOfPersistance.modifierPhaseOf(pPhaseOfValue);
	}
	
	@Override
	public ResultatMulticriterePhaseOfValue rechercherPhaseOfMultiCritere(
			RechercheMulticriterePhaseOfValue pRecherchePhaseOfValueMulticritere) {
		// TODO Auto-generated method stub
		return PhaseOfPersistance.rechercherPhaseOfMultiCritere(pRecherchePhaseOfValueMulticritere) ;
	}
	
	@Override
	public List<PhaseOfValue> listePhaseOfValue() {
		
		return PhaseOfPersistance.listePhaseOfValue();
	}
	

}
