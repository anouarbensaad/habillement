/**
 * 
 */
package com.gpro.consulting.tiers.gpao.coordination.report;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 * @since 25/05/2016
 */
public class ABCArticleDetailEtapeJourElementValue {

	private Long id;
	private Long qteProduite;
	private String refCommande;
	private Long qteCmd;
	private String clientAbrevation;
	private Long abcarticledetailId;
	private String produitAbrevation;
	private String affichage;
	private Calendar dateSaisie;
	private Calendar jourSaisie;
	private String moisSaisie;
	private int anneeSaisie;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getQteProduite() {
		return qteProduite;
	}
	public void setQteProduite(Long qteProduite) {
		this.qteProduite = qteProduite;
	}
	public String getRefCommande() {
		return refCommande;
	}
	public void setRefCommande(String refCommande) {
		this.refCommande = refCommande;
	}
	public Long getQteCmd() {
		return qteCmd;
	}
	public void setQteCmd(Long qteCmd) {
		this.qteCmd = qteCmd;
	}
	public String getClientAbrevation() {
		return clientAbrevation;
	}
	public void setClientAbrevation(String clientAbrevation) {
		this.clientAbrevation = clientAbrevation;
	}
	public Long getAbcarticledetailId() {
		return abcarticledetailId;
	}
	public void setAbcarticledetailId(Long abcarticledetailId) {
		this.abcarticledetailId = abcarticledetailId;
	}
	public String getProduitAbrevation() {
		return produitAbrevation;
	}
	public void setProduitAbrevation(String produitAbrevation) {
		this.produitAbrevation = produitAbrevation;
	}
	public String getAffichage() {
		return affichage;
	}
	public void setAffichage(String affichage) {
		this.affichage = affichage;
	}
	public Calendar getJourSaisie() {
		return jourSaisie;
	}
	public void setJourSaisie(Calendar jourSaisie) {
		this.jourSaisie = jourSaisie;
	}
	public String getMoisSaisie() {
		return moisSaisie;
	}
	public void setMoisSaisie(String moisSaisie) {
		this.moisSaisie = moisSaisie;
	}
	public int getAnneeSaisie() {
		return anneeSaisie;
	}
	public void setAnneeSaisie(int anneeSaisie) {
		this.anneeSaisie = anneeSaisie;
	}
	public Calendar getDateSaisie() {
		return dateSaisie;
	}
	public void setDateSaisie(Calendar dateSaisie) {
		this.dateSaisie = dateSaisie;
	}
	
}
