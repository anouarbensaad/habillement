package com.gpro.consulting.tiers.gc.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.SiteValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.domaine.ICouleurDomaine;
import com.gpro.consulting.tiers.commun.domaine.IProduitDomaine;
import com.gpro.consulting.tiers.commun.domaine.ITailleDomaine;
import com.gpro.consulting.tiers.gc.coordination.value.EtatCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.TypeCommandeValue;
import com.gpro.consulting.tiers.gc.domaine.IGestionnaireCacheDomaine;
import com.gpro.consulting.tiers.gc.service.IEtatCommandeService;
import com.gpro.consulting.tiers.gc.service.IGestionnaireGCCacheService;
import com.gpro.consulting.tiers.gc.service.ITypeCommandeService;

/**
 * 
 * @author $Author: $Ameni
 * @version $Revision: 0 $
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GestionnaireGCCacheServiceImpl implements IGestionnaireGCCacheService {

	/** Date initialisation */
	private static Calendar dateInit;

	/** Injection des Bean */
	@Autowired
	private IEtatCommandeService etatCommandeService;
	@Autowired
	private ITypeCommandeService typeCommandeService;
	/****** liste Client ******/
	@Autowired
	private IGestionnaireCacheDomaine gestionnaireCacheDomaine;
/*	
	@Autowired
	ICouleurDomaine couleurDomaine;
	
	@Autowired
	ITailleDomaine tailleDomaine;
	
	@Autowired
	IProduitDomaine produitDomaine;
*/	
	/** Instance Singleton */
	private static GestionnaireGCCacheServiceImpl INSTANCE = new GestionnaireGCCacheServiceImpl();

	/** Logger */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GestionnaireGCCacheServiceImpl.class);

	/** Liste des categories */

	private List<EtatCommandeValue> listeEtatCommandeVente;
	private List<TypeCommandeValue> listeTypeCommandeVente;
	private List<PartieInteresseCacheValue> listeClient = new ArrayList<PartieInteresseCacheValue>();
	private List<SiteValue> listeSite = new ArrayList<SiteValue>();

/*
	public Map<Long, String> mapCouleurIdDesignation;
	public Map<Long, String> mapTailleIdDesignation;
	public Map<Long, ProduitValue> mapProduit;
*/
	@Autowired
	private EhCacheCacheManager cacheManager;

	/** Instationation du singleton */
	public static synchronized IGestionnaireGCCacheService getInstance() {
		return INSTANCE;

	}
  private GestionnaireGCCacheServiceImpl(){
	  
  }
	public void init() {
		dateInit = Calendar.getInstance();

		LOGGER.info("Init EtatCommandeVente");
		initEtatCommandeVenteValeur();

		LOGGER.info("Init TypeCommandeVente");
		initTypeCommandeVenteValeur();

		LOGGER.info("Init ClienPartieInteressee");
		List<PartieInteresseCacheValue> listPartieInteresseCacheValues = gestionnaireCacheDomaine
				.getListePartieInteressee();
		initListPartieInteresseValeur(listPartieInteresseCacheValues);

		LOGGER.info("Init Site");
		List<SiteValue> listSiteCacheValues = gestionnaireCacheDomaine
				.getListeSite();
		initListSiteValeur(listSiteCacheValues);
/*		
		LOGGER.info("Init MapCouleur");
		initMapCouleurIdDesignation();
		
		LOGGER.info("Init MapTaille");
		initMapTailleIdDesignation();
		
		LOGGER.info("Init MapProduit");
		initMapProduit();
*/
	}
