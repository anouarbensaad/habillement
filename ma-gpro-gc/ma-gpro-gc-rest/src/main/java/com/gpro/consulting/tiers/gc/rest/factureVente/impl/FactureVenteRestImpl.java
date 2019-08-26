package com.gpro.consulting.tiers.gc.rest.factureVente.impl;

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

import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.FactureVenteElementValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.FactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.RechercheMulticritereFactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.ResultatRechecheFactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.gc.service.IGestionnaireGCCacheService;
import com.gpro.consulting.tiers.gc.service.factureVente.IFactureVenteService;

/**
 * @author Ameni Berrich
 *
 */
@Controller
@RequestMapping("/factureVente")
public class FactureVenteRestImpl {

private static final Logger logger = LoggerFactory.getLogger(FactureVenteRestImpl.class);
	
	@Autowired
	private IFactureVenteService factureVenteService;
	
	@Autowired
	private  IGestionnaireGCCacheService gestionnaireGCCacheService;
	
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public @ResponseBody LivraisonVenteVue validate(@RequestBody List<String> refBonLivList) {
		
		LivraisonVenteVue factureVue = new LivraisonVenteVue();
		
		if(refBonLivList!=null && refBonLivList.size()>0){
			
			factureVue = factureVenteService.getProduitFactureByReferenceBLList(refBonLivList);
		}
		
		return factureVue;
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody FactureVenteValue getFactureVente(@PathVariable Long id) {
		logger.info("getFactureVente/getById:" + id);
		return factureVenteService.rechercheFactureVenteValueParId(id);
	}
	@RequestMapping(value = "/creer", method = RequestMethod.POST)
	public @ResponseBody String creerFactureVente(@RequestBody FactureVenteValue pFactureVenteValue) {
		return factureVenteService.creerFactureVente(pFactureVenteValue);
	}

	@RequestMapping(value = "/modifier", method = RequestMethod.PUT)
	public @ResponseBody String modifierFctVente(
			@RequestBody FactureVenteValue pFactureVenteValue) {
		return this.factureVenteService.modifierFactureVenteValue(pFactureVenteValue);
	}

	@RequestMapping(value = "/supprimer:{id}", method = RequestMethod.DELETE)
	public void supprimerFactureVente(@PathVariable("id") Long id) {
		
		factureVenteService.supprimerFactureVenteValue(id);
	}
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST)
	public @ResponseBody ResultatRechecheFactureVenteValue rechercherFactureVenteMultiCritere(
			@RequestBody final RechercheMulticritereFactureVenteValue pRechercheMultiCritere) {
		
		logger.info("Recherche Multicritères :");
		ResultatRechecheFactureVenteValue vResultatRecherche = factureVenteService.rechercherFactureVenteMultiCritere(pRechercheMultiCritere);
		
		//Traitement : transformation de l'Id a sa propre Designation

	      for(FactureVenteElementValue facture : vResultatRecherche.getFactureVenteValues()){
			 //TODO fix methode params
	    	  Map<String, String> mapA = gestionnaireGCCacheService.rechercherCommandeParId(null, facture.getPartintId(), null, null);
	    	  //Client
	    	  facture.setPartieInteresseDesignation(mapA.get("client"));

		    }
		return vResultatRecherche;
	}
}
