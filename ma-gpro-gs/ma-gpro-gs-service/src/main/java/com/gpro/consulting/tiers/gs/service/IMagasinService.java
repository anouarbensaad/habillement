package com.gpro.consulting.tiers.gs.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gs.coordination.value.MagasinValue;


// TODO: Auto-generated Javadoc
/**
 * The Interface IMagasinService.
 */
public interface IMagasinService {

	
	  /**
  	 * Creer magasin.
  	 *
  	 * @param pMagasinValue the magasin value
  	 * @return the string
  	 */
    // transaction methode 
	@Transactional(readOnly = false, rollbackFor = Exception.class)
  	public String creerMagasin(MagasinValue pMagasinValue);

	  /**
  	 * Supprimer magasin.
  	 *
  	 * @param pId the id
  	 */
    // transaction methode 
	@Transactional(readOnly = false, rollbackFor = Exception.class)
  	public void supprimerMagasin(Long pId);
	
	  /**
  	 * Modifier magasin.
  	 *
  	 * @param pMagasinValue the magasin value
  	 * @return the string
  	 */
    // transaction methode 
	@Transactional(readOnly = false, rollbackFor = Exception.class)
  	public String modifierMagasin(MagasinValue pMagasinValue);

	  /**
  	 * Recherche magasin par id.
  	 *
  	 * @param pMagasinValue the magasin value
  	 * @return the magasin value
  	 */
    // transaction methode 
	@Transactional(readOnly = true, rollbackFor = Exception.class)
  	public MagasinValue rechercheMagasinParId(MagasinValue pMagasinValue);

	  /**
  	 * Liste magasin.
  	 *
  	 * @return the list
  	 */
    // transaction methode 
	@Transactional(readOnly = true, rollbackFor = Exception.class)
  	public List < MagasinValue > listeMagasin();
	  
}
