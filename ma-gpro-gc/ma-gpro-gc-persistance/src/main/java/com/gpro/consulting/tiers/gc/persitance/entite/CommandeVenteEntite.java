package com.gpro.consulting.tiers.gc.persitance.entite;

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
 * The Class CommandeVente
 * @author $Ameni
 */
@Entity
@Table(name=IConstanteGC.TABLE_GC_COMMANDEVENTE)
public class CommandeVenteEntite implements Serializable{

	private static final long serialVersionUID = 363140010439658847L;
	
	/** The id. */
	@Id
	@SequenceGenerator(name="COMMANDEVENTE_ID_GENERATOR", sequenceName=IConstanteGC.SEQUENCE_GC_COMMANDEVENTE,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COMMANDEVENTE_ID_GENERATOR")
    private Long id;
	
	/** The com site id. */
	@Column(name="pi_com_site_id")
	private Long siteId;
	
	/** The reference. */
	@Column(name="reference")
	private String reference;
	
	/** The saison. */
	@Column(name="saison")
	private String saison;
	
	/** The prix total */
	@Column(name="prix_total")
	private Double prixTotal;
	
	/** The date introduction. */
	@Column(name="date_introduction")
	private Calendar dateIntroduction;
	
	/** The date livraison. */
	@Column(name="date_livraison")
	private Calendar dateLivraison;

	/** The observations. */
	@Column(name="observations")
	private String observations;
	
	/** The pi pi id. */
	@Column(name="pi_pi_id")
	private Long partieIntersseId;
	
	
	/** The prix total */
	@Column(name="gc_typebc_id")
	private Long typeCommande_id;
	
	
	/** The etat  */
	@Column(name="gc_etatbc_id")
	private Long etatCommande_id;
	
	
	/** Ajouté par Ghazi on 08/01/2017  */
	/** The OF  */
	@Column(name="gp_of_numero")
	private String of_reference;
	
	
	
	/** The date creation. */
	@Column(name="date_creation")
	private Calendar dateCreation;
	
	/** The date modification. */
	@Column(name="date_modification")
	private Calendar dateModification;

	/** The date suppression. */
	@Column(name="date_suppression")
	private Calendar dateSuppression;
	
	/** The bl suppression. */
	@Column(name="bl_suppression")
	private boolean blSuppression;
	
	/** many-to-one association to ProduitCommande. */
	@OneToMany(fetch=FetchType.LAZY,mappedBy="commandeVente", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ProduitCommandeEntite> produitCommandes;
	
	/** many-to-one association to DocumentCommande. */
	@OneToMany(fetch=FetchType.LAZY,mappedBy="commandeVente", cascade = CascadeType.ALL)
	private Set<DocumentCommandeVenteEntite> documentCommandeVentes;
	
	//added on 15/03/2016, by Wahid Gazzah
	@Column(name = "QUANTITE")
	private Long quantite;
	
	@Column(name = "AGENT_ID")
	private Long agentId;
	/***************** Setter & getter *****************/
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
	 * @return the dateCreation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the dateModification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}

	/**
	 * @param dateModification the dateModification to set
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	/**
	 * @return the dateSuppression
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	/**
	 * @param dateSuppression the dateSuppression to set
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	/**
	 * @return the blSuppression
	 */
	public boolean isBlSuppression() {
		return blSuppression;
	}

	/**
	 * @param blSuppression the blSuppression to set
	 */
	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	/**
	 * @return the produitCommandes
	 */
	public Set<ProduitCommandeEntite> getProduitCommandes() {
		return produitCommandes;
	}

	/**
	 * @param produitCommandes the produitCommandes to set
	 */
	public void setProduitCommandes(Set<ProduitCommandeEntite> produitCommandes) {
		this.produitCommandes = produitCommandes;
	}

	/**
	 * @return the documentCommandeVentes
	 */
	public Set<DocumentCommandeVenteEntite> getDocumentCommandeVentes() {
		return documentCommandeVentes;
	}

	/**
	 * @param documentCommandeVentes the documentCommandeVentes to set
	 */
	public void setDocumentCommandeVentes(
			Set<DocumentCommandeVenteEntite> documentCommandeVentes) {
		this.documentCommandeVentes = documentCommandeVentes;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getOf_reference() {
		return of_reference;
	}

	public void setOf_reference(String of_reference) {
		this.of_reference = of_reference;
	}


	
}
