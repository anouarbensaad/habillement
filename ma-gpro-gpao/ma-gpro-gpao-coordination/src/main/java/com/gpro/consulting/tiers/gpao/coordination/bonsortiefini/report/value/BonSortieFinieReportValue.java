package com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.report.value;

import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 
 * @author Wahid Gazzah
 * @since 12/01/2016
 *
 */
public class BonSortieFinieReportValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private String reference;
	private String type; 
	private String client;
	private Calendar dateSortie;
	private Integer nbColis;
	private Double poidsEcru;
	private Double poidsFini;
	private String infoReception;
	private boolean facon;
	private String traitement;
	
	private List<RouleauFiniReportGroupedByProduitValue> listeRouleauFini;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Calendar getDateSortie() {
		return dateSortie;
	}
	public void setDateSortie(Calendar dateSortie) {
		this.dateSortie = dateSortie;
	}
	public Integer getNbColis() {
		return nbColis;
	}
	public void setNbColis(Integer nbColis) {
		this.nbColis = nbColis;
	}
	public List<RouleauFiniReportGroupedByProduitValue> getListeRouleauFini() {
		return listeRouleauFini;
	}
	public void setListeRouleauFini(
			List<RouleauFiniReportGroupedByProduitValue> listeRouleauFini) {
		this.listeRouleauFini = listeRouleauFini;
	}
	/**
	 * @return the poidsEcru
	 */
	public Double getPoidsEcru() {
		return poidsEcru;
	}
	/**
	 * @param poidsEcru the poidsEcru to set
	 */
	public void setPoidsEcru(Double poidsEcru) {
		this.poidsEcru = poidsEcru;
	}
	/**
	 * @return the poidsFini
	 */
	public Double getPoidsFini() {
		return poidsFini;
	}
	/**
	 * @param poidsFini the poidsFini to set
	 */
	public void setPoidsFini(Double poidsFini) {
		this.poidsFini = poidsFini;
	}
	/**
	 * @return the infoReception
	 */
	public String getInfoReception() {
		return infoReception;
	}
	/**
	 * @param infoReception the infoReception to set
	 */
	public void setInfoReception(String infoReception) {
		this.infoReception = infoReception;
	}
	/**
	 * @return the facon
	 */
	public boolean isFacon() {
		return facon;
	}
	/**
	 * @param facon the facon to set
	 */
	public void setFacon(boolean facon) {
		this.facon = facon;
	}
	/**
	 * @return the traitement
	 */
	public String getTraitement() {
		return traitement;
	}
	/**
	 * @param traitement the traitement to set
	 */
	public void setTraitement(String traitement) {
		this.traitement = traitement;
	}
	

}
