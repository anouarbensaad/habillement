package com.gpro.consulting.tiers.gpao.persitance.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gc.persitance.entite.ProduitCommandeEntite;
import com.gpro.consulting.tiers.gpao.coordination.value.CauseVariationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ChaineValue;
import com.gpro.consulting.tiers.gpao.coordination.value.CompositionOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.DetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.PhaseOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.StatutOfValue;
import com.gpro.consulting.tiers.gpao.persitance.entite.CauseVariationEntitie;
import com.gpro.consulting.tiers.gpao.persitance.entite.ChaineEntite;
import com.gpro.consulting.tiers.gpao.persitance.entite.CompositionOfEntite;
import com.gpro.consulting.tiers.gpao.persitance.entite.DetailOfEntite;
import com.gpro.consulting.tiers.gpao.persitance.entite.OrdreFabricationEntite;
import com.gpro.consulting.tiers.gpao.persitance.entite.PhaseOfEntite;
import com.gpro.consulting.tiers.gpao.persitance.entite.StatutOfEntite;

/**
 * The Class PersistanceUtilitiesGPAO.
 * 
 * @author Ameni
 */

@Component
public class PersistanceUtilities {

	private static final Logger logger = LoggerFactory.getLogger(PersistanceUtilities.class);
	
	/**
	 * Instanciation du gestionnaire de persistance
	 */
	private static PersistanceUtilities instance = new PersistanceUtilities();

	/******************************** ToValue *************************************/
	/**
	 * Methode de Conversion d'un ordre de Fabrication Entite en un Objet Valeur
	 * 
	 * @param pOrdreFabricationEntite
	 * @return pOrdreFabricationValue
	 */
	public static OrdreFabricationValue ToValue(
			OrdreFabricationEntite pOrdreFabricationEntite) {
		OrdreFabricationValue vOrdreFabricationValue = new OrdreFabricationValue();
		/** Id */
		vOrdreFabricationValue.setId(pOrdreFabricationEntite.getId());
		/** Numero */
		vOrdreFabricationValue.setNumero(pOrdreFabricationEntite.getNumero());
		
		/** Numero */
		vOrdreFabricationValue.setNumOFBeforeUpdate(pOrdreFabricationEntite.getNumero());
		
		
		/** Observation */
		vOrdreFabricationValue.setObservations(pOrdreFabricationEntite
				.getObservations());
		/** Date Introduction */
		vOrdreFabricationValue.setDateIntroduction(pOrdreFabricationEntite
				.getDateIntroduction());
		/** etat */
		vOrdreFabricationValue.setEtat(pOrdreFabricationEntite.getEtat());
		/** quantite (champ calculable) **/
		vOrdreFabricationValue.setQuantite(pOrdreFabricationEntite
				.getQuantite());
		
		vOrdreFabricationValue.setProduitId(pOrdreFabricationEntite.getProduitId());
		vOrdreFabricationValue.setPartieInterresId(pOrdreFabricationEntite.getPartieInterresId());
		
		vOrdreFabricationValue.setDateLivraison(pOrdreFabricationEntite.getDateLivraison());
		
		vOrdreFabricationValue.setNumeroInterne(pOrdreFabricationEntite.getNumeroInterne());
		
		
		vOrdreFabricationValue.setQtCoupe(((pOrdreFabricationEntite.getQtCoupe() != null) ? pOrdreFabricationEntite.getQtCoupe() : 0));
		vOrdreFabricationValue.setQtSortie(((pOrdreFabricationEntite.getQtSortie() != null) ? pOrdreFabricationEntite.getQtSortie() : 0));
		vOrdreFabricationValue.setQtFinition(((pOrdreFabricationEntite.getQtFinition() != null) ? pOrdreFabricationEntite.getQtFinition() : 0));
		vOrdreFabricationValue.setQtColisage(((pOrdreFabricationEntite.getQtColisage() != null) ? pOrdreFabricationEntite.getQtColisage() : 0));
		vOrdreFabricationValue.setQtExpedition(((pOrdreFabricationEntite.getQtExpedition() != null) ? pOrdreFabricationEntite.getQtExpedition() : 0));
		
		
		vOrdreFabricationValue.setQtSupp1(((pOrdreFabricationEntite.getQtSupp1()!= null) ? pOrdreFabricationEntite.getQtSupp1() : 0));
		vOrdreFabricationValue.setQtSupp2(((pOrdreFabricationEntite.getQtSupp2()!= null) ? pOrdreFabricationEntite.getQtSupp2() : 0));
		
		
		vOrdreFabricationValue.setQtEngagement(((pOrdreFabricationEntite.getQtEngagement()!= null) ? pOrdreFabricationEntite.getQtEngagement() : 0));
		
		
		
		
		
		
		
		
		// Liste compositionOF
		/**
		 * 2 eme version
		 
				if (pOrdreFabricationEntite.getCompositionOrdres() != null) {
					Set<CompositionOfValue> vListeCompositionsOF = new HashSet<CompositionOfValue>();
					for (CompositionOfEntite vCompositionEntite : pOrdreFabricationEntite
							.getCompositionOrdres()) {
						CompositionOfValue vComposition = toValue(vCompositionEntite);
						vListeCompositionsOF.add(vComposition);
					}
					vOrdreFabricationValue.setCompositionsOF(vListeCompositionsOF);
				}
		*/
		// Liste DetailOF
		if (pOrdreFabricationEntite.getDetailOrdres() != null) {

			Set<DetailOfValue> vListeDetailsOF = new HashSet<DetailOfValue>();

			for (DetailOfEntite vDetailEntite : pOrdreFabricationEntite
					.getDetailOrdres()) {
				DetailOfValue vDetail = toValue(vDetailEntite);
				vListeDetailsOF.add(vDetail);
			}
			vOrdreFabricationValue.setDetailsOF(vListeDetailsOF);
		}
		// Liste PhaseOF
		if (pOrdreFabricationEntite.getPhaseOrdres() != null) {

			Set<PhaseOfValue> vListePhasesOF = new HashSet<PhaseOfValue>();

			for (PhaseOfEntite vPhaseEntite : pOrdreFabricationEntite.getPhaseOrdres()) {
				PhaseOfValue vPhase = toValue(vPhaseEntite);
				vListePhasesOF.add(vPhase);
			}
			vOrdreFabricationValue.setPhasesOF(vListePhasesOF);
		}
		return vOrdreFabricationValue;
	}

