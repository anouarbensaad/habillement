package com.gpro.consulting.tiers.commun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.StandardTailleValue;
import com.gpro.consulting.tiers.commun.domaine.IStandardTailleDomaine;
import com.gpro.consulting.tiers.commun.service.IStandardTailleService;

@Service
@Transactional
public class StandardTailleServiceImpl implements IStandardTailleService{

	@Autowired
	IStandardTailleDomaine ebStandardTailleDomaine;
	
	/************************ Creation StandardTaille *****************************/
	@Override
	public String creerStandardTaille(StandardTailleValue pStandardTailleValue) {
		return ebStandardTailleDomaine.creerStandardTaille(pStandardTailleValue);
	}
	
	/************************ suppression StandardTaille  ***************************/
	@Override
	public void supprimerStandardTaille(Long pId) {
		ebStandardTailleDomaine.supprimerStandardTaille(pId);
	}

	/************************ Modification StandardTaille ***************************/
	@Override
	public String modifierStandardTaille(StandardTailleValue pStandardTailleValue) {
		return ebStandardTailleDomaine.modifierStandardTaille(pStandardTailleValue);
	}

	/************************** Recherche StandardTaille *****************************/
	@Override
	public StandardTailleValue rechercheStandardTailleParId(StandardTailleValue pStandardTailleValue) {
		return ebStandardTailleDomaine.rechercheStandardTailleParId(pStandardTailleValue);
	}

	@Override
	public List<StandardTailleValue> listeStandardTaille() {
		return ebStandardTailleDomaine.listeStandardTaille();
	}

	/************************** Getter & Setter *****************************/
	public IStandardTailleDomaine getEbStandardTailleDomaine() {
		return ebStandardTailleDomaine;
	}

	public void setEbStandardTailleDomaine(
			IStandardTailleDomaine ebStandardTailleDomaine) {
		this.ebStandardTailleDomaine = ebStandardTailleDomaine;
	}
	
}
