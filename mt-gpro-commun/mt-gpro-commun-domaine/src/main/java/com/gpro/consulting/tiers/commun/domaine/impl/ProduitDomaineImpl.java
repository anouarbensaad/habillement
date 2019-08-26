package com.gpro.consulting.tiers.commun.domaine.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gpro.consulting.tiers.commun.coordination.value.FicheBesoinValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitTailleValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.StandardTailleValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.domaine.IProduitDomaine;
import com.gpro.consulting.tiers.commun.domaine.IStandardTailleDomaine;
import com.gpro.consulting.tiers.commun.persistance.IFicheBesoinPersistance;
import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.IStandardTaillePersistance;
import com.gpro.consulting.tiers.commun.persistance.ITaillePersistance;
// TODO: Auto-generated Javadoc

/**
 * The Class ProduitDomaineImpl. *@author med
 */
public class ProduitDomaineImpl implements IProduitDomaine {

	private static final Logger logger = LoggerFactory.getLogger(ProduitDomaineImpl.class);

	/** The produit persistance. */
	@Autowired
	IProduitPersistance produitPersistance;

	@Autowired
	ITaillePersistance ebTaillePersistance;

	@Autowired
	IFicheBesoinPersistance ficheBesoinPersistance;
	
	
	
//	@Autowired
//	IFicheBesoinDomaine ficheBesoinDomaine;

	/*
	 * (non-Javadoc) creer produit
	 */
	@Override
	public String creerProduit(ProduitValue pProduitValue) {
		
		//Ajout de taille de produit 
		List<ProduitTailleValue> listProduitTaille=new ArrayList<ProduitTailleValue>();
		
	   	if(pProduitValue.getIdStandard()!=null && (pProduitValue.getTailleProduit()==null||pProduitValue.getTailleProduit().size()==0))
		  { List<TailleValue> listTaille =ebTaillePersistance.rechercherTailleByStandard(pProduitValue.getIdStandard());
		     for (TailleValue taille:listTaille)  {
		    	 ProduitTailleValue produitTaille=new ProduitTailleValue();
		    	 produitTaille.setEbTailleId(taille.getId());
		    	 produitTaille.setTailleOrdre(taille.getOrdre());
		    	 listProduitTaille.add(produitTaille);
		     }
		    pProduitValue.setTailleProduit(listProduitTaille);
		     
		  
		    
		  }
		
		//TODO TEST 
		//return "test";
		return produitPersistance.creerProduit(pProduitValue);
	}

	/*
	 * (non-Javadoc) supprimer produit
	 */
	@Override
	public void supprimerProduit(Long pProduitId) {
		produitPersistance.supprimerProduit(pProduitId);
	}

	/*
	 * (non-Javadoc) modifier produit
	 */
	@Override
	public String modifierProduit(ProduitValue pProduitValue) {
		return produitPersistance.modifierProduit(pProduitValue);
	}

	@Override
	public ProduitValue rechercheProduitById(Long pProduitId) {
		return produitPersistance.rechercheProduitById(pProduitId);
	}
	
	/*
	 * (non-Javadoc) recherche by id produit
	 */
	@Override
	public ProduitValue rechercheProduitById(Long pProduitId, boolean allegee) {

		return produitPersistance.rechercheProduitById(pProduitId, allegee);
	}

	/*
	 * (non-Javadoc) liste produit
	 */
	@Override
	public List<ProduitValue> listeProduit() {

		List<ProduitValue> list = produitPersistance.listeProduit();
		Collections.sort(list);

		return list;
	}

	/**
	 * Gets the produit persistance.
	 *
	 * @return the produit persistance
	 */
	public IProduitPersistance getProduitPersistance() {
		return produitPersistance;
	}

	/**
	 * Sets the produit persistance.
	 *
	 * @param produitPersistance
	 *            the new produit persistance
	 */
	public void setProduitPersistance(IProduitPersistance produitPersistance) {
		this.produitPersistance = produitPersistance;
	}
	
//	@Override
//	public ResultatRechecheProduitValue rechercherProduitMultiCritere(
//		RechercheMulticritereProduitValue pRechercheProduitMulitCritere) {
//		return produitPersistance.rechercherProduitMultiCritere(pRechercheProduitMulitCritere);
//			
//	}

	@Override
	public ResultatRechecheProduitValue rechercherProduitMultiCritere(
		RechercheMulticritereProduitValue pRechercheProduitMulitCritere) {
		
		//ResultatRechecheProduitValue listProduit=new ResultatRechecheProduitValue();
		//TODO 
		ResultatRechecheProduitValue listProduit = produitPersistance.rechercherProduitMultiCritere(pRechercheProduitMulitCritere);
		
//			logger.info("----------listFB:getAll ** ** **-------------"+listFB);
//				if(pRechercheProduitMulitCritere.getFicheB()) {
//					logger.info("----------domaine33-------------");
//
//				for (ProduitValue produitValue : listProduit.getProduitValues()) {
//					
//					for (FicheBesoinValue ficheBesoinValue : listFB) {
//						
//						if (ficheBesoinValue.getIdFicheBesoin().equals(produitValue.getId())) {
//							
//							listProduitFB.add(produitValue);
//							logger.info("----------listProduitFB-----oui--------"+listProduitFB);
//
//
//						
//					}
//				}
//			}
//			
//		}else{
//			
//			  for (ProduitValue produitValue : listProduit.getProduitValues()) {
//					
//				  for (FicheBesoinValue ficheBesoinValue : listFB) {
//						
//						if (!ficheBesoinValue.getIdFicheBesoin().equals(produitValue.getId())) {
//							
//							listProduitFB.add(produitValue);
//							logger.info("----------listProduitFB-----non--------"+listProduitFB);
//
//						}
//					}
//			 }
//		}
//				result.setProduitValues(listProduitFB);
//				return result ;	
//	}
		
		return listProduit;
				
	}

	public ITaillePersistance getEbTaillePersistance() {
		return ebTaillePersistance;
	}

	public void setEbTaillePersistance(ITaillePersistance ebTaillePersistance) {
		this.ebTaillePersistance = ebTaillePersistance;
	}
	
	
	

}
