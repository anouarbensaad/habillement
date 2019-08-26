package com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ameni Berrich
 *
 */
public class LivraisonVenteValue implements Comparable<LivraisonVenteValue>{

	private Long id;
	private String reference;
	private String saison;
	private Double prixTotal;
	private Calendar dateCommande;
	private Calendar dateLivraison;
	private String refCommande;
	private Long colis;
	private Double poids;
	private String observations;
	private Long partieIntersseId;
	private Long siteId;
	private String modeReglement;
	private Boolean blSuppression;
	private Calendar dateSuppression;
	private Calendar dateModification;
	private Calendar dateCreation;
	private Long agentId;
	private Set<ProduitLivraisonValue> produitLivraison = new HashSet<ProduitLivraisonValue>();
	
	@Override
	public int compareTo(LivraisonVenteValue o) {
		LivraisonVenteValue element= (LivraisonVenteValue)o;
		return (element.getId().compareTo(id));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getSaison() {
		return saison;
	}

	public void setSaison(String saison) {
		this.saison = saison;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Calendar getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Calendar dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public String getRefCommande() {
		return refCommande;
	}

	public void setRefCommande(String refCommande) {
		this.refCommande = refCommande;
	}

	public Long getColis() {
		return colis;
	}

	public void setColis(Long colis) {
		this.colis = colis;
	}

	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double poids) {
		this.poids = poids;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Long getPartieIntersseId() {
		return partieIntersseId;
	}

	public void setPartieIntersseId(Long partieIntersseId) {
		this.partieIntersseId = partieIntersseId;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public Boolean getBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public Calendar getDateModification() {
		return dateModification;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Set<ProduitLivraisonValue> getProduitLivraison() {
		return produitLivraison;
	}

	public void setProduitLivraison(Set<ProduitLivraisonValue> produitLivraison) {
		this.produitLivraison = produitLivraison;
	}

	public String getModeReglement() {
		return modeReglement;
	}

	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	@Override
	public String toString() {
		return "LivraisonVenteValue [id=" + id + ", reference=" + reference + ", produitLivraison=" + produitLivraison
				+ "]";
	}

}
