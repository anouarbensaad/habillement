package com.gpro.consulting.tiers.gs.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.gpro.consulting.tiers.gs.coordination.value.MouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheMouvementValue;
import com.gpro.consulting.tiers.gs.domaine.IMouvementDomaine;
import com.gpro.consulting.tiers.gs.service.IMouvementService;
// TODO: Auto-generated Javadoc

/**
 * The Class MouvementServiceImpl.
 */
@Service
@Transactional
public class MouvementServiceImpl     implements IMouvementService{

	/** The mouvement domaine. */
	@Autowired
	IMouvementDomaine mouvementDomaine;
	
	/* (non-Javadoc)
	 */
	@Override
	public ResultatRechecheMouvementValue rechercherMouvementMultiCritere( @RequestBody final RechercheMulticritereMouvementValue pRechercheMulticritereMouvementValue) {
		return mouvementDomaine.rechercherMouvementMultiCritere(pRechercheMulticritereMouvementValue);
	}

	/******list mouvement stock*****/
	@Override
	public List<MouvementStockValue> listeMouvementStock() {
		return mouvementDomaine.listeMouvementStock();
	}

}
