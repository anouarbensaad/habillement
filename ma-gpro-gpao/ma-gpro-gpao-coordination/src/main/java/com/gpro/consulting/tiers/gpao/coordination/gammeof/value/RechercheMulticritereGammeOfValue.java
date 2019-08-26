package com.gpro.consulting.tiers.gpao.coordination.gammeof.value;

/**
 * @author Wahid Gazzah
 * @since 10/05/2016
 *
 */
public class RechercheMulticritereGammeOfValue {

	private Long ordreFabricationId;
	private Long produitId;
	private Double tempsTotalMin;
	private Double tempsTotalMax;
	private Long machineId;
	private Long sectionId;

	private boolean isOptimized;

	public Long getOrdreFabricationId() {
		return ordreFabricationId;
	}
	public void setOrdreFabricationId(Long ordreFabricationId) {
		this.ordreFabricationId = ordreFabricationId;
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
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public boolean isOptimized() {
		return isOptimized;
	}
	public void setOptimized(boolean isOptimized) {
		this.isOptimized = isOptimized;
	}
	
}
