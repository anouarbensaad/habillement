package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.StandardTailleValue;
import com.gpro.consulting.tiers.commun.domaine.IStandardTailleDomaine;
import com.gpro.consulting.tiers.commun.persistance.IStandardTaillePersistance;

public class StandardTailleDomaineImpl implements IStandardTailleDomaine{

	@Autowired
	IStandardTaillePersistance ebStandardTaillePersistance;
	
	/************************ Creation StandardTaille ***************************/
	@Override
	public String creerStandardTaille(StandardTailleValue pStandardTailleValue) {
		return ebStandardTaillePersistance.creerStandardTaille(pStandardTailleValue);
	}

	/*********************** suppression StandardTaille **************************/
	@Override
	public void supprimerStandardTaille(Long pId) {
		ebStandardTaillePersistance.supprimerStandardTaille(pId);
	}

	/************************ Modifier StandardTaille ****************************/
	@Override
	public String modifierStandardTaille(StandardTailleValue pStandardTailleValue) {
		return ebStandardTaillePersistance.modifierStandardTaille(pStandardTailleValue);
	}

	/************************ Rechercher StandardTaille ***************************/
	@Override
	public StandardTailleValue rechercheStandardTailleParId(StandardTailleValue pStandardTailleValue) {
		return ebStandardTaillePersistance.rechercheStandardTailleParId(pStandardTailleValue);
	}

	/************************ Liste des StandardTaille ***************************/
	@Override
	public List<StandardTailleValue> listeStandardTaille() {
		return ebStandardTaillePersistance.listeStandardTaille();
	}
	
	/************************ Getter & Setter ***************************/
	public IStandardTaillePersistance getEbStandardTaillePersistance() {
		return ebStandardTaillePersistance;
	}

	public void setEbStandardTaillePersistance(
			IStandardTaillePersistance ebStandardTaillePersistance) {
		this.ebStandardTaillePersistance = ebStandardTaillePersistance;
	}

}
