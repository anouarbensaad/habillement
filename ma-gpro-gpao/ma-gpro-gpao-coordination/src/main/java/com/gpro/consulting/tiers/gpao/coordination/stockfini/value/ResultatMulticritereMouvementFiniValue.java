package com.gpro.consulting.tiers.gpao.coordination.stockfini.value;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author $Samer Hassen
 *
 */
public class ResultatMulticritereMouvementFiniValue {

	private Long nombreResultaRechercher;

	private Set<MouvementFiniValue> movementFiniValues = new TreeSet<MouvementFiniValue>();

	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	public Set<MouvementFiniValue> getMovementFiniValues() {
		return movementFiniValues;
	}

	public void setMovementFiniValues(Set<MouvementFiniValue> movementFiniValues) {
		this.movementFiniValues = movementFiniValues;
	}

	


	
	

}
