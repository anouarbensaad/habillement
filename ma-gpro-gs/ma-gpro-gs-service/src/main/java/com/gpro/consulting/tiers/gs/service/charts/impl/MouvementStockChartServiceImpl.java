package com.gpro.consulting.tiers.gs.service.charts.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.gs.coordination.chart.value.RechercheMulticritereMouvementChartValue;
import com.gpro.consulting.tiers.gs.coordination.chart.value.ResultatRechecheMouvementChartValue;
import com.gpro.consulting.tiers.gs.domaine.charts.IMouvementStockChartDomaine;
import com.gpro.consulting.tiers.gs.service.charts.IMouvementStockChartService;

/**
 * implementation of {@link IMouvementStockChartService}
 *  
 * @author Wahid Gazzah
 * @since 14/04/2016
 */

@Service
@Transactional
public class MouvementStockChartServiceImpl implements IMouvementStockChartService{
	
	private static final Logger logger = LoggerFactory.getLogger(MouvementStockChartServiceImpl.class);
	
    @Autowired
    IMouvementStockChartDomaine mouvementStockChartDomaine;

	@Override
	public ResultatRechecheMouvementChartValue rechercherMultiCritere(
			RechercheMulticritereMouvementChartValue request) {
		
		logger.info("rechercherMultiCritere: Delegating request to Domaine layer.");
		
		return mouvementStockChartDomaine.rechercherMultiCritere(request);
	}

}
