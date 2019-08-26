package com.gpro.consulting.tiers.commun.persistance;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.TypeDocumentValue;

public interface ITypeDocumentPersistance {
	/****************** ajouter type document partie interesse *************/
	public String creerTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue);

	/********************** supprimer type document partie interesse *****************************/
	public void supprimerTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue);

	/********************** modifier type document partie interesse *****************************/
	public String modifierTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue);

	/********************** recherche type document partie interesse par Id *****************************/
	public TypeDocumentValue rechercheTypeDocumentPartieInteresseeParId(
			TypeDocumentValue pTypeDocumentValue);

	/********************** afficher liste type document partie interesse *****************************/
	public List<TypeDocumentValue> listeTypeDocumentPartieInteressee();

}
