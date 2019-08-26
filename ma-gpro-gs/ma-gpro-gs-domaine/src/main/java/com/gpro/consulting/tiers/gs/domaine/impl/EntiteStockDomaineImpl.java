package com.gpro.consulting.tiers.gs.domaine.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gs.coordination.value.EntiteStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereEntiteStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.ReferenceEntiteStockConcatineeValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheEntiteStockStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechercheEntiteStockMouvementValue;
import com.gpro.consulting.tiers.gs.domaine.IEntiteStockDomaine;
import com.gpro.consulting.tiers.gs.persitance.IEntiteStockPersistance;

@Component
public class EntiteStockDomaineImpl implements IEntiteStockDomaine{
	
	@Autowired
	IEntiteStockPersistance entiteStockPersistance;
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	@Override
	public ResultatRechecheEntiteStockStockValue rechercherEntiteStockMultiCritere(
			RechercheMulticritereEntiteStockValue pRechercheMulticritereEntiteStockValue) {
			
	    //System.out.println("-------domaine pRechercheMulticritereEntiteStockValue.getQuantite():----- "+ pRechercheMulticritereEntiteStockValue.getQuantite());

		ResultatRechecheEntiteStockStockValue result = entiteStockPersistance.rechercherEntiteStockMultiCritere(pRechercheMulticritereEntiteStockValue);
		
		if(result != null){
			for(EntiteStockValue value : result.getEntiteStock()){
				if(value.getQteActuelle()!=null && value.getPmp()!=null)
					value.setPrixTotal(value.getQteActuelle() * value.getPmp());
			}
		}
		
		return result;
	}
	/*****liste entite stock****/
	@Override
	public List<EntiteStockValue> listeEntiteStock() {
		  
		  return entiteStockPersistance.listeEntiteStock();
	}
     /**********recherche entite stock mouvement ******************/
	@Override
	public ResultatRechercheEntiteStockMouvementValue rechercherEntiteStockMouvement(
			RechercheMulticritereEntiteStockValue pRechercheMulticritereEntiteStockValue) {
		return entiteStockPersistance.rechercherEntiteStockMouvement(pRechercheMulticritereEntiteStockValue);
	}

	
	
	
	public IEntiteStockPersistance getEntiteStockPersistance() {
		return entiteStockPersistance;
	}
	public void setEntiteStockPersistance(
			IEntiteStockPersistance entiteStockPersistance) {
		this.entiteStockPersistance = entiteStockPersistance;
	}
	@Override
	public String creerEntiteStock(EntiteStockValue pEntiteStockValue) {
		return entiteStockPersistance.creerEntiteStock(pEntiteStockValue);
	}
	@Override
	public String modifierEntiteStock(EntiteStockValue pEntiteStockValue) {
		return entiteStockPersistance.modifierEntiteStock(pEntiteStockValue);
	}
	@Override
	public void supprimerEntiteStock(Long pId) {
      entiteStockPersistance.supprimerEntiteStock(pId);		
	}
	@Override
	public EntiteStockValue rechercheEntiteStockParId(Long pEntiteStockId) {
		return entiteStockPersistance.rechercheEntiteStockParId(pEntiteStockId);
	}
	@Override
	public List<ReferenceEntiteStockConcatineeValue> getListeReferencesArticleConcatines() {

		List<EntiteStockValue> liste = this.listeEntiteStock();
		List<ReferenceEntiteStockConcatineeValue> listeReferences = new ArrayList<ReferenceEntiteStockConcatineeValue>();
		
		for (EntiteStockValue entiteStockValue : liste) {
			String reference = "";
			
			if(entiteStockValue.getReferenceArticle()!=null){
				reference+=entiteStockValue.getReferenceArticle();
				reference+=" ";
			}
			
			if(entiteStockValue.getReferenceLot()!=null){
				reference+=entiteStockValue.getReferenceLot();
				reference+=" ";
			}
			
			if(entiteStockValue.getDateEntree()!=null){
				
				
				SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);

				String dateEntreeFormatee = df.format(entiteStockValue.getDateEntree().getTime());
				if(dateEntreeFormatee != null){
					reference+=dateEntreeFormatee;
				}
			}
			
			ReferenceEntiteStockConcatineeValue element = new ReferenceEntiteStockConcatineeValue(entiteStockValue.getId(),reference);
			listeReferences.add(element);
			
		}
		
		return listeReferences;
	}
	
	
}
