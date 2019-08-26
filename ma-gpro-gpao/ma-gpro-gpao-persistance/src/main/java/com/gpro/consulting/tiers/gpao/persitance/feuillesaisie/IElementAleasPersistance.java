package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.aleas.ElementAleasValue;

/**
 * @author Hajer AMRI
 * @since 22/02/2017
 */
public interface IElementAleasPersistance {

	public String create(ElementAleasValue elementAleas);

	public ElementAleasValue getById(Long id);

	public String update(ElementAleasValue elementAleas);

	public void delete(Long id);
	
	public List<ElementAleasValue> rechercheParFeuilleEtId(Long aleaId, Long FeuilleId);


}
