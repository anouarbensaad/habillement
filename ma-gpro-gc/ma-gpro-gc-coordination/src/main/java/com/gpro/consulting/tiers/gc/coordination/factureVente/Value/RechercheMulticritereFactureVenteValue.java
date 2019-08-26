package com.gpro.consulting.tiers.gc.coordination.factureVente.Value;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 *
 */
public class RechercheMulticritereFactureVenteValue {
	private String reference;
	private Long partieInteresseeId;
	private Calendar dateFactureDu;
	private Calendar dateFactureAu;
	private Calendar dateEcheanceDu;
	private Calendar dateEcheanceAu;
	private Double coutDu; 
	private Double coutA; 
	
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Calendar getDateFactureDu() {
		return dateFactureDu;
	}

	public void setDateFactureDu(Calendar dateFactureDu) {
		this.dateFactureDu = dateFactureDu;
	}

	public Calendar getDateFactureAu() {
		return dateFactureAu;
	}

	public void setDateFactureAu(Calendar dateFactureAu) {
		this.dateFactureAu = dateFactureAu;
	}

	public Calendar getDateEcheanceDu() {
		return dateEcheanceDu;
	}

	public void setDateEcheanceDu(Calendar dateEcheanceDu) {
		this.dateEcheanceDu = dateEcheanceDu;
	}

	public Calendar getDateEcheanceAu() {
		return dateEcheanceAu;
	}

	public void setDateEcheanceAu(Calendar dateEcheanceAu) {
		this.dateEcheanceAu = dateEcheanceAu;
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

	public Long getPartieInteresseeId() {
		return partieInteresseeId;
	}

	public void setPartieInteresseeId(Long partieInteresseeId) {
		this.partieInteresseeId = partieInteresseeId;
	}
	
}
