package com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportOperateur.historique;

import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Ameni Berrich
 *
 */
public class OperateurHistoriqueReportValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private List<OperateurHistoriqueElementReportValue> operateurHistoriqueElementReportValue;

	
	private Calendar dateSaisieMin;
	private Calendar dateSaisieMax;
	private String matricule;
	private String nomPrenom;
//	private Double totalRendement;
//
//	public Double getTotalRendement() {
//		return totalRendement;
//	}
//
//	public void setTotalRendement(Double totalRendement) {
//		this.totalRendement = totalRendement;
//	}

	// Conversion id / Designation
	private String chaineDesignation;

	public InputStream getReportStream() {
		return reportStream;
	}

	public void setReportStream(InputStream reportStream) {
		this.reportStream = reportStream;
	}
	

	public String getNomPrenom() {
		return nomPrenom;
	}

	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
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

	public List<OperateurHistoriqueElementReportValue> getOperateurHistoriqueElementReportValue() {
		return operateurHistoriqueElementReportValue;
	}

	public void setOperateurHistoriqueElementReportValue(
			List<OperateurHistoriqueElementReportValue> operateurHistoriqueElementReportValue) {
		this.operateurHistoriqueElementReportValue = operateurHistoriqueElementReportValue;
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

}
