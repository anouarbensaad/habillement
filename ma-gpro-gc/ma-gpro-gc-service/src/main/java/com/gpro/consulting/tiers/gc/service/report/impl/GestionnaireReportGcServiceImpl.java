package com.gpro.consulting.tiers.gc.service.report.impl;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.gc.coordination.besoinarticle.value.RechercheMulticritereBesoinArticleValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.ArticleBesoinReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.BesoinArticleReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.CommandeVenteReportListValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.CommandeVenteReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.FactureVenteReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.LivraisonVenteReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.ProduitCommandeReportListValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereCommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereProduitCommandeValue;
import com.gpro.consulting.tiers.gc.domaine.report.IGestionnaireReportGcDomaine;
import com.gpro.consulting.tiers.gc.service.report.IGestionnaireReportGcService;

/**
 * Implementation of {@link IGestionnaireReportService}
 * 
 * @author Wahid Gazzah
 * @since 10/03/2016
 *
 */

@Service
@Transactional
public class GestionnaireReportGcServiceImpl implements IGestionnaireReportGcService{

	@Autowired
	private IGestionnaireReportGcDomaine gestionnaireReportGcDomaine;

	@Override
	public CommandeVenteReportValue getCommandeVenteReportValue(Long id)
			throws IOException {
		
		return gestionnaireReportGcDomaine.getCommandeVenteReportValue(id);
	}

	@Override
	public CommandeVenteReportListValue getListCommandeVenteReport(
			RechercheMulticritereCommandeVenteValue request) throws IOException {
		return gestionnaireReportGcDomaine.getListCommandeVenteReport(request);
	}

	@Override
	public ProduitCommandeReportListValue getListProduitCommandeVenteReport(
			RechercheMulticritereProduitCommandeValue request)
			throws IOException {
		return gestionnaireReportGcDomaine.getListProduitCommandeVenteReport(request);
	}

	@Override
	public BesoinArticleReportValue getAllBesoinArticle() throws IOException {
		
		return gestionnaireReportGcDomaine.getAllBesoinArticle();
	}

	@Override
	public BesoinArticleReportValue rechercheBesoinArticleMulticritere(
			RechercheMulticritereProduitCommandeValue request) throws IOException {
		
		return gestionnaireReportGcDomaine.rechercheBesoinArticleMulticritere(request);
	}


	@Override
	public ArticleBesoinReportValue getBesoinProduit(Long produitId)
			throws IOException {
		
		return gestionnaireReportGcDomaine.getBesoinProduit(produitId);
	}

	@Override
	public LivraisonVenteReportValue getLivraisonVenteReportValue(Long id)
			throws IOException {
		return gestionnaireReportGcDomaine.getLivraisonVenteReportValue(id);
	}

	@Override
	public FactureVenteReportValue getFactureVenteReportValue(Long id, int a)
			throws IOException {
		return gestionnaireReportGcDomaine.getFactureVenteReportValue(id, a);
	}
}
