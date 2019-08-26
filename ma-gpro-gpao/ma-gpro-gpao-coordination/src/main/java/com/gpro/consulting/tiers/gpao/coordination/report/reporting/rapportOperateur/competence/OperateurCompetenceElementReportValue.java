package com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportOperateur.competence;

/**
 * @author Zeineb Medimagh
 *
 */
public class OperateurCompetenceElementReportValue {

		
	private Double temps;
	private Long quantiteProduite;
	private String codeDesignation;
	private String codeReference;
	// ajouter machine 
	private String machine;

	
	
	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
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
