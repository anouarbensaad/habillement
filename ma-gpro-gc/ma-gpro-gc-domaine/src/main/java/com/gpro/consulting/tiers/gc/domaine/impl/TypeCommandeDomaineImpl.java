package com.gpro.consulting.tiers.gc.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gc.coordination.value.TypeCommandeValue;
import com.gpro.consulting.tiers.gc.domaine.ITypeCommandeDomaine;
import com.gpro.consulting.tiers.gc.persitance.ITypeCommandePersistance;

/**
 * @author $Ameni
 *
 */

@Component
public class TypeCommandeDomaineImpl implements ITypeCommandeDomaine{
	
	@Autowired
	ITypeCommandePersistance typeCommandePersistance;
	
	@Override
	public String creerTypeCommande(TypeCommandeValue pTypeCommandeValue) {
		return typeCommandePersistance.creerTypeCommande(pTypeCommandeValue);
	}

	@Override
	public void supprimerTypeCommande(Long pId) {
		typeCommandePersistance.supprimerTypeCommande(pId);
	}

	@Override
	public String modifierTypeCommande(TypeCommandeValue pTypeCommandeValue) {
		return typeCommandePersistance
				.modifierTypeCommande(pTypeCommandeValue);
	}

	@Override
	public TypeCommandeValue rechercheTypeCommandeParId(Long pId) {
		return typeCommandePersistance
				.rechercheTypeCommandeParId(pId);
	}

	@Override
	public List<TypeCommandeValue> listeTypeCommande() {
		return typeCommandePersistance.listeTypeCommande();
	}

	
}
