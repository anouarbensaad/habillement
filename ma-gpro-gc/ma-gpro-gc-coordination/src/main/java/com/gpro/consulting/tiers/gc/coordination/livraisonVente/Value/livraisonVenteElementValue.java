package com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 *
 */
public class livraisonVenteElementValue implements Comparable<livraisonVenteElementValue>{

	private Long id;
	private String reference;
	private String referenceCommande;
	private Long partieIntersseId;
	private Double prixTotal;
	private String saison;
	private Long colis;
	private Calendar dateCommande;
	private Calendar dateLivraison;
	private Long livraisonVenteId;
	private Long agentId;
	private String partieIntersseDesignation;
	
	@Override
	public int compareTo(livraisonVenteElementValue o) {
		livraisonVenteElementValue element= (livraisonVenteElementValue)o;
		return (element.getId().compareTo(id));
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getReferenceCommande() {
		return referenceCommande;
	}

	public void setReferenceCommande(String referenceCommande) {
		this.referenceCommande = referenceCommande;
	}

	public String getReference() {
		return reference;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
	public Long getPartieIntersseId() {
		return partieIntersseId;
	}
	public void setPartieIntersseId(Long partieIntersseId) {
		this.partieIntersseId = partieIntersseId;
	}

	
	public String getSaison() {
		return saison;
	}
	public void setSaison(String saison) {
		this.saison = saison;
	}
	public Long getColis() {
		return colis;
	}
	public void setColis(Long colis) {
		this.colis = colis;
	}
	public Calendar getDateLivraison() {
		return dateLivraison;
	}
	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}
	public Long getLivraisonVenteId() {
		return livraisonVenteId;
	}
	public void setLivraisonVenteId(Long livraisonVenteId) {
		this.livraisonVenteId = livraisonVenteId;
	}

	public String getPartieIntersseDesignation() {
		return partieIntersseDesignation;
	}

	public void setPartieIntersseDesignation(String partieIntersseDesignation) {
		this.partieIntersseDesignation = partieIntersseDesignation;
	}

	public Calendar getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Calendar dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	
}
