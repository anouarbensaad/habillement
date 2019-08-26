package com.gpro.consulting.tiers.gpao.persitance.fichecolisage.entity;

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

@Entity
@Table(name=IConstante.TABLE_GP_DETAILS_COLIS)
public class DetailsColisageEntity implements Serializable{
	
	private static final long serialVersionUID = -7070495944006203209L;
	
	@Id
	@SequenceGenerator(name="DETAILS_COLIS_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_GP_DETAILS_COLIS, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DETAILS_COLIS_ID_GENERATOR")
    private Long id;
	
	@Column(name = "EB_COULEUR_ID")
	private Long couleurId;
	
	@Column(name = "EB_TAILLE_ID")
	private Long tailleId;
	
	@Column(name = "QUANTITE_RESTANTE")
	private Long quantiteRestante;
		
	@Column(name = "QUANTITE")
	private Long quantite;
	
	@Column(name = "pcb")
	private Long pcb;
	
	
	@ManyToOne
	@JoinColumn(name = "GP_FICHE_ID")
	private FicheColisageEntity ficheColisage;
		
	@Column(name = "TAILLE_DESIGNATION")
	private String tailleDesignation;
	
	@Column(name = "COULEUR_DESIGNATION")
	private String couleurDesignation;
	
	@Column(name = "POIDS_NET")
	private Double poidsNet ;
	
	@Column(name = "POIDS_BRUT")
	private Double poidsBrut ; 
	
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

	public Long getCouleurId() {
		return couleurId;
	}

	public void setCouleurId(Long couleurId) {
		this.couleurId = couleurId;
	}

	public Long getTailleId() {
		return tailleId;
	}

	public void setTailleId(Long tailleId) {
		this.tailleId = tailleId;
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

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public Long getQuantiteRestante() {
		return quantiteRestante;
	}

	public void setQuantiteRestante(Long quantiteRestante) {
		this.quantiteRestante = quantiteRestante;
	}

	public FicheColisageEntity getFicheColisage() {
		return ficheColisage;
	}

	public void setFicheColisage(FicheColisageEntity ficheColisage) {
		this.ficheColisage = ficheColisage;
	}

	public String getTailleDesignation() {
		return tailleDesignation;
	}

	public void setTailleDesignation(String tailleDesignation) {
		this.tailleDesignation = tailleDesignation;
	}

	public String getCouleurDesignation() {
		return couleurDesignation;
	}

	public void setCouleurDesignation(String couleurDesignation) {
		this.couleurDesignation = couleurDesignation;
	}

	public Double getPoidsNet() {
		return poidsNet;
	}

	public void setPoidsNet(Double poidsNet) {
		this.poidsNet = poidsNet;
	}

	public Double getPoidsBrut() {
		return poidsBrut;
	}

	public void setPoidsBrut(Double poidsBrut) {
		this.poidsBrut = poidsBrut;
	}

	public Long getPcb() {
		return pcb;
	}

	public void setPcb(Long pcb) {
		this.pcb = pcb;
	}

	
	
}
