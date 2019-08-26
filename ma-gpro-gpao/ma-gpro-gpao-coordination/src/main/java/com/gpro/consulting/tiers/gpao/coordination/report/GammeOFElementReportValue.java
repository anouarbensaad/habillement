/**
 * 
 */
package com.gpro.consulting.tiers.gpao.coordination.report;

/**
 * @author Ameni Berrich
 *
 */
public class GammeOFElementReportValue implements Comparable<GammeOFElementReportValue>{

	private Long id;

	private Double temps;
	private Long pdh;
	private String observations;
	private Boolean comptage;

	private Long operationId;
	private Long gammeOFId;
	private Long sectionId;
	private Long machineId;
	
	private Long ordre;
	
	private String machineDesignation;
	private String sectionDesignation;
	private String operationDesignation;
	private String operationCode;
	
	@Override
	public int compareTo(GammeOFElementReportValue element) {
		
		return ordre.compareTo(element.getOrdre());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getTemps() {
		return temps;
	}
	public void setTemps(Double temps) {
		this.temps = temps;
	}
	public Long getPdh() {
		return pdh;
	}
	public void setPdh(Long pdh) {
		this.pdh = pdh;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public Boolean getComptage() {
		return comptage;
	}
	public void setComptage(Boolean comptage) {
		this.comptage = comptage;
	}
	public Long getOperationId() {
		return operationId;
	}
	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}
	public Long getGammeOFId() {
		return gammeOFId;
	}
	public void setGammeOFId(Long gammeOFId) {
		this.gammeOFId = gammeOFId;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public Long getMachineId() {
		return machineId;
	}
	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}
	public Long getOrdre() {
		return ordre;
	}
	public void setOrdre(Long ordre) {
		this.ordre = ordre;
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
	public String getOperationDesignation() {
		return operationDesignation;
	}
	public void setOperationDesignation(String operationDesignation) {
		this.operationDesignation = operationDesignation;
	}
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	
}
