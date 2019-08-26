package com.gpro.consulting.tiers.gc.persitance.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
import com.gpro.consulting.tiers.gc.coordination.value.CommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereCommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheCommandeVenteValue;
import com.gpro.consulting.tiers.gc.persitance.ICommandeVentePersistance;
import com.gpro.consulting.tiers.gc.persitance.entite.CommandeVenteEntite;
import com.gpro.consulting.tiers.gc.persitance.entite.ProduitCommandeEntite;
import com.gpro.consulting.tiers.gc.persitance.utilities.PersistanceUtilities;

/**
 * @author $Ameni
 *
 */

@Component
public class CommandeVentePersistanceImpl extends AbstractPersistance implements ICommandeVentePersistance {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private String PERCENT = "%";
	private String PREDICATE_REFERENCE="reference" ;
	private String PREDICATE_CLIENT="partieIntersseId";
	private String PREDICATE_ETAT="etatCommande_id";
	private String PREDICATE_TYPE="typeCommande_id";
	private String PREDICATE_PRODUIT_COMMANDES="produitCommandes";
	private String PREDICATE_DATE_INTRODUCTION="dateIntroduction";
	private String PREDICATE_DATE_LIVRAISON="dateLivraison";
	private String PREDICATE_PRODUIT="produit";
	private String PREDICATE_ID="id";
	private String PREDICATE_QUANTITE = "quantite";
	private String PREDICATE_COUT = "prixTotal";
	private String OF_PREDICATE = "of_reference";
	@Override
	public String creerCommandeVente(CommandeVenteValue request) {
		if(request.getProduitCommandes() != null){
			log.info("Creation d'une commande de Vente.. size "+request.getProduitCommandes().size());
			Set<ProduitCommandeValue> list = request.getProduitCommandes();
			//System.out.println("-----CommandeVenteValue : produitCommande request list----"+list);

		}
		
		CommandeVenteEntite result = (CommandeVenteEntite) this.modifier(PersistanceUtilities.toEntite(request));
		return result.getId().toString();
	}

	@Override
	public void supprimerCommandeVente(Long pId) {
		if (log.isDebugEnabled()) {
			log.debug("Suppression d'un bon de commande de vente avec l'ID : "
					+ pId.longValue());
		}
		this.supprimerEntite(CommandeVenteEntite.class, pId);
	}

	@Override
	public String modifierCommandeVente(CommandeVenteValue request) {
		log.info("Modification d'une commande de Vente.. size "+request.getProduitCommandes().size());
		
		if (log.isDebugEnabled()) {
			log.debug("Modification d'un bon de commande de vente avec la Reference : "
					+ request.getReference());
		}		
		
		CommandeVenteEntite result = (CommandeVenteEntite) this.modifier(PersistanceUtilities.toEntite(request));

		return result.getId().toString();
	}

	@Override
	public CommandeVenteValue rechercheCommandeVenteParId(Long pId) {
		if (log.isDebugEnabled()) {
			log.debug("recherche  d'un bon de commande de vente. Id : "
					+ pId);
		}
		log.info("recherche d'un bon de commande de vente. Id : "+ pId);
		
		CommandeVenteEntite vCommandeVenteEntite = this.rechercherParId(pId,CommandeVenteEntite.class);
		
		CommandeVenteValue vCommandeVenteValueResult = PersistanceUtilities.toValue(vCommandeVenteEntite);
		
		return vCommandeVenteValueResult;
	}

	@Override
	public List<CommandeVenteValue> listeCommandeVente() {
		
		List<CommandeVenteEntite> vListeCommandeVenteEntite = this.lister(CommandeVenteEntite.class);
		List<CommandeVenteValue> vListeCommandeVenteValues = new ArrayList<CommandeVenteValue>();
		
		for (CommandeVenteEntite vCommandeVenteEntite : vListeCommandeVenteEntite) {
			vListeCommandeVenteValues.add(PersistanceUtilities.toValue(vCommandeVenteEntite));
		}
		
		Collections.sort(vListeCommandeVenteValues);
		
		return vListeCommandeVenteValues;
	}

	@Override
	public ResultatRechecheCommandeVenteValue rechercherCommandeVenteMultiCritere(
			RechercheMulticritereCommandeVenteValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<CommandeVenteEntite> criteriaQuery = criteriaBuilder.createQuery(CommandeVenteEntite.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<CommandeVenteEntite> root = criteriaQuery.from(CommandeVenteEntite.class);
		
		/* Reference */
		if (estNonVide(request.getvReference())) {
			Expression<String> path = root.get(PREDICATE_REFERENCE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getvReference().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		/* Client (PartieInteressee) */
		if (estNonVide(request.getvTypePartieInteressee())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getvTypePartieInteressee()));
		}
		/* Produit */
		 if (estNonVide(request.getvProduit())) {
			 Join<CommandeVenteEntite, ProduitCommandeEntite> jointureCmdEnPrdCmd = root.join(PREDICATE_PRODUIT_COMMANDES);
			 whereClause.add(criteriaBuilder.equal(jointureCmdEnPrdCmd.get(PREDICATE_PRODUIT).get(PREDICATE_ID), request.getvProduit()));
	    }
		
		/* Data Introduction */
	    if(request.getDateIntroductionDu()!=null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(
	    			root.<Calendar>get(PREDICATE_DATE_INTRODUCTION), request.getDateIntroductionDu()));
	    }

		if (request.getDateIntroductionA() != null) {		
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(
					root.<Calendar>get(PREDICATE_DATE_INTRODUCTION), request.getDateIntroductionA()));
		}
		
