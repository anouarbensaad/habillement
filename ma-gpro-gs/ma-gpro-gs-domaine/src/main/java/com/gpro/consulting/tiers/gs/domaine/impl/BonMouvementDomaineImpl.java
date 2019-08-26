
package com.gpro.consulting.tiers.gs.domaine.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.ArticleValue;
import com.gpro.consulting.tiers.commun.domaine.IArticleDomaine;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.vue.MouvementOfVue;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gs.coordination.IConstante;
import com.gpro.consulting.tiers.gs.coordination.guichet.value.GuichetAnnuelValue;
import com.gpro.consulting.tiers.gs.coordination.value.BonMouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.EntiteStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.MouvementStockSupprime;
import com.gpro.consulting.tiers.gs.coordination.value.MouvementStockSupprimeElement;
import com.gpro.consulting.tiers.gs.coordination.value.MouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereBonMouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.RechercheMulticritereMouvementValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatListeBonMvtParTypeValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheBonMouvementStockValue;
import com.gpro.consulting.tiers.gs.coordination.value.ResultatRechecheMouvementValue;
import com.gpro.consulting.tiers.gs.domaine.IBonMouvementDomaine;
import com.gpro.consulting.tiers.gs.domaine.guichet.IGuichetAnnuelDomaine;
import com.gpro.consulting.tiers.gs.persitance.IBonMouvementPersistance;
import com.gpro.consulting.tiers.gs.persitance.IEntiteStockPersistance;
import com.gpro.consulting.tiers.gs.persitance.IMouvementPersistance;

@Component
public class BonMouvementDomaineImpl implements IBonMouvementDomaine {

	private final static Long ZEROL = 0L;
	private final static Double ZEROD = 0D;
	private final static String STANDARD = "STANDARD";
	private final static String LOT = "LOT";
	private final static String ENTREE = "ENTREE";
	private static final String TYPE_RESERVATION = "RESERVATION";

	@Autowired
	private IBonMouvementPersistance bonMouvementPersistance;

	@Autowired
	private IEntiteStockPersistance entiteStockPersistance;

	@Autowired
	private IArticleDomaine articleDomaine;

	@Autowired
	private IOrdreFabricationPersistance ordreFabricationPersistance;

	@Autowired
	private IMouvementPersistance mouvementPersistance;
	
