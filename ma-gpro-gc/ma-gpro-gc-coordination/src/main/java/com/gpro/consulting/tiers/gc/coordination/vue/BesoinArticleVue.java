package com.gpro.consulting.tiers.gc.coordination.vue;

/**
 * @author Wahid Gazzah
 * @since 19/05/2016
 *
 */
public class BesoinArticleVue {

	private Double besoin;
	private Long articleId;
	private String articleDesignation;
	private String tailleDesignation;
	private String couleurDesignation;
	private Long typeArticle;
	
	public Double getBesoin() {
		return besoin;
	}
	public void setBesoin(Double besoin) {
		this.besoin = besoin;
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
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	public Long getTypeArticle() {
		return typeArticle;
	}
	public void setTypeArticle(Long typeArticle) {
		this.typeArticle = typeArticle;
	}
	
	

}
