package com.gpro.consulting.tiers.commun.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheProduitValue;

/**
 * The Interface IProduitPersistance.
 * 
 * @author med
 */
public interface IProduitService {
  /**
   * Creer produit.
   *
   * @param pProduitValue
   *          the produit value
   * @return the string
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerProduit(ProduitValue pProduitValue);

  /**
   * Supprimer produit.
   *
   * @param pProduitId
   *          the produit id
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerProduit(Long pProduitId);

  /**
   * Modifier produit.
   *
   * @param pProduitValue
   *          the produit value
   * @return the string
   */
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierProduit(ProduitValue pProduitValue);

  /**
   * Recherche produit by id.
   *
   * @param pProduitId
   *          the produit id
   * @return the produit value
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public ProduitValue rechercheProduitById(Long pProduitId);

  /**
   * Liste produit.
   *
   * @return the list
   */
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < ProduitValue > listeProduit();

  // recherche multi criteres
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public ResultatRechecheProduitValue rechercherProduitMultiCritere(
    RechercheMulticritereProduitValue pRechercheProduitMulitCritere);

ProduitValue rechercheProduitById(Long pProduitId, boolean allegee);
}
