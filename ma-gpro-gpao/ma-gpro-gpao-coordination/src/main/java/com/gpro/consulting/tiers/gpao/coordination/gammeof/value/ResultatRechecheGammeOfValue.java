package com.gpro.consulting.tiers.gpao.coordination.gammeof.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 10/05/2016
 *
 */
public class ResultatRechecheGammeOfValue {

	private Long nombreResultaRechercher;
	
	private Set<ResultatRechecheGammeOfElementValue> list = new TreeSet <ResultatRechecheGammeOfElementValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public Set<ResultatRechecheGammeOfElementValue> getList() {
		return list;
	}

	public void setList(Set<ResultatRechecheGammeOfElementValue> list) {
		this.list = list;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}


	
}
