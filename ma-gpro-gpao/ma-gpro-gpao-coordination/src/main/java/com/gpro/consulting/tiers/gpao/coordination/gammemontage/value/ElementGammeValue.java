package com.gpro.consulting.tiers.gpao.coordination.gammemontage.value;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Wahid Gazzah
 * @since 07/04/2016
 *
 */

public class ElementGammeValue implements Comparable<ElementGammeValue>{

    private Long id;

	private Double temps;
	private Long pdh;
	private String observations;
	private Boolean comptage;

	private Long operationId;
	private Long gammeProduitId;
	private Long sectionId;
	private Long machineId;
	
	private Long ordre;
	
	private String machineDesignation;
	private String sectionDesignation;
	private String operationDesignation;
	private String operationCode;

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
	
	public int compareTo(ElementGammeValue element) {
		
		return (ordre.compareTo(element.getOrdre()));
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOperationId() {
		return operationId;
	}
	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}
	public Long getGammeProduitId() {
		return gammeProduitId;
	}
	public void setGammeProduitId(Long gammeProduitId) {
		this.gammeProduitId = gammeProduitId;
	}
	public Double getTemps() {
		return temps;
	}
	public void setTemps(Double temps) {
		this.temps = temps;
	}
	public Long getPdh() {
		return pdh;
	}
	public void setPdh(Long pdh) {
		this.pdh = pdh;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public Long getMachineId() {
		return machineId;
	}
	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public Boolean getComptage() {
		return comptage;
	}
	public void setComptage(Boolean comptage) {
		this.comptage = comptage;
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
	public Long getOrdre() {
		return ordre;
	}
	public void setOrdre(Long ordre) {
		this.ordre = ordre;
	}

	public String getMachineDesignation() {
		return machineDesignation;
	}

	public void setMachineDesignation(String machineDesignation) {
		this.machineDesignation = machineDesignation;
	}

	public String getSectionDesignation() {
		return sectionDesignation;
	}

	public void setSectionDesignation(String sectionDesignation) {
		this.sectionDesignation = sectionDesignation;
	}

	public String getOperationDesignation() {
		return operationDesignation;
	}

	public void setOperationDesignation(String operationDesignation) {
		this.operationDesignation = operationDesignation;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	
	
}
