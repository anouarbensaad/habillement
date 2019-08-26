package com.gpro.consulting.tiers.gs.coordination.report.value;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * @author Wahid Gazzah
 * @since 10/02/2016
 *
 */
public class MouvementStockHistoryReportValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private String historique;
	private String type;
	
	//Added on 15/11/2016 by Zeineb
	private String typeRapport;
	
	Set<MouvementStockHistoryElementReportValue> elementsList = new TreeSet<MouvementStockHistoryElementReportValue>();

	public String getHistorique() {
		return historique;
	}
	public void setHistorique(String historique) {
		this.historique = historique;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<MouvementStockHistoryElementReportValue> getElementsList() {
		return elementsList;
	}
	public void setElementsList(
			Set<MouvementStockHistoryElementReportValue> elementsList) {
		this.elementsList = elementsList;
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
	public JRBeanCollectionDataSource getJRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}
	public void setJRBeanCollectionDataSource(
			JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTypeRapport() {
		return typeRapport;
	}
	public void setTypeRapport(String typeRapport) {
		this.typeRapport = typeRapport;
	}
	
}
