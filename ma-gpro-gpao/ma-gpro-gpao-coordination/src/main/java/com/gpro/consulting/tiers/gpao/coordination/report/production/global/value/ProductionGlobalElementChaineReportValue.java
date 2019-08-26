package com.gpro.consulting.tiers.gpao.coordination.report.production.global.value;

/**
 * @author Wahid Gazzah
 * @since 10/08/2016
 *
 */
public class ProductionGlobalElementChaineReportValue implements Comparable<ProductionGlobalElementChaineReportValue>{

	private Long clientId;
	private String clientReference;
	private String clientAbreviation;
	
	private Long produitId;
	private String produitReference;
	private String produitDesignation;
	
	private Long ofId;
	private String ofNumero;
	
	//see gp_operation table
	private Long engagement;			//ENG
	private Long sortieChaine;			//SCH
	private Long sortieConditionnement;	//SCO
	

	@Override
	public int compareTo(ProductionGlobalElementChaineReportValue element) {
		return (element.getClientId().compareTo(clientId));
	}


	public Long getClientId() {
		return clientId;
	}


	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}


	public String getClientReference() {
		return clientReference;
	}


	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}


	public String getClientAbreviation() {
		return clientAbreviation;
	}


	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}


	public Long getProduitId() {
		return produitId;
	}


	public void setProduitId(Long produitId) {
		this.produitId = produitId;
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


	public Long getOfId() {
		return ofId;
	}


	public void setOfId(Long ofId) {
		this.ofId = ofId;
	}


	public String getOfNumero() {
		return ofNumero;
	}


	public void setOfNumero(String ofNumero) {
		this.ofNumero = ofNumero;
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


	public Long getSortieConditionnement() {
		return sortieConditionnement;
	}


	public void setSortieConditionnement(Long sortieConditionnement) {
		this.sortieConditionnement = sortieConditionnement;
	}
	
	
}
