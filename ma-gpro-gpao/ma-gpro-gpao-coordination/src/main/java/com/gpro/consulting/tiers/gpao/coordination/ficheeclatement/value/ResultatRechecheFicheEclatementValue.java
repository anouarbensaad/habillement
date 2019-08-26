package com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */
public class ResultatRechecheFicheEclatementValue {
	
	private Long nombreResultaRechercher;
	
	private Set<ResultatRechecheFicheEclatementElementValue> list = new TreeSet <ResultatRechecheFicheEclatementElementValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<ResultatRechecheFicheEclatementElementValue> getList() {
		return list;
	}

	public void setList(Set<ResultatRechecheFicheEclatementElementValue> list) {
		this.list = list;
	}
	
	

}
