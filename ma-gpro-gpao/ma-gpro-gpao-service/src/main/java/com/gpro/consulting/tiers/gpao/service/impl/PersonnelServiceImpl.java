/**
 * 
 */
package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticriterePersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechechePersonnelValue;
import com.gpro.consulting.tiers.gpao.domaine.IPersonnelDomaine;
import com.gpro.consulting.tiers.gpao.service.IPersonnelService;

/**
 * @author Ameni Berrich
 *
 */
@Service
@Transactional
public class PersonnelServiceImpl implements IPersonnelService {

	@Autowired
	IPersonnelDomaine personnelDomaine;
	
	@Override
	public String create(PersonnelValue personnel) {
		return personnelDomaine.create(personnel);
	}

	@Override
	public PersonnelValue getById(Long id) {
		return personnelDomaine.getById(id);
	}

	@Override
	public String update(PersonnelValue personnel) {
		return personnelDomaine.update(personnel);
	}

	@Override
	public void delete(Long id) {
		personnelDomaine.delete(id);		
	}

	@Override
	public ResultatRechechePersonnelValue rechercherMultiCritere(
			RechercheMulticriterePersonnelValue request) {
		return personnelDomaine.rechercherMultiCritere(request);
	}

	@Override
	public List<PersonnelValue> listPersonnel() {
		return personnelDomaine.listPersonnel();
	}

}
