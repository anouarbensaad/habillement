package com.gpro.consulting.tiers.commun.persistance.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

import java.util.Calendar;
/**
 * The persistent class for the eb_detailsprix database table.
 * @author med
 * 
 */
@Entity
@Table(name=IConstante.TABLE_DETAILS_PRIX_PRODUIT)
public class DetailsPrixProduitEntite implements Serializable {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@SequenceGenerator(name="EB_DETAILSPRIX_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_DETAILS_PRIX_PRODUIT)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EB_DETAILSPRIX_ID_GENERATOR")
	private Long id;
	
	/** The methode. */
	@Column(name="methode")
	private String methode;

	/** The prix vente. */
	@Column(name="prix_vente")
	private double prixVente;

	/** The quantite inferieur. */
	@Column(name="qte_inf")
	private Long quantiteInferieur;

	/** The quantite superieur. */
	@Column(name="qte_sup")
	private Long quantiteSuperieur;
	
	/** The date deb. */
	@Column(name="date_deb")
	private Calendar dateDeb;

	/** The date fin. */
	@Column(name="date_fin")
	private Calendar dateFin;
	
	/** The date suppresion. */
	@Column(name="date_suppresion")
	private Calendar dateSuppresion;
	
	/** The bl suppression. */
	@Column(name="bl_suppression")
	private boolean blSuppression;

	/** The date creation. */
	@Column(name="date_creation")
	private Calendar dateCreation;

	/** The date modification. */
	@Column(name="date_modification")
	private Calendar dateModification;
	
	/** * many-to-one association to Produit**. */
	@ManyToOne
	@JoinColumn(name = "eb_produit_id")
	private ProduitEntity produit;


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}



	
	/**
	 * Gets the methode.
	 *
	 * @return the methode
	 */
	public String getMethode() {
		return methode;
	}


	/**
	 * Sets the methode.
	 *
	 * @param methode the new methode
	 */
	public void setMethode(String methode) {
		this.methode = methode;
	}


	/**
	 * Gets the prix vente.
	 *
	 * @return the prix vente
	 */
	public double getPrixVente() {
		return prixVente;
	}


	/**
	 * Sets the prix vente.
	 *
	 * @param prixVente the new prix vente
	 */
	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
	}


	/**
	 * Gets the quantite inferieur.
	 *
	 * @return the quantite inferieur
	 */
	public Long getQuantiteInferieur() {
		return quantiteInferieur;
	}


	/**
	 * Sets the quantite inferieur.
	 *
	 * @param quantiteInferieur the new quantite inferieur
	 */
	public void setQuantiteInferieur(Long quantiteInferieur) {
		this.quantiteInferieur = quantiteInferieur;
	}


	/**
	 * Gets the quantite superieur.
	 *
	 * @return the quantite superieur
	 */
	public Long getQuantiteSuperieur() {
		return quantiteSuperieur;
	}


	/**
	 * Sets the quantite superieur.
	 *
	 * @param quantiteSuperieur the new quantite superieur
	 */
	public void setQuantiteSuperieur(Long quantiteSuperieur) {
		this.quantiteSuperieur = quantiteSuperieur;
	}


	/**
	 * Gets the date deb.
	 *
	 * @return the date deb
	 */
	public Calendar getDateDeb() {
		return dateDeb;
	}


	/**
	 * Sets the date deb.
	 *
	 * @param dateDeb the new date deb
	 */
	public void setDateDeb(Calendar dateDeb) {
		this.dateDeb = dateDeb;
	}


	/**
	 * Gets the date fin.
	 *
	 * @return the date fin
	 */
	public Calendar getDateFin() {
		return dateFin;
	}


	/**
	 * Sets the date fin.
	 *
	 * @param dateFin the new date fin
	 */
	public void setDateFin(Calendar dateFin) {
		this.dateFin = dateFin;
	}


	/**
	 * Gets the date suppresion.
	 *
	 * @return the date suppresion
	 */
	public Calendar getDateSuppresion() {
		return dateSuppresion;
	}


	/**
	 * Sets the date suppresion.
	 *
	 * @param dateSuppresion the new date suppresion
	 */
	public void setDateSuppresion(Calendar dateSuppresion) {
		this.dateSuppresion = dateSuppresion;
	}


	/**
	 * Checks if is bl suppression.
	 *
	 * @return true, if is bl suppression
	 */
	public boolean isBlSuppression() {
		return blSuppression;
	}


	/**
	 * Sets the bl suppression.
	 *
	 * @param blSuppression the new bl suppression
	 */
	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}


	/**
	 * Gets the date creation.
	 *
	 * @return the date creation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}


	/**
	 * Sets the date creation.
	 *
	 * @param dateCreation the new date creation
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}


	/**
	 * Gets the date modification.
	 *
	 * @return the date modification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}


	/**
	 * Sets the date modification.
	 *
	 * @param dateModification the new date modification
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}


	/**
	 * Gets the prouduit.
	 *
	 * @return the prouduit
	 */
	public ProduitEntity getProuduit() {
		return produit;
	}


	/**
	 * Sets the prouduit.
	 *
	 * @param prouduit the new prouduit
	 */
	public void setProuduit(ProduitEntity prouduit) {
		this.produit = prouduit;
	}
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	return super.toString();
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
    	return super.equals(obj);
    }

	

}