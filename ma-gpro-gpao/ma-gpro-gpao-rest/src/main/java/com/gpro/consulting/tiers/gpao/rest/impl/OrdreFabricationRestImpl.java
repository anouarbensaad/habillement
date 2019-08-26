package com.gpro.consulting.tiers.gpao.rest.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.PhaseValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.persistance.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;
import com.gpro.consulting.tiers.commun.service.IPartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.IPhaseService;
import com.gpro.consulting.tiers.commun.service.IProduitService;
import com.gpro.consulting.tiers.commun.service.ISousFamilleProduitService;
import com.gpro.consulting.tiers.gc.coordination.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.RechercheMulticritereDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.vue.PhaseProduitVue;
import com.gpro.consulting.tiers.gpao.coordination.vue.QuantiteVue;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gpao.rest.IOrdreFabricationRest;
import com.gpro.consulting.tiers.gpao.service.IGestionnaireCacheService;
import com.gpro.consulting.tiers.gpao.service.IOrdreFabricationService;

/**
 * @author toshiba
 *
 */
@Controller
@RequestMapping("/ordreFabrication")
public class OrdreFabricationRestImpl implements IOrdreFabricationRest {

	private static final Logger logger = LoggerFactory.getLogger(OrdreFabricationRestImpl.class);

	@Autowired
	private IOrdreFabricationService ordreFabricationService;

	@Autowired
	private IGestionnaireCacheService gestionnaireCacheServiceGpao;

	@Autowired
	private ISousFamilleProduitService sousFamilleProduitService;

	@Autowired
	private IProduitService produitService;

	@Autowired
	private IPhaseService phaseService;

	@Autowired
	private IPartieInteresseeService partieInteresseeService;
	
	@Autowired
	private IProduitPersistance produitPersistance;

	@Autowired
	private IOrdreFabricationPersistance IOrdreFabricationPersistance;
	
	@Autowired
	IPartieInteresseePersistance partieInteresseePersistance;

	
	
	/************* get Ordre de Fabrication By ID *************/
	/**
	 * recherche par Id d'un ordre de Fabrication
	 * 
	 * @param id
	 * @return id
	 */
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody OrdreFabricationValue getOrdreFabrication(@PathVariable Long id) {

		//LOGGER.info("recherche d'un OrdreFabrication  /getId:" + id);
		OrdreFabricationValue result = ordreFabricationService.rechercheOrdreFabricationParId(id);

		if (result != null) {

			List<SousFamilleProduitValue> listSousFamilleProduit = sousFamilleProduitService.listeSousFamilleProduit();
			Map<Long, String> mapSousFamilleProduit = new HashMap<Long, String>();
			for (SousFamilleProduitValue sousFamilleProduit : listSousFamilleProduit) {
				Long key = sousFamilleProduit.getId();
				if (mapSousFamilleProduit.get(key) == null) {
					mapSousFamilleProduit.put(sousFamilleProduit.getId(), sousFamilleProduit.getDesignation());
				}
			}

			if (result.getProduitId() != null) {

				ProduitValue produit = produitService.rechercheProduitById(result.getProduitId());

				if (produit != null) {
					result.setProduitDesignation(produit.getDesignation());
					result.setProduitReference(produit.getReference());
					if (produit.getPartieIntersseId() != null) {
						PartieInteresseValue pi = new PartieInteresseValue();
						pi.setId(produit.getPartieIntersseId());
						PartieInteresseValue data = partieInteresseeService.recherchePartieInteresseeParId(pi);

						if (data != null) {
							result.setPartieInterresAbreviation(data.getAbreviation());
						}
					}
					if (mapSousFamilleProduit.containsKey(produit.getSousFamilleId())) {
						result.setProduitSousFamilleDesignation(mapSousFamilleProduit.get(produit.getSousFamilleId()));

					}
				}
			}

		}
		return result;
	}

	@RequestMapping(value = "/getQteProduitId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<QuantiteVue> getListQuantite(@PathVariable Long id) {

		//LOGGER.info("recherche Quantite à partir d'un Produit. /getQteProduitId:" + id);

		return ordreFabricationService.rechercheQuantiteParIdProduit(id);
	}

