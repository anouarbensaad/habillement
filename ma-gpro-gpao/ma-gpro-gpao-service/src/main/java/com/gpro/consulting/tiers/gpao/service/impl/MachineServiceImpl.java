package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.machine.value.MachineValue;
import com.gpro.consulting.tiers.gpao.domaine.IMachineDomaine;
import com.gpro.consulting.tiers.gpao.service.IMachineService;

/**
 * implementation of {@link IMachineService}
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */

@Service
@Transactional
public class MachineServiceImpl implements IMachineService{
	
	private static final Logger logger = LoggerFactory.getLogger(MachineServiceImpl.class);

	@Autowired
	IMachineDomaine machineDomaine;
	
	@Override
	public String create(MachineValue machineValue) {
		
		//LOGGER.info("createMachine: Delegating request to Domaine layer.");
		
		return machineDomaine.create(machineValue);
	}

	@Override
	public MachineValue getById(Long id) {
		
		//LOGGER.info("getMachineById: Delegating request {} to Domaine layer."+id);
		
		return machineDomaine.getById(id);
	}

	@Override
	public String update(MachineValue machineValue) {
		
		//LOGGER.info("updateMachine: Delegating request to Domaine layer.");
		
		return machineDomaine.update(machineValue);
	}

	@Override
	public void delete(Long id) {
		
		//LOGGER.info("deleteMachineValue: Delegating request {} to Domaine layer."+id);
		
		machineDomaine.delete(id);
	}

	@Override
	public List<MachineValue> getAll(){
		
		//LOGGER.info("getAll: Delegating request to Domaine layer.");
		
		return machineDomaine.getAll();
	}
}
