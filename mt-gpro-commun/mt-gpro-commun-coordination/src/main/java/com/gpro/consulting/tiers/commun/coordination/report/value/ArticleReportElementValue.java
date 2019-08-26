package com.gpro.consulting.tiers.commun.coordination.report.value;

/**
 * @author Ameni Berrich
 * @since 07/04/2016
 */
public class ArticleReportElementValue {

	private String reference;
	private String designation;
	private Long sousFamilleArtId;
	private Double pu;
	private Double pmp;
	private Long siteId;
	
	//conversion id / designation
	private String sousFamilleArticleDesignation;
	private String familleArticleDesignation;
	private String typeArticleDesignation;
	private String siteDesignation;
	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Double getPu() {
		return pu;
	}
	public void setPu(Double pu) {
		this.pu = pu;
	}
	public Double getPmp() {
		return pmp;
	}
	public void setPmp(Double pmp) {
		this.pmp = pmp;
	}
	
	public Long getSousFamilleArtId() {
		return sousFamilleArtId;
	}
	public void setSousFamilleArtId(Long sousFamilleArtId) {
		this.sousFamilleArtId = sousFamilleArtId;
	}
	
	public String getSousFamilleArticleDesignation() {
		return sousFamilleArticleDesignation;
	}
	public void setSousFamilleArticleDesignation(
			String sousFamilleArticleDesignation) {
		this.sousFamilleArticleDesignation = sousFamilleArticleDesignation;
	}
	public String getFamilleArticleDesignation() {
		return familleArticleDesignation;
	}
	public void setFamilleArticleDesignation(String familleArticleDesignation) {
		this.familleArticleDesignation = familleArticleDesignation;
	}
	public String getTypeArticleDesignation() {
		return typeArticleDesignation;
	}
	public void setTypeArticleDesignation(String typeArticleDesignation) {
		this.typeArticleDesignation = typeArticleDesignation;
	}
	public Long getSiteId() {
		return siteId;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public String getSiteDesignation() {
		return siteDesignation;
	}
	public void setSiteDesignation(String siteDesignation) {
		this.siteDesignation = siteDesignation;
	}
	
}
