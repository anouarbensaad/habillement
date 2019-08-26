package com.gpro.consulting.tiers.gpao.persitance;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.value.PhaseOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticriterePhaseOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticriterePhaseOfValue;

public interface IPhaseOfPersistance {

	public String modifierPhaseOf(PhaseOfValue pPhaseOfValue);

	public PhaseOfValue recherchePhaseOfParId(PhaseOfValue pPhaseOfValue);

	public ResultatMulticriterePhaseOfValue rechercherPhaseOfMultiCritere(
			RechercheMulticriterePhaseOfValue pRecherchePhaseOfValueMulticritere);

	public List<PhaseOfValue> listePhaseOfValue();
}
