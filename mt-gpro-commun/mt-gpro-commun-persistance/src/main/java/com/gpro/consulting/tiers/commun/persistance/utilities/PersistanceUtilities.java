package com.gpro.consulting.tiers.commun.persistance.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.jdt.core.dom.ThisExpression;

import com.erp.socle.j2ee.mt.socle.uploadFichier.IGestionnaireDocument;
import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;
import com.gpro.consulting.tiers.commun.coordination.value.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.CategorieValue;
import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.DetProdArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.DetailsPrixProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.DeviseValue;
import com.gpro.consulting.tiers.commun.coordination.value.DocumentArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.DocumentProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.DocumentValue;
import com.gpro.consulting.tiers.commun.coordination.value.ElementBesoinValue;
import com.gpro.consulting.tiers.commun.coordination.value.FamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.FamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.FamilleValue;
import com.gpro.consulting.tiers.commun.coordination.value.FicheBesoinValue;
import com.gpro.consulting.tiers.commun.coordination.value.GrosseurValue;
import com.gpro.consulting.tiers.commun.coordination.value.MatiereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.MetrageValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.PhaseProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.PhaseValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitCouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitTailleValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.RepresentantValue;
import com.gpro.consulting.tiers.commun.coordination.value.SeuilArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.SiteValue;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.StandardTailleValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.coordination.value.TypeArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.TypeDocumentValue;
import com.gpro.consulting.tiers.commun.coordination.value.TypeValue;
import com.gpro.consulting.tiers.commun.coordination.value.UniteArticleValue;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.entity.BaseInfoEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.ArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.CategorieEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.CouleurEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.DetProdArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.DetailsPrixProduitEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.DeviseEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.DocumentArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.DocumentEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.DocumentProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.ElementFicheBesoinEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.FamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.FamilleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.FamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.FicheBesoinEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.GrosseurEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.MatiereArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.MetrageEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.PartieInteresseEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.PhaseEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.PhaseProduitEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.ProduitCouleurEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.ProduitTailleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.RepresentantEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.SeuilArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.SiteEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.SousFamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.SousFamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.StandardTailleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.TailleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.TypeArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.TypeDocumentEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.TypeEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.UniteArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.login.entity.UserEntity;

/**
 * Classe Utilitaire permettant de convertir des objets valeur en entité et des objets entité en
 * objets valeur
 * 
 * @author DELL
 *
 */
public class PersistanceUtilities {

  private IGestionnaireDocument vGestionnaireDocument;
  /**
   * Instanciation du gestionnaire de persistance
   */
  private static PersistanceUtilities instance = new PersistanceUtilities();

  /**
   * Méthode permettant l'accés au Gestionnaire de persistance
   * 
   * @return
   */
  public static PersistanceUtilities getInstance() {
    return instance;
  }

  /**
   * Converstion Partie Interessée value en entity
   **/
  /**
   * Converstion Partie Interessée value en entity
   **/
  public PartieInteresseEntite toPartieInteresseEntity(PartieInteresseValue pPartieInteresseValue) {

    PartieInteresseEntite vPartieInteresseEntite = new PartieInteresseEntite();

    if (pPartieInteresseValue.getId() != null) {
      vPartieInteresseEntite.setId(pPartieInteresseValue.getId());
    }
    /** The ref. */
    vPartieInteresseEntite.setReference(pPartieInteresseValue.getReference());
    /** raison sociale. */
    vPartieInteresseEntite.setRaisonSociale(pPartieInteresseValue.getRaisonSociale());
    /** Abréviation. */
    vPartieInteresseEntite.setAbreviation(pPartieInteresseValue.getAbreviation());
    /** devise. */
    vPartieInteresseEntite.setDevise(pPartieInteresseValue.getDevise());
    /** Activité */
    vPartieInteresseEntite.setActivite(pPartieInteresseValue.getActivite());
    /** Observation. */
    vPartieInteresseEntite.setObservation(pPartieInteresseValue.getObservation());
    /** date introduction */
    vPartieInteresseEntite.setDateIntroduction(pPartieInteresseValue.getDateIntroduction());
    /** Matricule fiscale. */
    vPartieInteresseEntite.setMatrFiscal(pPartieInteresseValue.getMatriculeFiscal());
    /** regime commercial. */
    vPartieInteresseEntite.setRegimeCommercial(pPartieInteresseValue.getRegimeCommercial());
    /** code douane. */
    vPartieInteresseEntite.setCodeDouane(pPartieInteresseValue.getCodeDouane());
    /** adresse. */
    vPartieInteresseEntite.setAdresse(pPartieInteresseValue.getAdresse());
    /** email */
    vPartieInteresseEntite.setEmail(pPartieInteresseValue.getEmail());
    /** telephone */
    vPartieInteresseEntite.setTelephone(pPartieInteresseValue.getTelephone());
    /** fax */
    vPartieInteresseEntite.setFax(pPartieInteresseValue.getFax());
    /** actif */
    vPartieInteresseEntite.setActif(pPartieInteresseValue.isActif());
    /** famille */
    vPartieInteresseEntite.setFamillePartieInteressee(pPartieInteresseValue.getFamillePartieInteressee());
    /** type */
    vPartieInteresseEntite.setTypePartieInteressee(pPartieInteresseValue.getTypePartieInteressee());
    /** site */
    vPartieInteresseEntite.setSitePartieInteressee(pPartieInteresseValue.getSitePartieInteressee());
    /** categorie */
    vPartieInteresseEntite.setCategoriePartieInteressee(pPartieInteresseValue.getCategoriePartieInteressee());
    /** adresseLivraison */
    vPartieInteresseEntite.setAdresseLiv(pPartieInteresseValue.getAdresseLiv());
    // Liste Document
    if (pPartieInteresseValue.getDocuments() != null) {

      Set < DocumentEntite > vListeDocuments = new HashSet < DocumentEntite >();

      for (DocumentValue vDocumentValue : pPartieInteresseValue.getDocuments()) {
        DocumentEntite vDe = this.toEntite(vDocumentValue);
        vDe.setPartieInteresse(vPartieInteresseEntite);
        vListeDocuments.add(vDe);
      }

      vPartieInteresseEntite.setDocumentEntites(vListeDocuments);

    }
    // Liste Représentants

    if (pPartieInteresseValue.getRepresentants() != null) {

      Set < RepresentantEntite > vListeRepresentants = new HashSet < RepresentantEntite >();

      for (RepresentantValue vRepresentantValue : pPartieInteresseValue.getRepresentants()) {
        RepresentantEntite vRe = toEntite(vRepresentantValue);
        vRe.setPartieInteresse(vPartieInteresseEntite);
        vListeRepresentants.add(vRe);
      }

      vPartieInteresseEntite.setRepresentantEntites(vListeRepresentants);

    }

    return vPartieInteresseEntite;
  }

  /**
   * Converstion Partie Interessée entity en value
   **/
  public PartieInteresseValue toPartieInteresseValue(PartieInteresseEntite pPartieInteresseEntity) {

    PartieInteresseValue vPartieInteresseValue = new PartieInteresseValue();

    vPartieInteresseValue.setId(pPartieInteresseEntity.getId());

    /** The ref. */
    vPartieInteresseValue.setReference(pPartieInteresseEntity.getReference());
    /** raison sociale. */
    vPartieInteresseValue.setRaisonSociale(pPartieInteresseEntity.getRaisonSociale());
    /** Abréviation. */
    vPartieInteresseValue.setAbreviation(pPartieInteresseEntity.getAbreviation());
    /** devise. */
    vPartieInteresseValue.setDevise(pPartieInteresseEntity.getDevise());
    /** Activité */
    vPartieInteresseValue.setActivite(pPartieInteresseEntity.getActivite());
    /** Observation. */
    vPartieInteresseValue.setObservation(pPartieInteresseEntity.getObservation());
    /** date introduction */
    vPartieInteresseValue.setDateIntroduction(pPartieInteresseEntity.getDateIntroduction());
    /** Matricule fiscale. */
    vPartieInteresseValue.setMatriculeFiscal(pPartieInteresseEntity.getMatrFiscal());
    /** regime commercial. */
    vPartieInteresseValue.setRegimeCommercial(pPartieInteresseEntity.getRegimeCommercial());
    /** code douane. */
    vPartieInteresseValue.setCodeDouane(pPartieInteresseEntity.getCodeDouane());
    /** adresse. */
    vPartieInteresseValue.setAdresse(pPartieInteresseEntity.getAdresse());
    /** email */
    vPartieInteresseValue.setEmail(pPartieInteresseEntity.getEmail());
    /** telephone */
    vPartieInteresseValue.setTelephone(pPartieInteresseEntity.getTelephone());
    /** fax */
    vPartieInteresseValue.setFax(pPartieInteresseEntity.getFax());
    /** actif */
    vPartieInteresseValue.setActif(pPartieInteresseEntity.isActif());
    /** famille */
    vPartieInteresseValue.setFamillePartieInteressee(pPartieInteresseEntity.getFamillePartieInteressee());
    /** type */
    vPartieInteresseValue.setTypePartieInteressee(pPartieInteresseEntity.getTypePartieInteressee());
    /** site */
    vPartieInteresseValue.setSitePartieInteressee(pPartieInteresseEntity.getSitePartieInteressee());
    /** categorie */
    vPartieInteresseValue.setCategoriePartieInteressee(pPartieInteresseEntity.getCategoriePartieInteressee());
    /** adresseLivraison */
    vPartieInteresseValue.setAdresseLiv(pPartieInteresseEntity.getAdresseLiv());
    
    // Liste Document
    if (pPartieInteresseEntity.getDocumentEntites() != null) {

      Set < DocumentValue > vListeDocuments = new HashSet < DocumentValue >();

      for (DocumentEntite vDocumentValue : pPartieInteresseEntity.getDocumentEntites()) {
        DocumentValue vDv = toValue(vDocumentValue);
        vListeDocuments.add(vDv);
      }

      vPartieInteresseValue.setDocuments(vListeDocuments);
    }
    // Liste Représentants

    if (pPartieInteresseEntity.getRepresentantEntites() != null) {

      Set < RepresentantValue > vListeRepresentants = new HashSet < RepresentantValue >();

      for (RepresentantEntite vRepresentantValue : pPartieInteresseEntity.getRepresentantEntites()) {
        RepresentantValue vRv = toValue(vRepresentantValue);

        vListeRepresentants.add(vRv);
      }

      vPartieInteresseValue.setRepresentants(vListeRepresentants);

    }

    return vPartieInteresseValue;
  }

