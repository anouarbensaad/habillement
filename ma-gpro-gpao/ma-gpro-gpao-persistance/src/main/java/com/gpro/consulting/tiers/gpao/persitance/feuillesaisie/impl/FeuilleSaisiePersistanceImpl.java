package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.ArticleEntite;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.FeuilleSaisieTRValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.FeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticritereFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechecheFeuilleSaisieElementValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechecheFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IFeuilleSaisiePersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.FeuilleSaisieEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.PersonnelEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.utility.FeuilleSaisiePersistanceUtility;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.entity.GammeOfEntity;

/**
 * Implementation of {@link IFeuilleSaisiePersistance} interface.
 * 
 * @author Wahid Gazzah
 * @since 25/05/2016
 *
 */

@Component
public class FeuilleSaisiePersistanceImpl extends AbstractPersistance implements IFeuilleSaisiePersistance {
	private static final Logger logger = LoggerFactory.getLogger(FeuilleSaisiePersistanceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	private String COLUMN_CHAINE = "chaineId";
	private String COLUMN_MATRICULE = "matricule";
	private String ENTITY_PERSONNEL = "personnel";
	private String PREDICATE_DATE_SAISIE = "dateSaisie";
	private String PREDICATE_RENDEMENT = "rendement";
	private String PREDICATE_ACTIVITE = "activite";
	private String COLUMN_PERIODE = "periode";
	private int MAX_RESULTS = 39;

	private String FS_PERSONNEL_DATE_CHAINE_EXIST_ERROR = "GPAO02";
	 
	@Autowired
	private FeuilleSaisiePersistanceUtility feuilleSaisiePersistanceUtility;

	@Override
	public String create(FeuilleSaisieValue feuilleSaisie) {
		
		/*if(this.personnelDateChaineExistence(
				feuilleSaisie.getPersonnelId(),
				feuilleSaisie.getDateSaisie(),
				feuilleSaisie.getChaineId()
				)){
			return FS_PERSONNEL_DATE_CHAINE_EXIST_ERROR;
		}*/
		
		FeuilleSaisieEntity entity = (FeuilleSaisieEntity) this
				.creer(feuilleSaisiePersistanceUtility.toEntity(feuilleSaisie));
		//LOGGER.info("createFeuilleSaisie:Persistance"+feuilleSaisie);

		return entity.getId().toString();
	}

	@Override
	public FeuilleSaisieValue getById(Long id) {
		FeuilleSaisieEntity entity = this.rechercherParId(id, FeuilleSaisieEntity.class);

		return feuilleSaisiePersistanceUtility.toValue(entity);
	}

	@Override
	public String update(FeuilleSaisieValue feuilleSaisie) {

	/*	if( !feuilleSaisie.getPersonnelMatricule().equals(feuilleSaisie.getPersonnelBeforeUpdate()) |
			!feuilleSaisie.getDateSaisie().equals(feuilleSaisie.getDateBeforeUpdate()) |
			feuilleSaisie.getChaineId() != feuilleSaisie.getChaineBeforeUpdate()
		){
			
			if(this.personnelDateChaineExistence(
				feuilleSaisie.getPersonnelId(),
				feuilleSaisie.getDateSaisie(),
				feuilleSaisie.getChaineId()
				))
				return FS_PERSONNEL_DATE_CHAINE_EXIST_ERROR;
		}*/
		
		FeuilleSaisieEntity entity = (FeuilleSaisieEntity) this
				.modifier(feuilleSaisiePersistanceUtility.toEntity(feuilleSaisie));

		return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		this.supprimerEntite(FeuilleSaisieEntity.class, id.longValue());
	}

	@Override
	public ResultatRechecheFeuilleSaisieValue rechercherMultiCritere(RechercheMulticritereFeuilleSaisieValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<FeuilleSaisieEntity> criteriaQuery = criteriaBuilder.createQuery(FeuilleSaisieEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<FeuilleSaisieEntity> root = criteriaQuery.from(FeuilleSaisieEntity.class);

		// Set request.dateSaisieMin on whereClause if not null
		if (request.getDateSaisieMin() != null) {
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_SAISIE),
					request.getDateSaisieMin()));
		}

