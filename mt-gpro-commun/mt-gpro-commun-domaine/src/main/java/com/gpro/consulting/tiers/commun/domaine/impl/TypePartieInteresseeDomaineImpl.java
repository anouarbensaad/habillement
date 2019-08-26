package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.TypeValue;
import com.gpro.consulting.tiers.commun.domaine.ITypePartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.persistance.ITypePartieInteresseePersistance;

/**
 * The Class TypePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
public class TypePartieInteresseeDomaineImpl implements
		ITypePartieInteresseeDomaine {

	@Autowired
	ITypePartieInteresseePersistance typePartieInteresseePersistance;

	
	/*************create Type Partie Intersesse******************/
	@Override
	public String creerTypePartieInteressee(TypeValue pTypeValue) {
		return typePartieInteresseePersistance.creerTypePartieInteressee(pTypeValue);
	}

	@Override
	public void supprimerTypePartieInteressee(TypeValue pTypeValue) {
		typePartieInteresseePersistance.supprimerTypePartieInteressee(pTypeValue);
		
	}

	@Override
	public String modifierTypePartieInteressee(TypeValue pTypeValue) {
		return typePartieInteresseePersistance.modifierTypePartieInteressee(pTypeValue);
	}

	@Override
	public TypeValue rechercheTypePartieInteresseeParId(TypeValue pTypeValue) {
		return typePartieInteresseePersistance.rechercheTypePartieInteresseeParId(pTypeValue);
	}

	@Override
	public List<TypeValue> listetypePartieInteressee() {
		return typePartieInteresseePersistance.listeTypePartieIntPartieInteressee();
	}

	public ITypePartieInteresseePersistance getTypePartieInteresseePersistance() {
		return typePartieInteresseePersistance;
	}

	public void setTypePartieInteresseePersistance(
			ITypePartieInteresseePersistance typePartieInteresseePersistance) {
		this.typePartieInteresseePersistance = typePartieInteresseePersistance;
	}

	


}
