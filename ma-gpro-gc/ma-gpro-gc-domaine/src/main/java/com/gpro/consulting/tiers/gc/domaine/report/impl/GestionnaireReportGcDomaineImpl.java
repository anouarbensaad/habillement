package com.gpro.consulting.tiers.gc.domaine.report.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.IConstante;
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
import com.gpro.consulting.tiers.gc.coordination.IConstanteGC;
import com.gpro.consulting.tiers.gc.coordination.besoinarticle.value.BesoinArticleValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.DiversFactureValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.FactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.ProduitFactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.DetailProduitLivraisonValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.LivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.ProduitLivraisonValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.RechercheMulticritereLivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.ResultatRechecheLivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.ArticleBesoinElementReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.ArticleBesoinReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.BesoinArticleElementReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.BesoinArticleReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.CommandeVenteReportElementValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.CommandeVenteReportListValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.CommandeVenteReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.DetailsProduitCommandeReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.DetailsProduitLivraisonReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.DiversFactureReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.FactureVenteReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.LivraisonVenteReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.ProduitCommandeReportListValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.ProduitCommandeReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.ProduitCommandeVenteReportElementValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.ProduitFactureReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.ProduitLivraisonReportValue;
import com.gpro.consulting.tiers.gc.coordination.value.CommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.DetailsProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereCommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheCommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.vue.BesoinArticleVue;
import com.gpro.consulting.tiers.gc.domaine.IBesoinArticleDomaine;
import com.gpro.consulting.tiers.gc.domaine.report.IGestionnaireReportGcDomaine;
import com.gpro.consulting.tiers.gc.domaine.report.utilities.FrenchNumberToWords;
import com.gpro.consulting.tiers.gc.persitance.ICommandeVentePersistance;
import com.gpro.consulting.tiers.gc.persitance.IProduitCommandePersistance;
import com.gpro.consulting.tiers.gc.persitance.factureVente.IFactureVentePersistance;
import com.gpro.consulting.tiers.gc.persitance.livraisonVente.ILivraisonVentePersistance;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Implementation of {@link IGestionnaireReportDomaine}
 * 
 * @author Wahid Gazzah
 * @since 10/03/2016
 *
 */

@Component
public class GestionnaireReportGcDomaineImpl implements IGestionnaireReportGcDomaine {

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportGcDomaineImpl.class);

	private static final String REPORT_NAME_COMMANDEVENTE = "commande vente";
	private static final String REPORT_NAME_ALL_COMMANDE_VENTE_LIST = "Liste des commandes de vente";
	private static final String REPORT_NAME_PRODUIT_COMMANDE_VENTE_LIST = "Liste des produits commandes de vente";
	private static final String REPORT_NAME_BESOINARTCLE = "besoin articles";
	private static final String REPORT_NAME_BESOINPRODUIT = "besoin produits";
	private static final String REPORT_NAME_LIVRAISON_VENTE = "Livraison";
	private static final String REPORT_NAME_FACTURE_VENTE = "Facture";

	private static final Long ARTICLETYPE_1 = 1L; // fourniture
	private static final Long ARTICLETYPE_2 = 2L; // tissu
	private static final Long ARTICLETYPE_3 = 3L; // fileacoudre

	private static final Double ZERO = 0D;
	private static final Long ZEROL = 0L;
	
	//amount to words
	private static final String EURO = " EURO ";
	private static final String CENTIMES = " CENTIMES";
	private static final String ET = " et ";

	@Autowired
	ICommandeVentePersistance commandeVentePersistance;

	@Autowired
	IPartieInteresseePersistance partieInteresseePersistance;

	@Autowired
	ICouleurPersistance couleurPersistance;

	@Autowired
	ITaillePersistance taillePersistance;

	@Autowired
	IProduitPersistance produitPersistance;

	@Autowired
	IProduitCommandePersistance produitCommandePersistance;

	@Autowired
	ISousFamilleProduitPersistance sousFamilleProduitPersistance;

	@Autowired
	IBesoinArticleDomaine besoinArticleDomaine;

	@Autowired
	private ILivraisonVentePersistance livraisonVentePersistance;

	@Autowired
	private IFactureVentePersistance factureVentePersistance;
	
	@Autowired
	private IBaseInfoPersistance baseInfoPersistance;
	

//	@Override
//	public CommandeVenteReportValue getCommandeVenteReportValue(Long id) throws IOException {
//
//		CommandeVenteReportValue report = new CommandeVenteReportValue();
//
//		// enrechissement des param du report
//		report.setFileName(REPORT_NAME_COMMANDEVENTE);
//		report.setReportStream(new FileInputStream(IConstanteGC.RAPPORTS_GC_BASE_URL + "FAROUK_CommandeVente/commande_vente_report.jrxml"));
//
//		HashMap<String, Object> params = new HashMap<String, Object>();
//
//		String logo = baseInfoPersistance.getLogo();
//		params.put("PATH_LOGO", logo);
//		
//		report.setParams(params);
//
//		CommandeVenteValue commandeVenteValue = commandeVentePersistance.rechercheCommandeVenteParId(id);
//
//		// enrichissement du report
//		enrichmentCommandeVenteReport(report, commandeVenteValue);
//
//		ArrayList<CommandeVenteReportValue> dataList = new ArrayList<CommandeVenteReportValue>();
//		dataList.add(report);
//
//		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
//
//		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
//
//		return report;
//	}

