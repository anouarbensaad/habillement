package com.gpro.consulting.tiers.commun.persistance.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.IConstante;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticriterePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechechePartieInteresseeValue;
import com.gpro.consulting.tiers.commun.persistance.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.PartieInteresseEntite;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * The Class CategoriePartieInteresseeImpl.
 * 
 * @author $mohamed
 */
public class PartieInteresseePersistanceImpl extends AbstractPersistance implements IPartieInteresseePersistance {

  /** EntityManager. */
  @PersistenceContext
  private EntityManager entityManager;
  
  private int MAX_RESULTS = 26;


  private static final Logger LOGGER = LoggerFactory.getLogger(PartieInteresseePersistanceImpl.class);

  @Autowired
  private PersistanceUtilities vPersistanceUtilities;

  private String actifValue = "oui";

  /**
   * methode de creation Partie Interessée
   **/
  @Override
  public String creerPartieInteressee(PartieInteresseValue pPartieInteresseValue) {

    PartieInteresseEntite vPartieInteresseEntite = (PartieInteresseEntite) this.creer(vPersistanceUtilities
      .toPartieInteresseEntity(pPartieInteresseValue));
//    if (LOGGER.isDebugEnabled()) {
//      LOGGER.debug("Persister la Partie Interessee d'ID=" + vPartieInteresseEntite.getRaisonSociale().toString()
//        + " est en cours.");
//    }
    return vPartieInteresseEntite.getId().toString();
  }

  /**
   * supprimer partie interessee
   **/
  @Override
  public void supprimerPartieInteressee(Long pId) {
//    if (LOGGER.isDebugEnabled()) {
//      LOGGER.debug("Suppression de la Partie Interessee d'ID=" + pId.longValue() + " est en cours.");
//    }
    this.supprimerEntite(PartieInteresseEntite.class, pId.longValue());
  }

  /**
   * modifier partie interessee
   * **/
  @Override
  public String modifierPartieInteressee(PartieInteresseValue pPartieInteresseValue) {
//    if (LOGGER.isDebugEnabled()) {
//      LOGGER.debug("Modification de la Partie Interessee d'ID=" + pPartieInteresseValue.getId().toString() + " est en cours.");
//    }
    PartieInteresseEntite vPartieInteresseEntite = (PartieInteresseEntite) this.modifier(vPersistanceUtilities
      .toPartieInteresseEntity(pPartieInteresseValue));

    return vPartieInteresseEntite.getId().toString();
  }

  /**
   * recherche partie interessee Par Id
   * */

  @Override
  public PartieInteresseValue recherchePartieInteresseeParId(PartieInteresseValue pPartieInteresseValue) {
//    if (LOGGER.isDebugEnabled()) {
//      LOGGER.debug("Recherche de la Partie Interessee d'ID=" + pPartieInteresseValue.getId().toString() + " est en cours.");
//    }
    PartieInteresseEntite pPartieInteresseEntite = this.rechercherParId(pPartieInteresseValue.getId().longValue(),
      PartieInteresseEntite.class);

    PartieInteresseValue pPartieInteresseValueResult = vPersistanceUtilities.toPartieInteresseValue(pPartieInteresseEntite);
    return pPartieInteresseValueResult;
  }

  /**
   * liste partie interessee
   * */

  @Override
  public List < PartieInteresseValue > listePartieInteressee() {
    List < PartieInteresseEntite > vListePartieInterresseeEntite = this.lister(PartieInteresseEntite.class);
    List < PartieInteresseValue > vListPartieInteresseValues = new ArrayList < PartieInteresseValue >();
    for (PartieInteresseEntite vPartieInteresseEntite : vListePartieInterresseeEntite) {
      vListPartieInteresseValues.add(vPersistanceUtilities.toPartieInteresseValue(vPartieInteresseEntite));
    }
    return vListPartieInteresseValues;
  }

