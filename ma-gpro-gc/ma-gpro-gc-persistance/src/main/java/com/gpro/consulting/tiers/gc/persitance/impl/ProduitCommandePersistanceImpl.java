package com.gpro.consulting.tiers.gc.persitance.impl;

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
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gc.coordination.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheProduitCommandeValue;
import com.gpro.consulting.tiers.gc.persitance.IProduitCommandePersistance;
import com.gpro.consulting.tiers.gc.persitance.entite.ProduitCommandeEntite;
import com.gpro.consulting.tiers.gc.persitance.utilities.PersistanceUtilities;

/**
 * Implementation of {@link IProduitCommandePersistance}
 * 
 * @author Wahid Gazzah
 * @since 15/03/2016
 *
 */

@Component
public class ProduitCommandePersistanceImpl extends AbstractPersistance implements IProduitCommandePersistance{

	private static final Logger logger = LoggerFactory.getLogger(ProduitCommandePersistanceImpl.class);
	
	private String PERCENT = "%";
	private String PREDICATE_REF_COMMANDE = "reference";
	private String PREDICATE_DATE_LIV = "dateLivraison";
	private String PREDICATE_DATE_CMD = "dateIntroduction";
	
	private String PRODUIT = "produit";
	private String COMMANDE_VENTE= "commandeVente";
	private String CLIENT_ID = "partieIntersseId";
	private String PRODUIT_ID = "id";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<ProduitCommandeValue> getAll() {
		
		List<ProduitCommandeEntite> listEntity = this.lister(ProduitCommandeEntite.class);
		
		return PersistanceUtilities.toValue(listEntity);
	}

	@Override
	public ResultatRechecheProduitCommandeValue rechercherMultiCritere(
			RechercheMulticritereProduitCommandeValue request) {
		
		logger.info("rechercherMultiCritere.");

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		
		CriteriaQuery<ProduitCommandeEntite> criteriaQuery = criteriaBuilder.createQuery(ProduitCommandeEntite.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		
		Root<ProduitCommandeEntite> root = criteriaQuery.from(ProduitCommandeEntite.class);
		
		// Set request.produitId on whereClause if not null
	    if (request.getProduitId() != null) {
	    	whereClause.add(criteriaBuilder.equal(root.get(PRODUIT).get(PRODUIT_ID), request.getProduitId()));
	    }
	    
		// Set request.clientId on whereClause if not null
	    if (request.getClientId() != null) {
	    	whereClause.add(criteriaBuilder.equal(root.get(COMMANDE_VENTE).get(CLIENT_ID), request.getClientId()));
	    }
		
		// Set request.refCommande on whereClause if not null
		if (estNonVide(request.getRefCommande())) {
			Expression<String> path = root.get(COMMANDE_VENTE).get(PREDICATE_REF_COMMANDE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getRefCommande().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}

		// Set request.dateLivraisonMin on whereClause if not null
	    if (request.getDateLivraisonMin() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_LIV), request.getDateLivraisonMin()));
	    }
	    
		// Set request.dateLivraisonMax on whereClause if not null
	    if (request.getDateLivraisonMax() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_LIV), request.getDateLivraisonMax()));
	    }
	    
		// Set request.dateCommandeMin on whereClause if not null
	    if (request.getDateCommandeMin() != null) {
	    	Expression<Calendar> path = root.get(COMMANDE_VENTE).get(PREDICATE_DATE_CMD);
	    	Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(path, request.getDateCommandeMin());
	    	whereClause.add(criteriaBuilder.and(predicate));
	    	//whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_CMD), request.getDateCommandeMin()));
	    }
	    
		// Set request.dateCommandeMax on whereClause if not null
	    if (request.getDateCommandeMax() != null) {
	    	Expression<Calendar> path = root.get(COMMANDE_VENTE).get(PREDICATE_DATE_CMD);
	    	Predicate predicate = criteriaBuilder.lessThanOrEqualTo(path, request.getDateCommandeMax());
	    	whereClause.add(criteriaBuilder.and(predicate));
	    	//whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_CMD), request.getDateCommandeMax()));
	    }

	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <ProduitCommandeEntite> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<ProduitCommandeValue> list = new ArrayList<ProduitCommandeValue>();
	    
	    for (ProduitCommandeEntite entity : resultatEntite) {
	    	ProduitCommandeValue dto = PersistanceUtilities.toValue(entity);
	    	list.add(dto);
	    }

	    ResultatRechecheProduitCommandeValue result = new ResultatRechecheProduitCommandeValue();
	    Collections.sort(list);
	    result.setNombreResultaRechercher(Long.valueOf(list.size()));
	    result.setListProduitCommandeValue(new TreeSet<>(list));

	    return result;
	}

	//Added on 22/03/2016, by Ameni
	@Override
	public void supprimerProduitCommande(Long pId) {
		if (logger.isDebugEnabled()) {
		      logger.debug("Suppression d'un ProduitComande est en cours. id :" + pId.toString());
		    }
		logger.info("suppresion d'un ProduitCommande: " + pId.toString() );
		this.supprimerEntite(ProduitCommandeEntite.class, pId.longValue());
	}
	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);
	}
	
	@Override
	public EntityManager getEntityManager() {
		
		return entityManager;
	}

	@Override
	public ProduitCommandeValue getById(Long id) {
		
		ProduitCommandeEntite entity = this.rechercherParId(id, ProduitCommandeEntite.class);
		
		ProduitCommandeValue dto = PersistanceUtilities.toValue(entity);
		
		return dto;
	}

}
