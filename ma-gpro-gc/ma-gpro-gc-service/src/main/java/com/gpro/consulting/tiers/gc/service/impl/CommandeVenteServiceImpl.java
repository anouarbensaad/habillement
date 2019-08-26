package com.gpro.consulting.tiers.gc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.value.CommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereCommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheCommandeVenteValue;
import com.gpro.consulting.tiers.gc.domaine.ICommandeVenteDomaine;
import com.gpro.consulting.tiers.gc.service.ICommandeVenteService;

@Service
@Transactional
public class CommandeVenteServiceImpl implements ICommandeVenteService {

	@Autowired
	ICommandeVenteDomaine commandeVenteDomaine;

	/**
	 * Methode de Creation d'un Bon de Commande de Vente de la base de données
	 * 
	 * @param pCommandeVenteValue
	 * @return reference 
	 */
	@Override
	public String creerCommandeVente(CommandeVenteValue pCommandeVenteValue) {
		return commandeVenteDomaine.creerCommandeVente(pCommandeVenteValue);
	}
	
	/**
	 * Methode de suppression d'un bon de commande de la base de données
	 * 
	 * @param pId
	 */
	@Override
	public void supprimerCommandeVente(Long pId) {
		commandeVenteDomaine.supprimerCommandeVente(pId);
	}

	/**
	 * Methode de suppression d'un bon de commande de la base de données
	 * 
	 * @param pCommandeVenteValue
	 * @return reference 
	 */
	@Override
	public String modifierCommandeVente(CommandeVenteValue pCommandeVenteValue) {
		return commandeVenteDomaine
				.modifierCommandeVente(pCommandeVenteValue);
	}
	
	/**
	 * Methode de recherche d'un Bon de Commande par Id retournant un Objet
	 * Valeur
	 * 
	 * @param pCommandeVenteValue
	 * @return CommandeVenteValue
	 */
	@Override
	public CommandeVenteValue rechercheCommandeVenteParId(
			Long pId) {
		return commandeVenteDomaine
				.rechercheCommandeVenteParId(pId);
	}
	
	/**
	 * Methode d'affichage de tous les bons Commandes
	 * 
	 * @return List < CommandeVenteValue >
	 */
	@Override
	public List<CommandeVenteValue> listeCommandeVente() {
		return commandeVenteDomaine.listeCommandeVente();
	}
	
	/**
	 * @param pRechercheCommandeVenteMulitCritere
	 * @return ResultatRechecheCommandeVenteValue
	 */
	@Override
	public ResultatRechecheCommandeVenteValue rechercherCommandeVenteMultiCritere(
			RechercheMulticritereCommandeVenteValue pRechercheCommandeVenteMulitCritere) {
		return commandeVenteDomaine.rechercherCommandeVenteMultiCritere(pRechercheCommandeVenteMulitCritere);
	}
	
	
	

	@Override
	public List<String> getReferences() {
		
		return commandeVenteDomaine.getReferences();
	}
	
	
	/***************************** Getter & Setter ********************************/
	/**
	 * @return the commandeVenteDomaine
	 */
	public ICommandeVenteDomaine getCommandeVenteDomaine() {
		return commandeVenteDomaine;
	}

	/**
	 * @param commandeVenteDomaine the commandeVenteDomaine to set
	 */
	public void setCommandeVenteDomaine(ICommandeVenteDomaine commandeVenteDomaine) {
		this.commandeVenteDomaine = commandeVenteDomaine;
	}
	
}
