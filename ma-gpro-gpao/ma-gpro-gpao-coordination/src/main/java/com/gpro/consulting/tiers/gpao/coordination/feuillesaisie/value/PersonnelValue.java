package com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Wahid Gazzah
 * @since 25/05/2016
 */
public class PersonnelValue {
	
	private Long id;
	private String matricule;
	private String nom;
	private String prenom;
	private boolean indirect;
	
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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
	public boolean isIndirect() {
		return indirect;
	}
	public void setIndirect(boolean indirect) {
		this.indirect = indirect;
	}
	@Override
	public String toString() {
		return "PersonnelValue [id=" + id + ", matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom
				+ ", indirect=" + indirect + ", blSuppression=" + blSuppression + ", dateSuppression=" + dateSuppression
				+ ", dateCreation=" + dateCreation + ", dateModification=" + dateModification + ", version=" + version
				+ "]";
	}
	

}
