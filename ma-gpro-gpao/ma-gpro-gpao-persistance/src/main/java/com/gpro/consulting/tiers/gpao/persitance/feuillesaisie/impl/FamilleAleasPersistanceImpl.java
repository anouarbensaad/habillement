package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gpao.coordination.aleas.FamilleAleasValue;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IFamilleAleasPersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity.FamilleAleasEntity;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.utility.AleasPersistanceUtility;

/**
 * @author Hajer AMRI
 * @since 22/02/2017
 */

@Component
public class FamilleAleasPersistanceImpl extends AbstractPersistance implements IFamilleAleasPersistance {
	private static final Logger logger = LoggerFactory.getLogger(FamilleAleasPersistanceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	// private String COLUMN_CHAINE = "chaineId";
	// private String COLUMN_MATRICULE = "matricule";
	// private String ENTITY_PERSONNEL = "personnel";
	// private String PREDICATE_DATE_SAISIE = "dateSaisie";
	// private String PREDICATE_RENDEMENT = "rendement";
	// private String PREDICATE_ACTIVITE = "activite";

	@Autowired
	private AleasPersistanceUtility aleasPersistanceUtility;

	@Override
	public String create(FamilleAleasValue familleAleas) {
		FamilleAleasEntity entity = (FamilleAleasEntity) this
				.creer(aleasPersistanceUtility.toEntity(familleAleas));
		//LOGGER.info("createFamilleAleas:Persistance" + familleAleas);

		return entity.getId().toString();
	}

	@Override
	public FamilleAleasValue getById(Long id) {
		FamilleAleasEntity entity = this.rechercherParId(id, FamilleAleasEntity.class);

		return aleasPersistanceUtility.toValue(entity);
	}

	@Override
	public String update(FamilleAleasValue familleAleas) {
		FamilleAleasEntity entity = (FamilleAleasEntity) this
				.modifier(aleasPersistanceUtility.toEntity(familleAleas));

		return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		this.supprimerEntite(FamilleAleasEntity.class, id.longValue());
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

}
