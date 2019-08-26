package com.gpro.consulting.tiers.gs.persitance.impl;
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
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.Metamodel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.ArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.FamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.MatiereArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.SiteEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.SousFamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.TypeArticleEntity;
import com.gpro.consulting.tiers.gs.coordination.IConstante;
import com.gpro.consulting.tiers.gs.coordination.value.EntiteStockMouvementValue;
import com.gpro.consulting.tiers.gs.coordination.value.EntiteStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereEntiteStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheEntiteStockStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechercheEntiteStockMouvementValue;
import com.gpro.consulting.tiers.gs.persitance.IEntiteStockPersistance;
import com.gpro.consulting.tiers.gs.persitance.entite.EntiteStockEntite;
import com.gpro.consulting.tiers.gs.persitance.entite.MagasinEntite;
import com.gpro.consulting.tiers.gs.persitance.entite.MouvementEntite;
import com.gpro.consulting.tiers.gs.persitance.utilities.PersistanceUtilities;

/**
 * The Class BonMouvementStockPersistanceImpl.
 */

@Component
public class EntiteStockPersistanceImpl  extends AbstractPersistance  implements IEntiteStockPersistance{

	/** EntityManager. */
	  @PersistenceContext
	  private EntityManager entityManager;
	
	  /** The Constant LOGGER. */
	  	private static final Logger LOGGER = LoggerFactory.getLogger(EntiteStockPersistanceImpl.class);
	/* (non-Javadoc)
	 */
	  	private String PERCENT = "%";
	  	private String typeArticle="referenceArticle";
		private String famille="familleArticle" ;
		private String sousFamille="sousFamilleArtEntite"; 
	
		private String article="article";
		private String grosseur="grosseur" ;
		private String metrage="metrage";
		private String matiere="matiere";
		private String familleProduit="familleProduit";
		private String sousFamilleProduit="sousFamilleProduit";
		private String refProduit="refProduit";
		private String produit="produit";
		private String date="dateEntree";
		private String quantite="qteResrvee";
		private String magasin="magasin";
		private String emplacement="emplacement";
		private String site="site";
		private String zoneDispo="zoneDispo";
		private String idArticle="eb_article_id";
		private String qteActuelle="qteActuelle";
		private String familleproduit="familleproduit";
		private String referenceArticle= "referenceArticle"; 
		private String id="id";
		private String zoneDispoRouge="rouge";
        private String designationArticle="designation";
        
