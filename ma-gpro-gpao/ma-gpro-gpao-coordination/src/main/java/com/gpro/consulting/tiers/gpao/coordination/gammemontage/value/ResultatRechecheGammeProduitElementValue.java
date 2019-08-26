package com.gpro.consulting.tiers.gpao.coordination.gammemontage.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 13/04/2016
 *
 */
public class ResultatRechecheGammeProduitElementValue implements Comparable<ResultatRechecheGammeProduitElementValue>{
	
    private Long id;
	private Double tempsTotal;
	private Long nbOperation;
	private String observations;
	private Calendar date;
	
	private Long produitId;
	private String produitReference;
	private String produitDesignation;
	
	public int compareTo(ResultatRechecheGammeProduitElementValue element) {
		
		return (element.getId().compareTo(id));
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
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	
	

}
