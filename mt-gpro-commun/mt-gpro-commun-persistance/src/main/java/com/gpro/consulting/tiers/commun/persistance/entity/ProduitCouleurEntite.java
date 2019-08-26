package com.gpro.consulting.tiers.commun.persistance.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

/**
* Classe: ProduitCouleur
* @author: $AMENI
* 
*/
@Entity
@Table(name = IConstante.TABLE_EB_PRODUIT_COULEUR)
public class ProduitCouleurEntite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2744661561988480189L;
	@Id
	@SequenceGenerator(name="PRODUITCOULEUR_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_EB_PRODUIT_COULEUR,allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="PRODUITCOULEUR_ID_GENERATOR")
	private Long id;
	
	/** The eb_couleur_id. */
	@Column(name = "eb_couleur_id")
	private Long ebCouleurId;
	
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

	/** many-to-one association to Produit**. */
	@ManyToOne
	@JoinColumn(name = "eb_produit_id")
	private ProduitEntity produit;

	/********** Getter & Setter ************/
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the ebCouleurId
	 */
	public Long getEbCouleurId() {
		return ebCouleurId;
	}

	/**
	 * @param ebCouleurId the ebCouleurId to set
	 */
	public void setEbCouleurId(Long ebCouleurId) {
		this.ebCouleurId = ebCouleurId;
	}

	/**
	 * @return the blSuppression
	 */
	public boolean isBlSuppression() {
		return blSuppression;
	}

	/**
	 * @param blSuppression the blSuppression to set
	 */
	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	/**
	 * @return the dateSuppression
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	/**
	 * @param dateSuppression the dateSuppression to set
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	/**
	 * @return the dateCreation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the dateModification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}

	/**
	 * @param dateModification the dateModification to set
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	/**
	 * @return the produit
	 */
	public ProduitEntity getProduit() {
		return produit;
	}

	/**
	 * @param produit the produit to set
	 */
	public void setProduit(ProduitEntity produit) {
		this.produit = produit;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}
	
	/* (non-Javadoc)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
