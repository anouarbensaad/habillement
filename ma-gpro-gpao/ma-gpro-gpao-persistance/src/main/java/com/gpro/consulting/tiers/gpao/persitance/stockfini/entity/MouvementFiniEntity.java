package com.gpro.consulting.tiers.gpao.persitance.stockfini.entity;

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
 * @author Samer Hassen
 *
 */
@Entity
@Table(name=IConstante.TABLE_GP_MOUVEMENT_FINI)
public class MouvementFiniEntity implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -7133721560774729188L;


	@Id
	@SequenceGenerator(name="MOUVEMENTFINI_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_GP_MOUVEMENT_FINI, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MOUVEMENTFINI_ID_GENERATOR")
    private Long id;
	
	@Column(name = "numero_bon")
	private String numeroBon ;
	
	@Column(name = "numero_of")
	private String numeroOf ;
	
	@Column(name = "detail_of_id")
	private Long detailOfId;
	
	
	@Column(name = "DATE")
	private Calendar date;
	
	@Column(name = "type")
	private String type ;
		
	@Column(name = "QUANTITE")
	private Long quantite;
	

	
	@Column(name = "BL_SUPPRESSION")
	private Boolean blSuppression ;
	
	@Column(name = "DATE_SUPPRESSION")
	private Calendar dateSuppression;  
	
	@Column(name = "DATE_MODIFICATION")
	private Calendar dateModification;  
	   
	@Column(name = "DATE_CREATION")
	private Calendar dateCreation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroBon() {
		return numeroBon;
	}

	public void setNumeroBon(String numeroBon) {
		this.numeroBon = numeroBon;
	}

	public Long getDetailOfId() {
		return detailOfId;
	}

	public void setDetailOfId(Long detailOfId) {
		this.detailOfId = detailOfId;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
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

	public String getNumeroOf() {
		return numeroOf;
	}

	public void setNumeroOf(String numeroOf) {
		this.numeroOf = numeroOf;
	}

	

}
