package com.gpro.consulting.tiers.commun.coordination.report.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Zeineb
 * @since 04/11/2016
 *
 */
public class ProduitBesoinReportValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private String produitDesignation;
	private String produitReference;
	private String clientAbreviation;
	private String observations ;
	
	private List<ProduitBesoinElementReportValue> listElementsTissu = new ArrayList<ProduitBesoinElementReportValue>();
	private List<ProduitBesoinElementReportValue> listElementsFourniture = new ArrayList<ProduitBesoinElementReportValue>();
	private List<ProduitBesoinElementReportValue> listElementsFil = new ArrayList<ProduitBesoinElementReportValue>();

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

	public List<ProduitBesoinElementReportValue> getListElementsTissu() {
		return listElementsTissu;
	}

	public void setListElementsTissu(
			List<ProduitBesoinElementReportValue> listElementsTissu) {
		this.listElementsTissu = listElementsTissu;
	}

	public List<ProduitBesoinElementReportValue> getListElementsFourniture() {
		return listElementsFourniture;
	}

	public void setListElementsFourniture(
			List<ProduitBesoinElementReportValue> listElementsFourniture) {
		this.listElementsFourniture = listElementsFourniture;
	}

	public List<ProduitBesoinElementReportValue> getListElementsFil() {
		return listElementsFil;
	}

	public void setListElementsFil(
			List<ProduitBesoinElementReportValue> listElementsFil) {
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

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}
	
	
}
