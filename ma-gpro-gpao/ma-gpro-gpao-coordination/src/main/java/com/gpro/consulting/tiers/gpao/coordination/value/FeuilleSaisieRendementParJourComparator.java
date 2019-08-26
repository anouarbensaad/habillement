package com.gpro.consulting.tiers.gpao.coordination.value;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

public class FeuilleSaisieRendementParJourComparator implements Comparator <Map.Entry<Date, Double>>{
	
	public int compare( Map.Entry<Date, Double> o1, Map.Entry<Date, Double> o2 )
    {
        return (o2.getKey()).compareTo( o1.getKey());
    }
}
