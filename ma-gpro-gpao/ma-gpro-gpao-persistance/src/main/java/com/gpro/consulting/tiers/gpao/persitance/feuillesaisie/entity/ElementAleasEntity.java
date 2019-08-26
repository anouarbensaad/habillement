package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity;

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

/**
 * @author Hajer AMRI
 * @since 22/02/2017
 *
 */
@Entity
@Table(name = IConstante.TABLE_GP_ELEMENT_ALEAS)
public class ElementAleasEntity implements Serializable {

	private static final long serialVersionUID = 3299677062564801215L;

	@Id
	@SequenceGenerator(name = "ELEMENTALEAS_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_GP_ELEMENT_ALEAS, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ELEMENTALEAS_ID_GENERATOR")
	private Long id;

	@Column(name = "Duree")
	private Double duree;

	@Column(name = "GP_ALEAS_ID")
	private Long aleasId;

	@Column(name = "OBSERVATIONS")
	private String observations;

	@Column(name = "BL_SUPPRESSION")
	private boolean blSuppression;

	@Column(name = "DATE_SUPPRESSION")
	private Calendar dateSuppression;

	@Column(name = "DATE_CREATION")
	private Calendar dateCreation;

	@Column(name = "DATE_MODIFICATION")
	private Calendar dateModification;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GP_FEUILLE_ID")
	private FeuilleSaisieEntity feuilleSaisie;

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

	public FeuilleSaisieEntity getFeuilleSaisie() {
		return feuilleSaisie;
	}

	public void setFeuilleSaisie(FeuilleSaisieEntity feuilleSaisie) {
		this.feuilleSaisie = feuilleSaisie;
	}

	public Double getDuree() {
		return duree;
	}

	public void setDuree(Double duree) {
		this.duree = duree;
	}

	@Override
	public String toString() {
		return "ElementAleasEntity [id=" + id + ", duree=" + duree + ", aleasId=" + aleasId + ", observations="
				+ observations + ", blSuppression=" + blSuppression + "]";
	}


}
