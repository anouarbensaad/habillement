package com.gpro.consulting.tiers.commun.persistance;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.DocumentValue;

public interface IDocumentPartieInteresseePersistance {
	/****************** ajouter Document partie interesse *************/
	public String creerDocumentPartieInteressee(DocumentValue pDocumenteValue);

	/********************** supprimer Document partie interesse *****************************/
	public void supprimerDocumentPartieInteressee(DocumentValue pDocumentValue);

	/********************** modifier Document partie interesse *****************************/
	public String modifierDocumentPartieInteressee(DocumentValue pDocumentValue);

	/********************** recherche Document partie interesse par Id *****************************/
	public DocumentValue rechercheDocumentPartieInteresseeParId(
			DocumentValue pDocumentValue);

	/********************** afficher liste Document partie interesse *****************************/
	public List<DocumentValue> listeDocumentPartieInteressee();

}
