package com.gpro.consulting.tiers.gc.rest.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.SiteValue;
import com.gpro.consulting.tiers.gc.coordination.value.EtatCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.TypeCommandeValue;
import com.gpro.consulting.tiers.gc.service.IGestionnaireGCCacheService;

@Controller
@RequestMapping("/gestionCommercialCache")
public class GestionnaireCacheRestImpl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GestionnaireCacheRestImpl.class);
	
	@Autowired
	private IGestionnaireGCCacheService gestionnaireGCCacheService;

	/** EtatCommande cache
	 * @return List<EtatCommandeValue>
	 */
	@RequestMapping(value = "/listeEtatCommandeVenteCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EtatCommandeValue> viewAllEtatCommandeVente(){
		LOGGER.info("Entrer Cache EtatCommandeVente");
		return gestionnaireGCCacheService.getListeEtatCommandeVente();
    }

	/** typeCommande cache
	 * @return List<EtatCommandeValue>
	 */
	@RequestMapping(value = "/listeTypeCommandeVenteCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<TypeCommandeValue> viewAllTypeCommandeVente(){
		LOGGER.info("Entrer Cache TypeCommandeVente");
		return gestionnaireGCCacheService.getListeTypeCommandeVente();
    }

	@RequestMapping(value = "/listeClientCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<PartieInteresseCacheValue> viewClient(){
		LOGGER.info("Entrer Cache Client");
		return gestionnaireGCCacheService.getListeClient();
    }
	
	@RequestMapping(value = "/listeSiteCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<SiteValue> viewSite(){
		LOGGER.info("Entrer Cache Site");
		return gestionnaireGCCacheService.getListeSite();
    }
	
	/**
	 * Refresh du cache
	 * @return
	 */
	@RequestMapping(value = "/reloadGc", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody void reloadGestionCommerciale(){
		LOGGER.info("reloadReferentiel");
		gestionnaireGCCacheService.reloadGestionCommerciale();
    }
	
	
}
