/**
 * 
 */
package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.DeviseValue;
import com.gpro.consulting.tiers.commun.domaine.IDeviseDomaine;
import com.gpro.consulting.tiers.commun.persistance.IDevisePersistance;

/**
 * @author ameni
 *
 */
public class DevisePartieInteresseeDomaineImpl implements IDeviseDomaine {

	@Autowired
	IDevisePersistance devisePartieInteressePersistance;

	/************************ Creation Devise ***************************/
	@Override
	public String creerDevise(DeviseValue pDeviseValue) {
		return devisePartieInteressePersistance.creerDevise(pDeviseValue);
	}

	/*********************** suppression Devise **************************/
	@Override
	public void supprimerDevise(Long pId) {
		devisePartieInteressePersistance.supprimerDevise(pId);
	}

	/************************ Modifier Devise ****************************/
	@Override
	public String modifierDevise(DeviseValue pDeviseValue) {
		return devisePartieInteressePersistance.modifierDevise(pDeviseValue);
	}

	/************************ Rechercher Devise ***************************/
	@Override
	public DeviseValue rechercheDeviseParId(DeviseValue pDeviseValue) {
		return devisePartieInteressePersistance
				.rechercheDeviseParId(pDeviseValue);
	}

	/************************ Liste des Devise ***************************/
	@Override
	public List<DeviseValue> listeDevise() {
		return devisePartieInteressePersistance.listeDevise();
	}

	/**
	 * @return the devisePartieInteressePersistance
	 */
	public IDevisePersistance getDevisePartieInteressePersistance() {
		return devisePartieInteressePersistance;
	}

	/**
	 * @param devisePartieInteressePersistance
	 *            the devisePartieInteressePersistance to set
	 */
	public void setDevisePartieInteressePersistance(
			IDevisePersistance devisePartieInteressePersistance) {
		this.devisePartieInteressePersistance = devisePartieInteressePersistance;
	}

}