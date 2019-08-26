package com.gpro.consulting.tiers.gpao.persitance.gammeof;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ElementGammeOfValue;

/**
 * ElementGammeOf Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 30/07/2016
 *
 */
public interface IElementGammeOfPersistance {

	public List<ElementGammeOfValue> getListFromCriteria(Long ofId, String operationCode);

	/**
	 * @author Zeineb Medimagh
	 */
	
	//Retourne la liste des ID des élements Gamme OF
	public List<Long> getByOfIdAndOperationId(Long ofId, Long operationId);

	public ElementGammeOfValue getByElementOFId(Long elementOFId);
	
	public List<ElementGammeOfValue> getListByGammeOFId(Long gammeOFId);
	
	public List<ElementGammeOfValue> getListByGammeOperationId(Long operationId);
	
}
