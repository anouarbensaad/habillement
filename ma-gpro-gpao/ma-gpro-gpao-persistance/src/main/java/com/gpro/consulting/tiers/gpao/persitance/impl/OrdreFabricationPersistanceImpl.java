package com.gpro.consulting.tiers.gpao.persitance.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.ArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.PhaseProduitEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.ProduitEntity;
import com.gpro.consulting.tiers.gc.persitance.entite.CommandeVenteEntite;
import com.gpro.consulting.tiers.gc.persitance.entite.ProduitCommandeEntite;
import com.gpro.consulting.tiers.gpao.coordination.value.CompositionOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.vue.PhaseProduitVue;
import com.gpro.consulting.tiers.gpao.coordination.vue.QuantiteVue;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.entite.CompositionOfEntite;
import com.gpro.consulting.tiers.gpao.persitance.entite.OrdreFabricationEntite;
import com.gpro.consulting.tiers.gpao.persitance.utilities.PersistanceUtilities;

/**
 * @author $Ameni
 *
 */

@Component
public class OrdreFabricationPersistanceImpl extends AbstractPersistance implements IOrdreFabricationPersistance {

	@PersistenceContext
	private EntityManager entityManager;

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(OrdreFabricationPersistanceImpl.class);

	private String PREDICATE_CLIENT_ID = "partieInterresId";
	private String PREDICATE_PRODUIT_ID = "produitId";
	private String PREDICATE_DATE_INTRODUCTION = "dateIntroduction";
	private String PREDICATE_DATE_LIVRAISON = "dateLivraison";
	private String PREDICATE_NUMERO = "numero";
	private String PREDICATE_ETAT = "etat";
	private String PREDICATE_ID = "id";
	
	private String PREDICATE_QTE = "quantite";
	private String PREDICATE_QTE_COUPE = "qtCoupe";
	private String PREDICATE_QTE_FABRICATION = "qtSortie";
	private String PREDICATE_QTE_CONDITIONNEMENT = "qtFinition";
	private String PREDICATE_QTE_COLLISAGE = "qtColisage";
	private String PREDICATE_QTE_EXPEDITION = "qtExpedition";
	
	private String PREDICATE_QTE_ENG = "qtEngagement";
	
	
	
	private int MAX_RESULTS = 39;

	private String OF_NUMERO_EXIST_ERROR = "GPAO01";

	  
	/*
	 * Methode de Creation d'un Ordre de Fabrication dans la BD
	 * 
	 */
	@Override
	public String creerOrdreFabrication(OrdreFabricationValue pOrdreFabricationValue) {

		if(this.numOFExistence(pOrdreFabricationValue.getNumero())){
			return OF_NUMERO_EXIST_ERROR;
		}
		
		Set<ProduitCommandeEntite> vListProduitCommandeEntite = new HashSet<ProduitCommandeEntite>();

		if (LOGGER.isDebugEnabled()) {
			//logger.debug("Persister l'ordre de fabrication de numero : " + pOrdreFabricationValue.getNumero()
				//	+ " est en cours.");
		}
		if (pOrdreFabricationValue.getCompositionsOF() != null) {
			//LOGGER.info("--SIZE CompositionsOF " + pOrdreFabricationValue.getCompositionsOF().size());

			for (CompositionOfValue cmpOF : pOrdreFabricationValue.getCompositionsOF()) {
				//LOGGER.info("--IdProduit " + cmpOF.getIdProduit());
				//LOGGER.info("--Qte " + cmpOF.getQuantite());
				//LOGGER.info("--ProduitCommandeId " + cmpOF.getCommandeId());
				ProduitCommandeEntite vEntite = this.rechercherParId(cmpOF.getCommandeId(),
						ProduitCommandeEntite.class);
				//LOGGER.info("--vEntite ID" + vEntite.getId());

				vListProduitCommandeEntite.add(vEntite);
			}
		} else {
			//LOGGER.info("--pOrdreFabricationValue.getCompositionsOF() is NULL");

		}
		OrdreFabricationEntite vOrdreFabricationEntite = (OrdreFabricationEntite) this
				.modifier(PersistanceUtilities.ToEntite(pOrdreFabricationValue, vListProduitCommandeEntite));
		// //LOGGER.info(" Compositions "+
		// pOrdreFabricationValue.getCompositionsOF().size());
		return vOrdreFabricationEntite.getId().toString();
	}

	@Override
	public void supprimerOrdreFabrication(Long pId) {
		if (LOGGER.isDebugEnabled()) {
			//logger.debug("Suppression d'un ordre de fabrication d'ID=" + pId.toString() + " est en cours.");
		}
		this.supprimerEntite(OrdreFabricationEntite.class, pId.longValue());
	}

