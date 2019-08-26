package com.gpro.consulting.tiers.commun.rest.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechechePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.rest.IPartieInteresseeRest;
import com.gpro.consulting.tiers.commun.service.IGestionnaireCommunCacheService;
import com.gpro.consulting.tiers.commun.service.IPartieInteresseeService;

/**
 * 
 * @author $Author: DELL $
 * @version $Revision: 0 $
 */
@Controller
@RequestMapping("/partieInteressee")
public class PartieInteresseeRestImpl implements IPartieInteresseeRest {

  @Autowired
  private IPartieInteresseeService partieInteresseeService;

  @Autowired
  private IGestionnaireCommunCacheService gestionnaireCacheService;
	private static final Logger LOGGER = LoggerFactory.getLogger(PartieInteresseeRestImpl.class);

  
  /**
   * Constructeur de la classe.
   */
  public PartieInteresseeRestImpl() {
    // Constructeur vide
  }
  /*************get Partie Interessee By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody PartieInteresseValue getInteresseValue(@PathVariable Long id) {
		PartieInteresseValue pPartieInteresseValue=new PartieInteresseValue();
		pPartieInteresseValue.setId(id.longValue());
		return  partieInteresseeService.recherchePartieInteresseeParId(pPartieInteresseValue);
	}
	

/*************get Partie Interessee By ID*************/
	@RequestMapping(value = "/listePiCache", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody List< PartieInteresseCacheValue> getPartieInteresseValue() {
		LOGGER.info("this pi");
		return  partieInteresseeService.listePartieInteresseeCache();
	}
	
	
  /**
   * Méthode permettant de retourner une liste des Parties Interessées
   * 
   * @return
   */
  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public @ResponseBody List < PartieInteresseValue > viewAllPartieInteressee() {
	 
	  List < PartieInteresseValue > vPiValue = partieInteresseeService.listePartieInteressee();
	  for(PartieInteresseValue partieInteressee : vPiValue){
			 //Famille, Categorie, Type PI
	    	  Map<String, String> mapA = gestionnaireCacheService.rechercherPartieInteresseeParId(partieInteressee.getFamillePartieInteressee(), partieInteressee.getCategoriePartieInteressee(), partieInteressee.getTypePartieInteressee() );
	    	  partieInteressee.setFamillePartieInteresseeDesignation(mapA.get("famillePi"));
	    	  partieInteressee.setCategoriePartieInteresseeDesignation(mapA.get("categoriePi"));
	    	  partieInteressee.setTypePartieInteresseeDesignation(mapA.get("typePi"));
	  }

    return vPiValue; 
  }

  /**
   * Méthode permettant la recherche multicritére d'une partie interessée
   * 
   * @param pRecherchePartieInteresseMulitCritere
   * @return
   */

  @RequestMapping(value = "/recherchePIMulticritere", method = RequestMethod.POST)
  public @ResponseBody ResultatRechechePartieInteresseeValue rechercherPartieInteresseMultiCritere(
    @RequestBody final RechercheMulticriterePartieInteresseeValue pRechercheMultiCritere) {
  

	//Check if all criterias are null so generic search
	  pRechercheMultiCritere.setOptimized(this.checkForOptimization(pRechercheMultiCritere));
		
		
	// Création des critères de recherche
    ResultatRechechePartieInteresseeValue vResultatRecherche = partieInteresseeService
      .rechercherPartieInteresseMultiCritere(pRechercheMultiCritere);
    
    for(PartieInteresseValue partieInteressee : vResultatRecherche.getPartieInteresseValues()){
	  //Famille, Categorie, Type PI
   	  Map<String, String> mapA = gestionnaireCacheService.rechercherPartieInteresseeParId(partieInteressee.getFamillePartieInteressee(), partieInteressee.getCategoriePartieInteressee(), partieInteressee.getTypePartieInteressee() );

   	  partieInteressee.setFamillePartieInteresseeDesignation(mapA.get("famillePi"));
   	  partieInteressee.setCategoriePartieInteresseeDesignation(mapA.get("categoriePi"));
   	  partieInteressee.setTypePartieInteresseeDesignation(mapA.get("typePi"));
   	  
 }

    return vResultatRecherche;
  }

  /**
   * Méthode de création d'une partie Interessée
   * 
   * @param pPartieInteresseValue
   * @return
   */
  @RequestMapping(value = "/creerPartieInteressee", method = RequestMethod.POST)
  public @ResponseBody String creerPartieInteressee(@RequestBody PartieInteresseValue pPartieInteresseValue) {
    return this.partieInteresseeService.creerPartieInteressee(pPartieInteresseValue);
  }

  /**
   * Méthode de modification d'une partie Interessée
   * 
   * @param pPartieInteresseValue
   * @return
   */
  @RequestMapping(value = "/modifierPartieInteressee", method = RequestMethod.POST)
  public @ResponseBody String modifierPartieInteressee(@RequestBody PartieInteresseValue pPartieInteresseValue) {
    return this.partieInteresseeService.modifierPartieInteressee(pPartieInteresseValue);
  }
  @RequestMapping(value = "/supprimerPartieInteressee:{id}", method = RequestMethod.DELETE)
  public void supprimerPartieInteressee(@PathVariable("id") String id) {
    partieInteresseeService.supprimerPartieInteressee(Long.valueOf(id));

  }

  /**
   * Accesseur en lecture de l'attribut <code>partieInteresseeService</code>.
   * 
   * @return IPartieInteresseeService L'attribut partieInteresseeService à lire.
   */
  public IPartieInteresseeService getPartieInteresseeService() {
    return partieInteresseeService;
  }

  /**
   * Accesseur en écriture de l'attribut <code>partieInteresseeService</code>.
   *
   * @param partieInteresseeService
   *          L'attribut partieInteresseeService à modifier.
   */
  public void setPartieInteresseeService(IPartieInteresseeService partieInteresseeService) {
    this.partieInteresseeService = partieInteresseeService;
  }
  
  public boolean checkForOptimization( RechercheMulticriterePartieInteresseeValue request){
		
		return 	isNullOrEmpty(request.getvReference()) &&
				isNullOrEmpty(request.getvRaisonSociale()) &&
				isNullOrEmpty(request.getvFamillePartieInteressee()) &&
				isNullOrEmpty(request.getvCategoriePartieInteressee()) &&
				isNullOrEmpty(request.getvTypePartieInteressee()) &&
				isNullOrEmpty(request.getvActivite()) &&
				isNullOrEmpty(request.getActif());
		

	}
	
	public boolean isNullOrEmpty (Object criteria){
		return criteria == null || criteria.toString().length() == 0;
	}


}
