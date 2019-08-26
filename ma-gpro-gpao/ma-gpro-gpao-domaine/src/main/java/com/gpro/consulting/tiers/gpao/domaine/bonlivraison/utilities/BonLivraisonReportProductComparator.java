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
public class BonLivraisonReportProductComparator implements Comparator<BonLivraisonReportProductValue>{
	
	@Override
    public int compare(BonLivraisonReportProductValue o1, BonLivraisonReportProductValue o2) {
		 
	    return ComparisonChain.start()
	    		.compare(o1.getPalette(), o2.getPalette())
	    		.compare(o1.getUnite(), o2.getUnite())
	    		.compare(o1.getNombreColis(), o2.getNombreColis())
	 	        .compare(o1.getNumeroOF(), o2.getNumeroOF())	        
	 	        .compare(o1.getQuantiteColis(), o2.getQuantiteColis())
	        .result();
    }
}
