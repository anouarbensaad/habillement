package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.TypeArticleValue;
import com.gpro.consulting.tiers.commun.domaine.ITypeArticleDomaine;
import com.gpro.consulting.tiers.commun.persistance.ITypeArticlePersistance;

// TODO: Auto-generated Javadoc
/**
 * The Class TypeArticleDomaineImpl.
 */
public class TypeArticleDomaineImpl implements ITypeArticleDomaine{
	@Autowired
   ITypeArticlePersistance typeArticlePersistance ;
	/* (non-Javadoc)
	 * ajouter type article 
	 */
	@Override
	public String creerTypeArticle(TypeArticleValue pTypeArticleValue) {
		return typeArticlePersistance.creerTypeArticle(pTypeArticleValue);
	}

	/* (non-Javadoc)
	 * supprimer type article
	 */
	@Override
	public void supprimerTypeArticle(Long pTypeArticleId) {
		typeArticlePersistance.supprimerTypeArticle(pTypeArticleId);
	}

	/* (non-Javadoc)
	 * modidier type article
	 */
	@Override
	public String modifierTypeArticle(TypeArticleValue pTypeArticleValue) {
		return typeArticlePersistance.modifierTypeArticle(pTypeArticleValue);
	}

	/* (non-Javadoc)
	 * recherche par id type article
	 */
	@Override
	public TypeArticleValue rechercheTypeArticleById(Long pTypeArticleId) {
		return typeArticlePersistance.rechercheTypeArticleById(pTypeArticleId);
	}

	/* (non-Javadoc)
	 * liste type article
	 */
	@Override
	public List<TypeArticleValue> listeTypeArticle() {
		return typeArticlePersistance.listeTypeArticle();
	}

	/****getter and setter typeArticlePersistance********/
	public ITypeArticlePersistance getTypeArticlePersistance() {
		return typeArticlePersistance;
	}

	public void setTypeArticlePersistance(
			ITypeArticlePersistance typeArticlePersistance) {
		this.typeArticlePersistance = typeArticlePersistance;
	}

	
}
