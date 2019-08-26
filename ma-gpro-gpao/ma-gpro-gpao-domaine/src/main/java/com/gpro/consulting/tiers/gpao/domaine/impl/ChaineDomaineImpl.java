package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.value.ChaineValue;
import com.gpro.consulting.tiers.gpao.domaine.IChaineDomaine;
import com.gpro.consulting.tiers.gpao.persitance.IChainePersistance;

/**
 * @author $Ameni
 *
 */

@Component
public class ChaineDomaineImpl implements IChaineDomaine {

	@Autowired
	IChainePersistance chainePersistance;

	@Override
	public String creerChaine(ChaineValue pChaineValue) {
		return chainePersistance.creerChaine(pChaineValue);
	}

	@Override
	public void supprimerChaine(Long pId) {
		chainePersistance.supprimerChaine(pId);
	}

	@Override
	public String modifierChaine(ChaineValue pChaineValue) {
		return chainePersistance.modifierChaine(pChaineValue);
	}

	@Override
	public ChaineValue rechercheChaineParId(Long pId) {
		return chainePersistance.rechercheChaineParId(pId);
	}

	@Override
	public List<ChaineValue> listeChaine() {
		return chainePersistance.listeChaine();
	}
	

}
