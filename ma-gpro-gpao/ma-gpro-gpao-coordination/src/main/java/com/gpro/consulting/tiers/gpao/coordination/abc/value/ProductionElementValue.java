package com.gpro.consulting.tiers.gpao.coordination.abc.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 11/05/2016
 *
 */
public class ProductionElementValue implements Comparable<ProductionElementValue>{
	
	private Long id;
	private Long productionId;
	private Long heure;
	private Long dem;
	private Long prod;
	private Double rend;
	private Calendar date;

	public int compareTo(ProductionElementValue o) {
		ProductionElementValue element= (ProductionElementValue)o;
		return (heure.compareTo(element.getHeure()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductionId() {
		return productionId;
	}

	public void setProductionId(Long productionId) {
		this.productionId = productionId;
	}

	public Long getHeure() {
		return heure;
	}

	public void setHeure(Long heure) {
		this.heure = heure;
	}

	public Long getDem() {
		return dem;
	}

	public void setDem(Long dem) {
		this.dem = dem;
	}

	public Long getProd() {
		return prod;
	}

	public void setProd(Long prod) {
		this.prod = prod;
	}

	public Double getRend() {
		return rend;
	}

	public void setRend(Double rend) {
		this.rend = rend;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
	
}