	private static PhaseOfValue toValue(PhaseOfEntite pPhaseEntite) {
		PhaseOfValue vPhaseOfValue = new PhaseOfValue();
		vPhaseOfValue.setId(pPhaseEntite.getId());
		vPhaseOfValue.setChaineId(pPhaseEntite.getChaineId());
		vPhaseOfValue.setDateDebut(pPhaseEntite.getDateDebut());
		vPhaseOfValue.setDateFin(pPhaseEntite.getDateFin());
		vPhaseOfValue.setDevise(pPhaseEntite.getDevise());
		vPhaseOfValue.setClientId(pPhaseEntite.getClientId());
		vPhaseOfValue.setFacturee(pPhaseEntite.isFacturee());
		vPhaseOfValue.setMethode(pPhaseEntite.getMethode());
		vPhaseOfValue.setPhaseId(pPhaseEntite.getPhaseId());
		vPhaseOfValue.setPrix(pPhaseEntite.getPrix());

		return vPhaseOfValue;
	}

	/************************************************
	 * Convertion TO ValueSuivi********************************************
	 * 
	 * @param pPhaseEntite
	 * @returnv PhaseOfValue
	 */
	public static PhaseOfValue toValueSuivi(PhaseOfEntite pPhaseOfEntite) {
		PhaseOfValue vPhaseOfValue = new PhaseOfValue();
		vPhaseOfValue.setId(pPhaseOfEntite.getId());
		vPhaseOfValue.setChaineId(pPhaseOfEntite.getChaineId());
		vPhaseOfValue.setDateDebut(pPhaseOfEntite.getDateDebut());
		vPhaseOfValue.setDateFin(pPhaseOfEntite.getDateFin());
		vPhaseOfValue.setDevise(pPhaseOfEntite.getDevise());
		vPhaseOfValue.setClientId(pPhaseOfEntite.getClientId());
		vPhaseOfValue.setFacturee(pPhaseOfEntite.isFacturee());
		//LOGGER.info(" ! Methode TOVALUE" + pPhaseOfEntite.getMethode());
		vPhaseOfValue.setMethode(pPhaseOfEntite.getMethode());
		vPhaseOfValue.setPhaseId(pPhaseOfEntite.getPhaseId());
		vPhaseOfValue.setPrix(pPhaseOfEntite.getPrix());
		vPhaseOfValue.setDateDebutreel(pPhaseOfEntite.getDateDebutreel());
		vPhaseOfValue.setDateFinreel(pPhaseOfEntite.getDateFinreel());
		vPhaseOfValue.setObservations(pPhaseOfEntite.getObservations());
		vPhaseOfValue.setQuantite(pPhaseOfEntite.getQuantite());
		vPhaseOfValue.setManque(pPhaseOfEntite.getManque());
		vPhaseOfValue.setCloture(pPhaseOfEntite.getCloture());
		vPhaseOfValue.setValide(pPhaseOfEntite.getValide());

		if (pPhaseOfEntite.getCauseVariationEntitie() != null) {

			Set<CauseVariationValue> vListeCauseVariation = new HashSet<CauseVariationValue>();

			for (CauseVariationEntitie vCauseVariationValue : pPhaseOfEntite
					.getCauseVariationEntitie()) {
				CauseVariationValue vCp = toValue(vCauseVariationValue);
				vListeCauseVariation.add(vCp);
			}

			vPhaseOfValue.setCauseVariation(vListeCauseVariation);
		}
		return vPhaseOfValue;
	}

