/**
 *
 */
package com.gpro.consulting.tiers.gc.coordination.report.commandevente.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Ameni Berrich
 * @since 21 mars 2016
 */
public class ProduitCommandeReportListValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private List<ProduitCommandeVenteReportElementValue> produitCommandeVenteReportList = new ArrayList<ProduitCommandeVenteReportElementValue>();

	private String referenceBC;
	private Long produitId;
	private Long clientId;
	private Calendar dateIntroductionDu;
	private Calendar dateIntroductionA;
	private Calendar dateLivraisonDu;
	private Calendar dateLivraisonA;
	
	//Conversion Id=>Designation
	private String produitReference;
	private String produitDesignation;
	private String clientAbreviation;
	
	
	private String tailleD1;
	private String tailleD2;
	private String tailleD3;
	private String tailleD4;
	private String tailleD5;
	private String tailleD6;
	private Double prixTotal;
	private Long quantiteTotal;
	private List<DetailsProduitCommandeReportValue> listDetailsProduitCommande = new ArrayList<DetailsProduitCommandeReportValue>();
	
	/**
	 * @return the reportStream
	 */
	public InputStream getReportStream() {
	
		return reportStream;
	}
	/**
	 * @param reportStream the reportStream to set
	 */
	public void setReportStream(InputStream reportStream) {
		this.reportStream = reportStream;
	}
	/**
	 * @return the params
	 */
	public HashMap<String, Object> getParams() {
	
		return params;
	}
	/**
	 * @param params the params to set
	 */
	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}
	/**
	 * @return the jRBeanCollectionDataSource
	 */
	public JRBeanCollectionDataSource getjRBeanCollectionDataSource() {
	
		return jRBeanCollectionDataSource;
	}
	/**
	 * @param jRBeanCollectionDataSource the jRBeanCollectionDataSource to set
	 */
	public void setjRBeanCollectionDataSource(
			JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
	
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the produitCommandeVenteReportList
	 */
	public List<ProduitCommandeVenteReportElementValue> getProduitCommandeVenteReportList() {
	
		return produitCommandeVenteReportList;
	}
	/**
	 * @param produitCommandeVenteReportList the produitCommandeVenteReportList to set
	 */
	public void setProduitCommandeVenteReportList(
			List<ProduitCommandeVenteReportElementValue> produitCommandeVenteReportList) {
		this.produitCommandeVenteReportList = produitCommandeVenteReportList;
	}
	/**
	 * @return the referenceBC
	 */
	public String getReferenceBC() {
	
		return referenceBC;
	}
	/**
	 * @param referenceBC the referenceBC to set
	 */
	public void setReferenceBC(String referenceBC) {
		this.referenceBC = referenceBC;
	}
	/**
	 * @return the produitId
	 */
	public Long getProduitId() {
	
		return produitId;
	}
	/**
	 * @param produitId the produitId to set
	 */
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	/**
	 * @return the clientId
	 */
	public Long getClientId() {
	
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the dateIntroductionDu
	 */
	public Calendar getDateIntroductionDu() {
	
		return dateIntroductionDu;
	}
	/**
	 * @param dateIntroductionDu the dateIntroductionDu to set
	 */
	public void setDateIntroductionDu(Calendar dateIntroductionDu) {
		this.dateIntroductionDu = dateIntroductionDu;
	}
	/**
	 * @return the dateIntroductionA
	 */
	public Calendar getDateIntroductionA() {
	
		return dateIntroductionA;
	}
	/**
	 * @param dateIntroductionA the dateIntroductionA to set
	 */
	public void setDateIntroductionA(Calendar dateIntroductionA) {
		this.dateIntroductionA = dateIntroductionA;
	}
	/**
	 * @return the dateLivraisonDu
	 */
	public Calendar getDateLivraisonDu() {
	
		return dateLivraisonDu;
	}
	/**
	 * @param dateLivraisonDu the dateLivraisonDu to set
	 */
	public void setDateLivraisonDu(Calendar dateLivraisonDu) {
		this.dateLivraisonDu = dateLivraisonDu;
	}
	/**
	 * @return the dateLivraisonA
	 */
	public Calendar getDateLivraisonA() {
	
		return dateLivraisonA;
	}
	/**
	 * @param dateLivraisonA the dateLivraisonA to set
	 */
	public void setDateLivraisonA(Calendar dateLivraisonA) {
		this.dateLivraisonA = dateLivraisonA;
	}
	
	/**
	 * @return the produitReference
	 */
	public String getProduitReference() {
	
		return produitReference;
	}
	/**
	 * @param produitReference the produitReference to set
	 */
	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}
	
	/**
	 * @return the produitDesignation
	 */
	public String getProduitDesignation() {
	
		return produitDesignation;
	}
	/**
	 * @param produitDesignation the produitDesignation to set
	 */
	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}
	/**
	 * @return the clientAbreviation
	 */
	public String getClientAbreviation() {
	
		return clientAbreviation;
	}
	/**
	 * @param clientAbreviation the clientAbreviation to set
	 */
	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}
	public String getTailleD1() {
		return tailleD1;
	}
	public void setTailleD1(String tailleD1) {
		this.tailleD1 = tailleD1;
	}
	public String getTailleD2() {
		return tailleD2;
	}
	public void setTailleD2(String tailleD2) {
		this.tailleD2 = tailleD2;
	}
	public String getTailleD3() {
		return tailleD3;
	}
	public void setTailleD3(String tailleD3) {
		this.tailleD3 = tailleD3;
	}
	public String getTailleD4() {
		return tailleD4;
	}
	public void setTailleD4(String tailleD4) {
		this.tailleD4 = tailleD4;
	}
	public String getTailleD5() {
		return tailleD5;
	}
	public void setTailleD5(String tailleD5) {
		this.tailleD5 = tailleD5;
	}
	public String getTailleD6() {
		return tailleD6;
	}
	public void setTailleD6(String tailleD6) {
		this.tailleD6 = tailleD6;
	}
	public List<DetailsProduitCommandeReportValue> getListDetailsProduitCommande() {
		return listDetailsProduitCommande;
	}
	public void setListDetailsProduitCommande(
			List<DetailsProduitCommandeReportValue> listDetailsProduitCommande) {
		this.listDetailsProduitCommande = listDetailsProduitCommande;
	}
	public Double getPrixTotal() {
		return prixTotal;
	}
	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}
	public Long getQuantiteTotal() {
		return quantiteTotal;
	}
	public void setQuantiteTotal(Long quantiteTotal) {
		this.quantiteTotal = quantiteTotal;
	}
	
	
}
