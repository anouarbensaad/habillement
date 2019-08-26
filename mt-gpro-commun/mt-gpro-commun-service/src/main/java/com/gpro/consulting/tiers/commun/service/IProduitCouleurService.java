package com.gpro.consulting.tiers.commun.service;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitCouleurValue;

public interface IProduitCouleurService {
	
	public List<ProduitCouleurValue> ListeProduitCouleur(Long pIdProduit);

}
