package com.gpro.consulting.tiers.gc.coordination.report.commandevente.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Wahid Gazzah
 * @since 20/04/2016
 */
public class BesoinArticleReportValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private String refCommande;
	private Calendar dateLivraisonMin;
	private Calendar dateLivraisonMax;
	private Calendar dateCommandeMin;
	private Calendar dateCommandeMax;
	
	private String produitDesignation;
	private String clientAbreviation;
	
	private Double total;
	
	private List<BesoinArticleElementReportValue> listBesoinArticleElements = new ArrayList<BesoinArticleElementReportValue>();

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

	public List<BesoinArticleElementReportValue> getListBesoinArticleElements() {
		return listBesoinArticleElements;
	}

	public void setListBesoinArticleElements(
			List<BesoinArticleElementReportValue> listBesoinArticleElements) {
		this.listBesoinArticleElements = listBesoinArticleElements;
	}

	public JRBeanCollectionDataSource getjRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}

	public void setjRBeanCollectionDataSource(
			JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	public String getRefCommande() {
		return refCommande;
	}

	public void setRefCommande(String refCommande) {
		this.refCommande = refCommande;
	}
	
	public Calendar getDateLivraisonMin() {
		return dateLivraisonMin;
	}
	
	public void setDateLivraisonMin(Calendar dateLivraisonMin) {
		this.dateLivraisonMin = dateLivraisonMin;
	}
	
	public Calendar getDateLivraisonMax() {
		return dateLivraisonMax;
	}
	
	public void setDateLivraisonMax(Calendar dateLivraisonMax) {
		this.dateLivraisonMax = dateLivraisonMax;
	}
	
	public Calendar getDateCommandeMin() {
		return dateCommandeMin;
	}
	
	public void setDateCommandeMin(Calendar dateCommandeMin) {
		this.dateCommandeMin = dateCommandeMin;
	}
	
	public Calendar getDateCommandeMax() {
		return dateCommandeMax;
	}
	
	public void setDateCommandeMax(Calendar dateCommandeMax) {
		this.dateCommandeMax = dateCommandeMax;
	}
	
	public Double getTotal() {
		return total;
	}
	
	public void setTotal(Double total) {
		this.total = total;
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

}
