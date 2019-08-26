package com.gpro.consulting.tiers.gc.coordination.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 09/03/2016
 *
 */

public class DetailsProduitCommandeValue implements Comparable<DetailsProduitCommandeValue>{
	
	private Long id;
	private Long quantite;
	private Long tailleId;
	private Long couleurId;
	private Long produitCommandeId;
	private Boolean blSuppression;
	private Calendar dateSuppression;
	private Calendar dateCreation;
	private Calendar dateModification;
	
	//added on 01/04/2016, by Wahid Gazzah
	//the order of TAILLE
	//in order to sort the detail dto
	private String order;
	
	@Override
	public int compareTo(DetailsProduitCommandeValue element) {
		
		return element.getOrder().compareTo(order);
	}
	
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
	public Long getProduitCommandeId() {
		return produitCommandeId;
	}
	public void setProduitCommandeId(Long produitCommandeId) {
		this.produitCommandeId = produitCommandeId;
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
	
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}

}
