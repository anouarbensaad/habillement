package com.gpro.consulting.tiers.gpao.coordination.gammeof.value;

import java.util.Calendar;

/**
 * @author Ameni Berrich
 *
 */
public class JourProductionAvancementOFValue {
	
	private Calendar date;
	private Long qtePrdteParJour;
	
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public Long getQtePrdteParJour() {
		return qtePrdteParJour;
	}
	public void setQtePrdteParJour(Long qtePrdteParJour) {
		this.qtePrdteParJour = qtePrdteParJour;
	}
	
}
