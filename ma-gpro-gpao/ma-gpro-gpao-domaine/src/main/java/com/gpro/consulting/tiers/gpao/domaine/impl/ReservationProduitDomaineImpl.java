package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.ArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.ElementBesoinValue;
import com.gpro.consulting.tiers.commun.coordination.value.FamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.FicheBesoinValue;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleArticleValue;
import com.gpro.consulting.tiers.commun.coordination.value.TypeArticleValue;
import com.gpro.consulting.tiers.commun.persistance.IArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.IFamilleArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.IFicheBesoinPersistance;
import com.gpro.consulting.tiers.commun.persistance.ISousFamilleArticlePersistance;
import com.gpro.consulting.tiers.commun.persistance.ITypeArticlePersistance;
import com.gpro.consulting.tiers.gpao.coordination.reservationproduit.value.ReservationProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.value.DetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.vue.MouvementOfVue;
import com.gpro.consulting.tiers.gpao.domaine.IReservationProduitDomaine;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gs.coordination.value.EntiteStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.MouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheMouvementValue;
import com.gpro.consulting.tiers.gs.persitance.IEntiteStockPersistance;
import com.gpro.consulting.tiers.gs.persitance.IMouvementPersistance;

/**
 * implementation of {@link IReservationProduitDomaine}
 * 
 * @author Wahid Gazzah
 * @since 09/05/2016
 *
 */

