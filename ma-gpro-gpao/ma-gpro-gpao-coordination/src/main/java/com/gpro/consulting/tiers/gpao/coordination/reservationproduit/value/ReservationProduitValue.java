package com.gpro.consulting.tiers.gpao.coordination.reservationproduit.value;

/**
 * @author Wahid Gazzah
 * @since 09/05/2016
 */
public class ReservationProduitValue {
	
	private Long articleId;
	private String articleDesignation;
	private String articleReference;
	private String articleFamille;
	private Double besoin;
	private String type;
	private Long typeArticle;
	private Long  quantite;
	private Double  besoinUnitaire;

	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
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
	public Long getQuantite() {
		return quantite;
	}
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public String getArticleDesignation() {
		return articleDesignation;
	}
	public void setArticleDesignation(String articleDesignation) {
		this.articleDesignation = articleDesignation;
	}
	public String getArticleReference() {
		return articleReference;
	}
	public void setArticleReference(String articleReference) {
		this.articleReference = articleReference;
	}
	public Double getBesoinUnitaire() {
		return besoinUnitaire;
	}
	public void setBesoinUnitaire(Double besoinUnitaire) {
		this.besoinUnitaire = besoinUnitaire;
	}
	public Long getTypeArticle() {
		return typeArticle;
	}
	public void setTypeArticle(Long typeArticle) {
		this.typeArticle = typeArticle;
	}
	public String getArticleFamille() {
		return articleFamille;
	}
	public void setArticleFamille(String articleFamille) {
		this.articleFamille = articleFamille;
	}
	
	
}
