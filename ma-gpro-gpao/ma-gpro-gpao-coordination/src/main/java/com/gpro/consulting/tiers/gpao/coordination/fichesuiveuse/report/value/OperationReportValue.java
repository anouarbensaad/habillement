package com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.report.value;


/**
 * @author Wahid Gazzah
 * @since 20/05/2016
 *
 */
public class OperationReportValue implements Comparable<OperationReportValue>{

	private String numOrdreFabrication;
	private String numPaquet;
	private Long quantitePaquets;
	private String bareCode;
	private Double temps;
	private String nomElementGamme;
	private String couleurDesignation;
	private String tailleDesignation;
	
	private String produitDesignation;
	private String produitReference;
	private String clientAbreviation;
	private String clientReference;
	
	public int compareTo(OperationReportValue element) {
		return (bareCode.compareTo(element.getBareCode()));
	}
	
	public String getNumPaquet() {
		return numPaquet;
	}
	public void setNumPaquet(String numPaquet) {
		this.numPaquet = numPaquet;
	}
	public String getCouleurDesignation() {
		return couleurDesignation;
	}
	public void setCouleurDesignation(String couleurDesignation) {
		this.couleurDesignation = couleurDesignation;
	}
	public String getTailleDesignation() {
		return tailleDesignation;
	}
	public void setTailleDesignation(String tailleDesignation) {
		this.tailleDesignation = tailleDesignation;
	}
	public String getNumOrdreFabrication() {
		return numOrdreFabrication;
	}
	public void setNumOrdreFabrication(String numOrdreFabrication) {
		this.numOrdreFabrication = numOrdreFabrication;
	}
	public Long getQuantitePaquets() {
		return quantitePaquets;
	}
	public void setQuantitePaquets(Long quantitePaquets) {
		this.quantitePaquets = quantitePaquets;
	}
	public String getBareCode() {
		return bareCode;
	}
	public void setBareCode(String bareCode) {
		this.bareCode = bareCode;
	}
	public Double getTemps() {
		return temps;
	}
	public void setTemps(Double temps) {
		this.temps = temps;
	}
	public String getNomElementGamme() {
		return nomElementGamme;
	}
	public void setNomElementGamme(String nomElementGamme) {
		this.nomElementGamme = nomElementGamme;
	}

	public String getProduitDesignation() {
		return produitDesignation;
	}

	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}

	public String getProduitReference() {
		return produitReference;
	}

	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}

	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}

	public String getClientReference() {
		return clientReference;
	}

	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}

	
}
