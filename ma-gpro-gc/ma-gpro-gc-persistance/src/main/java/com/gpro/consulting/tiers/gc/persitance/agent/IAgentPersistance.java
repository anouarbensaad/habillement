package com.gpro.consulting.tiers.gc.persitance.agent;

import java.util.List;

import com.gpro.consulting.tiers.gc.coordination.agent.value.AgentValue;

public interface IAgentPersistance {

	public String creer(AgentValue value);
	
	public void supprimer(Long pId);
	
	public String modifier(AgentValue value);
	
	public List<AgentValue> listeAgent();
}
