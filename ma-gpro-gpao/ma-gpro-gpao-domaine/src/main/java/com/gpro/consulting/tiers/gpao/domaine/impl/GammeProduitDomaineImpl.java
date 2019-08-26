package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ElementGammeValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.GammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.RechercheMulticritereGammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitElementValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.machine.value.MachineValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.section.value.SectionValue;
import com.gpro.consulting.tiers.gpao.domaine.IGammeProduitDomaine;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IGammeProduitPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IMachinePersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IOperationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.ISectionPersistance;

/**
 * implementation of {@link IGammeProduitDomaine}
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */

@Component
public class GammeProduitDomaineImpl implements IGammeProduitDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(GammeProduitDomaineImpl.class);
	
	private static final Double ZERO_D = 0D;
	private static final Long ZERO_L = 0L;
	
	@Autowired
	private IGammeProduitPersistance gammeProduitPersistance;
	
	@Autowired
	private IMachinePersistance machinePersistance;
	
	@Autowired
	private ISectionPersistance sectionPersistance;
	
	@Autowired
	private IOperationPersistance operationPersistance;
	
	@Autowired
	private IProduitPersistance produitPersistance;
	
	@Override
	public String create(GammeProduitValue gammeProduit) {
		
		if(gammeProduit != null){
			
			Double tempsTotal = ZERO_D;
			Long nbOperation = ZERO_L;
			
			if(gammeProduit.getListElementGamme() != null){
				
				nbOperation = Long.valueOf(gammeProduit.getListElementGamme().size());
			}
			
			for(ElementGammeValue elementGamme : gammeProduit.getListElementGamme()){
				
				if (elementGamme.getOperationId() == null){
					
					OperationValue operation = new OperationValue();
					
					operation.setCode(elementGamme.getOperationCode());
					operation.setDesignation(elementGamme.getOperationDesignation());
					operation.setTemps(elementGamme.getTemps());
					operation.setObservations(elementGamme.getObservations());
					operation.setMachineId(elementGamme.getMachineId());
					operation.setSectionId(elementGamme.getSectionId());
					
					if(validDoubleValue(elementGamme.getTemps())){
						
						Long pdh = (long)(60 / elementGamme.getTemps());
						operation.setPdh(pdh);
					}
					
					String id = operationPersistance.create(operation);
					
					if(estNonVide(id)){
						
						elementGamme.setOperationId(Long.valueOf(id).longValue());
					}
					
				}
				
				Double temps = (elementGamme.getTemps() != null) ? elementGamme.getTemps() : ZERO_D;
				tempsTotal = tempsTotal + temps;
				
				Long pdh = ZERO_L;
				
				if(validDoubleValue(elementGamme.getTemps())){
					
					pdh = (long)(60 / elementGamme.getTemps());
				}
				
				elementGamme.setPdh(pdh);
			}
			
			gammeProduit.setTempsTotal(tempsTotal);
			gammeProduit.setNbOperation(nbOperation);
			
		}
		
		return gammeProduitPersistance.create(gammeProduit);
	}
	

	@Override
	public String update(GammeProduitValue gammeProduit) {
		
		if(gammeProduit != null){
			
			Double tempsTotal = ZERO_D;
			Long nbOperation = ZERO_L;
			
			if(gammeProduit.getListElementGamme() != null){
				
				nbOperation = Long.valueOf(gammeProduit.getListElementGamme().size());
			}
			
			for(ElementGammeValue elementGamme : gammeProduit.getListElementGamme()){
				
				if (elementGamme.getOperationId() == null){
					
					OperationValue operation = new OperationValue();
					
					operation.setCode(elementGamme.getOperationCode());
					operation.setDesignation(elementGamme.getOperationDesignation());
					operation.setTemps(elementGamme.getTemps());
					operation.setObservations(elementGamme.getObservations());
					operation.setMachineId(elementGamme.getMachineId());
					operation.setSectionId(elementGamme.getSectionId());
					
					if(validDoubleValue(elementGamme.getTemps())){
						
						Long pdh = (long)(60 / elementGamme.getTemps());
						operation.setPdh(pdh);
					}
					
					String id = operationPersistance.create(operation);
					
					if(estNonVide(id)){
						
						elementGamme.setOperationId(Long.valueOf(id).longValue());
					}
					
				}
				
				Double temps = (elementGamme.getTemps() != null) ? elementGamme.getTemps() : ZERO_D;
				tempsTotal = tempsTotal + temps;
			
				Long pdh = ZERO_L;
				
				if(validDoubleValue(elementGamme.getTemps())){
					
					pdh = (long)(60 / elementGamme.getTemps());
				}
				
				elementGamme.setPdh(pdh);

			}
			
			gammeProduit.setTempsTotal(tempsTotal);
			gammeProduit.setNbOperation(nbOperation);
			
		}
		
		return gammeProduitPersistance.update(gammeProduit);
	}

	@Override
	public GammeProduitValue getById(Long id) {
		
		GammeProduitValue gammeProduit = gammeProduitPersistance.getById(id);
		
		if(gammeProduit != null){
			
			// Map< machineId , machineDesignation >
			List<MachineValue> listMachine = machinePersistance.getAll();
			Map<Long, String> mapMachineIdDesignation = new HashMap<Long, String>();
			for(MachineValue machine : listMachine){
				mapMachineIdDesignation.put(machine.getId(), machine.getDesignation());
			}
			
			// Map< sectionId , sectionDesignation >
			List<SectionValue> listSection = sectionPersistance.getAll();
			Map<Long, String> mapSectionIdDesignation = new HashMap<Long, String>();
			for(SectionValue section : listSection){
				mapSectionIdDesignation.put(section.getId(), section.getDesignation());
			}
			
			// Map< operationId , operationDesignation >
			Map<Long, String> mapOperationIdDesignation = new HashMap<Long, String>();
			
			// Map< operationId , operationCode >
			Map<Long, String> mapOperationIdCode = new HashMap<Long, String>();
			
			List<OperationValue> listOperation = operationPersistance.getAll();
			
			for(OperationValue operation : listOperation){
				mapOperationIdDesignation.put(operation.getId(), operation.getDesignation());
			}
			
			for(OperationValue operation : listOperation){
				mapOperationIdCode.put(operation.getId(), operation.getCode());
			}
			
			List<ElementGammeValue> listElementGamme = new ArrayList<ElementGammeValue>();
			
			for(ElementGammeValue element : gammeProduit.getListElementGamme()){
				
				if(element.getMachineId() != null){
					
					if(mapMachineIdDesignation.containsKey(element.getMachineId())){
						
						element.setMachineDesignation(mapMachineIdDesignation.get(element.getMachineId()));
					}
				}
				
				if(element.getSectionId() != null){

					if(mapSectionIdDesignation.containsKey(element.getSectionId())){
						
						element.setSectionDesignation(mapSectionIdDesignation.get(element.getSectionId()));
					}
				}
				
				if(element.getOperationId() != null){

					if(mapOperationIdDesignation.containsKey(element.getOperationId())){
						
						element.setOperationDesignation(mapOperationIdDesignation.get(element.getOperationId()));
						element.setOperationCode(mapOperationIdCode.get(element.getOperationId()));
					}
				}
				
				listElementGamme.add(element);
			}
			
			Collections.sort(listElementGamme);
			
			gammeProduit.setListElementGamme(new TreeSet<>(listElementGamme));
		}
		
		return gammeProduit;
	}

	@Override
	public void delete(Long id) {
		
		gammeProduitPersistance.delete(id);
	}

	@Override
	public List<GammeProduitValue> getAll() {
		
		return gammeProduitPersistance.getAll();
	}

	@Override
	public ResultatRechecheGammeProduitValue rechercherMultiCritere(
			RechercheMulticritereGammeProduitValue request) {
		
		return gammeProduitPersistance.rechercherMultiCritere(request);
	}

	@Override
	public List<ProduitValue> getProduitListAvailable() {
		
		List<ProduitValue> result = null;

		List<GammeProduitValue> currentListGammeProduit = gammeProduitPersistance.getAll();
		
		if(currentListGammeProduit != null){
			
			List<Long> listProduitIdUsed = new ArrayList<Long>();
			
			for(GammeProduitValue gammeProduit : currentListGammeProduit){
				
				listProduitIdUsed.add(gammeProduit.getProduitId());
			}
			
			if(listProduitIdUsed.size() > 0){
				
				result = produitPersistance.getAllNotInList(listProduitIdUsed);
			}
			
			else{
				
				result = produitPersistance.listeProduit();
			}
			
		}
		
		return result;
	}

	@Override
	public List<ProduitValue> getProduitListUsed() {
		
		List<ProduitValue> result = null;

		List<GammeProduitValue> currentListGammeProduit = gammeProduitPersistance.getAll();
		
		if(currentListGammeProduit != null){
			
			List<Long> listProduitIdUsed = new ArrayList<Long>();
			
			for(GammeProduitValue gammeProduit : currentListGammeProduit){
				
				listProduitIdUsed.add(gammeProduit.getProduitId());
			}
			
			result = produitPersistance.getAllInList(listProduitIdUsed);
		}
		
		return result;
	}
	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);

	}


	@Override
	public GammeProduitValue getByProduitId(Long produitId) {
		
		GammeProduitValue gammeProduit = null;
		
		RechercheMulticritereGammeProduitValue request = new RechercheMulticritereGammeProduitValue();
		request.setProduitId(produitId);
		
		ResultatRechecheGammeProduitValue result = gammeProduitPersistance.rechercherMultiCritere(request);
		
		if(result != null){
			
			Set<ResultatRechecheGammeProduitElementValue> list = result.getList();
			
			if(!list.isEmpty()){
				
				ResultatRechecheGammeProduitElementValue firstElement = list.iterator().next();
				
				if(firstElement!=null){
					
					gammeProduit = gammeProduitPersistance.getById(firstElement.getId());
					
					// Map< machineId , machineDesignation >
					List<MachineValue> listMachine = machinePersistance.getAll();
					Map<Long, String> mapMachineIdDesignation = new HashMap<Long, String>();
					for(MachineValue machine : listMachine){
						mapMachineIdDesignation.put(machine.getId(), machine.getDesignation());
					}
					
					// Map< sectionId , sectionDesignation >
					List<SectionValue> listSection = sectionPersistance.getAll();
					Map<Long, String> mapSectionIdDesignation = new HashMap<Long, String>();
					for(SectionValue section : listSection){
						mapSectionIdDesignation.put(section.getId(), section.getDesignation());
					}
					
					// Map< operationId , operationDesignation >
					Map<Long, String> mapOperationIdDesignation = new HashMap<Long, String>();
					
					// Map< operationId , operationCode >
					Map<Long, String> mapOperationIdCode = new HashMap<Long, String>();
					
					List<OperationValue> listOperation = operationPersistance.getAll();
					
					for(OperationValue operation : listOperation){
						mapOperationIdDesignation.put(operation.getId(), operation.getDesignation());
					}
					
					for(OperationValue operation : listOperation){
						mapOperationIdCode.put(operation.getId(), operation.getCode());
					}
					
					List<ElementGammeValue> listElementGamme = new ArrayList<ElementGammeValue>();
					
					for(ElementGammeValue element : gammeProduit.getListElementGamme()){
						
						if(element.getMachineId() != null){
							
							if(mapMachineIdDesignation.containsKey(element.getMachineId())){
								
								element.setMachineDesignation(mapMachineIdDesignation.get(element.getMachineId()));
							}
						}
						
						if(element.getSectionId() != null){

							if(mapSectionIdDesignation.containsKey(element.getSectionId())){
								
								element.setSectionDesignation(mapSectionIdDesignation.get(element.getSectionId()));
							}
						}
						
						if(element.getOperationId() != null){

							if(mapOperationIdDesignation.containsKey(element.getOperationId())){
								
								element.setOperationDesignation(mapOperationIdDesignation.get(element.getOperationId()));
								element.setOperationCode(mapOperationIdCode.get(element.getOperationId()));
							}
						}
						
						listElementGamme.add(element);
					}
					
					Collections.sort(listElementGamme);
					
					gammeProduit.setListElementGamme(new TreeSet<>(listElementGamme));
				}
				
			}
		}
		
		return gammeProduit;
	}

	@Override
	public ResultatRechecheGammeProduitValue rechercherMultiCritere(
			RechercheMulticritereGammeProduitValue request, boolean allegee){
		return gammeProduitPersistance.rechercherMultiCritere(request, allegee);
	}
	private boolean validDoubleValue(Double val) {
		return val != null && val != 0;
	}
}
