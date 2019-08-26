package com.gpro.consulting.tiers.gc.persitance;

import java.util.List;

import com.gpro.consulting.tiers.gc.coordination.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheProduitCommandeValue;

/**
 * Produit Commande Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 15/03/2016
 *
 */
public interface IProduitCommandePersistance {

	List<ProduitCommandeValue> getAll();

	ResultatRechecheProduitCommandeValue rechercherMultiCritere(
			RechercheMulticritereProduitCommandeValue request);
	
	//Added on 22/03/2016, by Ameni
	/**
	 * Methode de suppression d'un produitCommande de la base de données
	 * 
	 * @param pId
	 */
	public void supprimerProduitCommande(Long pId);
	
	public ProduitCommandeValue getById(Long id);

}
