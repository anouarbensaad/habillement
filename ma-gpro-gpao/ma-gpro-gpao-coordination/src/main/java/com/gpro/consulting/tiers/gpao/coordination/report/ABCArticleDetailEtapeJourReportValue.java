/**
 * 
 */
package com.gpro.consulting.tiers.gpao.coordination.report;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Ameni Berrich
 * @since 25/05/2016
 */

public class ABCArticleDetailEtapeJourReportValue {
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private List<ABCArticleDetailEtapeJourElementValue> abcArticleEtapeJourList = new ArrayList<ABCArticleDetailEtapeJourElementValue>();
	
	private Calendar dateSaisieTo;
	private Calendar dateSaisieFrom;
	private Long phaseId;
	private String affichage;
	
	//Conversion id / Designation
	private String phaseDesignation;

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

	public List<ABCArticleDetailEtapeJourElementValue> getAbcArticleEtapeJourList() {
		return abcArticleEtapeJourList;
	}

	public void setAbcArticleEtapeJourList(
			List<ABCArticleDetailEtapeJourElementValue> abcArticleEtapeJourList) {
		this.abcArticleEtapeJourList = abcArticleEtapeJourList;
	}

	public Calendar getDateSaisieTo() {
		return dateSaisieTo;
	}

	public void setDateSaisieTo(Calendar dateSaisieTo) {
		this.dateSaisieTo = dateSaisieTo;
	}

	public Calendar getDateSaisieFrom() {
		return dateSaisieFrom;
	}

	public void setDateSaisieFrom(Calendar dateSaisieFrom) {
		this.dateSaisieFrom = dateSaisieFrom;
	}

	public Long getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
	}

	public String getPhaseDesignation() {
		return phaseDesignation;
	}

	public void setPhaseDesignation(String phaseDesignation) {
		this.phaseDesignation = phaseDesignation;
	}

	public String getAffichage() {
		return affichage;
	}

	public void setAffichage(String affichage) {
		this.affichage = affichage;
	}

}
