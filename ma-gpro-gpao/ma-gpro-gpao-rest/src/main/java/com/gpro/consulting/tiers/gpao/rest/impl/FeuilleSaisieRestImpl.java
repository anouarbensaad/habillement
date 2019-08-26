
package com.gpro.consulting.tiers.gpao.rest.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.FeuilleSaisieTRValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.FeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticritereFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechecheFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.RechercheMulticritereGammeOfValue;
import com.gpro.consulting.tiers.gpao.service.IFeuilleSaisieService;

/**
 * Feuille Saisie Controller
 * 
 * @author Wahid Gazzah
 * @since 25/05/2016
 *
 */

@RestController
@RequestMapping("/feuilleSaisie")
public class FeuilleSaisieRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(FeuilleSaisieRestImpl.class);
	
	@Autowired
	private IFeuilleSaisieService feuilleSaisieService;
	private static final String DATE_FORMAT = "yyyy-MM-dd";

	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheFeuilleSaisieValue rechercherMultiCritere(@RequestBody RechercheMulticritereFeuilleSaisieValue request) {
		 
			//Check if all criterias are null so generic search is applied with optimisation (max results)
			request.setOptimized(this.checkForOptimization(request));
	
		return feuilleSaisieService.rechercherMultiCritere(request);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody FeuilleSaisieValue feuilleSaisie) {
		
		//LOGGER.info("createFeuilleSaisie: Delegating request to Service layer.");
		//LOGGER.info("createFeuilleSaisie:Rest"+feuilleSaisie);

		return feuilleSaisieService.create(feuilleSaisie);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public FeuilleSaisieValue getById(@PathVariable Long id) {
		  
		//LOGGER.info("getFeuilleSaisieById: Delegating request id {} to service layer.", id);
		
		return feuilleSaisieService.getById(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody FeuilleSaisieValue feuilleSaisie) {
	    
		//LOGGER.info("UpdateFeuilleSaisie: Delegating request to service layer.");
		
		return feuilleSaisieService.update(feuilleSaisie);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//LOGGER.info("deleteFeuilleSaisie: Delegating request id {} to service layer.", id);
		  
		feuilleSaisieService.delete(id);
	}
	
	@RequestMapping(value = "/validate", method = RequestMethod.PUT)
	public FeuilleSaisieValue validate(@RequestBody FeuilleSaisieValue feuilleSaisie) {
	    
		//LOGGER.info("validateBareCode: Delegating request to service layer.");
		
		return feuilleSaisieService.validate(feuilleSaisie);
	}
	
	@RequestMapping(value = "/listPersonnel", method = RequestMethod.GET, produces = "application/json")
	public List<PersonnelValue> listMatricule() {
	    
		//LOGGER.info("listPersonnel: Delegating request to service layer.");
		
		return feuilleSaisieService.listPersonnel();
	}
	
	@RequestMapping(value = "/updatePscProdForAllFeuilleSaisie", method = RequestMethod.GET, produces = "application/json")
	public boolean updatePscProdForAllFeuilleSaisie() {
		  
		//LOGGER.info("Update PscProd For All FeuilleSaisie: Delegating request id {} to service layer.");
		
		return feuilleSaisieService.updatePscProdForAllFeuilleSaisie();
	}
	
	
	@RequestMapping(value = "/addOrUpdateFeuilleSaisieTR", method = RequestMethod.POST, produces = "application/json")
	public void addOrUpdateFeuilleSaisieTR(@RequestBody FeuilleSaisieTRValue feuilleSaisieTR){
		
		this.feuilleSaisieService.addOrUpdateFeuilleSaisieTR(feuilleSaisieTR);
	}
	
	@RequestMapping(value = "/rendementChaineParMatricule", method = RequestMethod.POST, produces = "application/json")
	public List<Entry<String, Double>> rendementChaineParMatricule(
			@RequestParam("dateDebut") String dateDebut,
			@RequestParam("dateFin") String dateFin,
			@RequestParam("chaineId") String chaineId
			){
		
		List<Entry<String, Double>> result = this.feuilleSaisieService.rendementChaineParMatricule(
				stringToCalendar(dateDebut),
				stringToCalendar(dateFin), 
				Long.parseLong(chaineId));
		
		return result;
	}
	
	@RequestMapping(value = "/rendementMatriculeParJour", method = RequestMethod.POST, produces = "application/json")
	public Map<Date, Double> rendementMatriculeParJour(
			@RequestParam("dateDebut") String dateDebut,
			@RequestParam("dateFin") String dateFin,
			@RequestParam("matricule") String matricule
			){
		
		return this.feuilleSaisieService.rendementMatriculeParJour(
				stringToCalendar(dateDebut),
				stringToCalendar(dateFin), 
				matricule);
	}
	
	
	@RequestMapping(value = "/rendementChaineParJour", method = RequestMethod.POST, produces = "application/json")
	public Map<Date, Double> rendementChaineParJour(
			@RequestParam("dateDebut") String dateDebut,
			@RequestParam("dateFin") String dateFin,
			@RequestParam("chaineId") String chaineId
			){
		
		Long chaineIdParam ;
		if(chaineId != null && chaineId.length() > 0){
			chaineIdParam = Long.parseLong(chaineId);
		}else{
			chaineIdParam = null;	
		}
		
		Map<Date, Double> result = this.feuilleSaisieService.rendementChaineParJour(
				stringToCalendar(dateDebut),
				stringToCalendar(dateFin), 
				chaineIdParam);
		
		return result;
	}

	

	@RequestMapping(value = "/recapRendementChaine", method = RequestMethod.POST, produces = "application/json")
	public List<Entry<Long, Double>> rendementChaineParMatricule(
			@RequestParam("dateDebut") String dateDebut,
			@RequestParam("dateFin") String dateFin
			){
		
		List<Entry<Long, Double>> result = this.feuilleSaisieService.recapRendementChaine(
				stringToCalendar(dateDebut),
				stringToCalendar(dateFin));
		
		return result;
	}
	
	
	public boolean checkForOptimization( RechercheMulticritereFeuilleSaisieValue request){
		
		return 	isNullOrEmpty(request.getMatricule()) &&
				isNullOrEmpty(request.getChaineId()) &&
				isNullOrEmpty(request.getDateSaisieMax()) &&
				isNullOrEmpty(request.getDateSaisieMin()) &&
				isNullOrEmpty(request.getRendementMax()) &&
				isNullOrEmpty(request.getRendementMin()) &&
				isNullOrEmpty(request.getActiviteMax()) &&					
				isNullOrEmpty(request.getActiviteMin());
		
	}
	
	public boolean isNullOrEmpty (Object criteria){
		return criteria == null || criteria.toString().length() == 0;
	}

	private boolean isNotEmty(String value) {
		return value != null && !"".equals(value);

	}

	private Calendar stringToCalendar(String dateString) {

		Calendar dateCalendar = null;

		if (isNotEmty(dateString)) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			dateCalendar = sdf.getCalendar();
			try {
				dateCalendar.setTime(sdf.parse(dateString));

			} catch (ParseException e) {
				logger.error("parse date exception: " + e.getMessage());
			}
		}

		return dateCalendar;
	}
}
