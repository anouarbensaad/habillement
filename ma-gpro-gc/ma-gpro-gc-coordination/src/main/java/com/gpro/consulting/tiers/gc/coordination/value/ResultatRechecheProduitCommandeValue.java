package com.gpro.consulting.tiers.gc.coordination.value;

import java.util.Set;
import java.util.TreeSet;

/**
* @author Wahid Gazzah
* @since 15/03/2016
*/
public class ResultatRechecheProduitCommandeValue {
	
	private Long nombreResultaRechercher;

	private Set<ProduitCommandeValue> listProduitCommandeValue = new TreeSet <ProduitCommandeValue>();

	
	
	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<ProduitCommandeValue> getListProduitCommandeValue() {
		return listProduitCommandeValue;
	}

	public void setListProduitCommandeValue(
			Set<ProduitCommandeValue> listProduitCommandeValue) {
		this.listProduitCommandeValue = listProduitCommandeValue;
	}
	
}
