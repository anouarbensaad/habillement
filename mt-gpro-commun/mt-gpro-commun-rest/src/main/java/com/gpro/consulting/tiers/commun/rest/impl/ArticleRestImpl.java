package com.gpro.consulting.tiers.commun.rest.impl;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.ArticleCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.rest.IArticleRest;
import com.gpro.consulting.tiers.commun.service.IArticleService;
import com.gpro.consulting.tiers.commun.service.IGestionnaireCommunCacheService;

/**
 * 
 * @author $Author: Ghazi $
 * @version $Revision: 0 $
 */
@Controller
@RequestMapping("/article")
public class ArticleRestImpl implements IArticleRest {

  @Autowired
  private IArticleService articleService;
  
  @Autowired
  private IGestionnaireCommunCacheService gestionnaireCacheService;
  

  /**
   * Constructeur de la classe.
   */
  public ArticleRestImpl() {
    // Constructeur vide
  }

  @RequestMapping(value = "/string", method = RequestMethod.GET)
  public @ResponseBody String testStringPersonne() {

    return "Test";
  }
  
  /*************get Article By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody ArticleValue getArticle(@PathVariable Long id) {
		ArticleValue pArticleValue=new ArticleValue();
		pArticleValue.setId(id);
		return  articleService.rechercheArticleParId(pArticleValue);
	}

  /******************************* All article *********************************/ 
  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < ArticleValue > viewAllArticle() {
	  List < ArticleValue > vArticleValue = articleService.listeArticle();
	//Traitement : transformation de l'Id a sa propre Designation

      for(ArticleValue article : vArticleValue){
		 //SousFamille, Famille, Type
    	  Map<String, String> mapA = gestionnaireCacheService.rechercherArticleParId(article.getSousFamilleArtEntite(),article.getSiteEntite());

		  article.setSousFamilleArtEntiteDesignation(mapA.get("sousFamille"));
		  article.setFamilleArticleDesignation(mapA.get("famille"));
		  article.setTypeArticleDesignation(mapA.get("type"));
		  
		  //Site
		  article.setSiteEntiteDesignation(mapA.get("site"));

	    }
    return vArticleValue;
  }
  
  
  /******************************* All article cache*********************************/ 
  @RequestMapping(value = "/articleCache", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < ArticleCacheValue > viewArticleCache() {
	 return articleService.listeArticleCache();
  }
  
  /******************** Méthode de recherche multicritères **********************/
  @RequestMapping(value = "/rechercheArticleMulticritere", method = RequestMethod.POST)
  public @ResponseBody ResultatRechecheArticleValue rechercherArticleMultiCritere(
    @RequestBody final RecherecheMulticritereArticleValue pRechercheMultiCritere) {
   
	//Check if all criterias are null so generic search
	pRechercheMultiCritere.setOptimized(this.checkForOptimization(pRechercheMultiCritere));
					
			
	  ResultatRechecheArticleValue vResultatRecherche = articleService.rechercherArticleMultiCritere(pRechercheMultiCritere);
    //Traitement : transformation de l'Id sousFamille a sa propre Designation pour chaque Article
    
    for(ArticleValue article : vResultatRecherche.getArticleValues()){
    	
    	Map<String, String> mapA = gestionnaireCacheService.rechercherArticleParId(article.getSousFamilleArtEntite(), article.getSiteEntite());
    	//SousFamille, Famille, Type
    	article.setSousFamilleArtEntiteDesignation(mapA.get("sousFamille"));
		article.setFamilleArticleDesignation(mapA.get("famille"));
		article.setTypeArticleDesignation(mapA.get("type"));
		  
		//Site
		article.setSiteEntiteDesignation(mapA.get("site"));
    }
    return vResultatRecherche;
 }
  
 
  
  
  
  /**********************  Méthode de Creation d'un Article **********************/
  @RequestMapping(value = "/creerArticle", method = RequestMethod.POST)
  public @ResponseBody String creerArticle(@RequestBody ArticleValue pArticleValue) {
	  
		String  envoi ="{"+
				  "\"chainePrep\": {"+
		      "\"DOS\": {"+
		         "\"nom\": \"Khalil\""+","+ "\"pourcent\""+":"+ "\"10\""+
		     " },"+
		      "\"Devant\""+": {"+
		          "\"nom\": \"Khalil\""+"," +"\"pourcent\""+":"+"\"10\""+
		      "}"+
		  "}"+
		"}\""
				+ "";
	    pArticleValue.setDesignation(envoi);
	  
	    Log.info("ici");
		PrintWriter writer;
		
		try {
			writer = new PrintWriter("C:/ERP/chaine1Rendement.json", "UTF-8");
			writer.println(pArticleValue.getDesignation());

			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}
	  
	  
	  
    return "Created";
  
  
  
  
  
  
  }

  /**********************  Méthode de modification d'un Article **********************/
  @RequestMapping(value = "/modifierArticle", method = RequestMethod.POST)
  public @ResponseBody String modifierArticle(@RequestBody ArticleValue pArticleValue) {
    return this.articleService.modifierArticle(pArticleValue);
  }
  
  /**********************  Méthode de Suppression d'un Article **********************/
  @RequestMapping(value = "/supprimerArticle:{id}", method = RequestMethod.DELETE)
  public void supprimerArticle(@PathVariable("id") String id) {
   
   Long idSupp= Long.parseLong(id);
   articleService.supprimerArticle(Long.valueOf(idSupp));
  }
  
/**
 * @return the articleService
 */
public IArticleService getArticleService() {
	return articleService;
}

/**
 * @param articleService the articleService to set
 */
public void setArticleService(IArticleService articleService) {
	this.articleService = articleService;
}


/******************************* All article cache*********************************/ 
@RequestMapping(value = "/referenceExistence", method = RequestMethod.GET, produces = "application/json")
public @ResponseBody boolean referenceExistence(@RequestParam String reference) {
	 return articleService.referenceExistence(reference);
}


public boolean checkForOptimization( RecherecheMulticritereArticleValue request){
	
	return 	isNullOrEmpty(request.getRef()) &&
			isNullOrEmpty(request.getDesignation()) &&
			isNullOrEmpty(request.getTypeEntite()) &&
			isNullOrEmpty(request.getSousFamilleEntite()) &&
			isNullOrEmpty(request.getFamilleEntite()) &&
			isNullOrEmpty(request.getSite()) &&
			isNullOrEmpty(request.getPrix_inf()) &&
			isNullOrEmpty(request.getPrix_sup()) &&
			isNullOrEmpty(request.getIdMAgasin());
	
}

public boolean isNullOrEmpty (Object criteria){
	return criteria == null || criteria.toString().length() == 0;
}

}
