package com.gpro.consulting.tiers.gpao.rest.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.gpao.coordination.machine.value.MachineValue;
import com.gpro.consulting.tiers.gpao.service.IMachineService;

/**
 * Machine Controller
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */

@RestController
@RequestMapping("/machine")
public class MachineRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(MachineRestImpl.class);
	
	@Autowired
	private IMachineService machineService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody MachineValue machineValue) {
		
		//LOGGER.info("createMachine: Delegating request to Service layer.");
		
		return machineService.create(machineValue);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public MachineValue getById(@PathVariable Long id) {
		  
		//LOGGER.info("getMachineById: Delegating request id {} to service layer.", id);
		
		return machineService.getById(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody MachineValue machineValue) {
	    
		//LOGGER.info("UpdateMachine: Delegating request to service layer.");
		
		return machineService.update(machineValue);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//LOGGER.info("deleteMachine: Delegating request id {} to service layer.", id);
		  
		machineService.delete(id);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<MachineValue> getAll() {
		  
		//LOGGER.info("getAllMachine: Delegating request id to service layer.");
		
		return machineService.getAll();
	}
}
