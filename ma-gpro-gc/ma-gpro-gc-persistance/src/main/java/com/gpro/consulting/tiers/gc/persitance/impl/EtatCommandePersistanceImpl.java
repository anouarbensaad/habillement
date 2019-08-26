package com.gpro.consulting.tiers.gc.persitance.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gc.coordination.value.EtatCommandeValue;
import com.gpro.consulting.tiers.gc.persitance.IEtatCommandePersistance;
import com.gpro.consulting.tiers.gc.persitance.entite.EtatCommandeEntite;
import com.gpro.consulting.tiers.gc.persitance.utilities.PersistanceUtilities;

/**
 * @author $Ameni
 *
 */

@Component
public class EtatCommandePersistanceImpl  extends AbstractPersistance  implements IEtatCommandePersistance {

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String creerEtatCommande(EtatCommandeValue pEtatCommandeValue) {
		if(log.isDebugEnabled()){
			log.debug("creation d'un etat de Commande de Vente avec la designation : " + pEtatCommandeValue.getDesignation());
		}
		EtatCommandeEntite vCommandeVenteEntite= (EtatCommandeEntite) this.creer(PersistanceUtilities.toEntite(pEtatCommandeValue));
		EtatCommandeValue vEtatCommandeValueResult=PersistanceUtilities.toValue(vCommandeVenteEntite);
		return vEtatCommandeValueResult.getId().toString();
	}

	@Override
	public void supprimerEtatCommande(Long pId) {
		if (log.isDebugEnabled()) {
			log.debug("Suppression  d'un etat de Commande de Vente avec l'ID : " + pId.longValue());
           }
          this.supprimerEntite(EtatCommandeEntite.class, pId);			
	}

	@Override
	public String modifierEtatCommande(EtatCommandeValue pEtatCommandeValue) {
		if(log.isDebugEnabled()){
			log.debug("Modification d'un etat de Commande de Vente avec la designation : " + pEtatCommandeValue.getDesignation());
		}
		EtatCommandeEntite vEtatCommandeEntite= (EtatCommandeEntite) this.modifier(PersistanceUtilities.toEntite(pEtatCommandeValue));
		EtatCommandeValue vEtatCommandeValueResult=PersistanceUtilities.toValue(vEtatCommandeEntite);
		return vEtatCommandeValueResult.getId().toString();
	}

	@Override
	public EtatCommandeValue rechercheEtatCommandeParId(Long pId) {
		if(log.isDebugEnabled()){
			log.debug("recherche d'un etat de Commande de Vente avec l'id : " +pId );
		}
		EtatCommandeEntite vEtatCommandeEntite=  this.rechercherParId(pId,EtatCommandeEntite.class);
		EtatCommandeValue vEtatCommandeValueResult=PersistanceUtilities.toValue(vEtatCommandeEntite);
		return vEtatCommandeValueResult;
	}

	@Override
	public List<EtatCommandeValue> listeEtatCommande() {
		List<EtatCommandeEntite> vListeEtatCommandeEntite = this.lister(EtatCommandeEntite.class);
		List < EtatCommandeValue > vListeEtatCommandeValues = new ArrayList < EtatCommandeValue >();
		for (EtatCommandeEntite vEtatCommandeEntite : vListeEtatCommandeEntite) {
			 vListeEtatCommandeValues.add(PersistanceUtilities.toValue(vEtatCommandeEntite));
	 	    }
		return vListeEtatCommandeValues;
	}

	/***************************** Getter & Setter ********************************/
	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}	

}
