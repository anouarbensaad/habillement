package com.gpro.consulting.tiers.gpao.coordination.gammeof.value;

import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Wahid Gazzah
 * @since 10/05/2016
 *
 */

public class ElementGammeOfValue implements Comparable<ElementGammeOfValue>{

    private Long id;

	private Double temps;
	private Long pdh;
	private String observations;
	private Boolean comptage;

	private Long operationId;
	private Long gammeOfId;
	private Long sectionId;
	private Long machineId;
	
	private Long ordre;
	
	private String machineDesignation;
	private String sectionDesignation;
	private String operationDesignation;
	private String operationCode;
	private Long qteProduite;
	private Double minutage;
	private Long reste;

	private Long resteEclate;
	
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
	
	private List<JourProductionAvancementOFValue> listJourProductionGammeOf;
	private List<DetailTailleCouleurAvancementOFValue> listTailleCouleurGammeOf;
	
	
	public int compareTo(ElementGammeOfValue o) {
		ElementGammeOfValue element= (ElementGammeOfValue)o;
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
	public Long getGammeOfId() {
		return gammeOfId;
	}
	public void setGammeOfId(Long gammeOfId) {
		this.gammeOfId = gammeOfId;
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

	public Long getQteProduite() {
		return qteProduite;
	}

	public void setQteProduite(Long qteProduite) {
		this.qteProduite = qteProduite;
	}

	public Double getMinutage() {
		return minutage;
	}

	public void setMinutage(Double minutage) {
		this.minutage = minutage;
	}

	public Long getReste() {
		return reste;
	}

	public void setReste(Long reste) {
		this.reste = reste;
	}

	public List<JourProductionAvancementOFValue> getListJourProductionGammeOf() {
		return listJourProductionGammeOf;
	}

	public void setListJourProductionGammeOf(
			List<JourProductionAvancementOFValue> listJourProductionGammeOf) {
		this.listJourProductionGammeOf = listJourProductionGammeOf;
	}

	public List<DetailTailleCouleurAvancementOFValue> getListTailleCouleurGammeOf() {
		return listTailleCouleurGammeOf;
	}

	public void setListTailleCouleurGammeOf(
			List<DetailTailleCouleurAvancementOFValue> listTailleCouleurGammeOf) {
		this.listTailleCouleurGammeOf = listTailleCouleurGammeOf;
	}

	public Long getResteEclate() {
		return resteEclate;
	}

	public void setResteEclate(Long resteEclate) {
		this.resteEclate = resteEclate;
	}
	
}
