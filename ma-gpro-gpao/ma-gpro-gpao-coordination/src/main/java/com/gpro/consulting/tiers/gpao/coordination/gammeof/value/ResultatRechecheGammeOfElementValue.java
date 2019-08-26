package com.gpro.consulting.tiers.gpao.coordination.gammeof.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 10/05/2016
 *
 */
public class ResultatRechecheGammeOfElementValue implements Comparable<ResultatRechecheGammeOfElementValue>{
	
    private Long id;
	private Double tempsTotal;
	private Long nbOperation;
	private String observations;
	private Calendar date;
	
	private Long ordreFabricationId;
	private String produitReference;
	private String produitDesignation;
	private String ordreFabricationNumero;
	
	private String clientAbreviation;
	private String clientReference;
	
	public int compareTo(ResultatRechecheGammeOfElementValue element) {
		
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

	public String getOrdreFabricationNumero() {
		return ordreFabricationNumero;
	}

	public void setOrdreFabricationNumero(String ordreFabricationNumero) {
		this.ordreFabricationNumero = ordreFabricationNumero;
	}

	public Long getOrdreFabricationId() {
		return ordreFabricationId;
	}

	public void setOrdreFabricationId(Long ordreFabricationId) {
		this.ordreFabricationId = ordreFabricationId;
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

	public String getClientReference() {
		return clientReference;
	}

	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}

	
}