	@Autowired
	private IGuichetAnnuelDomaine guichetAnnuelDomaine;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BonMouvementDomaineImpl.class);

	@Override
	public ResultatRechecheBonMouvementStockValue rechercherBonMouvementMultiCritere(
			RechercheMulticritereBonMouvementStockValue pRechercheMulticritereMouvementStockValue) {

		return bonMouvementPersistance.rechercherBonMouvementMultiCritere(pRechercheMulticritereMouvementStockValue);
	}

	@Override
	public String creerBonMouvement(BonMouvementStockValue bonMouvementStock) {

		BonMouvementStockValue request = new BonMouvementStockValue();

		if (bonMouvementStock.getType() != null) {

			switch (bonMouvementStock.getType()) {
			case IConstante.TYPE_MVT_ENT:
				request = enrichirBonEntree(bonMouvementStock);
				break;

			case IConstante.TYPE_MVT_SORT:
				request = enrichirBonSortie(bonMouvementStock);
				break;

			case IConstante.TYPE_MVT_RES:
				request = enrichirReservation(bonMouvementStock);
				break;
				
			case IConstante.TYPE_MVT_RET:
				request = enrichirBonRetour(bonMouvementStock);
				break;

			default:
				break;

			}
		}

		return bonMouvementPersistance.creerBonMouvement(request);
	}

	public Double calculerEquivalenceCone(EntiteStockValue pEntiteStock) {
		Double vEquivalenceCone = new Double(0);
		return vEquivalenceCone;
	}

	public Double calculerPrixMoyenPondere(EntiteStockValue pEntiteStock, MouvementStockValue pMouvementStock) {
		Double vPMP = new Double(0);
		return vPMP;
	}

	public BonMouvementStockValue enrichirBonEntree(BonMouvementStockValue pBonMouvementStockValue) {

		BonMouvementStockValue vBonMouvementStockValue = pBonMouvementStockValue;
		Set<MouvementStockValue> vListMouvementResultat = new HashSet<MouvementStockValue>();

		if((( pBonMouvementStockValue.getNumero()==null || pBonMouvementStockValue.getNumero().equals("") &&pBonMouvementStockValue.getNumero()!=null ))){
			pBonMouvementStockValue.setNumero(this.getNumeroBonEntree(pBonMouvementStockValue.getDate()));
		}
		
		for (MouvementStockValue mouvement : vBonMouvementStockValue.getMouvements()) {

			// A changer
			ArticleValue vArticleValue = new ArticleValue();
			vArticleValue.setId(mouvement.getIdArticle());
			vArticleValue = articleDomaine.rechercheArticleParId(vArticleValue);

			EntiteStockValue vEntiteStock = new EntiteStockValue();

            // Modifié par Ghazi 24092017 - forcer methode de gestion à Standard si elle n'existe pas 
			
			if (vArticleValue.getMethodeGestion()==null || (vArticleValue.getMethodeGestion()!=null && !vArticleValue.getMethodeGestion().equals(LOT) && !vArticleValue.getMethodeGestion().equals(ENTREE)))
			   vArticleValue.setMethodeGestion(STANDARD);
			if (vArticleValue.getMethodeGestion() != null && vArticleValue.getMethodeGestion().equals(STANDARD)) {
				vEntiteStock = entiteStockPersistance.rechercheEntiteStockByArticleMagasin(mouvement.getIdArticle(),
						mouvement.getIdMagasin());
			}
			if (vArticleValue.getMethodeGestion() != null && vArticleValue.getMethodeGestion().equals(LOT)
					&& mouvement.getLot() != null) {
				vEntiteStock = entiteStockPersistance.rechercheEntiteStockByLotMagasin(mouvement.getIdArticle(),
						mouvement.getLot(), mouvement.getIdMagasin());
			}
			if (vArticleValue.getMethodeGestion() != null && vArticleValue.getMethodeGestion().equals(ENTREE)
					&& mouvement.getLot() != null) {
				vEntiteStock = entiteStockPersistance.rechercheEntiteStockByLotDateMagasin(mouvement.getIdArticle(),
						mouvement.getLot(), mouvement.getIdMagasin(), pBonMouvementStockValue.getDate());
			}

			// Creation de l'entité stock si elle n'existe pas
			if (vEntiteStock == null) {

				vEntiteStock = new EntiteStockValue();
				vEntiteStock.setArticle(mouvement.getIdArticle());
				
				if (vArticleValue != null){
					vEntiteStock.setReferenceArticle(vArticleValue.getRef());
					vEntiteStock.setLibelleArticle(vArticleValue.getDesignation());
				}
			
				vEntiteStock.setMagasin(mouvement.getIdMagasin());
				vEntiteStock.setPmp(new Double(0));
				vEntiteStock.setPrixUnitaire(new Double(0));
				vEntiteStock.setEmplacement(IConstante.VIDE);
				if (mouvement.getTypeArticle().equals(3L)) {
					vEntiteStock.setConeReserve(new Long(0));
					vEntiteStock.setConesActuel(new Long(0));
					vEntiteStock.setFinconeActuel(new Long(0));
					vEntiteStock.setFinconeReserve(new Long(0));
					vEntiteStock.setPoidsActuel(new Double(0));
					vEntiteStock.setPoidsReserve(new Double(0));
					vEntiteStock.setEquivalenceCone(new Double(0));
				} else {
					vEntiteStock.setQteActuelle(new Double(0));
					vEntiteStock.setQteReservee(new Double(0));
					if (mouvement.getTypeArticle().equals(2L)) {
						vEntiteStock.setRouleauxActuel(new Long(0));
						vEntiteStock.setRouleauxReserve(new Long(0));
					}
				}
				// entiteStockPersistance.creerEntiteStock(vEntiteStock);
				if (!vArticleValue.getMethodeGestion().equals(STANDARD)) {
					if (mouvement.getLot() != null) {
						vEntiteStock.setReferenceLot(mouvement.getLot());
						vEntiteStock.setDateLot(pBonMouvementStockValue.getDate());
						vEntiteStock.setDateEntree(pBonMouvementStockValue.getDate());
					
					}
					if (vArticleValue.getMethodeGestion().equals(ENTREE))
						vEntiteStock.setDateEntree(pBonMouvementStockValue.getDate());
				}
				if(mouvement.getOa() != null){
					vEntiteStock.setOa(mouvement.getOa());
				}
			}else{

				if(mouvement.getOa() != null){
					vEntiteStock.setOa(mouvement.getOa());
				}
				
			}
			

			// // Mise à jour de l'entité Stock
			// vEntiteStock =
			// entiteStockPersistance.rechercheEntiteStockByArticleMagasin(mouvement.getIdArticle(),
			// mouvement.getIdMagasin());
			if (mouvement.getTypeArticle().equals(3L)) {
				if (mouvement.getConesReel() != null)
					vEntiteStock.setConesActuel(vEntiteStock.getConesActuel() + mouvement.getConesReel());
				if (mouvement.getFinconesReel() != null) {
					vEntiteStock.setFinconeActuel(vEntiteStock.getFinconeActuel() + mouvement.getFinconesReel());
				}
				if (vEntiteStock.getPoidsActuel() != null && mouvement.getPoidsReel() != null) {
					vEntiteStock.setPoidsActuel(new Double(vEntiteStock.getPoidsActuel() + mouvement.getPoidsReel()));
				}
				vEntiteStock.setEquivalenceCone(calculerEquivalenceCone(vEntiteStock));

			} else {
				if (vEntiteStock.getQteActuelle() != null && mouvement.getQuantiteReelle() != null) {

					vEntiteStock.setQteActuelle(vEntiteStock.getQteActuelle() + mouvement.getQuantiteReelle());
					
					if (mouvement.getTypeArticle().equals(2L) && mouvement.getNbRouleauxReel()!= null) {
						vEntiteStock
								.setRouleauxActuel(vEntiteStock.getRouleauxActuel() + mouvement.getNbRouleauxReel());
					}
				}

			}
			// ArticleValue ar
			if (vEntiteStock.getEmplacement() != null && !vEntiteStock.getEmplacement().equals(IConstante.VIDE))
				vEntiteStock.setEmplacement(vEntiteStock.getEmplacement() + IConstante.SEPARATEUR);
			if(mouvement.getEmplacement()!=null){
				vEntiteStock.setEmplacement(vEntiteStock.getEmplacement() + mouvement.getEmplacement());
			}
			vEntiteStock.setPrixUnitaire(mouvement.getPrixUnitaire());
			vEntiteStock.setPmp(calculerPrixMoyenPondere(vEntiteStock, mouvement));
			// entiteStockPersistance.modifierEntiteStock(vEntiteStock);
			mouvement.setEntiteStock(vEntiteStock.getId());
			mouvement.setEntiteStockValue(vEntiteStock);
			// mouvement.setPrixTotal(prixTotal);
			vListMouvementResultat.add(mouvement);
		}
		vBonMouvementStockValue.setMouvements(vListMouvementResultat);
		return vBonMouvementStockValue;
	}
	
	//enrichirBonEntreeRetour
	public BonMouvementStockValue enrichirBonRetour(BonMouvementStockValue pBonMouvementStockValue) {

		BonMouvementStockValue vBonMouvementStockValue = pBonMouvementStockValue;
		Set<MouvementStockValue> vListeMouvement = new HashSet<MouvementStockValue>();

		if(pBonMouvementStockValue.getNumero()==null || (( pBonMouvementStockValue.getNumero().equals("") &&pBonMouvementStockValue.getNumero()!=null ))){
			pBonMouvementStockValue.setNumero(this.getNumeroBonEntree(pBonMouvementStockValue.getDate()));
		}
		
		for (MouvementStockValue mouvement : vBonMouvementStockValue.getMouvements()) {
			// A changer
			// MouvementStockValue mouvement;
			EntiteStockValue vEntiteStock = entiteStockPersistance
					.rechercheEntiteStockParId(mouvement.getEntiteStock());

			if (mouvement.getTypeArticle().equals(3L)) {
				if (mouvement.getConesReel() != null && vEntiteStock.getConesActuel() != null)
						vEntiteStock.setConesActuel(vEntiteStock.getConesActuel() + mouvement.getConesReel());
				// TODO Else exception
				if (mouvement.getFinconesReel() != null && mouvement.getPoidsReel() != null
						&& vEntiteStock.getFinconeActuel() != null && vEntiteStock.getPoidsActuel() != null) {
						vEntiteStock.setFinconeActuel(vEntiteStock.getFinconeActuel() + mouvement.getFinconesReel());
						vEntiteStock
								.setPoidsActuel(new Double(vEntiteStock.getPoidsActuel() + mouvement.getPoidsReel()));
						vEntiteStock.setEquivalenceCone(calculerEquivalenceCone(vEntiteStock));
				}

			} else {
				if (vEntiteStock.getQteActuelle() != null && mouvement.getQuantiteReelle() != null)
						vEntiteStock.setQteActuelle(vEntiteStock.getQteActuelle() + mouvement.getQuantiteReelle());
				if (mouvement.getTypeArticle().equals(2L)) {
					if (vEntiteStock.getRouleauxActuel() != null && mouvement.getNbRouleauxReel() != null)
							vEntiteStock.setRouleauxActuel(
									vEntiteStock.getRouleauxActuel() + mouvement.getNbRouleauxReel());

				}
			}
			// entiteStockPersistance.modifierEntiteStock(vEntiteStock);
			mouvement.setEntiteStockValue(vEntiteStock);

			vListeMouvement.add(mouvement);
		}

		vBonMouvementStockValue.setMouvements(vListeMouvement);
		return vBonMouvementStockValue;
	}

	
	// Calcul modifié par Ghazi le 27/11/2016
	private BonMouvementStockValue enrichirReservation(BonMouvementStockValue bonMouvementStock) {

		BonMouvementStockValue request = bonMouvementStock;
		Set<MouvementStockValue> listMouvementStock = new HashSet<MouvementStockValue>();

		for (MouvementStockValue mouvement : request.getMouvements()) {
			EntiteStockValue entiteStock = entiteStockPersistance.rechercheEntiteStockParId(mouvement.getEntiteStock());
           
            	
            	
			if (entiteStock != null) {
				 Double vQuantiteLibre= new Double(0) ;
				 Long vConeLibre=new Long(0) ;
				 Long vFinConeLibre = new Long (0);
			   if(entiteStock.getQteActuelle()!=null)
		              { if (entiteStock.getQteReservee()==null)
		            	  entiteStock.setQteReservee(0D);
		                vQuantiteLibre=(entiteStock.getQteActuelle()-entiteStock.getQteReservee())-mouvement.getQuantiteReelle();
		              }
			   
				if (mouvement.getTypeArticle() != null) {
                    
					if (mouvement.getTypeArticle().equals(3L)) {
						 vConeLibre=entiteStock.getConesActuel()-entiteStock.getConeReserve()-mouvement.getConesReel();
						if (mouvement.getConesReel() != null && entiteStock.getConesActuel() != null) {

							if (mouvement.getConesReel() <= (entiteStock.getConesActuel()-entiteStock.getConeReserve())) {

								entiteStock.setConeReserve(entiteStock.getConeReserve() + mouvement.getConesReel());
							}
						}
						if (mouvement.getFinconesReel() != null && mouvement.getPoidsReel() != null
								&& entiteStock.getFinconeActuel() != null && entiteStock.getPoidsActuel() != null) {

							if (mouvement.getFinconesReel() <= (entiteStock.getFinconeActuel()-entiteStock.getFinconeReserve())) {
								entiteStock
										.setFinconeReserve(entiteStock.getFinconeReserve() + mouvement.getFinconesReel());
								entiteStock.setPoidsActuel(
										new Double(entiteStock.getPoidsReserve() + mouvement.getPoidsReel()));
								entiteStock.setEquivalenceCone(calculerEquivalenceCone(entiteStock));
							}
						}

					} else {

						if (entiteStock.getQteActuelle() != null && mouvement.getQuantiteReelle() != null) {
							if (((entiteStock.getQteActuelle()-entiteStock.getQteReservee()) >= mouvement.getQuantiteReelle())) {
								entiteStock
										.setQteReservee(entiteStock.getQteReservee() + mouvement.getQuantiteReelle());
							}
						}
						if (mouvement.getTypeArticle().equals(2L)) {

							if (entiteStock.getRouleauxActuel() != null && mouvement.getNbRouleauxReel() != null) {
								if ((entiteStock.getRouleauxActuel()-entiteStock.getRouleauxReserve()) >= mouvement.getNbRouleauxReel()) {
									entiteStock.setRouleauxReserve(
											entiteStock.getRouleauxReserve() + mouvement.getNbRouleauxReel());
								}
							}
						}
					}
                    mouvement.setTypeMouvement(IConstante.TYPE_MVT_RES);
					mouvement.setEntiteStockValue(entiteStock);
					if((mouvement.getQuantiteReelle()!=null && mouvement.getQuantiteReelle()>0 && vQuantiteLibre>=0)
							||(mouvement.getFinconesReel() != null && mouvement.getFinconesReel()>0 && vFinConeLibre>=0) 
							|| (mouvement.getConesReel() != null && mouvement.getConesReel()>0 && vConeLibre>=0))
					listMouvementStock.add(mouvement);
				}
			}

			OrdreFabricationValue OF = ordreFabricationPersistance.rechercheOrdreFabricationParId(request.getOfId());

			if (OF != null) {
				request.setPartieintId(OF.getPartieInterresId());
			}
		}
		    //Ajout de raison de mouvement
		   request.setRaisonMouvementId(20L);

		request.setMouvements(listMouvementStock);
        
		return request;

	}

	@Override
	public String modifierBonMouvement(BonMouvementStockValue pBonMouvement) {
		
		BonMouvementStockValue vBonMouvementStockValue = new BonMouvementStockValue();
		// Enrichissement du bon de mouvement Entrée

		if (pBonMouvement.getType().equals(IConstante.TYPE_MVT_ENT))
			vBonMouvementStockValue = enrichirBonEntreeModification(pBonMouvement);

		if (pBonMouvement.getType().equals(IConstante.TYPE_MVT_SORT))
			vBonMouvementStockValue = enrichirBonSortieModification(pBonMouvement);

		if(pBonMouvement.getType().equals(IConstante.TYPE_MVT_RES)){
			vBonMouvementStockValue = pBonMouvement;
		}
		if(pBonMouvement.getType().equals(IConstante.TYPE_MVT_RET)){
			vBonMouvementStockValue = enrichirBonRetourModification(pBonMouvement);
		}
		return bonMouvementPersistance.modifierBonMouvement(vBonMouvementStockValue);
	}

	@Override
	public List<BonMouvementStockValue> listeBonMouvement() {

		return bonMouvementPersistance.listeBonMouvement();
	}

	@Override
	public void supprimerBonMouvement(Long pId) {
		
		BonMouvementStockValue bonMouvement = this.rechercheBonMouvementParId(pId);
		
		if(bonMouvement.getType().equals(IConstante.TYPE_MVT_ENT) || bonMouvement.getType().equals(IConstante.TYPE_MVT_SORT) || bonMouvement.getType().equals(IConstante.TYPE_MVT_RES)  || bonMouvement.getType().equals(IConstante.TYPE_MVT_RET)){
			for (MouvementStockValue mouvement : bonMouvement.getMouvements()) {
				
				 EntiteStockValue entiteStock = entiteStockPersistance.rechercheEntiteStockParId(mouvement.getEntiteStock());
				 
				if(mouvement.getTypeArticle().equals(3L)){
					
					if(mouvement.getType().equals(IConstante.TYPE_MVT_ENT) || mouvement.getType().equals(IConstante.TYPE_MVT_RET)){
						
						entiteStock.setConesActuel(entiteStock.getConesActuel()- mouvement.getConesReel());
						entiteStock.setFinconeActuel(entiteStock.getFinconeActuel()- mouvement.getFinconesReel());
						entiteStock.setPoidsActuel(entiteStock.getPoidsActuel() - mouvement.getPoidsReel());
						
					}else if(mouvement.getType().equals(IConstante.TYPE_MVT_SORT)){
						
						entiteStock.setConesActuel(entiteStock.getConesActuel() + mouvement.getConesReel());
						entiteStock.setFinconeActuel(entiteStock.getFinconeActuel() + mouvement.getFinconesReel());
						entiteStock.setPoidsActuel(entiteStock.getPoidsActuel() + mouvement.getPoidsReel());
					}
					else if(bonMouvement.getType().equals(IConstante.TYPE_MVT_RES)){
						if(mouvement.getCones() != null && mouvement.getFincones()!=null && mouvement.getPoids()!=null){
							entiteStock.setConeReserve(entiteStock.getConeReserve() - mouvement.getConesReel());
							entiteStock.setFinconeReserve(entiteStock.getFinconeReserve() - mouvement.getFinconesReel());
							entiteStock.setPoidsReserve(entiteStock.getPoidsReserve() - mouvement.getPoidsReel());
						}
						
					}
				 }
				else{
					
					if(mouvement.getType().equals(IConstante.TYPE_MVT_ENT) || mouvement.getType().equals(IConstante.TYPE_MVT_RET)){
						if(entiteStock.getQteActuelle()!=null && mouvement.getQuantiteReelle()!= null){
							 entiteStock.setQteActuelle(entiteStock.getQteActuelle() - mouvement.getQuantiteReelle()); 
						}
						
						 if(mouvement.getTypeArticle().equals(2L)){
							 if(entiteStock.getRouleauxActuel()!= null && mouvement.getNbRouleauxReel()!= null){
								 entiteStock.setRouleauxActuel(entiteStock.getRouleauxActuel()-mouvement.getNbRouleauxReel());

							 }
						 }
					}
					else if (mouvement.getType().equals(IConstante.TYPE_MVT_SORT)){
						
						if(entiteStock.getQteActuelle()!= null && mouvement.getQuantiteReelle()!= null){
							entiteStock.setQteActuelle(entiteStock.getQteActuelle() + mouvement.getQuantiteReelle()); 
						}
						
						 if(mouvement.getTypeArticle().equals(2L)){
							 if(entiteStock.getRouleauxActuel() != null && mouvement.getNbRouleauxReel()!=null){
								 entiteStock.setRouleauxActuel(entiteStock.getRouleauxActuel()+ mouvement.getNbRouleauxReel());

							 }
						 }
					}
					else if(bonMouvement.getType().equals(IConstante.TYPE_MVT_RES)){
						if(entiteStock.getQteReservee()!=null && mouvement.getQteReservee()!=null){
							entiteStock.setQteReservee(entiteStock.getQteReservee() - mouvement.getQuantiteReelle());
						}
						
						 if(mouvement.getTypeArticle().equals(2L)){
							 if(mouvement.getNbRouleaux()!=null && entiteStock.getRouleauxReserve()!= null){
								 entiteStock.setRouleauxReserve(entiteStock.getRouleauxReserve() - mouvement.getNbRouleaux());

							 }
						 }
					}
				 }
			
				entiteStockPersistance.modifierEntiteStock(entiteStock);
			}
			
		}
		 
		bonMouvementPersistance.supprimerBonMouvement(pId);

	}

	@Override
	public List<BonMouvementStockValue> getByTypeBonMouvement(String type) {

		return bonMouvementPersistance.getByTypeBonMouvement(type);
	}

	@Override
	public BonMouvementStockValue rechercheBonMouvementParId(Long pBonMouvementId) {

		BonMouvementStockValue bonMouvement = bonMouvementPersistance.rechercheBonMouvementParId(pBonMouvementId);

		if (bonMouvement != null) {
			if (bonMouvement.getMouvements() != null) {
				for (MouvementStockValue mouvement : bonMouvement.getMouvements()) {

					if (mouvement.getEntiteStock() != null) {

						EntiteStockValue entiteStock = entiteStockPersistance
								.rechercheEntiteStockParId(mouvement.getEntiteStock());

						if (entiteStock != null) {
							//mouvement.setLot(entiteStock.getReferenceLot());
							mouvement.setDateEntree(entiteStock.getDateEntree());
							mouvement.setQuantiteAct(entiteStock.getQteActuelle());

							if (entiteStock.getQteActuelle() != null && entiteStock.getQteReservee() != null) {
								mouvement.setQuantiteLibre(entiteStock.getQteActuelle() - entiteStock.getQteReservee());
							}

							mouvement.setReservationOF(
									calculerReservationOF(entiteStock.getId(), bonMouvement.getOfId()));
							mouvement.setQteReservee(mouvement.getQuantiteReelle());

							if (mouvement.getBesoinOF() != null && mouvement.getQteOF() != null) {
								mouvement.setBu(mouvement.getBesoinOF() / mouvement.getQteOF());
							}
							
							mouvement.setRouleauxActuels(entiteStock.getRouleauxActuel());
						}
					}
				}
			}
		}

		return bonMouvement;
	}

	public BonMouvementStockValue enrichirBonSortie(BonMouvementStockValue pBonMouvementStockValue) {
		BonMouvementStockValue vBonMouvementStockValue = pBonMouvementStockValue;
		String referenceReservation =IConstante.VIDE;
		if (pBonMouvementStockValue.getNumBRSortie()!=null && !pBonMouvementStockValue.getNumBRSortie().equals(IConstante.VIDE))
			referenceReservation=pBonMouvementStockValue.getNumBRSortie();
		
		Set<MouvementStockValue> vListeMouvement = new HashSet<MouvementStockValue>();

		if(pBonMouvementStockValue.getNumero()==null || (( pBonMouvementStockValue.getNumero().equals("") &&pBonMouvementStockValue.getNumero()!=null ))){
			pBonMouvementStockValue.setNumero(this.getNumeroBonSortie(pBonMouvementStockValue.getDate()));
		}
		
		for (MouvementStockValue mouvement : vBonMouvementStockValue.getMouvements()) {
			// A changer
			// MouvementStockValue mouvement;
			EntiteStockValue vEntiteStock = entiteStockPersistance
					.rechercheEntiteStockParId(mouvement.getEntiteStock());
        	 Double vQuantiteLibre= new Double(0) ;
				 Long vConeLibre=new Long(0) ;
				 Long vFinConeLibre = new Long (0);
			   if(vEntiteStock.getQteActuelle()!=null)
		              { if (vEntiteStock.getQteReservee()==null)
		            	  vEntiteStock.setQteReservee(0D);
		                vQuantiteLibre=(vEntiteStock.getQteActuelle()-vEntiteStock.getQteReservee())-mouvement.getQuantiteReelle();
		                if(!referenceReservation.equals(IConstante.VIDE))
		                	vQuantiteLibre+=mouvement.getReservationOrigine();
		                    pBonMouvementStockValue.setRaisonMouvementId(21L);
		              }
			
			
			
			if (mouvement.getTypeArticle().equals(3L)) {
				if (mouvement.getConesReel() != null && vEntiteStock.getConesActuel() != null)
					if (mouvement.getConesReel() <= vEntiteStock.getConesActuel())
						vEntiteStock.setConesActuel(vEntiteStock.getConesActuel() - mouvement.getConesReel());
				// TODO Else exception
				if (mouvement.getFinconesReel() != null && mouvement.getPoidsReel() != null
						&& vEntiteStock.getFinconeActuel() != null && vEntiteStock.getPoidsActuel() != null) {
					if (mouvement.getFinconesReel() <= vEntiteStock.getFinconeActuel()) {
						vEntiteStock.setFinconeActuel(vEntiteStock.getFinconeActuel() - mouvement.getFinconesReel());
						vEntiteStock
								.setPoidsActuel(new Double(vEntiteStock.getPoidsActuel() - mouvement.getPoidsReel()));
						vEntiteStock.setEquivalenceCone(calculerEquivalenceCone(vEntiteStock));
					}
				}

			} else {
						
				
				if (vEntiteStock.getQteActuelle() != null && mouvement.getQuantiteReelle() != null)
					if (vQuantiteLibre >=ZEROD)
					{
						
						vEntiteStock.setQteActuelle(vEntiteStock.getQteActuelle() - mouvement.getQuantiteReelle());
						if((!referenceReservation.equals(IConstante.VIDE)))
							if (mouvement.getQuantiteReelle()<=mouvement.getReservationOrigine())
								vEntiteStock.setQteReservee(vEntiteStock.getQteReservee()-mouvement.getQuantiteReelle());
							else 
								vEntiteStock.setQteReservee(vEntiteStock.getQteReservee()-mouvement.getReservationOrigine());
					}
				
				if (mouvement.getTypeArticle().equals(2L)) {
					if (vEntiteStock.getRouleauxActuel() != null && mouvement.getNbRouleauxReel() != null)
						if (vEntiteStock.getRouleauxActuel() >= mouvement.getNbRouleauxReel())
							vEntiteStock.setRouleauxActuel(
									vEntiteStock.getRouleauxActuel() - mouvement.getNbRouleauxReel());

				}
			}
             
			
			// entiteStockPersistance.modifierEntiteStock(vEntiteStock);
			mouvement.setTypeMouvement(IConstante.TYPE_MVT_SORT);
			mouvement.setEntiteStockValue(vEntiteStock);
            if((mouvement.getQuantiteReelle() != null && mouvement.getQuantiteReelle()>ZEROD&&vQuantiteLibre >=ZEROD )
            		||(mouvement.getConesReel()>ZEROL) || mouvement.getFinconesReel()>ZEROL)
			vListeMouvement.add(mouvement);
		}
		if(vBonMouvementStockValue.getOfId()!=null)
		{	OrdreFabricationValue OF = ordreFabricationPersistance.rechercheOrdreFabricationParId(vBonMouvementStockValue.getOfId());

		if (OF != null) {
			vBonMouvementStockValue.setPartieintId(OF.getPartieInterresId());
		}
		}
		vBonMouvementStockValue.setMouvements(vListeMouvement);
		return vBonMouvementStockValue;

	}

	public BonMouvementStockValue enrichirBonEntreeModification(BonMouvementStockValue pBonMouvementStockValue) {

		BonMouvementStockValue vBonMouvementStockValue = pBonMouvementStockValue;
		Set<MouvementStockValue> vListMouvementResultat = new HashSet<MouvementStockValue>();

		for (MouvementStockValue mouvement : vBonMouvementStockValue.getMouvements()) {
			// A changer
			if (mouvement.getNouveau() == null || (mouvement.getNouveau() == true)) {

				EntiteStockValue vEntiteStock = entiteStockPersistance
						.rechercheEntiteStockByArticleMagasin(mouvement.getIdArticle(), mouvement.getIdMagasin());

				// Creation de l'entité stock si elle n'existe pas
				if (vEntiteStock == null) {

					vEntiteStock = new EntiteStockValue();
					vEntiteStock.setArticle(mouvement.getIdArticle());
					vEntiteStock.setMagasin(mouvement.getIdMagasin());
					vEntiteStock.setPmp(new Double(0));
					vEntiteStock.setPrixUnitaire(new Double(0));
					vEntiteStock.setEmplacement(IConstante.VIDE);

					if (mouvement.getTypeArticle().equals(3L)) {

						vEntiteStock.setConeReserve(new Long(0));
						vEntiteStock.setConesActuel(new Long(0));
						vEntiteStock.setFinconeActuel(new Long(0));
						vEntiteStock.setFinconeReserve(new Long(0));
						vEntiteStock.setPoidsActuel(new Double(0));
						vEntiteStock.setPoidsReserve(new Double(0));
						vEntiteStock.setEquivalenceCone(new Double(0));

					} else {

						vEntiteStock.setQteActuelle(new Double(0));
						vEntiteStock.setQteReservee(new Double(0));

						if (mouvement.getTypeArticle().equals(2L)) {

							vEntiteStock.setRouleauxActuel(new Long(0));
							vEntiteStock.setRouleauxReserve(new Long(0));
						}
					}
					// entiteStockPersistance.creerEntiteStock(vEntiteStock);

				}

				// // Mise à jour de l'entité Stock
				// vEntiteStock =
				// entiteStockPersistance.rechercheEntiteStockByArticleMagasin(mouvement.getIdArticle(),
				// mouvement.getIdMagasin());

				if (mouvement.getTypeArticle().equals(3L)) {
					if (mouvement.getConesReel() != null)
						vEntiteStock.setConesActuel(vEntiteStock.getConesActuel() + mouvement.getConesReel());
					if (mouvement.getFinconesReel() != null) {
						vEntiteStock.setFinconeActuel(vEntiteStock.getFinconeActuel() + mouvement.getFinconesReel());
						vEntiteStock
								.setPoidsActuel(new Double(vEntiteStock.getPoidsActuel() + mouvement.getPoidsReel()));
						vEntiteStock.setEquivalenceCone(calculerEquivalenceCone(vEntiteStock));
					}

				} else {

					vEntiteStock.setQteActuelle(vEntiteStock.getQteActuelle() + mouvement.getQuantiteReelle());
					if (mouvement.getTypeArticle().equals(2L)) {
						vEntiteStock
								.setRouleauxActuel(vEntiteStock.getRouleauxActuel() + mouvement.getNbRouleauxReel());

					}
				}
				// ArticleValue ar
				if (!vEntiteStock.getEmplacement().equals(IConstante.VIDE))
					vEntiteStock.setEmplacement(vEntiteStock.getEmplacement() + IConstante.SEPARATEUR);
				vEntiteStock.setEmplacement(vEntiteStock.getEmplacement() + mouvement.getEmplacement());
				vEntiteStock.setPrixUnitaire(mouvement.getPrixUnitaire());
				vEntiteStock.setPmp(calculerPrixMoyenPondere(vEntiteStock, mouvement));
				// entiteStockPersistance.modifierEntiteStock(vEntiteStock);
				mouvement.setEntiteStock(vEntiteStock.getId());
				mouvement.setEntiteStockValue(vEntiteStock);
				// mouvement.setPrixTotal(prixTotal);
				
			}
			vListMouvementResultat.add(mouvement);
		}
		vBonMouvementStockValue.setMouvements(vListMouvementResultat);

		this.updateEntiteStockAfterDeleteMvtEntreeOuRetour(pBonMouvementStockValue.getListeMouvementsSupprimes());

		return vBonMouvementStockValue;
	}

	private void updateEntiteStockAfterDeleteMvtEntreeOuRetour(List<MouvementStockSupprime> listeMouvementsSupprimes) {

		for (MouvementStockSupprime mouvementStockSupprime : listeMouvementsSupprimes) {

			for (MouvementStockSupprimeElement mouvementStockSupprimeElement : mouvementStockSupprime
					.getListeElementsSupprimes()) {

				EntiteStockValue entiteStock = entiteStockPersistance
						.rechercheEntiteStockParId(mouvementStockSupprimeElement.getEntiteStockId());

				if (entiteStock != null) {

					// ConesAct & finConeAct & PoidsActu MAJ pour type 3 (Fil à
					// coudre)
					if (mouvementStockSupprime.getTypeArticle().equals(3L)) {

						entiteStock.setConesActuel(
								entiteStock.getConesActuel() - mouvementStockSupprimeElement.getConesReel());
						entiteStock.setFinconeActuel(
								entiteStock.getFinconeActuel() - mouvementStockSupprimeElement.getFinconesReel());
						entiteStock.setPoidsActuel(
								entiteStock.getPoidsActuel() - mouvementStockSupprimeElement.getPoidsReel());
					}

					else {

						// QteActuelle MAJ pour type 1 & 2 (Fourniture & Tissu)
						entiteStock.setQteActuelle(
								entiteStock.getQteActuelle() - mouvementStockSupprimeElement.getQteReelle());
                 //TODO A verifier 
						// QteRouleaux MAJ pour type 2 (Tissu)
						if (mouvementStockSupprime.getTypeArticle().equals(2L)) {
							
							entiteStock.setRouleauxActuel(entiteStock.getRouleauxActuel()- mouvementStockSupprimeElement.getNbRouleauxReel());
						}

					}

					this.entiteStockPersistance.modifierEntiteStock(entiteStock);
				}

			}
		}
	}

	public BonMouvementStockValue enrichirBonSortieModification(BonMouvementStockValue pBonMouvementStockValue) {
		BonMouvementStockValue vBonMouvementStockValue = pBonMouvementStockValue;
		Set<MouvementStockValue> vListeMouvement = new HashSet<MouvementStockValue>();

		for (MouvementStockValue mouvement : vBonMouvementStockValue.getMouvements()) {
			// A changer
			// MouvementStockValue mouvement;
			if (mouvement.getNouveau() == null || mouvement.getNouveau() == true) {
				EntiteStockValue vEntiteStock = entiteStockPersistance
						.rechercheEntiteStockParId(mouvement.getEntiteStock());

				 Double vQuantiteLibre= new Double(0) ;
				 Long vConeLibre=new Long(0) ;
				 Long vFinConeLibre = new Long (0);
			   if(vEntiteStock.getQteActuelle()!=null)
		              { if (vEntiteStock.getQteReservee()==null)
		            	  vEntiteStock.setQteReservee(0D);
		                vQuantiteLibre=(vEntiteStock.getQteActuelle()-vEntiteStock.getQteReservee())-mouvement.getQuantiteReelle();
		                
		              }
				
				if (mouvement.getTypeArticle().equals(3L)) {
					if (mouvement.getConesReel() != null && vEntiteStock.getConesActuel() != null)
						if (mouvement.getConesReel() <= vEntiteStock.getConesActuel())
							vEntiteStock.setConesActuel(vEntiteStock.getConesActuel() - mouvement.getConesReel());
					// TODO Else exception
					if (mouvement.getFinconesReel() != null && mouvement.getPoidsReel() != null
							&& vEntiteStock.getFinconeActuel() != null && vEntiteStock.getPoidsActuel() != null) {
						if (mouvement.getFinconesReel() <= vEntiteStock.getFinconeActuel()) {
							vEntiteStock
									.setFinconeActuel(vEntiteStock.getFinconeActuel() - mouvement.getFinconesReel());
							vEntiteStock.setPoidsActuel(
									new Double(vEntiteStock.getPoidsActuel() - mouvement.getPoidsReel()));
							vEntiteStock.setEquivalenceCone(calculerEquivalenceCone(vEntiteStock));
						}
					}

				} else {
					if (vEntiteStock.getQteActuelle() != null && mouvement.getQuantiteReelle() != null)

						if ((vEntiteStock.getQteActuelle()-vEntiteStock.getQteReservee()) >= mouvement.getQuantiteReelle())
							vEntiteStock.setQteActuelle(vEntiteStock.getQteActuelle() - mouvement.getQuantiteReelle());

					if (mouvement.getTypeArticle().equals(2L)) {
						if (vEntiteStock.getRouleauxActuel() != null && mouvement.getNbRouleauxReel() != null)
							if (vEntiteStock.getRouleauxActuel() >= mouvement.getNbRouleauxReel())
								vEntiteStock.setRouleauxActuel(
										vEntiteStock.getRouleauxActuel() - mouvement.getNbRouleauxReel());

					}
				}

				// entiteStockPersistance.modifierEntiteStock(vEntiteStock);
				mouvement.setTypeMouvement(IConstante.TYPE_MVT_SORT);
				mouvement.setEntiteStockValue(vEntiteStock);

				
			}
			//TODO A verifier 
			vListeMouvement.add(mouvement);
		}
		
		this.updateEntiteStockAfterDeleteMvtSortie(pBonMouvementStockValue.getListeMouvementsSupprimes());
		
		if(vBonMouvementStockValue.getOfId()!=null) {
		OrdreFabricationValue OF = ordreFabricationPersistance.rechercheOrdreFabricationParId(vBonMouvementStockValue.getOfId());

		if (OF != null) {
			vBonMouvementStockValue.setPartieintId(OF.getPartieInterresId());
		}
		}		
		
		vBonMouvementStockValue.setMouvements(vListeMouvement);

		return vBonMouvementStockValue;

	}

	public BonMouvementStockValue enrichirBonRetourModification(BonMouvementStockValue pBonMouvementStockValue) {
		BonMouvementStockValue vBonMouvementStockValue = pBonMouvementStockValue;
		Set<MouvementStockValue> vListeMouvement = new HashSet<MouvementStockValue>();

		for (MouvementStockValue mouvement : vBonMouvementStockValue.getMouvements()) {
			// A changer
			// MouvementStockValue mouvement;
			if (mouvement.getNouveau() == null || mouvement.getNouveau() == true) {
				EntiteStockValue vEntiteStock = entiteStockPersistance
						.rechercheEntiteStockParId(mouvement.getEntiteStock());

				if (mouvement.getTypeArticle().equals(3L)) {
					if (mouvement.getConesReel() != null && vEntiteStock.getConesActuel() != null)
							vEntiteStock.setConesActuel(vEntiteStock.getConesActuel() + mouvement.getConesReel());
					// TODO Else exception
					if (mouvement.getFinconesReel() != null && mouvement.getPoidsReel() != null
							&& vEntiteStock.getFinconeActuel() != null && vEntiteStock.getPoidsActuel() != null) {
						if (mouvement.getFinconesReel() <= vEntiteStock.getFinconeActuel()) {
							vEntiteStock
									.setFinconeActuel(vEntiteStock.getFinconeActuel() + mouvement.getFinconesReel());
							vEntiteStock.setPoidsActuel(
									new Double(vEntiteStock.getPoidsActuel() + mouvement.getPoidsReel()));
							vEntiteStock.setEquivalenceCone(calculerEquivalenceCone(vEntiteStock));
						}
					}

				} else {
					if (vEntiteStock.getQteActuelle() != null && mouvement.getQuantiteReelle() != null)

						vEntiteStock.setQteActuelle(vEntiteStock.getQteActuelle() + mouvement.getQuantiteReelle());

					if (mouvement.getTypeArticle().equals(2L)) {
						if (vEntiteStock.getRouleauxActuel() != null && mouvement.getNbRouleauxReel() != null)
							vEntiteStock.setRouleauxActuel(
									vEntiteStock.getRouleauxActuel() + mouvement.getNbRouleauxReel());

					}
				}

				// entiteStockPersistance.modifierEntiteStock(vEntiteStock);
				mouvement.setEntiteStockValue(vEntiteStock);

				
			}
			vListeMouvement.add(mouvement);
		}
		
		this.updateEntiteStockAfterDeleteMvtEntreeOuRetour(pBonMouvementStockValue.getListeMouvementsSupprimes());
		
		vBonMouvementStockValue.setMouvements(vListeMouvement);

		return vBonMouvementStockValue;

	}
	private void updateEntiteStockAfterDeleteMvtSortie(List<MouvementStockSupprime> listeMouvementsSupprimes) {
		
		for (MouvementStockSupprime mouvementStockSupprime : listeMouvementsSupprimes) {

			for (MouvementStockSupprimeElement mouvementStockSupprimeElement : mouvementStockSupprime
					.getListeElementsSupprimes()) {

				EntiteStockValue entiteStock = entiteStockPersistance
						.rechercheEntiteStockParId(mouvementStockSupprimeElement.getEntiteStockId());

				if (entiteStock != null) {

					// ConesAct & finConeAct & PoidsActu MAJ pour type 3 (Fil à
					// coudre)
					if (mouvementStockSupprime.getTypeArticle().equals(3L)) {

						entiteStock.setConesActuel(
								entiteStock.getConesActuel() + mouvementStockSupprimeElement.getConesReel());
						entiteStock.setFinconeActuel(
								entiteStock.getFinconeActuel() + mouvementStockSupprimeElement.getFinconesReel());
						entiteStock.setPoidsActuel(
								entiteStock.getPoidsActuel() + mouvementStockSupprimeElement.getPoidsReel());
					}

					else {

						// QteActuelle MAJ pour type 1 & 2 (Fourniture & Tissu)
						entiteStock.setQteActuelle(
								entiteStock.getQteActuelle() + mouvementStockSupprimeElement.getQteReelle());
                     //TODO A verifier 
						// QteRouleaux MAJ pour type 2 (Tissu)
						if (mouvementStockSupprime.getTypeArticle().equals(2L)) {
							entiteStock.setRouleauxActuel(entiteStock.getRouleauxActuel()
									+ mouvementStockSupprimeElement.getNbRouleauxReel());
						}

					}

					this.entiteStockPersistance.modifierEntiteStock(entiteStock);
				}

			}
		}
		
	}
	
