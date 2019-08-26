package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.section.value.SectionValue;
import com.gpro.consulting.tiers.gpao.domaine.ISectionDomaine;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.ISectionPersistance;

/**
 * implementation of {@link ISectionDomaine}
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */

@Component
public class SectionDomaineImpl implements ISectionDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(SectionDomaineImpl.class);
	
	@Autowired
	private ISectionPersistance sectionPersistance;
	
	@Override
	public String create(SectionValue sectionValue) {
		
		return sectionPersistance.create(sectionValue);
	}

	@Override
	public SectionValue getById(Long id) {
		
		return sectionPersistance.getById(id);
	}

	@Override
	public String update(SectionValue sectionValue) {
		
		return sectionPersistance.update(sectionValue);
	}

	@Override
	public void delete(Long id) {
		
		sectionPersistance.delete(id);
	}

	@Override
	public List<SectionValue> getAll() {
		
		return sectionPersistance.getAll();
	}

}
