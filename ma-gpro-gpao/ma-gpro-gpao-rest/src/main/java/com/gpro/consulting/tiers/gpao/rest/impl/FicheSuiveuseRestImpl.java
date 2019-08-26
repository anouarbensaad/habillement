package com.gpro.consulting.tiers.gpao.rest.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.vue.FicheSuiveuseVue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.service.IFicheSuiveuseService;
import com.gpro.consulting.tiers.gpao.service.IOrdreFabricationService;

/**
 * Fiche Suiveuse Controller
 * 
 * @author Wahid Gazzah
 * @since 20/05/2016
 *
 */

@RestController
@RequestMapping("/ficheSuiveuse")
public class FicheSuiveuseRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(FicheSuiveuseRestImpl.class);
	
	@Autowired
	private IFicheSuiveuseService ficheSuiveuseService;
	
	@Autowired
	private IOrdreFabricationService ordreFabricationService;
	
	
	@RequestMapping(value = "/getByOrdreFabricationId:{ordreFabricationId}", method = RequestMethod.GET, produces = "application/json")
	public FicheSuiveuseVue getFicheSuiveuse(@PathVariable Long ordreFabricationId) {
		  
		//LOGGER.info("getFicheSuiveuse: Delegating request to service layer.");
		
		return ficheSuiveuseService.getByOrdreFabricationId(ordreFabricationId);
	}
	

	@RequestMapping(value = "/getByOrdreFabricationNum:{ordreFabricationId}", method = RequestMethod.GET, produces = "application/json")
	public FicheSuiveuseVue getFicheSuiveuseByNum(@PathVariable String ordreFabricationId) {
		  
		Long of=null;  
		
		if (ordreFabricationId!=null ){
			  OrdreFabricationValue ordre=ordreFabricationService.getByNumero(ordreFabricationId);
			  
			  if (ordre!=null)
				  of=ordre.getId();
			  
		  }
		
		
		//LOGGER.info("getFicheSuiveuse: Delegating request to service layer.");
		
		return ficheSuiveuseService.getByOrdreFabricationId(of);
	}
	
	
}
