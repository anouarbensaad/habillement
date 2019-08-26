package com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.entity;

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
 * @since 16/05/2016
 *
 */

@Entity
@Table(name=IConstante.TABLE_GP_PAQUET)
public class PaquetEntity implements Serializable{
	
	private static final long serialVersionUID = -7070495944006203209L;
	
	@Id
	@SequenceGenerator(name="PAQUET_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_GP_PAQUET, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAQUET_ID_GENERATOR")
    private Long id;
	
	@Column(name = "NUM")
	private Long num;
	
	@Column(name = "rang")
	private Long rang;
	
	@Column(name = "EB_COULEUR_ID")
	private Long couleurId;
	
	@Column(name = "EB_TAILLE_ID")
	private Long tailleId;
	
	@Column(name = "QUANTITE")
	private Long quantite;
	
	@Column(name = "ORDRE")
	private Long ordre;	
	
	@Column(name = "NUM_MATELAS")
	private String numMatelas;
	
	@ManyToOne
	@JoinColumn(name = "GP_FICHE_ID")
	private FicheEclatementEntity ficheEclatement;
	
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

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
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

	public Long getOrdre() {
		return ordre;
	}

	public void setOrdre(Long ordre) {
		this.ordre = ordre;
	}

	public String getNumMatelas() {
		return numMatelas;
	}

	public void setNumMatelas(String numMatelas) {
		this.numMatelas = numMatelas;
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

	public FicheEclatementEntity getFicheEclatement() {
		return ficheEclatement;
	}

	public void setFicheEclatement(FicheEclatementEntity ficheEclatement) {
		this.ficheEclatement = ficheEclatement;
	}

	public Long getRang() {
		return rang;
	}

	public void setRang(Long rang) {
		this.rang = rang;
	}
	
	
}
