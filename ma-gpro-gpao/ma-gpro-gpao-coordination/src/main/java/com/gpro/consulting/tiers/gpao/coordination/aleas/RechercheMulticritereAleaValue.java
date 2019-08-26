package com.gpro.consulting.tiers.gpao.coordination.aleas;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 25/05/2016
 */
public class RechercheMulticritereAleaValue {

	private String matricule;
	private Long chaineId;
	private Calendar dateSaisieMin;
	private Calendar dateSaisieMax;
	private Long aleaId;
	
	
	public Long getChaineId() {
		return chaineId;
	}
	public void setChaineId(Long chaineId) {
		this.chaineId = chaineId;
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
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public Long getAleaId() {
		return aleaId;
	}
	public void setAleaId(Long aleaId) {
		this.aleaId = aleaId;
	}

}
