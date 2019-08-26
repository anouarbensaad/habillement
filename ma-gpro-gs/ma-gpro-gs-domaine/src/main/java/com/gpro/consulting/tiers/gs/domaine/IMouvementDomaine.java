package com.gpro.consulting.tiers.gs.domaine;

import java.util.List;

import com.gpro.consulting.tiers.gs.coordination.value.MouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheMouvementValue;


public interface IMouvementDomaine {

	/********************recherche multi critere mouvement ****************/
 	public ResultatRechecheMouvementValue rechercherMouvementMultiCritere(RechercheMulticritereMouvementValue pRechercheMulticritereMouvementValue);
     
	/************** liste mouvement stock **************/
	public List<MouvementStockValue> listeMouvementStock();
}
