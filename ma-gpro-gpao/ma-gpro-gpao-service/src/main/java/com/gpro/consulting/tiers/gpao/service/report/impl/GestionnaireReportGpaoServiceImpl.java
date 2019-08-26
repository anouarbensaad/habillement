package com.gpro.consulting.tiers.gpao.service.report.impl;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.gpro.consulting.tiers.gpao.domaine.report.IGestionnaireReportGpaoDomaine;
import com.gpro.consulting.tiers.gpao.service.report.IGestionnaireReportGpaoService;

/**
 * Implementation of {@link IGestionnaireReportGpaoService}
 * 
 * @author Wahid Gazzah
 * @since 03/05/2016
 *
 */

@Service
@Transactional
public class GestionnaireReportGpaoServiceImpl implements IGestionnaireReportGpaoService {

	@Autowired
	private IGestionnaireReportGpaoDomaine gestionnaireReportGpaoDomaine;

	@Override
	public OrdreFabricationReportListValue getListOrdreFabrication(RechercheMulticritereOrdreFabricationValue request)
			throws IOException {

		return gestionnaireReportGpaoDomaine.getListOrdreFabrication(request);
	}

	@Override
	public GammeProduitReportListValue getListGammeProduit(RechercheMulticritereGammeProduitValue request)
			throws IOException {
		return gestionnaireReportGpaoDomaine.getListGammeProduit(request);
	}

	@Override
	public GammeProduitReportValue getGammeProduitReportValue(Long id) throws IOException {
		return gestionnaireReportGpaoDomaine.getGammeProduitReportValue(id);
	}

	@Override
	public GammeOFReportValue getGammeOFReportValue(Long id) throws IOException {
		return gestionnaireReportGpaoDomaine.getGammeOFReportValue(id);
	}

	@Override
	public ReservationProduitReportValue getReservationByOrdreFabricationId(Long ordreFabricationId)
			throws IOException {

		return gestionnaireReportGpaoDomaine.getReservationByOrdreFabricationId(ordreFabricationId);
	}

	@Override
	public GammeOFReportListValue getListGammeOF(RechercheMulticritereGammeOfValue request) throws IOException {
		return gestionnaireReportGpaoDomaine.getListGammeOF(request);
	}

	@Override
	public FicheSuiveuseReportValue getFicheSuiveuseReport(Long ordreFabricationId, List<Long> paquetsList,
			List<Long> operationsList, boolean inverse) throws IOException {

		return gestionnaireReportGpaoDomaine.getFicheSuiveuseReport(ordreFabricationId, paquetsList, operationsList, inverse);
	}

	@Override
	public FicheDepartageReportValue getFicheDepartageReport(Long ordreFabricationId) throws IOException {

		return gestionnaireReportGpaoDomaine.getFicheDepartageReport(ordreFabricationId);
	}

	@Override
	public FicheEclatementReportListValue getListFicheEclatementReport(
			RechercheMulticritereFicheEclatementValue request) throws IOException {
		return gestionnaireReportGpaoDomaine.getListFicheEclatementReport(request);
	}

	@Override
	public OperationReportListValue getListOperationReport(RechercheMulticritereCatalogueValue request)
			throws IOException {
		return gestionnaireReportGpaoDomaine.getListOperationReport(request);
	}

	@Override
	public FichePaquetsReportValue getFichePaquetsReport(Long ordreFabricationId) throws IOException {

		return gestionnaireReportGpaoDomaine.getFichePaquetsReport(ordreFabricationId);
	}

	@Override
	public ABCArticleDetailEtapeJourReportValue getABCArticleDetailEtapeJourReport(
			RechercheMulticritereABCArticleDetailEtapeJourValue request) throws IOException {

		return gestionnaireReportGpaoDomaine.getABCArticleDetailEtapeJourReport(request);
	}

	@Override
	public ABCArticleDetailEtapeJourReportValue getChartProductionByDateGroupeByAffichageReport(
			RechercheMulticritereABCArticleDetailEtapeJourValue request) throws IOException {

		return gestionnaireReportGpaoDomaine.getChartProductionByDateGroupeByAffichageReport(request);
	}

	@Override
	public FicheSaisieReportSetValue getListFicheSaisieReport(RechercheMulticritereFeuilleSaisieValue request)
			throws IOException {

		return gestionnaireReportGpaoDomaine.getListFicheSaisieReport(request);
	}

