package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.domaine.ICouleurDomaine;
import com.gpro.consulting.tiers.commun.persistance.ICouleurPersistance;

public class CouleurDomaineImpl implements ICouleurDomaine{

	@Autowired
	ICouleurPersistance ebCouleurPersistance;
	
	/************************ Creation Couleur ***************************/
	@Override
	public String creerCouleur(CouleurValue pCouleurValue) {
		return ebCouleurPersistance.creerCouleur(pCouleurValue);
	}

	/*********************** suppression Couleur **************************/
	@Override
	public void supprimerCouleur(Long pId) {
		ebCouleurPersistance.supprimerCouleur(pId);
	}

	/************************ Modifier Couleur ****************************/
	@Override
	public String modifierCouleur(CouleurValue pCouleurValue) {
		return ebCouleurPersistance.modifierCouleur(pCouleurValue);
	}

	/************************ Rechercher Couleur ***************************/
	@Override
	public CouleurValue rechercheCouleurParId(CouleurValue pCouleurValue) {
		return ebCouleurPersistance.rechercheCouleurParId(pCouleurValue);
	}

	/************************ Liste des Couleur ***************************/
	@Override
	public List<CouleurValue> listeCouleur() {
		return ebCouleurPersistance.listeCouleur();
	}

	/************************ Getter & Setter ***************************/
	public ICouleurPersistance getEbCouleurPersistance() {
		return ebCouleurPersistance;
	}

	public void setEbCouleurPersistance(ICouleurPersistance ebCouleurPersistance) {
		this.ebCouleurPersistance = ebCouleurPersistance;
	}
	

}
