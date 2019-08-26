/**
 * 
 */
package com.gpro.consulting.tiers.gc.coordination.value;

import java.util.Calendar;
import java.util.Set;


/**
 * @author $Ameni
 *
 */
public class CommandeAchatValue {

	private Long id;

	/** The reference. */
	private String reference;

	/** The date commande. */
	private Calendar dateCommande;

	/** The date dateLivraisonPrevue. */
	private Calendar dateLivraisonPrevue;

	/** The observations. */
	private String observations;

	/** The coutTotal */
	private Long coutTotal;

	/** The etat. */
	private String etat;

	/** The com site id. */
	private Long siteId;

	/** The partieIntersseId. */
	private Long partieIntersseId;

	/** The date creation. */
	private Calendar dateCreation;

	/** The date modification. */
	private Calendar dateModification;

	/** The date suppression. */
	private Calendar dateSuppression;

	/** The bl suppression. */
	private boolean blSuppression;

	/** many-to-one association to ElementCommandeAchat. */
	private Set<ElementCommandeAchatValue> elementCommandes;
	
	/** many-to-one association to DocumentCommandeAchat. */
	private Set<DocumentCommandeAchatValue> documentCommandeAchat;

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
	public Set<ElementCommandeAchatValue> getElementCommandes() {
		return elementCommandes;
	}

	/**
	 * @param elementCommandes the elementCommandes to set
	 */
	public void setElementCommandes(Set<ElementCommandeAchatValue> elementCommandes) {
		this.elementCommandes = elementCommandes;
	}

	/**
	 * @return the documentCommandeAchat
	 */
	public Set<DocumentCommandeAchatValue> getDocumentCommandeAchat() {
		return documentCommandeAchat;
	}

	/**
	 * @param documentCommandeAchat the documentCommandeAchat to set
	 */
	public void setDocumentCommandeAchat(
			Set<DocumentCommandeAchatValue> documentCommandeAchat) {
		this.documentCommandeAchat = documentCommandeAchat;
	}

}
