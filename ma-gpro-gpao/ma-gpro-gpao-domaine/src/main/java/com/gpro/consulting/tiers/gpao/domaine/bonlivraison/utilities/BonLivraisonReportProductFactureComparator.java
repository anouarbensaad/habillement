package com.gpro.consulting.tiers.gpao.domaine.bonlivraison.utilities;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.gpro.consulting.tiers.gpao.coordination.report.bonlivraison.value.BonLivraisonReportProductValue;

/**
 * This comparator sorts a list of BonLivraisonReportProductValue by ProduitCode and Choix
 * into ascending order
 * 
 * @author Wahid Gazzah
 * @since 18/02/2016
 *
 */
public class BonLivraisonReportProductFactureComparator implements Comparator<BonLivraisonReportProductValue>{
	
	@Override
    public int compare(BonLivraisonReportProductValue o1, BonLivraisonReportProductValue o2) {
		 
	    return ComparisonChain.start()
	    		
	 	        .compare(o1.getNumeroOF(), o2.getNumeroOF())
	 	        
	        .result();
    }
}
