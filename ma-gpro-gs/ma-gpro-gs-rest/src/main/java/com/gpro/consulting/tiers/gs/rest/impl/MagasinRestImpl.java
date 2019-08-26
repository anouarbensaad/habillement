package com.gpro.consulting.tiers.gs.rest.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.gs.coordination.value.MagasinValue;
import com.gpro.consulting.tiers.gs.service.IMagasinService;


@Controller
@RequestMapping("/magasin")
public class MagasinRestImpl {

	@Autowired
	private IMagasinService magasinService;


	
	  /******************************* All magasin *********************************/ 
	  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < MagasinValue > viewAllMagasin() {
	    return magasinService.listeMagasin();
	  }
	  
	 
	  
	  /**********************  Méthode de Creation  magasin **********************/
	  @RequestMapping(value = "/creerMagasin", method = RequestMethod.POST)
	  public @ResponseBody String creerMagasin(@RequestBody MagasinValue pMagasinValue) {
	    return magasinService.creerMagasin(pMagasinValue);
	  }

	  /**********************  Méthode de modification magasin **********************/
	  @RequestMapping(value = "/modifierMagasin", method = RequestMethod.POST)
	  public @ResponseBody String modifierMagasin(@RequestBody MagasinValue pMagasinValue) {
	    return magasinService.modifierMagasin(pMagasinValue);
	  }
	  
	  /**********************  Méthode de Suppression  magasin **********************/
	  @RequestMapping(value = "/supprimerMagasin:{id}", method = RequestMethod.DELETE)
	  public void supprimerEmplacement(@PathVariable("id") String id) {
		  Long idSupp= Long.parseLong(id);
		  magasinService.supprimerMagasin(idSupp);
	  }

	  
}
