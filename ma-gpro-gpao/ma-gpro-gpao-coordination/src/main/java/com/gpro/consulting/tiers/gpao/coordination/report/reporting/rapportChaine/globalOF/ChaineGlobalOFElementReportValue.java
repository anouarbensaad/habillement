package com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.globalOF;

/**
 * @author Zeineb Medimagh
 *
 */
public class ChaineGlobalOFElementReportValue {

	private String client;
	private String referenceProduit;
	private String designationProduit;
	
	private String ofNum;
	private Long quantite;
	
	private Long sortie;			
	private Long engagement;			
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
	public Long getSortie() {
		return sortie;
	}
	public void setSortie(Long sortie) {
		this.sortie = sortie;
	}
	public Long getEngagement() {
		return engagement;
	}
	public void setEngagement(Long engagement) {
		this.engagement = engagement;
	}
	public Long getReste() {
		return reste;
	}
	public void setReste(Long reste) {
		this.reste = reste;
	}
	
	
}
