package com.gpro.consulting.tiers.gpao.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ChaineValue;
import com.gpro.consulting.tiers.gpao.coordination.value.StatutOfValue;

/**
 * @author $Ameni
 *
 */
public interface IGestionnaireCacheService {

	 /** Initialisation */
	  @Transactional
	  public void init();
	  
	  @Transactional
	  //@Cacheable(value = "ListeClientCache")
	  public List < PartieInteresseCacheValue> getListeClient();
	  

	  /**
	   * Liste StatutOF
	   * 
	   * @return List < EtatCommandeValue >
	   */
	  @Transactional
	  @Cacheable(value = "ListeStatutCache")
	  public List < StatutOfValue > getListeStatutOf();
	  
	  /**
	   * Liste ChaineOF
	   * 
	   * @return List < ChaineValue >
	   */
	  @Transactional
	  @Cacheable(value = "ListeChaineCache")
	  public List < ChaineValue > getListeChaineOf();
	  
	  /**
	   * Transforme  les Id en leurs propres designation
	   * 
	   * @return 
	   */
	  @Transactional
	  @Cacheable(value = "rechercherProduitEtatOFParId")
	  public Map<String, String> rechercherProduitEtatOFParId(Long pIdEtatOF);
	  

}
