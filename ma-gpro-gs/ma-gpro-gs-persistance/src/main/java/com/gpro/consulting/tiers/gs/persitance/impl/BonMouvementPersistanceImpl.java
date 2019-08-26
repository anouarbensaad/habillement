package com.gpro.consulting.tiers.gs.persitance.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.entity.ArticleEntite;
import com.gpro.consulting.tiers.commun.persistance.entity.PartieInteresseEntite;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gs.coordination.value.BonMouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.DocumentBonMouvementValue;
import com.gpro.consulting.tiers.gs.coordination.value.EntiteStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.MouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereBonMouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatListeBonMvtParTypeValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheBonMouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.customcomparator.BonMouvementComparator;
import com.gpro.consulting.tiers.gs.persitance.IBonMouvementPersistance;
import com.gpro.consulting.tiers.gs.persitance.IMouvementPersistance;
import com.gpro.consulting.tiers.gs.persitance.entite.BonMouvementEntite;
import com.gpro.consulting.tiers.gs.persitance.entite.DocumentBonMouvementEntity;
import com.gpro.consulting.tiers.gs.persitance.entite.EntiteStockEntite;
import com.gpro.consulting.tiers.gs.persitance.entite.MagasinEntite;
import com.gpro.consulting.tiers.gs.persitance.entite.MouvementEntite;
import com.gpro.consulting.tiers.gs.persitance.utilities.PersistanceUtilities;

/**
 * The Class BonMouvementStockPersistanceImpl.
 */

@Component
public class BonMouvementPersistanceImpl extends AbstractPersistance implements IBonMouvementPersistance {

  /** EntityManager. */
  @PersistenceContext
  private EntityManager entityManager;
  
  @Autowired
  private IOrdreFabricationPersistance ordreFabricationPersistance;

   @Autowired
   private IProduitPersistance produitPersistance;
   
   @Autowired
   private IMouvementPersistance mouvementPersistance;
   
   @Autowired
   private IPartieInteresseePersistance partieIntereseePersistance;
   
  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(BonMouvementPersistanceImpl.class);

  private String PERCENT = "%";
  private String nBE = "numero";
  private String nBLDae = "blachatId";
  private String partieInteresse = "partieInteresseeId";
  private String mouvement = "mouvements";
  private String entiteStock = "entiteStock";
  private String magasin = "magasin";
  private String etat = "etat";
  private String type = "type";
  private String date = "date";
  private String valeur = "valeur";
  private String id = "id";
  private String PREDICATE_OF_ID ="ofId"; 
  private String PREDICATE_NUM_BR_SORTIE="numBRSortie";
  private String PREDICATE_DAE_FACTURE = "daeFacture";
  
  private int MAX_RESULTS = 26;