  /**
   * @param vRepresentantValue
   * @return
   */
  public static RepresentantEntite toEntite(RepresentantValue pRepresentantValue) {
    RepresentantEntite vRepresentantEntite = new RepresentantEntite();

    /** The id. */
    if (pRepresentantValue.getId() != null) {
      vRepresentantEntite.setId(pRepresentantValue.getId());
    }
    /** The nom. */
    vRepresentantEntite.setNom(pRepresentantValue.getNom());
    /** The fonction. */
    vRepresentantEntite.setFonction(pRepresentantValue.getFonction());
    /** The email. */
    vRepresentantEntite.setEmail(pRepresentantValue.getEmail());
    /** The telephone. */
    vRepresentantEntite.setTelephone(pRepresentantValue.getTelephone());
    /** The fax. */
    vRepresentantEntite.setFax(pRepresentantValue.getFax());

    return vRepresentantEntite;
  }

  /**
   * Converstion entité à value du Representant
   * 
   * @param
   * @return
   */
  public static RepresentantValue toValue(RepresentantEntite pRepresentantEntite) {
    RepresentantValue vRepresentantValue = new RepresentantValue();

    /** The id. */

    vRepresentantValue.setId(pRepresentantEntite.getId());

    /** The nom. */
    vRepresentantValue.setNom(pRepresentantEntite.getNom());
    /** The fonction. */
    vRepresentantValue.setFonction(pRepresentantEntite.getFonction());
    /** The email. */
    vRepresentantValue.setEmail(pRepresentantEntite.getEmail());
    /** The telephone. */
    vRepresentantValue.setTelephone(pRepresentantEntite.getTelephone());
    /** The fax. */
    vRepresentantValue.setFax(pRepresentantEntite.getFax());

    return vRepresentantValue;
  }

  /**
   * Converstion entité à value du Document
   * 
   * @param
   * @return
   */
  public DocumentEntite toEntite(DocumentValue pDocumentValue) {
    DocumentEntite vDocumentEntite = new DocumentEntite();

    /** Persistance niveau base de données */
    if (pDocumentValue.getId() != null) {
      vDocumentEntite.setId(pDocumentValue.getId());
    }
    vDocumentEntite.setUuidDocument(pDocumentValue.getvUUIDDocument());

    vDocumentEntite.setPath(pDocumentValue.getPath());
    vDocumentEntite.setTypeDocumentEntite(pDocumentValue.getTypeDocument());

    return vDocumentEntite;

  }

  /**
   * Converstion value à entité du Document
   * 
   * @param
   * @return
   */
  public DocumentValue toValue(DocumentEntite pDocumentEntite) {
    DocumentValue vDocumentValue = new DocumentValue();

    if (pDocumentEntite.getId() != null) {
      vDocumentValue.setId(pDocumentEntite.getId());
    }
    /** Ajout du mapping de uuid */
    vDocumentValue.setvUUIDDocument(pDocumentEntite.getUuidDocument());
    vDocumentValue.setPath(pDocumentEntite.getPath());
    vDocumentValue.setTypeDocument(pDocumentEntite.getTypeDocumentEntite());

    return vDocumentValue;

  }

  /*** site partie int **/

  public static SiteEntite toEntite(SiteValue pSiteValue) {
    SiteEntite vSiteEntite = new SiteEntite();
    if (pSiteValue.getId() != null) {
      vSiteEntite.setId(pSiteValue.getId());
    }
    vSiteEntite.setNom(pSiteValue.getNom());
    return vSiteEntite;
  }

  public static SiteValue toValue(SiteEntite pSiteEntite) {
    SiteValue vSiteValue = new SiteValue();
    vSiteValue.setId(pSiteEntite.getId());
    vSiteValue.setNom(pSiteEntite.getNom());
    return vSiteValue;
  }

  /*** type document partie int ***/
  public static TypeDocumentValue toValue(TypeDocumentEntite pTypeDocumentEntite) {
    TypeDocumentValue vTypeDocumentValue = new TypeDocumentValue();
    vTypeDocumentValue.setId(pTypeDocumentEntite.getId());
    vTypeDocumentValue.setDesignation(pTypeDocumentEntite.getDesignation());
    vTypeDocumentValue.setModule(pTypeDocumentEntite.getModule());
    return vTypeDocumentValue;
  }

  public static TypeDocumentEntite toEntity(TypeDocumentValue pTypeDocumentValue) {
    TypeDocumentEntite vTypeDocumentEntite = new TypeDocumentEntite();
    if (pTypeDocumentValue.getId() != null) {
      vTypeDocumentEntite.setId(pTypeDocumentValue.getId());
    }
    vTypeDocumentEntite.setDesignation(pTypeDocumentValue.getDesignation());
    vTypeDocumentEntite.setModule(pTypeDocumentValue.getModule());
    return vTypeDocumentEntite;

  }

  /**
   * Méthode permettant de convertir un objet valeur en entité.
   */
  public static CategorieEntite toEntity(CategorieValue categorieValue) {
    CategorieEntite categorieEntite = new CategorieEntite();
    if (categorieValue.getId() != null) {
      categorieEntite.setId(categorieValue.getId());
    }
    categorieEntite.setDesignation(categorieValue.getDesignation());

    return categorieEntite;
  }

  /** Converstion CathegoriePartieINT entite en CathegoriePartieINT value **/
  public static CategorieValue toValue(CategorieEntite categorieEntite) {
    CategorieValue categorieValue = new CategorieValue();

    categorieValue.setId(categorieEntite.getId());
    categorieValue.setDesignation(categorieEntite.getDesignation());

    return categorieValue;
  }

  /**************************** value to entite famille PI *********************************/

  /** Converstion Famille value en Famille entite **/
  public static FamilleEntite toFamilleEntity(FamilleValue familleValue) {
    FamilleEntite familleEntite = new FamilleEntite();
    if (familleValue.getId() != null) {
      familleEntite.setId(familleValue.getId());
      familleEntite.setAchat(familleValue.isAchat());
      familleEntite.setDesignation(familleValue.getDesignation());
      familleEntite.setVente(familleValue.isVente());
    }
    return familleEntite;
  }

  /**** listValue categorie PI to liste entity ******/
  public static List < CategorieValue > tolistCategoriePartieIntereseValues(List < CategorieEntite > pCategorieEntites) {
    List < CategorieValue > categorieValues = new ArrayList < CategorieValue >();
    for (CategorieEntite categorieEntite : pCategorieEntites) {
      categorieValues.add(PersistanceUtilities.toValue(categorieEntite));
    }
    return categorieValues;
  }

  public static FamilleValue toFamilleValue(FamilleEntite familleEntite) {
    FamilleValue familleValue = new FamilleValue();
    familleValue.setId(familleEntite.getId());
    familleValue.setAchat(familleEntite.isAchat());
    familleValue.setDesignation(familleEntite.getDesignation());
    familleValue.setVente(familleEntite.isVente());

    return familleValue;
  }

  /** Converstion type value en type entite **/
  public static TypeEntite toTypePArtieInteresseEntity(TypeValue typeValue) {
    TypeEntite typeEntite = new TypeEntite();
    if (typeValue.getId() != null) {
      typeEntite.setId(typeValue.getId());
    }
    typeEntite.setDesignation(typeValue.getDesignation());

    return typeEntite;
  }

  /** Converstion type entite en type value **/

  public static TypeValue toTypePArtieInteresseValue(TypeEntite typeEntite) {
    TypeValue typeValue = new TypeValue();
    typeValue.setId(typeEntite.getId());
    typeValue.setDesignation(typeEntite.getDesignation());
    return typeValue;
  }

  /*************** list entitys to list values *****************/
  @SuppressWarnings("null")
  public static List < CategorieValue > tolistValues(List < CategorieEntite > pCategorieEntites) {
    List < CategorieValue > categorieValues = null;
    for (int i = 0; i < pCategorieEntites.size(); i++) {
      categorieValues.add(toValue(pCategorieEntites.get(i)));

    }
    return categorieValues;
  }

  /**************************** value to entite EbMatiere *********************************/

  /** Converstion MatiereArticleValue en MatiereArticleEntite **/
  public static MatiereArticleEntite toEntity(MatiereArticleValue matiereValue) {
    MatiereArticleEntite vEbMatiereEntite = new MatiereArticleEntite();
    if (matiereValue.getId() != null) {
      vEbMatiereEntite.setId(matiereValue.getId());
    }
    vEbMatiereEntite.setDesignation(matiereValue.getDesignation());
    return vEbMatiereEntite;
  }

  /** Converstion MatiereArticleEntite entite en MatiereArticleValue **/
  public static MatiereArticleValue toValue(MatiereArticleEntite matiereArticleEntite) {
    MatiereArticleValue ebmatiereValue = new MatiereArticleValue();
    ebmatiereValue.setId(matiereArticleEntite.getId());
    ebmatiereValue.setDesignation(matiereArticleEntite.getDesignation());
    return ebmatiereValue;
  }

  /** Converstion ListeMatiereArticleEntite en ListeMatiereArticleValue **/
  public static List < MatiereArticleValue > tolistMatiereArticleValues(List < MatiereArticleEntite > pMatiereArticleEntites) {
    List < MatiereArticleValue > MatiereArticleValues = new ArrayList < MatiereArticleValue >();
    for (MatiereArticleEntite MatiereArticleEntite : pMatiereArticleEntites) {
      MatiereArticleValues.add(PersistanceUtilities.toValue(MatiereArticleEntite));
    }
    return MatiereArticleValues;
  }

  /**************************** value to entite Metrage *********************************/

  /** Converstion MetrageValue en MetrageEntite **/
  public static MetrageEntite toEntity(MetrageValue metrageValue) {
    MetrageEntite vMetrageEntite = new MetrageEntite();
    if (metrageValue.getId() != null) {
      vMetrageEntite.setId(metrageValue.getId());
    }
    vMetrageEntite.setDesignation(metrageValue.getDesignation());
    return vMetrageEntite;
  }

  /** Converstion MatiereArticleEntite entite en MetrageValue **/
  public static MetrageValue toValue(MetrageEntite pMetrageEntite) {
    MetrageValue vMetrageValue = new MetrageValue();
    vMetrageValue.setId(pMetrageEntite.getId());
    vMetrageValue.setDesignation(pMetrageEntite.getDesignation());
    return vMetrageValue;
  }

  /** Converstion ListeMetrageEntite en ListeMetrageValue **/
  public static List < MetrageValue > tolistMetrageValues(List < MetrageEntite > pMetrageEntites) {
    List < MetrageValue > MetrageValues = new ArrayList < MetrageValue >();
    for (MetrageEntite MetrageEntite : pMetrageEntites) {
      MetrageValues.add(PersistanceUtilities.toValue(MetrageEntite));
    }
    return MetrageValues;
  }

  /**************************** value to entite EbGrosseur *********************************/
  /** Converstion GrosseurValue en GrosseurEntite **/
  public static GrosseurEntite toEntity(GrosseurValue pGrosseurValue) {
    GrosseurEntite vGrosseurEntite = new GrosseurEntite();
    if (pGrosseurValue.getId() != null) {
      vGrosseurEntite.setId(pGrosseurValue.getId());
    }
    vGrosseurEntite.setDesignation(pGrosseurValue.getDesignation());
    return vGrosseurEntite;
  }

