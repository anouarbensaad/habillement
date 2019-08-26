package com.gpro.consulting.tiers.gs.persitance;

import java.util.List;

import com.gpro.consulting.tiers.gs.coordination.report.value.RequestRechecheMouvementValue;
import com.gpro.consulting.tiers.gs.coordination.value.MouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheMouvementValue;


public interface IMouvementPersistance {

	/**************** recherche multi critere mouvement stock *******************/
	public ResultatRechecheMouvementValue rechercherMouvementMultiCritere(
			RechercheMulticritereMouvementValue pRechercheMulticritereMouvementValue);

	/************** liste mouvement stock **************/
	public List<MouvementStockValue> listeMouvementStock();

	public ResultatRechecheMouvementValue rechercherEtatMouvement(RequestRechecheMouvementValue request);

}
