package com.gpro.consulting.tiers.gpao.persitance.entite;

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

import com.gpro.consulting.tiers.gpao.coordination.IConstante;

/**
 * @author $Ameni
 *
 */
@Entity
@Table(name = IConstante.TABLE_GP_ORDREFABRICATION)
public class OrdreFabricationEntite implements Serializable {
	
	private static final long serialVersionUID = 1550579276203730476L;


	/** Id. */
	@Id
	@SequenceGenerator(name = "GP_ORDREFABRICATION_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_GP_ORDREFABRICATION, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GP_ORDREFABRICATION_ID_GENERATOR")
	private Long id;

	/** The numero. */
	@Column(name = "numero")
	private String numero;

	/** The date introduction. */
	@Column(name = "date_introduction")
	private Calendar dateIntroduction;

	/** The observations. */
	@Column(name = "observations")
	private String observations;

	/** The quantite. */
	@Column(name = "quantite")
	private Long quantite;
	
	/** The bl suppression. */
	@Column(name = "bl_suppression")
	private boolean blSuppression;

	/** The date suppression. */
	@Column(name = "date_suppression")
	private Calendar dateSuppression;

	/** The date creation. */
	@Column(name = "date_creation")
	private Calendar dateCreation;

	/** The date modification. */
	@Column(name = "date_modification")
	private Calendar dateModification;

	/** The date situation, Etat. */
	@Column(name = "gp_situation_id")
	private Long etat;
	
	
	/** *** many-to-one association to CompositionOF. */
	/**
	 * 2 eme version
	@OneToMany(fetch=FetchType.LAZY,mappedBy="ordre", cascade = CascadeType.ALL)
	private Set<CompositionOfEntite> compositionOrdres;
	*/
	/** *** many-to-one association to DetailsOrdre. */
	@OneToMany(fetch=FetchType.LAZY,mappedBy="ordre", cascade = CascadeType.ALL)
	private Set<DetailOfEntite> detailOrdres;
	
	/** *** many-to-one association to DetailsOrdre. */
	@OneToMany(fetch=FetchType.LAZY,mappedBy="ordre", cascade = CascadeType.ALL)
	private Set<PhaseOfEntite> phaseOrdres;

	@Column(name = "produit_id")
	private Long produitId;
	
	@Column(name = "pi_pi_id")
	private Long partieInterresId;
	
	@Column(name = "date_livraison")
	private Calendar dateLivraison;
	
	//Added By Ghazi on 19/02/2018
	/** The numeroInterne. */
	@Column(name = "numero_interne")
	private String numeroInterne;

	
	
	
	
	
	
	@Column(name = "qt_coupe")
	private Long qtCoupe;
	
	@Column(name = "qt_eng")
	private Long qtEngagement;
	
	@Column(name = "qt_sort")
	private Long qtSortie;
	
	@Column(name = "qt_fini")
	private Long qtFinition;
	
	@Column(name = "qt_colis")
	private Long qtColisage;
	
	@Column(name = "qt_exp")
	private Long qtExpedition;
	
	@Column(name = "qt_sup1")
	private Long qtSupp1;
	
	@Column(name = "qt_sup2")
	private Long qtSupp2;
	
	@Column(name = "qt_sup3")
	private Long qtSupp3;
	
	
	@Column(name = "prix_unitaire")
	private Double prixUnitaire;
	
	
	
	
	
	
	
	/************* Getters & Setters *****************/
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
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
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
	 * @return the etat
	 */
	public Long getEtat() {
		return etat;
	}

	/**
	 * @param etat the etat to set
	 */
	public void setEtat(Long etat) {
		this.etat = etat;
	}

	/**
	
	public Set<CompositionOfEntite> getCompositionOrdres() {
		return compositionOrdres;
	}

	public void setCompositionOrdres(Set<CompositionOfEntite> compositionOrdres) {
		this.compositionOrdres = compositionOrdres;
	}
	*/
	
	
	/**
	 * @return the detailOrdres
	 */
	public Set<DetailOfEntite> getDetailOrdres() {
		return detailOrdres;
	}

	/**
	 * @param detailOrdres the detailOrdres to set
	 */
	public void setDetailOrdres(Set<DetailOfEntite> detailOrdres) {
		this.detailOrdres = detailOrdres;
	}

	/**
	 * @return the phaseOrdres
	 */
	public Set<PhaseOfEntite> getPhaseOrdres() {
		return phaseOrdres;
	}

	/**
	 * @param phaseOrdres the phaseOrdres to set
	 */
	public void setPhaseOrdres(Set<PhaseOfEntite> phaseOrdres) {
		this.phaseOrdres = phaseOrdres;
	}

	/**
	 * @return the quantite
	 */
	public Long getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public Long getPartieInterresId() {
		return partieInterresId;
	}

	public void setPartieInterresId(Long partieInterresId) {
		this.partieInterresId = partieInterresId;
	}

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public String getNumeroInterne() {
		return numeroInterne;
	}

	public void setNumeroInterne(String numeroInterne) {
		this.numeroInterne = numeroInterne;
	}

	public Long getQtCoupe() {
		return qtCoupe;
	}

	public void setQtCoupe(Long qtCoupe) {
		this.qtCoupe = qtCoupe;
	}

	public Long getQtEngagement() {
		return qtEngagement;
	}

	public void setQtEngagement(Long qtEngagement) {
		this.qtEngagement = qtEngagement;
	}

	public Long getQtSortie() {
		return qtSortie;
	}

	public void setQtSortie(Long qtSortie) {
		this.qtSortie = qtSortie;
	}

	public Long getQtFinition() {
		return qtFinition;
	}

	public void setQtFinition(Long qtFinition) {
		this.qtFinition = qtFinition;
	}

	public Long getQtColisage() {
		return qtColisage;
	}

	public void setQtColisage(Long qtColisage) {
		this.qtColisage = qtColisage;
	}

	public Long getQtExpedition() {
		return qtExpedition;
	}

	public void setQtExpedition(Long qtExpedition) {
		this.qtExpedition = qtExpedition;
	}

	public Long getQtSupp1() {
		return qtSupp1;
	}

	public void setQtSupp1(Long qtSupp1) {
		this.qtSupp1 = qtSupp1;
	}

	public Long getQtSupp2() {
		return qtSupp2;
	}

	public void setQtSupp2(Long qtSupp2) {
		this.qtSupp2 = qtSupp2;
	}

	public Long getQtSupp3() {
		return qtSupp3;
	}

	public void setQtSupp3(Long qtSupp3) {
		this.qtSupp3 = qtSupp3;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	
}
