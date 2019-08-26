package com.gpro.consulting.tiers.commun.persistance;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechechePartieInteresseeValue;

public interface IPartieInteresseePersistance {
  /**
   * Méthode de création d'une partie intéressée et de l'inserer dans la base de données avec toutes
   * ses associations.
   * 
   * @param pPartieInteresseValue
   * @return
   */
  public String creerPartieInteressee(PartieInteresseValue pPartieInteresseValue);

  /**
   * Méthode de suppression d'une partie interessee par id.
   * 
   * @param pPartieInteresseValue
   */
  public void supprimerPartieInteressee(Long pId);

  /**
   * Méthode de recheche d'une partie interessee par id retournant un objet Entité.
   * 
   * @param pPartieInteresseValue
   * @return
   */
  public String modifierPartieInteressee(PartieInteresseValue pPartieInteresseValue);

  /**
   * Méthode de recherche d'une partie interessee par id retournant un objet Valeur.
   * 
   * @param pPartieInteresseValue
   * @return
   */
  public PartieInteresseValue recherchePartieInteresseeParId(PartieInteresseValue pPartieInteresseValue);

  /**
   * Méthode de recherche des parties interessees retournant un objet Valeur.
   * 
   * @return
   */
  public List < PartieInteresseValue > listePartieInteressee();

  /**
   * Méthode de recherche des parties interessees par critères
   * 
   * @return
   */
  public ResultatRechechePartieInteresseeValue rechercherPartieInteresseMultiCritere(
    RechercheMulticriterePartieInteresseeValue pRecherchePartieInteresseMulitCritere);

  
  public PartieInteresseValue recherchePartieInteresseeParId(Long id);
  
  /********************** afficher liste partie interesse Cache*****************************/
  public List < PartieInteresseCacheValue > listePartieInteresseeCache();

  
  public PartieInteresseValue getById(Long id);
  
  public PartieInteresseValue getAbreviationClient(Long id);
}
