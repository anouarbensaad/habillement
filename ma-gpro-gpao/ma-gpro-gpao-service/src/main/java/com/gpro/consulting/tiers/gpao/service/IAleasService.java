package com.gpro.consulting.tiers.gpao.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.aleas.AleasValue;

/**
 * @author Hajer AMRI
 * @since 22/02/2017
 */

public interface IAleasService {
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String create(AleasValue aleas);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public AleasValue getById(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public String update(AleasValue aleas);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public void delete(Long id);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<AleasValue> getAllAleas();


}
