package com.gpro.consulting.tiers.gpao.rest.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.gpao.coordination.value.ChaineValue;
import com.gpro.consulting.tiers.gpao.service.IChaineService;

/**
 * @author $Ameni
 *
 */
@Controller
@RequestMapping("/chaine")
public class ChaineRestImpl {

	@Autowired
	private IChaineService chaineService;

	/************* get Couleur By ID *************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ChaineValue getChaine(@PathVariable Long pId) {

		ChaineValue pChaineValue = new ChaineValue();
		pChaineValue.setId(pId);
		return chaineService.rechercheChaineParId(pId);
	}

	/********** all ***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ChaineValue> viewAllType() {
		return chaineService.listeChaine();
	}

	@RequestMapping(value = "/creerChaine", method = RequestMethod.POST)
	public @ResponseBody String creerChaine(
			@RequestBody ChaineValue pChaineValue) {
		// transformation et transcodage des referenciel
		return chaineService.creerChaine(pChaineValue);
	}

	@RequestMapping(value = "/modifierChaine", method = RequestMethod.POST)
	public @ResponseBody String modifierChaine(
			@RequestBody ChaineValue pChaineValue) {
		return this.chaineService.modifierChaine(pChaineValue);
	}

	@RequestMapping(value = "/supprimerChaine:{id}", method = RequestMethod.DELETE)
	public void supprimerChaine(@PathVariable("id") String id) {
		Long idSupp = Long.parseLong(id);
		chaineService.supprimerChaine(Long.valueOf(idSupp));
	}

}