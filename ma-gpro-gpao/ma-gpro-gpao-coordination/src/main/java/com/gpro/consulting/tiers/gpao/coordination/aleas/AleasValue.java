package com.gpro.consulting.tiers.gpao.coordination.aleas;

import java.util.Calendar;

/**
 * @author Hajer AMRI
 * @since 22/02/2017
 */

public class AleasValue {

	private Long id;
	private String designation;
	private Long familleId;
	private boolean blSuppression;
	private Calendar dateSuppression;
	private Calendar dateCreation;
	private Calendar dateModification;

	// Conversion id > designation
	private String familleDesignation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Long getFamilleId() {
		return familleId;
	}

	public void setFamilleId(Long familleId) {
		this.familleId = familleId;
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

	public String getFamilleDesignation() {
		return familleDesignation;
	}

	public void setFamilleDesignation(String familleDesignation) {
		this.familleDesignation = familleDesignation;
	}

	public boolean isBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

}
