package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.machine.value.MachineValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.RechercheMulticritereCatalogueValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.ResultatRechecheCatalogueValue;
import com.gpro.consulting.tiers.gpao.coordination.section.value.SectionValue;
import com.gpro.consulting.tiers.gpao.domaine.IOperationDomaine;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IMachinePersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IOperationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.ISectionPersistance;

/**
 * implementation of {@link IOperationDomaine}
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */

@Component
public class OperationDomaineImpl implements IOperationDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(OperationDomaineImpl.class);
	
	private static final Long ZERO_L = 0L;
	
	@Autowired
	private IOperationPersistance operationPersistance;
	
	@Autowired
	private IMachinePersistance machinePersistance;
	
	@Autowired
	private ISectionPersistance sectionPersistance;
	
	@Override
	public String create(OperationValue operationValue) {
		
		Long pdh = ZERO_L;
		
		if(validDoubleValue(operationValue.getTemps())){
			
			pdh = (long)(60 / operationValue.getTemps());
		}
		
		operationValue.setPdh(pdh);
		
		return operationPersistance.create(operationValue);
	}

	@Override
	public OperationValue getById(Long id) {
		
		OperationValue operation = operationPersistance.getById(id);
		
		if(operation != null){
			
			if(operation.getMachineId() != null){
				
				// Map< machineId , machineDesignation >
				List<MachineValue> listMachine = machinePersistance.getAll();
				Map<Long, String> mapMachineIdDesignation = new HashMap<Long, String>();
				
				for(MachineValue machine : listMachine){
					
					mapMachineIdDesignation.put(machine.getId(), machine.getDesignation());
				}
				
				if(mapMachineIdDesignation.containsKey(operation.getMachineId())){
					
					operation.setMachineDesignation(mapMachineIdDesignation.get(operation.getMachineId()));
				}
			}
			
			if(operation.getSectionId() != null){
				
				// Map< sectionId , sectionDesignation >
				List<SectionValue> listSection = sectionPersistance.getAll();
				Map<Long, String> mapSectionIdDesignation = new HashMap<Long, String>();
				
				for(SectionValue section : listSection){
					
					mapSectionIdDesignation.put(section.getId(), section.getDesignation());
				}
				
				if(mapSectionIdDesignation.containsKey(operation.getSectionId())){
					
					operation.setSectionDesignation(mapSectionIdDesignation.get(operation.getSectionId()));
				}
				
			}

		}
		
		return operation;
	}

	@Override
	public String update(OperationValue operationValue) {
		
		Long pdh = ZERO_L;
		
		if(validDoubleValue(operationValue.getTemps())){
			
			pdh = (long)(60 / operationValue.getTemps());
		}
		
		operationValue.setPdh(pdh);
		
		return operationPersistance.update(operationValue);
	}

	@Override
	public void delete(Long id) {
		
		operationPersistance.delete(id);
	}

	@Override
	public List<OperationValue> getAll() {
		
		List<OperationValue> listOperation = operationPersistance.getAll();
/*		
		if(listOperation != null){
			
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
			
			for(OperationValue operation : listOperation){
				
				if(mapMachineIdDesignation.containsKey(operation.getMachineId())){
					operation.setMachineDesignation(mapMachineIdDesignation.get(operation.getMachineId()));
				}
				
				if(mapSectionIdDesignation.containsKey(operation.getSectionId())){
					operation.setSectionDesignation(mapSectionIdDesignation.get(operation.getSectionId()));
				}
			}
			
		}
*/		
		return listOperation;
	}

	@Override
	public ResultatRechecheCatalogueValue rechercherMultiCritere(
			RechercheMulticritereCatalogueValue request) {
		
		ResultatRechecheCatalogueValue result = operationPersistance.rechercherMultiCritere(request);
		
		if(result != null){
			
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
			
			for(OperationValue operation : result.getList()){
				
				if(mapMachineIdDesignation.containsKey(operation.getMachineId())){
					operation.setMachineDesignation(mapMachineIdDesignation.get(operation.getMachineId()));
				}
				
				if(mapSectionIdDesignation.containsKey(operation.getSectionId())){
					operation.setSectionDesignation(mapSectionIdDesignation.get(operation.getSectionId()));
				}
			}
				
		}

		
		return result;
	}
	
	
	private boolean validDoubleValue(Double val) {
		return val != null && val != 0;
	}

	@Override
	public List<OperationValue> getAllByOF(Long OFId) {
		return operationPersistance.getAllByOF(OFId);
	}

	@Override
	public List<OperationValue> getAllByProduit(Long produitId) {
		return operationPersistance.getAllByProduit(produitId);
	}

	@Override
	public OperationValue getByCode(String operationCode) {
		// TODO Auto-generated method stub
		return operationPersistance.getByCode(operationCode);
	}

	@Override
	public List<OperationValue> getSwitchComptage(boolean comptage) {
		// TODO Auto-generated method stub
		return operationPersistance.getSwitchComptage(comptage);
	}
	
	
	
}
