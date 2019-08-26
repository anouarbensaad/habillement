package com.gpro.consulting.tiers.commun.coordination.besoinarticle.value;

/**
 * @author Wahid Gazzah
 * @since 18/04/2016
 *
 */
public class BesoinArticleValue {
	
	private Long produitId;
	private Long articleId;
	private Long tailleId;
	private Long couleurId;
	
	private Double besoin;
	private Long quantiteCommande;
	
	private String produitDesignation;
	private String articleDesignation;
	private String tailleDesignation;
	private String couleurDesignation;
	
	private String articleReference;
	private Double articlePrixUnitaire;
	private String sousFamilleDesignation;
	private Double total;
	
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	public Long getTailleId() {
		return tailleId;
	}
	public void setTailleId(Long tailleId) {
		this.tailleId = tailleId;
	}
	public Long getCouleurId() {
		return couleurId;
	}
	public void setCouleurId(Long couleurId) {
		this.couleurId = couleurId;
	}

	public Long getQuantiteCommande() {
		return quantiteCommande;
	}
	public void setQuantiteCommande(Long quantiteCommande) {
		this.quantiteCommande = quantiteCommande;
	}
	public String getProduitDesignation() {
		return produitDesignation;
	}
	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}
	public String getArticleDesignation() {
		return articleDesignation;
	}
	public void setArticleDesignation(String articleDesignation) {
		this.articleDesignation = articleDesignation;
	}
	public String getTailleDesignation() {
		return tailleDesignation;
	}
	public void setTailleDesignation(String tailleDesignation) {
		this.tailleDesignation = tailleDesignation;
	}
	public String getCouleurDesignation() {
		return couleurDesignation;
	}
	public void setCouleurDesignation(String couleurDesignation) {
		this.couleurDesignation = couleurDesignation;
	}
	public Double getBesoin() {
		return besoin;
	}
	public void setBesoin(Double besoin) {
		this.besoin = besoin;
	}
	public String getArticleReference() {
		return articleReference;
	}
	public void setArticleReference(String articleReference) {
		this.articleReference = articleReference;
	}
	public Double getArticlePrixUnitaire() {
		return articlePrixUnitaire;
	}
	public void setArticlePrixUnitaire(Double articlePrixUnitaire) {
		this.articlePrixUnitaire = articlePrixUnitaire;
	}
	public String getSousFamilleDesignation() {
		return sousFamilleDesignation;
	}
	public void setSousFamilleDesignation(String sousFamilleDesignation) {
		this.sousFamilleDesignation = sousFamilleDesignation;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	

}