        private String ARTICLE_ENTITY = "article";
        private String ARTICLE_ID = "id";
        private String MAGASIN_ENTITI = "magasin";
        private String MAGASIN_ID = "id";
        private String refLot="referenceLot";
        private String PREDICATE_LOT="referenceLot";
        private String PREDICATE_OA="oa";
        
        
	@Override
	public ResultatRechecheEntiteStockStockValue rechercherEntiteStockMultiCritere(
			RechercheMulticritereEntiteStockValue pRechercheMulticritereEntiteStockValue) {
				
		
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
	    /** entity principale **/
	    CriteriaQuery < EntiteStockEntite > vCriteriaQuery = vBuilder.createQuery(EntiteStockEntite.class);
	    List < Predicate > vWhereClause = new ArrayList < Predicate >();

	    /** create liste resyltat ***/

	    Root < EntiteStockEntite > vRootEntiteStock = vCriteriaQuery.from(EntiteStockEntite.class);

	    /***************** Predicate *************/
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getTypeArticle())) {
	    	   Join<EntiteStockEntite, ArticleEntite> jointureEnStkArt = vRootEntiteStock.join(article);
	    	   Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousF = jointureEnStkArt.join(sousFamille);
	    	   Join<SousFamilleArticleEntity, FamilleArticleEntity> jointureSouFmFamille = jointureArtSousF.join(famille);
	    	   Join<FamilleArticleEntity, TypeArticleEntity> jointureFmTypeArt = jointureSouFmFamille.join("typeArticle");
		      vWhereClause.add(vBuilder.equal(jointureFmTypeArt.get(id),Long.valueOf(pRechercheMulticritereEntiteStockValue.getTypeArticle())));
		    }
		    
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getRefArticle())) {
	    	Join<EntiteStockEntite, ArticleEntite> jointureEnstkArt = vRootEntiteStock.join(article);
		      vWhereClause.add(vBuilder.equal(jointureEnstkArt.get(id),
		    		  pRechercheMulticritereEntiteStockValue.getRefArticle()));
		    }
	 
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getArticle())) {
	    	   Join<EntiteStockEntite, ArticleEntite> jointureEnstkArt = vRootEntiteStock.join(article);
		      vWhereClause.add(vBuilder.equal(jointureEnstkArt.get(id),
		    		  pRechercheMulticritereEntiteStockValue.getArticle()));
		    }
		    
	    
	    if (pRechercheMulticritereEntiteStockValue.getDate()!=null) {
		      vWhereClause.add(vBuilder.equal(vRootEntiteStock.get(date),
		    		  pRechercheMulticritereEntiteStockValue.getDate()));
		    }
		    

	    //System.out.println("-------pRechercheMulticritereEntiteStockValue.getQuantite():----- "+ pRechercheMulticritereEntiteStockValue.getQuantite());
	    
		if (pRechercheMulticritereEntiteStockValue.getQuantite() != null) {
			Expression<Double> qte = vRootEntiteStock.get(qteActuelle);
			switch (pRechercheMulticritereEntiteStockValue
					.getOperateurQuantite()) {
			case IConstante.OPERATEUR_EGALE:
				vWhereClause.add(vBuilder.equal(
						vRootEntiteStock.get(qteActuelle),
						pRechercheMulticritereEntiteStockValue.getQuantite()));
				break;
			case IConstante.OPERATEUR_INFERIEUR:
				vWhereClause.add(vBuilder.lt(qte,
						pRechercheMulticritereEntiteStockValue.getQuantite()));
				break;
			case IConstante.OPERATEUR_SUPERIEUR:
				vWhereClause.add(vBuilder.gt(qte,
						pRechercheMulticritereEntiteStockValue.getQuantite()));
				break;
			case IConstante.OPERATEUR_INF_EGAL:
				vWhereClause.add(vBuilder.le(qte,
						pRechercheMulticritereEntiteStockValue.getQuantite()));

				break;
			case IConstante.OPERATEUR_SUP_EGALE:
				vWhereClause.add(vBuilder.ge(qte,
						pRechercheMulticritereEntiteStockValue.getQuantite()));
				break;
			case IConstante.OPERATEUR_DEFFERENT:
				vWhereClause.add(vBuilder.notEqual(qte,
						pRechercheMulticritereEntiteStockValue.getQuantite()));
				break;
			default:
			}
		}
		// TODO (calculer la quantite) afficher entiteStock suivant zoneDispo
		// choisi (rouge,vert)

		if (estNonVide(pRechercheMulticritereEntiteStockValue.getZoneDispo())) {
			if (pRechercheMulticritereEntiteStockValue.getOperateurZoneDispo() == zoneDispoRouge) {
				vWhereClause.add(vBuilder.equal(
						vRootEntiteStock.get(qteActuelle), // / calcule
						pRechercheMulticritereEntiteStockValue.getQuantite()));
			} else {
				vWhereClause.add(vBuilder.equal(
						vRootEntiteStock.get(qteActuelle),
						pRechercheMulticritereEntiteStockValue.getQuantite()));
			}
		}
			    
		 if (estNonVide(pRechercheMulticritereEntiteStockValue.getMagasin())) {
	    	   Join<EntiteStockEntite, MagasinEntite> jointureEnStkMagasin = vRootEntiteStock.join(magasin);
		      vWhereClause.add(vBuilder.equal(jointureEnStkMagasin.get(id), 
		    		  pRechercheMulticritereEntiteStockValue.getMagasin()));
		    }

		 if (estNonVide(pRechercheMulticritereEntiteStockValue.getEmplacement())) {
			 Expression<String> path = vRootEntiteStock.get(emplacement);
			      vWhereClause.add(vBuilder.like(vBuilder.upper(path),
			    	 PERCENT + pRechercheMulticritereEntiteStockValue.getEmplacement().toUpperCase() + PERCENT));
		  }
		    
		 /***********************/
		 
   /*************************jointure referentiel produit**************************/

		 if (estNonVide(pRechercheMulticritereEntiteStockValue.getRefProduit())) {
		      vWhereClause.add(vBuilder.equal(vRootEntiteStock.get(refProduit),                
		    		  pRechercheMulticritereEntiteStockValue.getRefProduit()));
		    }
		 if (estNonVide(pRechercheMulticritereEntiteStockValue.getSousFamilleProduit())) {
		      vWhereClause.add(vBuilder.equal(vRootEntiteStock.get(sousFamilleProduit),                  
		    		  pRechercheMulticritereEntiteStockValue.getSousFamilleProduit()));
		    }
		      
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getProduit())) {
		      vWhereClause.add(vBuilder.equal(vRootEntiteStock.get(produit),              
		    		  pRechercheMulticritereEntiteStockValue.getProduit()));
		    }
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getFamilleProduit())) {
		      vWhereClause.add(vBuilder.equal(vRootEntiteStock.get(familleproduit),     
		    		  pRechercheMulticritereEntiteStockValue.getFamilleProduit()));
		    }
	    
	        /*************************jointure referentiel article**************************/
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getFamilleArticle())) {
	    	
	    	//System.out.println("#####  ENTER TO SOUS FAMILLE ARTICLE  #########  ");  
	    	   Join<EntiteStockEntite, ArticleEntite> jointureEnStkArt = vRootEntiteStock.join(article);
	    	   Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousF = jointureEnStkArt.join(sousFamille);
	    	   Join<SousFamilleArticleEntity, FamilleArticleEntity> jointureSouFmFamille = jointureArtSousF.join(famille);
		      vWhereClause.add(vBuilder.equal(jointureSouFmFamille.get(id),pRechercheMulticritereEntiteStockValue.getFamilleArticle()));
		    }
	    
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getSousFamilleArticle())) {
	    	   //System.out.println("#####  ENTER TO SOUS FAMILLE ARTICLE  #########  ");
	    	   Join<EntiteStockEntite, ArticleEntite> jointureEnStkArt = vRootEntiteStock.join(article);
	    	   Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousF = jointureEnStkArt.join(sousFamille);
		      vWhereClause.add(vBuilder.equal(jointureArtSousF.get(id),pRechercheMulticritereEntiteStockValue.getSousFamilleArticle()));
		    }
	    
	    
		 if (estNonVide(pRechercheMulticritereEntiteStockValue.getMatiere())) {
	    	   Join<EntiteStockEntite, ArticleEntite> jointureEnStkArt = vRootEntiteStock.join(article);
	    	   Join<ArticleEntite, MatiereArticleEntite> jointureArtMatiere = jointureEnStkArt.join("matiereEntite");
		      vWhereClause.add(vBuilder.equal(jointureArtMatiere.get(id),            
		    		  pRechercheMulticritereEntiteStockValue.getMatiere()));
		    }
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getMetrage())) {
	    	Join<EntiteStockEntite, ArticleEntite> jointureEnStkArt = vRootEntiteStock.join(article);
	    	   Join<ArticleEntite, MatiereArticleEntite> jointureArtMatiere = jointureEnStkArt.join("metrageEntite");
		      vWhereClause.add(vBuilder.equal(jointureArtMatiere.get(id),              
		    		  pRechercheMulticritereEntiteStockValue.getMatiere()));
		    }
	  
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getGrosseur())) {
	    	Join<EntiteStockEntite, ArticleEntite> jointureEnStkArt = vRootEntiteStock.join(article);
	    	   Join<ArticleEntite, MatiereArticleEntite> jointureArtMatiere = jointureEnStkArt.join("grosseurEntite");
		      vWhereClause.add(vBuilder.equal(jointureArtMatiere.get(id),            
		    		  pRechercheMulticritereEntiteStockValue.getGrosseur()));
		    }
		      
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getSite())) {
	    	Join<EntiteStockEntite, MagasinEntite> jointureEnStMag = vRootEntiteStock.join("magasin");
	    	   Join<MagasinEntite, SiteEntite> jointureMagSite = jointureEnStMag.join("siteEntite");
		      vWhereClause.add(vBuilder.equal(jointureMagSite.get(id),               
		    		  pRechercheMulticritereEntiteStockValue.getSite()));
		    }
	    
	      //Hajer Amri
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getCodeFournisseur())) {
	    	Join<EntiteStockEntite, ArticleEntite> jointureEnstkArt = vRootEntiteStock.join(article);
		      vWhereClause.add(vBuilder.equal(jointureEnstkArt.get(id),
		    		  pRechercheMulticritereEntiteStockValue.getCodeFournisseur()));
		  }
	 
	 /**********************************Fin Jointure Referentiel ******************************************/
	   
	    if(estNonVide(pRechercheMulticritereEntiteStockValue.getMouvement())){
	    	
	    	LOGGER.info("---------------: "+pRechercheMulticritereEntiteStockValue.getMouvement());
	    	
	    	Metamodel metamodel = this.entityManager.getMetamodel();
	    	
	    	Subquery<Long> subquery = vCriteriaQuery.subquery(Long.class);
	    	Root<MouvementEntite> subRoot = subquery.from(metamodel.entity(MouvementEntite.class));
	    	subquery.select(subRoot.<Long>get("entiteStock").<Long>get("id"));
	    	
	    	vWhereClause.add(vBuilder.in(vRootEntiteStock.get("id")).value(subquery).not());
	    }
	    
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getLot())) {
		      vWhereClause.add(vBuilder.equal(vRootEntiteStock.get(PREDICATE_LOT),
		    	 pRechercheMulticritereEntiteStockValue.getLot()));
	    }
	    
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getOa())) {
		      vWhereClause.add(vBuilder.equal(vRootEntiteStock.get(PREDICATE_OA),
		    	 pRechercheMulticritereEntiteStockValue.getOa()));
	    }
	    
	    /** execute query and do something with result **/

	    vCriteriaQuery.select(vRootEntiteStock).where(vWhereClause.toArray(new Predicate[] {}));
	    List < EntiteStockEntite > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

	    /** Conversion de la liste en valeur */
	    List < EntiteStockValue > vListeResultat = new ArrayList < EntiteStockValue >();

	    for (EntiteStockEntite vEntiteStockEntite : resultatEntite) {
	    	EntiteStockValue vEs = PersistanceUtilities.toValue(vEntiteStockEntite);
	    	 vListeResultat.add(vEs);
	    }

	    /** retour de list de recherche et le nombre de resultat **/
	    ResultatRechecheEntiteStockStockValue vResultatRechecheEntiteStockStockValue = new ResultatRechecheEntiteStockStockValue();

	    vResultatRechecheEntiteStockStockValue.setNombreResultaRechercher(Long.valueOf(vListeResultat.size()));

	    Collections.sort(vListeResultat);
	    vResultatRechecheEntiteStockStockValue.setEntiteStock(new TreeSet<>(vListeResultat));
	    LOGGER.info("list  "+vResultatRechecheEntiteStockStockValue.getNombreResultaRechercher());
	    
	    return vResultatRechecheEntiteStockStockValue;
	 }

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	  private boolean estNonVide(String val) {
		    return val != null && !"".equals(val) && !"undefined".equals(val);

		  }

	@Override
	public String creerEntiteStock(EntiteStockValue pEntiteStockValue) {
		MagasinEntite vMagasinEntite=new MagasinEntite();
		 ArticleEntite vArticleEntite =new ArticleEntite();
		if(pEntiteStockValue.getMagasin()!=null){
			  vMagasinEntite=this.rechercherParId(pEntiteStockValue.getMagasin(), MagasinEntite.class);
		}
		if(pEntiteStockValue.getArticle()!=null){
			  vArticleEntite =this.rechercherParId(pEntiteStockValue.getArticle(),ArticleEntite.class);
		}
		 EntiteStockEntite vEntiteStockEntite = (EntiteStockEntite) this.creer(PersistanceUtilities.toEntity(pEntiteStockValue,vMagasinEntite,vArticleEntite));
		 LOGGER.info(" designation "+vEntiteStockEntite.getEmplacement());
			    return vEntiteStockEntite.getId().toString();
	}

	@Override
	public String modifierEntiteStock(EntiteStockValue pEntiteStockValue) {
		MagasinEntite vMagasinEntite=new MagasinEntite();
		 ArticleEntite vArticleEntite =new ArticleEntite();
		if(pEntiteStockValue.getMagasin()!=null){
			  vMagasinEntite=this.rechercherParId(pEntiteStockValue.getMagasin(), MagasinEntite.class);
		}
		if(pEntiteStockValue.getArticle()!=null){
			  vArticleEntite =this.rechercherParId(pEntiteStockValue.getArticle(),ArticleEntite.class);
		}
		 EntiteStockEntite vEntiteStockEntite = (EntiteStockEntite) this.modifier(PersistanceUtilities.toEntity(pEntiteStockValue,vMagasinEntite,vArticleEntite));
		 LOGGER.info(" designation "+vEntiteStockEntite.getEmplacement());
			    return vEntiteStockEntite.getId().toString();	}

	@Override
