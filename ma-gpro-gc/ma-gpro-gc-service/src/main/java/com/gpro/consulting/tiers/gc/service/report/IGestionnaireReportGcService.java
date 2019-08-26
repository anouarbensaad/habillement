package com.gpro.consulting.tiers.gc.service.report;

import java.io.IOException;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.ArticleBesoinReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.BesoinArticleReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.CommandeVenteReportListValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.CommandeVenteReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.FactureVenteReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.LivraisonVenteReportValue;
import com.gpro.consulting.tiers.gc.coordination.report.commandevente.value.ProduitCommandeReportListValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereCommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereProduitCommandeValue;

/**
 * GestionnaireReport Service Interface
 * 
 * @author Wahid Gazzah
 * @since 10/03/2016
 *
 */
public interface IGestionnaireReportGcService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	CommandeVenteReportValue getCommandeVenteReportValue(Long id) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public CommandeVenteReportListValue getListCommandeVenteReport(
			RechercheMulticritereCommandeVenteValue request) throws IOException;
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ProduitCommandeReportListValue getListProduitCommandeVenteReport(
			RechercheMulticritereProduitCommandeValue request) throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	BesoinArticleReportValue getAllBesoinArticle() throws IOException;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	BesoinArticleReportValue rechercheBesoinArticleMulticritere(
			RechercheMulticritereProduitCommandeValue request) throws IOException;
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	ArticleBesoinReportValue getBesoinProduit(Long produitId) throws IOException;
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	LivraisonVenteReportValue getLivraisonVenteReportValue(Long id) throws IOException;
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	FactureVenteReportValue getFactureVenteReportValue(Long id, int a) throws IOException;
	

}
