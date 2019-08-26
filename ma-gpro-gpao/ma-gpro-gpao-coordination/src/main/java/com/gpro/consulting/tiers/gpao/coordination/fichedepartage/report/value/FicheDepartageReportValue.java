package com.gpro.consulting.tiers.gpao.coordination.fichedepartage.report.value;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Wahid Gazzah
 * @since 24/05/2016
 *
 */
public class FicheDepartageReportValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private Long quantiteTotal;
	private String produitDesignation;
	private String produitReference;
	private String numOrdreFabrication;
	private String clientAbreviation;
	private String clientReference;
	
	private Set<FicheDepartageElementReportValue> paquetsList = new TreeSet<FicheDepartageElementReportValue>();

	
	/**
	 * @author Zeineb Medimagh
	 * @since 29/11/2016
	 * @return
	 */
	private Long quantiteDemandee;
	
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

	public String getProduitDesignation() {
		return produitDesignation;
	}

	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}

	public String getProduitReference() {
		return produitReference;
	}

	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}

	public String getNumOrdreFabrication() {
		return numOrdreFabrication;
	}

	public void setNumOrdreFabrication(String numOrdreFabrication) {
		this.numOrdreFabrication = numOrdreFabrication;
	}

	public Set<FicheDepartageElementReportValue> getPaquetsList() {
		return paquetsList;
	}

	public void setPaquetsList(Set<FicheDepartageElementReportValue> paquetsList) {
		this.paquetsList = paquetsList;
	}

	public Long getQuantiteTotal() {
		return quantiteTotal;
	}

	public void setQuantiteTotal(Long quantiteTotal) {
		this.quantiteTotal = quantiteTotal;
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}

	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}

	public String getClientReference() {
		return clientReference;
	}

	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}

	public Long getQuantiteDemandee() {
		return quantiteDemandee;
	}

	public void setQuantiteDemandee(Long quantiteDemandee) {
		this.quantiteDemandee = quantiteDemandee;
	}

}
