package com.gpro.consulting.tiers.gpao.rest.impl;

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

import com.gpro.consulting.tiers.gpao.coordination.value.PhaseOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticriterePhaseOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticriterePhaseOfValue;
import com.gpro.consulting.tiers.gpao.rest.IPhaseOfRest;
import com.gpro.consulting.tiers.gpao.service.IPhaseOfService;

@Controller
@RequestMapping("/phaseof")
public class PhaseOfRestImpl implements IPhaseOfRest {

	private static final Logger logger = LoggerFactory.getLogger(PhaseOfRestImpl.class);

	@Autowired
	IPhaseOfService PhaseOfService;

	/******************************* recherche multicritere *********************************/

	@RequestMapping(value = "/recherchePhaseOfMulticritere", method = RequestMethod.POST)
	public @ResponseBody ResultatMulticriterePhaseOfValue rechercherPhaseOfMultiCritere(
			@RequestBody final RechercheMulticriterePhaseOfValue pRecherchePhaseOfValueMulticritere) {
		ResultatMulticriterePhaseOfValue vResultatRecherche = PhaseOfService
				.rechercherPhaseOfMultiCritere(pRecherchePhaseOfValueMulticritere);
		// TODO Cache
		return vResultatRecherche;
	}

	/************* get PhaseOf By ID *************/
	/**
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/getPhaseOfId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody PhaseOfValue getEmploye(@PathVariable Long id) {
		PhaseOfValue pPhaseOfValue = new PhaseOfValue();
		pPhaseOfValue.setId(id);
		return PhaseOfService.recherchePhaseOfParId(pPhaseOfValue);
	}

	/******************************* All Phase Of *********************************/
	/**
	 * @return
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<PhaseOfValue> viewAllPhaseOfValue() {
		List<PhaseOfValue> vPhaseOfService = PhaseOfService.listePhaseOfValue();
		return vPhaseOfService;
	}

	/********************** Méthode de modification d'une phaseOf **********************/
	/**
	 * @param pPhaseOfValue
	 * @return
	 */
	@RequestMapping(value = "/modifiephase", method = RequestMethod.POST)
	public @ResponseBody String modifierPhaseOf(
			@RequestBody PhaseOfValue pPhaseOfValue) {
		return this.PhaseOfService.modifierPhaseOf(pPhaseOfValue);

	}

}
