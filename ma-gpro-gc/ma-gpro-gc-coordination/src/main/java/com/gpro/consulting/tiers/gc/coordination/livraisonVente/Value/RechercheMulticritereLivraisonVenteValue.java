package com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 *
 */
public class RechercheMulticritereLivraisonVenteValue {
	
	private String reference;
	private Long partieInteresseeId;
	private Long produitId;
	private Calendar dateIntroductionDu ;
	private Calendar dateIntroductionA;
	private Calendar dateLivraisonDu ;
	private Calendar dateLivraisonA;
	private Double coutDu; 
	private Double coutA;
	private Double quantiteDu; 
	private Double quantiteA;
	
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Long getPartieInteresseeId() {
		return partieInteresseeId;
	}
	public void setPartieInteresseeId(Long partieInteresseeId) {
		this.partieInteresseeId = partieInteresseeId;
	}
	public Calendar getDateIntroductionDu() {
		return dateIntroductionDu;
	}
	public void setDateIntroductionDu(Calendar dateIntroductionDu) {
		this.dateIntroductionDu = dateIntroductionDu;
	}
	public Calendar getDateIntroductionA() {
		return dateIntroductionA;
	}
	public void setDateIntroductionA(Calendar dateIntroductionA) {
		this.dateIntroductionA = dateIntroductionA;
	}
	public Calendar getDateLivraisonDu() {
		return dateLivraisonDu;
	}
	public void setDateLivraisonDu(Calendar dateLivraisonDu) {
		this.dateLivraisonDu = dateLivraisonDu;
	}
	public Calendar getDateLivraisonA() {
		return dateLivraisonA;
	}
	public void setDateLivraisonA(Calendar dateLivraisonA) {
		this.dateLivraisonA = dateLivraisonA;
	}
	public Double getCoutDu() {
		return coutDu;
	}
	public void setCoutDu(Double coutDu) {
		this.coutDu = coutDu;
	}
	public Double getCoutA() {
		return coutA;
	}
	public void setCoutA(Double coutA) {
		this.coutA = coutA;
	}
	public Double getQuantiteDu() {
		return quantiteDu;
	}
	public void setQuantiteDu(Double quantiteDu) {
		this.quantiteDu = quantiteDu;
	}
	public Double getQuantiteA() {
		return quantiteA;
	}
	public void setQuantiteA(Double quantiteA) {
		this.quantiteA = quantiteA;
	} 

}