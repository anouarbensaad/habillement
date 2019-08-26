package com.gpro.consulting.tiers.gpao.domaine.report.impl;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;



import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.persistance.ICouleurPersistance;
import com.gpro.consulting.tiers.commun.persistance.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.ISousFamilleProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.ITaillePersistance;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.IBaseInfoPersistance;
import com.gpro.consulting.tiers.gpao.coordination.IConstante;
import com.gpro.consulting.tiers.gpao.coordination.IConstanteReport;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.RechercheMulticritereABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.ResultatRechecheABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.coordination.aleas.AleasValue;
import com.gpro.consulting.tiers.gpao.coordination.aleas.ElementAleasValue;
import com.gpro.consulting.tiers.gpao.coordination.aleas.RechercheMulticritereAleaValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.DetailSaisieElementValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.RechercheMulticritereDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.ResultatRechecheDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticritereFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticriterePersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechecheFeuilleSaisieElementValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechecheFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechechePersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.FicheColisReportValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.FicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichedepartage.report.value.FicheDepartageElementReportValue;
import com.gpro.consulting.tiers.gpao.coordination.fichedepartage.report.value.FicheDepartageReportValue;
import com.gpro.consulting.tiers.gpao.coordination.fichedepartage.report.value.TableHeader;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.FicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.PaquetValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.RechercheMulticritereFicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.ResultatRechecheFicheEclatementElementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.ResultatRechecheFicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.fichepaquets.report.value.FichePaquetsReportValue;
import com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.report.value.FicheSuiveuseElementReportValue;
import com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.report.value.FicheSuiveuseReportValue;
import com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.report.value.PaquetReportValue;
import com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.vue.FicheSuiveuseVue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ElementGammeValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.GammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.RechercheMulticritereGammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitElementValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ElementGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.GammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.RechercheMulticritereGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ResultatRechecheGammeOfElementValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ResultatRechecheGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.machine.value.MachineValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.RechercheMulticritereCatalogueValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.ResultatRechecheCatalogueValue;
import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.RecapProductionElementReportValue;
import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.RecapProductionReportValue;
import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.RecapProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.RechercheMulticritereRecapProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.ResultatRechecheRecapProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.report.ABCArticleDetailEtapeJourElementValue;
import com.gpro.consulting.tiers.gpao.coordination.report.ABCArticleDetailEtapeJourReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.AvancementOFReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.FicheEclatementReportListValue;
import com.gpro.consulting.tiers.gpao.coordination.report.FicheSaisieReporListValue;
import com.gpro.consulting.tiers.gpao.coordination.report.FicheSaisieReportSetValue;
import com.gpro.consulting.tiers.gpao.coordination.report.GammeOFElementReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.GammeOFReportListValue;
import com.gpro.consulting.tiers.gpao.coordination.report.GammeOFReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.GammeProduitElementReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.GammeProduitReportListValue;
import com.gpro.consulting.tiers.gpao.coordination.report.GammeProduitReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.OperationReportListValue;
import com.gpro.consulting.tiers.gpao.coordination.report.OrdreFabricationReportListValue;
import com.gpro.consulting.tiers.gpao.coordination.report.ReservationProduitElementReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.ReservationProduitReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.ResultatRechecheFeuilleSaisieByMatriculeElementValue;
import com.gpro.consulting.tiers.gpao.coordination.report.ResultatRechecheFicheSaisieElementValue;
import com.gpro.consulting.tiers.gpao.coordination.report.charts.parClient.RepartitionQteParClientElementValue;
import com.gpro.consulting.tiers.gpao.coordination.report.charts.parClient.RepartitionQteParClientReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.production.global.value.ProductionChaineReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.production.global.value.ProductionGlobalElementChaineReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.production.global.value.ProductionGlobalElementReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.production.global.value.ProductionGlobalReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.production.global.value.ProductionReportRequestValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.AleaReportElementValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.AleaReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.production.ProductionElementReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.production.ProductionReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.comptage.ChaineComptageElementReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.comptage.ChaineComptageReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.globalOF.ChaineGlobalOFElementReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.globalOF.ChaineGlobalOFReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.globalOperation.ChaineGlobalOperationElementReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.globalOperation.ChaineGlobalOperationReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.jourOF.ChaineJourOFElementReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.jourOF.ChaineJourOFReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.recapProduction.RecapProductionChaineReportElementValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.recapProduction.RecapProductionChaineReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportOperateur.RechercheMulticritereOperateurReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportOperateur.competence.OperateurCompetenceElementReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportOperateur.competence.OperateurCompetenceReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportOperateur.historique.OperateurHistoriqueElementReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportOperateur.historique.OperateurHistoriqueReportValue;
import com.gpro.consulting.tiers.gpao.coordination.reservationproduit.value.ReservationProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.section.value.SectionValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ChaineValue;
import com.gpro.consulting.tiers.gpao.coordination.value.DetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.StatutOfValue;
import com.gpro.consulting.tiers.gpao.domaine.IDetailSaisieDomaine;
import com.gpro.consulting.tiers.gpao.domaine.IFicheSuiveuseDomaine;
import com.gpro.consulting.tiers.gpao.domaine.IRecapProductionDomaine;
import com.gpro.consulting.tiers.gpao.domaine.report.IGestionnaireReportGpaoDomaine;
import com.gpro.consulting.tiers.gpao.domaine.report.utilities.ResultatRechercheFicheSaisieElementComparator;
import com.gpro.consulting.tiers.gpao.persitance.IChainePersistance;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.IStatutPersistance;
import com.gpro.consulting.tiers.gpao.persitance.abc.IABCArticleDetailEtapeJourPersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IAleasPersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IDetailSaisiePersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IElementAleasPersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IFeuilleSaisiePersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IPersonnelPersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.ISaisieElementPersistance;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.IFicheColisagePersistance;
import com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.IFicheEclatementPersistance;
import com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.IPaquetPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IGammeProduitPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IMachinePersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IOperationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.ISectionPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.IElementGammeOfPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.IGammeOfPersistance;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Implementation of {@link IGestionnaireReportGpaoDomaine}
 * 
 * @author Wahid Gazzah
 * @since 03/05/2016
 *
 */

