package com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */
public class ResultatRechecheFicheColisageValue {
	
	private Long nombreResultaRechercher;
	
	private Set<ResultatRechecheFicheColisageElementValue> list = new TreeSet <ResultatRechecheFicheColisageElementValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<ResultatRechecheFicheColisageElementValue> getList() {
		return list;
	}

	public void setList(Set<ResultatRechecheFicheColisageElementValue> list) {
		this.list = list;
	}
	
	

}
