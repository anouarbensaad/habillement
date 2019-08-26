package com.gpro.consulting.tiers.gpao.coordination.gammemontage.value;

import java.util.Calendar;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Wahid Gazzah
 * @since 07/04/2016
 *
 */

public class GammeProduitValue implements Comparable<GammeProduitValue>{
    private Long id;
	private Double tempsTotal;
	private Long nbOperation;
	private String observations;
	private Calendar date;
	private Long produitId;
	private Set<ElementGammeValue> listElementGamme;

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
	
	public int compareTo(GammeProduitValue element) {
		
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
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public Set<ElementGammeValue> getListElementGamme() {
		return listElementGamme;
	}
	public void setListElementGamme(Set<ElementGammeValue> listElementGamme) {
		this.listElementGamme = listElementGamme;
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

}
