package com.gpro.consulting.tiers.gc.coordination.report.commandevente.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Ameni Berrich
 * @since 14 mars 2016
 */

public class CommandeVenteReportListValue {
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private List<CommandeVenteReportElementValue> commandeVenteList = new ArrayList<CommandeVenteReportElementValue>();

	private String reference;
	private String produit;
	private String partieInteressee;
	private Calendar dateIntroductionDu;
	private Calendar dateIntroductionA;
	private Calendar dateLivraisonDu;
	private Calendar dateLivraisonA;
	private String typeCommande;
	private String etatCommande;

	// added on 17/03/2016, by Ameni Berrich
	private Long quantiteDu;
	private Long quantiteA;
	private Double coutDu;
	private Double coutA;
	
	//added on 24/03/2016, by Ameni Berrich
	//conversion Id/ Designation
	private String produitReference;
	private String produitDesignation;
	private String clientAbreviation;

	/**
	 * @return the reportStream
	 */
	public InputStream getReportStream() {

		return reportStream;
	}

	/**
	 * @param reportStream
	 *            the reportStream to set
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
	 * @param params
	 *            the params to set
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
	 * @param jRBeanCollectionDataSource
	 *            the jRBeanCollectionDataSource to set
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
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the commandeVenteList
	 */
	public List<CommandeVenteReportElementValue> getCommandeVenteList() {

		return commandeVenteList;
	}

	/**
	 * @param commandeVenteList
	 *            the commandeVenteList to set
	 */
	public void setCommandeVenteList(
			List<CommandeVenteReportElementValue> commandeVenteList) {
		this.commandeVenteList = commandeVenteList;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {

		return reference;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the produit
	 */
	public String getProduit() {

		return produit;
	}

	/**
	 * @param produit
	 *            the produit to set
	 */
	public void setProduit(String produit) {
		this.produit = produit;
	}

	/**
	 * @return the partieInteressee
	 */
	public String getPartieInteressee() {

		return partieInteressee;
	}

	/**
	 * @param partieInteressee
	 *            the partieInteressee to set
	 */
	public void setPartieInteressee(String partieInteressee) {
		this.partieInteressee = partieInteressee;
	}

	/**
	 * @return the dateIntroductionDu
	 */
	public Calendar getDateIntroductionDu() {

		return dateIntroductionDu;
	}

	/**
	 * @param dateIntroductionDu
	 *            the dateIntroductionDu to set
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
	 * @param dateIntroductionA
	 *            the dateIntroductionA to set
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
	 * @param dateLivraisonDu
	 *            the dateLivraisonDu to set
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
	 * @param dateLivraisonA
	 *            the dateLivraisonA to set
	 */
	public void setDateLivraisonA(Calendar dateLivraisonA) {
		this.dateLivraisonA = dateLivraisonA;
	}

	/**
	 * @return the typeCommande
	 */
	public String getTypeCommande() {

		return typeCommande;
	}

	/**
	 * @param typeCommande
	 *            the typeCommande to set
	 */
	public void setTypeCommande(String typeCommande) {
		this.typeCommande = typeCommande;
	}

	/**
	 * @return the etatCommande
	 */
	public String getEtatCommande() {

		return etatCommande;
	}

	/**
	 * @param etatCommande
	 *            the etatCommande to set
	 */
	public void setEtatCommande(String etatCommande) {
		this.etatCommande = etatCommande;
	}

	/**
	 * @return the quantiteDu
	 */
	public Long getQuantiteDu() {
	
		return quantiteDu;
	}

	/**
	 * @param quantiteDu the quantiteDu to set
	 */
	public void setQuantiteDu(Long quantiteDu) {
		this.quantiteDu = quantiteDu;
	}

	/**
	 * @return the quantiteA
	 */
	public Long getQuantiteA() {
	
		return quantiteA;
	}

	/**
	 * @param quantiteA the quantiteA to set
	 */
	public void setQuantiteA(Long quantiteA) {
		this.quantiteA = quantiteA;
	}

	/**
	 * @return the coutDu
	 */
	public Double getCoutDu() {
	
		return coutDu;
	}

	/**
	 * @param coutDu the coutDu to set
	 */
	public void setCoutDu(Double coutDu) {
		this.coutDu = coutDu;
	}

	/**
	 * @return the coutA
	 */
	public Double getCoutA() {
	
		return coutA;
	}

	/**
	 * @param coutA the coutA to set
	 */
	public void setCoutA(Double coutA) {
		this.coutA = coutA;
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
	
}
