package com.gpro.consulting.tiers.gpao.persitance.planning.chaine.entity;

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
 * @author Hamdi Etteieb
 *
 */
@Entity
@Table(name=IConstante.TABLE_GP_PRODUCTIONJOUR)
public class ProductionJourEntity implements Serializable {

	private static final long serialVersionUID = 8173475315443424028L;

	@Id
	@SequenceGenerator(name="PRODUCTIONJOUR_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_GP_PRODUCTIONJOUR, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTIONJOUR_ID_GENERATOR")
    private Long id;
	
	
	@Column(name = "ELEMENTPLANNING_ID")
	private Long idElementPlanning;
	
	
	@Column(name = "DATE")
	private Calendar date;
		
	@Column(name = "QUANTITE")
	private Long quantite;
	
	@Column(name = "OBSERVATIONS")
	private String observation ;
	
	@Column(name = "BL_SUPPRESSION")
	private Boolean blSuppression ;
	
	@Column(name = "DATE_SUPPRESSION")
	private Calendar dateSuppression;  
	
	@Column(name = "DATE_MODIFICATION")
	private Calendar dateModification;  
	   
	@Column(name = "DATE_CREATION")
	private Calendar dateCreation;

	
	@Column(name = "of")
	private Long of;
	
	@Column(name = "chaine")
	private Long chaine;
	
	@Column(name = "phase")
	private Long phase;
	
	
	@Column(name = "semaine")
	private Long semaine;
	
	
	@Column(name = "periode")
	private String periode;
	

	
	@Column(name = "effectif")
	private Double effectif;
	
	@Column(name = "rendement")
	private Double rendement;
	
	@Column(name = "pi_pi_id")
	private Long partieInterresId;	
		
	@Column(name = "qte_non_conf")
	private Long qteNonConf;
		
		
		
		


	public Long getQteNonConf() {
		return qteNonConf;
	}

	public void setQteNonConf(Long qteNonConf) {
		this.qteNonConf = qteNonConf;
	}

	public Long getPartieInterresId() {
			return partieInterresId;
		}

		public void setPartieInterresId(Long partieInterresId) {
			this.partieInterresId = partieInterresId;
		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdElementPlanning() {
		return idElementPlanning;
	}

	public void setIdElementPlanning(Long idElementPlanning) {
		this.idElementPlanning = idElementPlanning;
	}

	
	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
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

	public Calendar getDateModification() {
		return dateModification;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Long getOf() {
		return of;
	}

	public void setOf(Long of) {
		this.of = of;
	}

	public Long getChaine() {
		return chaine;
	}

	public void setChaine(Long chaine) {
		this.chaine = chaine;
	}

	public Long getPhase() {
		return phase;
	}

	public void setPhase(Long phase) {
		this.phase = phase;
	}

	public Long getSemaine() {
		return semaine;
	}

	public void setSemaine(Long semaine) {
		this.semaine = semaine;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}



	public Double getEffectif() {
		return effectif;
	}

	public void setEffectif(Double effectif) {
		this.effectif = effectif;
	}

	public Double getRendement() {
		return rendement;
	}

	public void setRendement(Double rendement) {
		this.rendement = rendement;
	}





}
