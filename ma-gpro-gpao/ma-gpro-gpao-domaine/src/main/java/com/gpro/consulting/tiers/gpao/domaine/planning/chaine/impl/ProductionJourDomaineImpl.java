package com.gpro.consulting.tiers.gpao.domaine.planning.chaine.impl;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.DateValue;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.ProductionJourElementValue;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.RechercheMulticritereProductionJourValue;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.ResultatRechecheProductionJourValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ChaineValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;

import com.gpro.consulting.tiers.gpao.domaine.planning.chaine.IProductionJourDomaine;
import com.gpro.consulting.tiers.gpao.persitance.IChainePersistance;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.planning.chaine.IProductionJourPersistance;

/**
 * @author Hamdi Etteieb 
 *
 */
@Component
public class ProductionJourDomaineImpl implements IProductionJourDomaine{


	@Autowired
	IProductionJourPersistance productionJourPersistance;
	@Autowired
	IChainePersistance chainePersistance;
	@Autowired
	IOrdreFabricationPersistance ordreFabricationPersistance;
	

	
	

	@Override
	public String create(ProductionJourElementValue  pProductionValue) {
		
		//TODO 
//		////System.out.println(pProductionValue.getIdElementPlanning());
//		PlanningElementValue vElement=planningDomaine.getById(pProductionValue.getIdElementPlanning());
//		ChaineValue vChaine=chainePersistance.rechercheChaineParId(vElement.getIdChaine());
//		DateValue vDate=new DateValue();
//		vElement.setQuantiteProduite(vElement.getQuantiteProduite()+pProductionValue.getQuantite());
//		Long quantite=vElement.getQuantite()-vElement.getQuantiteProduite();
//		Calendar vDateDebut=Calendar.getInstance();
//		vDateDebut.setTime(pProductionValue.getDate().getTime());
//		SimpleDateFormat sdf = new SimpleDateFormat("dd");
//		int jour = Integer.parseInt(sdf.format(vDateDebut.getTime()));
//		
//		vDateDebut.set(vDateDebut.DAY_OF_MONTH,jour+ 1);
//		PlanningTravailElementValue planningTravail = planningTravailPersistance.recherchePeriode(vDateDebut);
//        vDateDebut.set(vDateDebut.HOUR_OF_DAY, (int)(planningTravail.getDebutNormal()/60));
//        vDateDebut.set(vDateDebut.MINUTE, (int)(planningTravail.getDebutNormal()%60));
//        vDate=calculDateFin(vChaine.getIdSite(),vDateDebut, quantite, vElement.getCadence());
//        ////System.out.println("#### Date Fin  :  "+vDate.getDateFin());
//        Boolean decaler =false ;
//        if(vElement.getDateFinReel()!= vDate.getDateFin()){
//        	decaler = true ;
//        }
//        	
//        
//        vElement.setDateFinReel(vDate.getDateFin());
//        vElement.setStatue(vDate.getDateFin().getTime().toString());
//        vElement.setInformationModification(vDate.getDateLibre().getTime().toString());
//
//        planningDomaine.updateFromProduction(vElement,decaler);
		
		OrdreFabricationValue of=ordreFabricationPersistance.getByNumero(pProductionValue.getoFDesignation());
		if (of!=null)
		{
//	     PlanningTravailElementValue periode=planningTravailPersistance.recherchePeriode(pProductionValue.getDate());
//	     Long minuteTravail=periode.getMinTravail();
//		  ProduitValue
			
		  pProductionValue.setOf(of.getId());
		  
		  if(pProductionValue.getPhase()!=null) {
			  Long phase=pProductionValue.getPhase();
			  
			  
			  switch (phase.intValue()) {
				case 1:
					of.setQtCoupe(of.getQtCoupe()+pProductionValue.getQuantite());
					break;
				case 2:
					of.setQtSortie(of.getQtSortie()+pProductionValue.getQuantite());
					break;
				case 3:
					of.setQtFinition(of.getQtFinition()+pProductionValue.getQuantite());
					break;
				case 4:
					of.setQtExpedition(of.getQtExpedition()+pProductionValue.getQuantite());
					break;
				
				default:
					break;
				}
		
			  
			  ordreFabricationPersistance.modifierOrdreFabrication(of);
		  }
		  
		  
		  return productionJourPersistance.create(pProductionValue);
		
		}
		
		else return "error";
	}

