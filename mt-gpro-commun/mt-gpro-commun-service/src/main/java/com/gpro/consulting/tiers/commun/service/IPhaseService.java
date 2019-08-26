package com.gpro.consulting.tiers.commun.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.PhaseValue;

public interface IPhaseService {
  /************************** ajouter Phase ***************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String creerPhase(PhaseValue pPhaseValue);

  /********************** supprimer Phase *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public void supprimerPhase(PhaseValue pPhaseValue);

  /********************** modifier Phase *****************************/
  @Transactional(readOnly = false, rollbackFor = Exception.class)
  public String modifierPhase(PhaseValue pPhaseValue);

  /********************** recherche Phase ****************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public PhaseValue recherchePhaseParId(PhaseValue pPhaseValue);

  /****************** afficher liste Phase **************************/
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List < PhaseValue > listePhase();

}
