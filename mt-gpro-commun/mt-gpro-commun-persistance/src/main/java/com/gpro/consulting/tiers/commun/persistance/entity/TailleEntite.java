package com.gpro.consulting.tiers.commun.persistance.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

/**
* Classe: Taille
* @author $AMENI
* 
*/
@Entity
@Table(name = IConstante.TABLE_EB_TAILLE)
public class TailleEntite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7337368850482167754L;

	@Id
	@SequenceGenerator(name="TAILLE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_EB_TAILLE,allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="TAILLE_ID_GENERATOR")
	private Long id;
	
	/** The designation. */
	@Column(name = "designation")
	private String designation;

	/** The bl suppression. */
	@Column(name = "bl_suppression")
	private boolean blSuppression;

	/** The date suppression. */
	@Column(name = "date_suppression")
	private Calendar dateSuppression;

	/** The date creation. */
	@Column(name = "date_creation")
	private Calendar dateCreation;

	/** The date modification. */
	@Column(name = "date_modification")
	private Calendar dateModification;
	
	/** The eb_sttaille_id . */
	@Column(name = "eb_sttaille_id ")
	private long eb_sttaille_id ;
	
	/** the ordre .*/
	private Integer  ordre;

	/************* Getters & Setters *****************/
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

	public long getEb_sttaille_id() {
		return eb_sttaille_id;
	}

	public void setEb_sttaille_id(long eb_sttaille_id) {
		this.eb_sttaille_id = eb_sttaille_id;
	}

	/**
	 * @return the ordre
	 */
	

	/************* Equals & ToString *****************/
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}	
	
}
