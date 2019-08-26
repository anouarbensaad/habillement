package com.gpro.consulting.tiers.gs.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.gs.coordination.value.MagasinValue;
import com.gpro.consulting.tiers.gs.domaine.IMagasinDomaine;
import com.gpro.consulting.tiers.gs.service.IMagasinService;

/**
 * The Class MagasinServiceImpl.
 */
@Service
@Transactional
public class MagasinServiceImpl   implements IMagasinService{
     
     /** The magasin domaine. */
     @Autowired
     IMagasinDomaine magasinDomaine;
	
	/* (non-Javadoc)
	 * creer
	 */
	@Override
	public String creerMagasin(MagasinValue pMagasinValue) {
		return magasinDomaine.creerMagasin(pMagasinValue);
	}

	/* (non-Javadoc)
	 * supprimer
	 */
	@Override
	public void supprimerMagasin(Long pId) {
		magasinDomaine.supprimerMagasin(pId);
	}

	/* (non-Javadoc)
	 * modifier
	 */
	@Override
	public String modifierMagasin(MagasinValue pMagasinValue) {
		 return magasinDomaine.modifierMagasin(pMagasinValue);
	}

	/* (non-Javadoc)
	 * recherche par id
	 */
	@Override
	public MagasinValue rechercheMagasinParId(MagasinValue pMagasinValue) {
		  return magasinDomaine.rechercheMagasinParId(pMagasinValue);
	}

	/* (non-Javadoc)
	 * liste magasin 
	 */
	@Override
	public List<MagasinValue> listeMagasin() {
		 return magasinDomaine.listeMagasin();
	}	
	
}
