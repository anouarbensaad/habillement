package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gpao.coordination.IConstante;

/**
 * @author Hajer AMRI
 * @since 22/02/2017
 *
 */
@Entity
@Table(name = IConstante.TABLE_GP_FAMILLE_ALEAS)
public class FamilleAleasEntity implements Serializable {

	private static final long serialVersionUID = 3299677062564801215L;

	@Id
	@SequenceGenerator(name = "FICHESAISIE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_GP_FAMILLE_ALEAS, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FICHESAISIE_ID_GENERATOR")
	private Long id;

	@Column(name = "DESIGNATION")
	private String designation;

	@Column(name = "BL_SUPPRESSION")
	private boolean blSuppression;

	@Column(name = "DATE_SUPPRESSION")
	private Calendar dateSuppression;

	@Column(name = "DATE_CREATION")
	private Calendar dateCreation;

	@Column(name = "DATE_MODIFICATION")
	private Calendar dateModification;

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

}
