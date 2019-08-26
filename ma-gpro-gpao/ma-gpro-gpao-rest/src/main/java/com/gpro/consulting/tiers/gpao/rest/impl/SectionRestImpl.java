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

import com.gpro.consulting.tiers.gpao.coordination.section.value.SectionValue;
import com.gpro.consulting.tiers.gpao.service.ISectionService;

/**
 * Section Controller
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */

@RestController
@RequestMapping("/section")
public class SectionRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(SectionRestImpl.class);
	
	@Autowired
	private ISectionService sectionService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody SectionValue sectionValue) {
		
		//LOGGER.info("createSection: Delegating request to Service layer.");
		
		return sectionService.create(sectionValue);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public SectionValue getById(@PathVariable Long id) {
		  
		//LOGGER.info("getSectionById: Delegating request id {} to service layer.", id);
		
		return sectionService.getById(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody SectionValue sectionValue) {
	    
		//LOGGER.info("UpdateSection: Delegating request to service layer.");
		
		return sectionService.update(sectionValue);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		//LOGGER.info("deleteSection: Delegating request id {} to service layer.", id);
		  
		sectionService.delete(id);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<SectionValue> getAll() {
		  
		//LOGGER.info("getAllSection: Delegating request id to service layer.");
		
		return sectionService.getAll();
	}

}
