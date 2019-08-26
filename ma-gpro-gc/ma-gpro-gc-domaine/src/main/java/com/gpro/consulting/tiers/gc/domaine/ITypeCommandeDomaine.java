/**
 * 
 */
package com.gpro.consulting.tiers.gc.domaine;

import java.util.List;

import com.gpro.consulting.tiers.gc.coordination.value.TypeCommandeValue;

/**
 * @author $Ameni
 *
 */
public interface ITypeCommandeDomaine {

	/**
	 * Methode de creation d'un nouveau type de commande dans la base de données
	 * 
	 * @param pTypeCommandeValue
	 * @return designation
	 */
	public String creerTypeCommande(TypeCommandeValue pTypeCommandeValue);

	/**
	 * Methode de suppression d'un nouveau type commande de la base de données
	 * 
	 * @param pId
	 */
	public void supprimerTypeCommande(Long pId);

	/**
	 * Methode de modification d'un nouveau type de commande
	 * 
	 * @param pTypeCommandeValue
	 * @return designation
	 */
	public String modifierTypeCommande(TypeCommandeValue pTypeCommandeValue);

	/**
	 * Methode de recherche d'un etat de commande par Id dans la base de données
	 * 
	 * @param pTypeCommandeValue
	 * @return TypeCommandeValue
	 */
	public TypeCommandeValue rechercheTypeCommandeParId(Long pId);

	/**
	 * Methode de recherche de tous les etats de commandes
	 * 
	 * @return List<TypeCommandeValue>
	 */
	public List<TypeCommandeValue> listeTypeCommande();
}
