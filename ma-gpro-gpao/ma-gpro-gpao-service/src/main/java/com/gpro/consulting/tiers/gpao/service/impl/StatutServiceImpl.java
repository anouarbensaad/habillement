package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.value.StatutOfValue;
import com.gpro.consulting.tiers.gpao.domaine.IStatutDomaine;
import com.gpro.consulting.tiers.gpao.service.IStatutService;

/**
 * @author $Ameni
 *
 */

@Service
@Transactional
public class StatutServiceImpl implements IStatutService {

	@Autowired
	IStatutDomaine statutDomaine;

	/**
	 * Methode de Creation d'un Statut dans la base de données
	 * 
	 * @param pStatutOfValue
	 * @return reference
	 */
	@Override
	public String creerStatut(StatutOfValue pStatutOfValue) {
		return statutDomaine.creerStatut(pStatutOfValue);
	}

	/**
	 * Methode de suppression d'un Statut dans la base de données
	 * 
	 * @param pId
	 */
	@Override
	public void supprimerStatut(Long pId) {
		statutDomaine.supprimerStatut(pId);
	}

	/**
	 * Methode de suppression d'un statut de la base de données
	 * 
	 * @param pStatutOfValue
	 * @return reference
	 */
	@Override
	public String modifierStatut(StatutOfValue pStatutOfValue) {
		return statutDomaine.modifierStatut(pStatutOfValue);
	}

	/**
	 * Methode de recherche d'un statut par Id retournant un Objet
	 * Valeur
	 * 
	 * @param pStatutOfValue
	 * @return StatutOfValue
	 */
	@Override
	public StatutOfValue rechercheStatutParId(Long pId) {
		return statutDomaine.rechercheStatutParId(pId);
	}

	/**
	 * Methode de recherche de tous les statutsOF
	 * 
	 * @return List < StatutOfValue >
	 */
	@Override
	public List<StatutOfValue> listeStatut() {
		return statutDomaine.listeStatut();
	}
	

}
