package com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ameni Berrich
 *
 */
public class ResultatRechecheDetailBonLivraisonValue {

	private Long nombreResultaRechercher;

	private List<DetLivraisonVenteValue> listDetailLivraison = new ArrayList<DetLivraisonVenteValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public List<DetLivraisonVenteValue> getListDetailLivraison() {
		return listDetailLivraison;
	}

	public void setListDetailLivraison(
			List<DetLivraisonVenteValue> listDetailLivraison) {
		this.listDetailLivraison = listDetailLivraison;
	}
	
}