	/********************* Converstion ListePhaseOfEntite en ListePhaseOfValue **/
	/**
	 * @param
	 * @return
	 */
	public static List<PhaseOfValue> tolistEmployeValues(
			List<PhaseOfEntite> pPhaseOfEntite) {
		List<PhaseOfValue> PhaseOfValue = new ArrayList<PhaseOfValue>();
		for (PhaseOfEntite PhaseOfPersistanceEntite : pPhaseOfEntite) {
			PhaseOfValue.add(PersistanceUtilities
					.toValueSuivi(PhaseOfPersistanceEntite));
		}
		return PhaseOfValue;
	}

	/*************************
	 * Convertion TO Value Cause Variation
	 * ****************************************
	 * 
	 * @parareturnm pCauseVariationEntite
	 * @returnv CauseVariationValue
	 */
	public static CauseVariationValue toValue(
			CauseVariationEntitie pCauseVariationEntite) {
		CauseVariationValue vCauseVariationValue = new CauseVariationValue();
		vCauseVariationValue.setId(pCauseVariationEntite.getId());
		vCauseVariationValue.setObservations(pCauseVariationEntite
				.getObservations());
		vCauseVariationValue.setQuantite(pCauseVariationEntite.getQuantite());
		return vCauseVariationValue;
	}

	public static DetailOfValue toValue(DetailOfEntite pDetailEntite) {
		DetailOfValue vDetailOfValue = new DetailOfValue();
		vDetailOfValue.setId(pDetailEntite.getId());
		vDetailOfValue.setCouleurId(pDetailEntite.getCouleurId());
		vDetailOfValue.setTailleId(pDetailEntite.getTailleId());
		vDetailOfValue.setQuantite(pDetailEntite.getQuantite());
		vDetailOfValue.setQteStock(pDetailEntite.getQteStock());
		
		vDetailOfValue.setOrdre(pDetailEntite.getOrdre().getId());
		return vDetailOfValue;
	}

