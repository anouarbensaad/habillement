package com.gpro.consulting.tiers.commun.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;

public interface ITailleService {
  /************************** ajouter Taille ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerTaille(TailleValue pTailleValue);

  /********************** supprimer Taille *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerTaille(Long pId);

  /********************** modifier Taille *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierTaille(TailleValue pTailleValue);

  /********************** recherche Taille ****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public TailleValue rechercheTailleParId(TailleValue pTailleValue);

  /****************** afficher liste Taille **************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < TailleValue > listeTaille();

}
