package com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.report.value;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Wahid Gazzah
 * @since 20/05/2016
 *
 */
public class FicheSuiveuseReportValue {
	
	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private Set<FicheSuiveuseElementReportValue> elementsList = new TreeSet<FicheSuiveuseElementReportValue>();
	
	private Set<FicheSuiveuseElementReportValue> leftElementsList = new TreeSet<FicheSuiveuseElementReportValue>();
	private Set<FicheSuiveuseElementReportValue> rightElementsList = new TreeSet<FicheSuiveuseElementReportValue>();
	
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
	public Set<FicheSuiveuseElementReportValue> getElementsList() {
		return elementsList;
	}
	public void setElementsList(Set<FicheSuiveuseElementReportValue> elementsList) {
		this.elementsList = elementsList;
	}
	public Set<FicheSuiveuseElementReportValue> getLeftElementsList() {
		return leftElementsList;
	}
	public void setLeftElementsList(
			Set<FicheSuiveuseElementReportValue> leftElementsList) {
		this.leftElementsList = leftElementsList;
	}
	public Set<FicheSuiveuseElementReportValue> getRightElementsList() {
		return rightElementsList;
	}
	public void setRightElementsList(
			Set<FicheSuiveuseElementReportValue> rightElementsList) {
		this.rightElementsList = rightElementsList;
	}

}
