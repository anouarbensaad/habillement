package com.gpro.consulting.tiers.gpao.coordination.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author $Ameni
 *
 */
public class ResultatMulticritereDetailOfValue {

	private Long nombreResultaRechercher;

	private Set<DetailOfValue> detailOfValues = new TreeSet<DetailOfValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<DetailOfValue> getDetailOfValues() {
		return detailOfValues;
	}

	public void setDetailOfValues(Set<DetailOfValue> detailOfValues) {
		this.detailOfValues = detailOfValues;
	}



	
	

}
