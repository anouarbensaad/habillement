package com.gpro.consulting.tiers.commun.persistance.impl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.persistance.IFicheBesoinPersistance;
import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.ArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.FamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.PartieInteresseEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.SousFamilleProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

/**
 * The Class ProduitPersistanceImpl.
 * @author med
 */
public class ProduitPersistanceImpl extends AbstractPersistance implements IProduitPersistance {
	
	private String PREDICATE_ID = "id";
	
	@Autowired
	IFicheBesoinPersistance ficheBesoinPersistance;
	
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	private String familleProduit="familleProduit";
	private String sousFamille="sousFamille";
	private String id="id";
	private String reference="reference";
	private String designation="designation";
	private String partieIntersseId="partieIntersseId";
	private String siteId="siteId";
	private String etat="etat";
	private String prixUnitaire="prixUnitaire";
	private String ficheBesoin="ficheBesoin";
	
	private int MAX_RESULTS = 52;
	
	  private String PRODUIT_REFERENCE_EXIST_ERROR = "C03";

	  
		private String PERCENT = "%";


	// creer  produit
	@Override
	public String creerProduit(ProduitValue pProduitValue) {
		
		if(this.referenceExistence(pProduitValue.getReference())){
			return PRODUIT_REFERENCE_EXIST_ERROR;
		}
		
		SousFamilleProduitEntity vSousFamille=new SousFamilleProduitEntity();
		if(log.isDebugEnabled()){
			log.debug("creation de produit de designation =" + pProduitValue.getDesignation() + " est en cours.");
		}
		if(pProduitValue.getSousFamilleId()!=null){
			vSousFamille=this.rechercherParId(pProduitValue.getSousFamilleId(), SousFamilleProduitEntity.class);
		}		
		ProduitEntity vProduitEntity= (ProduitEntity) this.creer(PersistanceUtilities.toEntity(pProduitValue,vSousFamille));
		ProduitValue vProduitValueResult=PersistanceUtilities.toValue(vProduitEntity);
		return vProduitValueResult.getId().toString();
	}

	// supprimer produit
	@Override
	public void supprimerProduit(Long pProduitId) {
		if (log.isDebugEnabled()) {

			log.debug("Suppression de  la produit  d'ID=" + pProduitId.toString() + " est en cours.");

           }
          this.supprimerEntite(ProduitEntity.class, pProduitId);	
	}

	//modifier produit
	@Override
	public String modifierProduit(ProduitValue pProduitValue) {
		
		
		if(!pProduitValue.getReference().equals(pProduitValue.getRefBeforeUpdate()) && this.referenceExistence(pProduitValue.getReference())){
			return PRODUIT_REFERENCE_EXIST_ERROR;
		}
		
		SousFamilleProduitEntity vSousFamille=new SousFamilleProduitEntity();
		if(log.isDebugEnabled()){
			log.debug("creation de produit   de designation =" + pProduitValue.getDesignation() + " est en cours.");
		}
		if(pProduitValue.getSousFamilleId()!=null){
			vSousFamille=this.rechercherParId(pProduitValue.getSousFamilleId(), SousFamilleProduitEntity.class);
		}
		ProduitEntity vProduitEntity= (ProduitEntity) this.modifier(PersistanceUtilities.toEntity(pProduitValue,vSousFamille));
		ProduitValue vProduitValueResult=PersistanceUtilities.toValue(vProduitEntity);
		return vProduitValueResult.getId().toString();
	}

	//recherche par id
	@Override
	public ProduitValue rechercheProduitById(Long pProduitId) {
		ProduitEntity vProduitEntity=  this.rechercherParId(pProduitId,ProduitEntity.class);
		ProduitValue vProduitValueResult=PersistanceUtilities.toValue(vProduitEntity);
		return vProduitValueResult;
	}
	