  /** Converstion GrosseurEntite entite en GrosseurValue **/
  public static GrosseurValue toValue(GrosseurEntite pGrosseurEntite) {
    GrosseurValue vGrosseurValue = new GrosseurValue();
    vGrosseurValue.setId(pGrosseurEntite.getId());
    vGrosseurValue.setDesignation(pGrosseurEntite.getDesignation());
    return vGrosseurValue;
  }

  /**************************** value to entite Devise *********************************/

  /** Converstion DeviseValue en DeviseEntite **/
  public static DeviseEntite toEntity(DeviseValue DeviseValue) {
    DeviseEntite vDeviseEntite = new DeviseEntite();
    if (DeviseValue.getId() != null) {
      vDeviseEntite.setId(DeviseValue.getId());
    }
    vDeviseEntite.setDesignation(DeviseValue.getDesignation());
    return vDeviseEntite;
  }

  /** Converstion DeviseEntite entite en DeviseValue **/
  public static DeviseValue toValue(DeviseEntite DeviseEntite) {
    DeviseValue DeviseValue = new DeviseValue();
    DeviseValue.setId(DeviseEntite.getId());
    DeviseValue.setDesignation(DeviseEntite.getDesignation());
    return DeviseValue;
  }

  /** Converstion ListeDeviseEntite en ListeDeviseValue **/
  public static List < DeviseValue > tolistDeviseValues(List < DeviseEntite > pDeviseEntites) {
    List < DeviseValue > DeviseValues = new ArrayList < DeviseValue >();
    for (DeviseEntite DeviseEntite : pDeviseEntites) {
      DeviseValues.add(PersistanceUtilities.toValue(DeviseEntite));
    }
    return DeviseValues;
  }

  /**************************** value to entite EbCouleur *********************************/
  /** Converstion CouleurValue en CouleurEntite **/
  public static CouleurEntite toEntity(CouleurValue CouleurValue) {
    CouleurEntite vEbCouleurEntite = new CouleurEntite();
    if (CouleurValue.getId() != null) {
      vEbCouleurEntite.setId(CouleurValue.getId());
    }
    vEbCouleurEntite.setDesignation(CouleurValue.getDesignation());
    return vEbCouleurEntite;
  }

  /** Converstion CouleurEntite entite en CouleurValue **/
  public static CouleurValue toValue(CouleurEntite CouleurEntite) {
    CouleurValue ebCouleurValue = new CouleurValue();
    ebCouleurValue.setId(CouleurEntite.getId());
    ebCouleurValue.setDesignation(CouleurEntite.getDesignation());
    return ebCouleurValue;
  }

  /** Converstion ListeCouleurEntite en ListeCouleurValue **/
  public static List < CouleurValue > tolistCouleurValues(List < CouleurEntite > pCouleurEntites) {
    List < CouleurValue > CouleurValues = new ArrayList < CouleurValue >();
    for (CouleurEntite CouleurEntite : pCouleurEntites) {
      CouleurValues.add(PersistanceUtilities.toValue(CouleurEntite));
    }
    return CouleurValues;
  }

  /**************************** value to entite ProduitCouleur *********************************/
  /** Converstion ProduitCouleurValue en CouleurEntite **/
  public static ProduitCouleurEntite toEntity(ProduitCouleurValue pProduitCouleurValue) {
    ProduitCouleurEntite vProduitCouleurEntite = new ProduitCouleurEntite();
    if (pProduitCouleurValue.getId() != null) {
      vProduitCouleurEntite.setId(pProduitCouleurValue.getId());
    }
    vProduitCouleurEntite.setEbCouleurId(pProduitCouleurValue.getEbCouleurId());
    return vProduitCouleurEntite;
  }

  /** Converstion CouleurEntite entite en CouleurValue **/
  public static ProduitCouleurValue toValue(ProduitCouleurEntite pProduitCouleurEntite) {
    ProduitCouleurValue vProduitCouleurValue = new ProduitCouleurValue();
    vProduitCouleurValue.setId(pProduitCouleurEntite.getId());
    vProduitCouleurValue.setEbCouleurId(pProduitCouleurEntite.getEbCouleurId());
    
    return vProduitCouleurValue;
  }

  /** Converstion ListeCouleurEntite en ListeCouleurValue **/
  public static List < ProduitCouleurValue > tolistProduitCouleurValues(List < ProduitCouleurEntite > pProduitCouleurEntites) {
    List < ProduitCouleurValue > vProduitCouleurValues = new ArrayList < ProduitCouleurValue >();
    for (ProduitCouleurEntite pProduitCouleurEntite : pProduitCouleurEntites) {
      vProduitCouleurValues.add(PersistanceUtilities.toValue(pProduitCouleurEntite));
    }
    return vProduitCouleurValues;
  }

  /**************************** value to entite ProduitTaille *********************************/
  /** Converstion ProduitTailleValue en TailleEntite **/
  public static ProduitTailleEntite toEntity(ProduitTailleValue pProduitTailleValue) {
    ProduitTailleEntite vProduitTailleEntite = new ProduitTailleEntite();
    if (pProduitTailleValue.getId() != null) {
      vProduitTailleEntite.setId(pProduitTailleValue.getId());
    }
    vProduitTailleEntite.setEbTailleId(pProduitTailleValue.getEbTailleId());
    return vProduitTailleEntite;
  }

  /** Converstion TailleEntite entite en TailleValue **/
  public static ProduitTailleValue toValue(ProduitTailleEntite pProduitTailleEntite) {
    ProduitTailleValue vProduitTailleValue = new ProduitTailleValue();
    vProduitTailleValue.setId(pProduitTailleEntite.getId());
    vProduitTailleValue.setEbTailleId(pProduitTailleEntite.getEbTailleId());
    return vProduitTailleValue;
  }

  /** Converstion ListeTailleEntite en ListeTailleValue **/
  public static List < ProduitTailleValue > tolistProduitTailleValues(List < ProduitTailleEntite > pProduitTailleEntites) {
    List < ProduitTailleValue > vProduitTailleValues = new ArrayList < ProduitTailleValue >();
    for (ProduitTailleEntite pProduitTailleEntite : pProduitTailleEntites) {
      vProduitTailleValues.add(PersistanceUtilities.toValue(pProduitTailleEntite));
    }
    return vProduitTailleValues;
  }

  /**************************** value to entite EbDetProdArticle *********************************/

  /** Converstion DetProdArticleValue en DetProdArticleEntite **/
  public static DetProdArticleEntite toEntity(DetProdArticleValue pDetProdArticleValue) {
    DetProdArticleEntite vEbDetProdArticleEntite = new DetProdArticleEntite();
    if (pDetProdArticleValue.getId() != null) {
      vEbDetProdArticleEntite.setId(pDetProdArticleValue.getId());
    }
    vEbDetProdArticleEntite.setQuantite(pDetProdArticleValue.getQuantite());
    vEbDetProdArticleEntite.setBloquante(pDetProdArticleValue.isBloquante());
    vEbDetProdArticleEntite.setOrdre(pDetProdArticleValue.getOrdre());
    vEbDetProdArticleEntite.setPhase(pDetProdArticleValue.getPhase());
    vEbDetProdArticleEntite.setEb_article_id(pDetProdArticleValue.getEb_article_id());
    vEbDetProdArticleEntite.setEb_produitdet_id(pDetProdArticleValue.getEb_produitdet_id());
    return vEbDetProdArticleEntite;
  }

  /** Converstion DetProdArticleEntite entite en DetProdArticleValue **/
  public static DetProdArticleValue toValue(DetProdArticleEntite pDetProdArticleEntite) {
    DetProdArticleValue vEbDetProdArticleValue = new DetProdArticleValue();
    vEbDetProdArticleValue.setId(pDetProdArticleEntite.getId());
    vEbDetProdArticleValue.setQuantite(pDetProdArticleEntite.getQuantite());
    vEbDetProdArticleValue.setBloquante(pDetProdArticleEntite.isBloquante());
    vEbDetProdArticleValue.setOrdre(pDetProdArticleEntite.getOrdre());
    vEbDetProdArticleValue.setPhase(pDetProdArticleEntite.getPhase());
    vEbDetProdArticleValue.setEb_article_id(pDetProdArticleEntite.getEb_article_id());
    vEbDetProdArticleValue.setEb_produitdet_id(pDetProdArticleEntite.getEb_produitdet_id());
    return vEbDetProdArticleValue;
  }

  /**************************** value to entite EbPhase *********************************/

  /** Converstion PhaseValue en PhaseEntite **/
  public static PhaseEntite toEntity(PhaseValue PhaseValue) {
    PhaseEntite vEbPhaseEntite = new PhaseEntite();
    if (PhaseValue.getId() != null) {
      vEbPhaseEntite.setId(PhaseValue.getId());
    }
    vEbPhaseEntite.setDesignation(PhaseValue.getDesignation());
    vEbPhaseEntite.setRef(PhaseValue.getRef());
    return vEbPhaseEntite;
  }

  /** Converstion PhaseEntite entite en PhaseValue **/
  public static PhaseValue toValue(PhaseEntite phaseEntite) {
    PhaseValue ebPhaseValue = new PhaseValue();
    ebPhaseValue.setId(phaseEntite.getId());
    ebPhaseValue.setDesignation(phaseEntite.getDesignation());
    ebPhaseValue.setRef(phaseEntite.getRef());
    return ebPhaseValue;
  }

  /** Converstion ListePhaseEntite en ListePhaseValue **/
  public static List < PhaseValue > tolistPhaseValues(List < PhaseEntite > pPhaseEntites) {
    List < PhaseValue > PhaseValues = new ArrayList < PhaseValue >();
    for (PhaseEntite PhaseEntite : pPhaseEntites) {
      PhaseValues.add(PersistanceUtilities.toValue(PhaseEntite));
    }
    return PhaseValues;
  }

  /**************************** value to entite EbPhaseProduit *********************************/

  /** Converstion PhaseProduitValue en PhaseProduitEntite **/
  public static PhaseProduitEntite toEntity(PhaseProduitValue pPhaseProduitValue) {
    PhaseProduitEntite vEbPhaseProduitEntite = new PhaseProduitEntite();
    vEbPhaseProduitEntite.setId(pPhaseProduitValue.getId());
    vEbPhaseProduitEntite.setDevise(pPhaseProduitValue.getDevise());
    vEbPhaseProduitEntite.setVariante(pPhaseProduitValue.getVariante());
    vEbPhaseProduitEntite.setOrdre(pPhaseProduitValue.getOrdre());
    vEbPhaseProduitEntite.setPrix(pPhaseProduitValue.getPrix());
    vEbPhaseProduitEntite.setEb_phase_id(pPhaseProduitValue.getEb_phase_id());
    return vEbPhaseProduitEntite;
  }

