package com.gpro.consulting.tiers.gpao.persitance.gammemontage;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ElementGammeValue;

/**
 * 
 * @author Zeineb Medimagh
 *
 */

public interface ElementGammePersistance {
	
	public List<ElementGammeValue>  getAllByGammeProdId(Long gammeProduitId);

}
