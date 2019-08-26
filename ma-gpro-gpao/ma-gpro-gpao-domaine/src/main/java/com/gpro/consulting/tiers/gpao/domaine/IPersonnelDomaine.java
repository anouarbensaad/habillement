package com.gpro.consulting.tiers.gpao.domaine;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticriterePersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechechePersonnelValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IPersonnelDomaine {
	
	public String create(PersonnelValue personnel);

	public PersonnelValue getById(Long id);

	public String update(PersonnelValue personnel);

	public void delete(Long id);

	public ResultatRechechePersonnelValue rechercherMultiCritere(
			RechercheMulticriterePersonnelValue request);

	public List<PersonnelValue> listPersonnel();
}