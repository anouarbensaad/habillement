package com.gpro.consulting.tiers.gs.coordination.report.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 25/02/2016
 */

public class RequestRechecheMouvementValue {

	private Long entiteStockId;
	private Calendar dateMin;
	private Calendar dateMax;

	private Calendar dateEntree;
	private Calendar lot;
	
	
	

	public Calendar getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Calendar dateEntree) {
		this.dateEntree = dateEntree;
	}

	public Calendar getLot() {
		return lot;
	}

	public void setLot(Calendar lot) {
		this.lot = lot;
	}

	public Long getEntiteStockId() {
		return entiteStockId;
	}

	public void setEntiteStockId(Long entiteStockId) {
		this.entiteStockId = entiteStockId;
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

}
