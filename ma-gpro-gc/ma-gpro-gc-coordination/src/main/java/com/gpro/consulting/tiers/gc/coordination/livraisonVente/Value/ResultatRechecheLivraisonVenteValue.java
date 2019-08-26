package com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value;

import java.util.List;

/**
 * @author Ameni Berrich
 *
 */
public class ResultatRechecheLivraisonVenteValue {

	private Long nombreResultaRechercher;

	private List<livraisonVenteElementValue> livraisonVenteValues;

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<livraisonVenteElementValue> getLivraisonVenteValues() {
		return livraisonVenteValues;
	}

	public void setLivraisonVenteValues(
			List<livraisonVenteElementValue> livraisonVenteValues) {
		this.livraisonVenteValues = livraisonVenteValues;
	}

	

}
