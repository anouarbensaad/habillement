package com.gpro.consulting.tiers.commun.service.baseinfo.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.domaine.baseinfo.IBaseInfoDomaine;
import com.gpro.consulting.tiers.commun.service.baseinfo.IBaseInfoService;

/**
 * implementation of {@link IBaseInfoService}
 * 
 * @author Wahid Gazzah
 * @since 01/06/2016
 *
 */

@Service
@Transactional
public class BaseInfoServiceImpl implements IBaseInfoService{
	
	private static final Logger logger = LoggerFactory.getLogger(BaseInfoServiceImpl.class);

	@Autowired
	private IBaseInfoDomaine baseInfoDomaine;

	@Override
	public String create(BaseInfoValue baseInfoValue) {
		
		logger.info("createBaseInfo: Delegating request to Domaine layer.");
		
		return baseInfoDomaine.create(baseInfoValue);
	}

	@Override
	public BaseInfoValue getById(Long id) {
		
		return baseInfoDomaine.getById(id);
	}

	@Override
	public String update(BaseInfoValue baseInfoValue) {
		
		return baseInfoDomaine.update(baseInfoValue);
	}

	@Override
	public void delete(Long id) {
		
		baseInfoDomaine.delete(id);
	}

	@Override
	public List<BaseInfoValue> getAll() {
		
		logger.info("getAllBaseInfo: Delegating request id to Domaine layer.");
		
		return baseInfoDomaine.getAll();
	}

	public IBaseInfoDomaine getBaseInfoDomaine() {
		return baseInfoDomaine;
	}

	public void setBaseInfoDomaine(IBaseInfoDomaine baseInfoDomaine) {
		this.baseInfoDomaine = baseInfoDomaine;
	}
	
	
}
