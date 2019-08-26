package com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value;

import java.util.Set;
import java.util.TreeSet;


public class ResultatRechecheProductionJourValue {

private Long nombreResultaRechercher;
	
	private Set<ProductionJourElementValue> list = new TreeSet<ProductionJourElementValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}
	public Set<ProductionJourElementValue> getList() {
		return list;
	}

	public void setList(Set<ProductionJourElementValue> list) {
		this.list = list;
	}

}
