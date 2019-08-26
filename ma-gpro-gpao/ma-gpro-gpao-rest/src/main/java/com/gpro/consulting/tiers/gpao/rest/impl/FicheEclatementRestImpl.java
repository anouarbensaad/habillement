package com.gpro.consulting.tiers.gpao.rest.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.service.IPartieInteresseeService;
import com.gpro.consulting.tiers.commun.service.IProduitService;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.FicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.PaquetValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.RechercheMulticritereFicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.ResultatRechecheFicheEclatementElementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.ResultatRechecheFicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.service.IFicheEclatementService;
import com.gpro.consulting.tiers.gpao.service.IOrdreFabricationService;

/**
 * Fiche Eclatement Controller
 * 
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */

@RestController
@RequestMapping("/ficheEclatement")
public class FicheEclatementRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(FicheEclatementRestImpl.class);
	
	@Autowired
	private IFicheEclatementService ficheEclatementService;
	
	@Autowired
	private IOrdreFabricationService ordreFabricationService;
	
	@Autowired
	private IProduitService produitService;
	
	@Autowired
	private IPartieInteresseeService prtieInteresseeService;
	
	@RequestMapping(value = "/getPaquetListByOfIdAndQuantitePaquet", method = RequestMethod.GET, produces = "application/json")
	public Set<PaquetValue> getPaquetListByOfIdAndQuantitePaquet(
			@RequestParam(value="ordreFabricationId") Long ordreFabricationId,
			@RequestParam(value="quantitePaquet") Long quantitePaquet) {
		
		//LOGGER.info("getPaquetListByOfIdAndQuantitePaquet: Delegating request to service layer.");
		
		return ficheEclatementService.getPaquetListByOfIdAndQuantitePaquet(ordreFabricationId, quantitePaquet);
	}

	
	@RequestMapping(value = "/getPaquetListByOfNumAndQuantitePaquet", method = RequestMethod.GET, produces = "application/json")
	public Set<PaquetValue> getPaquetListByOfNumAndQuantitePaquet(
			@RequestParam(value="ordreFabricationId") String ordreFabricationId,
			@RequestParam(value="quantitePaquet") Long quantitePaquet) {
		
        Long of=null;  
		
		if (ordreFabricationId!=null ){
			  OrdreFabricationValue ordre=ordreFabricationService.getByNumero(ordreFabricationId);
			  
			  if (ordre!=null)
				  of=ordre.getId();
			  
		  }
		
		//LOGGER.info("getPaquetListByOfIdAndQuantitePaquet: Delegating request to service layer.");
		
		return ficheEclatementService.getPaquetListByOfIdAndQuantitePaquet(of, quantitePaquet);
	}
	
	
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheFicheEclatementValue rechercherMultiCritere(@RequestBody RechercheMulticritereFicheEclatementValue request) {

		//Check if all criterias are null so generic search
				request.setOptimized(this.checkForOptimization(request));
				
		ResultatRechecheFicheEclatementValue result = ficheEclatementService.rechercherMultiCritere(request);
		
		if(result != null){
			
			for(ResultatRechecheFicheEclatementElementValue element : result.getList()){
				
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
	public String create(@RequestBody FicheEclatementValue ficheEclatementValue) {
		
		//LOGGER.info("Delegating request to Service layer.");
		
		return ficheEclatementService.create(ficheEclatementValue);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public FicheEclatementValue getById(@PathVariable Long id) {
		  
		//LOGGER.info(" Delegating request id {} to service layer.", id);
		
		FicheEclatementValue ficheEclatement = ficheEclatementService.getById(id);
		
		if(ficheEclatement != null){
			
			if(ficheEclatement.getOrdreFabricationId() != null){
				
				OrdreFabricationValue ordreFabrication = ordreFabricationService.rechercheOrdreFabricationParId(ficheEclatement.getOrdreFabricationId());
				
				if(ordreFabrication != null){
					
					ficheEclatement.setProduitId(ordreFabrication.getProduitId());
					ficheEclatement.setOrdreFabricationNumero(ordreFabrication.getNumero());
					
					if(ordreFabrication.getProduitId() != null){
						
						ProduitValue produit = produitService.rechercheProduitById(ordreFabrication.getProduitId());
						
						if(produit != null){
							
							ficheEclatement.setProduitDesignation(produit.getDesignation());
							ficheEclatement.setProduitReference(produit.getReference());
						}

					}
					
					if(ordreFabrication.getPartieInterresId() != null){
						
						PartieInteresseValue client = prtieInteresseeService.getById(ordreFabrication.getPartieInterresId());
						
						if(client != null){
							
							ficheEclatement.setClientId(client.getId());
							ficheEclatement.setClientAbreviation(client.getAbreviation());
							ficheEclatement.setClientReference(client.getReference());
						}

					}
					
					
				}
			}
		}
		
		return ficheEclatement;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody FicheEclatementValue ficheEclatementValue) {
	    
		//LOGGER.info("Delegating request to service layer.");
		
		return ficheEclatementService.update(ficheEclatementValue);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//LOGGER.info("deleteOperation: Delegating request id {} to service layer.", id);
		  
		ficheEclatementService.delete(id);
	}
	
	
	public boolean checkForOptimization( RechercheMulticritereFicheEclatementValue request){
		
		return 	isNullOrEmpty(request.getOrdreFabricationId()) &&
				isNullOrEmpty(request.getProduitId()) ;

	}
	
	public boolean isNullOrEmpty (Object criteria){
		return criteria == null || criteria.toString().length() == 0;
	}
	
	@RequestMapping(value = "/updateAll", method = RequestMethod.GET)
	public String updateAll() {
		
		//LOGGER.info("Delegating request to Service layer.");
		
		return ficheEclatementService.updateAll();
	}
	
	
}
