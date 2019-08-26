package com.gpro.consulting.tiers.gpao.rest.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.gpao.coordination.value.StatutOfValue;
import com.gpro.consulting.tiers.gpao.service.IStatutService;

/**
 * @author $Ameni
 *
 */
@Controller
@RequestMapping("/statut")
public class StatutRestImpl {

	@Autowired
	private IStatutService statutService;

	/************* get Couleur By ID *************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody StatutOfValue getStatut(@PathVariable Long pId) {

		StatutOfValue pStatutOfValue = new StatutOfValue();
		pStatutOfValue.setId(pId);
		return statutService.rechercheStatutParId(pId);
	}

	/********** all ***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<StatutOfValue> viewAllType() {
		return statutService.listeStatut();
	}

	@RequestMapping(value = "/creerStatut", method = RequestMethod.POST)
	public @ResponseBody String creerStatut(
			@RequestBody StatutOfValue pStatutOfValue) {
		// transformation et transcodage des referenciel
		return statutService.creerStatut(pStatutOfValue);
	}

	@RequestMapping(value = "/modifierStatut", method = RequestMethod.POST)
	public @ResponseBody String modifierStatut(
			@RequestBody StatutOfValue pStatutOfValue) {
		return this.statutService.modifierStatut(pStatutOfValue);
	}

	@RequestMapping(value = "/supprimerStatut:{id}", method = RequestMethod.DELETE)
	public void supprimerStatut(@PathVariable("id") String id) {
		Long idSupp = Long.parseLong(id);
		statutService.supprimerStatut(Long.valueOf(idSupp));
	}
	
}