  @Override
  public ResultatRechecheBonMouvementStockValue rechercherBonMouvementMultiCritere(
    RechercheMulticritereBonMouvementStockValue pRechercheMulticritereMouvementStockValue) {

    CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
    /** entity principale **/
    CriteriaQuery < Object[] > vCriteriaQuery = vBuilder.createQuery(Object[].class);
    List < Predicate > vWhereClause = new ArrayList < Predicate >();

    /** create liste resyltat ***/

    /************ entity jointure *****************/
    Root < BonMouvementEntite > vRootBonMouvement = vCriteriaQuery.from(BonMouvementEntite.class);

    /***************** Predicate *************/
    if (estNonVide(pRechercheMulticritereMouvementStockValue.getnBE())) {
      vWhereClause.add(vBuilder.equal(vRootBonMouvement.get(nBE), pRechercheMulticritereMouvementStockValue.getnBE()));
    }

    if (estNonVide(pRechercheMulticritereMouvementStockValue.getTypeBonMouvement())) {

    	vWhereClause.add(vBuilder.equal(vRootBonMouvement.get(type),
    	        pRechercheMulticritereMouvementStockValue.getTypeBonMouvement()));

    	if(pRechercheMulticritereMouvementStockValue.getTypeBonMouvement().equals("SORTIE") && pRechercheMulticritereMouvementStockValue.isMouvementOF()){
    		vWhereClause.add(vBuilder.notEqual(vRootBonMouvement.get(PREDICATE_NUM_BR_SORTIE),""));
    	}
      
    }

    if (estNonVide(pRechercheMulticritereMouvementStockValue.getEtat())) {
      vWhereClause.add(vBuilder.equal(vRootBonMouvement.get(etat), pRechercheMulticritereMouvementStockValue.getEtat()));
    }

    if (estNonVide(pRechercheMulticritereMouvementStockValue.getFournisseur())) {
      vWhereClause.add(vBuilder.equal(vRootBonMouvement.get(partieInteresse),
        pRechercheMulticritereMouvementStockValue.getFournisseur()));
    }

    /************** date bonMouvement ******************/
    if (pRechercheMulticritereMouvementStockValue.getDateDu() != null) {
      Expression < Calendar > dateMouvement = vRootBonMouvement.get(date);
      Calendar dateEntre = pRechercheMulticritereMouvementStockValue.getDateDu();
      vWhereClause.add(vBuilder.greaterThanOrEqualTo(dateMouvement, dateEntre));
;
    }

    if (pRechercheMulticritereMouvementStockValue.getDateA() != null) {
      Expression < Calendar > dateMouvement = vRootBonMouvement.get(date);
      Calendar dateSortie = pRechercheMulticritereMouvementStockValue.getDateA();
      vWhereClause.add(vBuilder.lessThanOrEqualTo(dateMouvement, dateSortie));
    }

    if ((pRechercheMulticritereMouvementStockValue.getValeurInf() != null)) {
      Expression < Double > rootValeurInf = vRootBonMouvement.get(valeur);
      vWhereClause.add(vBuilder.ge(rootValeurInf, pRechercheMulticritereMouvementStockValue.getValeurInf()));
    }

    if ((pRechercheMulticritereMouvementStockValue.getValeurSupp() != null)) {
      Expression < Double > rootValeurSup = vRootBonMouvement.get(valeur);
      vWhereClause.add(vBuilder.le(rootValeurSup, pRechercheMulticritereMouvementStockValue.getValeurSupp()));
    }

//    if (estNonVide(pRechercheMulticritereMouvementStockValue.getRaisonEntre())) {
//      vWhereClause.add(vBuilder.equal(vRootBonMouvement.get(raisonMouvementId),
//        Long.parseLong(pRechercheMulticritereMouvementStockValue.getRaisonEntre())));
//    }
    if (estNonVide(pRechercheMulticritereMouvementStockValue.getnBLDae())) {
      vWhereClause.add(vBuilder.equal(vRootBonMouvement.get(nBLDae), pRechercheMulticritereMouvementStockValue.getnBLDae()));
    }

    if (estNonVide(pRechercheMulticritereMouvementStockValue.getMagasin())) {
      Join < BonMouvementEntite, MouvementEntite > jointureMvtBmvt = vRootBonMouvement.join(mouvement);
      Join < MouvementEntite, EntiteStockEntite > jointureMvtEnStk = jointureMvtBmvt.join(entiteStock);
      Join < EntiteStockEntite, MagasinEntite > jointureEnStkMagasin = jointureMvtEnStk.join(magasin);
      vWhereClause.add(vBuilder.equal(jointureEnStkMagasin.get(id), pRechercheMulticritereMouvementStockValue.getMagasin()));
    }

    /************** OF ******************/
    if(pRechercheMulticritereMouvementStockValue.getOfId()!=null){
    	  Expression<Long> ofIdExpression =vRootBonMouvement.get(PREDICATE_OF_ID);
		  Long ofId = pRechercheMulticritereMouvementStockValue.getOfId(); 
    	 vWhereClause.add(vBuilder.equal(ofIdExpression,ofId));
    }
    
    
    /************* DAE Facture ******************/
    
    if (estNonVide(pRechercheMulticritereMouvementStockValue.getDaeFacture())) {
    	 Expression<String> path = vRootBonMouvement.get(PREDICATE_DAE_FACTURE);
   	  Expression<String> upper = vBuilder.upper(path);
        vWhereClause.add(vBuilder.like(upper, PERCENT + pRechercheMulticritereMouvementStockValue.getDaeFacture().toUpperCase() + PERCENT));
      }
    
    /** execute query and do something with result **/

    vCriteriaQuery.select(vBuilder.array(
    		vRootBonMouvement.get("id").as(Long.class),
    		vRootBonMouvement.get("numero").as(String.class),
    		vRootBonMouvement.get("date").as(Calendar.class),
    		vRootBonMouvement.get("raisonMouvementId").as(Long.class),
    		vRootBonMouvement.get("valeur").as(Double.class),
    		vRootBonMouvement.get("etat").as(String.class),
    		vRootBonMouvement.get("ofId").as(Long.class),
    		vRootBonMouvement.get("numBRSortie").as(String.class),
    		vRootBonMouvement.get("partieInteresseeId").as(Long.class)    		
    		)).where(vWhereClause.toArray(new Predicate[] {}));
    
    vCriteriaQuery.orderBy(vBuilder.desc(vRootBonMouvement.get("id")));
    
    List<Object[]> resultatEntite = null;
    //If criterias are empty
	if(pRechercheMulticritereMouvementStockValue.isOptimized()){
		resultatEntite = this.entityManager
				.createQuery(vCriteriaQuery)
				.setMaxResults(MAX_RESULTS)
				.getResultList();

	}else{
		resultatEntite = this.entityManager
				.createQuery(vCriteriaQuery)
				.getResultList();
	}
    
    /** Conversion de la liste en valeur */
    List < BonMouvementStockValue > vListeResultat = new ArrayList < BonMouvementStockValue >();
    for (Object[] element : resultatEntite) {
    	
    	BonMouvementEntite vBonMouvementEntite = new BonMouvementEntite();
    	
    	vBonMouvementEntite.setId((Long) element[0]);
    	vBonMouvementEntite.setNumero((String) element[1]);
    	vBonMouvementEntite.setDate((Calendar) element[2]);
    	vBonMouvementEntite.setRaisonMouvementId((Long) element[3]);
    	vBonMouvementEntite.setValeur((Double) element[4]);
    	vBonMouvementEntite.setEtat((String) element[5]);
    	vBonMouvementEntite.setOfId((Long) element[6]);
    	vBonMouvementEntite.setNumBRSortie((String) element[7]);
    	vBonMouvementEntite.setPartieInteresseeId((Long) element[8]);
    	
    	BonMouvementStockValue vBm = PersistanceUtilities.toValue(vBonMouvementEntite);
      
      if(vBonMouvementEntite.getOfId() != null){
    	  OrdreFabricationValue OF = ordreFabricationPersistance.rechercheOrdreFabricationParId(vBonMouvementEntite.getOfId());
    	  
    	  if(OF != null){
    		  vBm.setNumOF(OF.getNumero());
        	  
        	  ProduitValue produit = produitPersistance.rechercheProduitById(OF.getProduitId());
        	  vBm.setRefProduit(produit.getDesignation());
    	  }
    	  
      }
      
      if(vBonMouvementEntite.getNumBRSortie()!=null){
    	  vBm.setDateReservation(this.getDateBonMvt(vBonMouvementEntite.getNumBRSortie()));
      }
      if(vBonMouvementEntite.getPartieInteresseeId()!=null){
    	  PartieInteresseValue pi = partieIntereseePersistance.getById(vBonMouvementEntite.getPartieInteresseeId());
    	  vBm.setClient(pi.getAbreviation());
      }
    	  vListeResultat.add(vBm);
    }

    /** retour de list de recherche et le nombre de resultat **/
    ResultatRechecheBonMouvementStockValue vResultatRechecheBonMouvementStockValue = new ResultatRechecheBonMouvementStockValue();

    vResultatRechecheBonMouvementStockValue.setNombreResultaRechercher(Long.valueOf(vListeResultat.size()));
    
    Collections.sort(vListeResultat, new BonMouvementComparator());
    vResultatRechecheBonMouvementStockValue.setBonMouvementStock(new TreeSet<>(vListeResultat));
    return vResultatRechecheBonMouvementStockValue;
  }

