/**
 * 
 */
package com.gpro.consulting.tiers.commun.coordination.report.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Ameni Berrich
 * @since 07/04/2016
 */
public class ArticlesReportListValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private List<ArticleReportElementValue> articlesList = new ArrayList <ArticleReportElementValue>();
	
	/** The ref. */
	private String ref;
	/** The designation. */
	private String designation ;
	/** The type. */
	private Long typeEntite;
	/** The sous famille. */
	private Long sousFamilleEntite;
	/** The famille. */
	private Long familleEntite;
	/** The site. */
	private Long site;
	/** The prix inf. */
	private Double prix_inf;
	/** The prix sup. */
	private Double prix_sup;
	
	//Conversion id/Designation
	private String typeArticleDesignation;
	private String sousFamilleArticleDesignation;
	private String familleArticleDesignation;
	private String siteArticleDesignation;
	
	public InputStream getReportStream() {
		return reportStream;
	}
	public void setReportStream(InputStream reportStream) {
		this.reportStream = reportStream;
	}
	public HashMap<String, Object> getParams() {
		return params;
	}
	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}
	public JRBeanCollectionDataSource getjRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}
	public void setjRBeanCollectionDataSource(
			JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<ArticleReportElementValue> getArticlesList() {
		return articlesList;
	}
	public void setArticlesList(List<ArticleReportElementValue> articlesList) {
		this.articlesList = articlesList;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Long getTypeEntite() {
		return typeEntite;
	}
	public void setTypeEntite(Long typeEntite) {
		this.typeEntite = typeEntite;
	}
	public Long getSousFamilleEntite() {
		return sousFamilleEntite;
	}
	public void setSousFamilleEntite(Long sousFamilleEntite) {
		this.sousFamilleEntite = sousFamilleEntite;
	}
	public Long getFamilleEntite() {
		return familleEntite;
	}
	public void setFamilleEntite(Long familleEntite) {
		this.familleEntite = familleEntite;
	}
	public Long getSite() {
		return site;
	}
	public void setSite(Long site) {
		this.site = site;
	}
	public Double getPrix_inf() {
		return prix_inf;
	}
	public void setPrix_inf(Double prix_inf) {
		this.prix_inf = prix_inf;
	}
	public Double getPrix_sup() {
		return prix_sup;
	}
	public void setPrix_sup(Double prix_sup) {
		this.prix_sup = prix_sup;
	}
	public String getTypeArticleDesignation() {
		return typeArticleDesignation;
	}
	public void setTypeArticleDesignation(String typeArticleDesignation) {
		this.typeArticleDesignation = typeArticleDesignation;
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
	public String getSiteArticleDesignation() {
		return siteArticleDesignation;
	}
	public void setSiteArticleDesignation(String siteArticleDesignation) {
		this.siteArticleDesignation = siteArticleDesignation;
	}
	
}
