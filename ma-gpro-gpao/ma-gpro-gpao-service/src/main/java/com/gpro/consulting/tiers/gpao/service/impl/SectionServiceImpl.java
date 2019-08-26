package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.section.value.SectionValue;
import com.gpro.consulting.tiers.gpao.domaine.ISectionDomaine;
import com.gpro.consulting.tiers.gpao.service.ISectionService;

/**
 * implementation of {@link ISectionService}
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */

@Service
@Transactional
public class SectionServiceImpl implements ISectionService{
	
	private static final Logger logger = LoggerFactory.getLogger(SectionServiceImpl.class);

	@Autowired
	ISectionDomaine sectionDomaine;
	
	@Override
	public String create(SectionValue sectionValue) {
		
		//LOGGER.info("createSection: Delegating request to Domaine layer.");
		
		return sectionDomaine.create(sectionValue);
	}

	@Override
	public SectionValue getById(Long id) {
		
		//LOGGER.info("getSectionById: Delegating request {} to Domaine layer."+id);
		
		return sectionDomaine.getById(id);
	}

	@Override
	public String update(SectionValue sectionValue) {
		
		//LOGGER.info("updateSection: Delegating request to Domaine layer.");
		
		return sectionDomaine.update(sectionValue);
	}

	@Override
	public void delete(Long id) {
		
		//LOGGER.info("deleteSection: Delegating request {} to Domaine layer."+id);
		
		sectionDomaine.delete(id);
	}

	@Override
	public List<SectionValue> getAll(){
		
		//LOGGER.info("getAll: Delegating request to Domaine layer.");
		
		return sectionDomaine.getAll();
	}
}