	@Override
	public ProduitValue rechercheProduitById(Long pProduitId, boolean allegee) {

		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
	    /** entity principale **/
	    CriteriaQuery < Object[] > vCriteriaQuery = vBuilder.createQuery(Object[].class);
	    List < Predicate > vWhereClause = new ArrayList < Predicate >();

	    /** create liste resultat ***/

	    /************ entity jointure *****************/
	    Root < ProduitEntity > vRootProduit = vCriteriaQuery.from(ProduitEntity.class);

	    /***************** Predicate *************/
	    if (pProduitId != null) {
	      vWhereClause.add(vBuilder.equal(vRootProduit.get(id),
	    		  pProduitId));
	    }
	  
	    /** execute query and do something with result **/

	    vCriteriaQuery.select(vBuilder.array(
	    		
	    		vRootProduit.get("id").as(Long.class),
	    		vRootProduit.get("reference").as(String.class),
	    		vRootProduit.get("designation").as(String.class),
	    		vRootProduit.get("partieIntersseId").as(Long.class),
	    		vRootProduit.get("sousFamille").get("id").as(Long.class)
	    		
	    		)).where(vWhereClause.toArray(new Predicate[] {}));
	    List < Object[] > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

	    for (Object[] element : resultatEntite) {
	    	
	    	ProduitEntity vProduitEntite = new ProduitEntity();
	    	
	    	vProduitEntite.setId((Long) element[0]);
	    	vProduitEntite.setReference((String) element[1]);
	    	vProduitEntite.setDesignation((String) element[2]);
	    	vProduitEntite.setPartieIntersseId((Long) element[3]);
	    	
	    	SousFamilleProduitEntity sousFamille = new SousFamilleProduitEntity();
	    	sousFamille.setId((Long) element[4]);
	    	vProduitEntite.setSousFamille(sousFamille);
	    	
	    	ProduitValue produit = PersistanceUtilities.toValue(vProduitEntite);
	    	
	    	if(produit != null){
	    		return produit;
	    	}
	    }

		
		return null;
	}
	
	//liste produit
	//Modifier par Ghazi on 27/06/2019
	
