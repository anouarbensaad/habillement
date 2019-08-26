package com.gpro.consulting.tiers.commun.domaine;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitTailleValue;

public interface IProduitTailleDomaine {
	public List < ProduitTailleValue > ListeProduitTaille(Long pIdProduit);
}
