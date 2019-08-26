package com.gpro.consulting.tiers.gc.persitance.livraisonVente.entite;

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

import com.gpro.consulting.tiers.gc.coordination.IConstanteGC;

/**
 * @author Ameni Berrich
 *
 */
@Entity
@Table(name=IConstanteGC.TABLE_GC_DETAILPRODUITLIVRAISON)
public class DetailProduitLivraisonEntite implements Serializable{

	private static final long serialVersionUID = 584519537402029424L;

	@Id
	@SequenceGenerator(name="DETAILPRODUITLIVRAISON_ID_GENERATOR", sequenceName=IConstanteGC.SEQUENCE_GC_DETAILPRODUITLIVRAISON,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DETAILPRODUITLIVRAISON_ID_GENERATOR")
    private Long id;
	
	@Column(name = "QUANTITE")
	private Long quantite;
	
	@Column(name = "eb_couleur_id")
	private Long couleurId;
	
	@Column(name = "eb_taille_id")
	private Long tailleId;

	@Column(name="bl_suppression")
	private Boolean blSuppression;
	
	@Column(name="date_suppression")
	private Calendar dateSuppression;
	
	@Column(name="date_modification")
	private Calendar dateModification;
	
	@Column(name="date_creation")
	private Calendar dateCreation;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "produitlivraison_id")
	private ProduitLivraisonEntite produitLivraison;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public Long getCouleurId() {
		return couleurId;
	}

	public void setCouleurId(Long couleurId) {
		this.couleurId = couleurId;
	}

	public Long getTailleId() {
		return tailleId;
	}

	public void setTailleId(Long tailleId) {
		this.tailleId = tailleId;
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

	public ProduitLivraisonEntite getProduitLivraison() {
		return produitLivraison;
	}

	public void setProduitLivraison(ProduitLivraisonEntite produitLivraison) {
		this.produitLivraison = produitLivraison;
	}

}
