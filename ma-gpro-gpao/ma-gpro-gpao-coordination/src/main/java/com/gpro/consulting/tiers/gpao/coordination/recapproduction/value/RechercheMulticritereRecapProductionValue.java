package com.gpro.consulting.tiers.gpao.coordination.recapproduction.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 21/06/2016
 *
 */
public class RechercheMulticritereRecapProductionValue {
	
	private Long produitId;
	private Long partieInteresseId;
	private Long statutId;
	private Calendar dateLivraisonMin;
	private Calendar dateLivraisonMax;
	private Calendar dateIntroductionMin;
	private Calendar dateIntroductionMax;
	
	
	public Long getStatutId() {
		return statutId;
	}
	public void setStatutId(Long statutId) {
		this.statutId = statutId;
	}
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public Long getPartieInteresseId() {
		return partieInteresseId;
	}
	public void setPartieInteresseId(Long partieInteresseId) {
		this.partieInteresseId = partieInteresseId;
	}
	public Calendar getDateLivraisonMin() {
		return dateLivraisonMin;
	}
	public void setDateLivraisonMin(Calendar dateLivraisonMin) {
		this.dateLivraisonMin = dateLivraisonMin;
	}
	public Calendar getDateLivraisonMax() {
		return dateLivraisonMax;
	}
	public void setDateLivraisonMax(Calendar dateLivraisonMax) {
		this.dateLivraisonMax = dateLivraisonMax;
	}
	public Calendar getDateIntroductionMin() {
		return dateIntroductionMin;
	}
	public void setDateIntroductionMin(Calendar dateIntroductionMin) {
		this.dateIntroductionMin = dateIntroductionMin;
	}
	public Calendar getDateIntroductionMax() {
		return dateIntroductionMax;
	}
	public void setDateIntroductionMax(Calendar dateIntroductionMax) {
		this.dateIntroductionMax = dateIntroductionMax;
	}
	
}
