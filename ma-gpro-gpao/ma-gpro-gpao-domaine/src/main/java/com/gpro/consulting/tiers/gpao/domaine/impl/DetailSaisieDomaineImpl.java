package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.DetailSaisieElementValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.RechercheMulticritereDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.ResultatRechecheDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.FeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ElementGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.GammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ChaineValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.domaine.IDetailSaisieDomaine;
import com.gpro.consulting.tiers.gpao.persitance.IChainePersistance;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IDetailSaisiePersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IFeuilleSaisiePersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IPersonnelPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IOperationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.IElementGammeOfPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.IGammeOfPersistance;

/**
 * @author Ameni Berrich
 *
 */
@Component
public class DetailSaisieDomaineImpl implements IDetailSaisieDomaine{

	private static final Logger logger = LoggerFactory.getLogger(DetailSaisieDomaineImpl.class);

	@Autowired
	private IDetailSaisiePersistance detailSaisiePersistance;
	
	@Autowired
	private IGammeOfPersistance gammeOfPersistance;
	
	@Autowired
	private IOrdreFabricationPersistance ordreFabricationPersistance;
	
	@Autowired
	private IFeuilleSaisiePersistance feuilleSaisiePersistance;
	
	@Autowired
	private IOperationPersistance operationPersistance;
	
	@Autowired
	IPersonnelPersistance personnelPersistance;
	
	@Autowired
	IChainePersistance chainePersistance;
	
	@Autowired
	private IElementGammeOfPersistance elementGammeOFPersistance;

	@Override
	public ResultatRechecheDetailSaisieValue rechercherMultiCritere(
			RechercheMulticritereDetailSaisieValue request) {


		
		ResultatRechecheDetailSaisieValue detailsaisieList = detailSaisiePersistance.rechercherMultiCritere(request);
		
		if(detailsaisieList.getList() != null){
			for(DetailSaisieElementValue detailElementSaisie : detailsaisieList.getList()){
				
				//FeuilleSaisieValue feuilleSaisie = feuilleSaisiePersistance.getById(detailElementSaisie.getFicheSaisieId());
				/** OfNumero **/

				/** Chaine **/
				if(detailElementSaisie.getIdChaine() != null){
					ChaineValue chaine = chainePersistance.rechercheChaineParId(detailElementSaisie.getIdChaine());
					if(chaine != null){
						detailElementSaisie.setChaine(chaine.getDesignation());
					}
				}
                
				
				
				if (detailElementSaisie.getIdOPeration()!=null){
					
					OperationValue  operation=operationPersistance.getById(detailElementSaisie.getIdOPeration());
					
					  if (operation!=null)
						  detailElementSaisie.setOperation(operation.getDesignation());
					
				}
				
				if (detailElementSaisie.getElementGammeOFId()!=null){
					
					
					ElementGammeOfValue elementGamme=gammeOfPersistance.getElementGammeOFById(detailElementSaisie.getElementGammeOFId());
					
					if (elementGamme!=null){
						detailElementSaisie.setTemps(elementGamme.getTemps());
						
						if (detailElementSaisie.getTemps()!=null && detailElementSaisie.getQuantite()!=null)
						{
							Double minutage=detailElementSaisie.getTemps()*detailElementSaisie.getQuantite();
							detailElementSaisie.setMinutage(minutage);
						
					    }
					}
					
				}
				
				
				
				
			}
		}
		
		return detailsaisieList;
	}

	@Override
	public Set<DetailSaisieElementValue> getListDetailsSaisieElement(RechercheMulticritereDetailSaisieValue request) {
		
		ResultatRechecheDetailSaisieValue detailsaisieList = detailSaisiePersistance.rechercherMultiCritere(request);
		
		return detailsaisieList.getList();
	}

	@Override
	public Long getSommeQteProduite(Long operationId, Calendar dateMin, Calendar dateMax) {
		
		Long qteProduite =0L;
		
		List<ElementGammeOfValue> listeElementsGammeOf = elementGammeOFPersistance.getListByGammeOperationId(operationId);
		
		if(listeElementsGammeOf!=null && listeElementsGammeOf.size()>0){
			
		
			
			
			qteProduite += detailSaisiePersistance.getSommeQteProduite(listeElementsGammeOf.get(0).getId(), dateMin, dateMax);
		
		
		
		
		}
		
		return qteProduite;
	}

