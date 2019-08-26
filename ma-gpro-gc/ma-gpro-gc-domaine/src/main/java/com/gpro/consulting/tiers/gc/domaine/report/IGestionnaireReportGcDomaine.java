package com.gpro.consulting.tiers.gc.domaine.report;

import java.io.IOException;

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
 * GestionnaireReportDomaine Interface
 * 
 * @author Wahid Gazzah
 * @since 10/03/2016
 *
 */
public interface IGestionnaireReportGcDomaine {

	public CommandeVenteReportValue getCommandeVenteReportValue(Long id) throws IOException;
	
	public CommandeVenteReportListValue getListCommandeVenteReport(
			RechercheMulticritereCommandeVenteValue request) throws IOException;
	
	public ProduitCommandeReportListValue getListProduitCommandeVenteReport(
			RechercheMulticritereProduitCommandeValue request) throws IOException;

	public BesoinArticleReportValue getAllBesoinArticle() throws IOException;

	public BesoinArticleReportValue rechercheBesoinArticleMulticritere(
			RechercheMulticritereProduitCommandeValue request) throws IOException ;
	
	public ArticleBesoinReportValue getBesoinProduit(Long produitId) throws IOException;
	
	public LivraisonVenteReportValue getLivraisonVenteReportValue(Long id) throws IOException;
	
//	public FactureVenteReportValue getFactureVenteReportValue(Long id) throws IOException;

	public FactureVenteReportValue getFactureVenteReportValue(Long id, int a) throws IOException;
	
}