@Component
public class ReservationProduitDomaineImpl implements IReservationProduitDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(ReservationProduitDomaineImpl.class);
	
	private static final Double ZEROD = 0D;
	private static final Long ZEROL = 0L;
	private static final String TYPE_RESERVATION = "RESERVATION";
	
	@Autowired
	private IFicheBesoinPersistance ficheBesoinPersistance;
	
	@Autowired
	private IOrdreFabricationPersistance ordreFabricationPersistance;

	@Autowired
	private IArticlePersistance articlePersistance;
	
	@Autowired
	private IEntiteStockPersistance entiteStockPersistance;
	
	@Autowired
	IMouvementPersistance mouvementPersistance;
	
	@Autowired
	ISousFamilleArticlePersistance sousFamilleArticlePersistance;
	
	@Autowired
	ITypeArticlePersistance typeArticlePersistance;
	
	@Autowired
	IFamilleArticlePersistance familleArticlePersistance;
	

	public List<MouvementOfVue> getByOrdreFabricationId(Long ordreFabricationId, Long magasinId) {
		
		List<MouvementOfVue> finalListResult = new ArrayList<MouvementOfVue>();
		
		List<ReservationProduitValue> listReservationProduit = new ArrayList<ReservationProduitValue>();

		OrdreFabricationValue ordreFabrication = ordreFabricationPersistance.rechercheOrdreFabricationParId(ordreFabricationId);
		
		if(ordreFabrication != null){
			
			List<DetailOfValue> listDetailOf = new ArrayList<DetailOfValue>();
			
			for(DetailOfValue detailOf : ordreFabrication.getDetailsOF()){
			
				listDetailOf.add(detailOf);
			
			}
			
			Long produitId = ordreFabrication.getProduitId();
			FicheBesoinValue ficheBesoin = ficheBesoinPersistance.rechercheFicheBesoinParId(produitId);

			if(ficheBesoin != null){
				
				for(ElementBesoinValue elementBesoin : ficheBesoin.getElementBesoinValue()){
					
					ReservationProduitValue reservationProduit = new ReservationProduitValue();
		    		
		    		Double besoin = ZEROD;
		    		Long qteOF=calculQuantiteDetailsOF(listDetailOf, elementBesoin);
	    			besoin = elementBesoin.getQuantite() * qteOF;
					
		    		reservationProduit.setBesoin(besoin);
		    		reservationProduit.setType(elementBesoin.getType());
		    		reservationProduit.setQuantite(qteOF);
		    		reservationProduit.setBesoinUnitaire(elementBesoin.getQuantite());
		    		
		    		if(elementBesoin.getArticle() != null){
		    			
		    			Long articleId = elementBesoin.getArticle().getId();
		    			
		    			reservationProduit.setArticleId(articleId);
		    			
		    			if(articleId != null){
		    				
		    				ArticleValue article = articlePersistance.getArticleParId(articleId);
		    				
		    				if(article != null){
		    					
			    				reservationProduit.setArticleDesignation(article.getDesignation());
			    				reservationProduit.setArticleReference(article.getRef());
			    				//DISCUSS
								//articleId > sousFamilleId > familleId > typeArticleId
			    				if(article.getSousFamilleArtEntite() != null){
			    					SousFamilleArticleValue sousFamilleArticle = sousFamilleArticlePersistance.rechercheSousFamilleArticleById(article.getSousFamilleArtEntite());
			    					if(sousFamilleArticle.getIdFamilleArticle() != null){
			    						FamilleArticleValue familleArticle = familleArticlePersistance.rechercheFamilleArticleById(sousFamilleArticle.getIdFamilleArticle());
			    						reservationProduit.setArticleFamille(familleArticle.getDesignation());
			    						if(familleArticle.getIdTypeArticle() != null){
			    							TypeArticleValue typeArticle = typeArticlePersistance.rechercheTypeArticleById(familleArticle.getIdTypeArticle());
			    							reservationProduit.setTypeArticle(typeArticle.getId());
			    						}
			    					
			    					}
			    				}
			    				
		    				}
		    				
		    			}
		    		}
		    		
		    		listReservationProduit.add(reservationProduit);
				}
				//Groupement par articleId
				
				//Map<articleId, ReservationProduitValue>
				
				Map<Long,ReservationProduitValue> mapReservation = new HashMap<Long,ReservationProduitValue>();
				
				for (ReservationProduitValue reservationProduit : listReservationProduit) {
					
					if(mapReservation.containsKey(reservationProduit.getArticleId())){
						
						ReservationProduitValue currentReservationProduit = mapReservation.get(reservationProduit.getArticleId());
						Long currentQuantite = currentReservationProduit.getQuantite();
						Double currentBesoin = currentReservationProduit.getBesoin();
						
						currentReservationProduit.setQuantite(currentQuantite+ reservationProduit.getQuantite());
						currentReservationProduit.setBesoin(currentBesoin + reservationProduit.getBesoin());
						
						mapReservation.remove(reservationProduit.getArticleId());
						mapReservation.put(reservationProduit.getArticleId(), currentReservationProduit);
						
					}else{
						mapReservation.put(reservationProduit.getArticleId(), reservationProduit);
					}
					
				}
				
				Iterator it = mapReservation.entrySet().iterator();
				
				while(it.hasNext()){
					
					@SuppressWarnings("unchecked")
					Map.Entry <Long, ReservationProduitValue> pair = (Map.Entry<Long, ReservationProduitValue>)it.next();
					
					ReservationProduitValue reservationProduit = pair.getValue();
					
					List<EntiteStockValue> result = entiteStockPersistance.getByArticleIdMagasinId(reservationProduit.getArticleId(), magasinId);
					
					if(result != null){
						
						List<MouvementOfVue> finalElementResult = new ArrayList<MouvementOfVue>();
				
						for(EntiteStockValue entiteStock : result){
							
							MouvementOfVue mouvementOfVue = new MouvementOfVue();
							
							mouvementOfVue.setReferenceArticle(reservationProduit.getArticleReference());
							mouvementOfVue.setDesignationArticle(reservationProduit.getArticleDesignation());
							mouvementOfVue.setFamilleArticle(reservationProduit.getArticleFamille());
							
							mouvementOfVue.setType(reservationProduit.getType());
							mouvementOfVue.setBesoinOF(reservationProduit.getBesoin());
							mouvementOfVue.setQteOF(reservationProduit.getQuantite());
							
							mouvementOfVue.setQuantiteAct(entiteStock.getQteActuelle());
							mouvementOfVue.setEmplacement(entiteStock.getEmplacement());
							mouvementOfVue.setEntiteStock(entiteStock.getId());
							
							mouvementOfVue.setTypeArticle(reservationProduit.getTypeArticle());
							
							mouvementOfVue.setReservationOF(calculerReservationOF(entiteStock.getId(), ordreFabricationId));

							if(entiteStock.getQteActuelle() !=null && entiteStock.getQteReservee() != null){
								mouvementOfVue.setQuantiteLibre(entiteStock.getQteActuelle() - entiteStock.getQteReservee());
							}
							
							if(reservationProduit.getBesoin() !=null && mouvementOfVue.getQuantiteLibre() != null){
								mouvementOfVue.setManque((reservationProduit.getBesoin() - mouvementOfVue.getQuantiteLibre()) < ZEROD ? true : false);
							}
							
							if(reservationProduit.getBesoin() !=null && mouvementOfVue.getReservationOF() != null){
								
								Double aReserver = reservationProduit.getBesoin() - mouvementOfVue.getReservationOF();
								
								mouvementOfVue.setaReserver((aReserver > 0 ?aReserver : ZEROD) );
								
							}
//							mouvementOfVue.setReservationAct();
							
							mouvementOfVue.setLot(entiteStock.getReferenceLot());
							mouvementOfVue.setDateEntree(entiteStock.getDateEntree());
							mouvementOfVue.setBu(reservationProduit.getBesoinUnitaire());
							finalElementResult.add(mouvementOfVue);
						
						
							//System.out.println("*-*-**    Article :  "+mouvementOfVue.getReferenceArticle());
						 
							//System.out.println("*-*-**    Qte OF :  "+mouvementOfVue.getQteOF());
							 
						
						
						
						
						}
						
						finalListResult.addAll(finalElementResult);
					}
					
				}
				
			}
		}

		return finalListResult;
	}

	private Double calculerReservationOF(Long entiteStockId, Long ordreFabricationId) {
		
		Double reservationOF = ZEROD;
		
		RechercheMulticritereMouvementValue request = new RechercheMulticritereMouvementValue();
		request.setHistorique(TYPE_RESERVATION);
		request.setOfId(ordreFabricationId);
		request.setEntiteStockId(entiteStockId);
		
		ResultatRechecheMouvementValue result = mouvementPersistance.rechercherMouvementMultiCritere(request);
		
		if(result != null){
			
			for(MouvementStockValue mouvementStock : result.getMouvementStock()){
				
				if(mouvementStock.getQuantiteReelle() != null){
					
					reservationOF = reservationOF + mouvementStock.getQuantiteReelle();
				}
					
			}
		}
		//System.out.println("###### RESERVATION  OF  :   "+reservationOF);
		return reservationOF;
	}

	private Long calculQuantiteDetailsOF(List<DetailOfValue> list,	ElementBesoinValue element) {
		
		Long quantite = ZEROL;
		
		for (DetailOfValue detail : list){
			
			if(	detail.getCouleurId().equals(element.getEb_couleur_id()) 
					&& detail.getTailleId().equals(element.getEb_taille_id())){
				//System.out.println(" list ELEMENT  +"+element.getArticle().getDesignation()+  "   Qte  "+detail.getQuantite());
				return detail.getQuantite();
				
			}
		}
		
		//System.out.println(" apres ELEMENT  +"+element.getArticle().getDesignation()+  "   Qte  "+quantite);
		
		return quantite;
		
	}

}
