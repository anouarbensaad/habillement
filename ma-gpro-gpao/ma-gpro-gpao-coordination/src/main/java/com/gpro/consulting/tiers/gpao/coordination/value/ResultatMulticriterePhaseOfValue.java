package com.gpro.consulting.tiers.gpao.coordination.value;

import java.util.List;


public class ResultatMulticriterePhaseOfValue {
	private Long nombreResultatRecherche;
	private List<PhaseOfValue> phaseOf;
	
	/*********** Getter & Setter ************/

	/**
	 * @return the nombreResultatRecherche
	 */
	public Long getNombreResultatRecherche() {
		return nombreResultatRecherche;
	}
	/**
	 * @param nombreResultatRecherche the nombreResultatRecherche to set
	 */
	public void setNombreResultatRecherche(Long nombreResultatRecherche) {
		this.nombreResultatRecherche = nombreResultatRecherche;
	}
	/**
	 * @return the phaseof 
	 * 
	 */
	
	public List<PhaseOfValue> getPhaseOf() {
		return phaseOf;
	}
	public void setPhaseOf(List<PhaseOfValue> phaseOf) {
		this.phaseOf = phaseOf;
	}
	
}
