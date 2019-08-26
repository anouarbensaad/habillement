package com.gpro.consulting.tiers.gc.domaine.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.service.IArticleService;
import com.gpro.consulting.tiers.gc.coordination.value.ArticleAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheArticleAchatValue;
import com.gpro.consulting.tiers.gc.domaine.IArticleAchatDomaine;
/**
 * @author $Ameni
 *
 */

@Component
public class ArticleAchatDomaineImpl implements IArticleAchatDomaine {

	@Autowired
	IArticleService articleService;
	
	@Override
	public ResultatRechecheArticleAchatValue rechercherArticleMultiCritere(
			RecherecheMulticritereArticleValue pRechercheArticleMulitCritere) {
		
		ResultatRechecheArticleAchatValue vResultFinal=new ResultatRechecheArticleAchatValue();
		List <ArticleAchatValue> list=new ArrayList<ArticleAchatValue>();
		ResultatRechecheArticleValue vResult=articleService.rechercherArticleMultiCritere(pRechercheArticleMulitCritere);
		if(vResult!=null && vResult.getNombreResultaRechercher()>0)
		{   
            for (ArticleValue art:vResult.getArticleValues()){
	            ArticleAchatValue artAchat=new ArticleAchatValue();
	            	artAchat.setId(art.getId());
	            	artAchat.setRef(art.getRef());
					artAchat.setDesignation(art.getDesignation());
					artAchat.setSousFamille(art.getSousFamilleArtEntiteDesignation());
					artAchat.setFamille(art.getFamilleArticleDesignation());
					artAchat.setType(art.getTypeArticleDesignation());
            
            list.add(artAchat);
            }
       
		}
		 vResultFinal.setNombreResultaRechercher(new Long (list.size()));
	     vResultFinal.setArticleAchatValues(list);
         return vResultFinal;
	}
	
}
