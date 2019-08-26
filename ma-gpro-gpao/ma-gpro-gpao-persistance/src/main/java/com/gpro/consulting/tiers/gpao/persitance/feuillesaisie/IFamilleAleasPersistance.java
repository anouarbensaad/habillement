package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie;

import com.gpro.consulting.tiers.gpao.coordination.aleas.FamilleAleasValue;

/**
 * @author Hajer AMRI
 * @since 22/02/2017
 */
public interface IFamilleAleasPersistance {

	public String create(FamilleAleasValue feuilleSaisie);

	public FamilleAleasValue getById(Long id);

	public String update(FamilleAleasValue feuilleSaisie);

	public void delete(Long id);

}
