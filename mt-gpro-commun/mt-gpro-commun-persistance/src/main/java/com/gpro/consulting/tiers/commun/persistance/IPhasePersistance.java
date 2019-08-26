package com.gpro.consulting.tiers.commun.persistance;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.PhaseValue;

public interface IPhasePersistance {

	/**************************ajouter Phase***************************/
	public  String creerPhase(PhaseValue pPhaseValue);
	
	/**********************supprimer Phase*****************************/
	public  void supprimerPhase(PhaseValue pPhaseValue);
	
	/**********************modifier Phase *****************************/
	public String modifierPhase(PhaseValue pPhaseValue);
	
	/**********************recherche  Phase****************************/
	public PhaseValue recherchePhaseParId(PhaseValue pPhaseValue);
	
	/******************afficher  liste  Phase**************************/
	public List<PhaseValue> listePhase();
	
}
