package com.gpro.consulting.tiers.commun.rest.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.ElementBesoinValue;
import com.gpro.consulting.tiers.commun.coordination.value.FicheBesoinValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitCouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitTailleValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.service.IFicheBesoinService;
import com.gpro.consulting.tiers.commun.service.IGestionnaireCommunCacheService;


@Controller
@RequestMapping("/ficheBesoins")
public class FicheBesoinRestImpl {
	
	@Autowired
	IFicheBesoinService ficheBesoinService ; 
    @Autowired
    private IGestionnaireCommunCacheService gestionnaireCacheService;

	public IFicheBesoinService getFicheBesoinService() {
		return ficheBesoinService;
	}

	public void setFicheBesoinService(IFicheBesoinService ficheBesoinService) {
		this.ficheBesoinService = ficheBesoinService;
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(FicheBesoinRestImpl.class);
	
	/**
	 * cette méthode est utiliser pour récuperer une 
	 * fiche besoin 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/produits/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody FicheBesoinValue getFicheBesoinParId(@PathVariable Long id) {
		 LOGGER.info("appele de service FicheBesoinRestImpl avec paramétre {}"+id+" pour recherche une fiche de besoin");
		FicheBesoinValue vFicheValue = ficheBesoinService.rechercheFicheBesoinParId(id);
		Set<ElementBesoinValue> vElementBesoinValue = vFicheValue.getElementBesoinValue();
		ProduitValue vProduitValue = vFicheValue.getProduitValue();
		if(vElementBesoinValue != null){
		 for(ElementBesoinValue vItemElemBesoin : vElementBesoinValue){
			 //SousFamille, Famille, Type
	    	  Map<String, String> mapA = gestionnaireCacheService.rechFamEtSousFamParId(vItemElemBesoin.getArticle().getSousFamilleArtEntite());
	    	  vItemElemBesoin.getArticle().setSousFamilleArtEntiteDesignation(mapA.get("sousFamilleDesignation"));
	    	  vItemElemBesoin.getArticle().setFamilleArticleDesignation(mapA.get("FamilleDesignation"));
	    	  
	          vItemElemBesoin.setDesignationCouleur(gestionnaireCacheService.rechercheColorParId(vItemElemBesoin.getEb_couleur_id()));
	          vItemElemBesoin.setDesignationTaille(gestionnaireCacheService.rechercheTailleParId(vItemElemBesoin.getEb_taille_id()));	    	  
			  }
		}
			 //SousFamille, Famille
	    	  Map<String, String> mapA = gestionnaireCacheService.rechercherProduitParId(vProduitValue.getSousFamilleId(),vProduitValue.getSiteId(),vProduitValue.getPartieIntersseId());
	    	  vProduitValue.setSousFamilleDesignation(mapA.get("sousFamille"));
	    	  vProduitValue.setFamilleDesignation(mapA.get("famille"));
	    	  vProduitValue.setSiteEntiteDesignation(mapA.get("site"));
	    	  vProduitValue.setPartieIntersseDesignation(mapA.get("partieInteressee"));
	    	  
	    	  List<ProduitTailleValue> vNewSetProduitTailleValue  = vFicheValue.getProduitValue().getTailleProduit();
	    	  Set<ProduitCouleurValue> vNewSetCouleurValue = vFicheValue.getProduitValue().getCouleurProduit();
	    	 
	    	 for(ProduitTailleValue vItemProTaille : vNewSetProduitTailleValue){
	    		 vItemProTaille.setDesignation(gestionnaireCacheService.rechercheTailleParId(vItemProTaille.getEbTailleId()));
	    	 }
	    	 
	    	 for(ProduitCouleurValue vItemProCouleur : vNewSetCouleurValue){
	    		 vItemProCouleur.setDesignation(gestionnaireCacheService.rechercheColorParId(vItemProCouleur.getEbCouleurId())); 
	    	 }
	    	 
		return  vFicheValue;
	}	
	/**
	 * cette méthode est utiliser pour créer ou modifier
	 * une fiche besoin
	 * @param pFicheBesoinValue
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String creerOmodifierFicheBesoin(@RequestBody FicheBesoinValue pFicheBesoinValue){
	//public @ResponseBody String creerOmodifierFicheBesoin(){
		
		String idFicheBesoin = ficheBesoinService.creerOmodifierFicheBesoin(pFicheBesoinValue);
		
		return idFicheBesoin;
	}
	
	
	 // liste FicheBesoinValue 
	  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	  public @ResponseBody List < FicheBesoinValue > getAllFB() {
		  LOGGER.info("list des FicheBesoinValue /all");
		  List < FicheBesoinValue > vFicheBesoinValue = ficheBesoinService.getAll();
          return vFicheBesoinValue;
	  }
	  /**
		 * cette méthode est utiliser pour récuperer une 
		 * fiche besoin similaire  
		 * @param id
		 * @return
		 */
		@RequestMapping(value = "/produitsimilaire/{id}", method = RequestMethod.GET, produces = "application/json")
		public @ResponseBody FicheBesoinValue getFicheBesoinSimilaireParId(@PathVariable Long id) {
			 LOGGER.info("appele de service FicheBesoinRestImpl avec paramétre {}"+id+" pour recherche une fiche de besoin");
			FicheBesoinValue vFicheValue = ficheBesoinService.rechercheFicheBesoinParId(id);
			Set<ElementBesoinValue> vElementBesoinValue = vFicheValue.getElementBesoinValue();
			ProduitValue vProduitValue = vFicheValue.getProduitValue();
			if(vElementBesoinValue != null){
			 for(ElementBesoinValue vItemElemBesoin : vElementBesoinValue){
				 //SousFamille, Famille, Type
		    	  Map<String, String> mapA = gestionnaireCacheService.rechFamEtSousFamParId(vItemElemBesoin.getArticle().getSousFamilleArtEntite());
		    	  vItemElemBesoin.getArticle().setSousFamilleArtEntiteDesignation(mapA.get("sousFamilleDesignation"));
		    	  vItemElemBesoin.getArticle().setFamilleArticleDesignation(mapA.get("FamilleDesignation"));
		    	  vItemElemBesoin.setEb_taille_id(null);
		    	  vItemElemBesoin.setEb_couleur_id(null);
		         // vItemElemBesoin.setDesignationCouleur(gestionnaireCacheService.rechercheColorParId(vItemElemBesoin.getEb_couleur_id()));
		          //vItemElemBesoin.setDesignationTaille(gestionnaireCacheService.rechercheTailleParId(vItemElemBesoin.getEb_taille_id()));	    	  
				  }
			}
				 //SousFamille, Famille
		    	  Map<String, String> mapA = gestionnaireCacheService.rechercherProduitParId(vProduitValue.getSousFamilleId(),vProduitValue.getSiteId(),vProduitValue.getPartieIntersseId());
		    	  vProduitValue.setSousFamilleDesignation(mapA.get("sousFamille"));
		    	  vProduitValue.setFamilleDesignation(mapA.get("famille"));
		    	  vProduitValue.setSiteEntiteDesignation(mapA.get("site"));
		    	  vProduitValue.setPartieIntersseDesignation(mapA.get("partieInteressee"));
		    	  
		    	  List<ProduitTailleValue> vNewSetProduitTailleValue  = vFicheValue.getProduitValue().getTailleProduit();
		    	  Set<ProduitCouleurValue> vNewSetCouleurValue = vFicheValue.getProduitValue().getCouleurProduit();
		    	 
		    	 for(ProduitTailleValue vItemProTaille : vNewSetProduitTailleValue){
		    		 vItemProTaille.setDesignation(gestionnaireCacheService.rechercheTailleParId(vItemProTaille.getEbTailleId()));
		    	 }
		    	 
		    	 for(ProduitCouleurValue vItemProCouleur : vNewSetCouleurValue){
		    		 vItemProCouleur.setDesignation(gestionnaireCacheService.rechercheColorParId(vItemProCouleur.getEbCouleurId())); 
		    	 }
		    	 
			return  vFicheValue;
		}
}
