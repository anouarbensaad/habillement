package com.gpro.consulting.tiers.gc.persitance.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.gc.coordination.value.TypeCommandeValue;
import com.gpro.consulting.tiers.gc.persitance.ITypeCommandePersistance;
import com.gpro.consulting.tiers.gc.persitance.entite.TypeCommandeEntite;
import com.gpro.consulting.tiers.gc.persitance.utilities.PersistanceUtilities;

/**
 * @author toshiba
 *
 */

@Component
public class TypeCommandePersistanceImpl extends AbstractPersistance implements ITypeCommandePersistance {
	
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String creerTypeCommande(TypeCommandeValue pTypeCommandeValue) {
		if(log.isDebugEnabled()){
			log.debug("creation d'un etat de Commande de Vente avec la designation : " + pTypeCommandeValue.getDesignation());
		}
		TypeCommandeEntite vCommandeVenteEntite= (TypeCommandeEntite) this.creer(PersistanceUtilities.toEntite(pTypeCommandeValue));
		TypeCommandeValue vTypeCommandeValueResult=PersistanceUtilities.toValue(vCommandeVenteEntite);
		return vTypeCommandeValueResult.getId().toString();
	}

	@Override
	public void supprimerTypeCommande(Long pId) {
		if (log.isDebugEnabled()) {
			log.debug("Suppression  d'un etat de Commande de Vente avec l'ID : " + pId.longValue());
           }
          this.supprimerEntite(TypeCommandeEntite.class, pId);			
	}

	@Override
	public String modifierTypeCommande(TypeCommandeValue pTypeCommandeValue) {
		if(log.isDebugEnabled()){
			log.debug("Modification d'un etat de Commande de Vente avec la designation : " + pTypeCommandeValue.getDesignation());
		}
		TypeCommandeEntite vTypeCommandeEntite= (TypeCommandeEntite) this.modifier(PersistanceUtilities.toEntite(pTypeCommandeValue));
		TypeCommandeValue vTypeCommandeValueResult=PersistanceUtilities.toValue(vTypeCommandeEntite);
		return vTypeCommandeValueResult.getId().toString();
	}

	@Override
	public TypeCommandeValue rechercheTypeCommandeParId(Long pId) {
		if(log.isDebugEnabled()){
			log.debug("recherche d'un etat de Commande de Vente avec l'id : " +pId );
		}
		TypeCommandeEntite vTypeCommandeEntite=  this.rechercherParId(pId,TypeCommandeEntite.class);
		TypeCommandeValue vTypeCommandeValueResult=PersistanceUtilities.toValue(vTypeCommandeEntite);
		return vTypeCommandeValueResult;
	}

	@Override
	public List<TypeCommandeValue> listeTypeCommande() {
		List<TypeCommandeEntite> vListeTypeCommandeEntite = this.lister(TypeCommandeEntite.class);
		List < TypeCommandeValue > vListeTypeCommandeValues = new ArrayList < TypeCommandeValue >();
		for (TypeCommandeEntite vTypeCommandeEntite : vListeTypeCommandeEntite) {
			 vListeTypeCommandeValues.add(PersistanceUtilities.toValue(vTypeCommandeEntite));
	 	    }
		return vListeTypeCommandeValues;
	}

	@Override
	public EntityManager getEntityManager() {

		return entityManager;
	}


}
