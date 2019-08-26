package com.gpro.consulting.tiers.gpao.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticriterePersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechechePersonnelValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IPersonnelService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(PersonnelValue personnel);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public PersonnelValue getById(Long id);
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(PersonnelValue personnel);
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechechePersonnelValue rechercherMultiCritere(
			RechercheMulticriterePersonnelValue request);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<PersonnelValue> listPersonnel();
}