		/* Data Livraison */
	    if(request.getDateLivraisonDu()!=null){
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(
	    			root.<Calendar>get(PREDICATE_DATE_LIVRAISON), request.getDateLivraisonDu()));
	    	
	    }

	    if (request.getDateLivraisonA() != null) {
	    	whereClause.add(criteriaBuilder.lessThanOrEqualTo(
					root.<Calendar>get(PREDICATE_DATE_LIVRAISON), request.getDateLivraisonA()));
		}
	    
		/* Etat  */
		if (estNonVide(request.getvEtatCommande())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_ETAT),	request.getvEtatCommande()));
		}
		/* Type */
		if (estNonVide(request.getvTypeCommande())) {
			whereClause.add(criteriaBuilder.equal( root.get(PREDICATE_TYPE), request.getvTypeCommande()));
		}
		
		/* quantite */
	    if(request.getQuantiteDu()!=null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(
	    			root.<Long>get(PREDICATE_QUANTITE), request.getQuantiteDu()));
	    }

		if (request.getQuantiteA() != null) {		
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(
					root.<Long>get(PREDICATE_QUANTITE), request.getQuantiteA()));
		}
		
		/* Cout */
	    if(request.getCoutDu()!=null) {
	    	whereClause.add(criteriaBuilder.greaterThanOrEqualTo(
	    			root.<Double>get(PREDICATE_COUT), request.getCoutDu()));
	    }

		if (request.getCoutA() != null) {		
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(
					root.<Double>get(PREDICATE_COUT), request.getCoutA()));
		}

	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <CommandeVenteEntite> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<CommandeVenteValue> listCommandeVenteValue = new ArrayList <CommandeVenteValue>();
	    
	    for (CommandeVenteEntite entity : resultatEntite) {
	    	CommandeVenteValue dto = PersistanceUtilities.toValue(entity);
	    	listCommandeVenteValue.add(dto);
	    }

	    Collections.sort(listCommandeVenteValue);
	    
	    ResultatRechecheCommandeVenteValue resultat = new ResultatRechecheCommandeVenteValue();

	    resultat.setNombreResultaRechercher(Long.valueOf(listCommandeVenteValue.size()));

	    resultat.setCommandeVenteValues(listCommandeVenteValue);

	    return resultat;
	  }

	

	@Override
	public List<CommandeVenteValue> getAllByReference(List<String> refBonCmdList) {
		
	    if(refBonCmdList.size() > 0){
	    	
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		    CriteriaQuery<CommandeVenteEntite> criteriaQuery = criteriaBuilder.createQuery(CommandeVenteEntite.class);
		    List<Predicate> whereClause = new ArrayList<Predicate>();
		    Root<CommandeVenteEntite> root = criteriaQuery.from(CommandeVenteEntite.class);
	    	
	    	whereClause.add(root.get(PREDICATE_REFERENCE).in(refBonCmdList));
	    	
		    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		    
		    List<CommandeVenteEntite> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

		    List<CommandeVenteValue> result = new ArrayList<CommandeVenteValue>();
		    
		    for (CommandeVenteEntite entity : entityListResult) {
		    	CommandeVenteValue value = PersistanceUtilities.toValue(entity);
		    	result.add(value);
		    }
		    
		    return result;
	    }
	    else
	    	return null;

	}
	
	@Override
	public List<CommandeVenteValue> getAll() {
		
		List<CommandeVenteEntite> listEntity = this.lister(CommandeVenteEntite.class);
		
		List<CommandeVenteValue> result = new ArrayList<CommandeVenteValue>();
		
		for(CommandeVenteEntite entity : listEntity){
			
			CommandeVenteValue value = PersistanceUtilities.toValue(entity);
	    	result.add(value);
		}
		
		return result;
	}
	
	
	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);

	}

	
	
	/***************************** Getter & Setter ********************************/
	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
    
	@Override
    public boolean existeOF(String numeroOF) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery<CommandeVenteEntite> criteriaQuery = criteriaBuilder.createQuery(CommandeVenteEntite.class);
	    List<Predicate> whereClause = new ArrayList<Predicate>();
	    Root<CommandeVenteEntite> root = criteriaQuery.from(CommandeVenteEntite.class);
	    
	    Expression<String> path = root.get(OF_PREDICATE);
		Expression<String> upper =criteriaBuilder.upper(path);
		Predicate predicate = criteriaBuilder.like(upper,numeroOF.toUpperCase());
		
	    criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    criteriaQuery.where(criteriaBuilder.and(predicate));
	    
	    List<CommandeVenteEntite> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();
	    
	    if(entityListResult.size()!=0){
	    	return true;
	    }
	    
		return false;
	}

}
