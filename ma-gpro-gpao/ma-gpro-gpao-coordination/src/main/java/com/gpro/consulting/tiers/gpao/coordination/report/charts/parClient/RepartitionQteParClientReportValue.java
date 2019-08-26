package com.gpro.consulting.tiers.gpao.coordination.report.charts.parClient;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RepartitionQteParClientReportValue {

	private InputStream reportStream;
	private HashMap<String, Object> params;
	private JRBeanCollectionDataSource jRBeanCollectionDataSource;
	private String fileName;

	private Calendar dateMin;
	private Calendar dateMax;
	private String abreviationClient;

	private List<RepartitionQteParClientElementValue> qteParClientReportElementValueList = new ArrayList<RepartitionQteParClientElementValue>();
	

	public String getAbreviationClient() {
		return abreviationClient;
	}

	public void setAbreviationClient(String abreviationClient) {
		this.abreviationClient = abreviationClient;
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

	public Calendar getDateMin() {
		return dateMin;
	}

	public void setDateMin(Calendar dateMin) {
		this.dateMin = dateMin;
	}

	public Calendar getDateMax() {
		return dateMax;
	}

	public void setDateMax(Calendar dateMax) {
		this.dateMax = dateMax;
	}

	public List<RepartitionQteParClientElementValue> getQteParClientReportElementValueList() {
		return qteParClientReportElementValueList;
	}

	public void setQteParClientReportElementValueList(
			List<RepartitionQteParClientElementValue> qteParClientReportElementValueList) {
		this.qteParClientReportElementValueList = qteParClientReportElementValueList;
	}

}
