package com.gpro.consulting.tiers.gpao.coordination.abc.value;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Wahid Gazzah
 * @since 11/05/2016
 *
 */
public class ProductionValue implements Comparable<ProductionValue>{
	
	private Long id;
	private Calendar date;
	private Long chaineId;
	private String chaineDesignation;
	private Long produitId;
	private Set<ProductionElementValue> listProductionElement = new HashSet<ProductionElementValue>();
	
	public int compareTo(ProductionValue o) {
		ProductionValue element= (ProductionValue)o;
		return (id.compareTo(element.getId()));
	}
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
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
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public Set<ProductionElementValue> getListProductionElement() {
		return listProductionElement;
	}
	public void setListProductionElement(
			Set<ProductionElementValue> listProductionElement) {
		this.listProductionElement = listProductionElement;
	}

	
}
