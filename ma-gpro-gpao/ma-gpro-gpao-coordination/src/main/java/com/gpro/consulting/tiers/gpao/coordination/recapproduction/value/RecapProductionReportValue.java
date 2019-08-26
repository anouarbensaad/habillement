package com.gpro.consulting.tiers.gpao.coordination.recapproduction.value;

import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Wahid Gazzah
 * @since 21/06/2016
 *
 */
public class RecapProductionReportValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private String partieInterresAbreviation;
	private String produitReference;
	private String produitDesignation;
	
	private Calendar dateLivraisonMin;
	private Calendar dateLivraisonMax;
	private Calendar dateIntroductionMin;
	private Calendar dateIntroductionMax;
	private String  statutDesignation;
	
	
	private Set<RecapProductionElementReportValue> recapProductionElementList = new TreeSet<RecapProductionElementReportValue>();

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

	public Set<RecapProductionElementReportValue> getRecapProductionElementList() {
		return recapProductionElementList;
	}

	public void setRecapProductionElementList(
			Set<RecapProductionElementReportValue> recapProductionElementList) {
		this.recapProductionElementList = recapProductionElementList;
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

	public Calendar getDateIntroductionMin() {
		return dateIntroductionMin;
	}

	public void setDateIntroductionMin(Calendar dateIntroductionMin) {
		this.dateIntroductionMin = dateIntroductionMin;
	}

	public Calendar getDateIntroductionMax() {
		return dateIntroductionMax;
	}

	public void setDateIntroductionMax(Calendar dateIntroductionMax) {
		this.dateIntroductionMax = dateIntroductionMax;
	}

	public String getPartieInterresAbreviation() {
		return partieInterresAbreviation;
	}

	public void setPartieInterresAbreviation(String partieInterresAbreviation) {
		this.partieInterresAbreviation = partieInterresAbreviation;
	}

	public String getProduitReference() {
		return produitReference;
	}

	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}

	public String getProduitDesignation() {
		return produitDesignation;
	}

	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}

	public String getStatutDesignation() {
		return statutDesignation;
	}

	public void setStatutDesignation(String statutDesignation) {
		this.statutDesignation = statutDesignation;
	}
	
}
