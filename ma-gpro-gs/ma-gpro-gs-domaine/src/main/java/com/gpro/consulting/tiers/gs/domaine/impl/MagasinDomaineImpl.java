package com.gpro.consulting.tiers.gs.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gs.coordination.value.MagasinValue;
import com.gpro.consulting.tiers.gs.domaine.IMagasinDomaine;
import com.gpro.consulting.tiers.gs.persitance.IMagasinPersistance;

@Component
public class MagasinDomaineImpl implements IMagasinDomaine{
	
	@Autowired
	IMagasinPersistance magasinPersistance;

	@Override
	public String creerMagasin(MagasinValue pMagasinValue) {
		return magasinPersistance.creerMagasin(pMagasinValue);
	}

	@Override
	public void supprimerMagasin(Long pId) {
		magasinPersistance.supprimerMagasin(pId);
		
	}

	@Override
	public String modifierMagasin(MagasinValue pMagasinValue) {
		return magasinPersistance.modifierMagasin(pMagasinValue);
	}

	@Override
	public MagasinValue rechercheMagasinParId(MagasinValue pMagasinValue) {
		return magasinPersistance.rechercheMagasinParId(pMagasinValue);
	}

	@Override
	public List<MagasinValue> listeMagasin() {
		return magasinPersistance.listeMagasin();
	}
	
	
}
