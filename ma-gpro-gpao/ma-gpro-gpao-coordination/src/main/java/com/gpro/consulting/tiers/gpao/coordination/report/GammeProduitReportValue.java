/**
 * 
 */
package com.gpro.consulting.tiers.gpao.coordination.report;

import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Ameni Berrich
 * @since 18/05/2016
 */
public class GammeProduitReportValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private Long id;
	private Double tempsTotal;
	private Long nbOperation;
	private String observations;
	private Calendar date;
	private Long produitId;

	private Set<GammeProduitElementReportValue> listGammeProduit = new TreeSet<GammeProduitElementReportValue>();

	//conversion Id > Designation
	private String referenceProduit;
	private String designationProduit;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTempsTotal() {
		return tempsTotal;
	}

	public void setTempsTotal(Double tempsTotal) {
		this.tempsTotal = tempsTotal;
	}

	public Long getNbOperation() {
		return nbOperation;
	}

	public void setNbOperation(Long nbOperation) {
		this.nbOperation = nbOperation;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public Set<GammeProduitElementReportValue> getListGammeProduit() {
		return listGammeProduit;
	}

	public void setListGammeProduit(
			Set<GammeProduitElementReportValue> listGammeProduit) {
		this.listGammeProduit = listGammeProduit;
	}

	public String getReferenceProduit() {
		return referenceProduit;
	}

	public void setReferenceProduit(String referenceProduit) {
		this.referenceProduit = referenceProduit;
	}

	public String getDesignationProduit() {
		return designationProduit;
	}

	public void setDesignationProduit(String designationProduit) {
		this.designationProduit = designationProduit;
	}
	
	
}
