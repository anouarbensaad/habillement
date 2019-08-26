package com.gpro.consulting.tiers.gpao.domaine;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.value.PhaseOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticriterePhaseOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticriterePhaseOfValue;

public interface IPhaseOfDomaine {
	/**
	 * Methode de Recherche des PhaseOf par Id
	 * 
	 * @return
	 */
	
	public PhaseOfValue recherchePhaseOfParId(PhaseOfValue pPhaseOfValue);

	 
	 /**
		 * Methode de Modification des PhaseOf
		 * 
		 * @return
		 */
	 
	 public String modifierPhaseOf(PhaseOfValue pPhaseOfValue);
	 
	 /**
		 * Methode de ResultatMulticriterePhaseOf
		 * 
		 * @return
		 */
	 public ResultatMulticriterePhaseOfValue rechercherPhaseOfMultiCritere(
    		 RechercheMulticriterePhaseOfValue pRecherchePhaseOfValueMulticritere); 
	 
	 /**
		 * Methode de listing  des PhaseOf
		 * 
		 * @return
		 */
	 
	 public List <PhaseOfValue>listePhaseOfValue();
}

