package com.gpro.consulting.tiers.gpao.domaine.bonlivraison.utilities;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.DetLivraisonVenteValue;

/**
 * This comparator sorts a list of DetLivraisonVenteValue by ProduitCode and Choix
 * into ascending order
 * 
 * @author Wahid Gazzah
 * @since 18/02/2016
 *
 */
public class DetLivraisonVenteFaconValidateComparator implements Comparator<DetLivraisonVenteValue>{
	
	@Override
    public int compare(DetLivraisonVenteValue o1, DetLivraisonVenteValue o2) {
		 
	    return ComparisonChain.start()
	        .compare(o1.getTraitementFaconId(), o2.getTraitementFaconId())
	        .result();
    }
}
