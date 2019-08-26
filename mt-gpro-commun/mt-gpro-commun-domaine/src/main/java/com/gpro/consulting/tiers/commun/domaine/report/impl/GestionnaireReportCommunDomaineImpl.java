package com.gpro.consulting.tiers.commun.domaine.report.impl;

import java.io.FileInputStream;
import java.io.IOException;
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

import com.ctc.wstx.util.StringUtil;
import com.gpro.consulting.tiers.commun.coordination.IConstante;
import com.gpro.consulting.tiers.commun.coordination.report.value.ArticleReportElementValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ArticlesReportListValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.PartieInteresseeElementValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.PartieInteresseeReportListValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitBesoinElementReportValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitBesoinReportValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitReportElementValue;
import com.gpro.consulting.tiers.commun.coordination.report.value.ProduitsReportListValue;
import com.gpro.consulting.tiers.commun.coordination.value.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.ElementBesoinValue;
import com.gpro.consulting.tiers.commun.coordination.value.FamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.FicheBesoinValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechechePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.domaine.report.IGestionnaireReportCommunDomaine;
import com.gpro.consulting.tiers.commun.persistance.IArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.ICouleurPersistance;
import com.gpro.consulting.tiers.commun.persistance.IFamilleArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.IFicheBesoinPersistance;
import com.gpro.consulting.tiers.commun.persistance.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.ISousFamilleArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.ITaillePersistance;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.IBaseInfoPersistance;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Implementation of {@link IGestionnaireReportCommunDomaine}
 * 
 * @author Wahid Gazzah
 * @since 07/03/2016
 *
 */

@Component
public class GestionnaireReportCommunDomaineImpl implements IGestionnaireReportCommunDomaine {

	private static final Logger logger = LoggerFactory.getLogger(GestionnaireReportCommunDomaineImpl.class);

	private static final String REPORT_NAME_PRODUIT_LIST = "produits";
	private static final String REPORT_NAME_ARTICLE_LIST = "articles";
	private static final String REPORT_NAME_PARTIE_INTERESSEE_LIST = "PartieInteressees";
	private static final String REPORT_NAME_BESOINPRODUIT = "besoin produits";
	
	private static final Long ARTICLETYPE_1 = 1L;	//fourniture
	private static final Long ARTICLETYPE_2 = 2L;	//tissu
	private static final Long ARTICLETYPE_3 = 3L;	//fileacoudre

	@Autowired
	private IProduitPersistance produitPersistance;

	@Autowired
	private IArticlePersistance articlePersistance;

	@Autowired
	private IPartieInteresseePersistance partieInteressePersistance;

	@Autowired
	private IFicheBesoinPersistance ficheBesoinPersistance;
	
	@Autowired
	private ISousFamilleArticlePersistance sousFamilleArticlePersistance;
	
	@Autowired
	private IFamilleArticlePersistance familleArticlePersistance;

	
	@Autowired
	private ITaillePersistance ebTaillePersistance;
	
	@Autowired
	private ICouleurPersistance ebCouleurPersistance;

	@Autowired
	private IBaseInfoPersistance baseInfoPersistance;
	
