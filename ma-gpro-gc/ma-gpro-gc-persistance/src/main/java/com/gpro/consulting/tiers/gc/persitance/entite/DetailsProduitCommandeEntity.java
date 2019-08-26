package com.gpro.consulting.tiers.gc.persitance.entite;

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
 * @author Wahid Gazzah
 * @since 09/03/2016
 *
 */

@Entity
@Table(name= IConstanteGC.TABLE_GC_DETAILPRODUITCOMMANDE)
public class DetailsProduitCommandeEntity implements Serializable {

	private static final long serialVersionUID = -8138610064390052537L;
	
	@Id
	@SequenceGenerator(name="DETAILPRODUITCOMMANDE_ID_GENERATOR", 
	sequenceName=IConstanteGC.SEQUENCE_GL_DETAILPRODUITCOMMANDE,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DETAILPRODUITCOMMANDE_ID_GENERATOR")
    private Long id;

	@Column(name="QUANTITE")
	private Long quantite;
	 
	@Column(name="TAILLE_ID")
	private Long tailleId;
	
	@Column(name="COULEUR_ID")
	private Long couleurId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "PRODUITCOMMANDE_ID")
	private ProduitCommandeEntite produitCommande;
	   
	@Column(name="BL_SUPPRESSION")
	private Boolean blSuppression;
	
	@Column(name="DATE_SUPPRESSION")
	private Calendar dateSuppression;
	
	@Column(name="DATE_CREATION")
	private Calendar dateCreation;
	
	@Column(name="DATE_MODIFICATION")
	private Calendar dateModification;

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

	public Long getTailleId() {
		return tailleId;
	}

	public void setTailleId(Long tailleId) {
		this.tailleId = tailleId;
	}

	public Long getCouleurId() {
		return couleurId;
	}

	public void setCouleurId(Long couleurId) {
		this.couleurId = couleurId;
	}

	public ProduitCommandeEntite getProduitCommande() {
		return produitCommande;
	}

	public void setProduitCommande(ProduitCommandeEntite produitCommande) {
		this.produitCommande = produitCommande;
	}

	public Boolean isBlSuppression() {
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
	

}
