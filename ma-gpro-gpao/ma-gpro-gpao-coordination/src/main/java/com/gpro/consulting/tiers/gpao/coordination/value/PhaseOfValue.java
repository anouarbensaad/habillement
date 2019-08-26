/**
 * 
 */
package com.gpro.consulting.tiers.gpao.coordination.value;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;




/**
 * @author $Ameni
 *
 */
/**
 * @author raan
 *
 */
/**
 * @author raan
 *
 */
public class PhaseOfValue {
	
	/** Id */
	private Long id;

	/** The eb_phase_id. */
	private Long phaseId;

	/** The prix. */
	private Long prix;

	/** The devise. */
	private String devise;

	/** The chaine_id. */
	private Long chaineId;

	/** The pi_pi_id. */
	private Long clientId;
	
	/** The facturee. */
	private boolean facturee;

	/** The methode. */
	private String methode;
	
	/** The date Debut. */
	private Calendar dateDebut;

	/** The date Fin. */
	private Calendar dateFin;
	
	/** The date DebutReel. */
	private Calendar dateDebutreel;

	/** The date FinReel. */
	private Calendar dateFinreel;
	
	/** Observation */
	private String observations;
	
	/** Quantité Production */
	private Long quantite;
	
	/** Quantité Manque */
	private Long manque;
	
	/** Quantité Cloture */
	private Boolean cloture;

	/** Quantité validation */
	private Boolean valide;
	
	/** Liste des Variation. */
	private Set<CauseVariationValue> CauseVariation = new HashSet<CauseVariationValue>();

	/********** Getter & Setter ********/
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
	 * @param methode the methode to set
	 */
	public void setMethode(String methode) {
		this.methode = methode;
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
	 * @returndateDebutreel
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
	 * @return qteprod
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
	 * @return manquant
	 */
	public Long getManque() {
		return manque;
	}

	/**
	 * @param manquant
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

	public Set<CauseVariationValue> getCauseVariation() {
		return CauseVariation;
	}

	public void setCauseVariation(Set<CauseVariationValue> causeVariation) {
		CauseVariation = causeVariation;
	}

	
	

	

}