		// Set request.dateSaisieMax on whereClause if not null
		if (request.getDateSaisieMax() != null) {
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.<Calendar> get(PREDICATE_DATE_SAISIE),
					request.getDateSaisieMax()));
		}

		// Set request.chaineId on whereClause if not null
		if (request.getChaineId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_CHAINE), request.getChaineId()));
		}

		// Set request.matricule on whereClause if valid String value
		if (estNonVide(request.getMatricule())) {
			whereClause.add(
					criteriaBuilder.equal(root.get(ENTITY_PERSONNEL).get(COLUMN_MATRICULE), request.getMatricule()));
		}

		// Set request.rendementMin et request.rendementMax on whereClause if
		// valid String value
		if (request.getRendementMin() != null && request.getRendementMax() != null) {
			whereClause.add(criteriaBuilder.between(root.<Double> get(PREDICATE_RENDEMENT), request.getRendementMin(),
					request.getRendementMax()));
		}

		// Set request.activiteMin et request.activitetMax on whereClause if
		// valid String value
		if (request.getActiviteMin() != null && request.getActiviteMax() != null) {
			whereClause.add(criteriaBuilder.between(root.<Double> get(PREDICATE_ACTIVITE), request.getActiviteMin(),
					request.getActiviteMax()));
		}
		// Set request.matricule on whereClause if valid String value
				if (estNonVide(request.getPeriode())) {
					whereClause.add(
							criteriaBuilder.equal(root.get(COLUMN_PERIODE), request.getPeriode()));
				}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));


		List <FeuilleSaisieEntity> resultatEntite = null;
				
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
				

		List<ResultatRechecheFeuilleSaisieElementValue> list = new ArrayList<ResultatRechecheFeuilleSaisieElementValue>();

		for (FeuilleSaisieEntity entity : resultatEntite) {

			ResultatRechecheFeuilleSaisieElementValue dto = feuilleSaisiePersistanceUtility
					.toResultatRechecheFicheSaisieElementValue(entity);
			list.add(dto);
		}

		ResultatRechecheFeuilleSaisieValue result = new ResultatRechecheFeuilleSaisieValue();
		result.setNombreResultaRechercher(Long.valueOf(list.size()));
		result.setList(new TreeSet<>(list));

		return result;
	}

	@Override
	public List<PersonnelValue> listPersonnel() {

		List<PersonnelEntity> listEntity = this.lister(PersonnelEntity.class);

		return feuilleSaisiePersistanceUtility.toValue(listEntity);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private boolean estNonVide(String val) {

		return val != null && !"".equals(val);
	}

	@Override
	public FeuilleSaisieValue checkExistence(FeuilleSaisieValue feuilleSaisie) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<FeuilleSaisieEntity> criteriaQuery = criteriaBuilder.createQuery(FeuilleSaisieEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<FeuilleSaisieEntity> root = criteriaQuery.from(FeuilleSaisieEntity.class);

		if (feuilleSaisie.getChaineId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(COLUMN_CHAINE),
					feuilleSaisie.getChaineId() ));
		}

		if (feuilleSaisie.getPersonnelId() != null) {
			whereClause.add(criteriaBuilder.equal(root.get(ENTITY_PERSONNEL).get("id"),
					feuilleSaisie.getPersonnelId()));
		}

		// Set request.chaineId on whereClause if not null
		if (feuilleSaisie.getDateSaisie()!= null) {
			whereClause.add(criteriaBuilder.equal(root.get(PREDICATE_DATE_SAISIE), feuilleSaisie.getDateSaisie()));
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		List<FeuilleSaisieEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		FeuilleSaisieValue result = new FeuilleSaisieValue();
		if(resultatEntite.size() != 0) {

			result = this.feuilleSaisiePersistanceUtility.toValue(resultatEntite.get(0));
			return result;
		}
		
		return null;
	}

	@Override
	  public boolean personnelDateChaineExistence(Long personnelId, Calendar date, Long chaineId){
		  CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		    /** entity principale **/
		    CriteriaQuery < Long > vCriteriaQuery = vBuilder.createQuery(Long.class);
		    List < Predicate > vWhereClause = new ArrayList < Predicate >();

		    /** create liste resyltat ***/

		    /************ entity jointure *****************/
		    Root < FeuilleSaisieEntity > vRootArticle = vCriteriaQuery.from(FeuilleSaisieEntity.class);

		    /***************** Predicate *************/
		    if ( personnelId != null && date != null && chaineId != null) {
		    	vWhereClause.add(vBuilder.equal(vRootArticle.get(PREDICATE_DATE_SAISIE), date));
		    	vWhereClause.add(vBuilder.equal(vRootArticle.get(ENTITY_PERSONNEL).get("id"), personnelId));		      
		    	vWhereClause.add(vBuilder.equal(vRootArticle.get(COLUMN_CHAINE), chaineId));		      
		    }
		
		    vCriteriaQuery.select(
		    		vRootArticle.get("id").as(Long.class)	
					).where(vWhereClause.toArray(new Predicate[] {}));
		    
		    List<Long> result = this.entityManager.createQuery(vCriteriaQuery).getResultList();
      
		if(!result.isEmpty()){
			return true;
		}
		
		return false;
	  }

	@Override
	public Calendar getDateSaisie(Long feuilleId) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Calendar> criteriaQuery = criteriaBuilder.createQuery(Calendar.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<FeuilleSaisieEntity> root = criteriaQuery.from(FeuilleSaisieEntity.class);

		// Set request.dateSaisieMin on whereClause if not null
		if (feuilleId != null) {
			whereClause.add(criteriaBuilder.equal( root.get("id"),feuilleId));
		}

		 criteriaQuery.select(
		    		root.get("dateSaisie").as(Calendar.class)	
					).where(whereClause.toArray(new Predicate[] {}));


		List<Calendar> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
				
				
		if(!resultatEntite.isEmpty()){
			return resultatEntite.get(0);
		}
		
		return null;
	}

	@Override
	public Long getChaineId(Long feuilleId) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<FeuilleSaisieEntity> root = criteriaQuery.from(FeuilleSaisieEntity.class);

		// Set request.dateSaisieMin on whereClause if not null
		if (feuilleId != null) {
			whereClause.add(criteriaBuilder.equal( root.get("id"),feuilleId));
		}

		 criteriaQuery.select(
		    		root.get("chaineId").as(Long.class)	
					).where(whereClause.toArray(new Predicate[] {}));


		List<Long> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();
				
				
		if(!resultatEntite.isEmpty()){
			return resultatEntite.get(0);
		}
		
		return null;
	}

}
