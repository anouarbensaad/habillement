package com.gpro.consulting.tiers.gpao.coordination.gammemontage.value;


/**
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */
public class RechercheMulticritereGammeProduitValue {

	private Long produitId;
	private Double tempsTotalMin;
	private Double tempsTotalMax;
	private Long machineId;
	private Long sectionId;
	
	//Conversion id / Designation
	private String produitReference;
	private String produitDesignation;
	private String machineDesignation;
	private String sectionDesignation;
	
	private boolean isOptimized;
	
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public Double getTempsTotalMin() {
		return tempsTotalMin;
	}
	public void setTempsTotalMin(Double tempsTotalMin) {
		this.tempsTotalMin = tempsTotalMin;
	}
	public Double getTempsTotalMax() {
		return tempsTotalMax;
	}
	public void setTempsTotalMax(Double tempsTotalMax) {
		this.tempsTotalMax = tempsTotalMax;
	}
	public Long getMachineId() {
		return machineId;
	}
	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public String getProduitReference() {
		return produitReference;
	}
	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}
	public String getProduitDesignation() {
		return produitDesignation;
	}
	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}
	public String getMachineDesignation() {
		return machineDesignation;
	}
	public void setMachineDesignation(String machineDesignation) {
		this.machineDesignation = machineDesignation;
	}
	public String getSectionDesignation() {
		return sectionDesignation;
	}
	public void setSectionDesignation(String sectionDesignation) {
		this.sectionDesignation = sectionDesignation;
	}
	public boolean isOptimized() {
		return isOptimized;
	}
	public void setOptimized(boolean isOptimized) {
		this.isOptimized = isOptimized;
	}
	
}
