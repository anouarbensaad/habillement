package com.gpro.consulting.tiers.gc.domaine;

import java.util.List;

import com.gpro.consulting.tiers.gc.coordination.value.CommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereCommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheCommandeVenteValue;

/**
 * @author $Ameni
 *
 */
public interface ICommandeVenteDomaine {
	
	/** Methode de Creation d'un Bon de Commande de Vente de la base de données
	 * @param pCommandeVenteValue
	 * @return 
	 */
	public String creerCommandeVente(CommandeVenteValue pCommandeVenteValue);

	/**Methode de suppression d'un bon de commande de la base de données
	 * @param pId
	 */
	public void supprimerCommandeVente(Long pId);
	
	/**Methode de suppression d'un bon de commande de la base de données
	 * @param pCommandeVenteValue
	 * @return
	 */
	public String modifierCommandeVente(CommandeVenteValue pCommandeVenteValue);

	/** Methode de recherche d'un Bon de Commande par Id retournant un Objet Valeur
	 * @param pCommandeVenteValue
	 * @return pId
	 */
	public CommandeVenteValue rechercheCommandeVenteParId(Long pId);

	/** Methode d'affichage  de tous les bons Commandes 
	 * @returnList < CommandeVenteValue >
	 */
	public List < CommandeVenteValue > listeCommandeVente();

	/** Methode de recherche de tous les bons Commandes ds la Base de données
	 * @param pRechercheCommandeVenteMulitCritere
	 * @return
	 */
	public ResultatRechecheCommandeVenteValue rechercherCommandeVenteMultiCritere(
			    RechercheMulticritereCommandeVenteValue pRechercheCommandeVenteMulitCritere);

	public List<String> getReferences();

	public boolean existeOF(String numeroOF);

}
