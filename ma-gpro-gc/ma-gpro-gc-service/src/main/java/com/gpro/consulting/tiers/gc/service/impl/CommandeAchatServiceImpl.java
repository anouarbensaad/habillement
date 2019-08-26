package com.gpro.consulting.tiers.gc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.value.CommandeAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereCommandeAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheCommandeAchatValue;
import com.gpro.consulting.tiers.gc.domaine.ICommandeAchatDomaine;
import com.gpro.consulting.tiers.gc.service.ICommandeAchatService;

/**
 * @author $Ameni
 *
 */

@Service
@Transactional
public class CommandeAchatServiceImpl implements ICommandeAchatService {

	@Autowired
	ICommandeAchatDomaine commandeAchatDomaine;

	/**
	 * Methode de Creation d'un Bon de Commande d'Achat de la base de données
	 * 
	 * @param pCommandeAchatValue
	 * @return reference
	 */
	@Override
	public String creerCommandeAchat(CommandeAchatValue pCommandeAchatValue) {
		return commandeAchatDomaine.creerCommandeAchat(pCommandeAchatValue);
	}

	/**
	 * Methode de suppression d'un bon de commande d'achat de la base de données
	 * 
	 * @param pId
	 */
	@Override
	public void supprimerCommandeAchat(Long pId) {
		commandeAchatDomaine.supprimerCommandeAchat(pId);
	}

	/**
	 * Methode de suppression d'un bon de commande d'achat de la base de données
	 * 
	 * @param pCommandeAchatValue
	 * @return reference
	 */
	@Override
	public String modifierCommandeAchat(CommandeAchatValue pCommandeAchatValue) {
		return commandeAchatDomaine.modifierCommandeAchat(pCommandeAchatValue);
	}

	/**
	 * Methode de recherche d'un Bon de Commande par Id retournant un Objet
	 * Valeur
	 * 
	 * @param pCommandeAchatValue
	 * @return CommandeAchatValue
	 */
	@Override
	public CommandeAchatValue rechercheCommandeAchatParId(Long pId) {
		return commandeAchatDomaine.rechercheCommandeAchatParId(pId);
	}

	/**
	 * Methode d'affichage de tous les bons Commandes
	 * 
	 * @return List < CommandeAchatValue >
	 */
	@Override
	public List<CommandeAchatValue> listeCommandeAchat() {
		return commandeAchatDomaine.listeCommandeAchat();
	}

	/**
	 * @param pRechercheCommandeAchatMulitCritere
	 * @return ResultatRechecheCommandeAchatValue
	 */
	@Override
	public ResultatRechecheCommandeAchatValue rechercherCommandeAchatMultiCritere(
			RechercheMulticritereCommandeAchatValue pRechercheCommandeAchatMulitCritere) {
		return commandeAchatDomaine
				.rechercherCommandeAchatMultiCritere(pRechercheCommandeAchatMulitCritere);
	}

	/**
	 * @return the commandeAchatDomaine
	 */
	public ICommandeAchatDomaine getCommandeAchatDomaine() {
		return commandeAchatDomaine;
	}

	/**
	 * @param commandeAchatDomaine the commandeAchatDomaine to set
	 */
	public void setCommandeAchatDomaine(ICommandeAchatDomaine commandeAchatDomaine) {
		this.commandeAchatDomaine = commandeAchatDomaine;}
	
}
