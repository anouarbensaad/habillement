package com.gpro.consulting.tiers.commun.rest.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.StandardTailleValue;
import com.gpro.consulting.tiers.commun.service.IStandardTailleService;

@Controller
@RequestMapping("/standardTaille")
public class StandardTailleRestImpl {

	@Autowired
	private IStandardTailleService ebStandardTailleService;
	
	List<StandardTailleValue> listeType=new ArrayList<>();
	
	//conctructeur
			public StandardTailleRestImpl(){
				
			}
	/*************get StandardTaille By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody StandardTailleValue getStandardTaille(@PathVariable Long id) {
		StandardTailleValue pStandardTailleValue=new StandardTailleValue();
		pStandardTailleValue.setId(id);
		return  ebStandardTailleService.rechercheStandardTailleParId(pStandardTailleValue);
	}

	/**********all StdTaille***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<StandardTailleValue> viewAllType(){
		return this.listeType= ebStandardTailleService.listeStandardTaille();
    }
	/*********Creer stdTaille ********/
	 @RequestMapping(value = "/creerStandardTailleProduit", method = RequestMethod.POST)
	 public @ResponseBody String creerStandardTailleProduit(@RequestBody StandardTailleValue pStdTailleProduitValue) {
	      //transformation et transcodage des referenciel 
	 return ebStandardTailleService.creerStandardTaille(pStdTailleProduitValue);
	 }
	  
	 /*********Modifier stdTaille ********/
	 @RequestMapping(value = "/modifierStandardTailleProduit", method = RequestMethod.POST)
	 public @ResponseBody String modifierStandardTailleProduit(@RequestBody StandardTailleValue pStdTailleProduitValue) {
	    return this.ebStandardTailleService.modifierStandardTaille(pStdTailleProduitValue);
	 }
	 /*********Supprimer stdTaille ********/
	 @RequestMapping(value = "/supprimerStandarTailleProduit:{id}", method = RequestMethod.DELETE)
	 public void supprimerStandardTailleProduit(@PathVariable("id") String id) {
	 Long idSupp= Long.parseLong(id);
	 ebStandardTailleService.supprimerStandardTaille(idSupp);
	  }
	/******** GETTER & SETTER********/
	public IStandardTailleService getEbStandardTailleService() {
		return ebStandardTailleService;
	}

	public void setEbStandardTailleService(IStandardTailleService ebStandardTailleService) {
		this.ebStandardTailleService = ebStandardTailleService;
	}

	public List<StandardTailleValue> getListeType() {
		return listeType;
	}

	public void setListeType(List<StandardTailleValue> listeType) {
		this.listeType = listeType;
	}
	
	
}

