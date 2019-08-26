package com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value;

import java.util.Calendar;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */
public class FicheColisageValue implements Comparable<FicheColisageValue>{

    private Long id;
	private Calendar dateLancement;
	private String observations;
	private Long ordreFabricationId;
	private Long nombreColis;
	private Long quantiteColis;
	private Long quantiteTotale;
	private String ordreFabricationNumero;
	private Long produitId;
	private String produitReference;
	private String produitDesignation;
	private Long clientId;
	private String clientAbreviation;
	private String clientReference;
	private String  couleur ;
	private Set<ColisValue> listColis;
	private Set<DetailsColisageValue> listDetails;
	private String semaine;
	private Boolean colPalette ;
	
	private Double ofPrixUnitaire;
	
	
	private Boolean solder ;
	
	
	
	
	
	
	public Boolean getSolder() {
		return solder;
	}

	public void setSolder(Boolean solder) {
		this.solder = solder;
	}

	public Double getOfPrixUnitaire() {
		return ofPrixUnitaire;
	}

	public void setOfPrixUnitaire(Double ofPrixUnitaire) {
		this.ofPrixUnitaire = ofPrixUnitaire;
	}

	public Boolean getColPalette() {
		return colPalette;
	}

	public void setColPalette(Boolean colPalette) {
		this.colPalette = colPalette;
	}

	private Long quantiteColisBefore;
	
	
	public Long getQuantiteColisBefore() {
		return quantiteColisBefore;
	}

	public void setQuantiteColisBefore(Long quantiteColisBefore) {
		this.quantiteColisBefore = quantiteColisBefore;
	}

	public int compareTo(FicheColisageValue element) {
		return (element.getId().compareTo(id));
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Calendar getDateLancement() {
		return dateLancement;
	}
	public void setDateLancement(Calendar dateLancement) {
		this.dateLancement = dateLancement;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	
	
	public Long getOrdreFabricationId() {
		return ordreFabricationId;
	}

	public void setOrdreFabricationId(Long ordreFabricationId) {
		this.ordreFabricationId = ordreFabricationId;
	}

	public String getOrdreFabricationNumero() {
		return ordreFabricationNumero;
	}

	public void setOrdreFabricationNumero(String ordreFabricationNumero) {
		this.ordreFabricationNumero = ordreFabricationNumero;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public String getProduitReference() {
		return produitReference;
	}

	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}

	public String getProduitDesignation() {
		return produitDesignation;
	}

	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}

	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}

	public String getClientReference() {
		return clientReference;
	}

	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}

	public Long getNombreColis() {
		return nombreColis;
	}

	public void setNombreColis(Long nombreColis) {
		this.nombreColis = nombreColis;
	}

	public Long getQuantiteColis() {
		return quantiteColis;
	}

	public void setQuantiteColis(Long quantiteColis) {
		this.quantiteColis = quantiteColis;
	}

	public Long getQuantiteTotale() {
		return quantiteTotale;
	}

	public void setQuantiteTotale(Long quantiteTotale) {
		this.quantiteTotale = quantiteTotale;
	}

	public Set<ColisValue> getListColis() {
		return listColis;
	}

	public void setListColis(Set<ColisValue> listColis) {
		this.listColis = listColis;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public Set<DetailsColisageValue> getListDetails() {
		return listDetails;
	}

	public void setListDetails(Set<DetailsColisageValue> listDetails) {
		this.listDetails = listDetails;
	}

	public String getSemaine() {
		return semaine;
	}

	public void setSemaine(String semaine) {
		this.semaine = semaine;
	}

	
	
}
