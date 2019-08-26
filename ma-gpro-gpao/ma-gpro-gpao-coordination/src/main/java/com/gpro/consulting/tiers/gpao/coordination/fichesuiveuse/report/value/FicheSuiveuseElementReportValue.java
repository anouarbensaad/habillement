package com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.report.value;

/**
 * @author Wahid Gazzah
 * @since 16/06/2016
 *
 */
public class FicheSuiveuseElementReportValue implements Comparable<FicheSuiveuseElementReportValue>{

	private String numOrdreFabrication;
	private Long numPaquet;
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
	
	private Long quantite;
	
	private Boolean headerElement;
	private Boolean emptyElement;
	
	private Long ordreElementGammeOf;
	
	private String bareCodeForCompare;
	private Long pdh;
	
	public int compareTo(FicheSuiveuseElementReportValue element) {
		return bareCodeForCompare.compareTo(element.getBareCodeForCompare());
	}

	public String getNumOrdreFabrication() {
		return numOrdreFabrication;
	}

	public void setNumOrdreFabrication(String numOrdreFabrication) {
		this.numOrdreFabrication = numOrdreFabrication;
	}

	public Long getNumPaquet() {
		return numPaquet;
	}

	public void setNumPaquet(Long numPaquet) {
		this.numPaquet = numPaquet;
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

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public Boolean getHeaderElement() {
		return headerElement;
	}

	public void setHeaderElement(Boolean headerElement) {
		this.headerElement = headerElement;
	}

	public Boolean getEmptyElement() {
		return emptyElement;
	}

	public void setEmptyElement(Boolean emptyElement) {
		this.emptyElement = emptyElement;
	}
	

	public Long getOrdreElementGammeOf() {
		return ordreElementGammeOf;
	}

	public void setOrdreElementGammeOf(Long ordreElementGammeOf) {
		this.ordreElementGammeOf = ordreElementGammeOf;
	}

	public String getBareCodeForCompare() {
		return bareCodeForCompare;
	}

	public void setBareCodeForCompare(String bareCodeForCompare) {
		this.bareCodeForCompare = bareCodeForCompare;
	}

	public Long getPdh() {
		return pdh;
	}

	public void setPdh(Long pdh) {
		this.pdh = pdh;
	}

}
