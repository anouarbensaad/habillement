package com.gpro.consulting.tiers.gpao.persitance.gammemontage.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gpao.coordination.section.value.SectionValue;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.ISectionPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.entity.SectionEntity;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.utility.GammeMontagePersistanceUtilities;

/**
 * Implementation of {@link ISectionPersistance} interface.
 *  
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */

@Component
public class SectionPersistanceImpl  extends AbstractPersistance implements ISectionPersistance{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private GammeMontagePersistanceUtilities gammeMontagePersistanceUtilities;
	
	@Override
	public String create(SectionValue request) {

		SectionEntity entity = (SectionEntity) this.creer(gammeMontagePersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public void delete(Long id) {
		
		this.supprimerEntite(SectionEntity.class, id.longValue());
	}
	
	@Override
	public String update(SectionValue request) {
		
		SectionEntity entity = (SectionEntity) this.modifier(gammeMontagePersistanceUtilities.toEntity(request));

	    return entity.getId().toString();
	}

	@Override
	public SectionValue getById(Long id) {
		
		SectionEntity entity = this.rechercherParId(id, SectionEntity.class);

	    return gammeMontagePersistanceUtilities.toValue(entity);
	}
	
	@Override
	public List<SectionValue> getAll() {
		
		List<SectionEntity> listEntity = this.lister(SectionEntity.class);
		
		return gammeMontagePersistanceUtilities.listSectionToValue(listEntity);
	}

	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}