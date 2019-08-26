package com.gpro.consulting.tiers.gpao.persitance.entite;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gpao.coordination.IConstante;

@Entity
@Table(name = IConstante.TABLE_GP_CAUSEVARIATION)
public class CauseVariationEntitie implements Serializable {

	private static final long serialVersionUID = 6600062302464153166L;
	
	@Id
	@SequenceGenerator(name = "GPAO_CAUSEVARIATION_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_GP_CAUSEVARIATION, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GPAO_CAUSEVARIATION_ID_GENERATOR")
	private Long id;

	
	/** The Quantité. */
	@Column(name = "quantite")
	private Long quantite;

	/** Observation. */
	@Column(name = "observations")
	private String observations;

	/** Bl_Suppression. */
	@Column(name = "bl_suppression")
	private Boolean blSuppression;

	/** Date_Suppression. */
	@Column(name = "date_suppression")
	private Calendar dateSuppression;

	/* Date_Modification. */
	@Column(name = "date_modification")
	private Calendar dateModification;

	/* Date_Creation. */
	@Column(name = "date_creation")
	private Calendar dateCreation;
	
	/**
	 * join column phaseof
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gp_phaseof_id")
	private PhaseOfEntite phaseOfEntite;
/*
	*//**
	 * join column phaseof
	 *//*
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gp_causemanque_id")
	private CauseManqueEntitie manqueEntitie;
*/
	

	/************* Getters & Setters *****************/


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PhaseOfEntite getPhaseOfEntite() {
		return phaseOfEntite;
	}

	public void setPhaseOfEntite(PhaseOfEntite phaseOfEntite) {
		this.phaseOfEntite = phaseOfEntite;
	}

	/*public CauseManqueEntitie getManqueEntitie() {
		return manqueEntitie;
	}

	public void setManqueEntitie(CauseManqueEntitie manqueEntitie) {
		this.manqueEntitie = manqueEntitie;
	}*/

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
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

	public Calendar getDateModification() {
		return dateModification;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}



}
