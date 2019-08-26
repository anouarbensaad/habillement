package com.gpro.consulting.tiers.gc.coordination.factureVente.Value;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ameni Berrich
 *
 */
public class FactureVenteValue {

	private Long id;
	private Long piId;
	private String reference;
	private String rep;
	private Double prixTotalHT;
	private Long tauxTVA;
	private Double montantTVA;
	private Double montantTTC;
	private Calendar dateFacture;
	private Calendar dateEcheance;
	private String refCommande;
	private String refLivraison;
	private String modeReglement;
	private Long partintId;
	private Long siteId;
	private String observations;
	private Boolean blSuppression;
	private Calendar dateSuppression;
	private Calendar dateModification;
	private Calendar dateCreation;
	private Long colis;
	private Long agentId;

	/**
	 * Hajer AMRI
	 */
	private Double totalFacture;
	private Double poidsBrut;
	private Double poidsNet;
	private Double totalColis;
	private String totalPalette;
	private Double valeurMatierePremiere;
	private Double valeurAjouteArticle;
	private String origineTissus;


	// Added on 08/03/2017
	private String incoterm;
	private String volume;

	private Set<ProduitFactureVenteValue> produitFactureVente = new HashSet<ProduitFactureVenteValue>();
	private Set<DiversFactureValue> diversFactureValue = new HashSet<DiversFactureValue>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPiId() {
		return piId;
	}

	public void setPiId(Long piId) {
		this.piId = piId;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getRep() {
		return rep;
	}

	public void setRep(String rep) {
		this.rep = rep;
	}

	public Double getPrixTotalHT() {
		return prixTotalHT;
	}

	public void setPrixTotalHT(Double prixTotalHT) {
		this.prixTotalHT = prixTotalHT;
	}

	public Long getTauxTVA() {
		return tauxTVA;
	}

	public void setTauxTVA(Long tauxTVA) {
		this.tauxTVA = tauxTVA;
	}

	public Double getMontantTVA() {
		return montantTVA;
	}

	public void setMontantTVA(Double montantTVA) {
		this.montantTVA = montantTVA;
	}

	public Double getMontantTTC() {
		return montantTTC;
	}

	public void setMontantTTC(Double montantTTC) {
		this.montantTTC = montantTTC;
	}

	public Calendar getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(Calendar dateFacture) {
		this.dateFacture = dateFacture;
	}

	public Calendar getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Calendar dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public String getRefCommande() {
		return refCommande;
	}

	public void setRefCommande(String refCommande) {
		this.refCommande = refCommande;
	}

	public String getModeReglement() {
		return modeReglement;
	}

	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}

	public Long getPartintId() {
		return partintId;
	}

	public void setPartintId(Long partintId) {
		this.partintId = partintId;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Boolean isBlSuppression() {
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

	public Set<ProduitFactureVenteValue> getProduitFactureVente() {
		return produitFactureVente;
	}

	public void setProduitFactureVente(Set<ProduitFactureVenteValue> produitFactureVente) {
		this.produitFactureVente = produitFactureVente;
	}

	public String getRefLivraison() {
		return refLivraison;
	}

	public void setRefLivraison(String refLivraison) {
		this.refLivraison = refLivraison;
	}

	public Boolean getBlSuppression() {
		return blSuppression;
	}

	public Long getColis() {
		return colis;
	}

	public void setColis(Long colis) {
		this.colis = colis;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public Set<DiversFactureValue> getDiversFactureValue() {
		return diversFactureValue;
	}

	public void setDiversFactureValue(Set<DiversFactureValue> diversFactureValue) {
		this.diversFactureValue = diversFactureValue;
	}

	public Double getTotalFacture() {
		return totalFacture;
	}

	public void setTotalFacture(Double totalFacture) {
		this.totalFacture = totalFacture;
	}

	public Double getPoidsBrut() {
		return poidsBrut;
	}

	public void setPoidsBrut(Double poidsBrut) {
		this.poidsBrut = poidsBrut;
	}

	public Double getPoidsNet() {
		return poidsNet;
	}

	public void setPoidsNet(Double poidsNet) {
		this.poidsNet = poidsNet;
	}

	public Double getTotalColis() {
		return totalColis;
	}

	public void setTotalColis(Double totalColis) {
		this.totalColis = totalColis;
	}

	public String getTotalPalette() {
		return totalPalette;
	}

	public void setTotalPalette(String totalPalette) {
		this.totalPalette = totalPalette;
	}

	public Double getValeurMatierePremiere() {
		return valeurMatierePremiere;
	}

	public void setValeurMatierePremiere(Double valeurMatierePremiere) {
		this.valeurMatierePremiere = valeurMatierePremiere;
	}

	public Double getValeurAjouteArticle() {
		return valeurAjouteArticle;
	}

	public void setValeurAjouteArticle(Double valeurAjouteArticle) {
		this.valeurAjouteArticle = valeurAjouteArticle;
	}

	public String getOrigineTissus() {
		return origineTissus;
	}

	public void setOrigineTissus(String origineTissus) {
		this.origineTissus = origineTissus;
	}

	public String getIncoterm() {
		return incoterm;
	}

	public void setIncoterm(String incoterm) {
		this.incoterm = incoterm;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "FactureVenteValue [id=" + id + ", piId=" + piId + ", reference=" + reference + ", rep=" + rep
				+ ", prixTotalHT=" + prixTotalHT + ", tauxTVA=" + tauxTVA + ", montantTVA=" + montantTVA
				+ ", montantTTC=" + montantTTC + ", refCommande=" + refCommande + ", refLivraison=" + refLivraison
				+ ", modeReglement=" + modeReglement + ", partintId=" + partintId + ", siteId=" + siteId
				+ ", observations=" + observations + ", colis=" + colis + ", agentId=" + agentId + ", totalFacture="
				+ totalFacture + ", poidsBrut=" + poidsBrut + ", poidsNet=" + poidsNet + ", totalColis=" + totalColis
				+ ", origineTissus=" + origineTissus + ", incoterm=" + incoterm + ", volume=" + volume
				+ ", produitFactureVente=" + produitFactureVente + "]";
	}
	
	

}
