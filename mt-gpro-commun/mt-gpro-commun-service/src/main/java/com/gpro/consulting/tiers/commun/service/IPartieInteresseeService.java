package com.gpro.consulting.tiers.commun.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechechePartieInteresseeValue;

public interface IPartieInteresseeService {
  /****************** ajouter partie interesse *************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerPartieInteressee(PartieInteresseValue pPartieInteresseValue);

  /********************** supprimer partie interesse *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerPartieInteressee(Long pId);

  /********************** modifier partie interesse *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierPartieInteressee(PartieInteresseValue pPartieInteresseValue);

  /********************** recherche partie interesse par Id *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public PartieInteresseValue recherchePartieInteresseeParId(PartieInteresseValue pPartieInteresseValue);

  /********************** afficher liste partie interesse *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < PartieInteresseValue > listePartieInteressee();

  /********************** recherche partie interesse multcritere *****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public ResultatRechechePartieInteresseeValue rechercherPartieInteresseMultiCritere(
    RechercheMulticriterePartieInteresseeValue pRecherchePartieInteresseMulitCritere);

  
  /********************** afficher liste partie interesse Cache*****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < PartieInteresseCacheValue > listePartieInteresseeCache();
  

  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public PartieInteresseValue getById(Long id);

  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public PartieInteresseValue getAbreviationClient(Long id) ;

  }
