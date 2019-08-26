package com.gpro.consulting.tiers.gc.persitance.factureVente.entite;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gc.coordination.IConstanteGC;

/**
 * @author Ameni Berrich
 *
 */
@Entity
@Table(name = IConstanteGC.TABLE_GC_FACTUREVENTE)
public class FactureVenteEntite implements Serializable {

	private static final long serialVersionUID = 5526899889553190941L;

	@Id
	@SequenceGenerator(name = "FACTUREVENTE_ID_GENERATOR", sequenceName = IConstanteGC.SEQUENCE_GC_FACTUREVENTE, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FACTUREVENTE_ID_GENERATOR")
	private Long id;

	@Column(name = "pi__id")
	private Long piId;

	@Column(name = "reference")
	private String reference;

	@Column(name = "rep")
	private String rep;

	@Column(name = "prix_total_ht")
	private Double prixTotalHT;

	@Column(name = "taux_tva")
	private Long tauxTVA;

	@Column(name = "montant_tva")
	private Double montantTVA;

	@Column(name = "montant_ttc")
	private Double montantTTC;

	@Column(name = "date_facture")
	private Calendar dateFacture;

	@Column(name = "date_echeance")
	private Calendar dateEcheance;

	@Column(name = "ref_commande")
	private String refCommande;

	@Column(name = "ref_livraison")
	private String refLivraison;

	@Column(name = "mode_reglement")
	private String modeReglement;

	@Column(name = "partint_id")
	private Long partintId;

	@Column(name = "site_id")
	private Long siteId;

	@Column(name = "observations")
	private String observations;

	@Column(name = "bl_suppression")
	private Boolean blSuppression;

	@Column(name = "date_suppression")
	private Calendar dateSuppression;

	@Column(name = "date_modification")
	private Calendar dateModification;

	@Column(name = "date_creation")
	private Calendar dateCreation;

	@Column(name = "AGENT_ID")
	private Long agentId;

	@Column(name = "TOTAL_FACTURE")
	private Double totalFacture;

	@Column(name = "POIDS_BRUT")
	private Double poidsBrut;

	@Column(name = "POIDS_NET")
	private Double poidsNet;

	@Column(name = "TOTAL_COLIS")
	private Double totalColis;

	@Column(name = "TOTAL_PALETTE")
	private String totalPalette;

	@Column(name = "VALEUR_MAT_PREM")
	private Double valeurMatierePremiere;

	@Column(name = "VALEUR_AJOUTE_ARTICLE")
	private Double valeurAjouteArticle;

	@Column(name = "ORIGINE_TISSUS")
	private String origineTissus;

	// Added on 08/03/2017
	@Column(name = "INCOTERM")
	private String incoterm;

	@Column(name = "VOLUME")
	private String volume;

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "factureVente", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ProduitFactureVenteEntite> produitFactureVente;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "factureVente", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DiversFactureEntite> diversFactureEntite;

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

	public Set<ProduitFactureVenteEntite> getProduitFactureVente() {
		return produitFactureVente;
	}

	public void setProduitFactureVente(Set<ProduitFactureVenteEntite> produitFactureVente) {
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

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public Set<DiversFactureEntite> getDiversFactureEntite() {
		return diversFactureEntite;
	}

	public void setDiversFactureEntite(Set<DiversFactureEntite> diversFactureEntite) {
		this.diversFactureEntite = diversFactureEntite;
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

	public String getTotalPalette() {
		return totalPalette;
	}

	public void setTotalPalette(String totalPalette) {
		this.totalPalette = totalPalette;
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
		return "FactureVenteEntite [id=" + id + ", piId=" + piId + ", reference=" + reference + ", rep=" + rep
				+ ", prixTotalHT=" + prixTotalHT + ", tauxTVA=" + tauxTVA + ", montantTVA=" + montantTVA
				+ ", montantTTC=" + montantTTC + ", dateFacture=" + dateFacture + ", refCommande=" + refCommande
				+ ", refLivraison=" + refLivraison + ", modeReglement=" + modeReglement + ", partintId=" + partintId
				+ ", siteId=" + siteId + ", observations=" + observations + ", blSuppression=" + blSuppression
				+ ", agentId=" + agentId + ", totalFacture=" + totalFacture + ", poidsBrut=" + poidsBrut + ", poidsNet="
				+ poidsNet + ", totalColis=" + totalColis + ", totalPalette=" + totalPalette
				+ ", valeurMatierePremiere=" + valeurMatierePremiere + ", valeurAjouteArticle=" + valeurAjouteArticle
				+ ", origineTissus=" + origineTissus + ", incoterm=" + incoterm + ", volume=" + volume
				+ ", produitFactureVente=" + produitFactureVente + ", diversFactureEntite=" + diversFactureEntite + "]";
	}

	

	
}
