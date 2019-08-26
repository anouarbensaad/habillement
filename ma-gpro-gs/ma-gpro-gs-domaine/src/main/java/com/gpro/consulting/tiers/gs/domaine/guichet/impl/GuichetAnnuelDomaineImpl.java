package com.gpro.consulting.tiers.gs.domaine.guichet.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gs.coordination.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.tiers.gs.domaine.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.gs.persitance.guichet.IGuichetAnnuelPersistance;
@Component
public class GuichetAnnuelDomaineImpl implements IGuichetAnnuelDomaine{

	/** Service Persisantce */
	@Autowired
	IGuichetAnnuelPersistance guichetAnnuelPersistance;

	@Override
	public Long getNextNumBonEntreeReference() {
		// TODO Auto-generated method stub
		return guichetAnnuelPersistance.getNextNumBonEntreeReference();
	}

	@Override
	public Long getNextNumBonSortieReference() {
		// TODO Auto-generated method stub
		return guichetAnnuelPersistance.getNextNumBonSortieReference();
	}

	@Override
	public Long modifierGuichetBonEntreeAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// TODO Auto-generated method stub
		return guichetAnnuelPersistance.modifierGuichetBonEntreeAnnuel(pGuichetValeur);
	}

	@Override
	public Long modifierGuichetBonSortieAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// TODO Auto-generated method stub
		return guichetAnnuelPersistance.modifierGuichetBonSortieAnnuel(pGuichetValeur);
	}

}
