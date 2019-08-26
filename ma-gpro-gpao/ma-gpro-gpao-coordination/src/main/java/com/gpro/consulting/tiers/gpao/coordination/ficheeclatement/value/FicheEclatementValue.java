package com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value;

import java.util.Calendar;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */
public class FicheEclatementValue implements Comparable<FicheEclatementValue>{

    private Long id;
	private Calendar dateLancement;
	private String observations;
	private Long ordreFabricationId;
	private Long nombrePaquet;
	private Long quantiteEclate;
	
	private String ordreFabricationNumero;
	private Long produitId;
	private String produitReference;
	private String produitDesignation;
	private Long clientId;
	private String clientAbreviation;
	private String clientReference;
	
	private Set<PaquetValue> listPaquet;

	private Calendar dateExport;
	
	
	@JsonIgnore
	private Boolean blSuppression;
	@JsonIgnore
	private Calendar dateSuppression;
	@JsonIgnore
	private Calendar dateCreation;
	@JsonIgnore
	private Calendar dateModification;
	@JsonIgnore
	private String version;
	
	public int compareTo(FicheEclatementValue element) {
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
	
	public Long getNombrePaquet() {
		return nombrePaquet;
	}
	public void setNombrePaquet(Long nombrePaquet) {
		this.nombrePaquet = nombrePaquet;
	}
	public Long getQuantiteEclate() {
		return quantiteEclate;
	}
	public void setQuantiteEclate(Long quantiteEclate) {
		this.quantiteEclate = quantiteEclate;
	}
	public Set<PaquetValue> getListPaquet() {
		return listPaquet;
	}
	public void setListPaquet(Set<PaquetValue> listPaquet) {
		this.listPaquet = listPaquet;
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
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

	public Calendar getDateExport() {
		return dateExport;
	}

	public void setDateExport(Calendar dateExport) {
		this.dateExport = dateExport;
	}

}
