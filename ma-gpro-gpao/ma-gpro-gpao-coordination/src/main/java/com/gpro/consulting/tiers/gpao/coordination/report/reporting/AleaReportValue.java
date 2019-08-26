package com.gpro.consulting.tiers.gpao.coordination.report.reporting;

import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class AleaReportValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

//	private Set<RecapProductionChaineReportElementValue> recapProductionChaineReportElementValueList = new TreeSet<RecapProductionChaineReportElementValue>();
	private List<AleaReportElementValue> aleaReportElementValueList;

	private String matricule;
	private Calendar dateSaisieMin;
	private Calendar dateSaisieMax;
	private String alea;

	// Conversion id / Designation
	private String chaineDesignation;

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

	public void setjRBeanCollectionDataSource(JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
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

	public String getChaineDesignation() {
		return chaineDesignation;
	}

	public void setChaineDesignation(String chaineDesignation) {
		this.chaineDesignation = chaineDesignation;
	}

	public List<AleaReportElementValue> getAleaReportElementValueList() {
		return aleaReportElementValueList;
	}

	public void setAleaReportElementValueList(List<AleaReportElementValue> aleaReportElementValueList) {
		this.aleaReportElementValueList = aleaReportElementValueList;
	}

	public String getAlea() {
		return alea;
	}

	public void setAlea(String alea) {
		this.alea = alea;
	}

}
