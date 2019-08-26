package com.gpro.consulting.tiers.commun.domaine.report;

import java.io.IOException;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitBesoinReportValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ArticlesReportListValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.PartieInteresseeReportListValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitsReportListValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;

/**
 * GestionnaireReportCommunDomaine Interface
 * 
 * @author Wahid Gazzah
 * @since 07/03/2016
 *
 */
public interface IGestionnaireReportCommunDomaine {

	public ProduitsReportListValue getListProduitsReport(
			RechercheMulticritereProduitValue request) throws IOException;

	//added on 08/04/2016, by Ameni Berrich
	public ArticlesReportListValue getListArticlesReport(
			RecherecheMulticritereArticleValue request) throws IOException;
	
	/** Methode de generation d'une Liste de PartieInteressées selon des Critères de recherches
	 * @param RechercheMulticriterePartieInteresseeValue
	 * @return PartieInteresseeReportListValue
	 * @throws IOException
	 * @author Ameni Berrich
	 * @since 23/05/2016
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public PartieInteresseeReportListValue getListPIReport(
			RechercheMulticriterePartieInteresseeValue request) throws IOException;
	
	/**
	 * 
	 * @param produitId
	 * @return
	 * @throws IOException
	 * @author Zeineb
	 * @since 04/11/2016
	 */
	public ProduitBesoinReportValue getBesoinProduit(Long produitId) throws IOException ;
}