//	private void enrichmentCommandeVenteReport(CommandeVenteReportValue report, CommandeVenteValue commandeVenteValue) {
//
//		// TODO cache
//		List<CouleurValue> listCouleur = couleurPersistance.listeCouleur();
//		Map<Long, String> mapCouleurIdDesignation = new HashMap<Long, String>();
//		for (CouleurValue couleur : listCouleur) {
//			mapCouleurIdDesignation.put(couleur.getId(), couleur.getDesignation());
//		}
//
//		// TODO cache
//		List<TailleValue> listTaille = taillePersistance.listeTaille();
//		Map<Long, String> mapTailleIdDesignation = new HashMap<Long, String>();
//		for (TailleValue taille : listTaille) {
//			mapTailleIdDesignation.put(taille.getId(), taille.getDesignation());
//		}
//
//		// TODO cache
//		List<ProduitValue> listProduit = produitPersistance.listeProduit();
//		Map<Long, ProduitValue> mapProduitIdDesignation = new HashMap<Long, ProduitValue>();
//		for (ProduitValue produit : listProduit) {
//			mapProduitIdDesignation.put(produit.getId(), produit);
//		}
//
//		// TODO cache, client data
//		if (commandeVenteValue.getPartieIntersseId() != null) {
//
//			PartieInteresseValue client = partieInteresseePersistance
//					.recherchePartieInteresseeParId(commandeVenteValue.getPartieIntersseId());
//			if (client != null) {
//				report.setClientAbreviation(client.getAbreviation());
//				report.setClientAdresse(client.getAdresse());
//				report.setClientTelephone(client.getTelephone());
//				report.setClientFax(client.getFax());
//				report.setAdresseLivraison(client.getAdresseLiv());
//				report.setClientReference(client.getReference());
//				report.setClientRaisonSociale(client.getRaisonSociale());
//			}
//		}
//
//		report.setReference(commandeVenteValue.getReference());
//		report.setObservations(commandeVenteValue.getObservations());
//		report.setDateLivraison(commandeVenteValue.getDateLivraison());
//		report.setDateIntroduction(commandeVenteValue.getDateIntroduction());
//		report.setSaison(commandeVenteValue.getSaison());
//		report.setPrixTotal(commandeVenteValue.getPrixTotal());
//		report.setQuantiteTotal(commandeVenteValue.getQuantite());
//
//		ProduitCommandeReportValue produitCommandeReportRef = new ProduitCommandeReportValue();
//		setTailleDesignationToProduitCommandeRef(produitCommandeReportRef, listTaille);
//
//		List<ProduitCommandeReportValue> produitCommandes = new ArrayList<ProduitCommandeReportValue>();
//
//		for (ProduitCommandeValue produitCommandeValue : commandeVenteValue.getProduitCommandes()) {
//
//			List<String> listTailles = new ArrayList<String>();
//			List<String> listCouleurs = new ArrayList<String>();
//
//			Double montant = ZERO;
//
//			ProduitCommandeReportValue produitCommandeReport = new ProduitCommandeReportValue();
//
//			produitCommandeReport.setDateLivraison(produitCommandeValue.getDateLivraison());
//			produitCommandeReport.setPrixUnitaire(produitCommandeValue.getPrix());
//
//			if (mapProduitIdDesignation.containsKey(produitCommandeValue.getProduitId())) {
//				produitCommandeReport.setProduitDesignation(
//						mapProduitIdDesignation.get(produitCommandeValue.getProduitId()).getDesignation());
//
//				Long sousFamilleId = mapProduitIdDesignation.get(produitCommandeValue.getProduitId())
//						.getSousFamilleId();
//				if (sousFamilleId != null) {
//
//					SousFamilleProduitValue sousFamilleProduitValue = sousFamilleProduitPersistance
//							.rechercheSousFamilleProduitById(sousFamilleId);
//					if (sousFamilleProduitValue != null)
//						produitCommandeReport.setSousFamilleDesignation(sousFamilleProduitValue.getDesignation());
//				}
//
//			}
//
//			setTailleDesignationFromProduitCommandeRef(produitCommandeReport, produitCommandeReportRef);
//
//			List<DetailsProduitCommandeReportValue> listDetailsProduitCommande = new ArrayList<DetailsProduitCommandeReportValue>();
//
//			for (DetailsProduitCommandeValue detailsProduitCommandeVlue : produitCommandeValue
//					.getListDetailsProduitCommande()) {
//
//				DetailsProduitCommandeReportValue detailsProduitCommandeReport = new DetailsProduitCommandeReportValue();
//
//				detailsProduitCommandeReport.setTailleId(detailsProduitCommandeVlue.getTailleId());
//
//				setQuantite(detailsProduitCommandeReport, detailsProduitCommandeVlue.getQuantite());
//
//				if (produitCommandeReport.getPrixUnitaire() != null) {
//					montant = produitCommandeReport.getPrixUnitaire();
//				}
//
//				detailsProduitCommandeReport.setMontant(montant);
//
//				if (mapCouleurIdDesignation.containsKey(detailsProduitCommandeVlue.getCouleurId())) {
//					detailsProduitCommandeReport.setCouleurDesignation(
//							mapCouleurIdDesignation.get(detailsProduitCommandeVlue.getCouleurId()));
//				}
//
//				if (mapTailleIdDesignation.containsKey(detailsProduitCommandeVlue.getTailleId())) {
//					detailsProduitCommandeReport
//							.setTailleDesignation(mapTailleIdDesignation.get(detailsProduitCommandeVlue.getTailleId()));
//				}
//
//				listCouleurs.add(detailsProduitCommandeReport.getCouleurDesignation());
//
//				if (!listTailles.contains(detailsProduitCommandeReport.getTailleDesignation())) {
//					listTailles.add(detailsProduitCommandeReport.getTailleDesignation());
//				}
//
//				listDetailsProduitCommande.add(detailsProduitCommandeReport);
//
//			}
//
//			produitCommandeReport.setQuantiteTotal(produitCommandeValue.getQuantite());
//
//			if (produitCommandeValue.getPrix() != null && produitCommandeValue.getQuantite() != null) {
//
//				produitCommandeReport.setPrixTotal(produitCommandeValue.getPrix() * produitCommandeValue.getQuantite());
//			}
//
//			// Grouppement par Couleur
//			List<DetailsProduitCommandeReportValue> detailsProduit = new ArrayList<DetailsProduitCommandeReportValue>();
//
//			Map<String, List<DetailsProduitCommandeReportValue>> mapDetails = new HashMap<String, List<DetailsProduitCommandeReportValue>>();
//			for (DetailsProduitCommandeReportValue detail : listDetailsProduitCommande) {
//				String key = detail.getCouleurDesignation();
//				if (mapDetails.get(key) == null) {
//					mapDetails.put(key, new ArrayList<DetailsProduitCommandeReportValue>());
//				}
//				mapDetails.get(key).add(detail);
//			}
//
//			Iterator it = mapDetails.entrySet().iterator();
//			while (it.hasNext()) {
//
//				DetailsProduitCommandeReportValue element = new DetailsProduitCommandeReportValue();
//
//				Map.Entry<String, List<DetailsProduitCommandeReportValue>> pair = (Map.Entry<String, List<DetailsProduitCommandeReportValue>>) it
//						.next();
//				element.setCouleurDesignation(pair.getKey());
//
//				Long quantiteTD1 = ZEROL;
//				Long quantiteTD2 = ZEROL;
//				Long quantiteTD3 = ZEROL;
//				Long quantiteTD4 = ZEROL;
//				Long quantiteTD5 = ZEROL;
//				Long quantiteTD6 = ZEROL;
//
//				for (DetailsProduitCommandeReportValue details : pair.getValue()) {
//					quantiteTD1 = quantiteTD1 + ((details.getQuantiteTD1() != null) ? details.getQuantiteTD1() : ZEROL);
//					quantiteTD2 = quantiteTD2 + ((details.getQuantiteTD2() != null) ? details.getQuantiteTD2() : ZEROL);
//					quantiteTD3 = quantiteTD3 + ((details.getQuantiteTD3() != null) ? details.getQuantiteTD3() : ZEROL);
//					quantiteTD4 = quantiteTD4 + ((details.getQuantiteTD4() != null) ? details.getQuantiteTD4() : ZEROL);
//					quantiteTD5 = quantiteTD5 + ((details.getQuantiteTD5() != null) ? details.getQuantiteTD5() : ZEROL);
//					quantiteTD6 = quantiteTD6 + ((details.getQuantiteTD6() != null) ? details.getQuantiteTD6() : ZEROL);
//
//					element.setQuantite(
//							quantiteTD1 + quantiteTD2 + quantiteTD3 + quantiteTD4 + quantiteTD5 + quantiteTD6);
//					element.setMontant(details.getMontant() * element.getQuantite());
//				}
//
//				element.setQuantiteTD1(quantiteTD1.equals(ZEROL) ? null : quantiteTD1);
//				element.setQuantiteTD2(quantiteTD2.equals(ZEROL) ? null : quantiteTD2);
//				element.setQuantiteTD3(quantiteTD3.equals(ZEROL) ? null : quantiteTD3);
//				element.setQuantiteTD4(quantiteTD4.equals(ZEROL) ? null : quantiteTD4);
//				element.setQuantiteTD5(quantiteTD5.equals(ZEROL) ? null : quantiteTD5);
//				element.setQuantiteTD6(quantiteTD6.equals(ZEROL) ? null : quantiteTD6);
//
//				detailsProduit.add(element);
//			}
//
//			produitCommandeReport.setListDetailsProduitCommande(detailsProduit);
//
//			produitCommandes.add(produitCommandeReport);
//
//		}
//
//		report.setListProduitCommande(produitCommandes);
//
//	}
	
	//Leo Minor 09/03/2017
	@Override
	public CommandeVenteReportValue getCommandeVenteReportValue(Long id) throws IOException {

		CommandeVenteReportValue report = new CommandeVenteReportValue();

		// enrechissement des param du report
		report.setFileName(REPORT_NAME_COMMANDEVENTE);
		report.setReportStream(new FileInputStream(IConstanteGC.RAPPORTS_GC_BASE_URL + "FAROUK_CommandeVente/commandeVente/commande_vente_report.jrxml"));
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_COMMANDE_VENTE_PATH",
				IConstanteGC.RAPPORTS_GC_BASE_URL + "FAROUK_CommandeVente/commandeVente/commande_vente_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		CommandeVenteValue commandeVenteValue = commandeVentePersistance.rechercheCommandeVenteParId(id);

		// enrichissement du report
		enrichmentCommandeVenteReport(report, commandeVenteValue);

		ArrayList<CommandeVenteReportValue> dataList = new ArrayList<CommandeVenteReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}
	
	private void enrichmentCommandeVenteReport(CommandeVenteReportValue report, CommandeVenteValue commandeVenteValue) {

		// TODO cache
		List<CouleurValue> listCouleur = couleurPersistance.listeCouleur();
		Map<Long, String> mapCouleurIdDesignation = new HashMap<Long, String>();
		for (CouleurValue couleur : listCouleur) {
			mapCouleurIdDesignation.put(couleur.getId(), couleur.getDesignation());
		}

		// TODO cache
		List<TailleValue> listTaille = taillePersistance.listeTaille();
		Map<Long, String> mapTailleIdDesignation = new HashMap<Long, String>();
		for (TailleValue taille : listTaille) {
			mapTailleIdDesignation.put(taille.getId(), taille.getDesignation());
		}

		// TODO cache
		List<ProduitValue> listProduit = produitPersistance.listeProduit();
		Map<Long, ProduitValue> mapProduitIdDesignation = new HashMap<Long, ProduitValue>();
		for (ProduitValue produit : listProduit) {
			mapProduitIdDesignation.put(produit.getId(), produit);
		}

		// TODO cache, client data
		if (commandeVenteValue.getPartieIntersseId() != null) {

			PartieInteresseValue client = partieInteresseePersistance
					.recherchePartieInteresseeParId(commandeVenteValue.getPartieIntersseId());
			if (client != null) {
				report.setClientAbreviation(client.getAbreviation());
				report.setClientAdresse(client.getAdresse());
				report.setClientTelephone(client.getTelephone());
				report.setClientFax(client.getFax());
				report.setAdresseLivraison(client.getAdresseLiv());
				report.setClientReference(client.getReference());
				report.setClientRaisonSociale(client.getRaisonSociale());
			}
		}

		report.setReference(commandeVenteValue.getReference());
		report.setObservations(commandeVenteValue.getObservations());
		report.setDateLivraison(commandeVenteValue.getDateLivraison());
		report.setDateIntroduction(commandeVenteValue.getDateIntroduction());
		report.setSaison(commandeVenteValue.getSaison());
		report.setPrixTotal(commandeVenteValue.getPrixTotal());
		report.setQuantiteTotal(commandeVenteValue.getQuantite());

		ProduitCommandeReportValue produitCommandeReportRef = new ProduitCommandeReportValue();
		setTailleDesignationToProduitCommandeRef(produitCommandeReportRef, listTaille);

		List<ProduitCommandeReportValue> produitCommandes = new ArrayList<ProduitCommandeReportValue>();

		for (ProduitCommandeValue produitCommandeValue : commandeVenteValue.getProduitCommandes()) {


			ProduitCommandeReportValue produitCommandeReport = new ProduitCommandeReportValue();

			
			produitCommandeReport.setQuantiteP(produitCommandeValue.getQuantite());
			//System.out.println("--------produitCommandeValue.produitId"+produitCommandeValue.getProduitId());

			if (produitCommandeValue.getProduitId()!=null) {

			ProduitValue produit  = produitPersistance.rechercheProduitById(produitCommandeValue.getProduitId());
			
			//System.out.println("--------produit"+produit);
			produitCommandeReport.setDesignationP(produit.getDesignation());
			
			SousFamilleProduitValue sousFamille = sousFamilleProduitPersistance.rechercheSousFamilleProduitById(produit.getSousFamilleId());

			if (sousFamille!=null) {
				
			produitCommandeReport.setSousFamilleP(sousFamille.getDesignation());
			
			}
			}
			produitCommandes.add(produitCommandeReport);

		}

		report.setListProduitCommande(produitCommandes);

	}

