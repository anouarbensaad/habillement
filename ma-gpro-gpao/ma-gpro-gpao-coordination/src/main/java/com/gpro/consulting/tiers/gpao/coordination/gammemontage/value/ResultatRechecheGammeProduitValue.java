package com.gpro.consulting.tiers.gpao.coordination.gammemontage.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */
public class ResultatRechecheGammeProduitValue {

	private Long nombreResultaRechercher;
	
	private Set<ResultatRechecheGammeProduitElementValue> list = new TreeSet <ResultatRechecheGammeProduitElementValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public Set<ResultatRechecheGammeProduitElementValue> getList() {
		return list;
	}

	public void setList(Set<ResultatRechecheGammeProduitElementValue> list) {
		this.list = list;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}


	
}
