package com.gpro.consulting.tiers.gs.domaine;

import java.util.List;

import com.gpro.consulting.tiers.gs.coordination.value.MagasinValue;


public interface IMagasinDomaine {

	  public String creerMagasin(MagasinValue pMagasinValue);

	  public void supprimerMagasin(Long pId);
	
	  public String modifierMagasin(MagasinValue pMagasinValue);

	  public MagasinValue rechercheMagasinParId(MagasinValue pMagasinValue);

	  public List < MagasinValue > listeMagasin();
	  
}
