package com.gpro.consulting.tiers.gpao.domaine.report.utilities;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.gpro.consulting.tiers.gpao.coordination.report.ResultatRechecheFicheSaisieElementValue;
/**
 * @author Ameni Berrich
 *
 */
public class ResultatRechercheFicheSaisieElementComparator implements Comparator<ResultatRechecheFicheSaisieElementValue>{

	@Override
	public int compare(ResultatRechecheFicheSaisieElementValue o1,
			ResultatRechecheFicheSaisieElementValue o2) {
			 
		    return ComparisonChain.start()
		        .compare(o2.getDateSaisie(), o1.getDateSaisie())
		        .compare(o1.getMatricule(), o2.getMatricule())
		        .result();
	}
}