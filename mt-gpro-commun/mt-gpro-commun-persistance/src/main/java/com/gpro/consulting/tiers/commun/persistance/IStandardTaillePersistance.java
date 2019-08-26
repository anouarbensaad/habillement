package com.gpro.consulting.tiers.commun.persistance;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.StandardTailleValue;

public interface IStandardTaillePersistance {

	/**************************ajouter StandardTaille***************************/
	public  String creerStandardTaille(StandardTailleValue pStandardTailleValue);
	
	/**********************supprimer StandardTaille*****************************/
	public  void supprimerStandardTaille(Long pId);
	
	/**********************modifier StandardTaille *****************************/
	public String modifierStandardTaille(StandardTailleValue pStandardTailleValue);
	
	/**********************recherche  StandardTaille****************************/
	public StandardTailleValue rechercheStandardTailleParId(StandardTailleValue pStandardTailleValue);
	
	/******************afficher  liste  StandardTaille**************************/
	public List<StandardTailleValue> listeStandardTaille();

	public StandardTailleValue rechercheStandardTailleParId(Long pId);
	
}