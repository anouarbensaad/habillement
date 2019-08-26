package com.gpro.consulting.tiers.gpao.persitance.entite;

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

import com.gpro.consulting.tiers.gpao.coordination.IConstante;

/**
 * @author $Ameni
 *
 */
@Entity
@Table(name = IConstante.TABLE_GP_DETAILSOF)
public class DetailOfEntite implements Serializable {
	
	private static final long serialVersionUID = 5934373905032236213L;

	/** Id. */
	@Id
	@SequenceGenerator(name = "GP_DETAILSOF_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_GP_DETAILSOF, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GP_DETAILSOF_ID_GENERATOR")
	private Long id;
	
	/** The quantite. */
	@Column(name = "quantite")
	private Long quantite;

	/** The eb_taille_id. */
	@Column(name = "eb_taille_id")
	private Long tailleId;
	
	/** The eb_couleur_id. */
	@Column(name = "eb_couleur_id")
	private Long couleurId;
	
	/** The quantite Stock. */
	@Column(name = "qte_stock")
	private Long qteStock;
	
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
	
	/** * many-to-one association to ordreFabrication*. */
	@ManyToOne
	@JoinColumn(name = "gp_of_id")
	private OrdreFabricationEntite ordre;

	/********** Getter & Setter **********/
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the quantite
	 */
	public Long getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite
	 *            the quantite to set
	 */
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	/**
	 * @return the ordre
	 */
	public OrdreFabricationEntite getOrdre() {
		return ordre;
	}

	/**
	 * @param ordre
	 *            the ordre to set
	 */
	public void setOrdre(OrdreFabricationEntite ordre) {
		this.ordre = ordre;
	}

	/**
	 * @return the tailleId
	 */
	public Long getTailleId() {
		return tailleId;
	}

	/**
	 * @param tailleId the tailleId to set
	 */
	public void setTailleId(Long tailleId) {
		this.tailleId = tailleId;
	}

	/**
	 * @return the couleurId
	 */
	public Long getCouleurId() {
		return couleurId;
	}

	/**
	 * @param couleurId the couleurId to set
	 */
	public void setCouleurId(Long couleurId) {
		this.couleurId = couleurId;
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

	public Long getQteStock() {
		return qteStock;
	}

	public void setQteStock(Long qteStock) {
		this.qteStock = qteStock;
	}

}
