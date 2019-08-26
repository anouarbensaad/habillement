package com.gpro.consulting.tiers.gpao.coordination.value;

import java.util.Comparator;
import java.util.Map;

public class FeuilleSaisieRendementChaineComparator implements Comparator<Map.Entry<Long, Double>>{
	
	public int compare( Map.Entry<Long, Double> o1, Map.Entry<Long, Double> o2 )
    {
        return (o2.getValue()).compareTo( o1.getValue() );
    }
}
