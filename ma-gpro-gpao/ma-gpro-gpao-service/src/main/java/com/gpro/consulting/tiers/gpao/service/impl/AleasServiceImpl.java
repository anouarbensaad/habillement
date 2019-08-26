package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.aleas.AleasValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.domaine.IAleasDomaine;
import com.gpro.consulting.tiers.gpao.service.IAleasService;

/**
 * @author Hajer AMRI
 * @since 22/02/2017
 */

@Service
@Transactional
public class AleasServiceImpl implements IAleasService{

	private static final Logger logger = LoggerFactory.getLogger(AleasServiceImpl.class);

	@Autowired
	private IAleasDomaine aleasDomaine;

	@Override
	public String create(AleasValue aleas) {
		// TODO Auto-generated method stub
		return aleasDomaine.create(aleas);
	}

	@Override
	public AleasValue getById(Long id) {
		// TODO Auto-generated method stub
		return aleasDomaine.getById(id);
	}

	@Override
	public String update(AleasValue aleas) {
		// TODO Auto-generated method stub
		return aleasDomaine.update(aleas);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		aleasDomaine.delete(id);
	}

	@Override
	public List<AleasValue> getAllAleas() {
		// TODO Auto-generated method stub
		return aleasDomaine.getAllAleas();
	}
	
	

}
