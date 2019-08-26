package com.gpro.consulting.tiers.gc.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gc.coordination.value.CommandeAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereCommandeAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheCommandeAchatValue;
import com.gpro.consulting.tiers.gc.domaine.ICommandeAchatDomaine;
import com.gpro.consulting.tiers.gc.persitance.ICommandeAchatPersistance;

/**
 * @author $Ameni
 *
 */

@Component
public class CommandeAchatDomaineImpl implements ICommandeAchatDomaine{
	
	@Autowired
	ICommandeAchatPersistance commandeAchatPersistance;

	/**
	 * Methode de Creation d'un Bon de Commande de Achat de la base de données
	 * 
	 * @param pCommandeAchatValue
	 * @return reference du Bon de Commande de Achat
	 */
	@Override
	public String creerCommandeAchat(CommandeAchatValue pCommandeAchatValue) {
		return commandeAchatPersistance.creerCommandeAchat(pCommandeAchatValue);
	}

	/**
	 * Methode de suppression d'un bon de commande de la base de données
	 * 
	 * @param pId
	 */
	@Override
	public void supprimerCommandeAchat(Long pId) {
		commandeAchatPersistance.supprimerCommandeAchat(pId);
	}

	/**
	 * Methode de Modification d'un bon de commande d'Achat de la base de données
	 * 
	 * @param pCommandeAchatValue
	 * @return reference du Bon de Commande d'Achat
	 */
	@Override
	public String modifierCommandeAchat(CommandeAchatValue pCommandeAchatValue) {
		return commandeAchatPersistance
				.modifierCommandeAchat(pCommandeAchatValue);
	}

	/**
	 * Methode de recherche d'un Bon de Commande d'Achat par Id retournant un Objet
	 * Valeur
	 * 
	 * @param pCommandeAchatValue
	 * @return objet CommandeAchatValue
	 */
	@Override
	public CommandeAchatValue rechercheCommandeAchatParId(
			Long  pId) {
		return commandeAchatPersistance
				.rechercheCommandeAchatParId(pId);
	}

	/**
	 * Methode de recherche de tous les bons Commandes d'Achat
	 * 
	 * @return List < CommandeAchatValue >
	 */
	@Override
	public List<CommandeAchatValue> listeCommandeAchat() {
		return commandeAchatPersistance.listeCommandeAchat();
	}

	
	/** recherche multi critere  Bon de Commande d'Achat 
	 * 
	 */
	@Override
	public ResultatRechecheCommandeAchatValue rechercherCommandeAchatMultiCritere(
			RechercheMulticritereCommandeAchatValue pRechercheCommandeAchatMulitCritere) {
		return commandeAchatPersistance.rechercherCommandeAchatMultiCritere(pRechercheCommandeAchatMulitCritere);
	}

	
}
