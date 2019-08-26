package com.gpro.consulting.tiers.commun.rest.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.CategorieValue;
import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.DeviseValue;
import com.gpro.consulting.tiers.commun.coordination.value.FamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.FamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.FamilleValue;
import com.gpro.consulting.tiers.commun.coordination.value.GrosseurValue;
import com.gpro.consulting.tiers.commun.coordination.value.MatiereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.MetrageValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.PhaseValue;
import com.gpro.consulting.tiers.commun.coordination.value.SiteValue;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.StandardTailleValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.coordination.value.TypeArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.TypeDocumentValue;
import com.gpro.consulting.tiers.commun.coordination.value.TypeValue;
import com.gpro.consulting.tiers.commun.coordination.value.UniteArticleValue;
import com.gpro.consulting.tiers.commun.service.IGestionnaireCommunCacheService;

@Controller
@RequestMapping("/gestionnaireCache")
public class GestionnaireCacheRestImpl {
	
	@Autowired
	private IGestionnaireCommunCacheService gestionnaireCacheService;

	/** Logger */
	private static final Logger LOGGER = LoggerFactory.getLogger(GestionnaireCacheRestImpl.class);

	/**
	 * all TypeArticle
	 * @return
	 */
	@RequestMapping(value = "/listeTypeArticleCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<TypeArticleValue> viewAllTypeArticle(){
		LOGGER.info("Entrer Cache TypeArticle");
		return gestionnaireCacheService.getListeTypeArticle();
    }
	
	/**
	 * all FamilleArticle
	 * @return
	 */
	@RequestMapping(value = "/listeFamilleArticleCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<FamilleArticleValue> viewAllFamilleArticle(){
		LOGGER.info("Entrer Cache FamilleArticle");
		return gestionnaireCacheService.getListFamilleArticle();
    }
	
	/**
	 * all SousFamilleArticle
	 * @return
	 */
	@RequestMapping(value = "/listeSousFamilleArticleCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<SousFamilleArticleValue> viewAllSousFamilleArticle(){
		LOGGER.info("Entrer Cache SousFamilleArticle");
		return gestionnaireCacheService.getListSousFamilleArticle();
    }
	
	/**
	 * all SiteArticle
	 * @return
	 */
	@RequestMapping(value = "/listeSitePartieInteresseeCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<SiteValue> viewAllSiteArticle(){
		LOGGER.info("Entrer Cache SiteArticle");
		return gestionnaireCacheService.getListSitePartieInteressee();
    }
	
	/**
	 * all UniteArticle
	 * @return
	 */
	@RequestMapping(value = "/listeUniteArticleCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<UniteArticleValue> viewAllUniteArticle(){
		LOGGER.info("Entrer Cache UniteArticle");
		return gestionnaireCacheService.getListUniteArticle();
    }
	
	/**
	 * all MatiereArticle
	 * @return
	 */
	@RequestMapping(value = "/listeMatiereArticleCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<MatiereArticleValue> viewAllMatiereArticle(){
		LOGGER.info("Entrer Cache MatiereArticle");
		return gestionnaireCacheService.getListMatiereArticle();
    }
	
	/**
	 * all MetrageArticle
	 * @return
	 */
	@RequestMapping(value = "/listeMetrageArticleCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<MetrageValue> viewAllMetrageArticle(){
		LOGGER.info("Entrer Cache MetrageArticle");
		return gestionnaireCacheService.getListMetrageArticle();
    }
	
	/**
	 * all GrosseurArticle
	 * @return
	 */
	@RequestMapping(value = "/listeGrosseurArticleCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<GrosseurValue> viewAllGrosseurArticle(){
		LOGGER.info("Entrer Cache GrosseurArticle");
		return gestionnaireCacheService.getListGrosseurArticle();
    }
		