	@Override
	public ProduitsReportListValue getListProduitsReport(RechercheMulticritereProduitValue request) throws IOException {

		ProduitsReportListValue produitsReportList = new ProduitsReportListValue();

		// enrechissement des param du report
		produitsReportList.setFileName(REPORT_NAME_PRODUIT_LIST);
		produitsReportList.setReportStream(new FileInputStream(IConstante.RAPPORTS_COMMUN_BASE_URL + "FAROUK_ProduitList/produits_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_PRODUITS_PATH", IConstante.RAPPORTS_COMMUN_BASE_URL + "/FAROUK_ProduitList/produits_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		produitsReportList.setParams(params);

		ResultatRechecheProduitValue result = produitPersistance.rechercherProduitMultiCritere(request);

		Set<ProduitValue> newProduitsList=new TreeSet<ProduitValue>();
////		TEST de rectification 
//		Map<Long,Long> mapCptArticle =new HashMap<Long, Long>();
//		int compt=0;
//		for (ProduitValue prod:result.getProduitValues()){
//			 mapCptArticle =new HashMap<Long, Long>();
//			if(prod.getCouleurProduit()!=null && prod.getTailleProduit()!=null && prod.isHasFB())
//			{	  compt = prod.getCouleurProduit().size()*prod.getTailleProduit().size();
//			      FicheBesoinValue fiche = ficheBesoinPersistance.rechercheFicheBesoinParId(prod.getId());
//					boolean trouv=false;
//			      if (fiche.getElementBesoinValue().size()>0)
//						for (ElementBesoinValue elt :fiche.getElementBesoinValue())
//						{  if(!mapCptArticle.containsKey(elt.getArticle().getId()))
//							mapCptArticle.put(elt.getArticle().getId(), 1L);
//						   else
//						   {  Long nb=mapCptArticle.get(elt.getArticle().getId());
//						      nb++;
//						      mapCptArticle.put(elt.getArticle().getId(), nb);
//							  //trouv=true; 
//						   }
//							
//						}
//							for (Long i:mapCptArticle.keySet()){
//							    	
//								if(mapCptArticle.get(i)>compt)
//									 newProduitsList.add(prod);
//							}
//							
//							//System.out.println("#######  Produit   :  "+prod.getReference());
//					  
//			}
//		}
		
		
		Set<ProduitValue> produitsList = new TreeSet<ProduitValue>(result.getProduitValues());

		// enrichissement du report
		enrichmentProduitReportList(produitsReportList, produitsList, request);

		ArrayList<ProduitsReportListValue> dataList = new ArrayList<ProduitsReportListValue>();
		dataList.add(produitsReportList);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		produitsReportList.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return produitsReportList;
	}

	private void enrichmentProduitReportList(ProduitsReportListValue produitsReportList, Set<ProduitValue> produitsSet,
			RechercheMulticritereProduitValue request) {

		produitsReportList.setReference(request.getReference());
		produitsReportList.setDesignation(request.getDesignation());
		produitsReportList.setEtat(request.getEtat());
		produitsReportList.setSousfamille(request.getSousfamille());
		produitsReportList.setPartieInteressee(request.getPartieInteressee());
		produitsReportList.setPrixInf(request.getPrix_inf());
		produitsReportList.setPrixSup(request.getPrix_sup());

		List<ProduitReportElementValue> listeElementProduits = new ArrayList<ProduitReportElementValue>();

		for (ProduitValue produitValue : produitsSet) {

			ProduitReportElementValue vProduitReportElementValue = new ProduitReportElementValue();
			vProduitReportElementValue.setDesignation(produitValue.getDesignation());
			vProduitReportElementValue.setEtat(produitValue.getEtat());
			// à partir de sousFamilleId on recupere la familleId
			vProduitReportElementValue.setFamilleId(produitValue.getSousFamilleId());
			vProduitReportElementValue.setPartieInteresseeId(produitValue.getPartieIntersseId());
			vProduitReportElementValue.setPrix(produitValue.getPrixUnitaire());
			vProduitReportElementValue.setReference(produitValue.getReference());
			vProduitReportElementValue.setSiteId(produitValue.getSiteId());
			vProduitReportElementValue.setSousfamilleId(produitValue.getSousFamilleId());

			listeElementProduits.add(vProduitReportElementValue);
		}

		produitsReportList.setProductList(listeElementProduits);
	}

	// ListArticle -report
	@Override
	public ArticlesReportListValue getListArticlesReport(RecherecheMulticritereArticleValue request)
			throws IOException {
		ArticlesReportListValue articlesReportList = new ArticlesReportListValue();

		// enrechissement des param du report
		articlesReportList.setFileName(REPORT_NAME_ARTICLE_LIST);
		articlesReportList.setReportStream(new FileInputStream(IConstante.RAPPORTS_COMMUN_BASE_URL + "/FAROUK_ArticleList/articles_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_ARTICLES_PATH", IConstante.RAPPORTS_COMMUN_BASE_URL + "/FAROUK_ArticleList/articles_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		articlesReportList.setParams(params);

		ResultatRechecheArticleValue result = articlePersistance.rechercherArticleMultiCritere(request);

		Set<ArticleValue> articlesList = new TreeSet<ArticleValue>(result.getArticleValues());

		// enrichissement du report
		enrichmentArticleReportList(articlesReportList, articlesList, request);

		ArrayList<ArticlesReportListValue> dataList = new ArrayList<ArticlesReportListValue>();
		dataList.add(articlesReportList);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		articlesReportList.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return articlesReportList;
	}

	// Enrichissement ListeArticle
	private void enrichmentArticleReportList(ArticlesReportListValue articlesReportList, Set<ArticleValue> articlesSet,
			RecherecheMulticritereArticleValue request) {

		// critere de recherche params
		articlesReportList.setRef(request.getRef());
		articlesReportList.setDesignation(request.getDesignation());
		// articlesReportList.setSousFamilleEntite(request.getSousFamilleEntite());
		articlesReportList.setTypeEntite(request.getTypeEntite());
		articlesReportList.setFamilleEntite(request.getFamilleEntite());
		articlesReportList.setPrix_inf(request.getPrix_inf());
		articlesReportList.setPrix_sup(request.getPrix_sup());
		articlesReportList.setSite(request.getSite());

		// Grid
		List<ArticleReportElementValue> listeElementArticles = new ArrayList<ArticleReportElementValue>();

		for (ArticleValue articleValue : articlesSet) {

			ArticleReportElementValue vArticleReportElementValue = new ArticleReportElementValue();
			vArticleReportElementValue.setReference(articleValue.getRef());
			vArticleReportElementValue.setDesignation(articleValue.getDesignation());
			vArticleReportElementValue.setPu(articleValue.getPu());
			vArticleReportElementValue.setPmp(articleValue.getPmp());
			// sousFamilleId => familleId => typeId
			// puis conversion Id/ Designation (couche Service / Rest)
			vArticleReportElementValue.setSousFamilleArtId(articleValue.getSousFamilleArtEntite());
			// siteId => siteDEsignation
			vArticleReportElementValue.setSiteId(articleValue.getSiteEntite());

			listeElementArticles.add(vArticleReportElementValue);
		}

		articlesReportList.setArticlesList(listeElementArticles);

	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public PartieInteresseeReportListValue getListPIReport(RechercheMulticriterePartieInteresseeValue request)
			throws IOException {
		PartieInteresseeReportListValue partieInteesseeReportList = new PartieInteresseeReportListValue();

		// enrechissement des param du report
		partieInteesseeReportList.setFileName(REPORT_NAME_PARTIE_INTERESSEE_LIST);
		partieInteesseeReportList.setReportStream(new FileInputStream(
				IConstante.RAPPORTS_COMMUN_BASE_URL + "/PartieInteressee/ListePartieInteressee/partieInteressee_List_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUB_PARTIE_INT_REPORT",
				IConstante.RAPPORTS_COMMUN_BASE_URL + "/PartieInteressee/ListePartieInteressee/partieIntersse_sub_List_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		partieInteesseeReportList.setParams(params);
		ResultatRechechePartieInteresseeValue result = partieInteressePersistance
				.rechercherPartieInteresseMultiCritere(request);
		Set<PartieInteresseValue> partieInteresseeList = new TreeSet<PartieInteresseValue>(
				result.getPartieInteresseValues());

		// enrichissement du report
		enrichirPIReportList(partieInteesseeReportList, partieInteresseeList, request);

		ArrayList<PartieInteresseeReportListValue> dataList = new ArrayList<PartieInteresseeReportListValue>();
		dataList.add(partieInteesseeReportList);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		partieInteesseeReportList.setjRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return partieInteesseeReportList;
	}

	private void enrichirPIReportList(PartieInteresseeReportListValue partieInteesseeReportList,
			Set<PartieInteresseValue> partieInteresseeList, RechercheMulticriterePartieInteresseeValue request) {

		partieInteesseeReportList.setReference(request.getvReference());
		partieInteesseeReportList.setRaisonSociale(request.getvRaisonSociale());
		partieInteesseeReportList.setFamillePIId(request.getvFamillePartieInteressee());
		partieInteesseeReportList.setCategoriePIId(request.getvCategoriePartieInteressee());
		partieInteesseeReportList.setTypePIId(request.getvTypePartieInteressee());
		partieInteesseeReportList.setActif(request.getActif());

		// Grid
		List<PartieInteresseeElementValue> listeElement = new ArrayList<PartieInteresseeElementValue>();

		for (PartieInteresseValue piValue : partieInteresseeList) {

			PartieInteresseeElementValue vElementValue = new PartieInteresseeElementValue();
			vElementValue.setReference(piValue.getReference());
			vElementValue.setRaisonSociale(piValue.getRaisonSociale());
			vElementValue.setFamillePartieInteressee(piValue.getFamillePartieInteressee());
			vElementValue.setCategoriePartieInteressee(piValue.getCategoriePartieInteressee());
			vElementValue.setTypePartieInteressee(piValue.getTypePartieInteressee());
			vElementValue.setTelephone(piValue.getTelephone());
			listeElement.add(vElementValue);
		}

		partieInteesseeReportList.setPartieInteresseeList(listeElement);
	}

	/**
	 * @return the produitPersistance
	 */
	public IProduitPersistance getProduitPersistance() {
		return produitPersistance;
	}

	/**
	 * @param produitPersistance
	 *            the produitPersistance to set
	 */
	public void setProduitPersistance(IProduitPersistance produitPersistance) {
		this.produitPersistance = produitPersistance;
	}

	public IArticlePersistance getArticlePersistance() {
		return articlePersistance;
	}

	public void setArticlePersistance(IArticlePersistance articlePersistance) {
		this.articlePersistance = articlePersistance;
	}

	public IPartieInteresseePersistance getPartieInteressePersistance() {
		return partieInteressePersistance;
	}

	public void setPartieInteressePersistance(IPartieInteresseePersistance partieInteressePersistance) {
		this.partieInteressePersistance = partieInteressePersistance;
	}

	/**
	 * 
	 * @param produitId
	 * @return
	 * @throws IOException
	 * @author Zeineb
	 * @since 04/11/2016
	 */

	@Override
	public ProduitBesoinReportValue getBesoinProduit(Long produitId) throws IOException {

		ProduitBesoinReportValue report = new ProduitBesoinReportValue();

		report.setFileName(REPORT_NAME_BESOINPRODUIT);
		report.setReportStream(new FileInputStream(IConstante.RAPPORTS_COMMUN_BASE_URL + "/BesoinProduit/besoin_produits_report.jrxml"));

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_BESOINS_PRODUIT_TISSU_PATH",
				IConstante.RAPPORTS_COMMUN_BASE_URL + "/BesoinProduit/besoin_produits_tissu_sub_report.jasper");
		params.put("SUBREPORT_BESOINS_PRODUIT_FOURNITURE_PATH",
				IConstante.RAPPORTS_COMMUN_BASE_URL + "/BesoinProduit/besoin_produits_fourniture_sub_report.jasper");
		params.put("SUBREPORT_BESOINS_PRODUIT_FIL_PATH",
				IConstante.RAPPORTS_COMMUN_BASE_URL + "/BesoinProduit/besoin_produits_fil_sub_report.jasper");

		String logo = baseInfoPersistance.getLogo();
		params.put("PATH_LOGO", logo);
		
		report.setParams(params);

		// enrichissement du report
		if (produitId != null) {
			enrichissementBesoinProduitReport(produitId, report);
		}

		ArrayList<ProduitBesoinReportValue> dataList = new ArrayList<ProduitBesoinReportValue>();
		dataList.add(report);

		JRBeanCollectionDataSource jRBeanCollectionDataSource = new JRBeanCollectionDataSource(dataList);

		report.setJRBeanCollectionDataSource(jRBeanCollectionDataSource);

		return report;
	}

	private void enrichissementBesoinProduitReport(
			Long produitId,
			ProduitBesoinReportValue report) {
		
			ProduitValue produit = produitPersistance.rechercheProduitById(produitId);
			
				FicheBesoinValue ficheBesoin = ficheBesoinPersistance.rechercheFicheBesoinParId(produitId);

				/**
				 * Key: Map<Long,Double> = Map<articleId,besoin>
				 * Value: ProduitBesoinElementReportValue
				 */
				Map<Object,ProduitBesoinElementReportValue> map = new HashMap<Object,ProduitBesoinElementReportValue>();
				
				/**
				 * Key : articleId
				 * Value: besoin (Qte)
				 */
				Map<Long,Double> mapKey = new HashMap<Long,Double>();
				
				List<String> listTailles = new ArrayList<String>();
				List<String> listCouleurs = new ArrayList<String>();
				
				
				if(ficheBesoin.getElementBesoinValue()!=null){
					for (ElementBesoinValue elementBesoin : ficheBesoin.getElementBesoinValue()) {
						
						if(map.get(mapKey)==null){
							mapKey.put(elementBesoin.getArticle().getId(), elementBesoin.getQuantite());
							listCouleurs.add(elementBesoin.getEb_couleur_id().toString());
							listTailles.add(elementBesoin.getEb_taille_id().toString());
							map.put(mapKey, 
									new ProduitBesoinElementReportValue(
											elementBesoin.getQuantite(), 
											elementBesoin.getArticle().getId(),
											elementBesoin.getArticle().getDesignation(),
											elementBesoin.getArticle().getRef(),
											"",
											"",
											listTailles,
											listCouleurs));
							
							
//							//System.out.println("---------first time--------");
//							//System.out.println("-----mapkey----"+ mapKey.toString());
//							
//							//System.out.println("-----map value----"+ map.get(mapKey).toString());
						}
						else{
	
							mapKey.clear();
							mapKey.put(elementBesoin.getArticle().getId(), elementBesoin.getQuantite());
							
							
							if(map.containsKey(mapKey)){
								
								ProduitBesoinElementReportValue element = map.get(mapKey);
								
								listCouleurs = element.getListCouleurs();
								
								if(!listCouleurs.contains(elementBesoin.getEb_couleur_id().toString())){
									listCouleurs.add(elementBesoin.getEb_couleur_id().toString());
								}
								
								listTailles=element.getListTailles();
								
								if(!listTailles.contains(elementBesoin.getEb_taille_id().toString())){
									listTailles.add(elementBesoin.getEb_taille_id().toString());
								}
								
								element.setListCouleurs(listCouleurs);
								element.setListTailles(listTailles);
									
							}else{
								
								listTailles = new ArrayList<String>();
								listCouleurs = new ArrayList<String>();
								
								listTailles.add(elementBesoin.getEb_taille_id().toString());
								listCouleurs.add(elementBesoin.getEb_couleur_id().toString());
								
								map.put(mapKey, 
										new ProduitBesoinElementReportValue(
												elementBesoin.getQuantite(), 
												elementBesoin.getArticle().getId(),
												elementBesoin.getArticle().getDesignation(),
												elementBesoin.getArticle().getRef(),
												"",
												"",
												listTailles,
												listCouleurs));
								}
							
//							//System.out.println("-----mapkey----"+ mapKey.toString());
//							
//							//System.out.println("-----map value----"+ map.get(mapKey).toString());
							
							}
						
						}
						
						
				}
	
				Iterator it = map.entrySet().iterator();
				
				while(it.hasNext()){
					
					Map.Entry <Object, ProduitBesoinElementReportValue> pair = (Map.Entry<Object, ProduitBesoinElementReportValue>)it.next();
					
					ProduitBesoinElementReportValue element = pair.getValue();
					
					if(produit.getTailleProduit().size() == element.getListTailles().size()){
						element.setTaillesAffiches("G");
					}else{
						
						for (int i = 0; i < element.getListTailles().size(); i++) {
							String tailleId= element.getListTailles().get(i);
							TailleValue taille = ebTaillePersistance.getById(Long.parseLong(tailleId));
							element.getListTailles().set(i, taille.getDesignation());
						}
						
						element.setTaillesAffiches(StringUtil.concatEntries(element.getListTailles(), ",",","));
					}
					
					if(produit.getCouleurProduit().size() == element.getListCouleurs().size()){
						element.setCouleursAffiches("G");
					}else{
						
						for (int i = 0; i < element.getListCouleurs().size(); i++) {
							String couleurId= element.getListCouleurs().get(i);
							CouleurValue couleur = ebCouleurPersistance.getById(Long.parseLong(couleurId));
							element.getListCouleurs().set(i, couleur.getDesignation());
						}
						
						element.setCouleursAffiches(StringUtil.concatEntries(element.getListCouleurs(), ",", ","));
					}
					
					if(element.getArticleId() != null){
			    		
			    		ArticleValue article = articlePersistance.getArticleParId(element.getArticleId() );
			    		
			    		if(article != null){
			    			
			    			if(article.getSousFamilleArtEntite() != null){
			    				
			    				SousFamilleArticleValue sousFamille = sousFamilleArticlePersistance.rechercheSousFamilleArticleById(article.getSousFamilleArtEntite());
			    				
			    				if(sousFamille != null){
			    					
			    					if(sousFamille.getIdFamilleArticle() != null){
			    						
			    						FamilleArticleValue famille = familleArticlePersistance.rechercheFamilleArticleById(sousFamille.getIdFamilleArticle());
			    						
			    						if(famille != null){
			    							
			    							if(famille.getIdTypeArticle() != null){
			    								
			    								if(famille.getIdTypeArticle() == ARTICLETYPE_1){
			    									report.getListElementsFourniture().add(element);
			    								}
	
			    								if(famille.getIdTypeArticle() == ARTICLETYPE_2){
			    									report.getListElementsTissu().add(element);
			    								}
			    								
			    								if(famille.getIdTypeArticle() == ARTICLETYPE_3){
			    									report.getListElementsFil().add(element);
			    								}
			    							}
			    							
			    						}
			    					}
			    				}
			    			}
			    		}
			    	}
					
				}
				
	
		if(produit!=null)
		{
	
			report.setProduitDesignation(produit.getDesignation());
			report.setProduitReference(produit.getReference());
	        report.setObservations(ficheBesoin.getObservation());
			if (produit.getPartieIntersseId() != null) {
	
				PartieInteresseValue client = partieInteressePersistance.getById(produit.getPartieIntersseId());
	
				if (client != null) {
	
					report.setClientAbreviation(client.getAbreviation());
				}
			}
		}
			
	}

	public IFicheBesoinPersistance getFicheBesoinPersistance() {
		return ficheBesoinPersistance;
	}

	public void setFicheBesoinPersistance(IFicheBesoinPersistance ficheBesoinPersistance) {
		this.ficheBesoinPersistance = ficheBesoinPersistance;
	}

	public ISousFamilleArticlePersistance getSousFamilleArticlePersistance() {
		return sousFamilleArticlePersistance;
	}

	public void setSousFamilleArticlePersistance(ISousFamilleArticlePersistance sousFamilleArticlePersistance) {
		this.sousFamilleArticlePersistance = sousFamilleArticlePersistance;
	}

	public IFamilleArticlePersistance getFamilleArticlePersistance() {
		return familleArticlePersistance;
	}

	public void setFamilleArticlePersistance(IFamilleArticlePersistance familleArticlePersistance) {
		this.familleArticlePersistance = familleArticlePersistance;
	}

	public ITaillePersistance getEbTaillePersistance() {
		return ebTaillePersistance;
	}

	public void setEbTaillePersistance(ITaillePersistance ebTaillePersistance) {
		this.ebTaillePersistance = ebTaillePersistance;
	}

	public ICouleurPersistance getEbCouleurPersistance() {
		return ebCouleurPersistance;
	}

	public void setEbCouleurPersistance(ICouleurPersistance ebCouleurPersistance) {
		this.ebCouleurPersistance = ebCouleurPersistance;
	}

	public IBaseInfoPersistance getBaseInfoPersistance() {
		return baseInfoPersistance;
	}

	public void setBaseInfoPersistance(IBaseInfoPersistance baseInfoPersistance) {
		this.baseInfoPersistance = baseInfoPersistance;
	}
	
	

}
