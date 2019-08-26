/**
 * 
 */
package com.gpro.consulting.tiers.gpao.coordination.report;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.ResultatRechecheFicheEclatementElementValue;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Ameni Berrich
 * @since 23/05/2016
 */
public class FicheEclatementReportListValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private List<ResultatRechecheFicheEclatementElementValue> eclatementList = new ArrayList<ResultatRechecheFicheEclatementElementValue>();
	
	private Long ordreFabricationId;
	private Long produitId;
	
	//Conversion id / Designation
	private String ordreFabricationDesignation;
	private String referenceProduit;
	private String designationProduit;
	
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
	public List<ResultatRechecheFicheEclatementElementValue> getEclatementList() {
		return eclatementList;
	}
	public void setEclatementList(
			List<ResultatRechecheFicheEclatementElementValue> eclatementList) {
		this.eclatementList = eclatementList;
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
	public String getOrdreFabricationDesignation() {
		return ordreFabricationDesignation;
	}
	public void setOrdreFabricationDesignation(String ordreFabricationDesignation) {
		this.ordreFabricationDesignation = ordreFabricationDesignation;
	}
	public String getReferenceProduit() {
		return referenceProduit;
	}
	public void setReferenceProduit(String referenceProduit) {
		this.referenceProduit = referenceProduit;
	}
	public String getDesignationProduit() {
		return designationProduit;
	}
	public void setDesignationProduit(String designationProduit) {
		this.designationProduit = designationProduit;
	}
	
	
}
