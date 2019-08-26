package com.gpro.consulting.tiers.commun.persistance.baseinfo.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.coordination.IConstante;


/**
 * @author Wahid Gazzah
 * @since 01/06/2016
 *
 */

@Entity
@Table(name=IConstante.TABLE_EB_BASEINFO)
public class BaseInfoEntity implements Serializable{

	private static final long serialVersionUID = -3893584995848949449L;

	@Id
	@SequenceGenerator(name="BASEINFO_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_EB_BASEINFO, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BASEINFO_ID_GENERATOR")
    private Long id;
	
	@Column(name = "DESIGNATION")
	private String designation;
	
	@Column(name = "VALEUR")
	private String valeur;
	
	@Column(name = "UNITE")
	private String unite;
	
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

	@Column(name = "LOGO")
	private String logo;

	@Column(name = "ACTIF")
	private boolean actif;

	

	
	@Column(name = "sch")
	private Long sortieChaine;
	
	
	@Column(name = "eng")
	private Long engagement;
	
	
	@Column(name = "scpe")
	private Long sortieCoupe;
	
	
	@Column(name = "conditionnement")
	private Long conditionnement;	
	
	
	@Column(name = "ecl")
	private Long eclatement;		
	
	
	
	@Column(name = "supp1")
	private Long supp1;	
	
	
	@Column(name = "supp2")
	private Long supp2;	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public Long getSortieChaine() {
		return sortieChaine;
	}

	public void setSortieChaine(Long sortieChaine) {
		this.sortieChaine = sortieChaine;
	}

	public Long getEngagement() {
		return engagement;
	}

	public void setEngagement(Long engagement) {
		this.engagement = engagement;
	}

	public Long getSortieCoupe() {
		return sortieCoupe;
	}

	public void setSortieCoupe(Long sortieCoupe) {
		this.sortieCoupe = sortieCoupe;
	}

	public Long getConditionnement() {
		return conditionnement;
	}

	public void setConditionnement(Long conditionnement) {
		this.conditionnement = conditionnement;
	}

	public Long getEclatement() {
		return eclatement;
	}

	public void setEclatement(Long eclatement) {
		this.eclatement = eclatement;
	}

	public Long getSupp1() {
		return supp1;
	}

	public void setSupp1(Long supp1) {
		this.supp1 = supp1;
	}

	public Long getSupp2() {
		return supp2;
	}

	public void setSupp2(Long supp2) {
		this.supp2 = supp2;
	}
	
	
	
	
	
}
