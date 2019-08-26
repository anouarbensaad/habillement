package com.gpro.consulting.tiers.commun.domaine;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.UniteArticleValue;

// TODO: Auto-generated Javadoc
/**
 * The Interface IUniteArticleDomaine.
 * @author mohamed
 */
public interface IUniteArticleDomaine {
	
	/**
	 * Creer unite article.
	 *
	 * @param pUniteArticleValue the unite article value
	 * @return the string
	 */
	public  String creerUniteArticle(UniteArticleValue pUniteArticleValue);
	
	/**
	 * Supprimer unite article.
	 *
	 * @param pUniteArticleId the unite article id
	 */
	public  void supprimerUniteArticle(Long pUniteArticleId);
	
	/**
	 * Modifierunite article.
	 *
	 * @param pUniteArticleValue the unite article value
	 * @return the string
	 */
	public String modifieruniteArticle(UniteArticleValue pUniteArticleValue);
	
	/**
	 * Recherche unite article by id.
	 *
	 * @param pUniteArticleId the unite article id
	 * @return the unite article value
	 */
	public UniteArticleValue rechercheUniteArticleById(Long pUniteArticleId);
	
	/**
	 * Liste unite article.
	 *
	 * @return the list
	 */
	public List<UniteArticleValue> listeUniteArticle();
}
