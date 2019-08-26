package com.gpro.consulting.tiers.commun.rest;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.TypeDocumentValue;

public interface ITypeDocumentRest {

	/********************** recherche type doc partie interesse par Id *****************************/
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public TypeDocumentValue rechercheTypeDocumentPartieInteresseeParId(
			TypeDocumentValue pTypeDocumentValue);

	/********************** afficher liste type doc partie interesse *****************************/
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<TypeDocumentValue> listeTypeDocumentPartieInteressee();

}
