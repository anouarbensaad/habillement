package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.domaine.ISaisieElementDomaine;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.ISaisieElementPersistance;

/**
 * implementation of {@link ISaisieElementDomaine}
 * 
 * @author Wahid Gazzah
 * @since 01/08/2016
 *
 */

@Component
public class SaisieElementDomaineImpl implements ISaisieElementDomaine{

	@Autowired
	private ISaisieElementPersistance saisieElementPersistance;
	
	@Override
	public List<Long> getByElementGammeId(Long elementGammeId) {
		
		return saisieElementPersistance.getByElementGammeId(elementGammeId);
	}

}