  /** Converstion PhaseProduitEntite entite en PhaseProduitValue **/
  public static PhaseProduitValue toValue(PhaseProduitEntite pPhaseProduitEntite) {
    PhaseProduitValue vEbPhaseProduitValue = new PhaseProduitValue();
    vEbPhaseProduitValue.setId(pPhaseProduitEntite.getId());
    vEbPhaseProduitValue.setDevise(pPhaseProduitEntite.getDevise());
    vEbPhaseProduitValue.setVariante(pPhaseProduitEntite.getVariante());
    vEbPhaseProduitValue.setOrdre(pPhaseProduitEntite.getOrdre());
    vEbPhaseProduitValue.setPrix(pPhaseProduitEntite.getPrix());
    vEbPhaseProduitValue.setEb_phase_id(pPhaseProduitEntite.getEb_phase_id());
    return vEbPhaseProduitValue;
  }

  /**************************** value to entite EbStandardTaille *********************************/

  /** Converstion StandardTailleValue en StandardTailleEntite **/
  public static StandardTailleEntite toEntity(StandardTailleValue StandardTailleValue) {
    StandardTailleEntite vEbStandardTailleEntite = new StandardTailleEntite();
    if (StandardTailleValue.getId() != null) {
      vEbStandardTailleEntite.setId(StandardTailleValue.getId());
    }
    vEbStandardTailleEntite.setDesignation(StandardTailleValue.getDesignation());
    return vEbStandardTailleEntite;
  }

  /** Converstion StandardTailleEntite entite en StandardTailleValue **/
  public static StandardTailleValue toValue(StandardTailleEntite StandardTailleEntite) {
    StandardTailleValue ebStandardTailleValue = new StandardTailleValue();
    ebStandardTailleValue.setId(StandardTailleEntite.getId());
    ebStandardTailleValue.setDesignation(StandardTailleEntite.getDesignation());
    return ebStandardTailleValue;
  }

  /** Converstion ListeStandardTailleEntite en ListeStandardTailleValue **/
  public static List < StandardTailleValue > tolistStandardTailleValues(List < StandardTailleEntite > pStandardTailleEntites) {
    List < StandardTailleValue > StandardTailleValues = new ArrayList < StandardTailleValue >();
    for (StandardTailleEntite StandardTailleEntite : pStandardTailleEntites) {
      StandardTailleValues.add(PersistanceUtilities.toValue(StandardTailleEntite));
    }
    return StandardTailleValues;
  }

  /**************************** value to entite EbTaille *********************************/

  /** Converstion TailleValue en TailleEntite **/
  public static TailleEntite toEntity(TailleValue pTailleValue) {
    TailleEntite vEbTailleEntite = new TailleEntite();
    if (pTailleValue.getId() != null) {
      vEbTailleEntite.setId(pTailleValue.getId());
    }
    vEbTailleEntite.setDesignation(pTailleValue.getDesignation());
    vEbTailleEntite.setEb_sttaille_id(pTailleValue.getEb_sttaille_id());
    vEbTailleEntite.setOrdre(pTailleValue.getOrdre());
    return vEbTailleEntite;
  }

  /** Converstion TailleEntite entite en TailleValue **/
  public static TailleValue toValue(TailleEntite pTailleEntite) {
    TailleValue ebTailleValue = new TailleValue();
    ebTailleValue.setId(pTailleEntite.getId());
    ebTailleValue.setDesignation(pTailleEntite.getDesignation());
    ebTailleValue.setEb_sttaille_id(pTailleEntite.getEb_sttaille_id());
    ebTailleValue.setOrdre(pTailleEntite.getOrdre());
    return ebTailleValue;
  }

  /** Converstion ListeTailleEntite en ListeTailleValue **/
  public static List < TailleValue > tolistTailleValues(List < TailleEntite > pTailleEntites) {
    List < TailleValue > TailleValues = new ArrayList < TailleValue >();
    for (TailleEntite TailleEntite : pTailleEntites) {
      TailleValues.add(PersistanceUtilities.toValue(TailleEntite));
    }
    return TailleValues;
  }

  /****************************************************************************************/

  /** Converstion ListeGrosseurEntite en ListeGrosseurValue **/
  public static List < GrosseurValue > tolistGrosseurValues(List < GrosseurEntite > pGrosseurEntites) {
    List < GrosseurValue > GrosseurValues = new ArrayList < GrosseurValue >();
    for (GrosseurEntite GrosseurEntite : pGrosseurEntites) {
      GrosseurValues.add(PersistanceUtilities.toValue(GrosseurEntite));
    }
    return GrosseurValues;
  }

  /**************************** FamilleArticlvalue to FamilleArticlEntite *********************************/

  /** FamilleArticleEntite to familleArticleValue **/
  public static FamilleArticleValue toValue(FamilleArticleEntity pFamilleArticleEntite) {
    FamilleArticleValue familleArticleValue = new FamilleArticleValue();
    familleArticleValue.setId(pFamilleArticleEntite.getId());
    familleArticleValue.setDesignation(pFamilleArticleEntite.getDesignation());
    if (pFamilleArticleEntite.getTypeArticle() != null) {
      familleArticleValue.setIdTypeArticle(pFamilleArticleEntite.getTypeArticle().getId());
    }
    return familleArticleValue;

  }

  /** FamilleArticleValue to familleArticleEntite **/
  public static FamilleArticleEntity toEntity(FamilleArticleValue pFamilleArticleValue, TypeArticleEntity pTypeArticle) {
    FamilleArticleEntity familleArticleEntite = new FamilleArticleEntity();
    if (pFamilleArticleValue.getId() != null) {
      familleArticleEntite.setId(pFamilleArticleValue.getId());
    }
    familleArticleEntite.setDesignation(pFamilleArticleValue.getDesignation());
    familleArticleEntite.setTypeArticle(pTypeArticle);
    return familleArticleEntite;
  }

  /**** listValue famille article to liste entity ******/
  public static List < FamilleArticleValue > tolistFamilleArticleValues(List < FamilleArticleEntity > pFamilleEntites) {
    List < FamilleArticleValue > familleValues = new ArrayList < FamilleArticleValue >();
    for (FamilleArticleEntity familleEntite : pFamilleEntites) {
      familleValues.add(PersistanceUtilities.toValue(familleEntite));
    }
    return familleValues;
  }

  /** SeuilArticleEntite to SeuilArticleValue **/
  public static SeuilArticleValue toValue(SeuilArticleEntity pSeuilArticleEntity) {
    SeuilArticleValue seuilArticleValue = new SeuilArticleValue();
    seuilArticleValue.setId(pSeuilArticleEntity.getId());
    seuilArticleValue.setMaxSeuil(pSeuilArticleEntity.getMaxSeuil());
    seuilArticleValue.setMinSeuil(pSeuilArticleEntity.getMinSeuil());
    seuilArticleValue.setDateFin(pSeuilArticleEntity.getDateFin());
    seuilArticleValue.setDateDebut(pSeuilArticleEntity.getDateDebut());
    return seuilArticleValue;
  }

  /** SeuilArticleValue to SeuilArticleEntite **/
  public static SeuilArticleEntity toEntity(SeuilArticleValue pSeuilArticleValue) {
    SeuilArticleEntity seuilArticleEntity = new SeuilArticleEntity();
    if (pSeuilArticleValue.getId() != null) {
      seuilArticleEntity.setId(pSeuilArticleValue.getId());
    }
    seuilArticleEntity.setMaxSeuil(pSeuilArticleValue.getMaxSeuil());
    seuilArticleEntity.setMinSeuil(pSeuilArticleValue.getMinSeuil());
    seuilArticleEntity.setDateFin(pSeuilArticleValue.getDateFin());
    seuilArticleEntity.setDateDebut(pSeuilArticleValue.getDateDebut());
    return seuilArticleEntity;
  }

  /** sousfamilelArticleEntite to sousfamilleArticleValue **/
  public static SousFamilleArticleValue toValue(SousFamilleArticleEntity pSousFamilleArticleEntity) {
    SousFamilleArticleValue sousFamilleArticleValue = new SousFamilleArticleValue();
    sousFamilleArticleValue.setId(pSousFamilleArticleEntity.getId());
    if (pSousFamilleArticleEntity.getFamilleArticle() != null) {
      sousFamilleArticleValue.setIdFamilleArticle(pSousFamilleArticleEntity.getFamilleArticle().getId());
    }
    sousFamilleArticleValue.setDesignation(pSousFamilleArticleEntity.getDesignation());
    return sousFamilleArticleValue;
  }

  /** sousfamilleArticleValue to sousfamilleArticleEntite **/
  public static SousFamilleArticleEntity toEntity(SousFamilleArticleValue pSousFamilleArticleValue,
    FamilleArticleEntity pFamilleArticle) {
    SousFamilleArticleEntity sousFamilleArticleEntity = new SousFamilleArticleEntity();
    if (pSousFamilleArticleValue.getId() != null) {
      sousFamilleArticleEntity.setId(pSousFamilleArticleValue.getId());
    }
    sousFamilleArticleEntity.setFamilleArticle(pFamilleArticle);
    sousFamilleArticleEntity.setDesignation(pSousFamilleArticleValue.getDesignation());
    return sousFamilleArticleEntity;
  }

  /**** listValue sous famille article article to liste entity ******/
  public static List < SousFamilleArticleValue > tolistSousFamilleArticleValues(
    List < SousFamilleArticleEntity > pSousFamilleEntites) {
    List < SousFamilleArticleValue > sousFamilleArticleValues = new ArrayList < SousFamilleArticleValue >();
    for (SousFamilleArticleEntity sousFamilleEntite : pSousFamilleEntites) {
      sousFamilleArticleValues.add(PersistanceUtilities.toValue(sousFamilleEntite));
    }
    return sousFamilleArticleValues;
  }

  /** typelArticleEntite to typeArticleValue **/
  public static TypeArticleValue toValue(TypeArticleEntity pTypeArticleEntity) {
    TypeArticleValue typeArticleValue = new TypeArticleValue();
    typeArticleValue.setId(pTypeArticleEntity.getId());
    typeArticleValue.setDesignation(pTypeArticleEntity.getDesignation());
    return typeArticleValue;
  }

  /** typeArticleValue to typeArticleEntite **/
  public static TypeArticleEntity toEntity(TypeArticleValue pTypeArticleValue) {
    TypeArticleEntity typeArticleEntity = new TypeArticleEntity();
    if (pTypeArticleValue.getId() != null) {
      typeArticleEntity.setId(pTypeArticleValue.getId());
    }
    typeArticleEntity.setDesignation(pTypeArticleValue.getDesignation());
    return typeArticleEntity;
  }

  /**** listValue type article article to liste entity ******/
  public static List < TypeArticleValue > tolistTypeArticleValues(List < TypeArticleEntity > pTypeArticleEntitys) {
    List < TypeArticleValue > typeArticleValues = new ArrayList < TypeArticleValue >();
    for (TypeArticleEntity typeArticleEntite : pTypeArticleEntitys) {
      typeArticleValues.add(PersistanceUtilities.toValue(typeArticleEntite));
    }
    return typeArticleValues;
  }

