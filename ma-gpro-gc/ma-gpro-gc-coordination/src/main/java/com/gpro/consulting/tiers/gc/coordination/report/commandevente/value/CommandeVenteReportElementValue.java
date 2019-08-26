package com.gpro.consulting.tiers.gc.coordination.report.commandevente.value;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 * @since 14 mars 2016
 */
public class CommandeVenteReportElementValue {
	private String reference;
	private Long typeCommande;
	private Calendar dateIntroduction;
	private Calendar dateLivraison;
	private Long etatCommande;
	private String saison;
	private Double prixTotal;
	private Long partieInteressee;
	private Long site;
	
	//conversion id / designation
	private String partieIntersseDesignation;
	private String typeCommandeDesignation;
	private String etatCommandeDesignation;
	private String siteDesignation;
	
	//Added on 25/03/2016, by Ameni Berrich
	private Long quantite;
	
	//Added on 23/12/2016, by Hajer Amri
	private Long colis;
	private String palette;
	
	/**
	 * @return the reference
	 */
	public String getReference() {
	
		return reference;
	}
	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	/**
	 * @return the typeCommande
	 */
	public Long getTypeCommande() {
	
		return typeCommande;
	}
	/**
	 * @param typeCommande the typeCommande to set
	 */
	public void setTypeCommande(Long typeCommande) {
		this.typeCommande = typeCommande;
	}
	/**
	 * @param etatCommande the etatCommande to set
	 */
	public void setEtatCommande(Long etatCommande) {
		this.etatCommande = etatCommande;
	}
	
	/**
	 * @return the dateIntroduction
	 */
	public Calendar getDateIntroduction() {
	
		return dateIntroduction;
	}
	/**
	 * @param dateIntroduction the dateIntroduction to set
	 */
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
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
	 * @return the prixTotal
	 */
	public Double getPrixTotal() {
	
		return prixTotal;
	}
	/**
	 * @param prixTotal the prixTotal to set
	 */
	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}
	
	/**
	 * @return the partieInteressee
	 */
	public Long getPartieInteressee() {
	
		return partieInteressee;
	}
	/**
	 * @param partieInteressee the partieInteressee to set
	 */
	public void setPartieInteressee(Long partieInteressee) {
		this.partieInteressee = partieInteressee;
	}
	
	/**
	 * @return the site
	 */
	public Long getSite() {
	
		return site;
	}
	/**
	 * @param site the site to set
	 */
	public void setSite(Long site) {
		this.site = site;
	}
	/**
	 * @return the partieIntersseDesignation
	 */
	public String getPartieIntersseDesignation() {
	
		return partieIntersseDesignation;
	}
	/**
	 * @param partieIntersseDesignation the partieIntersseDesignation to set
	 */
	public void setPartieIntersseDesignation(String partieIntersseDesignation) {
		this.partieIntersseDesignation = partieIntersseDesignation;
	}
	/**
	 * @return the typeCommandeDesignation
	 */
	public String getTypeCommandeDesignation() {
	
		return typeCommandeDesignation;
	}
	/**
	 * @param typeCommandeDesignation the typeCommandeDesignation to set
	 */
	public void setTypeCommandeDesignation(String typeCommandeDesignation) {
		this.typeCommandeDesignation = typeCommandeDesignation;
	}
	/**
	 * @return the etatCommandeDesignation
	 */
	public String getEtatCommandeDesignation() {
	
		return etatCommandeDesignation;
	}
	/**
	 * @param etatCommandeDesignation the etatCommandeDesignation to set
	 */
	public void setEtatCommandeDesignation(String etatCommandeDesignation) {
		this.etatCommandeDesignation = etatCommandeDesignation;
	}
	/**
	 * @return the etatCommande
	 */
	public Long getEtatCommande() {
	
		return etatCommande;
	}
	/**
	 * @return the siteDesignation
	 */
	public String getSiteDesignation() {
	
		return siteDesignation;
	}
	/**
	 * @param siteDesignation the siteDesignation to set
	 */
	public void setSiteDesignation(String siteDesignation) {
		this.siteDesignation = siteDesignation;
	}
	
	//added on 25/03/2016, by Ameni Berrich
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
		return "CommandeVenteReportElementValue [reference=" + reference + ", typeCommande=" + typeCommande
				+ ", dateIntroduction=" + dateIntroduction + ", dateLivraison=" + dateLivraison + ", etatCommande="
				+ etatCommande + ", saison=" + saison + ", prixTotal=" + prixTotal + ", partieInteressee="
				+ partieInteressee + ", site=" + site + ", partieIntersseDesignation=" + partieIntersseDesignation
				+ ", typeCommandeDesignation=" + typeCommandeDesignation + ", etatCommandeDesignation="
				+ etatCommandeDesignation + ", siteDesignation=" + siteDesignation + ", quantite=" + quantite
				+ ", colis=" + colis + ", palette=" + palette + "]";
	}
	
	
	
}