	@Override
	public String modifierOrdreFabrication(OrdreFabricationValue pOrdreFabricationValue) {
		
		if(!pOrdreFabricationValue.getNumero().equals(pOrdreFabricationValue.getNumOFBeforeUpdate()) && this.numOFExistence(pOrdreFabricationValue.getNumero())){
			System.out.println("Exist");
			return OF_NUMERO_EXIST_ERROR;
		}
		
		Set<ProduitCommandeEntite> vListProduitCommandeEntite = new HashSet<ProduitCommandeEntite>();

		if (LOGGER.isDebugEnabled()) {
			//logger.debug("Modification d'un ordre de fabrication d'ID=" + pOrdreFabricationValue.getId().toString()
				//	+ " est en cours.");
		}
		if (pOrdreFabricationValue.getCompositionsOF() != null) {
			for (CompositionOfValue cmpOF : pOrdreFabricationValue.getCompositionsOF()) {
				ProduitCommandeEntite vEntite = this.rechercherParId(cmpOF.getCommandeId(),
						ProduitCommandeEntite.class);
				vListProduitCommandeEntite.add(vEntite);
			}
		}

		OrdreFabricationEntite vOrdreFabricationEntite = (OrdreFabricationEntite) this
				.modifier(PersistanceUtilities.ToEntite(pOrdreFabricationValue, vListProduitCommandeEntite));

		return vOrdreFabricationEntite.getId().toString();
	}

	@Override
	public OrdreFabricationValue rechercheOrdreFabricationParId(Long pOrdreFabricationId) {
		if (LOGGER.isDebugEnabled()) {
			//logger.debug(
				//	"Recherche d'un ordre de fabrication d'ID=" + pOrdreFabricationId.longValue() + " est en cours.");
		}
		OrdreFabricationEntite pOrdreFabricationEntite = this.rechercherParId(pOrdreFabricationId.longValue(),
				OrdreFabricationEntite.class);
		if (pOrdreFabricationEntite != null) {
			OrdreFabricationValue vOrdreFabricationValueResult = PersistanceUtilities.ToValue(pOrdreFabricationEntite);
			return vOrdreFabricationValueResult;
		} else
			return null;
	}

	@Override
	public List<QuantiteVue> rechercheQuantiteParIdProduit(Long pIdProduit) {
		if (LOGGER.isDebugEnabled()) {
			//logger.debug("Recherche des quantites à travers un ID Produit=" + pIdProduit);
		}
		//LOGGER.info("Recherche des quantites à travers un ID Produit=" + pIdProduit);

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		/** entity principale **/
		CriteriaQuery<ProduitCommandeEntite> criteriaQuery = criteriaBuilder.createQuery(ProduitCommandeEntite.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		/************ entity jointure *****************/
		Root<ProduitCommandeEntite> vRootProduitCommande = criteriaQuery.from(ProduitCommandeEntite.class);

		/********* Select ***********/
		criteriaQuery.select(vRootProduitCommande).where(whereClause.toArray(new Predicate[] {}));

		/***************** Predicates *************/
		Join<ProduitCommandeEntite, CommandeVenteEntite> jointurePcCv = vRootProduitCommande.join("commandeVente");

		/** Criteria 1: produitCommandeId = pIdProduit **/
		whereClause.add(criteriaBuilder.equal(vRootProduitCommande.get("produit").get("id"), pIdProduit));

		/** Criteria 2: QtePrdCmd >= QteOF **/
		ArrayList<Long> numlist = getAllProduitCommande(pIdProduit);

		for (int x = 0; x < numlist.size(); x++) {
			Expression<Long> vQuantiteOF = getQuantiteOF(pIdProduit, numlist.get(x));
			Expression<Long> vQuantitePrd = vRootProduitCommande.get("quantite");

			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(vQuantitePrd, vQuantiteOF));
		}

		criteriaQuery.multiselect(
				/** ProduitId. */
				vRootProduitCommande.get("produit").get("id"),
				/** CommandeVenteId. */
				vRootProduitCommande.get("commandeVente").get("id"),
				/** QuantiteOF. */
				vRootProduitCommande.get("quantite"),
				/** referenceBC */
				jointurePcCv.get("reference"));

		criteriaQuery.select(vRootProduitCommande).where(whereClause.toArray(new Predicate[] {}));

		List<ProduitCommandeEntite> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		/** Conversion de la liste en valeur */
		List<QuantiteVue> vListeQuantiteVueResultat = new ArrayList<QuantiteVue>();

		for (ProduitCommandeEntite vProduitCommandeEntite : resultatEntite) {
			QuantiteVue vQte = new QuantiteVue();
			//LOGGER.info("** idProduit: " + vProduitCommandeEntite.getProduit().getId());
			vQte.setId(vProduitCommandeEntite.getProduit().getId());
			//LOGGER.info("** quantiteBC: " + vProduitCommandeEntite.getQuantite());
			vQte.setQuantiteBC(vProduitCommandeEntite.getQuantite());
			//LOGGER.info("** referenceBC : " + vProduitCommandeEntite.getCommandeVente().getReference());
			vQte.setNumeroBC(vProduitCommandeEntite.getCommandeVente().getReference());
			//LOGGER.info("** ProduitCommandeId : " + vProduitCommandeEntite.getId());
			vQte.setCommandeId(vProduitCommandeEntite.getId());

			vListeQuantiteVueResultat.add(vQte);
		}

		return vListeQuantiteVueResultat;
	}

