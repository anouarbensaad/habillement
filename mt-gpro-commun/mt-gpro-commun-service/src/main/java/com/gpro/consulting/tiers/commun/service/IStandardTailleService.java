package com.gpro.consulting.tiers.commun.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.StandardTailleValue;

public interface IStandardTailleService {

  /************************** ajouter StandardTaille ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerStandardTaille(StandardTailleValue pStandardTailleValue);

  /********************** supprimer StandardTaille *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerStandardTaille(Long pId);

  /********************** modifier StandardTaille *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierStandardTaille(StandardTailleValue pStandardTailleValue);

  /********************** recherche StandardTaille ****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public StandardTailleValue rechercheStandardTailleParId(StandardTailleValue pStandardTailleValue);

  /****************** afficher liste StandardTaille **************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < StandardTailleValue > listeStandardTaille();

}
