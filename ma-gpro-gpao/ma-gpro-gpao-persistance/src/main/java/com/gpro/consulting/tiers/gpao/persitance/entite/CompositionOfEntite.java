package com.gpro.consulting.tiers.gpao.persitance.entite;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
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

import com.gpro.consulting.tiers.gc.persitance.entite.ProduitCommandeEntite;
import com.gpro.consulting.tiers.gpao.coordination.IConstante;

/**
 * @author $Ameni
 *
 */

@Entity
@Table(name = IConstante.TABLE_GP_COMPOSITIONOF)
public class CompositionOfEntite implements Serializable {
	
	private static final long serialVersionUID = 4141278888466455307L;

	/** Id. */
	@Id
	@SequenceGenerator(name = "GP_COMPOSITIONOF_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_GP_COMPOSITIONOF, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GP_COMPOSITIONOF_ID_GENERATOR")
	private Long id;
	
	/** The quantite. */
	@Column(name = "quantite")
	private Long quantite;
	
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
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gp_of_id")
    private OrdreFabricationEntite ordre;
	
	/** The GcProduitCommande. */
	@ManyToOne(fetch = FetchType.LAZY ,cascade=CascadeType.MERGE)
	@JoinColumn(name = "gc_commande_id")
	private ProduitCommandeEntite produitCommandeEntite;

	/************* Getters & Setters *****************/
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
	 * @return the quantite
	 */
	public Long getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite the quantite to set
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
	 * @param ordre the ordre to set
	 */
	public void setOrdre(OrdreFabricationEntite ordre) {
		this.ordre = ordre;
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
	 * @return the produitCommandeEntite
	 */
	public ProduitCommandeEntite getProduitCommandeEntite() {
		return produitCommandeEntite;
	}

	/**
	 * @param produitCommandeEntite the produitCommandeEntite to set
	 */
	public void setProduitCommandeEntite(ProduitCommandeEntite produitCommandeEntite) {
		this.produitCommandeEntite = produitCommandeEntite;
	}
	
	
}
