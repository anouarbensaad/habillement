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

import com.gpro.consulting.tiers.gpao.coordination.aleas.AleasValue;
import com.gpro.consulting.tiers.gpao.service.IAleasService;

/**
 * @author Hajer AMRI
 * @since 22/02/2017
 */

@RestController
@RequestMapping("/aleas")
public class AleasRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(AleasRestImpl.class);
	
	@Autowired
	private IAleasService aleasService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<AleasValue> getAllAleas() {
		 
		//LOGGER.info("getAllAleas: Delegating request to service layer.");

		return aleasService.getAllAleas();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody AleasValue aleas) {
		
		//LOGGER.info("createAleas: Delegating request to Service layer.");
		//LOGGER.info("createAleas:Rest"+aleas);

		return aleasService.create(aleas);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public AleasValue getById(@PathVariable Long id) {
		  
		//LOGGER.info("getAleasById: Delegating request id {} to service layer.", id);
		
		return aleasService.getById(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody AleasValue aleas) {
	    
		//LOGGER.info("UpdateAleas: Delegating request to service layer.");
		
		return aleasService.update(aleas);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//LOGGER.info("deleteAleas: Delegating request id {} to service layer.", id);
		  
		aleasService.delete(id);
	}
	
	
}
