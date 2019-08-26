package com.gpro.consulting.tiers.commun.persistance;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.RepresentantValue;

public interface IRepresentantPartieInteresseePersistance {
	/****************** ajouter Representant partie interesse *************/
	public String creerRepresentantPartieInteressee(
			RepresentantValue pRepresantentValue);

	/********************** supprimer Representant partie interesse *****************************/
	public void supprimerRepresentantPartieInteressee(
			RepresentantValue pRepresantentValue);

	/********************** modifier Representant partie interesse *****************************/
	public String modifierRepresentantPartieInteressee(
			RepresentantValue pRepresantentValue);

	/********************** recherche Representant partie interesse par Id *****************************/
	public RepresentantValue rechercheRepresentantPartieInteresseeParId(
			RepresentantValue pRepresantentValue);

	/********************** afficher liste Representant partie interesse *****************************/
	public List<RepresentantValue> listeRepresentantPartieInteressee();

}
