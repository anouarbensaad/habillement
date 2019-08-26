package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.DetailSaisieElementValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.RechercheMulticritereDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.ResultatRechecheDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.persitance.entite.OrdreFabricationEntite;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IDetailSaisiePersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.FeuilleSaisieEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.PersonnelEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.SaisieElementEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.utility.FeuilleSaisiePersistanceUtility;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.entity.ElementGammeOfEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.entity.GammeOfEntity;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class DetailSaisiePersistanceImpl extends AbstractPersistance implements IDetailSaisiePersistance {

	private static final Logger logger = LoggerFactory.getLogger(DetailSaisiePersistanceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	private String PREDICATE_PAQUET = "paquetId";
	private String JOIN_FICHE_SAISIE = "fiheSaisie";
	private String JOIN_PERSONNEL = "personnel";
	private String COLUMN_MATRICULE = "matricule";
	private String PREDICATE_DATE_SAISIE = "dateSaisie";
	private String COLUMN_ORDRE_FABRICATION_ID = "idOF";
	private String COLUMN_ELEMENT_GAMME_OF_ID = "elementGammeId";
	private String COLUMN_OPERATION = "idOperation";
	private String COLUMN_CHAINE = "chaineId";

	private String PREDICATE_ORDRE = "ordre";
	
	
	private int MAX_RESULTS = 39;

	private String PREDICATE_CODE = "codeBarre";
	
	private String PREDICATE_PRODUIT = "idProduit";
	private String PREDICATE_CLIENT = "idClient";
	
	
	
	
	@Autowired
	private FeuilleSaisiePersistanceUtility feuilleSaisiePersistanceUtility;

	public ResultatRechecheDetailSaisieValue rechercherMultiCritere(RechercheMulticritereDetailSaisieValue request) {
		//LOGGER.info("rechercheMulticritere");

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<SaisieElementEntity> criteriaQuery = criteriaBuilder.createQuery(SaisieElementEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<SaisieElementEntity> root = criteriaQuery.from(SaisieElementEntity.class);

		// Set request.matricule on whereClause if not null
		if (estNonVide(request.getMatricule())) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureArtSousFm = root.join(JOIN_FICHE_SAISIE);
			Join<FeuilleSaisieEntity, PersonnelEntity> jointureSFmFamille = jointureArtSousFm.join(JOIN_PERSONNEL);
			whereClause.add(criteriaBuilder.equal(jointureSFmFamille.get(COLUMN_MATRICULE), request.getMatricule()));
		}

		// Set request.dateSaisieMin on whereClause if not null
		if (request.getDateSaisieMin() != null) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureSaisieFeuilleSaisieMin = root
					.join(JOIN_FICHE_SAISIE);
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(
					jointureSaisieFeuilleSaisieMin.<Calendar> get(PREDICATE_DATE_SAISIE), request.getDateSaisieMin()));
		}

		// Set request.dateSaisieMax on whereClause if not null
		if (request.getDateSaisieMax() != null) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureSaisieFeuilleSaisieMax = root
					.join(JOIN_FICHE_SAISIE);
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(
					jointureSaisieFeuilleSaisieMax.<Calendar> get(PREDICATE_DATE_SAISIE), request.getDateSaisieMax()));
		}

		// Set request.paquetId on whereClause if not null
		if (estNonVide(request.getPaquetId())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_ORDRE), request.getPaquetId()));
		}

		// Set request.elementGameOfId on whereClause if not null
		if (request.getElementGammeOfId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_ELEMENT_GAMME_OF_ID), request.getElementGammeOfId()));
		}

		// Set request.ofId on whereClause if not null
		if (request.getOfId() != null) {
		    
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_ORDRE_FABRICATION_ID), request.getOfId()));

		}

		// Set request.operationId on whereClause if not null
		if (request.getOperation() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_OPERATION), request.getOperation()));

		}

		// Set request.matricule on whereClause if not null
		if (request.getChaineId() != null) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureArtSousFm = root.join(JOIN_FICHE_SAISIE);
			whereClause.add(criteriaBuilder.equal(jointureArtSousFm.get(COLUMN_CHAINE), request.getChaineId()));
		}
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));


		List <SaisieElementEntity> resultatEntite = null;
				
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
				
		List<DetailSaisieElementValue> list = new ArrayList<DetailSaisieElementValue>();

		for (SaisieElementEntity entity : resultatEntite) {
			DetailSaisieElementValue dto = feuilleSaisiePersistanceUtility.toDetailSaisieElementValue(entity);
			list.add(dto);
		}

		ResultatRechecheDetailSaisieValue result = new ResultatRechecheDetailSaisieValue();
		result.setNombreResultaRechercher(Long.valueOf(new TreeSet<>(list).size()));
		result.setList(new TreeSet<>(list));

		return result;
	}

	public List<DetailSaisieElementValue> getListDetailsSaisieAllegee(RechercheMulticritereDetailSaisieValue request) {
		//LOGGER.info("rechercheMulticritere");

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<SaisieElementEntity> root = criteriaQuery.from(SaisieElementEntity.class);

		// Set request.matricule on whereClause if not null
		if (estNonVide(request.getMatricule())) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureArtSousFm = root.join(JOIN_FICHE_SAISIE);
			Join<FeuilleSaisieEntity, PersonnelEntity> jointureSFmFamille = jointureArtSousFm.join(JOIN_PERSONNEL);
			whereClause.add(criteriaBuilder.equal(jointureSFmFamille.get(COLUMN_MATRICULE), request.getMatricule()));
		}

		// Set request.dateSaisieMin on whereClause if not null
		if (request.getDateSaisieMin() != null) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureSaisieFeuilleSaisieMin = root
					.join(JOIN_FICHE_SAISIE);
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(
					jointureSaisieFeuilleSaisieMin.<Calendar> get(PREDICATE_DATE_SAISIE), request.getDateSaisieMin()));
		}

		// Set request.dateSaisieMax on whereClause if not null
		if (request.getDateSaisieMax() != null) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureSaisieFeuilleSaisieMax = root
					.join(JOIN_FICHE_SAISIE);
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(
					jointureSaisieFeuilleSaisieMax.<Calendar> get(PREDICATE_DATE_SAISIE), request.getDateSaisieMax()));
		}

		// Set request.paquetId on whereClause if not null
		if (estNonVide(request.getPaquetId())) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PAQUET), request.getPaquetId()));
		}

		// Set request.elementGameOfId on whereClause if not null
		if (request.getElementGammeOfId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_ELEMENT_GAMME_OF_ID), request.getElementGammeOfId()));
		}

		// Set request.ofId on whereClause if not null
		if (request.getOfId() != null) {
			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<GammeOfEntity> subRoot = subQuery.from(GammeOfEntity.class);

			subQuery.select(subRoot.<Long> get("id"));
			subQuery.where(criteriaBuilder.equal(subRoot.get(COLUMN_ORDRE_FABRICATION_ID), request.getOfId()));
			whereClause.add(criteriaBuilder.in(root.get(COLUMN_ELEMENT_GAMME_OF_ID)).value(subQuery));

		}

		// Set request.operationId on whereClause if not null
		if (request.getOperation() != null) {
			Subquery<Long> subQuery = criteriaQuery.subquery(Long.class);
			Root<ElementGammeOfEntity> subRoot = subQuery.from(ElementGammeOfEntity.class);

			subQuery.select(subRoot.<Long> get("id"));
			subQuery.where(criteriaBuilder.equal(subRoot.get(COLUMN_OPERATION), request.getOperation()));
			whereClause.add(criteriaBuilder.in(root.get(COLUMN_ELEMENT_GAMME_OF_ID)).value(subQuery));

		}

		// Set request.matricule on whereClause if not null
		if (request.getChaineId() != null) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureArtSousFm = root.join(JOIN_FICHE_SAISIE);
			whereClause.add(criteriaBuilder.equal(jointureArtSousFm.get(COLUMN_CHAINE), request.getChaineId()));
		}

		criteriaQuery.select(criteriaBuilder.array(root.get("id").as(Long.class),
				root.get("elementGammeId").as(Long.class), root.get("quantite").as(Long.class),
				root.get("fiheSaisie").get("dateSaisie").as(Calendar.class)));

		criteriaQuery.where(whereClause.toArray(new Predicate[] {}));
		List<Object[]> result = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<DetailSaisieElementValue> list = new ArrayList<DetailSaisieElementValue>();

		for (Object[] entity : result) {

			DetailSaisieElementValue element = new DetailSaisieElementValue(
					(Long) entity[0], // Id
					(Long) entity[1], // idGammeOf
					(Long) entity[2], // quantite
					(Calendar) entity[3] // dateSaisie
			);

			list.add(element);
		}

		return list;
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
	public List<DetailSaisieElementValue> rechercherMultiCritere(Long elementGammeOfId, Calendar dateMin,
			Calendar dateMax) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<SaisieElementEntity> root = criteriaQuery.from(SaisieElementEntity.class);

		if (elementGammeOfId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_ELEMENT_GAMME_OF_ID), elementGammeOfId));
		}

		if (dateMin != null) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureSaisieFeuilleSaisieMin = root
					.join(JOIN_FICHE_SAISIE);
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(
					jointureSaisieFeuilleSaisieMin.<Calendar> get(PREDICATE_DATE_SAISIE), dateMax));
		}

		if (dateMax != null) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureSaisieFeuilleSaisieMax = root
					.join(JOIN_FICHE_SAISIE);
			whereClause.add(criteriaBuilder
					.lessThanOrEqualTo(jointureSaisieFeuilleSaisieMax.<Calendar> get(PREDICATE_DATE_SAISIE), dateMax));
		}

		criteriaQuery.select(criteriaBuilder.array(root.get("id").as(Long.class), root.get("quantite").as(Long.class)));

		criteriaQuery.where(whereClause.toArray(new Predicate[] {}));
		List<Object[]> result = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<DetailSaisieElementValue> list = new ArrayList<DetailSaisieElementValue>();

		for (Object[] entity : result) {

			DetailSaisieElementValue element = new DetailSaisieElementValue((Long) entity[0], // Id
					(Long) entity[1] // quantite
			);

			list.add(element);
		}
		return list;
	}

	@Override
	public Long getSommeQteProduite(Long elementGammeOfId, Calendar dateMin, Calendar dateMax) {

		Long sommeQte = 0L;

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<SaisieElementEntity> root = criteriaQuery.from(SaisieElementEntity.class);

		if (elementGammeOfId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_ELEMENT_GAMME_OF_ID), elementGammeOfId));
		}

		if (dateMin != null) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureSaisieFeuilleSaisieMin = root
					.join(JOIN_FICHE_SAISIE);
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(
					jointureSaisieFeuilleSaisieMin.<Calendar> get(PREDICATE_DATE_SAISIE), dateMin));
		}

		if (dateMax != null) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureSaisieFeuilleSaisieMax = root
					.join(JOIN_FICHE_SAISIE);
			whereClause.add(criteriaBuilder
					.lessThanOrEqualTo(jointureSaisieFeuilleSaisieMax.<Calendar> get(PREDICATE_DATE_SAISIE), dateMax));
		}

		criteriaQuery.select(criteriaBuilder.sum(root.get("quantite").as(Long.class)));

		criteriaQuery.where(whereClause.toArray(new Predicate[] {}));
		sommeQte = this.entityManager.createQuery(criteriaQuery).getSingleResult();

		if(sommeQte== null){
			sommeQte=0L;
		}
		return sommeQte;

	}

	
	
	
	@Override
	public Long getSommeQteProduiteForOperationOF(Long ofId,Long operationId, Calendar dateMin, Calendar dateMax) {

		Long sommeQte = 0L;

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<SaisieElementEntity> root = criteriaQuery.from(SaisieElementEntity.class);

		if (operationId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_OPERATION), operationId));
		}

		
		if (ofId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_ORDRE_FABRICATION_ID), ofId));
		}
		
		
		if (dateMin != null) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureSaisieFeuilleSaisieMin = root
					.join(JOIN_FICHE_SAISIE);
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(
					jointureSaisieFeuilleSaisieMin.<Calendar> get(PREDICATE_DATE_SAISIE), dateMin));
		}

		if (dateMax != null) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureSaisieFeuilleSaisieMax = root
					.join(JOIN_FICHE_SAISIE);
			whereClause.add(criteriaBuilder
					.lessThanOrEqualTo(jointureSaisieFeuilleSaisieMax.<Calendar> get(PREDICATE_DATE_SAISIE), dateMax));
		}

		criteriaQuery.select(criteriaBuilder.sum(root.get("quantite").as(Long.class)));

		criteriaQuery.where(whereClause.toArray(new Predicate[] {}));
		sommeQte = this.entityManager.createQuery(criteriaQuery).getSingleResult();

		if(sommeQte== null){
			sommeQte=0L;
		}
		return sommeQte;

	}
	
	
	@Override
	public Long getSommeQteProduiteForOpratationProduit(Long produitId,Long operationId, Calendar dateMin, Calendar dateMax) {

		Long sommeQte = 0L;

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<SaisieElementEntity> root = criteriaQuery.from(SaisieElementEntity.class);

		if (operationId != null) {
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_OPERATION), operationId));
		}

		if (dateMin != null) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureSaisieFeuilleSaisieMin = root
					.join(JOIN_FICHE_SAISIE);
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(
					jointureSaisieFeuilleSaisieMin.<Calendar> get(PREDICATE_DATE_SAISIE), dateMin));
		}

		if (dateMax != null) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureSaisieFeuilleSaisieMax = root
					.join(JOIN_FICHE_SAISIE);
			whereClause.add(criteriaBuilder
					.lessThanOrEqualTo(jointureSaisieFeuilleSaisieMax.<Calendar> get(PREDICATE_DATE_SAISIE), dateMax));
		}

		criteriaQuery.select(criteriaBuilder.sum(root.get("quantite").as(Long.class)));

		criteriaQuery.where(whereClause.toArray(new Predicate[] {}));
		sommeQte = this.entityManager.createQuery(criteriaQuery).getSingleResult();

		if(sommeQte== null){
			sommeQte=0L;
		}
		return sommeQte;

	}
	
	
	public boolean codeExistence(String codeBarre) {
		  CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < String > vCriteriaQuery = vBuilder.createQuery(String.class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();

		    /** create liste resyltat ***/

		    /************ entity jointure *****************/
		    Root < SaisieElementEntity > vRootArticle = vCriteriaQuery.from(SaisieElementEntity.class);

		    /***************** Predicate *************/
		    if (estNonVide(codeBarre)) {
		    	vWhereClause.add(vBuilder.equal(vRootArticle.get(PREDICATE_CODE), codeBarre));		      
		    }
		
		    vCriteriaQuery.select(
		    		vRootArticle.get("codeBarre").as(String.class)	
					).where(vWhereClause.toArray(new Predicate[] {}));
		    
		    List<String> result = this.entityManager.createQuery(vCriteriaQuery).getResultList();
    
		if(!result.isEmpty()){
			return true;
		}
		
		return false;
	}
	
	
	// Recherche pour rapport de Production 
	
	
	
	public List<Long> rechercherMultiCritereOF(RechercheMulticritereDetailSaisieValue request) {
		//LOGGER.info("rechercheMulticritere");

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<SaisieElementEntity> root = criteriaQuery.from(SaisieElementEntity.class);

		// Set request.matricule on whereClause if not null
		if (estNonVide(request.getMatricule())) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureArtSousFm = root.join(JOIN_FICHE_SAISIE);
			Join<FeuilleSaisieEntity, PersonnelEntity> jointureSFmFamille = jointureArtSousFm.join(JOIN_PERSONNEL);
			whereClause.add(criteriaBuilder.equal(jointureSFmFamille.get(COLUMN_MATRICULE), request.getMatricule()));
		}

		// Set request.dateSaisieMin on whereClause if not null
		if (request.getDateSaisieMin() != null) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureSaisieFeuilleSaisieMin = root
					.join(JOIN_FICHE_SAISIE);
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(
					jointureSaisieFeuilleSaisieMin.<Calendar> get(PREDICATE_DATE_SAISIE), request.getDateSaisieMin()));
		}

		// Set request.dateSaisieMax on whereClause if not null
		if (request.getDateSaisieMax() != null) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureSaisieFeuilleSaisieMax = root
					.join(JOIN_FICHE_SAISIE);
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(
					jointureSaisieFeuilleSaisieMax.<Calendar> get(PREDICATE_DATE_SAISIE), request.getDateSaisieMax()));
		}

		

		

	

		// Set request.operationId on whereClause if not null
		if (request.getOperation() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_OPERATION), request.getOperation()));

		}

	
		// Set request.operationId on whereClause if not null
				if (request.getProduitId() != null) {
					whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_PRODUIT), request.getProduitId()));

				}
		

				// Set request.operationId on whereClause if not null
				if (request.getClientId() != null) {
					whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_CLIENT), request.getClientId()));

				}
				
				
				
		// Set request.matricule on whereClause if not null
		if (request.getChaineId() != null) {
			Join<SaisieElementEntity, FeuilleSaisieEntity> jointureArtSousFm = root.join(JOIN_FICHE_SAISIE);
			whereClause.add(criteriaBuilder.equal(jointureArtSousFm.get(COLUMN_CHAINE), request.getChaineId()));
		}
		
		
		
		List <Long>  list =new ArrayList<Long>();
		
		criteriaQuery.select(criteriaBuilder.array(root.get("idOF").as(Long.class), root.get("quantite").as(Long.class)));
		
		

		criteriaQuery.where(whereClause.toArray(new Predicate[] {}));
		List<Object[]> result = this.entityManager.createQuery(criteriaQuery).getResultList();

		//List<DetailSaisieElementValue> list = new ArrayList<DetailSaisieElementValue>();

		for (Object[] entity : result) {

			Long idOF=(Long) entity[0];
			
            if (!list.contains(idOF))
			list.add(idOF);
		}
				


		

		return list;
	}
	
	
	
	
	
	
	
}
