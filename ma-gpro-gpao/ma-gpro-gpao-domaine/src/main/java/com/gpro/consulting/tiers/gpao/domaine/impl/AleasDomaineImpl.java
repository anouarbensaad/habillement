package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.aleas.AleasValue;
import com.gpro.consulting.tiers.gpao.coordination.aleas.ElementAleasValue;
import com.gpro.consulting.tiers.gpao.domaine.IAleasDomaine;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IAleasPersistance;

/**
 * @author Hajer AMRI
 * @since 22/02/2017
 */

@Component
public class AleasDomaineImpl implements IAleasDomaine{

	private static final Logger logger = LoggerFactory.getLogger(AleasDomaineImpl.class);

	private static final Double ZERO = 0D;
	private static final Long ZEROL = 0L;
	
	@Autowired
	private IAleasPersistance aleasPersistance;
	

	@Override
	public String create(AleasValue feuilleSaisie) {
		// TODO Auto-generated method stub
		return aleasPersistance.create(feuilleSaisie);
	}

	@Override
	public AleasValue getById(Long id) {
		// TODO Auto-generated method stub
		return aleasPersistance.getById(id);
	}

	@Override
	public String update(AleasValue feuilleSaisie) {
		// TODO Auto-generated method stub
		return aleasPersistance.update(feuilleSaisie);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		aleasPersistance.delete(id);
		
	}

	@Override
	public List<AleasValue> getAllAleas() {
		// TODO Auto-generated method stub
		return aleasPersistance.getAllAleas();
	}
	
	
}
