package com.gpro.consulting.tiers.gpao.rest.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.FicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereFicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheFicheColisageValue;
import com.gpro.consulting.tiers.gpao.service.IColisService;
import com.gpro.consulting.tiers.gpao.service.IFicheColisageService;

/**
 * Fiche Colisage Controller
 * 
 * @author Hamdi Etteieb
 * @since 06/12/2017
 *
 */

@RestController
@RequestMapping("/colisage")
public class ColisRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(ColisRestImpl.class);
	
	@Autowired
	private IColisService colisageService;
	
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheColisValue rechercherMultiCritere(@RequestBody RechercheMulticritereColisValue request) {
		
		ResultatRechecheColisValue result = colisageService.rechercherMultiCritere(request);
	return result;
	
	}
	
		
}
