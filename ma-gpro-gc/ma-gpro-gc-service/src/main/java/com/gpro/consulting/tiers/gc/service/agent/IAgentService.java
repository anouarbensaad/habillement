package com.gpro.consulting.tiers.gc.service.agent;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.agent.value.AgentValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IAgentService {
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String creer(AgentValue value);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void supprimer(Long pId);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String modifier(AgentValue value);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<AgentValue> listeAgent();
}