//	@Override
//	public LivraisonVenteReportValue getLivraisonVenteReportValue(Long id) throws IOException {
//		LivraisonVenteReportValue report = new LivraisonVenteReportValue();
//
//		Long reportQuantiteTotal = ZEROL;
//		Double reportMontantTotal = ZERO;
//
//		// enrechissement des param du report
//		report.setFileName(REPORT_NAME_LIVRAISON_VENTE);
//		report.setReportStream(
//				new FileInputStream(IConstanteGC.RAPPORTS_GC_BASE_URL + "FAROUK_BonLivraisonVente/_livraison_vente_report.jrxml"));
//
//		HashMap<String, Object> params = new HashMap<String, Object>();
//
//		String logo = baseInfoPersistance.getLogo();
//		params.put("PATH_LOGO", logo);
//		
//		report.setParams(params);
//
//		LivraisonVenteValue livraisonVenteValue = livraisonVentePersistance.rechercheLivraisonVenteValueParId(id);
//
//		// enrichissement du report
//		enrichmentLivraisonVenteReport(report, livraisonVenteValue);
//
//		ArrayList<LivraisonVenteReportValue> dataList = new ArrayList<LivraisonVenteReportValue>();
//		dataList.add(report);
//
//		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);
//
//		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);
//
//		return report;
//	}
//
//	private void enrichmentLivraisonVenteReport(LivraisonVenteReportValue report,
//			LivraisonVenteValue livraisonVenteValue) {
//
//		// TODO cache
//		List<CouleurValue> listCouleur = couleurPersistance.listeCouleur();
//		Map<Long, String> mapCouleurIdDesignation = new HashMap<Long, String>();
//		for (CouleurValue couleur : listCouleur) {
//			mapCouleurIdDesignation.put(couleur.getId(), couleur.getDesignation());
//		}
//
//		// TODO cache
//		List<TailleValue> listTaille = taillePersistance.listeTaille();
//		Map<Long, String> mapTailleIdDesignation = new HashMap<Long, String>();
//		for (TailleValue taille : listTaille) {
//			mapTailleIdDesignation.put(taille.getId(), taille.getDesignation());
//		}
//
//		// TODO cache
//		List<ProduitValue> listProduit = produitPersistance.listeProduit();
//		Map<Long, ProduitValue> mapProduitIdDesignation = new HashMap<Long, ProduitValue>();
//		for (ProduitValue produit : listProduit) {
//			mapProduitIdDesignation.put(produit.getId(), produit);
//		}
//
//		// TODO cache, client data
//		if (livraisonVenteValue.getPartieIntersseId() != null) {
//
//			PartieInteresseValue client = partieInteresseePersistance
//					.recherchePartieInteresseeParId(livraisonVenteValue.getPartieIntersseId());
//			if (client != null) {
//				report.setClientAbreviation(client.getAbreviation());
//				report.setClientAdresse(client.getAdresse());
//				report.setClientTelephone(client.getTelephone());
//				report.setClientFax(client.getFax());
//				report.setAdresseLivraison(client.getAdresseLiv());
//				report.setClientReference(client.getReference());
//				report.setClientRaisonSociale(client.getRaisonSociale());
//			}
//		}
//
//		report.setReference(livraisonVenteValue.getReference());
//		report.setReferenceCommande(livraisonVenteValue.getRefCommande());
//		report.setObservations(livraisonVenteValue.getObservations());
//		report.setDateLivraison(livraisonVenteValue.getDateLivraison());
//		report.setDateCommande(livraisonVenteValue.getDateCommande());
//		report.setSaison(livraisonVenteValue.getSaison());
//		report.setColis(livraisonVenteValue.getColis());
//		report.setModeReglement(livraisonVenteValue.getModeReglement());
//		report.setPoids(livraisonVenteValue.getPoids());
//
//		Long quantiteTotal = ZEROL;
//
//		Long reportLivraisonQuantiteTotal = ZEROL;
//		Double reportLivraisonMontantTotal = ZERO;
//
//		ProduitLivraisonReportValue produitLivraisonReportRef = new ProduitLivraisonReportValue();
//		setTailleDesignationToProduitLivraisonRef(produitLivraisonReportRef, listTaille);
//
//		List<ProduitLivraisonReportValue> produitLivraisons = new ArrayList<ProduitLivraisonReportValue>();
//
//		for (ProduitLivraisonValue produitLivraison : livraisonVenteValue.getProduitLivraison()) {
//
//			Long produitLivraisonQuantiteTotal = ZEROL;
//			Double produitLivraisonMontantTotal = ZERO;
//
//			List<String> listTailles = new ArrayList<String>();
//			List<String> listCouleurs = new ArrayList<String>();
//
//			Double montant = ZERO;
//
//			ProduitLivraisonReportValue produitLivraisonReport = new ProduitLivraisonReportValue();
//
//			produitLivraisonReport.setDateLivraison(produitLivraison.getDateLivraison());
//			produitLivraisonReport.setPrixUnitaire(produitLivraison.getPrix());
//
//			if (mapProduitIdDesignation.containsKey(produitLivraison.getProduitId())) {
//				produitLivraisonReport.setProduitDesignation(
//						mapProduitIdDesignation.get(produitLivraison.getProduitId()).getDesignation());
//
//				Long sousFamilleId = mapProduitIdDesignation.get(produitLivraison.getProduitId()).getSousFamilleId();
//				if (sousFamilleId != null) {
//
//					SousFamilleProduitValue sousFamilleProduitValue = sousFamilleProduitPersistance
//							.rechercheSousFamilleProduitById(sousFamilleId);
//					if (sousFamilleProduitValue != null)
//						produitLivraisonReport.setSousFamilleDesignation(sousFamilleProduitValue.getDesignation());
//				}
//
//			}
//
//			setTailleDesignationFromProduitLivraisonRef(produitLivraisonReport, produitLivraisonReportRef);
//
//			List<DetailsProduitLivraisonReportValue> listDetailsProduitLivraison = new ArrayList<DetailsProduitLivraisonReportValue>();
//
//			for (DetailProduitLivraisonValue detailProduitLivraison : produitLivraison
//					.getListDetailsProduitLivraison()) {
//
//				DetailsProduitLivraisonReportValue detailsProduitLivraionsReport = new DetailsProduitLivraisonReportValue();
//
//				detailsProduitLivraionsReport.setTailleId(detailProduitLivraison.getTailleId());
//
//				setQuantite(detailsProduitLivraionsReport, detailProduitLivraison.getQuantite());
//
//				if (produitLivraisonReport.getPrixUnitaire() != null) {
//					montant = produitLivraisonReport.getPrixUnitaire();
//				}
//
//				detailsProduitLivraionsReport.setMontant(montant);
//
//				if (mapCouleurIdDesignation.containsKey(detailProduitLivraison.getCouleurId())) {
//					detailsProduitLivraionsReport
//							.setCouleurDesignation(mapCouleurIdDesignation.get(detailProduitLivraison.getCouleurId()));
//				}
//
//				if (mapTailleIdDesignation.containsKey(detailProduitLivraison.getTailleId())) {
//					detailsProduitLivraionsReport
//							.setTailleDesignation(mapTailleIdDesignation.get(detailProduitLivraison.getTailleId()));
//				}
//
//				listCouleurs.add(detailsProduitLivraionsReport.getCouleurDesignation());
//
//				if (!listTailles.contains(detailsProduitLivraionsReport.getTailleDesignation())) {
//					listTailles.add(detailsProduitLivraionsReport.getTailleDesignation());
//				}
//
//				if (detailsProduitLivraionsReport.getQuantite() != null) {
//
//					produitLivraisonQuantiteTotal = produitLivraisonQuantiteTotal
//							+ detailsProduitLivraionsReport.getQuantite();
//				}
//
//				if (detailsProduitLivraionsReport.getMontant() != null
//						&& detailsProduitLivraionsReport.getQuantite() != null) {
//
//					Double quantite = detailsProduitLivraionsReport.getMontant()
//							* detailsProduitLivraionsReport.getQuantite();
//
//					produitLivraisonMontantTotal = produitLivraisonMontantTotal + quantite;
//				}
//
//				listDetailsProduitLivraison.add(detailsProduitLivraionsReport);
//			}
//
//			if (produitLivraison.getQuantite() != null) {
//				quantiteTotal = quantiteTotal + produitLivraison.getQuantite();
//			}
//
//			produitLivraisonReport.setQuantiteTotal(produitLivraisonQuantiteTotal);
//			produitLivraisonReport.setPrixTotal(produitLivraisonMontantTotal);
//
//			reportLivraisonQuantiteTotal = reportLivraisonQuantiteTotal + produitLivraisonReport.getQuantiteTotal();
//			reportLivraisonMontantTotal = reportLivraisonMontantTotal + produitLivraisonReport.getPrixTotal();
//
//			/*
//			 * produitLivraisonReport.setQuantiteTotal(produitLivraison.
//			 * getQuantite());
//			 * 
//			 * if(produitLivraison.getPrix() != null &&
//			 * produitLivraison.getQuantite() != null){
//			 * 
//			 * produitLivraisonReport.setPrixTotal(produitLivraison.getPrix() *
//			 * produitLivraison.getQuantite()); }
//			 */
//
//			// Grouppement par Couleur
//			List<DetailsProduitLivraisonReportValue> detailsProduit = new ArrayList<DetailsProduitLivraisonReportValue>();
//
//			Map<String, List<DetailsProduitLivraisonReportValue>> mapDetails = new HashMap<String, List<DetailsProduitLivraisonReportValue>>();
//			for (DetailsProduitLivraisonReportValue detail : listDetailsProduitLivraison) {
//				String key = detail.getCouleurDesignation();
//				if (mapDetails.get(key) == null) {
//					mapDetails.put(key, new ArrayList<DetailsProduitLivraisonReportValue>());
//				}
//				mapDetails.get(key).add(detail);
//			}
//
//			Iterator it = mapDetails.entrySet().iterator();
//			while (it.hasNext()) {
//
//				DetailsProduitLivraisonReportValue element = new DetailsProduitLivraisonReportValue();
//
//				Map.Entry<String, List<DetailsProduitLivraisonReportValue>> pair = (Map.Entry<String, List<DetailsProduitLivraisonReportValue>>) it
//						.next();
//				element.setCouleurDesignation(pair.getKey());
//
//				Long quantiteTD1 = ZEROL;
//				Long quantiteTD2 = ZEROL;
//				Long quantiteTD3 = ZEROL;
//				Long quantiteTD4 = ZEROL;
//				Long quantiteTD5 = ZEROL;
//				Long quantiteTD6 = ZEROL;
//
//				for (DetailsProduitLivraisonReportValue details : pair.getValue()) {
//					quantiteTD1 = quantiteTD1 + ((details.getQuantiteTD1() != null) ? details.getQuantiteTD1() : ZEROL);
//					quantiteTD2 = quantiteTD2 + ((details.getQuantiteTD2() != null) ? details.getQuantiteTD2() : ZEROL);
//					quantiteTD3 = quantiteTD3 + ((details.getQuantiteTD3() != null) ? details.getQuantiteTD3() : ZEROL);
//					quantiteTD4 = quantiteTD4 + ((details.getQuantiteTD4() != null) ? details.getQuantiteTD4() : ZEROL);
//					quantiteTD5 = quantiteTD5 + ((details.getQuantiteTD5() != null) ? details.getQuantiteTD5() : ZEROL);
//					quantiteTD6 = quantiteTD6 + ((details.getQuantiteTD6() != null) ? details.getQuantiteTD6() : ZEROL);
//
//					element.setQuantite(quantiteTD1 + quantiteTD2 + quantiteTD3 + quantiteTD4 + quantiteTD5);
//					element.setMontant(details.getMontant() * element.getQuantite());
//				}
//
//				element.setQuantiteTD1(quantiteTD1.equals(ZEROL) ? null : quantiteTD1);
//				element.setQuantiteTD2(quantiteTD2.equals(ZEROL) ? null : quantiteTD2);
//				element.setQuantiteTD3(quantiteTD3.equals(ZEROL) ? null : quantiteTD3);
//				element.setQuantiteTD4(quantiteTD4.equals(ZEROL) ? null : quantiteTD4);
//				element.setQuantiteTD5(quantiteTD5.equals(ZEROL) ? null : quantiteTD5);
//				element.setQuantiteTD6(quantiteTD6.equals(ZEROL) ? null : quantiteTD6);
//
//				detailsProduit.add(element);
//			}
//
//			produitLivraisonReport.setListDetailsProduitLivraison(detailsProduit);
//
//			produitLivraisons.add(produitLivraisonReport);
//
//		}
//
//		// report.setQuantiteTotal(quantiteTotal);
//
//		report.setQuantiteTotal(reportLivraisonQuantiteTotal);
//		report.setPrixTotal(reportLivraisonMontantTotal);
//
//		report.setListProduitLivraison(produitLivraisons);
//	}
	
	@Override
	public LivraisonVenteReportValue getLivraisonVenteReportValue(Long id) throws IOException {
		LivraisonVenteReportValue report = new LivraisonVenteReportValue();

		Long reportQuantiteTotal = ZEROL;
		Double reportMontantTotal = ZERO;

		// enrechissement des param du report
		report.setFileName(REPORT_NAME_LIVRAISON_VENTE);
		report.setReportStream(
				new FileInputStream(IConstanteGC.RAPPORTS_GC_BASE_URL + "FAROUK_BonLivraisonVente/livraison_vente_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_LIVRAISON_VENTE_PATH",
				IConstanteGC.RAPPORTS_GC_BASE_URL + "FAROUK_BonLivraisonVente/livraison_vente_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		LivraisonVenteValue livraisonVenteValue = livraisonVentePersistance.rechercheLivraisonVenteValueParId(id);

		// enrichissement du report
		enrichmentLivraisonVenteReport(report, livraisonVenteValue);

		ArrayList<LivraisonVenteReportValue> dataList = new ArrayList<LivraisonVenteReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichmentLivraisonVenteReport(LivraisonVenteReportValue report,
			LivraisonVenteValue livraisonVenteValue) {

		// TODO cache
		List<CouleurValue> listCouleur = couleurPersistance.listeCouleur();
		Map<Long, String> mapCouleurIdDesignation = new HashMap<Long, String>();
		for (CouleurValue couleur : listCouleur) {
			mapCouleurIdDesignation.put(couleur.getId(), couleur.getDesignation());
		}

		// TODO cache
		List<TailleValue> listTaille = taillePersistance.listeTaille();
		Map<Long, String> mapTailleIdDesignation = new HashMap<Long, String>();
		for (TailleValue taille : listTaille) {
			mapTailleIdDesignation.put(taille.getId(), taille.getDesignation());
		}

		// TODO cache
		List<ProduitValue> listProduit = produitPersistance.listeProduit();
		Map<Long, ProduitValue> mapProduitIdDesignation = new HashMap<Long, ProduitValue>();
		for (ProduitValue produit : listProduit) {
			mapProduitIdDesignation.put(produit.getId(), produit);
		}

		// TODO cache, client data
		if (livraisonVenteValue.getPartieIntersseId() != null) {

			PartieInteresseValue client = partieInteresseePersistance
					.recherchePartieInteresseeParId(livraisonVenteValue.getPartieIntersseId());
			if (client != null) {
				report.setClientAbreviation(client.getAbreviation());
				report.setClientAdresse(client.getAdresse());
				report.setClientTelephone(client.getTelephone());
				report.setClientFax(client.getFax());
				report.setAdresseLivraison(client.getAdresseLiv());
				report.setClientReference(client.getReference());
				report.setClientRaisonSociale(client.getRaisonSociale());
			}
		}

		report.setReference(livraisonVenteValue.getReference());
		report.setReferenceCommande(livraisonVenteValue.getRefCommande());
		report.setObservations(livraisonVenteValue.getObservations());
		report.setDateLivraison(livraisonVenteValue.getDateLivraison());
		report.setDateCommande(livraisonVenteValue.getDateCommande());
		report.setSaison(livraisonVenteValue.getSaison());
		report.setColis(livraisonVenteValue.getColis());
		report.setModeReglement(livraisonVenteValue.getModeReglement());
		report.setPoids(livraisonVenteValue.getPoids());

		Long quantiteTotal = ZEROL;

		Long reportLivraisonQuantiteTotal = ZEROL;
		Double reportLivraisonMontantTotal = ZERO;

		ProduitLivraisonReportValue produitLivraisonReportRef = new ProduitLivraisonReportValue();
		setTailleDesignationToProduitLivraisonRef(produitLivraisonReportRef, listTaille);

		List<ProduitLivraisonReportValue> produitLivraisons = new ArrayList<ProduitLivraisonReportValue>();

		for (ProduitLivraisonValue produitLivraison : livraisonVenteValue.getProduitLivraison()) {

			Long produitLivraisonQuantiteTotal = ZEROL;
			Double produitLivraisonMontantTotal = ZERO;

			List<String> listTailles = new ArrayList<String>();
			List<String> listCouleurs = new ArrayList<String>();

			Double montant = ZERO;

			ProduitLivraisonReportValue produitLivraisonReport = new ProduitLivraisonReportValue();

			produitLivraisonReport.setDateLivraison(produitLivraison.getDateLivraison());
			produitLivraisonReport.setPrixUnitaire(produitLivraison.getPrix());
			
			
			
			
			//System.out.println("--------produitLivraison.getProduitId()"+produitLivraison.getProduitId());

			if (produitLivraison.getProduitId()!=null) {

			ProduitValue produit  = produitPersistance.rechercheProduitById(produitLivraison.getProduitId());
			
			//System.out.println("--------produit"+produit);
			produitLivraisonReport.setDesignationP(produit.getDesignation());
			
			SousFamilleProduitValue sousFamille = sousFamilleProduitPersistance.rechercheSousFamilleProduitById(produit.getSousFamilleId());

			if (sousFamille!=null) {
				
				produitLivraisonReport.setSousFamilleP(sousFamille.getDesignation());
			
			}
			}
			produitLivraisonReport.setQuantiteP(produitLivraison.getQuantite());

			produitLivraisons.add(produitLivraisonReport);

		}

		// report.setQuantiteTotal(quantiteTotal);

		report.setQuantiteTotal(reportLivraisonQuantiteTotal);
		report.setPrixTotal(reportLivraisonMontantTotal);

		report.setListProduitLivraison(produitLivraisons);
	}

	private void setTailleDesignationFromProduitCommandeRef(ProduitCommandeReportValue produit,
			ProduitCommandeReportValue produitRef) {

		produit.setTailleD1(produitRef.getTailleD1());
		produit.setTailleD2(produitRef.getTailleD2());
		produit.setTailleD3(produitRef.getTailleD3());
		produit.setTailleD4(produitRef.getTailleD4());
		produit.setTailleD5(produitRef.getTailleD5());
		produit.setTailleD6(produitRef.getTailleD6());
	}

	private void setTailleDesignationFromProduitLivraisonRef(ProduitLivraisonReportValue produit,
			ProduitLivraisonReportValue produitRef) {

		produit.setTailleD1(produitRef.getTailleD1());
		produit.setTailleD2(produitRef.getTailleD2());
		produit.setTailleD3(produitRef.getTailleD3());
		produit.setTailleD4(produitRef.getTailleD4());
		produit.setTailleD5(produitRef.getTailleD5());
		produit.setTailleD6(produitRef.getTailleD6());

	}

	private void setQuantite(DetailsProduitCommandeReportValue detail, Long quantite) {

		if (detail.getTailleId() != null) {
			detail.setQuantite(quantite);
			switch (Integer.valueOf(detail.getTailleId().intValue())) {
			case 1:
				detail.setQuantiteTD1(quantite);
				break;
			case 2:
				detail.setQuantiteTD2(quantite);
				break;
			case 3:
				detail.setQuantiteTD3(quantite);
				break;
			case 4:
				detail.setQuantiteTD4(quantite);
				break;
			case 5:
				detail.setQuantiteTD5(quantite);
				break;
			case 6:
				detail.setQuantiteTD6(quantite);
				break;
			default:
				break;
			}
		}
	}

	private void setQuantite(DetailsProduitLivraisonReportValue detail, Long quantite) {

		if (detail.getTailleId() != null) {
			detail.setQuantite(quantite);
			switch (Integer.valueOf(detail.getTailleId().intValue())) {
			case 1:
				detail.setQuantiteTD1(quantite);
				break;
			case 2:
				detail.setQuantiteTD2(quantite);
				break;
			case 3:
				detail.setQuantiteTD3(quantite);
				break;
			case 4:
				detail.setQuantiteTD4(quantite);
				break;
			case 5:
				detail.setQuantiteTD5(quantite);
				break;
			case 6:
				detail.setQuantiteTD6(quantite);
				break;
			default:
				break;
			}
		}
	}

	private void setTailleDesignationToProduitCommandeRef(ProduitCommandeReportValue produit,
			List<TailleValue> listTaille) {

		for (TailleValue taille : listTaille) {
			switch (Integer.valueOf(taille.getId().intValue())) {
			case 1:
				produit.setTailleD1(taille.getDesignation());
				break;
			case 2:
				produit.setTailleD2(taille.getDesignation());
				break;
			case 3:
				produit.setTailleD3(taille.getDesignation());
				break;
			case 4:
				produit.setTailleD4(taille.getDesignation());
				break;
			case 5:
				produit.setTailleD5(taille.getDesignation());
				break;
			case 6:
				produit.setTailleD6(taille.getDesignation());
				break;
			default:
				break;
			}
		}
	}

	private void setTailleDesignationToProduitLivraisonRef(ProduitLivraisonReportValue produitLivraisonReportRef,
			List<TailleValue> listTaille) {

		for (TailleValue taille : listTaille) {
			switch (Integer.valueOf(taille.getId().intValue())) {
			case 1:
				produitLivraisonReportRef.setTailleD1(taille.getDesignation());
				break;
			case 2:
				produitLivraisonReportRef.setTailleD2(taille.getDesignation());
				break;
			case 3:
				produitLivraisonReportRef.setTailleD3(taille.getDesignation());
				break;
			case 4:
				produitLivraisonReportRef.setTailleD4(taille.getDesignation());
				break;
			case 5:
				produitLivraisonReportRef.setTailleD5(taille.getDesignation());
				break;
			case 6:
				produitLivraisonReportRef.setTailleD6(taille.getDesignation());
				break;
			default:
				break;
			}
		}

	}

	@Override
	public CommandeVenteReportListValue getListCommandeVenteReport(RechercheMulticritereCommandeVenteValue request)
			throws IOException {

		CommandeVenteReportListValue vCommandeVenteReportList = new CommandeVenteReportListValue();
		// enrechissement des param du report
		vCommandeVenteReportList.setFileName(REPORT_NAME_ALL_COMMANDE_VENTE_LIST);
		vCommandeVenteReportList.setReportStream(new FileInputStream(
				IConstanteGC.RAPPORTS_GC_BASE_URL + "FAROUK_CommandeVente/AllCommandeVente/all_CommandeVente_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_ALL_COMMANDES_VENTE_PATH",
				IConstanteGC.RAPPORTS_GC_BASE_URL + "FAROUK_CommandeVente/AllCommandeVente/All_commandeVente_sub_report.jasper");
		params.put("PATH_LOGO", "/report/logo_enfavet.jpg");
		
		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		vCommandeVenteReportList.setParams(params);

		ResultatRechecheCommandeVenteValue result = commandeVentePersistance
				.rechercherCommandeVenteMultiCritere(request);
		Set<CommandeVenteValue> vCommandeVenteList = new TreeSet<CommandeVenteValue>(result.getCommandeVenteValues());

		// enrichissement du report
		enrichmentCommandeVenteReportList(vCommandeVenteReportList, vCommandeVenteList, request);

		ArrayList<CommandeVenteReportListValue> dataList = new ArrayList<CommandeVenteReportListValue>();
		dataList.add(vCommandeVenteReportList);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		vCommandeVenteReportList.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return vCommandeVenteReportList;
	}

	private void enrichmentCommandeVenteReportList(CommandeVenteReportListValue vCommandeVenteReportList,
			Set<CommandeVenteValue> vCommandeVenteSet, RechercheMulticritereCommandeVenteValue request) {

		vCommandeVenteReportList.setReference(request.getvReference());
		vCommandeVenteReportList.setPartieInteressee(request.getvTypePartieInteressee());
		vCommandeVenteReportList.setProduit(request.getvProduit());
		vCommandeVenteReportList.setDateIntroductionDu(request.getDateIntroductionDu());
		vCommandeVenteReportList.setDateIntroductionA(request.getDateIntroductionA());
		vCommandeVenteReportList.setDateLivraisonDu(request.getDateLivraisonDu());
		vCommandeVenteReportList.setDateLivraisonA(request.getDateLivraisonA());
		vCommandeVenteReportList.setTypeCommande(request.getvTypeCommande());
		vCommandeVenteReportList.setEtatCommande(request.getvEtatCommande());
		// added on 17/06/2016, by Ameni Berrich
		vCommandeVenteReportList.setQuantiteDu(request.getQuantiteDu());
		vCommandeVenteReportList.setQuantiteA(request.getQuantiteA());
		vCommandeVenteReportList.setCoutDu(request.getCoutDu());
		vCommandeVenteReportList.setCoutA(request.getCoutA());

		// TODO cache
		List<ProduitValue> listProduit = produitPersistance.listeProduit();

		Map<Long, ProduitValue> mapProduits = new HashMap<Long, ProduitValue>();
		for (ProduitValue produit : listProduit) {
			Long key = produit.getId();
			if (mapProduits.get(key) == null) {
				mapProduits.put(key, produit);
			}
		}

		// TODO cache
		Map<Long, String> mapClientsAbreviations = new HashMap<Long, String>();
		for (PartieInteresseValue client : partieInteresseePersistance.listePartieInteressee()) {
			Long key = client.getId();
			if (mapClientsAbreviations.get(key) == null) {
				mapClientsAbreviations.put(key, client.getAbreviation());
			}
		}

		if (estNonVide(request.getvProduit())) {
			Long produitId = Long.valueOf(request.getvProduit()).longValue();

			if (produitId != null) {
				if (mapProduits.containsKey(produitId)) {
					vCommandeVenteReportList.setProduitReference(mapProduits.get(produitId).getReference());
					vCommandeVenteReportList.setProduitDesignation(mapProduits.get(produitId).getDesignation());
				} else {
				}
			}
		}

		if (estNonVide(request.getvTypePartieInteressee())) {
			Long clientId = Long.valueOf(request.getvTypePartieInteressee()).longValue();

			if (clientId != null) {
				if (mapClientsAbreviations.containsKey(clientId)) {
					vCommandeVenteReportList.setClientAbreviation(mapClientsAbreviations.get(clientId));
				}
			}
		}
		// listeCommandeVente
		List<CommandeVenteReportElementValue> listeElementCommandeVentes = new ArrayList<CommandeVenteReportElementValue>();
		for (CommandeVenteValue commandeVenteValue : vCommandeVenteSet) {

			CommandeVenteReportElementValue vCommandeVenteReportElementValue = new CommandeVenteReportElementValue();
			vCommandeVenteReportElementValue.setDateIntroduction(commandeVenteValue.getDateIntroduction());
			vCommandeVenteReportElementValue.setDateLivraison(commandeVenteValue.getDateLivraison());
			// à partir de etatCommandeId on recupere l'etat
			vCommandeVenteReportElementValue.setEtatCommande(commandeVenteValue.getEtatCommande_id());
			vCommandeVenteReportElementValue.setPrixTotal(commandeVenteValue.getPrixTotal());
			vCommandeVenteReportElementValue.setReference(commandeVenteValue.getReference());
			vCommandeVenteReportElementValue.setSaison(commandeVenteValue.getSaison());
			vCommandeVenteReportElementValue.setPartieInteressee(commandeVenteValue.getPartieIntersseId());
			vCommandeVenteReportElementValue.setSite(commandeVenteValue.getSiteId());
			// à partir de typeCommandeId on recupere la TypeBC
			vCommandeVenteReportElementValue.setTypeCommande(commandeVenteValue.getTypeCommande_id());

			// Added on 25/03/2016, by Ameni Berrich
			vCommandeVenteReportElementValue.setQuantite(commandeVenteValue.getQuantite());
			vCommandeVenteReportElementValue.setTypeCommande(commandeVenteValue.getTypeCommande_id());

			listeElementCommandeVentes.add(vCommandeVenteReportElementValue);
		}

		vCommandeVenteReportList.setCommandeVenteList(listeElementCommandeVentes);

	}

	@Override
	public ProduitCommandeReportListValue getListProduitCommandeVenteReport(
			RechercheMulticritereProduitCommandeValue request) throws IOException {

		ProduitCommandeReportListValue vProduitCommandeVenteReportList = new ProduitCommandeReportListValue();
		// enrichissement des param du report
		vProduitCommandeVenteReportList.setFileName(REPORT_NAME_PRODUIT_COMMANDE_VENTE_LIST);
		vProduitCommandeVenteReportList.setReportStream(new FileInputStream(
				IConstanteGC.RAPPORTS_GC_BASE_URL + "FAROUK_CommandeVente/produit_commande/All_Produit_CommandeVente_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_ALL_PRODUIT_COMMANDES_VENTE_PATH",
				IConstanteGC.RAPPORTS_GC_BASE_URL + "FAROUK_CommandeVente/produit_commande/All_produitcommandeVente_sub_report.jasper");
		params.put("PATH_LOGO", "/report/logo_enfavet.jpg");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		vProduitCommandeVenteReportList.setParams(params);

		ResultatRechecheProduitCommandeValue result = produitCommandePersistance.rechercherMultiCritere(request);

		Set<ProduitCommandeValue> vProduitCommandeVenteList = new TreeSet<ProduitCommandeValue>(
				result.getListProduitCommandeValue());

		// enrichissement du report
		enrichmentProduitCommandeVenteReportList(vProduitCommandeVenteReportList, vProduitCommandeVenteList, request);

		ArrayList<ProduitCommandeReportListValue> dataList = new ArrayList<ProduitCommandeReportListValue>();
		dataList.add(vProduitCommandeVenteReportList);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		vProduitCommandeVenteReportList.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return vProduitCommandeVenteReportList;
	}

	private void enrichmentProduitCommandeVenteReportList(
			ProduitCommandeReportListValue vProduitCommandeVenteReportList,
			Set<ProduitCommandeValue> vProduitCommandeVenteSet, RechercheMulticritereProduitCommandeValue request) {

		// TODO: completer la methode
		vProduitCommandeVenteReportList.setReferenceBC(request.getRefCommande());
		vProduitCommandeVenteReportList.setClientId(request.getClientId());
		vProduitCommandeVenteReportList.setProduitId(request.getProduitId());
		vProduitCommandeVenteReportList.setDateIntroductionDu(request.getDateCommandeMin());
		vProduitCommandeVenteReportList.setDateIntroductionA(request.getDateCommandeMax());
		vProduitCommandeVenteReportList.setDateLivraisonDu(request.getDateLivraisonMin());
		vProduitCommandeVenteReportList.setDateLivraisonDu(request.getDateLivraisonMax());

		// TODO cache
		List<ProduitValue> listProduit = produitPersistance.listeProduit();
		Map<Long, ProduitValue> mapProduits = new HashMap<Long, ProduitValue>();
		for (ProduitValue produit : listProduit) {
			Long key = produit.getId();
			if (mapProduits.get(key) == null) {
				mapProduits.put(key, produit);
			}
		}

		// TODO cache
		Map<Long, String> mapClientsAbreviations = new HashMap<Long, String>();
		for (PartieInteresseValue client : partieInteresseePersistance.listePartieInteressee()) {
			Long key = client.getId();
			if (mapClientsAbreviations.get(key) == null) {
				mapClientsAbreviations.put(key, client.getAbreviation());
			}
		}

		Long produitId = request.getProduitId();

		if (produitId != null) {
			if (mapProduits.containsKey(produitId)) {
				vProduitCommandeVenteReportList.setProduitReference(mapProduits.get(produitId).getReference());
				vProduitCommandeVenteReportList.setProduitDesignation(mapProduits.get(produitId).getDesignation());
			}
		}

		Long clientId = request.getClientId();

		if (clientId != null) {
			if (mapClientsAbreviations.containsKey(clientId)) {
				vProduitCommandeVenteReportList.setClientAbreviation(mapClientsAbreviations.get(clientId));
			}
		}

		List<DetailsProduitCommandeReportValue> listDetailsProduitCommande = new ArrayList<DetailsProduitCommandeReportValue>();

		// listeProduitCommandeVente
		List<ProduitCommandeVenteReportElementValue> listeElementProduitCommandeVentes = new ArrayList<ProduitCommandeVenteReportElementValue>();
		for (ProduitCommandeValue commandeVenteValue : vProduitCommandeVenteSet) {

			listDetailsProduitCommande = new ArrayList<DetailsProduitCommandeReportValue>();

			ProduitCommandeVenteReportElementValue vProduitCommandeVenteReportElementValue = new ProduitCommandeVenteReportElementValue();
			// à partir de commandeVenteId on recupere la reference & la
			// abreviation Client d'un BC
			vProduitCommandeVenteReportElementValue.setCommandeVenteId(commandeVenteValue.getCommandeVenteId());

			// à partir de produitId on recupere la reference, designation d'un
			// produit
			vProduitCommandeVenteReportElementValue.setProduitId(commandeVenteValue.getProduitId());

			vProduitCommandeVenteReportElementValue.setPrix(commandeVenteValue.getPrix());
			vProduitCommandeVenteReportElementValue.setQuantite(commandeVenteValue.getQuantite());
			vProduitCommandeVenteReportElementValue.setDateLivraison(commandeVenteValue.getDateLivraison());

			vProduitCommandeVenteReportElementValue.setReferenceBC(commandeVenteValue.getRefCommandeVente());

			produitId = commandeVenteValue.getProduitId();

			if (produitId != null) {
				if (mapProduits.containsKey(produitId)) {
					vProduitCommandeVenteReportElementValue
							.setProduitDesignation(mapProduits.get(produitId).getDesignation());
					vProduitCommandeVenteReportElementValue
							.setProduitReference(mapProduits.get(produitId).getReference());
				}
			}

			clientId = commandeVenteValue.getPartieIntersseId();

			if (clientId != null) {
				if (mapClientsAbreviations.containsKey(clientId)) {
					vProduitCommandeVenteReportElementValue.setClientDesignation(mapClientsAbreviations.get(clientId));
				}
			}

			listeElementProduitCommandeVentes.add(vProduitCommandeVenteReportElementValue);
		}
		vProduitCommandeVenteReportList.setProduitCommandeVenteReportList(listeElementProduitCommandeVentes);

		if (request.getProduitId() != null) {

			// TODO cache
			List<CouleurValue> listCouleur = couleurPersistance.listeCouleur();
			Map<Long, String> mapCouleurIdDesignation = new HashMap<Long, String>();
			for (CouleurValue couleur : listCouleur) {
				mapCouleurIdDesignation.put(couleur.getId(), couleur.getDesignation());
			}

			// TODO cache
			List<TailleValue> listTaille = taillePersistance.listeTaille();
			Map<Long, String> mapTailleIdDesignation = new HashMap<Long, String>();
			for (TailleValue taille : listTaille) {
				mapTailleIdDesignation.put(taille.getId(), taille.getDesignation());
			}

			setTailleDesignationToProduitCommandeVenteReportList(vProduitCommandeVenteReportList, listTaille);

			List<DetailsProduitCommandeReportValue> listDetails = new ArrayList<DetailsProduitCommandeReportValue>();

			for (ProduitCommandeValue produitCommandeValue : vProduitCommandeVenteSet) {

				listDetails = new ArrayList<DetailsProduitCommandeReportValue>();

				List<String> listTailles = new ArrayList<String>();
				List<String> listCouleurs = new ArrayList<String>();

				Double montant = ZERO;

				ProduitCommandeReportValue produitCommandeReport = new ProduitCommandeReportValue();

				produitCommandeReport.setDateLivraison(produitCommandeValue.getDateLivraison());
				produitCommandeReport.setPrixUnitaire(produitCommandeValue.getPrix());

				if (mapProduits.containsKey(produitCommandeValue.getProduitId())) {
					produitCommandeReport.setProduitDesignation(
							mapProduits.get(produitCommandeValue.getProduitId()).getDesignation());

					Long sousFamilleId = mapProduits.get(produitCommandeValue.getProduitId()).getSousFamilleId();
					if (sousFamilleId != null) {

						SousFamilleProduitValue sousFamilleProduitValue = sousFamilleProduitPersistance
								.rechercheSousFamilleProduitById(sousFamilleId);
						if (sousFamilleProduitValue != null)
							produitCommandeReport.setSousFamilleDesignation(sousFamilleProduitValue.getDesignation());
					}
				}

				for (DetailsProduitCommandeValue detailsProduitCommandeVlue : produitCommandeValue
						.getListDetailsProduitCommande()) {

					DetailsProduitCommandeReportValue detailsProduitCommandeReport = new DetailsProduitCommandeReportValue();

					detailsProduitCommandeReport.setTailleId(detailsProduitCommandeVlue.getTailleId());

					setQuantite(detailsProduitCommandeReport, detailsProduitCommandeVlue.getQuantite());

					if (produitCommandeReport.getPrixUnitaire() != null) {
						montant = produitCommandeReport.getPrixUnitaire();
					}

					detailsProduitCommandeReport.setMontant(montant);

					if (mapCouleurIdDesignation.containsKey(detailsProduitCommandeVlue.getCouleurId())) {
						detailsProduitCommandeReport.setCouleurDesignation(
								mapCouleurIdDesignation.get(detailsProduitCommandeVlue.getCouleurId()));
					}

					if (mapTailleIdDesignation.containsKey(detailsProduitCommandeVlue.getTailleId())) {
						detailsProduitCommandeReport.setTailleDesignation(
								mapTailleIdDesignation.get(detailsProduitCommandeVlue.getTailleId()));
					}

					listCouleurs.add(detailsProduitCommandeReport.getCouleurDesignation());

					if (!listTailles.contains(detailsProduitCommandeReport.getTailleDesignation())) {
						listTailles.add(detailsProduitCommandeReport.getTailleDesignation());
					}

					listDetailsProduitCommande.add(detailsProduitCommandeReport);
				}

				produitCommandeReport.setQuantiteTotal(produitCommandeValue.getQuantite());

				if (produitCommandeValue.getPrix() != null && produitCommandeValue.getQuantite() != null) {

					produitCommandeReport
							.setPrixTotal(produitCommandeValue.getPrix() * produitCommandeValue.getQuantite());
				}

				// Grouppement par Couleur
				List<DetailsProduitCommandeReportValue> detailsProduit = new ArrayList<DetailsProduitCommandeReportValue>();

				Map<String, List<DetailsProduitCommandeReportValue>> mapDetails = new HashMap<String, List<DetailsProduitCommandeReportValue>>();
				for (DetailsProduitCommandeReportValue detail : listDetailsProduitCommande) {
					String key = detail.getCouleurDesignation();
					if (mapDetails.get(key) == null) {
						mapDetails.put(key, new ArrayList<DetailsProduitCommandeReportValue>());
					}
					mapDetails.get(key).add(detail);
				}

				Iterator itA = mapDetails.entrySet().iterator();
				while (itA.hasNext()) {

					DetailsProduitCommandeReportValue element = new DetailsProduitCommandeReportValue();

					Map.Entry<String, List<DetailsProduitCommandeReportValue>> pair = (Map.Entry<String, List<DetailsProduitCommandeReportValue>>) itA
							.next();
					element.setCouleurDesignation(pair.getKey());

					Long quantiteTD1 = ZEROL;
					Long quantiteTD2 = ZEROL;
					Long quantiteTD3 = ZEROL;
					Long quantiteTD4 = ZEROL;
					Long quantiteTD5 = ZEROL;
					Long quantiteTD6 = ZEROL;

					for (DetailsProduitCommandeReportValue details : pair.getValue()) {
						quantiteTD1 = quantiteTD1
								+ ((details.getQuantiteTD1() != null) ? details.getQuantiteTD1() : ZEROL);
						quantiteTD2 = quantiteTD2
								+ ((details.getQuantiteTD2() != null) ? details.getQuantiteTD2() : ZEROL);
						quantiteTD3 = quantiteTD3
								+ ((details.getQuantiteTD3() != null) ? details.getQuantiteTD3() : ZEROL);
						quantiteTD4 = quantiteTD4
								+ ((details.getQuantiteTD4() != null) ? details.getQuantiteTD4() : ZEROL);
						quantiteTD5 = quantiteTD5
								+ ((details.getQuantiteTD5() != null) ? details.getQuantiteTD5() : ZEROL);
						quantiteTD6 = quantiteTD6
								+ ((details.getQuantiteTD6() != null) ? details.getQuantiteTD6() : ZEROL);

						element.setQuantite(
								quantiteTD1 + quantiteTD2 + quantiteTD3 + quantiteTD4 + quantiteTD5 + quantiteTD6);
						element.setMontant(details.getMontant() * element.getQuantite());
					}

					element.setQuantiteTD1(quantiteTD1);
					element.setQuantiteTD2(quantiteTD2);
					element.setQuantiteTD3(quantiteTD3);
					element.setQuantiteTD4(quantiteTD4);
					element.setQuantiteTD5(quantiteTD5);
					element.setQuantiteTD6(quantiteTD6);

					listDetails.add(element);

				}

				itA.remove();
				mapDetails = new HashMap<String, List<DetailsProduitCommandeReportValue>>();

			}

			Double montantTotal = ZERO;
			Long quantiteTotal = ZEROL;
			for (DetailsProduitCommandeReportValue detail : listDetails) {
				quantiteTotal = quantiteTotal + ((detail.getQuantite() != null) ? detail.getQuantite() : ZEROL);
				montantTotal = montantTotal + ((detail.getMontant() != null) ? detail.getMontant() : ZERO);
			}

			vProduitCommandeVenteReportList.setQuantiteTotal(quantiteTotal);
			vProduitCommandeVenteReportList.setPrixTotal(montantTotal);

			vProduitCommandeVenteReportList.setListDetailsProduitCommande(listDetails);

		}

	}

	private void setTailleDesignationToProduitCommandeVenteReportList(ProduitCommandeReportListValue produit,
			List<TailleValue> listTaille) {

		for (TailleValue taille : listTaille) {
			switch (Integer.valueOf(taille.getId().intValue())) {
			case 1:
				produit.setTailleD1(taille.getDesignation());
				break;
			case 2:
				produit.setTailleD2(taille.getDesignation());
				break;
			case 3:
				produit.setTailleD3(taille.getDesignation());
				break;
			case 4:
				produit.setTailleD4(taille.getDesignation());
				break;
			case 5:
				produit.setTailleD5(taille.getDesignation());
				break;
			case 6:
				produit.setTailleD6(taille.getDesignation());
				break;
			default:
				break;
			}
		}

	}

	@Override
	public BesoinArticleReportValue getAllBesoinArticle() throws IOException {

		BesoinArticleReportValue report = new BesoinArticleReportValue();
		List<BesoinArticleValue> listBesoinArticle = new ArrayList<BesoinArticleValue>();

		report.setFileName(REPORT_NAME_BESOINARTCLE);
		report.setReportStream(new FileInputStream(IConstante.RAPPORTS_COMMUN_BASE_URL +"BesoinArticle/besoin_article_report.jrxml"));

		listBesoinArticle = besoinArticleDomaine.getAllBesoinArticles();

		HashMap<String, Object> params = new HashMap<String, Object>();
		
		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		// enrichissement du report
		enrichmentBesoinArticleReport(listBesoinArticle, report);

		ArrayList<BesoinArticleReportValue> dataList = new ArrayList<BesoinArticleReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	@Override
	public ArticleBesoinReportValue getBesoinProduit(Long produitId) throws IOException {

		ArticleBesoinReportValue report = new ArticleBesoinReportValue();
		List<BesoinArticleVue> listBesoinArticle = new ArrayList<BesoinArticleVue>();

		report.setFileName(REPORT_NAME_BESOINPRODUIT);
		report.setReportStream(new FileInputStream(IConstante.RAPPORTS_COMMUN_BASE_URL + "BesoinProduit/besoin_produits_report.jrxml"));

		listBesoinArticle = besoinArticleDomaine.getBesoinProduit(produitId);

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_BESOINS_PRODUIT_TISSU_PATH",
				IConstante.RAPPORTS_COMMUN_BASE_URL + "BesoinProduit/besoin_produits_tissu_sub_report.jasper");
		params.put("SUBREPORT_BESOINS_PRODUIT_FOURNITURE_PATH",
				IConstante.RAPPORTS_COMMUN_BASE_URL + "BesoinProduit/besoin_produits_fourniture_sub_report.jasper");
		params.put("SUBREPORT_BESOINS_PRODUIT_FIL_PATH",
				IConstante.RAPPORTS_COMMUN_BASE_URL + "BesoinProduit/besoin_produits_tissu_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		// enrichissement du report
		if (listBesoinArticle != null) {
			enrichissementBesoinProduitReport(listBesoinArticle, report);
		}
		ProduitValue produit = produitPersistance.rechercheProduitById(produitId);

		if (produit != null) {

			report.setProduitDesignation(produit.getDesignation());
			report.setProduitReference(produit.getReference());

			if (produit.getPartieIntersseId() != null) {

				PartieInteresseValue client = partieInteresseePersistance.getById(produit.getPartieIntersseId());

				if (client != null) {

					report.setClientAbreviation(client.getAbreviation());
				}
			}
		}

		ArrayList<ArticleBesoinReportValue> dataList = new ArrayList<ArticleBesoinReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);
		logger.info("---------------------Size " + report.getListElementsFourniture().size());

		return report;
	}

	private void enrichissementBesoinProduitReport(List<BesoinArticleVue> listBesoinArticle,
			ArticleBesoinReportValue report) {

		List<ArticleBesoinElementReportValue> ListElements = new ArrayList<ArticleBesoinElementReportValue>();

		for (BesoinArticleVue besoinArticle : listBesoinArticle) {
			ArticleBesoinElementReportValue element = new ArticleBesoinElementReportValue();

			element.setBesoin(besoinArticle.getBesoin());
			element.setArticleDesignation(besoinArticle.getArticleDesignation());
			element.setTailleDesignation(besoinArticle.getTailleDesignation());
			element.setCouleurDesignation(besoinArticle.getCouleurDesignation());
			if (besoinArticle.getTypeArticle() != null) {

				if (besoinArticle.getTypeArticle().equals(ARTICLETYPE_1)) {

					report.getListElementsFourniture().add(element);

				}
				if (besoinArticle.getTypeArticle().equals(ARTICLETYPE_2)) {

					report.getListElementsTissu().add(element);
				}
				if (besoinArticle.getTypeArticle().equals(ARTICLETYPE_3)) {

					report.getListElementsFil().add(element);
				}

			}

		}

	}

	@Override
	public BesoinArticleReportValue rechercheBesoinArticleMulticritere(
			RechercheMulticritereProduitCommandeValue request) throws IOException {

		BesoinArticleReportValue report = new BesoinArticleReportValue();
		List<BesoinArticleValue> listBesoinArticle = new ArrayList<BesoinArticleValue>();

		report.setFileName(REPORT_NAME_BESOINARTCLE);
		report.setReportStream(new FileInputStream(IConstante.RAPPORTS_COMMUN_BASE_URL + "BesoinArticle/besoin_article_report.jrxml"));

		listBesoinArticle = besoinArticleDomaine.rechercheMulticritere(request);

		HashMap<String, Object> params = new HashMap<String, Object>();
		
		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		// enrichissement du report
		enrichmentBesoinArticleReport(listBesoinArticle, report);

		ArrayList<BesoinArticleReportValue> dataList = new ArrayList<BesoinArticleReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichmentBesoinArticleReport(List<BesoinArticleValue> listBesoinArticle,
			BesoinArticleReportValue report) {

		List<BesoinArticleElementReportValue> listBesoinArticleElements = new ArrayList<BesoinArticleElementReportValue>();

		for (BesoinArticleValue besoinArticle : listBesoinArticle) {

			BesoinArticleElementReportValue element = new BesoinArticleElementReportValue();

			element.setBesoin(besoinArticle.getBesoin());
			element.setTotal(besoinArticle.getTotal());

			element.setArticleDesignation(besoinArticle.getArticleDesignation());
			element.setArticleReference(besoinArticle.getArticleReference());
			element.setArticlePrixUnitaire(besoinArticle.getArticlePrixUnitaire());

			element.setSousFamilleDesignation(besoinArticle.getSousFamilleDesignation());

			listBesoinArticleElements.add(element);
		}

		report.setListBesoinArticleElements(listBesoinArticleElements);

	}

	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);

	}

	@Override
	public FactureVenteReportValue getFactureVenteReportValue(Long id,int a) throws IOException {

		FactureVenteReportValue report = new FactureVenteReportValue();
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		
		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		if (a==1) {
			
			// enrechissement des param du report
			report.setFileName(REPORT_NAME_FACTURE_VENTE);
			report.setReportStream(new FileInputStream(IConstanteGC.RAPPORTS_GC_BASE_URL + "LMT/Z_FactureVente/FactureVenteC/facture_vente_report.jrxml"));

			
			params.put("SUBREPORT_FACTURE_VENTE_PATH", IConstanteGC.RAPPORTS_GC_BASE_URL + "LMT/Z_FactureVente/FactureVenteC/facture_vente_sub_report.jasper");
			//TODO params.put("SUBREPORT_FACTURE_DIVERS_VENTE_PATH", IConstanteGC.RAPPORTS_GC_BASE_URL + "Z_FactureVente/FactureVenteC/facture_divers_vente_sub_report.jasper");
			
			report.setParams(params);
			FactureVenteValue factureVenteValue = factureVentePersistance.rechercheFactureVenteValueParId(id);
			//
		}
		if (a==2) {

		// enrechissement des param du report
		report.setFileName(REPORT_NAME_FACTURE_VENTE);
		report.setReportStream(new FileInputStream(IConstanteGC.RAPPORTS_GC_BASE_URL + "LMT/Z_FactureVente2/FactureVenteC/facture_vente_report.jrxml"));

		params.put("SUBREPORT_FACTURE_VENTE_PATH", IConstanteGC.RAPPORTS_GC_BASE_URL + "LMT/Z_FactureVente2/FactureVenteC/facture_vente_sub_report.jasper");

		report.setParams(params);
		}
		
		FactureVenteValue factureVenteValue = factureVentePersistance.rechercheFactureVenteValueParId(id);

		//System.out.println("#### Facture Vente  :"+factureVenteValue.getTotalFacture());
		
		
		// enrichissement du report
		enrichissementFactureVenteReport(report, factureVenteValue, a);

	ArrayList<FactureVenteReportValue> dataList = new ArrayList<FactureVenteReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichissementFactureVenteReport(FactureVenteReportValue report, FactureVenteValue factureVenteValue,int a) {

		// TODO cache
		List<ProduitValue> listProduit = produitPersistance.listeProduit();
		Map<Long, ProduitValue> mapProduits = new HashMap<Long, ProduitValue>();

		for (ProduitValue produit : listProduit) {
			Long key = produit.getId();
			if (mapProduits.get(key) == null) {
				mapProduits.put(key, produit);
			}
		}
		// RefBC
		report.setReferenceCommande(factureVenteValue.getRefCommande());
		// RefBL
		report.setReferenceLivraison(factureVenteValue.getRefLivraison());
		// RefFacture
		report.setReference(factureVenteValue.getReference());

		//logger.info("========== RefBC: " + factureVenteValue.getRefCommande());
		//logger.info("========== RefLiv : " + factureVenteValue.getRefLivraison());
		//logger.info("========== RefFac : " + factureVenteValue.getReference());

		// TODO cache, client data
		if (factureVenteValue.getPartintId() != null) {

			PartieInteresseValue client = partieInteresseePersistance
					.recherchePartieInteresseeParId(factureVenteValue.getPartintId());
			if (client != null) {
				report.setClientAbreviation(client.getAbreviation());
				report.setClientAdresse(client.getAdresse());
				report.setClientTelephone(client.getTelephone());
				report.setClientFax(client.getFax());
				report.setAdresseLivraison(client.getAdresseLiv());
				report.setClientReference(client.getReference());
				report.setClientRaisonSociale(client.getRaisonSociale());
			}

		}

		// Coli, Saison From BL
		RechercheMulticritereLivraisonVenteValue requestBL = new RechercheMulticritereLivraisonVenteValue();

		if (estNonVide(factureVenteValue.getRefLivraison())) {
			requestBL.setReference(factureVenteValue.getRefLivraison());
			ResultatRechecheLivraisonVenteValue livVenteValue = livraisonVentePersistance.rechercherLivraisonVenteMultiCritere(requestBL);

			if (livVenteValue != null) {
				if (livVenteValue.getLivraisonVenteValues() != null) {
					if (livVenteValue.getLivraisonVenteValues().size() > 0) {
						if (livVenteValue.getLivraisonVenteValues().get(0) != null) {
							/** Coli */
							Long coliBL = livVenteValue.getLivraisonVenteValues().get(0).getColis();
							report.setColis(coliBL);
							/** Saision */
							String saison = livVenteValue.getLivraisonVenteValues().get(0).getSaison();
							report.setSaison(saison);
						}
					}
				}
			}
		}
		
		
		report.setObservations(factureVenteValue.getObservations());
		report.setDateFacture(factureVenteValue.getDateFacture());
		report.setDateEcheance(factureVenteValue.getDateEcheance());
		report.setPrixTotal(factureVenteValue.getPrixTotalHT());
		report.setModeReglement(factureVenteValue.getModeReglement());
		report.setPoidsBrut(factureVenteValue.getPoidsBrut());
		report.setPoidsNet(factureVenteValue.getPoidsNet());
    	report.setTotalFacture(factureVenteValue.getTotalFacture());
		report.setTotalColis(factureVenteValue.getTotalColis());
		report.setTotalPalette(factureVenteValue.getTotalPalette());
		// TODO Valeur Ajoutée voir Wided
		report.setValeurAjouteArticle(factureVenteValue.getValeurAjouteArticle());
		report.setValeurMatierePremiere(factureVenteValue.getValeurMatierePremiere());
		report.setOrigineTissus(factureVenteValue.getOrigineTissus());
		report.setIncoterm(factureVenteValue.getIncoterm());
		report.setVolume(factureVenteValue.getVolume());
		
		// report.setColis(factureVenteValue.getColis());

		List<ProduitFactureReportValue> listProduitFacture = new ArrayList<ProduitFactureReportValue>();

		Long quantiteTotale = 0L;
		Double montantTotal = 0D;
		Double montantMP = 0D;
		for (ProduitFactureVenteValue produitFacture : factureVenteValue.getProduitFactureVente()) {

			ProduitFactureReportValue element = new ProduitFactureReportValue();

			element.setDevise(produitFacture.getDevise());

			Long produitId = produitFacture.getProduitId();
			element.setProduitId(produitFacture.getProduitId());
			element.setColis(produitFacture.getColis());
			element.setPalette(produitFacture.getPalette());
			element.setReferenceCommande(produitFacture.getReferenceCommande());
			element.setPrixTMP(produitFacture.getPrixTMP());
			//System.out.println("-----------PrixTMP-----"+produitFacture.getPrixTMP());
			if (produitId != null) {
				if (mapProduits.containsKey(produitId)) {
					element.setProduitReference(mapProduits.get(produitId).getReference());
					element.setProduitDesignation(mapProduits.get(produitId).getDesignation());
					Long sousFamilleProduitId = mapProduits.get(produitId).getSousFamilleId();

					if (sousFamilleProduitId != null) {
						SousFamilleProduitValue sousFamilleProduitValue = sousFamilleProduitPersistance
								.rechercheSousFamilleProduitById(sousFamilleProduitId);
						if (sousFamilleProduitValue != null)
							element.setProduitSousFamilleDesignation(sousFamilleProduitValue.getDesignation());

					}
				}

			}
            
			
			
			
			element.setQuantite(produitFacture.getQuantite());
			element.setPrix(produitFacture.getPrix());
			Double montant = produitFacture.getPrix() * produitFacture.getQuantite();
			element.setMontant(montant);

			quantiteTotale = quantiteTotale + produitFacture.getQuantite();
			montantTotal = montantTotal + montant;
            if(element.getPrixTMP()!=null)
			montantMP=montantMP+element.getPrixTMP();
			listProduitFacture.add(element);
		}

		Double TOTALFACTURE =1000.5D;
		Double SumDivers =0D;

		//TODO Avant 
		
		// DiversFacture
		List<DiversFactureReportValue> listDiversFacture = new ArrayList<DiversFactureReportValue>();

		for (DiversFactureValue diversFactureV : factureVenteValue.getDiversFactureValue()) {

			DiversFactureReportValue elementDiversF = new DiversFactureReportValue();

			elementDiversF.setDesignation(diversFactureV.getDesignation());
			elementDiversF.setPrix(diversFactureV.getPrix());
			elementDiversF.setQuantite(diversFactureV.getQuantite());
			elementDiversF.setId(diversFactureV.getId());
			
			//total DIVERS

			 SumDivers += diversFactureV.getQuantite()*diversFactureV.getPrix();
			 listDiversFacture.add(elementDiversF);
		
		}
		 if (factureVenteValue.getTotalFacture()!=null) {
				
			 if (a==1) {
				  
				 report.setListDiversFacture(listDiversFacture);
				 TOTALFACTURE = factureVenteValue.getTotalFacture();
			 }else 
			 if(a==2){
					
					TOTALFACTURE = factureVenteValue.getTotalFacture() - SumDivers;

				}
			
		}
		
		report.setTotalFacture(TOTALFACTURE);

		report.setQuantiteTotal(quantiteTotale);
		report.setMontantTotal(montantTotal);
        report.setValeurMatierePremiere(montantMP);
		// Calcule des Prix
		// Double prixTotalHT = 0D;
		Double montantTTC = 0D;
		Double montantTVA = 0D;
		Double tauxTVA = 0D;

		tauxTVA = factureVenteValue.getTauxTVA().doubleValue();

		/** montantTTC **/
		if (tauxTVA != null) {
			montantTTC = montantTotal * (1 + (tauxTVA / 100));

			logger.info("== montantTTC " + montantTTC);
		}
		/** montantTVA **/
		if (tauxTVA != null) {
			montantTVA = montantTotal * (tauxTVA / 100);
			logger.info("== montantTVA " + montantTVA);
		}

		report.setPrixTotalHT(montantTotal);
		report.setMontantTTC(montantTTC);
		report.setMontantTVA(montantTVA);
		report.setTauxTVA(tauxTVA.longValue());
		
		String montantTTCToWords = "ZERO EURO ET ZERO CENTIMES";
		
		//System.out.println("##### TOTAL  FACTURE :    "+TOTALFACTURE);
        //TODO Aprés 
		//TODO TOTALFACTURE=55555.555D;
		if(TOTALFACTURE != null){
			BigDecimal bigDecimal = new BigDecimal(TOTALFACTURE);
			BigDecimal bigDecimalScalled = bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP);
			BigDecimal fraction = bigDecimalScalled.remainder(BigDecimal.ONE).multiply(new BigDecimal(100));
			//TODO verifier le cas 0.008
//            //System.out.println("####### CONVERSION  1 :   ");
//            
//            //System.out.println("bigDecimal  :"+bigDecimal);
//            //System.out.println("bigDecimalScalled:  "+bigDecimalScalled);
//            //System.out.println("fraction  :  "+fraction);
//            
//            //System.out.println("####### fin  CONVERSION  1 :   ");
			 
            
			
			
			int euro = bigDecimal.intValue();
			int centimes = fraction.intValue();
		//System.out.println(" euro  :"+euro);
			//System.out.println("centimes  : "+centimes);
			
			
			montantTTCToWords = FrenchNumberToWords.convert(euro).toUpperCase() + EURO + ET
				+ FrenchNumberToWords.convert(centimes).toUpperCase() + CENTIMES;
			
			//montantTTCToWords = "DEUX CENT SOIXANTE-DEUX EURO et HUIT CENTIMES ";
		}
		
		report.setListProduitFacture(listProduitFacture);
		report.setMontantTTCToWords(montantTTCToWords);
	}

}
