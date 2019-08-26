/**
 * 
 */
package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ChaineValue;
import com.gpro.consulting.tiers.gpao.coordination.value.StatutOfValue;
import com.gpro.consulting.tiers.gpao.domaine.IGestionnaireCacheDomaine;
import com.gpro.consulting.tiers.gpao.service.IChaineService;
import com.gpro.consulting.tiers.gpao.service.IGestionnaireCacheService;
import com.gpro.consulting.tiers.gpao.service.IStatutService;

/**
 * @author $Ameni
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GestionnaireCacheServiceImpl implements IGestionnaireCacheService {

	/** Date initialisation */
	private static Calendar dateInit;

	/** Injection des Bean */
	@Autowired
	private IStatutService statutService;
	@Autowired
	private IChaineService chaineService;

	/** invocation du service des Clients (du module mt-gpro-commun) */
	@Autowired
	private IGestionnaireCacheDomaine gestionnaireCacheDomaine;
	@Autowired
	private EhCacheCacheManager cacheManager;

	/** Instance Singleton */
	private static GestionnaireCacheServiceImpl INSTANCE = new GestionnaireCacheServiceImpl();

	/** Liste des Clients */
	private List<PartieInteresseCacheValue> listeClient = new ArrayList<PartieInteresseCacheValue>();
	
	/** Liste des ChaineOF */
	private List<ChaineValue> listeChaineOf;
	/** Liste des StatutOF */
	private List<StatutOfValue> listeStatutOf;

	/** Logger */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GestionnaireCacheServiceImpl.class);

	/** Instationation du singleton */
	public static synchronized IGestionnaireCacheService getInstance() {
		return INSTANCE;

	}

	private GestionnaireCacheServiceImpl() {

	}

	@Override
	public void init() {
		dateInit = Calendar.getInstance();

		//LOGGER.info("Init ClienPartieInteressee en cours!");
		List<PartieInteresseCacheValue> listPartieInteresseCacheValues = gestionnaireCacheDomaine
				.getListePartieInteressee();
		initListPartieInteresseValeur(listPartieInteresseCacheValues);

		//LOGGER.info("Init StatutOf");
		initStatutValeur();

		//LOGGER.info("Init ChaineOf");
		initChaineValeur();

	}

	private void initListPartieInteresseValeur(
			List<PartieInteresseCacheValue> listPartieInteresseCacheValues) {
		for (PartieInteresseCacheValue partieInteresse : listPartieInteresseCacheValues) {

			if (partieInteresse.getFamillePartieInteressee() != null
					&& partieInteresse.getFamillePartieInteressee().equals(1L))
				listeClient.add(partieInteresse);
			else {
				//LOGGER.info("Liste Client Vide. Condition non satisfaite !");
			}
		}

	}

	
	private void initStatutValeur() {
		listeStatutOf = statutService.listeStatut();
	}

	private void initChaineValeur() {
		listeChaineOf = chaineService.listeChaine();
	}

	@Override
	public Map<String, String> rechercherProduitEtatOFParId( Long pIdEtatOF) {

		Map<String, String> mapA = new HashMap<String, String>();
		
		// Etat (StatutOF)
				for (StatutOfValue etat : this.getListeStatutOf()) {

					if (etat.getId().equals(pIdEtatOF)) {
						// retourne etat Designation
						mapA.put("etatOF", etat.getDesignation());
					}
				}
		return mapA;
	}

	/**************** Getter & Setter *********************/
	/**
	 * @return the statutService
	 */
	public IStatutService getStatutService() {
		return statutService;
	}

	/**
	 * @param statutService the statutService to set
	 */
	public void setStatutService(IStatutService statutService) {
		this.statutService = statutService;
	}

	/**
	 * @return the chaineService
	 */
	public IChaineService getChaineService() {
		return chaineService;
	}

	/**
	 * @param chaineService the chaineService to set
	 */
	public void setChaineService(IChaineService chaineService) {
		this.chaineService = chaineService;
	}

	/**
	 * @return the gestionnaireCacheDomaine
	 */
	public IGestionnaireCacheDomaine getGestionnaireCacheDomaine() {
		return gestionnaireCacheDomaine;
	}

	/**
	 * @param gestionnaireCacheDomaine the gestionnaireCacheDomaine to set
	 */
	public void setGestionnaireCacheDomaine(
			IGestionnaireCacheDomaine gestionnaireCacheDomaine) {
		this.gestionnaireCacheDomaine = gestionnaireCacheDomaine;
	}

	/**
	 * @return the cacheManager
	 */
	public EhCacheCacheManager getCacheManager() {
		return cacheManager;
	}

	/**
	 * @param cacheManager the cacheManager to set
	 */
	public void setCacheManager(EhCacheCacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	/**
	 * @return the listeClient
	 */
	public List<PartieInteresseCacheValue> getListeClient() {
		return listeClient;
	}

	/**
	 * @param listeClient the listeClient to set
	 */
	public void setListeClient(List<PartieInteresseCacheValue> listeClient) {
		this.listeClient = listeClient;
	}

	/**
	 * @return the listeChaineOf
	 */
	public List<ChaineValue> getListeChaineOf() {
		return listeChaineOf;
	}

	/**
	 * @param listeChaineOf the listeChaineOf to set
	 */
	public void setListeChaineOf(List<ChaineValue> listeChaineOf) {
		this.listeChaineOf = listeChaineOf;
	}

	/**
	 * @return the listeStatutOf
	 */
	public List<StatutOfValue> getListeStatutOf() {
		return listeStatutOf;
	}

	/**
	 * @param listeStatutOf the listeStatutOf to set
	 */
	public void setListeStatutOf(List<StatutOfValue> listeStatutOf) {
		this.listeStatutOf = listeStatutOf;
	}

	
}