	/**
	 * toEntite CompositionOF
	 * 
	 * @param vCompositionEntite
	 * @return
	 */
	private static CompositionOfValue toValue(
			CompositionOfEntite pCompositionEntite) {

		CompositionOfValue vCompositionOfValue = new CompositionOfValue();
		vCompositionOfValue.setId(pCompositionEntite.getId());
		vCompositionOfValue.setQuantite(pCompositionEntite.getQuantite());
		// A Modifier par produitCommandeID
		vCompositionOfValue.setCommandeId(pCompositionEntite
				.getProduitCommandeEntite().getId());
		// Ajoutée pour pouvoir Afficher les données reliées à QuantiteVue de la
		// table ProduitCommade et CommandeVente
		/** Data de la table Produit **/

		//LOGGER.info("----------- ToVALUE Produitcommande ----------- ");

		if (pCompositionEntite.getProduitCommandeEntite() != null) {
			if (pCompositionEntite.getProduitCommandeEntite().getProduit() != null) {
				vCompositionOfValue.setIdProduit(pCompositionEntite
						.getProduitCommandeEntite().getProduit().getId());
				vCompositionOfValue
						.setReferenceProduit(pCompositionEntite
								.getProduitCommandeEntite().getProduit()
								.getReference());
				vCompositionOfValue.setDesignationProduit(pCompositionEntite
						.getProduitCommandeEntite().getProduit()
						.getDesignation());
				vCompositionOfValue.setSousFamilleProduit(pCompositionEntite
						.getProduitCommandeEntite().getProduit()
						.getSousFamille().getId());
				if (pCompositionEntite.getProduitCommandeEntite().getProduit()
						.getSousFamille() != null) {
					vCompositionOfValue
							.setSousfamilleProduitDesignation(pCompositionEntite
									.getProduitCommandeEntite().getProduit()
									.getSousFamille().getDesignation());
				} else {
					//LOGGER.info(" -- Produitcommande.getProduit is NULL");
				}
			} else {
				//LOGGER.info(" -- ProduitCommande is NULL");
			}
		}
		/** data de la table commande Vente **/
		if (pCompositionEntite.getProduitCommandeEntite() != null) {
			vCompositionOfValue.setQuantiteBC(pCompositionEntite
					.getProduitCommandeEntite().getQuantite());
			if (pCompositionEntite.getProduitCommandeEntite()
					.getCommandeVente() != null) {
				vCompositionOfValue.setNumeroBC(pCompositionEntite
						.getProduitCommandeEntite().getCommandeVente()
						.getReference());
				vCompositionOfValue.setTypeBC(pCompositionEntite
						.getProduitCommandeEntite().getCommandeVente()
						.getTypeCommande_id());
			} else {
				//LOGGER.info(" -- ProduitCommandeEntite.getCommandeVente is NULL");
			}
		} else {
			//LOGGER.info(" -- ProduitCommandeEntite is NULL");
		}
		return vCompositionOfValue;
	}

	/** Converstion ChaineEntite entite en ChaineValue **/
	/**
	 * @param ChaineEntite
	 * @return
	 */
	public static ChaineValue toValue(ChaineEntite pChaineEntite) {
		ChaineValue vChaineValue = new ChaineValue();
		vChaineValue.setId(pChaineEntite.getId());
		vChaineValue.setDesignation(pChaineEntite.getDesignation());
		return vChaineValue;
	}

	/** Converstion StatutOfEntite entite en StatutOfValue **/
	/**
	 * @param StatutOfEntite
	 * @return
	 */
	public static StatutOfValue toValue(StatutOfEntite pStatutOFEntite) {
		StatutOfValue vStatutOFValue = new StatutOfValue();
		vStatutOFValue.setId(pStatutOFEntite.getId());
		vStatutOFValue.setDesignation(pStatutOFEntite.getDesignation());
		return vStatutOFValue;
	}

