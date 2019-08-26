package com.gpro.consulting.tiers.gpao.persitance.entite;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gpao.coordination.IConstante;

/**
 * @author $Ameni
 *
 */
@Entity
@Table(name = IConstante.TABLE_GP_PHASEOF)
public class PhaseOfEntite implements Serializable {
	
	private static final long serialVersionUID = -81068813889361156L;

	/** Id. */
	@Id
	@SequenceGenerator(name = "GP_PHASEOF_ID_GENERATOR", sequenceName = IConstante.SEQUENCE_GP_PHASEOF, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GP_PHASEOF_ID_GENERATOR")
	private Long id;

	/** The eb_phase_id. */
	@Column(name = "eb_phase_id")
	private Long phaseId;

	/** The prix. */
	@Column(name = "prix")
	private Long prix;

	/** The devise. */
	@Column(name = "devise")
	private String devise;

	/** The chaine_id. */
	@Column(name = "chaine_id")
	private Long chaineId;

	/** The patint_id. */
	@Column(name = "pi_pi_id")
	private Long clientId;
	
	/** The facturee. */
	@Column(name = "facturee")
	private boolean facturee;

	/** The methode. */
	@Column(name = "methode")
	private String methode;

	/** The date Debut. */
	@Column(name = "date_debut")
	private Calendar dateDebut;

	/** The date Fin. */
	@Column(name = "date_fin")
	private Calendar dateFin;
	
	/** The bl suppression. */
	@Column(name = "bl_suppression")
	private boolean blSuppression;

	/** The date suppression. */
	@Column(name = "date_suppression")
	private Calendar dateSuppression;

	/** The date modification. */
	@Column(name = "date_modification")
	private Calendar dateModification;
	
	/** The date Debut Reél. */
	@Column(name = "date_debut_reel")
	private Calendar dateDebutreel;

	/** The date Fin Reél. */
	@Column(name = "date_fin_reelle")
	private Calendar dateFinreel;
	
	/** Observation */
	@Column(name = "observations")
	private String observations;
	
	/** Quantité Production */
	@Column(name = "quantite")
	private Long quantite;
	
	/** Quantité Manquante */
	@Column(name = "manque")
	private Long manque;
	
	/** Quantité Cloture */
	@Column(name = "cloture")
	private Boolean cloture;
	
	/** Quantité validation */
	@Column(name = "valide")
	private Boolean valide;
	
	
	/** * many-to-one association to ordreFabrication*. */
	@ManyToOne
	@JoinColumn(name = "eb_of_id")
    private OrdreFabricationEntite ordre;
	
	/** * many-to-one association to Cause de Variation ordreFabrication*. */
	
	@OneToMany(mappedBy = "phaseOfEntite",cascade=CascadeType.ALL)
	private Set<CauseVariationEntitie> CauseVariationEntitie;

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
	 * @return the prix
	 */
	public Long getPrix() {
		return prix;
	}

	/**
	 * @param prix the prix to set
	 */
	public void setPrix(Long prix) {
		this.prix = prix;
	}

	/**
	 * @return the devise
	 */
	public String getDevise() {
		return devise;
	}

	/**
	 * @param devise the devise to set
	 */
	public void setDevise(String devise) {
		this.devise = devise;
	}

	/**
	 * @return the chaineId
	 */
	public Long getChaineId() {
		return chaineId;
	}

	/**
	 * @param chaineId the chaineId to set
	 */
	public void setChaineId(Long chaineId) {
		this.chaineId = chaineId;
	}

	/**
	 * @return the clientId
	 */
	public Long getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the dateDebut
	 */
	public Calendar getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(Calendar dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public Calendar getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(Calendar dateFin) {
		this.dateFin = dateFin;
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
	 * @return the ordre
	 */
	public OrdreFabricationEntite getOrdre() {
		return ordre;
	}

	/**
	 * @param ordre the ordre to set
	 */
	public void setOrdre(OrdreFabricationEntite ordre) {
		this.ordre = ordre;
	}

	/**
	 * @return the phaseId
	 */
	public Long getPhaseId() {
		return phaseId;
	}

	/**
	 * @param phaseId the phaseId to set
	 */
	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
	}

	/**
	 * @return the facturee
	 */
	public boolean isFacturee() {
		return facturee;
	}

	/**
	 * @param facturee the facturee to set
	 */
	public void setFacturee(boolean facturee) {
		this.facturee = facturee;
	}

	/**
	 * @return the methode
	 */
	public String getMethode() {
		return methode;
	}

	
	/**
	 * @param methode
	 */
	public void setMethode(String methode) {
		this.methode = methode;
	}

	/**
	 * @return dateDebutreel
	 */
	public Calendar getDateDebutreel() {
		return dateDebutreel;
	}

	/**
	 * @param dateDebutreel
	 */
	public void setDateDebutreel(Calendar dateDebutreel) {
		this.dateDebutreel = dateDebutreel;
	}

	/**
	 * @return dateFinreel
	 */
	public Calendar getDateFinreel() {
		return dateFinreel;
	}

	/**
	 * @param dateFinreel
	 */
	public void setDateFinreel(Calendar dateFinreel) {
		this.dateFinreel = dateFinreel;
	}

	/**
	 * @return observation
	 */
	public String getObservations() {
		return observations;
	}

	/**
	 * @param observation
	 */
	public void setObservations(String observations) {
		this.observations = observations;
	}

	/**
	 * @return quantite
	 */
	public Long getQuantite() {
		return quantite;
	}

	/**
	 * @param qteprod
	 */
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	/**
	 * @return manque
	 */
	public Long getManque() {
		return manque;
	}

	/**
	 * @param manque
	 */
	public void setManque(Long manque) {
		this.manque = manque;
	}

	/**
	 * @return cloture
	 */
	public Boolean getCloture() {
		return cloture;
	}

	/**
	 * @param cloture
	 */
	public void setCloture(Boolean cloture) {
		this.cloture = cloture;
	}

	/**
	 * @return valide
	 */
	public Boolean getValide() {
		return valide;
	}

	/**
	 * @param valide
	 */
	public void setValide(Boolean valide) {
		this.valide = valide;
	}

	public Set<CauseVariationEntitie> getCauseVariationEntitie() {
		return CauseVariationEntitie;
	}

	public void setCauseVariationEntitie(
			Set<CauseVariationEntitie> causeVariationEntitie) {
		CauseVariationEntitie = causeVariationEntitie;
	}

	

	
	

	
	
	
}
