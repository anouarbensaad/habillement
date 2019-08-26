package com.gpro.consulting.tiers.commun.persistance.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

import java.util.Calendar;
import java.util.Set;
/**
 * The Class ProduitEntity.
 * @author med
 */
@Entity
@Table(name=IConstante.TABLE_PRODUIT)
public class ProduitEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 641467517751608746L;

	/** The id. */
	@Id
	@SequenceGenerator(name="EB_PRODUIT_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_PRODUIT,allocationSize=1)
	// allocationSize = 1
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EB_PRODUIT_ID_GENERATOR")
    private Long id;
	
	/** The com site id. */
	@Column(name="com_site_id")
	private Long siteId;
	
	/** The eb sfprod id. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="eb_sfprod_id")
	private SousFamilleProduitEntity sousFamille;

	/** The pi pi id. */
	@Column(name="pi_pi_id")
	private Long partieIntersseId;
	
	/** The reference. */
	@Column(name="reference")
	private String reference;
	
	/** The uidImage. */
	@Column(name="uid_image")
	private String uidImage;
	
	/** The designation. */
	@Column(name="designation")
	private String designation;
	
	/** The prix unitaire. */
	@Column(name = "prix_unitaire")
	private Double prixUnitaire;
	
	/** The etat. */
	@Column(name = "etat")
	private String etat;
	
	/** The date creation. */
	@Column(name="date_creation")
	private Calendar dateCreation;
	
	/** The date introduction. */
	@Column(name="date_introduction")
	private Calendar dateIntroduction;
	
	/** The date modification. */
	@Column(name="date_modification")
	private Calendar dateModification;

	/** The date suppression. */
	@Column(name="date_suppression")
	private Calendar dateSuppression;
	
	/** The bl suppression. */
	@Column(name="bl_suppression")
	private boolean blSuppression;
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="produit", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<PhaseProduitEntite> phaseProduits;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="produit", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ProduitCouleurEntite> couleurProduits;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="produit", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ProduitTailleEntite> tailleProduits;
	
	/** ***bi-directional many-to-one association to DetailsPrixProduit. */
	@OneToMany(fetch=FetchType.LAZY,mappedBy="produit", cascade = CascadeType.ALL)
	private Set<DetailsPrixProduitEntite> detailsPrix;

	/** *** many-to-one association to DocumentProduit. */
	@OneToMany(fetch=FetchType.LAZY,mappedBy="produit", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DocumentProduitEntity> documentProduits;
	
	
	//added on 08/03/2016, by Wahid Gazzah
	@Column(name = "tissu")
	private String tissu;
	
	@Column(name = "composition")
	private String composition;
	
	@Column(name = "entretien")
	private String entretien;
	
	@Column(name = "observations")
	private String observations;
	
	@Column(name = "observations_dev")
	private String observationsDev;

	//added on 16/03/2016, by Ameni Berrich
	/** The prix majore. */
	@Column(name = "prix_majore")
	private Double prixMajore;
	
	@Column(name="fiche_besoin")
	private boolean ficheBesoin;
	
	
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

	

	public SousFamilleProduitEntity getSousFamille() {
		return sousFamille;
	}

	public void setSousFamille(SousFamilleProduitEntity sousFamille) {
		this.sousFamille = sousFamille;
	}

	/**
	 * Gets the partie intersse id.
	 *
	 * @return the partie intersse id
	 */
	public Long getPartieIntersseId() {
		return partieIntersseId;
	}

	/**
	 * Sets the partie intersse id.
	 *
	 * @param partieIntersseId the new partie intersse id
	 */
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

	/**
	 * Gets the designation.
	 *
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
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
	 * Sets the designation.
	 *
	 * @param designation the new designation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
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
	 * Gets the date creation.
	 *
	 * @return the date creation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * Sets the date creation.
	 *
	 * @param dateCreation the new date creation
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
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
	 * Gets the date modification.
	 *
	 * @return the date modification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}

	/**
	 * Sets the date modification.
	 *
	 * @param dateModification the new date modification
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	/**
	 * Gets the date suppression.
	 *
	 * @return the date suppression
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	/**
	 * Sets the date suppression.
	 *
	 * @param dateSuppression the new date suppression
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}


	public boolean isBlSuppression() {
		return blSuppression;
	}

	/**
	 * @param blSuppression the blSuppression to set
	 */
	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}


	public Set<DetailsPrixProduitEntite> getDetailsPrix() {
		return detailsPrix;
	}

	/**
	 * Sets the details prix.
	 *
	 * @param detailsPrix the new details prix
	 */
	public void setDetailsPrix(Set<DetailsPrixProduitEntite> detailsPrix) {
		this.detailsPrix = detailsPrix;
	}

	/**
	 * Gets the document prosuits.
	 *
	 * @return the document prosuits
	 */
	public Set<DocumentProduitEntity> getDocumentProduits() {
		return documentProduits;
	}

	/**
	 * Sets the document prosuits.
	 *
	 * @param documentProsuits the new document prosuits
	 */

	public void setDocumentProduits(Set<DocumentProduitEntity> documentProduits) {
		this.documentProduits = documentProduits;
	}

	
	/**
	 * @return the phaseProduits
	 */
	public Set<PhaseProduitEntite> getPhaseProduits() {
		return phaseProduits;
	}

	/**
	 * @return the couleurProduits
	 */
	public Set<ProduitCouleurEntite> getCouleurProduits() {
		return couleurProduits;
	}

	/**
	 * @param couleurProduits the couleurProduits to set
	 */
	public void setCouleurProduits(Set<ProduitCouleurEntite> couleurProduits) {
		this.couleurProduits = couleurProduits;
	}

	/**
	 * @param phaseProduits the phaseProduits to set
	 */
	public void setPhaseProduits(Set<PhaseProduitEntite> phaseProduits) {
		this.phaseProduits = phaseProduits;
	}
	
	/**
	 * @return the tailleProduits
	 */
	public Set<ProduitTailleEntite> getTailleProduits() {
		return tailleProduits;
	}

	/**
	 * @param tailleProduits the tailleProduits to set
	 */
	public void setTailleProduits(Set<ProduitTailleEntite> tailleProduits) {
		this.tailleProduits = tailleProduits;
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

	public boolean isFicheBesoin() {
		return ficheBesoin;
	}

	public void setFicheBesoin(boolean ficheBesoin) {
		this.ficheBesoin = ficheBesoin;
	}

	@Override
	public String toString() {
		return "ProduitEntity [id=" + id + ", siteId=" + siteId + ", sousFamille=" + sousFamille + ", partieIntersseId="
				+ partieIntersseId + ", reference=" + reference + ", uidImage=" + uidImage + ", designation="
				+ designation + ", prixUnitaire=" + prixUnitaire + ", etat=" + etat + ", dateCreation=" + dateCreation
				+ ", dateIntroduction=" + dateIntroduction + ", dateModification=" + dateModification
				+ ", dateSuppression=" + dateSuppression + ", blSuppression=" + blSuppression + ", phaseProduits="
				+ phaseProduits + ", couleurProduits=" + couleurProduits + ", tailleProduits=" + tailleProduits
				+ ", detailsPrix=" + detailsPrix + ", documentProduits=" + documentProduits + ", tissu=" + tissu
				+ ", composition=" + composition + ", entretien=" + entretien + ", observations=" + observations
				+ ", observationsDev=" + observationsDev + ", prixMajore=" + prixMajore + ", ficheBesoin=" + ficheBesoin
				+ "]";
	}
	
	/* (non-Javadoc)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	
}