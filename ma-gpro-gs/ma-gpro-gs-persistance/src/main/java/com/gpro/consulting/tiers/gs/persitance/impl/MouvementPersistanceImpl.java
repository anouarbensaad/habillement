package com.gpro.consulting.tiers.gs.persitance.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.persistance.AbstractPersistance;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.ArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.FamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.SousFamilleArticleEntity;
import com.gpro.consulting.tiers.commun.persistance.entity.TypeArticleEntity;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gs.coordination.report.value.RequestRechecheMouvementValue;
import com.gpro.consulting.tiers.gs.coordination.value.MouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheMouvementValue;
import com.gpro.consulting.tiers.gs.persitance.IMouvementPersistance;
import com.gpro.consulting.tiers.gs.persitance.entite.BonMouvementEntite;
import com.gpro.consulting.tiers.gs.persitance.entite.EntiteStockEntite;
import com.gpro.consulting.tiers.gs.persitance.entite.MagasinEntite;
import com.gpro.consulting.tiers.gs.persitance.entite.MouvementEntite;
import com.gpro.consulting.tiers.gs.persitance.utilities.PersistanceUtilities;

/**
 * The Class BonMouvementStockPersistanceImpl.
 */

@Component
public class MouvementPersistanceImpl extends AbstractPersistance implements IMouvementPersistance {

