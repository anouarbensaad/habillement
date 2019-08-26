package com.gpro.consulting.tiers.gs.service.charts;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gs.coordination.chart.value.RechercheMulticritereMouvementChartValue;
import com.gpro.consulting.tiers.gs.coordination.chart.value.ResultatRechecheMouvementChartValue;

/**
 * @author Wahid Gazzah
 * @since 14/04/2016
 */
public interface IMouvementStockChartService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheMouvementChartValue rechercherMultiCritere(
			RechercheMulticritereMouvementChartValue request);

}
