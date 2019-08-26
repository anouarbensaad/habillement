package com.gpro.consulting.tiers.gpao.rest.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.RechercheMulticritereDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.ResultatRechecheDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticritereFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.service.IDetailSaisieService;

/**
 * @author Ameni Berrich
 *
 */
@RestController
@RequestMapping("/detailFeuilleSaisie")
public class DetailSaisieRestImpl {

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	private static final Logger logger = LoggerFactory.getLogger(FeuilleSaisieRestImpl.class);

	@Autowired
	private IDetailSaisieService detailSaisieService;

	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheDetailSaisieValue rechercherMultiCritere(
			@RequestBody RechercheMulticritereDetailSaisieValue request) {

		// Check if all criterias are null so generic search is applied with
		// optimisation (max results)
		request.setOptimized(this.checkForOptimization(request));

		return detailSaisieService.rechercherMultiCritere(request);
	}

	@RequestMapping(value = "/getSommeQteProduite", method = RequestMethod.GET, produces = "application/json")
	public Long getSommeQteProduite(@RequestParam Long ofId,@RequestParam Long produitId,@RequestParam Long operationId, @RequestParam String dateMin,
			@RequestParam String dateMax) {

		// LOGGER.info("rechercheMulticritere: Delegating request to service
		// layer.");
        if (ofId!=null)
		return detailSaisieService.getSommeQteProduiteOF(ofId,operationId, stringToCalendar(dateMin),
				stringToCalendar(dateMax));
        else 
        	return detailSaisieService.getSommeQteProduiteProduit(produitId,operationId, stringToCalendar(dateMin),
    				stringToCalendar(dateMax));
            	
	}

	@RequestMapping(value = "/productionParOperation", method = RequestMethod.POST, produces = "application/json")

	public Map<Date, Long> productionOfOperation(
			@RequestParam String dateDebut,
			@RequestParam String dateFin,
			@RequestParam Long operationId,
			@RequestParam(value = "chaineId", required = false) String chaineId) {
		
		Long chaineIdParam = null;
		
		if(!isNullOrEmpty(chaineId)){
			chaineIdParam = Long.parseLong(chaineId);
		}
		
		return detailSaisieService.productionParOperation(
				stringToCalendar(dateDebut),
				stringToCalendar(dateFin),
				operationId,
				chaineIdParam);

	}
	
	@RequestMapping(value = "/recapProductionParChaine", method = RequestMethod.POST, produces = "application/json")

	public Map<Long, Long> recapProductionParChaine(
			@RequestParam String dateDebut,
			@RequestParam String dateFin,
			@RequestParam Long operationId) {
		
		
		return detailSaisieService.recapProductionParChaine(
				stringToCalendar(dateDebut),
				stringToCalendar(dateFin),
				operationId);

	}

	/***********************************
	 * Méthodes complémentaires
	 *****************************/

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

	private boolean isNotEmty(String value) {
		return value != null && !"".equals(value);

	}

	public boolean checkForOptimization(RechercheMulticritereDetailSaisieValue request) {

		return isNullOrEmpty(request.getMatricule()) && isNullOrEmpty(request.getChaineId())
				&& isNullOrEmpty(request.getDateSaisieMax()) && isNullOrEmpty(request.getDateSaisieMin())
				&& isNullOrEmpty(request.getPaquetId()) && isNullOrEmpty(request.getOfId())
				&& isNullOrEmpty(request.getOperation()) && isNullOrEmpty(request.getElementGammeOfId());

	}

	public boolean isNullOrEmpty(Object criteria) {
		return criteria == null || criteria.toString().length() == 0;
	}

}
