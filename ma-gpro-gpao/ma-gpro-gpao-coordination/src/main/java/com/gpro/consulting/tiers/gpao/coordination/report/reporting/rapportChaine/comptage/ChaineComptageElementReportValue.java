package com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.comptage;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 *
 */
public class ChaineComptageElementReportValue {

	private Long ofId;
	private Long quantite;
	private Calendar dateIntroduction;
	
	private String chaineDesignation;
	
	//see gp_operation table
	private Long sortieCoupe;			//SCP
	private Long engagement;			//ENG
	private Long sortieChaine;			//SCH
	private Long export;				//EXP
	
	private Long eclatemenet;			//ECL
	private Long miseEnColis;			//MCOL
	private Long sortieConditionnement;	//SCO
	
	public Long getOfId() {
		return ofId;
	}
	public void setOfId(Long ofId) {
		this.ofId = ofId;
	}
	public Long getQuantite() {
		return quantite;
	}
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}
	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}
	public String getChaineDesignation() {
		return chaineDesignation;
	}
	public void setChaineDesignation(String chaineDesignation) {
		this.chaineDesignation = chaineDesignation;
	}
	public Long getSortieCoupe() {
		return sortieCoupe;
	}
	public void setSortieCoupe(Long sortieCoupe) {
		this.sortieCoupe = sortieCoupe;
	}
	public Long getEngagement() {
		return engagement;
	}
	public void setEngagement(Long engagement) {
		this.engagement = engagement;
	}
	public Long getSortieChaine() {
		return sortieChaine;
	}
	public void setSortieChaine(Long sortieChaine) {
		this.sortieChaine = sortieChaine;
	}
	public Long getExport() {
		return export;
	}
	public void setExport(Long export) {
		this.export = export;
	}
	public Long getEclatemenet() {
		return eclatemenet;
	}
	public void setEclatemenet(Long eclatemenet) {
		this.eclatemenet = eclatemenet;
	}
	public Long getMiseEnColis() {
		return miseEnColis;
	}
	public void setMiseEnColis(Long miseEnColis) {
		this.miseEnColis = miseEnColis;
	}
	public Long getSortieConditionnement() {
		return sortieConditionnement;
	}
	public void setSortieConditionnement(Long sortieConditionnement) {
		this.sortieConditionnement = sortieConditionnement;
	}
	
}
