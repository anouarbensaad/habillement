package com.gpro.consulting.tiers.commun.rest.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitCouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitTailleValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.service.IProduitCouleurService;
import com.gpro.consulting.tiers.commun.service.IProduitTailleService;
import com.gpro.consulting.tiers.commun.service.ITailleService;

@Controller
@RequestMapping("/taille")
public class TailleRestImpl {

	@Autowired
	private ITailleService ebTailleService;
	@Autowired
	private IProduitTailleService produitTailleService;

	List<TailleValue> listeType = new ArrayList<>();

	/************* get Taille By ID *************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody TailleValue getTaille(@PathVariable Long id) {
		TailleValue pTailleValue = new TailleValue();
		pTailleValue.setId(id);
		return ebTailleService.rechercheTailleParId(pTailleValue);
	}

	/********** all Taille ***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TailleValue> viewAllType() {
		return this.listeType = ebTailleService.listeTaille();
	}

	/********* Creer Taille ********/
	@RequestMapping(value = "/creerTailleProduit", method = RequestMethod.POST)
	public @ResponseBody String creerTailleProduit(@RequestBody TailleValue pTailleProduitValue) {
		// transformation et transcodage des referenciel
		return ebTailleService.creerTaille(pTailleProduitValue);
	}

	/********* Modifier Taille ********/
	@RequestMapping(value = "/modifierTailleProduit", method = RequestMethod.POST)
	public @ResponseBody String modifierTailleProduit(@RequestBody TailleValue pTailleProduitValue) {
		return this.ebTailleService.modifierTaille(pTailleProduitValue);
	}

	/********* Supprimer Taille ********/
	@RequestMapping(value = "/supprimerTailleProduit:{id}", method = RequestMethod.DELETE)
	public void supprimerDetailsPrixProduit(@PathVariable("id") String id) {
		Long idSupp = Long.parseLong(id);
		ebTailleService.supprimerTaille(idSupp);
	}

	/*** Tailleparpoduit ***/
	@RequestMapping(value = "/TailleParProduit:{pIdProduit}", method = RequestMethod.GET)
	public @ResponseBody List<TailleValue> listTailleParProduit(@PathVariable("pIdProduit") Long pIdProduit) {

		List<TailleValue> vRp = new ArrayList<>();
		List<ProduitTailleValue> vListProduitTailleValue = this.produitTailleService.ListeProduitTaille(pIdProduit);
		this.listeType = ebTailleService.listeTaille();

		for (ProduitTailleValue itemProTaille : vListProduitTailleValue) {

			TailleValue tailleARechercher = new TailleValue();
			tailleARechercher.setId(itemProTaille.getEbTailleId());
			TailleValue taille = ebTailleService.rechercheTailleParId(tailleARechercher);
			vRp.add(taille);
			Collections.sort(vRp);
		}

		return vRp;
	}

	/******** GETTER & SETTER ********/
	public ITailleService getEbTailleService() {
		return ebTailleService;
	}

	public void setEbTailleService(ITailleService ebTailleService) {
		this.ebTailleService = ebTailleService;
	}

	public List<TailleValue> getListeType() {
		return listeType;
	}

	public void setListeType(List<TailleValue> listeType) {
		this.listeType = listeType;
	}

}
