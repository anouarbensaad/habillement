package com.gpro.consulting.tiers.gc.persitance;

import java.util.List;

import com.gpro.consulting.tiers.gc.coordination.value.CommandeAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereCommandeAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheCommandeAchatValue;

/**
 * @author $Ameni
 *
 */
public interface ICommandeAchatPersistance {
	/**
	 * Methode de Creation d'un Bon de Commande d'Achat dans la base de données
	 * 
	 * @param pCommandeAchatValue
	 * @return reference du Bon de Commande d'Achat
	 */
	public String creerCommandeAchat(CommandeAchatValue pCommandeAchatValue);

	/**
	 * Methode de suppression d'un bon de commande de la base de données
	 * 
	 * @param pId
	 */
	public void supprimerCommandeAchat(Long pId);

	/**
	 * Methode de Modification d'un bon de commande de la base de données
	 * 
	 * @param pCommandeAchatValue
	 * @return reference du Bon de Commande d'Achat
	 */
	public String modifierCommandeAchat(CommandeAchatValue pCommandeAchatValue);

	/**
	 * Methode de recherche d'un Bon de Commande par Id retournant un Objet
	 * Valeur
	 * 
	 * @param pId
	 * @return objet CommandeAchatValue
	 */
	public CommandeAchatValue rechercheCommandeAchatParId(Long pId);

	/**
	 * Methode de recherche de tous les bons Commandes
	 * 
	 * @return List < CommandeAchatValue >
	 */
	public List<CommandeAchatValue> listeCommandeAchat();

	/**
	 * @param pRechercheCommandeAchatMulitCritere
	 * @return ResultatRechecheCommandeAchatValue
	 */
	public ResultatRechecheCommandeAchatValue rechercherCommandeAchatMultiCritere(
			RechercheMulticritereCommandeAchatValue pRechercheCommandeAchatMulitCritere);

}

