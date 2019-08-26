package com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value;

import java.util.Calendar;

public class DateValue {

	
	private Calendar dateFin ;    
	private Calendar dateLibre;
	public Calendar getDateFin() {
		return dateFin;
	}
	public void setDateFin(Calendar dateFin) {
		this.dateFin = dateFin;
	}
	public Calendar getDateLibre() {
		return dateLibre;
	}
	public void setDateLibre(Calendar dateLibre) {
		this.dateLibre = dateLibre;
	}
	@Override
	public String toString() {
		return "DateValue [dateFin=" + dateFin + ", dateLibre=" + dateLibre
				+ "]";
	}
	
	
	
	

	
}
