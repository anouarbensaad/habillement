package com.gpro.consulting.tiers.gpao.rest.stockfini.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.gpro.consulting.tiers.gpao.coordination.value.DetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereDetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereDetailOfValue;


import com.gpro.consulting.tiers.gpao.rest.stockfini.IDetailOfRest;


import com.gpro.consulting.tiers.gpao.service.stockfini.IDetailOfService;

/**
 * @author toshiba
 *
 */
@Controller
@RequestMapping("/detailOf")
public class DetailOfRestImpl implements IDetailOfRest {

	private static final Logger logger = LoggerFactory.getLogger(DetailOfRestImpl.class);

	@Autowired
	private IDetailOfService detailOfService;



	
	
	/************* get detail ordre Fabrication By ID *************/
	/**
	 * recherche par Id d'un ordre de Fabrication
	 * 
	 * @param id
	 * @return id
	 */
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DetailOfValue getDetailOf(@PathVariable Long id) {

		
		return detailOfService.rechercheDetailOfParId(id);
	}





	/**
	 * Modification detail Ordre de Fabrication dans la BD
	 * 
	 * @param pDetailOfValue
	 * @return Id
	 */
	@RequestMapping(value = "/modifierDetailOf", method = RequestMethod.POST)
	public @ResponseBody String modifierDetailOf(@RequestBody DetailOfValue pDetailOfValue) {
		return this.detailOfService.modifierDetailOf(pDetailOfValue);
	}



	/**
	 * Méthode de recherche multicritères
	 * @param RechercheMulticritereDetailOfValue
	 * @return ResultatMulticritereDetailOfValue
	 */
	@RequestMapping(value = "/rechercheDetailOfMulticritere", method = RequestMethod.POST)
	public @ResponseBody ResultatMulticritereDetailOfValue rechercherDetailOfMultiCritere(
		   @RequestBody final RechercheMulticritereDetailOfValue request) {

		
		return detailOfService.rechercherDetailOfMultiCritere(request);
	}

	



}