  /**
   * Created by rkhaskhoussy
   * 
   * Création d'un bon de mouvement
   * 
   * @see creerBonMouvement(BonMouvementStockValue)
   */
  @Override
  public String creerBonMouvement(BonMouvementStockValue pBonMouvementStockValue) {
	  

    BonMouvementEntite vBonMouvementEntite = (BonMouvementEntite) toBonMouvementEntity(pBonMouvementStockValue);

    BonMouvementEntite vBonMouvementEntiteResultat = (BonMouvementEntite) this.modifier(vBonMouvementEntite);

    return vBonMouvementEntiteResultat.getId().toString();
  }

  /**
   * To toBonMouvementEntity.
   *
   * @param pBonMouvementStockValue
   *          the bon mouvement stock value
   * @return the bon mouvement entite
   */
  private BonMouvementEntite toBonMouvementEntity(BonMouvementStockValue pBonMouvementStockValue) {

    BonMouvementEntite bonMouvementEntite = new BonMouvementEntite();

    if (pBonMouvementStockValue.getId() != null) {
      bonMouvementEntite.setId(pBonMouvementStockValue.getId());
    }

    bonMouvementEntite.setDate(pBonMouvementStockValue.getDate());
    bonMouvementEntite.setDescription(pBonMouvementStockValue.getDescription());

    if (pBonMouvementStockValue.getMouvements() != null) {
      Set < MouvementEntite > vListMouvementEntite = new HashSet < MouvementEntite >();

      for (MouvementStockValue vMouvementValue : pBonMouvementStockValue.getMouvements()) {

        EntiteStockEntite vEntiteStockMvt = toEntityStock(vMouvementValue.getEntiteStockValue());

        MouvementEntite vMs = toMouvementEntity(vMouvementValue, vEntiteStockMvt);

        vMs.setBonMouvement(bonMouvementEntite);
        vListMouvementEntite.add(vMs);
      }
      bonMouvementEntite.setMouvements(vListMouvementEntite);
    }
    bonMouvementEntite.setNumero(pBonMouvementStockValue.getNumero());
    bonMouvementEntite.setRaisonMouvementId(pBonMouvementStockValue.getRaisonMouvementId());
    bonMouvementEntite.setResponsable(pBonMouvementStockValue.getResponsable());
    bonMouvementEntite.setType(pBonMouvementStockValue.getType());
    bonMouvementEntite.setValeur(pBonMouvementStockValue.getValeur());
    bonMouvementEntite.setEtat(pBonMouvementStockValue.getEtat());
    bonMouvementEntite.setPartieInteresseeId(pBonMouvementStockValue.getPartieintId());
    bonMouvementEntite.setOfId(pBonMouvementStockValue.getOfId());
    bonMouvementEntite.setNumBRSortie(pBonMouvementStockValue.getNumBRSortie());
    bonMouvementEntite.setMethode(pBonMouvementStockValue.getMethode());
    bonMouvementEntite.setDaeFacture(pBonMouvementStockValue.getDaeFacture());
    bonMouvementEntite.setFournisseurId(pBonMouvementStockValue.getFournisseurId());
    
    if (pBonMouvementStockValue.getDocuments() != null) {
        Set < DocumentBonMouvementEntity > vListeDocuments = new HashSet < DocumentBonMouvementEntity >();
        for (DocumentBonMouvementValue vDocumentEntity : pBonMouvementStockValue.getDocuments()) {
        	DocumentBonMouvementEntity vDe = PersistanceUtilities.toEntity(vDocumentEntity);
        	vDe.setBonMouvement(bonMouvementEntite);
          vListeDocuments.add(vDe);
        }
        bonMouvementEntite.setDocuments(vListeDocuments);
      }
    
    return bonMouvementEntite;
  }