@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class GestionnaireReportGpaoDomaineImpl implements IGestionnaireReportGpaoDomaine {

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportGpaoDomaineImpl.class);

	@Autowired
	private IOrdreFabricationPersistance ordreFabricationPersistance;

	@Autowired
	private IGammeProduitPersistance gammeProduitPersistance;

	@Autowired
	private IGammeOfPersistance gammeOfPersistance;

	@Autowired
	private IFicheSuiveuseDomaine ficheSuiveuseDomaine;

	@Autowired
	private IFicheEclatementPersistance ficheEclatementPersistance;

	@Autowired
	private ICouleurPersistance couleurPersistance;

	@Autowired
	private ITaillePersistance taillePersistance;

	@Autowired
	private IProduitPersistance produitPersistance;

	@Autowired
	private IPartieInteresseePersistance partieInteresseePersistance;

	@Autowired
	private IOperationPersistance operationPersistance;

	@Autowired
	private IMachinePersistance machinePersistance;
	
	@Autowired
	private ISectionPersistance sectionPersistance;

	@Autowired
	private IABCArticleDetailEtapeJourPersistance aBCArticleDetailEtapeJourPersistance;

	@Autowired
	private IFeuilleSaisiePersistance feuilleSaisiePersistance;

	@Autowired
	private IRecapProductionDomaine recapProductionDomaine;

	@Autowired
	private IPersonnelPersistance personnelPersistance;

	@Autowired
	private IChainePersistance chainePersistance;
	
	@Autowired
	private IAleasPersistance aleaPersistance;
	
	@Autowired
	private IElementAleasPersistance elementAleaPersistance;

	@Autowired
	private IDetailSaisiePersistance detailSaisiePersistance;

	@Autowired
	private IElementGammeOfPersistance elementGammeOfPersistance;

	@Autowired
	private ISaisieElementPersistance saisieElementPersistance;

	@Autowired
	private IDetailSaisieDomaine detailSaisieDomaine;

	@Autowired
	private IPaquetPersistance packetPersistance;
	
	@Autowired
	private IBaseInfoPersistance baseInfoPersistance;


	@Autowired
	private ISousFamilleProduitPersistance sousFamilleProduitPersistance;

	@Autowired
	private IStatutPersistance statutPersistence;
	
	
	@Autowired
	private IFicheColisagePersistance ficheColisagePersistance;
	

	private String CHAINE_COUPE = "Coupe";
	private String CHAINE_1 = "Chaine 1";
	private String CHAINE_2 = "Chaine 2";
	private String CHAINE_3 = "Chaine 3";
	private String CHAINE_4 = "Chaine 4";
	private String CHAINE_5 = "Chaine 5";
	private String CHAINE_6 = "Chaine 6";

	@Override
	public OrdreFabricationReportListValue getListOrdreFabrication(RechercheMulticritereOrdreFabricationValue request)
			throws IOException {

		OrdreFabricationReportListValue report = new OrdreFabricationReportListValue();

		// enrechissement des param du report
		report.setFileName(IConstanteReport.REPORT_NAME_ORDRE_FABRICATION_LIST);
		report.setReportStream(new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "OrdreFabrication/ordre_fabrication_list_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_ORDRE_FABRICATION__LIST_PATH",
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+"OrdreFabrication/ordre_fabrication_list_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);

		report.setParams(params);

		ResultatMulticritereOrdreFabricationValue resultRecherche = ordreFabricationPersistance
				.rechercherOrdreFabricationMultiCritere(request);

		if (resultRecherche.getOrdreFabricationValues() != null) {
			Set<OrdreFabricationValue> ordreFabricationList = new TreeSet<OrdreFabricationValue>(
					resultRecherche.getOrdreFabricationValues());

			// enrichissement du rapport
			enrichmentOrdreFabricationReportListReport(report, ordreFabricationList, request);
		}

		ArrayList<OrdreFabricationReportListValue> dataList = new ArrayList<OrdreFabricationReportListValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichmentOrdreFabricationReportListReport(OrdreFabricationReportListValue report,
			Set<OrdreFabricationValue> resultRecherche, RechercheMulticritereOrdreFabricationValue request) {

		report.setNumero(request.getvNumero());
		report.setCompositionBC(request.getvCompositionBC());
		report.setCompositionClient(request.getvCompositionClient());
		report.setEtat(request.getvEtat());
		report.setDateIntroductionDu(request.getvDateIntroductionDu());
		report.setDateIntroductionAu(request.getvDateIntroductionAu());
		report.setClientId(request.getClientId());
		report.setProduitId(request.getProduitId());
		report.setDateLivraisonDu(request.getDateLivraisonDu());
		report.setDateLivraisonTo(request.getDateLivraisonTo());

		for (OrdreFabricationValue ordreFabrication : resultRecherche) {

			report.getOrdreFabricationList().add(ordreFabrication);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GammeProduitReportListValue getListGammeProduit(RechercheMulticritereGammeProduitValue request)
			throws IOException {

		GammeProduitReportListValue report = new GammeProduitReportListValue();

		// enrechissement des param du report
		report.setFileName(IConstanteReport.REPORT_NAME_GAMME_PRODUIT_LIST);
		report.setReportStream(new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+"/GammeProduit/gamme_produit_list_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_GAMME_PRODUIT_LIST_PATH",
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+"/GammeProduit/gamme_produit_list_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		ResultatRechecheGammeProduitValue resultRecherche = gammeProduitPersistance.rechercherMultiCritere(request);
		if (resultRecherche != null) {
			Set<ResultatRechecheGammeProduitElementValue> gammeProduitsList = new TreeSet<ResultatRechecheGammeProduitElementValue>(
					resultRecherche.getList());

			// enrichissement du rapport
			enrichirGammeProduitReportList(report, gammeProduitsList, request);
		}
		ArrayList<GammeProduitReportListValue> dataList = new ArrayList<GammeProduitReportListValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichirGammeProduitReportList(GammeProduitReportListValue pGammeProduitsReportList,
			Set<ResultatRechecheGammeProduitElementValue> gammeProduitsList,
			RechercheMulticritereGammeProduitValue request) {

		pGammeProduitsReportList.setMachineId(request.getMachineId());
		pGammeProduitsReportList.setProduitId(request.getProduitId());
		pGammeProduitsReportList.setSectionId(request.getSectionId());
		pGammeProduitsReportList.setTempsTotalMin(request.getTempsTotalMin());
		pGammeProduitsReportList.setTempsTotalMax(request.getTempsTotalMax());

		List<ResultatRechecheGammeProduitElementValue> listeElementGammeProduit = new ArrayList<ResultatRechecheGammeProduitElementValue>();
		for (ResultatRechecheGammeProduitElementValue gammeProduitValue : gammeProduitsList) {

			ResultatRechecheGammeProduitElementValue vGammeProduitReportElementValue = new ResultatRechecheGammeProduitElementValue();
			vGammeProduitReportElementValue.setId(gammeProduitValue.getId());
			vGammeProduitReportElementValue.setNbOperation(gammeProduitValue.getNbOperation());
			vGammeProduitReportElementValue.setDate(gammeProduitValue.getDate());
			vGammeProduitReportElementValue.setObservations(gammeProduitValue.getObservations());
			// à partir de produitId on recupère sa reference & designation
			vGammeProduitReportElementValue.setProduitId(gammeProduitValue.getProduitId());
			vGammeProduitReportElementValue.setTempsTotal(gammeProduitValue.getTempsTotal());

			listeElementGammeProduit.add(vGammeProduitReportElementValue);
		}

		Collections.sort(listeElementGammeProduit);
		pGammeProduitsReportList.setGammeProduitList(new TreeSet<>(listeElementGammeProduit));

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GammeProduitReportValue getGammeProduitReportValue(Long id) throws IOException {
		GammeProduitReportValue report = new GammeProduitReportValue();

		// enrechissement des param du report
		report.setFileName(IConstanteReport.REPORT_NAME_GAMMEPRODUIT);
		report.setReportStream(
				new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "GammeProduit/gammeProduitById/gamme_produit_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_GAMME_PATH",
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "GammeProduit/gammeProduitById/gamme_produit_Operation_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		GammeProduitValue gammeProduitValue = gammeProduitPersistance.getById(id);

		// enrichissement du rapport
		if (gammeProduitValue != null) {
			enrichmentGammeProduitReport(report, gammeProduitValue);
		}
		List<GammeProduitReportValue> dataList = new ArrayList<GammeProduitReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichmentGammeProduitReport(GammeProduitReportValue report, GammeProduitValue gammeProduitValue) {

		report.setNbOperation(gammeProduitValue.getNbOperation());
		report.setObservations(gammeProduitValue.getObservations());
		report.setProduitId(gammeProduitValue.getProduitId());
		report.setTempsTotal(gammeProduitValue.getTempsTotal());

		List<GammeProduitElementReportValue> listElementGamme = new ArrayList<GammeProduitElementReportValue>();

		for (ElementGammeValue gameProduit : gammeProduitValue.getListElementGamme()) {

			GammeProduitElementReportValue element = new GammeProduitElementReportValue();
			element.setComptage(gameProduit.getComptage());
			element.setGammeProduitId(gameProduit.getGammeProduitId());
			element.setMachineId(gameProduit.getMachineId());
			element.setObservations(gameProduit.getObservations());
			element.setOperationId(gameProduit.getOperationId());
			element.setTemps(gameProduit.getTemps());
			element.setPdh(gameProduit.getPdh());
			element.setOrdre(gameProduit.getOrdre());

			listElementGamme.add(element);
		}

		Collections.sort(listElementGamme);

		report.setListGammeProduit(new TreeSet<>(listElementGamme));

	}

	@Override
	public GammeOFReportValue getGammeOFReportValue(Long id) throws IOException {

		GammeOFReportValue report = new GammeOFReportValue();

		// enrechissement des param du report
		report.setFileName(IConstanteReport.REPORT_NAME_GAMME_OF);
		report.setReportStream(new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "GammeOF/gammeOFById/gamme_OF_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_GAMME_OF_PATH", IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "GammeOF/gammeOFById/gamme_OF_Operation_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		GammeOfValue gammeOFValue = gammeOfPersistance.getById(id);

		// enrichissement du rapport
		enrichmentGammeOFReport(report, gammeOFValue);

		ArrayList<GammeOFReportValue> dataList = new ArrayList<GammeOFReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichmentGammeOFReport(GammeOFReportValue report, GammeOfValue gammeOFValue) {

		report.setNbOperation(gammeOFValue.getNbOperation());
		report.setOfId(gammeOFValue.getOrdreFabricationId());
		report.setNbOperationProduit(gammeOFValue.getNbOperationProduit());
		report.setObservations(gammeOFValue.getObservations());
		report.setProduitId(gammeOFValue.getProduitId());
		report.setTempsTotal(gammeOFValue.getTempsTotal());
		report.setTempsTotalProduit(gammeOFValue.getTempsTotalProduit());
		List<GammeOFElementReportValue> listElementGamme = new ArrayList<GammeOFElementReportValue>();

		for (ElementGammeOfValue gameProduit : gammeOFValue.getListElementGammeOf()) {

			GammeOFElementReportValue element = new GammeOFElementReportValue();
			element.setComptage(gameProduit.getComptage());
			element.setGammeOFId(gameProduit.getGammeOfId());
			element.setMachineId(gameProduit.getMachineId());
			element.setObservations(gameProduit.getObservations());
			element.setOperationId(gameProduit.getOperationId());
			element.setTemps(gameProduit.getTemps());
			element.setPdh(gameProduit.getPdh());
			element.setOrdre(gameProduit.getOrdre());
			listElementGamme.add(element);
		}

		Collections.sort(listElementGamme);

		report.setListGammeProduit(new TreeSet<>(listElementGamme));

	}

	@Override
	public ReservationProduitReportValue getReservationByOrdreFabricationId(Long ordreFabricationId)
			throws IOException {

		ReservationProduitReportValue report = new ReservationProduitReportValue();
		List<ReservationProduitValue> listReservationProduit = new ArrayList<ReservationProduitValue>();

		report.setFileName(IConstanteReport.REPORT_NAME_RESERVATIONPRODUIT);
		report.setReportStream(new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "ReservationProduit/reservation_produit_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		// enrichissement du report
		enrichmentReservationProduitReport(listReservationProduit, report);

		ArrayList<ReservationProduitReportValue> dataList = new ArrayList<ReservationProduitReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichmentReservationProduitReport(List<ReservationProduitValue> listReservationProduit,
			ReservationProduitReportValue report) {

		List<ReservationProduitElementReportValue> listBesoinArticleElements = new ArrayList<ReservationProduitElementReportValue>();

		for (ReservationProduitValue besoinArticle : listReservationProduit) {

			ReservationProduitElementReportValue element = new ReservationProduitElementReportValue();

			element.setBesoin(besoinArticle.getBesoin());
			element.setType(besoinArticle.getType());

			listBesoinArticleElements.add(element);
		}

		report.setListReservationProduitElements(listBesoinArticleElements);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GammeOFReportListValue getListGammeOF(RechercheMulticritereGammeOfValue request) throws IOException {

		GammeOFReportListValue report = new GammeOFReportListValue();

		// enrechissement des param du report
		report.setFileName(IConstanteReport.REPORT_NAME_GAMME_OF_LIST);
		report.setReportStream(new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "GammeOF/gamme_OF_list_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_GAMME_OF_LIST_PATH", IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "GammeOF/gamme_OF_list_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		ResultatRechecheGammeOfValue resultRecherche = gammeOfPersistance.rechercherMultiCritere(request);
		if (resultRecherche.getList() != null) {
			Set<ResultatRechecheGammeOfElementValue> gammeOFList = new TreeSet<ResultatRechecheGammeOfElementValue>(
					resultRecherche.getList());
			// enrichissement du rapport
			enrichirGammeOFReportList(report, gammeOFList, request);
		}
		List<GammeOFReportListValue> dataList = new ArrayList<GammeOFReportListValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichirGammeOFReportList(GammeOFReportListValue pGammeOFReportList,
			Set<ResultatRechecheGammeOfElementValue> gammeOFList, RechercheMulticritereGammeOfValue request) {

		pGammeOFReportList.setMachineId(request.getMachineId());
		pGammeOFReportList.setProduitId(request.getProduitId());
		pGammeOFReportList.setSectionId(request.getSectionId());
		pGammeOFReportList.setTempsTotalMin(request.getTempsTotalMin());
		pGammeOFReportList.setTempsTotalMax(request.getTempsTotalMax());

		List<ResultatRechecheGammeOfElementValue> listeElementGammeOF = new ArrayList<ResultatRechecheGammeOfElementValue>();
		for (ResultatRechecheGammeOfElementValue gammeOFValue : gammeOFList) {

			ResultatRechecheGammeOfElementValue vGammeOFReportElementValue = new ResultatRechecheGammeOfElementValue();
			vGammeOFReportElementValue.setId(gammeOFValue.getId());
			vGammeOFReportElementValue.setNbOperation(gammeOFValue.getNbOperation());
			vGammeOFReportElementValue.setDate(gammeOFValue.getDate());
			vGammeOFReportElementValue.setObservations(gammeOFValue.getObservations());
			vGammeOFReportElementValue.setTempsTotal(gammeOFValue.getTempsTotal());
			vGammeOFReportElementValue.setOrdreFabricationId(gammeOFValue.getOrdreFabricationId());
			listeElementGammeOF.add(vGammeOFReportElementValue);
		}

		Collections.sort(listeElementGammeOF);
		pGammeOFReportList.setGammeOFList(new TreeSet<>(listeElementGammeOF));

	}

	@Override
	public FicheSuiveuseReportValue getFicheSuiveuseReport(Long ordreFabricationId, List<Long> paquetsList,
			List<Long> operationsList, boolean inverse) throws IOException {

		FicheSuiveuseReportValue report = new FicheSuiveuseReportValue();

		report.setFileName(IConstanteReport.REPORT_NAME_FICHESUIVEUSE);
		report.setReportStream(new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+"EMILE_FicheSuiveuse/fiche_suiveuse_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		if (!operationsList.isEmpty() && !paquetsList.isEmpty()) {
			
			if(inverse){
				enrechissementFicheSuiveuseInverseReport(report, ordreFabricationId, paquetsList, operationsList);
			}
			else{
				enrechissementFicheSuiveuseReport(report, ordreFabricationId, paquetsList, operationsList);
			}
		}

		ArrayList<FicheSuiveuseReportValue> dataList = new ArrayList<FicheSuiveuseReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	@Override
	public FicheDepartageReportValue getFicheDepartageReport(Long ordreFabricationId) throws IOException {

		FicheDepartageReportValue report = new FicheDepartageReportValue();

		report.setFileName(IConstanteReport.REPORT_NAME_FICHEDEPARTAGE);
		report.setReportStream(new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL +"EMILE_FicheDepartage/fiche_departage_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		enrechissementFicheDepartageReport(report, ordreFabricationId);

		ArrayList<FicheDepartageReportValue> dataList = new ArrayList<FicheDepartageReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	@Override
	public FichePaquetsReportValue getFichePaquetsReport(Long ordreFabricationId) throws IOException {

		FichePaquetsReportValue report = new FichePaquetsReportValue();

		report.setFileName(IConstanteReport.REPORT_NAME_FICHEPAQUETS);
		report.setReportStream(new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "EMILE_FichePaquets/fiche_paquets_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		enrechissementFichePaquetsReport(report, ordreFabricationId);

		ArrayList<FichePaquetsReportValue> dataList = new ArrayList<FichePaquetsReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrechissementFicheDepartageReport(FicheDepartageReportValue report, Long ordreFabricationId) {

		List<FicheEclatementValue> ficheEclatementList = ficheEclatementPersistance
				.getByOrdreFabricationId(ordreFabricationId);

		OrdreFabricationValue ordreFabrication = ordreFabricationPersistance
				.rechercheOrdreFabricationParId(ordreFabricationId);

		if (ordreFabrication != null) {

			report.setNumOrdreFabrication(ordreFabrication.getNumero());
			report.setQuantiteDemandee(ordreFabrication.getQuantite());

			if (ordreFabrication.getProduitId() != null) {

				ProduitValue produit = produitPersistance.rechercheProduitById(ordreFabrication.getProduitId());

				if (produit != null) {

					report.setProduitDesignation(produit.getDesignation());
					report.setProduitReference(produit.getReference());
				}

			}

			if (ordreFabrication.getPartieInterresId() != null) {

				PartieInteresseValue client = partieInteresseePersistance
						.getById(ordreFabrication.getPartieInterresId());

				if (client != null) {

					report.setClientAbreviation(client.getAbreviation());
					report.setClientReference(client.getReference());
				}

			}

		}

		if (ficheEclatementList != null && ficheEclatementList.size() > 0) {

			List<FicheDepartageElementReportValue> paquetsList = new ArrayList<FicheDepartageElementReportValue>();

			FicheEclatementValue ficheEclatement = ficheEclatementList.get(IConstanteReport.FIRST_INDEX);

			List<CouleurValue> listCouleur = couleurPersistance.listeCouleur();
			Map<Long, String> mapCouleurIdDesignation = new HashMap<Long, String>();
			for (CouleurValue couleur : listCouleur) {
				mapCouleurIdDesignation.put(couleur.getId(), couleur.getDesignation());
			}

			List<TailleValue> listTaille = taillePersistance.listeTaille();
			Map<Long, String> mapTailleIdDesignation = new HashMap<Long, String>();
			for (TailleValue taille : listTaille) {
				mapTailleIdDesignation.put(taille.getId(), taille.getDesignation());
			}

			Long quantiteTotal = IConstanteReport.ZERO;

			for (PaquetValue paquetValue : ficheEclatement.getListPaquet()) {

				FicheDepartageElementReportValue element = new FicheDepartageElementReportValue();

				element.setPaquetId(paquetValue.getId());
				element.setNumPaquet(paquetValue.getNum());
				element.setQuantite(paquetValue.getQuantite());

				if (paquetValue.getCouleurId() != null
						&& mapCouleurIdDesignation.containsKey(paquetValue.getCouleurId())) {
					element.setCouleurDesignation(mapCouleurIdDesignation.get(paquetValue.getCouleurId()));
				}

				if (paquetValue.getTailleId() != null
						&& mapTailleIdDesignation.containsKey(paquetValue.getTailleId())) {
					element.setTailleDesignation(mapTailleIdDesignation.get(paquetValue.getTailleId()));
				}

				quantiteTotal = quantiteTotal
						+ ((paquetValue.getQuantite() != null) ? paquetValue.getQuantite() : IConstanteReport.ZERO);

				paquetsList.add(element);
			}

			report.setQuantiteTotal(quantiteTotal);

			Collections.sort(paquetsList);
			report.setPaquetsList(new TreeSet<>(paquetsList));

		}

	}

	private void enrechissementFichePaquetsReport(FichePaquetsReportValue report, Long ordreFabricationId) {

		List<FicheEclatementValue> ficheEclatementList = ficheEclatementPersistance
				.getByOrdreFabricationId(ordreFabricationId);

		OrdreFabricationValue ordreFabrication = ordreFabricationPersistance
				.rechercheOrdreFabricationParId(ordreFabricationId);

		String numOrdreFabrication = null;

		String produitDesignation = null;
		String produitReference = null;
		String clientAbreviation = null;
		String clientReference = null;
        Long  qteOF=0L;
		if (ordreFabrication != null) {

			numOrdreFabrication = ordreFabrication.getNumero();
            qteOF=ordreFabrication.getQuantite();
			report.setNumOrdreFabrication(numOrdreFabrication);
			if (ordreFabrication.getProduitId() != null) {

				ProduitValue produit = produitPersistance.rechercheProduitById(ordreFabrication.getProduitId());
               // SousFamilleProduitValue sousFamille=sousFamilleProduitPersistance.rechercheSousFamilleProduitById(produit.getSousFamilleId());
				if (produit != null) {
					SousFamilleProduitValue sousFamille=sousFamilleProduitPersistance.rechercheSousFamilleProduitById(produit.getSousFamilleId());
					produitDesignation = sousFamille.getDesignation();
					produitReference = produit.getReference();
				}

			}

			if (ordreFabrication.getPartieInterresId() != null) {

				PartieInteresseValue client = partieInteresseePersistance
						.getById(ordreFabrication.getPartieInterresId());

				if (client != null) {
					clientAbreviation = client.getAbreviation();
					clientReference = client.getReference();
				}

			}

		}
        List<PaquetReportValue> listPaquet=new ArrayList<PaquetReportValue>();
		if (ficheEclatementList != null && ficheEclatementList.size() > 0) {

			FicheEclatementValue ficheEclatement = ficheEclatementList.get(IConstanteReport.FIRST_INDEX);

			List<CouleurValue> listCouleur = couleurPersistance.listeCouleur();
			Map<Long, String> mapCouleurIdDesignation = new HashMap<Long, String>();
			for (CouleurValue couleur : listCouleur) {
				mapCouleurIdDesignation.put(couleur.getId(), couleur.getDesignation());
			}

			List<TailleValue> listTaille = taillePersistance.listeTaille();
			Map<Long, String> mapTailleIdDesignation = new HashMap<Long, String>();
			for (TailleValue taille : listTaille) {
				mapTailleIdDesignation.put(taille.getId(), taille.getDesignation());
			}

			for (PaquetValue paquetValue : ficheEclatement.getListPaquet()) {

				PaquetReportValue paquetReportValue = new PaquetReportValue();

				paquetReportValue.setNumPaquet(paquetValue.getNum());
				paquetReportValue.setQuantite(paquetValue.getQuantite());

				if (paquetValue.getCouleurId() != null
						&& mapCouleurIdDesignation.containsKey(paquetValue.getCouleurId())) {
					paquetReportValue.setCouleurDesignation(mapCouleurIdDesignation.get(paquetValue.getCouleurId()));
				}

				if (paquetValue.getTailleId() != null
						&& mapTailleIdDesignation.containsKey(paquetValue.getTailleId())) {
					paquetReportValue.setTailleDesignation(mapTailleIdDesignation.get(paquetValue.getTailleId()));
				}

				paquetReportValue.setNumOrdreFabrication(numOrdreFabrication);

				paquetReportValue.setProduitDesignation(produitDesignation);
				paquetReportValue.setProduitReference(produitReference);
				paquetReportValue.setClientAbreviation(clientAbreviation);
				paquetReportValue.setClientReference(clientReference);
                paquetReportValue.setQuantiteOF(qteOF);
				listPaquet.add(paquetReportValue);
			}
                Collections.sort(listPaquet);
                report.setPaquetsList(listPaquet);
		}

	}

	private void enrechissementFicheSuiveuseReport(FicheSuiveuseReportValue report, Long ordreFabricationId,
			List<Long> paquetsList, List<Long> operationsList) {

		FicheSuiveuseVue value = ficheSuiveuseDomaine.getByOrdreFabricationId(ordreFabricationId);

		if (value != null) {

			String numOrdreFabrication = null;
			Long idOrdreFabrication = null;

			String produitDesignation = null;
			String produitReference = null;
			String clientAbreviation = null;
			String clientReference = null;

			OrdreFabricationValue ordreFabrication = ordreFabricationPersistance
					.rechercheOrdreFabricationParId(ordreFabricationId);

			if (ordreFabrication != null) {

				numOrdreFabrication = ordreFabrication.getNumero();
				idOrdreFabrication = ordreFabrication.getId();

				if (ordreFabrication.getProduitId() != null) {

					ProduitValue produit = produitPersistance.rechercheProduitById(ordreFabrication.getProduitId());

					if (produit != null) {
						produitDesignation = produit.getDesignation();
						produitReference = produit.getReference();
					}

				}

				if (ordreFabrication.getPartieInterresId() != null) {

					PartieInteresseValue client = partieInteresseePersistance
							.getById(ordreFabrication.getPartieInterresId());

					if (client != null) {
						clientAbreviation = client.getAbreviation();
						clientReference = client.getReference();
					}

				}

			}

			List<CouleurValue> listCouleur = couleurPersistance.listeCouleur();
			Map<Long, String> mapCouleurIdDesignation = new HashMap<Long, String>();
			for (CouleurValue couleur : listCouleur) {
				mapCouleurIdDesignation.put(couleur.getId(), couleur.getDesignation());
			}

			List<TailleValue> listTaille = taillePersistance.listeTaille();
			Map<Long, String> mapTailleIdDesignation = new HashMap<Long, String>();
			for (TailleValue taille : listTaille) {
				mapTailleIdDesignation.put(taille.getId(), taille.getDesignation());
			}

			List<FicheSuiveuseElementReportValue> elementsList = new ArrayList<FicheSuiveuseElementReportValue>();

			List<FicheSuiveuseElementReportValue> leftElementsList = new ArrayList<FicheSuiveuseElementReportValue>();
			List<FicheSuiveuseElementReportValue> rightElementsList = new ArrayList<FicheSuiveuseElementReportValue>();

			Long quantiteTotal = IConstanteReport.ZERO;

			for (PaquetValue paquet : value.getPaquetsList()) {

				if (paquetsList.contains(paquet.getId())) {

					FicheSuiveuseElementReportValue headerElement = new FicheSuiveuseElementReportValue();

					String tempBareCode = createBareCodePattern(idOrdreFabrication, paquet.getOrdre(), 0L);

					headerElement.setBareCode(tempBareCode);
					headerElement.setBareCodeForCompare(tempBareCode);
					headerElement.setProduitDesignation(produitDesignation);
					headerElement.setProduitReference(produitReference);
					headerElement.setClientAbreviation(clientAbreviation);
					headerElement.setClientReference(clientReference);

					headerElement.setNumPaquet(paquet.getNum());
					headerElement.setNumOrdreFabrication(numOrdreFabrication);
					headerElement.setQuantite(paquet.getQuantite());

					quantiteTotal = quantiteTotal + paquet.getQuantite();

					if (paquet.getCouleurId() != null && mapCouleurIdDesignation.containsKey(paquet.getCouleurId())) {
						headerElement.setCouleurDesignation(mapCouleurIdDesignation.get(paquet.getCouleurId()));
					}

					if (paquet.getTailleId() != null && mapTailleIdDesignation.containsKey(paquet.getTailleId())) {
						headerElement.setTailleDesignation(mapTailleIdDesignation.get(paquet.getTailleId()));
					}

					headerElement.setHeaderElement(true);
					elementsList.add(headerElement);

					for (OperationValue operation : value.getOperationsList()) {

						
						FicheSuiveuseElementReportValue bodyElement = new FicheSuiveuseElementReportValue();


						if (operationsList.contains(operation.getId())) {
							String bareCode = createBareCodePattern(idOrdreFabrication, paquet.getOrdre(),
									operation.getOrdreElementGammeOf());
							
							bodyElement.setBareCodeForCompare(bareCode);

							bodyElement.setProduitDesignation(produitDesignation);
							bodyElement.setProduitReference(produitReference);
							bodyElement.setClientAbreviation(clientAbreviation);
							bodyElement.setClientReference(clientReference);
							bodyElement.setTailleDesignation(headerElement.getTailleDesignation());
							bodyElement.setCouleurDesignation(headerElement.getCouleurDesignation());

							bodyElement.setNumOrdreFabrication(numOrdreFabrication);
							bodyElement.setQuantitePaquets(headerElement.getQuantite());
							bodyElement.setTemps(operation.getTemps());
							bodyElement.setNumPaquet(paquet.getNum());
                            bodyElement.setPdh(operation.getPdh());
							bodyElement.setNomElementGamme(operation.getDesignation());

							bodyElement.setBareCode(bareCode);

							bodyElement.setOrdreElementGammeOf(operation.getOrdreElementGammeOf());

							bodyElement.setHeaderElement(false);
							elementsList.add(bodyElement);

						}
					}
				}
			}


			Collections.sort(elementsList);
			report.setElementsList(new TreeSet<>(elementsList));

			String bareCode = "";

			if (operationsList.size() % 2 == 0) {

				for (int i = 0; i < elementsList.size(); i++) {

					elementsList.get(i).setEmptyElement(false);

					leftElementsList.add(elementsList.get(i));

					if (elementsList.get(i).getHeaderElement()) {

						rightElementsList.add(elementsList.get(i));
					} else {

						
						i = i + 1;

						if(i < elementsList.size()){
							rightElementsList.add(elementsList.get(i));
						}
					}
				}

			} else {

				boolean lastElementForTheCurrentPaquet = false;
				int nbrOperations = operationsList.size();
				int indexLastElement = nbrOperations + 1;

				for (int i = 1; i <= elementsList.size(); i++) {

					bareCode = elementsList.get(i - 1).getBareCode();

					if (i == indexLastElement) {

						indexLastElement = indexLastElement + nbrOperations + 1;

						lastElementForTheCurrentPaquet = true;
					} else {

						lastElementForTheCurrentPaquet = false;
					}

					leftElementsList.add(elementsList.get(i - 1));

					if (lastElementForTheCurrentPaquet) {

						FicheSuiveuseElementReportValue element = new FicheSuiveuseElementReportValue();

						element.setEmptyElement(true);
						element.setHeaderElement(false);
						element.setBareCode(bareCode + "1");
						element.setBareCodeForCompare(element.getBareCode());
						rightElementsList.add(element);
					} else {

						if (elementsList.get(i - 1).getHeaderElement()) {

							rightElementsList.add(elementsList.get(i - 1));
						} else {

							i = i + 1;

							if (i <= elementsList.size()) {

								rightElementsList.add(elementsList.get(i - 1));
							}

						}

					}

				}
			}

			Collections.sort(leftElementsList);
			Collections.sort(rightElementsList);

			report.setLeftElementsList(new TreeSet<>(leftElementsList));
			report.setRightElementsList(new TreeSet<>(rightElementsList));

		}

	}

	private String createBareCodePattern(Long idOrdreFabrication, Long ordre, Long ordreElementGammeOf) {

		String bareCode = "";
		String element1 = "";
		String element2 = "";
		String element3 = "";

		if (idOrdreFabrication != null) {

			element1 = Long.toString(idOrdreFabrication);
		}

		if (ordre != null) {

			element2 = Long.toString(ordre);
		}

		if (ordreElementGammeOf != null) {

			element3 = Long.toString(ordreElementGammeOf);
		}

		int nbrZEROToAdd = 0;
		String ZEROToAdd = "";

		// element1
		nbrZEROToAdd = 4 - element1.length();

		if (nbrZEROToAdd > 0) {

			for (int i = 0; i < nbrZEROToAdd; i++)
				ZEROToAdd = ZEROToAdd + "0";

			element1 = ZEROToAdd + element1;
		}

		// element2
		ZEROToAdd = "";
		nbrZEROToAdd = 3 - element2.length();

		if (nbrZEROToAdd > 0) {

			for (int i = 0; i < nbrZEROToAdd; i++)
				ZEROToAdd = ZEROToAdd + "0";

			element2 = ZEROToAdd + element2;
		}

		// element3
		ZEROToAdd = "";
		nbrZEROToAdd = 3 - element3.length();

		if (nbrZEROToAdd > 0) {

			for (int i = 0; i < nbrZEROToAdd; i++)
				ZEROToAdd = ZEROToAdd + "0";

			element3 = ZEROToAdd + element3;
		}

		// bareCode pattern : idOF + ordrePaquet + ordreEGOF
		bareCode = element1 + element2 + element3;

		return bareCode;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FicheEclatementReportListValue getListFicheEclatementReport(
			RechercheMulticritereFicheEclatementValue request) throws IOException {
		FicheEclatementReportListValue report = new FicheEclatementReportListValue();

		// enrechissement des param du report
		report.setFileName(IConstanteReport.REPORT_NAME_ECLATEMENT_LIST);
		report.setReportStream(
				new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Eclatement/EclatementList/eclatement_List_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_ECLATEMENT_LIST_PATH",
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Eclatement/EclatementList/eclatement_sub_List_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		ResultatRechecheFicheEclatementValue resultRecherche = ficheEclatementPersistance
				.rechercherMultiCritere(request);
		Set<ResultatRechecheFicheEclatementElementValue> list = new TreeSet<ResultatRechecheFicheEclatementElementValue>(
				resultRecherche.getList());

		// enrichissement du rapport
		enrichirFicheEclatementReportList(report, list, request);

		ArrayList<FicheEclatementReportListValue> dataList = new ArrayList<FicheEclatementReportListValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichirFicheEclatementReportList(FicheEclatementReportListValue report,
			Set<ResultatRechecheFicheEclatementElementValue> eclatementList,
			RechercheMulticritereFicheEclatementValue request) {

		report.setProduitId(request.getProduitId());
		report.setOrdreFabricationId(request.getOrdreFabricationId());

		List<ResultatRechecheFicheEclatementElementValue> listeElementGammeOF = new ArrayList<ResultatRechecheFicheEclatementElementValue>();

		for (ResultatRechecheFicheEclatementElementValue elementEclatement : eclatementList) {

			ResultatRechecheFicheEclatementElementValue vGammeOFReportElementValue = new ResultatRechecheFicheEclatementElementValue();

			vGammeOFReportElementValue.setNombrePaquet(elementEclatement.getNombrePaquet());
			vGammeOFReportElementValue.setOrdreFabricationId(elementEclatement.getOrdreFabricationId());

			listeElementGammeOF.add(vGammeOFReportElementValue);
		}

		report.setEclatementList(listeElementGammeOF);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OperationReportListValue getListOperationReport(RechercheMulticritereCatalogueValue request)
			throws IOException {

		OperationReportListValue report = new OperationReportListValue();

		// enrechissement des param du report
		report.setFileName(IConstanteReport.REPORT_NAME_OPERATION_LIST);
		report.setReportStream(new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Catalogue/Catalogue_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_OPERATION_LIST_PATH", IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Catalogue/Catalogue_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		// enrichissement du rapport
		ResultatRechecheCatalogueValue resultRecherche = operationPersistance.rechercherMultiCritere(request);

		if (resultRecherche != null) {
			Set<OperationValue> operationsList = new TreeSet<OperationValue>(resultRecherche.getList());
			// enrichissement du rapport
			enrichirOperationReportList(report, operationsList, request);

		}

		ArrayList<OperationReportListValue> dataList = new ArrayList<OperationReportListValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichirOperationReportList(OperationReportListValue reportList, Set<OperationValue> setOperation,
			RechercheMulticritereCatalogueValue request) {

		reportList.setMachineId(request.getMachineId());
		reportList.setCode(request.getCode());
		reportList.setSectionId(request.getSectionId());
		reportList.setDesignation(request.getDesignation());

		if (reportList.getOperationList() != null) {
			for (OperationValue ordreFabrication : setOperation) {

				reportList.getOperationList().add(ordreFabrication);
			}
		}

		if (reportList != null) {

			// Map< machineId , machineDesignation >
			List<MachineValue> listMachine = machinePersistance.getAll();
			Map<Long, String> mapMachineIdDesignation = new HashMap<Long, String>();
			for (MachineValue machine : listMachine) {
				mapMachineIdDesignation.put(machine.getId(), machine.getDesignation());
			}

			// Map< sectionId , sectionDesignation >
			List<SectionValue> listSection = sectionPersistance.getAll();
			Map<Long, String> mapSectionIdDesignation = new HashMap<Long, String>();
			for (SectionValue section : listSection) {
				mapSectionIdDesignation.put(section.getId(), section.getDesignation());
			}

			reportList.setMachineDesignation(mapMachineIdDesignation.get(request.getMachineId()));
			reportList.setSectionDesignation(mapSectionIdDesignation.get(request.getSectionId()));

			for (OperationValue operation : reportList.getOperationList()) {

				if (mapMachineIdDesignation.containsKey(operation.getMachineId())) {
					operation.setMachineDesignation(mapMachineIdDesignation.get(operation.getMachineId()));
				}

				if (mapSectionIdDesignation.containsKey(operation.getSectionId())) {
					operation.setSectionDesignation(mapSectionIdDesignation.get(operation.getSectionId()));
				}
			}

		}

	}

	@Override
	public ABCArticleDetailEtapeJourReportValue getABCArticleDetailEtapeJourReport(
			RechercheMulticritereABCArticleDetailEtapeJourValue request) throws IOException {

		ABCArticleDetailEtapeJourReportValue report = new ABCArticleDetailEtapeJourReportValue();

		// enrechissement des param du report
		report.setFileName(IConstanteReport.REPORT_NAME_ABCARTICLEDETAILETAPEJOUR_LIST);

		report.setReportStream(
				new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "AbcDetailEtapeJour/abc_detail_etape_jour_list_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_ABC_ARTICLE_DETAIL_ETAPE_JOUR_LIST_PATH",
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "AbcDetailEtapeJour/abc_detail_etape_jour_list_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		ResultatRechecheABCArticleDetailEtapeJourValue resultRecherche = aBCArticleDetailEtapeJourPersistance
				.rechercherMultiCritere(request);
		Set<ABCArticleDetailEtapeJourValue> articleDetailList = new TreeSet<ABCArticleDetailEtapeJourValue>(
				resultRecherche.getList());

		// enrichissement du rapport
		enrichirAbcArticleDetailEtapeJourReportList(report, articleDetailList, request);

		ArrayList<ABCArticleDetailEtapeJourReportValue> dataList = new ArrayList<ABCArticleDetailEtapeJourReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichirAbcArticleDetailEtapeJourReportList(ABCArticleDetailEtapeJourReportValue report,
			Set<ABCArticleDetailEtapeJourValue> articleDetailList,
			RechercheMulticritereABCArticleDetailEtapeJourValue request) {

		// TODO : phaseId > phaseDesignation
		report.setPhaseId(request.getPhaseId());
		report.setPhaseDesignation("");

		report.setDateSaisieFrom(request.getDateSaisieFrom());
		report.setDateSaisieTo(request.getDateSaisieTo());
		// groupement par refCommande
		Map<String, List<ABCArticleDetailEtapeJourValue>> mapListABCByRefCommande = new HashMap<String, List<ABCArticleDetailEtapeJourValue>>();

		// Liste
		List<ABCArticleDetailEtapeJourElementValue> listeArticleDetailElement = new ArrayList<ABCArticleDetailEtapeJourElementValue>();

		for (ABCArticleDetailEtapeJourValue elementARticleDEtail : articleDetailList) {

			String key = elementARticleDEtail.getRefCommande();

			if (mapListABCByRefCommande.get(key) == null) {
				mapListABCByRefCommande.put(key, new ArrayList<ABCArticleDetailEtapeJourValue>());

			}
			mapListABCByRefCommande.get(key).add(elementARticleDEtail);
		}
		Iterator it = mapListABCByRefCommande.entrySet().iterator();

		while (it.hasNext()) {

			Map.Entry<String, List<ABCArticleDetailEtapeJourValue>> pair = (Entry<String, List<ABCArticleDetailEtapeJourValue>>) it
					.next();

			ABCArticleDetailEtapeJourElementValue element = new ABCArticleDetailEtapeJourElementValue();

			Long qteTotal = 0L;

			for (ABCArticleDetailEtapeJourValue abc : pair.getValue()) {

				qteTotal = qteTotal + ((abc.getQte() != null) ? abc.getQte() : 0L);

			}
			element.setClientAbrevation(pair.getValue().get(IConstanteReport.FIRST_INDEX).getClientAbrevation());
			element.setProduitAbrevation(pair.getValue().get(IConstanteReport.FIRST_INDEX).getProduitAbrevation());
			element.setRefCommande(pair.getKey());
			element.setQteCmd(pair.getValue().get(IConstanteReport.FIRST_INDEX).getQteCmd());
			element.setQteProduite(qteTotal);

			listeArticleDetailElement.add(element);
		}

		report.setAbcArticleEtapeJourList(listeArticleDetailElement);

	}

	// Production Chart
	public ABCArticleDetailEtapeJourReportValue getChartProductionByDateGroupeByAffichageReport(
			RechercheMulticritereABCArticleDetailEtapeJourValue request) throws IOException {

		ABCArticleDetailEtapeJourReportValue report = new ABCArticleDetailEtapeJourReportValue();

		// enrechissement des param du report
		report.setFileName(IConstanteReport.REPORT_NAME_CHART_PRODUCTION_LIST);

		report.setReportStream(new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "chartProduction/chart_production_list_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_CHART_PRODUCTION_LIST_PATH",
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "chartProduction/chart_production_list_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		ResultatRechecheABCArticleDetailEtapeJourValue resultRecherche = aBCArticleDetailEtapeJourPersistance
				.rechercherMultiCritere(request);

		Set<ABCArticleDetailEtapeJourValue> articleDetailList = new TreeSet<ABCArticleDetailEtapeJourValue>(
				resultRecherche.getList());

		// enrichissement du rapport
		enrichirchartProductionReportList(report, articleDetailList, request);

		ArrayList<ABCArticleDetailEtapeJourReportValue> dataList = new ArrayList<ABCArticleDetailEtapeJourReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichirchartProductionReportList(ABCArticleDetailEtapeJourReportValue report,
			Set<ABCArticleDetailEtapeJourValue> articleDetailList,
			RechercheMulticritereABCArticleDetailEtapeJourValue request) {

		// TODO : phaseId > phaseDesignation
		report.setPhaseId(request.getPhaseId());
		report.setPhaseDesignation("");

		report.setDateSaisieFrom(request.getDateSaisieFrom());
		report.setDateSaisieTo(request.getDateSaisieTo());
		report.setAffichage(request.getAffichage());

		Map<Calendar, List<ABCArticleDetailEtapeJourValue>> mapListByDay = new HashMap<Calendar, List<ABCArticleDetailEtapeJourValue>>();
		Map<String, List<ABCArticleDetailEtapeJourValue>> mapListByMonth = new HashMap<String, List<ABCArticleDetailEtapeJourValue>>();
		Map<Integer, List<ABCArticleDetailEtapeJourValue>> mapListByYear = new HashMap<Integer, List<ABCArticleDetailEtapeJourValue>>();

		if (request.getAffichage() != null) {

			switch (request.getAffichage()) {
			case "Jour":
				System.out.print("Jouuur");
				List<ABCArticleDetailEtapeJourElementValue> listeElementParJour = new ArrayList<ABCArticleDetailEtapeJourElementValue>();

				for (ABCArticleDetailEtapeJourValue elementARticleDEtail : articleDetailList) {

					// stringToCalendar(elementARticleDEtail.getDateSaisie().toString());
					if (elementARticleDEtail.getDateSaisie() != null) {
						Calendar dayKey = elementARticleDEtail.getDateSaisie();

						if (mapListByDay.get(dayKey) == null) {
							mapListByDay.put(dayKey, new ArrayList<ABCArticleDetailEtapeJourValue>());

						}
						mapListByDay.get(dayKey).add(elementARticleDEtail);
					}
				}
				Iterator it = mapListByDay.entrySet().iterator();

				while (it.hasNext()) {

					Map.Entry<Calendar, List<ABCArticleDetailEtapeJourValue>> pair = (Entry<Calendar, List<ABCArticleDetailEtapeJourValue>>) it
							.next();

					ABCArticleDetailEtapeJourElementValue element = new ABCArticleDetailEtapeJourElementValue();

					Long qteTotal = 0L;

					for (ABCArticleDetailEtapeJourValue abc : pair.getValue()) {

						qteTotal = qteTotal + ((abc.getQte() != null) ? abc.getQte() : 0L);

					}
					element.setDateSaisie(pair.getKey());
					element.setQteCmd(pair.getValue().get(IConstanteReport.FIRST_INDEX).getQteCmd());
					element.setQteProduite(qteTotal);
					element.setAffichage(request.getAffichage());
					element.setJourSaisie(pair.getValue().get(IConstanteReport.FIRST_INDEX).getDateSaisie());

					listeElementParJour.add(element);
				}
				report.setAbcArticleEtapeJourList(listeElementParJour);

				break;
			case "Mois":
				// Groupement par Mois
				List<ABCArticleDetailEtapeJourElementValue> listeElementParMois = new ArrayList<ABCArticleDetailEtapeJourElementValue>();

				for (ABCArticleDetailEtapeJourValue elementARticleDEtail : articleDetailList) {

					if (elementARticleDEtail.getDateSaisie() != null) {
						String MonthYearKey = "" + (elementARticleDEtail.getDateSaisie().get(Calendar.MONTH) + 1) + "-"
								+ elementARticleDEtail.getDateSaisie().get(Calendar.YEAR);

						if (mapListByMonth.get(MonthYearKey) == null) {

							mapListByMonth.put(MonthYearKey, new ArrayList<ABCArticleDetailEtapeJourValue>());

						}
						mapListByMonth.get(MonthYearKey).add(elementARticleDEtail);
					}
				}
				Iterator itMois = mapListByMonth.entrySet().iterator();

				while (itMois.hasNext()) {

					Map.Entry<String, List<ABCArticleDetailEtapeJourValue>> pair = (Entry<String, List<ABCArticleDetailEtapeJourValue>>) itMois
							.next();

					ABCArticleDetailEtapeJourElementValue element = new ABCArticleDetailEtapeJourElementValue();

					Long qteTotal = 0L;

					for (ABCArticleDetailEtapeJourValue abc : pair.getValue()) {

						qteTotal = qteTotal + ((abc.getQte() != null) ? abc.getQte() : 0L);

					}
					element.setQteCmd(pair.getValue().get(IConstanteReport.FIRST_INDEX).getQteCmd());
					element.setQteProduite(qteTotal);
					element.setAffichage(request.getAffichage());
					element.setMoisSaisie(pair.getKey());

					listeElementParMois.add(element);
				}
				report.setAbcArticleEtapeJourList(listeElementParMois);

				break;
			case "Annee":
				System.out.print("Annéeee");
				List<ABCArticleDetailEtapeJourElementValue> listeElementParAnnee = new ArrayList<ABCArticleDetailEtapeJourElementValue>();

				for (ABCArticleDetailEtapeJourValue elementARticleDEtail : articleDetailList) {

					if (elementARticleDEtail.getDateSaisie() != null) {
						int yearKey = elementARticleDEtail.getDateSaisie().get(Calendar.YEAR);

						if (mapListByYear.get(yearKey) == null) {

							mapListByYear.put(yearKey, new ArrayList<ABCArticleDetailEtapeJourValue>());

						}
						mapListByYear.get(yearKey).add(elementARticleDEtail);
					}
				}
				Iterator itAnnee = mapListByYear.entrySet().iterator();

				while (itAnnee.hasNext()) {

					Map.Entry<Integer, List<ABCArticleDetailEtapeJourValue>> pair = (Entry<Integer, List<ABCArticleDetailEtapeJourValue>>) itAnnee
							.next();

					ABCArticleDetailEtapeJourElementValue element = new ABCArticleDetailEtapeJourElementValue();

					Long qteTotal = 0L;

					for (ABCArticleDetailEtapeJourValue abc : pair.getValue()) {

						qteTotal = qteTotal + ((abc.getQte() != null) ? abc.getQte() : 0L);

					}
					element.setQteCmd(pair.getValue().get(IConstanteReport.FIRST_INDEX).getQteCmd());
					element.setQteProduite(qteTotal);
					element.setAffichage(request.getAffichage());
					element.setAnneeSaisie(pair.getKey());

					listeElementParAnnee.add(element);
				}
				report.setAbcArticleEtapeJourList(listeElementParAnnee);

				break;
			}
		}

	}

	@Override
	public FicheSaisieReportSetValue getListFicheSaisieReport(RechercheMulticritereFeuilleSaisieValue request)
			throws IOException {
		FicheSaisieReportSetValue report = new FicheSaisieReportSetValue();

		// enrechissement des param du report
		report.setFileName(IConstanteReport.REPORT_NAME_FICHE_SAISIE_LIST);
		report.setReportStream(
				new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "FicheSaisie/FicheSaisieList/fiche_saisie_List_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_FICHE_SAISIE_LIST_PATH",
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "FicheSaisie/FicheSaisieList/fiche_saisie__sub_List_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		ResultatRechecheFeuilleSaisieValue resultRecherche = feuilleSaisiePersistance.rechercherMultiCritere(request);
		Set<ResultatRechecheFeuilleSaisieElementValue> list = new TreeSet<ResultatRechecheFeuilleSaisieElementValue>(
				resultRecherche.getList());

		// enrichissement du rapport
		enrichirFicheSaisieReportList(report, list, request);

		ArrayList<FicheSaisieReportSetValue> dataList = new ArrayList<FicheSaisieReportSetValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichirFicheSaisieReportList(FicheSaisieReportSetValue report,
			Set<ResultatRechecheFeuilleSaisieElementValue> listFicheSaisie,
			RechercheMulticritereFeuilleSaisieValue request) {

		if (request.getChaineId() != null) {
			report.setChaineId(request.getChaineId());
			ChaineValue chaine = chainePersistance.rechercheChaineParId(request.getChaineId());
			if (chaine != null) {
				report.setChaineDesignation(chaine.getDesignation());
			}
		}
		report.setDateSaisieMin(request.getDateSaisieMin());
		report.setDateSaisieMax(request.getDateSaisieMax());
		report.setMatricule(request.getMatricule());

		List<ResultatRechecheFicheSaisieElementValue> listeElementFicheSaisie = new ArrayList<ResultatRechecheFicheSaisieElementValue>();

		for (ResultatRechecheFeuilleSaisieElementValue elementficheSaisie : listFicheSaisie) {

			ResultatRechecheFicheSaisieElementValue elementValue = new ResultatRechecheFicheSaisieElementValue();
			/** Nom&Prenom */
			RechercheMulticriterePersonnelValue requestPeronnel = new RechercheMulticriterePersonnelValue();
			requestPeronnel.setMatricule(elementficheSaisie.getPersonnelMatricule());

			ResultatRechechePersonnelValue personnel = personnelPersistance.rechercherMultiCritere(requestPeronnel);
			if (personnel.getList() != null) {

				if (personnel.getList().size() > 0) {

					elementValue.setNomPrenom(personnel.getList().iterator().next().getNom() + " "
							+ personnel.getList().iterator().next().getPrenom());
				}
			}
			/** Chaine */
			if (elementficheSaisie.getChaineId() != null) {
				ChaineValue chaine = chainePersistance.rechercheChaineParId(elementficheSaisie.getChaineId());
				if (chaine != null) {
					elementValue.setChaineDesignation(chaine.getDesignation());
				}
			}

			elementValue.setActivite(elementficheSaisie.getActivite());
			elementValue.setDateSaisie(elementficheSaisie.getDateSaisie());
			elementValue.setMatricule(elementficheSaisie.getPersonnelMatricule());
			elementValue.setMinPresence(elementficheSaisie.getMinPresence());
			elementValue.setMinProd(elementficheSaisie.getMinProd());
			elementValue.setMinAleas(elementficheSaisie.getMinAleas());
			elementValue.setRendement(elementficheSaisie.getRendement());

			listeElementFicheSaisie.add(elementValue);
		}

		Collections.sort(listeElementFicheSaisie, new ResultatRechercheFicheSaisieElementComparator());
		report.setFicheSaisieElementList(listeElementFicheSaisie);

	}

	@Override
	public FicheSaisieReporListValue getListFSaisieProductiviteGlobaleReport(
			RechercheMulticritereFeuilleSaisieValue request) throws IOException {

		FicheSaisieReporListValue report = new FicheSaisieReporListValue();

		// enrechissement des param du report
		report.setFileName(IConstanteReport.REPORT_NAME_F_SAISIE_PRODUCTIVITE_GLOBALE_LIST);
		report.setReportStream(new FileInputStream(
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "FicheSaisie/ProductiviteGlobale/f_saisie_productivite_globale_List_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_F_SAISIE_PRODUCTIVITE_GLOBALE_LIST_PATH",
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "FicheSaisie/ProductiviteGlobale/f_saisie_productivite_globale_sub_List_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		ResultatRechecheFeuilleSaisieValue resultRecherche = feuilleSaisiePersistance.rechercherMultiCritere(request);

		Set<ResultatRechecheFeuilleSaisieElementValue> set = new TreeSet<ResultatRechecheFeuilleSaisieElementValue>(
				resultRecherche.getList());

		// enrichissement du rapport
		enrichirFSaisieProductiviteGlobaleReportList(report, set, request);

		ArrayList<FicheSaisieReporListValue> dataList = new ArrayList<FicheSaisieReporListValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichirFSaisieProductiviteGlobaleReportList(FicheSaisieReporListValue report,
			Set<ResultatRechecheFeuilleSaisieElementValue> set, RechercheMulticritereFeuilleSaisieValue request) {

		report.setChaineId(request.getChaineId());
		if (request.getChaineId() != null) {
			report.setChaineId(request.getChaineId());
			ChaineValue chaine = chainePersistance.rechercheChaineParId(request.getChaineId());
			if (chaine != null) {
				report.setChaineDesignation(chaine.getDesignation());
			}
		}

		report.setDateSaisieMin(request.getDateSaisieMin());
		report.setDateSaisieMax(request.getDateSaisieMax());
		report.setMatricule(request.getMatricule());

		Double totalActivite = IConstanteReport.ZEROD;
		Double totalRendement = IConstanteReport.ZEROD;
		Double totalAleas = IConstanteReport.ZEROD;
		Double totalPresence = IConstanteReport.ZEROD;
		Double totalProduction = IConstanteReport.ZEROD;

		Map<String, List<ResultatRechecheFeuilleSaisieElementValue>> mapListByMatricule = new HashMap<String, List<ResultatRechecheFeuilleSaisieElementValue>>();

		// List<ResultatRechecheFeuilleSaisieByMatriculeElementValue>
		// listeProductiviteParMatricule = new
		// ArrayList<ResultatRechecheFeuilleSaisieByMatriculeElementValue>();
		List<ResultatRechecheFeuilleSaisieByMatriculeElementValue> listResultatRechecheFeuilleSaisieByMat = new ArrayList<ResultatRechecheFeuilleSaisieByMatriculeElementValue>();

		for (ResultatRechecheFeuilleSaisieElementValue elementFicheSaisie : set) {

			if (elementFicheSaisie.getPersonnelMatricule() != null
					&& !personnelPersistance.personnelIsIndirect(elementFicheSaisie.getPersonnelMatricule())) {
				String matriculeKey = elementFicheSaisie.getPersonnelMatricule();

				if (mapListByMatricule.get(matriculeKey) == null) {
					mapListByMatricule.put(matriculeKey, new ArrayList<ResultatRechecheFeuilleSaisieElementValue>());

				}
				mapListByMatricule.get(matriculeKey).add(elementFicheSaisie);
			}
		}
		Iterator it = mapListByMatricule.entrySet().iterator();

		while (it.hasNext()) {

			Map.Entry<String, List<ResultatRechecheFeuilleSaisieElementValue>> pair = (Entry<String, List<ResultatRechecheFeuilleSaisieElementValue>>) it
					.next();

			ResultatRechecheFeuilleSaisieByMatriculeElementValue element = new ResultatRechecheFeuilleSaisieByMatriculeElementValue();

			element.setPersonnelMatricule(pair.getKey());
			/** Nom&Prenom */
			RechercheMulticriterePersonnelValue requestPeronnel = new RechercheMulticriterePersonnelValue();
			requestPeronnel.setMatricule(pair.getKey());

			PersonnelValue personnel = personnelPersistance.getByMatricule(requestPeronnel.getMatricule());
			if (personnel != null) {
				element.setNomPrenom(personnel.getNom() + " " + personnel.getPrenom());
			}

			Long pscProd = IConstanteReport.ZERO;

			Double rendement = IConstanteReport.ZEROD;
			Double minProd = IConstanteReport.ZEROD;
			Double minPresence = IConstanteReport.ZEROD;
			Double minAlea = IConstanteReport.ZEROD;
			Double activite = IConstanteReport.ZEROD;
			Double denominateur;

			for (ResultatRechecheFeuilleSaisieElementValue abc : pair.getValue()) {
				/** Nbr Pieces */
				pscProd = pscProd + ((abc.getPscProd() != null) ? abc.getPscProd() : 0);

				/** minPresence */
				minPresence = minPresence + ((abc.getMinPresence() != null) ? abc.getMinPresence() : 0);

				/** minProd */
				minProd = minProd + ((abc.getMinProd() != null) ? abc.getMinProd() : 0);

				/** minAleas */
				minAlea = minAlea + ((abc.getMinAleas() != null) ? abc.getMinAleas() : 0);

			}

			/** rendement */
			if (minPresence != 0) {
				rendement = (minProd / minPresence) * 100;
			}

			/** activite */
			if (minAlea >= 0) {
				denominateur = minPresence - minAlea;

				if (denominateur != null) {

					activite = (minProd / denominateur) * 100;
				}
			}

			element.setPscProd(pscProd);

			element.setMinPresence(minPresence);
			element.setMinProd(minProd);
			element.setRendement(rendement);
			element.setMinAleas(minAlea);
			element.setActivite(activite);

			totalAleas = totalAleas + minAlea;
			totalPresence = totalPresence + minPresence;
			totalProduction = totalProduction + minProd;

			listResultatRechecheFeuilleSaisieByMat.add(element);
		}

		Collections.sort(listResultatRechecheFeuilleSaisieByMat);
		//Ordre croissant
		Collections.reverse(listResultatRechecheFeuilleSaisieByMat);
		report.setFicheSaisieElementByMatriculeList(listResultatRechecheFeuilleSaisieByMat);

		if (totalPresence >= 0) {
			totalRendement = (totalProduction / totalPresence) * 100;
		}

		if (totalAleas >= 0) {
			Double totalDenominateur = totalPresence - totalAleas;

			if (totalDenominateur > 0) {
				totalActivite = (totalProduction / totalDenominateur) * 100;

			}
		}

		report.setTotalActivite(totalActivite);

		report.setTotalRendement(totalRendement);

		report.setTotalAleas(totalAleas);

		report.setTotalPresence(totalPresence);

		report.setTotalProduction(totalProduction);
	}

	@Override
	public RecapProductionReportValue getRecapProductionReport(RechercheMulticritereRecapProductionValue request)
			throws IOException {

		RecapProductionReportValue report = new RecapProductionReportValue();

		// enrechissement des param du report
		report.setFileName(IConstanteReport.REPORT_NAME_RECAP_PRODUCTION);
		report.setReportStream(new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "RecapProduction/recap_production_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		ResultatRechecheRecapProductionValue resultRecherche = recapProductionDomaine.rechercherMultiCritere(request);

		// enrichissement du rapport
		enrichirRecapProductionReport(report, resultRecherche, request);

		ArrayList<RecapProductionReportValue> dataList = new ArrayList<RecapProductionReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichirRecapProductionReport(RecapProductionReportValue report,
			ResultatRechecheRecapProductionValue resultRecherche, RechercheMulticritereRecapProductionValue request) {

		Set<RecapProductionElementReportValue> recapProductionElementList = new TreeSet<RecapProductionElementReportValue>();

		report.setDateIntroductionMax(request.getDateIntroductionMax());
		report.setDateIntroductionMin(request.getDateIntroductionMin());
		report.setDateLivraisonMax(request.getDateLivraisonMax());
		report.setDateLivraisonMin(request.getDateLivraisonMin());

		if (request.getPartieInteresseId() != null) {
			PartieInteresseValue partieInteresse = partieInteresseePersistance.getById(request.getPartieInteresseId());
			if (partieInteresse != null) {
				report.setPartieInterresAbreviation(partieInteresse.getAbreviation());
			}
		}

		if (request.getProduitId() != null) {
			ProduitValue produit = produitPersistance.rechercheProduitById(request.getProduitId());
			if (produit != null) {
				report.setProduitReference(produit.getReference());
				report.setProduitDesignation(produit.getDesignation());
			}
		}
		
		if (request.getStatutId()!= null) {
			StatutOfValue statut = statutPersistence.rechercheStatutParId(request.getStatutId());
			if (statut != null) {
				report.setStatutDesignation(statut.getDesignation());;
				
			}
		}

		for (RecapProductionValue recapProduction : resultRecherche.getList()) {

			RecapProductionElementReportValue recapProductionElementReport = new RecapProductionElementReportValue();

			recapProductionElementReport.setOfId(recapProduction.getOfId());
			recapProductionElementReport.setNumero(recapProduction.getNumero());
			recapProductionElementReport.setQuantite(recapProduction.getQuantite());
			recapProductionElementReport.setPartieInterresId(recapProduction.getPartieInterresId());
			recapProductionElementReport.setProduitId(recapProduction.getProduitId());
			recapProductionElementReport.setDateLivraison(recapProduction.getDateLivraison());
			recapProductionElementReport.setDateIntroduction(recapProduction.getDateIntroduction());

			recapProductionElementReport.setPartieInterresAbreviation(recapProduction.getPartieInterresAbreviation());
			recapProductionElementReport.setProduitReference(recapProduction.getProduitReference());
			recapProductionElementReport.setProduitDesignation(recapProduction.getProduitDesignation());

			enrichirRecapOperations(recapProductionElementReport);

			recapProductionElementList.add(recapProductionElementReport);
		}

		report.setRecapProductionElementList(recapProductionElementList);
	}

	private void enrichirRecapOperations(RecapProductionElementReportValue element) {

		element.setSortieCoupe(quantiteOpt(element.getOfId(), IConstante.SCPID));
		element.setEngagement(quantiteOpt(element.getOfId(), IConstante.ENGID));
		element.setSortieChaine(quantiteOpt(element.getOfId(), IConstante.SCHID));
		//element.setExport(quantite(quantiteOpt.getOfId(), IConstante.EXPID));
		element.setEclatemenet(quantiteOpt(element.getOfId(), IConstante.ECLID));
		element.setMiseEnColis(quantiteOpt(element.getOfId(), IConstante.MCOLID));
		element.setSortieConditionnement(quantiteOpt(element.getOfId(), IConstante.SCOID));

	}
	
	private Long quantiteOpt(Long ofId, Long operation) {

		Long quantite = IConstanteReport.ZERO;

		

		if (operation != null) {

					quantite = ordreFabricationPersistance.CalculeQteProduiteCumul(ofId,operation) ;
				
		}

		return quantite;
	}
	

	private Long quantite(Long ofId, String operationCode) {

		Long quantite = IConstanteReport.ZERO;

		Long operationId = operationPersistance.getIdByCode(operationCode);

		if (operationId != null) {

			List<Long> listIdsGammeOf = elementGammeOfPersistance.getByOfIdAndOperationId(ofId, operationId);

			for (Long idGammeOf : listIdsGammeOf) {

				if (idGammeOf != null) {
					quantite = saisieElementPersistance.getSommeQteByGammeId(idGammeOf);
				}
			}
		}

		return quantite;
	}

	@Override
	public AvancementOFReportValue getAvancementOFReportValue(Long id) throws IOException {

		AvancementOFReportValue report = new AvancementOFReportValue();

		// enrechissement des param du report
		report.setFileName(IConstanteReport.REPORT_NAME_AVANCEMENT_OF);
		report.setReportStream(new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "AvancementOF/avancement_OF_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_AVANCEMENT_OF_PATH", IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "AvancementOF/avancement_OF_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		GammeOfValue gammeOf = gammeOfPersistance.getByOFId(id);

		// enrichissement du rapport
		if (gammeOf != null) {
			enrichmentAvancementOFReport(report, gammeOf);
		}
		ArrayList<AvancementOFReportValue> dataList = new ArrayList<AvancementOFReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichmentAvancementOFReport(AvancementOFReportValue report, GammeOfValue gammeOFValue) {

		// quantiteOF
		Long qteOF = 0L;
		// Map< operationId , operationDesignation >
		Map<Long, String> mapOperationIdDesignation = new HashMap<Long, String>();

		// Map< operationId , operationCode >
		Map<Long, String> mapOperationIdCode = new HashMap<Long, String>();

		List<OperationValue> listOperation = operationPersistance.getAll();

		for (OperationValue operation : listOperation) {
			mapOperationIdDesignation.put(operation.getId(), operation.getDesignation());
		}

		for (OperationValue operation : listOperation) {
			mapOperationIdCode.put(operation.getId(), operation.getCode());
		}

		report.setNbOperation(gammeOFValue.getNbOperation());
		report.setOrdreFabricationId(gammeOFValue.getOrdreFabricationId());
		if (gammeOFValue.getOrdreFabricationId() != null) {

			OrdreFabricationValue ordreFabrication = ordreFabricationPersistance
					.rechercheOrdreFabricationParId(gammeOFValue.getOrdreFabricationId());

			if (ordreFabrication != null) {
				/** qteOF **/
				qteOF = ordreFabrication.getQuantite();
				report.setQuantiteOF(qteOF);
				/** OFNumero **/
				report.setOrdreFabricationNumero(ordreFabrication.getNumero());
				/** produitId **/
				report.setProduitId(ordreFabrication.getProduitId());
				if (ordreFabrication.getProduitId() != null) {

					ProduitValue produit = produitPersistance.rechercheProduitById(ordreFabrication.getProduitId());

					if (produit != null) {
						report.setProduitDesignation(produit.getDesignation());
						report.setProduitReference(produit.getReference());
					}
				}

				if (ordreFabrication.getPartieInterresId() != null) {

					PartieInteresseValue client = partieInteresseePersistance
							.getById(ordreFabrication.getPartieInterresId());

					if (client != null) {

						report.setClientId(client.getId());
						report.setClientAbreviation(client.getAbreviation());
						report.setClientReference(client.getReference());
					}

				}
			}
		}

		Long sommeQteEclatee = packetPersistance.getSommeQte(gammeOFValue.getOrdreFabricationId());

		report.setQuantiteEclatee(sommeQteEclatee);
		report.setObservations(gammeOFValue.getObservations());
		report.setProduitId(gammeOFValue.getProduitId());
		report.setTempsTotal(gammeOFValue.getTempsTotal());

		List<ElementGammeOfValue> listElementGammeOf = new ArrayList<ElementGammeOfValue>();

		for (ElementGammeOfValue elementOf : gammeOFValue.getListElementGammeOf()) {
			/** Operation **/
			if (elementOf.getOperationId() != null) {

				if (mapOperationIdDesignation.containsKey(elementOf.getOperationId())) {

					elementOf.setOperationDesignation(mapOperationIdDesignation.get(elementOf.getOperationId()));
					elementOf.setOperationCode(mapOperationIdCode.get(elementOf.getOperationId()));
				}
			}

			/** sommeQteProduite **/
			Long elementGammeOFId = elementOf.getId();

			RechercheMulticritereDetailSaisieValue request = new RechercheMulticritereDetailSaisieValue();
			request.setElementGammeOfId(elementGammeOFId);

			ResultatRechecheDetailSaisieValue resultatDetailSaisie = detailSaisiePersistance
					.rechercherMultiCritere(request);
			Long sommeQteProduite = 0L;

			if (resultatDetailSaisie != null) {

				Set<DetailSaisieElementValue> list = resultatDetailSaisie.getList();

				if (!list.isEmpty()) {

					for (DetailSaisieElementValue detail : resultatDetailSaisie.getList()) {

						if (detail != null) {
							sommeQteProduite = sommeQteProduite + detail.getQuantite();

						}
					}

					//logger.debug("*** sommeQteProduiteParElementGammeOF : " + sommeQteProduite);
					elementOf.setQteProduite(sommeQteProduite);

				}

			}
			/** Minutage **/
			Double minutage = 0D;
			if (elementOf.getQteProduite() != null && elementOf.getTemps() != null) {

				minutage = elementOf.getQteProduite() * elementOf.getTemps();
			}
			elementOf.setMinutage(minutage);

			/** Reste **/
			Long reste = 0L;
			if ((elementOf.getQteProduite() != null) && (elementOf.getQteProduite() != 0L)) {
				reste = qteOF - elementOf.getQteProduite();
			}
			elementOf.setReste(reste);

			/** Comptage **/
			elementOf.setComptage(elementOf.getComptage());

			/*** Reste / eclaté ***/
			elementOf.setResteEclate(sommeQteEclatee - sommeQteProduite);

			listElementGammeOf.add(elementOf);
		}

		Collections.sort(listElementGammeOf);

		report.setListElementGammeOf(new TreeSet<>(listElementGammeOf));

	}

	@Override
	public ChaineComptageReportValue getChaineComptageReport(RechercheMulticritereDetailSaisieValue request)
			throws IOException {

		ChaineComptageReportValue report = new ChaineComptageReportValue();

		// enrechissement des param du report
		report.setFileName(IConstanteReport.REPORT_NAME_RAPPORT_CHAINE_COMPTAGE);
		report.setReportStream(
				new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/RapportChaine/comptage/comptage_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("DETAIL_SAISIE_SUB_REPORT",
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/RapportChaine/comptage/comptage_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		// enrichissement du rapport
		enrichirRapportChaineComptageReport(report, request);

		ArrayList<ChaineComptageReportValue> dataList = new ArrayList<ChaineComptageReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichirRapportChaineComptageReport(ChaineComptageReportValue report,
			RechercheMulticritereDetailSaisieValue request) {

		Long ID_SCP = 0L;
		Long ID_ENG = 0L;
		Long ID_SCH = 0L;
		Long ID_EXP = 0L;
		Long ID_ECL = 0L;
		Long ID_MCOL = 0L;
		Long ID_SCO = 0L;

		/** Critère de recherche */
		if (request.getChaineId() != null) {
			report.setChaineId(request.getChaineId());
			String designationChaine = chainePersistance.rechercheDesignationChaineParId(request.getChaineId());
			if (designationChaine != null) {
				report.setChaineDesignation(designationChaine);
			}
		}
		//LOGGER.info("====**" + request.getDateSaisieMin());
		report.setDateIntroductionMin(request.getDateSaisieMin());
		report.setDateIntroductionMax(request.getDateSaisieMax());

		RechercheMulticritereCatalogueValue requestOperation = new RechercheMulticritereCatalogueValue();

		/** operation SCP */
		requestOperation.setCode(IConstante.SCP);
		ID_SCP = operationPersistance.getIdByCode(requestOperation.getCode());

		/** operation ENG */
		requestOperation.setCode(IConstante.ENG);
		ID_ENG = operationPersistance.getIdByCode(requestOperation.getCode());

		/** operation SCH */
		requestOperation.setCode(IConstante.SCH);
		ID_SCH = operationPersistance.getIdByCode(requestOperation.getCode());

		/** operation EXP */
		requestOperation.setCode(IConstante.EXP);
		ID_EXP = operationPersistance.getIdByCode(requestOperation.getCode());

		/** operation ECL */
		requestOperation.setCode(IConstante.ECL);
		ID_ECL = operationPersistance.getIdByCode(requestOperation.getCode());

		/** operation MCOL */
		requestOperation.setCode(IConstante.MCOL);
		ID_MCOL = operationPersistance.getIdByCode(requestOperation.getCode());

		/** operation SCO */
		requestOperation.setCode(IConstante.SCO);
		ID_SCO = operationPersistance.getIdByCode(requestOperation.getCode());

		List<ChaineValue> listeChaines = new ArrayList<ChaineValue>();

		if (request.getChaineId() != null) {

			ChaineValue chaine = chainePersistance.rechercheChaineParId(request.getChaineId());
			listeChaines.add(chaine);

		} else {

			listeChaines = chainePersistance.listeChaine();
		}

		List<ChaineComptageElementReportValue> chaineComptageElementList = new ArrayList<ChaineComptageElementReportValue>();

		for (ChaineValue chaineElement : listeChaines) {

			request.setChaineId(chaineElement.getId());

			List<DetailSaisieElementValue> listElementdetailSaisie = detailSaisiePersistance
					.getListDetailsSaisieAllegee(request);

			Long QTE_CP = 0L;
			Long QTE_ENG = 0L;
			Long QTE_SCO = 0L;
			Long QTE_MCOL = 0L;
			Long QTE_ECL = 0L;
			Long QTE_EXP = 0L;
			Long QTE_SCH = 0L;

			/** calcule des quantites */
			ChaineComptageElementReportValue elementChaineComp = new ChaineComptageElementReportValue();
			for (DetailSaisieElementValue elementDetailSaisie : listElementdetailSaisie) {

				if (elementDetailSaisie != null) {
					if (elementDetailSaisie.getElementGammeOFId() != null) {

						ElementGammeOfValue element = elementGammeOfPersistance
								.getByElementOFId(elementDetailSaisie.getElementGammeOFId());

						if (element != null) {

							Long operationId = element.getOperationId();

							/** QTE_CP */
							if (ID_SCP != null && operationId != null && operationId.equals(ID_SCP)) {
								if (elementDetailSaisie.getQuantite() != null)
									QTE_CP = QTE_CP + elementDetailSaisie.getQuantite();
							}
							/** QTE_ENG */
							if (ID_ENG != null && operationId != null && operationId.equals(ID_ENG)) {
								if (elementDetailSaisie.getQuantite() != null)
									QTE_ENG = QTE_ENG + elementDetailSaisie.getQuantite();
							}
							/** QTE_SCH */
							if (ID_SCH != null && operationId != null && operationId.equals(ID_SCH)) {
								if (elementDetailSaisie.getQuantite() != null)
									QTE_SCH = QTE_SCH + elementDetailSaisie.getQuantite();

							}
							/** QTE_EXP */
							if (ID_EXP != null && operationId != null && operationId.equals(ID_EXP)) {
								if (elementDetailSaisie.getQuantite() != null)
									QTE_EXP = QTE_EXP + elementDetailSaisie.getQuantite();
							}
							/** QTE_ECL */
							if (ID_ECL != null && operationId != null && operationId.equals(ID_ECL)) {
								if (elementDetailSaisie.getQuantite() != null)
									QTE_ECL = QTE_ECL + elementDetailSaisie.getQuantite();
							}
							/** QTE_MCOL */
							if (ID_MCOL != null && operationId != null && operationId.equals(ID_MCOL)) {
								if (elementDetailSaisie.getQuantite() != null)
									QTE_MCOL = QTE_MCOL + elementDetailSaisie.getQuantite();
							}
							/** QTE_SCO */
							if (ID_SCO != null && operationId != null && operationId.equals(ID_SCO)) {
								if (elementDetailSaisie.getQuantite() != null)
									QTE_SCO = QTE_SCO + elementDetailSaisie.getQuantite();
							}

						}
					}
				}
			}
			elementChaineComp.setChaineDesignation(chaineElement.getDesignation());
			elementChaineComp.setEngagement(QTE_ENG);
			elementChaineComp.setSortieCoupe(QTE_CP);
			elementChaineComp.setSortieChaine(QTE_SCH);
			elementChaineComp.setExport(QTE_EXP);
			elementChaineComp.setEclatemenet(QTE_ECL);
			elementChaineComp.setMiseEnColis(QTE_MCOL);
			elementChaineComp.setSortieConditionnement(QTE_SCO);

			chaineComptageElementList.add(elementChaineComp);

		}
		report.setChaineComptageElementList(chaineComptageElementList);
	}

	@Override
	public ChaineGlobalOperationReportValue getChaineGlobalOperationReport(
			RechercheMulticritereDetailSaisieValue request) throws IOException {

		ChaineGlobalOperationReportValue report = new ChaineGlobalOperationReportValue();

		// enrechissement des param du report
		report.setFileName(IConstanteReport.REPORT_NAME_RAPPORT_CHAINE_COMPTAGE);
		report.setReportStream(new FileInputStream(
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/RapportChaine/globalOperation/global_operation_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("CHAINE_GLOBAL_PRODUCTION_SUB_REPORT",
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/RapportChaine/globalOperation/global_operation_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		// enrichissement du rapport
		enrichirRapportChaineGlobalOperationReport(report, request);

		ArrayList<ChaineGlobalOperationReportValue> dataList = new ArrayList<ChaineGlobalOperationReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichirRapportChaineGlobalOperationReport(ChaineGlobalOperationReportValue report,
			RechercheMulticritereDetailSaisieValue request) {

		/** Critère de recherche */
		if (request.getChaineId() != null) {
			report.setChaineId(request.getChaineId());
			String designationChaine = chainePersistance.rechercheDesignationChaineParId(request.getChaineId());
			if (designationChaine != null) {
				report.setChaineDesignation(designationChaine);
			}
		}

		report.setDateIntroductionMin(request.getDateSaisieMin());
		report.setDateIntroductionMax(request.getDateSaisieMax());

		/** Liste */
		Set<DetailSaisieElementValue> resultRecherche = detailSaisieDomaine.getListDetailsSaisieElement(request);

		Map<Long, List<DetailSaisieElementValue>> mapDetailSaisieByOperationId = new HashMap<Long, List<DetailSaisieElementValue>>();
		Map<Long, Long> mapQteByOperationId = new HashMap<Long, Long>();

		List<ChaineGlobalOperationElementReportValue> chaineGlobalOperationElementList = new ArrayList<ChaineGlobalOperationElementReportValue>();
		Long qteTotale = 0L;
		for (DetailSaisieElementValue elementDetailSaisie : resultRecherche) {
			if (elementDetailSaisie != null) {
				if (elementDetailSaisie.getElementGammeOFId() != null) {

					ElementGammeOfValue elementGammeOF = elementGammeOfPersistance
							.getByElementOFId(elementDetailSaisie.getElementGammeOFId());

					if (elementGammeOF != null) {
						if (elementGammeOF.getOperationId() != null) {

							Long key = elementGammeOF.getOperationId();

							if (mapDetailSaisieByOperationId.get(key) == null) {
								mapDetailSaisieByOperationId.put(key, new ArrayList<DetailSaisieElementValue>());

							}
							/** calcule de la qte Totale de chaque Operation */
							qteTotale = qteTotale + elementDetailSaisie.getQuantite();
							/** affectation */
							elementDetailSaisie.setQuantite(qteTotale);
							/** affectation le l'objet au MapDetail */
							mapDetailSaisieByOperationId.get(key).add(elementDetailSaisie);
							/** affectation de la qteTotale au MapQte */
							mapQteByOperationId.put(key, qteTotale);

						}
					}
				}
			}

		}
		Iterator it = mapDetailSaisieByOperationId.entrySet().iterator();

		while (it.hasNext()) {

			ChaineGlobalOperationElementReportValue element = new ChaineGlobalOperationElementReportValue();

			Map.Entry<Long, List<DetailSaisieElementValue>> pair = (Entry<Long, List<DetailSaisieElementValue>>) it
					.next();
			if (pair.getValue() != null) {
				if (pair.getValue().size() > 0) {
					if (pair.getValue().get(IConstanteReport.FIRST_INDEX) != null) {
						Long codeId = pair.getKey();
						OperationValue operation = operationPersistance.getById(codeId);
						element.setCodeId(codeId);
						element.setCodeDesignation(operation.getDesignation());
						element.setCodeReference(operation.getCode());
						element.setTemps(operation.getTemps());

						element.setQuantiteProduite(pair.getValue().get(IConstanteReport.FIRST_INDEX).getQuantite());
						chaineGlobalOperationElementList.add(element);
					}
				}
			}
		}

		report.setChaineGlobalOperationElementList(chaineGlobalOperationElementList);
	}

	@Override
	public ProductionGlobalReportValue getProductionGlobalReport(ProductionReportRequestValue request)
			throws IOException {

		ProductionGlobalReportValue report = new ProductionGlobalReportValue();

		report.setFileName(IConstanteReport.REPORT_NAME_PRODUCTION_GLOBALE);
		report.setReportStream(new FileInputStream(IConstanteReport.PATH_JRXML_PRODUCTION_GLOBALE));

		HashMap<String, Object> params = new HashMap<String, Object>();

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		enrichirProductionGlobalReport(report, request);

		ArrayList<ProductionGlobalReportValue> dataList = new ArrayList<ProductionGlobalReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichirProductionGlobalReport(ProductionGlobalReportValue report,
			ProductionReportRequestValue request) {

		report.setDateMin(request.getDateMin());
		report.setDateMax(request.getDateMax());

		List<ProductionGlobalElementReportValue> listElement = new ArrayList<ProductionGlobalElementReportValue>();

		// retrive all Chaine
		List<ChaineValue> listChaine = chainePersistance.listeChaine();

		if (listChaine != null) {

			// one instance used for any detailSaisieRequest
			RechercheMulticritereDetailSaisieValue detailSaisieRequest = new RechercheMulticritereDetailSaisieValue();

			for (ChaineValue chaine : listChaine) {

				ProductionGlobalElementReportValue element = new ProductionGlobalElementReportValue();

				element.setChaineId(chaine.getId());
				element.setChaineDesignation(chaine.getDesignation());

				List<ProductionGlobalElementChaineReportValue> listElementChaine = new ArrayList<ProductionGlobalElementChaineReportValue>();

				detailSaisieRequest.setChaineId(chaine.getId());
				detailSaisieRequest.setDateSaisieMax(request.getDateMax());
				detailSaisieRequest.setDateSaisieMin(request.getDateMin());
				ResultatRechecheDetailSaisieValue resultRecherche = detailSaisieDomaine
						.rechercherMultiCritere(detailSaisieRequest);

				if (resultRecherche != null) {

					for (DetailSaisieElementValue detailSaisieElement : resultRecherche.getList()) {

						ProductionGlobalElementChaineReportValue elementChaine = new ProductionGlobalElementChaineReportValue();

						elementChaine.setOfNumero(detailSaisieElement.getOrdreFabricationNumero());

						if (elementChaine.getOfNumero() != null) {

							OrdreFabricationValue ordreFabrication = ordreFabricationPersistance
									.getByNumero(elementChaine.getOfNumero());

							if (ordreFabrication != null) {

								elementChaine.setOfId(ordreFabrication.getId());

								if (ordreFabrication.getPartieInterresId() != null) {
									PartieInteresseValue client = partieInteresseePersistance
											.getById(ordreFabrication.getPartieInterresId());
									if (client != null) {
										elementChaine.setClientId(ordreFabrication.getPartieInterresId());
										elementChaine.setClientReference(client.getReference());
										elementChaine.setClientAbreviation(client.getAbreviation());
									}
								}

								if (ordreFabrication.getProduitId() != null) {
									ProduitValue produit = produitPersistance
											.rechercheProduitById(ordreFabrication.getProduitId());
									if (produit != null) {
										elementChaine.setProduitId(ordreFabrication.getProduitId());
										elementChaine.setProduitReference(produit.getReference());
										elementChaine.setProduitDesignation(produit.getDesignation());
									}
								}
							}
						}

						elementChaine.setEngagement(quantite(elementChaine.getOfId(), IConstante.ENG));
						elementChaine.setSortieChaine(quantite(elementChaine.getOfId(), IConstante.SCH));
						elementChaine.setSortieConditionnement(quantite(elementChaine.getOfId(), IConstante.SCO));

						listElementChaine.add(elementChaine);
					}
				}

				element.setList(new TreeSet<>(listElementChaine));

				listElement.add(element);
			}
		}

		report.setElementsList(new TreeSet<>(listElement));

	}

	/*********************************************************************************************/

	// TODO: Méthode non utilisée
	@Override
	public ProductionChaineReportValue getProductionChaineReport(ProductionReportRequestValue request)
			throws IOException {

		ProductionChaineReportValue report = new ProductionChaineReportValue();

		report.setFileName(IConstanteReport.REPORT_NAME_PRODUCTION_CHAINE);
		report.setReportStream(new FileInputStream(IConstanteReport.PATH_JRXML_PRODUCTION_CHAINE));

		HashMap<String, Object> params = new HashMap<String, Object>();

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		enrichirProductionChaineReport(report, request);

		ArrayList<ProductionChaineReportValue> dataList = new ArrayList<ProductionChaineReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichirProductionChaineReport(ProductionChaineReportValue report,
			ProductionReportRequestValue request) {

	}

	/****************************
	 * Rapport Global / OF
	 ******************************/

	@Override
	public ChaineGlobalOFReportValue getChaineGlobalOFReport(RechercheMulticritereDetailSaisieValue request)
			throws IOException {

		ChaineGlobalOFReportValue report = new ChaineGlobalOFReportValue();

		report.setFileName(IConstanteReport.REPORT_NAME_CHAINE_GLOBAL_OF);
		report.setReportStream(
				new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/RapportChaine/globalOF/global_of_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();

		params.put("CHAINE_GLOBAL_OF_SUB_REPORT",
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/RapportChaine/globalOF/global_of_sub_report.jasper");
		
		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		enrichirChaineGlobalOFReport(report, request);

		ArrayList<ChaineGlobalOFReportValue> dataList = new ArrayList<ChaineGlobalOFReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichirChaineGlobalOFReport(ChaineGlobalOFReportValue report,
			RechercheMulticritereDetailSaisieValue request) {

		Long ID_ENG = 0L;
		Long ID_SCH = 0L;

		report.setDateIntroductionMin(request.getDateSaisieMin());
		report.setDateIntroductionMax(request.getDateSaisieMax());

		RechercheMulticritereCatalogueValue requestOperation = new RechercheMulticritereCatalogueValue();

		/** operation ENG */
		requestOperation.setCode(IConstante.ENG);
		ID_ENG = operationPersistance.getIdByCode(requestOperation.getCode());

		/** operation SCH */
		requestOperation.setCode(IConstante.SCH);
		ID_SCH = operationPersistance.getIdByCode(requestOperation.getCode());

		List<ChaineValue> listeChaines = new ArrayList<>();

		if (request.getChaineId() != null) {

			ChaineValue chaine = chainePersistance.rechercheChaineParId(request.getChaineId());
			listeChaines.add(chaine);

		} else {

			listeChaines = chainePersistance.listeChaine();
		}

		for (ChaineValue currentChaine : listeChaines) {

			// Map <OFId, List [ qteEng, qteSCh]>
			Map<Long, List<Long>> mapOF = new HashMap<Long, List<Long>>();

			request.setChaineId(currentChaine.getId());
			List<DetailSaisieElementValue> listeDetailsSaisie = detailSaisiePersistance
					.getListDetailsSaisieAllegee(request);

			for (DetailSaisieElementValue detailSaisieElementValue : listeDetailsSaisie) {

				if (detailSaisieElementValue.getElementGammeOFId() != null) {
					ElementGammeOfValue elementGammeOF = elementGammeOfPersistance
							.getByElementOFId(detailSaisieElementValue.getElementGammeOFId());

					if (elementGammeOF != null) {

						Long currentOperationId = elementGammeOF.getOperationId();

						// Appliquer le travail de calcul de qte sauf pour
						// opérations type Engagement ou sortie chaine

						if (currentOperationId.equals(ID_ENG) || currentOperationId.equals(ID_SCH)) {

							Long OFId = gammeOfPersistance.getOFIdByGammeId(elementGammeOF.getGammeOfId());

							/****************
							 * Opération type Engagement
							 **********************/

							if (currentOperationId.equals(ID_ENG)) {

								if (mapOF.containsKey(OFId)) {

									if (detailSaisieElementValue.getQuantite() != null) {

										List<Long> liste = mapOF.get(OFId);
										Long qteEng = liste.get(0);
										qteEng += detailSaisieElementValue.getQuantite();
										liste.set(0, qteEng);

										mapOF.remove(OFId);
										mapOF.put(OFId, liste);

									}

								}

								else {

									List<Long> liste = new ArrayList<Long>();
									liste.add(0, 0L); // qteEng
									liste.add(1, 0L); // qteSCh
									mapOF.put(OFId, liste);

								}
							} // Fermeture if (op ==ID_ENG)

							/****************
							 * Opération type Sortie Chaine
							 **********************/

							if (currentOperationId.equals(ID_SCH)) {

								if (mapOF.containsKey(OFId)) {

									if (detailSaisieElementValue.getQuantite() != null) {

										List<Long> liste = mapOF.get(OFId);
										Long qteSch = liste.get(1);
										qteSch += detailSaisieElementValue.getQuantite();
										liste.set(1, qteSch);

										mapOF.remove(OFId);
										mapOF.put(OFId, liste);

									}

								}

								else {

									List<Long> liste = new ArrayList<Long>();
									liste.add(0, 0L); // qteEng
									liste.add(1, 0L); // qteSCh
									mapOF.put(OFId, liste);

								}
							} // Fermeture if (op ==ID_SCH)

						} // Fermeture if(op == ID_SCH || op == ID_ENG)

					} // Fermeture if(gammeOf != null)
				}

			} // Fermeture boucle for listDetailsSaisis

			// remplissage des élements du rapport

			Iterator<Entry<Long, List<Long>>> it = mapOF.entrySet().iterator();
			List<ChaineGlobalOFElementReportValue> listeReportElement = new ArrayList<ChaineGlobalOFElementReportValue>();

			while (it.hasNext()) {

				Map.Entry<Long, List<Long>> pair = (Entry<Long, List<Long>>) it.next();

				ChaineGlobalOFElementReportValue reportElement = new ChaineGlobalOFElementReportValue();

				// Retourne ID/Numero/Qte/PIID/ProduitId
				OrdreFabricationValue OF = ordreFabricationPersistance.getOFWithSomeColumns(pair.getKey());

				if (OF != null) {

					reportElement.setOfNum(OF.getNumero());
					reportElement.setEngagement(pair.getValue().get(0)); // Engagement
					reportElement.setSortie(pair.getValue().get(1)); // Sortie
					reportElement.setQuantite(OF.getQuantite());
					reportElement.setReste(reportElement.getQuantite() - reportElement.getSortie());

					if (OF.getPartieInterresId() != null) {

						PartieInteresseValue client = partieInteresseePersistance.getById(OF.getPartieInterresId());

						if (client != null) {
							reportElement.setClient(client.getAbreviation());
						}
					}

					if (OF.getProduitId() != null) {

						ProduitValue produit = produitPersistance.rechercheProduitById(OF.getProduitId());

						if (produit != null) {
							reportElement.setDesignationProduit(produit.getDesignation());
							reportElement.setReferenceProduit(produit.getReference());
						}

					}

				}

				listeReportElement.add(reportElement);

			} // Fermeture mapIteration

			report.setChaineCoupe(CHAINE_COUPE);
			if (currentChaine.getDesignation().equals(CHAINE_COUPE)) {
				report.setChaineCoupeElementsList(listeReportElement);
			}

			report.setChaine1(CHAINE_1);
			if (currentChaine.getDesignation().equals(CHAINE_1)) {
				report.setChaine1ElementsList(listeReportElement);
			}

			report.setChaine2(CHAINE_2);
			if (currentChaine.getDesignation().equals(CHAINE_2)) {
				report.setChaine2ElementsList(listeReportElement);
			}

			report.setChaine3(CHAINE_3);
			if (currentChaine.getDesignation().equals(CHAINE_3)) {
				report.setChaine3ElementsList(listeReportElement);
			}

			report.setChaine4(CHAINE_4);
			if (currentChaine.getDesignation().equals(CHAINE_4)) {
				report.setChaine4ElementsList(listeReportElement);
			}

			report.setChaine5(CHAINE_5);
			if (currentChaine.getDesignation().equals(CHAINE_5)) {
				report.setChaine5ElementsList(listeReportElement);
			}

			report.setChaine6(CHAINE_6);
			if (currentChaine.getDesignation().equals(CHAINE_6)) {
				report.setChaine6ElementsList(listeReportElement);
			}

		} // Fermeture boucle for listeChaines

	}

	/****************************
	 * Rapport Jour / OF
	 ******************************/

	@Override
	public ChaineJourOFReportValue getChaineJourOFReport(RechercheMulticritereDetailSaisieValue request)
			throws IOException {

		ChaineJourOFReportValue report = new ChaineJourOFReportValue();

		report.setFileName(IConstanteReport.REPORT_NAME_CHAINE_JOUR_OF);
		report.setReportStream(new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/RapportChaine/jourOF/jour_of_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();

		params.put("CHAINE_JOUR_OF_SUB_REPORT", IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/RapportChaine/jourOF/jour_of_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		enrichirChaineJourOFReport(report, request);

		ArrayList<ChaineJourOFReportValue> dataList = new ArrayList<ChaineJourOFReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichirChaineJourOFReport(ChaineJourOFReportValue report,
			RechercheMulticritereDetailSaisieValue request) {

		// report.setDateIntroductionMin(request.getDateSaisieMin());
		// report.setDateIntroductionMax(request.getDateSaisieMax());

		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			Calendar todayCalendar = Calendar.getInstance();
			String formattedDate = dateFormat.format(todayCalendar.getTime());
			Calendar formattedTodayCalendar = dateFormat.getCalendar();

			formattedTodayCalendar.setTime(dateFormat.parse(formattedDate));

			Calendar minDate = todayCalendar;

			// Soustraire 30 jours depuis la date courante
			minDate.add(Calendar.DAY_OF_MONTH, -30);
			formattedDate = dateFormat.format(minDate.getTime());
			Calendar formattedMinDate = dateFormat.getCalendar();

			formattedMinDate.setTime(dateFormat.parse(formattedDate));

			request.setDateSaisieMin(minDate);
			request.setDateSaisieMax(todayCalendar);

			//LOGGER.info("---- Rapport Jour/OF : minDate  -----" + minDate);
			//LOGGER.info("---- Rapport Jour/OF : todayCalendar  -----" + todayCalendar);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//LOGGER.info("-----error-----");
			e.printStackTrace();
		} finally {

			Long ID_SCH = 0L;

			RechercheMulticritereCatalogueValue requestOperation = new RechercheMulticritereCatalogueValue();

			/** operation SCH */
			requestOperation.setCode(IConstante.SCH);
			ID_SCH = operationPersistance.getIdByCode(requestOperation.getCode());

			List<ChaineValue> listeChaines = new ArrayList<>();

			if (request.getChaineId() != null) {

				ChaineValue chaine = chainePersistance.rechercheChaineParId(request.getChaineId());
				listeChaines.add(chaine);

			} else {

				listeChaines = chainePersistance.listeChaine();
			}

			for (ChaineValue currentChaine : listeChaines) {

				// Map <OFId, List [ qteJour, qteCumul]>
				Map<Long, List<Long>> mapOF = new HashMap<Long, List<Long>>();

				request.setChaineId(currentChaine.getId());

				List<DetailSaisieElementValue> listeDetailsSaisie = detailSaisiePersistance
						.getListDetailsSaisieAllegee(request);

				for (DetailSaisieElementValue detailSaisieElementValue : listeDetailsSaisie) {

					if (detailSaisieElementValue.getElementGammeOFId() != null) {
						ElementGammeOfValue elementGammeOF = elementGammeOfPersistance
								.getByElementOFId(detailSaisieElementValue.getElementGammeOFId());

						if (elementGammeOF != null) {

							Long currentOperationId = elementGammeOF.getOperationId();

							// Appliquer le travail de calcul de qte sauf pour
							// opérations type Engagement ou sortie chaine

							if (currentOperationId.equals(ID_SCH)) {

								Long OFId = gammeOfPersistance.getOFIdByGammeId(elementGammeOF.getGammeOfId());

								if (mapOF.containsKey(OFId)) {

									if (detailSaisieElementValue.getQuantite() != null) {

										//LOGGER.info(
												//"---- Rapport Jour/OF : detailSaisieElementValue.getDateSaisie()  -----"
												//		+ detailSaisieElementValue.getDateSaisie());
										// Calcul qte sortie/Jour
										// Si date exist dans [dateSaisieMin,
										// dateSaisieMax]
										if (dateIsBetween(detailSaisieElementValue.getDateSaisie(),
												request.getDateSaisieMin(), request.getDateSaisieMax())) {
											List<Long> liste = mapOF.get(OFId);
											Long qteJour = liste.get(0);
											qteJour += detailSaisieElementValue.getQuantite();
											liste.set(0, qteJour);

											mapOF.remove(OFId);
											mapOF.put(OFId, liste);
										}

										List<Long> liste = mapOF.get(OFId);
										Long qteCumul = liste.get(1);
										qteCumul += detailSaisieElementValue.getQuantite();
										liste.set(1, qteCumul);

										mapOF.remove(OFId);
										mapOF.put(OFId, liste);

									}
								}

								else {

									List<Long> liste = new ArrayList<Long>();
									liste.add(0, 0L); // qteJour
									liste.add(1, 0L); // qteCumul
									mapOF.put(OFId, liste);

								}
							} // Fermeture if(op == ID_SCH )

						} // Fermeture if(gammeOf != null)
					}

				} // Fermeture boucle for listDetailsSaisis

				// remplissage des élements du rapport

				Iterator<Entry<Long, List<Long>>> it = mapOF.entrySet().iterator();
				List<ChaineJourOFElementReportValue> listeReportElement = new ArrayList<ChaineJourOFElementReportValue>();

				while (it.hasNext()) {

					Map.Entry<Long, List<Long>> pair = (Entry<Long, List<Long>>) it.next();

					ChaineJourOFElementReportValue reportElement = new ChaineJourOFElementReportValue();

					// Retourne ID/Numero/Qte/PIID/ProduitId
					OrdreFabricationValue OF = ordreFabricationPersistance.getOFWithSomeColumns(pair.getKey());

					if (OF != null) {

						reportElement.setOfNum(OF.getNumero());
						reportElement.setQuantite(OF.getQuantite());
						reportElement.setSortieJour(pair.getValue().get(0)); // qteJour
						reportElement.setSortieCumul(pair.getValue().get(1)); // qteCumul
						// TODO: Vérifier calcul reste
						reportElement.setReste(reportElement.getQuantite() - reportElement.getSortieJour());

						if (OF.getPartieInterresId() != null) {

							PartieInteresseValue client = partieInteresseePersistance.getById(OF.getPartieInterresId());

							if (client != null) {
								reportElement.setClient(client.getAbreviation());
							}
						}

						if (OF.getProduitId() != null) {

							ProduitValue produit = produitPersistance.rechercheProduitById(OF.getProduitId());

							if (produit != null) {
								reportElement.setDesignationProduit(produit.getDesignation());
								reportElement.setReferenceProduit(produit.getReference());
							}

						}

					}

					listeReportElement.add(reportElement);

				} // Fermeture mapIteration

				report.setChaineCoupe(CHAINE_COUPE);
				if (currentChaine.getDesignation().equals(CHAINE_COUPE)) {
					report.setChaineCoupeElementsList(listeReportElement);
				}

				report.setChaine1(CHAINE_1);
				if (currentChaine.getDesignation().equals(CHAINE_1)) {
					report.setChaine1ElementsList(listeReportElement);
				}

				report.setChaine2(CHAINE_2);
				if (currentChaine.getDesignation().equals(CHAINE_2)) {
					report.setChaine2ElementsList(listeReportElement);
				}

				report.setChaine3(CHAINE_3);
				if (currentChaine.getDesignation().equals(CHAINE_3)) {
					report.setChaine3ElementsList(listeReportElement);
				}

				report.setChaine4(CHAINE_4);
				if (currentChaine.getDesignation().equals(CHAINE_4)) {
					report.setChaine4ElementsList(listeReportElement);
				}

				report.setChaine5(CHAINE_5);
				if (currentChaine.getDesignation().equals(CHAINE_5)) {
					report.setChaine5ElementsList(listeReportElement);
				}

				report.setChaine6(CHAINE_6);
				if (currentChaine.getDesignation().equals(CHAINE_6)) {
					report.setChaine6ElementsList(listeReportElement);
				}

			} // Fermeture boucle for listeChaines

		}

	}

	private Boolean dateIsBetween(Calendar date, Calendar minDate, Calendar maxDate) {

		if ((date.before(maxDate) || date.equals(maxDate)) && (date.after(minDate) || date.equals(minDate))) {
			return true;
		}

		return false;
	}

	@Override
	public OperateurCompetenceReportValue getOperateurCompetenceReport(
			RechercheMulticritereOperateurReportValue request) throws IOException {

		OperateurCompetenceReportValue report = new OperateurCompetenceReportValue();

		// TODO :enrechissement des param du report
		report.setFileName(IConstanteReport.PATH_JRXML_OPERATEUR_COMPETENCE);
		report.setReportStream(new FileInputStream(
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/RapportOperateur/competence/operateur_competence_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();

		params.put("OPERATEUR_COMPETENCE_SUB_REPORT",
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/RapportOperateur/competence/operateur_competence_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		// enrichissement du rapport
		enrichirRapportOperationCompetenceReport(report, request);

		ArrayList<OperateurCompetenceReportValue> dataList = new ArrayList<OperateurCompetenceReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichirRapportOperationCompetenceReport(OperateurCompetenceReportValue report,
			RechercheMulticritereOperateurReportValue request) {

		report.setMatricule(request.getMatricule());
		//LOGGER.info("request.getMatricule()" + request.getMatricule());

		/** Critère de recherche */
		if (request.getMatricule() != null && !request.equals("")) {

			PersonnelValue personnelValue = personnelPersistance.getByMatricule(request.getMatricule());
			//LOGGER.info("** **Competence personnelValue ** ** " + personnelValue.toString());

			if (personnelValue != null) {
				report.setNomPersonnel(personnelValue.getNom());
				report.setPrenomPersonnel(personnelValue.getPrenom());
			}

		}

		report.setDateSaisieMax(request.getDateSaisieMax());
		report.setDateSaisieMin(request.getDateSaisieMin());

		/** Liste */
		RechercheMulticritereDetailSaisieValue rechercheMulticritereDetailSaisieValue = new RechercheMulticritereDetailSaisieValue();

		rechercheMulticritereDetailSaisieValue.setMatricule(request.getMatricule());
		rechercheMulticritereDetailSaisieValue.setDateSaisieMin(request.getDateSaisieMin());
		rechercheMulticritereDetailSaisieValue.setDateSaisieMax(request.getDateSaisieMax());

		Set<DetailSaisieElementValue> resultRecherche = detailSaisieDomaine
				.getListDetailsSaisieElement(rechercheMulticritereDetailSaisieValue);

		Map<Long, List<DetailSaisieElementValue>> mapDetailSaisieByOperationId = new HashMap<Long, List<DetailSaisieElementValue>>();
		Map<Long, Long> mapQteByOperationId = new HashMap<Long, Long>();

		List<OperateurCompetenceElementReportValue> operateurCompetenceElementList = new ArrayList<OperateurCompetenceElementReportValue>();
		Long qteTotale = 0L;
		for (DetailSaisieElementValue elementDetailSaisie : resultRecherche) {
			if (elementDetailSaisie != null) {
				if (elementDetailSaisie.getElementGammeOFId() != null) {

					ElementGammeOfValue elementGammeOF = elementGammeOfPersistance
							.getByElementOFId(elementDetailSaisie.getElementGammeOFId());

					if (elementGammeOF != null) {
						if (elementGammeOF.getOperationId() != null) {

							Long key = elementGammeOF.getOperationId();

							if (mapDetailSaisieByOperationId.get(key) == null) {
								mapDetailSaisieByOperationId.put(key, new ArrayList<DetailSaisieElementValue>());

							}
							/** calcule de la qte Totale de chaque Operation */
							qteTotale = qteTotale + elementDetailSaisie.getQuantite();
							/** affectation */
							elementDetailSaisie.setQuantite(qteTotale);
							/** affectation le l'objet au MapDetail */
							mapDetailSaisieByOperationId.get(key).add(elementDetailSaisie);
							/** affectation de la qteTotale au MapQte */
							mapQteByOperationId.put(key, qteTotale);

						}
					}
				}
			}

		}
		Iterator it = mapDetailSaisieByOperationId.entrySet().iterator();

		while (it.hasNext()) {

			OperateurCompetenceElementReportValue element = new OperateurCompetenceElementReportValue();

			Map.Entry<Long, List<DetailSaisieElementValue>> pair = (Entry<Long, List<DetailSaisieElementValue>>) it
					.next();
			if (pair.getValue() != null) {
				if (pair.getValue().size() > 0) {
					if (pair.getValue().get(IConstanteReport.FIRST_INDEX) != null) {
						Long codeId = pair.getKey();
						OperationValue operation = operationPersistance.getById(codeId);
						element.setCodeDesignation(operation.getDesignation());
						element.setCodeReference(operation.getCode());
						element.setTemps(operation.getTemps());
						MachineValue machineValue = machinePersistance.getById(operation.getMachineId());
						element.setMachine(machineValue.getDesignation());
						element.setQuantiteProduite(pair.getValue().get(IConstanteReport.FIRST_INDEX).getQuantite());
						operateurCompetenceElementList.add(element);
					}
				}
			}
		}

		report.setOperateurCompetenceElementList(operateurCompetenceElementList);

	}

	@Override
	public OperateurHistoriqueReportValue getOperateurHistoriqueReport(
			RechercheMulticritereOperateurReportValue request) throws IOException {
		OperateurHistoriqueReportValue report = new OperateurHistoriqueReportValue();

		//LOGGER.info("--------Domaine : Request :----"+request);
		
		// enrechissement des param du report
		report.setFileName(IConstanteReport.REPORT_NAME_F_OPERATEUR_HISTORIQUE);
		report.setReportStream(new FileInputStream(
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/RapportOperateur/historique/f_operateur_historique_report.jrxml"));
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_OPERATEUR_HISTORIQUE_LIST_PATH",
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/RapportOperateur/historique/f_operateur_historique_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		RechercheMulticritereFeuilleSaisieValue request2 = new RechercheMulticritereFeuilleSaisieValue();
		request2.setDateSaisieMax(request.getDateSaisieMax());
		request2.setDateSaisieMin(request.getDateSaisieMin());
		request2.setMatricule(request.getMatricule());
		ResultatRechecheFeuilleSaisieValue resultRecherche = feuilleSaisiePersistance.rechercherMultiCritere(request2);
		Set<ResultatRechecheFeuilleSaisieElementValue> set = new TreeSet<ResultatRechecheFeuilleSaisieElementValue>(
				resultRecherche.getList());

		// enrichissement du rapport OperateurHistorique

		enrichirOperateurHistoriqueReport(report, set, request);

		ArrayList<OperateurHistoriqueReportValue> dataList = new ArrayList<OperateurHistoriqueReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichirOperateurHistoriqueReport(OperateurHistoriqueReportValue report,
			Set<ResultatRechecheFeuilleSaisieElementValue> set, RechercheMulticritereOperateurReportValue request) {

		report.setDateSaisieMin(request.getDateSaisieMin());
		report.setDateSaisieMax(request.getDateSaisieMax());
		report.setMatricule(request.getMatricule());
		report.setMatricule(request.getMatricule());

		// Nom & Prenom
		PersonnelValue personnel = personnelPersistance.getByMatricule(request.getMatricule());
		if (personnel != null) {
			report.setMatricule(personnel.getMatricule());
			report.setNomPrenom(personnel.getNom() + " " + personnel.getPrenom());
		}

		List<OperateurHistoriqueElementReportValue> list = new ArrayList<OperateurHistoriqueElementReportValue>();
		for (ResultatRechecheFeuilleSaisieElementValue elementFicheSaisie : set) {

			OperateurHistoriqueElementReportValue element = new OperateurHistoriqueElementReportValue();

			element.setPscProd(elementFicheSaisie.getPscProd());
			element.setMinPresence(elementFicheSaisie.getMinPresence());
			element.setMinProd(elementFicheSaisie.getMinProd());
			element.setRendement(elementFicheSaisie.getRendement());
			element.setMinAleas(elementFicheSaisie.getMinAleas());

			// Désignation chaine
			if (elementFicheSaisie.getChaineId() != null) {
				element.setChaineDesignation(
						chainePersistance.rechercheDesignationChaineParId(elementFicheSaisie.getChaineId()));
			}

			element.setDateSaisie(elementFicheSaisie.getDateSaisie());
			element.setActivite(elementFicheSaisie.getActivite());
			//LOGGER.info("----** ** Rapport Historique : sub element ** **-----" + element);

			list.add(element);
		}

		report.setOperateurHistoriqueElementReportValue(list);
	}

	// Recap production chaine
	@Override
	public RecapProductionChaineReportValue getRecapProductionChaine(RechercheMulticritereFeuilleSaisieValue request)
			throws IOException {

		// FicheSaisieReporListValue report = new FicheSaisieReporListValue();
		RecapProductionChaineReportValue report = new RecapProductionChaineReportValue();
		
		// enrechissement des param du report
		report.setFileName(IConstanteReport.REPORT_RECAP_PRODUCTION_CHAINE);

		//		report.setReportStream(new FileInputStream(
//				"C://ERP/Lib/Reporting/RapportChaine/recapProdChaine/f_recap_production_chaine_report.jrxml"));

report.setReportStream(new FileInputStream(
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/RapportChaine/recapProdChaine/f_recap_production_chaine_report.jrxml"));

//		report.setReportStream(new FileInputStream("C://ERP/Lib/Reporting/RapportChaine/recapProdChaine/recap_production_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("REPORT_RECAP_PRODUCTION_CHAINE_LIST_PATH",
				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/RapportChaine/recapProdChaine/f_recap_production_chaine_sub_report.jasper");

//		// TODO : Hiba Confection, LMT sans Logo, Farouk Confection avec Logo
//		params.put(IConstanteReport.PATH_LOGO, IConstanteReport.LOGO_ENFAVET);
//
//				"C://ERP/Lib/Reporting/RapportChaine/recapProdChaine/f_recap_production_chaine_sub_report.jasper");
		// TODO : Hiba Confection, LMT sans Logo, Farouk Confection avec Logo
//		params.put(IConstanteReport.PATH_LOGO, IConstanteReport.LOGO_ENFAVET);
//
//				IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/RapportChaine/recapProdChaine/f_recap_production_chaine_sub_report.jasper");

		
		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		ResultatRechecheFeuilleSaisieValue resultRecherche = feuilleSaisiePersistance.rechercherMultiCritere(request);

		Set<ResultatRechecheFeuilleSaisieElementValue> set = new TreeSet<ResultatRechecheFeuilleSaisieElementValue>(
				resultRecherche.getList());

		// enrichissement du rapport
		enrichirRecapProductionChaineReportList(report, set, request);

		ArrayList<RecapProductionChaineReportValue> dataList = new ArrayList<RecapProductionChaineReportValue>();

		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}
	
	
	// enrichissement recap production chaine
	private void enrichirRecapProductionChaineReportList(RecapProductionChaineReportValue report,
			Set<ResultatRechecheFeuilleSaisieElementValue> set, RechercheMulticritereFeuilleSaisieValue request) {

		if (request.getChaineId() != null) {
			report.setChaineId(request.getChaineId());
			ChaineValue chaine = chainePersistance.rechercheChaineParId(request.getChaineId());
			//LOGGER.info("----** ** Rapport recap : sub chaine design ** **-----" + chaine);

			if (chaine != null) {
				report.setChaineDesignation(chaine.getDesignation());
			}
		}

		report.setDateSaisieMin(request.getDateSaisieMin());
		report.setDateSaisieMax(request.getDateSaisieMax());

		// Map<String, List<ResultatRechecheFeuilleSaisieElementValue>>
		// mapListByMatricule = new HashMap<String,
		// List<ResultatRechecheFeuilleSaisieElementValue>>();

		Map<Long, List<ResultatRechecheFeuilleSaisieElementValue>> mapListByChaineId = new HashMap<Long, List<ResultatRechecheFeuilleSaisieElementValue>>();

		List<RecapProductionChaineReportElementValue> recapProductionChaineReportElementList = new ArrayList<RecapProductionChaineReportElementValue>();
		//LOGGER.info("----** ** Rapport Historique : sub ResultatRechecheFeuilleSaisieElementValue.size() ** **-----"
			//	+ set.size());

		for (ResultatRechecheFeuilleSaisieElementValue elementFicheSaisie : set) {
			//LOGGER.info("----** ** Rapport Historique : sub elementFicheSaisie.getDateSaisie() ** **-----"
					//+ elementFicheSaisie.getDateSaisie());

			//LOGGER.info("----** ** Rapport Historique : sub elementFicheSaisie.getActivite() ** **-----"
					//+ elementFicheSaisie.getActivite());
			if (elementFicheSaisie.getChaineId() != null) {

				// String chaineIDKey =
				// Long.toString((elementFicheSaisie.getChaineId()));
				Long chaineIDKey = elementFicheSaisie.getChaineId();
				//LOGGER.info("----** ** Rapport recap : sub chaineIDKey ** **-----" + chaineIDKey);

				if (mapListByChaineId.get(chaineIDKey) == null) {
					mapListByChaineId.put(chaineIDKey, new ArrayList<ResultatRechecheFeuilleSaisieElementValue>());

				}
				mapListByChaineId.get(chaineIDKey).add(elementFicheSaisie);
			}
		}
		Iterator it = mapListByChaineId.entrySet().iterator();

		while (it.hasNext()) {

			Map.Entry<Long, List<ResultatRechecheFeuilleSaisieElementValue>> pair = (Entry<Long, List<ResultatRechecheFeuilleSaisieElementValue>>) it
					.next();

			//LOGGER.info("----** ** Rapport Historique : map pair ** **-----" + pair.getValue().size());

			RecapProductionChaineReportElementValue element = new RecapProductionChaineReportElementValue();

			Long pscProd = IConstanteReport.ZERO;
			Double rendement = IConstanteReport.ZEROD;
			Double minProd = IConstanteReport.ZEROD;
			Double minPresence = IConstanteReport.ZEROD;
			Double minAlea = IConstanteReport.ZEROD;
			Double activite = IConstanteReport.ZEROD;
			Double denominateur;

			for (ResultatRechecheFeuilleSaisieElementValue elmFeuilleSaisie : pair.getValue()) {
				//LOGGER.info("----** ** Rapport recap : SUM elmFeuilleSaisie.getPscProd() ** **-----"
						//+ elmFeuilleSaisie.getPscProd());

				/** Nbr Pieces */
				pscProd = pscProd + ((elmFeuilleSaisie.getPscProd() != null) ? elmFeuilleSaisie.getPscProd() : 0);

				/** minPresence */
				minPresence = minPresence
						+ ((elmFeuilleSaisie.getMinPresence() != null) ? elmFeuilleSaisie.getMinPresence() : 0);

				/** minProd */
				minProd = minProd + ((elmFeuilleSaisie.getMinProd() != null) ? elmFeuilleSaisie.getMinProd() : 0);

				/** minAleas */
				minAlea = minAlea + ((elmFeuilleSaisie.getMinAleas() != null) ? elmFeuilleSaisie.getMinAleas() : 0);

			}

			/** rendement */
			if (minPresence != 0) {
				rendement = (minProd / minPresence) * 100;
			}

			/** activite */
			if (minAlea >= 0) {
				denominateur = minPresence - minAlea;

				if (denominateur != null) {

					activite = (minProd / denominateur) * 100;
				}
			}
			// Désignation chaine
			if (pair.getKey() != null) {
				
				element.setChaineDesignation(chainePersistance.rechercheDesignationChaineParId(pair.getKey()));
			}

			element.setPscProd(pscProd);
			element.setMinPresence(minPresence);
			element.setMinProd(minProd);
			element.setRendement(rendement);
			element.setMinAleas(minAlea);
			element.setActivite(activite);
			//LOGGER.info("----** ** Rapport recap : sub element ** **-----" + element);

			recapProductionChaineReportElementList.add(element);

		}
		//LOGGER.info("----** ** Rapport recap : sub recapProductionChaineReportElementValueList ** **-----size=="
				//+ recapProductionChaineReportElementList.size());

		// Collections.sort(recapProductionChaineReportElementValueList);

		// report.setRecapProductionChaineReportElementValueList(new
		// TreeSet<>(recapProductionChaineReportElementValueList));

		report.setRecapProductionChaineReportElementValueList(recapProductionChaineReportElementList);
		//LOGGER.info(
				//"----** ** Rapport recap : sub report.getRecapProductionChaineReportElementValueList().get(0).getMinProd() ** **-----size=="
					//	+ report.getRecapProductionChaineReportElementValueList().get(0).getMinProd());

	}

	//Hajer Amri 
	/*-----------------------------------------Amchart GPAO Report-------------------------------------------------------------*/
	
	// Par Client
		@Override
		public RepartitionQteParClientReportValue getRepartitionQteParClientReport(RechercheMulticritereOrdreFabricationValue request)
				throws IOException {
			
			
			//System.out.println("------------Domaine : getRepartitionQteParClientReport---------------------");

			RepartitionQteParClientReportValue report = new RepartitionQteParClientReportValue();

			// enrechissement des param du report
			report.setFileName(IConstanteReport.REPORT_QTE_PAR_CLIENT);
			report.setReportStream(new FileInputStream("C://ERP/Lib/gpao/chartReport/parClient/repartition_Qte_par_client_report.jrxml"));

			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("REPORT_QTE_PAR_CLIENT_LIST_PATH",
					"C://ERP/Lib/gpao/chartReport/parClient/repartition_Qte_par_client_sub_report.jasper");
			
			// TODO : Hiba Confection, LMT sans Logo, Farouk Confection avec Logo
			params.put(IConstanteReport.PATH_LOGO, IConstanteReport.LOGO_ENFAVET);

			report.setParams(params);

			report.setDateMin(request.getvDateIntroductionDu());
			report.setDateMax(request.getvDateIntroductionAu());

			
			ResultatMulticritereOrdreFabricationValue result = ordreFabricationPersistance.rechercherOrdreFabricationMultiCritere(request);
			
			 Map<Long, List<OrdreFabricationValue>> mapQteByClient = new HashMap<Long, List<OrdreFabricationValue>>();

			 //subReport			
			 List<RepartitionQteParClientElementValue> ListSubElt = new ArrayList<RepartitionQteParClientElementValue>();
			 
			 if (result != null) {		
			 for (OrdreFabricationValue ordreFabrication : result.getOrdreFabricationValues()) {	
				 if (ordreFabrication.getPartieInterresId() != null) {
			
			 
			 					Long clientIDKey = ordreFabrication.getPartieInterresId();
			 
			 					if (mapQteByClient.get(clientIDKey) == null) {
			 						mapQteByClient.put(clientIDKey, new ArrayList<OrdreFabricationValue>());
			 
			 					}
			 					mapQteByClient.get(clientIDKey).add(ordreFabrication);
			 				}
			 			}
			 
			Iterator it = mapQteByClient.entrySet().iterator();

			while (it.hasNext()) {

				Map.Entry<Long, List<OrdreFabricationValue>> pair = (Entry<Long, List<OrdreFabricationValue>>) it
						.next();


				RepartitionQteParClientElementValue element = new RepartitionQteParClientElementValue();

				Long quantite = IConstanteReport.ZERO;
				

				for (OrdreFabricationValue elmOrdreFabricationValue : pair.getValue()) {

					
					if( (elmOrdreFabricationValue.getPartieInterresId()!= null) && (elmOrdreFabricationValue.getQuantite() != null)) {
						/** Quantite */
						quantite = quantite + ((elmOrdreFabricationValue.getQuantite() != null) ? elmOrdreFabricationValue.getQuantite() : 0);
							
							PartieInteresseValue client = partieInteresseePersistance.getById(elmOrdreFabricationValue.getPartieInterresId());
							element.setAbreviationClient(client.getAbreviation());
							element.setQuantite(quantite);
							
						}
				}
				
				ListSubElt.add(element);

				}
			
			
			 }//fin result!=null
			
			
			report.setQteParClientReportElementValueList(ListSubElt);

			ArrayList<RepartitionQteParClientReportValue> dataList = new ArrayList<RepartitionQteParClientReportValue>();

			dataList.add(report);

			JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

			report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

			return report;
		}
		
		
		 /*------------------------------------------------Par Produit------------------------------------------------------------*/
				@Override
				public RepartitionQteParClientReportValue getRepartitionQteParProduitReport(RechercheMulticritereOrdreFabricationValue request)
						throws IOException {
					
					
					//System.out.println("------------Domaine : getRepartitionQteParProduitReport---------------------");

					RepartitionQteParClientReportValue report = new RepartitionQteParClientReportValue();

					// Enrichissement des param du report
					report.setFileName(IConstanteReport.REPORT_QTE_PAR_CLIENT);
					report.setReportStream(new FileInputStream("C://ERP/Lib/gpao/chartReport/parProduit/repartition_Qte_par_produit_report.jrxml"));

					HashMap<String, Object> params = new HashMap<String, Object>();
					params.put("REPORT_QTE_PAR_CLIENT_LIST_PATH",
							"C://ERP/Lib/gpao/chartReport/parProduit/repartition_Qte_par_produit_sub_report.jasper");
					
					// TODO : Hiba Confection, LMT sans Logo, Farouk Confection avec Logo
					params.put(IConstanteReport.PATH_LOGO, IConstanteReport.LOGO_ENFAVET);

					report.setParams(params);

					report.setDateMin(request.getvDateIntroductionDu());
					report.setDateMax(request.getvDateIntroductionAu());
					
					if (request.getClientId()!= null) {
						PartieInteresseValue client = partieInteresseePersistance.getById(request.getClientId());
						report.setAbreviationClient(client.getAbreviation());
					}

					ResultatMulticritereOrdreFabricationValue result = ordreFabricationPersistance.rechercherOrdreFabricationMultiCritere(request);
					
					 Map<Long, List<OrdreFabricationValue>> mapQteByProduit = new HashMap<Long, List<OrdreFabricationValue>>();

					 //subReport			
					 List<RepartitionQteParClientElementValue> ListSubElt = new ArrayList<RepartitionQteParClientElementValue>();
					 
					 if (result != null) {	
						 
					 for (OrdreFabricationValue ordreFabrication : result.getOrdreFabricationValues()) {	
						 if (ordreFabrication.getPartieInterresId() != null) {
					
					 
					 					Long produitIDKey = ordreFabrication.getProduitId();
					 
					 					if (mapQteByProduit.get(produitIDKey) == null) {
					 						mapQteByProduit.put(produitIDKey, new ArrayList<OrdreFabricationValue>());
					 
					 					}
					 					mapQteByProduit.get(produitIDKey).add(ordreFabrication);
					 				}
					 			}
					 
					Iterator it = mapQteByProduit.entrySet().iterator();

					while (it.hasNext()) {

						Map.Entry<Long, List<OrdreFabricationValue>> pair = (Entry<Long, List<OrdreFabricationValue>>) it
								.next();


						RepartitionQteParClientElementValue element = new RepartitionQteParClientElementValue();

						Long quantite = IConstanteReport.ZERO;
						
						for (OrdreFabricationValue elmOrdreFabricationValue : pair.getValue()) {

							if( (elmOrdreFabricationValue.getProduitId()!= null) && (elmOrdreFabricationValue.getQuantite() != null)) {
								/** Quantite */
								quantite = quantite + ((elmOrdreFabricationValue.getQuantite() != null) ? elmOrdreFabricationValue.getQuantite() : 0);
									
									ProduitValue produit = produitPersistance.rechercheProduitById(elmOrdreFabricationValue.getProduitId());
									
									//designation produit
									element.setDesignationProduit(produit.getDesignation());
									
									//reference produit
									element.setReferenceProduit(produit.getReference());
									element.setQuantite(quantite);
									
								}
						}
						
						ListSubElt.add(element);

						}
					
					 }
					
					report.setQteParClientReportElementValueList(ListSubElt);

					ArrayList<RepartitionQteParClientReportValue> dataList = new ArrayList<RepartitionQteParClientReportValue>();

					dataList.add(report);

					JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

					report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

					return report;
				}
		
		
		
				/*------------------------------------------------Par Sous Famille------------------------------------------------------------*/
				@Override
				public RepartitionQteParClientReportValue getRepartitionQteParSFamilleReport(RechercheMulticritereOrdreFabricationValue request)
						throws IOException {
					
					
					//System.out.println("------------Domaine : getRepartitionQteParSFamilleReport---------------------");

					RepartitionQteParClientReportValue report = new RepartitionQteParClientReportValue();

					// enrechissement des param du report
					report.setFileName(IConstanteReport.REPORT_QTE_PAR_CLIENT);
					report.setReportStream(new FileInputStream("C://ERP/Lib/gpao/chartReport/parSFamille/repartition_Qte_par_sfamille_report.jrxml"));

					HashMap<String, Object> params = new HashMap<String, Object>();
					params.put("REPORT_QTE_PAR_CLIENT_LIST_PATH",
							"C://ERP/Lib/gpao/chartReport/parSFamille/repartition_Qte_par_sfamille_sub_report.jasper");
					
					// TODO : Hiba Confection, LMT sans Logo, Farouk Confection avec Logo
					params.put(IConstanteReport.PATH_LOGO, IConstanteReport.LOGO_ENFAVET);

					report.setParams(params);

					report.setDateMin(request.getvDateIntroductionDu());
					report.setDateMax(request.getvDateIntroductionAu());
					
					if (request.getClientId()!= null) {
						PartieInteresseValue client = partieInteresseePersistance.getById(request.getClientId());
						report.setAbreviationClient(client.getAbreviation());
					}

					ResultatMulticritereOrdreFabricationValue result = ordreFabricationPersistance.rechercherOrdreFabricationMultiCritere(request);
					

					//Sous famille designation
						
					 List<SousFamilleProduitValue> listSousFamilleProduit = sousFamilleProduitPersistance.listeSousFamilleProduit();
						Map<Long, String> mapSousFamilleProduit = new HashMap<Long, String>();
						for (SousFamilleProduitValue sousFamilleProduit : listSousFamilleProduit) {
							Long key = sousFamilleProduit.getId();
							if (mapSousFamilleProduit.get(key) == null) {
								mapSousFamilleProduit.put(sousFamilleProduit.getId(), sousFamilleProduit.getDesignation());
							}
						}
					
					
					 Map<String, List<OrdreFabricationValue>> mapQteBySFamille = new HashMap<String, List<OrdreFabricationValue>>();

					 //subReport			
					 List<RepartitionQteParClientElementValue> ListSubElt = new ArrayList<RepartitionQteParClientElementValue>();
					 
					
					 List<OrdreFabricationValue> newResult = new ArrayList<OrdreFabricationValue>();

					 if (result != null) {	
						 
					 for (OrdreFabricationValue ordreFabrication : result.getOrdreFabricationValues()) {

						
							ProduitValue produit = produitPersistance.rechercheProduitById(ordreFabrication.getProduitId());


							if (mapSousFamilleProduit.containsKey(produit.getSousFamilleId())) {
								ordreFabrication.setProduitSousFamilleDesignation(
										mapSousFamilleProduit.get(produit.getSousFamilleId()));
								
							}
							

							newResult.add(ordreFabrication);
							
					 }
		 					
		 					
					for (OrdreFabricationValue ordreFabricationElt : newResult) {

						 if (ordreFabricationElt.getProduitSousFamilleDesignation()!=null){
					
							 String sfamilleIDKey = ordreFabricationElt.getProduitSousFamilleDesignation();
					 
					 					if (mapQteBySFamille.get(sfamilleIDKey) == null) {
					 						mapQteBySFamille.put(sfamilleIDKey, new ArrayList<OrdreFabricationValue>());
					 
					 					}
					 					mapQteBySFamille.get(sfamilleIDKey).add(ordreFabricationElt);
					 				}
					 			}
					
					
					 
					Iterator it = mapQteBySFamille.entrySet().iterator();

					while (it.hasNext()) {

						Map.Entry<String, List<OrdreFabricationValue>> pair = (Entry<String, List<OrdreFabricationValue>>) it
								.next();


						RepartitionQteParClientElementValue element = new RepartitionQteParClientElementValue();

						Long quantite = IConstanteReport.ZERO;
						
						for (OrdreFabricationValue elmOrdreFabricationValue : pair.getValue()) {

							if(elmOrdreFabricationValue.getProduitSousFamilleDesignation()!=null); {

								
								/** Quantite */
								quantite = quantite + ((elmOrdreFabricationValue.getQuantite() != null) ? elmOrdreFabricationValue.getQuantite() : 0);
									
									element.setProduitSousFamilleDesignation(elmOrdreFabricationValue.getProduitSousFamilleDesignation());
									element.setQuantite(quantite);
									
								}
						}
						
						ListSubElt.add(element);

						}
					
					 }
					
					
					report.setQteParClientReportElementValueList(ListSubElt);

					ArrayList<RepartitionQteParClientReportValue> dataList = new ArrayList<RepartitionQteParClientReportValue>();

					dataList.add(report);

					JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

					report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

					return report;
				}
				
				private void enrechissementFicheSuiveuseInverseReport(FicheSuiveuseReportValue report, Long ordreFabricationId,
						List<Long> paquetsList, List<Long> operationsList) {

					FicheSuiveuseVue value = ficheSuiveuseDomaine.getByOrdreFabricationId(ordreFabricationId);

					if (value != null) {

						String numOrdreFabrication = null;
						Long idOrdreFabrication = null;

						String produitDesignation = null;
						String produitReference = null;
						String clientAbreviation = null;
						String clientReference = null;

						OrdreFabricationValue ordreFabrication = ordreFabricationPersistance
								.rechercheOrdreFabricationParId(ordreFabricationId);

						if (ordreFabrication != null) {

							numOrdreFabrication = ordreFabrication.getNumero();
							idOrdreFabrication = ordreFabrication.getId();

							if (ordreFabrication.getProduitId() != null) {

								ProduitValue produit = produitPersistance.rechercheProduitById(ordreFabrication.getProduitId());

								if (produit != null) {
									produitDesignation = produit.getDesignation();
									produitReference = produit.getReference();
								}

							}

							if (ordreFabrication.getPartieInterresId() != null) {

								PartieInteresseValue client = partieInteresseePersistance
										.getById(ordreFabrication.getPartieInterresId());

								if (client != null) {
									clientAbreviation = client.getAbreviation();
									clientReference = client.getReference();
								}

							}

						}

						List<CouleurValue> listCouleur = couleurPersistance.listeCouleur();
						Map<Long, String> mapCouleurIdDesignation = new HashMap<Long, String>();
						for (CouleurValue couleur : listCouleur) {
							mapCouleurIdDesignation.put(couleur.getId(), couleur.getDesignation());
						}

						List<TailleValue> listTaille = taillePersistance.listeTaille();
						Map<Long, String> mapTailleIdDesignation = new HashMap<Long, String>();
						for (TailleValue taille : listTaille) {
							mapTailleIdDesignation.put(taille.getId(), taille.getDesignation());
						}

						List<FicheSuiveuseElementReportValue> elementsList = new ArrayList<FicheSuiveuseElementReportValue>();

						List<FicheSuiveuseElementReportValue> leftElementsList = new ArrayList<FicheSuiveuseElementReportValue>();
						List<FicheSuiveuseElementReportValue> rightElementsList = new ArrayList<FicheSuiveuseElementReportValue>();

						Long quantiteTotal = IConstanteReport.ZERO;
						for (OperationValue operation : value.getOperationsList()) {
							
							

									if (operationsList.contains(operation.getId())) {
										FicheSuiveuseElementReportValue headerElement = new FicheSuiveuseElementReportValue();
										String tempBareCode = createBareCodePattern(idOrdreFabrication,0L,operation.getOrdreElementGammeOf());
										String tempbareCodeCompare=createBareCodePattern(idOrdreFabrication ,
												operation.getOrdreElementGammeOf(),0L);
										headerElement.setBareCode(tempBareCode);
                                        headerElement.setBareCodeForCompare(tempbareCodeCompare);
										headerElement.setProduitDesignation(produitDesignation);
										headerElement.setProduitReference(produitReference);
										headerElement.setClientAbreviation(clientAbreviation);
										headerElement.setClientReference(clientReference);

									
										headerElement.setNumOrdreFabrication(numOrdreFabrication);
										//headerElement.setQuantite(paquet.getQuantite());

										//quantiteTotal = quantiteTotal + paquet.getQuantite();

										    headerElement.setCouleurDesignation(operation.getDesignation());
											headerElement.setNomElementGamme(operation.getDesignation());
										    headerElement.setOrdreElementGammeOf(operation.getOrdreElementGammeOf());
										    headerElement.setTailleDesignation(operation.getPdh()+"");
										    headerElement.setNumPaquet(0L);
										    headerElement.setQuantite(0L);
											//}
                                            
										

										headerElement.setHeaderElement(true);
										elementsList.add(headerElement);
										
										
										for (PaquetValue paquet : value.getPaquetsList()) {
											FicheSuiveuseElementReportValue bodyElement = new FicheSuiveuseElementReportValue();
											if (paquetsList.contains(paquet.getId())) {

												


										String bareCode = createBareCodePattern(idOrdreFabrication, paquet.getOrdre(),
												operation.getOrdreElementGammeOf());
										
										String bareCodeCompare=createBareCodePattern(idOrdreFabrication ,
												operation.getOrdreElementGammeOf(),paquet.getOrdre());

										bodyElement.setProduitDesignation(produitDesignation);
										bodyElement.setProduitReference(produitReference);
										bodyElement.setClientAbreviation(clientAbreviation);
										bodyElement.setClientReference(clientReference);
										if (paquet.getTailleId() != null && mapTailleIdDesignation.containsKey(paquet.getTailleId()))
										bodyElement.setTailleDesignation(mapTailleIdDesignation.get(paquet.getTailleId()));
										if (paquet.getCouleurId() != null && mapCouleurIdDesignation.containsKey(paquet.getCouleurId()))
										bodyElement.setCouleurDesignation(mapCouleurIdDesignation.get(paquet.getCouleurId()));

										bodyElement.setNumOrdreFabrication(numOrdreFabrication);
										bodyElement.setQuantitePaquets(paquet.getQuantite());
										bodyElement.setTemps(operation.getTemps());
										bodyElement.setNumPaquet(paquet.getNum());
										bodyElement.setPdh(operation.getPdh());
										bodyElement.setNomElementGamme(operation.getDesignation());

										bodyElement.setBareCode(bareCode);
										bodyElement.setBareCodeForCompare(bareCodeCompare);
										bodyElement.setOrdreElementGammeOf(operation.getOrdreElementGammeOf());

										bodyElement.setHeaderElement(false);
										elementsList.add(bodyElement);

									}
								}
							}
						}

						Collections.sort(elementsList);
						report.setElementsList(new TreeSet<>(elementsList));

						String bareCode = "";
                        String bareCodeCompare="";
						if (paquetsList.size() % 2 == 0) {

							for (int i = 0; i < elementsList.size(); i++) {

								elementsList.get(i).setEmptyElement(false);

								leftElementsList.add(elementsList.get(i));

								if (elementsList.get(i).getHeaderElement()) {

									rightElementsList.add(elementsList.get(i));
								} else {

									i = i + 1;

									rightElementsList.add(elementsList.get(i));
								}
							}

						} else {

							boolean lastElementForTheCurrentPaquet = false;
							int nbrOperations = paquetsList.size();
							int indexLastElement = nbrOperations + 1;

							for (int i = 1; i <= elementsList.size(); i++) {

								bareCodeCompare = elementsList.get(i - 1).getBareCodeForCompare();

								if (i == indexLastElement) {

									indexLastElement = indexLastElement + nbrOperations + 1;

									lastElementForTheCurrentPaquet = true;
								} else {

									lastElementForTheCurrentPaquet = false;
								}

								leftElementsList.add(elementsList.get(i - 1));

								if (lastElementForTheCurrentPaquet) {

									FicheSuiveuseElementReportValue element = new FicheSuiveuseElementReportValue();

									element.setEmptyElement(true);
									element.setHeaderElement(false);
									element.setBareCode(bareCode + "1");
									element.setBareCodeForCompare(bareCodeCompare + "1");
									rightElementsList.add(element);
								} else {

									if (elementsList.get(i - 1).getHeaderElement()) {

										rightElementsList.add(elementsList.get(i - 1));
									} else {

										i = i + 1;

										if (i <= elementsList.size()) {

											rightElementsList.add(elementsList.get(i - 1));
										}

									}

								}

							}
						}

						Collections.sort(leftElementsList);
						Collections.sort(rightElementsList);

						report.setLeftElementsList(new TreeSet<>(leftElementsList));
						report.setRightElementsList(new TreeSet<>(rightElementsList));

					}

				}
				
				
				
				
				
				
				/************************************ Rapport Alea ****************************/
				
				@Override
				public AleaReportValue getAleaReport(RechercheMulticritereAleaValue request)
						throws IOException {

					// FicheSaisieReporListValue report = new FicheSaisieReporListValue();
					AleaReportValue report = new AleaReportValue();
					
					// enrechissement des param du report
					report.setFileName(IConstanteReport.REPORT_ALEA);


			report.setReportStream(new FileInputStream(
							IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/RapportAlea/alea_report.jrxml"));
			
					HashMap<String, Object> params = new HashMap<String, Object>();
					params.put("REPORT_ALEA_LIST_PATH",
							IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/RapportAlea/alea_sub_report.jasper");

					String logo = baseInfoPersistance.getLogo();
					params.put("PATH_LOGO", logo);
					
					report.setParams(params);

					RechercheMulticritereFeuilleSaisieValue requestFS = new RechercheMulticritereFeuilleSaisieValue();
					requestFS.setDateSaisieMax(request.getDateSaisieMax());
					requestFS.setDateSaisieMin(request.getDateSaisieMin());
					requestFS.setMatricule(request.getMatricule());
					requestFS.setChaineId(request.getChaineId());
					
					ResultatRechecheFeuilleSaisieValue resultRecherche = feuilleSaisiePersistance.rechercherMultiCritere(requestFS);

					Set<ResultatRechecheFeuilleSaisieElementValue> set = new TreeSet<ResultatRechecheFeuilleSaisieElementValue>(
							resultRecherche.getList());

					// enrichissement du rapport
					enrichirAleaReportList(report, set, request);

					ArrayList<AleaReportValue> dataList = new ArrayList<AleaReportValue>();

					dataList.add(report);

					JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

					report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

					return report;
					
				}
				
				
				// enrichissement recap production chaine
				private void enrichirAleaReportList(AleaReportValue report,
						Set<ResultatRechecheFeuilleSaisieElementValue> set, RechercheMulticritereAleaValue request) {

					if (request.getChaineId() != null) {
						ChaineValue chaine = chainePersistance.rechercheChaineParId(request.getChaineId());

						if (chaine != null) {
							report.setChaineDesignation(chaine.getDesignation());
						}
					}
					
					if (request.getAleaId() != null) {
						AleasValue alea = aleaPersistance.getById(request.getAleaId());

						if (alea != null) {
							report.setAlea(alea.getDesignation());
						}
					}

					report.setDateSaisieMin(request.getDateSaisieMin());
					report.setDateSaisieMax(request.getDateSaisieMax());
					report.setMatricule(request.getMatricule());
					
					List<AleaReportElementValue> subReport = new ArrayList<AleaReportElementValue>();

					for (ResultatRechecheFeuilleSaisieElementValue element : set) {
						
						List<ElementAleasValue> result = this.elementAleaPersistance.rechercheParFeuilleEtId(request.getAleaId(), element.getId());
							
						for (ElementAleasValue elementAleasValue : result) {
							AleaReportElementValue e = new AleaReportElementValue();
							
							AleasValue aleaValue= new AleasValue();
							
							if(elementAleasValue.getAleasId() != null)
								 aleaValue = this.aleaPersistance.getById(elementAleasValue.getAleasId());
							
							if(element.getChaineId() != null){
								e.setAlea(aleaValue.getDesignation());
							}
							
							element.setChaineDesignation(report.getChaineDesignation());
							
							if (element.getChaineId() != null) {
								ChaineValue chaine = chainePersistance.rechercheChaineParId(element.getChaineId());

								if (chaine != null) {
									e.setChaineDesignation(chaine.getDesignation());
								}
							}
							
							e.setDateSaisie(element.getDateSaisie());
							e.setDuree(elementAleasValue.getDuree());
							e.setMatricule(element.getPersonnelMatricule());
							e.setNomPrenom(element.getNomPrenom());
							
							subReport.add(e);
							
						}
					}

					report.setAleaReportElementValueList(subReport);

				}
				// enrichisement de production 
				// ajouter par etteieb hamdi le 15/09/201
				@Override
				public ProductionReportValue getProductionReportValue(RechercheMulticritereDetailSaisieValue request) throws IOException {

					ProductionReportValue report = new ProductionReportValue();

					// enrechissement des param du report
					report.setFileName(IConstanteReport.REPORT_NAME_PRODUCTION);
					report.setReportStream(new FileInputStream(IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/Production/All_Production_report.jrxml"));

					HashMap<String, Object> params = new HashMap<String, Object>();
					params.put("SUBREPORT_PRODUCTION_PATH", IConstanteReport.RAPPORTS_GPAO_BASE_URL+ "Reporting/Production/All_Production_sub_report.jasper");

					String logo = baseInfoPersistance.getLogo();
					params.put("PATH_LOGO", logo);
			
					report.setParams(params);
			//debut traitement
					
					if (request.getProduitId() != null) {
						
						ProduitValue produit=produitPersistance.rechercheProduitById(request.getProduitId());
						report.setProduit(produit.getDesignation());
					
					}
	                 if (request.getClientId() != null) {
						
						PartieInteresseValue pi=partieInteresseePersistance.recherchePartieInteresseeParId(request.getClientId());
						report.setClientDesignation(pi.getRaisonSociale());
					
					}
	                 
	                 if (request.getOperation() != null) {
							
							OperationValue op=operationPersistance.getById(request.getOperation());
							report.setPointDesignation(op.getDesignation());
						
						}
					
	                 if (request.getChaineId()!=null){
	                	 
	                	 ChaineValue chaine =chainePersistance.rechercheChaineParId(request.getChaineId());
	                	 report.setChaine(chaine.getDesignation());
	                	 
	                	 
	                	 
	                 }
	                 
	                 
	                 
	                 
	                report.setDateDe(request.getDateSaisieMin());
					report.setDateA(request.getDateSaisieMax());
					
					
					
					RechercheMulticritereDetailSaisieValue requestSaisie=new RechercheMulticritereDetailSaisieValue();
					requestSaisie.setDateSaisieMax(request.getDateSaisieMax());
					requestSaisie.setDateSaisieMin(request.getDateSaisieMin());
					requestSaisie.setProduitId(request.getProduitId());
					requestSaisie.setChaineId(request.getChaineId());
					requestSaisie.setClientId(request.getClientId());
					requestSaisie.setOperation(request.getOperation());
					
					
					List<Long> listIdOF=new ArrayList<Long>();
					
					listIdOF=detailSaisiePersistance.rechercherMultiCritereOF(requestSaisie);
					
					
						// enrichissement du rapport
					if (listIdOF != null) {
						
						if(listIdOF.size()>0)
						enrichmentProductionReport(report, listIdOF ,  request);
					}
					
					
					ArrayList<ProductionReportValue> dataList = new ArrayList<ProductionReportValue>();
					dataList.add(report);

				    JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

			        report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

				return report;
				}

				private void enrichmentProductionReport(ProductionReportValue report,
						List<Long> resultRecherche , RechercheMulticritereDetailSaisieValue request) {

					List<ProductionElementReportValue> listeProductionOF = new ArrayList<ProductionElementReportValue>();
					
					Long qteProduite = 0l;
					for (Long idOF : resultRecherche) {
						
						OrdreFabricationValue vOFValue =ordreFabricationPersistance.rechercheOrdreFabricationParId(idOF);
						
						
						ProductionElementReportValue vProductionValue = new ProductionElementReportValue();
						Long vQteJour = 0L ;
						Long vQteCumul = 0L;
						vQteJour=ordreFabricationPersistance.calculeQteProduiteJours(vOFValue.getId(), request.getDateSaisieMin(), request.getDateSaisieMax(), request.getOperation());
					  
						if(vQteJour != null)
							qteProduite += vQteJour;
						
						vProductionValue.setJour(vQteJour);
					    vQteCumul= ordreFabricationPersistance.CalculeQteProduiteCumul(vOFValue.getId(), request.getOperation()) ;
						Long vQteEclate=0L;
						
						ProduitValue vProduit=produitPersistance.rechercheProduitById(vOFValue.getProduitId(), true);
						vProductionValue.setDesignation(vProduit.getDesignation());
						vProductionValue.setReference(vProduit.getReference());
						vProductionValue.setDemande(vOFValue.getQuantite());
						vProductionValue.setoFId(vOFValue.getNumero());
						List<FicheEclatementValue> vListFicheValue = ficheEclatementPersistance.getByOrdreFabricationId(vOFValue.getId());
						if(vListFicheValue!=null && vListFicheValue.size()>0&&vListFicheValue.get(0).getQuantiteEclate()!=null)
							vQteEclate=vListFicheValue.get(0).getQuantiteEclate();
						vProductionValue.setEclatee(vQteEclate);
						vProductionValue.setCumule(vQteCumul);
						//System.out.println("##### QTE ECLATEE :  "+vProductionValue.getEclatee());
						//System.out.println("######  CUMUL  :  "+vProductionValue.getCumule());
						
						vProductionValue.setRest(vProductionValue.getEclatee()-vProductionValue.getCumule());
						listeProductionOF.add(vProductionValue);
					}

					Collections.sort(listeProductionOF);
                    report.setListProduction(listeProductionOF);
                    report.setQteProduite(qteProduite);

				}

				@Override
				public FicheColisReportValue getFicheDepartageColisReport(Long id) throws IOException {
					// TODO Auto-generated method stub
					FicheColisReportValue report = new FicheColisReportValue();

					report.setFileName(IConstanteReport.REPORT_NAME_COLIS);
					report.setReportStream(new FileInputStream(
							IConstanteReport.RAPPORTS_GPAO_BASE_URL + "/Reporting/Colisage/fiche_colis2.jrxml"));

					HashMap<String, Object> params = new HashMap<String, Object>();

					String logo = baseInfoPersistance.getLogo();
					params.put("PATH_LOGO", logo);

					report.setParams(params);
					FicheColisageValue result = ficheColisagePersistance.getById(id);
					report.setNumOrdreFabrication(result.getOrdreFabricationNumero());
					report.setProduitReference(result.getProduitReference());
//								report.setPoidBrut(report.getPoidBrut());
//								report.setPoidNet(result.getp);
					List<ColisValue> list = new ArrayList<ColisValue>();
					for (ColisValue det : result.getListColis()) {
						det.setProduitReference(result.getProduitReference());
						det.setOrdreNumero(result.getOrdreFabricationNumero());
						det.setProduitDesignation(result.getProduitDesignation());
						det.setNombreCartons(result.getNombreColis());
						det.setCouleurDesignation(result.getCouleur());
						String size = det.getTailleDesignation() + "/" + det.getQuantiteDesignation() + "pcs";
						det.setSize(size);
						String carton = det.getOrdreFiche() + "/" + det.getNombreCartons();
						det.setCarton(carton);

						list.add(det);
					}

					report.setColisList(list);
					ArrayList<FicheColisReportValue> dataList = new ArrayList<FicheColisReportValue>();
					dataList.add(report);

					JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
					report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

					return report;
				}

				@Override
				public FicheColisReportValue getColisReport(Long id) throws IOException {
					// TODO Auto-generated method stub
					FicheColisReportValue report = new FicheColisReportValue();

					report.setFileName(IConstanteReport.REPORT_NAME_COLIS);
					report.setReportStream(new FileInputStream(
							IConstanteReport.RAPPORTS_GPAO_BASE_URL + "/Reporting/Colisage/fiche_departage_colis.jrxml"));

					HashMap<String, Object> params = new HashMap<String, Object>();

					String logo = baseInfoPersistance.getLogo();
					params.put("PATH_LOGO", logo);

					report.setParams(params);
					FicheColisageValue result = ficheColisagePersistance.getById(id);
					report.setNumOrdreFabrication(result.getOrdreFabricationNumero());
					report.setProduitReference(result.getProduitReference());
//								report.setPoidBrut(report.getPoidBrut());
//								report.setPoidNet(result.getp);
					List<ColisValue> list = new ArrayList<ColisValue>();
					for (ColisValue det : result.getListColis()) {
						det.setProduitReference(result.getProduitReference());
						det.setOrdreNumero(result.getOrdreFabricationNumero());
						det.setProduitDesignation(result.getProduitDesignation());
						det.setNombreCartons(result.getNombreColis());
						det.setCouleurDesignation(result.getCouleur());
						String size = det.getTailleDesignation() + "/" + det.getQuantiteDesignation() + "pcs";
						det.setSize(size);
						String carton = det.getOrdreFiche() + "/" + det.getNombreCartons();
						det.setCarton(carton);

						list.add(det);
					}

					report.setColisList(list);
					ArrayList<FicheColisReportValue> dataList = new ArrayList<FicheColisReportValue>();
					dataList.add(report);

					JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
					report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

					return report;
				}
				
				
				
				
	

}
