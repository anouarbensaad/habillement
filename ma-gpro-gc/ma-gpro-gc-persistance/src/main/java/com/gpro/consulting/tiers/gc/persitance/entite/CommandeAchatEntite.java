/**
 * 
 */
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
 * @author $Ameni
 *
 */
@Entity
@Table(name=IConstanteGC.TABLE_GC_COMMANDEACHAT)
public class CommandeAchatEntite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1227270643279076527L;
	/** The id. */
	@Id
	@SequenceGenerator(name="COMMANDEACHAT_ID_GENERATOR", sequenceName=IConstanteGC.SEQUENCE_GC_COMMANDEACHAT,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COMMANDEACHAT_ID_GENERATOR")
    private Long id;
	
	/** The reference. */
	@Column(name="reference")
	private String reference;
	
	/** The date commande. */
	@Column(name="date_commande")
	private Calendar dateCommande;
	
	/** The date dateLivraisonPrevue. */
	@Column(name="date_liv_prevue")
	private Calendar dateLivraisonPrevue;
	
	/** The observations. */
	@Column(name="observations")
	private String observations;
	
	/** The coutTotal */
	@Column(name="cout_total")
	private Long coutTotal;
	
	/** The etat. */
	@Column(name="etat")
	private String etat;
	
	/** The com site id. */
	@Column(name="pi_com_site_id")
	private Long siteId;
	
	/** The partieIntersseId. */
	@Column(name="pi_partieint_id")
	private Long partieIntersseId;
	
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

	/** many-to-one association to ElementCommandeAchat. */
	@OneToMany(fetch=FetchType.LAZY,mappedBy="commandeAchat", cascade = CascadeType.MERGE)
	private Set<ElementCommandeAchatEntite> elementCommandes;
	
	/** many-to-one association to DocumentCommandeAchat. */
	@OneToMany(fetch=FetchType.LAZY,mappedBy="commandeAchat")
	private Set<DocumentCommandeAchatEntite> documentCommandeAchat;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the dateCommande
	 */
	public Calendar getDateCommande() {
		return dateCommande;
	}

	/**
	 * @param dateCommande the dateCommande to set
	 */
	public void setDateCommande(Calendar dateCommande) {
		this.dateCommande = dateCommande;
	}

	/**
	 * @return the dateLivraisonPrevue
	 */
	public Calendar getDateLivraisonPrevue() {
		return dateLivraisonPrevue;
	}

	/**
	 * @param dateLivraisonPrevue the dateLivraisonPrevue to set
	 */
	public void setDateLivraisonPrevue(Calendar dateLivraisonPrevue) {
		this.dateLivraisonPrevue = dateLivraisonPrevue;
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
	 * @return the coutTotal
	 */
	public Long getCoutTotal() {
		return coutTotal;
	}

	/**
	 * @param coutTotal the coutTotal to set
	 */
	public void setCoutTotal(Long coutTotal) {
		this.coutTotal = coutTotal;
	}

	/**
	 * @return the etat
	 */
	public String getEtat() {
		return etat;
	}

	/**
	 * @param etat the etat to set
	 */
	public void setEtat(String etat) {
		this.etat = etat;
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
	 * @return the elementCommandes
	 */
	public Set<ElementCommandeAchatEntite> getElementCommandes() {
		return elementCommandes;
	}

	/**
	 * @param elementCommandes the elementCommandes to set
	 */
	public void setElementCommandes(Set<ElementCommandeAchatEntite> elementCommandes) {
		this.elementCommandes = elementCommandes;
	}

	/**
	 * @return the documentCommandeAchat
	 */
	public Set<DocumentCommandeAchatEntite> getDocumentCommandeAchat() {
		return documentCommandeAchat;
	}

	/**
	 * @param documentCommandeAchat the documentCommandeAchat to set
	 */
	public void setDocumentCommandeAchat(
			Set<DocumentCommandeAchatEntite> documentCommandeAchat) {
		this.documentCommandeAchat = documentCommandeAchat;
	}
	
	
}