	/************************************* ToEntite *******************************************/
	/**
	 * Methode de Conversion d'un ordre de Fabrication Valeur en Entite
	 * 
	 * @param pOrdreFabricationValue
	 * @return vOrdreFabricationEntite
	 */
	public static OrdreFabricationEntite ToEntite(
			OrdreFabricationValue pOrdreFabricationValue,
			Set<ProduitCommandeEntite> pListProduitCommandeEntite) {
		OrdreFabricationEntite vOrdreFabricationEntite = new OrdreFabricationEntite();
		/** Id */
		if (pOrdreFabricationValue.getId() != null) {
			vOrdreFabricationEntite.setId(pOrdreFabricationValue.getId());
		}
		/** Numero */
		vOrdreFabricationEntite.setNumero(pOrdreFabricationValue.getNumero());
		/** Observation */
		vOrdreFabricationEntite.setObservations(pOrdreFabricationValue
				.getObservations());
		/** Date Introduction */
		vOrdreFabricationEntite.setDateIntroduction(pOrdreFabricationValue
				.getDateIntroduction());
		/** Etat */
		vOrdreFabricationEntite.setEtat(pOrdreFabricationValue.getEtat());
		/** Quantite (Champ calculable) */
		vOrdreFabricationEntite.setQuantite(pOrdreFabricationValue
				.getQuantite());

		vOrdreFabricationEntite.setProduitId(pOrdreFabricationValue.getProduitId());
		vOrdreFabricationEntite.setPartieInterresId(pOrdreFabricationValue.getPartieInterresId());
		
		vOrdreFabricationEntite.setDateLivraison(pOrdreFabricationValue.getDateLivraison());
		
		vOrdreFabricationEntite.setNumeroInterne(pOrdreFabricationValue.getNumeroInterne());
		
	//	System.out.println("### !sort  :  "+pOrdreFabricationValue.getQtSortie());
		
		vOrdreFabricationEntite.setQtCoupe(((pOrdreFabricationValue.getQtCoupe() != null) ? pOrdreFabricationValue.getQtCoupe() : 0));
		vOrdreFabricationEntite.setQtEngagement(((pOrdreFabricationValue.getQtEngagement() != null) ? pOrdreFabricationValue.getQtEngagement() : 0));
		vOrdreFabricationEntite.setQtSortie(((pOrdreFabricationValue.getQtSortie()!= null) ? pOrdreFabricationValue.getQtSortie() : 0));
		vOrdreFabricationEntite.setQtFinition(((pOrdreFabricationValue.getQtFinition() != null) ? pOrdreFabricationValue.getQtFinition() : 0));
		vOrdreFabricationEntite.setQtColisage(((pOrdreFabricationValue.getQtColisage() != null) ? pOrdreFabricationValue.getQtColisage() : 0));
		vOrdreFabricationEntite.setQtExpedition(((pOrdreFabricationValue.getQtExpedition()!= null) ? pOrdreFabricationValue.getQtExpedition() : 0));
		vOrdreFabricationEntite.setQtSupp1(((pOrdreFabricationValue.getQtSupp1()!= null) ? pOrdreFabricationValue.getQtSupp1() : 0));
		vOrdreFabricationEntite.setQtSupp2(((pOrdreFabricationValue.getQtSupp2()!= null) ? pOrdreFabricationValue.getQtSupp2() : 0));
		
		
		
		
		// Liste CompositionsOf
		/**
		 * 2 eme version
		if (pOrdreFabricationValue.getCompositionsOF() != null) {
			Set<CompositionOfEntite> vListeCompositionsOf = new HashSet<CompositionOfEntite>();
			for (CompositionOfValue vCompositionOfsValue : pOrdreFabricationValue
					.getCompositionsOF()) {
				ProduitCommandeEntite vEntiteProduitCommande = rechercheEntiteProduitCommandeFromList(
						pListProduitCommandeEntite,
						vCompositionOfsValue.getCommandeId());
				CompositionOfEntite vComposition = toEntite(
						vCompositionOfsValue, vEntiteProduitCommande);
				vComposition.setOrdre(vOrdreFabricationEntite);
				vListeCompositionsOf.add(vComposition);
			}

			vOrdreFabricationEntite.setCompositionOrdres(vListeCompositionsOf);
		}
		*/
		// ListeDetailsOf
		if (pOrdreFabricationValue.getDetailsOF() != null) {

			Set<DetailOfEntite> vListeDetailOf = new HashSet<DetailOfEntite>();

			for (DetailOfValue vDetailOfValue : pOrdreFabricationValue
					.getDetailsOF()) {
				DetailOfEntite vDetail = toEntite(vDetailOfValue);
				vDetail.setOrdre(vOrdreFabricationEntite);
				vListeDetailOf.add(vDetail);
			}

			vOrdreFabricationEntite.setDetailOrdres(vListeDetailOf);
		}
		// ListePhasesOf
		if (pOrdreFabricationValue.getPhasesOF() != null) {

			Set<PhaseOfEntite> vListePhaseOf = new HashSet<PhaseOfEntite>();

			for (PhaseOfValue vPhaseOfValue : pOrdreFabricationValue
					.getPhasesOF()) {
				PhaseOfEntite vPhase = toEntite(vPhaseOfValue);
				vPhase.setOrdre(vOrdreFabricationEntite);
				vListePhaseOf.add(vPhase);
			}

			vOrdreFabricationEntite.setPhaseOrdres(vListePhaseOf);
		}
		return vOrdreFabricationEntite;

	}

