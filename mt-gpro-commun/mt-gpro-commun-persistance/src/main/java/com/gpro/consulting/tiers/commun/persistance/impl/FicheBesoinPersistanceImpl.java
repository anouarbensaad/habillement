package com.gpro.consulting.tiers.commun.persistance.impl;

/**
 * @auth Amine
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.ElementBesoinValue;
import com.gpro.consulting.tiers.commun.coordination.value.FicheBesoinValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.IFicheBesoinPersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.ArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.FicheBesoinEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.ProduitEntity;
import com.gpro.consulting.tiers.commun.persistance.utilities.PersistanceUtilities;

public class FicheBesoinPersistanceImpl extends AbstractPersistance implements IFicheBesoinPersistance {
	
	private String PREDICATE_PRODUIT_ID = "id";
	private String PREDICATE_PRODUIT = "produitEntity";
	
	/** Logger*/
	private static Logger logger = Logger.getLogger(FicheBesoinPersistanceImpl.class);
	

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}	

	@Override
	public List<FicheBesoinValue> getAll() {
		
		List<FicheBesoinEntite> listEntity = this.lister(FicheBesoinEntite.class);
		return PersistanceUtilities.listFicheBesoinToValue(listEntity);
	}
	
	@Override
	public FicheBesoinValue rechercheFicheBesoinParId(Long produitId) {
		
		//System.out.println("-----------recherche fiche besoin persistance ------------");
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<FicheBesoinEntite> criteriaQuery = criteriaBuilder.createQuery(FicheBesoinEntite.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<FicheBesoinEntite> root = criteriaQuery.from(FicheBesoinEntite.class);
		
		Join<FicheBesoinEntite, ProduitEntity> jointureFicheBesoinProduit = root.join("produitEntity");
		whereClause.add(criteriaBuilder.equal(jointureFicheBesoinProduit.get(PREDICATE_PRODUIT_ID), produitId) );	
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <FicheBesoinEntite> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    //System.out.println("----- resultatEntite recherche fiche par produit id-----" + resultatEntite.size());
	    FicheBesoinValue result = new FicheBesoinValue();
	    
	    if(resultatEntite != null && resultatEntite.size()!=0){
	    	
	    	result = PersistanceUtilities.toFicheBesoinValue(resultatEntite.get(0));	
	    	result = affectValue(result);
	    }

		return result;
	}
	
	/**
	 * Ajouter ou modifier une Fiche besoin
	 * si l'id fiche besoin exist alors modification
	 * si nn c'est un ajout d'une nouvelle fiche besoin
	 */
	
	@Override
	public String creerOmodifierFicheBesoin(FicheBesoinValue pFicheBesoinValue) {
		
		FicheBesoinEntite vFicheBesoinEntite =null;// = new FicheBesoinEntite() ;
		if(log.isDebugEnabled()){
			log.debug("creation d'une fiche de besoin par le responsable =" + pFicheBesoinValue.getResponsable() + " est en cours.");
		}		
		
		ProduitEntity vProduitEntity = this.rechercherParId(pFicheBesoinValue.getProduitValue().getId(), ProduitEntity.class);
		
		vProduitEntity.setFicheBesoin(true);
		
		if(pFicheBesoinValue.getIdFicheBesoin() == null ){
			vFicheBesoinEntite= (FicheBesoinEntite) this.creer(PersistanceUtilities.toEntity(pFicheBesoinValue,vProduitEntity,vFicheBesoinEntite));
		}
		else{
			vFicheBesoinEntite = this.rechercherParId(pFicheBesoinValue.getIdFicheBesoin(), FicheBesoinEntite.class);
			vFicheBesoinEntite= (FicheBesoinEntite) this.modifier(PersistanceUtilities.toEntity(pFicheBesoinValue,vProduitEntity,vFicheBesoinEntite));
		}
			
		FicheBesoinValue vFicheBesoinValue = PersistanceUtilities.toFicheBesoinValue(vFicheBesoinEntite);
		return vFicheBesoinValue.getIdFicheBesoin().toString();
	}
	

	
	/**
	 * this function is used to affect article Value to fiche besoin Value
	 * @param pFicheBesoinValue
	 * @return
	 */
	private FicheBesoinValue affectValue(FicheBesoinValue pFicheBesoinValue){
		
		//CouleurValue
		Set < ElementBesoinValue> vCurrentElementBesoinValue = new HashSet < ElementBesoinValue >();
		Set < ElementBesoinValue> vNewElementBesoinValue = new HashSet < ElementBesoinValue >();
		vCurrentElementBesoinValue = pFicheBesoinValue.getElementBesoinValue();
		 
	    for (ElementBesoinValue vItemElemFicheBesoinValue : vCurrentElementBesoinValue) {
	    	ElementBesoinValue vTmpElementBesoinValue = new ElementBesoinValue();
	    	ArticleEntite vArticleEntite=this.rechercherParId(vItemElemFicheBesoinValue.getArticle().getId(), ArticleEntite.class);
	    	ArticleValue vArticleValue  = PersistanceUtilities.toArticleValue(vArticleEntite);
	    	vTmpElementBesoinValue = vItemElemFicheBesoinValue;
	    	vTmpElementBesoinValue.setArticle(vArticleValue);
	    	vNewElementBesoinValue.add(vTmpElementBesoinValue);
	    }		
	    pFicheBesoinValue.setElementBesoinValue(vNewElementBesoinValue);

	    
		return pFicheBesoinValue;
	}
	
	
	@Override
	public FicheBesoinValue getById(Long id) {
		
		FicheBesoinEntite ficheBesoinEntite = this.rechercherParId(id, FicheBesoinEntite.class);

	    return PersistanceUtilities.toFicheBesoinValue(ficheBesoinEntite);
	}

	@Override
	public boolean checkFBExistence(Long produitId) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<FicheBesoinEntite> root = criteriaQuery.from(FicheBesoinEntite.class);

		if(produitId != null){
			//whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT).get(PREDICATE_PRODUIT_ID), produitId) );
			Join<FicheBesoinEntite, ProduitEntity> jointureFicheBesoinProduit = root.join("produitEntity");
			whereClause.add(criteriaBuilder.equal(jointureFicheBesoinProduit.get(PREDICATE_PRODUIT_ID), produitId) );
		}
		
		criteriaQuery.select(root.get("id").as(Long.class)).where(whereClause.toArray(new Predicate[] {}));
	    List<Long> resultat = this.entityManager.createQuery(criteriaQuery).getResultList();

	    if(resultat.isEmpty()){
	    	return false;
	    }else{
	    	return true;
	    }
	}
	
	
	
}
