package com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.globalOperation;

/**
 * @author Ameni Berrich
 *
 */
public class ChaineGlobalOperationElementReportValue {

	private Long codeId;
	
	private Double temps;
	private Long quantiteProduite;
	
	private String codeDesignation;
	private String codeReference;

	public Long getCodeId() {
		return codeId;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

	public String getCodeReference() {
		return codeReference;
	}

	public void setCodeReference(String codeReference) {
		this.codeReference = codeReference;
	}

	public Double getTemps() {
		return temps;
	}

	public void setTemps(Double temps) {
		this.temps = temps;
	}

	public Long getQuantiteProduite() {
		return quantiteProduite;
	}

	public void setQuantiteProduite(Long quantiteProduite) {
		this.quantiteProduite = quantiteProduite;
	}

	public String getCodeDesignation() {
		return codeDesignation;
	}

	public void setCodeDesignation(String codeDesignation) {
		this.codeDesignation = codeDesignation;
	}
	
}
