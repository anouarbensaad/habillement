package com.gpro.consulting.tiers.gpao.persitance.gammeof.entity;

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
 * @since 10/05/2016
 *
 */

@Entity
@Table(name=IConstante.TABLE_GP_GAMMEOF)
public class GammeOfEntity implements Serializable{

	private static final long serialVersionUID = 5940221410190495706L;

	@Id
	@SequenceGenerator(name="GAMMEOF_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_GP_GAMMEOF, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GAMMEOF_ID_GENERATOR")
    private Long id;
	
	@Column(name = "TEMPS_TOTAL")
	private Double tempsTotal;
	
	@Column(name = "NB_OPERATION")
	private Long nbOperation;
	
	@Column(name = "OBSERVATIONS")
	private String observations;
	
	@Column(name = "DATE")
	private Calendar date;
	
	@Column(name = "GP_OF_ID")
	private Long ordreFabricationId;
	
	@OneToMany(mappedBy = "gammeOf", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ElementGammeOfEntity> listElementGammeOf;
	
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

	public Double getTempsTotal() {
		return tempsTotal;
	}

	public void setTempsTotal(Double tempsTotal) {
		this.tempsTotal = tempsTotal;
	}

	public Long getNbOperation() {
		return nbOperation;
	}

	public void setNbOperation(Long nbOperation) {
		this.nbOperation = nbOperation;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Long getOrdreFabricationId() {
		return ordreFabricationId;
	}

	public void setOrdreFabricationId(Long ordreFabricationId) {
		this.ordreFabricationId = ordreFabricationId;
	}

	public Set<ElementGammeOfEntity> getListElementGammeOf() {
		return listElementGammeOf;
	}

	public void setListElementGammeOf(Set<ElementGammeOfEntity> listElementGammeOf) {
		this.listElementGammeOf = listElementGammeOf;
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

	
}
