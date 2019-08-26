package com.gpro.consulting.tiers.gs.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gs.coordination.value.MouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheMouvementValue;
import com.gpro.consulting.tiers.gs.domaine.IMouvementDomaine;
import com.gpro.consulting.tiers.gs.persitance.IMouvementPersistance;

@Component
public class MouvementDomaineImpl implements IMouvementDomaine{

	@Autowired
	IMouvementPersistance mouvementPersistance;
	
	@Override
	public ResultatRechecheMouvementValue rechercherMouvementMultiCritere(
			RechercheMulticritereMouvementValue pRechercheMulticritereMouvementValue) {
		
		return mouvementPersistance.rechercherMouvementMultiCritere(pRechercheMulticritereMouvementValue);
	}
	/***liste mouvement***/
	@Override
	public List<MouvementStockValue> listeMouvementStock() {
		
		return mouvementPersistance.listeMouvementStock();
	}
	
	
}
