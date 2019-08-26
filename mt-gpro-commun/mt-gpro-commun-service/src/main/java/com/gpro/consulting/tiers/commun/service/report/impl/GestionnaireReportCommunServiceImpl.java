package com.gpro.consulting.tiers.commun.service.report.impl;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.report.value.ArticlesReportListValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.PartieInteresseeReportListValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitBesoinReportValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitsReportListValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.domaine.report.IGestionnaireReportCommunDomaine;
import com.gpro.consulting.tiers.commun.service.report.IGestionnaireReportCommunService;

/**
 * Implementation of {@link IGestionnaireReportCommunService}
 * 
 * @author Wahid Gazzah
 * @since 07/03/2016
 *
 */
@Service
@Transactional
public class GestionnaireReportCommunServiceImpl implements IGestionnaireReportCommunService{

	@Autowired
	private IGestionnaireReportCommunDomaine gestionnaireReportCommunDomaine;

	@Override
	public ProduitsReportListValue getListProduitsReport(
			RechercheMulticritereProduitValue request) throws IOException {
		
		return gestionnaireReportCommunDomaine.getListProduitsReport(request);
	}

	//added on 08/04/2016, by Ameni Berrich
	@Override
	public ArticlesReportListValue getListArticlesReport(
			RecherecheMulticritereArticleValue request) throws IOException {
		return gestionnaireReportCommunDomaine.getListArticlesReport(request);
	}
	
	/*
	 * {@inheritDoc}
	 */
	@Override
	public PartieInteresseeReportListValue getListPIReport(
			RechercheMulticriterePartieInteresseeValue request)
			throws IOException {
		return gestionnaireReportCommunDomaine.getListPIReport(request);
	}
	
	/**
	 * @return the gestionnaireReportCommunDomaine
	 */
	public IGestionnaireReportCommunDomaine getGestionnaireReportCommunDomaine() {
		return gestionnaireReportCommunDomaine;
	}

	/**
	 * @param gestionnaireReportCommunDomaine the gestionnaireReportCommunDomaine to set
	 */
	public void setGestionnaireReportCommunDomaine(
			IGestionnaireReportCommunDomaine gestionnaireReportCommunDomaine) {
		this.gestionnaireReportCommunDomaine = gestionnaireReportCommunDomaine;
	}

	
	/**
	 * @author Zeineb
	 * @since 04/11/2016
	 */
	
	@Override
	public ProduitBesoinReportValue getBesoinProduit(Long produitId) throws IOException {
		return gestionnaireReportCommunDomaine.getBesoinProduit(produitId);
	}

}
