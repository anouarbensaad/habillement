package com.gpro.consulting.tiers.gpao.persitance.abc.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gpao.coordination.IConstante;

/**
 * @author Wahid Gazzah
 * @since 03/05/2016
 *
 */

@Entity
@Table(name = IConstante.TABLE_GP_ABCARTICLEDETAILETAPEJOUR)
public class ABCArticleDetailEtapeJourEntity implements Serializable {

	private static final long serialVersionUID = 4933052961633465600L;

	@Id
	@SequenceGenerator(name = "ABCARTICLEDETAILETAPEJOUR_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_GP_ABCARTICLEDETAILETAPEJOUR, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ABCARTICLEDETAILETAPEJOUR_ID_GENERATOR")
	private Long id;

	@Column(name = "QTE")
	private Long qte;

	@Column(name = "VARIATION_PRA")
	private Long variationPra;

	@Column(name = "VARIATION_THE")
	private Long variationThe;

	@Column(name = "DATE_SAISIE")
	private Calendar dateSaisie;

	@Column(name = "REF_ARTICLE")
	private String refArticle;

	@Column(name = "REF_COMMANDE")
	private String refCommande;

	@Column(name = "TAILLE")
	private String taille;

	@Column(name = "COULEUR")
	private String couleur;

	@Column(name = "NOM_PHASE")
	private String nomPhase;

	@Column(name = "QTE_CMD")
	private Long qteCmd;

	@Column(name = "LIEU_EXCECUTION")
	private String lieuExcecution;

	@Column(name = "CLIENT_ABREV")
	private String clientAbrevation;

	@Column(name = "ID_PHASE")
	private Long phaseId;

	@Column(name = "id_abc_article_detail")
	private Long abcarticledetailId;
	             
	@Column(name = "PRODUIT_ABREV")
	private String produitAbrevation;

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
