package com.gpro.consulting.tiers.gpao.coordination.report.production.global.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 10/08/2016
 *
 */
public class ProductionGlobalElementReportValue implements Comparable<ProductionGlobalElementReportValue>{

	private Long chaineId;
	private String chaineDesignation;
	
	private boolean haseList;
	
	private Set<ProductionGlobalElementChaineReportValue> list = new TreeSet<ProductionGlobalElementChaineReportValue>();

	@Override
	public int compareTo(ProductionGlobalElementReportValue element) {
		return (element.getChaineId().compareTo(chaineId));
	}
	
	public Long getChaineId() {
		return chaineId;
	}

	public void setChaineId(Long chaineId) {
		this.chaineId = chaineId;
	}

	public String getChaineDesignation() {
		return chaineDesignation;
	}

	public void setChaineDesignation(String chaineDesignation) {
		this.chaineDesignation = chaineDesignation;
	}

	public boolean haseList() {
		
		if(list.size() > 0)
			return true;
		
		else
			return false;
	}

	public Set<ProductionGlobalElementChaineReportValue> getList() {
		return list;
	}

	public void setList(Set<ProductionGlobalElementChaineReportValue> list) {
		this.list = list;
	}

	
}
