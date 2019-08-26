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
* Classe: DetProdArticle
* @author $AMENI
* 
*/
@Entity
@Table(name = IConstante.TABLE_EB_DETPROD_ARTICLE)
public class DetProdArticleEntite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7337368850482167754L;

	@Id
	@SequenceGenerator(name="DETPRODARTICLE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_EB_GROSSEUR,allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="DETPRODARTICLE_ID_GENERATOR")
	private Long id;
	
	/** The quantite. */
	@Column(name = "quantite")
	private long quantite;

	/** The bloquante. */
	@Column(name = "bloquante")
	private boolean bloquante;
	
	/** The ordre. */
	@Column(name = "ordre")
	private long ordre;
	
	/** The phase. */
	@Column(name = "phase")
	private String phase;
	
	/** The eb_article_id. */
	@Column(name = "eb_article_id")
	private long eb_article_id;
	
	/** The eb_produitdet_id. */
	@Column(name = "eb_produitdet_id")
	private long eb_produitdet_id;
	
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
	
	/************* Getters & Setters *****************/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getQuantite() {
		return quantite;
	}

	public void setQuantite(long quantite) {
		this.quantite = quantite;
	}

	public boolean isBloquante() {
		return bloquante;
	}

	public void setBloquante(boolean bloquante) {
		this.bloquante = bloquante;
	}

	public long getOrdre() {
		return ordre;
	}

	public void setOrdre(long ordre) {
		this.ordre = ordre;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public long getEb_article_id() {
		return eb_article_id;
	}

	public void setEb_article_id(long eb_article_id) {
		this.eb_article_id = eb_article_id;
	}

	public long getEb_produitdet_id() {
		return eb_produitdet_id;
	}

	public void setEb_produitdet_id(long eb_produitdet_id) {
		this.eb_produitdet_id = eb_produitdet_id;
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

	/************* Equals & ToString *****************/
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}	
}
