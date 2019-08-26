package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.domaine.ISousFamilleProduitDomaine;
import com.gpro.consulting.tiers.commun.persistance.ISousFamilleProduitPersistance;

// TODO: Auto-generated Javadoc
/**
 * The Class SousFamilleProduitImpl.
 * @author med
 */
public class SousFamilleProduitDomaineImpl implements ISousFamilleProduitDomaine{
	
	/** The sous famille produit persistance. */
	@Autowired
	ISousFamilleProduitPersistance sousFamilleProduitPersistance;

	/* (non-Javadoc)
	 * creer sous famille produit
	 */
	@Override
	public String creerSousFamilleProduit(
			SousFamilleProduitValue pSousFamilleProduitValue) {
		return sousFamilleProduitPersistance.creerSousFamilleProduit(pSousFamilleProduitValue);
	}

	/* (non-Javadoc)
	 * supprimer sous famille produit
	 */
	@Override
	public void supprimerSousFamilleProduit(Long pSousFamilleProduitId) {
		sousFamilleProduitPersistance.supprimerSousFamilleProduit(pSousFamilleProduitId);		
	}

	/* (non-Javadoc)
	 * modifier sous famille produit
	 */
	@Override
	public String modifierSousFamilleProduit(
			SousFamilleProduitValue pSousFamilleProduitValue) {
		return sousFamilleProduitPersistance.modifierSousFamilleProduit(pSousFamilleProduitValue);
	}

	/* (non-Javadoc)
	 * recherche by id sous famille produit
	 */
	@Override
	public SousFamilleProduitValue rechercheSousFamilleProduitById(
			Long pSousFamilleProduitId) {
		return sousFamilleProduitPersistance.rechercheSousFamilleProduitById(pSousFamilleProduitId);
	}

	/* (non-Javadoc)
	 * liste sous famille produit
	 */
	@Override
	public List<SousFamilleProduitValue> listeSousFamilleProduit() {
		return sousFamilleProduitPersistance.listeSousFamilleProduit();
	}

	/**
	 * Gets the sous famille produit persistance.
	 *
	 * @return the sous famille produit persistance
	 */
	public ISousFamilleProduitPersistance getSousFamilleProduitPersistance() {
		return sousFamilleProduitPersistance;
	}

	/**
	 * Sets the sous famille produit persistance.
	 *
	 * @param sousFamilleProduitPersistance the new sous famille produit persistance
	 */
	public void setSousFamilleProduitPersistance(
			ISousFamilleProduitPersistance sousFamilleProduitPersistance) {
		this.sousFamilleProduitPersistance = sousFamilleProduitPersistance;
	}

	
}
