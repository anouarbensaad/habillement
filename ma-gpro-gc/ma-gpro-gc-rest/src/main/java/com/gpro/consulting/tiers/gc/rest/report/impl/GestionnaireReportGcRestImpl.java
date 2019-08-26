package com.gpro.consulting.tiers.gc.rest.report.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
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
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.service.IPartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.IProduitService;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.ArticleBesoinReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.BesoinArticleElementReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.BesoinArticleReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.CommandeVenteReportElementValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.CommandeVenteReportListValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.CommandeVenteReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.FactureVenteReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.LivraisonVenteReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.ProduitCommandeReportListValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.ProduitCommandeVenteReportElementValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereCommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereProduitCommandeValue;
import com.gpro.consulting.tiers.gc.service.IGestionnaireGCCacheService;
import com.gpro.consulting.tiers.gc.service.report.IGestionnaireReportGcService;

import net.sf.jasperreports.engine.JRException;

/**
 * Gestionnaire de reporting
 * 
 * @author Wahid Gazzah
 * @since 10/03/2016
 *
 */

@Controller
@RequestMapping("/report")
@SuppressWarnings("static-access")
public class GestionnaireReportGcRestImpl extends AbstractGestionnaireDownloadImpl{

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportGcRestImpl.class);
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final Double ZERO = 0D;
	
	@Autowired
	private IGestionnaireReportGcService gestionnaireReportGcService;
	
	/** Gestionnaire de Cache Service */
	@Autowired
	private IGestionnaireGCCacheService gestionnaireGCCacheService;
	
	@Autowired
	private IPartieInteresseeService partieInteresseeService;
	
	@Autowired
	private IProduitService produitService;
	
	@RequestMapping(value="/commandeVente", method = RequestMethod.GET)
	public void generateCommandeVenteReport(@RequestParam("id") Long id,
			@RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report CommandeVente",type);

		CommandeVenteReportValue report = gestionnaireReportGcService.getCommandeVenteReportValue(id);
		
		this.download( type , report.getReportStream(),report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);

	}
	
	@RequestMapping(value="/listCommandeVente", method = RequestMethod.GET)
	public void generateListCommandeVenteReport(@RequestParam("type") String type,
//			@RequestParam("request") RechercheMulticritereCommandeVenteValue request,
			
			@RequestParam("reference") String reference,
			@RequestParam("partieInteressee") String partieInteressee,
			@RequestParam("produit") String produit,
			@RequestParam("dateIntroductionDu") String dateIntroductionDu,
			@RequestParam("dateIntroductionA") String dateIntroductionA,
			@RequestParam("dateLivraisonDu") String dateLivraisonDu,
			@RequestParam("dateLivraisonA") String dateLivraisonA,
//			@RequestParam("typeCommande") String typeCommande,
//			@RequestParam("etatCommande") String etatCommande,
			//Added on 17/03/2016, by Ameni Berrich
			@RequestParam("quantiteDu") Long quantiteDu,
			@RequestParam("quantiteA") Long quantiteA,
			@RequestParam("coutDu") Double coutDu,
			@RequestParam("coutA") Double coutA,
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report CommandeVente List",type);
		
		RechercheMulticritereCommandeVenteValue request = new RechercheMulticritereCommandeVenteValue();
		request.setvReference(reference);
		request.setvTypePartieInteressee(partieInteressee);
		request.setvProduit(produit);
		request.setDateIntroductionDu(stringToCalendar(dateIntroductionDu));
		request.setDateIntroductionA(stringToCalendar(dateIntroductionA));
		request.setDateLivraisonDu(stringToCalendar(dateLivraisonDu));
		request.setDateLivraisonA(stringToCalendar(dateLivraisonDu));
//		request.setvTypeCommande(typeCommande);
//		request.setvEtatCommande(etatCommande);
		//Added on 17/03/2016, by Ameni Berrich
		request.setQuantiteDu(quantiteDu);
		request.setQuantiteA(quantiteA);
		request.setCoutDu(coutDu);
		request.setCoutA(coutA);
		
		CommandeVenteReportListValue report = gestionnaireReportGcService.getListCommandeVenteReport(request);
		//Traitement : transformation de l'Id a sa propre Designation
        for(CommandeVenteReportElementValue commandeVenteElementProduit : report.getCommandeVenteList()){
        	
      	  Map<String, String> mapA = gestionnaireGCCacheService.rechercherCommandeParId(commandeVenteElementProduit.getTypeCommande(), commandeVenteElementProduit.getPartieInteressee(),commandeVenteElementProduit.getSite(), commandeVenteElementProduit.getEtatCommande());
      	  	//TypeCommande
      	commandeVenteElementProduit.setTypeCommandeDesignation(mapA.get("type"));
	  		//client (Abreviation)
      	commandeVenteElementProduit.setPartieIntersseDesignation(mapA.get("client"));
      		//etat
      	commandeVenteElementProduit.setEtatCommandeDesignation(mapA.get("etat"));
      		//site
      	commandeVenteElementProduit.setSiteDesignation(mapA.get("site"));
	    		  
  	    }
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getjRBeanCollectionDataSource(), response);
	}
	
	@RequestMapping(value="/bonLivraisonVente", method = RequestMethod.GET)
	public void generateBonLivraisonVenteReport(@RequestParam("id") Long id,
			@RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report BonLivraisonVente",type);

		LivraisonVenteReportValue report = gestionnaireReportGcService.getLivraisonVenteReportValue(id);
		
		this.download( type , report.getReportStream(),report.getParams(), 
				report.getFileName(),report.getjRBeanCollectionDataSource(), response);

	}
	
	@RequestMapping(value="/factureVente", method = RequestMethod.GET)
	public void generateFactureVenteReport(@RequestParam("id") Long id, @RequestParam("a") int a,
			@RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report FactureVente",type);

		FactureVenteReportValue report = gestionnaireReportGcService.getFactureVenteReportValue(id,a);
		
	this.download( type , report.getReportStream(),report.getParams(), 
			report.getFileName(),report.getjRBeanCollectionDataSource(), response);

	}
	//added on 21/03/2016, By Ameni Berrich
	@RequestMapping(value="/listProduitCommandeVente", method = RequestMethod.GET)
	public void generateListProduitCommandeVenteReport(@RequestParam("type") String type,
//			@RequestParam("request") RechercheMulticritereProduitCommandeValue request,
			
			@RequestParam("referenceBC") String referenceBC,
			@RequestParam("clientId") Long clientId,
			@RequestParam("produitId") Long produitId,
			@RequestParam("dateLivraisonMin") String dateLivraisonMin,
			@RequestParam("dateLivraisonMax") String dateLivraisonMax,
			@RequestParam("dateCommandeMin") String dateCommandeMin,
			@RequestParam("dateCommandeMax") String dateCommandeMax,
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report CommandeVente List",type);
		
		RechercheMulticritereProduitCommandeValue request = new RechercheMulticritereProduitCommandeValue();
		request.setRefCommande(referenceBC);
		request.setClientId(clientId);
		request.setProduitId(produitId);
		request.setDateLivraisonMin(stringToCalendar(dateLivraisonMin));
		request.setDateLivraisonMax(stringToCalendar(dateLivraisonMax));
		request.setDateCommandeMin(stringToCalendar(dateCommandeMin));
		request.setDateCommandeMax(stringToCalendar(dateCommandeMax));
		
		ProduitCommandeReportListValue report = gestionnaireReportGcService.getListProduitCommandeVenteReport(request);
		//TODO : Traitement : transformation de l'Id a sa propre Designation
        for(ProduitCommandeVenteReportElementValue produitCommandeVenteElementProduit : report.getProduitCommandeVenteReportList()){
        	
      	//  Map<String, String> mapA = gestionnaireGCCacheService.rechercherProduitCommandeParId(produitCommandeVenteElementProduit.getCommandeVenteId(), produitCommandeVenteElementProduit.getProduitId());
	  		//referenceBC
      	  	//referenceClient
      	  	//client (Abreviation)
      		//referenceProduit
      		//produitDesignation
	    		  
  	    }
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getjRBeanCollectionDataSource(), response);

	}
	
	@RequestMapping(value="/allBesoinArticle", method = RequestMethod.GET)
	public void generateBesoinArticleReport(@RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report for all BesoinArticle",type);

		BesoinArticleReportValue report = gestionnaireReportGcService.getAllBesoinArticle();
		
		this.download( type , report.getReportStream(),report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);

	}
	
	
	@RequestMapping(value="/rechercheBesoinArticleMulticritere", method = RequestMethod.GET)
	public void rechercheBesoinArticleMulticritere(
//			@RequestParam("request") RechercheMulticritereProduitCommandeValue request,
			@RequestParam("referenceBC") String referenceBC,
			@RequestParam("clientId") Long clientId,
			@RequestParam("produitId") Long produitId,
			@RequestParam("dateLivraisonMin") String dateLivraisonMin,
			@RequestParam("dateLivraisonMax") String dateLivraisonMax,
			@RequestParam("dateCommandeMin") String dateCommandeMin,
			@RequestParam("dateCommandeMax") String dateCommandeMax,
			@RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report BesoinArticle",type);
		
		RechercheMulticritereProduitCommandeValue request = new RechercheMulticritereProduitCommandeValue();
		request.setRefCommande(referenceBC);
		request.setClientId(clientId);
		request.setProduitId(produitId);
		request.setDateLivraisonMin(stringToCalendar(dateLivraisonMin));
		request.setDateLivraisonMax(stringToCalendar(dateLivraisonMax));
		request.setDateCommandeMin(stringToCalendar(dateCommandeMin));
		request.setDateCommandeMax(stringToCalendar(dateCommandeMax));

		BesoinArticleReportValue report = gestionnaireReportGcService.rechercheBesoinArticleMulticritere(request);
		
		if(report != null){
			
			report.setRefCommande(referenceBC);
			report.setDateLivraisonMin(request.getDateLivraisonMin());
			report.setDateLivraisonMax(request.getDateLivraisonMax());
			report.setDateCommandeMin(request.getDateCommandeMin());
			report.setDateCommandeMax(request.getDateCommandeMax());
			report.setClientAbreviation("");
			report.setProduitDesignation("");
			
			Map<Long, String> mapProduitsDesignation = new HashMap<Long, String>();
			for(ProduitValue produit : produitService.listeProduit()){
				Long key = produit.getId();
				if (mapProduitsDesignation.get(key) == null) {
					mapProduitsDesignation.put(key, produit.getDesignation());
				}
			}
			
			Map<Long, String> mapClientsAbreviations = new HashMap<Long, String>();
			for(PartieInteresseValue client : partieInteresseeService.listePartieInteressee()){
				Long key = client.getId();
				if (mapClientsAbreviations.get(key) == null) {
					mapClientsAbreviations.put(key, client.getAbreviation());
				}
			}
			
			Double total = ZERO;
			
			for(BesoinArticleElementReportValue element : report.getListBesoinArticleElements()){
				
				total = total + (element.getTotal()!=null?element.getTotal():ZERO);
			}
			
			if(request.getClientId() != null){
				if(mapClientsAbreviations.containsKey(request.getClientId())){
					
					report.setClientAbreviation(mapClientsAbreviations.get(request.getClientId()));
				}
			}
			
			if(request.getProduitId() != null){
				if(mapProduitsDesignation.containsKey(request.getProduitId())){
					
					report.setProduitDesignation(mapProduitsDesignation.get(request.getProduitId()));
				}
			}
			
			report.setTotal(total);
		}
		
		this.download( type , report.getReportStream(),report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);

	}
	
	
	/** Méthode de génération d'un rapport de Besoin Article par porduitId
	 * @param produitId
	 * @param type
	 * @param response
	 * @throws JRException
	 * @throws IOException
	 * @author Ameni Berrich
	 * @since 20/05/2016
	 */
	@RequestMapping(value="/getBesoinProduit", method = RequestMethod.GET)
	public void generateBesoinProduitReport(@RequestParam("produitId") Long produitId, @RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {"+type+"} report for besoinArticle by ProductId ");

		ArticleBesoinReportValue report = gestionnaireReportGcService.getBesoinProduit(produitId);
		
		this.download( type , report.getReportStream(),report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);

	}
	
	private Calendar stringToCalendar(String dateString) {
		
		Calendar dateCalendar = null;
		
		if(isNotEmty(dateString)){
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			dateCalendar = sdf.getCalendar();
			try {
				dateCalendar.setTime(sdf.parse(dateString));
				
			} catch (ParseException e) {
				logger.error("parse date exception: "+e.getMessage());
			}
		}
		
		return dateCalendar;
	}
	
	private boolean isNotEmty(String value) {
		return value != null && !"".equals(value);

	}
	
	

}