  /** unitelArticleEntite to uniteArticleValue **/
  public static UniteArticleValue toValue(UniteArticleEntity pUniteArticleEntity) {
    UniteArticleValue uniteArticleValue = new UniteArticleValue();
    uniteArticleValue.setId(pUniteArticleEntity.getId());
    uniteArticleValue.setDesignation(pUniteArticleEntity.getDesignation());
    return uniteArticleValue;
  }

  /** uniteArticleValue to uniteArticleEntite **/
  public static UniteArticleEntity toEntity(UniteArticleValue pUniteArticleValue) {
    UniteArticleEntity uniteArticleEntity = new UniteArticleEntity();
    if (pUniteArticleValue.getId() != null) {
      uniteArticleEntity.setId(pUniteArticleValue.getId());
    }
    uniteArticleEntity.setDesignation(pUniteArticleValue.getDesignation());
    return uniteArticleEntity;
  }

  // ///Persistance Article

  /**
   * Converstion Article value en entity
   **/
  public static ArticleEntite toArticleEntity(ArticleValue pArticleValue, SousFamilleArticleEntity pSousFamilleEntite,
    MetrageEntite pMetrageEntite, GrosseurEntite pGrosseurEntite, MatiereArticleEntite pMatiereEntite) {

    ArticleEntite vArticleEntity = new ArticleEntite();

    if (pArticleValue.getId() != null) {
      vArticleEntity.setId(pArticleValue.getId());
    }
    /** The ref. */
    vArticleEntity.setRef(pArticleValue.getRef());
    /** Designation. */
    vArticleEntity.setDesignation(pArticleValue.getDesignation());
    /** prix unitaire */
    vArticleEntity.setPu(pArticleValue.getPu());
    /** pmp */
    vArticleEntity.setPmp(pArticleValue.getPmp());
    /** producteur. */
    vArticleEntity.setProducteur(pArticleValue.getProducteur());
    /** date introduction */
    vArticleEntity.setDateIntroduction(pArticleValue.getDateIntroduction());
    /** laize */
    vArticleEntity.setLaize(pArticleValue.getLaize());
    /** poids */
    vArticleEntity.setPoids(pArticleValue.getPoids());
    /** tare */
    vArticleEntity.setTare(pArticleValue.getTare());
    /** poids */
    vArticleEntity.setPoidsBrut(pArticleValue.getPoidsBrut());
    /** observations. */
    vArticleEntity.setObservation(pArticleValue.getObservation());
    /** couleur. */
    vArticleEntity.setCouleur(pArticleValue.getCouleur());
    /** site */
    vArticleEntity.setSiteEntite(pArticleValue.getSiteEntite());
    /** Sous Famille */
    vArticleEntity.setSousFamilleArtEntite(pSousFamilleEntite);
    /** Site */
    vArticleEntity.setSiteEntite(pArticleValue.getSiteEntite());
    
    /** Grosseur */
    if (pGrosseurEntite != null && pGrosseurEntite.getId() != null) {
      vArticleEntity.setGrosseurEntite(pGrosseurEntite);
    }
    /** Metrage */
    if (pMetrageEntite != null && pMetrageEntite.getId() != null) {
      vArticleEntity.setMetrageEntite(pMetrageEntite);
    }
    /** Matiere */
    if (pMatiereEntite != null && pMatiereEntite.getId() != null) {
      vArticleEntity.setMatiereEntite(pMatiereEntite);
    }
    /** Unite */
    vArticleEntity.setUniteEntite(pArticleValue.getUniteEntite());
    /** Methode */
    vArticleEntity.setMethodeGestion(pArticleValue.getMethodeGestion());
    
    /** Emplacement */
    vArticleEntity.setCouleur(pArticleValue.getCouleur());
    
    /** Code fournisseur */
    vArticleEntity.setCodeFournisseur(pArticleValue.getCodeFournisseur());

    // Liste Document
    if (pArticleValue.getDocumentEntites() != null) {

      Set < DocumentArticleEntite > vListeDocuments = new HashSet < DocumentArticleEntite >();

      for (DocumentArticleValue vDocumentValue : pArticleValue.getDocumentEntites()) {
        DocumentArticleEntite vDe = toEntite(vDocumentValue);
        vDe.setArticle(vArticleEntity);
        vListeDocuments.add(vDe);
      }

      vArticleEntity.setDocumentEntites(vListeDocuments);

    }
    // Liste Seuils

    if (pArticleValue.getSeuilEntities() != null) {

      Set < SeuilArticleEntity > vListeSeuil = new HashSet < SeuilArticleEntity >();

      for (SeuilArticleValue vSeuilValue : pArticleValue.getSeuilEntities()) {
        SeuilArticleEntity vSe = toEntity(vSeuilValue);
        vSe.setArticle(vArticleEntity);
        vListeSeuil.add(vSe);
      }

      vArticleEntity.setSeuilEntites(vListeSeuil);

    }

    return vArticleEntity;
  }

  /**
   * Converstion Article entity en value
   **/
  public static ArticleValue toArticleValue(ArticleEntite pArticleEntity) {

    ArticleValue vArticleValue = new ArticleValue();

    /** Id */
    vArticleValue.setId(pArticleEntity.getId());

    /** The ref. */
    vArticleValue.setRef(pArticleEntity.getRef());
    /** Designation. */
    vArticleValue.setDesignation(pArticleEntity.getDesignation());
    /** prix unitaire */
    vArticleValue.setPu(pArticleEntity.getPu());
    /** poids */
    vArticleValue.setPoids(pArticleEntity.getPoids());
    /** poids Brut */
    vArticleValue.setPoidsBrut(pArticleEntity.getPoidsBrut());
    /** tare */
    vArticleValue.setTare(pArticleEntity.getTare());
    /** pmp */
    vArticleValue.setPmp(pArticleEntity.getPmp());
    /** producteur. */
    vArticleValue.setProducteur(pArticleEntity.getProducteur());
    /** date introduction */
    vArticleValue.setDateIntroduction(pArticleEntity.getDateIntroduction());
    /** laize */
    vArticleValue.setLaize(pArticleEntity.getLaize());
    /** date introduction */
    vArticleValue.setDateIntroduction(pArticleEntity.getDateIntroduction());
    /** observations. */
    vArticleValue.setObservation(pArticleEntity.getObservation());
    /** couleur. */
    vArticleValue.setCouleur(pArticleEntity.getCouleur());
    /** site */
    vArticleValue.setSiteEntite(pArticleEntity.getSiteEntite());
    
    
    vArticleValue.setCodeFournisseur(pArticleEntity.getCodeFournisseur());
    
    /** Sous Famille */
    if (pArticleEntity.getSousFamilleArtEntite() != null) {
      vArticleValue.setSousFamilleArtEntite(pArticleEntity.getSousFamilleArtEntite().getId());
      vArticleValue.setSousFamilleArtEntiteDesignation(pArticleEntity.getSousFamilleArtEntite().getDesignation());
      vArticleValue.setFamilleArticleDesignation(pArticleEntity.getSousFamilleArtEntite().getFamilleArticle().getDesignation());
    }
    

    
    
    /** Site */
    if (pArticleEntity.getSiteEntite() != null) {
      vArticleValue.setSiteEntite(pArticleEntity.getSiteEntite());
    }
    /** Grosseur */
    if (pArticleEntity.getGrosseurEntite() != null) {
      vArticleValue.setGrosseurEntite(pArticleEntity.getGrosseurEntite().getId());
    }
    /** Metrage */
    if (pArticleEntity.getMetrageEntite() != null) {
      vArticleValue.setMetrageEntite(pArticleEntity.getMetrageEntite().getId());
    }
    /** Matière */
    if (pArticleEntity.getMatiereEntite() != null) {
      vArticleValue.setMatiereEntite(pArticleEntity.getMatiereEntite().getId());
    }
    /** Unite */
    if (pArticleEntity.getUniteEntite() != null) {
      vArticleValue.setUniteEntite(pArticleEntity.getUniteEntite());
    }
    /** Methode de Gestion */
    if (pArticleEntity.getMethodeGestion() != null) {
      vArticleValue.setMethodeGestion(pArticleEntity.getMethodeGestion());
    }
    /** Emplacement */
    if (pArticleEntity.getCouleur() != null) {
      vArticleValue.setCouleur(pArticleEntity.getCouleur());
    }

    // Liste Documents
    if (pArticleEntity.getDocumentEntites() != null) {

      Set < DocumentArticleValue > vListeDocuments = new HashSet < DocumentArticleValue >();

      for (DocumentArticleEntite vDocumentEntity : pArticleEntity.getDocumentEntites()) {
        DocumentArticleValue vDe = toValue(vDocumentEntity);

        vListeDocuments.add(vDe);
      }

      vArticleValue.setDocumentEntites(vListeDocuments);

    }
    // Liste Seuils

    if (pArticleEntity.getSeuilEntites() != null) {

      Set < SeuilArticleValue > vListeSeuil = new HashSet < SeuilArticleValue >();

      for (SeuilArticleEntity vSeuilEntity : pArticleEntity.getSeuilEntites()) {
        SeuilArticleValue vSe = toValue(vSeuilEntity);

        vListeSeuil.add(vSe);
      }

      vArticleValue.setSeuilEntities(vListeSeuil);

    }

    return vArticleValue;
  }

  /**
   * Converstion entité à value du Document
   * 
   * @param
   * @return
   */
  public static DocumentArticleEntite toEntite(DocumentArticleValue pDocumentValue) {
    DocumentArticleEntite vDocumentEntite = new DocumentArticleEntite();
    if (pDocumentValue.getId() != null) {
      vDocumentEntite.setId(pDocumentValue.getId());
    }
    vDocumentEntite.setPath(pDocumentValue.getPath());
    vDocumentEntite.setUidDocument(pDocumentValue.getUidDocument());
    vDocumentEntite.setTypeDocumentEntite(pDocumentValue.getTypeDocumentEntite());

    return vDocumentEntite;

  }

  /**
   * Converstion value à entité du Document
   * 
   * @param
   * @return
   */
  public static DocumentArticleValue toValue(DocumentArticleEntite pDocumentEntite) {
    DocumentArticleValue vDocumentValue = new DocumentArticleValue();
    if (pDocumentEntite.getId() != null) {
      vDocumentValue.setId(pDocumentEntite.getId());
    }
    vDocumentValue.setPath(pDocumentEntite.getPath());
    vDocumentValue.setUidDocument(pDocumentEntite.getUidDocument());
    vDocumentValue.setTypeDocumentEntite(pDocumentEntite.getTypeDocumentEntite());

    return vDocumentValue;

  }

  /** SousFamilleArticleEntite to SousfamilleArticleValue **/
  public static SousFamilleProduitValue toValue(SousFamilleProduitEntity pSousFamilleProduitEntity) {
    SousFamilleProduitValue sousFamilleProduitValue = new SousFamilleProduitValue();
    sousFamilleProduitValue.setId(pSousFamilleProduitEntity.getId());
    sousFamilleProduitValue.setDesignation(pSousFamilleProduitEntity.getDesignation());
    if (pSousFamilleProduitEntity.getFamilleProduit() != null) {
      sousFamilleProduitValue.setFamilleProduitId(pSousFamilleProduitEntity.getFamilleProduit().getId());
    }
    return sousFamilleProduitValue;
  }