	@Override
	public FicheSaisieReporListValue getListFSaisieProductiviteGlobaleReport(
			RechercheMulticritereFeuilleSaisieValue request) throws IOException {

		return gestionnaireReportGpaoDomaine.getListFSaisieProductiviteGlobaleReport(request);
	}

	@Override
	public RecapProductionReportValue getRecapProductionReport(RechercheMulticritereRecapProductionValue request)
			throws IOException {

		return gestionnaireReportGpaoDomaine.getRecapProductionReport(request);
	}

	@Override
	public AvancementOFReportValue getAvancementOFReportValue(Long id) throws IOException {
		return gestionnaireReportGpaoDomaine.getAvancementOFReportValue(id);
	}

	@Override
	public ChaineComptageReportValue getChaineComptageReport(RechercheMulticritereDetailSaisieValue request)
			throws IOException {

		return gestionnaireReportGpaoDomaine.getChaineComptageReport(request);
	}

	@Override
	public ChaineGlobalOperationReportValue getChaineGlobalOperationReport(
			RechercheMulticritereDetailSaisieValue request) throws IOException {
		return gestionnaireReportGpaoDomaine.getChaineGlobalOperationReport(request);
	}

	@Override
	public ProductionGlobalReportValue getProductionGlobalReport(ProductionReportRequestValue request)
			throws IOException {

		return gestionnaireReportGpaoDomaine.getProductionGlobalReport(request);
	}

	@Override
	public ProductionChaineReportValue getProductionChaineReport(ProductionReportRequestValue request)
			throws IOException {

		return gestionnaireReportGpaoDomaine.getProductionChaineReport(request);
	}

	@Override
	public ChaineGlobalOFReportValue getChaineGlobalOFReport(RechercheMulticritereDetailSaisieValue request)
			throws IOException {
		return gestionnaireReportGpaoDomaine.getChaineGlobalOFReport(request);
	}

	@Override
	public ChaineJourOFReportValue getChaineJourOFReport(RechercheMulticritereDetailSaisieValue request)
			throws IOException {
		return gestionnaireReportGpaoDomaine.getChaineJourOFReport(request);
	}

	@Override
	public OperateurCompetenceReportValue getOperateurCompetenceReport(
			RechercheMulticritereOperateurReportValue request) throws IOException {
		return gestionnaireReportGpaoDomaine.getOperateurCompetenceReport(request);
	}

	@Override
	public OperateurHistoriqueReportValue getOperateurHistoriqueReport(
			RechercheMulticritereOperateurReportValue request) throws IOException {
		return gestionnaireReportGpaoDomaine.getOperateurHistoriqueReport(request);
	}

	@Override
	public RecapProductionChaineReportValue getRecapProductionChaine(RechercheMulticritereFeuilleSaisieValue request)
			throws IOException {
		return gestionnaireReportGpaoDomaine.getRecapProductionChaine(request);
	}

	@Override
	public RepartitionQteParClientReportValue getRepartitionQteParClientReport(
			RechercheMulticritereOrdreFabricationValue request) throws IOException {
		return gestionnaireReportGpaoDomaine.getRepartitionQteParClientReport(request);
	}
	
	@Override
	public RepartitionQteParClientReportValue getRepartitionQteParProduitReport(
			RechercheMulticritereOrdreFabricationValue request) throws IOException {
		return gestionnaireReportGpaoDomaine.getRepartitionQteParProduitReport(request);
	}
	
	@Override
	public RepartitionQteParClientReportValue getRepartitionQteParSFamilleReport(
			RechercheMulticritereOrdreFabricationValue request) throws IOException {
		return gestionnaireReportGpaoDomaine.getRepartitionQteParSFamilleReport(request);
	}

	@Override
	public AleaReportValue getAleaReport(RechercheMulticritereAleaValue request) throws IOException {
		// TODO Auto-generated method stub
		return gestionnaireReportGpaoDomaine.getAleaReport(request);
	}
	@Override
	public ProductionReportValue getProductionReportValue(
			RechercheMulticritereDetailSaisieValue request) throws IOException {
		return gestionnaireReportGpaoDomaine.getProductionReportValue(request);
	}

	@Override
	public FicheColisReportValue getFicheDepartageColisReport(Long id) throws IOException {
		// TODO Auto-generated method stub
		return gestionnaireReportGpaoDomaine.getFicheDepartageColisReport(id);
	}
	@Override
	public FicheColisReportValue getFicheColisReport(Long id) throws IOException {
		// TODO Auto-generated method stub
		return gestionnaireReportGpaoDomaine.getColisReport(id);
	}


}
