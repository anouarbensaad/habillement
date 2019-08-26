package com.gpro.consulting.tiers.gs.persitance.entite;

import java.io.Serializable;

import javax.persistence.*;

import com.gpro.consulting.tiers.gs.coordination.IConstante;

import java.util.Calendar;

/**
 * The Class RaisonMouvementEntite.
 * @author mohamed
 */
@Entity
@Table(name=IConstante.TABLE_RAISON_MOUVEMENT)
public class RaisonMouvementEntite implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8070562870505044569L;

	/** The id. */
	@Id
	@SequenceGenerator(name="GS_RAISONMOUVEMENT_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_RAISON_MOUVEMENT)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GS_RAISONMOUVEMENT_ID_GENERATOR")
	private Long id;

	
	/** The designation. */
	@Column(name="designation")
	private String designation;

	/** The bl suppression. */
	@Column(name="bl_suppression")
	private Boolean blSuppression;

	/** The date creation. */
	@Column(name="date_creation")
	private Calendar dateCreation;

	/** The date modification. */
	@Column(name="date_modification")
	private Calendar dateModification;

	/** The date suppression. */
	@Column(name="date_suppression")
	private Calendar dateSuppression;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the designation.
	 *
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * Sets the designation.
	 *
	 * @param designation the new designation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * Gets the bl suppression.
	 *
	 * @return the bl suppression
	 */
	public Boolean getBlSuppression() {
		return blSuppression;
	}

	/**
	 * Sets the bl suppression.
	 *
	 * @param blSuppression the new bl suppression
	 */
	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	/**
	 * Gets the date creation.
	 *
	 * @return the date creation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * Sets the date creation.
	 *
	 * @param dateCreation the new date creation
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Gets the date modification.
	 *
	 * @return the date modification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}

	/**
	 * Sets the date modification.
	 *
	 * @param dateModification the new date modification
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	/**
	 * Gets the date suppression.
	 *
	 * @return the date suppression
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	/**
	 * Sets the date suppression.
	 *
	 * @param dateSuppression the new date suppression
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	
	
	

}