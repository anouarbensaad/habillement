package com.gpro.consulting.tiers.gpao.persitance.gammemontage;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.machine.value.MachineValue;

/**
 * MAchine Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */
public interface IMachinePersistance {

	public String create(MachineValue machineValue);

	public MachineValue getById(Long id);

	public String update(MachineValue machineValue);

	public void delete(Long id);

	public List<MachineValue> getAll();
}
