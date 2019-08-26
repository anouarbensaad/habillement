package com.gpro.consulting.tiers.commun.coordination.value;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class ProduitValue.
 * 
 * @author med
 */
public class ProduitValue implements Comparable<ProduitValue>{
    
    /** The id. */
    private Long id;
	
	/** The com site id. */
	private Long siteId;
	
	/** The site entite Designation. */
	private String siteEntiteDesignation;
	
	/** The eb sfprod id. */
	private Long sousFamilleId;
	
	/** SousFamilleDesignation */
	private String sousFamilleDesignation;
	
	/** FamilleDesignation */
	private String familleDesignation;
	
	/** uidImage */
	private String uidImage;
	
	/** The pi pi id. */
	private Long partieIntersseId;
	
	/** The PI Designation. */
	private String partieIntersseDesignation;
	
	/** The reference. */
	private String reference;
	
	/** The designation. */
	private String designation;
	
	/** The prixUnitaire. */
	private Double prixUnitaire;
	
	/** The etat. */
	private String etat;
	
	/** The date introduction. */
	private Calendar dateIntroduction;
	
	/** The details prix. */
	private Set<DetailsPrixProduitValue> detailsPrix = new HashSet < DetailsPrixProduitValue >();
	
	/** The document prosuits. */
	private Set<DocumentProduitValue> documentProduits= new HashSet < DocumentProduitValue >();
	
	/** The phaseProduits. */
	private Set<PhaseProduitValue> phaseProduits= new HashSet < PhaseProduitValue >();
	
	/** The ProduitCouleur. */
	private Set<ProduitCouleurValue> couleurProduit= new HashSet < ProduitCouleurValue >();
	
	/** The ProduitTaille. */
	private List<ProduitTailleValue> tailleProduit= new ArrayList < ProduitTailleValue >();
	
	
	//added on 08/03/2016, by Wahid Gazzah
	private String tissu;
	private String composition;
	private String entretien;
	private String observations;
	private String observationsDev;
	
	//added on 16/03/2016, by Ameni Berrich
	/** The prix majore. */
	private Double prixMajore;
	
	private boolean hasFB;
	
	private String refBeforeUpdate;
	
	private Long idStandard; 
	
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the uidImage
	 */
	public String getUidImage() {
		return uidImage;
	}

	/**
	 * @param uidImage the uidImage to set
	 */
	public void setUidImage(String uidImage) {
		this.uidImage = uidImage;
	}

	/**
	 * @return the familleDesignation
	 */
	public String getFamilleDesignation() {
		return familleDesignation;
	}
	
	/**
	 * @return the prixUnitaire
	 */
	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	/**
	 * @param prixUnitaire the prixUnitaire to set
	 */
	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	/**
	 * @param familleDesignation the familleDesignation to set
	 */
	public void setFamilleDesignation(String familleDesignation) {
		this.familleDesignation = familleDesignation;
	}

	public Long getSousFamilleId() {
		return sousFamilleId;
	}

	public void setSousFamilleId(Long sousFamilleId) {
		this.sousFamilleId = sousFamilleId;
	}

	/**
	 * @return the sousFamilleDesignation
	 */
	public String getSousFamilleDesignation() {
		return sousFamilleDesignation;
	}

	/**
	 * @param sousFamilleDesignation the sousFamilleDesignation to set
	 */
	public void setSousFamilleDesignation(String sousFamilleDesignation) {
		this.sousFamilleDesignation = sousFamilleDesignation;
	}

	public Long getPartieIntersseId() {
		return partieIntersseId;
	}

