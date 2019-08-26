package com.gpro.consulting.tiers.gpao.domaine.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.domaine.ICauseVariationDomaine;
import com.gpro.consulting.tiers.gpao.persitance.ICauseVariationPersistance;

@Component
public class CauseVariationDomaineImpl implements ICauseVariationDomaine {
	
	@Autowired
	ICauseVariationPersistance CauseVariationPersistance ;
	
	
}
