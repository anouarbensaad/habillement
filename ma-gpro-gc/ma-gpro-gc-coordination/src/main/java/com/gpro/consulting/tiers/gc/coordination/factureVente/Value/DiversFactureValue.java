package com.gpro.consulting.tiers.gc.coordination.factureVente.Value;

import java.util.Calendar;

/**
 * @author Hajer Amri
 *
 */
public class DiversFactureValue implements Comparable<DiversFactureValue> {

	private Long id;
	private Double prix;
	private Long quantite;
	private String designation;
	private Boolean bl_suppression;
	private Calendar dateCreation;
	private Calendar date_modification;
	private Long factureVenteId;

	@Override
	public int compareTo(DiversFactureValue element) {
		return (element.getId().compareTo(id));
	}

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

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public Boolean getBl_suppression() {
		return bl_suppression;
	}

	public void setBl_suppression(Boolean bl_suppression) {
		this.bl_suppression = bl_suppression;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Calendar getDate_modification() {
		return date_modification;
	}

	public void setDate_modification(Calendar date_modification) {
		this.date_modification = date_modification;
	}

	public Long getFactureVenteId() {
		return factureVenteId;
	}

	public void setFactureVenteId(Long factureVenteId) {
		this.factureVenteId = factureVenteId;
	}

	@Override
	public String toString() {
		return "DiversFactureValue [id=" + id + ", prix=" + prix + ", quantite=" + quantite + ", designation="
				+ designation + ", bl_suppression=" + bl_suppression + ", dateCreation=" + dateCreation
				+ ", date_modification=" + date_modification + ", factureVenteId=" + factureVenteId + "]";
	}

}
