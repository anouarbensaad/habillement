package com.gpro.consulting.tiers.gpao.service.report;

import java.io.IOException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.abc.value.RechercheMulticritereABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.coordination.aleas.RechercheMulticritereAleaValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.RechercheMulticritereDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticritereFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.FicheColisReportValue;
import com.gpro.consulting.tiers.gpao.coordination.fichedepartage.report.value.FicheDepartageReportValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.RechercheMulticritereFicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.fichepaquets.report.value.FichePaquetsReportValue;
import com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.report.value.FicheSuiveuseReportValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.RechercheMulticritereGammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.RechercheMulticritereGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.RechercheMulticritereCatalogueValue;
import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.RecapProductionReportValue;
import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.RechercheMulticritereRecapProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.report.ABCArticleDetailEtapeJourReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.AvancementOFReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.FicheEclatementReportListValue;
import com.gpro.consulting.tiers.gpao.coordination.report.FicheSaisieReporListValue;
import com.gpro.consulting.tiers.gpao.coordination.report.FicheSaisieReportSetValue;
import com.gpro.consulting.tiers.gpao.coordination.report.GammeOFReportListValue;
import com.gpro.consulting.tiers.gpao.coordination.report.GammeOFReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.GammeProduitReportListValue;
import com.gpro.consulting.tiers.gpao.coordination.report.GammeProduitReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.OperationReportListValue;
import com.gpro.consulting.tiers.gpao.coordination.report.OrdreFabricationReportListValue;
import com.gpro.consulting.tiers.gpao.coordination.report.ReservationProduitReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.charts.parClient.RepartitionQteParClientReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.production.global.value.ProductionChaineReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.production.global.value.ProductionGlobalReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.production.global.value.ProductionReportRequestValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.AleaReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.production.ProductionReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.comptage.ChaineComptageReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.globalOF.ChaineGlobalOFReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.globalOperation.ChaineGlobalOperationReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.jourOF.ChaineJourOFReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.recapProduction.RecapProductionChaineReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportOperateur.RechercheMulticritereOperateurReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportOperateur.competence.OperateurCompetenceReportValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportOperateur.historique.OperateurHistoriqueReportValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereOrdreFabricationValue;

/**
 * GestionnaireReportGpao Service Interface
 * 
 * @author Wahid Gazzah
 * @since 03/05/2016
 *
 */

public interface IGestionnaireReportGpaoService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public OrdreFabricationReportListValue getListOrdreFabrication(RechercheMulticritereOrdreFabricationValue request)
			throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public GammeProduitReportListValue getListGammeProduit(RechercheMulticritereGammeProduitValue request)
			throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public GammeProduitReportValue getGammeProduitReportValue(Long id) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public GammeOFReportValue getGammeOFReportValue(Long id) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ReservationProduitReportValue getReservationByOrdreFabricationId(Long ordreFabricationId) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public GammeOFReportListValue getListGammeOF(RechercheMulticritereGammeOfValue request) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheSuiveuseReportValue getFicheSuiveuseReport(Long ordreFabricationId, List<Long> paquetsList,
			List<Long> operationsList, boolean inverse) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheDepartageReportValue getFicheDepartageReport(Long ordreFabricationId) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FichePaquetsReportValue getFichePaquetsReport(Long ordreFabricationId) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheEclatementReportListValue getListFicheEclatementReport(
			RechercheMulticritereFicheEclatementValue request) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public OperationReportListValue getListOperationReport(RechercheMulticritereCatalogueValue request)
			throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ABCArticleDetailEtapeJourReportValue getABCArticleDetailEtapeJourReport(
			RechercheMulticritereABCArticleDetailEtapeJourValue request) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ABCArticleDetailEtapeJourReportValue getChartProductionByDateGroupeByAffichageReport(
			RechercheMulticritereABCArticleDetailEtapeJourValue request) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheSaisieReportSetValue getListFicheSaisieReport(RechercheMulticritereFeuilleSaisieValue request)
			throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheSaisieReporListValue getListFSaisieProductiviteGlobaleReport(
			RechercheMulticritereFeuilleSaisieValue request) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public RecapProductionReportValue getRecapProductionReport(RechercheMulticritereRecapProductionValue request)
			throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public AvancementOFReportValue getAvancementOFReportValue(Long id) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ChaineComptageReportValue getChaineComptageReport(RechercheMulticritereDetailSaisieValue request)
			throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ChaineGlobalOperationReportValue getChaineGlobalOperationReport(
			RechercheMulticritereDetailSaisieValue request) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ProductionGlobalReportValue getProductionGlobalReport(ProductionReportRequestValue request)
			throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ProductionChaineReportValue getProductionChaineReport(ProductionReportRequestValue request)
			throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ChaineGlobalOFReportValue getChaineGlobalOFReport(RechercheMulticritereDetailSaisieValue request)
			throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ChaineJourOFReportValue getChaineJourOFReport(RechercheMulticritereDetailSaisieValue request)
			throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public OperateurCompetenceReportValue getOperateurCompetenceReport(
			RechercheMulticritereOperateurReportValue request) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public OperateurHistoriqueReportValue getOperateurHistoriqueReport(
			RechercheMulticritereOperateurReportValue request) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	RecapProductionChaineReportValue getRecapProductionChaine(RechercheMulticritereFeuilleSaisieValue request)
			throws IOException;
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	RepartitionQteParClientReportValue getRepartitionQteParClientReport(RechercheMulticritereOrdreFabricationValue request)
			throws IOException;
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	RepartitionQteParClientReportValue getRepartitionQteParProduitReport(RechercheMulticritereOrdreFabricationValue request)
			throws IOException;
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	RepartitionQteParClientReportValue getRepartitionQteParSFamilleReport(
			RechercheMulticritereOrdreFabricationValue request) throws IOException;
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public AleaReportValue getAleaReport(RechercheMulticritereAleaValue request)
			throws IOException;
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	 public ProductionReportValue getProductionReportValue(RechercheMulticritereDetailSaisieValue request) throws IOException ;
	

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheColisReportValue getFicheDepartageColisReport(Long id)throws IOException ;
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheColisReportValue getFicheColisReport(Long id)throws IOException ;
	
}