  /** SousFamilleArticleValue to SousfamilleArticleEntite **/
  public static SousFamilleProduitEntity toEntity(SousFamilleProduitValue pSousFamilleProduitValue,
    FamilleProduitEntity pFamilleProduit) {
    SousFamilleProduitEntity sousFamilleProduitEntity = new SousFamilleProduitEntity();
    if (pSousFamilleProduitValue.getId() != null) {
      sousFamilleProduitEntity.setId(pSousFamilleProduitValue.getId());
    }
    sousFamilleProduitEntity.setDesignation(pSousFamilleProduitValue.getDesignation());
    sousFamilleProduitEntity.setFamilleProduit(pFamilleProduit);
    return sousFamilleProduitEntity;
  }

  /****************** Produit Entity To Value ****************************/
  public static ProduitValue toValue(ProduitEntity pProduitEntity) {
    ProduitValue produitValue = new ProduitValue();
    produitValue.setId(pProduitEntity.getId());
    produitValue.setDesignation(pProduitEntity.getDesignation());
    produitValue.setPrixUnitaire(pProduitEntity.getPrixUnitaire());
    produitValue.setEtat(pProduitEntity.getEtat());
    produitValue.setDateIntroduction(pProduitEntity.getDateIntroduction());
    produitValue.setReference(pProduitEntity.getReference());
    produitValue.setSiteId(pProduitEntity.getSiteId());
    
    produitValue.setTissu(pProduitEntity.getTissu());
    produitValue.setComposition(pProduitEntity.getComposition());
    produitValue.setEntretien(pProduitEntity.getEntretien());
    produitValue.setObservations(pProduitEntity.getObservations());
    produitValue.setObservationsDev(pProduitEntity.getObservationsDev());
    produitValue.setPrixMajore(pProduitEntity.getPrixMajore());
    produitValue.setHasFB(pProduitEntity.isFicheBesoin());

    if (pProduitEntity.getSousFamille() != null) {
      produitValue.setSousFamilleId(pProduitEntity.getSousFamille().getId());
    }
    produitValue.setPartieIntersseId(pProduitEntity.getPartieIntersseId());
    /*** Liste Document produit */
    if (pProduitEntity.getDocumentProduits() != null) {
      Set < DocumentProduitValue > vListeDocuments = new HashSet < DocumentProduitValue >();
      for (DocumentProduitEntity vDocumentEntity : pProduitEntity.getDocumentProduits()) {
        DocumentProduitValue vDe = toValue(vDocumentEntity);
        vListeDocuments.add(vDe);
      }
      produitValue.setDocumentProduits(vListeDocuments);
    }
    /** liste details prix produit */
    if (pProduitEntity.getDetailsPrix() != null) {
      Set < DetailsPrixProduitValue > vListeDeatilsPrix = new HashSet < DetailsPrixProduitValue >();
      for (DetailsPrixProduitEntite vDetailsPrixEntity : pProduitEntity.getDetailsPrix()) {
        DetailsPrixProduitValue vDte = toValue(vDetailsPrixEntity);
        vListeDeatilsPrix.add(vDte);
      }
      produitValue.setDetailsPrix(vListeDeatilsPrix);
    }
    /** liste phaseProduit */
    if (pProduitEntity.getPhaseProduits() != null) {

      List <PhaseProduitValue> vListePhaseProduit = new ArrayList <PhaseProduitValue>();

      for (PhaseProduitEntite vPhaseProduitEntity : pProduitEntity.getPhaseProduits()) {
        PhaseProduitValue vPhPr = toValue(vPhaseProduitEntity);
        vListePhaseProduit.add(vPhPr);
      }
      
      Collections.sort(vListePhaseProduit);
      produitValue.setPhaseProduits(new TreeSet<>(vListePhaseProduit));
    }
    /** liste couleurProduit */
    if (pProduitEntity.getCouleurProduits() != null) {
      Set < ProduitCouleurValue > vListeCouleurProduit = new HashSet < ProduitCouleurValue >();
      for (ProduitCouleurEntite vCouleurProduitEntity : pProduitEntity.getCouleurProduits()) {
        ProduitCouleurValue vCouleurPr = toValue(vCouleurProduitEntity);
        vListeCouleurProduit.add(vCouleurPr);
      }
      
      produitValue.setCouleurProduit(vListeCouleurProduit);
    }
    /** liste tailleProduit */
    if (pProduitEntity.getTailleProduits() != null) {
      List < ProduitTailleValue > vListeTailleProduit = new ArrayList < ProduitTailleValue >();
      for (ProduitTailleEntite vTailleProduitEntity : pProduitEntity.getTailleProduits()) {
        ProduitTailleValue vTaillePr = toValue(vTailleProduitEntity);
        vListeTailleProduit.add(vTaillePr);
      }
      
      produitValue.setTailleProduit(vListeTailleProduit);
    }

    //System.out.println("produit toValue " + produitValue.toString());
    return produitValue;
  }

  /** produitArticleValue to produitEntite **/
  public static ProduitEntity toEntity(ProduitValue pProduitValue, SousFamilleProduitEntity pSousFamille) {
    ProduitEntity produiEntity = new ProduitEntity();
    if (pProduitValue.getId() != null) {
      produiEntity.setId(pProduitValue.getId());
    }
    produiEntity.setDesignation(pProduitValue.getDesignation());
    produiEntity.setPrixUnitaire(pProduitValue.getPrixUnitaire());
    produiEntity.setEtat(pProduitValue.getEtat());
    produiEntity.setDateIntroduction(pProduitValue.getDateIntroduction());
    produiEntity.setReference(pProduitValue.getReference());
    produiEntity.setSiteId(pProduitValue.getSiteId());
    produiEntity.setPartieIntersseId(pProduitValue.getPartieIntersseId());
    produiEntity.setSousFamille(pSousFamille);
    
    produiEntity.setTissu(pProduitValue.getTissu());
    produiEntity.setComposition(pProduitValue.getComposition());
    produiEntity.setEntretien(pProduitValue.getEntretien());
    produiEntity.setObservations(pProduitValue.getObservations());
    produiEntity.setObservationsDev(pProduitValue.getObservationsDev());
    produiEntity.setPrixMajore(pProduitValue.getPrixMajore());
    produiEntity.setFicheBesoin(pProduitValue.isHasFB());
    
    /*** Liste Document produit */
    if (pProduitValue.getDocumentProduits() != null) {
      Set < DocumentProduitEntity > vListeDocuments = new HashSet < DocumentProduitEntity >();
      for (DocumentProduitValue vDocumentValue : pProduitValue.getDocumentProduits()) {
        DocumentProduitEntity vDe = toEntity(vDocumentValue);
        vDe.setProuduit(produiEntity);
        vListeDocuments.add(vDe);
      }
      produiEntity.setDocumentProduits(vListeDocuments);
    }
    /** liste details prix produit */
    if (pProduitValue.getDetailsPrix() != null) {
      Set < DetailsPrixProduitEntite > vListeDeatilsPrix = new HashSet < DetailsPrixProduitEntite >();
      for (DetailsPrixProduitValue vDetailsPrixValue : pProduitValue.getDetailsPrix()) {
        DetailsPrixProduitEntite vDte = toEntity(vDetailsPrixValue);
        vDte.setProuduit(produiEntity);
        vListeDeatilsPrix.add(vDte);
      }
      produiEntity.setDetailsPrix(vListeDeatilsPrix);
    }
    /** liste phaseProduit */
    if (pProduitValue.getPhaseProduits() != null) {

      Set < PhaseProduitEntite > vListePhaseProduit = new HashSet < PhaseProduitEntite >();

      for (PhaseProduitValue vPhaseProduitValue : pProduitValue.getPhaseProduits()) {
        PhaseProduitEntite vPhPr = toEntity(vPhaseProduitValue);
        vPhPr.setProduit(produiEntity);
        vListePhaseProduit.add(vPhPr);
      }
      produiEntity.setPhaseProduits(vListePhaseProduit);
    }
    /** liste couleurProduit */
    if (pProduitValue.getCouleurProduit() != null) {

      Set < ProduitCouleurEntite > vListeCouleurProduit = new HashSet < ProduitCouleurEntite >();

      for (ProduitCouleurValue vCouleurProduitValue : pProduitValue.getCouleurProduit()) {
        ProduitCouleurEntite vCouleurPr = toEntity(vCouleurProduitValue);
        vCouleurPr.setProduit(produiEntity);
        vListeCouleurProduit.add(vCouleurPr);
      }
      produiEntity.setCouleurProduits(vListeCouleurProduit);
    }
    /** liste tailleProduit */
    if (pProduitValue.getTailleProduit() != null) {

      Set < ProduitTailleEntite > vListeTailleProduit = new HashSet < ProduitTailleEntite >();

      for (ProduitTailleValue vTailleProduitValue : pProduitValue.getTailleProduit()) {
        ProduitTailleEntite vTaillePr = toEntity(vTailleProduitValue);
        vTaillePr.setProduit(produiEntity);
        vListeTailleProduit.add(vTaillePr);
      }
      produiEntity.setTailleProduits(vListeTailleProduit);
    }

    return produiEntity;
  }

  /** details prix produit Entite to details prix produit Value **/
  public static DetailsPrixProduitValue toValue(DetailsPrixProduitEntite pDetailsPrixProduitEntity) {
    DetailsPrixProduitValue detailsPrixProduitValue = new DetailsPrixProduitValue();
    detailsPrixProduitValue.setId(pDetailsPrixProduitEntity.getId());
    detailsPrixProduitValue.setMethode(pDetailsPrixProduitEntity.getMethode());
    detailsPrixProduitValue.setPrixVente(pDetailsPrixProduitEntity.getPrixVente());
    detailsPrixProduitValue.setQuantiteInferieur(pDetailsPrixProduitEntity.getQuantiteInferieur());
    detailsPrixProduitValue.setQuantiteSuperieur(pDetailsPrixProduitEntity.getQuantiteSuperieur());
    return detailsPrixProduitValue;
  }

  /** details prix produit Value to details prix produit Entite **/
  public static DetailsPrixProduitEntite toEntity(DetailsPrixProduitValue pDetailsPrixProduitValue) {
    DetailsPrixProduitEntite detailsPrixProduitEntity = new DetailsPrixProduitEntite();
    if (pDetailsPrixProduitValue.getId() != null) {
      detailsPrixProduitEntity.setId(pDetailsPrixProduitValue.getId());
    }
    detailsPrixProduitEntity.setMethode(pDetailsPrixProduitValue.getMethode());
    detailsPrixProduitEntity.setPrixVente(pDetailsPrixProduitValue.getPrixVente());

    detailsPrixProduitEntity.setQuantiteInferieur(pDetailsPrixProduitValue.getQuantiteInferieur());
    detailsPrixProduitEntity.setQuantiteSuperieur(pDetailsPrixProduitValue.getQuantiteSuperieur());
    return detailsPrixProduitEntity;
  }

