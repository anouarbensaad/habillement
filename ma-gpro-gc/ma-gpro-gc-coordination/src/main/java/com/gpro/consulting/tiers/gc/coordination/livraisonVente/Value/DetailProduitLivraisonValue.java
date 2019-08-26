/**
 * 
 */
package com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Ameni Berrich
 *
 */
public class DetailProduitLivraisonValue {
	
	private Long id;
	private Long quantite;
	private Long couleurId;
	private Long tailleId;
	private Long produitLivraisonId;
	
	@JsonIgnore
	private Boolean blSuppression;
	@JsonIgnore
	private Calendar dateSuppression;
	@JsonIgnore
	private Calendar dateModification;
	@JsonIgnore
	private Calendar dateCreation;
	
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
	public Long getProduitLivraisonId() {
		return produitLivraisonId;
	}
	public void setProduitLivraisonId(Long produitLivraisonId) {
		this.produitLivraisonId = produitLivraisonId;
	}
	
}
