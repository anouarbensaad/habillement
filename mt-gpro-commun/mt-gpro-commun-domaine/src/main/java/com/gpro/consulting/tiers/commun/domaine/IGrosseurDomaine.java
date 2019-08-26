package com.gpro.consulting.tiers.commun.domaine;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.GrosseurValue;

public interface IGrosseurDomaine {
	
	/**************************ajouter Grosseur***************************/
	public  String creerGrosseur(GrosseurValue pGrosseurValue);
	
	/**********************supprimer Grosseur*****************************/
	public  void supprimerGrosseur(Long pId);
	
	/**********************modifier Grosseur *****************************/
	public String modifierGrosseur(GrosseurValue pGrosseurValue);
	
	/**********************recherche  Grosseur****************************/
	public GrosseurValue rechercheGrosseurParId(GrosseurValue pGrosseurValue);
	
	/******************afficher  liste  Grosseur**************************/
	public List<GrosseurValue> listeGrosseur();
}
