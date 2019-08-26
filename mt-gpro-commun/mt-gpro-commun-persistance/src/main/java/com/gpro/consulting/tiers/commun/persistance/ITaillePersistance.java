package com.gpro.consulting.tiers.commun.persistance;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;

public interface ITaillePersistance {

	/**************************ajouter Taille***************************/
	public  String creerTaille(TailleValue pTailleValue);
	
	/**********************supprimer Taille*****************************/
	public  void supprimerTaille(Long pId);
	
	/**********************modifier Taille *****************************/
	public String modifierTaille(TailleValue pTailleValue);
	
	/**********************recherche  Taille****************************/
	public TailleValue rechercheTailleParId(TailleValue pTailleValue);
	
	/******************afficher  liste  Taille**************************/
	public List<TailleValue> listeTaille();
	
	public TailleValue getById(Long id);
	
	public List<TailleValue> getAllInList(List<Long> ids);

	public List<TailleValue> rechercherTailleByStandard(Long pIdStandard);
	
}

