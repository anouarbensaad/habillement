package com.gpro.consulting.tiers.gpao.coordination.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author $Ameni
 *
 */
public class ResultatMulticritereOrdreFabricationValue {

	private Long nombreResultaRechercher;

	private Set<OrdreFabricationValue> ordreFabricationValues = new TreeSet<OrdreFabricationValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<OrdreFabricationValue> getOrdreFabricationValues() {
		return ordreFabricationValues;
	}

	public void setOrdreFabricationValues(
			Set<OrdreFabricationValue> ordreFabricationValues) {
		this.ordreFabricationValues = ordreFabricationValues;
	}

	@Override
	public String toString() {
		return "ResultatMulticritereOrdreFabricationValue [nombreResultaRechercher=" + nombreResultaRechercher
				+ ", ordreFabricationValues=" + ordreFabricationValues + "]";
	}
	
	

}