	@RequestMapping(value = "/getPhaseProduitId:{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<PhaseProduitVue> getListPhase(@PathVariable Long id) {

		//LOGGER.info("recherche Phase à partir d'un Produit. /getPhaseProduitId:" + id);

		List<PhaseProduitVue> listPhaseProduitVue = ordreFabricationService.recherchePhaseParIdProduit(id);

		List<PhaseValue> listPahse = phaseService.listePhase();
		Map<Long, String> map = new HashMap<Long, String>();
		for (PhaseValue phase : listPahse) {
			Long key = phase.getId();
			if (map.get(key) == null) {
				map.put(phase.getId(), phase.getDesignation());
			}
		}

		for (PhaseProduitVue phaseProduit : listPhaseProduitVue) {
			if (map.containsKey(phaseProduit.getIdPhase())) {
				phaseProduit.setDesignation(map.get(phaseProduit.getIdPhase()));
			}
		}

		return listPhaseProduitVue;
	}

	/*******************************
	 * All OrdreFabrication
	 *********************************/
	/**
	 * Affichage de tous les Ordres de Fabrication
	 * 
	 * @return List < OrdreFabricationValue >
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<OrdreFabricationValue> viewAllOrdreFabrication() {

		//LOGGER.info("Liste Ordre de Fabrication :/all");

		List<OrdreFabricationValue> vOrdreFabricationValue = ordreFabricationService.listeOrdreFabrication();
		// Traitement : transformation de l'Id a sa propre Designation
		for (OrdreFabricationValue ordreFabrication : vOrdreFabricationValue) {

			// Etat
			Map<String, String> mapA = gestionnaireCacheServiceGpao
					.rechercherProduitEtatOFParId(ordreFabrication.getEtat());

			// Etat(StatutOF)
			ordreFabrication.setEtatDesignation(mapA.get("etatOF"));

		}
		return vOrdreFabricationValue;
	}

	/****************** Creation Ordre de Fabrication ********************/

	/**
	 * Creation Ordre de Fabrication dans la BD
	 * 
	 * @param pOrdreFabricationValue
	 * @return Id
	 */
	@RequestMapping(value = "/creerOrdreFabrication", method = RequestMethod.POST)
	public @ResponseBody String creerOrdreFabrication(@RequestBody OrdreFabricationValue pOrdreFabricationValue) {

		//LOGGER.info("Creation Ordre de Fabrication :/creerOrdreFabrication, Numero : "
			//	+ pOrdreFabricationValue.getNumero());

		return this.ordreFabricationService.creerOrdreFabrication(pOrdreFabricationValue);
	}

	/**
	 * Modification Ordre de Fabrication dans la BD
	 * 
	 * @param pOrdreFabricationValue
	 * @return Id
	 */
	@RequestMapping(value = "/modifierOrdreFabrication", method = RequestMethod.POST)
	public @ResponseBody String modifierOrdreFabrication(@RequestBody OrdreFabricationValue pOrdreFabricationValue) {
		return this.ordreFabricationService.modifierOrdreFabrication(pOrdreFabricationValue);
	}

