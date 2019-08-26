/**
 * 
 */
package com.gpro.consulting.tiers.gpao.coordination.report;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.GammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitElementValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ResultatRechecheGammeOfElementValue;

/**
 * @author Ameni Berrich
 * @since 04/05/2016
 */
public class GammeOFReportListValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private Set<ResultatRechecheGammeOfElementValue> gammeOFList = new TreeSet<ResultatRechecheGammeOfElementValue>();
	private Long ordreFabricationId;
	private Long produitId;
	private Double tempsTotalMin;
	private Double tempsTotalMax;
	private Long machineId;
	private Long sectionId;
	
	//Conversion id / Designation
	private String ordreFabricationReference;
	private String produitReference;
	private String produitDesignation;
	private String machineDesignation;
	private String sectionDesignation;
	
	
	public Set<ResultatRechecheGammeOfElementValue> getGammeOFList() {
		return gammeOFList;
	}
	public void setGammeOFList(Set<ResultatRechecheGammeOfElementValue> gammeOFList) {
		this.gammeOFList = gammeOFList;
	}
	public InputStream getReportStream() {
		return reportStream;
	}
	public void setReportStream(InputStream reportStream) {
		this.reportStream = reportStream;
	}
	public HashMap<String, Object> getParams() {
		return params;
	}
	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}
	public JRBeanCollectionDataSource getjRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}
	public void setjRBeanCollectionDataSource(
			JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public Long getOrdreFabricationId() {
		return ordreFabricationId;
	}
	public void setOrdreFabricationId(Long ordreFabricationId) {
		this.ordreFabricationId = ordreFabricationId;
	}
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
	public String getOrdreFabricationReference() {
		return ordreFabricationReference;
	}
	public void setOrdreFabricationReference(String ordreFabricationReference) {
		this.ordreFabricationReference = ordreFabricationReference;
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
		
	
	
}
