package com.gpro.consulting.tiers.commun.service;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitTailleValue;

public interface IProduitTailleService {
	public List<ProduitTailleValue> ListeProduitTaille(Long pIdProduit);

}
