package com.gpro.consulting.tiers.gpao.coordination.value;

import java.util.Comparator;
import java.util.Map;

public class FeuilleSaisieRendementComparator implements Comparator<Map.Entry<String, Double>>{
	
	public int compare( Map.Entry<String, Double> o1, Map.Entry<String, Double> o2 )
    {
        return (o2.getValue()).compareTo( o1.getValue() );
    }
}
