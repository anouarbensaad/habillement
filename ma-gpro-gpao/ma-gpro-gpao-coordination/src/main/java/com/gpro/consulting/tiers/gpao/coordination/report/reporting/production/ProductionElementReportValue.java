
package com.gpro.consulting.tiers.gpao.coordination.report.reporting.production;

/**
 * @author Hamdi Etteieb 
 *
 */
public class ProductionElementReportValue implements Comparable<ProductionElementReportValue>{

	private Long id;
    private Long demande;
	private Long jour;
	private Long rest;
	private Long cumule;
	private Long eclatee;


	private String designation;
	private String reference;
	private String oFId;
	
	@Override
	public int compareTo(ProductionElementReportValue o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDemande() {
		return demande;
	}

	public void setDemande(Long demande) {
		this.demande = demande;
	}

	public Long getJour() {
		return jour;
	}

	public void setJour(Long jour) {
		this.jour = jour;
	}

	public Long getRest() {
		return rest;
	}

	public void setRest(Long rest) {
		this.rest = rest;
	}


	public Long getCumule() {
		return cumule;
	}

	public void setCumule(Long cumule) {
		this.cumule = cumule;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getoFId() {
		return oFId;
	}

	public void setoFId(String oFId) {
		this.oFId = oFId;
	}

	public Long getEclatee() {
		return eclatee;
	}

	public void setEclatee(Long eclatee) {
		this.eclatee = eclatee;
	}
	
	
	
		
}
