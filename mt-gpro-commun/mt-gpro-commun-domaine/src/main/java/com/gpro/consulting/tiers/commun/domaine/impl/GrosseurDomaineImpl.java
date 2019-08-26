package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.GrosseurValue;
import com.gpro.consulting.tiers.commun.domaine.IGrosseurDomaine;
import com.gpro.consulting.tiers.commun.persistance.IGrosseurPersistance;

public class GrosseurDomaineImpl implements IGrosseurDomaine{

	@Autowired
	IGrosseurPersistance ebGrosseurPersistance;
	
	/************************ Creation Grosseur ***************************/
	@Override
	public String creerGrosseur(GrosseurValue pGrosseurValue) {
		return ebGrosseurPersistance.creerGrosseur(pGrosseurValue);
	}

	/*********************** suppression Grosseur **************************/
	@Override
	public void supprimerGrosseur(Long pId) {
		ebGrosseurPersistance.supprimerGrosseur(pId);
	}

	/************************ Modifier Grosseur ****************************/
	@Override
	public String modifierGrosseur(GrosseurValue pGrosseurValue) {
		return ebGrosseurPersistance.modifierGrosseur(pGrosseurValue);
	}

	/************************ Rechercher Grosseur ***************************/
	@Override
	public GrosseurValue rechercheGrosseurParId(GrosseurValue pGrosseurValue) {
		return ebGrosseurPersistance.rechercheGrosseurParId(pGrosseurValue);
	}

	/************************ Liste des Grosseur ***************************/
	@Override
	public List<GrosseurValue> listeGrosseur() {
		return ebGrosseurPersistance.listeGrosseur();
	}

	/************************ Getter & Setter ***************************/
	public IGrosseurPersistance getEbGrosseurPersistance() {
		return ebGrosseurPersistance;
	}

	public void setEbGrosseurPersistance(IGrosseurPersistance ebGrosseurPersistance) {
		this.ebGrosseurPersistance = ebGrosseurPersistance;
	}
	

}
