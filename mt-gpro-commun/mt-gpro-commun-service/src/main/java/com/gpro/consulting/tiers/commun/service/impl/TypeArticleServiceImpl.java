package com.gpro.consulting.tiers.commun.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gpro.consulting.tiers.commun.coordination.value.TypeArticleValue;
import com.gpro.consulting.tiers.commun.domaine.ITypeArticleDomaine;
import com.gpro.consulting.tiers.commun.service.ITypeArticleService;

/**
 * The Class TypeArticleServiceImpl.
 * @author mohamed
 */
@Service
public class TypeArticleServiceImpl implements ITypeArticleService {
 
 /** The type article domaine. */
 @Autowired
   ITypeArticleDomaine typeArticleDomaine;
	
	/* (non-Javadoc)
	 * ajouter type Article
	 */
	@Override
	public String creerTypeArticle(TypeArticleValue pTypeArticleValue) {
		return typeArticleDomaine.creerTypeArticle(pTypeArticleValue);
	}

	/* (non-Javadoc)
	 * supprimer type article
	 */
	@Override
	public void supprimerTypeArticle(Long pTypeArticleId) {
        typeArticleDomaine.supprimerTypeArticle(pTypeArticleId);		
	}

	/* (non-Javadoc)
	 * modifier type article
	 */
	@Override
	public String modifierTypeArticle(TypeArticleValue pTypeArticleValue) {
		return typeArticleDomaine.modifierTypeArticle(pTypeArticleValue);
	}

	/* (non-Javadoc)
	 * recherche type article by id
	 */
	@Override
	public TypeArticleValue rechercheTypeArticleById(Long pTypeArticleId) {
		return typeArticleDomaine.rechercheTypeArticleById(pTypeArticleId);
	}

	/* (non-Javadoc)
	 * list type article
	 */
	@Override
	public List<TypeArticleValue> listeTypeArticle() {
		return typeArticleDomaine.listeTypeArticle();
	}

	/**
	 * Gets the type article domaine.
	 *
	 * @return the type article domaine
	 */
	public ITypeArticleDomaine getTypeArticleDomaine() {
		return typeArticleDomaine;
	}

	/**
	 * Sets the type article domaine.
	 *
	 * @param typeArticleDomaine the new type article domaine
	 */
	public void setTypeArticleDomaine(ITypeArticleDomaine typeArticleDomaine) {
		this.typeArticleDomaine = typeArticleDomaine;
	}

	
}
