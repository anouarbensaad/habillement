package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticriterePersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechechePersonnelValue;
import com.gpro.consulting.tiers.gpao.domaine.IPersonnelDomaine;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IPersonnelPersistance;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class PersonnelDomaineImpl implements IPersonnelDomaine{

	@Autowired
	IPersonnelPersistance personnelPersistance;
	
	@Override
	public String create(PersonnelValue personnel) {
		return personnelPersistance.create(personnel);
	}

	@Override
	public PersonnelValue getById(Long id) {
		return personnelPersistance.getById(id);
	}

	@Override
	public String update(PersonnelValue personnel) {
		return personnelPersistance.update(personnel);
	}

	@Override
	public void delete(Long id) {
		personnelPersistance.delete(id);
		
	}

	@Override
	public ResultatRechechePersonnelValue rechercherMultiCritere(
			RechercheMulticriterePersonnelValue request) {
		return personnelPersistance.rechercherMultiCritere(request);
	}

	@Override
	public List<PersonnelValue> listPersonnel() {
		return personnelPersistance.listPersonnel();
	}

}
