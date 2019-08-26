package com.gpro.consulting.tiers.gpao.coordination.report.reporting;

import java.util.Calendar;

public class AleaReportElementValue {

	private String matricule;
	private Calendar dateSaisie;
	private String chaineDesignation;
	private String alea;
	private String nomPrenom;
	private Double duree;
	
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
	public String getNomPrenom() {
		return nomPrenom;
	}

	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}

	public String getAlea() {
		return alea;
	}

	public void setAlea(String alea) {
		this.alea = alea;
	}

	public Double getDuree() {
		return duree;
	}

	public void setDuree(Double duree) {
		this.duree = duree;
	}

}
