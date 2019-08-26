package com.gpro.consulting.tiers.gc.persitance.factureVente.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.FactureVenteElementValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.FactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.RechercheMulticritereFactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.ResultatRechecheFactureVenteValue;
import com.gpro.consulting.tiers.gc.persitance.factureVente.IFactureVentePersistance;
import com.gpro.consulting.tiers.gc.persitance.factureVente.entite.FactureVenteEntite;
import com.gpro.consulting.tiers.gc.persitance.factureVente.utility.PersistanceUtilities;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class FactureVentePersistanceImpl extends AbstractPersistance implements
		IFactureVentePersistance {

	@PersistenceContext
	private EntityManager entityManager;

	private String PERCENT = "%";
	private String PREDICATE_REFERENCE = "reference";
	private String PREDICATE_PARTIE_INT = "partintId";
	private String PREDICATE_DATE_FACTURE = "dateFacture";
	private String PREDICATE_DATE_ECHEANCE = "dateEcheance";
	private String PREDICATE_PRIX_TOTAL_HT = "prixTotalHT";
	

	@Override
	public String creerFactureVente(FactureVenteValue pFactureVenteValue) {
		FactureVenteEntite result = (FactureVenteEntite) this
				.creer(PersistanceUtilities.toEntite(pFactureVenteValue));
		return result.getId().toString();
	}

	@Override
	public void supprimerFactureVenteValue(Long pId) {
		this.supprimerEntite(FactureVenteEntite.class, pId);
	}

	@Override
	public String modifierFactureVenteValue(FactureVenteValue pFactureVenteValue) {
		FactureVenteEntite result = (FactureVenteEntite) this
				.modifier(PersistanceUtilities.toEntite(pFactureVenteValue));
		return result.getId().toString();
	}

	@Override
	public FactureVenteValue rechercheFactureVenteValueParId(Long pId) {
		
		FactureVenteEntite vFactureVenteEntite = this.rechercherParId(pId, FactureVenteEntite.class);

		FactureVenteValue vFactureVenteValueResult = PersistanceUtilities.toValue(vFactureVenteEntite);
		//System.out.println("-----------vFactureVenteValueResult-----"+vFactureVenteValueResult);

		return vFactureVenteValueResult;
	}

	@Override
	public ResultatRechecheFactureVenteValue rechercherFactureVenteMultiCritere(
			RechercheMulticritereFactureVenteValue request) {
		CriteriaBuilder criteriaBuilder = this.entityManager
				.getCriteriaBuilder();
		CriteriaQuery<FactureVenteEntite> criteriaQuery = criteriaBuilder
				.createQuery(FactureVenteEntite.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<FactureVenteEntite> root = criteriaQuery
				.from(FactureVenteEntite.class);

		// Set request.reference on whereClause if not null
		if (estNonVide(request.getReference())) {
			Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper = criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT
					+ request.getReference().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}

		// Set request.piId on whereClause if not null
		if (request.getPartieInteresseeId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PARTIE_INT), request.getPartieInteresseeId()));
		}
		// Set request.dateFactureMin on whereClause if not null
	    if (request.getDateFactureDu() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_FACTURE), request.getDateFactureDu()));
	    }
	    
	    // Set request.dateFactureMax on whereClause if not null
	    if (request.getDateFactureAu() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_FACTURE), request.getDateFactureAu()));
	    }
	    
	    // Set request.dateEcheanceMin on whereClause if not null
	    if (request.getDateEcheanceDu() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_ECHEANCE), request.getDateEcheanceDu()));
	    }
	    
	    // Set request.dateEcheanceMax on whereClause if not null
	    if (request.getDateEcheanceAu() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar>get(PREDICATE_DATE_ECHEANCE), request.getDateEcheanceAu()));
	    }
	    
	    // Set request.prixTotalMin on whereClause if not null
	    if (request.getCoutDu() != null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Double>get(PREDICATE_PRIX_TOTAL_HT), request.getCoutDu()));
	    }
	    
		// Set request.prixTotalMax on whereClause if not null
	    if (request.getCoutA() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Double>get(PREDICATE_PRIX_TOTAL_HT), request.getCoutA()));
	    }
	    
		criteriaQuery.select(root).where(
				whereClause.toArray(new Predicate[] {}));
		List<FactureVenteEntite> resultatEntite = this.entityManager
				.createQuery(criteriaQuery).getResultList();

		List<FactureVenteElementValue> listFactureVenteValue = new ArrayList<FactureVenteElementValue>();

		for (FactureVenteEntite entity : resultatEntite) {
			FactureVenteElementValue dto = PersistanceUtilities
					.toElementValue(entity);
			listFactureVenteValue.add(dto);
		}

		Collections.sort(listFactureVenteValue);

		ResultatRechecheFactureVenteValue resultat = new ResultatRechecheFactureVenteValue();

		resultat.setNombreResultaRechercher(Long.valueOf(listFactureVenteValue
				.size()));

		resultat.setFactureVenteValues(listFactureVenteValue);
		//System.out.println("------resultat:rech. Multi C. persistance"+resultat);
		return resultat;
	}

	@Override
	public List<FactureVenteValue> getAllByReference(List<String> refBLList) {
		
		if (refBLList.size() > 0) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<FactureVenteEntite> criteriaQuery = criteriaBuilder.createQuery(FactureVenteEntite.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<FactureVenteEntite> root = criteriaQuery.from(FactureVenteEntite.class);

			whereClause.add(root.get(PREDICATE_REFERENCE).in(refBLList));

			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

			List<FactureVenteEntite> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

			List<FactureVenteValue> result = new ArrayList<FactureVenteValue>();

			for (FactureVenteEntite entity : entityListResult) {
				FactureVenteValue value = PersistanceUtilities.toValue(entity);
				result.add(value);
			}

			return result;
		} else
			return null;

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
