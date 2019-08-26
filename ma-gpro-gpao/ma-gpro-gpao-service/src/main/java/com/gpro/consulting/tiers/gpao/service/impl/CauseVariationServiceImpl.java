package com.gpro.consulting.tiers.gpao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.domaine.ICauseVariationDomaine;
import com.gpro.consulting.tiers.gpao.service.ICauseVariationService;

@Service
@Transactional(rollbackFor = Exception.class)
public class CauseVariationServiceImpl implements ICauseVariationService {
	
	@Autowired
	ICauseVariationDomaine  CauseVariationDomaine ;

	public ICauseVariationDomaine getCauseVariationDomaine() {
		return CauseVariationDomaine;
	}

	public void setCauseVariationDomaine(
			ICauseVariationDomaine causeVariationDomaine) {
		CauseVariationDomaine = causeVariationDomaine;
	}

}
