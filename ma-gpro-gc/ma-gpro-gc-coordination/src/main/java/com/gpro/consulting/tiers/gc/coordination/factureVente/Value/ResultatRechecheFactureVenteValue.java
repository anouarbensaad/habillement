package com.gpro.consulting.tiers.gc.coordination.factureVente.Value;

import java.util.List;

/**
 * @author Ameni Berrich
 *
 */
public class ResultatRechecheFactureVenteValue {

	private Long nombreResultaRechercher;

	private List<FactureVenteElementValue> factureVenteValues;

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<FactureVenteElementValue> getFactureVenteValues() {
		return factureVenteValues;
	}

	public void setFactureVenteValues(
			List<FactureVenteElementValue> factureVenteValues) {
		this.factureVenteValues = factureVenteValues;
	}

}