private void updateEntiteStockAfterDeleteMvtReservation(List<MouvementStockSupprime> listeMouvementsSupprimes) {
		
		for (MouvementStockSupprime mouvementStockSupprime : listeMouvementsSupprimes) {

			for (MouvementStockSupprimeElement mouvementStockSupprimeElement : mouvementStockSupprime
					.getListeElementsSupprimes()) {

				EntiteStockValue entiteStock = entiteStockPersistance
						.rechercheEntiteStockParId(mouvementStockSupprimeElement.getEntiteStockId());

				if (entiteStock != null) {

					// ConesAct & finConeAct & PoidsActu MAJ pour type 3 (Fil à
					// coudre)
					if (mouvementStockSupprime.getTypeArticle().equals(3L)) {
						entiteStock.setConeReserve(entiteStock.getConeReserve() - mouvementStockSupprimeElement.getConesReel());
						entiteStock.setFinconeReserve(entiteStock.getFinconeReserve() - mouvementStockSupprimeElement.getFinconesReel());
						entiteStock.setPoidsReserve(entiteStock.getPoidsReserve() - mouvementStockSupprimeElement.getPoidsReserve());
					}

					else {

						// QteActuelle MAJ pour type 1 & 2 (Fourniture & Tissu)
						entiteStock.setQteReservee(entiteStock.getQteReservee() - mouvementStockSupprimeElement.getQteReelle());
                     //TODO A verifier 
						// QteRouleaux MAJ pour type 2 (Tissu)
						if (mouvementStockSupprime.getTypeArticle().equals(2L)) {
							entiteStock.setRouleauxReserve(entiteStock.getRouleauxReserve() - mouvementStockSupprimeElement.getNbRouleauxReel());
						}

					}

					this.entiteStockPersistance.modifierEntiteStock(entiteStock);
				}

			}
		}
		
	}

	// Added on 09/11/2016, by Zeineb Medimagh
	@Override
	public List<ResultatListeBonMvtParTypeValue> getListeNumerosParType(String type) {
		return bonMouvementPersistance.getListeNumerosParType(type);
	}

	@Override
	public List<MouvementOfVue> getListeMouvementsSortie(Long bonMouvementId) {

		BonMouvementStockValue bonMouvement = bonMouvementPersistance.rechercheBonMouvementParId(bonMouvementId);

		List<MouvementOfVue> listMouvements = new ArrayList<MouvementOfVue>();

		for (MouvementStockValue mouvement : bonMouvement.getMouvements()) {

			EntiteStockValue entiteStock = entiteStockPersistance.rechercheEntiteStockParId(mouvement.getEntiteStock());

			ArticleValue article = articleDomaine.rechercheArticleParId(entiteStock.getArticle());
			// get SousFamille puis getFamille puis getType

			Double qteLibre =0D;
			Double aReserver = 0D;
			Double reservationOF = 0D;
			
			if(entiteStock.getQteActuelle() !=null && entiteStock.getQteReservee()!=null){
				qteLibre = entiteStock.getQteActuelle() - entiteStock.getQteReservee();
			}
			
			if(entiteStock.getId() !=null && bonMouvement.getOfId()!=null){
				
				reservationOF = calculerReservationOF(entiteStock.getId(), bonMouvement.getOfId());
				
				if(mouvement.getBesoinOF() !=null && reservationOF!=null){
					aReserver = mouvement.getQuantiteReelle();
				}
			}
			
			Boolean manque = false;

			Double besoinUnitaire = 0D;

			if (mouvement.getBesoinOF() != null && mouvement.getQteOF() != null) {
				besoinUnitaire = mouvement.getBesoinOF() / mouvement.getQteOF();
			}

			if (mouvement.getBesoinOF() != null && qteLibre != null) {
				manque = ((mouvement.getBesoinOF() - qteLibre) < ZEROD ? true : false);
			}

			MouvementOfVue mouvementOfVue = new MouvementOfVue();
			/*MouvementOfVue mouvementOfVue = new MouvementOfVue(article.getRef(), article.getDesignation(),
					article.getFamilleArticleDesignation(), entiteStock.getQteActuelle(), qteLibre,
					mouvement.getBesoinOF(), aReserver, entiteStock.getEmplacement(), mouvement.getType(), manque,
					reservationOF, mouvement.getQteOF().longValue(), 0D, mouvement.getEntiteStock(),
					Long.parseLong(mouvement.getType()), entiteStock.getReferenceLot(), entiteStock.getDateEntree(),
					besoinUnitaire);*/
			
			if(article.getRef()!=null){
				mouvementOfVue.setReferenceArticle(article.getRef());
			}
			
			if(article.getDesignation()!=null){
				mouvementOfVue.setDesignationArticle(article.getDesignation());
			}

			if(article.getFamilleArticleDesignation()!=null){
				mouvementOfVue.setFamilleArticle(article.getFamilleArticleDesignation());
			}
			
			if(entiteStock.getQteActuelle()!=null){
				mouvementOfVue.setQuantiteAct(entiteStock.getQteActuelle());
			}
			
			if(qteLibre!=null){
				mouvementOfVue.setQuantiteLibre(qteLibre);
			}
			
			if(entiteStock.getRouleauxActuel()!=null){
				mouvementOfVue.setRouleauxActuel(entiteStock.getRouleauxActuel());
			}
			
			if(mouvement.getBesoinOF()!=null){
				mouvementOfVue.setBesoinOF(mouvement.getBesoinOF());
			}
			
			if(aReserver!=null){
				mouvementOfVue.setaReserver(aReserver);
			}
			
			if(entiteStock.getEmplacement()!=null){
				mouvementOfVue.setEmplacement(entiteStock.getEmplacement());
			}
			
			if(mouvement.getType()!=null){
				mouvementOfVue.setType(mouvement.getType());
				//mouvementOfVue.setTypeArticle(Long.parseLong(mouvement.getType()));
			}
			
			if(manque!=null){
				mouvementOfVue.setManque(manque);
			}
			
			if(reservationOF!=null){
				mouvementOfVue.setReservationOF(reservationOF);
			}
				
			if(mouvement.getQteOF()!=null){
				mouvementOfVue.setQteOF(mouvement.getQteOF().longValue()); 
			}
			
			if(mouvement.getEntiteStock() != null){
				mouvementOfVue.setEntiteStock(mouvement.getEntiteStock());
			}
			
			if( entiteStock.getReferenceLot()!=null){
				mouvementOfVue.setLot(entiteStock.getReferenceLot());
			}
			if(entiteStock.getDateEntree()!=null){
				mouvementOfVue.setDateEntree(entiteStock.getDateEntree());
			}

			if(besoinUnitaire!=null){
				mouvementOfVue.setBu(besoinUnitaire);
			}
			mouvementOfVue.setReservationOrigine(mouvement.getQuantiteReelle());
			mouvementOfVue.setReservationAct(0D);
			
			listMouvements.add(mouvementOfVue);

		}

		return listMouvements;
	}

	private Double calculerReservationOF(Long entiteStockId, Long ordreFabricationId) {

		Double reservationOF = ZEROD;

		RechercheMulticritereMouvementValue request = new RechercheMulticritereMouvementValue();
		request.setHistorique(TYPE_RESERVATION);
		request.setOfId(ordreFabricationId);
		request.setEntiteStockId(entiteStockId);

		ResultatRechecheMouvementValue result = mouvementPersistance.rechercherMouvementMultiCritere(request);

		if (result != null) {

			for (MouvementStockValue mouvementStock : result.getMouvementStock()) {

				if (mouvementStock.getQuantiteReelle() != null) {

					reservationOF = reservationOF + mouvementStock.getQuantiteReelle();
				}

			}
		}

		return reservationOF;
	}

	@Override
	public BonMouvementStockValue rechercheBonMouvementParNum(String pBonMouvementNum) {
		// TODO Auto-generated method stub
		return bonMouvementPersistance.rechercheBonMouvementParNum(pBonMouvementNum);
	}
	
	// Added by Zeineb Medimagh on 18/01/2016
	
	private String getNumeroBonEntree(final Calendar dateBonEntree) {

		Long vNumGuichetBonEntree = this.guichetAnnuelDomaine.getNextNumBonEntreeReference();
		/** Année courante. */
		int vAnneeCourante = dateBonEntree.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBonEntree = new StringBuilder("");
		vNumBonEntree.append(vAnneeCourante);
		vNumBonEntree.append( String.format("%05d",vNumGuichetBonEntree));
		/** Inserer une nouvelle valeur dans Guichet BonReception. */
		GuichetAnnuelValue vGuichetValeur = new GuichetAnnuelValue();
		

		Calendar cal = Calendar.getInstance();
		int anneActuelle = cal.get(Calendar.YEAR);

		int idAnnuel = (anneActuelle - 2017) +1;

		vGuichetValeur.setId(new Long(idAnnuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceBonEntree(new Long(
				vNumGuichetBonEntree + 1L));
		/** Modification de la valeur en base du numéro. */
		this.guichetAnnuelDomaine
				.modifierGuichetBonEntreeAnnuel(vGuichetValeur);
		return vNumBonEntree.toString();
	}

	private String getNumeroBonSortie(final Calendar dateBonSortie) {

		Long vNumGuichetBonSortie = this.guichetAnnuelDomaine.getNextNumBonSortieReference();
		/** Année courante. */
		int vAnneeCourante = dateBonSortie.get(Calendar.YEAR);
		/** Format du numero de la Bon Reception= AAAA-NN. */
		StringBuilder vNumBonSortie = new StringBuilder("");
		vNumBonSortie.append(vAnneeCourante);
		vNumBonSortie.append(String.format("%05d", vNumGuichetBonSortie));
		/** Inserer une nouvelle valeur dans Guichet BonReception. */
		GuichetAnnuelValue vGuichetValeur = new GuichetAnnuelValue();
		

		Calendar cal = Calendar.getInstance();
		int anneActuelle = cal.get(Calendar.YEAR);

		int idAnnuel = (anneActuelle - 2017) +1;

		vGuichetValeur.setId(new Long(idAnnuel));
		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
		vGuichetValeur.setNumReferenceBonSortie(new Long(
				vNumGuichetBonSortie + 1L));
		/** Modification de la valeur en base du numéro. */
		this.guichetAnnuelDomaine
				.modifierGuichetBonSortieAnnuel(vGuichetValeur);
		return vNumBonSortie.toString();
	}

	@Override
	public boolean ofIsReserved(Long ofId) {
		return this.bonMouvementPersistance.ofIsReserved(ofId);
	}

}
