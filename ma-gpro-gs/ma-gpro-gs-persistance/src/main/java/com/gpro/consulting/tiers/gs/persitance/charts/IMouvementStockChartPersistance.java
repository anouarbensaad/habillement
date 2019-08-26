package com.gpro.consulting.tiers.gs.persitance.charts;

import com.gpro.consulting.tiers.gs.coordination.chart.value.RechercheMulticritereMouvementChartValue;
import com.gpro.consulting.tiers.gs.coordination.chart.value.ResultatRechecheMouvementChartValue;

/**
 * MouvementStock Chart Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 14/04/2016
 *
 */
public interface IMouvementStockChartPersistance {

	ResultatRechecheMouvementChartValue rechercherMultiCritere(
			RechercheMulticritereMouvementChartValue request);

}
