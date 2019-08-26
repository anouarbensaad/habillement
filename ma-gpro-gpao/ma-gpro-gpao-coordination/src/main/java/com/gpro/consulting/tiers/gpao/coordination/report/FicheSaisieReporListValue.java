package com.gpro.consulting.tiers.gpao.coordination.report;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


/**
 * @author Ameni Berrich
 *
 */
public class FicheSaisieReporListValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private List<ResultatRechecheFeuilleSaisieByMatriculeElementValue> ficheSaisieElementByMatriculeList =  new ArrayList<ResultatRechecheFeuilleSaisieByMatriculeElementValue>();
	
	private String matricule;
	private Long chaineId;
	private Calendar dateSaisieMin;
	private Calendar dateSaisieMax;
	
	private Double totalAleas;
	private Double totalPresence;
	private Double totalProduction;
	private Double totalRendement;
	private Double totalActivite;
	
	//Conversion id / Designation
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

	public List<ResultatRechecheFeuilleSaisieByMatriculeElementValue> getFicheSaisieElementByMatriculeList() {
		return ficheSaisieElementByMatriculeList;
	}

	public void setFicheSaisieElementByMatriculeList(
			List<ResultatRechecheFeuilleSaisieByMatriculeElementValue> ficheSaisieElementByMatriculeList) {
		this.ficheSaisieElementByMatriculeList = ficheSaisieElementByMatriculeList;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Long getChaineId() {
		return chaineId;
	}

	public void setChaineId(Long chaineId) {
		this.chaineId = chaineId;
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


	public Double getTotalAleas() {
		return totalAleas;
	}

	public void setTotalAleas(Double totalAleas) {
		this.totalAleas = totalAleas;
	}

	public Double getTotalPresence() {
		return totalPresence;
	}

	public void setTotalPresence(Double totalPresence) {
		this.totalPresence = totalPresence;
	}

	public Double getTotalProduction() {
		return totalProduction;
	}

	public void setTotalProduction(Double totalProduction) {
		this.totalProduction = totalProduction;
	}

	public Double getTotalRendement() {
		return totalRendement;
	}

	public void setTotalRendement(Double totalRendement) {
		this.totalRendement = totalRendement;
	}

	public Double getTotalActivite() {
		return totalActivite;
	}

	public void setTotalActivite(Double totalActivite) {
		this.totalActivite = totalActivite;
	}
	
}