  /**
   * To entity.Mouveement
   *
   * @param pMouvementStockValue
   *          the mouvement stock value
   * @return the mouvement entite
   */
  public static MouvementEntite toMouvementEntity(MouvementStockValue pMouvementStockValue, EntiteStockEntite pEntiteStockEntite) {
    MouvementEntite mouvementEntite = new MouvementEntite();
    if (pMouvementStockValue.getId() != null) {
      mouvementEntite.setId(pMouvementStockValue.getId());
    }
    mouvementEntite.setType(pMouvementStockValue.getType());
    mouvementEntite.setCones(pMouvementStockValue.getCones());
    mouvementEntite.setConesReel(pMouvementStockValue.getConesReel());
    mouvementEntite.setDetailsMouvement(pMouvementStockValue.getDetailsMouvement());
    mouvementEntite.setEmplacement(pMouvementStockValue.getEmplacement());
    mouvementEntite.setEntiteStock(pEntiteStockEntite);
    mouvementEntite.setFinCones(pMouvementStockValue.getFincones());
    mouvementEntite.setNbRouleaux(pMouvementStockValue.getNbRouleaux());
    mouvementEntite.setNbRouleauxReel(pMouvementStockValue.getNbRouleauxReel());
    mouvementEntite.setPoids(pMouvementStockValue.getPoids());
    mouvementEntite.setPrixUnitaire(pMouvementStockValue.getPrixUnitaire());
    mouvementEntite.setQuantite(pMouvementStockValue.getQuantite());
    mouvementEntite.setQuantiteReelle(pMouvementStockValue.getQuantiteReelle());
    mouvementEntite.setDescription(pMouvementStockValue.getDescription());
    mouvementEntite.setPoidsReel(pMouvementStockValue.getPoidsReel());
    mouvementEntite.setFinConesReel(pMouvementStockValue.getFinconesReel());
    mouvementEntite.setLot(pMouvementStockValue.getLot());
    mouvementEntite.setQteOF(pMouvementStockValue.getQteOF());
    mouvementEntite.setBesoinOF(pMouvementStockValue.getBesoinOF());
    mouvementEntite.setOa(pMouvementStockValue.getOa());
    mouvementEntite.setObservation(pMouvementStockValue.getObservation());
    mouvementEntite.setTypeMouvement(pMouvementStockValue.getTypeMouvement());
    return mouvementEntite;
  }

