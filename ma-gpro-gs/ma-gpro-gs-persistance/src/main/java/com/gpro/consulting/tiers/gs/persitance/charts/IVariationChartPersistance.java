package com.gpro.consulting.tiers.gs.persitance.charts;

import com.gpro.consulting.tiers.gs.coordination.chart.variation.value.RechercheMulticritereVariationChartValue;
import com.gpro.consulting.tiers.gs.coordination.chart.variation.value.ResultatRechecheVariationChartValue;

public interface IVariationChartPersistance {
	
	public ResultatRechecheVariationChartValue rechercherMultiCritere(
			RechercheMulticritereVariationChartValue request);
}
