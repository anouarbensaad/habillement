package com.gpro.consulting.tiers.gc.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheProduitCommandeValue;

/**
 * Produit Commande Service Interface
 * 
 * @author Wahid Gazzah
 * @since 15/03/2016
 *
 */

public interface IProduitCommandeService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	List<ProduitCommandeValue> getAll();

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	ResultatRechecheProduitCommandeValue rechercherMultiCritere(
			RechercheMulticritereProduitCommandeValue request);

	// Added on 22/03/2016, by Ameni
	/**
	 * Methode de suppression d'un produitCommande de la base de données
	 * 
	 * @param pId
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public void supprimerProduitCommande(Long pId);
}
