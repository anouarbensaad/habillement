package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitCouleurValue;
import com.gpro.consulting.tiers.commun.domaine.IProduitCouleurDomaine;
import com.gpro.consulting.tiers.commun.persistance.IProduitCouleurPersistance;

public class ProduitCouleurDomaineImpl implements IProduitCouleurDomaine {

	@Autowired
	IProduitCouleurPersistance ebProduitCouleur;
	
	@Override
	public List<ProduitCouleurValue> ListeProduitCouleur(Long pIdProduit) {
		return ebProduitCouleur.ListeProduitCouleur(pIdProduit);
	}

	public IProduitCouleurPersistance getEbProduitCouleur() {
		return ebProduitCouleur;
	}

	public void setEbProduitCouleur(IProduitCouleurPersistance ebProduitCouleur) {
		this.ebProduitCouleur = ebProduitCouleur;
	}
	
	
	
	

}
