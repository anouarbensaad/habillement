package com.gpro.consulting.tiers.gs.coordination.value;

import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Class MouvementStockValue.
 */
public class MouvementStockValue implements Comparable<Object> {

	/** The id. */
	private Long id;

	/** The cones. */
	private Long cones;

	/** The cones reel. */
	private Long conesReel;

	/** The fincones. */
	private Long fincones;

	/** The fincones reel. */
	private Long finconesReel;

	/** The nb rouleaux. */
	private Long nbRouleaux;

	/** The nb rouleaux reel. */
	private Long nbRouleauxReel;

	/** The poids. */
	private Double poids;

	/** The poids reel. */
	private Double poidsReel;

	/** The quantite. */
	private Double quantite;

	/** The quantite reelle. */
	private Double quantiteReelle;

	/** The type. */
	private String type;

	private String detailsMouvement;

	private String description;

	// bi-directional many-to-one association to GsBonmouvement
	/** The bon mouvement. */
	private BonMouvementStockValue bonMouvement;

	/** The bon mouvement. */
	private Long bonMouvementId;

	// bi-directional many-to-one association to GsEntitestock
	/** The entite stock. */
	private Long entiteStock;

	/** EntiteStock Value */
	private EntiteStockValue entiteStockValue;

	/** The prix unitaire. */
	private Double prixUnitaire;

	/** The emplacement. */
	private String emplacement;

	/** The nouveau. */
	private Boolean nouveau;

	/** The affichage. */
	/** mouvement supprimé. */
	private Boolean affichage;

	// added on 13/04/2016, by Ameni Berrich
	/** The lot. */
	private String lot;

	/**** Attributs ajoutés pour recuperation des données de la vue ***/

	private String designationArticle;
	private String referenceArticle;
	private String familleArticle;
	private String sousFamilleArticle;

	private Long idMagasin;

	private String designationMagasin;

	private Long idArticle;

	private Long typeArticle;
	private Double prixTotal;

	private String clientAbreviation;

	// Added on 08/11/2016, by Zeineb

	private Calendar dateEntree;

	private Long qteOF;

	private Double besoinOF;

	private Double quantiteAct;

	private Double quantiteLibre;

	private Double reservationOF;

	private Double qteReservee;

	// Added on 14/11/2016, by Zeineb

	private Double bu;
	private Double sortieOF;

	// Added on 19/01/2017, by Zeineb

	private String oa;

	// Added on 25/01/2017, by Zeineb

	private String couleur;

	// Added on 15/03/2017, by Hajer
	private String codeFournisseur;
	
	// Added on 02/04/2017, by Zeineb
	private Long OFId;
	private String OF;
	private Long produitId;
	private String refProduit;
	private String designationProduit;
	
	private String numBonMvt;
	private Calendar dateBonMvt;
	
	private String observation;
	private Long rouleauxActuels;
	
	private String typeMouvement;
	
	private Double reservationOrigine;
	
	public Calendar getDateBonMvt() {
		return dateBonMvt;
	}

	public void setDateBonMvt(Calendar dateBonMvt) {
		this.dateBonMvt = dateBonMvt;
	}

	public String getNumBonMvt() {
		return numBonMvt;
	}

	public void setNumBonMvt(String numBonMvt) {
		this.numBonMvt = numBonMvt;
	}

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
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Long getCones() {
		return cones;
	}

	public void setCones(Long cones) {
		this.cones = cones;
	}

	public Long getConesReel() {
		return conesReel;
	}

	public void setConesReel(Long conesReel) {
		this.conesReel = conesReel;
	}

	public Long getFincones() {
		return fincones;
	}

	public void setFincones(Long fincones) {
		this.fincones = fincones;
	}

	public void setFinconesReel(Long finconesReel) {
		this.finconesReel = finconesReel;
	}

	public Long getFinconesReel() {
		return finconesReel;
	}

