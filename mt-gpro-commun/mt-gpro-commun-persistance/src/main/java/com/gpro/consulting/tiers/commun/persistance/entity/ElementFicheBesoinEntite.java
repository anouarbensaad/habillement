package com.gpro.consulting.tiers.commun.persistance.entity;

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

import com.gpro.consulting.tiers.commun.coordination.IConstante;


@Entity
@Table(name=IConstante.TABLE_ELEMENT_Fiche_BESOIN)
public class ElementFicheBesoinEntite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8805509752149672712L;

	@Id
	@SequenceGenerator(name="EB_ELEMENT_FICHE_BESOIN_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_ELEMENT_FICHE_BESOIN,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EB_ELEMENT_FICHE_BESOIN_ID_GENERATOR")
    private Long id;	
	
	private double quantite;
	
	@Column(name="bloquante")
	private boolean bloQuatite;
	
	private int ordre;
	
	private String phase;
	
	@Column(name="bl_suppression")
	private boolean blSuppression;
	
	@Column(name="date_creation")
	private Calendar dateCreation;
	
	@Column(name="date_modification")
	private Calendar dateModification;
	
	@Column(name="date_suppression")
	private Calendar dateSuppression;
	
	/** * many-to-one association to Produit*. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "eb_fichebesoin_id")
    private FicheBesoinEntite ficheBesoin;	

//	@ManyToOne
//	@JoinColumn(name = "eb_taille_id")
//    private TailleEntite taille;	
//
//	@ManyToOne
//	@JoinColumn(name = "eb_couleur_id")
//    private CouleurEntite couleur;	
//	
//	@ManyToOne
//	@JoinColumn(name = "eb_article_id")
//    private ArticleEntite article;

    private Long eb_taille_id;	

    private Long eb_couleur_id;	
	
    private Long eb_article_id;
    
  //Added on 07/04/2016, By Ameni Berrich
  	private String   type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	public boolean isBloQuatite() {
		return bloQuatite;
	}

	public void setBloQuatite(boolean bloQuatite) {
		this.bloQuatite = bloQuatite;
	}

	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public boolean isBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
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

	public FicheBesoinEntite getFicheBesoin() {
		return ficheBesoin;
	}

	public void setFicheBesoin(FicheBesoinEntite ficheBesoin) {
		this.ficheBesoin = ficheBesoin;
	}

	public Long getEb_taille_id() {
		return eb_taille_id;
	}

	public void setEb_taille_id(Long eb_taille_id) {
		this.eb_taille_id = eb_taille_id;
	}

	public Long getEb_couleur_id() {
		return eb_couleur_id;
	}

	public void setEb_couleur_id(Long eb_couleur_id) {
		this.eb_couleur_id = eb_couleur_id;
	}

	public Long getEb_article_id() {
		return eb_article_id;
	}

	public void setEb_article_id(Long eb_article_id) {
		this.eb_article_id = eb_article_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
}
