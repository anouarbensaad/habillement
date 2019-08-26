package com.gpro.consulting.tiers.gpao.domaine;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ElementGammeOfValue;

/**
 * ElementGammeOf Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 30/07/2016
 *
 */
public interface IElementGammeOfDomaine {

	List<ElementGammeOfValue> getListFromCriteria(Long ofId, String operationCode);

}
