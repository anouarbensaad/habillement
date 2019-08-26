package com.gpro.consulting.tiers.gs.coordination.report.value;

import java.util.Calendar;

public class MouvementStockHistoryElementReportValue implements Comparable<Object> {

	// this param used to sort like the same case on
	// rechercherMouvementMultiCritere FE
	private Long id;

	// from bonMouvement
	private String numero;
	private Calendar date;
	private String responsable;
	private Double valeur;
	private Long clientId;
	private Long magasinId;
	private String referenceArticle;
	private String designationArticle;
	private String familleArticle;
	private Double quantiteReelle;
	private Double poidsReel;
	private Double prixUnitaire;
	private String designationMagasin;
	private String emplacement;
	private Long nbRouleauxReel;
	private String client;
	private Long finconesReel;
	private Long cones;
	private String OF;
	private String refProduit;
	private String designationProduit;

	@Override
	public int compareTo(Object o) {
		MouvementStockHistoryElementReportValue element = (MouvementStockHistoryElementReportValue) o;
		return (element.getId().compareTo(id));
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public Double getValeur() {
		return valeur;
	}

	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}

	public String getReferenceArticle() {
		return referenceArticle;
	}

	public void setReferenceArticle(String referenceArticle) {
		this.referenceArticle = referenceArticle;
	}

	public String getDesignationArticle() {
		return designationArticle;
	}

	public void setDesignationArticle(String designationArticle) {
		this.designationArticle = designationArticle;
	}

	public String getFamilleArticle() {
		return familleArticle;
	}

	public void setFamilleArticle(String familleArticle) {
		this.familleArticle = familleArticle;
	}

	public Double getQuantiteReelle() {
		return quantiteReelle;
	}

	public void setQuantiteReelle(Double quantiteReelle) {
		this.quantiteReelle = quantiteReelle;
	}

	public Double getPoidsReel() {
		return poidsReel;
	}

	public void setPoidsReel(Double poidsReel) {
		this.poidsReel = poidsReel;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public String getDesignationMagasin() {
		return designationMagasin;
	}

	public void setDesignationMagasin(String designationMagasin) {
		this.designationMagasin = designationMagasin;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public Long getNbRouleauxReel() {
		return nbRouleauxReel;
	}

	public void setNbRouleauxReel(Long nbRouleauxReel) {
		this.nbRouleauxReel = nbRouleauxReel;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getMagasinId() {
		return magasinId;
	}

	public void setMagasinId(Long magasinId) {
		this.magasinId = magasinId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFinconesReel() {
		return finconesReel;
	}

	public void setFinconesReel(Long finconesReel) {
		this.finconesReel = finconesReel;
	}

	public Long getCones() {
		return cones;
	}

	public void setCones(Long cones) {
		this.cones = cones;
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

}
