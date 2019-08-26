package com.gpro.consulting.tiers.gpao.persitance.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gpao.persitance.ICauseVariationPersistance;

@Component
public class CauseVariationPersistanceImpl extends AbstractPersistance implements ICauseVariationPersistance {
	
	private static final Logger logger = LoggerFactory.getLogger(CauseVariationPersistanceImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
