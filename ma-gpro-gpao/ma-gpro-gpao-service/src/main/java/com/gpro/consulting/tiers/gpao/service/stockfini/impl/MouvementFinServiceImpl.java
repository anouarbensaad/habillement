package com.gpro.consulting.tiers.gpao.service.stockfini.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.MouvementFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.RechercheMulticritereMouvementFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.ResultatMulticritereMouvementFiniValue;

import com.gpro.consulting.tiers.gpao.domaine.stockfini.IMouvementFiniDomaine;

import com.gpro.consulting.tiers.gpao.service.stockfini.IMouvementFiniService;


/**
 * @author $Samer
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MouvementFinServiceImpl implements IMouvementFiniService{

	@Autowired
	IMouvementFiniDomaine mouvementFiniDomaine;

	@Override
	public String createMouvementFini(MouvementFiniValue pMouvementFiniValue) {

		return mouvementFiniDomaine.createMouvementFini(pMouvementFiniValue);
	}

	@Override
	public void deleteMouvementFini(Long id) {
		mouvementFiniDomaine.deleteMouvementFini(id);
		
	}

	@Override
	public MouvementFiniValue findMouvementFiniParId(Long id) {
	
		return mouvementFiniDomaine.findMouvementFiniParId(id);
	}

	@Override
	public ResultatMulticritereMouvementFiniValue rechercherMouvementFiniMultiCritere(
			RechercheMulticritereMouvementFiniValue pRechercheMouvementFiniMulitCritere) {
	
		return mouvementFiniDomaine.rechercherMouvementFiniMultiCritere(pRechercheMouvementFiniMulitCritere);
	}



}