	private static final Logger logger = LoggerFactory.getLogger(MouvementPersistanceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public IOrdreFabricationPersistance ordreFabricationPersistance;
	
	@Autowired
	public  IProduitPersistance produitPersistance;

	private String typeMouvement = "typeMouvement";
	private String typeArticle = "type";
	private String reference = "referenceArticle";
	private String article = "article";
	private String magasin = "magasin";
	private String emplacement = "emplacement";
	private String raison = "raisonMouvementId";
	private String responsable = "responsable";
	private String client = "partieInteresseeId";
	private String fournisseur = "partieInteresseeId";
	private String sousTraitant = "partieInteresseeId";
	private String entiteStock = "entiteStock";
	private String bonMouvement = "bonMouvement";
	private String id = "id";
	private String finConesReel = "finConesReel";
	private String finConeValue = "oui";
	private String date = "date";
	private String famille = "familleArticle";
	private String sousFamille = "sousFamilleArtEntite";

	private static final String ATTRIBUTE_NAME_ID = "id";
	private String PREDICATE_DATEMOUVEMENT = "date";
	private String PREDICATE_OF_ID = "ofId";
    private String PREDICATE_LOT="referenceLot";


	@Override
	public ResultatRechecheMouvementValue rechercherMouvementMultiCritere(
			RechercheMulticritereMouvementValue pRechercheMulticritereMouvementValue) {

		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
		/** entity principale **/
		CriteriaQuery<MouvementEntite> vCriteriaQuery = vBuilder.createQuery(MouvementEntite.class);
		List<Predicate> vWhereClause = new ArrayList<Predicate>();

		/********** entite de base **********/
		Root<MouvementEntite> vRootMouvement = vCriteriaQuery.from(MouvementEntite.class);
		/*********** jointure ********/

		/************* jointure magasin entite ***********/
		if (estNonVide(pRechercheMulticritereMouvementValue.getMagasin())) {
			Join<MouvementEntite, EntiteStockEntite> jointureMvtEnStk = vRootMouvement.join(entiteStock);
			Join<EntiteStockEntite, MagasinEntite> jointureEnStkMagasin = jointureMvtEnStk.join(magasin);
			vWhereClause.add(
					vBuilder.equal(jointureEnStkMagasin.get(id), pRechercheMulticritereMouvementValue.getMagasin()));
		}

		/*********** jointure article entite ********/
		if (estNonVide(pRechercheMulticritereMouvementValue.getArticle())) {
			Join<MouvementEntite, EntiteStockEntite> jointureMvtEnStk = vRootMouvement.join(entiteStock);
			Join<EntiteStockEntite, ArticleEntite> jointureEnstkArt = jointureMvtEnStk.join(article);
			vWhereClause
					.add(vBuilder.equal(jointureEnstkArt.get(id), pRechercheMulticritereMouvementValue.getArticle()));
		}

		if (estNonVide(pRechercheMulticritereMouvementValue.getRefArticle())) {
			Join<MouvementEntite, EntiteStockEntite> jointureMvtEnStk = vRootMouvement.join(entiteStock);
			Join<EntiteStockEntite, ArticleEntite> jointureEnstkArt = jointureMvtEnStk.join(article);
			vWhereClause.add(
					vBuilder.equal(jointureEnstkArt.get(id), pRechercheMulticritereMouvementValue.getRefArticle()));
		}

		// TODO : vérifier l'utilité
		if (estNonVide(pRechercheMulticritereMouvementValue.getReference())) {
			Join<MouvementEntite, EntiteStockEntite> jointureMvtEnStk = vRootMouvement.join(entiteStock);
			vWhereClause.add(vBuilder.equal(jointureMvtEnStk.get(reference),
					pRechercheMulticritereMouvementValue.getReference()));
		}
		
		//Hajer Amri 15/03/2017
		if (estNonVide(pRechercheMulticritereMouvementValue.getCodeFournisseur())) {
			Join<MouvementEntite, EntiteStockEntite> jointureMvtEnStk = vRootMouvement.join(entiteStock);
			Join<EntiteStockEntite, ArticleEntite> jointureEnstkArt = jointureMvtEnStk.join(article);
			vWhereClause.add(
					vBuilder.equal(jointureEnstkArt.get(id), pRechercheMulticritereMouvementValue.getCodeFournisseur()));
		}

		/**************** jointure partieInteresse entite *************/
		if (estNonVide(pRechercheMulticritereMouvementValue.getClient())) {
			Join<MouvementEntite, BonMouvementEntite> jointureMvtBonMvt = vRootMouvement.join(bonMouvement);
			vWhereClause.add(
					vBuilder.equal(jointureMvtBonMvt.get(client), pRechercheMulticritereMouvementValue.getClient()));
		}
		if (estNonVide(pRechercheMulticritereMouvementValue.getFournisseur())) {
			Join<MouvementEntite, BonMouvementEntite> jointureMvtBonMvt = vRootMouvement.join(bonMouvement);
			vWhereClause.add(vBuilder.equal(jointureMvtBonMvt.get(fournisseur),
					pRechercheMulticritereMouvementValue.getFournisseur()));
		}

		if (estNonVide(pRechercheMulticritereMouvementValue.getSousTraitant())) {
			Join<MouvementEntite, BonMouvementEntite> jointureMvtBonMvt = vRootMouvement.join(bonMouvement);
			vWhereClause.add(vBuilder.equal(jointureMvtBonMvt.get(sousTraitant),
					pRechercheMulticritereMouvementValue.getSousTraitant()));
		}
		/****************************/
		if (estNonVide(pRechercheMulticritereMouvementValue.getHistorique())) {
			vWhereClause.add(vBuilder.equal(vRootMouvement.get(typeMouvement),
					pRechercheMulticritereMouvementValue.getHistorique()));
		}

		if (estNonVide(pRechercheMulticritereMouvementValue.getEmplacement())) {
			vWhereClause.add(vBuilder.equal(vRootMouvement.get(emplacement),
					pRechercheMulticritereMouvementValue.getEmplacement()));
		}

		if (estNonVide(pRechercheMulticritereMouvementValue.getFamille())) {
			Join<MouvementEntite, EntiteStockEntite> jointureMvtEnStk = vRootMouvement.join(entiteStock);
			Join<EntiteStockEntite, ArticleEntite> jointureEnStkArt = jointureMvtEnStk.join(article);
			Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousF = jointureEnStkArt.join(sousFamille);
			Join<SousFamilleArticleEntity, FamilleArticleEntity> jointureSouFmFamille = jointureArtSousF.join(famille);
			vWhereClause.add(
					vBuilder.equal(jointureSouFmFamille.get(id), pRechercheMulticritereMouvementValue.getFamille()));
		}
		
		if (estNonVide(pRechercheMulticritereMouvementValue.getLot())) {
			Join<MouvementEntite, EntiteStockEntite> jointureMvtEnStk = vRootMouvement.join(entiteStock);
		      vWhereClause.add(vBuilder.equal(jointureMvtEnStk.get(PREDICATE_LOT),
		    		  pRechercheMulticritereMouvementValue.getLot()));
	    }

		if (estNonVide(pRechercheMulticritereMouvementValue.getSousFamille())) {
			Join<MouvementEntite, EntiteStockEntite> jointureMvtEnStk = vRootMouvement.join(entiteStock);
			Join<EntiteStockEntite, ArticleEntite> jointureEnStkArt = jointureMvtEnStk.join(article);
			Join<ArticleEntite, SousFamilleArticleEntity> jointureArtSousF = jointureEnStkArt.join(sousFamille);
			vWhereClause.add(
					vBuilder.equal(jointureArtSousF.get(id), pRechercheMulticritereMouvementValue.getSousFamille()));
		}

		if (estNonVide(pRechercheMulticritereMouvementValue.getFinCone())) {
			Expression<Long> nbrFinCone = vRootMouvement.get(finConesReel);
			if (pRechercheMulticritereMouvementValue.getFinCone() == finConeValue) {
				vWhereClause.add(vBuilder.gt(nbrFinCone, 0L));
			} else {
				vWhereClause.add(vBuilder.le(nbrFinCone, 0L));
			}

		}

		// type article
		if (estNonVide(pRechercheMulticritereMouvementValue.getType())) {
			vWhereClause.add(vBuilder.equal(vRootMouvement.get(typeArticle),
					pRechercheMulticritereMouvementValue.getType()));
		}

		/***** jointure bonMouvement entite *******/
		/************** date bonMouvement ******************/
		if (pRechercheMulticritereMouvementValue.getDateDu() != null) {
			Join<MouvementEntite, BonMouvementEntite> jointureMvtBmvt = vRootMouvement.join(bonMouvement);
			Expression<Calendar> dateMouvement = jointureMvtBmvt.get(date);
			Calendar dateEntre = pRechercheMulticritereMouvementValue.getDateDu();
			vWhereClause.add(vBuilder.greaterThanOrEqualTo(dateMouvement, dateEntre));

		}

		if (pRechercheMulticritereMouvementValue.getDateA() != null) {
			Join<MouvementEntite, BonMouvementEntite> jointureMvtBmvt = vRootMouvement.join(bonMouvement);
			Expression<Calendar> dateMouvement = jointureMvtBmvt.get(date);
			Calendar dateSortie = pRechercheMulticritereMouvementValue.getDateA();
			vWhereClause.add(vBuilder.lessThanOrEqualTo(dateMouvement, dateSortie));
		}

		if (estNonVide(pRechercheMulticritereMouvementValue.getResponsable())) {
			Join<MouvementEntite, BonMouvementEntite> jointureMvtBmvt = vRootMouvement.join(bonMouvement);
			vWhereClause.add(vBuilder.equal(jointureMvtBmvt.get(responsable),
					pRechercheMulticritereMouvementValue.getResponsable()));
		}

		if (estNonVide(pRechercheMulticritereMouvementValue.getRaison())) {
			Join<MouvementEntite, BonMouvementEntite> jointureMvtBmvt = vRootMouvement.join(bonMouvement);
			vWhereClause
					.add(vBuilder.equal(jointureMvtBmvt.get(raison), pRechercheMulticritereMouvementValue.getRaison()));
		}

		/************** OF ******************/
		if (pRechercheMulticritereMouvementValue.getOfId() != null) {
			Join<MouvementEntite, BonMouvementEntite> jointureMvtBmvt = vRootMouvement.join(bonMouvement);
			Expression<Long> ofIdExpression = jointureMvtBmvt.get(PREDICATE_OF_ID);
			Long ofId = pRechercheMulticritereMouvementValue.getOfId();
			vWhereClause.add(vBuilder.equal(ofIdExpression, ofId));

		}

		if (pRechercheMulticritereMouvementValue.getEntiteStockId() != null) {
			Expression<Long> entiteStockIdExpression = vRootMouvement.get(entiteStock).get("id");
			Long entiteStockId = pRechercheMulticritereMouvementValue.getEntiteStockId();
			vWhereClause.add(vBuilder.equal(entiteStockIdExpression, entiteStockId));

		}

		/** execute query and do something with result **/

		vCriteriaQuery.select(vRootMouvement).where(vWhereClause.toArray(new Predicate[] {}));
		List<MouvementEntite> resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

		/** Conversion de la liste en valeur */
		List<MouvementStockValue> vListeResultat = new ArrayList<MouvementStockValue>();

		for (MouvementEntite vMouvementEntite : resultatEntite) {
			MouvementStockValue vMe = PersistanceUtilities.toValueAffichage(vMouvementEntite);
			
			 if(vMouvementEntite.getBonMouvement()!= null){
			    	
				 
				 vMe.setNumBonMvt(vMouvementEntite.getBonMouvement().getNumero());
				 vMe.setDateBonMvt(vMouvementEntite.getBonMouvement().getDate());
				 
				 if(vMouvementEntite.getBonMouvement().getOfId()!=null){
					 Long OFId = vMouvementEntite.getBonMouvement().getOfId();
				    	//System.out.println("--------- OF ID------ " + OFId);
				    	vMe.setOFId(OFId);
				        String OF = ordreFabricationPersistance.getNumOfParId(OFId);
				        if(OF!=null){
				        	vMe.setOF(OF);
				        }
				        
				        Long produitId = ordreFabricationPersistance.getProduitIdByOFId(OFId);
				        
				        if(produitId != null){
				        	vMe.setProduitId(produitId);
				        	ProduitValue produit = produitPersistance.rechercheProduitById(produitId);
				        	
				        	if(produit!= null){
				        		vMe.setRefProduit(produit.getReference());
				        		vMe.setDesignationProduit(produit.getDesignation());
				        	}
				        }
				 }
			    	
			    }
			 
			vListeResultat.add(vMe);
		}

		/** retour de list de recherche et le nombre de resultat **/
		ResultatRechecheMouvementValue vResultatRechecheMouvementValue = new ResultatRechecheMouvementValue();

		vResultatRechecheMouvementValue.setNombreResultaRechercher(Long.valueOf(vListeResultat.size()));

		Collections.sort(vListeResultat);
		vResultatRechecheMouvementValue.setMouvementStock(new TreeSet<>(vListeResultat));
		logger.info("nombre resultat =  " + vResultatRechecheMouvementValue.getNombreResultaRechercher());
		return vResultatRechecheMouvementValue;
	}

	/**
	 * Est non vide.
	 *
	 * @param val
	 *            the val
	 * @return true, if successful
	 */
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);

	}

	/******* liste mouvement stock ******/
	@Override
	public List<MouvementStockValue> listeMouvementStock() {

		List<MouvementEntite> vListMouvementStockEntite = this.lister(MouvementEntite.class);
		List<MouvementStockValue> vlistMouvementStockValue = new ArrayList<MouvementStockValue>();
		for (MouvementEntite vMouvementStockEntite : vListMouvementStockEntite) {
			vlistMouvementStockValue.add(PersistanceUtilities.toValue(vMouvementStockEntite));
		}

		Collections.sort(vlistMouvementStockValue);
		return vlistMouvementStockValue;
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public ResultatRechecheMouvementValue rechercherEtatMouvement(RequestRechecheMouvementValue request) {

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<MouvementEntite> criteriaQuery = criteriaBuilder.createQuery(MouvementEntite.class);
		List<Predicate> whereClause = new ArrayList<Predicate>();

		Root<MouvementEntite> root = criteriaQuery.from(MouvementEntite.class);

		if ((request.getEntiteStockId()) != null) {

			Join<MouvementEntite, EntiteStockEntite> jointureMouvementEntiteStock = root.join(entiteStock);
			// Join<EntiteStockEntite, ArticleEntite> jointureEntiteStockArticle
			// = jointureMouvementEntiteStock.join(article);
			whereClause.add(criteriaBuilder.equal(jointureMouvementEntiteStock.get(ATTRIBUTE_NAME_ID),
					request.getEntiteStockId()));
		}

		// Set request.dateMin on whereClause if not null
		if (request.getDateMin() != null) {
			Join<MouvementEntite, BonMouvementEntite> jointureMouvementBonMouvement = root.join(bonMouvement);
			whereClause.add(criteriaBuilder.greaterThanOrEqualTo(
					jointureMouvementBonMouvement.<Calendar> get(PREDICATE_DATEMOUVEMENT), request.getDateMin()));
		}

		// Set request.dateMax on whereClause if not null
		if (request.getDateMax() != null) {
			Join<MouvementEntite, BonMouvementEntite> jointureMouvementBonMouvement = root.join(bonMouvement);
			whereClause.add(criteriaBuilder.lessThanOrEqualTo(
					jointureMouvementBonMouvement.<Calendar> get(PREDICATE_DATEMOUVEMENT), request.getDateMax()));
		}

		criteriaQuery.select(root).where(whereClause.toArray(new Predicate[] {}));
		List<MouvementEntite> resultatEntite = this.entityManager.createQuery(criteriaQuery).getResultList();

		List<MouvementStockValue> mouvementStockList = new ArrayList<MouvementStockValue>();

		for (MouvementEntite entity : resultatEntite) {
			MouvementStockValue dto = PersistanceUtilities.toValueAffichage(entity);
			mouvementStockList.add(dto);
		}

		ResultatRechecheMouvementValue result = new ResultatRechecheMouvementValue();

		result.setNombreResultaRechercher(Long.valueOf(mouvementStockList.size()));

		result.setMouvementStock(new TreeSet<>(mouvementStockList));
		return result;
	}

}