  /**
   * to Entity EntityStock
   * 
   * @param pEntiteStockValue
   * @param pMagasinEntite
   * @param pArticleEntite
   * @return
   */
  private EntiteStockEntite toEntityStock(EntiteStockValue pEntiteStockValue) {
    EntiteStockEntite entiteStock = new EntiteStockEntite();
    if (pEntiteStockValue.getId() != null) {
      entiteStock.setId(pEntiteStockValue.getId());
    }
    
    
    if(pEntiteStockValue.getArticle() != null){
    	ArticleEntite article = this.rechercherParId(pEntiteStockValue.getArticle(), ArticleEntite.class);
    	if(article != null){
    		entiteStock.setArticle(article);
    	}
    }
    if(pEntiteStockValue.getMagasin() != null){
    	MagasinEntite magasin = this.rechercherParId(pEntiteStockValue.getMagasin(), MagasinEntite.class);
    	if(magasin != null){
    		entiteStock.setMagasin(magasin);
    	}
    }
    
    entiteStock.setConeReserve(pEntiteStockValue.getConeReserve());
    entiteStock.setConesActuel(pEntiteStockValue.getConesActuel());
    entiteStock.setDateEntree(pEntiteStockValue.getDateEntree());
    entiteStock.setDateLot(pEntiteStockValue.getDateLot());
    entiteStock.setEmplacement(pEntiteStockValue.getEmplacement());
    entiteStock.setEquivalenceCone(pEntiteStockValue.getEquivalenceCone());
    entiteStock.setFinconeActuel(pEntiteStockValue.getFinconeActuel());
    entiteStock.setFinconeReserve(pEntiteStockValue.getFinconeReserve());
    entiteStock.setLibelleArticle(pEntiteStockValue.getLibelleArticle());
    entiteStock.setPmp(pEntiteStockValue.getPmp());
    entiteStock.setPoidsActuel(pEntiteStockValue.getPoidsActuel());
    entiteStock.setPoidsReserve(pEntiteStockValue.getPoidsReserve());
    entiteStock.setPrixUnitaire(pEntiteStockValue.getPrixUnitaire());
    entiteStock.setQteActuelle(pEntiteStockValue.getQteActuelle());
    entiteStock.setQteResrvee(pEntiteStockValue.getQteReservee());
    entiteStock.setReferenceArticle(pEntiteStockValue.getReferenceArticle());
    entiteStock.setReferenceContenaire(pEntiteStockValue.getReferenceContenaire());
    entiteStock.setReferenceLot(pEntiteStockValue.getReferenceLot());
    entiteStock.setRouleauxActuel(pEntiteStockValue.getRouleauxActuel());
    entiteStock.setRouleauxReserve(pEntiteStockValue.getRouleauxReserve());
    entiteStock.setOa(pEntiteStockValue.getOa());
    return entiteStock;
  }

