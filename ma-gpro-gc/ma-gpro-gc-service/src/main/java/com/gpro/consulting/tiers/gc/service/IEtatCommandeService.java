package com.gpro.consulting.tiers.gc.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.value.EtatCommandeValue;

public interface IEtatCommandeService {
	/**
	 * Methode de creation d'un nouveau etat de commande dans la base de données
	 * 
	 * @param pEtatCommandeValue
	 * @return designation
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String creerEtatCommande(EtatCommandeValue pEtatCommandeValue);

	/**
	 * Methode de suppression d'un etat d'une commande de la base de données
	 * 
	 * @param pId
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void supprimerEtatCommande(Long pId);

	/**
	 * Methode de modification d'un etat de commande
	 * 
	 * @param pEtatCommandeValue
	 * @return
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String modifierEtatCommande(EtatCommandeValue pEtatCommandeValue);

	/**
	 * Methode de recherche d'un etat de commande par Id dans la base de données
	 * 
	 * @param pEtatCommandeValue
	 * @return
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public EtatCommandeValue rechercheEtatCommandeParId(Long pId);

	/**
	 * Methode de recherche de tous les etats de commandes
	 * 
	 * @return List<EtatCommandeValue>
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<EtatCommandeValue> listeEtatCommande();
}
