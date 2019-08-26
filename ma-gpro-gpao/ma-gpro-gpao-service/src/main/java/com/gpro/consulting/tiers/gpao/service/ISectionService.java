package com.gpro.consulting.tiers.gpao.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.section.value.SectionValue;

/**
 * @author Wahid Gazzah
 * @since 08/04/2016
 */
public interface ISectionService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(SectionValue sectionValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public SectionValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(SectionValue sectionValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<SectionValue> getAll();
}
