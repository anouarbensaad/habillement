package com.gpro.consulting.tiers.gpao.persitance.gammeof;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ElementGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.GammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.RechercheMulticritereGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ResultatRechecheGammeOfValue;

/**
 * GammeProduit Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 10/05/2016
 *
 */
public interface IGammeOfPersistance {

	public String create(GammeOfValue gammeOfValue);

	public GammeOfValue getById(Long id);

	public String update(GammeOfValue gammeOfValue);

	public void delete(Long id);

	public List<GammeOfValue> getAll();
	
	public ResultatRechecheGammeOfValue rechercherMultiCritere(
			RechercheMulticritereGammeOfValue request);

	public GammeOfValue getByOFId(Long ordreFabricationId);
	
	/**
	 * @author Zeineb Medimagh
	 * @since 01/12/2016
	 */
	
	public Long getOFIdByGammeId(Long gammeOfId);
	
	public ResultatRechecheGammeOfValue rechercherMultiCritere(
			RechercheMulticritereGammeOfValue request, boolean allegee);
	
	public ElementGammeOfValue getElementGammeOFById(Long id);
		
	
	
	
}
