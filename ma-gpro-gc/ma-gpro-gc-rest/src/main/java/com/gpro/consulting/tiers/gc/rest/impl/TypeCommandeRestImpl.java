package com.gpro.consulting.tiers.gc.rest.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.gc.coordination.value.TypeCommandeValue;
import com.gpro.consulting.tiers.gc.service.ITypeCommandeService;

/**
 * @author $Ameni
 *
 */
@Controller
@RequestMapping("/typeCommande")
public class TypeCommandeRestImpl {
	
	@Autowired
	private ITypeCommandeService typeCommandeService;

	/************* get Couleur By ID *************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody TypeCommandeValue getTypeCommande(@PathVariable Long pId) {
		
		return typeCommandeService.rechercheTypeCommandeParId(Long.valueOf(pId));
	}

	/********** all ***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TypeCommandeValue> viewAllType() {
		
		return typeCommandeService.listeTypeCommande();
	}

	@RequestMapping(value = "/creerTypeCommande", method = RequestMethod.POST)
	public @ResponseBody String creerTypeCommande(@RequestBody TypeCommandeValue pTypeCommandeValue) {
		
		// transformation et transcodage des referenciel
		return typeCommandeService.creerTypeCommande(pTypeCommandeValue);
	}

	@RequestMapping(value = "/modifierTypeCommande", method = RequestMethod.POST)
	public @ResponseBody String modifierTypeCommande(@RequestBody TypeCommandeValue pTypeCommandeValue) {
		
		return typeCommandeService.modifierTypeCommande(pTypeCommandeValue);
	}

	@RequestMapping(value = "/supprimerTypeCommande:{id}", method = RequestMethod.DELETE)
	public void supprimerTypeCommande(@PathVariable("id") String id) {
		
		Long idSupp = Long.parseLong(id);
		typeCommandeService.supprimerTypeCommande(Long.valueOf(idSupp));
	}

}
