package com.gpro.consulting.tiers.gc.rest.livraisonVente;

import java.util.ArrayList;
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

import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.LivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.ProduitLivraisonValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.RechercheMulticritereLivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.ResultatRechecheLivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.livraisonVenteElementValue;
import com.gpro.consulting.tiers.gc.service.IGestionnaireGCCacheService;
import com.gpro.consulting.tiers.gc.service.livraisonVente.ILivraisonVenteService;

/**
 * @author Ameni Berrich
 *
 */
@Controller
@RequestMapping("/livraisonVente")
public class LivraisonVenteRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(LivraisonVenteRestImpl.class);
	
	@Autowired
	private ILivraisonVenteService livraisonVenteService;
	
	@Autowired
	private  IGestionnaireGCCacheService gestionnaireGCCacheService;
	
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public @ResponseBody List<ProduitLivraisonValue> validate(@RequestBody List<String> refBonCmdList) {
		
		List<ProduitLivraisonValue> list = new ArrayList<ProduitLivraisonValue>();
		
		if(refBonCmdList!=null && refBonCmdList.size()>0){
			
			list = livraisonVenteService.getProduitLivraisonListByRefBonCmdList(refBonCmdList);
		}
		
		return list;
	}
	
	@RequestMapping(value = "/getListBLReference", method = RequestMethod.GET , produces = "application/json")
	public @ResponseBody List<String> getReferences() {
		
		return livraisonVenteService.getReferences();
	}

	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody LivraisonVenteValue getLivraisonVente(@PathVariable Long id) {
		logger.info("recherche d'un bon de Livraison  /getId:" + id);
		
		return livraisonVenteService.rechercheLivraisonVenteValueParId(id);
	}
	
	@RequestMapping(value = "/creer", method = RequestMethod.POST)
	public @ResponseBody String creerLivVente(
			@RequestBody LivraisonVenteValue pLivraisonVenteValue) {
		return livraisonVenteService.creerLivraisonVente(pLivraisonVenteValue);
	}

	@RequestMapping(value = "/modifier", method = RequestMethod.PUT)
	public @ResponseBody String modifierLivVente(
			@RequestBody LivraisonVenteValue pLivraisonVenteValue) {
		return this.livraisonVenteService.modifierLivraisonVenteValue(pLivraisonVenteValue);
	}

	@RequestMapping(value = "/supprimer:{id}", method = RequestMethod.DELETE)
	public void supprimerLivVente(@PathVariable("id") Long id) {
		
		livraisonVenteService.supprimerLivraisonVenteValue(id);
	}
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST)
	public @ResponseBody ResultatRechecheLivraisonVenteValue rechercherLivraisonVenteMultiCritere(
			@RequestBody final RechercheMulticritereLivraisonVenteValue pRechercheMultiCritere) {
		
		logger.info("Recherche Multicritères :");
		// Création des critères de recherche
		ResultatRechecheLivraisonVenteValue vResultatRecherche = livraisonVenteService
				.rechercherLivraisonVenteMultiCritere(pRechercheMultiCritere);

		//Traitement : transformation de l'Id a sa propre Designation

	      for(livraisonVenteElementValue commande : vResultatRecherche.getLivraisonVenteValues()){
			 //TODO fix methode params
	    	  Map<String, String> mapA = gestionnaireGCCacheService.rechercherCommandeParId(null, commande.getPartieIntersseId(), null, null);
	    	  //Client
	    	  commande.setPartieIntersseDesignation(mapA.get("client"));

		    }
		return vResultatRecherche;
	}
}
