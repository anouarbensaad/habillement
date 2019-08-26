package com.gpro.consulting.tiers.gs.coordination.value.customcomparator;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.gpro.consulting.tiers.gs.coordination.value.BonMouvementStockValue;

public class BonMouvementComparator implements Comparator<BonMouvementStockValue> {

	@Override
    public int compare(BonMouvementStockValue o1, BonMouvementStockValue o2) {
		 
	    return ComparisonChain.start()
	        .compare(o1.getDate(), o2.getDate())
	        .compare(o1.getId(), o2.getId())
	        .result();
    }

}
