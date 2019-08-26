package com.gpro.consulting.tiers.commun.domaine;

import java.util.List;




import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechechePartieInteresseeValue;

public interface IPartieInteresseeDomaine {
  /****************** ajouter partie interesse *************/
  public String creerPartieInteressee(PartieInteresseValue pPartieInteresseValue);

  /********************** supprimer partie interesse *****************************/
  public void supprimerPartieInteressee(Long pId);

  /********************** modifier partie interesse *****************************/
  public String modifierPartieInteressee(PartieInteresseValue pPartieInteresseValue);

  /********************** recherche partie interesse par Id *****************************/
  public PartieInteresseValue recherchePartieInteresseeParId(PartieInteresseValue pPartieInteresseValue);

  /********************** afficher liste partie interesse *****************************/
  public List < PartieInteresseValue > listePartieInteressee();

  /********************** recherche partie interesse multcritere *****************************/
  public ResultatRechechePartieInteresseeValue rechercherPartieInteresseMultiCritere(
    RechercheMulticriterePartieInteresseeValue pRecherchePartieInteresseMulitCritere);

  
  /********************** afficher liste partie interesse Cache*****************************/
  public List < PartieInteresseCacheValue > listePartieInteresseeCache();

  
  public PartieInteresseValue getById(Long id);
  
  public PartieInteresseValue getAbreviationClient(Long id) ;
  
}
