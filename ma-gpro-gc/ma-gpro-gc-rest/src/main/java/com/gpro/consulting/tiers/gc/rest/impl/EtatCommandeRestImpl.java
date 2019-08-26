package com.gpro.consulting.tiers.gc.rest.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.gc.coordination.value.EtatCommandeValue;
import com.gpro.consulting.tiers.gc.service.IEtatCommandeService;

/**
 * @author $Ameni
 *
 */
@Controller
@RequestMapping("/etatCommande")
public class EtatCommandeRestImpl {

	@Autowired
	private IEtatCommandeService etatCommandeService;

	/************* get Couleur By ID *************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody EtatCommandeValue getEtatCommande(@PathVariable Long pId) {
		
		EtatCommandeValue pEtatCommandeValue = new EtatCommandeValue();
		pEtatCommandeValue.setId(pId);
		return etatCommandeService.rechercheEtatCommandeParId(pId);
	}

	/********** all ***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<EtatCommandeValue> viewAllType() {
		
		return etatCommandeService.listeEtatCommande();
	}

	@RequestMapping(value = "/creerEtatCommande", method = RequestMethod.POST)
	public @ResponseBody String creerEtatCommande(@RequestBody EtatCommandeValue pEtatCommandeValue) {
		
		// transformation et transcodage des referenciel
		return etatCommandeService.creerEtatCommande(pEtatCommandeValue);
	}

	@RequestMapping(value = "/modifierEtatCommande", method = RequestMethod.POST)
	public @ResponseBody String modifierEtatCommande(@RequestBody EtatCommandeValue pEtatCommandeValue) {
		
		return this.etatCommandeService.modifierEtatCommande(pEtatCommandeValue);
	}

	@RequestMapping(value = "/supprimerEtatCommande:{id}", method = RequestMethod.DELETE)
	public void supprimerEtatCommande(@PathVariable("id") String id) {
		
		Long idSupp = Long.parseLong(id);
		etatCommandeService.supprimerEtatCommande(Long.valueOf(idSupp));
	}

	
}
