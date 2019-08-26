package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gc.persitance.factureVente.entite.DiversFactureEntite;
import com.gpro.consulting.tiers.gpao.coordination.IConstante;

/**
 * @author Wahid Gazzah
 * @since 25/05/2016
 *
 */
@Entity
@Table(name = IConstante.TABLE_GP_FEUILLE_SAISIE)
public class FeuilleSaisieEntity implements Serializable {

	private static final long serialVersionUID = 3299677062564801215L;

	@Id
	@SequenceGenerator(name = "FICHESAISIE_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_GP_FEUILLE_SAISIE, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FICHESAISIE_ID_GENERATOR")
	private Long id;

	@Column(name = "MIN_PROD")
	private Double minProd;

	@Column(name = "MIN_PRESENCE")
	private Double minPresence;

	@Column(name = "MIN_ALEAS")
	private Double minAleas;

	@ManyToOne
	@JoinColumn(name = "GP_PERSONNEL_ID")
	private PersonnelEntity personnel;

	@Column(name = "GP_CHAINE_ID")
	private Long chaineId;

	@Column(name = "RENDEMENT")
	private Double rendement;

	@Column(name = "ACTIVITE")
	private Double activite;

	@Column(name = "PSC_PROD")
	private Long pscProd;

	@Column(name = "DATE_SAISIE")
	private Calendar dateSaisie;

	@Column(name = "OBSERVATIONS")
	private String observations;

	@Column(name = "PERIODE")
	private String periode;
	
	@OneToMany(mappedBy = "fiheSaisie", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<SaisieElementEntity> listSaisie;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "feuilleSaisie", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ElementAleasEntity> listElementAleas;

	@Column(name = "BL_SUPPRESSION")
	private Boolean blSuppression;

	@Column(name = "DATE_SUPPRESSION")
	private Calendar dateSuppression;

	@Column(name = "DATE_CREATION")
	private Calendar dateCreation;

	@Column(name = "DATE_MODIFICATION")
	private Calendar dateModification;

	@Column(name = "VERSION")
	private String version;

	@Column(name = "DIRECTE")
	private boolean directe;

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

	public PersonnelEntity getPersonnel() {
		return personnel;
	}

	public void setPersonnel(PersonnelEntity personnel) {
		this.personnel = personnel;
	}

	public Long getChaineId() {
		return chaineId;
	}

	public void setChaineId(Long chaineId) {
		this.chaineId = chaineId;
	}

	public Double getRendement() {
		return rendement;
	}

	public void setRendement(Double rendement) {
		this.rendement = rendement;
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

	public Calendar getDateSaisie() {
		return dateSaisie;
	}

	public void setDateSaisie(Calendar dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Set<SaisieElementEntity> getListSaisie() {
		return listSaisie;
	}

	public void setListSaisie(Set<SaisieElementEntity> listSaisie) {
		this.listSaisie = listSaisie;
	}

	public Set<ElementAleasEntity> getListElementAleas() {
		return listElementAleas;
	}

	public void setListElementAleas(Set<ElementAleasEntity> listElementAleas) {
		this.listElementAleas = listElementAleas;
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

	public boolean isDirecte() {
		return directe;
	}

	public void setDirecte(boolean directe) {
		this.directe = directe;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	@Override
	public String toString() {
		return "FeuilleSaisieEntity [id=" + id + ", minAleas=" + minAleas + ", listSaisie=" + listSaisie
				+ ", listElementAleas=" + listElementAleas + "]";
	}

}
