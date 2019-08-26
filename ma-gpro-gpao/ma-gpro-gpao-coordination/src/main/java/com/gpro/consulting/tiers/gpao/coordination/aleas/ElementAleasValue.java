package com.gpro.consulting.tiers.gpao.coordination.aleas;

import java.util.Calendar;

/**
 * @author Hajer AMRI
 * @since 22/02/2017
 */

public class ElementAleasValue implements Comparable<ElementAleasValue> {

	private Long id;
	private Double duree;
	private Long aleasId;
	private String observations;
	private boolean blSuppression;
	private Calendar dateSuppression;
	private Calendar dateCreation;
	private Calendar dateModification;
	private Long feuilleId;

	// Conversion id > designation
//	private String aleasDesignation;
//	private String feuilleDesignation;

	public int compareTo(ElementAleasValue element) {
		return (element.getId().compareTo(id));
	}

	public Double getDuree() {
		return duree;
	}

	public void setDuree(Double duree) {
		this.duree = duree;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAleasId() {
		return aleasId;
	}

	public void setAleasId(Long aleasId) {
		this.aleasId = aleasId;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public boolean isBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(boolean blSuppression) {
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

	public Long getFeuilleId() {
		return feuilleId;
	}

	public void setFeuilleId(Long feuilleId) {
		this.feuilleId = feuilleId;
	}

//	public String getAleasDesignation() {
//		return aleasDesignation;
//	}
//
//	public void setAleasDesignation(String aleasDesignation) {
//		this.aleasDesignation = aleasDesignation;
//	}
//
//	public String getFeuilleDesignation() {
//		return feuilleDesignation;
//	}
//
//	public void setFeuilleDesignation(String feuilleDesignation) {
//		this.feuilleDesignation = feuilleDesignation;
//	}

	@Override
	public String toString() {
		return "ElementAleasValue [id=" + id + ", duree=" + duree + ", aleasId=" + aleasId + ", observations="
				+ observations + ", blSuppression=" + blSuppression + ", feuilleId=" + feuilleId + "]";
	}

	// @JsonIgnore
	// private Boolean blSuppression;
	// @JsonIgnore
	// private Calendar dateSuppression;
	// @JsonIgnore
	// private Calendar dateCreation;
	// @JsonIgnore
	// private Calendar dateModification;
	// @JsonIgnore
	// private String version;

}
