package com.gpro.consulting.tiers.commun.rest.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

import com.gpro.consulting.tiers.commun.coordination.value.ProduitTailleValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.service.IGestionnaireCommunCacheService;
import com.gpro.consulting.tiers.commun.service.IProduitService;
@Controller
@RequestMapping("/produit")
public class ProduitRestImpl {
	@Autowired
	IProduitService produitService;

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(ProduitRestImpl.class);

	/** Gestionnaire de Cache Service */
	@Autowired
	private IGestionnaireCommunCacheService gestionnaireCacheService;
	  
	
	//conctructeur
	public ProduitRestImpl(){
		
	}
	 @RequestMapping(value = "/string", method = RequestMethod.GET)
	  public @ResponseBody String testStringProduit() {
	    return "Test";
	  }

	  // liste produit 
	  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < ProduitValue > viewAllProduit() {
		  //LOGGER.info("list des produits /all");
		  List < ProduitValue > vProduitValue = produitService.listeProduit();
          //Traitement : transformation de l'Id a sa propre Designation
            for(ProduitValue produit : vProduitValue){
      		 //SousFamille, Famille
          	  Map<String, String> mapA = gestionnaireCacheService.rechercherProduitParId(produit.getSousFamilleId(),produit.getSiteId(), produit.getPartieIntersseId());
      		  produit.setSousFamilleDesignation(mapA.get("sousFamille"));
      		  produit.setFamilleDesignation(mapA.get("famille"));
      		  //Site
      		  produit.setSiteEntiteDesignation(mapA.get("site"));
      		  //partieInteressee
      		  produit.setPartieIntersseDesignation(mapA.get("partieInteressee"));
      		  
      		
      	    }
            return vProduitValue;
	  }
       // recherche produit by id
	  @RequestMapping(value = "/getId:{id}", method = RequestMethod.GET)
		public @ResponseBody ProduitValue getProduit(@PathVariable Long id) {
			
		  List<TailleValue> listTailleProduit = gestionnaireCacheService.getListTailleProduit();
		  Map<Long, Integer> mapOrder = new HashMap<Long, Integer>();
			
		  for(TailleValue taille : listTailleProduit){
			  mapOrder.put(taille.getId(), taille.getOrdre());
		  }
			
			ProduitValue produitValue = produitService.rechercheProduitById(id);
			
			if(produitValue != null){
				
				List<ProduitTailleValue> listProduitTaille = new ArrayList<ProduitTailleValue>();
				
				for(ProduitTailleValue produitTaille : produitValue.getTailleProduit()){
					
					if(produitTaille.getEbTailleId() != null){
						
						if(mapOrder.containsKey(produitTaille.getEbTailleId())){
							produitTaille.setTailleOrdre(mapOrder.get(produitTaille.getEbTailleId()));
						}
					}
					
					listProduitTaille.add(produitTaille);
				}
				
				Collections.sort(listProduitTaille);
				
				produitValue.setTailleProduit(listProduitTaille);
			}
			
			return produitValue;
		}
	  
	  @RequestMapping(value = "/rechercheProduitMulticritere", method = RequestMethod.POST)
	  public @ResponseBody ResultatRechecheProduitValue rechercherProduitMultiCritere(
	    @RequestBody final RechercheMulticritereProduitValue pRechercheMultiCritere) {

		//Check if all criterias are null so generic search
		pRechercheMultiCritere.setOptimized(this.checkForOptimization(pRechercheMultiCritere));
				
			  
	    // Création des critères de recherche
		  //LOGGER.info("list des produits par Recherche MultiCritères /rechercheProduitMulticritere");
		ResultatRechecheProduitValue vResultatRecherche = produitService.rechercherProduitMultiCritere(pRechercheMultiCritere);
	  
        //Traitement : transformation de l'Id a sa propre Designation

          for(ProduitValue produit : vResultatRecherche.getProduitValues()){
        	  
    		 //SousFamille, Famille
        	  Map<String, String> mapA = gestionnaireCacheService.rechercherProduitParId(produit.getSousFamilleId(), produit.getSiteId(), produit.getPartieIntersseId());

    		  produit.setSousFamilleDesignation(mapA.get("sousFamille"));
    		  produit.setFamilleDesignation(mapA.get("famille"));
    		  //Site
    		  produit.setSiteEntiteDesignation(mapA.get("site"));
    		  //partieInteressee
      		  produit.setPartieIntersseDesignation(mapA.get("partieInteressee"));
      		  
    	    }
	    return vResultatRecherche;
	  }

	  @RequestMapping(value = "/creerProduit", method = RequestMethod.POST)
	  public @ResponseBody String creerProduit(@RequestBody ProduitValue pProduitValue) {
	      //transformation et transcodage des referenciel 
		  
		  
		  //System.out.println("#####  ID  Standard  :"+pProduitValue.getIdStandard());	
		    
		  return this.produitService.creerProduit(pProduitValue);
	  }

	 
	  @RequestMapping(value = "/modifierProduit", method = RequestMethod.POST)
	  public @ResponseBody String modifierProduit(@RequestBody ProduitValue pProduitValue) {
	    return this.produitService.modifierProduit(pProduitValue);
	  }

	  @RequestMapping(value = "/supprimerProduit:{id}", method = RequestMethod.DELETE)
	  public void supprimerProduit(@PathVariable("id") String id) {
		  if(LOGGER.isDebugEnabled()){
				//logger.debug(" id  produit  : =" + id );
			}
		  //LOGGER.info(""+id);
		  Long idSupp= Long.parseLong(id);
		  produitService.supprimerProduit(Long.valueOf(idSupp));

	  }
	  

	public IProduitService getProduitService() {
		return produitService;
	}

	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}
	  
	  public boolean checkForOptimization( RechercheMulticritereProduitValue request){
			
			return 	isNullOrEmpty(request.getReference()) &&
					isNullOrEmpty(request.getDesignation()) &&
					isNullOrEmpty(request.getFamille()) &&
					isNullOrEmpty(request.getSousfamille()) &&
					isNullOrEmpty(request.getPartieInteressee()) &&
					isNullOrEmpty(request.getEtat()) &&
					isNullOrEmpty(request.getSite()) &&
					isNullOrEmpty(request.getPrix_inf()) &&
					isNullOrEmpty(request.getPrix_sup()) &&
					isNullOrEmpty(request.getFicheB());
			
		}
		
		public boolean isNullOrEmpty (Object criteria){
			return criteria == null || criteria.toString().length() == 0;
		}
	  
}
