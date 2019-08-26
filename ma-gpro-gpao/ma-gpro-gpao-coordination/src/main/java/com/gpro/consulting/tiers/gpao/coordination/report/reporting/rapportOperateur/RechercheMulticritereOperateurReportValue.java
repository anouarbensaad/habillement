package com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportOperateur;

import java.util.Calendar;

/**
 * @author Zeineb Medimagh
 */
public class RechercheMulticritereOperateurReportValue {
	
	private String matricule;
	private Calendar dateSaisieMin;
	private Calendar dateSaisieMax;
	
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public Calendar getDateSaisieMin() {
		return dateSaisieMin;
	}
	public void setDateSaisieMin(Calendar dateSaisieMin) {
		this.dateSaisieMin = dateSaisieMin;
	}
	public Calendar getDateSaisieMax() {
		return dateSaisieMax;
	}
	public void setDateSaisieMax(Calendar dateSaisieMax) {
		this.dateSaisieMax = dateSaisieMax;
	}
	
}
