package com.gpro.consulting.tiers.commun.persistance.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.DocumentValue;
import com.gpro.consulting.tiers.commun.persistance.IDocumentPartieInteresseePersistance;

/**
 * The Class DocumentPartieInteresseeImpl.
 * 
 * @author $mohamed
 */
public class DocumentPartieInteresseePersistanceImpl extends
		AbstractPersistance implements IDocumentPartieInteresseePersistance {

	/** EntityManager. */
	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * get entity manager
	 */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public String creerDocumentPartieInteressee(DocumentValue pDocumenteValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerDocumentPartieInteressee(DocumentValue pDocumentValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String modifierDocumentPartieInteressee(DocumentValue pDocumentValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentValue rechercheDocumentPartieInteresseeParId(DocumentValue pDocumentValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentValue> listeDocumentPartieInteressee() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
