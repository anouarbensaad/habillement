package com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 25/05/2016
 */
public class RechercheMulticritereFeuilleSaisieValue {

	private String matricule;
	private Long chaineId;
	private Calendar dateSaisieMin;
	private Calendar dateSaisieMax;
	private Double rendementMin;
	private Double rendementMax;
	private Double activiteMin;
	private Double activiteMax;
	
	private boolean isOptimized;
    private String periode;
	
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
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
	public Double getRendementMin() {
		return rendementMin;
	}
	public void setRendementMin(Double rendementMin) {
		this.rendementMin = rendementMin;
	}
	public Double getRendementMax() {
		return rendementMax;
	}
	public void setRendementMax(Double rendementMax) {
		this.rendementMax = rendementMax;
	}
	public Double getActiviteMin() {
		return activiteMin;
	}
	public void setActiviteMin(Double activiteMin) {
		this.activiteMin = activiteMin;
	}
	public Double getActiviteMax() {
		return activiteMax;
	}
	public void setActiviteMax(Double activiteMax) {
		this.activiteMax = activiteMax;
	}
	public boolean isOptimized() {
		return isOptimized;
	}
	public void setOptimized(boolean isOptimized) {
		this.isOptimized = isOptimized;
	}
	public String getPeriode() {
		return periode;
	}
	public void setPeriode(String periode) {
		this.periode = periode;
	}
	
}
