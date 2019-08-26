package com.gpro.consulting.tiers.gs.coordination.value;

import java.util.Calendar;

public class RechercheMulticritereMouvementValue {
	private String historique;
	private String type;
	private String famille;
	private String sousFamille;
	private String finCone;
	private String reference;
	private String article;
	private String magasin;
	private String emplacement;
	private Calendar dateDu;
	private Calendar DateA;
	private String raison;
	private String responsable;
	private String client;
	private String fournisseur;
	private String sousTraitant;
	private String typeMouvement;

	private Long ofId;
	private Long entiteStockId;

	// Added on 23/11/2016, by Zeineb Medimagh
	private String refArticle;

	// Added on 19/01/2017, by Zeineb Medimagh
	private String lot;
	private String oa;

	// Added on 15/03/2017, by Hajer AMRI
	private String codeFournisseur;
	private Long finconesReel;
	private Long cones;
	
	private String numeroBonMvt;
	private Calendar dateBonMvt;
	private String numOF;

	public String getHistorique() {
		return historique;
	}

	public void setHistorique(String historique) {
		this.historique = historique;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
	}

	public String getSousFamille() {
		return sousFamille;
	}

	public void setSousFamille(String sousFamille) {
		this.sousFamille = sousFamille;
	}

	public String getFinCone() {
		return finCone;
	}

	public void setFinCone(String finCone) {
		this.finCone = finCone;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getMagasin() {
		return magasin;
	}

	public void setMagasin(String magasin) {
		this.magasin = magasin;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}

	public Calendar getDateDu() {
		return dateDu;
	}

	public void setDateDu(Calendar dateDu) {
		this.dateDu = dateDu;
	}

	public Calendar getDateA() {
		return DateA;
	}

	public void setDateA(Calendar dateA) {
		DateA = dateA;
	}

	public String getRaison() {
		return raison;
	}

	public void setRaison(String raison) {
		this.raison = raison;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}

	public String getSousTraitant() {
		return sousTraitant;
	}

	public void setSousTraitant(String sousTraitant) {
		this.sousTraitant = sousTraitant;
	}

	public Long getOfId() {
		return ofId;
	}

	public void setOfId(Long ofId) {
		this.ofId = ofId;
	}

	public Long getEntiteStockId() {
		return entiteStockId;
	}

	public void setEntiteStockId(Long entiteStockId) {
		this.entiteStockId = entiteStockId;
	}

	public String getRefArticle() {
		return refArticle;
	}

	public void setRefArticle(String refArticle) {
		this.refArticle = refArticle;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getOa() {
		return oa;
	}

	public void setOa(String oa) {
		this.oa = oa;
	}

	public String getCodeFournisseur() {
		return codeFournisseur;
	}

	public void setCodeFournisseur(String codeFournisseur) {
		this.codeFournisseur = codeFournisseur;
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
	
	public String getTypeMouvement() {
		return typeMouvement;
	}

	public void setTypeMouvement(String typeMouvement) {
		this.typeMouvement = typeMouvement;
	}

	public String getNumeroBonMvt() {
		return numeroBonMvt;
	}

	public void setNumeroBonMvt(String numeroBonMvt) {
		this.numeroBonMvt = numeroBonMvt;
	}

	public Calendar getDateBonMvt() {
		return dateBonMvt;
	}

	public void setDateBonMvt(Calendar dateBonMvt) {
		this.dateBonMvt = dateBonMvt;
	}

	public String getNumOF() {
		return numOF;
	}

	public void setNumOF(String numOF) {
		this.numOF = numOF;
	}

	@Override
	public String toString() {
		return "RechercheMulticritereMouvementValue [historique=" + historique + ", type=" + type + ", famille="
				+ famille + ", sousFamille=" + sousFamille + ", finCone=" + finCone + ", reference=" + reference
				+ ", article=" + article + ", magasin=" + magasin + ", emplacement=" + emplacement + ", dateDu="
				+ dateDu + ", DateA=" + DateA + ", raison=" + raison + ", responsable=" + responsable + ", client="
				+ client + ", fournisseur=" + fournisseur + ", sousTraitant=" + sousTraitant + ", ofId=" + ofId
				+ ", entiteStockId=" + entiteStockId + ", refArticle=" + refArticle + ", lot=" + lot + ", oa=" + oa
				+ "]";
	}

}
