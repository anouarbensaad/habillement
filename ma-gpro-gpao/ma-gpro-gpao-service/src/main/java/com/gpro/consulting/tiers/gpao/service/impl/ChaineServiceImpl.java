/**
 * 
 */
package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.value.ChaineValue;
import com.gpro.consulting.tiers.gpao.domaine.IChaineDomaine;
import com.gpro.consulting.tiers.gpao.service.IChaineService;

/**
 * @author $Ameni
 *
 */
@Service
@Transactional
public class ChaineServiceImpl implements IChaineService {

	@Autowired
	IChaineDomaine chaineDomaine;

	/**
	 * Methode de Creation d'un Chaine dans la base de données
	 * 
	 * @param pChaineValue
	 * @return reference
	 */
	@Override
	public String creerChaine(ChaineValue pChaineValue) {
		return chaineDomaine.creerChaine(pChaineValue);
	}

	/**
	 * Methode de suppression d'un Chaine dans la base de données
	 * 
	 * @param pId
	 */
	@Override
	public void supprimerChaine(Long pId) {
		chaineDomaine.supprimerChaine(pId);
	}

	/**
	 * Methode de suppression d'un Chaine de la base de données
	 * 
	 * @param pChaineValue
	 * @return reference
	 */
	@Override
	public String modifierChaine(ChaineValue pChaineValue) {
		return chaineDomaine.modifierChaine(pChaineValue);
	}

	/**
	 * Methode de recherche d'un Chaine par Id retournant un Objet Valeur
	 * 
	 * @param pChaineValue
	 * @return ChaineValue
	 */
	@Override
	public ChaineValue rechercheChaineParId(Long pId) {
		return chaineDomaine.rechercheChaineParId(pId);
	}

	/**
	 * Methode de recherche de tous les Chaines
	 * 
	 * @return List < ChaineValue >
	 */
	@Override
	public List<ChaineValue> listeChaine() {
		return chaineDomaine.listeChaine();
	}
	

}