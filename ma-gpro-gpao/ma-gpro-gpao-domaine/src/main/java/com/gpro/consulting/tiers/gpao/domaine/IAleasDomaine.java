package com.gpro.consulting.tiers.gpao.domaine;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.aleas.AleasValue;

public interface IAleasDomaine {

	public String create(AleasValue feuilleSaisie);

	public AleasValue getById(Long id);

	public String update(AleasValue feuilleSaisie);

	public void delete(Long id);
	
	public List<AleasValue> getAllAleas();

}