	/**
	 * all TypeDocument
	 * @return
	 */
	@RequestMapping(value = "/listeTypeDocumentCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<TypeDocumentValue> viewAllTypeDocument(){
		LOGGER.info("Entrer Cache TypeDocument");
		return gestionnaireCacheService.getListTypeDocument();
    }
	
	/**
	 * all FamillePi
	 * @return
	 */
	@RequestMapping(value = "/listeFamillePICache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<FamilleValue> viewAllFamillePi(){
		LOGGER.info("Entrer Cache FamillePI");
		return gestionnaireCacheService.getListFamillePi();
    }
	
	/**
	 * all CategoriePi
	 * @return
	 */
	@RequestMapping(value = "/listeCategoriePICache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<CategorieValue> viewAllCategoriePi(){
		LOGGER.info("Entrer Cache CategoriePI");
		return gestionnaireCacheService.getListCategoriePi();
    }
	
	/**
	 * all TypePi
	 * @return
	 */
	@RequestMapping(value = "/listeTypePICache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<TypeValue> viewAllTypePi(){
		LOGGER.info("Entrer Cache TypePI");
		return gestionnaireCacheService.getListTypePi();
    }
	
	/**
	 * all Devise
	 * @return
	 */
	@RequestMapping(value = "/listeDeviseCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<DeviseValue> viewAllDevise(){
		LOGGER.info("Entrer Cache Devise");
		return gestionnaireCacheService.getListDevise();
    }
	
	/**
	 * all FamilleProduit
	 * @return
	 */
	@RequestMapping(value = "/listeFamilleProduitCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<FamilleProduitValue> viewAllFamilleProduit(){
		LOGGER.info("Entrer Cache FamilleProduit");
		return gestionnaireCacheService.getListFamilleProduit();
    }
	
	/**
	 * all SousFamilleProduit
	 * @return
	 */
	@RequestMapping(value = "/listeSousFamilleProduitCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<SousFamilleProduitValue> viewAllSousFamilleProduit(){
		LOGGER.info("Entrer Cache SousFamilleProduit");
		return gestionnaireCacheService.getListSousFamilleProduit();
    }
	
	/**
	 * all CouleurProduit
	 * @return
	 */
	@RequestMapping(value = "/listeCouleurProduitCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<CouleurValue> viewAllCouleurProduit(){
		LOGGER.info("Entrer Cache CouleurProduit");
		return gestionnaireCacheService.getListCouleurProduit();
    }
	
	/**
	 * all TailleProduit
	 * @return
	 */
	@RequestMapping(value = "/listeTailleProduitCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<TailleValue> viewAllTailleProduit(){
		LOGGER.info("Entrer Cache TailleProduit");
		return gestionnaireCacheService.getListTailleProduit();
    }
	
	/**
	 * all StandardTailleProduit
	 * @return
	 */
	@RequestMapping(value = "/listeStandardTailleProduitCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<StandardTailleValue> viewAllStandardTailleProduit(){
		LOGGER.info("Entrer Cache StandardTailleProduit");
		return gestionnaireCacheService.getListStandardTailleProduit();
    }
	
	/**
	 * all PhaseProduit
	 * @return
	 */
	@RequestMapping(value = "/listePhaseProduitCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<PhaseValue> viewAllPhaseProduit(){
		LOGGER.info("Entrer Cache PhaseProduit");
		return gestionnaireCacheService.getListPhaseProduit();
    }
	/**
	 * all PartieInteressee
	 * @return
	 */
	@RequestMapping(value = "/listePartieInteresseeCache", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<PartieInteresseValue> viewAllPartieInteressee(){
		LOGGER.info("Entrer Cache PartieInteressee");
		return gestionnaireCacheService.getListPartieInteressee();
    }
	
	/**
	 * Refresh du cache
	 * @return
	 */
	@RequestMapping(value = "/reloadReferentiel", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody void reloadReferentiel(){
		LOGGER.info("reloadReferentiel");
		gestionnaireCacheService.reloadReferentiel();
    }
	
	
}
