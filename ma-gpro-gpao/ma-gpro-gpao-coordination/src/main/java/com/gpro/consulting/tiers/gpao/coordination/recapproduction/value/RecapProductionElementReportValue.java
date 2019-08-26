package com.gpro.consulting.tiers.gpao.coordination.recapproduction.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 25/07/2016
 *
 */
public class RecapProductionElementReportValue implements Comparable<RecapProductionElementReportValue>{

	private String numero;
	private Long ofId;
	private Long quantite;
	private Long partieInterresId;
	private Long produitId;
	private Calendar dateLivraison;
	private Calendar dateIntroduction;
	
	private String partieInterresAbreviation;
	private String produitReference;
	private String produitDesignation;
	
	//see gp_operation table
	private Long sortieCoupe;			//SCP
	private Long engagement;			//ENG
	private Long sortieChaine;			//SCH
	private Long export;				//EXP
	
	private Long eclatemenet;			//ECL
	private Long miseEnColis;			//MCOL
	private Long sortieConditionnement;	//SCO
	
	@Override
	public int compareTo(RecapProductionElementReportValue element) {
		return (element.getNumero().compareTo(numero));
	}
	
	public Long getOfId() {
		return ofId;
	}

	public void setOfId(Long ofId) {
		this.ofId = ofId;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public Long getPartieInterresId() {
		return partieInterresId;
	}

	public void setPartieInterresId(Long partieInterresId) {
		this.partieInterresId = partieInterresId;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	public String getPartieInterresAbreviation() {
		return partieInterresAbreviation;
	}

	public void setPartieInterresAbreviation(String partieInterresAbreviation) {
		this.partieInterresAbreviation = partieInterresAbreviation;
	}

	public String getProduitReference() {
		return produitReference;
	}

	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}

	public String getProduitDesignation() {
		return produitDesignation;
	}

	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
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
