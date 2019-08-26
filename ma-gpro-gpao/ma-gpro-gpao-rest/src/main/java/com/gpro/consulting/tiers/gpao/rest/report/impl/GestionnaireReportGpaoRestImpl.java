package com.gpro.consulting.tiers.gpao.rest.report.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.service.IPartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.IProduitService;
import com.gpro.consulting.tiers.commun.service.ISousFamilleProduitService;
import com.gpro.consulting.tiers.gpao.coordination.abc.value.RechercheMulticritereABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.coordination.aleas.RechercheMulticritereAleaValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.RechercheMulticritereDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticritereFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.FicheColisReportValue;
import com.gpro.consulting.tiers.gpao.coordination.fichedepartage.report.value.FicheDepartageReportValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.RechercheMulticritereFicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.ResultatRechecheFicheEclatementElementValue;
import com.gpro.consulting.tiers.gpao.coordination.fichepaquets.report.value.FichePaquetsReportValue;
import com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.report.value.FicheSuiveuseReportValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.RechercheMulticritereGammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitElementValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.RechercheMulticritereGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ResultatRechecheGammeOfElementValue;
import com.gpro.consulting.tiers.gpao.coordination.machine.value.MachineValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.RechercheMulticritereCatalogueValue;
import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.RecapProductionReportValue;
import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.RechercheMulticritereRecapProductionValue;
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
import com.gpro.consulting.tiers.gpao.coordination.section.value.SectionValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.service.IGammeOfService;
import com.gpro.consulting.tiers.gpao.service.IGestionnaireCacheService;
import com.gpro.consulting.tiers.gpao.service.IMachineService;
import com.gpro.consulting.tiers.gpao.service.IOperationService;
import com.gpro.consulting.tiers.gpao.service.IOrdreFabricationService;
import com.gpro.consulting.tiers.gpao.service.ISectionService;
import com.gpro.consulting.tiers.gpao.service.report.IGestionnaireReportGpaoService;
import com.lowagie.text.DocumentException;

import net.sf.jasperreports.engine.JRException;

/**
 * Gestionnaire de reporting
 * 
 * @author Wahid Gazzah
 * @since 03/05/2016
 *
 */

