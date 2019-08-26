package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.domaine.ITailleDomaine;
import com.gpro.consulting.tiers.commun.persistance.ITaillePersistance;

public class TailleDomaineImpl implements ITailleDomaine{

	@Autowired
	ITaillePersistance ebTaillePersistance;
	
	/************************ Creation Taille ***************************/
	@Override
	public String creerTaille(TailleValue pTailleValue) {
		return ebTaillePersistance.creerTaille(pTailleValue);
	}

	/*********************** suppression Taille **************************/
	@Override
	public void supprimerTaille(Long pId) {
		ebTaillePersistance.supprimerTaille(pId);
	}

	/************************ Modifier Taille ****************************/
	@Override
	public String modifierTaille(TailleValue pTailleValue) {
		return ebTaillePersistance.modifierTaille(pTailleValue);
	}

	/************************ Rechercher Taille ***************************/
	@Override
	public TailleValue rechercheTailleParId(TailleValue pTailleValue) {
		return ebTaillePersistance.rechercheTailleParId(pTailleValue);
	}

	/************************ Liste des Taille ***************************/
	@Override
	public List<TailleValue> listeTaille() {
		return ebTaillePersistance.listeTaille();
	}

	/************************ Getter & Setter ***************************/
	public ITaillePersistance getEbTaillePersistance() {
		return ebTaillePersistance;
	}

	public void setEbTaillePersistance(ITaillePersistance ebTaillePersistance) {
		this.ebTaillePersistance = ebTaillePersistance;
	}
	

}
