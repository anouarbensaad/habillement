package com.gpro.consulting.tiers.gpao.coordination.operation.value;

/**
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */
public class RechercheMulticritereCatalogueValue {

	private String code;
	private Long machineId;
	private Long sectionId;
	private String designation;
	private String observations;
	
	private boolean isOptimized;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public boolean isOptimized() {
		return isOptimized;
	}
	public void setOptimized(boolean isOptimized) {
		this.isOptimized = isOptimized;
	}
	
}
