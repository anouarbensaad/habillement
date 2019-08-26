package com.gpro.consulting.tiers.gpao.persitance.gammemontage.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gpao.coordination.IConstante;

/**
 * @author Wahid Gazzah
 * @since 07/04/2016
 *
 */

@Entity
@Table(name=IConstante.TABLE_GP_OPERATION)
public class OperationEntity implements Serializable{

	private static final long serialVersionUID = -7959896184257196212L;

	@Id
	@SequenceGenerator(name="OPERATION_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_GP_OPERATION, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="OPERATION_ID_GENERATOR")
    private Long id;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "DESIGNATION")
	private String designation;
	
	@Column(name = "TEMPS")
	private Double temps;
	
	@Column(name = "PDH")
	private Long pdh;
	
	@Column(name = "OBSERVATIONS")
	private String observations;
	
	@Column(name = "GP_MACHINE_ID")
	private Long machineId;
	
	@Column(name = "GP_SECTION_ID")
	private Long sectionId;
	
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

	@Column(name = "COMPTAGE")
	private boolean comptage;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Long getMachineId() {
		return machineId;
	}

	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
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

	public boolean isComptage() {
		return comptage;
	}

	public void setComptage(boolean comptage) {
		this.comptage = comptage;
	}
	
}
