package com.gpro.consulting.tiers.gpao.domaine;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.GammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.RechercheMulticritereGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ResultatRechecheGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;

/**
 * GammeOf Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 10/05/2016
 *
 */

public interface IGammeOfDomaine {
	
	public String create(GammeOfValue gammeOfValue);

	public GammeOfValue getById(Long id);

	public String update(GammeOfValue gammeOfValue);

	public void delete(Long id);

	public List<GammeOfValue> getAll();

	public ResultatRechecheGammeOfValue rechercherMultiCritere(
			RechercheMulticritereGammeOfValue request);

	public List<OrdreFabricationValue> getOrdreFabricationListAvailable();

	public List<OrdreFabricationValue> getOrdreFabricationListUsed();

	public GammeOfValue getByOrdreFabricationId(Long ordreFabricationId);

	public GammeOfValue getByOFId(Long ordreFabricationId);
	
	public GammeOfValue validateByOrdreFabricationId(Long ordreFabricationId);
	
	public ResultatRechecheGammeOfValue rechercherMultiCritere(
			RechercheMulticritereGammeOfValue request, boolean allegee);
}
