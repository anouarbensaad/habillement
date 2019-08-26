package com.gpro.consulting.tiers.gc.coordination.report.commandevente.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Wahid Gazzah
 * @since 19/05/2016
 *
 */
public class ArticleBesoinReportValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private String produitDesignation;
	private String produitReference;
	private String clientAbreviation;
	
	private List<ArticleBesoinElementReportValue> listElementsTissu = new ArrayList<ArticleBesoinElementReportValue>();
	private List<ArticleBesoinElementReportValue> listElementsFourniture = new ArrayList<ArticleBesoinElementReportValue>();
	private List<ArticleBesoinElementReportValue> listElementsFil = new ArrayList<ArticleBesoinElementReportValue>();

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

	public JRBeanCollectionDataSource getJRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}

	public void setJRBeanCollectionDataSource(
			JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<ArticleBesoinElementReportValue> getListElementsTissu() {
		return listElementsTissu;
	}

	public void setListElementsTissu(
			List<ArticleBesoinElementReportValue> listElementsTissu) {
		this.listElementsTissu = listElementsTissu;
	}

	public List<ArticleBesoinElementReportValue> getListElementsFourniture() {
		return listElementsFourniture;
	}

	public void setListElementsFourniture(
			List<ArticleBesoinElementReportValue> listElementsFourniture) {
		this.listElementsFourniture = listElementsFourniture;
	}

	public List<ArticleBesoinElementReportValue> getListElementsFil() {
		return listElementsFil;
	}

	public void setListElementsFil(
			List<ArticleBesoinElementReportValue> listElementsFil) {
		this.listElementsFil = listElementsFil;
	}

	public String getProduitDesignation() {
		return produitDesignation;
	}

	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}

	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}

	public String getProduitReference() {
		return produitReference;
	}

	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}
	
	
}
