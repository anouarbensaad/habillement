package com.gpro.consulting.tiers.gpao.coordination.abc.value;

import java.util.Set;
import java.util.TreeSet;

public class ResultatRechecheProductionValue {

	private Long nombreResultaRechercher;

	private Set<ProductionValue> list = new TreeSet<ProductionValue>();

	public Set<ProductionValue> getList() {
		return list;
	}

	public void setList(Set<ProductionValue> list) {
		this.list = list;
	}

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}
	
	
}
