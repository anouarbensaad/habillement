package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.TypeDocumentValue;
import com.gpro.consulting.tiers.commun.domaine.ITypeDocumentDomaine;
import com.gpro.consulting.tiers.commun.persistance.ITypeDocumentPersistance;

// TODO: Auto-generated Javadoc
/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
public class TypeDocumentDomaineImpl implements ITypeDocumentDomaine {

	/** The type document persistance. */
	@Autowired 
	ITypeDocumentPersistance typeDocumentPersistance;

	/* (non-Javadoc)
	 * recherche par id type document
	 */
	@Override
	public TypeDocumentValue rechercheTypeDocumentPartieInteresseeParId(TypeDocumentValue pTypeDocumentValue) {
		return typeDocumentPersistance.rechercheTypeDocumentPartieInteresseeParId(pTypeDocumentValue);
	}

	/* (non-Javadoc)
	 * lister type document 
	 */
	@Override
	public List<TypeDocumentValue> listeTypeDocumentPartieInteressee() {
		 return typeDocumentPersistance.listeTypeDocumentPartieInteressee();
	}

	
	/* (non-Javadoc)
	 * creer type docuement 
	 */
	@Override
	public String creerTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue) {
		return typeDocumentPersistance.creerTypeDocumentPartieInteressee(pTypeDocumentValue);
	}

	/* (non-Javadoc)
	 * supprimer type document 
	 */
	@Override
	public void supprimerTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue) {
		typeDocumentPersistance.supprimerTypeDocumentPartieInteressee(pTypeDocumentValue);		
	}

	/* (non-Javadoc)
	 * modifier type document 
	 */
	@Override
	public String modifierTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue) {
		return typeDocumentPersistance.modifierTypeDocumentPartieInteressee(pTypeDocumentValue);
	}


	/**
	 * Gets the type document persistance.
	 *
	 * @return the type document persistance
	 */
	public ITypeDocumentPersistance getTypeDocumentPersistance() {
		return typeDocumentPersistance;
	}

	/**
	 * Sets the type document persistance.
	 *
	 * @param typeDocumentPersistance the new type document persistance
	 */
	public void setTypeDocumentPersistance(ITypeDocumentPersistance typeDocumentPersistance) {
		this.typeDocumentPersistance = typeDocumentPersistance;
	}


	

}
