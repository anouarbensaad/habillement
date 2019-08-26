package com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.globalOF;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Zeineb Medimagh
 *
 */
public class ChaineGlobalOFReportValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private Calendar dateIntroductionMin;
	private Calendar dateIntroductionMax;
	
	private String chaineCoupe;
	private String chaine1;
	private String chaine2;
	private String chaine3;
	private String chaine4;
	private String chaine5;
	private String chaine6;
	
	private List<ChaineGlobalOFElementReportValue> chaineCoupeElementsList = new ArrayList<ChaineGlobalOFElementReportValue>();
	private List<ChaineGlobalOFElementReportValue> chaine1ElementsList = new ArrayList<ChaineGlobalOFElementReportValue>();
	private List<ChaineGlobalOFElementReportValue> chaine2ElementsList = new ArrayList<ChaineGlobalOFElementReportValue>();
	private List<ChaineGlobalOFElementReportValue> chaine3ElementsList = new ArrayList<ChaineGlobalOFElementReportValue>();
	private List<ChaineGlobalOFElementReportValue> chaine4ElementsList = new ArrayList<ChaineGlobalOFElementReportValue>();
	private List<ChaineGlobalOFElementReportValue> chaine5ElementsList = new ArrayList<ChaineGlobalOFElementReportValue>();
	private List<ChaineGlobalOFElementReportValue> chaine6ElementsList = new ArrayList<ChaineGlobalOFElementReportValue>();
	
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

	public void setJRBeanCollectionDataSource(JRBeanCollectionDataSource jRBeanCollectionDataSource) {
		this.jRBeanCollectionDataSource = jRBeanCollectionDataSource;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Calendar getDateIntroductionMin() {
		return dateIntroductionMin;
	}

	public void setDateIntroductionMin(Calendar dateIntroductionMin) {
		this.dateIntroductionMin = dateIntroductionMin;
	}

	public Calendar getDateIntroductionMax() {
		return dateIntroductionMax;
	}

	public void setDateIntroductionMax(Calendar dateIntroductionMax) {
		this.dateIntroductionMax = dateIntroductionMax;
	}

	public String getChaineCoupe() {
		return chaineCoupe;
	}

	public void setChaineCoupe(String chaineCoupe) {
		this.chaineCoupe = chaineCoupe;
	}

	public String getChaine1() {
		return chaine1;
	}

	public void setChaine1(String chaine1) {
		this.chaine1 = chaine1;
	}

	public String getChaine2() {
		return chaine2;
	}

	public void setChaine2(String chaine2) {
		this.chaine2 = chaine2;
	}

	public String getChaine3() {
		return chaine3;
	}

	public void setChaine3(String chaine3) {
		this.chaine3 = chaine3;
	}

	public String getChaine4() {
		return chaine4;
	}

	public void setChaine4(String chaine4) {
		this.chaine4 = chaine4;
	}

	public String getChaine5() {
		return chaine5;
	}

	public void setChaine5(String chaine5) {
		this.chaine5 = chaine5;
	}

	public String getChaine6() {
		return chaine6;
	}

	public void setChaine6(String chaine6) {
		this.chaine6 = chaine6;
	}

	public List<ChaineGlobalOFElementReportValue> getChaineCoupeElementsList() {
		return chaineCoupeElementsList;
	}

	public void setChaineCoupeElementsList(List<ChaineGlobalOFElementReportValue> chaineCoupeElementsList) {
		this.chaineCoupeElementsList = chaineCoupeElementsList;
	}

	public List<ChaineGlobalOFElementReportValue> getChaine1ElementsList() {
		return chaine1ElementsList;
	}

	public void setChaine1ElementsList(List<ChaineGlobalOFElementReportValue> chaine1ElementsList) {
		this.chaine1ElementsList = chaine1ElementsList;
	}

	public List<ChaineGlobalOFElementReportValue> getChaine2ElementsList() {
		return chaine2ElementsList;
	}

	public void setChaine2ElementsList(List<ChaineGlobalOFElementReportValue> chaine2ElementsList) {
		this.chaine2ElementsList = chaine2ElementsList;
	}

	public List<ChaineGlobalOFElementReportValue> getChaine3ElementsList() {
		return chaine3ElementsList;
	}

	public void setChaine3ElementsList(List<ChaineGlobalOFElementReportValue> chaine3ElementsList) {
		this.chaine3ElementsList = chaine3ElementsList;
	}

	public List<ChaineGlobalOFElementReportValue> getChaine4ElementsList() {
		return chaine4ElementsList;
	}

	public void setChaine4ElementsList(List<ChaineGlobalOFElementReportValue> chaine4ElementsList) {
		this.chaine4ElementsList = chaine4ElementsList;
	}

	public List<ChaineGlobalOFElementReportValue> getChaine5ElementsList() {
		return chaine5ElementsList;
	}

	public void setChaine5ElementsList(List<ChaineGlobalOFElementReportValue> chaine5ElementsList) {
		this.chaine5ElementsList = chaine5ElementsList;
	}

	public List<ChaineGlobalOFElementReportValue> getChaine6ElementsList() {
		return chaine6ElementsList;
	}

	public void setChaine6ElementsList(List<ChaineGlobalOFElementReportValue> chaine6ElementsList) {
		this.chaine6ElementsList = chaine6ElementsList;
	}

}
