/**
 * 
 */
package com.gpro.consulting.tiers.gpao.coordination.report;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 *
 */
public class ResultatRechecheFicheSaisieElementValue {

	private Long id;
	private String matricule;
	private Calendar dateSaisie;
	private String chaineDesignation;
	private Double rendement;
	private Double activite;
	private Double minProd;
	private Double minPresence;
	private Double minAleas;
	private Long pscProd;
	private String nomPrenom;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Calendar getDateSaisie() {
		return dateSaisie;
	}

	public void setDateSaisie(Calendar dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public String getChaineDesignation() {
		return chaineDesignation;
	}

	public void setChaineDesignation(String chaineDesignation) {
		this.chaineDesignation = chaineDesignation;
	}

	public Double getRendement() {
		return rendement;
	}

	public void setRendement(Double rendement) {
		this.rendement = rendement;
	}

	public Double getActivite() {
		return activite;
	}

	public void setActivite(Double activite) {
		this.activite = activite;
	}

	public Double getMinProd() {
		return minProd;
	}

	public void setMinProd(Double minProd) {
		this.minProd = minProd;
	}

	public Double getMinPresence() {
		return minPresence;
	}

	public void setMinPresence(Double minPresence) {
		this.minPresence = minPresence;
	}

	public String getNomPrenom() {
		return nomPrenom;
	}

	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}

	public Long getPscProd() {
		return pscProd;
	}

	public void setPscProd(Long pscProd) {
		this.pscProd = pscProd;
	}

	public Double getMinAleas() {
		return minAleas;
	}

	public void setMinAleas(Double minAleas) {
		this.minAleas = minAleas;
	}

}
