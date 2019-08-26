package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.aleas.AleasValue;

/**
 * @author Hajer AMRI
 * @since 22/02/2017
 */
public interface IAleasPersistance {

	public String create(AleasValue aleas);

	public AleasValue getById(Long id);

	public String update(AleasValue aleas);

	public void delete(Long id);

	public List<AleasValue> getAllAleas();


}
