package com.gpro.consulting.tiers.gpao.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.value.PhaseOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticriterePhaseOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticriterePhaseOfValue;

public interface IPhaseOfService {
	
	/**
	 * Methode de Recherche des PhaseOf par Id
	 * 
	 * @return
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public PhaseOfValue recherchePhaseOfParId(PhaseOfValue pPhaseOfValue);
	 
	 /**
		 * Methode de Modification des PhaseOf
		 * 
		 * @return
		 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	 public String modifierPhaseOf(PhaseOfValue pPhaseOfValue);
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	 public ResultatMulticriterePhaseOfValue rechercherPhaseOfMultiCritere(
    		 RechercheMulticriterePhaseOfValue pRecherchePhaseOfValueMulticritere); 
	
	 /**
	 * Methode de listing  des PhaseOf
	 * 
	 * @return
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
     public List <PhaseOfValue>listePhaseOfValue();
 
}
