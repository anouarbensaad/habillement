package com.gpro.consulting.tiers.gpao.rest.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ChaineValue;
import com.gpro.consulting.tiers.gpao.coordination.value.StatutOfValue;
import com.gpro.consulting.tiers.gpao.service.IGestionnaireCacheService;

@Controller
@RequestMapping("/gestionProduitAOCache")
public class GestionnaireCacheRestImpl {

	@Autowired
	private IGestionnaireCacheService gestionnaireCacheServiceGpao;

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireCacheRestImpl.class);

	@RequestMapping(value = "/listeClientCache", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<PartieInteresseCacheValue> viewClient() {
		
		//LOGGER.info("Entrer Cache Client");
		
		return gestionnaireCacheServiceGpao.getListeClient();
	}

	/**
	 * StatutOf cache
	 * 
	 * @return List<StatutValue>
	 */
	@RequestMapping(value = "/listeStatutCache", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<StatutOfValue> viewAllStatut() {
		
		//LOGGER.info(" entrer cache StatutOF ");
		
		return gestionnaireCacheServiceGpao.getListeStatutOf();
	}

	/**
	 * ChaineOf cache
	 * 
	 * @return List<StatutValue>
	 */
	@RequestMapping(value = "/listeChaineCache", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ChaineValue> viewAllChaine() {
		
		//LOGGER.info(" entrer cache ChaineOF ");
		
		return gestionnaireCacheServiceGpao.getListeChaineOf();
	}

}
