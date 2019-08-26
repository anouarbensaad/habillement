package com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gpro.consulting.tiers.gpao.coordination.aleas.ElementAleasValue;

/**
 * @author Wahid Gazzah
 * @since 25/05/2016
 */
public class FeuilleSaisieValue {

	private Long id;
	private Double minProd;
	private Double minPresence;
	private Double minAleas;
	private Long personnelId;
	private Long chaineId;
	private Double rendement;
	private Double activite;
	private Long pscProd;
	private Calendar dateSaisie;
	private String observations;
	private boolean directe;

	// Conversion id > designation
	private String chaineDesignation;
	private String personnelMatricule;

	// used only for the validate function
	private List<String> listBareCode;

	private Set<SaisieElementValue> listSaisie;
	private Set<ElementAleasValue> listElementAleas;

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

	private String personnelBeforeUpdate;
	
	private Calendar dateBeforeUpdate;

	private Long chaineBeforeUpdate;

	private String periode;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMinProd() {
		return minProd;
	}

	public void setMinProd(Double minProd) {
		this.minProd = minProd;
	}

	public Double getMinPresence() {
		return minPresence;
	}

	public void setMinPresence(Double minPresence) {
		this.minPresence = minPresence;
	}

	public Double getMinAleas() {
		return minAleas;
	}

	public void setMinAleas(Double minAleas) {
		this.minAleas = minAleas;
	}

	public Long getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(Long personnelId) {
		this.personnelId = personnelId;
	}

	public Long getChaineId() {
		return chaineId;
	}

	public void setChaineId(Long chaineId) {
		this.chaineId = chaineId;
	}

	public Double getActivite() {
		return activite;
	}

	public void setActivite(Double activite) {
		this.activite = activite;
	}

	public Long getPscProd() {
		return pscProd;
	}

	public void setPscProd(Long pscProd) {
		this.pscProd = pscProd;
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

	public Set<SaisieElementValue> getListSaisie() {
		return listSaisie;
	}

	public void setListSaisie(Set<SaisieElementValue> listSaisie) {
		this.listSaisie = listSaisie;
	}

	public Calendar getDateSaisie() {
		return dateSaisie;
	}

	public void setDateSaisie(Calendar dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public Double getRendement() {
		return rendement;
	}

	public void setRendement(Double rendement) {
		this.rendement = rendement;
	}

	public String getChaineDesignation() {
		return chaineDesignation;
	}

	public void setChaineDesignation(String chaineDesignation) {
		this.chaineDesignation = chaineDesignation;
	}

	public String getPersonnelMatricule() {
		return personnelMatricule;
	}

	public void setPersonnelMatricule(String personnelMatricule) {
		this.personnelMatricule = personnelMatricule;
	}

	public List<String> getListBareCode() {
		return listBareCode;
	}

	public void setListBareCode(List<String> listBareCode) {
		this.listBareCode = listBareCode;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public boolean isDirecte() {
		return directe;
	}

	public void setDirecte(boolean directe) {
		this.directe = directe;
	}

	public Set<ElementAleasValue> getListElementAleas() {
		return listElementAleas;
	}

	public void setListElementAleas(Set<ElementAleasValue> listElementAleas) {
		this.listElementAleas = listElementAleas;
	}
	
	public String getPersonnelBeforeUpdate() {
		return personnelBeforeUpdate;
	}

	public void setPersonnelBeforeUpdate(String personnelBeforeUpdate) {
		this.personnelBeforeUpdate = personnelBeforeUpdate;
	}

	public Calendar getDateBeforeUpdate() {
		return dateBeforeUpdate;
	}

	public void setDateBeforeUpdate(Calendar dateBeforeUpdate) {
		this.dateBeforeUpdate = dateBeforeUpdate;
	}

	public Long getChaineBeforeUpdate() {
		return chaineBeforeUpdate;
	}

	public void setChaineBeforeUpdate(Long chaineBeforeUpdate) {
		this.chaineBeforeUpdate = chaineBeforeUpdate;
	}

	@Override
	public String toString() {
		return "FeuilleSaisieValue [id=" + id + ", minProd=" + minProd + ", minPresence=" + minPresence + ", minAleas="
				+ minAleas + ", rendement=" + rendement + ", activite=" + activite + ", pscProd=" + pscProd
				+ ", dateSaisie=" + dateSaisie + ", directe=" + directe + ", chaineDesignation=" + chaineDesignation
				+ ", personnelMatricule=" + personnelMatricule + ", listElementAleas=" + listElementAleas + "]";
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

}
