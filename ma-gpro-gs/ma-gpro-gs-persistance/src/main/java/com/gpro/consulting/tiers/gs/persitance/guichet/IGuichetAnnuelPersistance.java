package com.gpro.consulting.tiers.gs.persitance.guichet;

import com.gpro.consulting.tiers.gs.coordination.guichet.value.GuichetAnnuelValue;

public interface IGuichetAnnuelPersistance {

	  public Long getNextNumBonEntreeReference();
	  
	  public Long getNextNumBonSortieReference();
	
	  public Long modifierGuichetBonEntreeAnnuel(GuichetAnnuelValue pGuichetValeur);
	  
	  public Long modifierGuichetBonSortieAnnuel(GuichetAnnuelValue pGuichetValeur);

}
