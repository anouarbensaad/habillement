package com.gpro.consulting.tiers.gc.coordination.value;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class CommandeVenteValue implements Comparable<CommandeVenteValue>{


	/** The id. */
    private Long id;
	
	/** The com site id. */
	private Long siteId;
	
	/** The siteDesignation. */
	private String siteDesignation;
	
	/** The reference. */
	private String reference;
	
	/** The saison. */
	private String saison;
	
	/** The prix total */
	private Double prixTotal;
	
	/** The date introduction. */
	private Calendar dateIntroduction;
	
	/** The date livraison. */
	private Calendar dateLivraison;

	/** The observations. */
	private String observations;
	
	/** The pi pi id. */
	private Long partieIntersseId;
	
	/** The partieIntersseDesignation. */
	private String partieIntersseDesignation;
	
	/** The prix total */
	private Long typeCommande_id;
	
	/** The typeCommandeDesignation. */
	private String typeCommandeDesignation;
	
	/** The etat  */
	private Long etatCommande_id;
	
	/** The etatCommandeDesignation. */
	private String etatCommandeDesignation;
	
	//added on 15/03/2016, by Wahid Gazzah
	private Long quantite;
		
	//added on 19/07/2016, by Ameni
	private Long agentId;
	
	
	
	private Boolean fromOf;
	
	
	private String of_reference;
	
	/** The document commandes. */
	private Set<DocumentCommandeVenteValue> documentCommandeVentes = new HashSet<DocumentCommandeVenteValue>();
	
	/** The ProduitCommandeValue. */
	private Set<ProduitCommandeValue> produitCommandes = new HashSet<ProduitCommandeValue>();
	
	@Override
	public int compareTo(CommandeVenteValue o) {
		CommandeVenteValue element= (CommandeVenteValue)o;
		return (element.getId().compareTo(id));
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the siteId
	 */
	public Long getSiteId() {
		return siteId;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

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
	 * @return the observations
	 */
	public String getObservations() {
		return observations;
	}

	/**
	 * @param observations the observations to set
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}

	/**
	 * @return the partieIntersseId
	 */
	public Long getPartieIntersseId() {
		return partieIntersseId;
	}

	/**
	 * @param partieIntersseId the partieIntersseId to set
	 */
	public void setPartieIntersseId(Long partieIntersseId) {
		this.partieIntersseId = partieIntersseId;
	}

	/**
	 * @return the typeCommande_id
	 */
	public Long getTypeCommande_id() {
		return typeCommande_id;
	}

	/**
	 * @param typeCommande_id the typeCommande_id to set
	 */
	public void setTypeCommande_id(Long typeCommande_id) {
		this.typeCommande_id = typeCommande_id;
	}

	/**
	 * @return the etatCommande_id
	 */
	public Long getEtatCommande_id() {
		return etatCommande_id;
	}

	/**
	 * @param etatCommande_id the etatCommande_id to set
	 */
	public void setEtatCommande_id(Long etatCommande_id) {
		this.etatCommande_id = etatCommande_id;
	}

	/**
	 * @return the documentCommandeVentes
	 */
	public Set<DocumentCommandeVenteValue> getDocumentCommandeVentes() {
		return documentCommandeVentes;
	}

	/**
	 * @param documentCommandeVentes the documentCommandeVentes to set
	 */
	public void setDocumentCommandeVentes(
			Set<DocumentCommandeVenteValue> documentCommandeVentes) {
		this.documentCommandeVentes = documentCommandeVentes;
	}

	/**
	 * @return the produitCommandes
	 */
	public Set<ProduitCommandeValue> getProduitCommandes() {
		return produitCommandes;
	}

	/**
	 * @param produitCommandes the produitCommandes to set
	 */
	public void setProduitCommandes(Set<ProduitCommandeValue> produitCommandes) {
		this.produitCommandes = produitCommandes;
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

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}


	@Override
	public String toString() {
		return "CommandeVenteValue [id=" + id + ", quantite=" + quantite + ", produitCommandes=" + produitCommandes
				+ "]";
	}

	public Boolean getFromOf() {
		return fromOf;
	}

	public void setFromOf(Boolean fromOf) {
		this.fromOf = fromOf;
	}

	public String getOf_reference() {
		return of_reference;
	}

	public void setOf_reference(String of_reference) {
		this.of_reference = of_reference;
	}
	
	
}
