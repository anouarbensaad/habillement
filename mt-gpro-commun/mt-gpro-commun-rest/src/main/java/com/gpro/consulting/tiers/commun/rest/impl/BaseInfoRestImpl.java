package com.gpro.consulting.tiers.commun.rest.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.service.baseinfo.IBaseInfoService;

/**
 * Base Info Controller
 * 
 * @author Wahid Gazzah
 * @since 01/06/2016
 *
 */

@RestController
@RequestMapping("/baseinfo")
public class BaseInfoRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseInfoRestImpl.class);
	
	@Autowired
	private IBaseInfoService baseInfoService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody BaseInfoValue baseInfoValue) {
		
		logger.info("createBaseInfo: Delegating request to Service layer.");
		
		return baseInfoService.create(baseInfoValue);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public BaseInfoValue getById(@PathVariable Long id) {
		  
		logger.info("getOperationById: Delegating request id {} to service layer.", id);
		
		return baseInfoService.getById(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody BaseInfoValue baseInfoValue) {
	    
		logger.info("UpdateBaseInfo: Delegating request to service layer.");
		
		return baseInfoService.update(baseInfoValue);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		logger.info("deleteBaseInfo: Delegating request id {} to service layer.", id);
		  
		baseInfoService.delete(id);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<BaseInfoValue> getAll() {
		  
		logger.info("getAllBaseInfo: Delegating request id to service layer.");
		
		return baseInfoService.getAll();
	}

	public IBaseInfoService getBaseInfoService() {
		return baseInfoService;
	}

	public void setBaseInfoService(IBaseInfoService baseInfoService) {
		this.baseInfoService = baseInfoService;
	}
	
}
