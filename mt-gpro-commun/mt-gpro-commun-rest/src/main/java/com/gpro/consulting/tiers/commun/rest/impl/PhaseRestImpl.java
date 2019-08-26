package com.gpro.consulting.tiers.commun.rest.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gpro.consulting.tiers.commun.coordination.value.PhaseValue;
import com.gpro.consulting.tiers.commun.service.IPhaseService;

@Controller
@RequestMapping("/phase")
public class PhaseRestImpl {

	@Autowired
	private IPhaseService ebPhaseService;
	
	List<PhaseValue> listeType=new ArrayList<>();
	
	//conctructeur
		public PhaseRestImpl(){
			
		}
	
	/*************get Phase By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody PhaseValue getPhase(@PathVariable Long id) {
		PhaseValue pPhaseValue=new PhaseValue();
		pPhaseValue.setId(id);
		return  ebPhaseService.recherchePhaseParId(pPhaseValue);
	}

	/**********all type***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<PhaseValue> viewAllType(){
		return this.listeType= ebPhaseService.listePhase();
    }
	
	 @RequestMapping(value = "/creerPhaseProduit", method = RequestMethod.POST)
	  public @ResponseBody String creerFamilleProduit(@RequestBody PhaseValue pPhaseValue) {
	      //transformation et transcodage des referenciel 
		  return  ebPhaseService.creerPhase(pPhaseValue);
	 }

	  @RequestMapping(value = "/modifierPhaseProduit", method = RequestMethod.POST)
	  public @ResponseBody String modifierPhaseProduit(@RequestBody PhaseValue pPhaseValue) {
	    return ebPhaseService.modifierPhase(pPhaseValue);
	  }

	  @RequestMapping(value = "/supprimerPhaseProduit:{id}", method = RequestMethod.DELETE)
	  public void supprimerPhase(@PathVariable("id") String id) {
		  Long idSupp= Long.parseLong(id);
		  PhaseValue phase= new PhaseValue();
		  phase.setId(idSupp);
		  ebPhaseService.supprimerPhase(phase);

	  }
	  
	  
	/******** GETTER & SETTER********/
	public IPhaseService getEbPhaseService() {
		return ebPhaseService;
	}

	public void setEbPhaseService(IPhaseService ebPhaseService) {
		this.ebPhaseService = ebPhaseService;
	}

	public List<PhaseValue> getListeType() {
		return listeType;
	}

	public void setListeType(List<PhaseValue> listeType) {
		this.listeType = listeType;
	}
	
	
}
