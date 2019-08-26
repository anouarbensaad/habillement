package com.gpro.consulting.tiers.gs.domaine.charts;

import com.gpro.consulting.tiers.gs.coordination.chart.variation.value.RechercheMulticritereVariationChartValue;
import com.gpro.consulting.tiers.gs.coordination.chart.variation.value.ResultatRechecheVariationChartValue;

/**
 * Variation Chart Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 26/04/2016
 *
 */
public interface IVariationChartDomaine {
	
	public ResultatRechecheVariationChartValue rechercherMultiCritere(
			RechercheMulticritereVariationChartValue request);

}
