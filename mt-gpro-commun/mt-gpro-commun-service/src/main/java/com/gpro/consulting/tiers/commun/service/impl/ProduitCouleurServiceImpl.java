package com.gpro.consulting.tiers.commun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitCouleurValue;
import com.gpro.consulting.tiers.commun.domaine.IProduitCouleurDomaine;
import com.gpro.consulting.tiers.commun.service.IProduitCouleurService;


@Service
@Transactional
public class ProduitCouleurServiceImpl implements IProduitCouleurService {

	
	@Autowired
	IProduitCouleurDomaine produitCouleurDomaine; 
	
	@Override
	public List<ProduitCouleurValue> ListeProduitCouleur(Long pIdProduit) {
		// TODO Auto-generated method stub
		return produitCouleurDomaine.ListeProduitCouleur(pIdProduit);
	}

	public IProduitCouleurDomaine getProduitCouleurDomaine() {
		return produitCouleurDomaine;
	}

	public void setProduitCouleurDomaine(
			IProduitCouleurDomaine produitCouleurDomaine) {
		this.produitCouleurDomaine = produitCouleurDomaine;
	}

	
}
