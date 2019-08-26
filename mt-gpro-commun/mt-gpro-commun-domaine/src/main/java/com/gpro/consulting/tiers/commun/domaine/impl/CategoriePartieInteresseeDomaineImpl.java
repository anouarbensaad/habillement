package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.CategorieValue;
import com.gpro.consulting.tiers.commun.domaine.ICategoriePartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.persistance.ICategoriePartieInteresseePersistance;

/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
public class CategoriePartieInteresseeDomaineImpl implements
		ICategoriePartieInteresseeDomaine {

	@Autowired
	ICategoriePartieInteresseePersistance categoriePartieInteresseePersistance;

	/*
	 * methode de creation cathegorie Partie Interessée
	 */
	@Override
	public String creerCategoriePartieInteressee(CategorieValue pCategorieValue) {

		return categoriePartieInteresseePersistance
				.creerCategoriePartieInteressee(pCategorieValue);
	}

	
	/*******************supprimer categorie partie interesse **********************/
	@Override
	public void supprimerCategoriePartieInteressee(
			Long pCategorieValue) {
		categoriePartieInteresseePersistance.supprimerCategoriePartieInteressee(pCategorieValue);
		
	}
	
	
	/*******************modifier categorie partie interesse **********************/
	@Override
	public String modifierCategoriePartieInteressee(CategorieValue pCategorieValue) {
		return categoriePartieInteresseePersistance.modifierCategoriePartieInteressee(pCategorieValue);
	}

	/*******************recherce  categorie partie interesse  par id**********************/
	@Override
	public CategorieValue rechercheCategoriePartieInteresseeParId(CategorieValue pCategorieValue) {
		return categoriePartieInteresseePersistance.rechercheCategoriePartieInteresseeParId(pCategorieValue);
	}

	/*******************recherce  categorie partie interesse  par id**********************/
	@Override
	public List<CategorieValue> listeCategoriePartieInteressee() {
		return categoriePartieInteresseePersistance.listeCategoriePartieInteressee();
	}

	
	
	
	
	public ICategoriePartieInteresseePersistance getCategoriePartieInteresseePersistance() {
		return categoriePartieInteresseePersistance;
	}

	public void setCategoriePartieInteresseePersistance(
			ICategoriePartieInteresseePersistance categoriePartieInteresseePersistance) {
		this.categoriePartieInteresseePersistance = categoriePartieInteresseePersistance;
	}


	






	

}
