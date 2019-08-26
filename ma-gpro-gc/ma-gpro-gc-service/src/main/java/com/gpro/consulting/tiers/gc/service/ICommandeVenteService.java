package com.gpro.consulting.tiers.gc.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.value.CommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereCommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheCommandeVenteValue;

/**
 * @author $Ameni
 *
 */
public interface ICommandeVenteService {
	/**
	 * Methode de Creation d'un Bon de Commande de Vente de la base de données
	 * 
	 * @param pCommandeVenteValue
	 * @return reference du Bon de Commande de vente
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String creerCommandeVente(CommandeVenteValue pCommandeVenteValue);

	/**
	 * Methode de suppression d'un bon de commande de la base de données
	 * 
	 * @param pId
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void supprimerCommandeVente(Long pId);

	/**
	 * Methode de Modification d'un bon de commande de la base de données
	 * 
	 * @param pCommandeVenteValue
	 * @return reference du Bon de Commande de vente
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String modifierCommandeVente(CommandeVenteValue pCommandeVenteValue);

	/**
	 * Methode de recherche d'un Bon de Commande par Id retournant un Objet
	 * Valeur
	 * 
	 * @param pCommandeVenteValue
	 * @return objet CommandeVenteValue
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public CommandeVenteValue rechercheCommandeVenteParId(
			Long pId);

	/**
	 * Methode d'affichage de tous les bons Commandes
	 * 
	 * @return List < CommandeVenteValue >
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<CommandeVenteValue> listeCommandeVente();

	/** Methode de recherche de tous le bons de commandes de vente
	 * @param pRechercheCommandeVenteMulitCritere
	 * @return ResultatRechecheCommandeVenteValue 
	 */
	public ResultatRechecheCommandeVenteValue rechercherCommandeVenteMultiCritere(
		    RechercheMulticritereCommandeVenteValue pRechercheCommandeVenteMulitCritere);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<String> getReferences();


}