@Controller
@RequestMapping("/report")
@SuppressWarnings("static-access")
public class GestionnaireReportGpaoRestImpl extends AbstractGestionnaireDownloadImpl {

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportGpaoRestImpl.class);

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Autowired
	private IGestionnaireReportGpaoService gestionnaireReportGpaoService;

	@Autowired
	private IGestionnaireCacheService gestionnaireCacheServiceGpao;

	@Autowired
	private ISousFamilleProduitService sousFamilleProduitService;

	@Autowired
	private IProduitService produitService;

	@Autowired
	private IPartieInteresseeService partieInteresseeService;

	@Autowired
	private IMachineService machineService;

	@Autowired
	private ISectionService sectionService;

	@Autowired
	private IGammeOfService gammeOfService;

	@Autowired
	private IOrdreFabricationService ordreFabricationService;

	@Autowired
	private IOperationService operationService;

	@RequestMapping(value = "/listOrdreFabrication", method = RequestMethod.GET)
	public void generateOrdreFabricationReport(@RequestParam(value = "vNumero", required = false) String vNumero,
			@RequestParam(value = "vCompositionBC", required = false) String vCompositionBC,
			@RequestParam(value = "vCompositionClient", required = false) String vCompositionClient,
			@RequestParam(value = "vEtat", required = false) String vEtat,
			@RequestParam(value = "vDateIntroductionDu", required = false) String vDateIntroductionDu,
			@RequestParam(value = "vDateIntroductionAu", required = false) String vDateIntroductionAu,
			@RequestParam(value = "clientId", required = false) Long clientId,
			@RequestParam(value = "produitId", required = false) Long produitId,
			@RequestParam(value = "dateLivraisonDu", required = false) String dateLivraisonDu,
			@RequestParam(value = "dateLivraisonTo", required = false) String dateLivraisonTo,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report list OrdreFabrication", type);

		RechercheMulticritereOrdreFabricationValue request = new RechercheMulticritereOrdreFabricationValue();
		request.setvNumero(vNumero);
		request.setvCompositionBC(vCompositionBC);
		request.setvCompositionClient(vCompositionClient);
		request.setvEtat(vEtat);
		request.setvDateIntroductionDu(stringToCalendar(vDateIntroductionDu));
		request.setvDateIntroductionAu(stringToCalendar(vDateIntroductionAu));
		request.setClientId(clientId);
		request.setProduitId(produitId);
		request.setDateLivraisonDu(stringToCalendar(dateLivraisonDu));
		request.setDateLivraisonTo(stringToCalendar(dateLivraisonTo));

		OrdreFabricationReportListValue report = gestionnaireReportGpaoService.getListOrdreFabrication(request);
		// Traitement : transformation de l'Id a sa propre Designation

		List<SousFamilleProduitValue> listSousFamilleProduit = sousFamilleProduitService.listeSousFamilleProduit();
		Map<Long, String> mapSousFamilleProduit = new HashMap<Long, String>();

		// ProduitDesignation
		if (report.getProduitId() != null) {

			ProduitValue vProduit = produitService.rechercheProduitById(report.getProduitId());
			if (vProduit != null) {
				report.setProduitDesignation(vProduit.getDesignation());
			}
		}

		// ClientAbreviation
		if (report.getClientId() != null) {
			PartieInteresseValue vClientRequest = new PartieInteresseValue();
			vClientRequest.setId(report.getClientId());
			PartieInteresseValue vClientResponse = partieInteresseeService
					.recherchePartieInteresseeParId(vClientRequest);
			if (vClientResponse != null) {
				report.setClientAbreviation(vClientResponse.getAbreviation());
			}
		}
		for (OrdreFabricationValue ordreFabrication : report.getOrdreFabricationList()) {

			// Etat
			Map<String, String> mapA = gestionnaireCacheServiceGpao
					.rechercherProduitEtatOFParId(ordreFabrication.getEtat());

			// Etat(StatutOF)
			ordreFabrication.setEtatDesignation(mapA.get("etatOF"));

			// Details Produit
			if (ordreFabrication.getProduitId() != null) {

				ProduitValue produit = produitService.rechercheProduitById(ordreFabrication.getProduitId());

				if (produit != null) {
					ordreFabrication.setProduitDesignation(produit.getDesignation());
					ordreFabrication.setProduitReference(produit.getReference());
					// produitId > designation > clientId > clientAbreviation
					// produitId > sousFamilleId > sousFamilleDesignation
					if (produit.getPartieIntersseId() != null) {
						PartieInteresseValue pi = new PartieInteresseValue();
						pi.setId(produit.getPartieIntersseId());
						PartieInteresseValue data = partieInteresseeService.recherchePartieInteresseeParId(pi);

						if (data != null) {
							ordreFabrication.setPartieInterresAbreviation(data.getAbreviation());
						}
					}
					if (produit.getSousFamilleId() != null) {
						SousFamilleProduitValue data = sousFamilleProduitService
								.rechercheSousFamilleProduitById(produit.getSousFamilleId());

						if (data != null) {
							ordreFabrication.setProduitSousFamilleDesignation(data.getDesignation());
						}
					}
					// DISCUSS
					// if(mapSousFamilleProduit.containsKey(produit.getSousFamilleId())){
					// ordreFabrication.setProduitSousFamilleDesignation(mapSousFamilleProduit.get(produit.getSousFamilleId()));
					// }
				}
			}

			// sousFamilleProduit
			// for( SousFamilleProduitValue sousFamilleProduit :
			// listSousFamilleProduit){
			// Long key = sousFamilleProduit.getId();
			// if (mapSousFamilleProduit.get(key) == null) {
			// mapSousFamilleProduit.put(sousFamilleProduit.getId(),
			// sousFamilleProduit.getDesignation());
			// }
			// }

		}

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getJRBeanCollectionDataSource(), response);

	}

	/**
	 * Methode de Génération d'une liste de GammeProduit selon des critères de
	 * recherche
	 * 
	 * @param produitId
	 * @param tempsTotalMin
	 * @param tempsTotalMax
	 * @param machineId
	 * @param sectionId
	 * @param type
	 * @param response
	 * @throws JRException
	 * @throws IOException
	 * @since 04/05/2016
	 * @author Ameni Berrich
	 */
	@RequestMapping(value = "/listGammeProduit", method = RequestMethod.GET)
	public void generateGammeProduitReport(@RequestParam(value = "produitId", required = false) Long produitId,
			@RequestParam(value = "tempsTotalMin", required = false) Double tempsTotalMin,
			@RequestParam(value = "tempsTotalMax", required = false) Double tempsTotalMax,
			@RequestParam(value = "machineId", required = false) Long machineId,
			@RequestParam(value = "sectionId", required = false) Long sectionId, @RequestParam("type") String type,
			HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report list GammeProduit ", type);

		RechercheMulticritereGammeProduitValue request = new RechercheMulticritereGammeProduitValue();
		request.setProduitId(produitId);
		request.setTempsTotalMin(tempsTotalMin);
		request.setTempsTotalMax(tempsTotalMax);
		request.setMachineId(machineId);
		request.setSectionId(sectionId);

		GammeProduitReportListValue report = gestionnaireReportGpaoService.getListGammeProduit(request);

		// Traitement : transformation des Ids en Designations
		// ProduitDesignation & reference TODO: Cache.
		if (report.getProduitId() != null) {
			ProduitValue vProduit = produitService.rechercheProduitById(report.getProduitId());
			if (vProduit != null) {
				report.setProduitReference(vProduit.getReference());
				report.setProduitDesignation(vProduit.getDesignation());
			}
		}
		// machineDesignation TODO: Cache.
		if (report.getMachineId() != null) {
			MachineValue vMachine = machineService.getById(report.getMachineId());
			if (vMachine != null) {
				report.setMachineDesignation(vMachine.getDesignation());
			}
		}
		// sectionDesignation TODO: Cache.
		if (report.getSectionId() != null) {
			SectionValue vSection = sectionService.getById(report.getSectionId());
			if (vSection != null) {
				report.setSectionDesignation(vSection.getDesignation());
			}
		}
		// conversion des produitId de la liste des gammeProduit en reference &
		// designation
		for (ResultatRechecheGammeProduitElementValue elementGammeProduit : report.getGammeProduitList()) {
			if (elementGammeProduit.getProduitId() != null) {
				ProduitValue vProduit = produitService.rechercheProduitById(elementGammeProduit.getProduitId());
				if (vProduit != null) {
					elementGammeProduit.setProduitReference(vProduit.getReference());
					elementGammeProduit.setProduitDesignation(vProduit.getDesignation());
				}
			}
		}

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);

	}

	/**
	 * Méthode de generation d'un rapport de GammeProduit
	 * 
	 * @param id
	 * @param type
	 * @param response
	 * @throws JRException
	 * @throws IOException
	 * @since 18/05/2016
	 * @author Ameni Berrich
	 */
	@RequestMapping(value = "/gammeProduit", method = RequestMethod.GET)
	public void generateGammeProduitReport(@RequestParam("id") Long id, @RequestParam("type") String type,
			HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report GammeProduit", type);

		GammeProduitReportValue report = gestionnaireReportGpaoService.getGammeProduitReportValue(id);
		// Traitement : transformation des Ids en Designations
		// ProduitDesignation & reference TODO: Cache.
		if (report.getProduitId() != null) {
			ProduitValue vProduit = produitService.rechercheProduitById(report.getProduitId());
			if (vProduit != null) {
				report.setReferenceProduit(vProduit.getReference());
				report.setDesignationProduit(vProduit.getDesignation());
			}
		}

		// conversion des produitId de la liste des gammeProduit en reference &
		// designation. TODO: Cache.
		for (GammeProduitElementReportValue elementGammeProduit : report.getListGammeProduit()) {
			// MachineId
			if (elementGammeProduit.getMachineId() != null) {
				MachineValue vMachine = machineService.getById(elementGammeProduit.getMachineId());

				if (vMachine != null) {
					elementGammeProduit.setMachineDesignation(vMachine.getDesignation());
				}
			}

			// OperationId
			if (elementGammeProduit.getOperationId() != null) {
				OperationValue vOperation = operationService.getById(elementGammeProduit.getOperationId());

				if (vOperation != null) {
					elementGammeProduit.setOperationCode(vOperation.getCode());
					elementGammeProduit.setOperationDesignation(vOperation.getDesignation());
				}
			}

			// SectionId
			if (elementGammeProduit.getSectionId() != null) {
				SectionValue vSection = sectionService.getById(elementGammeProduit.getSectionId());

				if (vSection != null) {
					elementGammeProduit.setSectionDesignation(vSection.getDesignation());
				}
			}
		}

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/gammeOF", method = RequestMethod.GET)
	public void generateGammeOFReport(@RequestParam("id") Long id, @RequestParam("type") String type,
			HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report GammeOF", type);

		GammeOFReportValue report = gestionnaireReportGpaoService.getGammeOFReportValue(id);
		// Traitement : transformation des Ids en Designations
		// ProduitDesignation & reference TODO: Cache.
		//LOGGER.info("=== report.getProduitId() " + report.getProduitId());
		if (report.getOfId() != null) {
			OrdreFabricationValue vOrdre = ordreFabricationService.rechercheOrdreFabricationParId(report.getOfId());
			if (vOrdre != null) {
				if (vOrdre.getProduitId() != null) {
					ProduitValue vProduit = produitService.rechercheProduitById(vOrdre.getProduitId());
					if (vProduit != null) {
						report.setReferenceProduit(vProduit.getReference());
						report.setDesignationProduit(vProduit.getDesignation());
					}
				}
				if (vOrdre.getPartieInterresId() != null) {
					PartieInteresseValue vPI = partieInteresseeService.getById(vOrdre.getPartieInterresId());
					if (vPI != null) {
						report.setReferenceClient(vPI.getReference());
					}
				}
			}
		}

		// conversion des produitId de la liste des gammeProduit en reference &
		// designation. TODO: Cache.
		for (GammeOFElementReportValue elementGammeOF : report.getListGammeProduit()) {
			// MachineId
			if (elementGammeOF.getMachineId() != null) {
				MachineValue vMachine = machineService.getById(elementGammeOF.getMachineId());

				if (vMachine != null) {
					elementGammeOF.setMachineDesignation(vMachine.getDesignation());
				}
			}

			// OperationId
			if (elementGammeOF.getOperationId() != null) {
				OperationValue vOperation = operationService.getById(elementGammeOF.getOperationId());

				if (vOperation != null) {
					elementGammeOF.setOperationCode(vOperation.getCode());
					elementGammeOF.setOperationDesignation(vOperation.getDesignation());
				}
			}

			// SectionId
			if (elementGammeOF.getSectionId() != null) {
				SectionValue vSection = sectionService.getById(elementGammeOF.getSectionId());

				if (vSection != null) {
					elementGammeOF.setSectionDesignation(vSection.getDesignation());
				}
			}
		}

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/reservationProduit", method = RequestMethod.GET)
	public void generateReservationProduitReport(
			@RequestParam(value = "ordreFabricationId", required = false) Long ordreFabricationId,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report list ReservationProduit ", type);

		ReservationProduitReportValue report = gestionnaireReportGpaoService
				.getReservationByOrdreFabricationId(ordreFabricationId);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getJRBeanCollectionDataSource(), response);
	}

	/**
	 * Methode de Génération d'un rapport de liste de GammeOF selon des critères
	 * de recherche
	 * 
	 * @param ordreFabricationId
	 * @param produitId
	 * @param tempsTotalMin
	 * @param tempsTotalMax
	 * @param machineId
	 * @param sectionId
	 * @param type
	 * @param response
	 * @throws JRException
	 * @throws IOException
	 * @since 16/05/2016
	 * @author Ameni Berrich
	 */
	@RequestMapping(value = "/listGammeOF", method = RequestMethod.GET)
	public void generateGammeOFReport(
			@RequestParam(value = "ordreFabricationId", required = false) Long ordreFabricationId,
			@RequestParam(value = "produitId", required = false) Long produitId,
			@RequestParam(value = "tempsTotalMin", required = false) Double tempsTotalMin,
			@RequestParam(value = "tempsTotalMax", required = false) Double tempsTotalMax,
			@RequestParam(value = "machineId", required = false) Long machineId,
			@RequestParam(value = "sectionId", required = false) Long sectionId, @RequestParam("type") String type,
			HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report list GammeOF ", type);

		RechercheMulticritereGammeOfValue request = new RechercheMulticritereGammeOfValue();
		request.setOrdreFabricationId(ordreFabricationId);
		request.setProduitId(produitId);
		request.setTempsTotalMin(tempsTotalMin);
		request.setTempsTotalMax(tempsTotalMax);
		request.setMachineId(machineId);
		request.setSectionId(sectionId);

		GammeOFReportListValue report = gestionnaireReportGpaoService.getListGammeOF(request);

		// Traitement : transformation des Ids en Designations
		// ProduitDesignation & reference TODO: Cache.
		if (report.getProduitId() != null) {
			ProduitValue vProduit = produitService.rechercheProduitById(report.getProduitId());
			if (vProduit != null) {
				report.setProduitReference(vProduit.getReference());
				report.setProduitDesignation(vProduit.getDesignation());
			}
		}
		// machineDesignation TODO: Cache.
		if (report.getMachineId() != null) {
			MachineValue vMachine = machineService.getById(report.getMachineId());
			if (vMachine != null) {
				report.setMachineDesignation(vMachine.getDesignation());
			}
		}
		// sectionDesignation TODO: Cache.
		if (report.getSectionId() != null) {
			SectionValue vSection = sectionService.getById(report.getSectionId());
			if (vSection != null) {
				report.setSectionDesignation(vSection.getDesignation());
			}
		}
		// conversion des produitId de la liste des gammeOF en reference &
		// designation. TODO: Cache.
		for (ResultatRechecheGammeOfElementValue elementGammeOF : report.getGammeOFList()) {

			if (elementGammeOF.getOrdreFabricationId() != null) {

				OrdreFabricationValue ordreFabrication = ordreFabricationService
						.rechercheOrdreFabricationParId(elementGammeOF.getOrdreFabricationId());

				if (ordreFabrication != null) {

					elementGammeOF.setOrdreFabricationNumero(ordreFabrication.getNumero());

					if (ordreFabrication.getProduitId() != null) {

						ProduitValue produit = produitService.rechercheProduitById(ordreFabrication.getProduitId());

						if (produit != null) {

							elementGammeOF.setProduitDesignation(produit.getDesignation());
							elementGammeOF.setProduitReference(produit.getReference());
						}

					}

					if (ordreFabrication.getPartieInterresId() != null) {

						PartieInteresseValue client = partieInteresseeService
								.getById(ordreFabrication.getPartieInterresId());

						if (client != null) {

							elementGammeOF.setClientAbreviation(client.getAbreviation());
							elementGammeOF.setClientReference(client.getReference());
						}

					}
				}
			}
		}

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/ficheSuiveuse", method = RequestMethod.GET)
	public void generateFicheSuiveuseReport(@RequestParam("ordreFabricationId") Long ordreFabricationId,
			@RequestParam("paquetsList") List<Long> paquetsList,
			@RequestParam("operationsList") List<Long> operationsList, 
			@RequestParam("type") String type,
			@RequestParam("inverse") String inverse,
			HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report FicheSuiveuse", type);
		
		boolean inverseParam =Boolean.valueOf(inverse);

		FicheSuiveuseReportValue report = gestionnaireReportGpaoService.getFicheSuiveuseReport(ordreFabricationId,
				paquetsList, operationsList, inverseParam);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getJRBeanCollectionDataSource(), response);
	}

	@RequestMapping(value = "/ficheDepartage", method = RequestMethod.GET)
	public void generateFicheDepartageReport(@RequestParam("ordreFabricationId") Long ordreFabricationId,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report FicheDepartage", type);

		FicheDepartageReportValue report = gestionnaireReportGpaoService.getFicheDepartageReport(ordreFabricationId);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getJRBeanCollectionDataSource(), response);
	}

	@RequestMapping(value = "/fichePaquets", method = RequestMethod.GET)
	public void generateFichePaquetsReport(@RequestParam("ordreFabricationId") Long ordreFabricationId,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report FichePaquets", type);

		FichePaquetsReportValue report = gestionnaireReportGpaoService.getFichePaquetsReport(ordreFabricationId);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getJRBeanCollectionDataSource(), response);
	}

	/**
	 * Méthode de génération de rapport de listeEclatement selon des critères de
	 * recherche
	 * 
	 * @param ordreFabricationId
	 * @param produitId
	 * @param type
	 * @param response
	 * @throws JRException
	 * @throws IOException
	 * @since 23/05/2016
	 * @author Ameni Berrich
	 */
	@RequestMapping(value = "/listEclatement", method = RequestMethod.GET)
	public void generateEclatementReport(@RequestParam("ordreFabricationId") Long ordreFabricationId,
			@RequestParam("produitId") Long produitId, @RequestParam("type") String type, HttpServletResponse response)
			throws JRException, IOException {

		//LOGGER.info("Generate a {} Report list for FicheEclatement ", type);

		RechercheMulticritereFicheEclatementValue request = new RechercheMulticritereFicheEclatementValue();
		request.setOrdreFabricationId(ordreFabricationId);
		request.setProduitId(produitId);

		FicheEclatementReportListValue report = gestionnaireReportGpaoService.getListFicheEclatementReport(request);

		// Traitement : transformation des Ids en Designations
		// OFNumero TODO: Cache.
		if (report.getOrdreFabricationId() != null) {
			OrdreFabricationValue ordreFabrication = ordreFabricationService
					.rechercheOrdreFabricationParId(report.getOrdreFabricationId());
			if (ordreFabrication != null) {
				report.setOrdreFabricationDesignation(ordreFabrication.getNumero());

			}
		}
		//LOGGER.info("prd {} ", report.getProduitId());
		// ProduitDesignation & reference TODO: Cache.
		if (report.getProduitId() != null) {

			ProduitValue vProduit = produitService.rechercheProduitById(report.getProduitId());
			if (vProduit != null) {
				report.setReferenceProduit(vProduit.getReference());
				report.setDesignationProduit(vProduit.getDesignation());
			}
		}

		// conversion des produitId de la liste des gammeOF en reference &
		// designation. TODO: Cache.
		for (ResultatRechecheFicheEclatementElementValue element : report.getEclatementList()) {

			if (element.getOrdreFabricationId() != null) {

				OrdreFabricationValue ordreFabrication = ordreFabricationService
						.rechercheOrdreFabricationParId(element.getOrdreFabricationId());

				if (ordreFabrication != null) {

					element.setOrdreFabricationNumero(ordreFabrication.getNumero());

					if (ordreFabrication.getProduitId() != null) {

						ProduitValue produit = produitService.rechercheProduitById(ordreFabrication.getProduitId());

						if (produit != null) {

							element.setProduitDesignation(produit.getDesignation());
							element.setProduitReference(produit.getReference());
						}

					}

					if (ordreFabrication.getPartieInterresId() != null) {

						PartieInteresseValue client = partieInteresseeService
								.getById(ordreFabrication.getPartieInterresId());

						if (client != null) {

							element.setClientAbreviation(client.getAbreviation());
							element.setClientReference(client.getReference());
						}

					}
				}
			}
		}

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);

	}

	/**
	 * Méthode de génération de rapport de liste de Catalogue de Temps
	 * (Operations) selon des critères de recherche
	 * 
	 * @param ordreFabricationId
	 * @param produitId
	 * @param type
	 * @param response
	 * @throws JRException
	 * @throws IOException
	 * @since 23/05/2016
	 * @author Ameni Berrich
	 */
	@RequestMapping(value = "/listeCatalogue", method = RequestMethod.GET)
	public void generateCatalogueReport(@RequestParam("code") String code,
			@RequestParam("designation") String designation, @RequestParam("machineId") Long machineId,
			@RequestParam("sectionId") Long sectionId, @RequestParam("type") String type, HttpServletResponse response)
			throws JRException, IOException {

		//LOGGER.info("Generate a {} Report list for Catalogue de Temps ", type);

		RechercheMulticritereCatalogueValue request = new RechercheMulticritereCatalogueValue();
		request.setCode(code);
		request.setDesignation(designation);
		request.setMachineId(machineId);
		request.setSectionId(sectionId);

		OperationReportListValue report = gestionnaireReportGpaoService.getListOperationReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/abcArticleDetailEtapeJourByPhaseIdDateSaisie", method = RequestMethod.GET)
	public void generateArticleDetailEtapeJourByPhaseIdDateSaisieReport(@RequestParam("phaseId") Long phaseId,
			@RequestParam("dateSaisieDu") String dateSaisieDu, @RequestParam("dateSaisieAu") String dateSaisieAu,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report list for AbcArticleDetailEtapJour ", type);

		RechercheMulticritereABCArticleDetailEtapeJourValue request = new RechercheMulticritereABCArticleDetailEtapeJourValue();
		request.setPhaseId(phaseId);
		request.setDateSaisieFrom(stringToCalendar(dateSaisieDu));
		request.setDateSaisieTo(stringToCalendar(dateSaisieAu));

		ABCArticleDetailEtapeJourReportValue report = gestionnaireReportGpaoService
				.getABCArticleDetailEtapeJourReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/chartProductionByDateGroupeByAffichage", method = RequestMethod.GET)
	public void generateChartProductionByDateGroupeByAffichageReport(@RequestParam("affichage") String affichage,
			@RequestParam("dateSaisieDu") String dateSaisieDu, @RequestParam("dateSaisieAu") String dateSaisieAu,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report list for ProductionChart ", type);

		RechercheMulticritereABCArticleDetailEtapeJourValue request = new RechercheMulticritereABCArticleDetailEtapeJourValue();
		request.setAffichage(affichage);
		request.setDateSaisieFrom(stringToCalendar(dateSaisieDu));
		request.setDateSaisieTo(stringToCalendar(dateSaisieAu));

		ABCArticleDetailEtapeJourReportValue report = gestionnaireReportGpaoService
				.getChartProductionByDateGroupeByAffichageReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/listeFicheSaisie", method = RequestMethod.GET)
	public void generateListeFicheSaisieReport(

			@RequestParam("matricule") String matricule, @RequestParam("chaineId") Long chaineId,
			@RequestParam("dateSaisieMin") String dateSaisieMin, @RequestParam("dateSaisieMax") String dateSaisieMax,
			@RequestParam("rendementMin") String rendementMin, @RequestParam("rendementMax") String rendementMax,
			@RequestParam("activiteMin") String activiteMin, @RequestParam("activiteMax") String activiteMax,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report list for Catalogue de Temps ", type);

		RechercheMulticritereFeuilleSaisieValue request = new RechercheMulticritereFeuilleSaisieValue();
		request.setMatricule(matricule);
		request.setChaineId(chaineId);
		request.setDateSaisieMin(stringToCalendar(dateSaisieMin));
		request.setDateSaisieMax(stringToCalendar(dateSaisieMax));

		if (isNotEmty(rendementMin)) {
			request.setRendementMin(Double.parseDouble(rendementMin));
		}

		if (isNotEmty(rendementMax)) {
			request.setRendementMax(Double.parseDouble(rendementMax));
		}

		if (isNotEmty(activiteMin)) {
			request.setActiviteMin(Double.parseDouble(activiteMin));
		}

		if (isNotEmty(activiteMax)) {
			request.setActiviteMax(Double.parseDouble(activiteMax));
		}

		FicheSaisieReportSetValue report = gestionnaireReportGpaoService.getListFicheSaisieReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/productiviteGlobaleByMatChaineDateGrByMat", method = RequestMethod.GET)
	public void generateProductionByMatChaineDateGrMatReport(@RequestParam("matricule") String matricule,
			@RequestParam("chaineId") Long chaineId, @RequestParam("dateSaisieMin") String dateSaisieMin,
			@RequestParam("dateSaisieMax") String dateSaisieMax, @RequestParam("rendementMin") String rendementMin,
			@RequestParam("rendementMax") String rendementMax, @RequestParam("activiteMin") String activiteMin,
			@RequestParam("activiteMax") String activiteMax, @RequestParam("type") String type,
			HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report list for AbcArticleDetailEtapJour ", type);

		RechercheMulticritereFeuilleSaisieValue request = new RechercheMulticritereFeuilleSaisieValue();
		request.setChaineId(chaineId);
		request.setMatricule(matricule);
		request.setDateSaisieMin(stringToCalendar(dateSaisieMin));
		request.setDateSaisieMax(stringToCalendar(dateSaisieMax));
		if (isNotEmty(rendementMin)) {
			request.setRendementMin(Double.parseDouble(rendementMin));
		}

		if (isNotEmty(rendementMax)) {
			request.setRendementMax(Double.parseDouble(rendementMax));
		}

		if (isNotEmty(activiteMin)) {
			request.setActiviteMin(Double.parseDouble(activiteMin));
		}

		if (isNotEmty(activiteMax)) {
			request.setActiviteMax(Double.parseDouble(activiteMax));
		}
		FicheSaisieReporListValue report = gestionnaireReportGpaoService
				.getListFSaisieProductiviteGlobaleReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/recapProduction", method = RequestMethod.GET)
	public void generateRecapProductionReport(
			@RequestParam(value = "partieInteresseId", required = false) Long partieInteresseId,
			@RequestParam(value = "produitId", required = false) Long produitId,
			@RequestParam(value = "statutId", required = false) Long statutId,
			@RequestParam(value = "dateLivraisonMin", required = false) String dateLivraisonMin,
			@RequestParam(value = "dateLivraisonMax", required = false) String dateLivraisonMax,
			@RequestParam(value = "dateIntroductionMin", required = false) String dateIntroductionMin,
			@RequestParam(value = "dateIntroductionMax", required = false) String dateIntroductionMax,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report for RecapProduction ", type);

		RechercheMulticritereRecapProductionValue request = new RechercheMulticritereRecapProductionValue();

		request.setPartieInteresseId(partieInteresseId);
		request.setProduitId(produitId);
		request.setStatutId(statutId);
		request.setDateLivraisonMin(stringToCalendar(dateLivraisonMin));
		request.setDateLivraisonMax(stringToCalendar(dateLivraisonMax));
		request.setDateIntroductionMin(stringToCalendar(dateIntroductionMin));
		request.setDateIntroductionMax(stringToCalendar(dateIntroductionMax));

		RecapProductionReportValue report = gestionnaireReportGpaoService.getRecapProductionReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getJRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/avancementOF", method = RequestMethod.GET)
	public void generateAvancementOFReport(@RequestParam("ordreFabricationId") Long id,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report GammeOF", type);

		AvancementOFReportValue report = gestionnaireReportGpaoService.getAvancementOFReportValue(id);
		// Traitement : transformation des Ids en Designations

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/rapportChaineComptage", method = RequestMethod.GET)
	public void generateRapportChaineComptageReport(@RequestParam(value = "chaineId", required = false) Long chaineId,
			@RequestParam(value = "dateIntroductionMin", required = false) String dateIntroductionMin,
			@RequestParam(value = "dateIntroductionMax", required = false) String dateIntroductionMax,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report for RecapProduction ", type);

		RechercheMulticritereDetailSaisieValue request = new RechercheMulticritereDetailSaisieValue();

		request.setChaineId(chaineId);
		request.setDateSaisieMin(stringToCalendar(dateIntroductionMin));
		request.setDateSaisieMax(stringToCalendar(dateIntroductionMax));

		ChaineComptageReportValue report = gestionnaireReportGpaoService.getChaineComptageReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/rapportChaineGlobalOperation", method = RequestMethod.GET)
	public void generateRapportChaineGlobalOperationReport(
			@RequestParam(value = "chaineId", required = false) Long chaineId,
			@RequestParam(value = "dateIntroductionMin", required = false) String dateIntroductionMin,
			@RequestParam(value = "dateIntroductionMax", required = false) String dateIntroductionMax,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report for RecapProduction ", type);

		RechercheMulticritereDetailSaisieValue request = new RechercheMulticritereDetailSaisieValue();

		request.setChaineId(chaineId);
		request.setDateSaisieMin(stringToCalendar(dateIntroductionMin));
		request.setDateSaisieMax(stringToCalendar(dateIntroductionMax));

		ChaineGlobalOperationReportValue report = gestionnaireReportGpaoService.getChaineGlobalOperationReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);

	}

	public void generateProductionGlobaleReport(@RequestParam(value = "chaineId", required = false) Long chaineId,
			@RequestParam(value = "dateMin", required = false) String dateMin,
			@RequestParam(value = "dateMax", required = false) String dateMax, @RequestParam("type") String type,
			HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report ProductionGlobal ", type);

		ProductionReportRequestValue request = new ProductionReportRequestValue();
		request.setChaineId(chaineId);
		request.setDateMin(stringToCalendar(dateMin));
		request.setDateMax(stringToCalendar(dateMax));

		ProductionGlobalReportValue report = gestionnaireReportGpaoService.getProductionGlobalReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getJRBeanCollectionDataSource(), response);

	}

	// TODO : non utilisée
	@RequestMapping(value = "/productionChaine", method = RequestMethod.GET)
	public void generateProductionChaineReport(@RequestParam(value = "chaineId", required = false) Long chaineId,
			@RequestParam(value = "dateMin", required = false) String dateMin,
			@RequestParam(value = "dateMax", required = false) String dateMax, @RequestParam("type") String type,
			HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report ProductionChaine", type);

		ProductionReportRequestValue request = new ProductionReportRequestValue();
		request.setChaineId(chaineId);
		request.setDateMin(stringToCalendar(dateMin));
		request.setDateMax(stringToCalendar(dateMax));

		ProductionChaineReportValue report = gestionnaireReportGpaoService.getProductionChaineReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getJRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/chaineGlobalOFReport", method = RequestMethod.GET)
	public void generateChaineGlobalOFReport(@RequestParam(value = "chaineId", required = false) Long chaineId,
			@RequestParam(value = "dateMin", required = false) String dateMin,
			@RequestParam(value = "dateMax", required = false) String dateMax, @RequestParam("type") String type,
			HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report chaineGlobalOFReport", type);

		RechercheMulticritereDetailSaisieValue request = new RechercheMulticritereDetailSaisieValue();
		request.setChaineId(chaineId);
		request.setDateSaisieMin(stringToCalendar(dateMin));
		request.setDateSaisieMax(stringToCalendar(dateMax));

		ChaineGlobalOFReportValue report = gestionnaireReportGpaoService.getChaineGlobalOFReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getJRBeanCollectionDataSource(), response);

	}

	@RequestMapping(value = "/chaineJourOFReport", method = RequestMethod.GET)
	public void generateChaineJourOFReport(@RequestParam(value = "chaineId", required = false) Long chaineId,
			@RequestParam(value = "dateMin", required = false) String dateMin,
			@RequestParam(value = "dateMax", required = false) String dateMax, @RequestParam("type") String type,
			HttpServletResponse response) throws JRException, IOException {

		//LOGGER.info("Generate a {} Report chaineJourOFReport", type);

		RechercheMulticritereDetailSaisieValue request = new RechercheMulticritereDetailSaisieValue();
		request.setChaineId(chaineId);
		request.setDateSaisieMin(stringToCalendar(dateMin));
		request.setDateSaisieMax(stringToCalendar(dateMax));

		ChaineJourOFReportValue report = gestionnaireReportGpaoService.getChaineJourOFReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getJRBeanCollectionDataSource(), response);

	}

	/***********************************
	 * Méthodes complémentaires
	 *****************************/

	private Calendar stringToCalendar(String dateString) {

		Calendar dateCalendar = null;

		if (isNotEmty(dateString)) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			dateCalendar = sdf.getCalendar();
			try {
				dateCalendar.setTime(sdf.parse(dateString));

			} catch (ParseException e) {
				logger.error("parse date exception: " + e.getMessage());
			}
		}

		return dateCalendar;
	}

	// Operateur competence
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/operateurCompetence", method = RequestMethod.GET)
	public void generateEtatStockNonMouvementesReport(@RequestParam("dateSaisieMin") String dateSaisieMin,
			@RequestParam("dateSaisieMax") String dateSaisieMax, @RequestParam("matricule") String matricule,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		// //LOGGER.info("Generate a {} Report EtatStockNonMouvementes",type);

		RechercheMulticritereOperateurReportValue request = new RechercheMulticritereOperateurReportValue();
		request.setDateSaisieMax(stringToCalendar(dateSaisieMax));
		request.setDateSaisieMin(stringToCalendar(dateSaisieMin));
		request.setMatricule(matricule);

		OperateurCompetenceReportValue report = gestionnaireReportGpaoService.getOperateurCompetenceReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);
	}

	// Operateur Historique
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/operateurHistorique", method = RequestMethod.GET)
	public void getOperateurHistoriqueReport(@RequestParam("dateSaisieMin") String dateSaisieMin,
			@RequestParam("dateSaisieMax") String dateSaisieMax, @RequestParam("matricule") String matricule,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		// //LOGGER.info("Generate a {} Report EtatStockNonMouvementes",type);

		RechercheMulticritereOperateurReportValue request = new RechercheMulticritereOperateurReportValue();
		request.setDateSaisieMax(stringToCalendar(dateSaisieMax));
		request.setDateSaisieMin(stringToCalendar(dateSaisieMin));
		request.setMatricule(matricule);

		OperateurHistoriqueReportValue report = gestionnaireReportGpaoService.getOperateurHistoriqueReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);
	}

	// rapportRecapProdChaine
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/rapportRecapProdChaine", method = RequestMethod.GET)
	public void getRecapProductionChaine(@RequestParam("dateSaisieMin") String dateSaisieMin,
			@RequestParam("dateSaisieMax") String dateSaisieMax, @RequestParam("chaineId") Long chaineId,
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

		// //LOGGER.info("Generate a {} Report EtatStockNonMouvementes",type);

		RechercheMulticritereFeuilleSaisieValue request = new RechercheMulticritereFeuilleSaisieValue();
		request.setDateSaisieMax(stringToCalendar(dateSaisieMax));
		request.setDateSaisieMin(stringToCalendar(dateSaisieMin));
		request.setChaineId(chaineId);

		RecapProductionChaineReportValue report = gestionnaireReportGpaoService.getRecapProductionChaine(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);
	}

	private boolean isNotEmty(String value) {
		return value != null && !"".equals(value);

	}
	
	
	// Par Client
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/qteParClientReport", method = RequestMethod.GET)
	public void getRepartitionQteParClientReport(@RequestParam("dateMin") String dateMin,
			@RequestParam("dateMax") String dateMax, 
			@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {


		RechercheMulticritereOrdreFabricationValue request = new RechercheMulticritereOrdreFabricationValue();
		request.setvDateIntroductionDu(stringToCalendar(dateMin));
		request.setvDateIntroductionAu(stringToCalendar(dateMax));
		
		
		RepartitionQteParClientReportValue report = gestionnaireReportGpaoService.getRepartitionQteParClientReport(request);

		this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
				report.getjRBeanCollectionDataSource(), response);
	}
	
	// Par Produit
		@SuppressWarnings("static-access")
		@RequestMapping(value = "/qteParProduitReport", method = RequestMethod.GET)
		public void getRepartitionQteParProduitReport(
				@RequestParam("dateMin") String dateMin,
				@RequestParam("dateMax") String dateMax, 
				@RequestParam("clientId") Long clientId,
				@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

			RechercheMulticritereOrdreFabricationValue request = new RechercheMulticritereOrdreFabricationValue();
			request.setvDateIntroductionDu(stringToCalendar(dateMin));
			request.setvDateIntroductionAu(stringToCalendar(dateMax));
			request.setClientId(clientId);
			
			
			RepartitionQteParClientReportValue report = gestionnaireReportGpaoService.getRepartitionQteParProduitReport(request);

			this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
					report.getjRBeanCollectionDataSource(), response);
		}
		
		//Par SFamille
		@SuppressWarnings("static-access")
		@RequestMapping(value = "/qteParSFamilleReport", method = RequestMethod.GET)
		public void getRepartitionQteParSFamilleReport(
				@RequestParam("dateMin") String dateMin,
				@RequestParam("dateMax") String dateMax,
				@RequestParam("clientId") Long clientId,
				@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

			RechercheMulticritereOrdreFabricationValue request = new RechercheMulticritereOrdreFabricationValue();
			request.setvDateIntroductionDu(stringToCalendar(dateMin));
			request.setvDateIntroductionAu(stringToCalendar(dateMax));
			request.setClientId(clientId);

			
			RepartitionQteParClientReportValue report = gestionnaireReportGpaoService.getRepartitionQteParSFamilleReport(request);

			this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
					report.getjRBeanCollectionDataSource(), response);
		}
		
		@SuppressWarnings("static-access")
		@RequestMapping(value = "/aleaReport", method = RequestMethod.GET)
		public void aleaReport(
				@RequestParam("dateMin") String dateMin,
				@RequestParam("dateMax") String dateMax,
				@RequestParam("matricule") String matricule,
				@RequestParam("chaineId") Long chaineId,
				@RequestParam("aleaId") Long aleaId,
				@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

			RechercheMulticritereAleaValue request = new RechercheMulticritereAleaValue();
			request.setDateSaisieMin(stringToCalendar(dateMin));
			request.setDateSaisieMax(stringToCalendar(dateMax));
			request.setMatricule(matricule);
			request.setChaineId(chaineId);
			request.setAleaId(aleaId);
			
			AleaReportValue report = gestionnaireReportGpaoService.getAleaReport(request);

			this.download(type,
					report.getReportStream(),
					report.getParams(), 
					report.getFileName(),
					report.getjRBeanCollectionDataSource(), response);
		}
		

		@RequestMapping(value = "/ProductionReport", method = RequestMethod.GET)
		public void getProductionReportValue(
				@RequestParam("dateDebut") String dateDebut,
				@RequestParam("dateFin") String dateFin,
				@RequestParam(value="clientId",required = false) Long clientId,
				@RequestParam("pointCmpt") Long pointCmpt ,
				@RequestParam(value="produitId", required = false) Long produitId,
				@RequestParam(value="chaineId", required = false) Long chaineId,
				@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

			RechercheMulticritereDetailSaisieValue request = new RechercheMulticritereDetailSaisieValue();
			//critaire de recherche 
			request.setDateSaisieMin(stringToCalendar(dateDebut));
			request.setDateSaisieMax(stringToCalendar(dateFin));
			request.setProduitId(produitId);
			request.setClientId(clientId);
			request.setOperation(pointCmpt);
            request.setChaineId(chaineId);
			
			
			ProductionReportValue report = gestionnaireReportGpaoService.getProductionReportValue(request);

			this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
					report.getjRBeanCollectionDataSource(), response);
		}
		
		
		@RequestMapping(value = "/ficheDepartageColis", method = RequestMethod.GET)
		public void generateFicheDepartageColisReport(@RequestParam("id") Long id,
				@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

			//LOGGER.info("Generate a {} Report FicheDepartage", type);

		  
			FicheColisReportValue report = gestionnaireReportGpaoService.getFicheDepartageColisReport(id);

			this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
					report.getjRBeanCollectionDataSource(), response);
		 //   }
		    }
		@RequestMapping(value = "/ficheColis", method = RequestMethod.GET)
		public void generateFicheColisReport(@RequestParam("id") Long id,
				@RequestParam("type") String type, HttpServletResponse response) throws JRException, IOException {

			//LOGGER.info("Generate a {} Report FicheDepartage", type);

		  
			FicheColisReportValue report = gestionnaireReportGpaoService.getFicheColisReport(id);

			this.download(type, report.getReportStream(), report.getParams(), report.getFileName(),
					report.getjRBeanCollectionDataSource(), response);
		 //   }
		    }
				
}
