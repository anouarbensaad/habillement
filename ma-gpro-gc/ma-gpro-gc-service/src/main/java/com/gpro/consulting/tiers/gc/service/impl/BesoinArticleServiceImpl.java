package com.gpro.consulting.tiers.gc.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.besoinarticle.value.BesoinArticleValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.vue.BesoinArticleVue;
import com.gpro.consulting.tiers.gc.domaine.IBesoinArticleDomaine;
import com.gpro.consulting.tiers.gc.service.IBesoinArticleService;

/**
 * implementation of {@link IBesoinArticleService}
 * 
 * @author Wahid Gazzah
 * @since 18/04/2016
 *
 */

@Service
@Transactional
public class BesoinArticleServiceImpl implements IBesoinArticleService{
	
	private static final Logger logger = LoggerFactory.getLogger(BesoinArticleServiceImpl.class);
	
	@Autowired
	IBesoinArticleDomaine besoinArticleDomaine;

	@Override
	public List<BesoinArticleValue> getAllBesoinArticle() {
		
		logger.info("Delegating request to Domain layer.");

		return besoinArticleDomaine.getAllBesoinArticles();
	}

	@Override
	public List<BesoinArticleValue> rechercheMulticritere(
			RechercheMulticritereProduitCommandeValue request) {
		
		logger.info("Delegating request to Domain layer.");
		
		return besoinArticleDomaine.rechercheMulticritere(request);
	}

	@Override
	public List<BesoinArticleVue> getBesoinProduit(Long produitId) {
		
		logger.info("Delegating request to Domain layer.");
		
		return besoinArticleDomaine.getBesoinProduit(produitId);
	}

}
