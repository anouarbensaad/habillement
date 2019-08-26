package com.gpro.consulting.tiers.commun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.TypeDocumentValue;
import com.gpro.consulting.tiers.commun.domaine.ITypeDocumentDomaine;
import com.gpro.consulting.tiers.commun.service.ITypeDocumentService;

/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
@Service
@Transactional
public class TypeDocumentServiceImpl implements ITypeDocumentService {
    @Autowired
    ITypeDocumentDomaine typeDocumentDomaine;

	public ITypeDocumentDomaine getTypeDocumentDomaine() {
		return typeDocumentDomaine;
	}

	public void setTypeDocumentDomaine(ITypeDocumentDomaine typeDocumentDomaine) {
		this.typeDocumentDomaine = typeDocumentDomaine;
	}

	@Override
	public TypeDocumentValue rechercheTypeDocumentPartieInteresseeParId(TypeDocumentValue pTypeDocumentValue) {
		  return typeDocumentDomaine.rechercheTypeDocumentPartieInteresseeParId(pTypeDocumentValue);
	}

	@Override
	public List<TypeDocumentValue> listeTypeDocumentPartieInteressee() {
		return typeDocumentDomaine.listeTypeDocumentPartieInteressee();
	}

	@Override
	public String creerTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue) {
		return typeDocumentDomaine.creerTypeDocumentPartieInteressee(pTypeDocumentValue);
	}

	@Override
	public void supprimerTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue) {
		typeDocumentDomaine.supprimerTypeDocumentPartieInteressee(pTypeDocumentValue);		
	}

	@Override
	public String modifierTypeDocumentPartieInteressee(
			TypeDocumentValue pTypeDocumentValue) {
		return typeDocumentDomaine.modifierTypeDocumentPartieInteressee(pTypeDocumentValue);
	}
 
}
