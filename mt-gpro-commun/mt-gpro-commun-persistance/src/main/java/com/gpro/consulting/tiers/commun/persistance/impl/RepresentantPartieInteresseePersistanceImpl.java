package com.gpro.consulting.tiers.commun.persistance.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.RepresentantValue;
import com.gpro.consulting.tiers.commun.persistance.IRepresentantPartieInteresseePersistance;

/**
 * The Class RepresentantPartieInteresseeImpl.
 * 
 * @author $mohamed
 */
public class RepresentantPartieInteresseePersistanceImpl extends
		AbstractPersistance implements IRepresentantPartieInteresseePersistance {

	/** EntityManager. */
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public String creerRepresentantPartieInteressee(
			RepresentantValue pRepresantentValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerRepresentantPartieInteressee(
			RepresentantValue pRepresantentValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public String modifierRepresentantPartieInteressee(
			RepresentantValue pRepresantentValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RepresentantValue rechercheRepresentantPartieInteresseeParId(
			RepresentantValue pRepresantentValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RepresentantValue> listeRepresentantPartieInteressee() {
		// TODO Auto-generated method stub
		return null;
	}

}
