package com.gpro.consulting.tiers.gpao.persitance.bonsortiefini.utilities;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.BonSortieFiniValue;

/**
 * This comparator sorts a list of BonLivraisonReportProductValue by ProduitCode and Choix
 * into ascending order
 * 
 * @author Wahid Gazzah
 * @since 18/02/2016
 *
 */
public class BonSortieFiniComparator implements Comparator<BonSortieFiniValue>{
	
	@Override
    public int compare(BonSortieFiniValue o1, BonSortieFiniValue o2) {
		 
	    return ComparisonChain.start()
	    		 .compare(o1.getReference(), o2.getReference())
	    		.compare(o1.getBlExport(), o2.getBlExport())
	 	       
	 	        
	        .result();
    }
}
