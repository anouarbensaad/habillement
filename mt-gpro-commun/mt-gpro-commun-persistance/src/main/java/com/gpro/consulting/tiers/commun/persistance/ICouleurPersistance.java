package com.gpro.consulting.tiers.commun.persistance;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;

public interface ICouleurPersistance {

	/**************************ajouter Couleur***************************/
	public  String creerCouleur(CouleurValue pCouleurValue);
	
	/**********************supprimer Couleur*****************************/
	public  void supprimerCouleur(Long pId);
	
	/**********************modifier Couleur *****************************/
	public String modifierCouleur(CouleurValue pCouleurValue);
	
	/**********************recherche  Couleur****************************/
	public CouleurValue rechercheCouleurParId(CouleurValue pCouleurValue);
	
	/******************afficher  liste  Couleur**************************/
	public List<CouleurValue> listeCouleur();

	public List<CouleurValue> getAllInList(List<Long> ids);
	
	
	/************** get by id **********************/
	public CouleurValue getById(Long id);
	
}

