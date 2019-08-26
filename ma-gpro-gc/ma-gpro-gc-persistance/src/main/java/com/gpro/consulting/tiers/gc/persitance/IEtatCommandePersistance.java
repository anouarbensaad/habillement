package com.gpro.consulting.tiers.gc.persitance;

import java.util.List;

import com.gpro.consulting.tiers.gc.coordination.value.EtatCommandeValue;

/**
 * @author $Ameni
 *
 */
public interface IEtatCommandePersistance {

	/**Methode de creation d'un nouveau etat de commande dans la base de données
	 * @param pEtatCommandeValue
	 * @return designation
	 */
	public  String creerEtatCommande(EtatCommandeValue pEtatCommandeValue);
	
	/**Methode de suppression d'un etat d'une commande de la base de données
	 * @param pId
	 */
	public  void supprimerEtatCommande(Long pId);
	
	/**Methode de modification d'un etat de commande 
	 * @param pEtatCommandeValue
	 * @return
	 */
	public String modifierEtatCommande(EtatCommandeValue pEtatCommandeValue);
	
	/** Methode de recherche d'un etat de commande par Id dans la base de données
	 * @param pEtatCommandeValue
	 * @return
	 */
	public EtatCommandeValue rechercheEtatCommandeParId(Long pId);
	
	/** Methode de recherche de tous les etats de commandes
	 * @return List<EtatCommandeValue>
	 */
	public List<EtatCommandeValue> listeEtatCommande();
}
