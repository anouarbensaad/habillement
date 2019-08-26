package com.gpro.consulting.tiers.commun.persistance.impl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.slf4j.LoggerFactory;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.ArticleCacheValue;
import com.gpro.consulting.tiers.commun.coordination.value.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.RecherecheMulticritereArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheArticleValue;
import com.gpro.consulting.tiers.commun.persistance.IArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.ArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.FamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.GrosseurEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.MatiereArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.MetrageEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.SousFamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.TypeArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

public class ArticlePersistanceImpl extends AbstractPersistance implements IArticlePersistance{

	 /** EntityManager. */
	  @PersistenceContext
	  private EntityManager entityManager;

	  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ArticlePersistanceImpl.class);

      private String pu="pu";
      private String ref="ref";
      private String designation="designation";
      private String sousFamilleArtEntite="sousFamilleArtEntite";
      private String familleArticle="familleArticle";
      private String id="id";
      private String typeArticle="typeArticle";
      
      private String PREDICATE_DESIGNATION_ARTICLE = "designation";
      private String PREDICATE_REFERENCE_ARTICLE = "ref";
      private String PREDICATE_CODE_FOURNISSEUR = "codeFournisseur";
      private String PERCENT = "%";
      
  	  private int MAX_RESULTS = 52;
  	  private String ARTICLE_REF_EXIST_ERROR = "C01";
  	  private String ARTICLE_CODE_FOUR_EXIST_ERROR = "C02";

	@Override
	public String creerArticle(ArticleValue pArticleValue) {
		
		if(this.referenceExistence(pArticleValue.getRef())){
			return ARTICLE_REF_EXIST_ERROR;
		}
		
		if(this.codeFournisseurExistence(pArticleValue.getCodeFournisseur())){
			return ARTICLE_CODE_FOUR_EXIST_ERROR;
		}
		
		// TODO Auto-generated method stub
		 MetrageEntite vMetrage=new MetrageEntite();
		 MatiereArticleEntite vMatiere=new MatiereArticleEntite();
		 GrosseurEntite vGrosseur=new GrosseurEntite();
		 SousFamilleArticleEntity vSFm=new SousFamilleArticleEntity();

		 if(pArticleValue.getMetrageEntite() !=null){
			  vMetrage=this.rechercherParId(pArticleValue.getMetrageEntite(), MetrageEntite.class);

		}
		if(pArticleValue.getMatiereEntite() !=null){
		 vMatiere=this.rechercherParId(pArticleValue.getMatiereEntite(), MatiereArticleEntite.class);

		}
		
		if(pArticleValue.getGrosseurEntite() !=null){
			 vGrosseur=this.rechercherParId(pArticleValue.getGrosseurEntite(), GrosseurEntite.class);

		}
		if(pArticleValue.getSousFamilleArtEntite()!=null){
			vSFm=this.rechercherParId(pArticleValue.getSousFamilleArtEntite(), SousFamilleArticleEntity.class);
		}
			  	
		    ArticleEntite vArticleEntite = (ArticleEntite) this.creer(PersistanceUtilities.toArticleEntity(pArticleValue, vSFm, vMetrage, vGrosseur, vMatiere));
		
		    if (LOGGER.isDebugEnabled()) {
		      LOGGER.debug("Persister l'article d'ID=" + vArticleEntite.getDesignation().toString() + " est en cours.");
		    }	     
		return vArticleEntite.getId().toString();
	}
	

	@Override
	public void supprimerArticle(Long pId) {
		// TODO Auto-generated method stub
		if (LOGGER.isDebugEnabled()) {
		      LOGGER.debug("Suppression de l'article d'ID=" + pId.toString() + " est en cours.");
		    }
		    this.supprimerEntite(ArticleEntite.class, pId.longValue());

		
	}

	@Override
	public String modifierArticle(ArticleValue pArticleValue) {
		
		if(! pArticleValue.getRef().equals(pArticleValue.getRefBeforeUpdate()) && this.referenceExistence(pArticleValue.getRef())){
			return ARTICLE_REF_EXIST_ERROR;
		}
		
		if(! pArticleValue.getCodeFournisseur().equals(pArticleValue.getCodeFournisseurBeforeUpdate()) && this.codeFournisseurExistence(pArticleValue.getCodeFournisseur())){
			return ARTICLE_CODE_FOUR_EXIST_ERROR;
		}
		
		MetrageEntite vMetrage=new MetrageEntite();
		 MatiereArticleEntite vMatiere=new MatiereArticleEntite();
		 GrosseurEntite vGrosseur=new GrosseurEntite();
		 
		if (LOGGER.isDebugEnabled()) {
		      LOGGER.debug("Modification de l'article  d'ID=" + pArticleValue.getId().toString() + " est en cours.");
		    }
		  SousFamilleArticleEntity vSFm=this.rechercherParId(pArticleValue.getSousFamilleArtEntite(), SousFamilleArticleEntity.class);
		  
		  if(pArticleValue.getMetrageEntite() !=null){
			  vMetrage=this.rechercherParId(pArticleValue.getMetrageEntite(), MetrageEntite.class);

		}
		if(pArticleValue.getMatiereEntite() !=null){
		 vMatiere=this.rechercherParId(pArticleValue.getMatiereEntite(), MatiereArticleEntite.class);

		}
		
		if(pArticleValue.getGrosseurEntite() !=null){
			 vGrosseur=this.rechercherParId(pArticleValue.getGrosseurEntite(), GrosseurEntite.class);

		}

		    ArticleEntite vArticleEntite = (ArticleEntite) this.modifier(PersistanceUtilities.toArticleEntity(pArticleValue, vSFm, vMetrage, vGrosseur, vMatiere));

		    return vArticleEntite.getId().toString();

	}

	@Override
	public ArticleValue rechercheArticleParId(ArticleValue pArticleValue) {
		 if (LOGGER.isDebugEnabled()) {
		      LOGGER.debug("Recherche de l'article d'ID=" + pArticleValue.getId().toString() + " est en cours.");
		    }
		    ArticleEntite pArticleEntite = this.rechercherParId(pArticleValue.getId().longValue(),
		      ArticleEntite.class);

		    ArticleValue vArticleValueResult = PersistanceUtilities.toArticleValue(pArticleEntite);

		return vArticleValueResult;
	}

	@Override
	public List<ArticleValue> listeArticle() {
		 List < ArticleEntite > vListeArticleEntite = this.lister(ArticleEntite.class);
		    List < ArticleValue > vListArticleValues = new ArrayList < ArticleValue >();
		    for (ArticleEntite vArticleEntite : vListeArticleEntite) {
		      vListArticleValues.add(PersistanceUtilities.toArticleValue(vArticleEntite));
		    }
		    return vListArticleValues;
	}

	@Override
	public ResultatRechecheArticleValue rechercherArticleMultiCritere(
			RecherecheMulticritereArticleValue pRechercheArticleMulitCritere) {
	    CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
	    /** entity principale **/
	    CriteriaQuery < Object[] > vCriteriaQuery = vBuilder.createQuery(Object[].class);
	    List < Predicate > vWhereClause = new ArrayList < Predicate >();

	    /** create liste resyltat ***/

	    /************ entity jointure *****************/
	    Root < ArticleEntite > vRootArticle = vCriteriaQuery.from(ArticleEntite.class);

	    /***************** Predicate *************/
	    if (estNonVide(pRechercheArticleMulitCritere.getRef())) {
	    	Expression<String> path = vRootArticle.get(PREDICATE_REFERENCE_ARTICLE);
			Expression<String> upper =vBuilder.upper(path);
			Predicate predicate = vBuilder.like(upper, PERCENT +pRechercheArticleMulitCritere.getRef().toUpperCase() + PERCENT);
			vWhereClause.add(vBuilder.and(predicate));
	      
	    }

	    if (estNonVide(pRechercheArticleMulitCritere.getDesignation())) {
	    	Expression<String> path = vRootArticle.get(PREDICATE_DESIGNATION_ARTICLE);
			Expression<String> upper =vBuilder.upper(path);
			Predicate predicate = vBuilder.like(upper, PERCENT + pRechercheArticleMulitCritere.getDesignation().toUpperCase() + PERCENT);
			vWhereClause.add(vBuilder.and(predicate));
	    }

	   if (pRechercheArticleMulitCritere.getFamilleEntite() != null) {
		  	  Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousFm = vRootArticle.join(sousFamilleArtEntite);
		  	  Join<SousFamilleArticleEntity, FamilleArticleEntity> jointureSFmFamille = jointureArtSousFm.join(familleArticle);
	      vWhereClause.add(vBuilder.equal(jointureSFmFamille.get(id),
	    		  pRechercheArticleMulitCritere.getFamilleEntite()));
	    }

	    if (pRechercheArticleMulitCritere.getSousFamilleEntite()!= null) {
		  	  Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousFm = vRootArticle.join(sousFamilleArtEntite);
	      vWhereClause.add(vBuilder.equal(jointureArtSousFm.get(id),
	    		  pRechercheArticleMulitCritere.getSousFamilleEntite()));
	    }
	    if (pRechercheArticleMulitCritere.getTypeEntite()!= null) {
	    	  Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousFm = vRootArticle.join(sousFamilleArtEntite);
		  	  Join<SousFamilleArticleEntity, FamilleArticleEntity> jointureSFmFamille = jointureArtSousFm.join(familleArticle);
		  	  Join<FamilleArticleEntity, TypeArticleEntity> jointureSousFmType= jointureSFmFamille.join(typeArticle);
	      vWhereClause.add(vBuilder.equal(jointureSousFmType.get(id),
	    		  pRechercheArticleMulitCritere.getTypeEntite()));
	    }
	    if (pRechercheArticleMulitCritere.getSite()!= null) {
	      vWhereClause.add(vBuilder.equal(vRootArticle.get("siteEntite"), pRechercheArticleMulitCritere.getSite()));
	    }
	   
	      if( pRechercheArticleMulitCritere.getPrix_inf()!=null ){
	  	    Expression<Double> rootValeur =vRootArticle.get(pu);
	        vWhereClause.add(vBuilder.ge(rootValeur, pRechercheArticleMulitCritere.getPrix_inf()));
	      }
	      if( pRechercheArticleMulitCritere.getPrix_sup()!=null ){
		  	    Expression<Double> rootValeur =vRootArticle.get(pu);
		        vWhereClause.add(vBuilder.le(rootValeur, pRechercheArticleMulitCritere.getPrix_sup()));
		      }
	    
	    /** execute query and do something with result **/

	    vCriteriaQuery.select(vBuilder.array(
	    		vRootArticle.get("id").as(Long.class),
	    		vRootArticle.get("ref").as(String.class),
	    		vRootArticle.get("designation").as(String.class),
	    		vRootArticle.get("sousFamilleArtEntite").as(SousFamilleArticleEntity.class),
	    		vRootArticle.get("pmp").as(Double.class),
	    		vRootArticle.get("pu").as(Double.class),
	    		vRootArticle.get("codeFournisseur").as(String.class),
	    		vRootArticle.get("couleur").as(String.class)
	    		)
	    		).where(vWhereClause.toArray(new Predicate[] {}));
	    
	    
	    vCriteriaQuery.orderBy(vBuilder.desc(vRootArticle.get("id")));  
	    
	    List<Object[]> resultatEntite = null;
	    
	    //If criterias are empty
		if(pRechercheArticleMulitCritere.isOptimized()){
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
	    List < ArticleValue > vListeResultat = new ArrayList < ArticleValue >();

	    for (Object[] element : resultatEntite) {
	    
	    	ArticleEntite vArticleEntite = new ArticleEntite();
	    	
	    	vArticleEntite.setId((Long) element[0]);
	    	vArticleEntite.setRef((String) element[1]);
	    	vArticleEntite.setDesignation((String) element[2]);
	    	vArticleEntite.setSousFamilleArtEntite((SousFamilleArticleEntity) element[3]);
	    	vArticleEntite.setPmp((Double) element[4]);
	    	vArticleEntite.setPu((Double) element[5]);
	    	vArticleEntite.setCodeFournisseur((String) element[6]);
	    	vArticleEntite.setCouleur((String) element[7]);
	    	
	      ArticleValue vPv = PersistanceUtilities.toArticleValue(vArticleEntite);
	      vListeResultat.add(vPv);
	    }

	    /** retour de list de recherche et le nombre de resultat **/
	    ResultatRechecheArticleValue vResultatRechecheArticleValue = new ResultatRechecheArticleValue();
		   
	    vResultatRechecheArticleValue.setNombreResultaRechercher(Long.valueOf(vListeResultat.size()));

	    Collections.sort(vListeResultat);
	    vResultatRechecheArticleValue.setArticleValues(new TreeSet<>(vListeResultat));

	    return vResultatRechecheArticleValue;
	}
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	private boolean estNonVide(String val) {
	    return val != null && !"".equals(val);
	}


	@Override
	public List<ArticleCacheValue> listeArticleCache() {
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<ArticleEntite> query = vBuilder
				.createQuery(ArticleEntite.class);
		Root<ArticleEntite> vRootArticleCache = query
				.from(ArticleEntite.class);
		
		query.multiselect(vRootArticleCache.get("id"),
				vRootArticleCache.get("ref"),vRootArticleCache.get("designation"));
		
		List<ArticleEntite> results = this.entityManager.createQuery(
				query).getResultList();
          
		/** Conversion de la liste en valeur */
		List<ArticleCacheValue> vListeResultat = new ArrayList<ArticleCacheValue>();
		
		for (ArticleEntite vArticleEntite : results) {
			ArticleCacheValue vArticleCache = new ArticleCacheValue();
			vArticleCache.setId(vArticleEntite.getId());
			vArticleCache.setDesignation(vArticleEntite.getDesignation());
			vArticleCache.setRef(vArticleEntite.getRef());
			vListeResultat.add(vArticleCache);
		}
		return vListeResultat;

	}


	@Override
	public ArticleValue getArticleParId(Long id) {
		
		ArticleEntite entity = this.rechercherParId(id, ArticleEntite.class);
		ArticleValue dto = PersistanceUtilities.toArticleValue(entity);

		return dto;
	}
	
	@Override
	  public boolean referenceExistence(String reference){
		  CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < String > vCriteriaQuery = vBuilder.createQuery(String.class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();

		    /** create liste resyltat ***/

		    /************ entity jointure *****************/
		    Root < ArticleEntite > vRootArticle = vCriteriaQuery.from(ArticleEntite.class);

		    /***************** Predicate *************/
		    if (estNonVide(reference)) {
		    	vWhereClause.add(vBuilder.equal(vRootArticle.get(ref), reference));		      
		    }
		
		    vCriteriaQuery.select(
		    		vRootArticle.get("ref").as(String.class)	
					).where(vWhereClause.toArray(new Predicate[] {}));
		    
		    List<String> result = this.entityManager.createQuery(vCriteriaQuery).getResultList();
          
		if(!result.isEmpty()){
			return true;
		}
		
		return false;
	  }
	
	
	@Override
	  public boolean codeFournisseurExistence(String codeFournisseur){
		  CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < String > vCriteriaQuery = vBuilder.createQuery(String.class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();

		    /** create liste resyltat ***/

		    /************ entity jointure *****************/
		    Root < ArticleEntite > vRootArticle = vCriteriaQuery.from(ArticleEntite.class);

		    /***************** Predicate *************/
		    if (estNonVide(codeFournisseur)) {
		    	vWhereClause.add(vBuilder.equal(vRootArticle.get(PREDICATE_CODE_FOURNISSEUR), codeFournisseur));		      
		    }
		
		    vCriteriaQuery.select(
		    		vRootArticle.get("codeFournisseur").as(String.class)	
					).where(vWhereClause.toArray(new Predicate[] {}));
		    
		    List<String> result = this.entityManager.createQuery(vCriteriaQuery).getResultList();
        
		if(!result.isEmpty()){
			return true;
		}
		
		return false;
	  }

}
