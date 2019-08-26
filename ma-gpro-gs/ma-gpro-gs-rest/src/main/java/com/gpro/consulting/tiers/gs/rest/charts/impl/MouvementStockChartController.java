package com.gpro.consulting.tiers.gs.rest.charts.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.gs.coordination.chart.value.RechercheMulticritereMouvementChartValue;
import com.gpro.consulting.tiers.gs.coordination.chart.value.ResultatRechecheMouvementChartValue;
import com.gpro.consulting.tiers.gs.service.charts.IMouvementStockChartService;

/**
 * MouvementStock Chart Controller
 * 
 * @author Wahid Gazzah
 * @since 13/04/2016
 *
 */

@RestController
@RequestMapping("/mouvementStockChart")
public class MouvementStockChartController {
	
	private static final Logger logger = LoggerFactory.getLogger(MouvementStockChartController.class);
	
	@Autowired
	IMouvementStockChartService mouvementStockChartService;
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheMouvementChartValue rechercheMulticritere( @RequestBody RechercheMulticritereMouvementChartValue request) {
		  
		logger.info("rechercherMultiCritere: Delegating request to Service layer.");
		  
		return mouvementStockChartService.rechercherMultiCritere(request);
	}

}
