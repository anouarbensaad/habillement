/**
 * 
 */
package com.gpro.consulting.tiers.commun.coordination.report.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Ameni Berrich
 * @since 23/05/2016
 *
 */
public class PartieInteresseeReportListValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private List<PartieInteresseeElementValue> partieInteresseeList = new ArrayList<PartieInteresseeElementValue>();

	/** The ref. */
	private String reference;

	/** The raison sociale. */
	private String raisonSociale;

	/** The famille entite. */
	private Long famillePIId;

	/** The categorie entite. */
	private Long categoriePIId;

	/** Type Partie Interessee **/
	private Long typePIId;

	/** Activité Partie Interessee **/
	private String activite;

	private String actif;

	// conversion Id / Designation
	private String famillePIDesignation;
	private String typePIDesignation;
	private String categoriePIDesignation;

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

	public List<PartieInteresseeElementValue> getPartieInteresseeList() {
		return partieInteresseeList;
	}

	public void setPartieInteresseeList(
			List<PartieInteresseeElementValue> partieInteresseeList) {
		this.partieInteresseeList = partieInteresseeList;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getRaisonSociale() {
		return raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	public Long getFamillePIId() {
		return famillePIId;
	}

	public void setFamillePIId(Long famillePIId) {
		this.famillePIId = famillePIId;
	}

	public Long getCategoriePIId() {
		return categoriePIId;
	}

	public void setCategoriePIId(Long categoriePIId) {
		this.categoriePIId = categoriePIId;
	}

	public Long getTypePIId() {
		return typePIId;
	}

	public void setTypePIId(Long typePIId) {
		this.typePIId = typePIId;
	}

	public String getActivite() {
		return activite;
	}

	public void setActivite(String activite) {
		this.activite = activite;
	}

	public String getActif() {
		return actif;
	}

	public void setActif(String actif) {
		this.actif = actif;
	}

	public String getFamillePIDesignation() {
		return famillePIDesignation;
	}

	public void setFamillePIDesignation(String famillePIDesignation) {
		this.famillePIDesignation = famillePIDesignation;
	}

	public String getTypePIDesignation() {
		return typePIDesignation;
	}

	public void setTypePIDesignation(String typePIDesignation) {
		this.typePIDesignation = typePIDesignation;
	}

	public String getCategoriePIDesignation() {
		return categoriePIDesignation;
	}

	public void setCategoriePIDesignation(String categoriePIDesignation) {
		this.categoriePIDesignation = categoriePIDesignation;
	}
}