public EntiteStockValue rechercheEntiteStockByArticleMagasin(Long IdArticle, Long IdMagsin) {
		
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
	    /** entity principale **/
	    CriteriaQuery < EntiteStockEntite > vCriteriaQuery = vBuilder.createQuery(EntiteStockEntite.class);
	    List < Predicate > vWhereClause = new ArrayList < Predicate >();
	    /** create liste resyltat ***/
	    /************ entity jointure *****************/
	    Root < EntiteStockEntite > vRootEntiteStock = vCriteriaQuery.from(EntiteStockEntite.class);
	    /***************** Predicate *************/
	    if (IdArticle!=null || IdMagsin!=null) {
	      vWhereClause.add(vBuilder.equal(vRootEntiteStock.get(article),IdArticle));
	      vWhereClause.add(vBuilder.equal(vRootEntiteStock.get(magasin),IdMagsin));
	    }
		
	    /** execute query and do something with result **/
	    vCriteriaQuery.select(vRootEntiteStock).where(vWhereClause.toArray(new Predicate[] {}));
	    List < EntiteStockEntite > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();
	    /** Conversion de la liste en valeur */
	    List < EntiteStockValue > vListeResultat = new ArrayList < EntiteStockValue >();
	      for (EntiteStockEntite vEntiteStockEntite : resultatEntite) {
	    	EntiteStockValue vEs = PersistanceUtilities.toValue(vEntiteStockEntite);
	    	 vListeResultat.add(vEs);
	       }
	    if (vListeResultat.size()==1)
        return (EntiteStockValue) vListeResultat.get(0);
	    else
	    return null;
	
	}
	
	
	@Override
	public List<EntiteStockValue> getByArticleIdMagasinId(Long articleId, Long magsinId) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<EntiteStockEntite> criteriaQuery = criteriaBuilder.createQuery(EntiteStockEntite.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<EntiteStockEntite> root = criteriaQuery.from(EntiteStockEntite.class);

		if (articleId != null && magsinId != null) {
			Expression<Double> qte = root.get(qteActuelle);
			whereClause.add(criteriaBuilder.equal(root.get(ARTICLE_ENTITY).get(ARTICLE_ID), articleId));
			whereClause.add(criteriaBuilder.equal(root.get(MAGASIN_ENTITI).get(MAGASIN_ID), magsinId));
			whereClause.add(criteriaBuilder.gt(qte,0D));
			
			
			
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
			List<EntiteStockEntite> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

			List<EntiteStockValue> listEntiteStock = new ArrayList<EntiteStockValue>();
			
			for (EntiteStockEntite entity : resultatEntite) {
				
				EntiteStockValue dto = PersistanceUtilities.toValue(entity);
				
				listEntiteStock.add(dto);
			}
			
			return listEntiteStock;

		} else
			return null;

	}
	
	

  /**************recherche entite stock for mouvement ****************/
	@Override
	public ResultatRechercheEntiteStockMouvementValue rechercherEntiteStockMouvement(
			RechercheMulticritereEntiteStockValue pRechercheMulticritereEntiteStockValue) {
		    
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
	    /** entity principale **/
	    CriteriaQuery < EntiteStockEntite > vCriteriaQuery = vBuilder.createQuery(EntiteStockEntite.class);
	    List < Predicate > vWhereClause = new ArrayList < Predicate >();
	    /** create liste resyltat ***/
	    /************ entity jointure *****************/
	    Root < EntiteStockEntite > vRootEntiteStock = vCriteriaQuery.from(EntiteStockEntite.class);
	    
	    /***************** Predicate *************/
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getTypeArticle())) {
	    	   LOGGER.info("enter to persist recherche type ");
	    	   Join<EntiteStockEntite, ArticleEntite> jointureEnStkArt = vRootEntiteStock.join(article);
	    	   Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousF = jointureEnStkArt.join(sousFamille);
	    	   Join<SousFamilleArticleEntity, FamilleArticleEntity> jointureSouFmFamille = jointureArtSousF.join(famille);
	    	   Join<FamilleArticleEntity, TypeArticleEntity> jointureFmTypeArt = jointureSouFmFamille.join("typeArticle");
		      vWhereClause.add(vBuilder.equal(jointureFmTypeArt.get("designation"),pRechercheMulticritereEntiteStockValue.getTypeArticle()));
		    }
	    
	    
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getMagasin())) {
	    	   Join<EntiteStockEntite, MagasinEntite> jointureEnStkMagasin = vRootEntiteStock.join(magasin);
		      vWhereClause.add(vBuilder.equal(jointureEnStkMagasin.get(id), 
		    		  pRechercheMulticritereEntiteStockValue.getMagasin()));
		    }
	    
	    
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getFamilleArticle())) {
	    	   Join<EntiteStockEntite, ArticleEntite> jointureEnStkArt = vRootEntiteStock.join(article);
	    	   Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousF = jointureEnStkArt.join(sousFamille);
	    	   Join<SousFamilleArticleEntity, FamilleArticleEntity> jointureSouFmFamille = jointureArtSousF.join(famille);
		      vWhereClause.add(vBuilder.equal(jointureSouFmFamille.get(id),pRechercheMulticritereEntiteStockValue.getFamilleArticle()));
		    }
	    
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getSousFamilleArticle())) {
	    	   Join<EntiteStockEntite, ArticleEntite> jointureEnStkArt = vRootEntiteStock.join(article);
	    	   Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousF = jointureEnStkArt.join(sousFamille);
		      vWhereClause.add(vBuilder.equal(jointureArtSousF.get(id),pRechercheMulticritereEntiteStockValue.getSousFamilleArticle()));
		    }
	    
	   
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getMetrage())) {
	    	Join<EntiteStockEntite, ArticleEntite> jointureEnStkArt = vRootEntiteStock.join(article);
	    	   Join<ArticleEntite, MatiereArticleEntite> jointureArtMatiere = jointureEnStkArt.join("metrageEntite");
		      vWhereClause.add(vBuilder.equal(jointureArtMatiere.get(id),              
		    		  pRechercheMulticritereEntiteStockValue.getMatiere()));
		    }
	  
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getGrosseur())) {
	    	Join<EntiteStockEntite, ArticleEntite> jointureEnStkArt = vRootEntiteStock.join(article);
	    	   Join<ArticleEntite, MatiereArticleEntite> jointureArtMatiere = jointureEnStkArt.join("metrageEntite");
		      vWhereClause.add(vBuilder.equal(jointureArtMatiere.get("grosseurEntite"),            
		    		  pRechercheMulticritereEntiteStockValue.getGrosseur()));
		    }
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getArticle())) {
	    	   Join<EntiteStockEntite, ArticleEntite> jointureEnstkArt = vRootEntiteStock.join(article);
		      vWhereClause.add(vBuilder.equal(jointureEnstkArt.get("designation"),pRechercheMulticritereEntiteStockValue.getArticle()));
		    }
		
	    if (estNonVide(pRechercheMulticritereEntiteStockValue.getRefArticle())) {
	    	  vWhereClause.add(vBuilder.equal(vRootEntiteStock.get("referenceArticle"),pRechercheMulticritereEntiteStockValue.getRefArticle()));
	    }
         // Sortie sauf les quantités >0
	    
	    Expression<Double> qte = vRootEntiteStock.get(qteActuelle);
	    vWhereClause.add(vBuilder.gt(qte,0D));
	    
	    /** execute query and do something with result **/
	    vCriteriaQuery.select(vRootEntiteStock).where(vWhereClause.toArray(new Predicate[] {}));
	    List < EntiteStockEntite > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();
	    
	    
	    /** Conversion de la liste en valeur */
	    List < EntiteStockMouvementValue > vListeResultat = new ArrayList < EntiteStockMouvementValue >();
	    
	      for (EntiteStockEntite vEntiteStockEntite : resultatEntite) {
	    	  EntiteStockMouvementValue vEsMvt = PersistanceUtilities.toEntiteStockMouvementValue(vEntiteStockEntite);
	    	 vListeResultat.add(vEsMvt);
	       }
              
	      /** retour de list de recherche et le nombre de resultat **/
		    ResultatRechercheEntiteStockMouvementValue vResultatRechecheEntiteStockMouvementValue = new ResultatRechercheEntiteStockMouvementValue();
		    vResultatRechecheEntiteStockMouvementValue.setNombreResultaRechercher(Long.valueOf(vListeResultat.size()));
		    
		    Collections.sort(vListeResultat);
		    vResultatRechecheEntiteStockMouvementValue.setEntiteStockMouvement(new TreeSet<>(vListeResultat));
		    return vResultatRechecheEntiteStockMouvementValue;
	   }
	
	
	/*******listeEntiteStock*****/
	@Override
	public List<EntiteStockValue> listeEntiteStock() {
		List<EntiteStockEntite> vListEntiteStockEntite = this.lister(EntiteStockEntite.class);
		List<EntiteStockValue> vlistEntiteStockValue = new ArrayList<EntiteStockValue>();
		for (EntiteStockEntite vEntiteStockEntite : vListEntiteStockEntite) {
			vlistEntiteStockValue.add(PersistanceUtilities
					.toValue(vEntiteStockEntite));
		}
		
		Collections.sort(vlistEntiteStockValue);
		return vlistEntiteStockValue;
	}

	/*****suppriemr entite stock*********/
	@Override
	public void supprimerEntiteStock(Long pId) {
		this.supprimerEntite(EntiteStockEntite.class, pId.longValue());
		
	}
   /*******rechereche entite stock par id********/
	@Override
	public EntiteStockValue rechercheEntiteStockParId(
			Long pEntiteStockId) {
		EntiteStockEntite vEntiteStockEntite = this.rechercherParId(pEntiteStockId,EntiteStockEntite.class);
		EntiteStockValue vEntiteStockValueResult = PersistanceUtilities.toValue(vEntiteStockEntite);
	 return vEntiteStockValueResult;
	}
	
	/// Ajout By Ghazi Atroussi
	
	@Override
