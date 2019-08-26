package com.gpro.consulting.tiers.gpao.coordination.recapproduction.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 21/06/2016
 *
 */
public class ResultatRechecheRecapProductionValue {

	private Long nombreResultaRechercher;
	
	private Set<RecapProductionValue> list = new TreeSet <RecapProductionValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<RecapProductionValue> getList() {
		return list;
	}

	public void setList(Set<RecapProductionValue> list) {
		this.list = list;
	}

}