  /** DocumentProduitEntite to DocumentProduitValue **/
  public static DocumentProduitValue toValue(DocumentProduitEntity pDocumentProduitEntity) {
    DocumentProduitValue documentProduitValue = new DocumentProduitValue();
    documentProduitValue.setId(pDocumentProduitEntity.getId());
    documentProduitValue.setPath(pDocumentProduitEntity.getPath());
    // documentProduitValue.setProduitId(pDocumentProduitEntity.getProduitId());
    documentProduitValue.setUidDocument(pDocumentProduitEntity.getUidDocument());
    documentProduitValue.setTypeDocumentId(pDocumentProduitEntity.getTypeDocumentId());
    return documentProduitValue;
  }

  /** DocumentProduitValue to DocumentProduitEntite **/
  public static DocumentProduitEntity toEntity(DocumentProduitValue pDocumentProduitValue) {
    DocumentProduitEntity documentProduitEntity = new DocumentProduitEntity();
    if (pDocumentProduitValue.getId() != null) {
      documentProduitEntity.setId(pDocumentProduitValue.getId());
    }
    documentProduitEntity.setPath(pDocumentProduitValue.getPath());
    // documentProduitEntity.setProduitId(pDocumentProduitValue.getProduitId());
    documentProduitEntity.setUidDocument(pDocumentProduitValue.getUidDocument());
    documentProduitEntity.setTypeDocumentId(pDocumentProduitValue.getTypeDocumentId());
    return documentProduitEntity;
  }

  /** FamilleProduitEntite to familleProduitValue **/
  public static FamilleProduitValue toValue(FamilleProduitEntity pFamilleProduitEntity) {
    FamilleProduitValue familleProduitValue = new FamilleProduitValue();
    familleProduitValue.setId(pFamilleProduitEntity.getId());
    familleProduitValue.setDesignation(pFamilleProduitEntity.getDesignation());
    return familleProduitValue;
  }

  /** FamilleProduitValue to familleProduitEntite **/
  public static FamilleProduitEntity toEntity(FamilleProduitValue pFamilleProduitValue) {
    FamilleProduitEntity familleProduitEntity = new FamilleProduitEntity();
    if (pFamilleProduitValue.getId() != null) {
      familleProduitEntity.setId(pFamilleProduitValue.getId());
    }
    familleProduitEntity.setDesignation(pFamilleProduitValue.getDesignation());
    return familleProduitEntity;
  }

  /** FamilleEntite to familleValue **/
  public static FamilleValue toValue(FamilleEntite pFamilleEntity) {
    FamilleValue familleValue = new FamilleValue();
    familleValue.setId(pFamilleEntity.getId());
    familleValue.setDesignation(pFamilleEntity.getDesignation());
    familleValue.setAchat(pFamilleEntity.isAchat());
    familleValue.setVente(pFamilleEntity.isVente());
    return familleValue;
  }

  /** FamilleValue to familleEntite **/
  public static FamilleEntite toEntity(FamilleValue pFamilleValue) {
    FamilleEntite familleEntity = new FamilleEntite();
    if (pFamilleValue.getId() != null) {
      familleEntity.setId(pFamilleValue.getId());
    }
    familleEntity.setDesignation(pFamilleValue.getDesignation());
    familleEntity.setAchat(pFamilleValue.isAchat());
    familleEntity.setVente(pFamilleValue.isVente());
    return familleEntity;
  }

  /**** listValue produit to liste entity ******/
  public static List < ProduitValue > tolistProduitsValues(List < ProduitEntity > pProduitEntitys) {
    List < ProduitValue > vProduits = new ArrayList < ProduitValue >();
    for (ProduitEntity vProduitEntite : pProduitEntitys) {
      vProduits.add(PersistanceUtilities.toValue(vProduitEntite));
    }
    return vProduits;
  }

  /**** listValue sous famille produit to liste entity ******/
  public static List < SousFamilleProduitValue > tolistSousFamilleProduitValues(
    List < SousFamilleProduitEntity > pSousFamilleEntites) {
    List < SousFamilleProduitValue > vSousFamilleProduitValues = new ArrayList < SousFamilleProduitValue >();
    for (SousFamilleProduitEntity sousFamilleEntite : pSousFamilleEntites) {
      vSousFamilleProduitValues.add(PersistanceUtilities.toValue(sousFamilleEntite));
    }
    return vSousFamilleProduitValues;
  }

  /**** listValue famille produit to liste entity ******/
  public static List < FamilleProduitValue > tolistFamilleProduitValues(List < FamilleProduitEntity > pFamilleEntites) {
    List < FamilleProduitValue > vFamilleProduitValues = new ArrayList < FamilleProduitValue >();
    for (FamilleProduitEntity familleEntite : pFamilleEntites) {
      vFamilleProduitValues.add(PersistanceUtilities.toValue(familleEntite));
    }
    return vFamilleProduitValues;
  }

  /**** listValue document produit to liste entity ******/
  public static List < DocumentProduitValue > tolistDocumentProduitValues(List < DocumentProduitEntity > pDocumentEntites) {
    List < DocumentProduitValue > vDocumentProduitValues = new ArrayList < DocumentProduitValue >();
    for (DocumentProduitEntity documentEntite : pDocumentEntites) {
      vDocumentProduitValues.add(PersistanceUtilities.toValue(documentEntite));
    }
    return vDocumentProduitValues;
  }

  /**** listValue Details prix produit to liste entity ******/
  public static List < DetailsPrixProduitValue > tolistDetailPrixProduitValues(
    List < DetailsPrixProduitEntite > pDetailsPrixProduitEntitys) {
    List < DetailsPrixProduitValue > vDetailsPrixProduitValues = new ArrayList < DetailsPrixProduitValue >();
    for (DetailsPrixProduitEntite DetaisPrixEntite : pDetailsPrixProduitEntitys) {
      vDetailsPrixProduitValues.add(PersistanceUtilities.toValue(DetaisPrixEntite));
    }
    return vDetailsPrixProduitValues;
  }

  /**
   * Accesseur en lecture de l'attribut <code>vGestionnaireDocument</code>.
   * 
   * @return IGestionnaireDocument L'attribut vGestionnaireDocument à lire.
   */
  public IGestionnaireDocument getvGestionnaireDocument() {
    return vGestionnaireDocument;
  }

  /**
   * Accesseur en écriture de l'attribut <code>vGestionnaireDocument</code>.
   *
   * @param vGestionnaireDocument
   *          L'attribut vGestionnaireDocument à modifier.
   */
  public void setvGestionnaireDocument(IGestionnaireDocument vGestionnaireDocument) {
    this.vGestionnaireDocument = vGestionnaireDocument;
  }


  public static FicheBesoinValue toFicheBesoinValue(FicheBesoinEntite entity){
	  	//System.out.println("------------toValue------------");
	  FicheBesoinValue dto = new FicheBesoinValue();
	  
	  dto.setIdFicheBesoin(entity.getId());
	  dto.setDateCreation(entity.getDateCreation());
	  dto.setDateIntroduction(entity.getDateIntroduction());
	  dto.setObservation(entity.getObservation());
	  dto.setResponsable(entity.getResponsable());

	  if(entity.getProduitEntity()!=null){
		  
		  dto.setProduitValue(PersistanceUtilities.toValue(entity.getProduitEntity()));
	  }
		  
	  
	  if(entity.getElementFicheBesoin()!=null)
		  dto.setElementBesoinValue(PersistanceUtilities.toSetElementFicheBesoinValues(entity.getElementFicheBesoin()));
		  

	  return dto;
  }
  

  public static ElementBesoinValue toElementFicheBesoinValue(ElementFicheBesoinEntite pElementFicheBesoinEntite){
	  ElementBesoinValue vElementBesoinValue = new ElementBesoinValue();
	  
	  ArticleValue vArticle = new ArticleValue();
//	 CouleurValue vCouleur = new CouleurValue();
//	 TailleValue  vTaille  = new TailleValue();
	  vArticle.setId(pElementFicheBesoinEntite.getEb_article_id());
//	  vCouleur.setId(pElementFicheBesoinEntite.getEb_couleur_id());
//	  vTaille.setId(pElementFicheBesoinEntite.getEb_taille_id());
//	  
	  vElementBesoinValue.setArticle(vArticle);
//	  vElementBesoinValue.setCouleur(vCouleur);
//	  vElementBesoinValue.setTaille(vTaille);
	  
	  if(pElementFicheBesoinEntite.getFicheBesoin() != null){
		  vElementBesoinValue.setFicheBesoinId(pElementFicheBesoinEntite.getFicheBesoin().getId());
	  }
	  
		 vElementBesoinValue.setEb_couleur_id(pElementFicheBesoinEntite.getEb_couleur_id());
		 vElementBesoinValue.setEb_taille_id(pElementFicheBesoinEntite.getEb_taille_id());
		 
		 
	  
	  vElementBesoinValue.setIdElementBesoinValue(pElementFicheBesoinEntite.getId());
	  vElementBesoinValue.setBloQuatite(pElementFicheBesoinEntite.isBloQuatite());
	  vElementBesoinValue.setDateCreation(pElementFicheBesoinEntite.getDateCreation());
//	  vElementBesoinValue.setBlSuppression(pElementFicheBesoinEntite.isBlSuppression());	  
//	  vElementBesoinValue.setDateModification(pElementFicheBesoinEntite.getDateModification());
//	  vElementBesoinValue.setDateSuppression(pElementFicheBesoinEntite.getDateSuppression());
	  vElementBesoinValue.setOrdre(pElementFicheBesoinEntite.getOrdre());
	  vElementBesoinValue.setPhase(pElementFicheBesoinEntite.getPhase());
	  vElementBesoinValue.setQuantite(pElementFicheBesoinEntite.getQuantite());
	  vElementBesoinValue.setType(pElementFicheBesoinEntite.getType());
	  
	  return vElementBesoinValue;
  }

  	public static Set < ElementBesoinValue > toSetElementFicheBesoinValues(Set < ElementFicheBesoinEntite > elementFicheBesoinList) {
	  
	    Set < ElementBesoinValue> resultList = new HashSet < ElementBesoinValue >();
	    
	    for (ElementFicheBesoinEntite vItem : elementFicheBesoinList) {
	    	
	    	resultList.add(PersistanceUtilities.toElementFicheBesoinValue(vItem));
	    }
	    
	    return resultList;
	  }
 
  
  
  /**
   * Converter FicheBesoinValue en FicheBesoinEntite
   * @param pFicheBesoinValue
   * @param pProduitEntity
   * @return
   */
  
