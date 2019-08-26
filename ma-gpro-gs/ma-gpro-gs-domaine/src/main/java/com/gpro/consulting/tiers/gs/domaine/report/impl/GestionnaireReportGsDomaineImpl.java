package com.gpro.consulting.tiers.gs.domaine.report.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.FicheBesoinValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.IArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.IFicheBesoinPersistance;
import com.gpro.consulting.tiers.commun.persistance.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.IBaseInfoPersistance;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gs.coordination.IConstante;
import com.gpro.consulting.tiers.gs.coordination.report.value.BonMouvementStockReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.EtatGlobalMapValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.EtatMouvementElementReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.EtatMouvementReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.EtatStockDetailleElementReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.EtatStockDetailleReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.EtatStockElementReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.EtatStockGlobalElementReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.EtatStockGlobalReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.EtatStockReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.MouvementStockHistoryDetailleReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.MouvementStockHistoryElementDetailleReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.MouvementStockHistoryElementReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.MouvementStockHistoryReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.MouvementStockReportValue;
import com.gpro.consulting.tiers.gs.coordination.report.value.RequestRechecheMouvementValue;
import com.gpro.consulting.tiers.gs.coordination.value.BonMouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.EntiteStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.MagasinValue;
import com.gpro.consulting.tiers.gs.coordination.value.MouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RaisonMouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereEntiteStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheEntiteStockStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheMouvementValue;
import com.gpro.consulting.tiers.gs.domaine.IBonMouvementDomaine;
import com.gpro.consulting.tiers.gs.domaine.report.IGestionnaireReportGsDomaine;
import com.gpro.consulting.tiers.gs.domaine.report.utilities.EtatMouvementElementReportComparator;
import com.gpro.consulting.tiers.gs.persitance.IEntiteStockPersistance;
import com.gpro.consulting.tiers.gs.persitance.IMagasinPersistance;
import com.gpro.consulting.tiers.gs.persitance.IMouvementPersistance;
import com.gpro.consulting.tiers.gs.persitance.IRaisonMouvementPersistance;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Implementation of {@link IGestionnaireReportGsDomaine}
 * 
 * @author Wahid Gazzah
 * @since 10/02/2016
 *
 */

