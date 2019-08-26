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
* Classe: ProduitTaille
* @author: $AMENI
* 
*/
@Entity
@Table(name = IConstante.TABLE_EB_PRODUIT_TAILLE)
public class ProduitTailleEntite implements Serializable {

	private static final long serialVersionUID = -6244457872611848881L;
	
	@Id
	@SequenceGenerator(name="PRODUITTAILLE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_EB_PRODUIT_TAILLE,allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="PRODUITTAILLE_ID_GENERATOR")
	private Long id;
	
	/** The eb_taille_id. */
	@Column(name = "eb_taille_id")
	private Long ebTailleId;
	
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
	 * @return the ebTailleId
	 */
	public Long getEbTailleId() {
		return ebTailleId;
	}

	/**
	 * @param ebTailleId the ebTailleId to set
	 */
	public void setEbTailleId(Long ebTailleId) {
		this.ebTailleId = ebTailleId;
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
		StringBuilder builder = new StringBuilder();
		builder.append("ProduitTailleEntite [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (ebTailleId != null) {
			builder.append("ebTailleId=");
			builder.append(ebTailleId);
			builder.append(", ");
		}
		builder.append("blSuppression=");
		builder.append(blSuppression);
		builder.append(", ");
		if (dateSuppression != null) {
			builder.append("dateSuppression=");
			builder.append(dateSuppression);
			builder.append(", ");
		}
		if (dateCreation != null) {
			builder.append("dateCreation=");
			builder.append(dateCreation);
			builder.append(", ");
		}
		if (dateModification != null) {
			builder.append("dateModification=");
			builder.append(dateModification);
			builder.append(", ");
		}
		if (produit != null) {
			builder.append("produit=");
			builder.append(produit);
		}
		builder.append("]");
		return builder.toString();
	}
	
	/* (non-Javadoc)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