	@Override
	public ProductionJourElementValue  getById(Long id) {
		
		return productionJourPersistance.getById(id);
	}

	@Override
	public String update(ProductionJourElementValue  pProductionValue) {
		//Update ElementPlanning
		
		
		OrdreFabricationValue of=ordreFabricationPersistance.getByNumero(pProductionValue.getoFDesignation());
		if (of!=null)
		{
//	     PlanningTravailElementValue periode=planningTravailPersistance.recherchePeriode(pProductionValue.getDate());
//	     Long minuteTravail=periode.getMinTravail();
//		  ProduitValue
			
		  pProductionValue.setOf(of.getId());
		  
		  if(pProductionValue.getPhase()!=null && pProductionValue.getQuantite()!=null && pProductionValue.getQteBefore()!=null) {
			  Long phase=pProductionValue.getPhase();
			  
			  
			  switch (phase.intValue()) {
				case 1:
					of.setQtCoupe(of.getQtCoupe()+pProductionValue.getQuantite()-pProductionValue.getQteBefore());
					break;
				case 2:
					of.setQtSortie(of.getQtSortie()+pProductionValue.getQuantite()-pProductionValue.getQteBefore());
					break;
				case 3:
					of.setQtFinition(of.getQtFinition()+pProductionValue.getQuantite()-pProductionValue.getQteBefore());
					break;
				case 4:
					of.setQtExpedition(of.getQtExpedition()+pProductionValue.getQuantite()-pProductionValue.getQteBefore());
					break;
				
				default:
					break;
				}
		
			  
			  ordreFabricationPersistance.modifierOrdreFabrication(of);
		  }
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		return productionJourPersistance.update(pProductionValue);
	}

	@Override
	public void delete(Long id) {
		//Update ElementPlanning
		ProductionJourElementValue pProductionValue=this.getById(id);

		//System.out.println("####  pProductionValue.getoFDesignation()  :   "+pProductionValue.getoFDesignation());
		
		OrdreFabricationValue of=ordreFabricationPersistance.getByNumero(pProductionValue.getoFDesignation());
		if (of!=null)
		{
//	     PlanningTravailElementValue periode=planningTravailPersistance.recherchePeriode(pProductionValue.getDate());
//	     Long minuteTravail=periode.getMinTravail();
//		  ProduitValue
			
		  pProductionValue.setOf(of.getId());
		  
		  if(pProductionValue.getPhase()!=null) {
			  Long phase=pProductionValue.getPhase();
			  
			  
			  switch (phase.intValue()) {
				case 1:
					of.setQtCoupe(of.getQtCoupe()-pProductionValue.getQuantite());
					break;
				case 2:
					of.setQtSortie(of.getQtSortie()-pProductionValue.getQuantite());
					break;
				case 3:
					of.setQtFinition(of.getQtFinition()-pProductionValue.getQuantite());
					break;
				case 4:
					of.setQtExpedition(of.getQtExpedition()-pProductionValue.getQuantite());
					break;
				
				default:
					break;
				}
		
			  
			  ordreFabricationPersistance.modifierOrdreFabrication(of);
		  }
		
		}
		
		productionJourPersistance.delete(id);
		
	}

	@Override
	public ResultatRechecheProductionJourValue rechercherMultiCritere(
			RechercheMulticritereProductionJourValue request) {
		 
		ResultatRechecheProductionJourValue result = productionJourPersistance.rechercherMultiCritere(request);
		
		return result;

	}

	@Override
	public List<ProductionJourElementValue > listProductionJour() {

		List<ProductionJourElementValue > listePlanning = productionJourPersistance
				.listProductionJour();
		for (ProductionJourElementValue pProduction : listePlanning) {
		
		//	Long vIdChaine = planningDomaine.getById(pProduction.getIdElementPlanning()).getIdChaine();
		//	Long vRefOf = planningDomaine.getById(pProduction.getIdElementPlanning()).getIdOF() ;
			//pProduction.setChaineDesignation(chainePersistance.rechercheDesignationChaineParId(vIdChaine).toString());
		//	pProduction.setoFDesignation(ordreFabricationPersistance.getNumOfParId(vRefOf));
			pProduction.setDesignationPlanning(pProduction.getChaineDesignation()+"/"+pProduction.getoFDesignation());
			
	
		}

		return listePlanning;
	}
	
	

	

}
