package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitCouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitTailleValue;
import com.gpro.consulting.tiers.commun.domaine.IProduitTailleDomaine;
import com.gpro.consulting.tiers.commun.persistance.IProduitTaillePersistance;

public class ProduitTailleDomaineImpl  implements IProduitTailleDomaine {

	@Autowired
	IProduitTaillePersistance ebProduitTaille;
	
	@Override
	public List<ProduitTailleValue> ListeProduitTaille(Long pIdProduit) {
		return ebProduitTaille.ListeProduitTaille(pIdProduit);
	}

	public IProduitTaillePersistance getEbProduitCouleur() {
		return ebProduitTaille;
	}

	public void setEbProduitTaille(IProduitTaillePersistance ebProduitTaille) {
		this.ebProduitTaille = ebProduitTaille;
	}
	
	
	
	

}