	private static ProduitCommandeEntite rechercheEntiteProduitCommandeFromList(
			Set<ProduitCommandeEntite> pListProduitCommandeEntite,
			Long produitCommandeId) {
		//LOGGER.info("** RechercheEntiteProduitCommandeFromList.. ");
		ProduitCommandeEntite vEntite = new ProduitCommandeEntite();
		//LOGGER.info("* pListProduitCommandeEntite Size :"
				//+ pListProduitCommandeEntite.size());
		if (pListProduitCommandeEntite != null) {

			for (ProduitCommandeEntite produitCommandeEntite : pListProduitCommandeEntite) {
				//LOGGER.info(produitCommandeEntite == null ? "NULL" : "NOT NULL");
				//LOGGER.info("produitCommandeEntite.getId() "
						//+ produitCommandeEntite.getId());
				//LOGGER.info("produitCommandeEntite " + produitCommandeId);
				if (produitCommandeEntite.getId().equals(produitCommandeId))
					vEntite = produitCommandeEntite;
				break;
			}
		}
		return vEntite;
	}

	private static PhaseOfEntite toEntite(PhaseOfValue pPhaseOfValue) {
		PhaseOfEntite vPhaseOfEntite = new PhaseOfEntite();

		/** Persistance niveau base de données */
		if (pPhaseOfValue.getId() != null) {
			vPhaseOfEntite.setId(pPhaseOfValue.getId());
		}
		vPhaseOfEntite.setChaineId(pPhaseOfValue.getChaineId());
		vPhaseOfEntite.setDateDebut(pPhaseOfValue.getDateDebut());
		vPhaseOfEntite.setDateFin(pPhaseOfValue.getDateFin());
		vPhaseOfEntite.setDevise(pPhaseOfValue.getDevise());
		vPhaseOfEntite.setClientId(pPhaseOfValue.getClientId());
		vPhaseOfEntite.setFacturee(pPhaseOfValue.isFacturee());
		//LOGGER.info("Methode TOENTITE" + pPhaseOfValue.getMethode());
		vPhaseOfEntite.setMethode(pPhaseOfValue.getMethode());
		vPhaseOfEntite.setPhaseId(pPhaseOfValue.getPhaseId());
		vPhaseOfEntite.setPrix(pPhaseOfValue.getPrix());
		return vPhaseOfEntite;

	}

	/*************************************
	 * convertion To EntiteSuivi************************************************
	 * 
	 * @param pPhaseOfValue
	 * @returnvPhaseOfEntite
	 */
	public static PhaseOfEntite toEntiteSuivi(PhaseOfValue pPhaseOfValue) {
		PhaseOfEntite vPhaseOfEntite = new PhaseOfEntite();

		/** Persistance niveau base de données */
		if (pPhaseOfValue.getId() != null) {
			vPhaseOfEntite.setId(pPhaseOfValue.getId());
		}
		vPhaseOfEntite.setChaineId(pPhaseOfValue.getChaineId());
		vPhaseOfEntite.setDateDebut(pPhaseOfValue.getDateDebut());
		vPhaseOfEntite.setDateFin(pPhaseOfValue.getDateFin());
		vPhaseOfEntite.setDevise(pPhaseOfValue.getDevise());
		vPhaseOfEntite.setClientId(pPhaseOfValue.getClientId());
		vPhaseOfEntite.setFacturee(pPhaseOfValue.isFacturee());
		//LOGGER.info("Methode TOENTITE" + pPhaseOfValue.getMethode());
		vPhaseOfEntite.setMethode(pPhaseOfValue.getMethode());
		vPhaseOfEntite.setPhaseId(pPhaseOfValue.getPhaseId());
		vPhaseOfEntite.setPrix(pPhaseOfValue.getPrix());
		vPhaseOfEntite.setDateDebutreel(pPhaseOfValue.getDateDebutreel());
		vPhaseOfEntite.setDateFinreel(pPhaseOfValue.getDateFinreel());
		vPhaseOfEntite.setObservations(pPhaseOfValue.getObservations());
		vPhaseOfEntite.setQuantite(pPhaseOfValue.getQuantite());
		vPhaseOfEntite.setManque(pPhaseOfValue.getManque());
		vPhaseOfEntite.setCloture(pPhaseOfValue.getCloture());
		vPhaseOfEntite.setValide(pPhaseOfValue.getValide());

		if (pPhaseOfValue.getCauseVariation() != null) {

			Set<CauseVariationEntitie> vListeCauseVariation = new HashSet<CauseVariationEntitie>();

			for (CauseVariationValue vCauseVariationValue : pPhaseOfValue
					.getCauseVariation()) {
				CauseVariationEntitie vCp = toEntity(vCauseVariationValue);
				vCp.setPhaseOfEntite(vPhaseOfEntite);
				vListeCauseVariation.add(vCp);
			}

			vPhaseOfEntite.setCauseVariationEntitie(vListeCauseVariation);
			;

		}
		return vPhaseOfEntite;

	}

