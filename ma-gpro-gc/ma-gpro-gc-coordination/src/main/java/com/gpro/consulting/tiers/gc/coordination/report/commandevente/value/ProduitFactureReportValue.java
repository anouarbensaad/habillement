package com.gpro.consulting.tiers.gc.coordination.report.commandevente.value;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 *
 */
public class ProduitFactureReportValue {
	private Long id;
	private Double prix;
	private String devise;
	private Long quantite;
	private Double montant;
	private Boolean blSuppression;
	private Calendar dateSuppression;
	private Calendar dateModification;
	private Calendar dateCreation;
	private Long factureVenteId;
	private Long produitId;

	// Hajer AMRI
	private Long colis;
	private String palette;
	private String referenceCommande;
	private Double prixTMP;


	// conversionId/Designation
	private String produitReference;
	private String produitDesignation;
	private String produitSousFamilleDesignation;
	
	public Double getPrixTMP() {
		return prixTMP;
	}

	public void setPrixTMP(Double prixTMP) {
		this.prixTMP = prixTMP;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public String getDevise() {
		return devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public Boolean getBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	public String getProduitSousFamilleDesignation() {
		return produitSousFamilleDesignation;
	}

	public void setProduitSousFamilleDesignation(String produitSousFamilleDesignation) {
		this.produitSousFamilleDesignation = produitSousFamilleDesignation;
	}

	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public Calendar getDateModification() {
		return dateModification;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Long getFactureVenteId() {
		return factureVenteId;
	}

	public void setFactureVenteId(Long factureVenteId) {
		this.factureVenteId = factureVenteId;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public String getProduitReference() {
		return produitReference;
	}

	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}

	public String getProduitDesignation() {
		return produitDesignation;
	}

	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Long getColis() {
		return colis;
	}

	public void setColis(Long colis) {
		this.colis = colis;
	}

	public String getPalette() {
		return palette;
	}

	public void setPalette(String palette) {
		this.palette = palette;
	}

	public String getReferenceCommande() {
		return referenceCommande;
	}

	public void setReferenceCommande(String referenceCommande) {
		this.referenceCommande = referenceCommande;
	}
	
	

}
