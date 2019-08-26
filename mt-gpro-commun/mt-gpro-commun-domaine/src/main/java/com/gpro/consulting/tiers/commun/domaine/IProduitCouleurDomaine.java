package com.gpro.consulting.tiers.commun.domaine;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitCouleurValue;

public interface IProduitCouleurDomaine {
	
	public List < ProduitCouleurValue > ListeProduitCouleur(Long pIdProduit);

}
