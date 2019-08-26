package com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gpao.coordination.IConstante;

/**
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */

@Entity
@Table(name=IConstante.TABLE_GP_FICHEECLATEMENT)
public class FicheEclatementEntity implements Serializable{
	
	private static final long serialVersionUID = -3004815224021179635L;
	
	@Id
	@SequenceGenerator(name="FICHEECLATEMENT_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_GP_FICHEECLATEMENT, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FICHEECLATEMENT_ID_GENERATOR")
    private Long id;
	
	@Column(name = "DATE_LANCEMENT")
	private Calendar dateLancement;
	
	@Column(name = "OBSERVATIONS")
	private String observations;
	
	@Column(name = "GP_OF_ID")
	private Long ordreFabricationId;
	
	@Column(name = "NOMBRE_PAQUET")
	private Long nombrePaquet;
	
	@Column(name = "QUANTITE_ECLATE")
	private Long quantiteEclate;
	
	@OneToMany(mappedBy = "ficheEclatement", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<PaquetEntity> listPaquet;
	
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

	public Set<PaquetEntity> getListPaquet() {
		return listPaquet;
	}

	public void setListPaquet(Set<PaquetEntity> listPaquet) {
		this.listPaquet = listPaquet;
	}

}