	/***************************
	 * Convertion TO Entity Cause Variation
	 * **************************************** toEntite CauseVariation
	 * 
	 * @param pCauseVariationValue
	 * @return vCauseVariationEntitie
	 */
	public static CauseVariationEntitie toEntity(
			CauseVariationValue pCauseVariationValue) {
		CauseVariationEntitie vCauseVariationEntitie = new CauseVariationEntitie();
		if (pCauseVariationValue.getId() != null) {
			vCauseVariationEntitie.setId(vCauseVariationEntitie.getId());
		}
		vCauseVariationEntitie.setId(vCauseVariationEntitie.getId());
		vCauseVariationEntitie.setObservations(vCauseVariationEntitie
				.getObservations());
		vCauseVariationEntitie
				.setQuantite(vCauseVariationEntitie.getQuantite());
		return vCauseVariationEntitie;

	}

	/**
	 * toEntite DetailOf
	 * 
	 * @param pDetailOfValue
	 * @return vDetailOfEntite
	 */
	public static DetailOfEntite toEntite(DetailOfValue pDetailOfValue) {
		DetailOfEntite vDetailOfEntite = new DetailOfEntite();

		/** Persistance niveau base de données */
		if (pDetailOfValue.getId() != null) {
			vDetailOfEntite.setId(pDetailOfValue.getId());
		}
		vDetailOfEntite.setCouleurId(pDetailOfValue.getCouleurId());
		vDetailOfEntite.setTailleId(pDetailOfValue.getTailleId());
		vDetailOfEntite.setQuantite(pDetailOfValue.getQuantite());

		OrdreFabricationEntite of = new OrdreFabricationEntite();
		of.setId(pDetailOfValue.getOrdre());
		
		
		vDetailOfEntite.setOrdre(of);
		
		if(pDetailOfValue.getQteStock() == null)
			vDetailOfEntite.setQteStock(0l);
		        else
			vDetailOfEntite.setQteStock(pDetailOfValue.getQteStock());
		
		
		return vDetailOfEntite;

	}

	/**
	 * toEntite CompositionOf
	 * 
	 * @param pCompositionOfValue
	 * @return vCompositionOfEntite
	 */
	public static CompositionOfEntite toEntite(
			CompositionOfValue pCompositionOfValue,
			ProduitCommandeEntite pProduitCommandeEntite) {
		CompositionOfEntite vCompositionOfEntite = new CompositionOfEntite();

		/** Persistance niveau base de données */
		if (pCompositionOfValue.getId() != null) {
			vCompositionOfEntite.setId(pCompositionOfValue.getId());
		}
		vCompositionOfEntite.setProduitCommandeEntite(pProduitCommandeEntite);
		vCompositionOfEntite.setQuantite(pCompositionOfValue.getQuantite());
		return vCompositionOfEntite;

	}

	/** Converstion ChaineOFValue en ChaineOFEntite **/
	/**
	 * @param pChaineValue
	 * @return vChaineEntite
	 */
	public static ChaineEntite toEntity(ChaineValue pChaineValue) {
		ChaineEntite vChaineEntite = new ChaineEntite();
		if (pChaineValue.getId() != null) {
			vChaineEntite.setId(pChaineValue.getId());
		}
		vChaineEntite.setDesignation(pChaineValue.getDesignation());
		return vChaineEntite;
	}

	/** Convertion StatutOfValue en StatutOfEntite **/
	/**
	 * @param pStatutOfValue
	 * @return vStatutOfEntite
	 */
	public static StatutOfEntite toEntity(StatutOfValue pStatutOFValue) {
		StatutOfEntite vStatutOFEntite = new StatutOfEntite();
		if (pStatutOFValue.getId() != null) {
			vStatutOFEntite.setId(pStatutOFValue.getId());
		}
		vStatutOFEntite.setDesignation(pStatutOFValue.getDesignation());
		return vStatutOFEntite;
	}

	/********* Getter & Setter ********/
	/**
	 * @return the instance
	 */
	public static PersistanceUtilities getInstance() {
		return instance;
	}

	/**
	 * @param instance
	 *            the instance to set
	 */
	public static void setInstance(PersistanceUtilities instance) {
		PersistanceUtilities.instance = instance;
	}

}