	/**
	 * Suppression d'un Ordre de Fabrication de la BD
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/supprimerOrdreFabrication:{id}", method = RequestMethod.DELETE)
	public void supprimerOrdreFabrication(@PathVariable("id") String id) {
		ordreFabricationService.supprimerOrdreFabrication(Long.valueOf(id));

	}

	/**
	 * Méthode de recherche multicritères
	 * @param RechercheMulticritereOrdreFabricationValue
	 * @return ResultatMulticritereOrdreFabricationValue
	 */
	@RequestMapping(value = "/rechercheOrdreFabricationMulticritere", method = RequestMethod.POST)
	public @ResponseBody ResultatMulticritereOrdreFabricationValue rechercherOrdreFabricationMultiCritere(
		   @RequestBody final RechercheMulticritereOrdreFabricationValue request) {

		//Check if all criterias are null so generic search
				request.setOptimized(this.checkForOptimization(request));
				
				
				
				if(request.getEtatCoupe() != null && request.getEtatCoupe().equals("PLUS")) request.setEtatCoupe("+");
				if(request.getEtatFabrication() !=null && request.getEtatFabrication().equals("PLUS")) request.setEtatFabrication("+");	
				if(request.getEtatCollisage() != null && request.getEtatCollisage().equals("PLUS")) request.setEtatCollisage("+");
				if(request.getEtatConditionnement() !=null && request.getEtatConditionnement().equals("PLUS")) request.setEtatConditionnement("+");	
				if(request.getEtatExpedition() != null && request.getEtatExpedition().equals("PLUS")) request.setEtatExpedition("+");	
				if(request.getEtatEngagement() != null && request.getEtatEngagement().equals("PLUS")) request.setEtatExpedition("+");	

				
				
				
		ResultatMulticritereOrdreFabricationValue result = ordreFabricationService
				.rechercherOrdreFabricationMultiCritere(request);

		// Traitement : transformation de l'Id a sa propre Designation
//		for (OrdreFabricationValue ordreFabrication : result.getOrdreFabricationValues()) {
//
//			// Etat
//			Map<String, String> mapA = gestionnaireCacheServiceGpao
//					.rechercherProduitEtatOFParId(ordreFabrication.getEtat());
//
//			// Etat(StatutOF)
//			ordreFabrication.setEtatDesignation(mapA.get("etatOF"));
//
//		}

		if (result != null) {

			List<SousFamilleProduitValue> listSousFamilleProduit = sousFamilleProduitService.listeSousFamilleProduit();
			Map<Long, String> mapSousFamilleProduit = new HashMap<Long, String>();
			for (SousFamilleProduitValue sousFamilleProduit : listSousFamilleProduit) {
				Long key = sousFamilleProduit.getId();
				if (mapSousFamilleProduit.get(key) == null) {
					mapSousFamilleProduit.put(sousFamilleProduit.getId(), sousFamilleProduit.getDesignation());
				}
			}

			for (OrdreFabricationValue ordreFabrication : result.getOrdreFabricationValues()) {

				if (ordreFabrication.getProduitId() != null) {

					ProduitValue produit = produitService.rechercheProduitById(ordreFabrication.getProduitId(), true);

					if (produit != null) {
						ordreFabrication.setProduitDesignation(produit.getDesignation());
						ordreFabrication.setProduitReference(produit.getReference());
						if (produit.getPartieIntersseId() != null) {
							PartieInteresseValue client = partieInteresseeService.getAbreviationClient(produit.getPartieIntersseId());

							if (client != null) {
								ordreFabrication.setPartieInterresAbreviation(client.getAbreviation());
							}
						}
						if (mapSousFamilleProduit.containsKey(produit.getSousFamilleId())) {
							ordreFabrication.setProduitSousFamilleDesignation(
									mapSousFamilleProduit.get(produit.getSousFamilleId()));
						}
					}
				}
				Map<String, String> mapA = gestionnaireCacheServiceGpao
						.rechercherProduitEtatOFParId(ordreFabrication.getEtat());

				// Etat(StatutOF)
				ordreFabrication.setEtatDesignation(mapA.get("etatOF"));
			}
		}

		return result;
	}

	
	
	/**
	 * Méthode de recherche multicritères
	 * ChartGPAO 
	 * @param RechercheMulticritereOrdreFabricationValue
	 * @return ResultatMulticritereOrdreFabricationValue
	 */
	
	@RequestMapping(value = "/rechercheOFChartMulticritere", method = RequestMethod.POST)
	public @ResponseBody ResultatMulticritereOrdreFabricationValue rechercheOFChartMulticritere(
			@RequestBody final RechercheMulticritereOrdreFabricationValue request) {

		//Check if all criterias are null so generic search
		request.setOptimized(this.checkForOptimization(request));
		
		ResultatMulticritereOrdreFabricationValue result = ordreFabricationService.rechercherOrdreFabricationMultiCritere(request);
		
//		//TODO cache
//		List<ProduitValue> listProduit = produitPersistance.listeProduit();
//		Map<Long, ProduitValue> mapProduits = new HashMap<Long, ProduitValue>();
//		for(ProduitValue produit : listProduit){
//			Long key = produit.getId();
//			if (mapProduits.get(key) == null) {
//				mapProduits.put(key, produit);
//			}
//		}
//		
//		//TODO cache
//		Map<Long, String> mapClientsAbreviations = new HashMap<Long, String>();
//		for(PartieInteresseValue client : partieInteresseePersistance.listePartieInteressee()){
//			Long key = client.getId();
//			if (mapClientsAbreviations.get(key) == null) {
//				mapClientsAbreviations.put(key, client.getAbreviation());
//			}
//		}
//		
//			for (OrdreFabricationValue ordreFabricationValue : result.getOrdreFabricationValues()) {
//
//				Long produitId = ordreFabricationValue.getProduitId();
//
//				if (produitId != null) {
//					if (mapProduits.containsKey(produitId)) {
//						ordreFabricationValue.setProduitDesignation(mapProduits.get(produitId).getDesignation());
//					}
//				}
//
//				Long clientId = ordreFabricationValue.getPartieInterresId();
//
//				if (clientId != null) {
//					if (mapClientsAbreviations.containsKey(clientId)) {
//						ordreFabricationValue.setPartieInterresAbreviation(mapClientsAbreviations.get(clientId));
//					}
//				}
//
//			}
//		}


		if (result != null) {

			List<SousFamilleProduitValue> listSousFamilleProduit = sousFamilleProduitService.listeSousFamilleProduit();
			Map<Long, String> mapSousFamilleProduit = new HashMap<Long, String>();
			for (SousFamilleProduitValue sousFamilleProduit : listSousFamilleProduit) {
				Long key = sousFamilleProduit.getId();
				if (mapSousFamilleProduit.get(key) == null) {
					mapSousFamilleProduit.put(sousFamilleProduit.getId(), sousFamilleProduit.getDesignation());
				}
			}

			for (OrdreFabricationValue ordreFabrication : result.getOrdreFabricationValues()) {

				if (ordreFabrication.getProduitId() != null) {

					ProduitValue produit = produitService.rechercheProduitById(ordreFabrication.getProduitId());

					if (produit != null) {
						ordreFabrication.setProduitDesignation(produit.getDesignation());
						ordreFabrication.setProduitReference(produit.getReference());
						if (produit.getPartieIntersseId() != null) {
							PartieInteresseValue pi = new PartieInteresseValue();
							pi.setId(produit.getPartieIntersseId());
							PartieInteresseValue data = partieInteresseeService.recherchePartieInteresseeParId(pi);
               
							if (data != null) {
								ordreFabrication.setPartieInterresAbreviation(data.getAbreviation());
							}
						}
						if (mapSousFamilleProduit.containsKey(produit.getSousFamilleId())) {
							ordreFabrication.setProduitSousFamilleDesignation(
									mapSousFamilleProduit.get(produit.getSousFamilleId()));
						}
					}
				}
			}
		}

		return result;
	}

