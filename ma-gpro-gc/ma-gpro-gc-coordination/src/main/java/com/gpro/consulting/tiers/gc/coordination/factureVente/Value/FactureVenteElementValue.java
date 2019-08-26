package com.gpro.consulting.tiers.gc.coordination.factureVente.Value;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 *
 */
public class FactureVenteElementValue implements Comparable<FactureVenteElementValue>{

	
	private Long id;
	private String reference;
	private String referenceCommande;
	private String modeReglement;
	private Long produitId;
	private Long partintId;
	private Double prixTotalHT;
	private Calendar dateFacturation;
	private Calendar dateEcheance;
	private Long factureVenteId;
	
	private String partieInteresseDesignation;
	
	@Override
	public int compareTo(FactureVenteElementValue o) {
		FactureVenteElementValue element= (FactureVenteElementValue)o;
		return (element.getId().compareTo(id));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getReferenceCommande() {
		return referenceCommande;
	}

	public void setReferenceCommande(String referenceCommande) {
		this.referenceCommande = referenceCommande;
	}

	public String getPartieInteresseDesignation() {
		return partieInteresseDesignation;
	}

	public void setPartieInteresseDesignation(String partieInteresseDesignation) {
		this.partieInteresseDesignation = partieInteresseDesignation;
	}

	public Long getPartintId() {
		return partintId;
	}

	public void setPartintId(Long partintId) {
		this.partintId = partintId;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getModeReglement() {
		return modeReglement;
	}

	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public Double getPrixTotalHT() {
		return prixTotalHT;
	}

	public void setPrixTotalHT(Double prixTotalHT) {
		this.prixTotalHT = prixTotalHT;
	}

	public Calendar getDateFacturation() {
		return dateFacturation;
	}

	public void setDateFacturation(Calendar dateFacturation) {
		this.dateFacturation = dateFacturation;
	}

	public Calendar getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Calendar dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public Long getFactureVenteId() {
		return factureVenteId;
	}

	public void setFactureVenteId(Long factureVenteId) {
		this.factureVenteId = factureVenteId;
	}

	
}
