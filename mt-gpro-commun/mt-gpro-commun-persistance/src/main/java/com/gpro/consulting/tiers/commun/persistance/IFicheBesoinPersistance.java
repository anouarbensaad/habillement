package com.gpro.consulting.tiers.commun.persistance;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.FicheBesoinValue;

public interface IFicheBesoinPersistance {

	/**
	 * cette fonction est utilser pour la recherche
	 * d'une fiche besoin entite, on passant comme
	 * paramétere idProduit 
	 * @param pProduitId
	 * @return
	 */
	public FicheBesoinValue rechercheFicheBesoinParId(Long pProduitId);
	
	public String creerOmodifierFicheBesoin(FicheBesoinValue pFicheBesoinValue);
	
	public List<FicheBesoinValue> getAll();
	
	public FicheBesoinValue getById(Long id);
	
	public boolean checkFBExistence(Long produitId);
}
