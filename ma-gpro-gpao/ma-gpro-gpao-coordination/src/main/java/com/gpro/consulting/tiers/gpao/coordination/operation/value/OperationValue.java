package com.gpro.consulting.tiers.gpao.coordination.operation.value;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Wahid Gazzah
 * @since 07/04/2016
 *
 */

public class OperationValue implements Comparable<OperationValue>{

	private Long id;
	private String code;
	private String designation;
	private Double temps;
	private Long pdh;
	private String observations;
	private Long machineId;
	private Long sectionId;
	
	private String machineDesignation;
	private String sectionDesignation;
	
	@JsonIgnore
	private Long ordreElementGammeOf;
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
	private boolean comptage;
	
	public int compareTo(OperationValue element) {

		return (element.getId().compareTo(id));
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
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
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public Long getMachineId() {
		return machineId;
	}
	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
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

	public Long getOrdreElementGammeOf() {
		return ordreElementGammeOf;
	}

	public void setOrdreElementGammeOf(Long ordreElementGammeOf) {
		this.ordreElementGammeOf = ordreElementGammeOf;
	}

	public boolean isComptage() {
		return comptage;
	}

	public void setComptage(boolean comptage) {
		this.comptage = comptage;
	}
	

}
