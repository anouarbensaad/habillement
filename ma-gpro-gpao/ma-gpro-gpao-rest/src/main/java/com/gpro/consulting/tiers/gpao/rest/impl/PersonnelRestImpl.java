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
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.FeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticritereFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticriterePersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechecheFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechechePersonnelValue;
import com.gpro.consulting.tiers.gpao.service.IPersonnelService;

/**
 * @author Ameni Berrich
 *
 */
@RestController
@RequestMapping("/personnel")
public class PersonnelRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(PersonnelRestImpl.class);
	
	@Autowired 
	IPersonnelService personnelService ;
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public PersonnelValue getById(@PathVariable Long id) {
		  
		//LOGGER.info("getPersonnelById: Delegating request id {} to service layer.", id);
		
		return personnelService.getById(id);
	}
	
	@RequestMapping(value = "/creer", method = RequestMethod.POST)
	public @ResponseBody String creerPersonnel(
			@RequestBody PersonnelValue pPersonnelValue) {
		return personnelService.create(pPersonnelValue);
	}

	@RequestMapping(value = "/modifier", method = RequestMethod.PUT)
	public @ResponseBody String modifierPersonnel(
			@RequestBody PersonnelValue pPersonnelValue) {
		return this.personnelService.update(pPersonnelValue);
	}

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechechePersonnelValue rechercherMultiCritere(@RequestBody RechercheMulticriterePersonnelValue request) {
		 
		//LOGGER.info("rechercheMulticritere: Delegating request to service layer.");

		return personnelService.rechercherMultiCritere(request);
	}
	
	@RequestMapping(value = "/supprimer:{id}", method = RequestMethod.DELETE)
	public void supprimerChaine(@PathVariable("id") String id) {
		Long idSupp = Long.parseLong(id);
		personnelService.delete(Long.valueOf(idSupp));
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<PersonnelValue> viewAllPersonnel() {
		return personnelService.listPersonnel();
	}

}
