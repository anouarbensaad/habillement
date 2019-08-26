package com.gpro.consulting.tiers.commun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.domaine.ITailleDomaine;
import com.gpro.consulting.tiers.commun.service.ITailleService;

@Service
@Transactional
public class TailleServiceImpl implements ITailleService{

	@Autowired
	ITailleDomaine ebTailleDomaine;
	
	/************************ Creation Matiere *****************************/
	@Override
	public String creerTaille(TailleValue pTailleValue) {
		return ebTailleDomaine.creerTaille(pTailleValue);
	}

	/************************ suppression Matiere ***************************/
	@Override
	public void supprimerTaille(Long pId) {
		ebTailleDomaine.supprimerTaille(pId);
	}

	/************************ Modification Matiere ***************************/
	@Override
	public String modifierTaille(TailleValue pTailleValue) {
		return ebTailleDomaine.modifierTaille(pTailleValue);
	}

	/************************** Recherche Matiere *****************************/
	@Override
	public TailleValue rechercheTailleParId(TailleValue pTailleValue) {
		return ebTailleDomaine.rechercheTailleParId(pTailleValue);
	}
	
	/************************** Liste des Matieres *****************************/
	@Override
	public List<TailleValue> listeTaille() {
		return ebTailleDomaine.listeTaille();
	}

	/************************** Getter & Setter *****************************/
	public ITailleDomaine getEbTailleDomaine() {
		return ebTailleDomaine;
	}

	public void setEbTailleDomaine(ITailleDomaine ebTailleDomaine) {
		this.ebTailleDomaine = ebTailleDomaine;
	}

	
}
