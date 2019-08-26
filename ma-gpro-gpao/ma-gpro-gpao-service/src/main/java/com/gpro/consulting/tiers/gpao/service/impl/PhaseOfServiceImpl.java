package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.value.PhaseOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticriterePhaseOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticriterePhaseOfValue;
import com.gpro.consulting.tiers.gpao.domaine.IPhaseOfDomaine;
import com.gpro.consulting.tiers.gpao.service.IPhaseOfService;

@Service
@Transactional(rollbackFor = Exception.class)
public class PhaseOfServiceImpl implements IPhaseOfService {
	
	@Autowired
	IPhaseOfDomaine PhaseOfDomaine;

	@Override
	public PhaseOfValue recherchePhaseOfParId(PhaseOfValue pPhaseOfValue) {
		return PhaseOfDomaine.recherchePhaseOfParId(pPhaseOfValue);
	}

	@Override
	public String modifierPhaseOf(PhaseOfValue pPhaseOfValue) {
		return PhaseOfDomaine.modifierPhaseOf(pPhaseOfValue);
	}

	@Override
	public ResultatMulticriterePhaseOfValue rechercherPhaseOfMultiCritere(
			RechercheMulticriterePhaseOfValue pRecherchePhaseOfValueMulticritere) {

		return PhaseOfDomaine
				.rechercherPhaseOfMultiCritere(pRecherchePhaseOfValueMulticritere);
	}

	@Override
	public List<PhaseOfValue> listePhaseOfValue() {

		return PhaseOfDomaine.listePhaseOfValue();
	}
	

}
