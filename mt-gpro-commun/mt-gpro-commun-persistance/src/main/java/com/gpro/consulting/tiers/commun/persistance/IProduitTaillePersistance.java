package com.gpro.consulting.tiers.commun.persistance;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitTailleValue;

public interface IProduitTaillePersistance {

	List < ProduitTailleValue > ListeProduitTaille(Long pIdProduit);
	
	public ProduitTailleValue getById(Long id);
	
}
