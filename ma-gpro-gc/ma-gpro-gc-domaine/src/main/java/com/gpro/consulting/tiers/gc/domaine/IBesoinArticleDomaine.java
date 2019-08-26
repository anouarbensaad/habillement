package com.gpro.consulting.tiers.gc.domaine;

import java.util.List;

import com.gpro.consulting.tiers.gc.coordination.besoinarticle.value.BesoinArticleValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.vue.BesoinArticleVue;

/**
 * Besoin Article Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 18/04/2016
 *
 */
public interface IBesoinArticleDomaine {

	public List<BesoinArticleValue> getAllBesoinArticles();

	public List<BesoinArticleValue> rechercheMulticritere(
			RechercheMulticritereProduitCommandeValue request);
	
	public List<BesoinArticleVue> getBesoinProduit(Long produitId);

}
