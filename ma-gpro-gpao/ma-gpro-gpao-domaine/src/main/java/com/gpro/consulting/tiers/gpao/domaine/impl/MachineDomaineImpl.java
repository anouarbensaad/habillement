package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.machine.value.MachineValue;
import com.gpro.consulting.tiers.gpao.domaine.IMachineDomaine;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IMachinePersistance;

/**
 * implementation of {@link IMachineDomaine}
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */

@Component
public class MachineDomaineImpl implements IMachineDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(MachineDomaineImpl.class);
	
	@Autowired
	private IMachinePersistance machinePersistance;
	
	@Override
	public String create(MachineValue machineValue) {
		
		return machinePersistance.create(machineValue);
	}

	@Override
	public MachineValue getById(Long id) {
		
		return machinePersistance.getById(id);
	}

	@Override
	public String update(MachineValue machineValue) {
		
		return machinePersistance.update(machineValue);
	}

	@Override
	public void delete(Long id) {
		
		machinePersistance.delete(id);
	}

	@Override
	public List<MachineValue> getAll() {
		
		return machinePersistance.getAll();
	}

}
