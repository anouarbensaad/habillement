package com.gpro.consulting.tiers.gc.rest.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheArticleAchatValue;
import com.gpro.consulting.tiers.gc.service.IArticleAchatService;

/**
 * @author $Ameni
 *
 */
@Controller
@RequestMapping("/articleAchat")
public class ArticleAchatRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(ArticleAchatRestImpl.class);

	@Autowired
	private IArticleAchatService articleAchatService;

	/******************************* Article Achat *********************************/
	@RequestMapping(value = "/rechercheArticleAchatMultiCritere", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResultatRechecheArticleAchatValue rechercheArticleAchatValue(
			@RequestBody RecherecheMulticritereArticleValue pArticle) {

		ResultatRechecheArticleAchatValue resultatRecherche = articleAchatService
				.rechercherArticleMultiCritere(pArticle);
		return resultatRecherche;
	}


}
