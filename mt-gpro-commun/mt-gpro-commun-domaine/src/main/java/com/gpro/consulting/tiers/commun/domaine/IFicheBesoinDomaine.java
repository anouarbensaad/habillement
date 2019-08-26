package com.gpro.consulting.tiers.commun.domaine;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.FicheBesoinValue;

public interface IFicheBesoinDomaine {
	
	public FicheBesoinValue rechercheFicheBesoinParId(Long pProduitId);
	public String creerOmodifierFicheBesoin(FicheBesoinValue pFicheBesoinValue);
	
	public List<FicheBesoinValue> getAll();

}
