package com.gpro.consulting.tiers.commun.rest.report.impl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.erp.socle.j2ee.mt.socle.report.impl.AbstractGestionnaireDownloadImpl;
import com.gpro.consulting.tiers.commun.coordination.report.value.ArticleReportElementValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ArticlesReportListValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.PartieInteresseeElementValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.PartieInteresseeReportListValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitBesoinReportValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitReportElementValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitsReportListValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.service.IFamillePartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.IGestionnaireCommunCacheService;
import com.gpro.consulting.tiers.commun.service.report.IGestionnaireReportCommunService;

import net.sf.jasperreports.engine.JRException;

/**
 * Gestionnaire de reporting
 * 
 * @author Wahid Gazzah
 * @since 07/03/2016
 *
 */

@Controller
@RequestMapping("/reportCommun")
public class GestionnaireReportCommunRestImpl extends AbstractGestionnaireDownloadImpl{
	
	@Autowired
	private IGestionnaireReportCommunService gestionnaireReportCommunService;
	
	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportCommunRestImpl.class);
	
	/** Gestionnaire de Cache Service */
	@Autowired
	private IGestionnaireCommunCacheService gestionnaireCacheService;
	
	@Autowired
	IFamillePartieInteresseeService famillePartieInteresseeService;
	
	  
	@RequestMapping(value="/listproduit", method = RequestMethod.GET)
	public void generateListProduitReport(@RequestParam("type") String type,
//			@RequestParam("request") RechercheMulticritereProduitValue request,
			@RequestParam("reference") String reference,
			@RequestParam("designation") String designation,
			/*@RequestParam("famille") String famille,*/
			@RequestParam("sousfamille") String sousfamille,
			@RequestParam("partieInteressee") String partieInteressee,
			@RequestParam("etat") String etat,
			@RequestParam("site") String site,
			@RequestParam("prixInf") Double prixInf,
			@RequestParam("prixSup") Double prixSup,
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report Produit List",type);
		
		RechercheMulticritereProduitValue request = new RechercheMulticritereProduitValue();
		request.setReference(reference);
		request.setDesignation(designation); 
//		request.setFamille(famille);
		request.setSousfamille(sousfamille);
		request.setPartieInteressee(partieInteressee);
		request.setEtat(etat);
		request.setSite(site);
		request.setPrix_inf(prixInf);
		request.setPrix_sup(prixSup);
		
		ProduitsReportListValue report = gestionnaireReportCommunService.getListProduitsReport(request);
		
		//Traitement : transformation de l'Id a sa propre Designation

        for(ProduitReportElementValue elementProduit : report.getProductList()){
        	
      	  Map<String, String> mapA = gestionnaireCacheService.rechercherProduitParId(elementProduit.getSousfamilleId(), elementProduit.getSiteId(), elementProduit.getPartieInteresseeId());
      	  	//SousFamille, Famille
	      	elementProduit.setSousfamille(mapA.get("sousFamille"));
	      	elementProduit.setFamille(mapA.get("famille"));
	      	//Site
	      	elementProduit.setSite(mapA.get("site"));
	  		  //partieInteressee
	      	elementProduit.setPartieInteressee(mapA.get("partieInteressee"));
	    		  
  	    }
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);

	}
	
	//added on 08/04/2016, by Ameni Berrich
	//ListeArticle -report
	@RequestMapping(value="/listArticle", method = RequestMethod.GET)
	public void generateListArticleReport(@RequestParam("type") String type,
//			@RequestParam("request") RecherecheMulticritereArticleValue request,
			@RequestParam("reference") String reference,
			@RequestParam("designation") String designation,
			@RequestParam("famille") Long famille,
			@RequestParam("sousfamille") Long sousfamille,
			@RequestParam("typeArticle") Long typeArticle,
			@RequestParam("site") Long site,
			@RequestParam("prixInf") Double prixInf,
			@RequestParam("prixSup") Double prixSup,
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report Article List",type);
		
		RecherecheMulticritereArticleValue request = new RecherecheMulticritereArticleValue();
		request.setRef(reference);
		request.setDesignation(designation); 
		request.setFamilleEntite(famille);
		request.setSousFamilleEntite(sousfamille);
		request.setTypeEntite(typeArticle);
		request.setSite(site);
		request.setPrix_inf(prixInf);
		request.setPrix_sup(prixSup);
		
		ArticlesReportListValue report = gestionnaireReportCommunService.getListArticlesReport(request);
		 Map<String, String> mapB = gestionnaireCacheService.rechercherArticleParId(request.getSousFamilleEntite(),request.getSite());

		 report.setSousFamilleArticleDesignation(mapB.get("sousFamille"));
		 report.setFamilleArticleDesignation(mapB.get("famille"));
		 report.setTypeArticleDesignation(mapB.get("type"));
		 report.setSiteArticleDesignation(mapB.get("site"));
		  
		//Traitement : transformation de l'Id a sa propre Designation
        for(ArticleReportElementValue elementArticle : report.getArticlesList()){
        	
      	  Map<String, String> mapA = gestionnaireCacheService.rechercherArticleParId(elementArticle.getSousFamilleArtId(), elementArticle.getSiteId());
      	  	//SousFamille, Famille, Type
	      	elementArticle.setSousFamilleArticleDesignation(mapA.get("sousFamille"));
	      	elementArticle.setFamilleArticleDesignation(mapA.get("famille"));
	      	elementArticle.setTypeArticleDesignation(mapA.get("type"));
	      	//Site
	      	elementArticle.setSiteDesignation(mapA.get("site"));
	    		  
  	    }
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getjRBeanCollectionDataSource(), response);

	}
	
	
	/** Methode de Generation d'une Liste de PartieInteressées selon des critère de recherches
	 * @param type
	 * @param reference
	 * @param raisonSociale
	 * @param famillePI
	 * @param categoriePI
	 * @param typePI
	 * @param activite
	 * @param actif
	 * @param response
	 * @throws JRException
	 * @throws IOException
	 * @author Ameni Berrich
	 * @since 23/05/2016
	 */
	@RequestMapping(value="/listPartieInteressee", method = RequestMethod.GET)
	public void generateListPartieInteresseeReport(@RequestParam("type") String type,
//			@RequestParam("request") RecherecheMulticritereArticleValue request,
			@RequestParam("reference") String reference,
			@RequestParam("raisonSociale") String raisonSociale,
			@RequestParam("famillePI") Long famillePI,
			@RequestParam("categoriePI") Long categoriePI,
			@RequestParam("typePI") Long typePI,
			@RequestParam("actif") String actif,
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate {} PartieInteresseList Report ",type);
		
		RechercheMulticriterePartieInteresseeValue request = new RechercheMulticriterePartieInteresseeValue();
		request.setvReference(reference);
		request.setvRaisonSociale(raisonSociale); 
		request.setvFamillePartieInteressee(famillePI);
		request.setvCategoriePartieInteressee(categoriePI);
		request.setvTypePartieInteressee(typePI);
		request.setActif(actif);
		
		PartieInteresseeReportListValue report = gestionnaireReportCommunService.getListPIReport(request);
		Map<String, String> mapB = gestionnaireCacheService.rechercherPartieInteresseeParId(request.getvFamillePartieInteressee(),request.getvCategoriePartieInteressee(),request.getvTypePartieInteressee());
     	  
		report.setFamillePIDesignation(mapB.get("famillePi"));
		report.setCategoriePIDesignation(mapB.get("categoriePi"));
		report.setTypePIDesignation(mapB.get("typePi"));
		
		//Traitement : transformation de l'Id a sa propre Designation
        for(PartieInteresseeElementValue elementPI: report.getPartieInteresseeList()){
      	  Map<String, String> mapA = gestionnaireCacheService.rechercherPartieInteresseeParId(elementPI.getFamillePartieInteressee(), elementPI.getCategoriePartieInteressee(), elementPI.getTypePartieInteressee());
      	  //famillePI
      	  elementPI.setFamillePIDesignation(mapA.get("famillePi"));
      	  //CategoriePI
      	  elementPI.setCategoriePIDesignation(mapA.get("categoriePi"));
      	  //TypePI
      	  elementPI.setTypePIDesignation(mapA.get("typePi"));
  	    }
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getjRBeanCollectionDataSource(), response);

	}
	/**
	 * @return the gestionnaireReportCommunService
	 */
	public IGestionnaireReportCommunService getGestionnaireReportCommunService() {
		return gestionnaireReportCommunService;
	}

	/**
	 * @param gestionnaireReportCommunService the gestionnaireReportCommunService to set
	 */
	public void setGestionnaireReportCommunService(
			IGestionnaireReportCommunService gestionnaireReportCommunService) {
		this.gestionnaireReportCommunService = gestionnaireReportCommunService;
	}
	
	/**
	 * 
	 * @param produitId
	 * @param type
	 * @param response
	 * @throws JRException
	 * @throws IOException
	 * @author Zeineb Medimagh
	 * @since 04/11/2016
	 */
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/getBesoinProduit", method = RequestMethod.GET)
	public void generateBesoinProduitReport(@RequestParam("produitId") Long produitId, @RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		ProduitBesoinReportValue report = gestionnaireReportCommunService.getBesoinProduit(produitId);
		
		this.download( type , report.getReportStream(),report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);

	}
	

}
