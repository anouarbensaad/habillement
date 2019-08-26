package com.gpro.consulting.tiers.gs.domaine;

import java.util.List;

import com.gpro.consulting.tiers.gs.coordination.value.RaisonMouvementStockValue;

public interface IRaisonMouvementDomaine {


	  public String creerRaisonMouvementStock(RaisonMouvementStockValue pRaisonMouvementStock);

	  public void supprimerRaisonMouvementStock(Long pId);
	
	  public String modifierRaisonMouvementStock(RaisonMouvementStockValue pRaisonMouvementStockValue);

	  public RaisonMouvementStockValue rechercheRaisonMouvementStockParId(RaisonMouvementStockValue pRaisonMouvementStockValue);

	  public List < RaisonMouvementStockValue > listeRaisonMouvementStock();
	  
}
