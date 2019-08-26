package com.gpro.consulting.tiers.gs.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.gs.coordination.value.RaisonMouvementStockValue;
import com.gpro.consulting.tiers.gs.domaine.IRaisonMouvementDomaine;
import com.gpro.consulting.tiers.gs.service.IRaisonMouvementService;
// TODO: Auto-generated Javadoc

/**
 * The Class RaisonMouvementServiceImpl.
 */
@Service
@Transactional
public class RaisonMouvementServiceImpl   implements IRaisonMouvementService{
    
    /** The raison mouvement domaine. */
    @Autowired
    IRaisonMouvementDomaine raisonMouvementDomaine;
	
	/* (non-Javadoc)
	 * creer
	 */
	@Override
	public String creerRaisonMouvementStock(
			RaisonMouvementStockValue pRaisonMouvementStock) {
		return raisonMouvementDomaine.creerRaisonMouvementStock(pRaisonMouvementStock);
	}

	/* (non-Javadoc)
	 * supprimer
	 */
	@Override
	public void supprimerRaisonMouvementStock(Long pId) {
            raisonMouvementDomaine.supprimerRaisonMouvementStock(pId);		
	}

	/* (non-Javadoc)
	 * modifier
	 */
	@Override
	public String modifierRaisonMouvementStock(
			RaisonMouvementStockValue pRaisonMouvementStockValue) {
		   return raisonMouvementDomaine.modifierRaisonMouvementStock(pRaisonMouvementStockValue);
	}

	/* (non-Javadoc)
	 * recherche par id
	 */
	@Override
	public RaisonMouvementStockValue rechercheRaisonMouvementStockParId(
			RaisonMouvementStockValue pRaisonMouvementStockValue) {
		  return raisonMouvementDomaine.rechercheRaisonMouvementStockParId(pRaisonMouvementStockValue);
	}

	/* (non-Javadoc)
	 * liste raison
	 * 
	 */
	@Override
	public List<RaisonMouvementStockValue> listeRaisonMouvementStock() {
		return raisonMouvementDomaine.listeRaisonMouvementStock();
	}
	
}
