package com.gpro.consulting.tiers.gc.persitance.agent.entite;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gc.coordination.IConstanteGC;

/**
 * @author Ameni Berrich
 *
 */
@Entity
@Table(name=IConstanteGC.TABLE_GC_AGENT)
public class AgentEntity implements Serializable{

	private static final long serialVersionUID = -1243472453035281227L;

	@Id
	@SequenceGenerator(name="AGENT_ID_GENERATOR", sequenceName=IConstanteGC.SEQUENCE_GC_AGENT,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AGENT_ID_GENERATOR")
    private Long id;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="observations")
	private String observations;
	
	@Column(name="date_creation")
	private Calendar dateCreation;
	
	@Column(name="date_modification")
	private Calendar dateModification;

	@Column(name="date_suppression")
	private Calendar dateSuppression;
	
	@Column(name="bl_suppression")
	private boolean blSuppression;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
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

	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public boolean isBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}
	   
}
