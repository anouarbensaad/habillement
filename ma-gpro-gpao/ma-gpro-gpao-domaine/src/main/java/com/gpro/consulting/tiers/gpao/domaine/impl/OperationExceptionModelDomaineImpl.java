package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.erp.socle.j2ee.mt.socle.exception.FonctionnelleException;
import com.erp.socle.j2ee.mt.socle.exception.TechniqueException;
import com.gpro.consulting.tiers.commun.coordination.response.value.CreateResponseValue;
import com.gpro.consulting.tiers.commun.coordination.response.value.DeleteResponseValue;
import com.gpro.consulting.tiers.commun.coordination.response.value.UpdateResponseValue;
import com.gpro.consulting.tiers.gpao.coordination.machine.value.MachineValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.ListOperationResponseValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationResponseValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.RechercheMulticritereCatalogueValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.ResultatRechecheCatalogueValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.ResultatRechecheOperationValue;
import com.gpro.consulting.tiers.gpao.coordination.section.value.SectionValue;
import com.gpro.consulting.tiers.gpao.domaine.IOperationExceptionModelDomaine;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IMachinePersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IOperationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.ISectionPersistance;

/**
 * implementation of {@link IOperationExceptionModelDomaine}
 * 
 * @author Wahid Gazzah
 * @since 08/08/2016
 *
 */

@Component
public class OperationExceptionModelDomaineImpl implements IOperationExceptionModelDomaine{

	
	private static final Logger logger = LoggerFactory.getLogger(OperationDomaineImpl.class);
	
	private static final Long ZERO_L = 0L;
	
	private static final String CODE = "code";
	
	@Autowired
	private IOperationPersistance operationPersistance;
	
	@Autowired
	private IMachinePersistance machinePersistance;
	
	@Autowired
	private ISectionPersistance sectionPersistance;
	
	@Override
	public CreateResponseValue create(OperationValue operationValue) 
			throws FonctionnelleException, TechniqueException{
		
		if(operationValue.getCode() != null){
			
			OperationValue operationByCode = operationPersistance.getByCode(operationValue.getCode());
			
			if(operationByCode != null){
				
				throw new FonctionnelleException("RGRF_CTRLEN_002", new String[0]);
			}
		}
		else{
			
			throw new FonctionnelleException("RGRF_CTRLEN_004", new String[]{CODE});
		}
		

		Long pdh = ZERO_L;
		
		if(validDoubleValue(operationValue.getTemps())){
			
			pdh = (long)(60 / operationValue.getTemps());
		}
		
		operationValue.setPdh(pdh);
		
		Long id = operationPersistance.createOperation(operationValue);
		
		CreateResponseValue response = new CreateResponseValue();
		response.setId(id);
		
		return response;
	}

	@Override
	public UpdateResponseValue update(OperationValue operationValue) 
			throws FonctionnelleException, TechniqueException{
		
		if(operationValue.getCode() != null){
			
			OperationValue operationByCode = operationPersistance.getByCode(operationValue.getCode());
			
			if(operationByCode != null){
				
				throw new FonctionnelleException("RGRF_CTRLEN_002", new String[0]);
			}
		}
		else{
			
			throw new FonctionnelleException("RGRF_CTRLEN_004", new String[]{CODE});
		}
		
		Long pdh = ZERO_L;
		
		if(validDoubleValue(operationValue.getTemps())){
			
			pdh = (long)(60 / operationValue.getTemps());
		}
		
		operationValue.setPdh(pdh);
		
		Long id = operationPersistance.updateOperation(operationValue);
		
		UpdateResponseValue response = new UpdateResponseValue();
		response.setId(id);
		
		return response;
	}
	

	@Override
	public DeleteResponseValue delete(Long id) 
			throws FonctionnelleException, TechniqueException{
		
		
		// contrainte de suppression non autorisé
		// exmp : id > 100
		
		if(id != null && id > 100){
			
			throw new FonctionnelleException("RGRF_CTRLEN_003", new String[0]);
		}
		
		operationPersistance.delete(id);
		
		//TODO
		DeleteResponseValue deleteResponse = new DeleteResponseValue();
		deleteResponse.setDeleted(true);
		
		return deleteResponse;
	}
	
	
	@Override
	public OperationResponseValue getById(Long id) throws FonctionnelleException{

		OperationValue operation = operationPersistance.getById(id);
		
		if(operation != null){
			
			OperationResponseValue response = new OperationResponseValue();
			
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

			response.setOperation(operation);
			
			return response;
		}
		else{
			
			throw new FonctionnelleException("RGRF_CTRLEN_005", new String[]{Long.toString(id)});

		}
		
	}


	@Override
	public ListOperationResponseValue getAll() 
			throws FonctionnelleException, TechniqueException{

		List<OperationValue> listOperation = operationPersistance.getAll();
		
		ListOperationResponseValue listOperationResponse = new ListOperationResponseValue();
		listOperationResponse.setOperations(new TreeSet<>(listOperation));
		listOperationResponse.setTotalResults(listOperation.size());
		
		return listOperationResponse;
	}

	@Override
	public ResultatRechecheOperationValue rechercherMultiCritere(
			RechercheMulticritereCatalogueValue request) 
				throws FonctionnelleException, TechniqueException{

		ResultatRechecheOperationValue resultatRechecheOperation = new ResultatRechecheOperationValue();
		ResultatRechecheCatalogueValue result = operationPersistance.rechercherMultiCritere(request);
		
		if(result != null){
			
			resultatRechecheOperation.getList().addAll(result.getList());
			resultatRechecheOperation.setTotalResults(result.getList().size());
			
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
			
			for(OperationValue operation : resultatRechecheOperation.getList()){
				
				if(mapMachineIdDesignation.containsKey(operation.getMachineId())){
					operation.setMachineDesignation(mapMachineIdDesignation.get(operation.getMachineId()));
				}
				
				if(mapSectionIdDesignation.containsKey(operation.getSectionId())){
					operation.setSectionDesignation(mapSectionIdDesignation.get(operation.getSectionId()));
				}
			}
				
		}
		
		return resultatRechecheOperation;
	}

	
	private boolean validDoubleValue(Double val) {
		return val != null && val != 0;
	}
}