@Component
public class GestionnaireReportGsDomaineImpl implements IGestionnaireReportGsDomaine {

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportGsDomaineImpl.class);

	private static final String REPORT_NAME_STOCKHISTORY = "mouvement stock history";
	private static final String REPORT_NAME_ETATSTOCK = "etat stock";
	private static final String REPORT_NAME_ETATMOUVEMENT = "etat mouvement";
	private static final String REPORT_NAME_ETATNONMOUVEMENT = "etat non mouvemente";
	private static final String REPORT_NAME_BON_MOUVENELENT = "bon de mouvement OF";
	private static final String REPORT_NAME_BON_MVT_STOK = "bon de mouvement Stock";

	private static final String ARTICLE_TYPE_1 = "1"; // fourniture
	private static final String ARTICLE_TYPE_2 = "2"; // tissu
	private static final String ARTICLE_TYPE_3 = "3"; // fileacoudre

	private static final Long ARTICLETYPE_1 = 1L; // fourniture
	private static final Long ARTICLETYPE_2 = 2L; // tissu
	private static final Long ARTICLETYPE_3 = 3L; // fileacoudre

	private static final String ENTREE = "ENTREE";
	private static final String SORTIE = "SORTIE";

	@Autowired
	private IMouvementPersistance mouvementPersistance;

	@Autowired
	private IEntiteStockPersistance entiteStockPersistance;

	@Autowired
	private IArticlePersistance articlePersistance;

	@Autowired
	private IBonMouvementDomaine bonMouvementDomaine;

	@Autowired
	private IOrdreFabricationPersistance ordreFabricationPersistance;

	@Autowired
	private IProduitPersistance produitPersistance;

	@Autowired
	private IPartieInteresseePersistance partieInteresseePersistance;

	@Autowired
	IMagasinPersistance magasinPersistance;

	@Autowired
	IRaisonMouvementPersistance raisonMouvementPersistance;
	
	@Autowired
	IBaseInfoPersistance baseInfoPersistance;

	@Autowired
	IFicheBesoinPersistance ficheBesoinPersistance;
	
	@Override
	public MouvementStockHistoryReportValue getHistoryReport(RechercheMulticritereMouvementValue critere)
			throws IOException {

		logger.info("Get Mouvement Stock History Report");

		MouvementStockHistoryReportValue report = new MouvementStockHistoryReportValue();

		// enrechissement des param du report
		report.setFileName(REPORT_NAME_STOCKHISTORY);
		report.setReportStream(new FileInputStream(IConstante.RAPPORTS_GS_BASE_URL + "Historique/mouvement_stock_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		
		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);

		if (critere != null) {
			if (critere.getType() != null) {

				switch (critere.getType()) {
				case ARTICLE_TYPE_1:
					params.put("SUBREPORT_STOCKHISTORYE_PATH",
							IConstante.RAPPORTS_GS_BASE_URL + "Historique/Basique/mouvement_stock_fourniture_sub_report.jasper");
					break;

				case ARTICLE_TYPE_2:
					params.put("SUBREPORT_STOCKHISTORYE_PATH",
							IConstante.RAPPORTS_GS_BASE_URL + "Historique/Basique/mouvement_stock_tissu_sub_report.jasper");
					break;

				case ARTICLE_TYPE_3:
					params.put("SUBREPORT_STOCKHISTORYE_PATH",
							IConstante.RAPPORTS_GS_BASE_URL + "Historique/Basique/mouvement_stock_fileacoudre_sub_report.jasper");
					break;
				}

			}
		}

		report.setParams(params);

		ResultatRechecheMouvementValue result = mouvementPersistance.rechercherMouvementMultiCritere(critere);
		
		logger.info("critere----"+ critere.toString());

		report.setHistorique(critere.getHistorique());
		report.setType(critere.getType());

		if (result != null) {
			enrichmentHistoryReportReport(report, result);

		}

		ArrayList<MouvementStockHistoryReportValue> dataList = new ArrayList<MouvementStockHistoryReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichmentHistoryReportReport(MouvementStockHistoryReportValue report,
			ResultatRechecheMouvementValue result) {

		Set<MouvementStockHistoryElementReportValue> elementsList = report.getElementsList();

		report.setTypeRapport("Rapport Basique");

		
		logger.info("result result.getMouvementStock()----"+ result.getMouvementStock().size());
		
		for (MouvementStockValue mouvementStock : result.getMouvementStock()) {
			

			MouvementStockHistoryElementReportValue elementReport = new MouvementStockHistoryElementReportValue();

			if (mouvementStock.getBonMouvement() != null) {
				elementReport.setNumero(mouvementStock.getBonMouvement().getNumero());
				elementReport.setClientId(mouvementStock.getBonMouvement().getPartieintId());
				elementReport.setDate(mouvementStock.getBonMouvement().getDate());
				elementReport.setResponsable(mouvementStock.getBonMouvement().getResponsable());
				elementReport.setValeur(mouvementStock.getBonMouvement().getValeur());
			}

			elementReport.setId(mouvementStock.getId());
			elementReport.setReferenceArticle(mouvementStock.getReferenceArticle());
			elementReport.setDesignationArticle(mouvementStock.getDesignationArticle());
			elementReport.setFamilleArticle(mouvementStock.getFamilleArticle());
			elementReport.setPoidsReel(mouvementStock.getPoidsReel());
			elementReport.setPrixUnitaire(mouvementStock.getPrixUnitaire());
			elementReport.setMagasinId(mouvementStock.getIdMagasin());
			elementReport.setEmplacement(mouvementStock.getEmplacement());
			elementReport.setNbRouleauxReel(mouvementStock.getNbRouleauxReel());
			elementReport.setFinconesReel(mouvementStock.getFinconesReel());
			elementReport.setCones(mouvementStock.getCones());
			elementReport.setQuantiteReelle(mouvementStock.getQuantiteReelle());
			
			elementReport.setOF(mouvementStock.getOF());
			elementReport.setRefProduit(mouvementStock.getRefProduit());
			elementReport.setDesignationProduit(mouvementStock.getDesignationProduit());
			
			
			elementsList.add(elementReport);
		}

		report.setElementsList(elementsList);
		
		logger.info("report.getElementsList----" + report.getElementsList().size());

	}

	@Override
	public MouvementStockHistoryDetailleReportValue getHistoryReportDetaille(
			RechercheMulticritereMouvementValue critere) throws IOException {

		logger.info("Get Mouvement Stock History Report");

		MouvementStockHistoryDetailleReportValue report = new MouvementStockHistoryDetailleReportValue();

		// enrechissement des param du report
		report.setFileName(REPORT_NAME_STOCKHISTORY);
		report.setReportStream(new FileInputStream(IConstante.RAPPORTS_GS_BASE_URL + "Historique/mouvement_stock_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		if (critere != null) {
			if (critere.getType() != null) {

				switch (critere.getType()) {
				case ARTICLE_TYPE_1:
					params.put("SUBREPORT_STOCKHISTORYE_PATH",
							IConstante.RAPPORTS_GS_BASE_URL + "Historique/Detaille/mouvement_stock_fourniture_sub_detaille_report.jasper");
					break;

				case ARTICLE_TYPE_2:
					params.put("SUBREPORT_STOCKHISTORYE_PATH",
							IConstante.RAPPORTS_GS_BASE_URL + "Historique/Detaille/mouvement_stock_tissu_sub_detaille_report.jasper");
					break;

				case ARTICLE_TYPE_3:
					params.put("SUBREPORT_STOCKHISTORYE_PATH",
							IConstante.RAPPORTS_GS_BASE_URL + "Historique/Detaille/mouvement_stock_fileacoudre_sub_detaille_report.jasper");
					break;
				}

			}
		}

		report.setParams(params);

		ResultatRechecheMouvementValue result = mouvementPersistance.rechercherMouvementMultiCritere(critere);

		report.setHistorique(critere.getHistorique());
		report.setType(critere.getType());

		if (result != null) {
			enrichmentHistoryReportDetaille(report, result);

		}

		ArrayList<MouvementStockHistoryDetailleReportValue> dataList = new ArrayList<MouvementStockHistoryDetailleReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichmentHistoryReportDetaille(MouvementStockHistoryDetailleReportValue report,
			ResultatRechecheMouvementValue result) {

		Set<MouvementStockHistoryElementDetailleReportValue> elementsList = report.getElementsList();

		report.setTypeRapport("Rapport Détaillée");

		for (MouvementStockValue mouvementStock : result.getMouvementStock()) {

			MouvementStockHistoryElementDetailleReportValue elementReport = new MouvementStockHistoryElementDetailleReportValue();

			if (mouvementStock.getBonMouvement() != null) {
				elementReport.setNumero(mouvementStock.getBonMouvement().getNumero());
				elementReport.setClientId(mouvementStock.getBonMouvement().getPartieintId());
				elementReport.setDate(mouvementStock.getBonMouvement().getDate());
				elementReport.setResponsable(mouvementStock.getBonMouvement().getResponsable());
				elementReport.setValeur(mouvementStock.getBonMouvement().getValeur());
			}

			elementReport.setId(mouvementStock.getId());
			elementReport.setReferenceArticle(mouvementStock.getReferenceArticle());
			elementReport.setDesignationArticle(mouvementStock.getDesignationArticle());
			elementReport.setFamilleArticle(mouvementStock.getFamilleArticle());
			elementReport.setQuantiteReelle(mouvementStock.getQuantiteReelle());
			elementReport.setPoidsReel(mouvementStock.getPoidsReel());
			elementReport.setPrixUnitaire(mouvementStock.getPrixUnitaire());
			elementReport.setMagasinId(mouvementStock.getIdMagasin());
			elementReport.setEmplacement(mouvementStock.getEmplacement());
			elementReport.setNbRouleauxReel(mouvementStock.getNbRouleauxReel());
			elementReport.setCones(mouvementStock.getCones());
			elementReport.setFinconesReel(mouvementStock.getFinconesReel());

			elementReport.setSousFamille(mouvementStock.getSousFamilleArticle());
			elementReport.setRefLot(mouvementStock.getLot());

			if (mouvementStock.getIdArticle() != null) {
				ArticleValue article = articlePersistance.getArticleParId(mouvementStock.getIdArticle());

				if (article != null) {
					elementReport.setCouleurArticle(article.getCouleur());
					elementReport.setCodeFournisseur(article.getCodeFournisseur());
					elementReport.setProducteur(article.getProducteur());
				}

			}
			
			elementReport.setOF(mouvementStock.getOF());
			elementReport.setRefProduit(mouvementStock.getRefProduit());
			elementReport.setDesignationProduit(mouvementStock.getDesignationProduit());
			
			elementsList.add(elementReport);
		}
	}

	@Override
	public EtatStockReportValue getEtatReport(RechercheMulticritereEntiteStockValue critere, String typeRapport)
			throws IOException {

		logger.info("Get Etat Stock Report");

		EtatStockReportValue report = new EtatStockReportValue();

		// enrechissement des param du report
		report.setFileName(REPORT_NAME_ETATSTOCK);
		report.setReportStream(new FileInputStream(IConstante.RAPPORTS_GS_BASE_URL + "EtatStock/etat_stock_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		
		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);

		if (critere != null) {
			if (critere.getTypeArticle() != null) {

				switch (critere.getTypeArticle()) {
				case ARTICLE_TYPE_1:
					params.put("SUBREPORT_STOCKHISTORYE_PATH",
							IConstante.RAPPORTS_GS_BASE_URL + "EtatStock/Basique/etat_stock_fourniture_sub_report.jasper");
					break;

				case ARTICLE_TYPE_2:
					params.put("SUBREPORT_STOCKHISTORYE_PATH",
							IConstante.RAPPORTS_GS_BASE_URL + "EtatStock/Basique/etat_stock_tissu_sub_report.jasper");
					break;

				case ARTICLE_TYPE_3:
					params.put("SUBREPORT_STOCKHISTORYE_PATH",
							IConstante.RAPPORTS_GS_BASE_URL + "EtatStock/Basique/etat_stock_fileacoudre_sub_report.jasper");
					break;
				}

			}
		}

		report.setParams(params);
		report.setType(critere.getTypeArticle());
		report.setTypeRapport(typeRapport);

		ResultatRechecheEntiteStockStockValue result = entiteStockPersistance
				.rechercherEntiteStockMultiCritere(critere);

		if (result != null) {
			enrichmentEtatReport(report, result);
		}

		ArrayList<EtatStockReportValue> dataList = new ArrayList<EtatStockReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	@Override
	public EtatStockReportValue getEtatNonMouvementesReport(RechercheMulticritereEntiteStockValue critere)
			throws IOException {

		logger.info("Get Etat Stock non mouvementes Report");

		EtatStockReportValue report = new EtatStockReportValue();

		// enrechissement des param du report
		report.setFileName(REPORT_NAME_ETATNONMOUVEMENT);
		report.setReportStream(new FileInputStream(IConstante.RAPPORTS_GS_BASE_URL + "EtatNonMouvemente/etat_stock_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		if (critere != null) {

			critere.setMouvement(IConstante.NO);

			if (critere.getTypeArticle() != null) {

				switch (critere.getTypeArticle()) {
				case ARTICLE_TYPE_1:
					params.put("SUBREPORT_STOCKHISTORYE_PATH",
							IConstante.RAPPORTS_GS_BASE_URL + "EtatNonMouvemente/etat_fourniture_sub_report.jasper");
					break;

				case ARTICLE_TYPE_2:
					params.put("SUBREPORT_STOCKHISTORYE_PATH",
							IConstante.RAPPORTS_GS_BASE_URL + "EtatNonMouvemente/etat_tissu_sub_report.jasper");
					break;

				case ARTICLE_TYPE_3:
					params.put("SUBREPORT_STOCKHISTORYE_PATH",
							IConstante.RAPPORTS_GS_BASE_URL + "EtatNonMouvemente/etat_fileacoudre_sub_report.jasper");
					break;
				}

			}
		}

		report.setParams(params);

		ResultatRechecheEntiteStockStockValue result = entiteStockPersistance
				.rechercherEntiteStockMultiCritere(critere);

		if (result != null) {
			enrichmentEtatReport(report, result);
		}

		ArrayList<EtatStockReportValue> dataList = new ArrayList<EtatStockReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichmentEtatReport(EtatStockReportValue report, ResultatRechecheEntiteStockStockValue result) {

		Set<EtatStockElementReportValue> elementsList = report.getElementsList();

		for (EntiteStockValue entiteStock : result.getEntiteStock()) {

			EtatStockElementReportValue elementReport = new EtatStockElementReportValue();

			elementReport.setId(entiteStock.getId());
			elementReport.setDesignationMagasin(entiteStock.getDesignationMagasin());
			elementReport.setEmplacement(entiteStock.getEmplacement());
			elementReport.setFamilleArticle(entiteStock.getFamilleArticle());
			elementReport.setFinconeActuel(entiteStock.getFinconeActuel());
			elementReport.setLibelleArticle(entiteStock.getLibelleArticle());
			elementReport.setOA(entiteStock.getOa());
			elementReport.setPmp(entiteStock.getPmp());
			elementReport.setPrixTotal(entiteStock.getPrixTotal());
			elementReport.setPrixUnitaire(entiteStock.getPrixUnitaire());
			elementReport.setQteActuelle(entiteStock.getQteActuelle());
			elementReport.setReferenceArticle(entiteStock.getReferenceArticle());
			elementReport.setRouleauxActuel(entiteStock.getRouleauxActuel());
			elementReport.setFinconeReserve(entiteStock.getFinconeReserve());
			elementsList.add(elementReport);
		}

		report.setElementsList(elementsList);

	}

	@Override
	public EtatMouvementReportValue getEtatMouvementReport(RequestRechecheMouvementValue request) throws IOException {

		logger.info("Get EtatMouvement Report");

		EtatMouvementReportValue report = new EtatMouvementReportValue();

		// enrechissement des param du report
		report.setFileName(REPORT_NAME_ETATMOUVEMENT);
		report.setReportStream(new FileInputStream(IConstante.RAPPORTS_GS_BASE_URL + "EtatMouvement/etat_mouvement_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_ETATMOUVEMENT_PATH", IConstante.RAPPORTS_GS_BASE_URL + "EtatMouvement/etat_mouvement_sub_report.jasper");
		
		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);
		
		
		ResultatRechecheMouvementValue result = mouvementPersistance.rechercherEtatMouvement(request);

		if ((request.getEntiteStockId()) != null) {
			EntiteStockValue entiteStock = entiteStockPersistance.rechercheEntiteStockParId(request.getEntiteStockId());

			if (entiteStock != null) {
				report.setDesignationArticle(entiteStock.getLibelleArticle());
				report.setReferenceArticle(entiteStock.getReferenceArticle());
				report.setDateEntree(entiteStock.getDateEntree());
				report.setLot(entiteStock.getReferenceLot());

			}

		}

		report.setDateMin(request.getDateMin());
		report.setDateMax(request.getDateMax());

		if (result != null) {
			enrichmentEtatMouvementReport(report, result);
		}

		Collections.sort(report.getElementsList(), new EtatMouvementElementReportComparator());

		ArrayList<EtatMouvementReportValue> dataList = new ArrayList<EtatMouvementReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichmentEtatMouvementReport(EtatMouvementReportValue report, ResultatRechecheMouvementValue result) {

		List<EtatMouvementElementReportValue> elementsList = report.getElementsList();
		String bonMouvementStockRaison = "";
		
		for (MouvementStockValue mouvement : result.getMouvementStock()) {

			EtatMouvementElementReportValue elementReport = new EtatMouvementElementReportValue();

			BonMouvementStockValue bonMouvement = bonMouvementDomaine.rechercheBonMouvementParId(mouvement.getBonMouvementId());
			
			elementReport.setId(mouvement.getId());
			elementReport.setQuantiteReelle(mouvement.getQuantiteReelle());
			elementReport.setTypeEntree(mouvement.getType());

			if (mouvement != null) {

				if (bonMouvement != null) {
					elementReport.setBonMouvementStockDate(bonMouvement.getDate());
					elementReport.setBonMouvementStockNumero(bonMouvement.getNumero());

					if(bonMouvement.getRaisonMouvementId()!=null){
						RaisonMouvementStockValue raisonValue= raisonMouvementPersistance
								.rechercheRaisonMouvementStockParId(bonMouvement.getRaisonMouvementId());
						bonMouvementStockRaison =  raisonValue.getDesignation();
					}
					
					
					if (bonMouvement.getOfId() != null) {

						OrdreFabricationValue OFValue = ordreFabricationPersistance.rechercheOrdreFabricationParId(bonMouvement.getOfId());
						if(OFValue != null){
							bonMouvementStockRaison+= " " + OFValue.getNumero();
						}
								
					}
					
					elementReport.setBonMouvementStockRaison(bonMouvementStockRaison);
					
				}

				elementsList.add(elementReport);
			}
		}

		report.setElementsList(elementsList);

	}
	// MvtOF
	@Override
	public BonMouvementStockReportValue getBonMouvementStockById(Long id) throws IOException {

		logger.info("Get BonMouvement Report");

		BonMouvementStockReportValue report = new BonMouvementStockReportValue();

		// enrechissement des param du report
		report.setFileName(REPORT_NAME_BON_MOUVENELENT);
		report.setReportStream(new FileInputStream(IConstante.RAPPORTS_GS_BASE_URL + "BonMvtOf/bon_mouvement_stock_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();

		params.put("SUBREPORT_BONMOUVEMENT_1_PATH",
				IConstante.RAPPORTS_GS_BASE_URL + "BonMvtOf/Reservation/bon_mouvement_stock_fourniture_sub_report.jasper");
		params.put("SUBREPORT_BONMOUVEMENT_2_PATH",
				IConstante.RAPPORTS_GS_BASE_URL + "BonMvtOf/Reservation/bon_mouvement_stock_tissu_sub_report.jasper");
		params.put("SUBREPORT_BONMOUVEMENT_3_PATH",
				IConstante.RAPPORTS_GS_BASE_URL + "BonMvtOf/Reservation/bon_mouvement_stock_fileacoudre_sub_report.jasper");

		params.put("SUBREPORT_BONMOUVEMENT_1_SORTIE_PATH",
				IConstante.RAPPORTS_GS_BASE_URL + "BonMvtOf/Sortie/bon_mouvement_stock_fourniture_sortie_sub_report.jasper");
		params.put("SUBREPORT_BONMOUVEMENT_2_SORTIE_PATH",
				IConstante.RAPPORTS_GS_BASE_URL + "BonMvtOf/Sortie/bon_mouvement_stock_tissu_sortie_sub_report.jasper");
		params.put("SUBREPORT_BONMOUVEMENT_3_SORTIE_PATH",
				IConstante.RAPPORTS_GS_BASE_URL + "BonMvtOf/Sortie/bon_mouvement_stock_fileacoudre_sortie_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		BonMouvementStockValue bonMouvementStockValue = bonMouvementDomaine.rechercheBonMouvementParId(id);

		if (bonMouvementStockValue != null) {

			enrichmentBonMouvementStockReport(report, bonMouvementStockValue);

		}

		ArrayList<BonMouvementStockReportValue> dataList = new ArrayList<BonMouvementStockReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	// Enrichir MvtOF
	private void enrichmentBonMouvementStockReport(BonMouvementStockReportValue report, BonMouvementStockValue value) {

		report.setNumero(value.getNumero());
		report.setDate(value.getDate());
		report.setResponsable(value.getResponsable());

		report.setRaisonMouvementId(value.getRaisonMouvementId());
		report.setRaisonMouvementDesignation(value.getRaisonMouvementDesignation());

		report.setValeur(value.getValeur());
		report.setDescription(value.getDescription());
		report.setType(value.getType());
		report.setOfId(value.getOfId());

		if (value.getOfId() != null) {

			OrdreFabricationValue ordreFabrication = ordreFabricationPersistance
					.rechercheOrdreFabricationParId(value.getOfId());

			if (ordreFabrication != null) {

				report.setOfNumero(ordreFabrication.getNumero());

				if (ordreFabrication.getProduitId() != null) {

					ProduitValue produit = produitPersistance.rechercheProduitById(ordreFabrication.getProduitId());

					if (produit != null) {
						String description=IConstante.VIDE;						
//                        FicheBesoinValue fiche=ficheBesoinPersistance.rechercheFicheBesoinParId(616L);
//                        if(fiche!=null){
//                           if(fiche.getObservation()!=null)
//                        	  description.concat(" "+fiche.getObservation());
//                        }
                        report.setDescription(description);
						report.setProduitReference(produit.getReference());
						report.setProduitDesignation(produit.getDesignation());
					}
				}

				if (ordreFabrication.getPartieInterresId() != null) {

					PartieInteresseValue client = partieInteresseePersistance
							.getById(ordreFabrication.getPartieInterresId());

					if (client != null) {

						report.setClientAbreviation(client.getAbreviation());
					}
				}
			}
		}

		for (MouvementStockValue mouvementStockValue : value.getMouvements()) {

			MouvementStockReportValue mouvementStockReport = new MouvementStockReportValue();

			mouvementStockReport.setId(mouvementStockValue.getId());
			mouvementStockReport.setReferenceArticle(mouvementStockValue.getReferenceArticle());
			mouvementStockReport.setDesignationArticle(mouvementStockValue.getDesignationArticle());
			mouvementStockReport.setFamilleArticle(mouvementStockValue.getFamilleArticle());
            
			if (mouvementStockValue.getIdMagasin() != null) {
				MagasinValue vMagasin = new MagasinValue();
				vMagasin.setId(mouvementStockValue.getIdMagasin());
				MagasinValue magasin = magasinPersistance.rechercheMagasinParId(vMagasin);

				report.setMagasinDesignation(magasin.getDesignation());
			}

			mouvementStockReport.setQuantiteOF(mouvementStockValue.getQteOF());
			mouvementStockReport.setBesoinOF(mouvementStockValue.getBesoinOF());
			mouvementStockReport.setEmplacement(mouvementStockValue.getEmplacement());
            mouvementStockReport.setLot(mouvementStockValue.getLot());
			if (mouvementStockValue.getBesoinOF() != null & mouvementStockValue.getQteOF() != null && mouvementStockValue.getQteOF()!=0) {
				mouvementStockReport.setBu(mouvementStockValue.getBesoinOF() / mouvementStockValue.getQteOF());
			}

			mouvementStockReport.setQuantiteReelle(mouvementStockValue.getQuantiteReelle());
			mouvementStockReport.setReservationOF(mouvementStockValue.getReservationOF());
			mouvementStockReport.setNbRouleauxReel(mouvementStockValue.getNbRouleauxReel());
            
			logger.info("----- type rapport -----" + value.getType());

			if (value.getType().equals("RESERVATION")) {

				if (mouvementStockValue.getTypeArticle() != null) {

					if (mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_1)) {

						report.getElementsList1().add(mouvementStockReport);

					}
					if (mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_2)) {

						report.getElementsList2().add(mouvementStockReport);
					}
					if (mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_3)) {

						report.getElementsList3().add(mouvementStockReport);
					}

					if (mouvementStockValue.getBesoinOF() != null & mouvementStockValue.getQteOF() != null) {
						mouvementStockReport.setBu(mouvementStockValue.getBesoinOF() / mouvementStockValue.getQteOF());
					}
				}

			} else if (value.getType().equals("SORTIE")) {

				report.setRefReservation(value.getNumBRSortie());

				BonMouvementStockValue bonMvtReservation = bonMouvementDomaine
						.rechercheBonMouvementParNum(value.getNumBRSortie());
				report.setDateReservation(bonMvtReservation.getDate());

				logger.info("mouvementStockValue-------------" + mouvementStockValue.toString());

				if (mouvementStockValue.getTypeArticle() != null) {

					if (mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_1)) {

						logger.info("fourniture--------------");

						report.getElementsList1Sortie().add(mouvementStockReport);

					}
					if (mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_2)) {

						report.getElementsList2Sortie().add(mouvementStockReport);
					}
					if (mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_3)) {

						report.getElementsList3Sortie().add(mouvementStockReport);
					}

				}

			}
		}

		logger.info("report.getElementsList1Sortie().length----" + report.getElementsList1Sortie().size());
	}

	// MvtStock
	public BonMouvementStockReportValue getBonMouvementStockEntreeSortieById(Long id) throws IOException {

		BonMouvementStockReportValue report = new BonMouvementStockReportValue();

		// enrechissement des param du report
		report.setFileName(REPORT_NAME_BON_MVT_STOK);

		BonMouvementStockValue bonMouvementStockValue = bonMouvementDomaine.rechercheBonMouvementParId(id);
		logger.info("----------- ***TYPE -----------" + bonMouvementStockValue.getType());
		switch (bonMouvementStockValue.getType()) {
		case ENTREE:
			logger.info("----------- ENTREE** -----------");
			HashMap<String, Object> params = new HashMap<String, Object>();
			report.setReportStream(new FileInputStream(
					IConstante.RAPPORTS_GS_BASE_URL + "BonMvtStock/Entree/getById/bon_mouvement_stock_entree_report.jrxml"));

			params.put("SUBREPORT_Mvt_STOCK_ENTREE_1_PATH",
					IConstante.RAPPORTS_GS_BASE_URL + "BonMvtStock/Entree/getById/bon_mouvement_stock_entree_fourniture_sub_report.jasper");
			params.put("SUBREPORT_Mvt_STOCK_ENTREE_2_PATH",
					IConstante.RAPPORTS_GS_BASE_URL + "BonMvtStock/Entree/getById/bon_mouvement_stock_entree_tissu_sub_report.jasper");
			params.put("SUBREPORT_Mvt_STOCK_ENTREE_3_PATH",
					IConstante.RAPPORTS_GS_BASE_URL + "BonMvtStock/Entree/getById/bon_mouvement_stock_entree_fileacoudre_sub_report.jasper");

			String logo = baseInfoPersistance.getLogo();
			params.put("PATH_LOGO", logo);
			
			report.setParams(params);
			if (bonMouvementStockValue != null) {

				enrichirBonMouvementStockEntreeReport(report, bonMouvementStockValue);
			}

			ArrayList<BonMouvementStockReportValue> dataList = new ArrayList<BonMouvementStockReportValue>();
			dataList.add(report);

			JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

			report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
			break;
		case SORTIE:

			logger.info("----------- SORTIE -----------");
			HashMap<String, Object> paramsSortie = new HashMap<String, Object>();
			report.setReportStream(new FileInputStream(
					IConstante.RAPPORTS_GS_BASE_URL + "BonMvtStock/Sortie/getById/bon_mouvement_stock_sortie_report.jrxml"));

			paramsSortie.put("SUBREPORT_Mvt_STOCK_SORTIE_1_PATH",
					IConstante.RAPPORTS_GS_BASE_URL + "BonMvtStock/Sortie/getById/bon_mouvement_stock_sortie_fourniture_sub_report.jasper");
			paramsSortie.put("SUBREPORT_Mvt_STOCK_SORTIE_2_PATH",
					IConstante.RAPPORTS_GS_BASE_URL + "BonMvtStock/Sortie/getById/bon_mouvement_stock_sortie_tissu_sub_report.jasper");
			paramsSortie.put("SUBREPORT_Mvt_STOCK_SORTIE_3_PATH",
					IConstante.RAPPORTS_GS_BASE_URL + "BonMvtStock/Sortie/getById/bon_mouvement_stock_sortie_fileacoudre_sub_report.jasper");

			String logoSortie = baseInfoPersistance.getLogo();
			paramsSortie.put("PATH_LOGO", logoSortie);
			report.setParams(paramsSortie);
			if (bonMouvementStockValue != null) {

				enrichirBonMouvementStockSortieReport(report, bonMouvementStockValue);
			}

			ArrayList<BonMouvementStockReportValue> dataListSortie = new ArrayList<BonMouvementStockReportValue>();
			dataListSortie.add(report);

			JRBeanCollectionDataSource jRBeanCollectionDataSourceSortie = new JRBeanCollectionDataSource(
					dataListSortie);

			report.setJRBeanCollectionDataSource(jRBeanCollectionDataSourceSortie);
			break;
		}

		return report;

	}

	// Enrichir MvtStock Entrée
	private void enrichirBonMouvementStockEntreeReport(BonMouvementStockReportValue report,
			BonMouvementStockValue value) {

		logger.info("----------- SubReport :ENTREE -----------");
		report.setNumero(value.getNumero());
		report.setDate(value.getDate());
		report.setResponsable(value.getResponsable());
		report.setRaisonMouvementId(value.getRaisonMouvementId());

		// RaisonId > RaisonDesignation
		if (value.getRaisonMouvementId() != null) {
			RaisonMouvementStockValue pRaisonMouvementStockValue = new RaisonMouvementStockValue();
			pRaisonMouvementStockValue.setId(value.getRaisonMouvementId());
			RaisonMouvementStockValue vRaison = raisonMouvementPersistance
					.rechercheRaisonMouvementStockParId(pRaisonMouvementStockValue);
			if (vRaison != null)
				report.setRaisonMouvementDesignation(vRaison.getDesignation());
		}

		if (value.getPartieintId() != null) {
			PartieInteresseValue pPIValue = partieInteresseePersistance
					.recherchePartieInteresseeParId(value.getPartieintId());
			if (pPIValue != null)
				report.setClientAbreviation(pPIValue.getAbreviation());
		}

		report.setValeur(value.getValeur());
		report.setDescription(value.getDescription());
		for (MouvementStockValue mouvementStockValue : value.getMouvements()) {

			MouvementStockReportValue mouvementStockReport = new MouvementStockReportValue();

			mouvementStockReport.setId(mouvementStockValue.getId());
			mouvementStockReport.setReferenceArticle(mouvementStockValue.getReferenceArticle());
			mouvementStockReport.setDesignationArticle(mouvementStockValue.getDesignationArticle());
			mouvementStockReport.setFamilleArticle(mouvementStockValue.getFamilleArticle());
			mouvementStockReport.setQuantiteAct(mouvementStockValue.getQuantite());
			mouvementStockReport.setPrixUnitaire(mouvementStockValue.getPrixUnitaire());
			mouvementStockReport.setEmplacement(mouvementStockValue.getEmplacement());

			if (mouvementStockValue.getTypeArticle() != null) {

				if (mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_1)) {

					mouvementStockReport.setQuantiteReelle(mouvementStockValue.getQuantiteReelle());
					report.getElementsList1().add(mouvementStockReport);

				}
				if (mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_2)) {

					mouvementStockReport.setQuantiteReelle(mouvementStockValue.getQuantiteReelle());
					mouvementStockReport.setNbRouleauxReel(mouvementStockValue.getNbRouleauxReel());

					report.getElementsList2().add(mouvementStockReport);
				}
				if (mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_3)) {
					mouvementStockReport.setConesReel(mouvementStockValue.getConesReel());
					mouvementStockReport.setFinconesReel(mouvementStockValue.getFinconesReel());
					mouvementStockReport.setPoids(mouvementStockValue.getPoids());
					report.getElementsList3().add(mouvementStockReport);
				}

			}

		}

	}

	// Enrichir MvtStock Sortie
	private void enrichirBonMouvementStockSortieReport(BonMouvementStockReportValue report,
			BonMouvementStockValue value) {
		logger.info("----------- SubReport :SORTIE -----------");
		report.setNumero(value.getNumero());
		report.setDate(value.getDate());
		report.setResponsable(value.getResponsable());
		report.setRaisonMouvementId(value.getRaisonMouvementId());

		// RaisonId > RaisonDesignation
		if (value.getRaisonMouvementId() != null) {
			RaisonMouvementStockValue pRaisonMouvementStockValue = new RaisonMouvementStockValue();
			pRaisonMouvementStockValue.setId(value.getRaisonMouvementId());
			RaisonMouvementStockValue vRaison = raisonMouvementPersistance
					.rechercheRaisonMouvementStockParId(pRaisonMouvementStockValue);
			if (vRaison != null)
				report.setRaisonMouvementDesignation(vRaison.getDesignation());
		}

		if (value.getPartieintId() != null) {
			PartieInteresseValue pPIValue = partieInteresseePersistance
					.recherchePartieInteresseeParId(value.getPartieintId());
			if (pPIValue != null)
				report.setClientAbreviation(pPIValue.getAbreviation());
		}

		report.setValeur(value.getValeur());
		report.setDescription(value.getDescription());

		for (MouvementStockValue mouvementStockValue : value.getMouvements()) {

			MouvementStockReportValue mouvementStockReport = new MouvementStockReportValue();

			mouvementStockReport.setId(mouvementStockValue.getId());
			mouvementStockReport.setReferenceArticle(mouvementStockValue.getReferenceArticle());
			mouvementStockReport.setDesignationArticle(mouvementStockValue.getDesignationArticle());
			mouvementStockReport.setFamilleArticle(mouvementStockValue.getFamilleArticle());
			mouvementStockReport.setQuantiteAct(mouvementStockValue.getQuantite());
			mouvementStockReport.setPrixUnitaire(mouvementStockValue.getPrixUnitaire());
			mouvementStockReport.setEmplacement(mouvementStockValue.getEmplacement());

			if (mouvementStockValue.getTypeArticle() != null) {

				if (mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_1)) {

					mouvementStockReport.setQuantiteReelle(mouvementStockValue.getQuantiteReelle());
					report.getElementsList1().add(mouvementStockReport);

				}
				if (mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_2)) {

					mouvementStockReport.setQuantiteReelle(mouvementStockValue.getQuantiteReelle());
					mouvementStockReport.setNbRouleauxReel(mouvementStockValue.getNbRouleauxReel());

					report.getElementsList2().add(mouvementStockReport);
				}
				if (mouvementStockValue.getTypeArticle().equals(ARTICLETYPE_3)) {
					mouvementStockReport.setConesReel(mouvementStockValue.getConesReel());
					mouvementStockReport.setFinconesReel(mouvementStockValue.getFinconesReel());
					mouvementStockReport.setPoids(mouvementStockValue.getPoids());
					report.getElementsList3().add(mouvementStockReport);
				}

			}

		}

	}

	@Override
	public EtatStockGlobalReportValue getEtatGlobalReport(RechercheMulticritereEntiteStockValue critere,
			String typeRapport) throws IOException {

		logger.info("Get Etat Stock Global Report");
		//System.out.println("Generate a {} Report EtatStockGlobal domaine layer");
		EtatStockGlobalReportValue report = new EtatStockGlobalReportValue();

		// enrechissement des param du report
		report.setFileName(REPORT_NAME_ETATSTOCK);
		report.setReportStream(new FileInputStream(IConstante.RAPPORTS_GS_BASE_URL + "EtatStock/etat_stock_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		
		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);

		if (critere != null) {
			if (critere.getTypeArticle() != null) {

				switch (critere.getTypeArticle()) {
				case ARTICLE_TYPE_1:
					params.put("SUBREPORT_STOCKHISTORYE_PATH",
							IConstante.RAPPORTS_GS_BASE_URL + "EtatStock/Global/etat_stock_fourniture_sub_report.jasper");
					break;

				case ARTICLE_TYPE_2:
					params.put("SUBREPORT_STOCKHISTORYE_PATH",
							IConstante.RAPPORTS_GS_BASE_URL + "EtatStock/Global/etat_stock_tissu_sub_report.jasper");
					break;
				}

			}
		}

		report.setParams(params);
		report.setType(critere.getTypeArticle());
		report.setTypeRapport(typeRapport);

		ResultatRechecheEntiteStockStockValue result = entiteStockPersistance
				.rechercherEntiteStockMultiCritere(critere);

		if (result != null) {
			enrichissementEtatGlobalReport(report, result);
		}

		ArrayList<EtatStockGlobalReportValue> dataList = new ArrayList<EtatStockGlobalReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichissementEtatGlobalReport(EtatStockGlobalReportValue report,
			ResultatRechecheEntiteStockStockValue result) {

		Set<EtatStockGlobalElementReportValue> elementsList = report.getElementsList();

		Map<Long, EtatGlobalMapValue> mapByArticle = new HashMap<Long, EtatGlobalMapValue>();

		for (EntiteStockValue entiteStock : result.getEntiteStock()) {

			Double qteActuelleTotal = 0D;
			Double qteReserveeTotal = 0D;
			Long nbRouleauxActuelsTotal = 0L;
			Long nbRouleauxReservesTotal = 0L;

			EtatGlobalMapValue values = new EtatGlobalMapValue();

			if (mapByArticle.containsKey(entiteStock.getArticle())) {

				values = mapByArticle.get(entiteStock.getArticle());

				qteActuelleTotal = values.getQteActuelle();
				qteReserveeTotal = values.getQteReservee();
				nbRouleauxActuelsTotal = values.getNbRouleauxActuels();
				nbRouleauxReservesTotal = values.getNbRouleauxReserves();

				if (entiteStock.getQteActuelle() != null) {
					qteActuelleTotal += entiteStock.getQteActuelle();
					values.setQteActuelle(qteActuelleTotal);
				}

				if (entiteStock.getQteReservee() != null) {
					qteReserveeTotal += entiteStock.getQteReservee();
					values.setQteReservee(qteReserveeTotal);
				}

				if (entiteStock.getRouleauxActuel() != null) {
					nbRouleauxActuelsTotal += entiteStock.getRouleauxActuel();
					values.setNbRouleauxActuels(nbRouleauxActuelsTotal);
				}

				if (entiteStock.getRouleauxReserve() != null) {
					nbRouleauxReservesTotal += entiteStock.getRouleauxReserve();
					values.setNbRouleauxReserves(nbRouleauxReservesTotal);
				}

				mapByArticle.remove(entiteStock.getArticle());
				mapByArticle.put(entiteStock.getArticle(), values);
				
				
			} else {
				
				values.setId(entiteStock.getId());
				
				values.setQteActuelle(0D);
				if (entiteStock.getQteActuelle() != null) {
					values.setQteActuelle(entiteStock.getQteActuelle());
				}

				values.setQteReservee(0D);
				if (entiteStock.getQteReservee() != null) {
					values.setQteReservee(entiteStock.getQteReservee());
				}

				values.setNbRouleauxActuels(0L);
				if (entiteStock.getRouleauxActuel() != null) {
					values.setNbRouleauxActuels(entiteStock.getRouleauxActuel());
				}

				values.setNbRouleauxReserves(0L);
				if (entiteStock.getRouleauxReserve() != null) {
					values.setNbRouleauxReserves(entiteStock.getRouleauxReserve());
				}
				
				if (entiteStock.getArticle() != null) {

					values.setFamilleArticle(entiteStock.getFamilleArticle());
					values.setLibelleArticle(entiteStock.getLibelleArticle());
					values.setReferenceArticle(entiteStock.getReferenceArticle());
					values.setPrixTotal(entiteStock.getPrixTotal());
					values.setPrixUnitaire(entiteStock.getPrixUnitaire());

					mapByArticle.put(entiteStock.getArticle(), values);
					
				}
			}
		}

		Iterator it = mapByArticle.entrySet().iterator();

		while (it.hasNext()) {

			Map.Entry<Long, Map<Double, Long>> pair = (Map.Entry<Long, Map<Double, Long>>) it.next();

			Long currentArticle = pair.getKey();

			ArticleValue article = articlePersistance.getArticleParId(currentArticle);

			EtatStockGlobalElementReportValue elementReport = new EtatStockGlobalElementReportValue();

			EtatGlobalMapValue values = mapByArticle.get(currentArticle);
			elementReport.setId(values.getId());
			elementReport.setQteActuelle(values.getQteActuelle());
			elementReport.setQteReservee(values.getQteReservee());
			elementReport.setRouleauActuel(values.getNbRouleauxActuels());
			elementReport.setRouleauReserve(values.getNbRouleauxReserves());

			elementReport.setFamilleArticle(values.getFamilleArticle());
			elementReport.setLibelleArticle(values.getLibelleArticle());
			elementReport.setPrixTotal(values.getPrixTotal());
			elementReport.setPrixUnitaire(values.getPrixUnitaire());
			elementReport.setReferenceArticle(values.getReferenceArticle());

			elementReport.setCouleur(article.getCouleur());
			elementReport.setCodeFournisseur(article.getCodeFournisseur());
			elementReport.setProducteur(article.getProducteur());
			elementReport.setSousFamilleArticle(article.getSousFamilleArtEntiteDesignation());

			elementsList.add(elementReport);
		}

		report.setElementsList(elementsList);

	}

	@Override
	public EtatStockDetailleReportValue getEtatDetailleReport(RechercheMulticritereEntiteStockValue critere,
			String typeRapport) throws IOException {

		logger.info("Get Etat Stock Detaille Report");

		EtatStockDetailleReportValue report = new EtatStockDetailleReportValue();

		// enrechissement des param du report
		report.setFileName(REPORT_NAME_ETATSTOCK);
		report.setReportStream(new FileInputStream(IConstante.RAPPORTS_GS_BASE_URL + "EtatStock/etat_stock_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		
		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);

		if (critere != null) {
			if (critere.getTypeArticle() != null) {

				switch (critere.getTypeArticle()) {
				case ARTICLE_TYPE_1:
					params.put("SUBREPORT_STOCKHISTORYE_PATH",
							IConstante.RAPPORTS_GS_BASE_URL + "EtatStock/Detaille/etat_stock_fourniture_sub_report.jasper");
					break;

				case ARTICLE_TYPE_2:
					params.put("SUBREPORT_STOCKHISTORYE_PATH",
							IConstante.RAPPORTS_GS_BASE_URL + "EtatStock/Detaille/etat_stock_tissu_sub_report.jasper");
					break;

				}

			}
		}

		report.setParams(params);
		report.setType(critere.getTypeArticle());
		report.setTypeRapport("Détaillé");

		ResultatRechecheEntiteStockStockValue result = entiteStockPersistance
				.rechercherEntiteStockMultiCritere(critere);

		if (result != null) {
			enrichissementEtatDetailleReport(report, result);
		}

		ArrayList<EtatStockDetailleReportValue> dataList = new ArrayList<EtatStockDetailleReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichissementEtatDetailleReport(EtatStockDetailleReportValue report,
			ResultatRechecheEntiteStockStockValue result) {

		Set<EtatStockDetailleElementReportValue> elementsList = report.getElementsList();

		for (EntiteStockValue entiteStock : result.getEntiteStock()) {

			ArticleValue article = articlePersistance.getArticleParId(entiteStock.getArticle());

			EtatStockDetailleElementReportValue elementReport = new EtatStockDetailleElementReportValue();

			elementReport.setId(entiteStock.getId());
			elementReport.setDesignationMagasin(entiteStock.getDesignationMagasin());
			elementReport.setEmplacement(entiteStock.getEmplacement());
			elementReport.setFamilleArticle(entiteStock.getFamilleArticle());
			elementReport.setLibelleArticle(entiteStock.getLibelleArticle());
			elementReport.setPmp(entiteStock.getPmp());
			elementReport.setPrixTotal(entiteStock.getPrixTotal());
			elementReport.setPrixUnitaire(entiteStock.getPrixUnitaire());
			elementReport.setQteActuelle(entiteStock.getQteActuelle());
			elementReport.setReferenceArticle(entiteStock.getReferenceArticle());
			elementReport.setQteReservee(entiteStock.getQteReservee());
			elementReport.setRouleauActuel(entiteStock.getRouleauxActuel());
			elementReport.setRouleauReserve(entiteStock.getRouleauxReserve());
			elementReport.setRefLot(entiteStock.getReferenceLot());
			elementReport.setDateEntree(entiteStock.getDateEntree());
			elementReport.setCouleur(article.getCouleur());
			elementReport.setCodeFournisseur(article.getCodeFournisseur());
			elementReport.setProducteur(article.getProducteur());
			elementReport.setSousFamilleArticle(article.getSousFamilleArtEntiteDesignation());
			elementReport.setOA(entiteStock.getOa());
			
			elementsList.add(elementReport);
		}

		report.setElementsList(elementsList);

	}

}
