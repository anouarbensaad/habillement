package com.gpro.consulting.tiers.commun.domaine;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;

public interface ICouleurDomaine {
	
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
	
}


