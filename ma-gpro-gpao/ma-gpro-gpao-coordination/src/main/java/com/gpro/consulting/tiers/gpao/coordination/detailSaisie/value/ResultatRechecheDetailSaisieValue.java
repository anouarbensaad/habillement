package com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value;

import java.util.Set;
import java.util.TreeSet;

public class ResultatRechecheDetailSaisieValue {

private Long nombreResultaRechercher;
	
	private Set<DetailSaisieElementValue> list = new TreeSet <DetailSaisieElementValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<DetailSaisieElementValue> getList() {
		return list;
	}

	public void setList(Set<DetailSaisieElementValue> list) {
		this.list = list;
	}

}
