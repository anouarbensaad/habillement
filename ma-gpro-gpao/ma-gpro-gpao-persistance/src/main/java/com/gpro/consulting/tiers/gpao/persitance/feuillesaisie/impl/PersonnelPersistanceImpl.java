package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.aspectj.weaver.patterns.PerObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticriterePersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelElementValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechechePersonnelValue;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IPersonnelPersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.PersonnelEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.utility.FeuilleSaisiePersistanceUtility;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class PersonnelPersistanceImpl extends AbstractPersistance implements IPersonnelPersistance{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private FeuilleSaisiePersistanceUtility feuilleSaisiePersistanceUtility;
	private String PERCENT = "%";
	private String PREDICATE_MATRICULE = "matricule";
	private String PREDICATE_NOM = "nom";
	private String PREDICATE_PRENOM = "prenom";
	
	@Override
	public String create(PersonnelValue personnel) {
		PersonnelEntity entity = (PersonnelEntity) this.creer(feuilleSaisiePersistanceUtility.ToPersonnelEntity(personnel));
	    return entity.getId().toString();
	}

	@Override
	public PersonnelValue getById(Long id) {
		PersonnelEntity entity = this.rechercherParId(id, PersonnelEntity.class);

	    return feuilleSaisiePersistanceUtility.personnelToValue(entity);
	}

	@Override
	public String update(PersonnelValue personnel) {
		PersonnelEntity entity = (PersonnelEntity) this.modifier(feuilleSaisiePersistanceUtility.ToPersonnelEntity(personnel));
	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		this.supprimerEntite(PersonnelEntity.class, id);
		
	}

	@Override
	public ResultatRechechePersonnelValue rechercherMultiCritere(
			RechercheMulticriterePersonnelValue request) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<PersonnelEntity> criteriaQuery = criteriaBuilder.createQuery(PersonnelEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<PersonnelEntity> root = criteriaQuery.from(PersonnelEntity.class);
		
		/** Matricule */
		if (estNonVide(request.getMatricule())) {
			Expression<String> path = root.get(PREDICATE_MATRICULE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getMatricule().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		/** Nom */
		if (estNonVide(request.getNom())) {
			Expression<String> path = root.get(PREDICATE_NOM);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getNom().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		/** Prenom */
		if (estNonVide(request.getPrenom())) {
			Expression<String> path = root.get(PREDICATE_PRENOM);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.like(upper, PERCENT + request.getPrenom().toUpperCase() + PERCENT);
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    List <PersonnelEntity> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

	    List<PersonnelElementValue> list = new ArrayList<PersonnelElementValue>();
	   
	    for (PersonnelEntity entity : resultatEntite) {
	    	
	    	PersonnelElementValue dto = feuilleSaisiePersistanceUtility.toResultatRechechePeronnelElementValue(entity);
	    	list.add(dto);
	    }
	    
	    ResultatRechechePersonnelValue result = new ResultatRechechePersonnelValue();
	    result.setNombreResultaRechercher(Long.valueOf(new TreeSet<>(list).size()));
	    result.setList(new TreeSet<>(list));
	    
		return result;
	}

	@Override
	public List<PersonnelValue> listPersonnel() {
		List<PersonnelEntity> listEntity = this.lister(PersonnelEntity.class);
		
		return feuilleSaisiePersistanceUtility.toValue(listEntity);
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
	public PersonnelValue getByMatricule(String matricule) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<PersonnelEntity> criteriaQuery = criteriaBuilder.createQuery(PersonnelEntity.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<PersonnelEntity> root = criteriaQuery.from(PersonnelEntity.class);
		
		/** Matricule */
		if (estNonVide(matricule)) {
			Expression<String> path = root.get(PREDICATE_MATRICULE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.equal(upper, matricule.toUpperCase());
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
	    PersonnelEntity resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList().get(0);

    	PersonnelValue dto = feuilleSaisiePersistanceUtility.personnelToValue(resultatEntite);
	    
		return dto;
	}

	@Override
	public boolean personnelIsIndirect(String matricule) {
		
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Boolean> criteriaQuery = criteriaBuilder.createQuery(Boolean.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();
		Root<PersonnelEntity> root = criteriaQuery.from(PersonnelEntity.class);
		
		/** Matricule */
		if (estNonVide(matricule)) {
			Expression<String> path = root.get(PREDICATE_MATRICULE);
			Expression<String> upper =criteriaBuilder.upper(path);
			Predicate predicate = criteriaBuilder.equal(upper, matricule.toUpperCase());
			whereClause.add(criteriaBuilder.and(predicate));
		}
		
		criteriaQuery.select(root.get("indirect").as(Boolean.class)).where(whereClause.toArray(new Predicate[] {}));
	    List<Boolean> personnelIndirect = this.entityManager.createQuery(criteriaQuery).getResultList();
		
	    if(personnelIndirect.size()>0){
	    	return personnelIndirect.get(0);
	    }
	    
	    return false;
	}

}