	/**
	 * Gets the nb rouleaux.
	 *
	 * @return the nb rouleaux
	 */
	public Long getNbRouleaux() {
		return nbRouleaux;
	}

	/**
	 * Sets the nb rouleaux.
	 *
	 * @param nbRouleaux
	 *            the new nb rouleaux
	 */
	public void setNbRouleaux(Long nbRouleaux) {
		this.nbRouleaux = nbRouleaux;
	}

	/**
	 * Gets the nb rouleaux reel.
	 *
	 * @return the nb rouleaux reel
	 */
	public Long getNbRouleauxReel() {
		return nbRouleauxReel;
	}

	/**
	 * Sets the nb rouleaux reel.
	 *
	 * @param nbRouleauxReel
	 *            the new nb rouleaux reel
	 */
	public void setNbRouleauxReel(Long nbRouleauxReel) {
		this.nbRouleauxReel = nbRouleauxReel;
	}

	/**
	 * Gets the poids.
	 *
	 * @return the poids
	 */
	public Double getPoids() {
		return poids;
	}

	/**
	 * Sets the poids.
	 *
	 * @param poids
	 *            the new poids
	 */
	public void setPoids(Double poids) {
		this.poids = poids;
	}

	/**
	 * Gets the poids reel.
	 *
	 * @return the poids reel
	 */
	public Double getPoidsReel() {
		return poidsReel;
	}

	/**
	 * Sets the poids reel.
	 *
	 * @param poidsReel
	 *            the new poids reel
	 */
	public void setPoidsReel(Double poidsReel) {
		this.poidsReel = poidsReel;
	}

	/**
	 * Gets the quantite.
	 *
	 * @return the quantite
	 */
	public Double getQuantite() {
		return quantite;
	}

	/**
	 * Sets the quantite.
	 *
	 * @param quantite
	 *            the new quantite
	 */
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	/**
	 * Gets the quantite reelle.
	 *
	 * @return the quantite reelle
	 */
	public Double getQuantiteReelle() {
		return quantiteReelle;
	}

	/**
	 * Sets the quantite reelle.
	 *
	 * @param quantiteReelle
	 *            the new quantite reelle
	 */
	public void setQuantiteReelle(Double quantiteReelle) {
		this.quantiteReelle = quantiteReelle;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	public BonMouvementStockValue getBonMouvement() {
		return bonMouvement;
	}

	public void setBonMouvement(BonMouvementStockValue bonMouvement) {
		this.bonMouvement = bonMouvement;
	}

	public String getDetailsMouvement() {
		return detailsMouvement;
	}

	public void setDetailsMouvement(String detailsMouvement) {
		this.detailsMouvement = detailsMouvement;
	}

	public Long getEntiteStock() {
		return entiteStock;
	}

	public void setEntiteStock(Long entiteStock) {
		this.entiteStock = entiteStock;
	}

	/**
	 * @return the prixUnitaire
	 */
	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	/**
	 * @param prixUnitaire
	 *            the prixUnitaire to set
	 */
	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	/**
	 * @return the emplacement
	 */
	public String getEmplacement() {
		return emplacement;
	}

	/**
	 * @param emplacement
	 *            the emplacement to set
	 */
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	/**
	 * @return the idMagasin
	 */
	public Long getIdMagasin() {
		return idMagasin;
	}

	/**
	 * @param idMagasin
	 *            the idMagasin to set
	 */
	public void setIdMagasin(Long idMagasin) {
		this.idMagasin = idMagasin;
	}

	/**
	 * @return the designationArticle
	 */
	public String getDesignationArticle() {
		return designationArticle;
	}

	/**
	 * @param designationArticle
	 *            the designationArticle to set
	 */
	public void setDesignationArticle(String designationArticle) {
		this.designationArticle = designationArticle;
	}

	/**
	 * @return the idArticle
	 */
	public Long getIdArticle() {
		return idArticle;
	}

	/**
	 * @param idArticle
	 *            the idArticle to set
	 */
	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
	}