  public static FicheBesoinEntite toEntity(FicheBesoinValue pFicheBesoinValue,ProduitEntity pProduitEntity,FicheBesoinEntite pFicheBesoinEntite){
	  	
	  if(pFicheBesoinEntite==null)
	  		pFicheBesoinEntite=new FicheBesoinEntite();
	  
		  //vFicheBesoinEntite.setBlSuppression(pFicheBesoinValue.isBlSuppression());
	  		pFicheBesoinEntite.setDateCreation(pFicheBesoinValue.getDateCreation());
	  		pFicheBesoinEntite.setDateIntroduction(pFicheBesoinValue.getDateCreation());
		  //vFicheBesoinEntite.setDateSuppression(pFicheBesoinValue.getDateSuppression());
	  		pFicheBesoinEntite.setObservation(pFicheBesoinValue.getObservation());
	  		pFicheBesoinEntite.setProduitEntity(pProduitEntity);
	  		pFicheBesoinEntite.setResponsable(pFicheBesoinValue.getResponsable());
	  		pFicheBesoinEntite.setElementFicheBesoin(PersistanceUtilities.toSetElementFicheBesoinEntite(pFicheBesoinValue.getElementBesoinValue(), pFicheBesoinEntite));
		  
		  return pFicheBesoinEntite;
  }
  
  /**
   * retourner une list des elements fiches besoin
   * @param pElementBesoinValue
   * @return
   */
  
  public static Set < ElementFicheBesoinEntite > toSetElementFicheBesoinEntite(Set < ElementBesoinValue > pElementBesoinValue, FicheBesoinEntite pFicheBesoinEntite) {

	  Set < ElementFicheBesoinEntite> vNewElementFicheBesoinEntite     = new HashSet<ElementFicheBesoinEntite>();
	  	Set < ElementBesoinValue >      vListElementBesoinValueTmp 		 = new HashSet<ElementBesoinValue>();
	  	
	  	 //Si Element fiche Besoin global alors on doit créer des autre 
	  	 //elements fiche besoin. pour ce la on doit crééer une autre boucle
	     //pour vérifier et créer une nouvelle element fiche besoin et l'ajouter des fiches element
	  	//pour tout couleur et pour tout taille
	  	
	  	for (ElementBesoinValue vItemElementBesoinValue : pElementBesoinValue){
	  		
	  		if(vItemElementBesoinValue.getEb_couleur_id()==null){
	  			for(ProduitTailleEntite vItemProduitTailleEntite : pFicheBesoinEntite.getProduitEntity().getTailleProduits()){
	  				for(ProduitCouleurEntite vItemProduitCouleurEntite : pFicheBesoinEntite.getProduitEntity().getCouleurProduits()){
	  					ElementBesoinValue  vElementBesoinValueTmp = new ElementBesoinValue();
	  					vElementBesoinValueTmp.setArticle(vItemElementBesoinValue.getArticle());
	  					vElementBesoinValueTmp.setBloQuatite(vItemElementBesoinValue.isBloQuatite());
	  					vElementBesoinValueTmp.setDateCreation(vItemElementBesoinValue.getDateCreation());
	  					vElementBesoinValueTmp.setFicheBesoinId(vItemElementBesoinValue.getFicheBesoinId());
	  					vElementBesoinValueTmp.setOrdre(vItemElementBesoinValue.getOrdre());
	  					vElementBesoinValueTmp.setPhase(vItemElementBesoinValue.getPhase());
	  					vElementBesoinValueTmp.setQuantite(vItemElementBesoinValue.getQuantite());
	  					
	  					vElementBesoinValueTmp.setEb_taille_id(vItemProduitTailleEntite.getEbTailleId());  //Set Taille
	  					vElementBesoinValueTmp.setEb_couleur_id(vItemProduitCouleurEntite.getEbCouleurId());//set Couleur
	  					vElementBesoinValueTmp.setType(vItemElementBesoinValue.getType());
	  					//System.out.println("#####   RESULT  ORIGINE  :    "+chercherElementBesoin(vElementBesoinValueTmp,vListElementBesoinValueTmp));
	  					if(chercherElementBesoin(vElementBesoinValueTmp,vListElementBesoinValueTmp)==false)
	  					vListElementBesoinValueTmp.add(vElementBesoinValueTmp);
	  					
	  				}
	  			}
	  		}else{
	  			if(chercherElementBesoin(vItemElementBesoinValue,vListElementBesoinValueTmp)==false)
	  			vListElementBesoinValueTmp.add(vItemElementBesoinValue);
	  		}
	  	   
	  	}
	  	
	  	//Convert from set ElementBesoinValue to ElementFicheBesoinEntite
	  	
	  	for (ElementBesoinValue vItemElementBesoinValue : vListElementBesoinValueTmp) {
	  		
	    	vNewElementFicheBesoinEntite.add(PersistanceUtilities.toEntity(vItemElementBesoinValue,pFicheBesoinEntite));
	    }
	  	
	  	
	    return vNewElementFicheBesoinEntite;
	  }
  

  /**
   * Convert from ElementBesoinValue to ElementFicheBesoinEntite
   * ElementFicheBesoinValue to ElementFicheBesoinEntite 
   * @param pElementBesoinValue
   * @return
   */

  public static ElementFicheBesoinEntite toEntity(ElementBesoinValue pItemElementBesoinValue, FicheBesoinEntite pFicheBesoinEntite) {
	  
	  ElementFicheBesoinEntite vElementFicheBesoinEntite = new ElementFicheBesoinEntite();
	  
	  if(pItemElementBesoinValue.getIdElementBesoinValue()!=null) //Update Element Fiche Besoin
		  vElementFicheBesoinEntite.setId(pItemElementBesoinValue.getIdElementBesoinValue());
	  
		  vElementFicheBesoinEntite.setBloQuatite(pItemElementBesoinValue.isBloQuatite());
		  //vElementFicheBesoinEntite.setBlSuppression(pElementBesoinValue.isBlSuppression());
		  vElementFicheBesoinEntite.setDateCreation(pItemElementBesoinValue.getDateCreation());
		  vElementFicheBesoinEntite.setDateModification(pItemElementBesoinValue.getDateCreation());
		  //vElementFicheBesoinEntite.setDateSuppression(pElementBesoinValue.getDateSuppression());
		  vElementFicheBesoinEntite.setOrdre(pItemElementBesoinValue.getOrdre());
		  vElementFicheBesoinEntite.setPhase(pItemElementBesoinValue.getPhase());
		  vElementFicheBesoinEntite.setQuantite(pItemElementBesoinValue.getQuantite());
		  vElementFicheBesoinEntite.setFicheBesoin(pFicheBesoinEntite);
		  vElementFicheBesoinEntite.setType(pItemElementBesoinValue.getType());
		  //Converstion ProduitTailleValue en TailleEntite
      if(pItemElementBesoinValue.getArticle()!=null)
        vElementFicheBesoinEntite.setEb_article_id(pItemElementBesoinValue.getArticle().getId());
        
		  vElementFicheBesoinEntite.setEb_couleur_id(pItemElementBesoinValue.getEb_couleur_id());
		  vElementFicheBesoinEntite.setEb_taille_id(pItemElementBesoinValue.getEb_taille_id());
		  
	  return vElementFicheBesoinEntite;
  }

  public static List<FicheBesoinValue> listFicheBesoinToValue(
		List<FicheBesoinEntite> listEntity) {
	
	List<FicheBesoinValue> list = new ArrayList<FicheBesoinValue>();
	
	for(FicheBesoinEntite entity : listEntity){
		list.add(toFicheBesoinValue(entity));
	}

	return list;
  }  
  
  
	public static BaseInfoValue toBaseInfoValue(BaseInfoEntity entity) {

		BaseInfoValue dto = new BaseInfoValue();

		dto.setId(entity.getId());
		dto.setDesignation(entity.getDesignation());
		dto.setValeur(entity.getValeur());
		dto.setUnite(entity.getUnite());
		dto.setLogo(entity.getLogo());
		dto.setActif(entity.isActif());
		dto.setSortieChaine(entity.getSortieChaine());
		dto.setSortieCoupe(entity.getSortieCoupe());
		dto.setEclatement(entity.getEclatement());
		dto.setConditionnement(entity.getConditionnement());
		dto.setSupp1(entity.getSupp1());
		dto.setSupp2(entity.getSupp2());
		dto.setEngagement(entity.getEngagement());
	        

		return dto;
	}

	public static BaseInfoEntity toBaseInfoEntity(BaseInfoValue dto) {

		BaseInfoEntity entity = new BaseInfoEntity();

		entity.setId(dto.getId());
		entity.setDesignation(dto.getDesignation());
		entity.setValeur(dto.getValeur());
		entity.setUnite(dto.getUnite());
		entity.setLogo(dto.getLogo());
		entity.setActif(dto.isActif());
		return entity;
	}

	public static UserValue toUserValue(UserEntity entity) {
		
		UserValue dto = new UserValue();
		
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setEmail(entity.getEmail());
		dto.setPhoneNumber(entity.getPhoneNumber());
		dto.setUserName(entity.getUserName());
		dto.setPassword(entity.getPassword());
		dto.setEnabled(entity.getEnabled());
		
		return dto;
	}

	public static UserEntity toUserEntity(UserValue dto) {
		
		UserEntity entity = new UserEntity();
		
		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		entity.setPhoneNumber(dto.getPhoneNumber());
		entity.setUserName(dto.getUserName());
		entity.setPassword(dto.getPassword());
		entity.setEnabled(dto.getEnabled());
		
		return entity;
	}

	public static List<UserValue> listUserToValue(List<UserEntity> listEntity) {
		
		List<UserValue> list = new ArrayList<UserValue>();
		
		for(UserEntity entity : listEntity){
			list.add(toUserValue(entity));
		}
		
		return list;
	}
	
	//Added By Ghazi 
	//le 23092017
	
  private static boolean chercherElementBesoin (ElementBesoinValue pElemment,Set < ElementBesoinValue > vListElementBesoinValueTmp ){
	  boolean exist=false;
	  
	  //System.out.println("##### ELEMENT PARAM  TO COMPARE ##########");
	  //System.out.println("*  Art : "+pElemment.getArticle().getId());
	  //System.out.println("*  Taille : "+pElemment.getEb_taille_id());
	  //System.out.println("*  Couleur : "+pElemment.getEb_couleur_id());
	  //System.out.println("##### FIN ELEMENT  PARAM TO COMPARE ##########");
	  
	  for (ElementBesoinValue vElement:vListElementBesoinValueTmp){
		  
		  //System.out.println("##### ELEMENT List  TO COMPARE ##########");
		  //System.out.println("*  Art : "+vElement.getArticle().getId());
		  //System.out.println("*  Taille : "+vElement.getEb_taille_id());
		  //System.out.println("*  Couleur : "+vElement.getEb_couleur_id());
		  //System.out.println("##### FIN ELEMENT  TO COMPARE ##########");
		  
		  if (vElement.getArticle().getId().equals(pElemment.getArticle().getId()) && vElement.getEb_couleur_id().equals(pElemment.getEb_couleur_id()) && vElement.getEb_taille_id().equals(pElemment.getEb_taille_id()))
		  {  exist=true;
		    break;
		  }
	  }
	  
	  
	  //System.out.println("#### RESULT  FUNCTION  :  "+exist);
	  
	  
      return  exist ;
     }
}
