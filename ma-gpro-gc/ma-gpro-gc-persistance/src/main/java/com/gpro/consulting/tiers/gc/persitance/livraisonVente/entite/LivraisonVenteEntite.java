package com.gpro.consulting.tiers.gc.persitance.livraisonVente.entite;

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
@Table(name=IConstanteGC.TABLE_GC_LIVRAISONVENTE)
public class LivraisonVenteEntite implements Serializable{

	private static final long serialVersionUID = 7153723674849932021L;

	@Id
	@SequenceGenerator(name="LIVRAISONVENTE_ID_GENERATOR", sequenceName=IConstanteGC.SEQUENCE_GC_LIVRAISONCOMMANDE,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LIVRAISONVENTE_ID_GENERATOR")
    private Long id;
	
	@Column(name="reference")
	private String reference;
	
	@Column(name="saison")
	private String saison;

	@Column(name="prix_total")
	private Double prixTotal;
	
	@Column(name="date_commande")
	private Calendar dateCommande;
	
	@Column(name="date_livraison")
	private Calendar dateLivraison;

	@Column(name="ref_commande")
	private String refCommande;

	@Column(name="colis")
	private Long colis;

	@Column(name="poids")
	private Double poids;

	@Column(name="observations")
	private String observations;

	@Column(name="partint_id")
	private Long partieIntersseId;
	
	@Column(name="site_id")
	private Long siteId;
	
	@Column(name="mode_reglement")
	private String modeReglement;
	
	@Column(name="bl_suppression")
	private Boolean blSuppression;
	
	@Column(name="date_suppression")
	private Calendar dateSuppression;
	
	@Column(name="date_modification")
	private Calendar dateModification;
	
	@Column(name="date_creation")
	private Calendar dateCreation;
	
	@Column(name = "AGENT_ID")
	private Long agentId;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="livraisonVente", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ProduitLivraisonEntite> produitLivraison;

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

	public Set<ProduitLivraisonEntite> getProduitLivraison() {
		return produitLivraison;
	}

	public void setProduitLivraison(Set<ProduitLivraisonEntite> produitLivraison) {
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

	public Boolean getBlSuppression() {
		return blSuppression;
	}

	@Override
	public String toString() {
		return "LivraisonVenteEntite [id=" + id + ", reference=" + reference + ", produitLivraison=" + produitLivraison
				+ "]";
	}

}
