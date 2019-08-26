package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ElementGammeOfValue;
import com.gpro.consulting.tiers.gpao.domaine.IElementGammeOfDomaine;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.IElementGammeOfPersistance;

/**
 * implementation of {@link IElementGammeOfDomaine}
 * 
 * @author Wahid Gazzah
 * @since 30/07/2016
 *
 */

@Component
public class ElementGammeOfDomaineImpl implements IElementGammeOfDomaine{

	@Autowired
	private IElementGammeOfPersistance elementGammeOfPersistance;
	
	
	@Override
	public List<ElementGammeOfValue> getListFromCriteria(Long ofId,	String operationCode) {
		
		return elementGammeOfPersistance.getListFromCriteria(ofId, operationCode);
	}

}
