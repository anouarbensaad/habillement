package com.gpro.consulting.tiers.commun.domaine.login.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.login.value.RechercheMulticritereUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.ResultatRechecheUserValue;
import com.gpro.consulting.tiers.commun.coordination.login.value.UserValue;
import com.gpro.consulting.tiers.commun.domaine.login.IUserDomaine;
import com.gpro.consulting.tiers.commun.persistance.login.IUserPersistance;

/**
 * implementation of {@link IUserDomaine}
 * 
 * @author Wahid Gazzah
 * @since 03/06/2016
 *
 */

@Component
public class UserDomaineImpl implements IUserDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(UserDomaineImpl.class);
	
	@Autowired
	private IUserPersistance userPersistance;
	
	@Override
	public String create(UserValue userValue) {
		
		return userPersistance.create(userValue);
	}

	@Override
	public UserValue getById(Long id) {
		
		UserValue User = userPersistance.getById(id);
		
		return User;
	}

	@Override
	public String update(UserValue userValue) {
		
		return userPersistance.update(userValue);
	}

	@Override
	public void delete(Long id) {
		
		userPersistance.delete(id);
	}

	@Override
	public List<UserValue> getAll() {
		
		List<UserValue> listUser = userPersistance.getAll();
		
		return listUser;
	}

	@Override
	public ResultatRechecheUserValue rechercherMultiCritere(
			RechercheMulticritereUserValue request) {
		
		ResultatRechecheUserValue result = userPersistance.rechercherMultiCritere(request);
		
		return result;
	}
	
	@Override
	public UserValue login(UserValue request) {
		
		return userPersistance.login(request);
	}

	public IUserPersistance getUserPersistance() {
		return userPersistance;
	}

	public void setUserPersistance(IUserPersistance userPersistance) {
		this.userPersistance = userPersistance;
	}
	
	
	
}
