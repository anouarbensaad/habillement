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
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereFicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheFicheColisageValue;
import com.gpro.consulting.tiers.gpao.service.IFicheColisageService;

/**
 * Fiche Colisage Controller
 * 
 * @author Hamdi Etteieb
 * @since 06/12/2017
 *
 */

@RestController
@RequestMapping("/ficheColisage")
public class FicheColisageRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(FicheColisageRestImpl.class);
	
	@Autowired
	private IFicheColisageService ficheColisageService;
	
	@RequestMapping(value = "/getPaquetListByOfIdAndQuantiteColis", method = RequestMethod.GET, produces = "application/json")
	public Set<ColisValue> getPaquetListByOfIdAndQuantitePaquet(
			@RequestParam(value="ordreFabricationId") Long ordreFabricationId,
			@RequestParam(value="quantitePaquet") Long quantitePaquet) {
		
		return ficheColisageService.getPaquetListByOfIdAndQuantiteColis(ordreFabricationId, quantitePaquet);
	}

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheFicheColisageValue rechercherMultiCritere(@RequestBody RechercheMulticritereFicheColisageValue request) {
		
		ResultatRechecheFicheColisageValue result = ficheColisageService.rechercherMultiCritere(request);
	return result;
	
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody FicheColisageValue ficheColisageValue) {
		return ficheColisageService.create(ficheColisageValue);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public FicheColisageValue getById(@PathVariable Long id) {
		FicheColisageValue ficheColisage = ficheColisageService.getById(id);
		
		return ficheColisage;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody FicheColisageValue ficheColisageValue) {
	    
		return ficheColisageService.update(ficheColisageValue);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
	  
		ficheColisageService.delete(id);
	}
	
}
