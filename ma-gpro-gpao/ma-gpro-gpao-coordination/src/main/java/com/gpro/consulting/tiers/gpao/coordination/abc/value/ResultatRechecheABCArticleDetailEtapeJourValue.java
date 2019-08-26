package com.gpro.consulting.tiers.gpao.coordination.abc.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Wahid Gazzah
 * @since 03/05/2016
 *
 */
public class ResultatRechecheABCArticleDetailEtapeJourValue {
	
	private Long nombreResultaRechercher;
	
	private Set<ABCArticleDetailEtapeJourValue> list = new TreeSet <ABCArticleDetailEtapeJourValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<ABCArticleDetailEtapeJourValue> getList() {
		return list;
	}

	public void setList(Set<ABCArticleDetailEtapeJourValue> list) {
		this.list = list;
	}
	

}
