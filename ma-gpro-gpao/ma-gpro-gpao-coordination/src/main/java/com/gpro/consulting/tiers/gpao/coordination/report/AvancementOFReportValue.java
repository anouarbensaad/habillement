package com.gpro.consulting.tiers.gpao.coordination.report;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ElementGammeOfValue;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Ameni Berrich
 *
 */
public class AvancementOFReportValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private Double tempsTotal;
	private Long nbOperation;
	private String observations;
	
	private Long ordreFabricationId;
	private String ordreFabricationNumero;
	
	private Long produitId;
	private String produitReference;
	private String produitDesignation;
	
	private Long clientId;
	private String clientAbreviation;
	private String clientReference;
	private Long quantiteOF;
	
	
	private Set<ElementGammeOfValue> listElementGammeOf = new TreeSet<ElementGammeOfValue>();

	/**
	 * @author Zeineb Medimagh
	 * @since 30/11/2016 
	 */
	private Long quantiteEclatee;
	
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

	public Long getOrdreFabricationId() {
		return ordreFabricationId;
	}

	public void setOrdreFabricationId(Long ordreFabricationId) {
		this.ordreFabricationId = ordreFabricationId;
	}

	public String getOrdreFabricationNumero() {
		return ordreFabricationNumero;
	}

	public void setOrdreFabricationNumero(String ordreFabricationNumero) {
		this.ordreFabricationNumero = ordreFabricationNumero;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
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

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
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

	public Set<ElementGammeOfValue> getListElementGammeOf() {
		return listElementGammeOf;
	}

	public void setListElementGammeOf(Set<ElementGammeOfValue> listElementGammeOf) {
		this.listElementGammeOf = listElementGammeOf;
	}

	public Long getQuantiteOF() {
		return quantiteOF;
	}

	public void setQuantiteOF(Long quantiteOF) {
		this.quantiteOF = quantiteOF;
	}

	public Long getQuantiteEclatee() {
		return quantiteEclatee;
	}

	public void setQuantiteEclatee(Long quantiteEclatee) {
		this.quantiteEclatee = quantiteEclatee;
	}
	
}
