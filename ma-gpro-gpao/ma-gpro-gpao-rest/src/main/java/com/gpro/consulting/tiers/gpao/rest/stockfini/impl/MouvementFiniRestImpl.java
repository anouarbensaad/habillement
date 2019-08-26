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

import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.MouvementFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.RechercheMulticritereMouvementFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.ResultatMulticritereMouvementFiniValue;
import com.gpro.consulting.tiers.gpao.rest.stockfini.IMouvementFiniRest;
import com.gpro.consulting.tiers.gpao.service.stockfini.IMouvementFiniService;


/**
 * @author Samer Hassen
 *
 */
@Controller
@RequestMapping("/mouvementFini")
public class MouvementFiniRestImpl implements IMouvementFiniRest {

	private static final Logger logger = LoggerFactory.getLogger(MouvementFiniRestImpl.class);

	@Autowired
	private IMouvementFiniService mouvementFiniService;



	
	
	/************* get detail ordre Fabrication By ID *************/
	/**
	 * recherche par Id d'un ordre de Fabrication
	 * 
	 * @param id
	 * @return id
	 */
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody MouvementFiniValue getMouvementFini(@PathVariable Long id) {

		
		return mouvementFiniService.findMouvementFiniParId(id);
	}





	/**
	 * Modification detail Ordre de Fabrication dans la BD
	 * 
	 * @param pMouvementFiniValue
	 * @return Id
	 */
	@RequestMapping(value = "/creerMouvementFini", method = RequestMethod.POST)
	public @ResponseBody String creerMouvementFini(@RequestBody MouvementFiniValue pMouvementFiniValue) {
		return this.mouvementFiniService.createMouvementFini(pMouvementFiniValue);
	}


	@RequestMapping(value = "/supprimer:{id}", method = RequestMethod.DELETE)
	public void supprimerMouvementFini(@PathVariable("id") String id) {
		Long idSupp = Long.parseLong(id);
		mouvementFiniService.deleteMouvementFini(Long.valueOf(idSupp)); 
	}

	/**
	 * Méthode de recherche multicritères
	 * @param RechercheMulticritereMouvementFiniValue
	 * @return ResultatMulticritereMouvementFiniValue
	 */
	@RequestMapping(value = "/rechercheMouvementFiniMulticritere", method = RequestMethod.POST)
	public @ResponseBody ResultatMulticritereMouvementFiniValue rechercherMouvementFiniMultiCritere(
		   @RequestBody final RechercheMulticritereMouvementFiniValue request) {

		
		return mouvementFiniService.rechercherMouvementFiniMultiCritere(request);
	}

	



}
