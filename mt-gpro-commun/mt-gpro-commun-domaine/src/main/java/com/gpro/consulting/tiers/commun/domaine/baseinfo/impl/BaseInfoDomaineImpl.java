package com.gpro.consulting.tiers.commun.domaine.baseinfo.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.domaine.baseinfo.IBaseInfoDomaine;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.IBaseInfoPersistance;

/**
 * implementation of {@link IBaseInfoDomaine}
 * 
 * @author Wahid Gazzah
 * @since 01/06/2016
 *
 */

@Component
public class BaseInfoDomaineImpl implements IBaseInfoDomaine{

	private static final Logger logger = LoggerFactory.getLogger(BaseInfoDomaineImpl.class);
	
	@Autowired
	private IBaseInfoPersistance baseInfoPersistance;

	@Override
	public String create(BaseInfoValue baseInfoValue) {
		
		logger.info("createBaseInfo: Delegating request to Persisance layer.");
		
		return baseInfoPersistance.create(baseInfoValue);
	}

	@Override
	public BaseInfoValue getById(Long id) {
		
		return baseInfoPersistance.getById(id);
	}

	@Override
	public String update(BaseInfoValue baseInfoValue) {
		
		return baseInfoPersistance.update(baseInfoValue);
	}

	@Override
	public void delete(Long id) {
		
		baseInfoPersistance.delete(id);
	}

	@Override
	public List<BaseInfoValue> getAll() {
		
		logger.info("getAllBaseInfo: Delegating request id to Persistance layer.");
		
		return baseInfoPersistance.getAll();
	}

	public IBaseInfoPersistance getBaseInfoPersistance() {
		return baseInfoPersistance;
	}

	public void setBaseInfoPersistance(IBaseInfoPersistance baseInfoPersistance) {
		this.baseInfoPersistance = baseInfoPersistance;
	}

	@Override
	public BaseInfoValue getClientActif() {
		return this.baseInfoPersistance.getClientActif();
	}

	@Override
	public String getLogo() {
		return this.baseInfoPersistance.getLogo();
	}
	
	
}
