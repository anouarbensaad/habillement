package com.gpro.consulting.tiers.gpao.coordination.abc.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 03/05/2016
 *
 */
public class RechercheMulticritereABCArticleDetailEtapeJourValue {
	
	private Calendar dateSaisieTo;
	private Calendar dateSaisieFrom;
	private String refCommande;
	private Long phaseId;
	private String affichage; //affichage par : "Jour","Mois","Annee"
	
	public Calendar getDateSaisieTo() {
		return dateSaisieTo;
	}
	public void setDateSaisieTo(Calendar dateSaisieTo) {
		this.dateSaisieTo = dateSaisieTo;
	}
	public Calendar getDateSaisieFrom() {
		return dateSaisieFrom;
	}
	public void setDateSaisieFrom(Calendar dateSaisieFrom) {
		this.dateSaisieFrom = dateSaisieFrom;
	}
	public String getRefCommande() {
		return refCommande;
	}
	public void setRefCommande(String refCommande) {
		this.refCommande = refCommande;
	}
	public Long getPhaseId() {
		return phaseId;
	}
	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
	}
	public String getAffichage() {
		return affichage;
	}
	public void setAffichage(String affichage) {
		this.affichage = affichage;
	}
	

}
