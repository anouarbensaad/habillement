package com.gpro.consulting.tiers.gpao.coordination.abc.value;

import java.util.Calendar;

public class RechercheRechecheProductionValue {

	private Calendar dateSaisieTo;
	private Calendar dateSaisieFrom;
	private Long chaineId;

	public Calendar getDateSaisieTo() {
		return dateSaisieTo;
	}

	public void setDateSaisieTo(Calendar dateSaisieTo) {
		this.dateSaisieTo = dateSaisieTo;
	}

	public Calendar getDateSaisieFrom() {
		return dateSaisieFrom;
	}

	public void setDateSaisieFrom(Calendar dateSaisieFrom) {
		this.dateSaisieFrom = dateSaisieFrom;
	}

	public Long getChaineId() {
		return chaineId;
	}

	public void setChaineId(Long chaineId) {
		this.chaineId = chaineId;
	}

}