  /**
   * recherche Partie Intersse multi Critere
   * 
   * {@inheritDoc}
   */
  @Override
  public ResultatRechechePartieInteresseeValue rechercherPartieInteresseMultiCritere(
    RechercheMulticriterePartieInteresseeValue pRecherchePartieInteresseMulitCritere) {

    CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
    /** entity principale **/
    CriteriaQuery < PartieInteresseEntite > vCriteriaQuery = vBuilder.createQuery(PartieInteresseEntite.class);
    List < Predicate > vWhereClause = new ArrayList < Predicate >();

    /** create liste resyltat ***/

    /************ entity jointure *****************/
    Root < PartieInteresseEntite > vRootParitieInteresse = vCriteriaQuery.from(PartieInteresseEntite.class);

    /***************** Predicate *************/
    if (estNonVide(pRecherchePartieInteresseMulitCritere.getvReference())) {
      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("reference"),
        pRecherchePartieInteresseMulitCritere.getvReference()));
    }

    if (estNonVide(pRecherchePartieInteresseMulitCritere.getvRaisonSociale())) {
      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("raisonSociale"),
        pRecherchePartieInteresseMulitCritere.getvRaisonSociale()));
    }

    if (pRecherchePartieInteresseMulitCritere.getvFamillePartieInteressee() != null) {
      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("famillePartieInteressee"),
        pRecherchePartieInteresseMulitCritere.getvFamillePartieInteressee()));
    }

    if (pRecherchePartieInteresseMulitCritere.getvCategoriePartieInteressee() != null) {
      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("categoriePartieInteressee"),
        pRecherchePartieInteresseMulitCritere.getvCategoriePartieInteressee()));
    }
    if (pRecherchePartieInteresseMulitCritere.getvTypePartieInteressee() != null) {
      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("typePartieInteressee"),
        pRecherchePartieInteresseMulitCritere.getvTypePartieInteressee()));
    }
    if (estNonVide(pRecherchePartieInteresseMulitCritere.getvActivite())) {
      vWhereClause
        .add(vBuilder.equal(vRootParitieInteresse.get("activite"), pRecherchePartieInteresseMulitCritere.getvActivite()));
    }

    if (estNonVide(pRecherchePartieInteresseMulitCritere.getActif())) {
      if (pRecherchePartieInteresseMulitCritere.getActif().equals(IConstante.OUI)) {
        vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("actif"), true));
      } else {
        vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("actif"), false));
      }

    }

    /** execute query and do something with result **/

    vCriteriaQuery.select(vRootParitieInteresse).where(vWhereClause.toArray(new Predicate[] {}));
    
    vCriteriaQuery.orderBy(vBuilder.desc(vRootParitieInteresse.get("id")));
    
    List<PartieInteresseEntite > resultatEntite = null;
    //If criterias are empty
	if(pRecherchePartieInteresseMulitCritere.isOptimized()){
		resultatEntite = this.entityManager
				.createQuery(vCriteriaQuery)
				.setMaxResults(MAX_RESULTS)
				.getResultList();

	}else{
		resultatEntite = this.entityManager
				.createQuery(vCriteriaQuery)
				.getResultList();
	}

    /** Conversion de la liste en valeur */
    List < PartieInteresseValue > vListeResultat = new ArrayList < PartieInteresseValue >();

    for (PartieInteresseEntite vPartieInteresseeEntite : resultatEntite) {
      PartieInteresseValue vPv = vPersistanceUtilities.toPartieInteresseValue(vPartieInteresseeEntite);
      vListeResultat.add(vPv);
    }

    /** retour de list de recherche et le nombre de resultat **/
    ResultatRechechePartieInteresseeValue vResultatRechechePartieInteresseeValue = new ResultatRechechePartieInteresseeValue();

    vResultatRechechePartieInteresseeValue.setNombreResultaRechercher(Long.valueOf(vListeResultat.size()));

    Collections.sort(vListeResultat);
    vResultatRechechePartieInteresseeValue.setPartieInteresseValues(new TreeSet<>(vListeResultat));

    return vResultatRechechePartieInteresseeValue;
  }

  
  private boolean estNonVide(String val) {
    return val != null && !"".equals(val);

  }

	/******** liste PartieIntersseCache *********/
	@Override
	public List<PartieInteresseCacheValue> listePartieInteresseeCache() {
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<PartieInteresseEntite> query = vBuilder
				.createQuery(PartieInteresseEntite.class);
		Root<PartieInteresseEntite> vRootParitieInteresseCache = query
				.from(PartieInteresseEntite.class);
		
		query.multiselect(vRootParitieInteresseCache.get("id"),
				vRootParitieInteresseCache.get("abreviation"),vRootParitieInteresseCache.get("famillePartieInteressee"));
		
       
		List<PartieInteresseEntite> results = this.entityManager.createQuery(
				query).getResultList();

		/** Conversion de la liste en valeur */
		List<PartieInteresseCacheValue> vListeResultat = new ArrayList<PartieInteresseCacheValue>();
		
		for (PartieInteresseEntite vPartieInteresseeEntite : results) {
			PartieInteresseCacheValue vPiCache = new PartieInteresseCacheValue();
			vPiCache.setId(vPartieInteresseeEntite.getId());
			vPiCache.setAbreviation(vPartieInteresseeEntite.getAbreviation());
			vPiCache.setFamillePartieInteressee(vPartieInteresseeEntite
					.getFamillePartieInteressee());
			
			vListeResultat.add(vPiCache);
		}
		return vListeResultat;
	}
  
	 @Override
	  public PartieInteresseValue getAbreviationClient(Long id) {
		 
		    CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < Object[] > vCriteriaQuery = vBuilder.createQuery(Object[].class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();

		    /** create liste resyltat ***/

		    /************ entity jointure *****************/
		    Root < PartieInteresseEntite > vRootParitieInteresse = vCriteriaQuery.from(PartieInteresseEntite.class);

		    /***************** Predicate *************/
		    if (id != null) {
		      vWhereClause.add(vBuilder.equal(vRootParitieInteresse.get("id"),id));
		    }


		    /** execute query and do something with result **/

		     vCriteriaQuery.select( vBuilder.array(
		    		
		    		 vRootParitieInteresse.get("abreviation").as(String.class),	    		 
		    		
		    		 vRootParitieInteresse.get("reference").as(String.class),
		    		 
		    		 vRootParitieInteresse.get("id").as(Long.class)

		    		 )).where(vWhereClause.toArray(new Predicate[] {}));
		    
		    List < Object[] > element = this.entityManager.createQuery(vCriteriaQuery).getResultList();

		    /** Conversion de la liste en valeur */
		    List < PartieInteresseValue > vListeResultat = new ArrayList < PartieInteresseValue >();

		    if(element != null && element.size()>0){
		    	PartieInteresseEntite vPartieInteresseeEntite = new PartieInteresseEntite();
		    	vPartieInteresseeEntite.setAbreviation((String) element.get(0)[0]);
		    	vPartieInteresseeEntite.setReference((String) element.get(0)[1]);
		    	vPartieInteresseeEntite.setId((Long) element.get(0)[2]);

		    	
			      PartieInteresseValue vPv = vPersistanceUtilities.toPartieInteresseValue(vPartieInteresseeEntite);
			      return vPv;
		    }
		   
		    return null;
			
	  }
  /*
   * get entity manager
   */
  @Override
  public EntityManager getEntityManager() {
    return entityManager;
  }

  /**
   * Accesseur en écriture de l'attribut <code>entityManager</code>.
   *
   * @param entityManager
   *          L'attribut entityManager à modifier.
   */
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /**
   * Accesseur en lecture de l'attribut <code>vPersistanceUtilities</code>.
   * 
   * @return PersistanceUtilities L'attribut vPersistanceUtilities à lire.
   */
  public PersistanceUtilities getvPersistanceUtilities() {
    return vPersistanceUtilities;
  }

  /**
   * Accesseur en écriture de l'attribut <code>vPersistanceUtilities</code>.
   *
   * @param vPersistanceUtilities
   *          L'attribut vPersistanceUtilities à modifier.
   */
  public void setvPersistanceUtilities(PersistanceUtilities vPersistanceUtilities) {
    this.vPersistanceUtilities = vPersistanceUtilities;
  }

  /**
   * Accesseur en lecture de l'attribut <code>actifValue</code>.
   * 
   * @return String L'attribut actifValue à lire.
   */
  public String getActifValue() {
    return actifValue;
  }

  /**
   * Accesseur en écriture de l'attribut <code>actifValue</code>.
   *
   * @param actifValue
   *          L'attribut actifValue à modifier.
   */
  public void setActifValue(String actifValue) {
    this.actifValue = actifValue;
  }

	@Override
	public PartieInteresseValue recherchePartieInteresseeParId(Long id) {
		
		PartieInteresseEntite entity = this.rechercherParId(id, PartieInteresseEntite.class);
		
		PartieInteresseValue value = vPersistanceUtilities.toPartieInteresseValue(entity);
	    return value;
	}

	@Override
	public PartieInteresseValue getById(Long id) {
		
		PartieInteresseEntite entity = this.rechercherParId(id, PartieInteresseEntite.class);

	    return vPersistanceUtilities.toPartieInteresseValue(entity);
	}




}
