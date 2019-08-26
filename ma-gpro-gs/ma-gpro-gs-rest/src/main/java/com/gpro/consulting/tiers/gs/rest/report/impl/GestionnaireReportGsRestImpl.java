package com.gpro.consulting.tiers.gs.rest.report.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.BonMouvementStockReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.EtatMouvementReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.EtatStockDetailleReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.EtatStockGlobalReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.EtatStockReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.MouvementStockHistoryDetailleReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.MouvementStockHistoryElementReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.MouvementStockHistoryReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.RequestRechecheMouvementValue;
import com.gpro.consulting.tiers.gs.coordination.value.MagasinValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereEntiteStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.gs.service.IGestionnaireGSCacheService;
import com.gpro.consulting.tiers.gs.service.report.IGestionnaireReportGsService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * GestionnaireReport Controller
 * 
 * @author Wahid Gazzah
 * @since 10/02/2016
 *
 */

@Controller
@RequestMapping("/reportgs")
public class GestionnaireReportGsRestImpl extends AbstractGestionnaireDownloadImpl{
	
	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportGsRestImpl.class);
	
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	@Autowired
	private IGestionnaireReportGsService gestionnaireReportGsService;
	
	@Autowired
	private IGestionnaireGSCacheService gestionnaireGSCacheService;
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/mouvementStockHistory", method = RequestMethod.GET)
	public void generateMouvementStockHistoryReport(
			@RequestParam("historique") String historique,
			@RequestParam("articleType") String articleType,
			@RequestParam("dateMin") String dateMin,
			@RequestParam("dateMax") String dateMax,
			@RequestParam("famille") String famille,
			@RequestParam("sousFamille") String sousFamille,
			@RequestParam("emplacement") String emplacement,
			@RequestParam("refArticle") String refArticle,
			@RequestParam("article") String article,
			@RequestParam("magasin") String magasinVal,
			@RequestParam("lot") String lot,
			@RequestParam("raison") String raison,
			@RequestParam("ofId") String ofId,
			@RequestParam("client") String clientVal,
			@RequestParam("fournisseur") String fournisseur,
			@RequestParam("oa") String oa,
			@RequestParam("type") String type,
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report MouvementStockHistory",type);
		RechercheMulticritereMouvementValue critere = new RechercheMulticritereMouvementValue();
		critere.setHistorique(historique);
		critere.setType(articleType);
		
		critere.setDateDu(stringToCalendar(dateMin));
		critere.setDateA(stringToCalendar(dateMax));
		
		critere.setFamille(famille);
		critere.setSousFamille(sousFamille);
		critere.setEmplacement(emplacement);
		critere.setRefArticle(refArticle);
		critere.setArticle(article);
		critere.setMagasin(magasinVal);
		critere.setLot(lot);
		critere.setRaison(raison);
		Long of = isNotEmty(ofId)? Long.parseLong(ofId):null; 
		critere.setOfId(of);
		critere.setClient(clientVal);
		critere.setFournisseur(fournisseur);
		critere.setOa(oa);
		MouvementStockHistoryReportValue report = gestionnaireReportGsService.getHistoryReport(critere);
		
		Map<Long, PartieInteresseCacheValue> mapClients = new HashMap<Long, PartieInteresseCacheValue>();
		Map<Long, MagasinValue> mapMagasins = new HashMap<Long, MagasinValue>();
		
		for(PartieInteresseCacheValue client : gestionnaireGSCacheService.getListeClient()){
			Long key = client.getId();
			if (mapClients.get(key) == null) {
				mapClients.put(key, client);
			}
		}
		
		for(MagasinValue magasin : gestionnaireGSCacheService.getListeMagasin()){
			Long key = magasin.getId();
			if (mapMagasins.get(key) == null) {
				mapMagasins.put(key, magasin);
			}
		}
		
		for(MouvementStockHistoryElementReportValue element : report.getElementsList()){
			
			if(element.getClientId() !=null){
				String client = (mapClients.containsKey(element.getClientId())) ? mapClients.get(element.getClientId()).getAbreviation() : null;
				element.setClient(client);
			}
			
			if(element.getMagasinId() != null){
				String designation = (mapMagasins.containsKey(element.getMagasinId())) ? mapMagasins.get(element.getMagasinId()).getDesignation() : null;
				element.setDesignationMagasin(designation);
			}
			
		}
		
		ArrayList<MouvementStockHistoryReportValue> dataList = new ArrayList<MouvementStockHistoryReportValue>();
		dataList.add(report);
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/etatStock", method = RequestMethod.GET)
	public void generateetatStockReport(
			@RequestParam("articleType") String articleType,
			@RequestParam("famille") String famille,
			@RequestParam("sousFamille") String sousFamille,
			@RequestParam("refArticle") String refArticle,
			@RequestParam("article") String article,
			@RequestParam("grosseur") String grosseur,
			@RequestParam("metrage") String metrage,
			@RequestParam("matiere") String matiere,
			@RequestParam("lot") String lot,
			@RequestParam("date") String date,
			@RequestParam("operateurQuantite") String operateurQuantite,
			@RequestParam("quantite") String quantite,
			@RequestParam("magasin") String magasin,
			@RequestParam("emplacement") String emplacement,
			@RequestParam("site") String site,
			@RequestParam("zone") String zone,
			@RequestParam("oa") String oa,
			@RequestParam("typeRapport") String typeRapport,
			@RequestParam("type") String type,
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report EtatStockHistory",type);
		
		RechercheMulticritereEntiteStockValue critere = new RechercheMulticritereEntiteStockValue();
		critere.setTypeArticle(articleType);
		critere.setDate(stringToCalendar(date));
		critere.setFamilleArticle(famille);
		critere.setSousFamilleArticle(sousFamille);
		critere.setEmplacement(emplacement);
		critere.setRefArticle(refArticle);
		critere.setArticle(article);
		critere.setMetrage(metrage);
		critere.setGrosseur(grosseur);
		critere.setMatiere(matiere);
		critere.setMagasin(magasin);
		critere.setLot(lot);
		critere.setOperateurQuantite(operateurQuantite);
		if(isNotEmty(quantite)){
			critere.setQuantite(Double.parseDouble(quantite));
		}
		critere.setSite(site);
		critere.setZoneDispo(zone);
		critere.setOa(oa);
		
		EtatStockReportValue report = gestionnaireReportGsService.getEtatReport(critere, typeRapport);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/etatStockNonMouvementes", method = RequestMethod.GET)
	public void generateEtatStockNonMouvementesReport(
			@RequestParam("articleType") String articleType,
			@RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report EtatStockNonMouvementes",type);
		
		RechercheMulticritereEntiteStockValue critere = new RechercheMulticritereEntiteStockValue();
		critere.setTypeArticle(articleType);
		
		EtatStockReportValue report = gestionnaireReportGsService.getEtatNonMouvementesReport(critere);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);
	}
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/etatMouvement", method = RequestMethod.GET)
	public void generateEtatMouvementeReport(
			//@RequestParam("requestRechecheMouvementValue") RequestRechecheMouvementValue request,
			//id:entiteStock ID
			@RequestParam("id") Long id,
			@RequestParam("dateMin") String dateMin,
			@RequestParam("dateMax") String dateMax,
			@RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report EtatMouvement",type);
		
		RequestRechecheMouvementValue request = new RequestRechecheMouvementValue();
		request.setEntiteStockId(id);
		request.setDateMin(stringToCalendar(dateMin));
		request.setDateMax(stringToCalendar(dateMax));
		
		EtatMouvementReportValue report = gestionnaireReportGsService.getEtatMouvementReport(request);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/bonMouvementStockById", method = RequestMethod.GET)
	public void generateBonMouvementStockByIdReport(
			@RequestParam("id") Long id,
			@RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report for BonMouvementStock",type);
		
		BonMouvementStockReportValue report = gestionnaireReportGsService.getBonMouvementStockById(id);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/bonMouvementStockEntreeSortieById", method = RequestMethod.GET)
	public void generateBonMouvementStockEntreeSortieByIdReport(
			@RequestParam("id") Long id,
			@RequestParam("type") String type, HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report for BonMouvementStock",type);
		
		BonMouvementStockReportValue report = gestionnaireReportGsService.getBonMouvementStockEntreeSortieById(id);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);
	}
	
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/etatStockGlobal", method = RequestMethod.GET)
	public void generateetatStockGlobalReport(
			@RequestParam("articleType") String articleType,
			@RequestParam("famille") String famille,
			@RequestParam("sousFamille") String sousFamille,
			@RequestParam("refArticle") String refArticle,
			@RequestParam("article") String article,
			@RequestParam("grosseur") String grosseur,
			@RequestParam("metrage") String metrage,
			@RequestParam("matiere") String matiere,
			@RequestParam("lot") String lot,
			@RequestParam("date") String date,
			@RequestParam("operateurQuantite") String operateurQuantite,
			@RequestParam("quantite") String quantite,
			@RequestParam("magasin") String magasin,
			@RequestParam("emplacement") String emplacement,
			@RequestParam("site") String site,
			@RequestParam("zone") String zone,
			@RequestParam("oa") String oa,
			@RequestParam("typeRapport") String typeRapport,
			@RequestParam("type") String type,
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report EtatStockGlobal",type);
		//System.out.println("Generate a {} Report EtatStockGlobal rest layer");
		RechercheMulticritereEntiteStockValue critere = new RechercheMulticritereEntiteStockValue();
		
		critere.setTypeArticle(articleType);
		critere.setDate(stringToCalendar(date));
		critere.setFamilleArticle(famille);
		critere.setSousFamilleArticle(sousFamille);
		critere.setEmplacement(emplacement);
		critere.setRefArticle(refArticle);
		critere.setArticle(article);
		critere.setMetrage(metrage);
		critere.setGrosseur(grosseur);
		critere.setMatiere(matiere);
		critere.setMagasin(magasin);
		critere.setLot(lot);
		critere.setOperateurQuantite(operateurQuantite);
		if(isNotEmty(quantite)){
			critere.setQuantite(Double.parseDouble(quantite));
		}
		critere.setSite(site);
		critere.setZoneDispo(zone);
		critere.setOa(oa);
		
		EtatStockGlobalReportValue report = gestionnaireReportGsService.getEtatGlobalReport(critere, typeRapport);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/etatStockDetaille", method = RequestMethod.GET)
	public void generateetatStockDetailleReport(
			@RequestParam("articleType") String articleType,
			@RequestParam("famille") String famille,
			@RequestParam("sousFamille") String sousFamille,
			@RequestParam("refArticle") String refArticle,
			@RequestParam("article") String article,
			@RequestParam("grosseur") String grosseur,
			@RequestParam("metrage") String metrage,
			@RequestParam("matiere") String matiere,
			@RequestParam("lot") String lot,
			@RequestParam("date") String date,
			@RequestParam("operateurQuantite") String operateurQuantite,
			@RequestParam("quantite") String quantite,
			@RequestParam("magasin") String magasin,
			@RequestParam("emplacement") String emplacement,
			@RequestParam("site") String site,
			@RequestParam("zone") String zone,
			@RequestParam("oa") String oa,
			@RequestParam("typeRapport") String typeRapport,
			@RequestParam("type") String type,
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report EtatStockGlobal",type);
		RechercheMulticritereEntiteStockValue critere = new RechercheMulticritereEntiteStockValue();
		
		critere.setTypeArticle(articleType);
		critere.setDate(stringToCalendar(date));
		critere.setFamilleArticle(famille);
		critere.setSousFamilleArticle(sousFamille);
		critere.setEmplacement(emplacement);
		critere.setRefArticle(refArticle);
		critere.setArticle(article);
		critere.setMetrage(metrage);
		critere.setGrosseur(grosseur);
		critere.setMatiere(matiere);
		critere.setMagasin(magasin);
		critere.setLot(lot);
		critere.setOperateurQuantite(operateurQuantite);
		if(isNotEmty(quantite)){
			critere.setQuantite(Double.parseDouble(quantite));
		}
		critere.setSite(site);
		critere.setZoneDispo(zone);
		critere.setOa(oa);
		EtatStockDetailleReportValue report = gestionnaireReportGsService.getEtatDetailleReport(critere, typeRapport);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
				report.getFileName(),report.getJRBeanCollectionDataSource(), response);
	}
	
	//Added on 15/11/2016, by Zeineb
	
	@SuppressWarnings("static-access")
	@RequestMapping(value="/mouvementStockHistoryDetaille", method = RequestMethod.GET)
	public void generateMouvementStockHistoryReportDetaille(
			@RequestParam("historique") String historique,
			@RequestParam("articleType") String articleType,
			@RequestParam("dateMin") String dateMin,
			@RequestParam("dateMax") String dateMax,
			@RequestParam("famille") String famille,
			@RequestParam("sousFamille") String sousFamille,
			@RequestParam("emplacement") String emplacement,
			@RequestParam("refArticle") String refArticle,
			@RequestParam("article") String article,
			@RequestParam("magasin") String magasinVal,
			@RequestParam("lot") String lot,
			@RequestParam("raison") String raison,
			@RequestParam("ofId") String ofId,
			@RequestParam("client") String clientVal,
			@RequestParam("fournisseur") String fournisseur,
			@RequestParam("oa") String oa,
			@RequestParam("type") String type,
			HttpServletResponse response ) throws JRException, IOException {
		
		logger.info("Generate a {} Report MouvementStockHistoryDetaille",type);
		RechercheMulticritereMouvementValue critere = new RechercheMulticritereMouvementValue();
		critere.setHistorique(historique);
		critere.setType(articleType);
		
		critere.setDateDu(stringToCalendar(dateMin));
		critere.setDateA(stringToCalendar(dateMax));
		
		critere.setFamille(famille);
		critere.setSousFamille(sousFamille);
		critere.setEmplacement(emplacement);
		critere.setRefArticle(refArticle);
		critere.setArticle(article);
		critere.setMagasin(magasinVal);
		critere.setLot(lot);
		critere.setRaison(raison);
		Long of = isNotEmty(ofId)? Long.parseLong(ofId):null; 
		critere.setOfId(of);
		critere.setClient(clientVal);
		critere.setFournisseur(fournisseur);
		critere.setOa(oa);
		
		MouvementStockHistoryDetailleReportValue report = gestionnaireReportGsService.getHistoryReportDetaille(critere);
		
		Map<Long, PartieInteresseCacheValue> mapClients = new HashMap<Long, PartieInteresseCacheValue>();
		Map<Long, MagasinValue> mapMagasins = new HashMap<Long, MagasinValue>();
		
		for(PartieInteresseCacheValue client : gestionnaireGSCacheService.getListeClient()){
			Long key = client.getId();
			if (mapClients.get(key) == null) {
				mapClients.put(key, client);
			}
		}
		
		for(MagasinValue magasin : gestionnaireGSCacheService.getListeMagasin()){
			Long key = magasin.getId();
			if (mapMagasins.get(key) == null) {
				mapMagasins.put(key, magasin);
			}
		}
		
		for(MouvementStockHistoryElementReportValue element : report.getElementsList()){
			
			if(element.getClientId() !=null){
				String client = (mapClients.containsKey(element.getClientId())) ? mapClients.get(element.getClientId()).getAbreviation() : null;
				element.setClient(client);
			}
			
			if(element.getMagasinId() != null){
				String designation = (mapMagasins.containsKey(element.getMagasinId())) ? mapMagasins.get(element.getMagasinId()).getDesignation() : null;
				element.setDesignationMagasin(designation);
			}
			
		}
		
		ArrayList<MouvementStockHistoryDetailleReportValue> dataList = new ArrayList<MouvementStockHistoryDetailleReportValue>();
		dataList.add(report);
		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
		
		this.download( type , report.getReportStream() ,report.getParams(), 
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
		    return value != null && !"".equals(value) && !value.equals("undefined");

		  }
}
