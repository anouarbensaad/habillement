package com.gpro.consulting.tiers.gc.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.besoinarticle.value.BesoinArticleValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.vue.BesoinArticleVue;

/**
 * @author Wahid Gazzah
 * @since 18/04/2016
 */
public interface IBesoinArticleService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<BesoinArticleValue> getAllBesoinArticle();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<BesoinArticleValue> rechercheMulticritere(RechercheMulticritereProduitCommandeValue request);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<BesoinArticleVue> getBesoinProduit(Long produitId);
}