/*
	private void initMapProduit() {
		mapProduit = mapProduit();
		
	}
	
	private void initMapTailleIdDesignation() {
		mapTailleIdDesignation = mapTailleIdDesignation();
		
	}
	
	private void initMapCouleurIdDesignation() {
		mapCouleurIdDesignation = mapCouleurIdDesignation();
		
	}
*/
	private void initListSiteValeur(List<SiteValue> listSiteCacheValues) {
		if (listSiteCacheValues != null) {
			for (SiteValue site : listSiteCacheValues) {
				listeSite.add(site);
			}
		}else {
			LOGGER.info("Liste Site Vide. Condition non satisfaite !");
		}

	}

	private void initListPartieInteresseValeur(
			List<PartieInteresseCacheValue> listPartieInteresseCacheValues) {
		for (PartieInteresseCacheValue partieInteresse : listPartieInteresseCacheValues) {
			
			if (partieInteresse.getFamillePartieInteressee() !=null && partieInteresse.getFamillePartieInteressee().equals(1L))
				listeClient.add(partieInteresse);
			else {
				LOGGER.info("Liste Client Vide. Condition non satisfaite !");
			}
		}

	}

	private void initTypeCommandeVenteValeur() {
		listeTypeCommandeVente = typeCommandeService.listeTypeCommande();
	}

	private void initEtatCommandeVenteValeur() {
		listeEtatCommandeVente = etatCommandeService.listeEtatCommande();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gpro.consulting.tiers.commun.service.IGestionnaireCacheService#
	 * rechercherParId(java.lang.Long)
	 */
	@Override
	// Ajout de Type de classe de l'objet valeur appelé
	public Map<String, String> rechercherCommandeParId(Long pIdtype,
			Long pIdClient, Long pIdSite, Long pIdEtat) {

		Map<String, String> mapA = new HashMap<String, String>();
		// Type
		for (TypeCommandeValue type : this.getListeTypeCommandeVente()) {

			if (type.getId().equals(pIdtype)) {
				// returne Type Designation
				mapA.put("type", type.getDesignation());
			}
		}
		// Etat
		for (EtatCommandeValue etat : this.getListeEtatCommandeVente()) {

			if (etat.getId().equals(pIdEtat)) {
				// returne etat Designation
				mapA.put("etat", etat.getDesignation());
			}
		}
		
		// Site
				for (SiteValue site : this.getListeSite()) {

					if (site.getId().equals(pIdSite)) {
						// returne Site Nom
						mapA.put("site", site.getNom());
					}
				} 
		 
		// Client
		for (PartieInteresseCacheValue client : this.getListeClient()) {

			if (client.getId().equals(pIdClient)) {
				// returne client Abreviation
				mapA.put("client", client.getAbreviation());
			}
		}
		return mapA;
	}

	
	@Override
	public String reloadGestionCommerciale() {
		// Get all the active caches
				List<String> caches = new ArrayList(cacheManager.getCacheNames().size());

				Collection<String> cacheNames = cacheManager.getCacheNames();
				Iterator<String> iter = cacheNames.iterator();

				while (iter.hasNext()) {
					// If the cache name has been passed from the request then flush it

					String cacheName = (String) iter.next();

					cacheManager.getCache(cacheName).clear();

					caches.add(cacheName);
				}
				init();
				return "Refresh G.Commerciale ";
	}

	/**************** Getter & Setter *********************/
	/**
	 * @return the etatCommandeService
	 */
	public IEtatCommandeService getEtatCommandeService() {
		return etatCommandeService;
	}

	/**
	 * @param etatCommandeService
	 *            the etatCommandeService to set
	 */
	public void setEtatCommandeService(IEtatCommandeService etatCommandeService) {
		this.etatCommandeService = etatCommandeService;
	}

	/**
	 * @return the typeCommandeService
	 */
	public ITypeCommandeService getTypeCommandeService() {
		return typeCommandeService;
	}

	/**
	 * @param typeCommandeService
	 *            the typeCommandeService to set
	 */
	public void setTypeCommandeService(ITypeCommandeService typeCommandeService) {
		this.typeCommandeService = typeCommandeService;
	}

	/**
	 * @return the listeEtatCommandeVente
	 */
	public List<EtatCommandeValue> getListeEtatCommandeVente() {
		return listeEtatCommandeVente;
	}

	/**
	 * @param listeEtatCommandeVente
	 *            the listeEtatCommandeVente to set
	 */
	public void setListeEtatCommandeVente(
			List<EtatCommandeValue> listeEtatCommandeVente) {
		this.listeEtatCommandeVente = listeEtatCommandeVente;
	}

	/**
	 * @return the listeTypeCommandeVente
	 */
	public List<TypeCommandeValue> getListeTypeCommandeVente() {
		return listeTypeCommandeVente;
	}

	/**
	 * @param listeTypeCommandeVente
	 *            the listeTypeCommandeVente to set
	 */
	public void setListeTypeCommandeVente(
			List<TypeCommandeValue> listeTypeCommandeVente) {
		this.listeTypeCommandeVente = listeTypeCommandeVente;
	}

	/**
	 * @return the cacheManager
	 */
	public EhCacheCacheManager getCacheManager() {
		return cacheManager;
	}

	/**
	 * @param cacheManager
	 *            the cacheManager to set
	 */
	public void setCacheManager(EhCacheCacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public List<PartieInteresseCacheValue> getListeClient() {
		return listeClient;
	}

	/**
	 * @return the gestionnaireCacheDomaine
	 */
	public IGestionnaireCacheDomaine getGestionnaireCacheDomaine() {
		return gestionnaireCacheDomaine;
	}

	/**
	 * @param gestionnaireCacheDomaine
	 *            the gestionnaireCacheDomaine to set
	 */
	public void setGestionnaireCacheDomaine(
			IGestionnaireCacheDomaine gestionnaireCacheDomaine) {
		this.gestionnaireCacheDomaine = gestionnaireCacheDomaine;
	}

	/**
	 * @param listeClient
	 *            the listeClient to set
	 */
	public void setListeClient(List<PartieInteresseCacheValue> listeClient) {
		this.listeClient = listeClient;
	}

	/**
	 * @return the listeSite
	 */
	public List<SiteValue> getListeSite() {
		return listeSite;
	}

	/**
	 * @param listeSite
	 *            the listeSite to set
	 */
	public void setListeSite(List<SiteValue> listeSite) {
		this.listeSite = listeSite;}
/*
	public Map<Long, String> mapCouleurIdDesignation(){
		List<CouleurValue> listCouleur = couleurDomaine.listeCouleur();
		Map<Long, String> mapCouleurIdDesignation = new HashMap<Long, String>();
		for(CouleurValue couleur : listCouleur){
			mapCouleurIdDesignation.put(couleur.getId(), couleur.getDesignation());
		}
		return mapCouleurIdDesignation;
	}
	
	public Map<Long, String> mapTailleIdDesignation(){
		List<TailleValue> listTaille = tailleDomaine.listeTaille();
		Map<Long, String> mapTailleIdDesignation = new HashMap<Long, String>();
		for(TailleValue taille : listTaille){
			mapTailleIdDesignation.put(taille.getId(), taille.getDesignation());
		}
		return mapTailleIdDesignation;
	}
	
	public Map<Long, ProduitValue> mapProduit(){
		List<ProduitValue> listProduit = produitDomaine.listeProduit();
		Map<Long, ProduitValue> mapProduit = new HashMap<Long, ProduitValue>();
		for(ProduitValue produit : listProduit){
			mapProduit.put(produit.getId(), produit);
		}
		return mapProduit;
	}
*/
	
	

}
