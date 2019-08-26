package com.gpro.consulting.tiers.gc.coordination.report.commandevente.value;

/**
 * @author Wahid Gazzah
 * @since 19/05/2016
 *
 */
public class ArticleBesoinElementReportValue implements Comparable<ArticleBesoinElementReportValue>{

	private Double besoin;
	private Long articleId;
	private String articleDesignation;
	private String tailleDesignation;
	private String couleurDesignation;
	
	@Override
	public int compareTo(ArticleBesoinElementReportValue element) {
		
		return articleDesignation.compareTo(element.getArticleDesignation());
	}

	public Double getBesoin() {
		return besoin;
	}

	public void setBesoin(Double besoin) {
		this.besoin = besoin;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
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
	
	
}
