package com.gpro.consulting.tiers.gpao.coordination.gammeof.value;

import java.util.Calendar;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Wahid Gazzah
 * @since 10/05/2016
 *
 */
public class GammeOfValue implements Comparable<GammeOfValue>{
	
    private Long id;
	private Double tempsTotal;
	private Long nbOperation;
	private Double tempsTotalProduit;
	private Long nbOperationProduit;
	private String observations;
	private Calendar date;
	
	private Long ordreFabricationId;
	private String ordreFabricationNumero;
	
	private Long produitId;
	private String produitReference;
	private String produitDesignation;
	
	private Long clientId;
	private String clientAbreviation;
	private String clientReference;
	
	private Set<ElementGammeOfValue> listElementGammeOf;

	@JsonIgnore
	private Boolean blSuppression;
	@JsonIgnore
	private Calendar dateSuppression;
	@JsonIgnore
	private Calendar dateCreation;
	@JsonIgnore
	private Calendar dateModification;
	@JsonIgnore
	private String version;
	
	public int compareTo(GammeOfValue element) {
		
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
	public Long getOrdreFabricationId() {
		return ordreFabricationId;
	}
	public void setOrdreFabricationId(Long ordreFabricationId) {
		this.ordreFabricationId = ordreFabricationId;
	}
	public Set<ElementGammeOfValue> getListElementGammeOf() {
		return listElementGammeOf;
	}
	public void setListElementGammeOf(Set<ElementGammeOfValue> listElementGammeOf) {
		this.listElementGammeOf = listElementGammeOf;
	}
	public Boolean getBlSuppression() {
		return blSuppression;
	}
	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}
	public Calendar getDateSuppression() {
		return dateSuppression;
	}
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}
	public Calendar getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Calendar getDateModification() {
		return dateModification;
	}
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public Double getTempsTotalProduit() {
		return tempsTotalProduit;
	}

	public void setTempsTotalProduit(Double tempsTotalProduit) {
		this.tempsTotalProduit = tempsTotalProduit;
	}

	public Long getNbOperationProduit() {
		return nbOperationProduit;
	}

	public void setNbOperationProduit(Long nbOperationProduit) {
		this.nbOperationProduit = nbOperationProduit;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public String getOrdreFabricationNumero() {
		return ordreFabricationNumero;
	}

	public void setOrdreFabricationNumero(String ordreFabricationNumero) {
		this.ordreFabricationNumero = ordreFabricationNumero;
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
	

}
