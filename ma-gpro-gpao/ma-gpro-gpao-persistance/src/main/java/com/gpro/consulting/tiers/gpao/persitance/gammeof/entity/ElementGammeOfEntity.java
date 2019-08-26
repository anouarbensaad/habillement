package com.gpro.consulting.tiers.gpao.persitance.gammeof.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gpao.coordination.IConstante;

/**
 * @author Wahid Gazzah
 * @since 10/05/2016
 *
 */

@Entity
@Table(name=IConstante.TABLE_GP_ELEMENTGAMMEOF)
public class ElementGammeOfEntity implements Serializable{

	private static final long serialVersionUID = 726216746273772965L;

	@Id
	@SequenceGenerator(name="ELEMENTGAMMEOF_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_GP_ELEMENTGAMMEOF, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ELEMENTGAMMEOF_ID_GENERATOR")
    private Long id;
	
	@Column(name = "GP_OPERATION_ID")
	private Long operationId;

	@ManyToOne
	@JoinColumn(name = "GP_GAMMEOF_ID")
	private GammeOfEntity gammeOf;
	
	@Column(name = "TEMPS")
	private Double temps;
	
	@Column(name = "PDH")
	private Long pdh;
	
	@Column(name = "SECTION_ID")
	private Long sectionId;
	
	@Column(name = "MACHINE_ID")
	private Long machineId;
	
	@Column(name = "OBSERVATION")
	private String observations;
	
	@Column(name = "COMPTAGE")
	private Boolean comptage;
	
	@Column(name = "ORDRE")
	private Long ordre;
	
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

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

	public GammeOfEntity getGammeOf() {
		return gammeOf;
	}

	public void setGammeOf(GammeOfEntity gammeOf) {
		this.gammeOf = gammeOf;
	}

	public Double getTemps() {
		return temps;
	}

	public void setTemps(Double temps) {
		this.temps = temps;
	}

	public Long getPdh() {
		return pdh;
	}

	public void setPdh(Long pdh) {
		this.pdh = pdh;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Long getMachineId() {
		return machineId;
	}

	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Boolean getComptage() {
		return comptage;
	}

	public void setComptage(Boolean comptage) {
		this.comptage = comptage;
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

	public Long getOrdre() {
		return ordre;
	}

	public void setOrdre(Long ordre) {
		this.ordre = ordre;
	}
	
	
}