	public void setPartieIntersseId(Long partieIntersseId) {
		this.partieIntersseId = partieIntersseId;
	}

	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}
	
	/**
	 * Sets the reference.
	 *
	 * @param reference the new reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	
	public String getPartieIntersseDesignation() {
		return partieIntersseDesignation;
	}

	public void setPartieIntersseDesignation(String partieIntersseDesignation) {
		this.partieIntersseDesignation = partieIntersseDesignation;
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
	

	public String getSiteEntiteDesignation() {
		return siteEntiteDesignation;
	}

	public void setSiteEntiteDesignation(String siteEntiteDesignation) {
		this.siteEntiteDesignation = siteEntiteDesignation;
	}

	/**
	 * Gets the designation.
	 *
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	
	/**
	 * Sets the designation.
	 *
	 * @param designation the new designation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
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
	 * Gets the date introduction.
	 *
	 * @return the date introduction
	 */
	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}
	
	/**
	 * Sets the date introduction.
	 *
	 * @param dateIntroduction the new date introduction
	 */
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}
	
	/**
	 * Gets the details prix.
	 *
	 * @return the details prix
	 */
	public Set<DetailsPrixProduitValue> getDetailsPrix() {
		return detailsPrix;
	}
	
	/**
	 * Sets the details prix.
	 *
	 * @param detailsPrix the new details prix
	 */
	public void setDetailsPrix(Set<DetailsPrixProduitValue> detailsPrix) {
		this.detailsPrix = detailsPrix;
	}
	


	/**
	 * Gets the document prosuits.
	 *
	 * @return the document prosuits
	 */
	public Set<DocumentProduitValue> getDocumentProduits() {
		return documentProduits;
	}
	
	/**
	 * Sets the document prosuits.
	 *
	 * @param documentProsuits the new document prosuits
	 */
	public void setDocumentProduits(Set<DocumentProduitValue> documentProsuits) {
		this.documentProduits = documentProsuits;
	}
	
	/**
	 * @return the phaseProduits
	 */
	public Set<PhaseProduitValue> getPhaseProduits() {
		return phaseProduits;
	}

	/**
	 * @param phaseProduits the phaseProduits to set
	 */
	public void setPhaseProduits(Set<PhaseProduitValue> phaseProduits) {
		this.phaseProduits = phaseProduits;
	}

	
	/**
	 * @return the couleurProduit
	 */
	public Set<ProduitCouleurValue> getCouleurProduit() {
		return couleurProduit;
	}

	/**
	 * @param couleurProduit the couleurProduit to set
	 */
	public void setCouleurProduit(Set<ProduitCouleurValue> couleurProduit) {
		this.couleurProduit = couleurProduit;
	}

	/**
	 * @return the tailleProduit
	 */
	public List<ProduitTailleValue> getTailleProduit() {
		return tailleProduit;
	}

	/**
	 * @param tailleProduit the tailleProduit to set
	 */
	public void setTailleProduit(List<ProduitTailleValue> tailleProduit) {
		this.tailleProduit = tailleProduit;
	}

	@Override
	public String toString() {
		return "ProduitValue [id=" + id + ", siteId=" + siteId + ", siteEntiteDesignation=" + siteEntiteDesignation
				+ ", sousFamilleId=" + sousFamilleId + ", sousFamilleDesignation=" + sousFamilleDesignation
				+ ", familleDesignation=" + familleDesignation + ", uidImage=" + uidImage + ", partieIntersseId="
				+ partieIntersseId + ", partieIntersseDesignation=" + partieIntersseDesignation + ", reference="
				+ reference + ", designation=" + designation + ", prixUnitaire=" + prixUnitaire + ", etat=" + etat
				+ ", dateIntroduction=" + dateIntroduction + ", detailsPrix=" + detailsPrix + ", documentProduits="
				+ documentProduits + ", phaseProduits=" + phaseProduits + ", couleurProduit=" + couleurProduit
				+ ", tailleProduit=" + tailleProduit + ", tissu=" + tissu + ", composition=" + composition
				+ ", entretien=" + entretien + ", observations=" + observations + ", observationsDev=" + observationsDev
				+ ", prixMajore=" + prixMajore + ", hasFB=" + hasFB + "]";
	}

	public String getTissu() {
		return tissu;
	}

	public void setTissu(String tissu) {
		this.tissu = tissu;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getEntretien() {
		return entretien;
	}

	public void setEntretien(String entretien) {
		this.entretien = entretien;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}
	
	public String getObservationsDev() {
		return observationsDev;
	}

	public void setObservationsDev(String observationsDev) {
		this.observationsDev = observationsDev;
	}
	
	/**
	 * @return the prixMajore
	 */
	public Double getPrixMajore() {
	
		return prixMajore;
	}

	/**
	 * @param prixMajore the prixMajore to set
	 */
	public void setPrixMajore(Double prixMajore) {
		this.prixMajore = prixMajore;
	}

	public boolean isHasFB() {
		return hasFB;
	}

	public void setHasFB(boolean hasFB) {
		this.hasFB = hasFB;
	}
	

	public String getRefBeforeUpdate() {
		return refBeforeUpdate;
	}

	public void setRefBeforeUpdate(String refBeforeUpdate) {
		this.refBeforeUpdate = refBeforeUpdate;
	}

	@Override
	public int compareTo(ProduitValue o) {
		ProduitValue element= (ProduitValue)o;
		return (element.getId().compareTo(id));
	}

	public Long getIdStandard() {
		return idStandard;
	}

	public void setIdStandard(Long idStandard) {
		this.idStandard = idStandard;
	}
	
	
	
}