package com.gpro.consulting.tiers.commun.domaine;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheProduitValue;

/**
 * The Interface IProduitPersistance.
 * @author med
 */
public interface IProduitDomaine {

	/**
	 * Creer produit.
	 *
	 * @param pProduitValue the produit value
	 * @return the string
	 */
	public  String creerProduit(ProduitValue pProduitValue);
	
	/**
	 * Supprimer produit.
	 *
	 * @param pProduitId the produit id
	 */
	public  void supprimerProduit(Long pProduitId);
	
	/**
	 * Modifier produit.
	 *
	 * @param pProduitValue the produit value
	 * @return the string
	 */
	public String modifierProduit(ProduitValue pProduitValue);
	
	/**
	 * Recherche produit by id.
	 *
	 * @param pProduitId the produit id
	 * @return the produit value
	 */
	public ProduitValue rechercheProduitById(Long pProduitId);
	
	/**
	 * Liste produit.
	 *
	 * @return the list 
	 */
	public List<ProduitValue> listeProduit();
	
	//recherche multi criteres
	  public ResultatRechecheProduitValue rechercherProduitMultiCritere(RechercheMulticritereProduitValue pRechercheProduitMulitCritere);

	ProduitValue rechercheProduitById(Long pProduitId, boolean allegee);

	
}
