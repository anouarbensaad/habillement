package com.gpro.consulting.tiers.gpao.domaine;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.machine.value.MachineValue;

public interface IMachineDomaine {

	public String create(MachineValue machineValue);

	public MachineValue getById(Long id);

	public String update(MachineValue machineValue);

	public void delete(Long id);

	public List<MachineValue> getAll();
}
