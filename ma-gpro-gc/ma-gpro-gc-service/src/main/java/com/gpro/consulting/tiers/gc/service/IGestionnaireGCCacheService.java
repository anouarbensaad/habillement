package com.gpro.consulting.tiers.gc.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.SiteValue;
import com.gpro.consulting.tiers.gc.coordination.value.EtatCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.TypeCommandeValue;


/**
 * 
 * @author $Author: $Ameni 
 * @version $Revision: 0 $
 */
public interface IGestionnaireGCCacheService {

  /** Initialisation */
  @Transactional
  public void init();

  /**
   * Liste EtatCommandeVente
   * 
   * @return List < EtatCommandeValue >
   */
  @Transactional
  @Cacheable(value = "ListeEtatCommandeVenteCache")
  public List < EtatCommandeValue > getListeEtatCommandeVente();

  /**
   * Liste ListeTypeCommandeVente
   * @return List < TypeCommandeValue>
   */
  @Transactional
  @Cacheable(value = "ListeTypeCommandeVenteCache")
  public List < TypeCommandeValue> getListeTypeCommandeVente();
  
  @Transactional
  //@Cacheable(value = "ListeClientCache")
  public List < PartieInteresseCacheValue> getListeClient();
  
  @Transactional
  @Cacheable(value = "ListeSiteCache")
  public List < SiteValue> getListeSite();
  
 /*
  	@Transactional
  	@Cacheable(value = "mapCouleurIdDesignationCache")
	public Map<Long, String> mapCouleurIdDesignation();
	
  	@Transactional
  	@Cacheable(value = "mapTailleIdDesignationCache")
	public Map<Long, String> mapTailleIdDesignation();
	
  	@Transactional
  	@Cacheable(value = "mapProduitCache")
	public Map<Long, ProduitValue> mapProduit();
 */ 
  /**
   * Retourne la liste des patieReferentielles
   * 
   * @return
   */
  @Transactional
  @Cacheable(value = "rechercherCommandeParId")
  public Map<String, String> rechercherCommandeParId(Long pIdtype, Long pIdClient, Long pIdSite, Long pIdEtat);
  
  
  /**
   * Rafraichir les tables de la G.Commerciale
   * 
   * @return
   */
  @Transactional
  public String reloadGestionCommerciale();

}
