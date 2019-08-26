package com.gpro.consulting.tiers.gpao.persitance.bonlivraison.impl;

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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.persistance.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.ResultatRechecheBonLivraisonValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.gpao.persitance.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.gpao.persitance.bonlivraison.entitie.LivraisonVenteEntity;
import com.gpro.consulting.tiers.gpao.persitance.bonlivraison.utilities.BonLivraisonPersistanceUtilities;

/**
 * Implementation of {@link IBonLivraisonPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class BonLivraisonPersistanceImpl extends AbstractPersistance implements IBonLivraisonPersistance{
	
	private static final Logger logger = LoggerFactory.getLogger(BonLivraisonPersistanceImpl.class);
	
	private String PREDICATE_REFERENCE = "reference";
	private String PREDICATE_CLIENT = "partieIntId";
	private String PREDICATE_DATE= "date";
//	private String PREDICATE_AVEC_FACTURE= "date";
	private String PREDICATE_METRAGE = "metrageTotal";
	private String PREDICATE_ETAT = "etat";
	private String PREDICATE_PRIX = "montantTTC";
	private String PREDICATE_INFO_SORTIE = "infoSortie";
	private String PREDICATE_MARCHE = "marcheId";
	private String PREDICATE_REGION = "regionId";
	private String PREDICATE_NATURELIVRAISON = "natureLivraison";
	private String PREDICATE_INFO_LIVRAISON = "infoLivraison";
	private String PERCENT = "%";
	
	private int FIRST_INDEX = 0;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private BonLivraisonPersistanceUtilities bonLivraisonPersistanceUtilities;

	@Autowired
	private IPartieInteresseePersistance partieInteresseePersistence;
	
	
	
	@Override
	public ResultatRechecheBonLivraisonValue rechercherMultiCritere(
			RechercheMulticritereBonLivraisonValue request) {
		
		logger.info("rechercherMultiCritere");
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
		
		// Set request.referenceBl on whereClause if not empty or null
		if (estNonVide(request.getReferenceBl())) {
			Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReferenceBl().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		// Set request.partieIntId on whereClause if not null
		if (request.getPartieIntId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieIntId()));
		}
		
		// Set request.dateLivraisonMin on whereClause if not null
	    if (request.getDateLivraisonMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMin()));
	    }
	    
		// Set request.dateLivraisonMax on whereClause if not null
	    if (request.getDateLivraisonMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMax()));
	    }
	    
		// Set request.metrageMin on whereClause if not null
	    if (request.getMetrageMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_METRAGE), request.getMetrageMin()));
	    }
	    
		// Set request.metrageMax on whereClause if not null
	    if (request.getMetrageMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_METRAGE), request.getMetrageMax()));
	    }
	    
		// Set request.etat on whereClause if not empty or null
		if (estNonVide(request.getEtat())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_ETAT), request.getEtat()));
		}
		
		// Set request.prixMin on whereClause if not null
	    if (request.getPrixMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_PRIX), request.getPrixMin()));
	    }
	    
		// Set request.prixMax on whereClause if not null
	    if (request.getPrixMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_PRIX), request.getPrixMax()));
	    }
	    
		// Set request.referenceBs on whereClause if not empty or null
		if (estNonVide(request.getReferenceBs())) {
			Expression<String> path = root.get(PREDICATE_INFO_SORTIE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReferenceBs().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		// Set request.marcheId on whereClause if not null
		if (request.getMarcheId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_MARCHE), request.getMarcheId()));
		}
		
		// Set request.getNatureLivraison on whereClause if not null
		if (estNonVide(request.getNatureLivraison())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_NATURELIVRAISON), request.getNatureLivraison()));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<LivraisonVenteValue> list = new ArrayList<LivraisonVenteValue>();
	    
	    for (LivraisonVenteEntity entity : resultatEntite) {
	    	LivraisonVenteValue dto = bonLivraisonPersistanceUtilities.toValue(entity);
	    
	    	
	    	if (dto.getPartieIntId()!=null)
	    	{
	    		PartieInteresseValue pi =partieInteresseePersistence.recherchePartieInteresseeParId(dto.getPartieIntId());
	    		
	    		if(pi!=null){
	    			dto.setPartieIntAbreviation(pi.getAbreviation());
	    		}
	    		
	    		
	    	}
	    	
	    	
	    	
	    	list.add(dto);
	    }
	    
	//TODO 
	    // Recherche Facture 
//	 	if(estNonVide(request.getAvecFacture())){
//	 			
//	 		List<LivraisonVenteValue> tempList = new ArrayList<LivraisonVenteValue>();
//	 		
//	 	// Livraison facturée
//	 		
//			if(request.getAvecFacture().equals("ouiFacture")){
//	 				
//				for (LivraisonVenteValue livraisonVenteValue : list) {
//					if(facturePersistance.existeBL(livraisonVenteValue.getReference())){
//						tempList.add(livraisonVenteValue);
//					}
//				}
//				
// 			}
//			// Livraison non facturée
//			else if(request.getAvecFacture().equals("nonFacture")){
//				
//				for (LivraisonVenteValue livraisonVenteValue : list) {
//					if(!facturePersistance.existeBL(livraisonVenteValue.getReference())){
//						tempList.add(livraisonVenteValue);
//					}
//				}
//			}
//			
//			list.clear();
//			list=tempList;
//	 			
//	 	}

	    ResultatRechecheBonLivraisonValue resultat = new ResultatRechecheBonLivraisonValue();
	    Collections.sort(list);
	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
	    resultat.setList(new TreeSet<>(list));

	    return resultat;
	}

	@Override
	public String createBonLivraison(LivraisonVenteValue bonLivraisonValue) {
		
		LivraisonVenteEntity entity = (LivraisonVenteEntity) this.creer(bonLivraisonPersistanceUtilities.toEntity(bonLivraisonValue));
        // TEST
		////System.out.println("########  Fin de creation de Bon de livraison   :   "+new Date());
	    return entity.getId().toString();
	}


	@Override
	public LivraisonVenteValue getBonLivraisonById(Long id) {
		
		LivraisonVenteEntity bonLivraisonEntity = this.rechercherParId(id, LivraisonVenteEntity.class);
		// TEST
		////System.out.println("########  Recuperation  de Bon de livraison  Persist :   "+new Date());
	    return bonLivraisonPersistanceUtilities.toValue(bonLivraisonEntity);
	}


	@Override
	public String updateBonLivraison(LivraisonVenteValue bonLivraisonValue) {
		
		LivraisonVenteEntity entity = (LivraisonVenteEntity) this.modifier(bonLivraisonPersistanceUtilities.toEntity(bonLivraisonValue));

	    return entity.getId().toString();
	}


	@Override
	public void deleteBonLivraison(Long id) {
		
		this.supprimerEntite(LivraisonVenteEntity.class, id);
	}

	@Override
	public List<LivraisonVenteValue> getAll() {

		List<LivraisonVenteEntity> listEntity = this.lister(LivraisonVenteEntity.class);
		
		return bonLivraisonPersistanceUtilities.listLivraisonVenteEntitytoValue(listEntity);
	}
	
	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<LivraisonVenteValue> getProduitElementList(List<String> refBonLivraisonList) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
	    
	    whereClause.add(root.get(PREDICATE_REFERENCE).in(refBonLivraisonList));
		
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    
	    List<LivraisonVenteEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<LivraisonVenteValue> listDetLivraison = new ArrayList<LivraisonVenteValue>();
	    
	    for (LivraisonVenteEntity entity : entityListResult) {
	    	LivraisonVenteValue value = bonLivraisonPersistanceUtilities.toValue(entity);
	    	listDetLivraison.add(value);
	    	
	    }
	    
	    return listDetLivraison;
	    
	}

	@Override
	public LivraisonVenteValue getByReference(String reference) {

		LivraisonVenteValue resultat = null;

		// Set reference on whereClause if not empty or null
		if (estNonVide(reference)) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
			
			Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + reference.toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null)
		    	if(resultatEntite.size() > 0)
		    		resultat = bonLivraisonPersistanceUtilities.toValue(resultatEntite.get(FIRST_INDEX));
		    	
		}

	    return resultat;
	}

	@Override
	public List<LivraisonVenteValue> getByClientId(Long clientId) {
		
		List<LivraisonVenteValue> resultat = new ArrayList<LivraisonVenteValue>();

		// Set clientId on whereClause if not null
		if (clientId != null) {
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			
			Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
		    
		    if(resultatEntite != null){
		    	
		    	for(LivraisonVenteEntity entity : resultatEntite){
		    		
		    		resultat.add(bonLivraisonPersistanceUtilities.toValue(entity));
		    	}
		    }
		}

	    return resultat;
	}
	
	@Override
	public List<LivraisonVenteVue> getReferenceBLByClientId(Long clientId) {
		
	List<LivraisonVenteVue> resultat = new ArrayList<LivraisonVenteVue>();

	// Set clientId on whereClause if not null
	if (clientId != null) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
		
		whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), clientId));

		criteriaQuery.multiselect(root.get("reference")).where(whereClause.toArray(new Predicate[] {}));
		
	    List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
	    if(resultatEntite != null){
	    	
	    	for(LivraisonVenteEntity entity : resultatEntite){
	    		
	    		resultat.add(bonLivraisonPersistanceUtilities.toVue(entity));
	    	}
	    }
	}

    return resultat;
	}

//	@Override
//	public ResultatRechecheBonLivraisonValue getLivraisonByFnReportingRegionDate(
//			RechercheMulticritereFnReportingtValue request) {
//		
//		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
//		
//		CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
//		List<Predicate> whereClause = new ArrayList<Predicate>();
//		
//		Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
//
//		// Set regionId on whereClause if not empty or null
//		if (request.getRegionId() != null) {
//			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
//			Root<PartieInteresseEntite> subRoot = subQuery.from(PartieInteresseEntite.class);
//			
//			subQuery.select(subRoot.<Long>get("id"));
//			subQuery.where(criteriaBuilder.equal(subRoot.get(PREDICATE_REGION), request.getRegionId()));
//			whereClause.add(criteriaBuilder.in(root.get(PREDICATE_CLIENT)).value(subQuery));
//		}
//		
//		// Set request.dateLivraisonMin on whereClause if not null
//	    if (request.getDateMin() != null) {
//		  	  
//	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateMin()));
//	    }
//	    
//		// Set request.dateLivraisonMax on whereClause if not null
//	    if (request.getDateMax() != null) {
//		  	  
//	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateMax()));
//	    }
//
//		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
//	    List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
//
//	    List<LivraisonVenteValue> list = new ArrayList<LivraisonVenteValue>();
//	    
//	    for (LivraisonVenteEntity entity : resultatEntite) {
//	    	LivraisonVenteValue dto = bonLivraisonPersistanceUtilities.toValue(entity);
//	    	list.add(dto);
//	    }
//
//	    ResultatRechecheBonLivraisonValue resultat = new ResultatRechecheBonLivraisonValue();
//	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
//	    resultat.setList(new TreeSet<>(list));
//
//	    return resultat;
//	}
	
	@Override
	public List<LivraisonVenteValue> getTraitementFaconElementList(List<String> refBonLivraisonList) {
		
		// Même traitement que la méthode getProduitElementList 
	    return this.getProduitElementList(refBonLivraisonList);
	    
	}

//	@Override
//	public Double getSommeMontHT(RechercheMulticritereBonLivraisonValue request) {
//		
//		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
//		
//		CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
//		List<Predicate> whereClause = new ArrayList<Predicate>();
//		
//		Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
//		
//		// Set request.partieIntId on whereClause if not null
//		if (request.getPartieIntId() != null) {
//			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieIntId()));
//		}
//		
//		// Set request.dateLivraisonMin on whereClause if not null
//	    if (request.getDateLivraisonMin() != null) {
//	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMin()));
//	    }
//	    
//		// Set request.dateLivraisonMax on whereClause if not null
//	    if (request.getDateLivraisonMax() != null) {
//	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMax()));
//	    }
//	    
//		criteriaQuery.select(criteriaBuilder.sum(root.get("montantHTaxe").as(Double.class))).where(whereClause.toArray(new Predicate[] {}));
//	    Double sommeMontHT = this.entityManager.createQuery(criteriaQuery).getSingleResult();
//	    
//	    if(sommeMontHT!=null){
//	    	return sommeMontHT;
//	    }
//	    
//	    return 0D;
//		
//	}
	
	//Hajer Amri 09/02/2017
	
//	@Override
//	public Double getSommeMontHT(RechercheMulticritereBonLivraisonValue request) {
//		
//		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
//		
//		CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
//		List<Predicate> whereClause = new ArrayList<Predicate>();
//		
//		Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
//		
//		// Set request.partieIntId on whereClause if not null
//		if (request.getPartieIntId() != null) {
//			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieIntId()));
//		}
//		
//		// Set request.dateLivraisonMin on whereClause if not null
//	    if (request.getDateLivraisonMin() != null) {
//	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMin()));
//	    }
//	    
//		// Set request.dateLivraisonMax on whereClause if not null
//	    if (request.getDateLivraisonMax() != null) {
//	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMax()));
//	    }
//	    
//	    //Hajer Amri 09/02/2017
//		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
//	    List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
//
//	    List<LivraisonVenteValue> list = new ArrayList<LivraisonVenteValue>();
//	    
//	    for (LivraisonVenteEntity entity : resultatEntite) {
//	    	LivraisonVenteValue dto = bonLivraisonPersistanceUtilities.toValue(entity);
//	    	list.add(dto);
//	    }
//	    
//	    Double sommeMontHT=0D;
//	    
//	 	if(estNonVide(request.getAvecFacture())){
//	 			
//	 	
//			// Livraison non facturée
//			 if(request.getAvecFacture().equals("nonFacture")){
//				
//				for (LivraisonVenteValue livraisonVenteValue : list) {
//					if(!facturePersistance.existeBL(livraisonVenteValue.getReference())){
//						
//						sommeMontHT += livraisonVenteValue.getMontantTTC();
//					}
//				}
//			}
//			
//	 			
//	 	}
//	    
//	    /*****/
////		criteriaQuery.select(criteriaBuilder.sum(root.get("montantHTaxe").as(Double.class))).where(whereClause.toArray(new Predicate[] {}));
////	    Double sommeMontHT = this.entityManager.createQuery(criteriaQuery).getSingleResult();
//	    
//	    if(sommeMontHT!=null){
//	    	return sommeMontHT;
//	    }
//	    
//	    return 0D;
//		
//	}
//
//
//	@Override
//	public Double getSommeMontHTFactures(RechercheMulticritereBonLivraisonValue request) {
//
//	CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
//		
//		CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
//		List<Predicate> whereClause = new ArrayList<Predicate>();
//		
//		Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
//
//		// Set request.partieIntId on whereClause if not null
//		if (request.getPartieIntId() != null) {
//			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getPartieIntId()));
//		}
//		
//		// Set request.dateLivraisonMin on whereClause if not null
//	    if (request.getDateLivraisonMin() != null) {
//	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMin()));
//	    }
//	    
//		// Set request.dateLivraisonMax on whereClause if not null
//	    if (request.getDateLivraisonMax() != null) {
//	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE), request.getDateLivraisonMax()));
//	    }
//	    
//		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
//	    
//		List <LivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
//	    
//		Double montantBLFactures = 0D;
//		
//		for (LivraisonVenteEntity livraisonVenteEntity : resultatEntite) {
//			
//			if(facturePersistance.existeBL(livraisonVenteEntity.getReference())){
//				montantBLFactures+=livraisonVenteEntity.getMontantHTaxe();
//			}
//		}
//		
//	    return montantBLFactures;
//	
//	}
	
	@Override
	public boolean existeBS(String referenceBL) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<LivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntity.class);
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    Root<LivraisonVenteEntity> root = criteriaQuery.from(LivraisonVenteEntity.class);
	    
	    Expression<String> path = root.get(PREDICATE_INFO_SORTIE);
		Expression<String> upper =criteriaBuilder.upper(path);
		Predicate predicate = criteriaBuilder.like(upper, PERCENT + referenceBL.toUpperCase() + PERCENT);
		
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    criteriaQuery.where(criteriaBuilder.and(predicate));
	    
	    List<LivraisonVenteEntity> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
	    if(entityListResult.size()!=0){
	    	return true;
	    }
	    
		return false;
	}
	

}
