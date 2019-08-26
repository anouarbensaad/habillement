package com.gpro.consulting.tiers.gpao.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.machine.value.MachineValue;

/**
 * @author Wahid Gazzah
 * @since 08/04/2016
 */
public interface IMachineService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(MachineValue machineValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public MachineValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(MachineValue machineValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<MachineValue> getAll();
}
