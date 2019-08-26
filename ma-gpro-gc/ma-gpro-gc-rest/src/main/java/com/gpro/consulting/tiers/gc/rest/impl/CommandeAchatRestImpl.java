package com.gpro.consulting.tiers.gc.rest.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.gc.coordination.value.CommandeAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereCommandeAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheCommandeAchatValue;
import com.gpro.consulting.tiers.gc.service.ICommandeAchatService;

/**
 * @author $Ameni
 *
 */
@Controller
@RequestMapping("/commandeAchat")
public class CommandeAchatRestImpl {
	
	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandeAchatRestImpl.class);

	@Autowired
	private ICommandeAchatService commandeAchatService;

	/************* get CommandeAchat By ID *************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody CommandeAchatValue getCommandeAchat(
			@PathVariable String id) {
		LOGGER.info("recherche d'un bon de Commande d'Achat  /getId:" + id);
		Long idSupp = Long.parseLong(id);
		return commandeAchatService.rechercheCommandeAchatParId(Long.valueOf(idSupp));
	}
	
	/********** all ***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<CommandeAchatValue> viewAllType() {
		 LOGGER.info("Liste des bons de Commandes d'Achats  /all:");
		 List < CommandeAchatValue > vCommandeAchatValue = commandeAchatService.listeCommandeAchat();
		
		return vCommandeAchatValue; 
	}

	@RequestMapping(value = "/creerCommandeAchat", method = RequestMethod.POST)
	public @ResponseBody String creerCommandeAchat(@RequestBody CommandeAchatValue pCommandeAchatValue) {
		// transformation et transcodage des referenciel
		return commandeAchatService.creerCommandeAchat(pCommandeAchatValue);
	}

	@RequestMapping(value = "/modifierCommandeAchat", method = RequestMethod.POST)
	public @ResponseBody String modifierCommandeAchat(@RequestBody CommandeAchatValue pCommandeAchatValue) {
		return this.commandeAchatService
				.modifierCommandeAchat(pCommandeAchatValue);
	}

	@RequestMapping(value = "/supprimerCommandeAchat:{id}", method = RequestMethod.DELETE)
	public void supprimerCommandeAchat(@PathVariable("id") String id) {
		
		Long idSupp = Long.parseLong(id);
		commandeAchatService.supprimerCommandeAchat(Long.valueOf(idSupp));
	}
	
	@RequestMapping(value = "/rechercheCommandeAchatMulticritere", method = RequestMethod.POST)
	public @ResponseBody ResultatRechecheCommandeAchatValue rechercherCommandeValueMultiCritere(
			@RequestBody final RechercheMulticritereCommandeAchatValue pRechercheMultiCritere) {
		
		LOGGER.info("Recherche Multicritères des bons de Commandes d'Achat  /rechercheCommandeAchatMulticritere:");
		// Création des critères de recherche
		ResultatRechecheCommandeAchatValue vResultatRecherche = commandeAchatService
				.rechercherCommandeAchatMultiCritere(pRechercheMultiCritere);

		/*//Traitement : transformation de l'Id a sa propre Designation

	      for(CommandeVenteValue commande : vResultatRecherche.getCommandeVenteValues()){
			 
	    	  //Type, Client, Site, Etat
	    	  Map<String, String> mapA = gestionnaireCacheService.rechercherCommandeParId(commande.getTypeCommande_id(), commande.getPartieIntersseId(), commande.getSiteId(), commande.getEtatCommande_id());
	    	  //Type
	    	  commande.setTypeCommandeDesignation(mapA.get("type"));
	    	  //Client
	    	  commande.setPartieIntersseDesignation(mapA.get("client"));
	    	  //Site
	    	    commande.setSiteDesignation(mapA.get("site"));
			  //Etat
	    	  commande.setEtatCommandeDesignation(mapA.get("etat"));

		    }*/
		return vResultatRecherche;
	}

}