  /****** ancienne methode for modifier bon mouvement ******/
 /* @Override
  public String modifierBonMouvement(BonMouvementStockValue pBonMouvement) {
    BLAchatEntite vblAchat = new BLAchatEntite();
    Set < EntiteStockEntite > vListEntiteStock = new HashSet < EntiteStockEntite >();

    if (pBonMouvement.getBlachatId() != null)
      vblAchat = this.rechercherParId(pBonMouvement.getBlachatId(), BLAchatEntite.class);

    if (pBonMouvement.getMouvements() != null) {
      for (MouvementStockValue mvt : pBonMouvement.getMouvements()) {
        EntiteStockEntite vEntite = this.rechercherParId(mvt.getEntiteStock(), EntiteStockEntite.class);
        vListEntiteStock.add(vEntite);
      }
    }
    BonMouvementEntite vBonMouvementEntite = (BonMouvementEntite) this.modifier(PersistanceUtilities.toEntity(pBonMouvement,
      vblAchat, vListEntiteStock));
    LOGGER.info(" designation " + pBonMouvement.getDescription());
    LOGGER.info(" mouvement " + pBonMouvement.getMouvements().size());
    return vBonMouvementEntite.getId().toString();
  }*/

  /**** list bon mouvement ****/
  @Override
  public List < BonMouvementStockValue > listeBonMouvement() {
    List < BonMouvementEntite > vListBonMouvementEntite = this.lister(BonMouvementEntite.class);
    List < BonMouvementStockValue > vlistBonMouvementValue = new ArrayList < BonMouvementStockValue >();
    for (BonMouvementEntite vBonMouvementEntite : vListBonMouvementEntite) {
      vlistBonMouvementValue.add(PersistanceUtilities.toValue(vBonMouvementEntite));
    }
    
    Collections.sort(vlistBonMouvementValue, new BonMouvementComparator());
    return vlistBonMouvementValue;
  }

  /****** supprimer bon mouvement *****/
  @Override
  public void supprimerBonMouvement(Long pId) {
    this.supprimerEntite(BonMouvementEntite.class, pId.longValue());

  }

  /**
   * Est non vide.
   *
   * @param val
   *          the val
   * @return true, if successful
   */
  private boolean estNonVide(String val) {
    return val != null && !"".equals(val);

  }

  /*
   * (non-Javadoc)
   */
  @Override
  public EntityManager getEntityManager() {
    return entityManager;
  }

