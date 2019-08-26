package com.gpro.consulting.tiers.gc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.value.EtatCommandeValue;
import com.gpro.consulting.tiers.gc.domaine.IEtatCommandeDomaine;
import com.gpro.consulting.tiers.gc.service.IEtatCommandeService;

/**
 * @author $Ameni
 *
 */
@Service
@Transactional
public class EtatCommandeServiceImpl implements IEtatCommandeService {

	@Autowired
	IEtatCommandeDomaine etatCommandeDomaine;

	/**
	 * Methode de Creation d'un Bon de Commande de Vente de la base de données
	 * 
	 * @param pEtatCommandeValue
	 * @return reference 
	 */
	@Override
	public String creerEtatCommande(EtatCommandeValue pEtatCommandeValue) {
		return etatCommandeDomaine.creerEtatCommande(pEtatCommandeValue);
	}
	
	/**
	 * Methode de suppression d'un bon de commande de la base de données
	 * 
	 * @param pId
	 */
	@Override
	public void supprimerEtatCommande(Long pId) {
		etatCommandeDomaine.supprimerEtatCommande(pId);
	}

	/**
	 * Methode de suppression d'un bon de commande de la base de données
	 * 
	 * @param pEtatCommandeValue
	 * @return reference 
	 */
	@Override
	public String modifierEtatCommande(EtatCommandeValue pEtatCommandeValue) {
		return etatCommandeDomaine
				.modifierEtatCommande(pEtatCommandeValue);
	}
	
	/**
	 * Methode de recherche d'un Bon de Commande par Id retournant un Objet
	 * Valeur
	 * 
	 * @param pEtatCommandeValue
	 * @return EtatCommandeValue
	 */
	@Override
	public EtatCommandeValue rechercheEtatCommandeParId(
			Long pId) {
		return etatCommandeDomaine
				.rechercheEtatCommandeParId(pId);
	}
	
	/**
	 * Methode de recherche de tous les bons Commandes
	 * 
	 * @return List < EtatCommandeValue >
	 */
	@Override
	public List<EtatCommandeValue> listeEtatCommande() {
		return etatCommandeDomaine.listeEtatCommande();
	}

	/***************************** Getter & Setter ********************************/
	/**
	 * @return the etatCommandeDomaine
	 */
	public IEtatCommandeDomaine getEtatCommandeDomaine() {
		return etatCommandeDomaine;
	}

	/**
	 * @param etatCommandeDomaine the etatCommandeDomaine to set
	 */
	public void setEtatCommandeDomaine(IEtatCommandeDomaine etatCommandeDomaine) {
		this.etatCommandeDomaine = etatCommandeDomaine;
	}
	
}