public EntiteStockValue rechercheEntiteStockByLotMagasin(Long IdArticle,String referenceLot, Long IdMagsin) {
		
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
	    /** entity principale **/
	    CriteriaQuery < EntiteStockEntite > vCriteriaQuery = vBuilder.createQuery(EntiteStockEntite.class);
	    List < Predicate > vWhereClause = new ArrayList < Predicate >();
	    /** create liste resyltat ***/
	    /************ entity jointure *****************/
	    Root < EntiteStockEntite > vRootEntiteStock = vCriteriaQuery.from(EntiteStockEntite.class);
	    /***************** Predicate *************/
	    if (IdArticle!=null && IdMagsin!=null && (referenceLot!=null&&!referenceLot.equals(""))) {
	      vWhereClause.add(vBuilder.equal(vRootEntiteStock.get(article),IdArticle));
	      vWhereClause.add(vBuilder.equal(vRootEntiteStock.get(magasin),IdMagsin));
	      vWhereClause.add(vBuilder.equal(vRootEntiteStock.get(refLot),referenceLot));
	    }
		
	    /** execute query and do something with result **/
	    vCriteriaQuery.select(vRootEntiteStock).where(vWhereClause.toArray(new Predicate[] {}));
	    List < EntiteStockEntite > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();
	    /** Conversion de la liste en valeur */
	    List < EntiteStockValue > vListeResultat = new ArrayList < EntiteStockValue >();
	      for (EntiteStockEntite vEntiteStockEntite : resultatEntite) {
	    	EntiteStockValue vEs = PersistanceUtilities.toValue(vEntiteStockEntite);
	    	 vListeResultat.add(vEs);
	       }
	    if (vListeResultat.size()==1)
        return (EntiteStockValue) vListeResultat.get(0);
	    else
	    return null;
	
	}
	
	@Override
	public EntiteStockValue rechercheEntiteStockByLotDateMagasin(Long IdArticle,String referenceLot, Long IdMagsin,Calendar dateEntree ) {
			
			CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < EntiteStockEntite > vCriteriaQuery = vBuilder.createQuery(EntiteStockEntite.class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();
		    /** create liste resyltat ***/
		    /************ entity jointure *****************/
		    Root < EntiteStockEntite > vRootEntiteStock = vCriteriaQuery.from(EntiteStockEntite.class);
		    /***************** Predicate *************/
		    if (IdArticle!=null && IdMagsin!=null && (referenceLot!=null&&!referenceLot.equals("")) && date!=null) {
		      vWhereClause.add(vBuilder.equal(vRootEntiteStock.get(article),IdArticle));
		      vWhereClause.add(vBuilder.equal(vRootEntiteStock.get(magasin),IdMagsin));
		      vWhereClause.add(vBuilder.equal(vRootEntiteStock.get(refLot),referenceLot));
		      vWhereClause.add(vBuilder.equal(vRootEntiteStock.get(date),dateEntree));
			      
		    
		    }
			
		    /** execute query and do something with result **/
		    vCriteriaQuery.select(vRootEntiteStock).where(vWhereClause.toArray(new Predicate[] {}));
		    List < EntiteStockEntite > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();
		    /** Conversion de la liste en valeur */
		    List < EntiteStockValue > vListeResultat = new ArrayList < EntiteStockValue >();
		      for (EntiteStockEntite vEntiteStockEntite : resultatEntite) {
		    	EntiteStockValue vEs = PersistanceUtilities.toValue(vEntiteStockEntite);
		    	 vListeResultat.add(vEs);
		       }
		    if (vListeResultat.size()==1)
	        return (EntiteStockValue) vListeResultat.get(0);
		    else
		    return null;
		
		}
	

}
