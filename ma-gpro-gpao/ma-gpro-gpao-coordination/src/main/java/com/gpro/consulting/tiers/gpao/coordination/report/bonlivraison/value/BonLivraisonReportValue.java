package com.gpro.consulting.tiers.gpao.coordination.report.bonlivraison.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * @author Wahid Gazzah
 * @since 29/01/2016
 *
 */
public class BonLivraisonReportValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private List<BonLivraisonReportProductValue> productList = new ArrayList <BonLivraisonReportProductValue>();
	
	private List<BonLivraisonReportTaxeValue> taxeList;
	
	private Long clientId;
	
	private Calendar dateBl;
	private String matriculeFiscal;
	private Double montantRemiseTotal;
	private String reference;
	private String observations;
	private String client;
	private String adresse;
	private String telephone;
	private String fax;
	
	private Double montantTTC;
	private Double montantHTTotal;
	
	private String montantTTCToWords;
	
	// FODEC params
	private Boolean existFodec;
	private String taxeFodec;
	private Double tauxFodec;
	private Double montantTaxeFodec;
	private Double assietteFodec;
	
	// TVA params
	private Boolean existTVA;
	private String taxeTVA;
	private Double tauxTVA;
	private Double montantTaxeTVA;
	private Double assietteTVA;
	
	// TIMBRE params
	private Boolean existTimbre;
	private String taxeTimbre;
	private Double montantTaxeTimbre;
	private Double assietteTimbre;
	
	// total des daxes
	private Double montantTaxes;
	
	//added on 23/02/2016, by Wahid Gazzah
	private Long modepaiementId;
	private Long marcheId;
	private String modepaiement;
	private String marche;
	
    private String  qteTotale;
	
	private String boxes;
	
	
	private String palette;
	
    
	private Long qteTotaleCh1;
	
	private Long qteTotaleCh2;
    
	
	
	

	//added on 07/10/2016, by Zeineb Medimagh	
	private List<BonLivraisonReportTraitementFaconValue> traitementFaconList = new ArrayList <BonLivraisonReportTraitementFaconValue>();
	
	
	
	
	public Long getQteTotaleCh1() {
		return qteTotaleCh1;
	}
	public void setQteTotaleCh1(Long qteTotaleCh1) {
		this.qteTotaleCh1 = qteTotaleCh1;
	}
	public Long getQteTotaleCh2() {
		return qteTotaleCh2;
	}
	public void setQteTotaleCh2(Long qteTotaleCh2) {
		this.qteTotaleCh2 = qteTotaleCh2;
	}
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Calendar getDateBl() {
		return dateBl;
	}
	public void setDateBl(Calendar dateBl) {
		this.dateBl = dateBl;
	}
	public String getMatriculeFiscal() {
		return matriculeFiscal;
	}
	public void setMatriculeFiscal(String matriculeFiscal) {
		this.matriculeFiscal = matriculeFiscal;
	}
	public JRBeanCollectionDataSource getJRBeanCollectionDataSource() {
		return jRBeanCollectionDataSource;
	}
	public void setJRBeanCollectionDataSourceProduct(
			JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	public List<BonLivraisonReportProductValue> getProductList() {
		return productList;
	}
	public void setProductList(List<BonLivraisonReportProductValue> productList) {
		this.productList = productList;
	}
	public List<BonLivraisonReportTaxeValue> getTaxeList() {
		return taxeList;
	}
	public void setTaxeList(List<BonLivraisonReportTaxeValue> taxeList) {
		this.taxeList = taxeList;
	}
	public Double getMontantRemiseTotal() {
		return montantRemiseTotal;
	}
	public void setMontantRemiseTotal(Double montantRemiseTotal) {
		this.montantRemiseTotal = montantRemiseTotal;
	}
	public Double getMontantHTTotal() {
		return montantHTTotal;
	}
	public void setMontantHTTotal(Double montantHTTotal) {
		this.montantHTTotal = montantHTTotal;
	}
	public Double getMontantTTC() {
		return montantTTC;
	}
	public void setMontantTTC(Double montantTTC) {
		this.montantTTC = montantTTC;
	}
	public String getTaxeTVA() {
		return taxeTVA;
	}
	public void setTaxeTVA(String taxeTVA) {
		this.taxeTVA = taxeTVA;
	}
	public Double getTauxTVA() {
		return tauxTVA;
	}
	public void setTauxTVA(Double tauxTVA) {
		this.tauxTVA = tauxTVA;
	}
	public Double getMontantTaxeTVA() {
		return montantTaxeTVA;
	}
	public void setMontantTaxeTVA(Double montantTaxeTVA) {
		this.montantTaxeTVA = montantTaxeTVA;
	}
	public Double getMontantTaxes() {
		return montantTaxes;
	}
	public void setMontantTaxes(Double montantTaxes) {
		this.montantTaxes = montantTaxes;
	}
	public String getTaxeFodec() {
		return taxeFodec;
	}
	public void setTaxeFodec(String taxeFodec) {
		this.taxeFodec = taxeFodec;
	}
	public Double getTauxFodec() {
		return tauxFodec;
	}
	public void setTauxFodec(Double tauxFodec) {
		this.tauxFodec = tauxFodec;
	}
	public Double getMontantTaxeFodec() {
		return montantTaxeFodec;
	}
	public void setMontantTaxeFodec(Double montantTaxeFodec) {
		this.montantTaxeFodec = montantTaxeFodec;
	}
	public Double getAssietteFodec() {
		return assietteFodec;
	}
	public void setAssietteFodec(Double assietteFodec) {
		this.assietteFodec = assietteFodec;
	}
	public Double getAssietteTVA() {
		return assietteTVA;
	}
	public void setAssietteTVA(Double assietteTVA) {
		this.assietteTVA = assietteTVA;
	}
	public String getTaxeTimbre() {
		return taxeTimbre;
	}
	public void setTaxeTimbre(String taxeTimbre) {
		this.taxeTimbre = taxeTimbre;
	}
	public Double getMontantTaxeTimbre() {
		return montantTaxeTimbre;
	}
	public void setMontantTaxeTimbre(Double montantTaxeTimbre) {
		this.montantTaxeTimbre = montantTaxeTimbre;
	}
	public Double getAssietteTimbre() {
		return assietteTimbre;
	}
	public void setAssietteTimbre(Double assietteTimbre) {
		this.assietteTimbre = assietteTimbre;
	}
	public Boolean getExistFodec() {
		return existFodec;
	}
	public void setExistFodec(Boolean existFodec) {
		this.existFodec = existFodec;
	}
	public Boolean getExistTVA() {
		return existTVA;
	}
	public void setExistTVA(Boolean existTVA) {
		this.existTVA = existTVA;
	}
	public Boolean getExistTimbre() {
		return existTimbre;
	}
	public void setExistTimbre(Boolean existTimbre) {
		this.existTimbre = existTimbre;
	}
	public String getMontantTTCToWords() {
		return montantTTCToWords;
	}
	public void setMontantTTCToWords(String montantTTCToWords) {
		this.montantTTCToWords = montantTTCToWords;
	}
	public Long getModepaiementId() {
		return modepaiementId;
	}
	public void setModepaiementId(Long modepaiementId) {
		this.modepaiementId = modepaiementId;
	}
	public Long getMarcheId() {
		return marcheId;
	}
	public void setMarcheId(Long marcheId) {
		this.marcheId = marcheId;
	}
	public String getModepaiement() {
		return modepaiement;
	}
	public void setModepaiement(String modepaiement) {
		this.modepaiement = modepaiement;
	}
	public String getMarche() {
		return marche;
	}
	public void setMarche(String marche) {
		this.marche = marche;
	}
	public List<BonLivraisonReportTraitementFaconValue> getTraitementFaconList() {
		return traitementFaconList;
	}
	public void setTraitementFaconList(List<BonLivraisonReportTraitementFaconValue> traitementFaconList) {
		this.traitementFaconList = traitementFaconList;
	}
	public String getQteTotale() {
		return qteTotale;
	}
	public void setQteTotale(String qteTotale) {
		this.qteTotale = qteTotale;
	}
	public String getBoxes() {
		return boxes;
	}
	public void setBoxes(String boxes) {
		this.boxes = boxes;
	}
	public String getPalette() {
		return palette;
	}
	public void setPalette(String palette) {
		this.palette = palette;
	}
	
	
	
	
	
}
