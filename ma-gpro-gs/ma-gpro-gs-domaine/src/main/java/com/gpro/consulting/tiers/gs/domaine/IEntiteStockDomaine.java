package com.gpro.consulting.tiers.gs.domaine;

import java.util.List;

import com.gpro.consulting.tiers.gs.coordination.value.EntiteStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereEntiteStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.ReferenceEntiteStockConcatineeValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheEntiteStockStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechercheEntiteStockMouvementValue;


public interface IEntiteStockDomaine {
	
  	
	/******** recherche entite stock  for  Mouvement *******/
	public ResultatRechercheEntiteStockMouvementValue rechercherEntiteStockMouvement(
			RechercheMulticritereEntiteStockValue pRechercheMulticritereEntiteStockValue);
	
	/*********recherche multi critere entite stock********/
 	public ResultatRechecheEntiteStockStockValue rechercherEntiteStockMultiCritere(RechercheMulticritereEntiteStockValue pRechercheMulticritereEntiteStockValue);
     
 	/******* list entite stock *********/
	public List<EntiteStockValue> listeEntiteStock();
	
	/******* creer entite stock ******/
	public String creerEntiteStock(EntiteStockValue pEntiteStockValue);

	/******** modifier entite stock ***********/
	public String modifierEntiteStock(EntiteStockValue pEntiteStockValue);
	/*******supprimer enti stock*****/
	public void supprimerEntiteStock(Long pId);
	
	/****************recherche entite stock par id****************/
  	public EntiteStockValue rechercheEntiteStockParId(Long pEntiteStockId);
  	
  	/****************get liste refArticle+lot+date concatinés****************/
  	public List<ReferenceEntiteStockConcatineeValue> getListeReferencesArticleConcatines();
	
	
}
