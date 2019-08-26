package com.gpro.consulting.tiers.gc.persitance.factureVente.entite;

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

import com.gpro.consulting.tiers.commun.persistance.entity.ProduitEntity;
import com.gpro.consulting.tiers.gc.coordination.IConstanteGC;

/**
 * @author Ameni Berrich
 *
 */
@Entity
@Table(name = IConstanteGC.TABLE_GC_PRODUIT_FACTURE_VENTE)
public class ProduitFactureVenteEntite implements Serializable {

	private static final long serialVersionUID = 6699416878428623274L;

	@Id
	@SequenceGenerator(name = "PRODUIT_FACTUREVENTE_ID_GENERATOR", sequenceName = IConstanteGC.SEQUENCE_GC_PRODUIT_FACTURE_VENTE, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUIT_FACTUREVENTE_ID_GENERATOR")
	private Long id;

	@Column(name = "prix")
	private Double prix;

	@Column(name = "devise")
	private String devise;

	@Column(name = "quantite")
	private Long quantite;

	@Column(name = "bl_suppression")
	private Boolean blSuppression;

	@Column(name = "date_suppression")
	private Calendar dateSuppression;

	@Column(name = "date_modification")
	private Calendar dateModification;

	@Column(name = "date_creation")
	private Calendar dateCreation;

	@Column(name = "colis")
	private Long colis;

	@Column(name = "palette")
	private String palette;

	@Column(name = "REFERENCE_COMMANDE")
	private String referenceCommande;

	@Column(name = "prix_tmp")
	private Double prixTMP;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gc_facturevente_id")
	private FactureVenteEntite factureVente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eb_produit_id")
	private ProduitEntity produit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public String getDevise() {
		return devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
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

	public Long getColis() {
		return colis;
	}

	public void setColis(Long colis) {
		this.colis = colis;
	}

	public String getPalette() {
		return palette;
	}

	public void setPalette(String palette) {
		this.palette = palette;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public FactureVenteEntite getFactureVente() {
		return factureVente;
	}

	public void setFactureVente(FactureVenteEntite factureVente) {
		this.factureVente = factureVente;
	}

	public ProduitEntity getProduit() {
		return produit;
	}

	public void setProduit(ProduitEntity produit) {
		this.produit = produit;
	}

	public String getReferenceCommande() {
		return referenceCommande;
	}

	public void setReferenceCommande(String referenceCommande) {
		this.referenceCommande = referenceCommande;
	}

	public Double getPrixTMP() {
		return prixTMP;
	}

	public void setPrixTMP(Double prixTMP) {
		this.prixTMP = prixTMP;
	}

	@Override
	public String toString() {
		return "ProduitFactureVenteEntite [id=" + id + ", prix=" + prix + ", quantite=" + quantite + ", colis=" + colis
				+ ", palette=" + palette + ", referenceCommande=" + referenceCommande + ", factureVente=" + factureVente
				+ ", produit=" + produit + "]";
	}

}
