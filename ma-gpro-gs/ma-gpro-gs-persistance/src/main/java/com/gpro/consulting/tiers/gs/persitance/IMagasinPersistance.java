package com.gpro.consulting.tiers.gs.persitance;

import java.util.List;

import com.gpro.consulting.tiers.gs.coordination.value.MagasinValue;


// TODO: Auto-generated Javadoc
/**
 * The Interface IMagasinPersistance.
 */
public interface IMagasinPersistance {

	
	  /**
  	 * Creer magasin.
  	 *
  	 * @param pMagasinValue the magasin value
  	 * @return the string
  	 */
  	public String creerMagasin(MagasinValue pMagasinValue);

	  /**
  	 * Supprimer magasin.
  	 *
  	 * @param pId the id
  	 */
  	public void supprimerMagasin(Long pId);
	
	  /**
  	 * Modifier magasin.
  	 *
  	 * @param pMagasinValue the magasin value
  	 * @return the string
  	 */
  	public String modifierMagasin(MagasinValue pMagasinValue);

	  /**
  	 * Recherche magasin par id.
  	 *
  	 * @param pMagasinValue the magasin value
  	 * @return the magasin value
  	 */
  	public MagasinValue rechercheMagasinParId(MagasinValue pMagasinValue);

	  /**
  	 * Liste magasin.
  	 *
  	 * @return the list
  	 */
  	public List < MagasinValue > listeMagasin();
	  
}
