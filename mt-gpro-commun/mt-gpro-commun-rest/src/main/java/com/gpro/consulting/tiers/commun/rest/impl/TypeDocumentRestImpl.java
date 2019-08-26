package com.gpro.consulting.tiers.commun.rest.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gpro.consulting.tiers.commun.coordination.value.TypeDocumentValue;
import com.gpro.consulting.tiers.commun.service.IGestionnaireCommunCacheService;
import com.gpro.consulting.tiers.commun.service.ITypeDocumentService;

/**
 * 
 * @author $Author: Ameni $
 * @version $Revision: 0 $
 */
@Controller
@RequestMapping("/typeDocument")
public class TypeDocumentRestImpl {
	@Autowired
	private ITypeDocumentService typeDocumentPartieInteresseeService;

	@Autowired
	private IGestionnaireCommunCacheService gestionnaireCacheService;

	/************* get Article By ID *************/
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody TypeDocumentValue getArticle(@PathVariable Long id) {
		TypeDocumentValue pTypeDocValue = new TypeDocumentValue();
		pTypeDocValue.setId(id);
		return typeDocumentPartieInteresseeService
				.rechercheTypeDocumentPartieInteresseeParId(pTypeDocValue);
	}

	/******************************* All article *********************************/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TypeDocumentValue> viewAllTypeDocument() {
		List<TypeDocumentValue> vTypeDocumentValue = typeDocumentPartieInteresseeService
				.listeTypeDocumentPartieInteressee();
		return vTypeDocumentValue;
	}

	// ***********create Type***************//*
	@RequestMapping(value = "/createTypeDocument", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String createTypeDocument(
			@RequestBody final TypeDocumentValue typeValue) {
		return typeDocumentPartieInteresseeService
				.creerTypeDocumentPartieInteressee(typeValue);
	}

	// ***********delete Type***************//*
	@RequestMapping(value = "/deleteTypeDocument:{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody void deleteTypeDocument(@PathVariable Long id) {
		TypeDocumentValue idSupp = new TypeDocumentValue();
		idSupp.setId(id.longValue());
		typeDocumentPartieInteresseeService
				.supprimerTypeDocumentPartieInteressee(idSupp);
	}

	// ***********modifier Type***************//*
	@RequestMapping(value = "/modifierTypeDocument", method = RequestMethod.POST)
	public @ResponseBody String updateTypeDocument(
			@RequestBody final TypeDocumentValue typeValue) {
		return typeDocumentPartieInteresseeService
				.modifierTypeDocumentPartieInteressee(typeValue);

	}

	/**
	 * @return the typeDocumentPartieInteresseeService
	 */
	public ITypeDocumentService getTypeDocumentPartieInteresseeService() {
		return typeDocumentPartieInteresseeService;
	}

	/**
	 * @param typeDocumentPartieInteresseeService the typeDocumentPartieInteresseeService to set
	 */
	public void setTypeDocumentPartieInteresseeService(
			ITypeDocumentService typeDocumentPartieInteresseeService) {
		this.typeDocumentPartieInteresseeService = typeDocumentPartieInteresseeService;
	}

	/**
	 * @return the gestionnaireCacheService
	 */
	public IGestionnaireCommunCacheService getGestionnaireCacheService() {
		return gestionnaireCacheService;
	}

	/**
	 * @param gestionnaireCacheService the gestionnaireCacheService to set
	 */
	public void setGestionnaireCacheService(
			IGestionnaireCommunCacheService gestionnaireCacheService) {
		this.gestionnaireCacheService = gestionnaireCacheService;
	}

	
}
