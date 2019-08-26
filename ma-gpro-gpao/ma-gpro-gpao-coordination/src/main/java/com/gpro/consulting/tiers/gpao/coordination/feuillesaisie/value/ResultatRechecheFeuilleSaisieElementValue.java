package com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 25/05/2016
 */
public class ResultatRechecheFeuilleSaisieElementValue implements Comparable<ResultatRechecheFeuilleSaisieElementValue>{

	private Long id;
	private String personnelMatricule;
	private Calendar dateSaisie;
	private Long chaineId;
	private String chaineDesignation;
	private Double rendement;
	private Double activite;
	private Double minProd;
	private Double minPresence;
	private Double minAleas;
	private Long pscProd;
	private String nomPrenom;
	private String periode ;
	
	
	public int compareTo(ResultatRechecheFeuilleSaisieElementValue element) {
		return (element.getId().compareTo(id));
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPersonnelMatricule() {
		return personnelMatricule;
	}

	public void setPersonnelMatricule(String personnelMatricule) {
		this.personnelMatricule = personnelMatricule;
	}

	public Long getChaineId() {
		return chaineId;
	}

	public void setChaineId(Long chaineId) {
		this.chaineId = chaineId;
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

	public Double getMinAleas() {
		return minAleas;
	}

	public void setMinAleas(Double minAleas) {
		this.minAleas = minAleas;
	}

	public Long getPscProd() {
		return pscProd;
	}

	public void setPscProd(Long pscProd) {
		this.pscProd = pscProd;
	}

	public String getNomPrenom() {
		return nomPrenom;
	}

	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}
	
	
	
}
