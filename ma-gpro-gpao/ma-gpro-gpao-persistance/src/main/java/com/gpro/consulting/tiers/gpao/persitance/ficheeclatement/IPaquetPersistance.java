package com.gpro.consulting.tiers.gpao.persitance.ficheeclatement;

import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.PaquetValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IPaquetPersistance {
	
	public PaquetValue getById(Long id);
	
	public Long getSommeQte(Long ofId);
}