  /**
   * Sets the entity manager.
   *
   * @param entityManager
   *          the new entity manager
   */
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /****** get bon mouvement by type ******/
  @Override
  public List < BonMouvementStockValue > getByTypeBonMouvement(String typeBonMouvement) {
    CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
    /** entity principale **/
    CriteriaQuery < BonMouvementEntite > vCriteriaQuery = vBuilder.createQuery(BonMouvementEntite.class);
    /** create liste resyltat ***/

    /************ entity jointure *****************/
    Root < BonMouvementEntite > vRootBonMouvement = vCriteriaQuery.from(BonMouvementEntite.class);
    /** execute query and do something with result **/
    vCriteriaQuery.where((vBuilder.equal(vRootBonMouvement.get(type), typeBonMouvement)));

    List < BonMouvementEntite > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();

    /** Conversion de la liste en valeur */
    List < BonMouvementStockValue > vListeResultat = new ArrayList < BonMouvementStockValue >();

    for (BonMouvementEntite vBonMouvementEntite : resultatEntite) {
      BonMouvementStockValue vBm = PersistanceUtilities.toValue(vBonMouvementEntite);
      vListeResultat.add(vBm);
    }
    
    Collections.sort(vListeResultat,new BonMouvementComparator());
    return vListeResultat;
  }

  /************** recherche par id ******************/
  @Override
  public BonMouvementStockValue rechercheBonMouvementParId(Long pBonMouvementId) {
    BonMouvementEntite vBonMouvementEntite = this.rechercherParId(pBonMouvementId, BonMouvementEntite.class);
    BonMouvementStockValue vBonMouvementValueResult = PersistanceUtilities.toValue(vBonMouvementEntite);
    return vBonMouvementValueResult;
  }
  /**
   * Created by rkhaskhoussy
   * 
   * Création d'un bon de mouvement
   * 
   * @see creerBonMouvement(BonMouvementStockValue)
   */
  @Override
  public String modifierBonMouvement(BonMouvementStockValue pBonMouvementStockValue) {

    BonMouvementEntite vBonMouvementEntite = (BonMouvementEntite) toBonMouvementEntity(pBonMouvementStockValue);

    BonMouvementEntite vBonMouvementEntiteResultat = (BonMouvementEntite) this.modifier(vBonMouvementEntite);

    return vBonMouvementEntiteResultat.getId().toString();
  }

  //Added on 09/11/2016, by Zeineb Medimagh
  
	@Override
	public List<ResultatListeBonMvtParTypeValue> getListeNumerosParType(String typeParam) {
		
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery < BonMouvementEntite > vCriteriaQuery = vBuilder.createQuery(BonMouvementEntite.class);
	    List < Predicate > vWhereClause = new ArrayList < Predicate >();
	    Root < BonMouvementEntite > vRootBonMouvement = vCriteriaQuery.from(BonMouvementEntite.class);
	
	    
	    /***************** Predicate *************/
	    
	    
	    if (estNonVide(type)) {
	      vWhereClause.add(vBuilder.equal(vRootBonMouvement.get(type), typeParam.toUpperCase()));
	    }
	
	    /** execute query and do something with result **/
	
	    vCriteriaQuery.select(vRootBonMouvement).where(vWhereClause.toArray(new Predicate[] {}));
	    List < BonMouvementEntite > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();
	
	    
	    /** Conversion et création de la liste des numéros bon mouvement */
	    
	    List < ResultatListeBonMvtParTypeValue > vListeResultat = new ArrayList < ResultatListeBonMvtParTypeValue >();
	    
	    for (BonMouvementEntite vBonMouvementEntite : resultatEntite) {
	    	
	    	if(vBonMouvementEntite.getNumero()!=null){
	    		ResultatListeBonMvtParTypeValue objet = new ResultatListeBonMvtParTypeValue(
	    				vBonMouvementEntite.getId(),
						vBonMouvementEntite.getNumero(),
						vBonMouvementEntite.getOfId());

	    		vListeResultat.add(objet);
	    	}
	    	
	    }
	    
	    Collections.sort(vListeResultat);
	
	    return vListeResultat;
		
	}

