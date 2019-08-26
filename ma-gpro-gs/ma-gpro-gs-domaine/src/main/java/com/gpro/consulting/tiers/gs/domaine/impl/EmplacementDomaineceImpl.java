package com.gpro.consulting.tiers.gs.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gs.coordination.value.EmplacementValue;
import com.gpro.consulting.tiers.gs.domaine.IEmplacementDomaine;
import com.gpro.consulting.tiers.gs.persitance.IEmplacementPersistance;

@Component
public class EmplacementDomaineceImpl implements IEmplacementDomaine{
     
	@Autowired
	IEmplacementPersistance emplacementPersistance;
	
	@Override
	public String creerEmplacement(EmplacementValue pEmplacementValue) {
		return emplacementPersistance.creerEmplacement(pEmplacementValue);
	}

	@Override
	public void supprimerEmplacement(Long pId) {
		emplacementPersistance.supprimerEmplacement(pId);
		
	}

	@Override
	public String modifierEmplacement(EmplacementValue pEmplacementValue) {
		return emplacementPersistance.modifierEmplacement(pEmplacementValue);
	}

	@Override
	public EmplacementValue rechercheEmplacementValueParId(EmplacementValue pEmplacementValue) {
		return emplacementPersistance.rechercheEmplacementValueParId(pEmplacementValue);
	}

	@Override
	public List<EmplacementValue> listeEmplacementValue() {
		return emplacementPersistance.listeEmplacementValue();
	}
	

}
