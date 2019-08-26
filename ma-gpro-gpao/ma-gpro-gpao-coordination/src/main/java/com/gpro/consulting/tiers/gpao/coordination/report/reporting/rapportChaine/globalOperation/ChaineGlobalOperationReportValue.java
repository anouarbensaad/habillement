package com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.globalOperation;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Ameni Berrich
 *
 */
public class ChaineGlobalOperationReportValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private Long chaineId;
	private String chaineDesignation;
	
	private Calendar dateIntroductionMin;
	private Calendar dateIntroductionMax;
	
	private List<ChaineGlobalOperationElementReportValue> chaineGlobalOperationElementList = new ArrayList<ChaineGlobalOperationElementReportValue>();

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

	public Long getChaineId() {
		return chaineId;
	}

	public void setChaineId(Long chaineId) {
		this.chaineId = chaineId;
	}

	public String getChaineDesignation() {
		return chaineDesignation;
	}

	public void setChaineDesignation(String chaineDesignation) {
		this.chaineDesignation = chaineDesignation;
	}

	public Calendar getDateIntroductionMin() {
		return dateIntroductionMin;
	}

	public void setDateIntroductionMin(Calendar dateIntroductionMin) {
		this.dateIntroductionMin = dateIntroductionMin;
	}

	public Calendar getDateIntroductionMax() {
		return dateIntroductionMax;
	}

	public void setDateIntroductionMax(Calendar dateIntroductionMax) {
		this.dateIntroductionMax = dateIntroductionMax;
	}

	public List<ChaineGlobalOperationElementReportValue> getChaineGlobalOperationElementList() {
		return chaineGlobalOperationElementList;
	}

	public void setChaineGlobalOperationElementList(
			List<ChaineGlobalOperationElementReportValue> chaineGlobalOperationElementList) {
		this.chaineGlobalOperationElementList = chaineGlobalOperationElementList;
	}
	
}
