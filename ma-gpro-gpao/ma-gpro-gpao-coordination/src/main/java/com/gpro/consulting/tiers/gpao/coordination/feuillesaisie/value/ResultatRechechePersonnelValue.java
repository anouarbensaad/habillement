package com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Ameni Berrich
 *
 */
public class ResultatRechechePersonnelValue {

	private Long nombreResultaRechercher;
	
	private Set<PersonnelElementValue> list = new TreeSet <PersonnelElementValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<PersonnelElementValue> getList() {
		return list;
	}

	public void setList(Set<PersonnelElementValue> list) {
		this.list = list;
	}

}