	@RequestMapping(value = "/getCouleurOf:{ordreFabricationId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<CouleurValue> listeCouleurOf(@PathVariable Long ordreFabricationId) {

		//LOGGER.info("listeCouleurOf: Delegating request id to service layer.");

		return ordreFabricationService.listeCouleurOf(ordreFabricationId);
	}

	@RequestMapping(value = "/getTailleOf:{ordreFabricationId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<TailleValue> listeTailleOf(@PathVariable Long ordreFabricationId) {

		//LOGGER.info("listeCouleurOf: Delegating request id to service layer.");

		return ordreFabricationService.listeTailleOf(ordreFabricationId);
	}
	
	@RequestMapping(value = "/changerEtat", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody void changerEtat() {

		//LOGGER.info("listeCouleurOf: Delegating request id to service layer.");

		 ordreFabricationService.changerEtatOF();
	}
	
	@RequestMapping(value = "/exporterOF", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody void exporterOFToBC() {


		 ordreFabricationService.exporterOFToBC();
	}
	
	

	
	@RequestMapping(value = "/getByNum", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody OrdreFabricationValue getByNumero(@RequestBody String numero) {
		// TODO Auto-generated method stub
		
		OrdreFabricationValue result = ordreFabricationService
				.getByNumero(numero);
		
		if (result != null) {

			List<SousFamilleProduitValue> listSousFamilleProduit = sousFamilleProduitService
					.listeSousFamilleProduit();
			Map<Long, String> mapSousFamilleProduit = new HashMap<Long, String>();
			for (SousFamilleProduitValue sousFamilleProduit : listSousFamilleProduit) {
				Long key = sousFamilleProduit.getId();
				if (mapSousFamilleProduit.get(key) == null) {
					mapSousFamilleProduit.put(sousFamilleProduit.getId(),
							sousFamilleProduit.getDesignation());
				}
			}

			if (result.getProduitId() != null) {

				ProduitValue produit = produitService
						.rechercheProduitById(result.getProduitId());

				if (produit != null) {
					result.setProduitDesignation(produit.getDesignation());
					result.setProduitReference(produit.getReference());
					//result.setCadence(produit.getCadence());
					if (produit.getPartieIntersseId() != null) {
						PartieInteresseValue pi = new PartieInteresseValue();
						pi.setId(produit.getPartieIntersseId());
						PartieInteresseValue data = partieInteresseeService
								.recherchePartieInteresseeParId(pi);

						if (data != null) {
							result.setPartieInterresAbreviation(data
									.getAbreviation());
						}
					}
					if (mapSousFamilleProduit.containsKey(produit
							.getSousFamilleId())) {
						result.setProduitSousFamilleDesignation(mapSousFamilleProduit
								.get(produit.getSousFamilleId()));

					}
				}
			}


		}
		
		return result;
	}
	
	
	
	
	
	@RequestMapping(value = "/getByNumForGamme", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody OrdreFabricationValue getByNumeroForGamme(@RequestBody String numero) {
		// TODO Auto-generated method stub
		
		OrdreFabricationValue result = ordreFabricationService
				.getByNumeroAvailableForGamme(numero);
		
		if (result != null) {

			List<SousFamilleProduitValue> listSousFamilleProduit = sousFamilleProduitService
					.listeSousFamilleProduit();
			Map<Long, String> mapSousFamilleProduit = new HashMap<Long, String>();
			for (SousFamilleProduitValue sousFamilleProduit : listSousFamilleProduit) {
				Long key = sousFamilleProduit.getId();
				if (mapSousFamilleProduit.get(key) == null) {
					mapSousFamilleProduit.put(sousFamilleProduit.getId(),
							sousFamilleProduit.getDesignation());
				}
			}

			if (result.getProduitId() != null) {

				ProduitValue produit = produitService
						.rechercheProduitById(result.getProduitId());

				if (produit != null) {
					result.setProduitDesignation(produit.getDesignation());
					result.setProduitReference(produit.getReference());
					//result.setCadence(produit.getCadence());
					if (produit.getPartieIntersseId() != null) {
						PartieInteresseValue pi = new PartieInteresseValue();
						pi.setId(produit.getPartieIntersseId());
						PartieInteresseValue data = partieInteresseeService
								.recherchePartieInteresseeParId(pi);

						if (data != null) {
							result.setPartieInterresAbreviation(data
									.getAbreviation());
						}
					}
					if (mapSousFamilleProduit.containsKey(produit
							.getSousFamilleId())) {
						result.setProduitSousFamilleDesignation(mapSousFamilleProduit
								.get(produit.getSousFamilleId()));

					}
				}
			}


		}
		
		return result;
	}
	
	
	
	
	
	
	
	@RequestMapping(value = "/updateSuivi", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody void updateSuivi( @RequestBody final RechercheMulticritereDetailSaisieValue request ) {

		//LOGGER.info("listeCouleurOf: Delegating request id to service layer.");

		 ordreFabricationService.updateSuivi(request);
	}
	
	
	
	
	@RequestMapping(value = "/getByNumForEclatement", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody OrdreFabricationValue getByNumeroForEclatement(@RequestBody String numero) {
		// TODO Auto-generated method stub
		
		OrdreFabricationValue result = ordreFabricationService
				.getByNumeroAvailableForEclatement(numero);
		
		if (result != null) {

			List<SousFamilleProduitValue> listSousFamilleProduit = sousFamilleProduitService
					.listeSousFamilleProduit();
			Map<Long, String> mapSousFamilleProduit = new HashMap<Long, String>();
			for (SousFamilleProduitValue sousFamilleProduit : listSousFamilleProduit) {
				Long key = sousFamilleProduit.getId();
				if (mapSousFamilleProduit.get(key) == null) {
					mapSousFamilleProduit.put(sousFamilleProduit.getId(),
							sousFamilleProduit.getDesignation());
				}
			}

			if (result.getProduitId() != null) {

				ProduitValue produit = produitService
						.rechercheProduitById(result.getProduitId());

				if (produit != null) {
					result.setProduitDesignation(produit.getDesignation());
					result.setProduitReference(produit.getReference());
					//result.setCadence(produit.getCadence());
					if (produit.getPartieIntersseId() != null) {
						PartieInteresseValue pi = new PartieInteresseValue();
						pi.setId(produit.getPartieIntersseId());
						PartieInteresseValue data = partieInteresseeService
								.recherchePartieInteresseeParId(pi);

						if (data != null) {
							result.setPartieInterresAbreviation(data
									.getAbreviation());
						}
					}
					if (mapSousFamilleProduit.containsKey(produit
							.getSousFamilleId())) {
						result.setProduitSousFamilleDesignation(mapSousFamilleProduit
								.get(produit.getSousFamilleId()));

					}
				}
			}


		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	public boolean checkForOptimization( RechercheMulticritereOrdreFabricationValue request){
		
		return 	isNullOrEmpty(request.getvNumero()) &&
				isNullOrEmpty(request.getvCompositionBC()) &&
				isNullOrEmpty(request.getvCompositionClient()) &&
				isNullOrEmpty(request.getvEtat()) &&
				isNullOrEmpty(request.getvDateIntroductionDu()) &&
				isNullOrEmpty(request.getvDateIntroductionAu()) &&
				isNullOrEmpty(request.getClientId()) &&
				isNullOrEmpty(request.getProduitId()) &&
				isNullOrEmpty(request.getDateLivraisonDu()) &&
				isNullOrEmpty(request.getDateLivraisonTo()) ;

	}
	
	public boolean isNullOrEmpty (Object criteria){
		return criteria == null || criteria.toString().length() == 0;
	}


}