	@Override
	public List<ProduitValue> listeProduit() {
//		 List<ProduitEntity> vListeProduitsEntity = this.lister(ProduitEntity.class);
		 List < ProduitValue > vListeProduitsValues = new ArrayList < ProduitValue >();
//		 for (ProduitEntity vProduitEntite : vListeProduitsEntity) {
//			 vListeProduitsValues.add(PersistanceUtilities.toValue(vProduitEntite));
//	 	    }
		
		 CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < Object[] > vCriteriaQuery = vBuilder.createQuery(Object[].class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();

		    /** create liste resyltat ***/

		    /************ entity jointure *****************/
		    Root < ProduitEntity > vRootProduit = vCriteriaQuery.from(ProduitEntity.class);

		    /** execute query and do something with result **/

		    vCriteriaQuery.select(vBuilder.array(
		    		vRootProduit.get("id").as(Long.class),
		    		vRootProduit.get("reference").as(String.class),
		    		vRootProduit.get("designation").as(String.class),
		    		vRootProduit.get("partieIntersseId").as(Long.class)
		    		   		
		    		)).where(vWhereClause.toArray(new Predicate[] {}));
		    
		    vCriteriaQuery.orderBy(vBuilder.desc(vRootProduit.get("id")));
		 
		 
		    List<Object[]> resultatEntite = null;
		    resultatEntite = this.entityManager
					.createQuery(vCriteriaQuery)
					.getResultList();
		    
		    
		    for (Object[] element : resultatEntite) {
		    	
		    	ProduitEntity vProduitEntite = new ProduitEntity();
		    	
		    	vProduitEntite.setId((Long) element[0]);
		    	vProduitEntite.setReference((String) element[1]);
		    	vProduitEntite.setDesignation((String) element[2]);
		    
		    	vProduitEntite.setPartieIntersseId((Long) element[3]);
		    	
		    	ProduitValue vBm = PersistanceUtilities.toValue(vProduitEntite);
		    	vListeProduitsValues.add(vBm);
		    
		    }
		    
		    
		    
		    
		    
		    
		    
		 
		 
		 return vListeProduitsValues;
	}
	//entity manager getter
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	/**
	 * Sets the entity manager.
	 *
	 * @param entityManager the new entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**rechereche multi critere */
	@Override
	public ResultatRechecheProduitValue rechercherProduitMultiCritere(RechercheMulticritereProduitValue pRechercheProduitMulitCritere) {
		  CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < ProduitEntity > vCriteriaQuery = vBuilder.createQuery(ProduitEntity.class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();

		    /** create liste resultat ***/

		    /************ entity jointure *****************/
		    Root < ProduitEntity > vRootProduit = vCriteriaQuery.from(ProduitEntity.class);

		    /***************** Predicate *************/
		    if (estNonVide(pRechercheProduitMulitCritere.getReference())) {
				//vWhereClause.add(vBuilder.equal(vRootProduit.get(reference), pRechercheProduitMulitCritere.getReference()));
			
				Expression<String> path=vRootProduit.get(reference);
				
				
				vWhereClause.add(vBuilder.like(vBuilder.upper(path),
				    	 PERCENT + pRechercheProduitMulitCritere.getReference().toUpperCase() + PERCENT));
			
		    
		    
		    
		    }
			if (estNonVide(pRechercheProduitMulitCritere.getDesignation())) {
				Expression<String> path=vRootProduit.get(designation);
				
			
				vWhereClause.add(vBuilder.like(vBuilder.upper(path),
				    	 PERCENT + pRechercheProduitMulitCritere.getDesignation().toUpperCase() + PERCENT));
			
			}
              
		    if (estNonVide(pRechercheProduitMulitCritere.getFamille())) {
		    	  Join<ProduitEntity, SousFamilleProduitEntity> jointurePrdSousFm = vRootProduit.join(sousFamille);
			  	  Join<SousFamilleProduitEntity, FamilleProduitEntity> jointureSFmFamille = jointurePrdSousFm.join(familleProduit);
			      vWhereClause.add(vBuilder.equal(jointureSFmFamille.get(id),
			    		  pRechercheProduitMulitCritere.getFamille()));
			    }
		    
		    if (estNonVide(pRechercheProduitMulitCritere.getPartieInteressee())) {
			      vWhereClause.add(vBuilder.equal(vRootProduit.get(partieIntersseId),
			    		  pRechercheProduitMulitCritere.getPartieInteressee()));
			    }
		    
		    if (estNonVide(pRechercheProduitMulitCritere.getSite())) {
			      vWhereClause.add(vBuilder.equal(vRootProduit.get(siteId),
			    		  pRechercheProduitMulitCritere.getSite()));
			    }
		    
		    if (estNonVide(pRechercheProduitMulitCritere.getSousfamille())) {
		    	  Join<ProduitEntity, SousFamilleProduitEntity> jointurePrdSousFm = vRootProduit.join(sousFamille);
			      vWhereClause.add(vBuilder.equal(jointurePrdSousFm.get(id),
			    		  pRechercheProduitMulitCritere.getSousfamille()));
			    }

		    if (estNonVide(pRechercheProduitMulitCritere.getEtat())) {
			      vWhereClause.add(vBuilder.equal(vRootProduit.get(etat),
			    		  pRechercheProduitMulitCritere.getEtat()));
			    }
		    
		    if( pRechercheProduitMulitCritere.getPrix_inf()!=null ){
			    Expression<Double> rootValeur =vRootProduit.get(prixUnitaire);
		        vWhereClause.add(vBuilder.ge(rootValeur, pRechercheProduitMulitCritere.getPrix_inf()));
		      }
		      if( pRechercheProduitMulitCritere.getPrix_sup()!=null ){
				    Expression<Double> rootValeur =vRootProduit.get(prixUnitaire);
			        vWhereClause.add(vBuilder.le(rootValeur, pRechercheProduitMulitCritere.getPrix_sup()));
			  }
		      
		      if (pRechercheProduitMulitCritere.getFicheB()!=null) {
			      vWhereClause.add(vBuilder.equal(vRootProduit.get(ficheBesoin),
			    		  pRechercheProduitMulitCritere.getFicheB()));
			    }
		    
		  
		    /** execute query and do something with result **/

		    vCriteriaQuery.select(vRootProduit).where(vWhereClause.toArray(new Predicate[] {}));
		    
		    vCriteriaQuery.orderBy(vBuilder.desc(vRootProduit.get("id")));
		    
		    List<ProduitEntity > resultatEntite = null;
		    //If criterias are empty
			if(pRechercheProduitMulitCritere.isOptimized()){
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
		    List < ProduitValue > vListeResultat = new ArrayList < ProduitValue >();

		    for (ProduitEntity vProduitEntite : resultatEntite) {
		    	
		    	ProduitValue produit = PersistanceUtilities.toValue(vProduitEntite);
		    	vListeResultat.add(produit);
		    }

		    /** retour de list de recherche et le nombre de resultat **/
		    ResultatRechecheProduitValue vResultatRechecheProduitValue = new ResultatRechecheProduitValue();
			  
		    vResultatRechecheProduitValue.setNombreResultaRechercher(Long.valueOf(vListeResultat.size()));

		    Collections.sort(vListeResultat);
		    vResultatRechecheProduitValue.setProduitValues(new TreeSet<>(vListeResultat));

		    return vResultatRechecheProduitValue;

	}
	
	@Override
	public List<ProduitValue> getAllInList(List<Long> ids) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<ProduitEntity> criteriaQuery = criteriaBuilder.createQuery(ProduitEntity.class);
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    Root<ProduitEntity> root = criteriaQuery.from(ProduitEntity.class);
		
	    if(ids.size() > 0){
	    	
	    	whereClause.add(root.get(PREDICATE_ID).in(ids));
	    }
		
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    
	    TypedQuery<ProduitEntity> typedQueryResult = this.entityManager.createQuery(criteriaQuery);
	    
	    List<ProduitEntity> entityListResult = new ArrayList<ProduitEntity>();
	    
	    if(typedQueryResult != null){
	    	
	    	entityListResult = typedQueryResult.getResultList();
	    }

	    List<ProduitValue> result = new ArrayList<ProduitValue>();
	    
	    for (ProduitEntity entity : entityListResult) {
	    	ProduitValue value = PersistanceUtilities.toValue(entity);
	    	result.add(value);
	    }
	    
	    return result;
	    
	}
	
	@Override
	public List<ProduitValue> getAllNotInList(List<Long> ids) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<ProduitEntity> criteriaQuery = criteriaBuilder.createQuery(ProduitEntity.class);
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    Root<ProduitEntity> root = criteriaQuery.from(ProduitEntity.class);
	    
	    if(ids.size() > 0){
	    	
	    	whereClause.add(root.get(PREDICATE_ID).in(ids).not());
	    }
	    
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    
	    TypedQuery<ProduitEntity> typedQueryResult = this.entityManager.createQuery(criteriaQuery);
	    
	    List<ProduitEntity> entityListResult = new ArrayList<ProduitEntity>();
	    
	    if(typedQueryResult != null){
	    	
	    	entityListResult = typedQueryResult.getResultList();
	    }

	    List<ProduitValue> result = new ArrayList<ProduitValue>();
	    
	    for (ProduitEntity entity : entityListResult) {
	    	ProduitValue value = PersistanceUtilities.toValue(entity);
	    	result.add(value);
	    }
	    
	    return result;
	    
	}
	
	@Override
	  public boolean referenceExistence(String reference){
		  CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < String > vCriteriaQuery = vBuilder.createQuery(String.class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();

		    /** create liste resyltat ***/

		    /************ entity jointure *****************/
		    Root < ProduitEntity > vRootArticle = vCriteriaQuery.from(ProduitEntity.class);

		    /***************** Predicate *************/
		    if (estNonVide(reference)) {
		    	vWhereClause.add(vBuilder.equal(vRootArticle.get("reference"), reference));		      
		    }
		
		    vCriteriaQuery.select(
		    		vRootArticle.get("reference").as(String.class)	
					).where(vWhereClause.toArray(new Predicate[] {}));
		    
		    List<String> result = this.entityManager.createQuery(vCriteriaQuery).getResultList();
        
		if(!result.isEmpty()){
			return true;
		}
		
		return false;
	  }
	
	
	private boolean estNonVide(String val) {
		
		return val != null && !"".equals(val);
	}

	public IFicheBesoinPersistance getFicheBesoinPersistance() {
		return ficheBesoinPersistance;
	}

	public void setFicheBesoinPersistance(IFicheBesoinPersistance ficheBesoinPersistance) {
		this.ficheBesoinPersistance = ficheBesoinPersistance;
	}
	
	
}
