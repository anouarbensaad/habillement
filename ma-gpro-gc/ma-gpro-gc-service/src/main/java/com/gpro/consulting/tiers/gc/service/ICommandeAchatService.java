package com.gpro.consulting.tiers.gc.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.value.CommandeAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereCommandeAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheCommandeAchatValue;

/**
 * @author $Ameni
 *
 */
public interface ICommandeAchatService {
	/**
	 * Methode de Creation d'un Bon de Commande d'Achat dans la base de données
	 * 
	 * @param pCommandeAchatValue
	 * @return reference du Bon de Commande d'Achat
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String creerCommandeAchat(CommandeAchatValue pCommandeAchatValue);

	/**
	 * Methode de suppression d'un bon de commande de la base de données
	 * 
	 * @param pId
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void supprimerCommandeAchat(Long pId);

	/**
	 * Methode de Modification d'un bon de commande de la base de données
	 * 
	 * @param pCommandeAchatValue
	 * @return reference du Bon de Commande d'Achat
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String modifierCommandeAchat(CommandeAchatValue pCommandeAchatValue);

	/**
	 * Methode de recherche d'un Bon de Commande par Id retournant un Objet
	 * Valeur
	 * 
	 * @param pId
	 * @return objet CommandeAchatValue
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public CommandeAchatValue rechercheCommandeAchatParId(Long pId);

	/**
	 * Methode de recherche de tous les bons Commandes
	 * 
	 * @return List < CommandeAchatValue >
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<CommandeAchatValue> listeCommandeAchat();

	/**
	 * @param pRechercheCommandeAchatMulitCritere
	 * @return ResultatRechecheCommandeAchatValue
	 */
	public ResultatRechecheCommandeAchatValue rechercherCommandeAchatMultiCritere(
			RechercheMulticritereCommandeAchatValue pRechercheCommandeAchatMulitCritere);

}

