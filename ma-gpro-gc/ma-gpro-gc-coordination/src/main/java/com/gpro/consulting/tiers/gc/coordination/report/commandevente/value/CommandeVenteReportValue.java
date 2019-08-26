package com.gpro.consulting.tiers.gc.coordination.report.commandevente.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Wahid Gazzah
 * @since 10/03/2016
 */
public class CommandeVenteReportValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private String reference;
	private Double prixTotal;
	private Calendar dateLivraison;
	private Calendar dateIntroduction;
	private String observations;
	private String saison;
	
	private String clientAbreviation;
	private String clientAdresse;
	private String clientTelephone;
	private String clientFax;
	
	private List<ProduitCommandeReportValue> listProduitCommande = new ArrayList<ProduitCommandeReportValue>();
	
	
	private String adresseLivraison;
	private Long quantiteTotal;
	
	//added on 28/03/2016, by Wahid Gazzah
	private String clientReference;
	
	//added on 30/03/2016, by Wahid Gazzah
	private String clientRaisonSociale;
	
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
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Double getPrixTotal() {
		return prixTotal;
	}
	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}
	public Calendar getDateLivraison() {
		return dateLivraison;
	}
	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}
	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}
	public String getClientAdresse() {
		return clientAdresse;
	}
	public void setClientAdresse(String clientAdresse) {
		this.clientAdresse = clientAdresse;
	}
	public String getClientTelephone() {
		return clientTelephone;
	}
	public void setClientTelephone(String clientTelephone) {
		this.clientTelephone = clientTelephone;
	}
	public String getClientFax() {
		return clientFax;
	}
	public void setClientFax(String clientFax) {
		this.clientFax = clientFax;
	}
	public List<ProduitCommandeReportValue> getListProduitCommande() {
		return listProduitCommande;
	}
	public void setListProduitCommande(
			List<ProduitCommandeReportValue> listProduitCommande) {
		this.listProduitCommande = listProduitCommande;
	}
	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}
	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}
	public String getSaison() {
		return saison;
	}
	public void setSaison(String saison) {
		this.saison = saison;
	}
	
	public String getAdresseLivraison() {
		return adresseLivraison;
	}

	public void setAdresseLivraison(String adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}
	public Long getQuantiteTotal() {
		return quantiteTotal;
	}
	public void setQuantiteTotal(Long quantiteTotal) {
		this.quantiteTotal = quantiteTotal;
	}
	public String getClientReference() {
		return clientReference;
	}
	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}
	public String getClientRaisonSociale() {
		return clientRaisonSociale;
	}
	public void setClientRaisonSociale(String clientRaisonSociale) {
		this.clientRaisonSociale = clientRaisonSociale;
	}
	
}
