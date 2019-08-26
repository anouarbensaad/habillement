package com.gpro.consulting.tiers.commun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.domaine.ICouleurDomaine;
import com.gpro.consulting.tiers.commun.service.ICouleurService;

@Service
@Transactional
public class CouleurServiceImpl implements ICouleurService{

	@Autowired
	ICouleurDomaine ebCouleurDomaine;
	
	/************************ Creation Matiere *****************************/
	@Override
	public String creerCouleur(CouleurValue pCouleurValue) {
		return ebCouleurDomaine.creerCouleur(pCouleurValue);
	}

	/************************ suppression Matiere ***************************/
	@Override
	public void supprimerCouleur(Long pId) {
		ebCouleurDomaine.supprimerCouleur(pId);
	}

	/************************ Modification Matiere ***************************/
	@Override
	public String modifierCouleur(CouleurValue pCouleurValue) {
		return ebCouleurDomaine.modifierCouleur(pCouleurValue);
	}

	/************************** Recherche Matiere *****************************/
	@Override
	public CouleurValue rechercheCouleurParId(CouleurValue pCouleurValue) {
		return ebCouleurDomaine.rechercheCouleurParId(pCouleurValue);
	}
	
	/************************** Liste des Matieres *****************************/
	@Override
	public List<CouleurValue> listeCouleur() {
		return ebCouleurDomaine.listeCouleur();
	}

	/************************** Getter & Setter *****************************/
	public ICouleurDomaine getEbCouleurDomaine() {
		return ebCouleurDomaine;
	}

	public void setEbCouleurDomaine(ICouleurDomaine ebCouleurDomaine) {
		this.ebCouleurDomaine = ebCouleurDomaine;
	}

	
}
