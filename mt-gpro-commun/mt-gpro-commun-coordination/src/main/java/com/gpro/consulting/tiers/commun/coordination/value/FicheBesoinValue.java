package com.gpro.consulting.tiers.commun.coordination.value;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class FicheBesoinValue {
	
	private Long 	 idFicheBesoin;
	private Calendar dateIntroduction;
	
	private ProduitValue produitValue;
	
	private Calendar dateCreation;
	private String   responsable;
	private String   observation;
	
//	private boolean  blSuppression;
//	private Calendar dateSuppression;
//	private Calendar dateModification;
	
	
	private Set<ElementBesoinValue> elementBesoinValue = new HashSet<ElementBesoinValue>();
	
	public Long getIdFicheBesoin() {
		return idFicheBesoin;
	}
	public void setIdFicheBesoin(Long idFicheBesoin) {
		this.idFicheBesoin = idFicheBesoin;
	}
	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String respnsable) {
		this.responsable = respnsable;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}

	public ProduitValue getProduitValue() {
		return produitValue;
	}
	public void setProduitValue(ProduitValue produitValue) {
		this.produitValue = produitValue;
	}
//	public boolean isBlSuppression() {
//		return blSuppression;
//	}
//	public void setBlSuppression(boolean blSuppression) {
//		this.blSuppression = blSuppression;
//	}
//	public Calendar getDateSuppression() {
//		return dateSuppression;
//	}
//	public void setDateSuppression(Calendar dateSuppression) {
//		this.dateSuppression = dateSuppression;
//	}
//	public Calendar getDateModification() {
//		return dateModification;
//	}
//	public void setDateModification(Calendar dateModification) {
//		this.dateModification = dateModification;
//	}
	public Calendar getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Set<ElementBesoinValue> getElementBesoinValue() {
		return elementBesoinValue;
	}
	public void setElementBesoinValue(Set<ElementBesoinValue> elementBesoinValue) {
		this.elementBesoinValue = elementBesoinValue;
	}
	@Override
	public String toString() {
		return "FicheBesoinValue [idFicheBesoin=" + idFicheBesoin
				+ ", dateIntroduction=" + dateIntroduction + ", produitValue="
				+ produitValue + ", dateCreation=" + dateCreation
				+ ", responsable=" + responsable + ", observation="
				+ observation + ", elementBesoinValue=" + elementBesoinValue
				+ "]";
	}
	
	
	
	
	
	
}
