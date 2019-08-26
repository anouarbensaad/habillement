package com.gpro.consulting.tiers.gc.persitance.livraisonVente.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.LivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.RechercheMulticritereLivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.ResultatRechecheLivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.livraisonVenteElementValue;
import com.gpro.consulting.tiers.gc.persitance.livraisonVente.ILivraisonVentePersistance;
import com.gpro.consulting.tiers.gc.persitance.livraisonVente.entite.LivraisonVenteEntite;
import com.gpro.consulting.tiers.gc.persitance.livraisonVente.entite.ProduitLivraisonEntite;
import com.gpro.consulting.tiers.gc.persitance.livraisonVente.utility.PersistanceUtilities;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class LivraisonVentePersistanceImpl extends AbstractPersistance implements ILivraisonVentePersistance {

	@PersistenceContext
	private EntityManager entityManager;
	
	private String PERCENT = "%";
	private String PREDICATE_REFERENCE="reference" ;
	private String PREDICATE_PARTIE_INT = "partieIntersseId";
	private String PREDICATE_PRODUIT_LIVRAISON = "produitLivraison";
	private String PREDICATE_PRODUIT="produit";
	private String PREDICATE_ID="id";
	private String PREDICATE_DATE_COMMANDE = "dateCommande";
	private String PREDICATE_DATE_LIVRAISON = "dateLivraison";
	private String PREDICATE_PRIX_TOTAL_HT = "prixTotal";
	
	@Override
	public String creerLivraisonVente(LivraisonVenteValue pLivraisonVenteValue) {
		
		LivraisonVenteEntite result = (LivraisonVenteEntite) this.creer(PersistanceUtilities.toEntite(pLivraisonVenteValue));
		return result.getId().toString();
	}

	@Override
	public void supprimerLivraisonVenteValue(Long pId) {
		this.supprimerEntite(LivraisonVenteEntite.class, pId);
	}

	@Override
	public String modifierLivraisonVenteValue(
			LivraisonVenteValue pLivraisonVenteValue) {
		LivraisonVenteEntite result = (LivraisonVenteEntite) this.modifier(PersistanceUtilities.toEntite(pLivraisonVenteValue));
		return result.getId().toString();
	}

	@Override
	public LivraisonVenteValue rechercheLivraisonVenteValueParId(Long pId) {
		LivraisonVenteEntite vLivraisonVenteEntite = this.rechercherParId(pId,LivraisonVenteEntite.class);
		
		LivraisonVenteValue vLivCommandeVenteValueResult = PersistanceUtilities.toValue(vLivraisonVenteEntite);
		return vLivCommandeVenteValueResult;
	}

	@Override
	public ResultatRechecheLivraisonVenteValue rechercherLivraisonVenteMultiCritere(
			RechercheMulticritereLivraisonVenteValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<LivraisonVenteEntite> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntite.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<LivraisonVenteEntite> root = criteriaQuery.from(LivraisonVenteEntite.class);
		
		// Set request.reference on whereClause if not null
		if (estNonVide(request.getReference())) {
			Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getReference().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		// Set request.piId on whereClause if not null
		if (request.getPartieInteresseeId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PARTIE_INT), request.getPartieInteresseeId()));
		}
		// Set request.produitId on whereClause if not null
		 if (request.getProduitId() != null) {
			 Join<LivraisonVenteEntite, ProduitLivraisonEntite> jointureCmdEnPrdLiv = root.join(PREDICATE_PRODUIT_LIVRAISON);
			 whereClause.add(criteriaBuilder.equal(jointureCmdEnPrdLiv.get(PREDICATE_PRODUIT).get(PREDICATE_ID), request.getProduitId()));
	    }
		// Set request.dateCommandeMin on whereClause if not null
	    if (request.getDateIntroductionDu() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_COMMANDE), request.getDateIntroductionDu()));
	    }
	    
	    // Set request.dateCommandeMax on whereClause if not null
	    if (request.getDateIntroductionA() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_COMMANDE), request.getDateIntroductionA()));
	    }
	    
	    // Set request.dateLivMin on whereClause if not null
	    if (request.getDateLivraisonDu() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_LIVRAISON), request.getDateLivraisonDu()));
	    }
	    
	    // Set request.dateLivMax on whereClause if not null
	    if (request.getDateLivraisonA() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_LIVRAISON), request.getDateLivraisonA()));
	    }
	    
	    // Set request.coutMin on whereClause if not null
	    if (request.getCoutDu() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_PRIX_TOTAL_HT), request.getCoutDu()));
	    }
	    
	    // Set request.coutMax on whereClause if not null
	    if (request.getCoutA() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_PRIX_TOTAL_HT), request.getCoutA()));
	    }
	    
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <LivraisonVenteEntite> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<livraisonVenteElementValue> listLivraisonVenteValue = new ArrayList <livraisonVenteElementValue>();
	    
	    for (LivraisonVenteEntite entity : resultatEntite) {
	    	livraisonVenteElementValue dto = PersistanceUtilities.toElementValue(entity);
	    	listLivraisonVenteValue.add(dto);
	    }

	    Collections.sort(listLivraisonVenteValue);
	    
	    ResultatRechecheLivraisonVenteValue resultat = new ResultatRechecheLivraisonVenteValue();

	    resultat.setNombreResultaRechercher(Long.valueOf(listLivraisonVenteValue.size()));

	    resultat.setLivraisonVenteValues(listLivraisonVenteValue);
	    return resultat;
	  }
	
	@Override
	public List<LivraisonVenteValue> getAllByReference(List<String> refLivraisonVenteList) {
		
	    if(refLivraisonVenteList.size() > 0){
	    	
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		    CriteriaQuery<LivraisonVenteEntite> criteriaQuery = criteriaBuilder.createQuery(LivraisonVenteEntite.class);
		    List<Predicate> whereClause = new ArrayList<Predicate>();
		    Root<LivraisonVenteEntite> root = criteriaQuery.from(LivraisonVenteEntite.class);
	    	
	    	whereClause.add(root.get(PREDICATE_REFERENCE).in(refLivraisonVenteList));
	    	
		    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    
		    List<LivraisonVenteEntite> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

		    List<LivraisonVenteValue> result = new ArrayList<LivraisonVenteValue>();
		    
		    for (LivraisonVenteEntite entity : entityListResult) {
		    	LivraisonVenteValue value = PersistanceUtilities.toValue(entity);
		    	result.add(value);
		    }
		    
		    return result;
	    }
	    else
	    	return null;

	}
	

	@Override
	public List<LivraisonVenteValue> getAll() {
		
		List<LivraisonVenteEntite> listEntity = this.lister(LivraisonVenteEntite.class);
		
		List<LivraisonVenteValue> result = new ArrayList<LivraisonVenteValue>();
		
		for(LivraisonVenteEntite entity : listEntity){
			
	    	LivraisonVenteValue value = PersistanceUtilities.toValue(entity);
	    	result.add(value);
		}
		
		return result;
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

}
