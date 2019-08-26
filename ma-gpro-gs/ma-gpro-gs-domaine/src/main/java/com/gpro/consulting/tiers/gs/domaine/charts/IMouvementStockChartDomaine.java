package com.gpro.consulting.tiers.gs.domaine.charts;

import com.gpro.consulting.tiers.gs.coordination.chart.value.RechercheMulticritereMouvementChartValue;
import com.gpro.consulting.tiers.gs.coordination.chart.value.ResultatRechecheMouvementChartValue;

/**
 * MouvementStockChart Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 14/04/2016
 *
 */
public interface IMouvementStockChartDomaine {

	public ResultatRechecheMouvementChartValue rechercherMultiCritere(
			RechercheMulticritereMouvementChartValue request);

}
