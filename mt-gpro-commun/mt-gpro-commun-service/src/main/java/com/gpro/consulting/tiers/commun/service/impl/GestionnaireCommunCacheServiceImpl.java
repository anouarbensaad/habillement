package com.gpro.consulting.tiers.commun.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.CategorieValue;
import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.DeviseValue;
import com.gpro.consulting.tiers.commun.coordination.value.FamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.FamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.FamilleValue;
import com.gpro.consulting.tiers.commun.coordination.value.GrosseurValue;
import com.gpro.consulting.tiers.commun.coordination.value.MatiereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.MetrageValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.PhaseValue;
import com.gpro.consulting.tiers.commun.coordination.value.SiteValue;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.StandardTailleValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.coordination.value.TypeArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.TypeDocumentValue;
import com.gpro.consulting.tiers.commun.coordination.value.TypeValue;
import com.gpro.consulting.tiers.commun.coordination.value.UniteArticleValue;
import com.gpro.consulting.tiers.commun.service.ICategoriePartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.ICouleurService;
import com.gpro.consulting.tiers.commun.service.IDeviseService;
import com.gpro.consulting.tiers.commun.service.IFamilleArticleService;
import com.gpro.consulting.tiers.commun.service.IFamillePartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.IFamilleProduitService;
import com.gpro.consulting.tiers.commun.service.IGestionnaireCommunCacheService;
import com.gpro.consulting.tiers.commun.service.IGrosseurService;
import com.gpro.consulting.tiers.commun.service.IMatiereService;
import com.gpro.consulting.tiers.commun.service.IMetrageService;
import com.gpro.consulting.tiers.commun.service.IPartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.IPhaseService;
import com.gpro.consulting.tiers.commun.service.ISitePartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.ISousFamilleArticleService;
import com.gpro.consulting.tiers.commun.service.ISousFamilleProduitService;
import com.gpro.consulting.tiers.commun.service.IStandardTailleService;
import com.gpro.consulting.tiers.commun.service.ITailleService;
import com.gpro.consulting.tiers.commun.service.ITypeArticleService;
import com.gpro.consulting.tiers.commun.service.ITypeDocumentService;
import com.gpro.consulting.tiers.commun.service.ITypePartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.IUniteArticleService;

