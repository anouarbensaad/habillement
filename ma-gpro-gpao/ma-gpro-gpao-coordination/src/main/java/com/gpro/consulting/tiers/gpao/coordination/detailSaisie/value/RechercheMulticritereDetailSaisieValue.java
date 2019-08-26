package com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 *
 */
public class RechercheMulticritereDetailSaisieValue {
	
	private String matricule;
	private String paquetId;
	private Long ofId;
	private Long operation;
	private Calendar dateSaisieMin;
	private Calendar dateSaisieMax;
	private Long elementGammeOfId;
	
	private Long chaineId;
	private boolean isOptimized;

	private Long clientId;
	
	private Long produitId;
	
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getPaquetId() {
		return paquetId;
	}
	public void setPaquetId(String paquetId) {
		this.paquetId = paquetId;
	}
	public Long getOfId() {
		return ofId;
	}
	public void setOfId(Long ofId) {
		this.ofId = ofId;
	}
	public Long getOperation() {
		return operation;
	}
	public void setOperation(Long operation) {
		this.operation = operation;
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
	public Long getElementGammeOfId() {
		return elementGammeOfId;
	}
	public void setElementGammeOfId(Long elementGammeOfId) {
		this.elementGammeOfId = elementGammeOfId;
	}
	public Long getChaineId() {
		return chaineId;
	}
	public void setChaineId(Long chaineId) {
		this.chaineId = chaineId;
	}
	public boolean isOptimized() {
		return isOptimized;
	}
	public void setOptimized(boolean isOptimized) {
		this.isOptimized = isOptimized;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	
}
