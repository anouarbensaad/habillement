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

public class FactureVenteReportValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private String reference;
	private Double prixTotal;
	private Double MontantTotal;
	private Calendar dateFacture;
	private Calendar dateEcheance;
	private String observations;
	private String saison;
	private String referenceCommande;
	private String referenceLivraison;
	private String modeReglement;
	private String clientAbreviation;
	private String clientAdresse;
	private String clientTelephone;
	private String clientFax;
	private Long poids;
	private Long colis;

	private Double prixTotalHT;
	private Long tauxTVA;
	private Double montantTVA;
	private Double montantTTC;


	/**
	 * Hajer AMRI
	 */
	private Double totalFacture;
	private Double poidsBrut;
	private Double poidsNet;
	private Double totalColis;
	private String totalPalette;
	private Double valeurMatierePremiere;
	private Double valeurAjouteArticle;
	private String origineTissus;
	private String incoterm;
	private String volume;
	private String montantTTCToWords;

	private List<ProduitFactureReportValue> listProduitFacture = new ArrayList<ProduitFactureReportValue>();
	private List<DiversFactureReportValue> listDiversFacture = new ArrayList<DiversFactureReportValue>();

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

	public void setjRBeanCollectionDataSource(JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Double getMontantTotal() {
		return MontantTotal;
	}

	public void setMontantTotal(Double montantTotal) {
		MontantTotal = montantTotal;
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

	public Calendar getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(Calendar dateFacture) {
		this.dateFacture = dateFacture;
	}

	public Calendar getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(Calendar dateEcheance) {
		this.dateEcheance = dateEcheance;
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

	public List<ProduitFactureReportValue> getListProduitFacture() {
		return listProduitFacture;
	}

	public void setListProduitFacture(List<ProduitFactureReportValue> listProduitFacture) {
		this.listProduitFacture = listProduitFacture;
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

	public String getReferenceLivraison() {
		return referenceLivraison;
	}

	public void setReferenceLivraison(String referenceLivraison) {
		this.referenceLivraison = referenceLivraison;
	}

	public String getModeReglement() {
		return modeReglement;
	}

	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}

	public Long getPoids() {
		return poids;
	}

	public void setPoids(Long poids) {
		this.poids = poids;
	}

	public Long getColis() {
		return colis;
	}

	public void setColis(Long colis) {
		this.colis = colis;
	}

	public Double getPrixTotalHT() {
		return prixTotalHT;
	}

	public void setPrixTotalHT(Double prixTotalHT) {
		this.prixTotalHT = prixTotalHT;
	}

	public Long getTauxTVA() {
		return tauxTVA;
	}

	public void setTauxTVA(Long tauxTVA) {
		this.tauxTVA = tauxTVA;
	}

	public Double getMontantTVA() {
		return montantTVA;
	}

	public void setMontantTVA(Double montantTVA) {
		this.montantTVA = montantTVA;
	}

	public Double getMontantTTC() {
		return montantTTC;
	}

	public void setMontantTTC(Double montantTTC) {
		this.montantTTC = montantTTC;
	}

	public List<DiversFactureReportValue> getListDiversFacture() {
		return listDiversFacture;
	}

	public void setListDiversFacture(List<DiversFactureReportValue> listDiversFacture) {
		this.listDiversFacture = listDiversFacture;
	}

	public Double getTotalFacture() {
		return totalFacture;
	}

	public void setTotalFacture(Double totalFacture) {
		this.totalFacture = totalFacture;
	}

	public Double getPoidsBrut() {
		return poidsBrut;
	}

	public void setPoidsBrut(Double poidsBrut) {
		this.poidsBrut = poidsBrut;
	}

	public Double getPoidsNet() {
		return poidsNet;
	}

	public void setPoidsNet(Double poidsNet) {
		this.poidsNet = poidsNet;
	}

	public Double getTotalColis() {
		return totalColis;
	}

	public void setTotalColis(Double totalColis) {
		this.totalColis = totalColis;
	}

	public String getTotalPalette() {
		return totalPalette;
	}

	public void setTotalPalette(String totalPalette) {
		this.totalPalette = totalPalette;
	}

	public Double getValeurMatierePremiere() {
		return valeurMatierePremiere;
	}

	public void setValeurMatierePremiere(Double valeurMatierePremiere) {
		this.valeurMatierePremiere = valeurMatierePremiere;
	}

	public Double getValeurAjouteArticle() {
		return valeurAjouteArticle;
	}

	public void setValeurAjouteArticle(Double valeurAjouteArticle) {
		this.valeurAjouteArticle = valeurAjouteArticle;
	}

	public String getOrigineTissus() {
		return origineTissus;
	}

	public void setOrigineTissus(String origineTissus) {
		this.origineTissus = origineTissus;
	}

	public String getMontantTTCToWords() {
		return montantTTCToWords;
	}

	public void setMontantTTCToWords(String montantTTCToWords) {
		this.montantTTCToWords = montantTTCToWords;
	}

	public String getIncoterm() {
		return incoterm;
	}

	public void setIncoterm(String incoterm) {
		this.incoterm = incoterm;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}


}
