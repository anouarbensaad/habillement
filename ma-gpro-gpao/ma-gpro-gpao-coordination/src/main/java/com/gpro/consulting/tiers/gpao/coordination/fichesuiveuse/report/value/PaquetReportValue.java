package com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.report.value;

import java.util.HashSet;
import java.util.Set;

import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.PaquetValue;

/**
 * @author Wahid Gazzah
 * @since 20/05/2016
 *
 */
public class PaquetReportValue  implements Comparable<PaquetReportValue>{

	private String numOrdreFabrication;
	private Long numPaquet;
	private Long quantite;
	private String couleurDesignation;
	private String tailleDesignation;
	
	private String produitDesignation;
	private String produitReference;
	private String clientAbreviation;
	private String clientReference;
	private Long quantiteOF;
	
	
	public Long getQuantiteOF() {
		return quantiteOF;
	}
	public void setQuantiteOF(Long quantiteOF) {
		this.quantiteOF = quantiteOF;
	}
	private Set<OperationReportValue> operationsList = new HashSet<OperationReportValue>();
	
	public String getNumOrdreFabrication() {
		return numOrdreFabrication;
	}
	public void setNumOrdreFabrication(String numOrdreFabrication) {
		this.numOrdreFabrication = numOrdreFabrication;
	}
	public Long getNumPaquet() {
		return numPaquet;
	}
	public void setNumPaquet(Long numPaquet) {
		this.numPaquet = numPaquet;
	}
	public String getCouleurDesignation() {
		return couleurDesignation;
	}
	public void setCouleurDesignation(String couleurDesignation) {
		this.couleurDesignation = couleurDesignation;
	}
	public String getTailleDesignation() {
		return tailleDesignation;
	}
	public void setTailleDesignation(String tailleDesignation) {
		this.tailleDesignation = tailleDesignation;
	}
	public Long getQuantite() {
		return quantite;
	}
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}
	public Set<OperationReportValue> getOperationsList() {
		return operationsList;
	}
	public void setOperationsList(Set<OperationReportValue> operationsList) {
		this.operationsList = operationsList;
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
	public int compareTo(PaquetReportValue element) {
		return (numPaquet.compareTo(element.getNumPaquet()));
	}
	
}