	/**
	 * @return the typeArticle
	 */
	public Long getTypeArticle() {
		return typeArticle;
	}

	/**
	 * @param typeArticle
	 *            the typeArticle to set
	 */
	public void setTypeArticle(Long typeArticle) {
		this.typeArticle = typeArticle;
	}

	/**
	 * @return the prixTotal
	 */
	public Double getPrixTotal() {
		return prixTotal;
	}

	/**
	 * @param prixTotal
	 *            the prixTotal to set
	 */
	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReferenceArticle() {
		return referenceArticle;
	}

	public void setReferenceArticle(String referenceArticle) {
		this.referenceArticle = referenceArticle;
	}

	public String getFamilleArticle() {
		return familleArticle;
	}

	public void setFamilleArticle(String familleArticle) {
		this.familleArticle = familleArticle;
	}

	public String getSousFamilleArticle() {
		return sousFamilleArticle;
	}

	public void setSousFamilleArticle(String sousFamilleArticle) {
		this.sousFamilleArticle = sousFamilleArticle;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>entiteStockValue</code>.
	 * 
	 * @return EntiteStockValue L'attribut entiteStockValue à lire.
	 */
	public EntiteStockValue getEntiteStockValue() {
		return entiteStockValue;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>entiteStockValue</code>.
	 *
	 * @param entiteStockValue
	 *            L'attribut entiteStockValue à modifier.
	 */
	public void setEntiteStockValue(EntiteStockValue entiteStockValue) {
		this.entiteStockValue = entiteStockValue;
	}

	/**
	 * @return the nouveau
	 */
	public Boolean getNouveau() {
		return nouveau;
	}

	/**
	 * @param nouveau
	 *            the nouveau to set
	 */
	public void setNouveau(Boolean nouveau) {
		this.nouveau = nouveau;
	}

	/**
	 * @return the affichage
	 */
	public Boolean getAffichage() {
		return affichage;
	}

	/**
	 * @param affichage
	 *            the affichage to set
	 */
	public void setAffichage(Boolean affichage) {
		this.affichage = affichage;
	}

	@Override
	public int compareTo(Object o) {
		MouvementStockValue element = (MouvementStockValue) o;
		return (element.getId().compareTo(id));
	}

	/**
	 * @return the designationMagasin
	 */
	public String getDesignationMagasin() {
		return designationMagasin;
	}

	/**
	 * @param designationMagasin
	 *            the designationMagasin to set
	 */
	public void setDesignationMagasin(String designationMagasin) {
		this.designationMagasin = designationMagasin;
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}

	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public Calendar getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Calendar dateEntree) {
		this.dateEntree = dateEntree;
	}

	public Long getQteOF() {
		return qteOF;
	}

	public void setQteOF(Long qteOF) {
		this.qteOF = qteOF;
	}

	public Double getBesoinOF() {
		return besoinOF;
	}

	public void setBesoinOF(Double besoinOF) {
		this.besoinOF = besoinOF;
	}

	public Double getQuantiteAct() {
		return quantiteAct;
	}

	public void setQuantiteAct(Double quantiteAct) {
		this.quantiteAct = quantiteAct;
	}

	public Double getQuantiteLibre() {
		return quantiteLibre;
	}

	public void setQuantiteLibre(Double quantiteLibre) {
		this.quantiteLibre = quantiteLibre;
	}

	public Double getReservationOF() {
		return reservationOF;
	}

	public void setReservationOF(Double reservationOF) {
		this.reservationOF = reservationOF;
	}

	public Double getQteReservee() {
		return qteReservee;
	}

	public void setQteReservee(Double qteReservee) {
		this.qteReservee = qteReservee;
	}

	public Double getBu() {
		return bu;
	}

	public void setBu(Double bu) {
		this.bu = bu;
	}

	public Double getSortieOF() {
		return sortieOF;
	}

	public void setSortieOF(Double sortieOF) {
		this.sortieOF = sortieOF;
	}

	public String getOa() {
		return oa;
	}

	public void setOa(String oa) {
		this.oa = oa;
	}

	public Long getBonMouvementId() {
		return bonMouvementId;
	}

	public void setBonMouvementId(Long bonMouvementId) {
		this.bonMouvementId = bonMouvementId;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getCodeFournisseur() {
		return codeFournisseur;
	}

	public void setCodeFournisseur(String codeFournisseur) {
		this.codeFournisseur = codeFournisseur;
	}

	public Long getOFId() {
		return OFId;
	}

	public void setOFId(Long oFId) {
		OFId = oFId;
	}

	public String getOF() {
		return OF;
	}

	public void setOF(String oF) {
		OF = oF;
	}

	public String getRefProduit() {
		return refProduit;
	}

	public void setRefProduit(String refProduit) {
		this.refProduit = refProduit;
	}

	public String getDesignationProduit() {
		return designationProduit;
	}

	public void setDesignationProduit(String designationProduit) {
		this.designationProduit = designationProduit;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Long getRouleauxActuels() {
		return rouleauxActuels;
	}

	public void setRouleauxActuels(Long rouleauxActuels) {
		this.rouleauxActuels = rouleauxActuels;
	}

	public String getTypeMouvement() {
		return typeMouvement;
	}

	public void setTypeMouvement(String typeMouvement) {
		this.typeMouvement = typeMouvement;
	}
	

	public Double getReservationOrigine() {
		return reservationOrigine;
	}

	public void setReservationOrigine(Double reservationOrigine) {
		this.reservationOrigine = reservationOrigine;
	}

	@Override
	public String toString() {
		return "MouvementStockValue [id=" + id + ", cones=" + cones + ", conesReel=" + conesReel + ", fincones="
				+ fincones + ", finconesReel=" + finconesReel + ", nbRouleaux=" + nbRouleaux + ", nbRouleauxReel="
				+ nbRouleauxReel + ", poids=" + poids + ", poidsReel=" + poidsReel + ", quantite=" + quantite
				+ ", quantiteReelle=" + quantiteReelle + ", type=" + type + ", detailsMouvement=" + detailsMouvement
				+ ", description=" + description + ", bonMouvement=" + bonMouvement + ", bonMouvementId="
				+ bonMouvementId + ", entiteStock=" + entiteStock + ", entiteStockValue=" + entiteStockValue
				+ ", prixUnitaire=" + prixUnitaire + ", emplacement=" + emplacement + ", nouveau=" + nouveau
				+ ", affichage=" + affichage + ", lot=" + lot + ", designationArticle=" + designationArticle
				+ ", referenceArticle=" + referenceArticle + ", familleArticle=" + familleArticle
				+ ", sousFamilleArticle=" + sousFamilleArticle + ", idMagasin=" + idMagasin + ", designationMagasin="
				+ designationMagasin + ", idArticle=" + idArticle + ", typeArticle=" + typeArticle + ", prixTotal="
				+ prixTotal + ", clientAbreviation=" + clientAbreviation + ", dateEntree=" + dateEntree + ", qteOF="
				+ qteOF + ", besoinOF=" + besoinOF + ", quantiteAct=" + quantiteAct + ", quantiteLibre=" + quantiteLibre
				+ ", reservationOF=" + reservationOF + ", qteReservee=" + qteReservee + ", bu=" + bu + ", sortieOF="
				+ sortieOF + ", oa=" + oa + ", couleur=" + couleur + ", codeFournisseur=" + codeFournisseur + ", OFId="
				+ OFId + ", OF=" + OF + ", produitId=" + produitId + ", refProduit=" + refProduit
				+ ", designationProduit=" + designationProduit + ", numBonMvt=" + numBonMvt + ", dateBonMvt="
				+ dateBonMvt + ", observation=" + observation + "]";
	}

}
