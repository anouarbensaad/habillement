package com.gpro.consulting.tiers.gs.persitance.guichet.impl;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gs.coordination.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.tiers.gs.persitance.guichet.IGuichetAnnuelPersistance;
import com.gpro.consulting.tiers.gs.persitance.guichet.entity.GuichetAnnuelEntity;
@Component
public class GuichetAnnuelPersistanceImpl extends AbstractPersistance implements IGuichetAnnuelPersistance{
   
	/** EntityManager. */
	  @PersistenceContext
	  private EntityManager entityManager;

	  /**
	   * Constructeur de la classe.
	   */
	 
	  
	  /**
	   * {@inheritDoc}
	   */
	  @Override
	  public EntityManager getEntityManager() {
	    return this.entityManager;
	  }

	  /**
	   * Méthode de recheche d'une entité GuichetBonReceptionEntity par année.
	   * @param pGuichetValeur ke guichet à rechercher
	   * @return GuichetBonReceptionEntity le guichet trouvé en base
	   */
	  public GuichetAnnuelEntity rechercherGuichetAnnuel(final GuichetAnnuelValue pGuichetValeur) {
		  GuichetAnnuelEntity vGuichetEntite = 
	      this.entityManager.find(GuichetAnnuelEntity.class, pGuichetValeur.getId(), 
	        LockModeType.PESSIMISTIC_WRITE);
	    return vGuichetEntite;
	  }
	  

	@Override
	public Long getNextNumBonEntreeReference() {
		// TODO Auto-generated method stub
		
		// Année courante
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	    
	    // Chercher le dernier numéro dans la base et le charger
	    Query vQuery = this.entityManager.createQuery(
	      "select g.numReferenceBonEntreeCourante from GuichetAnnuelEntity g where g.annee =" + vAnneeCourante);

	    Object vResult = vQuery.getSingleResult();
	    Long vNextNum = (Long) vResult;
	    // Format du Numéro du bon de reception : NNNNNNN
	    return vNextNum;
	}

	@Override
	public Long getNextNumBonSortieReference() {
		// TODO Auto-generated method stub
		
		// Année courante
	    int vAnneeCourante = Calendar.getInstance().get(Calendar.YEAR);
	    
	    // Chercher le dernier numéro dans la base et le charger
	    Query vQuery = this.entityManager.createQuery(
	      "select g.numReferenceBonSortieCourante from GuichetAnnuelEntity g where g.annee =" + vAnneeCourante);

	    Object vResult = vQuery.getSingleResult();
	    Long vNextNum = (Long) vResult;
	    // Format du Numéro du bon de reception : NNNNNNN
	    return vNextNum;
		
	}


	@Override
	public Long modifierGuichetBonEntreeAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceBonEntreeCourante(pGuichetValeur.getNumReferenceBonEntree());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	}
	
	@Override
	public Long modifierGuichetBonSortieAnnuel(GuichetAnnuelValue pGuichetValeur) {
		// Convertir la valeur du guichet en une entité.
		  GuichetAnnuelEntity vGuichetEntite = rechercherGuichetAnnuel(pGuichetValeur);
		// Sauvegarde de l'instance dans la base
	    vGuichetEntite.setNumReferenceBonSortieCourante(pGuichetValeur.getNumReferenceBonSortie());
	    this.entityManager.merge(vGuichetEntite);
	    this.entityManager.flush();
	    return vGuichetEntite.getId();
	}

}
