package com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value;

import java.util.List;
import java.util.Set;

/**
 * @author Zeineb Medimagh 
 * @since 08/06/2017
 */
public class FeuilleSaisieTRValue  {

	private Double minPresence;
	private Long chaineId;

	private List<SaisieElementTRValue> listSaisieTR;

	
	public Double getMinPresence() {
		return minPresence;
	}

	public void setMinPresence(Double minPresence) {
		this.minPresence = minPresence;
	}

	public Long getChaineId() {
		return chaineId;
	}

	public void setChaineId(Long chaineId) {
		this.chaineId = chaineId;
	}

	public List<SaisieElementTRValue> getListSaisieTR() {
		return listSaisieTR;
	}

	public void setListSaisieTR(List<SaisieElementTRValue> listSaisieTR) {
		this.listSaisieTR = listSaisieTR;
	}

}
