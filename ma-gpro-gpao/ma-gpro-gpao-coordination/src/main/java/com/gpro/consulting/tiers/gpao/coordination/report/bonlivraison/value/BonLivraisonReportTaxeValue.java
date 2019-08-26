package com.gpro.consulting.tiers.gpao.coordination.report.bonlivraison.value;


/**
 * @author Wahid Gazzah
 * @since 01/02/2016
 */
public class BonLivraisonReportTaxeValue {
	
	private Long taxeId;
	private Double pourcentage;
	private Double montant;
	private String taxeDesignation;
	
	public Double getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(Double pourcentage) {
		this.pourcentage = pourcentage;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public String getTaxeDesignation() {
		return taxeDesignation;
	}
	public void setTaxeDesignation(String taxeDesignation) {
		this.taxeDesignation = taxeDesignation;
	}
	public Long getTaxeId() {
		return taxeId;
	}
	public void setTaxeId(Long taxeId) {
		this.taxeId = taxeId;
	}
	
	

}
