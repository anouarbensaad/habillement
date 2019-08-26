package com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.jourOF;

/**
 * @author Zeineb Medimagh
 *
 */
public class ChaineJourOFElementReportValue {

	private String client;
	private String referenceProduit;
	private String designationProduit;
	
	private String ofNum;
	private Long quantite;
	
	private Long sortieJour;
	private Long sortieCumul;
	private Long reste;
	
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getReferenceProduit() {
		return referenceProduit;
	}
	public void setReferenceProduit(String referenceProduit) {
		this.referenceProduit = referenceProduit;
	}
	public String getDesignationProduit() {
		return designationProduit;
	}
	public void setDesignationProduit(String designationProduit) {
		this.designationProduit = designationProduit;
	}
	
	public String getOfNum() {
		return ofNum;
	}
	public void setOfNum(String ofNum) {
		this.ofNum = ofNum;
	}
	public Long getQuantite() {
		return quantite;
	}
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}
	public Long getSortieJour() {
		return sortieJour;
	}
	public void setSortieJour(Long sortieJour) {
		this.sortieJour = sortieJour;
	}
	public Long getSortieCumul() {
		return sortieCumul;
	}
	public void setSortieCumul(Long sortieCumul) {
		this.sortieCumul = sortieCumul;
	}
	public Long getReste() {
		return reste;
	}
	public void setReste(Long reste) {
		this.reste = reste;
	}
	
	
}
