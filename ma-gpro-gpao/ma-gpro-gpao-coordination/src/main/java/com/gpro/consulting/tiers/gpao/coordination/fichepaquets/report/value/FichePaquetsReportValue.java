package com.gpro.consulting.tiers.gpao.coordination.fichepaquets.report.value;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.report.value.PaquetReportValue;

/**
 * @author Wahid Gazzah
 * @since 24/05/2016
 *
 */
public class FichePaquetsReportValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;
	
	private List<PaquetReportValue> paquetsList = new ArrayList<PaquetReportValue>();
	private String NumOrdreFabrication;
	
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

	public List<PaquetReportValue> getPaquetsList() {
		return paquetsList;
	}

	public void setPaquetsList(List<PaquetReportValue> paquetsList) {
		this.paquetsList = paquetsList;
	}

	public String getNumOrdreFabrication() {
		return NumOrdreFabrication;
	}

	public void setNumOrdreFabrication(String numOrdreFabrication) {
		NumOrdreFabrication = numOrdreFabrication;
	}
}