	@Override
	public Map<Date, Long> productionParOperation(Calendar dateDebut, Calendar dateFin,  Long operationId, Long chaineId) {
		
		RechercheMulticritereDetailSaisieValue request = new RechercheMulticritereDetailSaisieValue();
		request.setDateSaisieMin(dateDebut);
		request.setDateSaisieMax(dateFin);
		request.setOperation(operationId);
		request.setChaineId(chaineId);
		
		ResultatRechecheDetailSaisieValue detailsaisieList = detailSaisiePersistance.rechercherMultiCritere(request);
		
		Map<Date, Long> mapProductionOfOperation = new HashMap<Date, Long>();

		for (DetailSaisieElementValue detailSaisie : detailsaisieList.getList()) {
			
			Calendar dateSaisie = this.feuilleSaisiePersistance.getDateSaisie(detailSaisie.getFicheSaisieId());
			
			if(dateSaisie != null){
				
				Date dateKey = dateSaisie.getTime();
				
				if (mapProductionOfOperation.get(dateKey) == null) {
					mapProductionOfOperation.put(dateKey, (long) -1);

				}
				
				Long sommeQteProduite = mapProductionOfOperation.get(dateKey);
				
				if(sommeQteProduite > 0){
					
					mapProductionOfOperation.remove(dateKey);
					if(detailSaisie.getQuantite() != null){
						mapProductionOfOperation.put(dateKey, sommeQteProduite + detailSaisie.getQuantite());
					}
					
				}else{
					mapProductionOfOperation.remove(dateKey);
					if(detailSaisie.getQuantite() != null){
						mapProductionOfOperation.put(dateKey, detailSaisie.getQuantite());
					}
				}
			}
				
				
		}
		
		Map<Date, Long> sortedList = new TreeMap<Date, Long>(mapProductionOfOperation); 


		return sortedList;
	}

	@Override
	public Map<Long, Long> recapProductionParChaine(Calendar dateDebut, Calendar dateFin, Long operationId) {
		
		RechercheMulticritereDetailSaisieValue request = new RechercheMulticritereDetailSaisieValue();
		
		request.setDateSaisieMin(dateDebut);
		request.setDateSaisieMax(dateFin);
		request.setOperation(operationId);
		
		ResultatRechecheDetailSaisieValue detailsaisieList = detailSaisiePersistance.rechercherMultiCritere(request);
		
		Map<Long, Long> mapRecapProductionParChaine = new HashMap<Long, Long>();

		for (DetailSaisieElementValue detailSaisie : detailsaisieList.getList()) {
			
			Long chaineKey = this.feuilleSaisiePersistance.getChaineId(detailSaisie.getFicheSaisieId());
			
			if(chaineKey != null){
				
				if (mapRecapProductionParChaine.get(chaineKey) == null) {
					mapRecapProductionParChaine.put(chaineKey, (long) -1);

				}
				
				Long sommeQteProduite = mapRecapProductionParChaine.get(chaineKey);
				
				if(sommeQteProduite > 0){
					
					mapRecapProductionParChaine.remove(chaineKey);
					if(detailSaisie.getQuantite() != null){
						mapRecapProductionParChaine.put(chaineKey, sommeQteProduite + detailSaisie.getQuantite());
					}
					
				}else{
					mapRecapProductionParChaine.remove(chaineKey);
					if(detailSaisie.getQuantite() != null && detailSaisie.getQuantite() != 0){
						mapRecapProductionParChaine.put(chaineKey, detailSaisie.getQuantite());
					}
				}
			}
				
				
		}
		
		Map<Long, Long> sortedList = new TreeMap(Collections.reverseOrder());
		sortedList.putAll(mapRecapProductionParChaine);
		
		//Map<Long, Long> sortedList = new TreeMap<Long, Long>(mapRecapProductionParChaine); 


		return sortedList;
	}
	
	
	@Override
	public Long getSommeQteProduiteOF(Long ofId ,Long operationId, Calendar dateMin, Calendar dateMax) {
		
		return detailSaisiePersistance.getSommeQteProduiteForOperationOF(ofId, operationId, dateMin, dateMax);
	}
	
	
	@Override
	public Long getSommeQteProduiteProduit(Long produitId ,Long operationId, Calendar dateMin, Calendar dateMax) {
		
	
		
		return detailSaisiePersistance.getSommeQteProduiteForOpratationProduit(produitId, operationId, dateMin, dateMax);
	}
	

}
