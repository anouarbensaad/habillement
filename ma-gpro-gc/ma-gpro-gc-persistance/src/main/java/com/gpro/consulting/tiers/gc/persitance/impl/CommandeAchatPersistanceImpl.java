package com.gpro.consulting.tiers.gc.persitance.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.ArticleEntite;
import com.gpro.consulting.tiers.gc.coordination.value.CommandeAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.ElementCommandeAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereCommandeAchatValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheCommandeAchatValue;
import com.gpro.consulting.tiers.gc.persitance.ICommandeAchatPersistance;
import com.gpro.consulting.tiers.gc.persitance.entite.CommandeAchatEntite;
import com.gpro.consulting.tiers.gc.persitance.utilities.PersistanceUtilities;

/**
 * @author $Ameni
 *
 */

@Component
public class CommandeAchatPersistanceImpl extends AbstractPersistance implements ICommandeAchatPersistance{

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	/** Predicates */
	private String reference="reference" ;
	private String fournisseur="partieIntersseId";
	
	@Override
	public String creerCommandeAchat(CommandeAchatValue pCommandeAchatValue) {
		if(pCommandeAchatValue.getElementCommandes() != null){
			log.info("Creation d'une commande d'Achat.. size "+pCommandeAchatValue.getElementCommandes().size());
		}
		if (log.isDebugEnabled()) {
			log.debug("creation d'un Bon de Commande d'Achat avec la reference : "	+ pCommandeAchatValue.getReference());
		}
		/*Set< ArticleEntite > vListArticles = new HashSet < ArticleEntite >();

		if (pCommandeAchatValue.getElementCommandes() != null) {
		      for (ElementCommandeAchatValue prdCmd : pCommandeAchatValue.getElementCommandes()) {
		    	  ArticleEntite vEntite = this.rechercherParId(prdCmd.getId(), ArticleEntite.class);
		    	  vListArticles.add(vEntite);
		      }
		    }
		if(vListArticles.size() != 0){
			log.info(" Aricle size "+vListArticles.size());
		}*/
		
		CommandeAchatEntite vCommandeAchatEntite = (CommandeAchatEntite) this.creer(PersistanceUtilities.toEntite(pCommandeAchatValue)); //, vListArticles));

		return vCommandeAchatEntite.getId().toString();
	}

	@Override
	public void supprimerCommandeAchat(Long pId) {
		if (log.isDebugEnabled()) {
			log.debug("Suppression d'un bon de commande d'Achat avec l'ID : "
					+ pId.longValue());
		}
		this.supprimerEntite(CommandeAchatEntite.class, pId);		
	}

	@Override
	public String modifierCommandeAchat(CommandeAchatValue pCommandeAchatValue) {
		if(pCommandeAchatValue.getElementCommandes() != null){
			log.info("Modification d'une commande d'Achat.. size "+pCommandeAchatValue.getElementCommandes().size());
		}
		if (log.isDebugEnabled()) {
			log.debug("creation d'un Bon de Commande d'Achat avec la reference : "	+ pCommandeAchatValue.getReference());
		}
		Set< ArticleEntite > vListArticles = new HashSet < ArticleEntite >();

		if (pCommandeAchatValue.getElementCommandes() != null) {
		      for (ElementCommandeAchatValue prdCmd : pCommandeAchatValue.getElementCommandes()) {
		    	  ArticleEntite vEntite = this.rechercherParId(prdCmd.getId(), ArticleEntite.class);
		    	  vListArticles.add(vEntite);
		      }
		    }
		if(vListArticles.size() != 0){
			log.info(" Aricle size "+vListArticles.size());
		}
		
		CommandeAchatEntite vCommandeAchatEntite = (CommandeAchatEntite) this.modifier(PersistanceUtilities.toEntite(pCommandeAchatValue)); //, vListArticles));
		//log.info("ElementCommande size "+vCommandeAchatEntite.getElementCommandes().size());

		return vCommandeAchatEntite.getId().toString();
	}

	@Override
	public CommandeAchatValue rechercheCommandeAchatParId(Long pId) {
		if (log.isDebugEnabled()) {
			log.debug("recherche  d'un bon de commande d'Achat. Id : "+ pId);
		}
		log.info("recherche d'un bon de commande d'achat. Id : "+ pId);
		
		CommandeAchatEntite vCommandeAchatEntite = this.rechercherParId(pId,CommandeAchatEntite.class);
		CommandeAchatValue vCommandeAchatValueResult = PersistanceUtilities.toValue(vCommandeAchatEntite);
		return vCommandeAchatValueResult;
	}

	@Override
	public List<CommandeAchatValue> listeCommandeAchat() {
		List<CommandeAchatEntite> vListeCommandeAchatEntite = this.lister(CommandeAchatEntite.class);
		List<CommandeAchatValue> vListeCommandeAchatValues = new ArrayList<CommandeAchatValue>();
		for (CommandeAchatEntite vCommandeAchatEntite : vListeCommandeAchatEntite) {
			vListeCommandeAchatValues.add(PersistanceUtilities.toValue(vCommandeAchatEntite));
		}
		return vListeCommandeAchatValues;
	}

	@Override
	public ResultatRechecheCommandeAchatValue rechercherCommandeAchatMultiCritere(
			RechercheMulticritereCommandeAchatValue pRechercheCommandeAchatMulitCritere) {
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		/** entity principale **/
		CriteriaQuery<CommandeAchatEntite> vCriteriaQuery = vBuilder
				.createQuery(CommandeAchatEntite.class);
		List<Predicate> vWhereClause = new ArrayList<Predicate>();

		/** create liste resultat ***/

		/************ entity jointure *****************/
		Root<CommandeAchatEntite> vRootCommandeAchat = vCriteriaQuery
				.from(CommandeAchatEntite.class);
		
		/* Reference */
		if (estNonVide(pRechercheCommandeAchatMulitCritere.getvReference())) {
			vWhereClause.add(vBuilder.equal(
					vRootCommandeAchat.get(reference),
					pRechercheCommandeAchatMulitCritere.getvReference()));
		}
		/* Client (PartieInteressee) */
		if (estNonVide(pRechercheCommandeAchatMulitCritere.getvPartieInteressee())) {
			vWhereClause.add(vBuilder.equal(
					vRootCommandeAchat.get(fournisseur),
					pRechercheCommandeAchatMulitCritere.getvPartieInteressee()));
		}
		
		 /** execute query and do something with result **/

	    vCriteriaQuery.select(vRootCommandeAchat).where(vWhereClause.toArray(new Predicate[] {}));
	    List < CommandeAchatEntite > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

	    /** Conversion de la liste en valeur */
	    List < CommandeAchatValue > vListeResultat = new ArrayList < CommandeAchatValue >();
	    
	    for (CommandeAchatEntite vCommandeVenteEntite : resultatEntite) {
	    	CommandeAchatValue vPv = PersistanceUtilities.toValue(vCommandeVenteEntite);
	      vListeResultat.add(vPv);
	    }

	    /** retour de list de recherche et le nombre de resultat **/
	    ResultatRechecheCommandeAchatValue vResultatRechecheCommandeAchatValue = new ResultatRechecheCommandeAchatValue();

	    vResultatRechecheCommandeAchatValue.setNombreResultaRechercher(Long.valueOf(vListeResultat.size()));

	    vResultatRechecheCommandeAchatValue.setCommandeAchatValues(vListeResultat);

	    return vResultatRechecheCommandeAchatValue;
	}


	private boolean estNonVide(String val) {
		
	    return val != null && !"".equals(val);
	    
	}
	
	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}
	

}
