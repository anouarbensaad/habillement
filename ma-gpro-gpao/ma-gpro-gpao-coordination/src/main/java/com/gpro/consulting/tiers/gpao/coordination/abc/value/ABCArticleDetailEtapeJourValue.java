package com.gpro.consulting.tiers.gpao.coordination.abc.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 03/05/2016
 *
 */
public class ABCArticleDetailEtapeJourValue implements Comparable<ABCArticleDetailEtapeJourValue>{
	
	private Long id;
	private Long qte;
	private Long variationPra;
	private Long variationThe;
	private Calendar dateSaisie;
	private String refArticle;
	private String refCommande;
	private String taille;
	private String couleur;
	private String nomPhase;
	private Long qteCmd;
	private String lieuExcecution;
	private String clientAbrevation;
	private Long phaseId;
	private Long abcarticledetailId;
	private String produitAbrevation;

	public int compareTo(ABCArticleDetailEtapeJourValue element) {
		return (element.getId().compareTo(id));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQte() {
		return qte;
	}

	public void setQte(Long qte) {
		this.qte = qte;
	}

	public Long getVariationPra() {
		return variationPra;
	}

	public void setVariationPra(Long variationPra) {
		this.variationPra = variationPra;
	}

	public Long getVariationThe() {
		return variationThe;
	}

	public void setVariationThe(Long variationThe) {
		this.variationThe = variationThe;
	}

	public Calendar getDateSaisie() {
		return dateSaisie;
	}

	public void setDateSaisie(Calendar dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public String getRefArticle() {
		return refArticle;
	}

	public void setRefArticle(String refArticle) {
		this.refArticle = refArticle;
	}

	public String getRefCommande() {
		return refCommande;
	}

	public void setRefCommande(String refCommande) {
		this.refCommande = refCommande;
	}

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getNomPhase() {
		return nomPhase;
	}

	public void setNomPhase(String nomPhase) {
		this.nomPhase = nomPhase;
	}

	public Long getQteCmd() {
		return qteCmd;
	}

	public void setQteCmd(Long qteCmd) {
		this.qteCmd = qteCmd;
	}

	public String getLieuExcecution() {
		return lieuExcecution;
	}

	public void setLieuExcecution(String lieuExcecution) {
		this.lieuExcecution = lieuExcecution;
	}

	public String getClientAbrevation() {
		return clientAbrevation;
	}

	public void setClientAbrevation(String clientAbrevation) {
		this.clientAbrevation = clientAbrevation;
	}

	public Long getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
	}

	public Long getAbcarticledetailId() {
		return abcarticledetailId;
	}

	public void setAbcarticledetailId(Long abcarticledetailId) {
		this.abcarticledetailId = abcarticledetailId;
	}

	public String getProduitAbrevation() {
		return produitAbrevation;
	}

	public void setProduitAbrevation(String produitAbrevation) {
		this.produitAbrevation = produitAbrevation;
	}
	

}
