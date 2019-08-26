package com.gpro.consulting.tiers.commun.coordination.report.value;

import java.util.List;

/**
 * @author Zeineb
 * @since 04/11/2016
 *
 */
public class ProduitBesoinElementReportValue implements Comparable<ProduitBesoinElementReportValue>{

	private Double besoin;
	private Long articleId;
	private String articleDesignation;
	private String articleReference;
	private String taillesAffiches;
	private String couleursAffiches;
	private List<String> listTailles;
	private List<String> listCouleurs;
	
	
	public ProduitBesoinElementReportValue() {
		// TODO Auto-generated constructor stub
	}
	
	public ProduitBesoinElementReportValue(Double besoin, Long articleId, String articleDesignation,
			String articleReference, String taillesAffiches, String couleursAffiches, List<String> listTailles,
			List<String> listCouleurs) {
		super();
		this.besoin = besoin;
		this.articleId = articleId;
		this.articleDesignation = articleDesignation;
		this.articleReference = articleReference;
		this.taillesAffiches = taillesAffiches;
		this.couleursAffiches = couleursAffiches;
		this.listTailles = listTailles;
		this.listCouleurs = listCouleurs;
	}



	@Override
	public int compareTo(ProduitBesoinElementReportValue element) {
		
		return articleDesignation.compareTo(element.getArticleDesignation());
	}

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

	public String getArticleReference() {
		return articleReference;
	}

	public void setArticleReference(String articleReference) {
		this.articleReference = articleReference;
	}

	public String getTaillesAffiches() {
		return taillesAffiches;
	}

	public void setTaillesAffiches(String taillesAffiches) {
		this.taillesAffiches = taillesAffiches;
	}

	public String getCouleursAffiches() {
		return couleursAffiches;
	}

	public void setCouleursAffiches(String couleursAffiches) {
		this.couleursAffiches = couleursAffiches;
	}

	public List<String> getListTailles() {
		return listTailles;
	}

	public void setListTailles(List<String> listTailles) {
		this.listTailles = listTailles;
	}

	public List<String> getListCouleurs() {
		return listCouleurs;
	}

	public void setListCouleurs(List<String> listCouleurs) {
		this.listCouleurs = listCouleurs;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	@Override
	public String toString() {
		return "ProduitBesoinElementReportValue [besoin=" + besoin + ", articleId=" + articleId
				+ ", articleDesignation=" + articleDesignation + ", articleReference=" + articleReference
				+ ", taillesAffiches=" + taillesAffiches + ", couleursAffiches=" + couleursAffiches + ", listTailles="
				+ listTailles + ", listCouleurs=" + listCouleurs + "]";
	}


}
