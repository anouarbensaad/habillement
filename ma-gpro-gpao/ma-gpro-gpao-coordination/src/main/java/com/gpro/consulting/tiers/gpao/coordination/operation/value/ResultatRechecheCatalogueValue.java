package com.gpro.consulting.tiers.gpao.coordination.operation.value;

import java.util.Set;
import java.util.TreeSet;

import com.gpro.consulting.tiers.commun.coordination.response.value.ResponseValue;

/**
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */
public class ResultatRechecheCatalogueValue extends ResponseValue{

	private Long nombreResultaRechercher;
	
	private Set<OperationValue> list = new TreeSet <OperationValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<OperationValue> getList() {
		return list;
	}

	public void setList(Set<OperationValue> list) {
		this.list = list;
	}
	
}
