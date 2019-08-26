package com.gpro.consulting.tiers.gpao.rest.planning.chaine.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.ProductionJourElementValue;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.RechercheMulticritereProductionJourValue;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.ResultatRechecheProductionJourValue;
import com.gpro.consulting.tiers.gpao.service.planning.chaine.IProductionJourService;


/**
 * @author Hamdi Etteieb
 *
 */
@RestController
@RequestMapping("/productionJour")

public class ProductionJourRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(ProductionJourRestImpl.class);
	
	@Autowired 
	IProductionJourService productionJourService ;
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public ProductionJourElementValue  getById(@PathVariable Long id) {
		  
		
		return productionJourService.getById(id);
	}
	
	@RequestMapping(value = "/creer", method = RequestMethod.POST)
	public @ResponseBody String creerProductionJour(
			@RequestBody ProductionJourElementValue  pProductionJourValue) {
		return productionJourService.create(pProductionJourValue);
	}

	@RequestMapping(value = "/modifier", method = RequestMethod.PUT)
	public @ResponseBody String modifierProductionJour(
			@RequestBody ProductionJourElementValue  pProductionJourValue) {
		return this.productionJourService.update(pProductionJourValue);
	}

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheProductionJourValue rechercherMultiCritere(@RequestBody RechercheMulticritereProductionJourValue request) {
	
		return productionJourService.rechercherMultiCritere(request);
	}
	
	@RequestMapping(value = "/supprimer:{id}", method = RequestMethod.DELETE)
	public void supprimerProductionJour(@PathVariable("id") String id) {
		Long idSupp = Long.parseLong(id);
		productionJourService.delete(Long.valueOf(idSupp));
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ProductionJourElementValue > viewAllProductionJour() {
		return productionJourService.listProductionJour();
	}

}
