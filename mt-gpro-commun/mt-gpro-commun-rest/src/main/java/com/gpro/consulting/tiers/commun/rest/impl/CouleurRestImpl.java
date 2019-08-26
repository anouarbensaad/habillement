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

import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitCouleurValue;
import com.gpro.consulting.tiers.commun.service.ICouleurService;
import com.gpro.consulting.tiers.commun.service.IProduitCouleurService;

@Controller
@RequestMapping("/couleur")
public class CouleurRestImpl {

	@Autowired
	private ICouleurService ebCouleurService;
	
	@Autowired
	private IProduitCouleurService produitCouleurService; 	
	 
	List<CouleurValue> listeType=new ArrayList<>();
	
	/*************get Couleur By ID*************/
	@RequestMapping(value = "/getId:{id}", method = RequestMethod.GET, produces =  "application/json")
	public @ResponseBody CouleurValue getCouleur(@PathVariable Long id) {
		CouleurValue pCouleurValue=new CouleurValue();
		pCouleurValue.setId(id);
		return  ebCouleurService.rechercheCouleurParId(pCouleurValue);
	}
	
	/**********all ***********/
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<CouleurValue> viewAllType(){
		return this.listeType= ebCouleurService.listeCouleur();
    }
	
	
	 @RequestMapping(value = "/creerCouleurProduit", method = RequestMethod.POST)
	  public @ResponseBody String creerCouleurProduit(@RequestBody CouleurValue pCouleurValue) {
	      //transformation et transcodage des referenciel 
		  return ebCouleurService.creerCouleur(pCouleurValue);
	  }

	  @RequestMapping(value = "/modifierCouleurProduit", method = RequestMethod.POST)
	  public @ResponseBody String modifierCouleurProduit(@RequestBody CouleurValue pCouleurValue) {
	    return this.ebCouleurService.modifierCouleur(pCouleurValue);
	  }

	  @RequestMapping(value = "/supprimerCouleurProduit:{id}", method = RequestMethod.DELETE)
	  public void supprimerCouleur(@PathVariable("id") String id) {
		  Long idSupp= Long.parseLong(id);
		  CouleurValue color= new CouleurValue();
		  color.setId(idSupp);
		  ebCouleurService.supprimerCouleur(idSupp);
	  }
	  
	  
	  @RequestMapping(value = "/CouleurParProduit:{pIdProduit}", method = RequestMethod.GET)
	  public @ResponseBody List<CouleurValue> listCouleurParProduit(@PathVariable("pIdProduit") Long pIdProduit) {
		  
		  List<CouleurValue> vRp =new ArrayList<>();
		  List<ProduitCouleurValue> vListProduitCouleurValue = this.produitCouleurService.ListeProduitCouleur(pIdProduit);
		  this.listeType= ebCouleurService.listeCouleur();
		  
		  for(CouleurValue itemCouleur: this.listeType){
			 for(ProduitCouleurValue itemProCouleur:vListProduitCouleurValue ) {
				 if(itemProCouleur.getEbCouleurId()==itemCouleur.getId())
					 vRp.add(itemCouleur);
			 }
			  
		  }
		  return vRp;
	  }	  

	  
	  
	/******** GETTER & SETTER********/
	public ICouleurService getEbCouleurService() {
		return ebCouleurService;
	}

	public void setEbCouleurService(ICouleurService ebCouleurService) {
		this.ebCouleurService = ebCouleurService;
	}

	public List<CouleurValue> getListeType() {
		return listeType;
	}

	public void setListeType(List<CouleurValue> listeType) {
		this.listeType = listeType;
	}
	
	
}
