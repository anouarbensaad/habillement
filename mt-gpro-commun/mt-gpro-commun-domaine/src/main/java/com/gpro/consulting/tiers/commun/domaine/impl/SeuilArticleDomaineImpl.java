package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.SeuilArticleValue;
import com.gpro.consulting.tiers.commun.domaine.ISeuilArticleDomaine;
import com.gpro.consulting.tiers.commun.persistance.ISeuilArticlePersistance;

// TODO: Auto-generated Javadoc
/**
 * The Class SeuilArticleDomaineImpl
 * @author mohamed.
 */
public class SeuilArticleDomaineImpl implements ISeuilArticleDomaine{

	@Autowired
	ISeuilArticlePersistance seuilArticlePersistance ;
	/* (non-Javadoc)
	 * ajouter seuil article
	 */
	@Override
	public String creerSeuilArticle(SeuilArticleValue pSeuilArticleValue) {
		return seuilArticlePersistance.creerSeuilArticle(pSeuilArticleValue);
	}

	/* (non-Javadoc)
	 * supprimer  seuil article 
	 */
	@Override
	public void supprimerSeuilArticle(Long pSeuilArticleId) {
		seuilArticlePersistance.supprimerSeuilArticle(pSeuilArticleId);
	}

	/* (non-Javadoc)
	 * modifier seuil article
	 */
	@Override
	public String modifierSeuilArticle(SeuilArticleValue pSeuilArticleValue) {
		return seuilArticlePersistance.modifierSeuilArticle(pSeuilArticleValue);
	}

	/* (non-Javadoc)
	 * recherche seuil article
	 */
	@Override
	public SeuilArticleValue rechercheSeuilArticleById(Long pSeuilArticleId) {
		return seuilArticlePersistance.rechercheSeuilArticleById(pSeuilArticleId);
	}

	/* (non-Javadoc)
	 * liste seuil article
	 */
	@Override
	public List<SeuilArticleValue> listeSeuilArticle() {
		return seuilArticlePersistance.listeSeuilArticle();
	}

	/********* Getter & Setter ***********/
	public ISeuilArticlePersistance getSeuilArticlePersistance() {
		return seuilArticlePersistance;
	}

	public void setSeuilArticlePersistance(
			ISeuilArticlePersistance seuilArticlePersistance) {
		this.seuilArticlePersistance = seuilArticlePersistance;
	}
	
	
	
}