/**
 * 
 * @author $Author: DELL $
 * @version $Revision: 0 $
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GestionnaireCommunCacheServiceImpl implements IGestionnaireCommunCacheService {

	/** Date initialisation */
	private static Calendar dateInit;

	@Autowired
	private ITypeArticleService typeArticleService;
	@Autowired
	private IFamilleArticleService familleArticleService;
	@Autowired
	private ISousFamilleArticleService sousFamilleArticleService;
	@Autowired
	private ISitePartieInteresseeService sitePartieInteresseeService;
	@Autowired
	private IUniteArticleService uniteArticleService;
	@Autowired
	private IMatiereService matiereArticleService;
	@Autowired
	private IMetrageService metrageArticleService;
	@Autowired
	private IGrosseurService grosseurArticleService;
	@Autowired
	private ITypeDocumentService typeDocumentPartieInteresseeService;
	@Autowired
	private IFamillePartieInteresseeService famillePartieInteresseeService;
	@Autowired
	private ICategoriePartieInteresseeService categoriePartieInteresseeService;
	@Autowired
	private ITypePartieInteresseeService typePartieInteresseeService;
	@Autowired
	private IDeviseService devisePartieInteresseeService;
	@Autowired
	private IFamilleProduitService familleProduitService;
	@Autowired
	private ISousFamilleProduitService sousFamilleProduitService;
	@Autowired
	private ICouleurService ebCouleurService;
	@Autowired
	private ITailleService ebTailleService;
	@Autowired
	private IStandardTailleService ebStandardTailleService;
	@Autowired
	private IPhaseService ebPhaseService;
	@Autowired
	private IPartieInteresseeService partieInteresseeService;

	/** Instance Singleton */
	private static GestionnaireCommunCacheServiceImpl INSTANCE = new GestionnaireCommunCacheServiceImpl();

	/** Logger */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GestionnaireCommunCacheServiceImpl.class);

	/** Liste des categories */

	private List<TypeArticleValue> listeTypeArticle;
	private List<FamilleArticleValue> listFamilleArticle;
	private List<SousFamilleArticleValue> listSousFamilleArticle;
	private List<SiteValue> listSitePartieInteressee;
	private List<UniteArticleValue> listUniteArticle;
	private List<MatiereArticleValue> listMatiereArticle;
	private List<MetrageValue> listMetrageArticle;
	private List<GrosseurValue> listGrosseurArticle;
	private List<TypeDocumentValue> listTypeDocument;
	private List<FamilleValue> ListFamillePi;
	private List<CategorieValue> ListCategoriePi;
	private List<TypeValue> ListTypePi;
	private List<DeviseValue> ListDevise;
	private List<FamilleProduitValue> ListFamilleProduit;
	private List<SousFamilleProduitValue> ListSousFamilleProduit;
	private List<CouleurValue> ListCouleurProduit;
	private List<TailleValue> ListTailleProduit;
	private List<StandardTailleValue> ListStandardTailleProduit;
	private List<PhaseValue> ListPhaseProduit;
	private List<PartieInteresseValue> ListPartieInteressee = new ArrayList<PartieInteresseValue>();;

	@Autowired
	private EhCacheCacheManager cacheManager;

	/** Instationation du singleton */
	public static synchronized IGestionnaireCommunCacheService getInstance() {
		return INSTANCE;

	}

	public void init() {
		dateInit = Calendar.getInstance();

		LOGGER.info("Init TypeArticle");
		initTypeArticleValeur();

		LOGGER.info("Init FamilleArticle");
		initFamilleArticleValeur();

		LOGGER.info("Init SousFamilleArticle");
		initSousFamilleArticleValeur();

		LOGGER.info("Init SiteArticle");
		initSitePartieInteresseeValeur();

		LOGGER.info("Init UniteArticle");
		initUniteArticleValeur();

		LOGGER.info("Init MatiereArticle");
		initMatiereArticleValeur();

		LOGGER.info("Init MetrageArticle");
		initMetrageArticleValeur();

		LOGGER.info("Init GrosseurArticle");
		initGrosseurArticleValeur();

		LOGGER.info("Init TypeDocument");
		initTypeDocumentValeur();

		LOGGER.info("Init FamillePi");
		initFamillePiValeur();

		LOGGER.info("Init CategoriePi");
		initCategoriePiValeur();

		LOGGER.info("Init TypePi");
		initTypePiValeur();

		LOGGER.info("Init Devise");
		initDeviseValeur();

		LOGGER.info("Init FamilleProduit");
		initFamilleProduitValeur();

		LOGGER.info("Init SousFamilleProduit");
		initSousFamilleProduitValeur();

		LOGGER.info("Init CouleurProduit");
		initCouleurProduitValeur();

		LOGGER.info("Init TailleProduit");
		initTailleProduitValeur();

		LOGGER.info("Init StandardTailleProduit");
		initStandardTailleProduitValeur();

		LOGGER.info("Init PhaseProduit");
		initPhaseProduitValeur();

		LOGGER.info("Init PartieInteressee");
		initPartieInteresseeValeur();
		
		

	}

	private void initTypeArticleValeur() {
		listeTypeArticle = typeArticleService.listeTypeArticle();
	}

	private void initFamilleArticleValeur() {
		listFamilleArticle = familleArticleService.listeFamilleArticle();
	}

	private void initSousFamilleArticleValeur() {
		listSousFamilleArticle = sousFamilleArticleService
				.listeSousFamilleArticle();
	}

	private void initSitePartieInteresseeValeur() {
		listSitePartieInteressee = sitePartieInteresseeService
				.listeSitePartieInteressee();
	}

	private void initUniteArticleValeur() {
		listUniteArticle = uniteArticleService.listeUniteArticle();
	}

	private void initMatiereArticleValeur() {
		listMatiereArticle = matiereArticleService.listeMatiere();
	}

	private void initMetrageArticleValeur() {
		listMetrageArticle = metrageArticleService.listeMetrage();
	}

	private void initGrosseurArticleValeur() {
		listGrosseurArticle = grosseurArticleService.listeGrosseur();
	}

	private void initTypeDocumentValeur() {
		listTypeDocument = typeDocumentPartieInteresseeService
				.listeTypeDocumentPartieInteressee();
	}

	private void initFamillePiValeur() {
		ListFamillePi = famillePartieInteresseeService
				.listeFamillePartieInteressee();
	}

	private void initCategoriePiValeur() {
		ListCategoriePi = categoriePartieInteresseeService
				.listeCategoriePartieInteressee();
	}

	private void initTypePiValeur() {
		ListTypePi = typePartieInteresseeService.listetypePartieInteressee();
	}

	private void initDeviseValeur() {
		ListDevise = devisePartieInteresseeService.listeDevise();
	}

	private void initFamilleProduitValeur() {
		ListFamilleProduit = familleProduitService.listeFamilleProduit();
	}

	private void initSousFamilleProduitValeur() {
		ListSousFamilleProduit = sousFamilleProduitService
				.listeSousFamilleProduit();

	}

	private void initCouleurProduitValeur() {
		ListCouleurProduit = ebCouleurService.listeCouleur();

	}

	private void initTailleProduitValeur() {
		ListTailleProduit = ebTailleService.listeTaille();

	}

	private void initStandardTailleProduitValeur() {
		ListStandardTailleProduit = ebStandardTailleService
				.listeStandardTaille();

	}

	private void initPhaseProduitValeur() {
		ListPhaseProduit = ebPhaseService.listePhase();

	}

	private void initPartieInteresseeValeur() {
		
		List<PartieInteresseValue> listPartieInteresseValues  = partieInteresseeService.listePartieInteressee();
		
		for (PartieInteresseValue partieInteresse : listPartieInteresseValues) {
					
			if (partieInteresse.getFamillePartieInteressee() !=null && partieInteresse.getFamillePartieInteressee().equals(1L))
				ListPartieInteressee.add(partieInteresse);
			else {
				LOGGER.info("Condition non satisfaite !");
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.commun.service.IGestionnaireCacheService#
	 * rechercherParId(java.lang.Long)
	 */
	@Override
	// Ajout de Type de classe de l'objet valeur appelé
	public Map<String, String> rechercherArticleParId(Long pIdSousFamille,
			Long pIdSite) {

		Map<String, String> mapA = new HashMap<String, String>();

		for (SousFamilleArticleValue sousFamille : this
				.getListSousFamilleArticle()) {

			if (sousFamille.getId().equals(pIdSousFamille)) {
				// returne sousFamille Designation
				mapA.put("sousFamille", sousFamille.getDesignation());
				for (FamilleArticleValue famille : this.getListFamilleArticle()) {
					if (famille.getId().equals(sousFamille.getIdFamilleArticle())){
						// returne famille Designation
						mapA.put("famille", famille.getDesignation());

						// returne type Designation
						for (TypeArticleValue type : this.getListeTypeArticle()) {
							if (type.getId().equals(famille.getIdTypeArticle())){
								// returne type Designation
								mapA.put("type", type.getDesignation());
							}
						}
					}
				}
			}
		}
		
		for (SiteValue site : this.getListSitePartieInteressee()) {

			if (site.getId().equals(pIdSite)) {
				// returne sousFamille Designation
				mapA.put("site", site.getNom());
			}
		}
		return mapA;
	}

	@Override
	// Ajout de Type de classe de l'objet valeur appelé
	public Map<String, String> rechercherProduitParId(Long pIdSousFamille,
			Long pIdSite, Long pIdPI) {

		Map<String, String> mapA = new HashMap<String, String>();

		for (SousFamilleProduitValue sousFamilleProduit : this
				.getListSousFamilleProduit()) {
			// SousFamille, Famille
			if (sousFamilleProduit.getId().equals(pIdSousFamille)) {
				// returne sousFamilleProduit Designation
				mapA.put("sousFamille", sousFamilleProduit.getDesignation());
				for (FamilleProduitValue famille : this.getListFamilleProduit()) {
					if (famille.getId().equals(
							sousFamilleProduit.getFamilleProduitId()))
						// returne famille Designation
						mapA.put("famille", famille.getDesignation());
				}

			}
			// Site
			if(pIdSite!=null)
			if(pIdSite != 0){
			for (SiteValue site : this.getListSitePartieInteressee()) {

				if (site.getId().equals(pIdSite)) {
					// returne sousFamille Designation
					mapA.put("site", site.getNom());
				}
			}
			}
			// PartieInteressee
			if(pIdPI!=null)
			if(pIdPI != 0){
			for (PartieInteresseValue partieInteressee : this
					.getListPartieInteressee()) {

				if (partieInteressee.getId().equals(pIdPI)) {
					// returne PI Abreviation
					mapA.put("partieInteressee",
							partieInteressee.getAbreviation());
				}
			}
			}

		}
		return mapA;
	}

	@Override
	// Ajout de Type de classe de l'objet valeur appelé
	public Map<String, String> rechercherPartieInteresseeParId(Long pIdFamille,
			Long pIdCategorie, Long pIdType) {

		Map<String, String> mapA = new HashMap<String, String>();
		// FamillePi Designation
		for (FamilleValue famillePi : this.getListFamillePi()) {

			if (famillePi.getId().equals(pIdFamille)) {
				// returne FamillePartieInteressee Designation
				mapA.put("famillePi", famillePi.getDesignation());
			}
		}
		// CategoriePi Designation
		for (CategorieValue categorie : this.getListCategoriePi()) {

			if (categorie.getId().equals(pIdCategorie)) {
				// returne Categorie Designation
				mapA.put("categoriePi", categorie.getDesignation());
			}
		}
		// TypePi Designation
		for (TypeValue type : this.getListTypePi()) {

			if (type.getId().equals(pIdType)) {
				// returne Type Designation
				mapA.put("typePi", type.getDesignation());
			}
		}
		return mapA;
	}

	// get Designation Famille et sous famille by Id sous famille



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String reloadReferentiel() {
		// Get all the active caches
		List<String> caches = new ArrayList(cacheManager.getCacheNames().size());

		Collection<String> cacheNames = cacheManager.getCacheNames();
		Iterator<String> iter = cacheNames.iterator();

		while (iter.hasNext()) {
			// If the cache name has been passed from the request then flush it

			String cacheName = (String) iter.next();

			cacheManager.getCache(cacheName).clear();
			// listeCategorie =
			// categorieService.listeCategoriePartieInteressee();

			caches.add(cacheName);
		}
		init();
		return "Done";

	}

	/**
	 * Constructeur de la classe. privé
	 */
	private GestionnaireCommunCacheServiceImpl() {
		// Constructeur vide
	}

	/**
	 * Accesseur en lecture de l'attribut <code>cacheManager</code>.
	 * 
	 * @return EhCacheCacheManager L'attribut cacheManager à lire.
	 */
	public EhCacheCacheManager getCacheManager() {
		return cacheManager;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>cacheManager</code>.
	 *
	 * @param cacheManager
	 *            L'attribut cacheManager à modifier.
	 */
	public void setCacheManager(EhCacheCacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	/**
	 * @return the typeArticleService
	 */
	public ITypeArticleService getTypeArticleService() {
		return typeArticleService;
	}

	/**
	 * @param typeArticleService
	 *            the typeArticleService to set
	 */
	public void setTypeArticleService(ITypeArticleService typeArticleService) {
		this.typeArticleService = typeArticleService;
	}

	/**
	 * @return the sousFamilleArticleService
	 */
	public ISousFamilleArticleService getSousFamilleArticleService() {
		return sousFamilleArticleService;
	}

	/**
	 * @param sousFamilleArticleService
	 *            the sousFamilleArticleService to set
	 */
	public void setSousFamilleArticleService(
			ISousFamilleArticleService sousFamilleArticleService) {
		this.sousFamilleArticleService = sousFamilleArticleService;
	}

	/**
	 * @return the listeTypeArticle
	 */
	public List<TypeArticleValue> getListeTypeArticle() {
		return listeTypeArticle;
	}

	/**
	 * @param listeTypeArticle
	 *            the listeTypeArticle to set
	 */
	public void setListeTypeArticle(List<TypeArticleValue> listeTypeArticle) {
		this.listeTypeArticle = listeTypeArticle;
	}

	/**
	 * @return the familleArticleService
	 */
	public IFamilleArticleService getFamilleArticleService() {
		return familleArticleService;
	}

	/**
	 * @param familleArticleService
	 *            the familleArticleService to set
	 */
	public void setFamilleArticleService(
			IFamilleArticleService familleArticleService) {
		this.familleArticleService = familleArticleService;
	}

	/**
	 * @return the listFamilleArticle
	 */
	
	@SuppressWarnings("null")
	public List<FamilleArticleValue> getListFamilleArticle() {
		List<SousFamilleArticleValue>  vListSousFamilleValue =  this.getListSousFamilleArticle();
		List<TypeArticleValue> vTypeArticle = this.getListeTypeArticle();
		if(listFamilleArticle != null){
			for(FamilleArticleValue vItemFamilleValue : listFamilleArticle){
				Set<SousFamilleArticleValue> vSousFamilleArticle = new HashSet<SousFamilleArticleValue>();
				//get Designation Type
				for(TypeArticleValue vItemType : vTypeArticle){
					if(vItemType.getId().equals(vItemFamilleValue.getIdTypeArticle()))
						vItemFamilleValue.setDesignationType(vItemType.getDesignation());
				}
			
				//get List Sous Famille 
				for(SousFamilleArticleValue vItemSousFamilleValue : vListSousFamilleValue){
				if ( vItemSousFamilleValue.getIdFamilleArticle().equals(vItemFamilleValue.getId())) {
					vSousFamilleArticle.add(vItemSousFamilleValue);
			}}
				vItemFamilleValue.setSousFamille(vSousFamilleArticle);
			}
		}
		return listFamilleArticle;
	}

	/**
	 * @param listFamilleArticle
	 *            the listFamilleArticle to set
	 */
	public void setListFamilleArticle(
			List<FamilleArticleValue> listFamilleArticle) {
		this.listFamilleArticle = listFamilleArticle;
	}

	/**
	 * @return the listSousFamilleArticle
	 */
	public List<SousFamilleArticleValue> getListSousFamilleArticle() {
		return listSousFamilleArticle;
	}

	/**
	 * @param listSousFamilleArticle
	 *            the listSousFamilleArticle to set
	 */
	public void setListSousFamilleArticle(
			List<SousFamilleArticleValue> listSousFamilleArticle) {
		this.listSousFamilleArticle = listSousFamilleArticle;
	}

	/**
	 * @return the sitePartieInteresseeService
	 */
	public ISitePartieInteresseeService getSitePartieInteresseeService() {
		return sitePartieInteresseeService;
	}

	/**
	 * @param sitePartieInteresseeService
	 *            the sitePartieInteresseeService to set
	 */
	public void setSitePartieInteresseeService(
			ISitePartieInteresseeService sitePartieInteresseeService) {
		this.sitePartieInteresseeService = sitePartieInteresseeService;
	}

	/**
	 * @return the listSitePartieInteressee
	 */
	public List<SiteValue> getListSitePartieInteressee() {
		return listSitePartieInteressee;
	}

	/**
	 * @param listSitePartieInteressee
	 *            the listSitePartieInteressee to set
	 */
	public void setListSitePartieInteressee(
			List<SiteValue> listSitePartieInteressee) {
		this.listSitePartieInteressee = listSitePartieInteressee;
	}

	/**
	 * @return the uniteArticleService
	 */
	public IUniteArticleService getUniteArticleService() {
		return uniteArticleService;
	}

	/**
	 * @param uniteArticleService
	 *            the uniteArticleService to set
	 */
	public void setUniteArticleService(IUniteArticleService uniteArticleService) {
		this.uniteArticleService = uniteArticleService;
	}

	/**
	 * @return the listUniteArticle
	 */
	public List<UniteArticleValue> getListUniteArticle() {
		return listUniteArticle;
	}

	/**
	 * @param listUniteArticle
	 *            the listUniteArticle to set
	 */
	public void setListUniteArticle(List<UniteArticleValue> listUniteArticle) {
		this.listUniteArticle = listUniteArticle;
	}

	/**
	 * @return the matiereArticleService
	 */
	public IMatiereService getMatiereArticleService() {
		return matiereArticleService;
	}

	/**
	 * @param matiereArticleService
	 *            the matiereArticleService to set
	 */
	public void setMatiereArticleService(IMatiereService matiereArticleService) {
		this.matiereArticleService = matiereArticleService;
	}

	/**
	 * @return the metrageArticleService
	 */
	public IMetrageService getMetrageArticleService() {
		return metrageArticleService;
	}

	/**
	 * @param metrageArticleService
	 *            the metrageArticleService to set
	 */
	public void setMetrageArticleService(IMetrageService metrageArticleService) {
		this.metrageArticleService = metrageArticleService;
	}

	/**
	 * @return the grosseurArticleService
	 */
	public IGrosseurService getGrosseurArticleService() {
		return grosseurArticleService;
	}

	/**
	 * @param grosseurArticleService
	 *            the grosseurArticleService to set
	 */
	public void setGrosseurArticleService(
			IGrosseurService grosseurArticleService) {
		this.grosseurArticleService = grosseurArticleService;
	}

	/**
	 * @return the typeDocumentPartieInteresseeService
	 */
	public ITypeDocumentService getTypeDocumentPartieInteresseeService() {
		return typeDocumentPartieInteresseeService;
	}

	/**
	 * @param typeDocumentPartieInteresseeService
	 *            the typeDocumentPartieInteresseeService to set
	 */
	public void setTypeDocumentPartieInteresseeService(
			ITypeDocumentService typeDocumentPartieInteresseeService) {
		this.typeDocumentPartieInteresseeService = typeDocumentPartieInteresseeService;
	}

	/**
	 * @return the listMatiereArticle
	 */
	public List<MatiereArticleValue> getListMatiereArticle() {
		return listMatiereArticle;
	}

	/**
	 * @param listMatiereArticle
	 *            the listMatiereArticle to set
	 */
	public void setListMatiereArticle(
			List<MatiereArticleValue> listMatiereArticle) {
		this.listMatiereArticle = listMatiereArticle;
	}

	/**
	 * @return the listMetrageArticle
	 */
	public List<MetrageValue> getListMetrageArticle() {
		return listMetrageArticle;
	}

	/**
	 * @param listMetrageArticle
	 *            the listMetrageArticle to set
	 */
	public void setListMetrageArticle(List<MetrageValue> listMetrageArticle) {
		this.listMetrageArticle = listMetrageArticle;
	}

	/**
	 * @return the listGrosseurArticle
	 */
	public List<GrosseurValue> getListGrosseurArticle() {
		return listGrosseurArticle;
	}

	/**
	 * @param listGrosseurArticle
	 *            the listGrosseurArticle to set
	 */
	public void setListGrosseurArticle(List<GrosseurValue> listGrosseurArticle) {
		this.listGrosseurArticle = listGrosseurArticle;
	}

	/**
	 * @return the listTypeDocument
	 */
	public List<TypeDocumentValue> getListTypeDocument() {
		return listTypeDocument;
	}

	/**
	 * @param listTypeDocument
	 *            the listTypeDocument to set
	 */
	public void setListTypeDocument(List<TypeDocumentValue> listTypeDocument) {
		this.listTypeDocument = listTypeDocument;
	}

	/**
	 * @return the famillePartieInteresseeService
	 */
	public IFamillePartieInteresseeService getFamillePartieInteresseeService() {
		return famillePartieInteresseeService;
	}

	/**
	 * @param famillePartieInteresseeService
	 *            the famillePartieInteresseeService to set
	 */
	public void setFamillePartieInteresseeService(
			IFamillePartieInteresseeService famillePartieInteresseeService) {
		this.famillePartieInteresseeService = famillePartieInteresseeService;
	}

	/**
	 * @return the listFamillePi
	 */
	public List<FamilleValue> getListFamillePi() {
		return ListFamillePi;
	}

	/**
	 * @param listFamillePi
	 *            the listFamillePi to set
	 */
	public void setListFamillePi(List<FamilleValue> listFamillePi) {
		ListFamillePi = listFamillePi;
	}

	/**
	 * @return the categoriePartieInteresseeService
	 */
	public ICategoriePartieInteresseeService getCategoriePartieInteresseeService() {
		return categoriePartieInteresseeService;
	}

	/**
	 * @param categoriePartieInteresseeService
	 *            the categoriePartieInteresseeService to set
	 */
	public void setCategoriePartieInteresseeService(
			ICategoriePartieInteresseeService categoriePartieInteresseeService) {
		this.categoriePartieInteresseeService = categoriePartieInteresseeService;
	}

	/**
	 * @return the listCategoriePi
	 */
	public List<CategorieValue> getListCategoriePi() {
		return ListCategoriePi;
	}

	/**
	 * @param listCategoriePi
	 *            the listCategoriePi to set
	 */
	public void setListCategoriePi(List<CategorieValue> listCategoriePi) {
		ListCategoriePi = listCategoriePi;
	}

	/**
	 * @return the typePartieInteresseeService
	 */
	public ITypePartieInteresseeService getTypePartieInteresseeService() {
		return typePartieInteresseeService;
	}

	/**
	 * @param typePartieInteresseeService
	 *            the typePartieInteresseeService to set
	 */
	public void setTypePartieInteresseeService(
			ITypePartieInteresseeService typePartieInteresseeService) {
		this.typePartieInteresseeService = typePartieInteresseeService;
	}

	/**
	 * @return the listTypePi
	 */
	public List<TypeValue> getListTypePi() {
		return ListTypePi;
	}

	/**
	 * @param listTypePi
	 *            the listTypePi to set
	 */
	public void setListTypePi(List<TypeValue> listTypePi) {
		ListTypePi = listTypePi;
	}

	/**
	 * @return the devisePartieInteresseeService
	 */
	public IDeviseService getDevisePartieInteresseeService() {
		return devisePartieInteresseeService;
	}

	/**
	 * @param devisePartieInteresseeService
	 *            the devisePartieInteresseeService to set
	 */
	public void setDevisePartieInteresseeService(
			IDeviseService devisePartieInteresseeService) {
		this.devisePartieInteresseeService = devisePartieInteresseeService;
	}

	/**
	 * @return the listDevise
	 */
	public List<DeviseValue> getListDevise() {
		return ListDevise;
	}

	/**
	 * @param listDevise
	 *            the listDevise to set
	 */
	public void setListDevise(List<DeviseValue> listDevise) {
		ListDevise = listDevise;
	}

	/**
	 * @return the familleProduitService
	 */
	public IFamilleProduitService getFamilleProduitService() {
		return familleProduitService;
	}

	/**
	 * @param familleProduitService
	 *            the familleProduitService to set
	 */
	public void setFamilleProduitService(
			IFamilleProduitService familleProduitService) {
		this.familleProduitService = familleProduitService;
	}

	/**
	 * @return the listFamilleProduit
	 */
	public List<FamilleProduitValue> getListFamilleProduit() {
		List<SousFamilleProduitValue>  vListSousFamilleValue =  this.getListSousFamilleProduit();
		if(ListFamilleProduit != null){
			for(FamilleProduitValue vItemFamilleValue : ListFamilleProduit){
				Set<SousFamilleProduitValue> vSousFamilleProduit = new HashSet<SousFamilleProduitValue>();
			
				//get List Sous Famille 
				for(SousFamilleProduitValue vItemSousFamilleValue : vListSousFamilleValue){
				if ( vItemSousFamilleValue.getFamilleProduitId().equals(vItemFamilleValue.getId())) {
					vSousFamilleProduit.add(vItemSousFamilleValue);
			}}
				vItemFamilleValue.setSousFamille(vSousFamilleProduit);
			}
		}
		
		return ListFamilleProduit;
	}

	/**
	 * @param listFamilleProduit
	 *            the listFamilleProduit to set
	 */
	public void setListFamilleProduit(
			List<FamilleProduitValue> listFamilleProduit) {
		ListFamilleProduit = listFamilleProduit;
	}

	/**
	 * @return the sousFamilleProduitService
	 */
	public ISousFamilleProduitService getSousFamilleProduitService() {
		return sousFamilleProduitService;
	}

	/**
	 * @param sousFamilleProduitService
	 *            the sousFamilleProduitService to set
	 */
	public void setSousFamilleProduitService(
			ISousFamilleProduitService sousFamilleProduitService) {
		this.sousFamilleProduitService = sousFamilleProduitService;
	}

	/**
	 * @return the listSousFamilleProduit
	 */
	public List<SousFamilleProduitValue> getListSousFamilleProduit() {
		return ListSousFamilleProduit;
	}

	/**
	 * @param listSousFamilleProduit
	 *            the listSousFamilleProduit to set
	 */
	public void setListSousFamilleProduit(
			List<SousFamilleProduitValue> listSousFamilleProduit) {
		ListSousFamilleProduit = listSousFamilleProduit;
	}

	/**
	 * @return the ebCouleurService
	 */
	public ICouleurService getEbCouleurService() {
		return ebCouleurService;
	}

	/**
	 * @param ebCouleurService
	 *            the ebCouleurService to set
	 */
	public void setEbCouleurService(ICouleurService ebCouleurService) {
		this.ebCouleurService = ebCouleurService;
	}

	/**
	 * @return the listCouleurProduit
	 */
	public List<CouleurValue> getListCouleurProduit() {
		return ListCouleurProduit;
	}

	/**
	 * @param listCouleurProduit
	 *            the listCouleurProduit to set
	 */
	public void setListCouleurProduit(List<CouleurValue> listCouleurProduit) {
		ListCouleurProduit = listCouleurProduit;
	}

	/**
	 * @return the ebTailleService
	 */
	public ITailleService getEbTailleService() {
		return ebTailleService;
	}

	/**
	 * @param ebTailleService
	 *            the ebTailleService to set
	 */
	public void setEbTailleService(ITailleService ebTailleService) {
		this.ebTailleService = ebTailleService;
	}

	/**
	 * @return the listTailleProduit
	 */
	public List<TailleValue> getListTailleProduit() {
		return ListTailleProduit;
	}

	/**
	 * @param listTailleProduit
	 *            the listTailleProduit to set
	 */
	public void setListTailleProduit(List<TailleValue> listTailleProduit) {
		ListTailleProduit = listTailleProduit;
	}

	/**
	 * @return the ebStandardTailleService
	 */
	public IStandardTailleService getEbStandardTailleService() {
		return ebStandardTailleService;
	}

	/**
	 * @param ebStandardTailleService
	 *            the ebStandardTailleService to set
	 */
	public void setEbStandardTailleService(
			IStandardTailleService ebStandardTailleService) {
		this.ebStandardTailleService = ebStandardTailleService;
	}

	/**
	 * @return the listStandardTailleProduit
	 */
	public List<StandardTailleValue> getListStandardTailleProduit() {
		return ListStandardTailleProduit;
	}

	/**
	 * @param listStandardTailleProduit
	 *            the listStandardTailleProduit to set
	 */
	public void setListStandardTailleProduit(
			List<StandardTailleValue> listStandardTailleProduit) {
		ListStandardTailleProduit = listStandardTailleProduit;
	}

	/**
	 * @return the ebPhaseService
	 */
	public IPhaseService getEbPhaseService() {
		return ebPhaseService;
	}

	/**
	 * @param ebPhaseService
	 *            the ebPhaseService to set
	 */
	public void setEbPhaseService(IPhaseService ebPhaseService) {
		this.ebPhaseService = ebPhaseService;
	}

	/**
	 * @return the listPhaseProduit
	 */
	public List<PhaseValue> getListPhaseProduit() {
		return ListPhaseProduit;
	}

	/**
	 * @param listPhaseProduit
	 *            the listPhaseProduit to set
	 */
	public void setListPhaseProduit(List<PhaseValue> listPhaseProduit) {
		ListPhaseProduit = listPhaseProduit;
	}

	/**
	 * @return the partieInteresseeService
	 */
	public IPartieInteresseeService getPartieInteresseeService() {
		return partieInteresseeService;
	}

	/**
	 * @param partieInteresseeService
	 *            the partieInteresseeService to set
	 */
	public void setPartieInteresseeService(
			IPartieInteresseeService partieInteresseeService) {
		this.partieInteresseeService = partieInteresseeService;
	}

	/**
	 * @return the listPartieInteressee
	 */
	public List<PartieInteresseValue> getListPartieInteressee() {
		return ListPartieInteressee;
	}

	/**
	 * @param listPartieInteressee
	 *            the listPartieInteressee to set
	 */
	public void setListPartieInteressee(
			List<PartieInteresseValue> listPartieInteressee) {
		ListPartieInteressee = listPartieInteressee;
	}
	
	@Override
	public Map<String, String> rechFamEtSousFamParId(Long pIdSousFamille) {
		Map<String, String> mapA = new HashMap<String, String>();
		Long familleId = null;
		// Sous Famille Article Designation
		for (SousFamilleArticleValue sousFamilleA : this
				.getListSousFamilleArticle()) {

			if (sousFamilleA.getId().equals(pIdSousFamille)) {
				// retourne SousFamille Article Designation
				mapA.put("sousFamilleDesignation",
						sousFamilleA.getDesignation());
				//retourne Famille Article Desigation
				familleId = sousFamilleA.getIdFamilleArticle();
			}
		}
		for (FamilleArticleValue FamilleA : this
				.getListFamilleArticle()) {
			if (FamilleA.getId().equals(familleId)) {
				mapA.put("FamilleDesignation",
						FamilleA.getDesignation());
			}
		}
		return mapA;
	}	
	
	/**
	 * 
	 * @param pIdTaill
	 * @return
	 */
	public String rechercheTailleParId(Long pIdTaill) {
		
		String vResponse = null;
		List<TailleValue>  vListTailleValue =  this.getListTailleProduit();	
		for(TailleValue vItemTailleValue : vListTailleValue){
			if ( vItemTailleValue.getId().equals(pIdTaill) ) 
				vResponse =  vItemTailleValue.getDesignation();
			
		}
		return vResponse;
	}	
	/**
	 * @param pIdColor
	 * @return
	 */
	public String rechercheColorParId(Long pIdColor) {

		String vResponse = null;
		List<CouleurValue>  vListColorValue =  this.getListCouleurProduit();	
		for(CouleurValue vItemColorValue : vListColorValue){
			if ( vItemColorValue.getId().equals(pIdColor) ) 
				vResponse = vItemColorValue.getDesignation();
			
		}
		return vResponse;
	}
	
	
	
}
