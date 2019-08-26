package com.gpro.consulting.tiers.gpao.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpro.consulting.tiers.gpao.service.ICauseVariationService;


@Controller
@RequestMapping("/causevariation")
public class CauseVariationRestImpl {

	@Autowired 
	ICauseVariationService CauseVariationService ;

	public ICauseVariationService getCauseVariationService() {
		return CauseVariationService;
	}

	public void setCauseVariationService(
			ICauseVariationService causeVariationService) {
		CauseVariationService = causeVariationService;
	}
	
	
}
