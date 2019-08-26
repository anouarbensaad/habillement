package com.gpro.consulting.tiers.gc.rest.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.gc.coordination.value.CommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereCommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheCommandeVenteValue;
import com.gpro.consulting.tiers.gc.service.ICommandeVenteService;
import com.gpro.consulting.tiers.gc.service.IGestionnaireGCCacheService;

/**
 * @author $Ameni
 *
 */
@Controller
@RequestMapping("/commandeVente")
public class CommandeVenteRestImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandeVenteRestImpl.class);
	
	@Autowired
	private ICommandeVenteService commandeVenteService;

	@Autowired
	private IGestionnaireGCCacheService gestionnaireGCCacheService;
	
	
	@RequestMapping(value = "/getListBCReferences", method = RequestMethod.GET , produces = "application/json")
	public @ResponseBody List<String> getReferences() {
		
		return commandeVenteService.getReferences();
	}
	
	/************* get CommandeVente By ID *************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody CommandeVenteValue getCommandeVente(
			@PathVariable String id) {
		LOGGER.info("recherche d'un bon de Commande  /getId:" + id);
		Long idSupp = Long.parseLong(id);
		return commandeVenteService
				.rechercheCommandeVenteParId(Long.valueOf(idSupp));
	}

	/********** all ***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<CommandeVenteValue> viewAllType() {
		 LOGGER.info("Liste des bons de Commandes  /all:");
		 List < CommandeVenteValue > vCommandeVenteValue = commandeVenteService.listeCommandeVente();
		//Traitement : transformation de l'Id a sa propre Designation

	      for(CommandeVenteValue commande : vCommandeVenteValue){
			 
	    	  //Type, Client, Site, Etat
	    	  Map<String, String> mapA = gestionnaireGCCacheService.rechercherCommandeParId(commande.getTypeCommande_id(), commande.getPartieIntersseId(), commande.getSiteId(), commande.getEtatCommande_id());
	    	  //Type
	    	  commande.setTypeCommandeDesignation(mapA.get("type"));
	    	  //Client
	    	  commande.setPartieIntersseDesignation(mapA.get("client"));
	    	  //Site
	          commande.setSiteDesignation(mapA.get("site"));
			  //Etat
	    	  commande.setEtatCommandeDesignation(mapA.get("etat"));

		    }
		return vCommandeVenteValue; 
	}

	@RequestMapping(value = "/creerCommandeVente", method = RequestMethod.POST)
	public @ResponseBody String creerCommandeVente(
			@RequestBody CommandeVenteValue pCommandeVenteValue) {
		
		//System.out.println("----create ** ** : CommandeVenteValue"+pCommandeVenteValue);
		// transformation et transcodage des referenciel
		return commandeVenteService.creerCommandeVente(pCommandeVenteValue);
	}

	@RequestMapping(value = "/modifierCommandeVente", method = RequestMethod.POST)
	public @ResponseBody String modifierCommandeVente(
			@RequestBody CommandeVenteValue pCommandeVenteValue) {
		return this.commandeVenteService
				.modifierCommandeVente(pCommandeVenteValue);
	}

	@RequestMapping(value = "/supprimerCommandeVente:{id}", method = RequestMethod.DELETE)
	public void supprimerCommandeVente(@PathVariable("id") String id) {
		Long idSupp = Long.parseLong(id);
		commandeVenteService.supprimerCommandeVente(Long.valueOf(idSupp));
	}

	@RequestMapping(value = "/rechercheCommandeVenteMulticritere", method = RequestMethod.POST)
	public @ResponseBody ResultatRechecheCommandeVenteValue rechercherCommandeValueMultiCritere(
			@RequestBody final RechercheMulticritereCommandeVenteValue pRechercheMultiCritere) {
		
		LOGGER.info("Recherche Multicritères des bons de Commandes  /rechercheCommandeVenteMulticritere:");
		// Création des critères de recherche
		ResultatRechecheCommandeVenteValue vResultatRecherche = commandeVenteService
				.rechercherCommandeVenteMultiCritere(pRechercheMultiCritere);

		//Traitement : transformation de l'Id a sa propre Designation

	      for(CommandeVenteValue commande : vResultatRecherche.getCommandeVenteValues()){
			 
	    	  //Type, Client, Site, Etat
	    	  Map<String, String> mapA = gestionnaireGCCacheService.rechercherCommandeParId(commande.getTypeCommande_id(), commande.getPartieIntersseId(), commande.getSiteId(), commande.getEtatCommande_id());
	    	  //Type
	    	  commande.setTypeCommandeDesignation(mapA.get("type"));
	    	  //Client
	    	  commande.setPartieIntersseDesignation(mapA.get("client"));
	    	  //Site
	    	    commande.setSiteDesignation(mapA.get("site"));
			  //Etat
	    	  commande.setEtatCommandeDesignation(mapA.get("etat"));

		    }
		return vResultatRecherche;
	}

}
