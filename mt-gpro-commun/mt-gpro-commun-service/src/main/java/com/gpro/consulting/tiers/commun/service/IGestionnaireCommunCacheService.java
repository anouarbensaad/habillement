package com.gpro.consulting.tiers.commun.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
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

/**
 * 
 * @author $Author: DELL $
 * @version $Revision: 0 $
 */
public interface IGestionnaireCommunCacheService {

  /** Initialisation */
  @Transactional
  public void init();

  /**
   * La liste des patiesReferentielles
   * 
   * @return
   */
  @Transactional
  @Cacheable(value = "ListTypeArticle")
  public List < TypeArticleValue> getListeTypeArticle();

  @Transactional
  @Cacheable(value = "listeFamilleArticleCache")
  public List < FamilleArticleValue> getListFamilleArticle();

  @Transactional
  @Cacheable(value = "ListSousFamilleArticle")
  public List < SousFamilleArticleValue> getListSousFamilleArticle();

  @Transactional
  @Cacheable(value = "listeSitePartieInteresseeCache")
  public List < SiteValue> getListSitePartieInteressee();

  @Transactional
  @Cacheable(value = "listeUniteArticleCache")
  public List < UniteArticleValue> getListUniteArticle();

  @Transactional
  @Cacheable(value = "listeMatiereArticleCache")
  public List < MatiereArticleValue> getListMatiereArticle();

  @Transactional
  @Cacheable(value = "listeMetrageArticleCache")
  public List <MetrageValue> getListMetrageArticle();
 
  @Transactional
  @Cacheable(value = "listeGrosseurArticleCache")
  public List <GrosseurValue> getListGrosseurArticle();
 
  @Transactional
  @Cacheable(value = "listeTypeDocumentCache")
  public List < TypeDocumentValue> getListTypeDocument();

  @Transactional
  @Cacheable(value = "listeFamillePiCache")
  public List < FamilleValue> getListFamillePi();

  @Transactional
  @Cacheable(value = "listeCategoriePiCache")
  public List < CategorieValue> getListCategoriePi();

  @Transactional
  @Cacheable(value = "listeTypePiCache")
  public List < TypeValue> getListTypePi();

  @Transactional
  @Cacheable(value = "listeDeviseCache")
  public List < DeviseValue> getListDevise();

  @Transactional
  @Cacheable(value = "listeFamilleProduitCache")
  public List <FamilleProduitValue> getListFamilleProduit();

  @Transactional
  @Cacheable(value = "listeSousFamilleProduitCache")
  public List <SousFamilleProduitValue> getListSousFamilleProduit();

  @Transactional
  @Cacheable(value = "listeCouleurProduitCache")
  public List <CouleurValue> getListCouleurProduit();
  
  @Transactional
  @Cacheable(value = "listeTailleProduitCache")
  public List <TailleValue> getListTailleProduit();
  
  @Transactional
  @Cacheable(value = "listeStandardTailleProduitCache")
  public List <StandardTailleValue> getListStandardTailleProduit();
  
  @Transactional
  @Cacheable(value = "listePhaseProduitCache")
  public List <PhaseValue> getListPhaseProduit();
  
  @Transactional
  @Cacheable(value = "listePartieInteresseeCache")
  public List <PartieInteresseValue> getListPartieInteressee();
  
  
  
  /**
   * Retourne la liste des patieReferentielles
   * 
   * @return
   */
  @Transactional
  @Cacheable(value = "rechercherArticleParId")
  public Map<String, String> rechercherArticleParId(Long pIdSousFamille, Long pIdSite);
  
  @Transactional
  @Cacheable(value = "rechercherProduitParId")
  public Map<String, String> rechercherProduitParId(Long pIdSousFamille, Long pIdSite, Long pIdPI);
  
  
  @Transactional
  @Cacheable(value = "rechercherPartieInteresseeParId")
  public Map<String, String> rechercherPartieInteresseeParId(Long pIdSousFamille, Long pIdCategorie, Long pIdType);

  /**
   * Rafraichir les tables du referentiel
   * 
   * @return
   */
  @Transactional
  public String reloadReferentiel();
  
  
  @Transactional
  @Cacheable(value = "rechFamEtSousFamParId")
  public Map<String, String> rechFamEtSousFamParId(Long pIdSousFamille);  
  
  @Transactional
  @Cacheable(value = "rechercheTailleParId")
  public String rechercheTailleParId(Long pIdTaill); 
  
  @Transactional
  @Cacheable(value = "rechercheColorParId")
  public String rechercheColorParId(Long pIdColor);
  
  
  
  

}
