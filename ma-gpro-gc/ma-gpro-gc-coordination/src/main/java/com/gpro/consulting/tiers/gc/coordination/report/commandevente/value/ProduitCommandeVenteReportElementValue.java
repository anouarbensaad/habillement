/**
 *
 */
package com.gpro.consulting.tiers.gc.coordination.report.commandevente.value;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 * @since 21 mars 2016
 */
public class ProduitCommandeVenteReportElementValue {
	
	private Long commandeVenteId;
	private Long produitId;
	private Calendar dateLivraison;
	private String saison;
	private Double prix;
	private Long quantite;
	private Long colis;
	private String palette;
	
	//conversion id / designation
	private String referenceClient;
	private String clientDesignation;
	private String referenceBC;
	
	private String produitReference;
	private String produitDesignation;
	/**
	 * @return the commandeVenteId
	 */
	public Long getCommandeVenteId() {
	
		return commandeVenteId;
	}
	/**
	 * @param commandeVenteId the commandeVenteId to set
	 */
	public void setCommandeVenteId(Long commandeVenteId) {
		this.commandeVenteId = commandeVenteId;
	}
	/**
	 * @return the produitId
	 */
	public Long getProduitId() {
	
		return produitId;
	}
	/**
	 * @param produitId the produitId to set
	 */
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	/**
	 * @return the dateLivraison
	 */
	public Calendar getDateLivraison() {
	
		return dateLivraison;
	}
	/**
	 * @param dateLivraison the dateLivraison to set
	 */
	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}
	/**
	 * @return the saison
	 */
	public String getSaison() {
	
		return saison;
	}
	/**
	 * @param saison the saison to set
	 */
	public void setSaison(String saison) {
		this.saison = saison;
	}
	/**
	 * @return the prix
	 */
	public Double getPrix() {
	
		return prix;
	}
	/**
	 * @param prix the prix to set
	 */
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	/**
	 * @return the quantite
	 */
	public Long getQuantite() {
	
		return quantite;
	}
	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}
	/**
	 * @return the referenceClient
	 */
	public String getReferenceClient() {
	
		return referenceClient;
	}
	/**
	 * @param referenceClient the referenceClient to set
	 */
	public void setReferenceClient(String referenceClient) {
		this.referenceClient = referenceClient;
	}
	/**
	 * @return the clientDesignation
	 */
	public String getClientDesignation() {
	
		return clientDesignation;
	}
	/**
	 * @param clientDesignation the clientDesignation to set
	 */
	public void setClientDesignation(String clientDesignation) {
		this.clientDesignation = clientDesignation;
	}
	/**
	 * @return the referenceBC
	 */
	public String getReferenceBC() {
	
		return referenceBC;
	}
	/**
	 * @param referenceBC the referenceBC to set
	 */
	public void setReferenceBC(String referenceBC) {
		this.referenceBC = referenceBC;
	}
	/**
	 * @return the produitReference
	 */
	public String getProduitReference() {
	
		return produitReference;
	}
	/**
	 * @param produitReference the produitReference to set
	 */
	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}
	/**
	 * @return the produitDesignation
	 */
	public String getProduitDesignation() {
	
		return produitDesignation;
	}
	/**
	 * @param produitDesignation the produitDesignation to set
	 */
	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}
	public Long getColis() {
		return colis;
	}
	public void setColis(Long colis) {
		this.colis = colis;
	}
	public String getPalette() {
		return palette;
	}
	public void setPalette(String palette) {
		this.palette = palette;
	}
	@Override
	public String toString() {
		return "ProduitCommandeVenteReportElementValue [commandeVenteId=" + commandeVenteId + ", produitId=" + produitId
				+ ", dateLivraison=" + dateLivraison + ", saison=" + saison + ", prix=" + prix + ", quantite="
				+ quantite + ", colis=" + colis + ", palette=" + palette + ", referenceClient=" + referenceClient
				+ ", clientDesignation=" + clientDesignation + ", referenceBC=" + referenceBC + ", produitReference="
				+ produitReference + ", produitDesignation=" + produitDesignation + "]";
	}
	
	
	
}
