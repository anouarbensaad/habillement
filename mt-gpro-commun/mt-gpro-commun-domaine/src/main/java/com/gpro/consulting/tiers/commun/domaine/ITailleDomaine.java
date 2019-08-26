package com.gpro.consulting.tiers.commun.domaine;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;

public interface ITailleDomaine {

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
	
}
