package com.gpro.consulting.tiers.gpao.coordination.report.reporting.production;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Hamdi Etteieb
 *
 */

public class ProductionReportValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private Calendar dateDe;
	private Long pointDeControle;
	private Calendar dateA;
	private String chaine;
	private String clientDesignation;
    private String produit;
    private String pointDesignation;
    
    private Long qteProduite;
    
    
    
    
	public Long getQteProduite() {
		return qteProduite;
	}

	public void setQteProduite(Long qteProduite) {
		this.qteProduite = qteProduite;
	}

	public String getPointDesignation() {
		return pointDesignation;
	}

	public void setPointDesignation(String pointDesignation) {
		this.pointDesignation = pointDesignation;
	}

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}

	private List<ProductionElementReportValue> listProduction = new ArrayList<ProductionElementReportValue>();

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

	public Calendar getDateDe() {
		return dateDe;
	}

	public void setDateDe(Calendar dateDe) {
		this.dateDe = dateDe;
	}

	public Calendar getDateA() {
		return dateA;
	}

	public void setDateA(Calendar dateA) {
		this.dateA = dateA;
	}



	public List<ProductionElementReportValue> getListProduction() {
		return listProduction;
	}

	public void setListProduction(List<ProductionElementReportValue> listProduction) {
		this.listProduction = listProduction;
	}

	public String getClientDesignation() {
		return clientDesignation;
	}

	public void setClientDesignation(String clientDesignation) {
		this.clientDesignation = clientDesignation;
	}

	public Long getPointDeControle() {
		return pointDeControle;
	}

	public void setPointDeControle(Long pointDeControle) {
		this.pointDeControle = pointDeControle;
	}

	public String getChaine() {
		return chaine;
	}

	public void setChaine(String chaine) {
		this.chaine = chaine;
	}

}
