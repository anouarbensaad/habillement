package com.gpro.consulting.tiers.gs.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.gpao.coordination.vue.MouvementOfVue;
import com.gpro.consulting.tiers.gs.coordination.value.BonMouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereBonMouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatListeBonMvtParTypeValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheBonMouvementStockValue;
import com.gpro.consulting.tiers.gs.domaine.IBonMouvementDomaine;
import com.gpro.consulting.tiers.gs.service.IBonMouvementService;


// TODO: Auto-generated Javadoc
/**
 * The Class BonMouvementServiceImpl.
 */
@Service
@Transactional
public class BonMouvementServiceImpl implements IBonMouvementService{

	@Autowired
	IBonMouvementDomaine bonMouvementDomaine;
	
	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.service.IBonMouvementService#rechercherBonMouvementMultiCritere(com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereBonMouvementStockValue)
	 */
	@Override
	public ResultatRechecheBonMouvementStockValue rechercherBonMouvementMultiCritere(
			RechercheMulticritereBonMouvementStockValue pRechercheMulticritereMouvementStockValue) {
		return bonMouvementDomaine.rechercherBonMouvementMultiCritere(pRechercheMulticritereMouvementStockValue);
	}
	
	
	/* (non-Javadoc)
	 * @see com.gpro.consulting.tiers.gs.service.IBonMouvementService#creerBonMouvement(com.gpro.consulting.tiers.gs.coordination.value.BonMouvementStockValue)
	 */
	@Override
	public String creerBonMouvement(
			BonMouvementStockValue pBonMouvementStockValue) {
	  return bonMouvementDomaine.creerBonMouvement(pBonMouvementStockValue);
	}

	/********modifier bon mouvement********/
	@Override
	public String modifierBonMouvement(BonMouvementStockValue pBonMouvement) {
		return bonMouvementDomaine.modifierBonMouvement(pBonMouvement);
	}

	/*******list bon mouvement *****/
	@Override
	public List<BonMouvementStockValue> listeBonMouvement() {
		return bonMouvementDomaine.listeBonMouvement();
	}
    /******supprimer bon mouvement*****/
	@Override
	public void supprimerBonMouvement(Long pId) {
		bonMouvementDomaine.supprimerBonMouvement(pId);
		
	}

	/**********recherche par type bon mouvement*********/
	@Override
	public List<BonMouvementStockValue> getByTypeBonMouvement(String type) {
		return bonMouvementDomaine.getByTypeBonMouvement(type);
	}

	/********recherche par id******/
	@Override
	public BonMouvementStockValue rechercheBonMouvementParId(
			Long pBonMouvementId) {
		return bonMouvementDomaine.rechercheBonMouvementParId(pBonMouvementId);
	}


	//Added on 09/11/2016, by Zeineb Medimagh
	@Override
	public List<ResultatListeBonMvtParTypeValue> getListeNumerosParType(String type) {
		return bonMouvementDomaine.getListeNumerosParType(type);
	}


	@Override
	public List<MouvementOfVue> getListeMouvementsSortie(Long bonMouvementId) {
		return bonMouvementDomaine.getListeMouvementsSortie(bonMouvementId);
	}


	@Override
	public boolean ofIsReserved(Long ofId) {
		return this.bonMouvementDomaine.ofIsReserved(ofId);
	} 
	
	

}
