package com.gpro.consulting.tiers.gc.coordination.report.commandevente.value;

/**
 * @author Ameni Berrich
 *
 */
public class DetailsProduitLivraisonReportValue {

	private Double montant;
	private String couleurDesignation;
	private String tailleDesignation;
	private Long tailleId;
	
	// quantites par tailleDesignation
	private Long quantite;
	private Long quantiteTD1;
	private Long quantiteTD2;
	private Long quantiteTD3;
	private Long quantiteTD4;
	private Long quantiteTD5;
	private Long quantiteTD6;
	
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public String getCouleurDesignation() {
		return couleurDesignation;
	}
	public void setCouleurDesignation(String couleurDesignation) {
		this.couleurDesignation = couleurDesignation;
	}
	public String getTailleDesignation() {
		return tailleDesignation;
	}
	public void setTailleDesignation(String tailleDesignation) {
		this.tailleDesignation = tailleDesignation;
	}
	public Long getTailleId() {
		return tailleId;
	}
	public void setTailleId(Long tailleId) {
		this.tailleId = tailleId;
	}
	public Long getQuantite() {
		return quantite;
	}
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}
	public Long getQuantiteTD1() {
		return quantiteTD1;
	}
	public void setQuantiteTD1(Long quantiteTD1) {
		this.quantiteTD1 = quantiteTD1;
	}
	public Long getQuantiteTD2() {
		return quantiteTD2;
	}
	public void setQuantiteTD2(Long quantiteTD2) {
		this.quantiteTD2 = quantiteTD2;
	}
	public Long getQuantiteTD3() {
		return quantiteTD3;
	}
	public void setQuantiteTD3(Long quantiteTD3) {
		this.quantiteTD3 = quantiteTD3;
	}
	public Long getQuantiteTD4() {
		return quantiteTD4;
	}
	public void setQuantiteTD4(Long quantiteTD4) {
		this.quantiteTD4 = quantiteTD4;
	}
	public Long getQuantiteTD5() {
		return quantiteTD5;
	}
	public void setQuantiteTD5(Long quantiteTD5) {
		this.quantiteTD5 = quantiteTD5;
	}
	public Long getQuantiteTD6() {
		return quantiteTD6;
	}
	public void setQuantiteTD6(Long quantiteTD6) {
		this.quantiteTD6 = quantiteTD6;
	}
}