	private ArrayList<Long> getAllProduitCommande(Long pIdProduit) {
		if (LOGGER.isDebugEnabled()) {
			//logger.debug("Recherche de tous les ProduitCommandes ayant comme IdProduit = " + pIdProduit);
		}
		//LOGGER.info("Recherche de tous les ProduitCommandes ayant comme IdProduit=" + pIdProduit);

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();

		/** entity principale **/
		CriteriaQuery<ProduitCommandeEntite> criteriaQuery = criteriaBuilder.createQuery(ProduitCommandeEntite.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		/************ entity jointure *****************/
		Root<ProduitCommandeEntite> vRootProduitCommande = criteriaQuery.from(ProduitCommandeEntite.class);

		/********* Select ***********/
		criteriaQuery.select(vRootProduitCommande).where(whereClause.toArray(new Predicate[] {}));

		/** Criteria 1: produitCommandeId = pIdProduit **/
		whereClause.add(criteriaBuilder.equal(vRootProduitCommande.get("produit").get("id"), pIdProduit));

		/** produitCommandeId */
		criteriaQuery.multiselect(vRootProduitCommande.get("id"));

		criteriaQuery.select(vRootProduitCommande).where(whereClause.toArray(new Predicate[] {}));

		List<ProduitCommandeEntite> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		/** Conversion de la liste en valeur */
		ArrayList<Long> vListeQuantiteVueResultat = new ArrayList<Long>();
		if (resultatEntite != null) {
			for (ProduitCommandeEntite vProduitCommandeEntite : resultatEntite) {

				//LOGGER.info("** ProduitCommandeId : " + vProduitCommandeEntite.getId());
				vListeQuantiteVueResultat.add(vProduitCommandeEntite.getId());
			}
		}
		return vListeQuantiteVueResultat;
	}

	private Expression<Long> getQuantiteOF(Long pIdProduit, Long pProduitCommandeId) {
		long somme = 0;
		//LOGGER.info("------**------ getQuantiteOF: /idProduit : " + pIdProduit + " /pProduitCommandeId : "
			//	+ pProduitCommandeId);

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		Expression<Long> quantiteOF = criteriaBuilder.literal(0L);

		/** entity principale **/
		CriteriaQuery<CompositionOfEntite> criteriaQuery = criteriaBuilder.createQuery(CompositionOfEntite.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		/************ entity jointure *****************/
		Root<CompositionOfEntite> vRootCompositionOF = criteriaQuery.from(CompositionOfEntite.class);

		/********* Select ***********/
		criteriaQuery.select(vRootCompositionOF).where(whereClause.toArray(new Predicate[] {}));

		/***************** Predicates *************/
		Join<CompositionOfEntite, ProduitCommandeEntite> jointureCmPc = vRootCompositionOF
				.join("produitCommandeEntite");
		Join<ProduitCommandeEntite, ProduitEntity> jointurePcPr = jointureCmPc.join("produit");

		/** Criteria 1: produitId = pIdProduit **/
		whereClause.add(criteriaBuilder.equal(jointurePcPr.get("id"), pIdProduit));

		/** Criteria 2: produitCommandeId = pProduitCommandeId **/
		whereClause.add(
				criteriaBuilder.equal(vRootCompositionOF.get("produitCommandeEntite").get("id"), pProduitCommandeId));

		/** Select quantiteOF **/
		criteriaQuery.multiselect(vRootCompositionOF.get("quantite"));

		/** query **/
		criteriaQuery.select(vRootCompositionOF).where(whereClause.toArray(new Predicate[] {}));

		/** CompositionOF résultante **/
		List<CompositionOfEntite> resultatCompositionEntite = this.entityManager.createQuery(criteriaQuery)
				.getResultList();

		/** QuantiteOF **/
		if (resultatCompositionEntite != null) {
			//LOGGER.info("** QuantiteOF size: " + resultatCompositionEntite.size());
			for (int x = 0; x < resultatCompositionEntite.size(); x++) {

				//LOGGER.info("** Quantite : " + resultatCompositionEntite.get(x).getQuantite());
				somme = somme + resultatCompositionEntite.get(x).getQuantite();
			}

			//LOGGER.info("** Somme  : " + somme);
			quantiteOF = criteriaBuilder.literal(somme);
		}

		return quantiteOF;
	}

	@Override
	public List<PhaseProduitVue> recherchePhaseParIdProduit(Long pIdProduit) {
		if (pIdProduit != null) {
			if (LOGGER.isDebugEnabled()) {
				//logger.debug("Recherche des PhasesProduits à travers un ID Produit=" + pIdProduit);
			}
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			/** entity principale **/
			CriteriaQuery<PhaseProduitEntite> criteriaQuery = criteriaBuilder.createQuery(PhaseProduitEntite.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();

			/************ entity jointure *****************/
			Root<PhaseProduitEntite> vRootPhaseProduit = criteriaQuery.from(PhaseProduitEntite.class);

			/********* Select ***********/
			criteriaQuery.select(vRootPhaseProduit).where(whereClause.toArray(new Predicate[] {}));

			/***************** Predicates *************/
			/* designation Phase */
			// Join<PhaseProduitEntite, PhaseEntite> jointurePcCv =
			// vRootPhaseProduit.join("phase");
			whereClause.add(criteriaBuilder.equal(vRootPhaseProduit.get("produit").get("id"), pIdProduit));

			// idProduit, Prix, Devise, idPhase, designationPhase
			criteriaQuery.multiselect(vRootPhaseProduit.get("produit").get("id"), vRootPhaseProduit.get("prix"),
					vRootPhaseProduit.get("devise"), vRootPhaseProduit.get("eb_phase_id"));
			// ,
			// jointurePcCv.get("designation"));

			criteriaQuery.select(vRootPhaseProduit).where(whereClause.toArray(new Predicate[] {}));
			List<PhaseProduitEntite> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

			if (resultatEntite != null) {
				//LOGGER.info("** ResultatEntite : " + resultatEntite.size());
				if (resultatEntite.size() != 0) {
					//LOGGER.info("** Rstl[0] IdProduit : " + resultatEntite.get(0).getProduit().getId());
					//LOGGER.info("** Rstl[0] Prix : " + resultatEntite.get(0).getPrix());
					//LOGGER.info("** Rstl[0] devise : " + resultatEntite.get(0).getDevise());
					// //LOGGER.info("** Rstl[0] PhaseId : " +
					// resultatEntite.get(0).getPhase().getId());
					// //LOGGER.info("** Rstl[0] Designation : " +
					// resultatEntite.get(0).getPhase().getDesignation());
				}
			}

			/** Conversion de la liste en Vue */
			List<PhaseProduitVue> vListePhaseProduitVueResultat = new ArrayList<PhaseProduitVue>();

			for (PhaseProduitEntite vPhaseProduitEntite : resultatEntite) {
				PhaseProduitVue vPhP = new PhaseProduitVue();
				vPhP.setIdProduit(vPhaseProduitEntite.getProduit().getId());
				vPhP.setPrix(vPhaseProduitEntite.getPrix());
				vPhP.setDevise(vPhaseProduitEntite.getDevise());
				// TODO vPhP.setIdPhase(vPhaseProduitEntite.getPhase().getId());
				// TODO
				// vPhP.setDesignation(vPhaseProduitEntite.getPhase().getDesignation());
				vListePhaseProduitVueResultat.add(vPhP);
			}

			return vListePhaseProduitVueResultat;
		} else
			return null;
	}

	@Override
	public List<OrdreFabricationValue> listeOrdreFabrication() {
		List<OrdreFabricationEntite> vListeOrdreFabricationEntite = this.lister(OrdreFabricationEntite.class);
		List<OrdreFabricationValue> vListvOrdreFabricationValues = new ArrayList<OrdreFabricationValue>();
		if (LOGGER.isDebugEnabled()) {
			//logger.debug("Lister tous les ordres de fabrication. Size : " + vListvOrdreFabricationValues.size());
		}
		for (OrdreFabricationEntite vOrdreFabricationEntite : vListeOrdreFabricationEntite) {
			vListvOrdreFabricationValues.add(PersistanceUtilities.ToValue(vOrdreFabricationEntite));
		}
		//LOGGER.info("** ResultatValues : " + vListvOrdreFabricationValues.size());

		return vListvOrdreFabricationValues;
	}

	@Override
	public ResultatMulticritereOrdreFabricationValue rechercherOrdreFabricationMultiCritere(
			RechercheMulticritereOrdreFabricationValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<OrdreFabricationEntite> root = criteriaQuery.from(OrdreFabricationEntite.class);

		// Set request.numero on whereClause if not null
		if (estNonVide(request.getvNumero())) {
			
           Expression<String> path=root.get(PREDICATE_NUMERO);
			
			
			//whereClause.add(criteriaBuilder.equal(criteriaBuilder.upper(path), numero));

			whereClause.add(criteriaBuilder.like(criteriaBuilder.upper(path),
					request.getvNumero().toUpperCase()));
			
			
			
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_NUMERO), request.getvNumero()));
		}

		// Set request.etat on whereClause if not null
		if (estNonVide(request.getvEtat())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_ETAT), request.getvEtat()));
		}

		// Set request.dateIntroductionDu on whereClause if not null
		if (request.getvDateIntroductionDu() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_INTRODUCTION),
					request.getvDateIntroductionDu()));
		}

		// Set request.dateIntroductionAu on whereClause if not null
		if (request.getvDateIntroductionAu() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_INTRODUCTION),
					request.getvDateIntroductionAu()));
		}

		// Set request.dateLivraisonDu on whereClause if not null
		if (request.getDateLivraisonDu() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_LIVRAISON),
					request.getDateLivraisonDu()));
		}

		// Set request.dateLivraisonA on whereClause if not null
		if (request.getDateLivraisonTo() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_LIVRAISON),
					request.getDateLivraisonTo()));
		}

		// Set request.clientId on whereClause if not null
		if (request.getClientId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT_ID), request.getClientId()));
		}

		// Set request.produitId on whereClause if not null
		if (request.getProduitId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT_ID), request.getProduitId()));
		}
		
		if (request.getStatut()!=null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_ETAT), request.getStatut()));
		}
		
		
		// Quantite Coupée 

		if (estNonVide(request.getEtatCoupe())) {

			switch (request.getEtatCoupe()) {
			
			case "0": 							
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_QTE_COUPE), 0));								
				break;

			
			case "+": 
				whereClause.add(criteriaBuilder.gt(root.<Long>get(PREDICATE_QTE_COUPE), root.<Long>get(PREDICATE_QTE)));
				break;
			
			case "-": 
				whereClause.add(criteriaBuilder.lt(root.<Long>get(PREDICATE_QTE_COUPE), root.<Long>get(PREDICATE_QTE)));
				
				break;
			
			case "=": 
				whereClause.add(criteriaBuilder.equal(root.<Long>get(PREDICATE_QTE_COUPE), root.<Long>get(PREDICATE_QTE)));
				break;

			}		

		}
		
		
		
		
		if (estNonVide(request.getEtatEngagement())) {

			switch (request.getEtatEngagement()) {
			
			case "0": 							
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_QTE_ENG), 0));								
				break;

			
			case "+": 
				whereClause.add(criteriaBuilder.gt(root.<Long>get(PREDICATE_QTE_ENG), root.<Long>get(PREDICATE_QTE)));
				break;
			
			case "-": 
				whereClause.add(criteriaBuilder.lt(root.<Long>get(PREDICATE_QTE_ENG), root.<Long>get(PREDICATE_QTE)));
				
				break;
			
			case "=": 
				whereClause.add(criteriaBuilder.equal(root.<Long>get(PREDICATE_QTE_ENG), root.<Long>get(PREDICATE_QTE)));
				break;

			}		

		}
		
		
		
		// Quantite Sortie Chaine 
		
		if (estNonVide(request.getEtatFabrication())) {

			switch (request.getEtatFabrication()) {
			
			case "0": 							
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_QTE_FABRICATION), 0));								
				break;

			
			case "+": 
				whereClause.add(criteriaBuilder.gt(root.<Long>get(PREDICATE_QTE_FABRICATION), root.<Long>get(PREDICATE_QTE)));
				break;
			
			case "-": 
				whereClause.add(criteriaBuilder.lt(root.<Long>get(PREDICATE_QTE_FABRICATION), root.<Long>get(PREDICATE_QTE)));
				
				break;
			
			case "=": 
				whereClause.add(criteriaBuilder.equal(root.<Long>get(PREDICATE_QTE_FABRICATION), root.<Long>get(PREDICATE_QTE)));
				break;

			}		

		}
		
		
		// Sortie Finition 
		
		if (estNonVide(request.getEtatConditionnement())) {

			switch (request.getEtatConditionnement()) {
			
			case "0": 							
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_QTE_CONDITIONNEMENT), 0));								
				break;

			
			case "+": 
				whereClause.add(criteriaBuilder.gt(root.<Long>get(PREDICATE_QTE_CONDITIONNEMENT), root.<Long>get(PREDICATE_QTE)));
				break;
			
			case "-": 
				whereClause.add(criteriaBuilder.lt(root.<Long>get(PREDICATE_QTE_CONDITIONNEMENT), root.<Long>get(PREDICATE_QTE)));
				
				break;
			
			case "=": 
				whereClause.add(criteriaBuilder.equal(root.<Long>get(PREDICATE_QTE_CONDITIONNEMENT), root.<Long>get(PREDICATE_QTE)));
				break;

			}		

		}
		
		
		// Colisage 
		
		if (estNonVide(request.getEtatCollisage())) {

			switch (request.getEtatCollisage()) {
			
			case "0": 							
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_QTE_COLLISAGE), 0));								
				break;

			
			case "+": 
				whereClause.add(criteriaBuilder.gt(root.<Long>get(PREDICATE_QTE_COLLISAGE), root.<Long>get(PREDICATE_QTE)));
				break;
			
			case "-": 
				whereClause.add(criteriaBuilder.lt(root.<Long>get(PREDICATE_QTE_COLLISAGE), root.<Long>get(PREDICATE_QTE)));
				
				break;
			
			case "=": 
				whereClause.add(criteriaBuilder.equal(root.<Long>get(PREDICATE_QTE_COLLISAGE), root.<Long>get(PREDICATE_QTE)));
				break;

			}		

		}

		
		
		// Expedition 
		
		if (estNonVide(request.getEtatExpedition())) {

			switch (request.getEtatExpedition()) {
			
			case "0": 							
				whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_QTE_EXPEDITION), 0));								
				break;

			
			case "+": 
				whereClause.add(criteriaBuilder.gt(root.<Long>get(PREDICATE_QTE_EXPEDITION), root.<Long>get(PREDICATE_QTE)));
				break;
			
			case "-": 
				whereClause.add(criteriaBuilder.lt(root.<Long>get(PREDICATE_QTE_EXPEDITION), root.<Long>get(PREDICATE_QTE)));
				
				break;
			
			case "=": 
				whereClause.add(criteriaBuilder.equal(root.<Long>get(PREDICATE_QTE_EXPEDITION), root.<Long>get(PREDICATE_QTE)));
				break;

			}		

		}
		
		
		
		
		
		
		
		
		
		
		criteriaQuery.select(criteriaBuilder.array(
				root.get("id").as(Long.class),
				root.get("numero").as(String.class),
				root.get("partieInterresId").as(Long.class),
				root.get("dateIntroduction").as(Calendar.class),
				root.get("dateLivraison").as(Calendar.class),
				root.get("produitId").as(Long.class),
				root.get("etat").as(Long.class),
				root.get("quantite").as(Long.class) ,
				root.get("qtCoupe").as(Long.class), 
				root.get("qtEngagement").as(Long.class), 
				root.get("qtSortie").as(Long.class),
				root.get("qtFinition").as(Long.class),
				root.get("qtColisage").as(Long.class),
				root.get("qtExpedition").as(Long.class)
			
				
				
				
				
				
				)
				).where(whereClause.toArray(new Predicate[] {}));

		List<Object[]> resultatEntite = null;
		
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

		//If criterias are empty
		if(request.isOptimized()){
			resultatEntite = this.entityManager
					.createQuery(criteriaQuery)
					.setMaxResults(MAX_RESULTS)
					.getResultList();

		}else{
			resultatEntite = this.entityManager
					.createQuery(criteriaQuery)
					.getResultList();
		}
		
		List<OrdreFabricationValue> vListeResultat = new ArrayList<OrdreFabricationValue>();

		for (Object[] element : resultatEntite) {
			
			OrdreFabricationEntite vOrdreFabricationEntite = new OrdreFabricationEntite();
			
			vOrdreFabricationEntite.setId((Long) element[0]);
			vOrdreFabricationEntite.setNumero((String) element[1]);
			vOrdreFabricationEntite.setPartieInterresId((Long) element[2]);
			vOrdreFabricationEntite.setDateIntroduction((Calendar) element[3]);
			vOrdreFabricationEntite.setDateLivraison((Calendar) element[4]);
			vOrdreFabricationEntite.setProduitId((Long) element[5]);
			vOrdreFabricationEntite.setEtat((Long) element[6]);
			vOrdreFabricationEntite.setQuantite((Long) element[7]);
			vOrdreFabricationEntite.setQtCoupe((Long) element[8]);
			vOrdreFabricationEntite.setQtEngagement((Long) element[9]);
			vOrdreFabricationEntite.setQtSortie((Long) element[10]);
			vOrdreFabricationEntite.setQtFinition((Long) element[11]);
			vOrdreFabricationEntite.setQtColisage((Long) element[12]);
			vOrdreFabricationEntite.setQtExpedition((Long) element[13]);
			
			
			
			
			OrdreFabricationValue vOrd = PersistanceUtilities.ToValue(vOrdreFabricationEntite);
			vListeResultat.add(vOrd);
		}

		Collections.sort(vListeResultat);

		ResultatMulticritereOrdreFabricationValue result = new ResultatMulticritereOrdreFabricationValue();

		result.setNombreResultaRechercher(Long.valueOf(vListeResultat.size()));
		result.setOrdreFabricationValues(new TreeSet<>(vListeResultat));

		return result;

	}

	@Override
	public List<OrdreFabricationValue> getAllInList(List<Long> ids) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<OrdreFabricationEntite> criteriaQuery = criteriaBuilder.createQuery(OrdreFabricationEntite.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<OrdreFabricationEntite> root = criteriaQuery.from(OrdreFabricationEntite.class);

		if (ids.size() > 0) {

			whereClause.add(root.get(PREDICATE_ID).in(ids));
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

		List<OrdreFabricationEntite> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<OrdreFabricationValue> result = new ArrayList<OrdreFabricationValue>();

		for (OrdreFabricationEntite entity : entityListResult) {
			OrdreFabricationValue value = PersistanceUtilities.ToValue(entity);
			result.add(value);
		}

		return result;

	}

	@Override
	public List<OrdreFabricationValue> getAllNotInList(List<Long> ids) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<OrdreFabricationEntite> criteriaQuery = criteriaBuilder.createQuery(OrdreFabricationEntite.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<OrdreFabricationEntite> root = criteriaQuery.from(OrdreFabricationEntite.class);

		if (ids.size() > 0) {

			whereClause.add(root.get(PREDICATE_ID).in(ids).not());
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

		List<OrdreFabricationEntite> entityListResult = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<OrdreFabricationValue> result = new ArrayList<OrdreFabricationValue>();

		for (OrdreFabricationEntite entity : entityListResult) {
			OrdreFabricationValue value = PersistanceUtilities.ToValue(entity);
			result.add(value);
		}

		return result;

	}

	@Override
	public OrdreFabricationValue getByNumero(String numero) {

		if (estNonVide(numero)) {

			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<OrdreFabricationEntite> criteriaQuery = criteriaBuilder
					.createQuery(OrdreFabricationEntite.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<OrdreFabricationEntite> root = criteriaQuery.from(OrdreFabricationEntite.class);

			
			Expression<String> path=root.get(PREDICATE_NUMERO);
			
			
			//whereClause.add(criteriaBuilder.equal(criteriaBuilder.upper(path), numero));

			whereClause.add(criteriaBuilder.like(criteriaBuilder.upper(path),
			    	numero.toUpperCase()));
			
			
			
			
			criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

			List<OrdreFabricationEntite> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

			if (resultatEntite != null)
				if (resultatEntite.size() > 0)
					return PersistanceUtilities.ToValue(resultatEntite.get(0));
		}

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

	@Override
	public String getNumOfParId(Long pOrdreFabricationId) {

		String numOF = null;

		if (pOrdreFabricationId != null) {
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<OrdreFabricationEntite> root = criteriaQuery.from(OrdreFabricationEntite.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_ID), pOrdreFabricationId));

			/********* Select ***********/
			criteriaQuery.select(root.get("numero").as(String.class)).where(whereClause.toArray(new Predicate[] {}));
			numOF = this.entityManager.createQuery(criteriaQuery).getSingleResult();

		}

		return numOF;

	}

	@Override
	public OrdreFabricationValue getOFWithSomeColumns(Long pOrdreFabricationId) {

		String numOF = null;

		if (pOrdreFabricationId != null) {
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<OrdreFabricationEntite> root = criteriaQuery.from(OrdreFabricationEntite.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_ID), pOrdreFabricationId));

			/********* Select ***********/
			criteriaQuery.select(
					criteriaBuilder.array(root.get("numero").as(String.class), root.get("quantite").as(Long.class),
							root.get("produitId").as(Long.class), root.get("partieInterresId").as(Long.class)));
			criteriaQuery.where(whereClause.toArray(new Predicate[] {}));

			Object[] resultat = this.entityManager.createQuery(criteriaQuery).getSingleResult();

			if (resultat != null) {
				OrdreFabricationValue OF = new OrdreFabricationValue(pOrdreFabricationId, (String) resultat[0],
						(Long) resultat[1], (Long) resultat[2], (Long) resultat[3]);

				return OF;
			}

		}

		return null;
	}

	
	@Override
	public Long getProduitIdByOFId(Long OFId) {
		Long produitId = null;

		if (OFId != null) {
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<OrdreFabricationEntite> root = criteriaQuery.from(OrdreFabricationEntite.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_ID), OFId));

			/********* Select ***********/
			criteriaQuery.select(root.get("produitId").as(Long.class)).where(whereClause.toArray(new Predicate[] {}));
			List<Long> results = this.entityManager.createQuery(criteriaQuery).getResultList();
			
			if(!results.isEmpty()){
				produitId = results.get(0);
			}

		}

		return produitId;

	}
	
	@Override
	public OrdreFabricationValue getNumeroOF(Long id){
		
		if (id != null) {
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<OrdreFabricationEntite> root = criteriaQuery.from(OrdreFabricationEntite.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_ID), id));

			/********* Select ***********/
			criteriaQuery.select(criteriaBuilder.array(
					root.get("numero").as(String.class),
					root.get("produitId").as(String.class)	
						)).where(whereClause.toArray(new Predicate[] {}));
			List<Object[]> results = this.entityManager.createQuery(criteriaQuery).getResultList();
			
			if(!results.isEmpty()){
				OrdreFabricationValue value = new OrdreFabricationValue();
				value.setNumero((String) results.get(0)[0]);
				value.setProduitId(Long.parseLong(results.get(0)[1].toString()));
				return value;
			}

		}

		return null;
		
	}

	@Override
	public boolean numOFExistence(String numOF) {
		  CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < String > vCriteriaQuery = vBuilder.createQuery(String.class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();

		    /** create liste resyltat ***/

		    /************ entity jointure *****************/
		    Root < OrdreFabricationEntite > vRootArticle = vCriteriaQuery.from(OrdreFabricationEntite.class);

		    /***************** Predicate *************/
		    if (estNonVide(numOF)) {
		    	vWhereClause.add(vBuilder.equal(vRootArticle.get(PREDICATE_NUMERO), numOF));		      
		    }
		
		    vCriteriaQuery.select(
		    		vRootArticle.get("numero").as(String.class)	
					).where(vWhereClause.toArray(new Predicate[] {}));
		    
		    List<String> result = this.entityManager.createQuery(vCriteriaQuery).getResultList();
      
		if(!result.isEmpty()){
			return true;
		}
		
		return false;
	}
	@Override
	public Long calculeQteProduiteJours(Long of ,Calendar dateDebut  ,Calendar dateFin , Long op ) {
		Long vQteProduiteJours = new Long(0);

		StoredProcedureQuery storedProcedure = entityManager
				.createStoredProcedureQuery("get_qte_produite_jours");
		// set parameters
		storedProcedure.registerStoredProcedureParameter("of", Long.class,ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("dateDebut", Date.class,ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("dateFin", Date.class,ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("op", Long.class,ParameterMode.IN);
		// storedProcedure.registerStoredProcedureParameter("tax", Double.class,
		// ParameterMode.OUT);
		storedProcedure.setParameter("of", of);
		storedProcedure.setParameter("dateDebut", dateDebut.getTime());
		storedProcedure.setParameter("dateFin", dateFin.getTime());
		storedProcedure.setParameter("op", op);
		// execute SP
		storedProcedure.execute();
		// get result
		if(storedProcedure.getSingleResult()!=null)
		{
		BigInteger result =(BigInteger) storedProcedure.getSingleResult();	
		vQteProduiteJours = result.longValue();
		//System.out.println("Persisttt  vQteProduiteJours :   "+vQteProduiteJours);
		}
		return vQteProduiteJours;

	}

	@Override
	public Long CalculeQteProduiteCumul(Long of, Long op) {
		Long vQteProduiteCumul = new Long(0);

		StoredProcedureQuery storedProcedure = entityManager
				.createStoredProcedureQuery("get_qte_produite_cumul");
		// set parameters
		storedProcedure.registerStoredProcedureParameter("of", Long.class,ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter("op", Long.class,ParameterMode.IN);
		// storedProcedure.registerStoredProcedureParameter("tax", Double.class,
		// ParameterMode.OUT);
		storedProcedure.setParameter("of", of);
		storedProcedure.setParameter("op", op);
		// execute SP
		storedProcedure.execute();
		// get result
		if(storedProcedure.getSingleResult()!=null)
		{BigInteger result =(BigInteger) storedProcedure.getSingleResult();
		vQteProduiteCumul = result.longValue();
        //System.out.println("Persisttt  vQteProduiteCumul :   "+vQteProduiteCumul);
		}
        return vQteProduiteCumul;
	}
	
	@Override
	public ResultatMulticritereOrdreFabricationValue rechercherOrdreFabricationMultiCritereGlobale(
			RechercheMulticritereOrdreFabricationValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<OrdreFabricationEntite> criteriaQuery = criteriaBuilder.createQuery(OrdreFabricationEntite.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<OrdreFabricationEntite> root = criteriaQuery.from(OrdreFabricationEntite.class);

		// Set request.numero on whereClause if not null
		if (estNonVide(request.getvNumero())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_NUMERO), request.getvNumero()));
		}

		// Set request.etat on whereClause if not null
		if (estNonVide(request.getvEtat())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_ETAT), request.getvEtat()));
		}

		// Set request.dateIntroductionDu on whereClause if not null
		if (request.getvDateIntroductionDu() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_INTRODUCTION),
					request.getvDateIntroductionDu()));
		}

		// Set request.dateIntroductionAu on whereClause if not null
		if (request.getvDateIntroductionAu() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_INTRODUCTION),
					request.getvDateIntroductionAu()));
		}

		// Set request.dateLivraisonDu on whereClause if not null
		if (request.getDateLivraisonDu() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_LIVRAISON),
					request.getDateLivraisonDu()));
		}

		// Set request.dateLivraisonA on whereClause if not null
		if (request.getDateLivraisonTo() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_LIVRAISON),
					request.getDateLivraisonTo()));
		}

		// Set request.clientId on whereClause if not null
		if (request.getClientId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT_ID), request.getClientId()));
		}

		// Set request.produitId on whereClause if not null
		if (request.getProduitId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT_ID), request.getProduitId()));
		}
		
		
		if (request.getStatut()!=null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_ETAT), request.getStatut()));
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

		List<OrdreFabricationEntite> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<OrdreFabricationValue> vListeResultat = new ArrayList<OrdreFabricationValue>();

		for (OrdreFabricationEntite vOrdreFabricationEntite : resultatEntite) {
			OrdreFabricationValue vOrd = PersistanceUtilities.ToValue(vOrdreFabricationEntite);
			vListeResultat.add(vOrd);
		}

		Collections.sort(vListeResultat);

		ResultatMulticritereOrdreFabricationValue result = new ResultatMulticritereOrdreFabricationValue();

		result.setNombreResultaRechercher(Long.valueOf(vListeResultat.size()));
		result.setOrdreFabricationValues(new TreeSet<>(vListeResultat));

		return result;

	}
	@Override
	public List<OrdreFabricationValue> listerOFTOCommercial() {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<OrdreFabricationEntite> criteriaQuery = criteriaBuilder.createQuery(OrdreFabricationEntite.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<OrdreFabricationEntite> root = criteriaQuery.from(OrdreFabricationEntite.class);
        whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Long>get(PREDICATE_ETAT),5L));
        

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));

		List<OrdreFabricationEntite> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<OrdreFabricationValue> vListeResultat = new ArrayList<OrdreFabricationValue>();

		for (OrdreFabricationEntite vOrdreFabricationEntite : resultatEntite) {
			OrdreFabricationValue vOrd = PersistanceUtilities.ToValue(vOrdreFabricationEntite);
			vListeResultat.add(vOrd);
		}

		Collections.sort(vListeResultat);

		
		return vListeResultat;

	}
	
	@Override
	public Long getIdOfParNum(String pOrdreFabricationNum) {

		Long numOF = null;

		if (pOrdreFabricationNum != null) {
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
			List<Predicate> whereClause = new ArrayList<Predicate>();
			Root<OrdreFabricationEntite> root = criteriaQuery.from(OrdreFabricationEntite.class);

			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_NUMERO), pOrdreFabricationNum));
			
			
			
			
			criteriaQuery.select(criteriaBuilder.array(
					root.get("id").as(Long.class)
										)
					).where(whereClause.toArray(new Predicate[] {}));

			List<Object[]> resultatEntite = null;
			
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

			
				resultatEntite = this.entityManager
						.createQuery(criteriaQuery)
						.getResultList();
			
			
			List<OrdreFabricationEntite> vListeResultat = new ArrayList<OrdreFabricationEntite>();

			for (Object[] element : resultatEntite) {
				
				OrdreFabricationEntite vOrdreFabricationEntite = new OrdreFabricationEntite();
				
				vOrdreFabricationEntite.setId((Long) element[0]);
				
				//OrdreFabricationValue vOrd = PersistanceUtilities.ToValue(vOrdreFabricationEntite);
				vListeResultat.add(vOrdreFabricationEntite);
			}
			
			  if (vListeResultat!=null && vListeResultat.size()>0)
              numOF=vListeResultat.get(0).getId();			
		}

		return numOF;

	}
	
	
}
