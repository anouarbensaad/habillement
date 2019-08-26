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

import com.gpro.consulting.tiers.gc.coordination.IConstanteGC;

/**
 * @author Hajer Amri
 *
 */

@Entity
@Table(name = IConstanteGC.TABLE_GC_DIVERS_FACTURE_VENTE)
public class DiversFactureEntite implements Serializable {

	private static final long serialVersionUID = 6699416878428623274L;

	@Id
	@SequenceGenerator(name = "DIVERS_FACTURE_VENTE_ID_GENERATOR", sequenceName = IConstanteGC.SEQUENCE_GC_DIVERS_FACTURE_VENTE, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIVERS_FACTURE_VENTE_ID_GENERATOR")
	private Long id;

	@Column(name = "prix")
	private Double prix;

	@Column(name = "quantite")
	private Long quantite;

	@Column(name = "designation")
	private String designation;

	@Column(name = "bl_suppression")
	private Boolean bl_suppression;

	@Column(name = "date_creation")
	private Calendar dateCreation;

	@Column(name = "date_modification")
	private Calendar date_modification;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gc_facturevente_id")
	private FactureVenteEntite factureVente;

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


	public FactureVenteEntite getFactureVente() {
		return factureVente;
	}


	public void setFactureVente(FactureVenteEntite factureVente) {
		this.factureVente = factureVente;
	}


	@Override
	public String toString() {
		return "DiversFacture [id=" + id + ", prix=" + prix + ", quantite=" + quantite + ", designation=" + designation
				+ ", bl_suppression=" + bl_suppression + ", dateCreation=" + dateCreation + ", date_modification="
				+ date_modification + ", factureVente=" + factureVente + "]";
	}



	
}
