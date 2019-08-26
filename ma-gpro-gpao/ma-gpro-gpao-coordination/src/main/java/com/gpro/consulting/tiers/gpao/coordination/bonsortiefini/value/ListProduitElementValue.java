package com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.DetLivraisonVenteValue;


/** 
 * @author Wahid Gazzah
 * @since 27/01/2016
 */
public class ListProduitElementValue {
	
	private int nombreResultaRechercher;
	
	//added on 25/02/2016, by Wahid Gazzah
	private Calendar dateSortie;
	private Calendar dateLivrison;
	private List<DetLivraisonVenteValue> listDetLivraisonVente;
    private int nombrePalette;
	private String observationsPalettes;
	
	public int getNombrePalette() {
		return nombrePalette;
	}

	public void setNombrePalette(int nombrePalette) {
		this.nombrePalette = nombrePalette;
	}

	private Long partieIntId ;
	
	public int getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(int nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Calendar getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Calendar dateSortie) {
		this.dateSortie = dateSortie;
	}

	public List<DetLivraisonVenteValue> getListDetLivraisonVente() {
		return listDetLivraisonVente;
	}

	public void setListDetLivraisonVente(
			List<DetLivraisonVenteValue> listDetLivraisonVente) {
		this.listDetLivraisonVente = listDetLivraisonVente;
	}

	public Long getPartieIntId() {
		return partieIntId;
	}

	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}

	public Calendar getDateLivrison() {
		return dateLivrison;
	}

	public void setDateLivrison(Calendar dateLivrison) {
		this.dateLivrison = dateLivrison;
	}

	public String getObservationsPalettes() {
		return observationsPalettes;
	}

	public void setObservationsPalettes(String observationsPalettes) {
		this.observationsPalettes = observationsPalettes;
	}

	

	
}
