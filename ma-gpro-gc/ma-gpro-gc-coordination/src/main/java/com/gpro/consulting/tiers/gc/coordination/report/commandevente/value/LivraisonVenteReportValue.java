package com.gpro.consulting.tiers.gc.coordination.report.commandevente.value;

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
public class LivraisonVenteReportValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private String reference;
	private String referenceCommande;
	private Double prixTotal;
	private Calendar dateLivraison;
	private Calendar dateCommande;
	private String observations;
	private String saison;
	
	private String modeReglement;
	private Long colis;
	private Double poids;
	
	private String clientAbreviation;
	private String clientAdresse;
	private String clientTelephone;
	private String clientFax;
	
	private List<ProduitLivraisonReportValue> listProduitLivraison = new ArrayList<ProduitLivraisonReportValue>();
	
	private String adresseLivraison;
	private Long quantiteTotal;
	private String clientReference;
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
	
	public Calendar getDateCommande() {
		return dateCommande;
	}
	public void setDateCommande(Calendar dateCommande) {
		this.dateCommande = dateCommande;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public String getSaison() {
		return saison;
	}
	public void setSaison(String saison) {
		this.saison = saison;
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
	public List<ProduitLivraisonReportValue> getListProduitLivraison() {
		return listProduitLivraison;
	}
	public void setListProduitLivraison(
			List<ProduitLivraisonReportValue> listProduitLivraison) {
		this.listProduitLivraison = listProduitLivraison;
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
	public String getReferenceCommande() {
		return referenceCommande;
	}
	public void setReferenceCommande(String referenceCommande) {
		this.referenceCommande = referenceCommande;
	}
	public Long getColis() {
		return colis;
	}
	public void setColis(Long colis) {
		this.colis = colis;
	}
	public String getModeReglement() {
		return modeReglement;
	}
	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}
	public Double getPoids() {
		return poids;
	}
	public void setPoids(Double poids) {
		this.poids = poids;
	}
	
}
