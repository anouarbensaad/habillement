package com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 25/05/2016
 */
public class ResultatRechecheFeuilleSaisieValue {

	private Long nombreResultaRechercher;
	
	private Set<ResultatRechecheFeuilleSaisieElementValue> list = new TreeSet <ResultatRechecheFeuilleSaisieElementValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<ResultatRechecheFeuilleSaisieElementValue> getList() {
		return list;
	}

	public void setList(Set<ResultatRechecheFeuilleSaisieElementValue> list) {
		this.list = list;
	}

}
