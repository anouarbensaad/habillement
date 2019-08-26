package com.gpro.consulting.tiers.gpao.rest.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.service.IPartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.IProduitService;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.GammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.RechercheMulticritereGammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitElementValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.GammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.RechercheMulticritereGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ResultatRechecheGammeOfElementValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ResultatRechecheGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.service.IGammeOfService;
import com.gpro.consulting.tiers.gpao.service.IGammeProduitService;
import com.gpro.consulting.tiers.gpao.service.IOrdreFabricationService;

/**
 * Gamme OF Controller
 * 
 * @author Wahid Gazzah
 * @since 10/05/2016
 *
 */

@RestController
@RequestMapping("/gammeof")
public class GammeOfRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(GammeOfRestImpl.class);
	
	@Autowired
	private IGammeOfService gammeOfService;
	
	@Autowired
	private IOrdreFabricationService ordreFabricationService;

	@Autowired
	private IProduitService produitService;
	
	@Autowired
	private IPartieInteresseeService prtieInteresseeService;
	
	@Autowired
	private IGammeProduitService gammeProduitService;
	

	@RequestMapping(value = "/getOrdreFabricationListAvailable", method = RequestMethod.GET, produces = "application/json")
	public List<OrdreFabricationValue> getOrdreFabricationAvailable() {
		  
		//LOGGER.info("getOrdreFabricationAvailable: Delegating request id to service layer.");
		
		return gammeOfService.getOrdreFabricationListAvailable();
	}
	
	@RequestMapping(value = "/getOrdreFabricationListUsed", method = RequestMethod.GET, produces = "application/json")
	public List<OrdreFabricationValue> getOrdreFabricationListUsed() {
		  
		//LOGGER.info("getOrdreFabricationListUsed: Delegating request id to service layer.");
		
		return gammeOfService.getOrdreFabricationListUsed();
	}
	
	@RequestMapping(value = "/getByOrdreFabricationId:{ordreFabricationId}", method = RequestMethod.GET, produces = "application/json")
	public GammeOfValue getByOrdreFabricationId(@PathVariable Long ordreFabricationId) {
		  
		//LOGGER.info("getGammeOfByOrdreFabricationId: Delegating request produitId {} to service layer.", ordreFabricationId);
		
		GammeOfValue gammeOf = gammeOfService.getGammeOfByOrdreFabricationId(ordreFabricationId);
		
		if(gammeOf != null){
			
			if(gammeOf.getProduitId() != null){
			
				ProduitValue produit = produitService.rechercheProduitById(gammeOf.getProduitId(), true);
			
				if(produit != null){
					gammeOf.setProduitDesignation(produit.getDesignation());
					gammeOf.setProduitReference(produit.getReference());
				}
				
			}
				
			if(gammeOf.getOrdreFabricationId() != null){
				
				OrdreFabricationValue ordreFabrication = ordreFabricationService.getNumeroOF(gammeOf.getOrdreFabricationId());
				
				if(ordreFabrication != null){
					
					gammeOf.setProduitId(ordreFabrication.getProduitId());
					gammeOf.setOrdreFabricationNumero(ordreFabrication.getNumero());
					
					if(ordreFabrication.getPartieInterresId() != null){
						
						PartieInteresseValue client = prtieInteresseeService.getAbreviationClient(ordreFabrication.getPartieInterresId());
						
						if(client != null){
							
							gammeOf.setClientId(client.getId());
							gammeOf.setClientAbreviation(client.getAbreviation());
							gammeOf.setClientReference(client.getReference());
						}

					}
				}
			}
			
		}
		
		return gammeOf;
	}
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheGammeOfValue rechercherMultiCritere(@RequestBody RechercheMulticritereGammeOfValue request) {
		 

		//Check if all criterias are null so generic search
		request.setOptimized(this.checkForOptimization(request));
		
		ResultatRechecheGammeOfValue result = gammeOfService.rechercherMultiCritere(request, true);
		
		if(result != null){
			
			for(ResultatRechecheGammeOfElementValue element : result.getList()){
				
				if(element.getOrdreFabricationId() != null){
					
					OrdreFabricationValue ordreFabrication = ordreFabricationService.rechercheOrdreFabricationParId(element.getOrdreFabricationId());
					
					if(ordreFabrication != null){
						
						element.setOrdreFabricationNumero(ordreFabrication.getNumero());
						
						if(ordreFabrication.getProduitId() != null){
							
							ProduitValue produit = produitService.rechercheProduitById(ordreFabrication.getProduitId());
							
							if(produit != null){
								
								element.setProduitDesignation(produit.getDesignation());
								element.setProduitReference(produit.getReference());
							}

						}
						
						if(ordreFabrication.getPartieInterresId() != null){
							
							PartieInteresseValue client = prtieInteresseeService.getById(ordreFabrication.getPartieInterresId());
							
							if(client != null){
								
								element.setClientAbreviation(client.getAbreviation());
								element.setClientReference(client.getReference());
							}

						}
					}
				}
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody GammeOfValue gammeOfValue) {
		
		//LOGGER.info("createGammeOf: Delegating request to Service layer.");
		
		return gammeOfService.create(gammeOfValue);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public GammeOfValue getById(@PathVariable Long id) {
		  
		//LOGGER.info("getGammeOfById: Delegating request id {} to service layer.", id);
		
		GammeOfValue gammeOf = gammeOfService.getById(id);
		
		if(gammeOf != null){
			
			if(gammeOf.getOrdreFabricationId() != null){
				
				OrdreFabricationValue ordreFabrication = ordreFabricationService.rechercheOrdreFabricationParId(gammeOf.getOrdreFabricationId());
				
				if(ordreFabrication != null){
					
					gammeOf.setProduitId(ordreFabrication.getProduitId());
					gammeOf.setOrdreFabricationNumero(ordreFabrication.getNumero());
					
					if(ordreFabrication.getProduitId() != null){
						
						ProduitValue produit = produitService.rechercheProduitById(ordreFabrication.getProduitId());
						
						if(produit != null){
							
							gammeOf.setProduitDesignation(produit.getDesignation());
							gammeOf.setProduitReference(produit.getReference());
						}

					}
					
					if(ordreFabrication.getPartieInterresId() != null){
						
						PartieInteresseValue client = prtieInteresseeService.getById(ordreFabrication.getPartieInterresId());
						
						if(client != null){
							
							gammeOf.setClientId(client.getId());
							gammeOf.setClientAbreviation(client.getAbreviation());
							gammeOf.setClientReference(client.getReference());
						}

					}
					
					RechercheMulticritereGammeProduitValue request = new RechercheMulticritereGammeProduitValue();
					request.setProduitId(ordreFabrication.getProduitId());
					
					ResultatRechecheGammeProduitValue resultatRechecheGammeProduit = gammeProduitService.rechercherMultiCritere(request);
					 
					 if(resultatRechecheGammeProduit != null){
						 
						 Set<ResultatRechecheGammeProduitElementValue> list = resultatRechecheGammeProduit.getList();
						 
						 if(!list.isEmpty()){
							 
							 ResultatRechecheGammeProduitElementValue firstElement = list.iterator().next();
							 
							 if(firstElement!=null){
								 
								 GammeProduitValue gammeProduit = gammeProduitService.getById(firstElement.getId());
								 
								 if(gammeProduit != null){
									 
									 gammeOf.setTempsTotalProduit(gammeProduit.getTempsTotal());
									 gammeOf.setNbOperationProduit(gammeProduit.getNbOperation());
									 
								 }
							 }
						 }
					 }
				}
			}
		}
		
		return gammeOf;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody GammeOfValue gammeOfValue) {
	    
		//LOGGER.info("UpdateGammeOf: Delegating request to service layer.");
		
		return gammeOfService.update(gammeOfValue);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//LOGGER.info("deleteGammeOf: Delegating request id {} to service layer.", id);
		  
		gammeOfService.delete(id);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<GammeOfValue> getAll() {
		  
		//LOGGER.info("getAllGammeOf: Delegating request id to service layer.");
		
		return gammeOfService.getAll();
	}

	@RequestMapping(value = "/validateByOrdreFabricationId:{ordreFabricationId}", method = RequestMethod.GET, produces = "application/json")
	public GammeOfValue validateByOrdreFabricationId(@PathVariable Long ordreFabricationId) {
		  
		//LOGGER.info("validateByOrdreFabricationId: Delegating request produitId {} to service layer.", ordreFabricationId);
		
		GammeOfValue gammeOf = gammeOfService.validateByOrdreFabricationId(ordreFabricationId);
		
		if(gammeOf != null){
			
			if(gammeOf.getProduitId() != null){
			
				ProduitValue produit = produitService.rechercheProduitById(gammeOf.getProduitId());
			
				if(produit != null){
					gammeOf.setProduitDesignation(produit.getDesignation());
					gammeOf.setProduitReference(produit.getReference());
				}
				
			}
				
			if(gammeOf.getOrdreFabricationId() != null){
				
				OrdreFabricationValue ordreFabrication = ordreFabricationService.rechercheOrdreFabricationParId(gammeOf.getOrdreFabricationId());
				
				if(ordreFabrication != null){
					
					gammeOf.setProduitId(ordreFabrication.getProduitId());
					gammeOf.setOrdreFabricationNumero(ordreFabrication.getNumero());
					
					if(ordreFabrication.getPartieInterresId() != null){
						
						PartieInteresseValue client = prtieInteresseeService.getById(ordreFabrication.getPartieInterresId());
						
						if(client != null){
							
							gammeOf.setClientId(client.getId());
							gammeOf.setClientAbreviation(client.getAbreviation());
							gammeOf.setClientReference(client.getReference());
						}

					}
				}
			}
			
		}
		
		return gammeOf;
	}
	
	public boolean checkForOptimization( RechercheMulticritereGammeOfValue request){
		
		return 	isNullOrEmpty(request.getProduitId()) &&
				isNullOrEmpty(request.getMachineId()) &&
				isNullOrEmpty(request.getSectionId()) &&
				isNullOrEmpty(request.getTempsTotalMax()) &&
				isNullOrEmpty(request.getOrdreFabricationId()) &&
				isNullOrEmpty(request.getTempsTotalMin());
		
	}
	
	public boolean isNullOrEmpty (Object criteria){
		return criteria == null || criteria.toString().length() == 0;
	}

}
