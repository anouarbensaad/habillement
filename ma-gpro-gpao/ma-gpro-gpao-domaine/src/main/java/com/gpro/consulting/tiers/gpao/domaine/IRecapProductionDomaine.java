package com.gpro.consulting.tiers.gpao.domaine;

import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.RechercheMulticritereRecapProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.ResultatRechecheRecapProductionValue;

/**
 * RecapProduction Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 21/05/2016
 *
 */
public interface IRecapProductionDomaine {

	public ResultatRechecheRecapProductionValue rechercherMultiCritere(
			RechercheMulticritereRecapProductionValue request);
}
