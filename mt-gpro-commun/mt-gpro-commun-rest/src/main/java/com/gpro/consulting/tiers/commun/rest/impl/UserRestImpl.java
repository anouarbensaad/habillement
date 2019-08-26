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

import com.gpro.consulting.tiers.commun.coordination.login.value.RechercheMulticritereUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.ResultatRechecheUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;
import com.gpro.consulting.tiers.commun.service.login.IUserService;

/**
 * User Login Controller
 * 
 * @author Wahid Gazzah
 * @since 03/06/2016
 *
 */

@RestController
@RequestMapping("/user")
public class UserRestImpl {

	private static final Logger logger = LoggerFactory.getLogger(UserRestImpl.class);
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/rechercheMulticritere", method = RequestMethod.POST, produces = "application/json")
	public ResultatRechecheUserValue rechercherMultiCritere(@RequestBody RechercheMulticritereUserValue request) {
		 
		logger.info("rechercheMulticritere: Delegating request to service layer.");

		return userService.rechercherMultiCritere(request);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public UserValue login(@RequestBody UserValue request) {
		 
		logger.info("login: Delegating request to service layer.");

		return userService.login(request);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@RequestBody UserValue controleValue) {
		
		logger.info("createUser: Delegating request to Service layer.");
		
		return userService.create(controleValue);
	}
	
	@RequestMapping(value = "/getById:{id}", method = RequestMethod.GET, produces = "application/json")
	public UserValue getById(@PathVariable Long id) {
		  
		logger.info("getUserById: Delegating request id {} to service layer.", id);
		
		return userService.getById(id);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody UserValue controleValue) {
	    
		logger.info("UpdateUser: Delegating request to service layer.");
		
		return userService.update(controleValue);
	}
	  
	@RequestMapping(value = "/delete:{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		  
		logger.info("deleteUser: Delegating request id {} to service layer.", id);
		  
		userService.delete(id);
	}
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = "application/json")
	public List<UserValue> getAll() {
		  
		logger.info("getAllUser: Delegating request id to service layer.");
		
		return userService.getAll();
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	
}
