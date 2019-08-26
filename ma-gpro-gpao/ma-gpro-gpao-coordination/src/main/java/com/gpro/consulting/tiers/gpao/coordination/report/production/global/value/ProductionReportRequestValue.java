package com.gpro.consulting.tiers.gpao.coordination.report.production.global.value;

import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

/**
 * Commun request used for ProductionGlobale and ProductionChaine Report
 * 
 * @author Wahid Gazzah
 * @since 10/08/2016
 *
 */
public class ProductionReportRequestValue {

	private Calendar dateMin;
	private Calendar dateMax;
	private Long chaineId;
	
	private Set<ProductionGlobalElementReportValue> list = new TreeSet<ProductionGlobalElementReportValue>();
	
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
	public Long getChaineId() {
		return chaineId;
	}
	public void setChaineId(Long chaineId) {
		this.chaineId = chaineId;
	}
	
	
}
