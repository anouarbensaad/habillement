package com.gpro.consulting.tiers.commun.domaine;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.MatiereArticleValue;

public interface IMatiereDomaine {

	/**************************ajouter Matiere***************************/
	public  String creerMatiere(MatiereArticleValue pMatiereValue);
	
	/**********************supprimer Matiere*****************************/
	public  void supprimerMatiere(Long pId);
		
	/**********************modifier Matiere *****************************/
	public String modifierMatiere(MatiereArticleValue pMatiereValue);
	
	/**********************recherche  Matiere****************************/
	public MatiereArticleValue rechercheMatiereParId(MatiereArticleValue pMatiereValue);
	
	/******************afficher  liste  Matiere**************************/
	public List<MatiereArticleValue> listeMatiere();
}
