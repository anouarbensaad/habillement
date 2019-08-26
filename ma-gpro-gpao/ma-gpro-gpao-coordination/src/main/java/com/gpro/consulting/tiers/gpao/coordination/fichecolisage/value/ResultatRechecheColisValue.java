package com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */
public class ResultatRechecheColisValue {
	
	private Long nombreResultaRechercher;
	
	private Set<ResultatRechecheColisElementValue> list = new TreeSet <ResultatRechecheColisElementValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<ResultatRechecheColisElementValue> getList() {
		return list;
	}

	public void setList(Set<ResultatRechecheColisElementValue> list) {
		this.list = list;
	}


	
	

}
