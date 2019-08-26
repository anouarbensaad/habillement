package com.gpro.consulting.tiers.commun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitTailleValue;
import com.gpro.consulting.tiers.commun.domaine.IProduitTailleDomaine;
import com.gpro.consulting.tiers.commun.service.IProduitTailleService;

public class ProduitTailleServiceImpl implements IProduitTailleService {
	
	@Autowired
	IProduitTailleDomaine produitTailleDomaine; 
	
	@Override
	public List<ProduitTailleValue> ListeProduitTaille(Long pIdProduit) {
		// TODO Auto-generated method stub
		return produitTailleDomaine.ListeProduitTaille(pIdProduit);
	}

	public IProduitTailleDomaine getProduitTailleDomaine() {
		return produitTailleDomaine;
	}

	public void setProduitTailleDomaine(
			IProduitTailleDomaine produitCouleurDomaine) {
		this.produitTailleDomaine = produitCouleurDomaine;
	}


}