	@Override
	public BonMouvementStockValue rechercheBonMouvementParNum(String pBonMouvementNum) {
		
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery < BonMouvementEntite > vCriteriaQuery = vBuilder.createQuery(BonMouvementEntite.class);
	    List < Predicate > vWhereClause = new ArrayList < Predicate >();
	    Root < BonMouvementEntite > vRootBonMouvement = vCriteriaQuery.from(BonMouvementEntite.class);
	
	    
	    /***************** Predicate *************/
	    
	    
	    if (estNonVide(pBonMouvementNum)) {
	      vWhereClause.add(vBuilder.equal(vRootBonMouvement.get(nBE), pBonMouvementNum));
	    }
	
	    /** execute query and do something with result **/
	
	    vCriteriaQuery.select(vRootBonMouvement).where(vWhereClause.toArray(new Predicate[] {}));
	    List < BonMouvementEntite > resultatEntite = this.entityManager.createQuery(vCriteriaQuery).getResultList();
	
	    
	    /** Conversion et création de la liste des numéros bon mouvement */
	    
	    BonMouvementStockValue resultat = new BonMouvementStockValue();
	    
	    if(resultatEntite.get(0) != null){
	    	resultat = PersistanceUtilities.toValue(resultatEntite.get(0));

	    }
	    
	    return resultat;
		
	}
	
	private Calendar getDateBonMvt(String numBonMvt) {
		
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery < Calendar > vCriteriaQuery = vBuilder.createQuery(Calendar.class);
	    List < Predicate > vWhereClause = new ArrayList < Predicate >();
	    Root < BonMouvementEntite > vRootBonMouvement = vCriteriaQuery.from(BonMouvementEntite.class);
	
	    
	    /***************** Predicate *************/
	    
	    
	    if (estNonVide(numBonMvt)) {
	      vWhereClause.add(vBuilder.equal(vRootBonMouvement.get(nBE), numBonMvt));
	    }
	
	    /** execute query and do something with result **/
	
	    vCriteriaQuery.select(vRootBonMouvement.get("date").as(Calendar.class)).where(vWhereClause.toArray(new Predicate[] {}));
	    List<Calendar>  data= this.entityManager.createQuery(vCriteriaQuery).getResultList();
	
	    if(data.size() > 0){
	    	return data.get(0);
	    }
	    return null;
		
	}

	@Override
	public Long getOFByBonMouvementId(Long bonMouvementId) {
		
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery < Long > vCriteriaQuery = vBuilder.createQuery(Long.class);
	    List < Predicate > vWhereClause = new ArrayList < Predicate >();
	    Root < BonMouvementEntite > vRootBonMouvement = vCriteriaQuery.from(BonMouvementEntite.class);
	
	    
	    /***************** Predicate *************/
	    
	    
	    if (bonMouvementId != null) {
	      vWhereClause.add(vBuilder.equal(vRootBonMouvement.get(id), bonMouvementId));
	    }
	
	    /** execute query and do something with result **/
	
	    vCriteriaQuery.select(vRootBonMouvement.get("ofId").as(Long.class)).where(vWhereClause.toArray(new Predicate[] {}));
	    Long ofId= null;
	    ofId = this.entityManager.createQuery(vCriteriaQuery).getResultList().get(0);
	
	    return ofId;
	}

	@Override
	public boolean ofIsReserved(Long ofId) {
		
		CriteriaBuilder vBuilder = this.entityManager.getCriteriaBuilder();
	    CriteriaQuery < Long > vCriteriaQuery = vBuilder.createQuery(Long.class);
	    List < Predicate > vWhereClause = new ArrayList < Predicate >();
	    Root < BonMouvementEntite > vRootBonMouvement = vCriteriaQuery.from(BonMouvementEntite.class);
	
	    
	    /***************** Predicate *************/
	    
	    
	    if (ofId != null) {
	      vWhereClause.add(vBuilder.equal(vRootBonMouvement.get(PREDICATE_OF_ID), ofId));
	      vWhereClause.add(vBuilder.equal(vRootBonMouvement.get(type), "RESERVATION"));

	    }
	
	    /** execute query and do something with result **/
	
	    vCriteriaQuery.select(vRootBonMouvement.get("id").as(Long.class)).where(vWhereClause.toArray(new Predicate[] {}));
	    List<Long> resultList = this.entityManager.createQuery(vCriteriaQuery).getResultList();
	    
	    if(resultList.isEmpty()){
	    	return false;
	    }else{
	    	return true;
	    }
	}

}
