package com.gpro.consulting.tiers.gc.coordination.besoinarticle.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 09/05/2016
 *
 */
public class RechercheMulticritereBesoinArticleValue {
	
	private String refCommande;
	private Calendar dateLivraisonMin;
	private Calendar dateLivraisonMax;
	private Calendar dateCommandeMin;
	private Calendar dateCommandeMax;
	private Long produitId;
	private Long clientId;
	public String getRefCommande() {
		return refCommande;
	}
	public void setRefCommande(String refCommande) {
		this.refCommande = refCommande;
	}
	public Calendar getDateLivraisonMin() {
		return dateLivraisonMin;
	}
	public void setDateLivraisonMin(Calendar dateLivraisonMin) {
		this.dateLivraisonMin = dateLivraisonMin;
	}
	public Calendar getDateLivraisonMax() {
		return dateLivraisonMax;
	}
	public void setDateLivraisonMax(Calendar dateLivraisonMax) {
		this.dateLivraisonMax = dateLivraisonMax;
	}
	public Calendar getDateCommandeMin() {
		return dateCommandeMin;
	}
	public void setDateCommandeMin(Calendar dateCommandeMin) {
		this.dateCommandeMin = dateCommandeMin;
	}
	public Calendar getDateCommandeMax() {
		return dateCommandeMax;
	}
	public void setDateCommandeMax(Calendar dateCommandeMax) {
		this.dateCommandeMax = dateCommandeMax;
	}
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	

}
