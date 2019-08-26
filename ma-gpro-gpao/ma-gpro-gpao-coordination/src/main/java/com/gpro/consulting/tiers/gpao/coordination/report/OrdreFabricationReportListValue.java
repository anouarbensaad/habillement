package com.gpro.consulting.tiers.gpao.coordination.report;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;

/**
 * @author Wahid Gazzah
 * @since 03/05/2016
 */

public class OrdreFabricationReportListValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private List<OrdreFabricationValue> ordreFabricationList = new ArrayList<OrdreFabricationValue>();
	
	private String numero;
	private String  compositionBC;
	private String compositionClient;
	private String etat;
	private Calendar dateIntroductionDu;
	private Calendar dateIntroductionAu;
	private Long clientId;
	private Long produitId;
	private Calendar dateLivraisonDu;
	private Calendar dateLivraisonTo;
	
	//Added on 04/05/2016, by Ameni Berrich
	//Conversion Id Designation
	private String clientAbreviation;
	private String produitDesignation;

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

	public List<OrdreFabricationValue> getOrdreFabricationList() {
		return ordreFabricationList;
	}

	public void setOrdreFabricationList(
			List<OrdreFabricationValue> ordreFabricationList) {
		this.ordreFabricationList = ordreFabricationList;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCompositionBC() {
		return compositionBC;
	}

	public void setCompositionBC(String compositionBC) {
		this.compositionBC = compositionBC;
	}

	public String getCompositionClient() {
		return compositionClient;
	}

	public void setCompositionClient(String compositionClient) {
		this.compositionClient = compositionClient;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Calendar getDateIntroductionDu() {
		return dateIntroductionDu;
	}

	public void setDateIntroductionDu(Calendar dateIntroductionDu) {
		this.dateIntroductionDu = dateIntroductionDu;
	}

	public Calendar getDateIntroductionAu() {
		return dateIntroductionAu;
	}

	public void setDateIntroductionAu(Calendar dateIntroductionAu) {
		this.dateIntroductionAu = dateIntroductionAu;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public Calendar getDateLivraisonDu() {
		return dateLivraisonDu;
	}

	public void setDateLivraisonDu(Calendar dateLivraisonDu) {
		this.dateLivraisonDu = dateLivraisonDu;
	}

	public Calendar getDateLivraisonTo() {
		return dateLivraisonTo;
	}

	public void setDateLivraisonTo(Calendar dateLivraisonTo) {
		this.dateLivraisonTo = dateLivraisonTo;
	}

	public JRBeanCollectionDataSource getjRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}

	public void setjRBeanCollectionDataSource(
			JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}

	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}

	public String getProduitDesignation() {
		return produitDesignation;
	}

	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}
	
	
	

}
