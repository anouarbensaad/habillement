package com.gpro.consulting.tiers.gpao.persitance.bonlivraison.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.ResultatRechecheDetailBonLivraisonValue;
import com.gpro.consulting.tiers.gpao.persitance.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.gpao.persitance.bonlivraison.IDetLivraisonVentePersistance;
import com.gpro.consulting.tiers.gpao.persitance.bonlivraison.entitie.DetLivraisonVenteEntity;
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
public class DetLivraisonVentePersistanceImpl extends AbstractPersistance implements IDetLivraisonVentePersistance{
	
	private String PREDICATE_PRODUIT = "produitId";
	private String PREDICATE_LIVRAISON_VENTE = "livraisonVente";
	private String PREDICATE_DATE = "date";
	private String PREDICATE_CLIENT = "partieIntId";
	private String PREDICATE_TRAITEMENTFACONID = "traitementFaconId";
	private String PREDICATE_FICHEID = "ficheId";
	private String PREDICATE_CHOIX = "choix";
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private BonLivraisonPersistanceUtilities bonLivraisonPersistanceUtilities;
	
	@Override
	public String create(DetLivraisonVenteValue detLivraisonVenteValue) {
		
		DetLivraisonVenteEntity entity = (DetLivraisonVenteEntity) this.creer(bonLivraisonPersistanceUtilities.toEntity(detLivraisonVenteValue));

	    return entity.getId().toString();
	}

	@Override
	public DetLivraisonVenteValue getById(Long id) {
		
		DetLivraisonVenteEntity detLivraisonVenteEntity = this.rechercherParId(id, DetLivraisonVenteEntity.class);

	    return bonLivraisonPersistanceUtilities.toValue(detLivraisonVenteEntity);
	}

	@Override
	public String update(DetLivraisonVenteValue detLivraisonVenteValue) {
		
		DetLivraisonVenteEntity entity = (DetLivraisonVenteEntity) this.modifier(bonLivraisonPersistanceUtilities.toEntity(detLivraisonVenteValue));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(DetLivraisonVenteEntity.class, id);
	}
	

	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public DetLivraisonVenteValue getBylivraisonVenteAndProduit(
			Long livraisonVenteId, Long produitId, String choix) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DetLivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(DetLivraisonVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<DetLivraisonVenteEntity> root = criteriaQuery.from(DetLivraisonVenteEntity.class);

		// Set livraisonVenteId on whereClause if not empty or null
		if (livraisonVenteId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_LIVRAISON_VENTE), livraisonVenteId));
		}
		
		// Set produitId on whereClause if not null
		if (produitId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), produitId));
		}
		
		// Set produitId on whereClause if not null
		if (choix != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CHOIX), choix));
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <DetLivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    if(resultatEntite.size() >0){
	    	return bonLivraisonPersistanceUtilities.toValue(resultatEntite.get(0));
	    }
	    else return null;

	}

//	@Override
//	public ResultatRechecheDetailBonLivraisonValue getDetailLivraisonByFnReportingClientProduitDate(
//			RechercheMulticritereFnReportingtValue request) {
//
//		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
//		
//		CriteriaQuery<DetLivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(DetLivraisonVenteEntity.class);
//		List<Predicate> whereClause = new ArrayList<Predicate>();
//		
//		Root<DetLivraisonVenteEntity> root = criteriaQuery.from(DetLivraisonVenteEntity.class);
//
//		// Set clientId on whereClause if not empty or null
//		if (request.getClientId() != null) {
//			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
//			Root<LivraisonVenteEntity> subRoot = subQuery.from(LivraisonVenteEntity.class);
//			
//			subQuery.select(subRoot.<Long>get("id"));
//			subQuery.where(criteriaBuilder.equal(subRoot.get(PREDICATE_CLIENT), request.getClientId()));
//			whereClause.add(criteriaBuilder.in(root.get(PREDICATE_LIVRAISON_VENTE)).value(subQuery));
//		}
//		
//		// Set produitId on whereClause if not null
//		if (request.getProduitId() != null) {
//			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), request.getProduitId()));
//		}
//				
//		// Set request.dateLivraisonMin on whereClause if not null
//	    if (request.getDateMin() != null) {
//	    	Join<DetLivraisonVenteEntity, LivraisonVenteEntity> jointuredetLiv = root.join(PREDICATE_LIVRAISON_VENTE);
//		  	  
//	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(jointuredetLiv.<Calendar>get(PREDICATE_DATE), request.getDateMin()));
//	    }
//	    
//		// Set request.dateLivraisonMax on whereClause if not null
//	    if (request.getDateMax() != null) {
//	    	Join<DetLivraisonVenteEntity, LivraisonVenteEntity> jointuredetLiv = root.join(PREDICATE_LIVRAISON_VENTE);
//		  	  
//	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(jointuredetLiv.<Calendar>get(PREDICATE_DATE), request.getDateMax()));
//	    }
//
//		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
//	    List <DetLivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
//
//	    List<DetLivraisonVenteValue> list = new ArrayList<DetLivraisonVenteValue>();
//	    
//	    for (DetLivraisonVenteEntity entity : resultatEntite) {
//	    	DetLivraisonVenteValue dto = bonLivraisonPersistanceUtilities.toValue(entity);
//	    	list.add(dto);
//	    }
//
//	    ResultatRechecheDetailBonLivraisonValue resultat = new ResultatRechecheDetailBonLivraisonValue();
//	    resultat.setNombreResultaRechercher(Long.valueOf(list.size()));
//	    resultat.setListDetailLivraison(list);
//	    
//
//	    return resultat;
//
//	}

	@Override
	public void setTraitementPU(Long idTraitementFacon, Double nouveauPU, Long idFiche) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DetLivraisonVenteEntity> criteriaQuery = criteriaBuilder.createQuery(DetLivraisonVenteEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<DetLivraisonVenteEntity> root = criteriaQuery.from(DetLivraisonVenteEntity.class);
		//////System.out.println("------- set PU traitement detLivraison-------");
		//////System.out.println("--- idTraitementFacon ---"+ idTraitementFacon);
		//////System.out.println("--- idFiche ---"+ idFiche);
		
		// Set traitementFaconId on whereClause if not null
		if (idTraitementFacon != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_TRAITEMENTFACONID), idTraitementFacon));
		}
				
		// Set ficheId on whereClause if not null
		if (idTraitementFacon != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_FICHEID), idFiche));
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <DetLivraisonVenteEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    //////System.out.println("------- resultatEntite.size() ---------"+ resultatEntite.size());
	    for (DetLivraisonVenteEntity entity : resultatEntite) {
	    	DetLivraisonVenteValue detLivraisonVenteValue = bonLivraisonPersistanceUtilities.toValue(entity);
	    	//////System.out.println("------ detLivraisonVenteValue.toString() :before change-------"+ detLivraisonVenteValue.toString());
	    	detLivraisonVenteValue.setPrixUnitaireHT(nouveauPU);
	    	if(detLivraisonVenteValue.getQuantite() != null){
	    		detLivraisonVenteValue.setPrixTotalHT(nouveauPU * detLivraisonVenteValue.getQuantite());
	    	}
	    	
	    	//////System.out.println("------ detLivraisonVenteValue.toString() after change -------"+ detLivraisonVenteValue.toString());
	    	this.update(detLivraisonVenteValue);
	    }

	}

	

	
}
