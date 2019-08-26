package com.gpro.consulting.tiers.gpao.coordination.report;

/**
 * @author Wahid Gazzah
 * @since 12/05/2016
 */
public class ReservationProduitElementReportValue {

	private String articleReference;
	private String articleDesignation;
	private String type;
	private Double besoin;
	private Double quantite;
	
	public String getArticleDesignation() {
		return articleDesignation;
	}
	public void setArticleDesignation(String articleDesignation) {
		this.articleDesignation = articleDesignation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getBesoin() {
		return besoin;
	}
	public void setBesoin(Double besoin) {
		this.besoin = besoin;
	}
	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
	public String getArticleReference() {
		return articleReference;
	}
	public void setArticleReference(String articleReference) {
		this.articleReference = articleReference;
	}
	
}
